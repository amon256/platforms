/**
 * CustomObjectMapper.java
 * 2015年5月22日
 */
package com.winplan.utils;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**  
 * <b>功能：</b>CustomObjectMapper.java<br/>
 * <b>描述：</b> 自定义ObjectMapper<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class CustomObjectMapper extends ObjectMapper {
	
	public CustomObjectMapper(){
		CustomSerializerFactory factory = new CustomSerializerFactory();  
        factory.addGenericMapping(Date.class, new SimpleDateSerializer());  //默认日期格式化yyyy-MM-dd HH:mm:ss
        this.setSerializerFactory(factory); 
	}
}
