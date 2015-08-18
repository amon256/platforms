/**
 * 
 */
package com.winplan.entity;


/**  
 * 功能描述：管理员用户
 * 
 * @author FengMy
 * @since 2015年8月18日
 */
public class AdminUser extends DataEntity {
	private static final long serialVersionUID = -3038906912633866697L;

	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 电话
	 */
	private String mobile;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 上次登录时间
	 */
	private String lastLoginTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
