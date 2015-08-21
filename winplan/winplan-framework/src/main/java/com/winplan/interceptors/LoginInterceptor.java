/**
 * LoginInterceptor.java.java
 * @author FengMy
 * @since 2015年7月6日
 */
package com.winplan.interceptors;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winplan.context.WebContext;

/**  
 * 功能描述：登录拦截器
 * 
 * @author FengMy
 * @since 2015年7月6日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private Set<String> excludePaths = new HashSet<String>();
	private String loginUrl = "login";

	public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		logger.debug("accept request : {}",path);
		if(excludePaths.contains(path)){
			logger.debug("accept request is exclude.");
			return true;
		}
		logger.debug("accept request is not exclude,validate login.");
		if(WebContext.hasLogin()){
			logger.debug("accept is allow.");
		}else{
			logger.debug("accept is not allow.");
			response.sendRedirect(request.getContextPath() + "/" + loginUrl);
			return false;
		}
		return true;
	}
	
	public void setExclude(String[] paths){
		if(paths != null){
			for(String path : paths){
				excludePaths.add(path.trim());
			}
		}
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
