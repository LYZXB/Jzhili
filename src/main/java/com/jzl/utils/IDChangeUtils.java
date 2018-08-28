package com.jzl.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

/**
 * 更新ID工具类
 * @author yangkai
 *
 */
public class IDChangeUtils {
	
	private static int YEAR = 0;
	private static int MONTH = 0;
	private static int DATE = 0;
	
	/**
	 * 初始化当前参数
	 * 记录当前启动的年月日
	 */
	@PostConstruct
	public static void init(){
		Calendar curDate = Calendar.getInstance();
		YEAR = curDate.get(Calendar.YEAR);
		MONTH = curDate.get(Calendar.MONTH);
		DATE = curDate.get(Calendar.DATE);
	}
	
	/**
	 * 是否要进行更新
	 * 目前只支持年月日+ID流水
	 * 后续需要支持年月日时分秒+ID流水
	 * @return
	 */
	public static boolean isChange(){
		Calendar curDate = Calendar.getInstance();
		if(YEAR != curDate.get(Calendar.YEAR) || MONTH != curDate.get(Calendar.MONTH)
				|| DATE != curDate.get(Calendar.DATE)){
			init();
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获取今天还剩下多少秒
	 * @return
	 */
	public static long getMiao(){
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate
				.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
				.get(Calendar.DATE) + 1, 0, 0, 0);
		return (long)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
	}
	
}
