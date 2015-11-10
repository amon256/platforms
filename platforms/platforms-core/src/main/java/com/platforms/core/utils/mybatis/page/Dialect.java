/**
 * Dialect.java.java
 * @author FengMy
 * @since 2014年10月9日
 */
package com.platforms.core.utils.mybatis.page;

import org.apache.ibatis.mapping.MappedStatement;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2014年10月9日
 */
public interface Dialect {
	/**
	 * 是否支持分页
	 * @return
	 */
	boolean isSupportLimit();
	
	/**
	 * 设置分页开始下标参数名
	 * @param startParameterName
	 */
	void setStartParameterName(String startParameterName);
	/**
	 * 设置分页大小参数名
	 * @param sizeParameterName
	 */
	void setSizeParameterName(String sizeParameterName);
	
	/**
	 * 创建分页查询映射语句
	 * @param selectMappedStatement
	 * @return
	 * @throws Exception 
	 */
	MappedStatement createLimitMappedStatement(MappedStatement selectMappedStatement) throws Exception;
	
	/**
	 * 创建统计数据查询映射语句
	 * @param selectMappedStatement
	 * @return
	 */
	MappedStatement createCountMappedStatement(MappedStatement selectMappedStatement) throws Exception;
	
	/**
	 * 处理分页参数
	 * @param parameter
	 */
	Object limitParameter(Object parameter, int start, int size);
	
	/**
	 * 处理查询count参数
	 * @param parameter
	 * @return
	 */
	Object countParameter(Object parameter);
}
