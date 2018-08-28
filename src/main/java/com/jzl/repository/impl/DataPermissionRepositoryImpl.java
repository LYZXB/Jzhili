package com.jzl.repository.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jzl.beans.common.Permission;
import com.jzl.repository.DataPermissionRepository;

/**
 * 
 * @ClassName: DataPermissionRepositoryImpl  
 * @Description: 数据权限数据操作接口实现类  
 * @author Sombra  
 * @date 2017年12月19日 下午3:04:58  
 * @version V1.0
 */
@Repository
public class DataPermissionRepositoryImpl implements DataPermissionRepository {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String queryAllDataPermissionByUserName(String userName) {
		String sql = "select GROUP_CONCAT(dataSigns) dataSigns from permission where user = ? group by user";
		return jdbcTemplate.queryForObject(sql, String.class, userName);
	}

	@Override
	public String queryDataPermissionByUserNameAndType(String userName, Integer type) {
		String sql = "select dataSigns dataSigns from permission where type = " + type + " and user = ? ";
		return jdbcTemplate.queryForObject(sql, String.class, userName);
	}

	@Override
	public Integer add(Permission permission) {
		String sql = "INSERT INTO permission (user, dataSigns) VALUES (?, ?)";
		return jdbcTemplate.update(sql, permission.getUser(), permission.getDataSigns());
	}


}
