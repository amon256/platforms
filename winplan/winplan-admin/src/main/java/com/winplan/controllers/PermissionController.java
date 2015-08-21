/**
 * PermissionController.java.java
 * @author FengMy
 * @since 2015年8月19日
 */
package com.winplan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月19日
 */
@Controller
public class PermissionController extends BaseController{

	@RequestMapping(value="noPermission")
	public String noPermission(){
		return "permission/noPermission";
	}
	
	@RequestMapping(value="submitRepeat")
	public String submitRepeat(){
		return "permission/submitRepeat";
	}
}
