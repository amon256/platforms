/**
 * DateUtils.java
 * 2015年5月20日
 */
package com.winplan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * <b>功能：</b>DateUtils.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class DateUtils {
	
	public static final String SIMPLE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_ONLY_PATTERN = "yyyy-MM-dd";
	public static final String TIME_ONLY_PATTERN = "HH:mm:ss";
	
	public static String format(Date date,String pattern){
		if(date == null){
			return null;
		}
		if(pattern == null){
			pattern = SIMPLE_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date parse(String value,String pattern){
		if(value == null){
			return null;
		}
		if(pattern == null){
			pattern = SIMPLE_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
