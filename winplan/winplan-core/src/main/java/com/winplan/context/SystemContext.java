/**
 * SystemContext.java.java
 * @author FengMy
 * @since 2015年7月7日
 */
package com.winplan.context;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月7日
 */
public class SystemContext {
	
	/**
	 * 注册一名需要扣除奖金数
	 * @return
	 */
	public static int getBonusPerRegisterUser(){
		return 1200;
	}
	
	/**
	 * 账号前缀
	 * @return
	 */
	public static String getAccountPrefix(){
		return "QZ";
	}
	
	/**
	 * 账号数字最低位数
	 * @return
	 */
	public static String getAccountPattern(){
		return "000000";
	}
	
	public static String getDefaultPassword(){
		return getAccountPrefix() + "123456";
	}

}
