/**
 * ConverterRegister.java
 * 2015年5月20日
 */
package com.winplan.utils;

import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**  
 * <b>功能：</b>ConverterRegister.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
@Component
@Lazy(value=false)
public class ConverterRegister {
	private boolean register = false;
	
	public void registerConverter(){
		if(!register){
			register();
		}
	}
	
	private void register(){
//		ConvertUtils.register(new EnumConverter(), BooleanEnum.class);
//		ConvertUtils.register(new EnumConverter(), SexEnum.class);
		ConvertUtils.register(new DateConverter(), Date.class);
	}
}
