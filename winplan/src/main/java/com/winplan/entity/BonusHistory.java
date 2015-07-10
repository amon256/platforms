/**
 * BonusHistory.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.winplan.enums.BonusTypeEnum;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
@Document(collection="bonushistory")
public class BonusHistory extends DataEntity {
	private static final long serialVersionUID = -7732117029237665355L;
	
	private BonusTypeEnum type;

	/**
	 * 金额
	 */
	private BigDecimal bonus;
	
	/**
	 * 记录归属账号
	 */
	private String account;
	
	/**
	 * 他方账号
	 */
	private String otherAccount;
	
	/**
	 * 剩余金额
	 */
	private BigDecimal surplus;
	
	/**
	 * 备注
	 */
	private String desc;

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public BigDecimal getSurplus() {
		return surplus;
	}

	public void setSurplus(BigDecimal surplus) {
		this.surplus = surplus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BonusTypeEnum getType() {
		return type;
	}

	public void setType(BonusTypeEnum type) {
		this.type = type;
	}
}
