/**
 * LoginController.java.java
 * @author FengMy
 * @since 2015年6月30日
 */
package com.winplan.controllers;

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

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年6月30日
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="login")
	public String login(User user,ModelMap model){
		if(WebContext.hasLogin()){
			return "redirect:index";
		}
		if(!StringUtils.isEmpty(user.getAccount())){
			if(loginProcess(user,model)){
				return "redirect:index";
			}
		}
		model.put("user", user);
		return "login";
	}
	
	@RequestMapping(value="logout")
	public String logout(){
		WebContext.logout();
		return "login";
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
		Query query = new Query(Criteria.where("account").is(user.getAccount()).and("password").is(user.getPassword()));
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
