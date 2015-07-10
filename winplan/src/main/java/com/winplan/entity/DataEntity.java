/**
 * DataEntity.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.entity;

import java.util.Date;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public class DataEntity extends CoreEntity {
	private static final long serialVersionUID = 3377057353374426809L;

	private Date createTime;
	
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
