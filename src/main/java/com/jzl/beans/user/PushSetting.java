package com.jzl.beans.user;

import com.jzl.beans.common.AbstractIncrement;

/**
 * 一个用户多个推送设置
 * 方便详细设置
 * @ClassName: PushSetting  
 * @Description: 推送设置  
 * @author Sombra  
 * @date 2018年4月3日 上午11:56:48  
 * @version V1.0
 */
public class PushSetting extends AbstractIncrement {
	/**  
	 */  
	private static final long serialVersionUID = 1L;
	
	private String gco;// 流水号
	private String userName; // 用户名
	private Integer pushType; // 推送方式
	private Integer forwardDays; // 提前多少天
	private Long pushDate; // 上次推送时间
	private Integer stat; // 是否开启  StatEnum
	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public String getGco() {
		return gco;
	}
	public void setGco(String gco) {
		this.gco = gco;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPushType() {
		return pushType;
	}
	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}
	public Integer getForwardDays() {
		return forwardDays;
	}
	public void setForwardDays(Integer forwardDays) {
		this.forwardDays = forwardDays;
	}
	public Long getPushDate() {
		return pushDate;
	}
	public void setPushDate(Long pushDate) {
		this.pushDate = pushDate;
	}
}
