/**
 * MyRollingFileAppender.java
 * 2015年5月25日
 */
package com.winplan.log;

import java.io.File;

import org.apache.log4j.RollingFileAppender;

/**  
 * <b>功能：</b>MyRollingFileAppender.java<br/>
 * <b>描述：</b> 自定义FileAppender<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class MyRollingFileAppender extends RollingFileAppender {

	public static String root = System.getProperty("user.dir");
	
	public static void setRoot(String root){
		MyRollingFileAppender.root = root;
	}
	
	@Override
	public void setFile(String file) {
		file = root + File.separator + file;
		super.setFile(file);
	}
}
