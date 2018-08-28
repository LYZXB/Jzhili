package com.jzl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.User;
import com.jzl.service.UserService;
import com.jzl.utils.CookieUtils;

/**
 * 
 * @ClassName: IndexController  
 * @Description: 系统入口  
 * @author Sombra  
 * @date 2018年3月21日 下午2:54:53  
 * @version V1.0
 */
@Controller
public class IndexController {
	private Logger LOGGER = Logger.getLogger(IndexController.class);
	
	@Resource
	UserService userServcie;
	
	/**
	 * <ol>
	 * 	<li> 判断cookies值是否正确。
	 * 	<li> 正确则刷新token__aways_change，并直接登进系统
	 * 	<li> 错误则跳转到登录页面
	 * </ol>
	 * @Title: index  
	 * @Description: 入口方法  
	 * @return  String    返回类型 
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("程序入口，检查cookies是否直接进入系统免登陆");
		// 1. 检查cookies
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1) {
			return "/userman/login";
		}
		Map<String, String> cookieMap = new HashMap<>();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
		Integer checkResult = CookieUtils.check(cookieMap);
		
		// 2. 正确，刷新cookie中的aways_change_token，并且加入会话。重定向到生日页面
		if (checkResult.equals(CookieUtils.CHECK__SUSESSE)) {
			LOGGER.info("检查成功，直接进入系统");
			String userName = cookieMap.get(User.TOKEN__LOGIN_CHANGE).split("_")[0];
			String newToken = CookieUtils.refreshAwaysChangeToken(userName);
			
			response.addCookie(new Cookie(User.TOKEN__AWAYS_CHANGE, newToken));
			request.getSession().setAttribute(User.SessionName, userName);
			return "redirect:/birthday/index.do";
		} 
		
		// 3. 错误，返回登录页面
		else {
			LOGGER.info("检查失败，跳转到登录页面");
			if (checkResult.equals(CookieUtils.CHECK__THEFT_FAILED)) {
				LOGGER.info("用户有被盗危险，提示");
				request.setAttribute("sheftError", true);
			}
			return "/userman/login";
		}
	}
	
	/**
	 * <ul>
	 * 	<li> 验证用户名密码
	 * 	<li> 登录正确，如果选择了remember me选项，刷新token_login TODO
	 * </ul>
	 * @Title: login  
	 * @Description: 登录方法  
	 * @param user
	 * @param httpSession
	 * @return  Message    返回类型 
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Message login(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("登录开始!");
		User user = new User();
		user.setUserName(map.get("userName"));
		user.setPassword(map.get("password"));
		boolean rememberme = Boolean.valueOf(map.get("rememberme"));
		Message message = userServcie.login(user);
		if (message.isSuccess()) {
			String userName = user.getUserName();
			// 加入会话信息
			request.getSession().setAttribute(User.SessionName, userName);
			
			// 如果remember me，则加入cookies信息awaysChangeToken&loginChangeToken
			if (rememberme) {
				String awaysChangeToken = CookieUtils.refreshAwaysChangeToken(userName);
				String loginChangeToken = CookieUtils.refreshLoginChangeToken(userName);
				response.addCookie(new Cookie(User.TOKEN__AWAYS_CHANGE, awaysChangeToken));
				response.addCookie(new Cookie(User.TOKEN__LOGIN_CHANGE, loginChangeToken));
			}
		}
		
		return message;
	}
}
