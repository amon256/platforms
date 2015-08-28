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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String list(Pagination pagination,User user,ModelMap model){
		Criteria criteria = Criteria.where("_id").exists(true);
		if(!StringUtils.isEmpty(user.getName())){
			criteria.and("name").is(user.getName());
		}else if(!StringUtils.isEmpty(user.getAccount())){
			criteria.and("account").is(user.getAccount());
		}else if(!StringUtils.isEmpty(user.getMobile())){
			criteria.and("mobile").is(user.getMobile());
		}
		Query query = Query.query(criteria);
		query.with(new Sort(Direction.DESC, "createTime"));
		pagination.setRecordCount((int) userService.count(query));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<User> historys = userService.findList(query);
		model.put("datas", historys);
		model.put("user", user);
		model.put("pagination", pagination);
		return "user/list";
	}
	
	@RequestMapping(value="detail")
	public String detail(@RequestParam(value="id",required=true)String id,ModelMap model){
		User user = userService.findById(id);
		model.put("data", user);
		return "user/detail";
	}
}
