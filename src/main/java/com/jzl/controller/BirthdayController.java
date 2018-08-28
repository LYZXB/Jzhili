package com.jzl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzl.beans.birthday.Birthday;
import com.jzl.beans.common.LayTableData;
import com.jzl.beans.common.PageBean;
import com.jzl.beans.user.User;
import com.jzl.service.BirthdayService;
import com.jzl.utils.JsonUtils;

/**
 * 
 * @ClassName: BirthdayController  
 * @Description: 生日管理控制器  
 * @author Sombra  
 * @date 2017年11月20日 下午5:35:55  
 * @version V1.0
 */
@Controller
@RequestMapping("/birthday")
public class BirthdayController {
	private Logger LOGGER = Logger.getLogger(BirthdayController.class);
	
	@Resource
	BirthdayService birthdayService;
	
	/**
	 * <ul>
	 * 	<li> 1. 随机生成Token_everyTime
	 * </ul>
	 * @Title: index  
	 * @Description: 程序登录成功后的入口  
	 * @return  String    返回类型 
	 */
	@RequestMapping(value="/index")
	public String index() {
		return "/view/birthday";
	}
	
	@RequestMapping(value="/showBirthday",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showBirthday(HttpSession httpSession, PageBean pagebean) {
		
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		LayTableData  birthdayList = birthdayService.findAll(userName, pagebean);
		String listJson = JsonUtils.toJson(birthdayList);
		
		LOGGER.info("返回生日数据:" + listJson);
		return listJson;
	}
	
	/**
	 * @Title: insertBirthday  
	 * @Description: 新增生日数据  
	 * @param birthday  存在字段：万年历生日、姓名、模式； 还需要字段：下一次生日、年龄、权限标志、农历生日
	 * @param httpSession
	 * @return  Birthday    返回类型 
	 */
	@RequestMapping(value="/insertBirthday",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Birthday insertBirthday(Birthday birthday, HttpSession httpSession) {
		LOGGER.info("新增生日数据：" + birthday.getGco());
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		return birthdayService.insertBirthday(birthday, userName);
	}

	/**
	 * 
	 * @Title: updateBirthday  
	 * @Description: 修改生日数据
	 * @param birthday 需要字段： 下一次生日、年龄
	 * @return  Integer    返回类型 
	 */
	@RequestMapping(value="/updateBirthday",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Integer updateBirthday(String birthdays) {
		LOGGER.info("修改生日数据：" + birthdays);
		Gson gson = new Gson();
		List<Birthday> birthdayList = gson.fromJson(birthdays, new TypeToken<List<Birthday>>() {}.getType()); 
		return birthdayService.updateBirthday(birthdayList);
	}
	
	@RequestMapping(value="/delBirthday",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Integer deleteBirthday(String gcos) {
		LOGGER.info("删除生日数据：" + gcos);
		String[] gcoz = gcos.split(",");
		Integer count = 0;
		for (String gco : gcoz) {
			count += birthdayService.delBirthday(gco);
		}
		return count;
	}
}
