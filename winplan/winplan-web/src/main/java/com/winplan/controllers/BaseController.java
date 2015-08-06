/**
 * BaseController.java.java
 * @author FengMy
 * @since 2015年6月30日
 */
package com.winplan.controllers;

import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年6月30日
 */
public abstract class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected static String STATUS = "status";
	protected static String MSG = "msg";
	
	/**
	 * 异常处理
	 */
	@ExceptionHandler
	@ResponseBody
	public ModelAndView exceptionHandler(HttpServletRequest request,Exception ex){
		String errorCode = createErrorCode();
		String path = request.getRequestURI();
		logger.error("处理请求[{}]时发生异常,异常码:{}", path,errorCode);
		logger.error("请求异常信息:", ex);
		ModelAndView view = new ModelAndView("common/error");
		view.getModelMap().put(STATUS, false);
		view.getModelMap().put(MSG,"服务器错误,错误码:" + errorCode);
		view.getModelMap().put("data", ex.getMessage());
		return view;
	}
	
	/**
	 *创建六位的错误码
	 */
	private String createErrorCode(){
		Random random = new Random();
		DecimalFormat df = new DecimalFormat("000000");
		return df.format(random.nextInt(999999));
	}
}
