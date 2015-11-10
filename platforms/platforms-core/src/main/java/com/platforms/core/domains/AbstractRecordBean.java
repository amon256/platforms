/**
 *  AbstractRecordBean.java
 */
package com.platforms.core.domains;

import java.util.Date;

import javax.persistence.Column;

/**
 * 记录型实体基类
 * @author fengmengyue
 * @since 2014年6月28日
 */
public abstract class AbstractRecordBean extends AbstractBean {
	private static final long serialVersionUID = 4042376943126353696L;
	
	/**
	 * 记录创建时间
	 */
	@Column(name="f_create_time",columnDefinition="DATETIME")
	private Date createTime;
	
	/**
	 * 记录最后修改时间
	 * 
	 */
	@Column(name="f_last_update_time",columnDefinition="DATETIME")
	private Date lastUpdateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
