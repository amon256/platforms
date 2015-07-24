/**
 * TokenInterceptor.java.java
 * @author FengMy
 * @since 2015年7月24日
 */
package com.winplan.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winplan.context.TokenContext;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月24日
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getParameter(TokenContext.TOKEN_KEY);
		if(!StringUtils.isEmpty(token)){
			boolean existsToken = TokenContext.validateToken(token);
			if(!existsToken){
				//TODO 转到提示页面
				response.sendRedirect(request.getContextPath() + "/");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
	
}
