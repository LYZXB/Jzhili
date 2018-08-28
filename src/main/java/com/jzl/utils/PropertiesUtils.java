package com.jzl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
 * @ClassName: PropertiesUtils  
 * @Description: 加载配置文件工具类  
 * @author Sombra  
 * @date 2017年12月19日 下午5:15:45  
 * @version V1.0
 */
public class PropertiesUtils {

	/**
	 * 加载配置文件 
	 * @param path
	 * @return
	 */
	public static Properties loadProperties(String path){
		Properties properties = new Properties();
		try {
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));  
			properties.load(bf);  
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return properties;
	}
}
