/**
 * ConsumeRecord.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**  
 * 功能描述：消费记录
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Document(collection="consumerecord")
public class ConsumeRecord extends DataEntity {
	private static final long serialVersionUID = 8593612676259842353L;

	/**
	 * 用户账号
	 */
	@Indexed
	private String account;
	
	/**
	 * 消费金额
	 */
	private BigDecimal amount; 
	
	/**
	 * 返还期数
	 */
	private int periodNumber;
	
	/**
	 * 消费备注
	 */
	private String description;
	
	/**
	 * 己返还期数
	 */
	@Transient
	private int returned;
	
	/**
	 * 己返金额
	 */
	@Transient
	private BigDecimal returnedAmount;
	
	/**
	 * 未返还期数
	 */
	@Transient
	private int unreturn;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public int getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(int periodNumber) {
		this.periodNumber = periodNumber;
	}

	public int getReturned() {
		return returned;
	}

	public void setReturned(int returned) {
		this.returned = returned;
	}

	public int getUnreturn() {
		return unreturn;
	}

	public void setUnreturn(int unreturn) {
		this.unreturn = unreturn;
	}

	public BigDecimal getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(BigDecimal returnedAmount) {
		this.returnedAmount = returnedAmount;
	}
}
