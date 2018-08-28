package com.jzl.beans.common;

/**
 * 
 * @ClassName: Permission  
 * @Description: 权限类  
 * @author Sombra  
 * @date 2017年12月8日 下午4:25:13  
 * @version V1.0
 */
public class Permission {
	/** 系统给予的权限 */
	public static final Integer TYPE_DEFAULT = 0;
	/** 别人分享的权限 */
	public static final Integer TYPE_SHARE = 1; 
	
	private String user;// 
	private String dataSigns;// 数据控制标志
	private Integer type;// 类型 0：本身生成的，1：别人给予的权限
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDataSigns() {
		return dataSigns;
	}
	public void setDataSigns(String dataSigns) {
		this.dataSigns = dataSigns;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
