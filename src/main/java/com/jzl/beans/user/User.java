package com.jzl.beans.user;

import com.jzl.beans.common.AbstractIncrement;

/**
 * 
 * @ClassName: User  
 * @Description: 用户实体  
 * @author Sombra  
 * @date 2017年11月6日 下午4:08:41  
 * @version V1.0
 */
public class User extends AbstractIncrement {
	
	/**  
	 * 序列化版本号
	 */  
	private static final long serialVersionUID = 1L;
	
	public static final String SessionName = "userName";
	
	/** 每次进入系统都会改变的token */
	public static final String TOKEN__AWAYS_CHANGE = "token__aways_change";
	/** 用户登录才会改变的token */
	public static final String TOKEN__LOGIN_CHANGE = "token__login_change";
	
	private String userName;
	private String password;
	private String nickName;
	private String phone;
	private String email;
	private String nation;
	private String province;
	private String city;
	private String address;
	private String qqcode;
	private String wechatcode;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQqcode() {
		return qqcode;
	}
	public void setQqcode(String qqcode) {
		this.qqcode = qqcode;
	}
	public String getWechatcode() {
		return wechatcode;
	}
	public void setWechatcode(String wechatcode) {
		this.wechatcode = wechatcode;
	}
}
