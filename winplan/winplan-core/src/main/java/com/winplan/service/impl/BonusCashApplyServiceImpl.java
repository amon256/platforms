/**
 * BonusCashApplyServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月24日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.winplan.entity.BonusCashApply;
import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;
import com.winplan.enums.BillResultEnum;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusCashApplyService;
import com.winplan.service.BonusService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月24日
 */
@Component
public class BonusCashApplyServiceImpl extends DataServiceImpl<BonusCashApply> implements BonusCashApplyService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BonusService bonusService;
	
	@Override
	public String process(BonusCashApply apply, BillResultEnum result) {
		if(result == BillResultEnum.AGREE){
			FindAndModifyOptions options = new FindAndModifyOptions();
			options.returnNew(true);
			//源账号扣除bonus
			User user = getMongoTemplate().findAndModify(
					Query.query(Criteria.where("_id").is(apply.getApplyer().getId())), 
					Update.update("lastUpdateTime", new Date()).inc("bonus", BigDecimal.ZERO.subtract(apply.getApplyAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue()),
					options, 
					User.class);
			BonusHistory his = new BonusHistory();
			his.setBonus(apply.getApplyAmount());
			his.setAccount(apply.getApplyer().getAccount());
			his.setDesc("提现");
			his.setType(BonusTypeEnum.BONUS_KOU);
			his.setSurplus(user.getBonus());
			his.setTotalBonus(apply.getApplyer().getTotalBonus());
			bonusService.add(his);
		}
		update(apply, CollectionUtils.createSet(String.class, "status","result","approver","applyMsg"));
		return null;
	}
}
