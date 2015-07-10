/**
 * EnumConverter.java
 * 2015年5月20日
 */
package com.winplan.enums;

import org.apache.commons.beanutils.Converter;

/**  
 * <b>功能：</b>EnumConverter.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class EnumConverter implements Converter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object convert(Class type, Object value) {
		if(value != null){
			return Enum.valueOf(type, value.toString());
		}
		return null;
	}

}
