package com.jzl.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jzl.beans.common.Permission;
import com.jzl.repository.DataPermissionRepository;
import com.jzl.service.DataPermissionService;
import com.jzl.utils.DataEncryptUtils;
import com.jzl.utils.JedisUtils;
import com.jzl.utils.RedisDataRulerUtils;

/**
 * 
 * @ClassName: DataPermissionServiceImpl  
 * @Description: 数据权限接口实现  
 * @author Sombra  
 * @date 2017年12月8日 下午4:11:35  
 * @version V1.0
 */
@Service
public class DataPermissionServiceImpl implements DataPermissionService {
	Logger logger = Logger.getLogger(DataPermissionServiceImpl.class);

	@Resource
	DataPermissionRepository dataPermissionRepository;
	
	@Override
	public String initDataPermissionForUser(String userName) {
		// 生成用户专属数据权限标志
		String dataSigns = null;
		try {
			dataSigns = DataEncryptUtils.oneWayEncryption(userName);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.info(e);
			return null;
		}
		
		
		// 存入用户权限表，存入redis
		Permission permission = new Permission();
		permission.setUser(userName);
		permission.setDataSigns(dataSigns);
		permission.setType(Permission.TYPE_DEFAULT);
		dataPermissionRepository.add(permission);
		
		String userDataSignKey = RedisDataRulerUtils.getAllUserDataSignKey(userName);
		JedisUtils.set(userDataSignKey, dataSigns);
		
		
		// 返回数据权限标志
		return dataSigns;
	}

	@Override
	public String queryAllDataPermissionByUserName(String userName) {
		// 根据redis查询
		String dataSignKey = RedisDataRulerUtils.getAllUserDataSignKey(userName);
		String rDataSigns = JedisUtils.get(dataSignKey);
		if (StringUtils.isNotEmpty(rDataSigns)) {
			return rDataSigns;
		}
		
		// 根据数据库查询，写入redis数据库 
		String dataSigns = dataPermissionRepository.queryAllDataPermissionByUserName(userName);
		JedisUtils.set(dataSignKey, dataSigns);
		
		return dataSigns;
	}

	@Override
	public String queryDefaultDataPermiassionByUserName(String userName) {
		// 根据redis查询
		String dataSignKey = RedisDataRulerUtils.getDefaultUserDataSignKey(userName);
		String rDataSigns = JedisUtils.get(dataSignKey);
		if (StringUtils.isNotEmpty(rDataSigns)) {
			return rDataSigns;
		}

		// 根据数据库查询，写入redis数据库
		String dataSigns = dataPermissionRepository.queryDataPermissionByUserNameAndType(userName, Permission.TYPE_DEFAULT);
		JedisUtils.set(dataSignKey, dataSigns);

		return dataSigns;
	}
	
}
