/**
 * BonusCashApplyController.java.java
 * @author FengMy
 * @since 2015年8月24日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winplan.context.WebContext;
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
	public String applyList(Pagination pagination,ModelMap model){
		User user = WebContext.getLoginUser();
		Query query = Query.query(Criteria.where("applyer.$id").is(user.getId()));
		pagination.setRecordCount((int) applyService.count(query));
		query = query.with(new Sort(Direction.DESC, "createTime"));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<BonusCashApply> historys = applyService.findList(query);
		model.put("datas", historys);
		model.put("pagination", pagination);
		return "bonuscash/applyList";
	}
	
	@RequestMapping(value="toApply")
	public String toApply(ModelMap model){
		User user = WebContext.getLoginUser();
		if(existsApply(user)){
			model.put(MSG, "己存在未完成的申请,不允许重复申请");
			return applyList(new Pagination(), model);
		}
		user = userService.findById(user.getId());
		model.put("user", user);
		return "bonuscash/apply";
	}
	
	private boolean existsApply(User user){
		Query query = new Query(Criteria.where("applyer.$id").is(user.getId()).and("status").ne(BillStatusEnum.END));
		long existsSize = applyService.count(query);
		return existsSize > 0;
	}
	
	@RequestMapping(value="apply")
	public String apply(String applyAmount,ModelMap model){
		User user = WebContext.getLoginUser();
		user = userService.findById(user.getId());
		if(applyAmount == null || "".equals(applyAmount)){
			model.put(MSG, "金额不能为空");
		}else if(!applyAmount.trim().matches("[1-9][0-9]*00")){
			model.put(MSG, "只能填写100的整倍数");
		}else if(existsApply(user)){
			model.put(MSG, "己存在未完成的申请，不允许重复申请");
		}else{
			BigDecimal amount = new BigDecimal(applyAmount.trim());
			if(amount.compareTo(user.getBonus()) > 0){
				model.put(MSG, "余额不足");
			}else{
				BonusCashApply apply = new BonusCashApply();
				apply.setApplyAmount(amount);
				apply.setApplyer(user);
				apply.setResult(BillResultEnum.UNDECIDED);
				apply.setStatus(BillStatusEnum.APPLYING);
				applyService.add(apply);
				model.put(SUCC, "提交成功");
				return applyList(new Pagination(), model);
			}
		}
		model.put("user", user);
		return "bonuscash/apply";
	}
}
