/**
 * DividendStrategyExecuteJob.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.winplan.service.DividendStrategyService;
import com.winplan.spring.ApplicationContextAware;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
public class DividendStrategyExecuteJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(DividendStrategyExecuteJob.class);
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("分红策略执行定时任务启动");
		ApplicationContextAware.getApplicationContext().getBean(DividendStrategyService.class).startDividend();
		logger.info("分红策略执行定时任务结束");
	}

}
