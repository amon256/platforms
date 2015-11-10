package com.platforms.core.utils.mybatis.page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

public class DynamicSqlSourceUtil {
	
	public static void addPaginationNode(DynamicSqlSource sqlSource,String startIndexName,String sizeName)throws Exception{
		Field rootSqlNode = DynamicSqlSource.class.getDeclaredField("rootSqlNode");
		rootSqlNode.setAccessible(true);
		SqlNode sqlNode = (SqlNode) rootSqlNode.get(sqlSource);
		if(MixedSqlNode.class.isAssignableFrom(sqlNode.getClass())){
			MixedSqlNode mixedSqlNode = (MixedSqlNode)sqlNode;
			rootSqlNode = MixedSqlNode.class.getDeclaredField("contents");
			rootSqlNode.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<SqlNode> sqlNodes = (List<SqlNode>) rootSqlNode.get(mixedSqlNode);
			sqlNodes.add(0, new TextSqlNode("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( "));
			sqlNodes.add(new TextSqlNode(") ROW_ ) WHERE ROWNUM_ >= #{"+startIndexName+",jdbcType=INTEGER} AND ROWNUM_ <= #{"+startIndexName+",jdbcType=INTEGER} + #{"+sizeName+",jdbcType=INTEGER}"));
		}
	}
	
	public static SqlSource copy(SqlSource sqlSource) throws Exception{
		SqlSource newSqlSource = null;
		if(DynamicSqlSource.class.isAssignableFrom(sqlSource.getClass())){
			newSqlSource = clone((DynamicSqlSource) sqlSource);
		}else{
			throw new RuntimeException("only for DynamicSqlSource,other is not support.");
		}
		return newSqlSource;
	}
	
	private static DynamicSqlSource clone(DynamicSqlSource dynamicSqlSource) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field configuration = DynamicSqlSource.class.getDeclaredField("configuration");
		configuration.setAccessible(true);
		Field rootSqlNode = DynamicSqlSource.class.getDeclaredField("rootSqlNode");
		rootSqlNode.setAccessible(true);
		SqlNode sqlNode = (SqlNode) rootSqlNode.get(dynamicSqlSource);
		if(MixedSqlNode.class.isAssignableFrom(sqlNode.getClass())){
			MixedSqlNode mixedSqlNode = (MixedSqlNode)sqlNode;
			rootSqlNode = MixedSqlNode.class.getDeclaredField("contents");
			rootSqlNode.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<SqlNode> sqlNodes = (List<SqlNode>) rootSqlNode.get(mixedSqlNode);
			sqlNodes = new ArrayList<SqlNode>(sqlNodes);
			sqlNode = new MixedSqlNode(sqlNodes);
		}
		DynamicSqlSource sqlSource = new DynamicSqlSource((Configuration)configuration.get(dynamicSqlSource), sqlNode);
		return sqlSource;
	}
}
