/**
 * BaseDao.java
 * @since 2014年6月13日
 */
package com.platforms.core.dao;

import java.util.List;

import com.platforms.core.domains.AbstractBean;
import com.platforms.core.utils.Pagination;

/**
 * @desc 
 * @author fengmengyue
 *
 * @since 2014年6月13日
 */
public interface BaseDao<T extends AbstractBean>{
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	void insert(T entity);
	
	/**
	 * 批量新增
	 * @param entitys
	 * @return
	 */
	int insertBatch(List<T> entitys);
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	int update(T entity);
	
	/**
	 * 批量更新
	 * @param entitys
	 * @return
	 */
	int updateBatch(List<T> entitys);
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	int delete(T entity);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	int delete(String id);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	T select(Integer id);
	
	/**
	 * 以实体为参数查询
	 * @param entity
	 * @return
	 */
	List<T> queryByEntity(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @param pagination
	 * @return
	 */
	Pagination<T> queryByEntity(T entity,Pagination<T> pagination);
	
}
