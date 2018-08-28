package com.jzl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jzl.repository.IncrementerRepository;
import com.jzl.service.IncrementerService;

/**
 * 
 * @ClassName: IncrementerServiceImpl  
 * @Description: 序列逻辑操作接口实现类  
 * @author Sombra  
 * @date 2017年12月26日 下午5:53:04  
 * @version V1.0
 */
@Service
public class IncrementerServiceImpl implements IncrementerService {
	
	@Resource
	IncrementerRepository incrementerRepository;

	@Override
	public String getNextNumber(String moid) {
		return incrementerRepository.getNextNumber(moid);
	}
	
}
