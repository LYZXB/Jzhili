package com.jzl.utils;

import com.google.gson.Gson;

/**
 * 
 * @ClassName: JsonUtils  
 * @Description: Json工具类  
 * @author Sombra  
 * @date 2017年12月21日 下午5:57:56  
 * @version V1.0
 */
public class JsonUtils {
	private static Gson gson = null;
	
	/**
	 * @Title: getGson  
	 * @Description: 获取Gson对象  
	 * @return  Gson    返回类型 
	 */
	private static Gson getGson() {
		if (gson == null) {
			synchronized (Gson.class) {
				if (gson == null) {
					gson = new Gson();
				}
			}
		}
		return gson;
	}
	
	/**
	 * @Title: toJson  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param o
	 * @return  String    返回类型 
	 */
	public static String toJson(Object o) {
		return getGson().toJson(o);
	}
}
