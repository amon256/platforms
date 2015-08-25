/**
 * BonusCashApply.java.java
 * @author FengMy
 * @since 2015年8月24日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.winplan.enums.BillResultEnum;
import com.winplan.enums.BillStatusEnum;

/**  
 * 功能描述：奖金提现申请
 * 
 * @author FengMy
 * @since 2015年8月24日
 */
@Document(collection="bonuscashapply")
public class BonusCashApply extends DataEntity{
	private static final long serialVersionUID = -8279344676677669714L;

	/**
	 * 申请人
	 */
	@DBRef
	private User applyer;
	
	/**
	 * 申请金额
	 */
	private BigDecimal applyAmount;
	
	/**
	 * 审批人
	 */
	@DBRef
	private AdminUser approver;
	
	/**
	 * 状态
	 */
	private BillStatusEnum status;
	
	/**
	 * 结果
	 */
	private BillResultEnum result;
	
	/**
	 * 审批消息
	 */
	private String applyMsg;

	public User getApplyer() {
		return applyer;
	}

	public void setApplyer(User applyer) {
		this.applyer = applyer;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BillStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BillStatusEnum status) {
		this.status = status;
	}

	public BillResultEnum getResult() {
		return result;
	}

	public void setResult(BillResultEnum result) {
		this.result = result;
	}

	public AdminUser getApprover() {
		return approver;
	}

	public void setApprover(AdminUser approver) {
		this.approver = approver;
	}

	public String getApplyMsg() {
		return applyMsg;
	}

	public void setApplyMsg(String applyMsg) {
		this.applyMsg = applyMsg;
	}
}
