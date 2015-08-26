/**
 * ReturnRecordService.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.service;

import java.util.Date;
import java.util.List;

import com.winplan.entity.ReturnRecord;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
public interface ReturnRecordService extends CoreService<ReturnRecord> {
	
	/**
	 * 根据消费记录查找返还记录
	 * @param id
	 * @return
	 */
	public List<ReturnRecord> findByConsumeRecordId(String id);

	/**
	 * 将在指定日期之前的数据进行处理
	 * @param beforeDate
	 */
	public void returnRecordExecute(Date beforeDate);
}
