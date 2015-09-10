/**
 * ThreadUtils.java.java
 * @author FengMy
 * @since 2015年9月10日
 */
package com.winplan.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 * 功能描述：线程工具类
 * 
 * @author FengMy
 * @since 2015年9月10日
 */
public class ThreadUtils {
	
	private static final int THREAD_POOL_SIZE = 100;

	private static ExecutorService executorService = null;
	
	/**
	 * 获取公共线程池
	 * @return
	 */
	public synchronized static ExecutorService getPublicExecutorService(){
		if(executorService == null || executorService.isShutdown()){
			executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		}
		return executorService;
	}
	
	/**
	 * 获取专用线程池
	 * @param size
	 * @return
	 */
	public static ExecutorService getPrivateExecutorService(int size){
		return Executors.newFixedThreadPool(size);
	}
}
