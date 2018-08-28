package com.jzl.service;

/**
 * 
 * @ClassName: IncrementerService  
 * @Description: 序列逻辑操作接口  
 * @author Sombra  
 * @date 2017年12月26日 下午5:49:20  
 * @version V1.0
 */
public interface IncrementerService {
	
	/**
	 * 
	 * @Title: getNextNumber  
	 * @Description: 通过模块id获取自增序列  
	 * @param moid
	 * @return  String    返回类型 
	 */
	String getNextNumber(String moid);
}
