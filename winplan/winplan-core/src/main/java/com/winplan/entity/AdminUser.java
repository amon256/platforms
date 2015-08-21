/**
 * 
 */
package com.winplan.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.winplan.enums.AdminUserStatusEnum;


/**  
 * 功能描述：管理员用户
 * 
 * @author FengMy
 * @since 2015年8月18日
 */
@Document(collection="adminuser")
public class AdminUser extends DataEntity {
	private static final long serialVersionUID = -3038906912633866697L;

	/**
	 * 姓名
	 */
	private String name;
	
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
	 * 电话
	 */
	private String mobile;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 管理员状态
	 */
	private AdminUserStatusEnum status;
	
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 角色
	 */
	private String roles;

	/**
	 * 头像
	 */
	private String headPhoto;
	
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public AdminUserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AdminUserStatusEnum status) {
		this.status = status;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
}
