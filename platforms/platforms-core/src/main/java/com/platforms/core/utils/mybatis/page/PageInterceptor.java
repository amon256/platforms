/**
 * PageInterceptor.java.java
 * @author FengMy
 * @since 2014年10月9日
 */
package com.platforms.core.utils.mybatis.page;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**  
 * 功能描述：基于数据库分页
 * 
 * @author FengMy
 * @since 2014年10月9日
 */
@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {
	
	private static int MAPPED_STATEMENT_INDEX = 0;//MappedStatement在参数数组中的下标位置
	
	private static int PARAMETER_INDEX = 1;//查询参数在参数数组的下标位置
	
	private static int ROWBOUNDS_INDEX = 2;//RowBounds在参数数组的下标位置
	
	private Dialect dialect;//分页方言 
	
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] queryArgs = invocation.getArgs();
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		if (dialect.isSupportLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
			MappedStatement newMs = dialect.createLimitMappedStatement(ms);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			queryArgs[PARAMETER_INDEX] = dialect.limitParameter(parameter, offset, limit);
		} else if(parameter instanceof CountParameter) {
			//获取总数
			MappedStatement newMs = dialect.createCountMappedStatement(ms);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			queryArgs[PARAMETER_INDEX] = dialect.countParameter(((CountParameter)parameter).getParameter());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String dialectClass = properties.getProperty("dialectClass");
		try {
			dialect = (Dialect) Class.forName(dialectClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
		}
		if(dialect != null){
			String startParameterName = properties.getProperty("startParameterName");
			String sizeParameterName = properties.getProperty("sizeParameterName");
			if(startParameterName != null && !"".equals(startParameterName.trim())){
				dialect.setStartParameterName(startParameterName);
			}
			if(sizeParameterName != null && !"".equals(sizeParameterName.trim())){
				dialect.setSizeParameterName(sizeParameterName);
			}
		}
	}

}
