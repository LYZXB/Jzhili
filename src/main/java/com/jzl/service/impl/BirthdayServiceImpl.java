package com.jzl.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jzl.beans.birthday.Birthday;
import com.jzl.beans.common.LayTableData;
import com.jzl.beans.common.PageBean;
import com.jzl.beans.common.SMSTemplateParam;
import com.jzl.beans.user.PushSetting;
import com.jzl.beans.user.User;
import com.jzl.repository.BirthdayRepository;
import com.jzl.service.BirthdayService;
import com.jzl.service.DataPermissionService;
import com.jzl.service.IncrementerService;
import com.jzl.service.UserService;
import com.jzl.utils.DateUtil;
import com.jzl.utils.SolarLunarUtils;

/**
 * 
 * @ClassName: BirthdayServiceImpl  
 * @Description: 生日逻辑处理类
 * @author Sombra  
 * @date 2017年11月22日 下午5:10:50  
 * @version V1.0
 */
@Service
public class BirthdayServiceImpl implements BirthdayService {
	
	@Resource
	BirthdayRepository birthdayRepository;
	@Resource
	DataPermissionService dataPermissionService;
	@Resource
	IncrementerService incrementerService;
	@Resource
	UserService userService;

	@Override
	public LayTableData findAll(String userName, PageBean pagebean) {
		String dataSigns = dataPermissionService.queryAllDataPermissionByUserName(userName);
		LayTableData data = new LayTableData();
		data.setCode(0);
		data.setMsg("查询生日数据成功");
		data.setCount(birthdayRepository.queryAllCount(dataSigns));
		data.setData(birthdayRepository.queryAll(dataSigns, pagebean));
		return data;
	}

	@Override
	public Integer delBirthday(String gco) {
		return birthdayRepository.delBirthday(gco);
	}

	@Override
	public Birthday insertBirthday(Birthday birthday, String userName) {
		String gco = incrementerService.getNextNumber("birthday");
		birthday.setGco(gco);
		fillBirthday(birthday);
		String dataSigns = dataPermissionService.queryDefaultDataPermiassionByUserName(userName);
		birthday.setDataSigns(dataSigns);
		
		Integer count = birthdayRepository.insertBirthday(birthday);
		if (count < 1)
			return null;
		
		return birthday;
	}
	
	@Override
	public Integer updateBirthday(List<Birthday> birthdays) {
		for (Birthday birthday : birthdays) {
			fillBirthday(birthday);
		}
		birthdayRepository.updateBirthday(birthdays);
		return 1;
	}
	
	
	/**
	 * 年龄、农历生日、下一次生日、剩余天数
	 * @Title: fillBirthday  
	 * @Description: 填充生日数据
	 * @param birthday  void    返回类型 
	 */
	private void fillBirthday(Birthday birthday) {
		String solarBirthdayStr = birthday.getBirthday(); // 公历生日字符串

		birthday.setAge(getAgeByBirthday(solarBirthdayStr));// 年龄
		birthday.setLunar_birthday(solar2Lunar(solarBirthdayStr));// 农历生日
		String nextSolarStr = getNextSolarByBirthday(solarBirthdayStr);
		birthday.setNext_solar(nextSolarStr);// 下一个公历生日
		birthday.setSolar_days(DateUtil.daysBetweenForNow(nextSolarStr));
		String nextLunarStr = getNextLunarByBirthday(birthday.getLunar_birthday());
		birthday.setNext_lunar(nextLunarStr);// 下一个农历生日，公历
		birthday.setLunar_days(DateUtil.daysBetweenForNow(nextLunarStr));
		birthday.setType(Birthday.TYPE_LUNAE); // 默认农历
		birthday.setUpdateTime(System.currentTimeMillis()); // 修改时间
	}
	
	/**
	 * @Title: getAgeByBirthday  
	 * @Description: 根据万年历生日，计算出年龄 (只计算年)
	 * @param birthday
	 * @param currentYear 当前年
	 * @return  Integer    返回类型 
	 */
	private Integer getAgeByBirthday(String solarBirthdayStr) {
		Integer currentYearInt = getCurrentYearInt();
		int birthdayYearInt = Integer.valueOf(solarBirthdayStr.split(" ")[0].split("-")[0]);// YYYY-MM-DD HH:mm:ss
		
		return currentYearInt - birthdayYearInt;
	}
	
	/**
	 * @Title: solar2Lunar  
	 * @Description: 万年历转农历
	 * @param solarBirthdayStr yyyy-MM-dd HH:mm:ss
	 * @return  String    返回类型 
	 */
	private String solar2Lunar(String solarBirthdayStr) {
		String solarStr = solarBirthdayStr.split(" ")[0].replace("-", "");// YYYYMMDD
		try {
			return SolarLunarUtils.solarToLunar(solarStr);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @Title: getNextSolarByBirthday  
	 * @Description: 根据万年历生日，计算出下一次万年历生日
	 * @param birthday
	 * @return  String    返回类型 
	 */
	private String getNextSolarByBirthday(String solarBirthdayStr) {
		Integer currentYearInt = getCurrentYearInt();

		boolean thisYearIsOver = isBirthdayDone(solarBirthdayStr);
		if (thisYearIsOver) {
			return solarBirthdayStr.replace(solarBirthdayStr.substring(0, 4), String.valueOf(currentYearInt+1));
		}
		return solarBirthdayStr.replace(solarBirthdayStr.substring(0, 4), currentYearInt.toString());
	}

	/**
	 * 
	 * @Title: getCurrentYearInt  
	 * @Description: 获取当前时间年整形数据  
	 * @return  Integer    返回类型 
	 */
	private Integer getCurrentYearInt() {
		Calendar currentCalendar = Calendar.getInstance(); // 当前时间日历
		Integer currentYearInt = currentCalendar.get(Calendar.YEAR);
		return currentYearInt;
	}
	
	/**
	 * 今年生日是否过去
	 * @Title: isBirthdayDone  
	 * @Description: 通过传入的公历生日，判断今年生日是否过去
	 * @param solarBirthdayStr
	 * @return  Boolean    返回类型 
	 */
	private Boolean isBirthdayDone(String solarBirthdayStr) {
		Calendar currentCalendar = Calendar.getInstance(); // 当前时间日历
		Calendar birthdayCalendar = Calendar.getInstance(); // 公历生日时间日历
		birthdayCalendar.setTime(DateUtil.string2Date(solarBirthdayStr));
		if (currentCalendar.get(Calendar.MONTH) < birthdayCalendar.get(Calendar.MONTH)) {
			return false;
		}
		if (currentCalendar.get(Calendar.MONTH) == birthdayCalendar.get(Calendar.MONTH)
				&& currentCalendar.get(Calendar.DAY_OF_MONTH) < birthdayCalendar.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private Boolean isBirthdayDone(String solarBirthdayStr, String currentStr) {
		Calendar currentCalendar = Calendar.getInstance(); // 当前时间日历
		Calendar birthdayCalendar = Calendar.getInstance(); // 公历生日时间日历
		birthdayCalendar.setTime(DateUtil.string2Date(solarBirthdayStr));
		currentCalendar.setTime(DateUtil.string2Date(currentStr));
		if (currentCalendar.get(Calendar.MONTH) < birthdayCalendar.get(Calendar.MONTH)) {
			return false;
		}
		if (currentCalendar.get(Calendar.MONTH) == birthdayCalendar.get(Calendar.MONTH)
				&& currentCalendar.get(Calendar.DAY_OF_MONTH) < birthdayCalendar.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @Title: getNextLunarByBirthday  
	 * @Description: 根据农历生日，计算出下一次农历生日(万年历表示)
	 * @param lunarBirthdayStr 农历生日
	 * @return  String    返回类型 
	 */
	private String getNextLunarByBirthday(String lunarBirthdayStr) {
		// 计算出下一次农历生日
		Integer currentYearInt = getCurrentYearInt();
		lunarBirthdayStr = lunarBirthdayStr.split(":")[1];
		lunarBirthdayStr = lunarBirthdayStr.replace(lunarBirthdayStr.substring(0, 4), currentYearInt.toString());
		String solarBirthdayStr = lunar2Solar(lunarBirthdayStr);
		boolean isBirthdayDone = isBirthdayDone(solarBirthdayStr);
		if (isBirthdayDone) {
			lunarBirthdayStr = lunarBirthdayStr.replace(lunarBirthdayStr.substring(0, 4), String.valueOf(currentYearInt+1));
		} else {
			lunarBirthdayStr = lunarBirthdayStr.replace(lunarBirthdayStr.substring(0, 4), currentYearInt.toString());
		}
		
		return lunar2Solar(lunarBirthdayStr);
	}
	
	/**
	 * @Title: lunar2Solar  
	 * @Description: 农历转万年历  
	 * @param lunarBirthdayStr
	 * @return  String    返回类型 
	 */
	private String lunar2Solar(String lunarBirthdayStr) {
		Matcher m = Pattern.compile("\\d+").matcher(lunarBirthdayStr);
		StringBuilder sb = new StringBuilder();
		while(m.find()) {
			sb.append(fillDateStr(m.group()));
		}
		boolean leapMonthFlag = lunarBirthdayStr.indexOf("闰") != -1 ? true : false;
		try {
			String solarStr = SolarLunarUtils.lunarToSolar(sb.toString(), leapMonthFlag);
			Date solarDate = DateUtil.string2DateNoTime(solarStr);
			return DateUtil.date2String(solarDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Birthday> findAllNeedUpdateBirthday(int count) {
		
		return birthdayRepository.findAllNeedUpdateBirthday();
	}

	@Override
	public Queue<SMSTemplateParam> findAllSMSPushData(PushSetting setting) {
		Integer days = setting.getForwardDays();
		User user = userService.findUserInfoByUserName(setting.getUserName());
		String phoneNum = user.getPhone();

		Queue<SMSTemplateParam> smsQueue = new LinkedBlockingQueue<>();
		// 1. 查询出当天数据
		List<SMSTemplateParam> todayData = birthdayRepository.findAllNeedPushByLunarDays(0).stream().map(birthday -> {
			SMSTemplateParam param = new SMSTemplateParam();
			param.setName(birthday.getName());
			param.setPhone(phoneNum);
			param.setBirthday(birthday.getBirthday());
			param.setDays(0);
			return param;
		}).collect(Collectors.toList());
		
		// 2. 查询出匹配天数数据
		List<SMSTemplateParam> daysData = birthdayRepository.findAllNeedPushByLunarDays(days).stream().map(birthday -> {
			SMSTemplateParam param = new SMSTemplateParam();
			param.setName(birthday.getName());
			param.setPhone(phoneNum);
			param.setBirthday(birthday.getBirthday());
			param.setDays(days);
			return param;
		}).collect(Collectors.toList());
		
		smsQueue.addAll(todayData);
		smsQueue.addAll(daysData);
		return smsQueue;
	}
	
	private String fillDateStr(String dateStr) {
		if (dateStr.length() == 1) {
			return 0+dateStr;
		}
		return dateStr;
	}
}
