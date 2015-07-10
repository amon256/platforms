/**
 * IndexController.java.java
 * @author FengMy
 * @since 2015年6月30日
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
 * 功能描述：主页
 * 
 * @author FengMy
 * @since 2015年6月30日
 */
@Controller
public class IndexController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="index")
	public String index(ModelMap model){
		User user = WebContext.getLoginUser();
		user = userService.findById(user.getId());
		model.put("user", user);		
		return "index";
	}
}
