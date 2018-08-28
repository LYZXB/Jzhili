package com.jzl.enums;

/**
 * 
 * @ClassName: PushTypeEnum  
 * @Description: 推送类型枚举  
 * @author Sombra  
 * @date 2018年4月9日 下午5:16:08  
 * @version V1.0
 */
public enum PushTypeEnum {

	SMS(1, "短信")
	,Email(2, "邮箱")
	,QQ(4, "QQ")
	,Wechat(8, "微信")
	;

	private Integer code;
	private String value;
	
	PushTypeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
