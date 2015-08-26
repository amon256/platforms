/**
 * ConsumeRecordServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winplan.entity.ConsumeRecord;
import com.winplan.entity.ReturnRecord;
import com.winplan.service.ConsumeRecordService;
import com.winplan.service.ReturnRecordService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Component
public class ConsumeRecordServiceImpl extends DataServiceImpl<ConsumeRecord> implements ConsumeRecordService {
	private static final Logger logger = LoggerFactory.getLogger(ConsumeRecordServiceImpl.class);
	@Autowired
	private ReturnRecordService returnRecordService;
	@Override
	public ConsumeRecord add(ConsumeRecord entity) {
		entity = super.add(entity);
		if(entity.getPeriodNumber() > 1 && entity.getAmount().compareTo(BigDecimal.ZERO) > 0){
			//期数大于0则需要返还
			logger.info("{}消费{}，按{}期返还",entity.getAccount(),entity.getPeriodNumber());
			List<ReturnRecord> returnRecords = getReturnRecords(entity);
			returnRecordService.add(returnRecords);
		}
		return entity;
	}
	
	private List<ReturnRecord> getReturnRecords(ConsumeRecord record){
		List<ReturnRecord> returnList = new LinkedList<ReturnRecord>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND, 0);
		
		BigDecimal amount = record.getAmount().divide(new BigDecimal(record.getPeriodNumber()),2,RoundingMode.DOWN);
		logger.debug("每期返还金额:{}",amount);
		for(int i = 1; i < record.getPeriodNumber(); i++){
			cal.add(Calendar.MONTH, 1);//下个月
			ReturnRecord rec = new ReturnRecord();
			rec.setPeriodIndex(i);
			rec.setComplete(false);
			rec.setConsumeRecord(record);
			rec.setReturnDate(cal.getTime());
			rec.setAmount(amount);
			returnList.add(rec);
		}
		amount = record.getAmount().subtract(amount.multiply(new BigDecimal(record.getPeriodNumber() - 1)));
		logger.debug("最后一期返还金额:{}",amount);
		ReturnRecord rec = new ReturnRecord();
		rec.setPeriodIndex(record.getPeriodNumber());
		rec.setComplete(false);
		rec.setConsumeRecord(record);
		rec.setReturnDate(cal.getTime());
		rec.setAmount(amount);
		returnList.add(rec);
		return returnList;
	}
}
