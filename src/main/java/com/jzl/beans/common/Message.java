package com.jzl.beans.common;

/**
 * 
 * @ClassName: Message  
 * @Description: 返回消息  
 * @author Sombra  
 * @date 2017年11月6日 下午5:08:41  
 * @version V1.0
 */
public class Message {
	public Message() {
		
	}
	public Message (Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	
	public static Message FAILURE = new Message (false, "NO");
	public static Message SUCCESS = new Message (true, "OK");
	
	private String msg;
	private Boolean success;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean isSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
