/**
 * BonusCashApply.java.java
 * @author FengMy
 * @since 2015年8月24日
 */
package com.winplan.service;

import com.winplan.entity.BonusCashApply;
import com.winplan.enums.BillResultEnum;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月24日
 */
public interface BonusCashApplyService extends DataService<com.winplan.entity.BonusCashApply> {

	/**
	 * 处理申请单
	 * @param apply
	 * @param result
	 * @return
	 */
	public String process(BonusCashApply apply,BillResultEnum result);
}
