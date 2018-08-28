package com.jzl.beans.common;

import java.io.Serializable;

/**
 * 
 * @ClassName: PageBean  
 * @Description: 分页参数
 * @author Sombra  
 * @date 2018年3月16日 上午10:30:13  
 * @version V1.0
 */
public class PageBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1997007729427761461L;

	private int page = 0;
	
	private int limit = -1;
	
	private String sort;
	
	private String order;
	
	/**
	 * 页码
	 * @return int
	 */
	public int getPage() {
		if(this.page > 1){
			return (page-1) * this.limit;
		}
		return 0;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
