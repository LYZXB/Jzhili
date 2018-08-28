package com.jzl.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.jzl.beans.birthday.Birthday;

public class OtherTest {
	/**
	 * 
	 * @Title: decimalAddTest  
	 * @Description: 为什么bigDecaimal的add加无效 
	 * @throws
	 */
	@Test
	@Ignore
	public void decimalAddTest() {
		BigDecimal bd = BigDecimal.ZERO;
		bd = bd.add(new BigDecimal(138.2));
		bd = bd.add(new BigDecimal(138.2));
		System.out.println(bd.doubleValue());
		BigDecimal testbd =  BigDecimal.ZERO;
		testbd = bd == null ? BigDecimal.ZERO : testbd.add(bd);
		Integer testInt = bd == null ? 0 : bd.intValue();
		System.out.println(testbd.doubleValue());
	}
	
	@Ignore
	@Test
	public void hashMapTest() {
		Map<Birthday, String> birthdayMap = new HashMap<>();
		Birthday bithday = new Birthday();
		bithday.setName("test");
		bithday.setAge(13);
		Birthday bithday1 = new Birthday();
		bithday.setName("test");
		bithday.setAge(13);
		birthdayMap.put(bithday, "test");
		birthdayMap.put(bithday1, "test1");
		bithday1.setName("test1");
		birthdayMap.put(bithday1, "test2");
		System.out.println(birthdayMap.get(bithday1));
	}
	
	@Test
	@Ignore
	public void retainAllTest() {
		List<String> addList = new ArrayList<>();
		List<String> delList = new ArrayList<>();
		addList.add("1");
		addList.add("2");
		addList.add("3");
		delList.add("3");
		delList.add("4");
		delList.add("5");
		List<String> tempList = new ArrayList<>();
		tempList.addAll(addList);
		tempList.retainAll(delList);
		System.out.println(tempList.toString());
	}
	
	@Test
	public void matchTest() {
		String birthdayStr = "2018年闰01月10日";
		Matcher m = Pattern.compile("\\d+").matcher(birthdayStr);
		StringBuilder sb = new StringBuilder();
		while(m.find()) {
			sb.append(m.group());
		}
		System.out.println(sb);
	}
}
