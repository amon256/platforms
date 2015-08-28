/**
 * BonusPayment.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**  
 * 功能描述：奖金发放，直接发放到账号
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
public class BonusPayment extends DataEntity {

	private static final long serialVersionUID = 8345096891875244405L;

	/**
	 * 发放用户
	 */
	@DBRef
	private User user;
	
	/**
	 * 发放用户
	 */
	@DBRef
	private AdminUser creater;
	
	/**
	 * 金额
	 */
	private BigDecimal amount;
	
	/**
	 * 描述
	 */
	private String description;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdminUser getCreater() {
		return creater;
	}

	public void setCreater(AdminUser creater) {
		this.creater = creater;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
