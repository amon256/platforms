/**
 * CountParameter.java.java
 * @author FengMy
 * @since 2014年10月10日
 */
package com.platforms.core.utils.mybatis.page;

/**  
 * 功能描述：用于查询总数的parameter类型
 * 
 * @author FengMy
 * @since 2014年10月10日
 */
public class CountParameter {
	private Object parameter;
	
	public CountParameter(Object parameter){
		this.parameter = parameter;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
}
