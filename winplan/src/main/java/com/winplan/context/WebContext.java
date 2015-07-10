/**
 * WebContext.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.context;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.winplan.entity.User;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public class WebContext {
	public static final String LOGIN_USER_KEY = "c67r6sd4kl221";
	
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
	public static User getLoginUser(){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attrs.getRequest().getSession();
		return (User) session.getAttribute(LOGIN_USER_KEY);
	}
	
	/**
	 * 登录
	 * @param user
	 */
	public static void login(User user){
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
