/**
 * DividendStrategyService.java.java
 * @author FengMy
 * @since 2015年8月27日
 */
package com.winplan.service;

import com.winplan.entity.DividendStrategy;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月27日
 */
public interface DividendStrategyService extends DataService<DividendStrategy> {

	/**
	 * 开始分红处理
	 */
	public void startDividend();
	
	/**
	 * 执行分红策略
	 * @param strategy
	 * @throws Exception 
	 */
	public void executeDividend(DividendStrategy strategy) throws Exception;
}
