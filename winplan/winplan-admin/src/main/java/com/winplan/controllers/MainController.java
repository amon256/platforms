/**
 * MainController.java.java
 * @author FengMy
 * @since 2015年7月6日
 */
package com.winplan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月6日
 */
@Controller
public class MainController extends BaseController {

	@RequestMapping(value="homepage")
	public String main(){
		return "homepage";
	}
	
}
