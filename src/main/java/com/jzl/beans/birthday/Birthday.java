package com.jzl.beans.birthday;

import com.jzl.beans.common.AbstractIncrement;

/**
 * 
 * @ClassName: Birthday  
 * @Description: 生日实体  
 * @author Sombra  
 * @date 2017年11月7日 下午9:56:28  
 * @version V1.0
 */
public class Birthday extends AbstractIncrement{
	/**  
	 * @Fields 反序列化版本号
	 */  
	private static final long serialVersionUID = 1L;
	
	/** 生日时间格式 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
	
	public static final Integer TYPE_SOLAR = 0;
	public static final Integer TYPE_LUNAE = 1;
	
	private String gco;
	private String name;
	private Integer age;
	private String birthday;
	private String lunar_birthday;// 农历生日
	private String next_solar;// 下一次公历生日
	private String next_lunar;// 下一次农历生日
	private Integer solar_days;// 公历生日还剩天数
	private Integer lunar_days;// 农历生日还剩天数
	private Integer type;// 0 公历， 1 农历
	private String dataSigns;// 数据控制标志
	private Long updateTime; // 更新时间（判断是否需要更新）
	private Integer needPush; // 是否需要推送 0 不需要 1 需要
	
	public String getGco() {
		return gco;
	}
	public void setGco(String gco) {
		this.gco = gco;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLunar_birthday() {
		return lunar_birthday;
	}
	public void setLunar_birthday(String lunar_birthday) {
		this.lunar_birthday = lunar_birthday;
	}
	public String getNext_solar() {
		return next_solar;
	}
	public void setNext_solar(String next_solar) {
		this.next_solar = next_solar;
	}
	public String getNext_lunar() {
		return next_lunar;
	}
	public void setNext_lunar(String next_lunar) {
		this.next_lunar = next_lunar;
	}
	public Integer getSolar_days() {
		return solar_days;
	}
	public void setSolar_days(Integer solar_days) {
		this.solar_days = solar_days;
	}
	public Integer getLunar_days() {
		return lunar_days;
	}
	public void setLunar_days(Integer lunar_days) {
		this.lunar_days = lunar_days;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDataSigns() {
		return dataSigns;
	}
	public void setDataSigns(String dataSigns) {
		this.dataSigns = dataSigns;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getNeedPush() {
		return needPush;
	}
	public void setNeedPush(Integer needPush) {
		this.needPush = needPush;
	}
}
