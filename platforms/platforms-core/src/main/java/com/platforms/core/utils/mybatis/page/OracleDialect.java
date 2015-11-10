/**
 * OracleDialect.java.java
 * @author FengMy
 * @since 2014年10月9日
 */
package com.platforms.core.utils.mybatis.page;

import java.util.List;

import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;

/**  
 * 功能描述：Oracle分页方言
 * 
 * @author FengMy
 * @since 2014年10月9日
 */
public class OracleDialect extends AbstractDialect {

	@Override
	protected void limitNodes(List<SqlNode> sqlNodes, String startIndexName, String sizeName) {
		sqlNodes.add(0, new TextSqlNode("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( "));
		sqlNodes.add(new TextSqlNode(") ROW_ ) WHERE ROWNUM_ >= #{"+startIndexName+",jdbcType=INTEGER} AND ROWNUM_ <= #{"+startIndexName+",jdbcType=INTEGER} + #{"+sizeName+",jdbcType=INTEGER}"));
	}
}
