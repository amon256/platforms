/**
 * BonusPaymentServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.service.impl;

import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winplan.entity.BonusPayment;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusPaymentService;
import com.winplan.service.BonusService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
@Component
public class BonusPaymentServiceImpl extends DataServiceImpl<BonusPayment> implements BonusPaymentService {
	@Autowired
	private BonusService bonusService;
	
	@Override
	public BonusPayment add(BonusPayment entity) {
		BonusPayment data = super.add(entity);
		bonusService.addBonus(entity.getUser().getId(), entity.getAmount().setScale(2, RoundingMode.HALF_UP).floatValue(), 1.0f, BonusTypeEnum.TRANSFER_IN, true, entity.getDescription());
		return data;
	}
}
