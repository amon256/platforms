/**
 * TokenContext.java.java
 * @author FengMy
 * @since 2015年7月24日
 */
package com.winplan.context;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

/**  
 * 功能描述：form防止重复提交token
 * 
 * @author FengMy
 * @since 2015年7月24日
 */
public class TokenContext {

	
	/**
	 * 页面提交key
	 */
	public static String TOKEN_KEY = "__TOKEN__";
	
	private static String TOKEN_SESSION_KEY = "_S_TOKENS_";
	
	/**
	 * 创建token
	 * @return
	 */
	public static String createToken(){
		String token = UUID.randomUUID().toString();
		HttpSession session = WebContext.getSession();
		@SuppressWarnings("unchecked")
		Set<String> tokens = (Set<String>) session.getAttribute(TOKEN_SESSION_KEY);
		if(tokens == null){
			tokens = new HashSet<String>();
		}
		tokens.add(token);
		session.setAttribute(TOKEN_SESSION_KEY, tokens);
		return token;
	}
	
	/**
	 * 校验并移除token
	 * @param token
	 * @return
	 */
	public synchronized static boolean validateToken(String token){
		HttpSession session = WebContext.getSession();
		@SuppressWarnings("unchecked")
		Set<String> tokens = (Set<String>) session.getAttribute(TOKEN_SESSION_KEY);
		if(tokens == null){
			tokens = new HashSet<String>();
		}
		return tokens.remove(token);
	}
}
