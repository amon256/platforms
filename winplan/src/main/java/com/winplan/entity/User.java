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
	 * 右
	 */
	private String right;
	
	/**
	 * 推荐人
	 */
	private String recommend;

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
	
	
}
