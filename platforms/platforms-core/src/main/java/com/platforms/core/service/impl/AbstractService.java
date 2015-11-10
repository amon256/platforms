/**
 *  AbstractService.java
 */
package com.platforms.core.service.impl;

import java.util.List;

import com.platforms.core.dao.BaseDao;
import com.platforms.core.domains.AbstractBean;
import com.platforms.core.service.BaseService;
import com.platforms.core.utils.Pagination;

/**
 * @author fengmengyue
 * @since 2014年6月23日
 */
public abstract class AbstractService<T extends AbstractBean> implements BaseService<T> {

	/**
	 * dao
	 * @return
	 */
	protected abstract BaseDao<T> getDao(); 
	
	@Override
	public void insert(T entity) {
		getDao().insert(entity);
	}

	@Override
	public int update(T entity) {
		return getDao().update(entity);
	}

	@Override
	public int delete(T entity) {
		return getDao().delete(entity);
	}

	@Override
	public T getById(Integer id) {
		return getDao().select(id);
	}

	@Override
	public List<T> queryByEntity(T entity) {
		return getDao().queryByEntity(entity);
	}
	
	@Override
	public Pagination<T> queryByEntity(T entity, Pagination<T> pagination) {
		return getDao().queryByEntity(entity, pagination);
	}
}
