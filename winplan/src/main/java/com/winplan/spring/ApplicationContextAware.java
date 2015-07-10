/**
 * ApplicationContextAware.java
 * 2015年5月26日
 */
package com.winplan.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**  
 * <b>功能：</b>ApplicationContextAware.java<br/>
 * <b>描述：</b> Spring上下文<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
@Component
@Lazy(value=false)
public class ApplicationContextAware implements org.springframework.context.ApplicationContextAware {
	private static ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		ApplicationContextAware.ctx = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return ApplicationContextAware.ctx;
	}
}
