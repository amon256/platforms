/**
 * DividendStrategyServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月27日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.winplan.context.BonusContext;
import com.winplan.entity.DividendRecord;
import com.winplan.entity.DividendStrategy;
import com.winplan.entity.DividendStrategy.Strategy;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.DividendRecordService;
import com.winplan.service.DividendStrategyService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月27日
 */
@Component
public class DividendStrategyServiceImpl extends DataServiceImpl<DividendStrategy> implements DividendStrategyService {
	private static final Logger logger = LoggerFactory.getLogger(DividendStrategyServiceImpl.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private BonusService bonusService;
	
	@Autowired
	private DividendRecordService dividendRecordService;
	
	@Override
	public void startDividend() {
		Query query = Query.query(Criteria.where("date").lte(new Date()).and("complete").is(false)).with(new Sort(Direction.ASC, "createTime"));
		List<DividendStrategy> strategys = this.findList(query);
		if(strategys != null && !strategys.isEmpty()){
			logger.info("查询到{}条分红策略需要处理.",strategys.size());
			for(DividendStrategy strategy : strategys){
				strategy.setGiveTime(new Date());
				strategy.setComplete(true);
				try{
					executeDividend(strategy);
				}catch(Exception e){
					logger.error("执行策略:{},发生异常",strategy.getName());
					logger.error("执行策略异常", e);
					strategy.setResult("执行策略时，程序异常");
				}finally{
					this.update(strategy, CollectionUtils.createSet(String.class, "complete","giveTime","percapita","reality","result"));//更新完成标识，执行时间，人均，实际，结果描述
				}
			}
		}else{
			logger.info("当前无分红策略需要执行");
		}
	}

	@Override
	public void executeDividend(final DividendStrategy strategy) throws ParseException {
		logger.info("开始执行分红策略:{}",strategy.getName());
		Query query = createQueryByStrategy(strategy);
		long count = userService.count(query);
		logger.info("根据策略定义，查询到{}个用户",count);
		if(count > 0){
			final BigDecimal percapita = strategy.getExpect().divide(BigDecimal.valueOf(count), 2, RoundingMode.DOWN);//采用舍位法，防止总额超支
			final BigDecimal reality = percapita.multiply(BigDecimal.valueOf(count));
			strategy.setPercapita(percapita);
			strategy.setReality(reality);
			strategy.setResult("发放完成，人均发放" + percapita.toString());
			final MongoConverter converter = getMongoTemplate().getConverter();
			getMongoTemplate().executeQuery(query, User.class.getAnnotation(Document.class).collection(), new DocumentCallbackHandler() {
				@Override
				public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
					User user = converter.read(User.class, dbObject);
					//增加奖金和奖金累计
					bonusService.addBonus(user.getId(), percapita.floatValue(), BonusContext.getTaxRate(), BonusTypeEnum.ADD, true, "商城分红");
					DividendRecord record = new DividendRecord();
					record.setAmount(percapita);;
					record.setUser(user);
					record.setStrategy(strategy);
					dividendRecordService.add(record);
				}
			});
		}else{
			strategy.setPercapita(BigDecimal.ZERO);
			strategy.setReality(BigDecimal.ZERO);
			strategy.setResult("根据策略设置，没有查找到可分红的用户");
		}
		
	}
	
	private Query createQueryByStrategy(DividendStrategy strategy) throws ParseException{
		Query query = new Query();
		if(strategy.getStrategys() != null && !strategy.getStrategys().isEmpty()){
			List<Strategy> strategys = strategy.getStrategys();
			Criteria criteria = Criteria.where("level").exists(true);
			for(Strategy stra : strategys){
				criteria = criteria.and(stra.getFieldName());
				Object value = stra.getValue();
				if("createTime".equals(stra.getFieldName())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					value = sdf.parse(stra.getValue());
				}
				if("$eq".equals(stra.getOperation())){
					criteria = criteria.is(value);
				}else if("$lt".equals(stra.getOperation())){
					criteria = criteria.lt(value);
				}else if("$lte".equals(stra.getOperation())){
					criteria = criteria.lte(value);
				}else if("$gt".equals(stra.getOperation())){
					criteria = criteria.gt(value);
				}else if("$gte".equals(stra.getOperation())){
					criteria = criteria.gte(value);
				}
			}
			query = new Query(criteria);
		}
		return query;
	}
}
