package com.jzl.beans.common;

import java.util.List;

/**
 * 
 * @ClassName: LayTableData  
 * @Description: 返回layui的table需要的数据  
 * @author Sombra  
 * @date 2018年5月18日 下午2:05:55  
 * @version V1.0
 */
public class LayTableData {
	private Integer code;
	private String msg;
	private Long count;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
