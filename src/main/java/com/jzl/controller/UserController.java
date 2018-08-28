package com.jzl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jzl.beans.common.Message;
import com.jzl.beans.user.PushSetting;
import com.jzl.beans.user.User;
import com.jzl.enums.PushTypeEnum;
import com.jzl.service.PushSettingService;
import com.jzl.service.UserService;

/**
 * 注册、登陆、修改密码、修改手机号、修改邮箱、修改用户信息
 * @ClassName: UserController  
 * @Description: 用户控制器  
 * @author Sombra  
 * @date 2017年11月6日 下午4:46:42  
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Resource
	UserService userServcie;
	@Resource
	PushSettingService pushSettingService;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/userman/login";
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Message register(@RequestBody User user, HttpSession httpSession) {
		LOGGER.info("注册开始!");
		
		Message message = userServcie.register(user);
		if (message.isSuccess()) {
			httpSession.setAttribute(User.SessionName, user.getUserName());
		}
		
		return message;
	}
	
	@RequestMapping("/updatePassword")
	public String updatePassword() {
		return "/user/updatePassword";
	}
	
	@RequestMapping("/toUserInfo")
	public String toUserInfo(Model model, HttpSession httpSession) {
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		User user = userServcie.findUserInfoByUserName(userName);
		Gson gson = new Gson();
		model.addAttribute("user", gson.toJson(user));
		return "/view/userInfo";
	}
	@RequestMapping("/saveUserInfo")
	@ResponseBody
	public Message saveUserInfo(HttpSession httpSession, User userInfo) {
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		userInfo.setUserName(userName);
		return userServcie.update(userInfo);
	}
	
	@RequestMapping("/pushSet")
	public String pushSet(Model model, HttpSession httpSession) {
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		PushSetting pushSet = pushSettingService.findPushByUsername(userName, PushTypeEnum.SMS);
		if (pushSet != null)
			model.addAttribute("gco", pushSet.getGco());
		Gson gson = new Gson();
		model.addAttribute("pushSet", gson.toJson(pushSet));
		return "/view/pushSet";
	}
	@RequestMapping("/savePushSet")
	@ResponseBody
	public Message savePushSet(HttpSession httpSession, PushSetting pushSet) {
		String userName = String.valueOf(httpSession.getAttribute(User.SessionName));
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		pushSet.setUserName(userName);
		return pushSettingService.savePushSet(pushSet);
	}
}
