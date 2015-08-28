/**
 * BonusCashApplyServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月24日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winplan.entity.BonusCashApply;
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
			bonusService.addBonus(apply.getApplyer().getId(), 
					BigDecimal.ZERO.subtract(apply.getApplyAmount()).setScale(2, RoundingMode.HALF_UP).floatValue(), 1.0f, 
					BonusTypeEnum.BONUS_KOU, false, "提现");
		}
		update(apply, CollectionUtils.createSet(String.class, "status","result","approver","applyMsg"));
		return null;
	}
}
