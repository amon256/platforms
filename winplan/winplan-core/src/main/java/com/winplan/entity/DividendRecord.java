/**
 * DividendRecord.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**  
 * 功能描述：分红记录
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
@Document(collection="dividendrecord")
public class DividendRecord extends DataEntity {
	private static final long serialVersionUID = 5997992731339631222L;

	/**
	 * 分红人
	 */
	@DBRef
	private User user;
	
	/**
	 * 分红策略
	 */
	@DBRef
	private DividendStrategy strategy;
	
	/**
	 * 金额
	 */
	private BigDecimal amount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DividendStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(DividendStrategy strategy) {
		this.strategy = strategy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
