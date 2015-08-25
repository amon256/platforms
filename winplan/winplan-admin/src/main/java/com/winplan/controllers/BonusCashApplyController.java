/**
 * BonusCashApplyController.java.java
 * @author FengMy
 * @since 2015年8月24日
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winplan.annotations.TokenCreate;
import com.winplan.annotations.TokenValidate;
import com.winplan.context.WebContext;
import com.winplan.entity.AdminUser;
import com.winplan.entity.BonusCashApply;
import com.winplan.entity.User;
import com.winplan.enums.BillResultEnum;
import com.winplan.enums.BillStatusEnum;
import com.winplan.service.BonusCashApplyService;
import com.winplan.service.UserService;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月24日
 */
@Controller
@RequestMapping(value="bonuscash/*")
public class BonusCashApplyController extends BaseController {

	@Autowired
	private BonusCashApplyService applyService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="applyList")
	public String applyList(Pagination pagination,User user,ModelMap model){
		Query query = new Query();
		if(user != null && user.getAccount() != null && !"".equals(user.getAccount().trim())){
			user = userService.findByAccount(user.getAccount());
			if(user != null){
				query.addCriteria(Criteria.where("applyer.$id").is(user.getId()));
			}else{
				model.put(MSG, "用户不存在");
			}
		}
		pagination.setRecordCount((int) applyService.count(query));
		query = query.with(new Sort(Direction.DESC, "createTime"));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<BonusCashApply> historys = applyService.findList(query);
		model.put("datas", historys);
		model.put("pagination", pagination);
		model.put("queryUser", user);
		return "bonuscash/applyList";
	}
	
	@RequestMapping(value="toProcess")
	@TokenCreate
	public String toProcess(@RequestParam(value="id",required=true)String id,ModelMap model){
		BonusCashApply apply = applyService.findById(id);
		if(apply == null || apply.getStatus() == BillStatusEnum.END){
			model.put(MSG, "该申请单己不存在或己被处理");
			return applyList(new Pagination(), null, model);
		}
		model.put("apply", apply);
		return "bonuscash/process";
	}
	
	@RequestMapping(value="process")
	@TokenCreate
	@TokenValidate
	public String process(BonusCashApply apply,ModelMap model){
		BonusCashApply applyBill = applyService.findById(apply.getId());
		AdminUser adminUser = WebContext.getLoginUser();
		applyBill.setApplyMsg(apply.getApplyMsg());
		applyBill.setResult(apply.getResult());
		applyBill.setApprover(adminUser);
		applyBill.setStatus(BillStatusEnum.END);
		if(apply.getResult() == BillResultEnum.AGREE){
			if(applyBill.getApplyAmount().compareTo(applyBill.getApplyer().getBonus()) > 0){
				model.put(MSG, "用户余额己不足，不能提现");
				model.put("apply", applyBill);
				return "bonuscash/process";
			}
		}
		applyService.process(applyBill, apply.getResult());
		model.put(SUCC, "处理成功");
		return applyList(new Pagination(), null, model);
	}
	
}
