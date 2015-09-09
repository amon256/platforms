/**
 * CoreService.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBObject;
import com.winplan.entity.CoreEntity;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public interface CoreService<T extends CoreEntity> {
	/**
	 * 增加实体
	 */
	public T add(T entity);
	
	/**
	 * 批量插入
	 */
	public void add(List<T> entityList);
	
	/**
	 * 更新数据
	 */
	public void update(T entity,Collection<String> updateFields);
	
	/**
	 * 自定义更新
	 */
	public void update(Query query,Update update);
	
	/**
	 * 删除实体
	 */
	public boolean delete(T Entity);
	
	/**
	 * 检查是否存在数据
	 */
	public boolean checkExists(Criteria criteria);
	
	/**
	 * 按ID查找
	 */
	public T findById(String id);
	
	/**
	 * 查询所有结果
	 */
	public List<T> findAll();
	
	/**
	 * 查询唯一对象
	 */
	public T findOne(Query query);
	
	/**
	 * 自定义查询
	 */
	public List<T> findList(Query query);
	
	/**
	 * 查询数据条数
	 */
	public long count(Query query);
	
	/**
	 * 获取下一个int值
	 * @return
	 */
	public int getNextInt(String key);
	
	/**
	 * 分组查询
	 * @param command
	 * @return
	 */
	public DBObject group(DBObject keys, DBObject condition, DBObject initial, String reduce, String finalize);
	
	/**
	 * 分组查询
	 * @param args   
	 *  args.put( "key" , keys );//args.put( "keyf" , "function(doc){......return {key,value}}" );
        args.put( "cond" , condition );
        args.put( "$reduce" , reduce );
        args.put( "initial" , initial );
	 * @return
	 */
	public DBObject group(DBObject args);
	
}
