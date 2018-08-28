package com.jzl.beans.common;

import com.google.gson.annotations.Expose;

/**
 * 
 * @ClassName: SMSTemplateParam  
 * @Description: 短信模板参数  
 * @author Sombra  
 * @date 2018年3月27日 下午5:40:03  
 * @version V1.0
 */
public class SMSTemplateParam {
	/** 接收者手机号 */
	@Expose(serialize = false, deserialize = false)
	private String phone;
	/** 过生日者姓名 */
	@Expose()
	private String name;
	/** 生日 */
	@Expose(serialize = false, deserialize = false)
	private String birthday;
	/** 还剩天数 */
	@Expose(serialize = false, deserialize = false)
	private Integer days;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
