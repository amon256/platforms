/**
 * CoreEntity.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**  
 * 功能描述：实体基类
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public abstract class CoreEntity implements Serializable{
	private static final long serialVersionUID = 925904316481738175L;
	
	@Id
	@Field(value="_id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
