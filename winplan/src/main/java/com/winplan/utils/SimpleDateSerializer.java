/**
 * SimpleDateSerializer.java
 * 2015年5月22日
 */
package com.winplan.utils;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**  
 * <b>功能：</b>SimpleDateSerializer.java<br/>
 * <b>描述：</b> 常用日期转换类,格式yyyy-MM-dd HH:mm:ss<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class SimpleDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if(value == null){
			return;
		}
		generator.writeString(DateUtils.format(value, DateUtils.SIMPLE_PATTERN));
	}

}
