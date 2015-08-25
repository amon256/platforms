/**
 * User.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
@Document(collection="users")
public class User extends DataEntity {
	private static final long serialVersionUID = -9147696470362067547L;

	/**
	 * 账号
	 */
	@Indexed(unique=true)
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 奖金
	 */
	private BigDecimal bonus; 
	
	/**
	 * 累计奖金
	 */
	private BigDecimal totalBonus;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 路径
	 */
	private String path;
	
	/**
	 * 层级
	 */
	private int level;
	
	/**
	 * 左
	 */
	private String left;
	
	/**
	 * 左区人数
	 */
	private int leftCount;
	
	/**
	 * 左区深度
	 */
	private int leftDeep;
	
	/**
	 * 右
	 */
	private String right;
	
	/**
	 * 右区人数
	 */
	private int rightCount;
	
	/**
	 * 右区深度
	 */
	private int rightDeep;
	
	
	/**
	 * 推荐人
	 */
	private String recommend;
	
	/**
	 * 头像
	 */
	private String headPhoto;
	
	/**
	 * 银行帐号
	 */
	private String cardNumber;
	
	/**
	 * 开户行
	 */
	private String bankName;
	
	/**
	 * 开户行地址
	 */
	private String bankAddress;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BigDecimal getTotalBonus() {
		return totalBonus;
	}

	public void setTotalBonus(BigDecimal totalBonus) {
		this.totalBonus = totalBonus;
	}

	public int getRightCount() {
		return rightCount;
	}

	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}

	public int getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}

	public int getLeftDeep() {
		return leftDeep;
	}

	public void setLeftDeep(int leftDeep) {
		this.leftDeep = leftDeep;
	}

	public int getRightDeep() {
		return rightDeep;
	}

	public void setRightDeep(int rightDeep) {
		this.rightDeep = rightDeep;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
}
