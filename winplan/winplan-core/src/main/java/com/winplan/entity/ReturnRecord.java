/**
 * ReturnRecord.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Document(collection="returnrecord")
public class ReturnRecord extends CoreEntity {
	private static final long serialVersionUID = -7520693137584497347L;
	
	/**
	 * 消费记录
	 */
	@DBRef
	private ConsumeRecord consumeRecord;

	/**
	 * 返还金额
	 */
	private BigDecimal amount;
	
	/**
	 * 计划返还日期
	 */
	private Date returnDate;
	
	/**
	 * 实际返还时间
	 */
	private Date returnTime;
	
	/**
	 * 当前期数
	 */
	private int periodIndex;
	
	/**
	 * 是否己返还
	 */
	private Boolean complete;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public ConsumeRecord getConsumeRecord() {
		return consumeRecord;
	}

	public void setConsumeRecord(ConsumeRecord consumeRecord) {
		this.consumeRecord = consumeRecord;
	}

	public int getPeriodIndex() {
		return periodIndex;
	}

	public void setPeriodIndex(int periodIndex) {
		this.periodIndex = periodIndex;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
}
