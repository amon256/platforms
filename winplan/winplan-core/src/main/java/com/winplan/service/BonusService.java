/**
 * BonusService.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.service;

import java.math.BigDecimal;

import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
public interface BonusService extends DataService<BonusHistory> {
	
	/**
	 * 奖金，amount为负数时扣，正数时为增加bonus
	 * @param userId
	 * @param amount
	 * @param taxRate 税后
	 * @param type
	 * @param incTotalBonus
	 * @param description
	 */
	public void addBonus(String userId,float amount,float taxRate,BonusTypeEnum type,boolean incTotalBonus,String description);
	
	/**
	 * 转让奖金
	 * @param from
	 * @param to
	 * @param bonus
	 * @param desc
	 */
	public void transfer(User from,User to,BigDecimal bonus,String desc);
}
