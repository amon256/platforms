/**
 * BonusController.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.winplan.context.WebContext;
import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
@Controller
@RequestMapping(value="bonus/*")
public class BonusController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BonusService bonusService;

	@RequestMapping(value="totransfer")
	public String toTransfer(ModelMap model){
		User user = WebContext.getLoginUser();
		user = userService.findById(user.getId());
		model.put("user", user);
		return "bonus/transfer";
	}
	
	@RequestMapping(value="transfer")
	public String transfer(@RequestParam(required=true)String account,@RequestParam(required=true)BigDecimal bonus,String desc,ModelMap model){
		User to = userService.findByAccount(account);
		if(to == null){
			model.put("msg", "转出账户不存在.");
		}else if(bonus.compareTo(BigDecimal.ZERO) <= 0){
			model.put("msg", "转账金额必须大于0.");
		}else{
			bonusService.transfer(WebContext.getLoginUser(), to, bonus,desc);
			model.put("succ", "转账成功.");
		}
		User user = WebContext.getLoginUser();
		user = userService.findById(user.getId());
		model.put("user", user);
		return "bonus/transfer";
	}
	
	@RequestMapping(value="transferhistory")
	public String transferhistory(@RequestParam(defaultValue="0")int skip,@RequestParam(defaultValue="10")int size,ModelMap model){
		User user = WebContext.getLoginUser();
		Query query = Query.query(Criteria.where("account").is(user.getAccount()).and("type").in(CollectionUtils.createSet(BonusTypeEnum.class, BonusTypeEnum.TRANSFER_IN,BonusTypeEnum.TRANSFER_OUT)))
				.with(new Sort(Direction.DESC, "createTime"));
		query.skip(skip).limit(size);
		List<BonusHistory> historys = bonusService.findList(query);
		model.put("datas", historys);
		model.put("skip", skip);
		model.put("size", size);
		return "bonus/transferHistory";
	}
}
