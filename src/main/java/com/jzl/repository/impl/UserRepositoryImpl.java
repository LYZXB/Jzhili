package com.jzl.repository.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jzl.beans.user.User;
import com.jzl.repository.UserRepository;

/**
 * 
 * @ClassName: UserRepositoryImpl  
 * @Description: 用户数据处理接口实现类  
 * @author Sombra  
 * @date 2017年12月18日 下午3:56:01  
 * @version V1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Long queryCountByUserName(String userName) {
		String sql = "select count(*) from user where userName = ?";
		return jdbcTemplate.queryForObject(sql, Long.class, userName);
	}

	@Override
	public String queryPasswordByUserName(String userName) {
		String sql = "select password from user where userName = ?";
		return jdbcTemplate.queryForObject(sql, String.class, userName);
	}

	@Override
	public Integer save(User user) {
		String sql = "insert into user (userName, password) values (?, ?)";
		return jdbcTemplate.update(sql, user.getUserName(), user.getPassword());
	}

	@Override
	public User findUserInfoByUserName(String userName) {
		String sql = "select * from user where userName = ?";
		return jdbcTemplate.queryForObject(sql, (rs,num) -> {
			User user = new User();
			user.setUserName(rs.getString("userName"));
			user.setPhone(rs.getString("phone"));
			return user;
		},userName);
	}

	@Override
	public Integer update(User user) {
		String sql = "update user set nick_name = ?, phone = ?, email = ?, wechatcode = ?, qqcode = ? where userName = ?";
		return jdbcTemplate.update(sql, user.getNickName(), user.getPhone(), user.getEmail(), user.getWechatcode(), user.getQqcode(), user.getUserName());
	}

}
