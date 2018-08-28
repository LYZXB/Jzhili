package com.jzl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jzl.beans.user.User;

/**
 * 
 * @ClassName: SystemSessionInterceptor
 * @Description: 用户会话拦截器
 * @author Sombra
 * @date 2017年12月28日 上午11:28:44
 * @version V1.0
 */
public class SystemSessionInterceptor implements HandlerInterceptor {

	/**
	 * 所有的springmvc请求都要进过此方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);
		// session中获取用户名信息
		Object obj = session.getAttribute(User.SessionName);
		if (obj == null || "".equals(obj.toString())) {
			response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
