/**
 * ReturnRecordServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.service.impl;

import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.winplan.entity.ReturnRecord;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.ConsumeRecordService;
import com.winplan.service.ReturnRecordService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Component
public class ReturnRecordServiceImpl extends CoreServiceImpl<ReturnRecord> implements ReturnRecordService {
	private static final Logger logger = LoggerFactory.getLogger(ReturnRecordServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BonusService bonusService;
	
	@Autowired
	private ConsumeRecordService consumeRecordService;
	
	@Override
	public List<ReturnRecord> findByConsumeRecordId(String id) {
		Query query = Query.query(Criteria.where("consumeRecord.$id").is(id));
		query.with(new Sort(Direction.ASC, "periodIndex"));
		return this.getMongoTemplate().find(query, getCurrentClass());
	}
	
	@Override
	public void returnRecordExecute(Date beforeDate) {
		Query query = Query.query(Criteria.where("returnDate").lt(beforeDate).and("complete").is(false));
		final MongoConverter converter = getMongoTemplate().getConverter();
		final Map<String,Integer> sizeMap = new HashMap<String, Integer>();
		sizeMap.put("size", 0);
		getMongoTemplate().executeQuery(query, ReturnRecord.class.getAnnotation(Document.class).collection(), new DocumentCallbackHandler() {
			@Override
			public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
				ReturnRecord record = converter.read(ReturnRecord.class, dbObject);
				executeReturnRecord(record);
				sizeMap.put("size", sizeMap.get("size") + 1);
			}
		});
		logger.info("己执行{}条定期返现记录",sizeMap.get("size"));
	}
	
	private void executeReturnRecord(ReturnRecord record){
		User user = userService.findByAccount(record.getConsumeRecord().getAccount());
		if(user == null){
			return;
		}
		bonusService.addBonus(user.getId(), record.getAmount().setScale(2, RoundingMode.HALF_UP).floatValue(), 1.00f, BonusTypeEnum.ADD, true, "返现");
		//返还记录标记己完成
		record.setComplete(true);
		record.setReturnTime(new Date());
		this.update(record, CollectionUtils.createSet(String.class, "complete","returnTime"));
		//消费记录更新己返还次数，己返金额
		consumeRecordService.update(
				Query.query(Criteria.where("_id").is(record.getConsumeRecord().getId())), 
				Update.update("lastUpdateTime", new Date()).inc("returned", 1).inc("returnedAmount", record.getAmount().floatValue())
		);
	}

}
