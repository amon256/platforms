/**
 * Sequence.java.java
 * @author FengMy
 * @since 2015年7月7日
 */
package com.winplan.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**  
 * 功能描述：sequence
 * 
 * @author FengMy
 * @since 2015年7月7日
 */
@Document
public class Sequence extends CoreEntity{
	private static final long serialVersionUID = 5876275947944561941L;
	
	private int currentValue;
	
	private Date lastDate;

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
}
