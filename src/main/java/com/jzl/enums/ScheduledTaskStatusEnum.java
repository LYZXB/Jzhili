package com.jzl.enums;

/**
 * 
 * @ClassName: ScheduledTaskStatuEnum  
 * @Description: 自动任务状态枚举  
 * @author Sombra  
 * @date 2018年3月28日 下午5:28:57  
 * @version V1.0
 */
public enum ScheduledTaskStatusEnum {
	IN_EXEC("1"),
	NOT_EXEC("0");

	private String code;
	
	ScheduledTaskStatusEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 1返回true，0返回false
	 * @Title: isExec  
	 * @Description: 判断是否正在执行
	 * @param code
	 * @return  boolean    返回类型 
	 */
	public static boolean isExec(String code) {
		return IN_EXEC.code == code;
	}
}
