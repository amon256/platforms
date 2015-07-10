/**
 * BonusService.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.service;

import java.math.BigDecimal;

import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
public interface BonusService extends DataService<BonusHistory> {
	
	public void transfer(User from,User to,BigDecimal bonus,String desc);
}
