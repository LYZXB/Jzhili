package com.jzl.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzl.utils.DateUtil;
import com.jzl.utils.SolarLunarUtils;

/**
 * 
 * @ClassName: DateConvertController  
 * @Description: 时间转换控制器
 * @author Sombra  
 * @date 2018年6月13日 下午5:48:52  
 * @version V1.0
 */
@Controller
@RequestMapping("/dateConvert")
public class DateConvertController {
	Logger LOGGER = Logger.getLogger(DateConvertController.class);
	
	@RequestMapping(value="/index")
	public String index(Model model) {
		String solarDate = DateUtil.long2String(System.currentTimeMillis(), "yyyyMMdd");
		try {
			String lunarStr = SolarLunarUtils.solarToLunar(solarDate);
			// 公历信息
			
			// 节日信息
			
			// 农历信息
			
			model.addAttribute("lunarStr", lunarStr.split(":")[1]);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		return "/view/solar_lunar";
	}
	
	@RequestMapping(value="solar2Lunar")
	@ResponseBody
	public String solar2Lunar(String year, String month, String day) {
		if (StringUtils.isEmpty(year) || StringUtils.isEmpty(year) || StringUtils.isEmpty(year)) {
			return "请先填写需要转换的数据";
		}
		try {
			return SolarLunarUtils.solarToLunar(year+fillMonth(month)+fillDay(day)).split("：")[1];
		} catch (Exception e) {
			LOGGER.error(e);
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="lunar2Solar")
	@ResponseBody
	public String lunar2Solar(String year, String month, String day, String leapMonthFlag) {
		if (StringUtils.isEmpty(year) || StringUtils.isEmpty(year) || StringUtils.isEmpty(year)) {
			return "请先填写需要转换的数据";
		}
		try {
			String solar = SolarLunarUtils.lunarToSolar(year+fillMonth(month)+fillDay(day), "on".equals(leapMonthFlag));
			return DateUtil.long2String(DateUtil.string2Long(solar, "yyyyMMdd"), "yyyy年MM月dd日");
		} catch (Exception e) {
			LOGGER.error(e);
			return e.getMessage();
		}
	}
	
	private String fillMonth(String month) {
		if (month.length() == 1) {
			return 0+month;
		}
		return month;
	}
	private String fillDay(String day) {
		if (day.length() == 1) {
			return 0+day;
		}
		return day;
	}
}
