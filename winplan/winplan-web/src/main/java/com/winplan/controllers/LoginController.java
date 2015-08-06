/**
 * LoginController.java.java
 * @author FengMy
 * @since 2015年6月30日
 */
package com.winplan.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winplan.context.WebContext;
import com.winplan.entity.User;
import com.winplan.service.UserService;
import com.winplan.utils.SecurityUtil;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年6月30日
 */
@Controller
public class LoginController extends BaseController {
	
	private static final String USER_NAME_COOKIE_KEY = "accounts";
	private static final int ACCOUNT_COOKIE_AGE = 60*60*24*180;
	private static final int MAX_ACCOUNTS = 10;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="login")
	public String login(User user,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if(WebContext.hasLogin()){
			return "redirect:index";
		}
		if(!StringUtils.isEmpty(user.getAccount())){
			if(loginProcess(user,model)){
				loginCookie(user, request, response);
				return "redirect:index";
			}
		}
		model.put("user", user);
		loginReadCookie(user,model, request, response);
		return "login";
	}
	
	@RequestMapping(value="logout")
	public String logout(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		WebContext.logout();
		User user = new User();
		model.put("user", user);
		loginReadCookie(user, model, request, response);
		return "login";
	}
	
	private void loginReadCookie(User user,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = readUserNameCookie(request);
		if(cookie != null){
			String names = cookie.getValue();
			Set<String> userNames = new LinkedHashSet<String>();
			if(names != null){
				String[] nameAttr = names.split(",");
				for(String name : nameAttr){
					if(name != null && !"".equals(name.trim())){
						userNames.add(name.trim());
					}
				}
				if(userNames.size() == 1){
					user.setAccount(userNames.iterator().next());
				}else if(userNames.size() > 1){
					user.setAccount(userNames.iterator().next());
					model.put("accounts", userNames);
				}
			}
		}
	}
	
	private void loginCookie(User user,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = readUserNameCookie(request);
		if(cookie == null){
			cookie = new Cookie(USER_NAME_COOKIE_KEY, user.getAccount());
		}
		Set<String> userNames = new LinkedHashSet<String>();
		userNames.add(user.getAccount());
		String names = cookie.getValue();
		if(names != null){
			String[] nameAttr = names.split(",");
			for(String name : nameAttr){
				if(name != null && !"".equals(name.trim())){
					userNames.add(name.trim());
				}
				if(userNames.size() >= MAX_ACCOUNTS){
					break;
				}
			}
		}
		String nameStr = "";
		for(String name : userNames){
			if(!"".equals(nameStr)){
				nameStr += ",";
			}
			nameStr += name;
		}
		cookie.setValue(nameStr);
		cookie.setMaxAge(ACCOUNT_COOKIE_AGE);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	private Cookie readUserNameCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if(USER_NAME_COOKIE_KEY.equals(cookie.getName())){
				return cookie;
			}
		}
		return null;
	}
	
	/**
	 * 登录过程
	 * @param account
	 * @param pwd
	 * @param model 
	 * @return
	 */
	private boolean loginProcess(User user, ModelMap model){
		if(StringUtils.isEmpty(user.getPassword())){
			model.put("msg", "密码不能为空");
			return false;
		}
		Query query = new Query(Criteria.where("account").is(user.getAccount()).and("password").is(SecurityUtil.encryptSHA(user.getPassword())));
		User validateUser = userService.findOne(query);
		if(validateUser != null){
			WebContext.login(validateUser);
			return true;
		}else{
			model.put("msg", "用户名或密码错误");
			return false;
		}
	}
}
