/**
 * MainController.java.java
 * @author FengMy
 * @since 2015年7月6日
 */
package com.winplan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winplan.context.WebContext;
import com.winplan.entity.User;
import com.winplan.service.UserService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月6日
 */
@Controller
public class MainController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="main")
	public String main(ModelMap model){
		User user = WebContext.getLoginUser();
		user = userService.findById(user.getId());
		model.put("user", user);
		return "main/main";
	}
	
}
