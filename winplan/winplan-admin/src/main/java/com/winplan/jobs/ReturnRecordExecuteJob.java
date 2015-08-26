/**
 * ReturnRecordExecuteJob.java.java
 * @author FengMy
 * @since 2015年8月26日
 */
package com.winplan.jobs;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.winplan.service.ReturnRecordService;
import com.winplan.spring.ApplicationContextAware;

/**  
 * 功能描述：返现定时任务
 * 
 * @author FengMy
 * @since 2015年8月26日
 */
public class ReturnRecordExecuteJob extends QuartzJobBean{
	
	private static final Logger logger = LoggerFactory.getLogger(ReturnRecordExecuteJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("定期返现记录job触发");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
//		try {
//			date = sdf.parse("20151128");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//new Date();
		ApplicationContextAware.getApplicationContext().getBean(ReturnRecordService.class).returnRecordExecute(date);
		logger.info("定期返现记录job运行结束");
	}
}
