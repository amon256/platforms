/**
 * DataServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.service.impl;

import java.util.Date;

import com.winplan.entity.DataEntity;
import com.winplan.service.DataService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public abstract class DataServiceImpl<T extends DataEntity> extends CoreServiceImpl<T> implements DataService<T> {

	@Override
	protected void setDefautAddValue(T entity) {
		super.setDefautAddValue(entity);
		if(entity.getCreateTime() == null){
			entity.setCreateTime(new Date());
		}
		if(entity.getLastUpdateTime() == null){
			entity.setLastUpdateTime(entity.getCreateTime());
		}
	}
}
