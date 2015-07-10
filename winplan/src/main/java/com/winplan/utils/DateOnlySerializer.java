/**
 * DateOnlySerializer.java
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
 * <b>功能：</b>DateOnlySerializer.java<br/>
 * <b>描述：</b> 日期格式化:yyyy-MM-dd<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class DateOnlySerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		if(value == null){
			return;
		}
		jgen.writeString(DateUtils.format(value, DateUtils.DATE_ONLY_PATTERN));
	}

}
