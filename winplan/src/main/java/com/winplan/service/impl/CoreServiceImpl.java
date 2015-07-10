/**
 * CoreServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.winplan.entity.CoreEntity;
import com.winplan.entity.Sequence;
import com.winplan.service.CoreService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public abstract class CoreServiceImpl<T extends CoreEntity> implements CoreService<T> {

	/**
	 * MongoTemplate实例
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public T add(T entity) {
		if(entity == null){
			return null;
		}
		setDefautAddValue(entity);
		mongoTemplate.insert(entity);
		return entity;
	}
	
	protected void setDefautAddValue(T entity){
		if(entity.getId() == null){
			entity.setId(UUID.randomUUID().toString().toLowerCase().replaceAll("-", ""));
		}
	}
	
	@Override
	public void add(List<T> entityList) {
		if(entityList == null){
			return;
		}
		for(T entity : entityList){
			if(entity != null){
				setDefautAddValue(entity);
			}
		}
		mongoTemplate.insert(entityList, getCurrentClass());
	}
	
	@Override
	public void update(T entity, Collection<String> updateFields) {
		if(entity == null || entity.getId() == null || updateFields == null || updateFields.isEmpty()){
			return;
		}
		Update update = Update.update("lastUpdateTime", new Date());
		try{
			for(String field : updateFields){
				update.set(field, BeanUtils.getPropertyDescriptor(entity.getClass(), field).getReadMethod().invoke(entity, new Object[]{}));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(entity.getId())), update, entity.getClass());
	}
	
	@Override
	public void update(Query query, Update update) {
		mongoTemplate.updateMulti(query, update, getCurrentClass());
	}
	
	@Override
	public boolean delete(T entity) {
		mongoTemplate.remove(entity);
		return true;
	}
	
	@Override
	public boolean checkExists(Criteria criteria) {
		return mongoTemplate.exists(Query.query(criteria), getCurrentClass());
	}
	
	public T findById(String id){
		Class<T> entityClass = getCurrentClass();
		return mongoTemplate.findById(id, entityClass);
	}
	
	@Override
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, getCurrentClass());
	}
	
	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(getCurrentClass());
	}
	
	@Override
	public List<T> findList(Query query) {
		return mongoTemplate.find(query, getCurrentClass());
	}
	
	@Override
	public long count(Query query) {
		return mongoTemplate.count(query, getCurrentClass());
	}
	
	protected Class<T> getCurrentClass(){
		ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass(); 
		@SuppressWarnings("unchecked")
		Class<T> entityClass= (Class<T>)(parameterizedType.getActualTypeArguments()[0]); 
		return entityClass;
	}
	
	@Override
	public synchronized int getNextInt(String key) {
		if(key == null){
			throw new IllegalArgumentException("key is null");
		}
		Sequence sequence = mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(key)), Update.update("lastDate", new Date()).inc("currentValue", 1), Sequence.class);
		if(sequence == null){
			sequence = new Sequence();
			sequence.setId(key);
			sequence.setCurrentValue(1);
			sequence.setLastDate(new Date());
			mongoTemplate.insert(sequence);
		}
		return sequence.getCurrentValue();
	}
	
	/**
	 * 获取MongoTemplate
	 */
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
}
