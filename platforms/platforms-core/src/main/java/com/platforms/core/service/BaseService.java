/**
 *  BaseService.java
 */
package com.platforms.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.platforms.core.domains.AbstractBean;
import com.platforms.core.utils.Pagination;

/**
 * @author fengmengyue
 * @since 2014年6月23日
 */
@Transactional
public interface BaseService<T extends AbstractBean> {
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	void insert(T entity);
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	int update(T entity);
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	int delete(T entity);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	T getById(Integer id);
	
	/**
	 * 以实体为参数查询
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly=true)
	List<T> queryByEntity(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @param pagination
	 * @return
	 */
	@Transactional(readOnly=true)
	Pagination<T> queryByEntity(T entity,Pagination<T> pagination);
}
