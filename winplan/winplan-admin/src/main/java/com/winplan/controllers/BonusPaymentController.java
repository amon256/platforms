/**
 * BonusPaymentController.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winplan.annotations.TokenCreate;
import com.winplan.annotations.TokenValidate;
import com.winplan.context.WebContext;
import com.winplan.entity.AdminUser;
import com.winplan.entity.BonusPayment;
import com.winplan.entity.User;
import com.winplan.service.BonusPaymentService;
import com.winplan.service.UserService;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
@Controller
@RequestMapping(value="bonuspayment/*")
public class BonusPaymentController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BonusPaymentService bonusPaymentService;
	
	@RequestMapping(value="list")
	public String list(Pagination pagination,ModelMap model){
		Query query = new Query();
		pagination.setRecordCount((int) bonusPaymentService.count(query));
		query = query.with(new Sort(Direction.DESC, "createTime"));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<BonusPayment> datas = bonusPaymentService.findList(query);
		model.put("datas", datas);
		model.put("pagination", pagination);
		return "bonuspayment/list";
	}
	
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap model){
		return "bonuspayment/add";
	}
	
	@RequestMapping(value="add")
	@TokenCreate
	public String add(BonusPayment bonusPayment,ModelMap model){
		model.put("data", bonusPayment);
		if(bonusPayment.getUser() == null || StringUtils.isEmpty(bonusPayment.getUser().getAccount())){
			model.put(MSG, "账号不能为空");
		}else if(bonusPayment.getAmount() == null || bonusPayment.getAmount().compareTo(BigDecimal.ZERO) <= 0){
			model.put(MSG, "金额不合法");
		}else{
			User user = userService.findByAccount(bonusPayment.getUser().getAccount().trim());
			if(user == null){
				model.put(MSG, "账号不存在");
			}else{
				bonusPayment.setUser(user);
				model.put(SUCC, "请确认发放人及金额");
				return "bonuspayment/submit";//转到确认页面
			}
		}
		return "bonuspayment/add";
	}
	
	@RequestMapping(value="submit")
	@TokenCreate
	@TokenValidate
	public String submit(BonusPayment bonusPayment,ModelMap model){
		if(bonusPayment.getUser() == null || StringUtils.isEmpty(bonusPayment.getUser().getAccount())){
			model.put(MSG, "账号不能为空");
		}else if(bonusPayment.getAmount() == null || bonusPayment.getAmount().compareTo(BigDecimal.ZERO) <= 0){
			model.put(MSG, "金额不合法");
		}else{
			User user = userService.findByAccount(bonusPayment.getUser().getAccount().trim());
			if(user == null){
				model.put(MSG, "账号不存在");
			}else{
				bonusPayment.setUser(user);
				AdminUser adminuser = WebContext.getLoginUser();
				bonusPayment.setCreater(adminuser);
				bonusPaymentService.add(bonusPayment);
				model.put(SUCC, "发放成功");
				return list(new Pagination(), model);
			}
		}
		model.put("data", bonusPayment);
		return "bonuspayment/add";
	}
}
