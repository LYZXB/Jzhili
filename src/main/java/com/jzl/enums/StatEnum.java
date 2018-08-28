package com.jzl.enums;

/**
 * 
 * @ClassName: StatEnum  
 * @Description: 状态枚举  
 * @author Sombra  
 * @date 2018年4月9日 下午5:16:08  
 * @version V1.0
 */
public enum StatEnum {

	VALID(0, "有效")
	,INVALID(1, "无效")
	;

	private Integer code;
	private String value;
	
	StatEnum(Integer code, String value) {
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
