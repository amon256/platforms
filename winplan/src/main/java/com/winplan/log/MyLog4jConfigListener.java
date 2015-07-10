/**
 * MyLog4jConfigListener.java
 * 2015年5月25日
 */
package com.winplan.log;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.springframework.web.util.Log4jConfigListener;

/**  
 * <b>功能：</b>MyLog4jConfigListener.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class MyLog4jConfigListener extends Log4jConfigListener {
	private static final String ROOT_KEY = "log4jRootDir";
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//设置log4j日志根目录
		String root = event.getServletContext().getInitParameter(ROOT_KEY);
		if(root == null || "".equals(root.trim())){
			root = System.getProperty("user.dir") + File.separator + "logs";
		}
		File rootDir = new File(root);
		if(!rootDir.exists()){
			rootDir.mkdirs();
		}
		MyRollingFileAppender.setRoot(root);
		System.out.println("log4j output path root is : " + root);
		super.contextInitialized(event);
	}
}
