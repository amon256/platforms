/**
 * LoginInterceptor.java.java
 * @author FengMy
 * @since 2015年7月6日
 */
package com.winplan.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winplan.context.WebContext;
import com.winplan.entity.Roleable;
import com.winplan.permission.PermissionManager;
import com.winplan.permission.PermissionUtil;
import com.winplan.spring.ApplicationContextAware;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：登录拦截器
 * 
 * @author FengMy
 * @since 2015年7月6日
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	private String noPermissionPath = null;

	public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		logger.debug("accept request : {}",path);
		PermissionManager manager = ApplicationContextAware.getApplicationContext().getBean(PermissionManager.class);
		if(!manager.isPermissionControl(path) && !"/index".equals(path)){
			logger.debug("accept request is not in permission control.");
			return true;
		}
		logger.debug("accept request is in permission control.");
		Roleable user = WebContext.getLoginUser();
		boolean isAllow = false;
		if(user != null && user.getRoles() != null){
			if("/index".equals(path.trim())){
				String pathId = request.getParameter("_p");
				if(pathId == null || (path = PermissionUtil.getPageUrlById(pathId)) == null){
					return true;
				}
			}
			List<String> roles = CollectionUtils.createList(String.class, user.getRoles().split(","));
			if(manager.validatePermission(path, roles)){
				isAllow = true;
				logger.debug("accept is allow.");
			}
		}
		if(!isAllow){
			logger.debug("accept is forbidden.");
			response.sendRedirect(getNoPermissionUrl(request,manager));
			return false;
		}
		return true;
	}
	
	public String getNoPermissionUrl(HttpServletRequest request, PermissionManager manager){
		if(noPermissionPath == null){
			String menuId = manager.getDefaultMenu().getId();
			String pageId = manager.getPageUrlMap().get("/noPermission").getId();
			noPermissionPath = request.getContextPath() + "/index?_m=" + menuId + "&_p=" + pageId;
		}
		return noPermissionPath;
	}
	
	public void setNoPermissionPath(String noPermissionPath) {
		this.noPermissionPath = noPermissionPath;
	}
}
