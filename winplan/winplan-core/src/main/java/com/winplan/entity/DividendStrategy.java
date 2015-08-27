/**
 * DividendStrategy.java.java
 * @author FengMy
 * @since 2015年8月27日
 */
package com.winplan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**  
 * 功能描述：分红策略
 * 
 * @author FengMy
 * @since 2015年8月27日
 */
@Document(collection="dividendstrategy")
public class DividendStrategy extends DataEntity {
	private static final long serialVersionUID = 895592958323838395L;
	
	/**
	 * 策略名称
	 */
	private String name;

	/**
	 * 预计发放金额
	 */
	private BigDecimal expect;
	
	/**
	 * 预计发放日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	/**
	 * 实际发放
	 */
	private BigDecimal reality;
	
	/**
	 * 人均
	 */
	private BigDecimal percapita;
	
	/**
	 * 人员范围策略
	 */
	private ArrayList<Strategy> strategys;
	
	/**
	 * 是否己完成
	 */
	private Boolean complete;
	
	/**
	 * 结果描述
	 */
	private String result;
	
	/**
	 * 发放时间
	 */
	private Date giveTime;
	
	/**
	 * 备注
	 */
	private String description;
	

	public BigDecimal getExpect() {
		return expect;
	}

	public void setExpect(BigDecimal expect) {
		this.expect = expect;
	}

	public BigDecimal getReality() {
		return reality;
	}

	public void setReality(BigDecimal reality) {
		this.reality = reality;
	}

	public BigDecimal getPercapita() {
		return percapita;
	}

	public void setPercapita(BigDecimal percapita) {
		this.percapita = percapita;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Date getGiveTime() {
		return giveTime;
	}

	public void setGiveTime(Date giveTime) {
		this.giveTime = giveTime;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Strategy> getStrategys() {
		return strategys;
	}

	public void setStrategys(ArrayList<Strategy> strategys) {
		this.strategys = strategys;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**  
	 * 功能描述：人员范围策略
	 * 
	 * @author FengMy
	 * @since 2015年8月27日
	 */
	public static class Strategy{
		
		private String fieldName;
		
		private String operation;
		
		private String value;

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
