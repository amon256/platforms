/**
 * MySQLDialect.java
 * @author FengMy
 * @since 2014年10月10日
 */
package com.platforms.core.utils.mybatis.page;

import java.util.List;

import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;

/**  
 * 功能描述：MySQL方言分页处理
 * 
 * @author FengMy
 * @since 2014年10月10日
 */
public class MySQLDialect extends AbstractDialect {

	@Override
	protected void limitNodes(List<SqlNode> sqlNodes, String startIndexName, String sizeName) {
		sqlNodes.add(new TextSqlNode(" LIMIT #{" + startIndexName + ",jdbcType=INTEGER} ，#{" + sizeName+",jdbcType=INTEGER}"));
	}

}
