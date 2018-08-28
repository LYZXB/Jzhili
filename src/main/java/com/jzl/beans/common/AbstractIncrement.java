package com.jzl.beans.common;

import java.io.Serializable;

/**
 * 
 * @ClassName: AbstractIncrement  
 * @Description: 主键类，所有数据库实体的父类  
 * @author Sombra  
 * @date 2017年11月6日 下午4:12:04  
 * @version V1.0
 */
public abstract class AbstractIncrement implements Serializable, Cloneable {
	
	/**  
	 * 序列化版本号
	 */  
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
