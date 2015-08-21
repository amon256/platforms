/**
 * WebContext.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.context;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public class WebContext {
	public static final String LOGIN_USER_KEY = "pfaidsofja2";
	
	public static HttpSession getSession(){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attrs.getRequest().getSession();
		return session;
	}
	
	/**
	 * 是否己登录
	 * @return
	 */
	public static boolean hasLogin(){
		return getLoginUser() != null;
	}
	
	/**
	 * 获取登录用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getLoginUser(){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attrs.getRequest().getSession();
		return (T) session.getAttribute(LOGIN_USER_KEY);
	}
	
	/**
	 * 登录
	 * @param user
	 */
	public static <T> void login(T user){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attrs.getRequest().getSession();
		session.setAttribute(LOGIN_USER_KEY, user);
	}
	
	/**
	 * 登出
	 */
	public static void logout(){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attrs.getRequest().getSession();
		session.invalidate();
	}
}
