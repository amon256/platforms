/**
 * DateConverter.java
 * 2015年5月20日
 */
package com.winplan.utils;

import org.apache.commons.beanutils.Converter;

/**  
 * <b>功能：</b>DateConverter.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class DateConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if(value == null){
			return null;
		}
		return DateUtils.parse(value.toString(), DateUtils.SIMPLE_PATTERN);
	}

}
