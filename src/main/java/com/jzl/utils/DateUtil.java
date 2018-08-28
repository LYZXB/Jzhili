package com.jzl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

public class DateUtil {
    private static final Logger LOGGER = Logger.getLogger(DateUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";

    /**
     * 字符串Date转Date
     * @param dateString
     * @return
     */
    public static Date string2Date(String dateString) {
        if (dateString == null || dateString.trim().equals("")) {
            return null;
        } else {
            try {
                return sf.parse(dateString);
            } catch (ParseException e) {
                LOGGER.info(null, e);
                return null;
            }
        }
    }

    /**
     * 字符串转Date yyyyMMdd
     * @param dateString
     * @return
     */
    public static Date string2DateNoTime(String dateString) {
        if (dateString == null || dateString.trim().equals("")) {
            return null;
        } else {
            try {
                return sdf.parse(dateString);
            } catch (ParseException e) {
                LOGGER.info(null, e);
                return null;
            }
        }
    }
    
    /**
     * 字符串时间格式转Long时间戳
     * @param strDate
     * @return
     */
    public static Long string2Long(String strDate) {
        Date date = string2Date(strDate);
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
    
    /**
     * Long类型时间戳转Date
     * @param longDate
     * @return
     */
    public static Date long2Date(Long longDate) {
        return new Date(longDate);
    }

    /**
     * Date 转 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        try {
            return sf.format(date);
        } catch (Exception e) {
            LOGGER.info(null, e);
            return null;
        }
    }

    /**
     * Long类型Date转 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String long2String(Long date) {
        try {
            return sf.format(new Date(date));
        } catch (Exception e) {
            LOGGER.info(null, e);
            return null;
        }
    }

    public static String long2String(Long date, String pattern) {
    	SimpleDateFormat sft = new SimpleDateFormat(pattern);
    	try {
    		return sft.format(new Date(date));
    	} catch (Exception e) {
    		LOGGER.info(null, e);
    		return null;
    	}
    }
    
    /**
     * 指定格式格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 根据指定格式解析日期
     * @param date
     * @return
     */
    public static Long string2Long(String date, String pattern) {
        Long time = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            time = sdf.parse(date).getTime();
        } catch (ParseException e) {
            LOGGER.info(null, e);
        }
        return time;
    }

    /**
     * 比较两个日期的大小(date1及date2任意为空或都不为空date1 < date2返回true;date1 >= date2返回false;)
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        if (date1 != null && date2 != null && date1.getTime() >= date2.getTime()) {
            return false;
        }
        return true;
    }
    
    /**
     * 计算两个阳历日期相差的天数。
     * @param startDate 开始时间
     * @param endDate 截至时间
     * @return (int)天数
     * @author liu 2017-3-2
     */
    public static int daysBetween(Date startDate, Date endDate) {
        int days = 0;
        //将转换的两个时间对象转换成Calendar对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startDate);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endDate);
        //拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        //天数

        Calendar can = null;
        //如果can1 < can2
        //减去小的时间在这一年已经过了的天数
        //加上大的时间已过的天数
        if(can1.before(can2)){
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        }else{
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2-year1); i++) {
            //获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            //再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }
    
    @Test
    public void daysBetweenTest() {
    	Date startDate = new Date(1527004800000L);
    	Date endDate = new Date(1525276800000L);
    	System.out.println(daysBetween(startDate, endDate));
    }
    
    /**
	 * @Title: daysBetweenForNow  
	 * @Description: 根据时间字符串yyyy-MM-dd HH:mm:ss，获取距离今天多少天
	 * @return  Integer    返回类型 
	 */
	public static Integer daysBetweenForNow(String dateStr) {
		Date date = DateUtil.string2Date(dateStr);
		return DateUtil.daysBetween(date, new Date());
	}
}

