/**
 * UserController.java.java
 * @author FengMy
 * @since 2015年7月6日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.winplan.annotations.TokenCreate;
import com.winplan.annotations.TokenValidate;
import com.winplan.context.SystemContext;
import com.winplan.context.WebContext;
import com.winplan.entity.User;
import com.winplan.service.UserService;
import com.winplan.tree.TreeBuild;
import com.winplan.tree.UserNode;
import com.winplan.tree.ZTreeNode;
import com.winplan.utils.CollectionUtils;
import com.winplan.utils.SecurityUtil;

/**  
 * 功能描述：用户controller
 * 
 * @author FengMy
 * @since 2015年7月6日
 */
@Controller
@RequestMapping(value="user/*")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="userinfo")
	public String userInfo(@RequestParam(value="id",required=true)String id,ModelMap model){
		User user = userService.findById(id);
		model.put("user", user);
		return "user/userInfo";
	}
	
	@RequestMapping(value="useredit")
	public String userEdit(ModelMap model){
		User user = WebContext.getLoginUser();
		model.put("user", user);
		return "user/userEdit";
	}
	
	@RequestMapping(value="userSave")
	public String userSave(User user,ModelMap model){
		User existsUser = WebContext.getLoginUser();
		existsUser = userService.findById(existsUser.getId());
		if(existsUser != null){
			existsUser.setMobile(user.getMobile());
			existsUser.setNickName(user.getNickName());
			userService.update(existsUser, CollectionUtils.createSet(String.class, "mobile","nickName"));
			WebContext.login(existsUser);
			model.put("user", existsUser);
			model.put("succ", "信息修改成功.");
		}
		return "user/userEdit";
	}
	
	@RequestMapping(value="password")
	public String password(ModelMap model){
		User user = WebContext.getLoginUser();
		model.put("user", user);
		return "user/password";
	}
	
	@RequestMapping(value="changepwd")
	public String changepwd(String pwd,String npwd,String npwd1,ModelMap model){
		if(StringUtils.isEmpty(pwd) || StringUtils.isEmpty(npwd) || StringUtils.isEmpty(npwd1)){
			model.put("msg", "密码不能为空");
		}else if(!npwd.equals(npwd1)){
			model.put("msg", "新密码两次输入不一致");
		}else{
			User existsUser = WebContext.getLoginUser();
			existsUser = userService.findById(existsUser.getId());
			if(existsUser != null){
				if(existsUser.getPassword().equals(SecurityUtil.encryptSHA(pwd))){
					existsUser.setPassword(SecurityUtil.encryptSHA(npwd));
					userService.update(existsUser, CollectionUtils.createSet(String.class, "password"));
					model.put("succ", "修改密码成功,下次请用新密码登录.");
				}else{
					model.put("msg", "旧密码错误");
				}
			}
		}
		User user = WebContext.getLoginUser();
		model.put("user", user);
		return "user/password";
	}
	
	@RequestMapping(value="usergragh")
	public String userGragh(String account,ModelMap model){
		User existsUser = WebContext.getLoginUser();
		if(account == null){
			account = existsUser.getAccount();
		}
		User user = userService.findOne(Query.query(Criteria.where("account").is(account).and("path").regex("^"+existsUser.getPath())));
		if(user == null){
			model.put("msg", "权限不足或该账号不存在");
		}else{
			List<User> users = userService.findList(Query.query(Criteria.where("path").regex("^" + user.getPath()).and("level").gte(user.getLevel()).lte(user.getLevel() + 2)));
			UserNode root = TreeBuild.buildUserTree(users, user);
			model.put("root", root);
		}
		return "user/userGragh";
	}
	
	@RequestMapping(value="toregister")
	@TokenCreate
	public String toRegister(String parentAccount,String dir,ModelMap model){
		User existsUser = WebContext.getLoginUser();
		existsUser = userService.findById(existsUser.getId());
		if(existsUser.getBonus() == null || existsUser.getBonus().compareTo(BigDecimal.valueOf(SystemContext.getBonusPerRegisterUser())) < 0){
			model.put("msg", "您的奖金未满1180元.");
		}
		User user = new User();
		if(parentAccount != null){
			User parentUser = userService.findOne(Query.query(Criteria.where("account").is(parentAccount).and("path").regex("^" + existsUser.getPath())));
			model.put("parentUser", parentUser);
		}
		model.put("user", user);
		model.put("dir", dir);
		return "user/register";
	}
	
	@RequestMapping(value="register")
	@TokenValidate
	@TokenCreate
	public String register(User user,String parentAccount,String dir,ModelMap model){
		User existsUser = WebContext.getLoginUser();
		existsUser = userService.findById(existsUser.getId());
		User parentUser = null;
		User recommendUser = null;
		boolean validateFlag = true;
		String validateMsg = null;
		if(!("left".equals(dir) || "right".equals(dir))){
			validateMsg = "非法数据";
			validateFlag = false;
		}
		if(StringUtils.isEmpty(user.getName())){
			validateMsg = "请填写姓名";
			validateFlag = false;
		}
		if(StringUtils.isEmpty(user.getNickName())){
			validateMsg = "请填写昵称";
			validateFlag = false;
		}
		if(StringUtils.isEmpty(user.getMobile())){
			validateMsg = "请填写电话号码";
			validateFlag = false;
		}
		if(validateFlag){
			if(parentAccount != null && !"".equals(parentAccount.trim())){
				parentUser = userService.findOne(Query.query(Criteria.where("account").is(parentAccount).and("path").regex("^" + existsUser.getPath())));
				if(parentUser == null){
					validateMsg = "接点人不存在";
					validateFlag = false;
				}else{
					if("left".equals(dir) && parentUser.getLeft() != null){
						validateMsg = parentUser.getAccount() + "的左边己注册";
						validateFlag = false;
					}else if("right".equals(dir) && parentUser.getRight() != null){
						validateMsg = parentUser.getAccount() + "的右边己注册";
						validateFlag = false;
					}else{
						//登录人奖金
						if(existsUser.getBonus().compareTo(new BigDecimal(SystemContext.getBonusPerRegisterUser())) < 0){
							validateMsg = "您的剩余奖金不足" + SystemContext.getBonusPerRegisterUser();
							validateFlag = false;
						}
					}
				}
			}
		}
		if(validateFlag){
			if(user.getRecommend() != null && !"".equals(user.getRecommend().trim())){
				recommendUser = userService.findOne(Query.query(Criteria.where("account").is(user.getRecommend())));
				if(recommendUser == null){
					validateMsg = "推荐人不存在";
					validateFlag = false;
				}else{
					user.setRecommend(recommendUser.getAccount());
				}
			}
		}
		if(validateFlag){
			userService.register(user,parentUser,existsUser,dir);
			model.put("succ", "注册成功,新用户账号:" + user.getAccount() + ",密码:" + SystemContext.getDefaultPassword());
		}else{
			model.put("user", user);
			model.put("dir", dir);
			model.put("msg", validateMsg);
			model.put("parentUser", parentUser);
			return "user/register";
		}
		return "user/userInfo";
	}
	
	@RequestMapping(value="recommend")
	public String recommend(String account){
		return "user/recommend";
	}
	
	@RequestMapping(value="recommendData")
	@ResponseBody
	public List<ZTreeNode> recommendData(@RequestParam(value="id",required=false)String account){
		List<ZTreeNode> resultList = new ArrayList<ZTreeNode>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<User> users = new LinkedList<User>();
		if(account == null || "".equals(account.trim())){
			users.add(WebContext.getLoginUser());
		}else{
			Query query = Query.query(Criteria.where("recommend").is(account)).with(new Sort(Direction.ASC, "createTime"));
			users = userService.findList(query);
		}
		if(users != null && users.size() > 0){
			Map<String,Integer> accountRecommend = accountRecommend(users);
			boolean isParent = true;
			for(User user : users){
				if(accountRecommend.containsKey(user.getAccount()) && accountRecommend.get(user.getAccount()) > 0){
					isParent = true;
				}else{
					isParent = false;
				}
				resultList.add(createNode(user,isParent, sdf));
			}
		}
		return resultList;
	}
	
	private Map<String,Integer> accountRecommend(List<User> users){
		List<String> accounts = new LinkedList<String>();
		for(User user : users){
			accounts.add(user.getAccount());
		}
		Map<String,Integer> recommendAccount = userService.groupRecommend(accounts);
		return recommendAccount;
	}
	
	private ZTreeNode createNode(User user,boolean isParent, SimpleDateFormat sdf){
		ZTreeNode node = new ZTreeNode();
		node.setId(user.getAccount());
		StringBuilder name = new StringBuilder();
		name.append(user.getAccount()).append("(").append(user.getName()).append(")(");
		if(user.getCreateTime() != null){
			name.append(sdf.format(user.getCreateTime()));
		}
		name.append(")");
		node.setName(name.toString());
		node.setIsParent(isParent);
		return node;
	}
}
