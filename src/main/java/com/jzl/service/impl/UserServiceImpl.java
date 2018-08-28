package com.jzl.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.User;
import com.jzl.repository.UserRepository;
import com.jzl.service.DataPermissionService;
import com.jzl.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl  
 * @Description: 用户处理接口实现类  
 * @author Sombra  
 * @date 2017年12月18日 下午3:39:44  
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	UserRepository userRepository;
	@Resource
	DataPermissionService dataPermissionService;

	@Override
	public Message register(User user) {
		// 1. 判断是否可以注册
		if (this.isexist(user.getUserName())) {
			return new Message(false, "用户已存在!");
		}
		
		// 2. 生成专用数据权限
		String dataSigns = dataPermissionService.initDataPermissionForUser(user.getUserName());
		if (dataSigns == null) {
			return new Message(false, "生成用户数据权限失败!");
		}
		
		// 3. 存入数据库
		userRepository.save(user);
		
		return Message.SUCCESS;
	}
	
	@Override
	public Message login(User user) {
		
		String userName = user.getUserName();
		
		// 1. 判断是否存在
		if (!isexist(userName)) {
			return new Message(false, "用户不存在!");
		}
		
		// 2. 判断验证码（暂时不做）

		// 3. 判断密码
		String password = userRepository.queryPasswordByUserName(userName);
		if (StringUtils.isEmpty(password) || !password.equals(user.getPassword())) {
			return new Message(false, "用户密码不匹配!");
		}
		
		return new Message(true, "登录成功!");
	}

	@Override
	public boolean isexist(String userName) {
		Long userCount = userRepository.queryCountByUserName(userName);

		if (userCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User findUserInfoByUserName(String userName) {
		return userRepository.findUserInfoByUserName(userName);
	}

	@Override
	public Message update(User user) {
		userRepository.update(user);
		return Message.SUCCESS;
	}

}
