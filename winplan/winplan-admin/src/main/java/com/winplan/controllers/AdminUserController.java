/**
 * AdminUserController.java.java
 * @author FengMy
 * @since 2015年8月18日
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

import com.winplan.annotations.TokenCreate;
import com.winplan.annotations.TokenValidate;
import com.winplan.context.WebContext;
import com.winplan.entity.AdminUser;
import com.winplan.enums.AdminUserStatusEnum;
import com.winplan.permission.PermissionManager;
import com.winplan.permission.SystemMenu;
import com.winplan.service.AdminUserService;
import com.winplan.utils.CollectionUtils;
import com.winplan.utils.Pagination;
import com.winplan.utils.SecurityUtil;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月18日
 */
@Controller
@RequestMapping(value="adminuser/*")
public class AdminUserController extends BaseController{
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private PermissionManager permissionManager;

	@RequestMapping(value="list")
	public String list(Pagination pagination,ModelMap model){
		Query query = new Query().with(new Sort(Direction.DESC, "createTime"));
		pagination.setRecordCount((int) adminUserService.count(query));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<AdminUser> historys = adminUserService.findList(query);
		model.put("datas", historys);
		model.put("pagination", pagination);
		return "adminuser/list";
	}
	
	@RequestMapping(value="toAdd")
	@TokenCreate
	public String toAdd(){
		return "adminuser/add";
	}
	
	@RequestMapping(value="add")
	@TokenValidate
	@TokenCreate
	public String add(AdminUser adminUser,ModelMap model){
		if(validate(adminUser, model)){
			Query query = Query.query(Criteria.where("account").is(adminUser.getAccount()));
			if(adminUserService.count(query) > 0){
				model.put(MSG, "该账号己存在");
			}else{
				adminUserService.add(adminUser);
				model.put(SUCC, "新增成功");
				return list(new Pagination(), model);
			}
		}
		model.put("user", adminUser);
		return "adminuser/add";
	}
	
	private boolean validate(AdminUser adminUser,ModelMap model){
		if(StringUtils.isEmpty(adminUser.getName())){
			model.put(MSG, "姓名不能为空");
			return false;
		}else if(StringUtils.isEmpty(adminUser.getAccount())){
			model.put(MSG, "账号不能为空");
			return false;
		}
		return true;
	}
	
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam(value="id",required=true)String id,ModelMap model){
		AdminUser adminUser = adminUserService.findById(id);
		if(adminUser == null){
			model.put(MSG, "该用户己不存在");
			return list(new Pagination(), model);
		}
		model.put("user", adminUser);
		List<SystemMenu> menus = permissionManager.getMenus();
		model.put("menus", menus);
		return "adminuser/update";
	}
	
	@RequestMapping(value="update")
	public String update(AdminUser adminUser,ModelMap model){
		if(StringUtils.isEmpty(adminUser.getId())){
			model.put(MSG, "关键字段空缺");
		}else if(validate(adminUser, model)){
			adminUserService.update(adminUser, CollectionUtils.createSet(String.class, "name","mobile","roles"));
			model.put(SUCC, "更新成功");
		}
		model.put("user", adminUser);
		return "adminuser/update";
	}
	
	@RequestMapping(value="toEffect")
	public String toEffect(@RequestParam(value="id",required=true)String id,ModelMap model){
		AdminUser adminUser = adminUserService.findById(id);
		if(adminUser == null){
			model.put(MSG, "该用户己不存在");
			return list(new Pagination(), model);
		}else if(adminUser.getStatus() == AdminUserStatusEnum.EFFECT){
			model.put(MSG, "该用户己启用,不需要重复启用");
			return list(new Pagination(), model);
		}
		model.put("user", adminUser);
		return "adminuser/effect";
	}
	
	@RequestMapping(value="effect")
	public String effect(AdminUser adminUser,String repassword,ModelMap model){
		if(StringUtils.isEmpty(adminUser.getId())){
			model.put(MSG, "关键字段空缺");
		}else if(StringUtils.isEmpty(adminUser.getPassword())){
			model.put(MSG, "激活账号必须重置密码,密码不能为空");
		}else if(StringUtils.isEmpty(repassword) || !repassword.equals(adminUser.getPassword())){
			model.put(MSG, "两个密码不一致");
		}else{
			AdminUser old = adminUserService.findById(adminUser.getId());
			if(old == null){
				model.put(MSG, "用户不存在");
			}else{
				old.setPassword(SecurityUtil.encryptSHA(adminUser.getPassword()));
				old.setStatus(AdminUserStatusEnum.EFFECT);
				adminUserService.update(old, CollectionUtils.createSet(String.class, "password","status"));
				model.put(SUCC, "激活账号成功");
				return list(new Pagination(), model);
			}
		}
		model.put("user", adminUser);
		return "adminuser/effect";
	}
	
	@RequestMapping(value="disabled")
	public String disabled(@RequestParam(value="id",required=true)String id,ModelMap model){
		AdminUser adminUser = adminUserService.findById(id);
		if(adminUser == null){
			model.put(MSG, "该用户己不存在");
		}else{
			adminUser.setStatus(AdminUserStatusEnum.DISABLED);
			adminUserService.update(adminUser, CollectionUtils.createSet(String.class, "status"));
			model.put(SUCC, "己成功禁用");
		}
		model.put("user", adminUser);
		return list(new Pagination(), model);
	}
	
	@RequestMapping(value="toProfile")
	public String toProfile(ModelMap model){
		AdminUser user = WebContext.getLoginUser();
		model.put("user", user);
		return "adminuser/profile";
	}
	
	@RequestMapping(value="profile")
	public String profile(AdminUser adminUser,ModelMap model){
		AdminUser user = WebContext.getLoginUser();
		user.setHeadPhoto(adminUser.getHeadPhoto());
		user.setMobile(adminUser.getMobile());
		user.setName(adminUser.getName());
		adminUserService.update(user, CollectionUtils.createSet(String.class, "headPhoto","mobile","name"));
		return "adminuser/profile";
	}
	
	@RequestMapping(value="toPassword")
	public String toPassword(ModelMap model){
		AdminUser user = WebContext.getLoginUser();
		model.put("user", user);
		return "adminuser/password";
	}
	
	@RequestMapping(value="password")
	public String password(String oldPassword,String password,String repassword,ModelMap model){
		AdminUser user = WebContext.getLoginUser();
		if(user.getPassword().equals(oldPassword)){
			model.put(MSG, "旧密码不正确");
		}else if(password == null || "".equals(password.trim())){
			model.put(MSG, "密码不能为空");
		}else if(!password.trim().equals(repassword)){
			model.put(MSG, "前后密码不一致");
		}else{
			user.setPassword(SecurityUtil.encryptSHA(password.trim()));
			adminUserService.update(user, CollectionUtils.createSet(String.class, "password"));
			model.put(SUCC, "修改密码成功");
		}
		return "adminuser/password";
	}
}
