/**
 * UserController.java.java
 * @author FengMy
 * @since 2015年8月20日
 */
package com.winplan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winplan.entity.User;
import com.winplan.service.UserService;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月20日
 */
@Controller
@RequestMapping(value="user/*")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="list")
	public String list(Pagination pagination,ModelMap model){
		Query query = new Query().with(new Sort(Direction.DESC, "createTime"));
		pagination.setRecordCount((int) userService.count(query));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<User> historys = userService.findList(query);
		model.put("datas", historys);
		model.put("pagination", pagination);
		return "user/list";
	}
}
