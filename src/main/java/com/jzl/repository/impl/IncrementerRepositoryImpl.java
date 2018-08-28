package com.jzl.repository.impl;

import org.springframework.stereotype.Repository;

import com.jzl.repository.IncrementerRepository;

/**
 * <ol>
 * 	<li> 格式：前缀YYYYMMDD000000
 * 	<li> 前缀自己定义
 * 	<li> YYYYMMDD是时间，当前时间日期
 * 	<li> 000000是序列号占位符	
 * 	<li> 序列每天都会更新
 * </ol>
 * 
 * @ClassName: IncrementerRepositoryImpl  
 * @Description: 流水号数据管理  
 * @author Sombra  
 * @date 2017年11月28日 上午10:29:09  
 * @version V1.0
 */
@Repository
public class IncrementerRepositoryImpl extends AbstractRepositoryImpl implements IncrementerRepository{

	@Override
	public String getNextNumber(String moid) {
		
		// 目前没有需要，直接用rule+next组成
		Integer nextInt = jdbcTemplate.queryForObject("select getnextint(?)", Integer.class, moid);
		if (nextInt == null)
			return null;
		String rule = jdbcTemplate.queryForObject("select rule from incrementer where moid = ?", String.class, moid);
		String nextNumber = rule + nextInt;
		return nextNumber;
	}
	
}
