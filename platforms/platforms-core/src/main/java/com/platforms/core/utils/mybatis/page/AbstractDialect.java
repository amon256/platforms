/**
 * AbstractDialect.java.java
 * @author FengMy
 * @since 2014年10月9日
 */
package com.platforms.core.utils.mybatis.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.session.Configuration;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2014年10月9日
 */
public abstract class AbstractDialect implements Dialect {
	private static final Log log = LogFactory.getLog(AbstractDialect.class);
	protected String startParameterName = "_start_";
	protected String sizeParameterName = "_size_";

	@Override
	public boolean isSupportLimit() {
		return true;
	}
	
	@Override
	public MappedStatement createLimitMappedStatement(MappedStatement selectMappedStatement) throws Exception {
		String limitId = createLimitId(selectMappedStatement.getId());
		MappedStatement newMs = getExistsMappedStatement(selectMappedStatement,limitId);
		if(newMs == null){
			log.debug("创建映射语句:" + limitId);
			SqlSource sqlSource = DynamicSqlSourceUtil.copy(selectMappedStatement.getSqlSource());
			addPageNode((DynamicSqlSource) sqlSource, getStartParameterName(), getSizeParameterName());
			Builder builder = new MappedStatement.Builder(selectMappedStatement.getConfiguration(), limitId, sqlSource, selectMappedStatement.getSqlCommandType());
			builder.resource(selectMappedStatement.getResource());
			builder.fetchSize(selectMappedStatement.getFetchSize());
			builder.statementType(selectMappedStatement.getStatementType());
			builder.keyGenerator(selectMappedStatement.getKeyGenerator());
			builder.keyProperty(delimitedArraytoString(selectMappedStatement.getKeyProperties()));
			builder.timeout(selectMappedStatement.getTimeout());
			builder.parameterMap(selectMappedStatement.getParameterMap());
			builder.resultMaps(selectMappedStatement.getResultMaps());
			builder.cache(selectMappedStatement.getCache());
			newMs = builder.build();
			selectMappedStatement.getConfiguration().addMappedStatement(newMs);
		}
		return newMs;
	}
	
	private MappedStatement getExistsMappedStatement(MappedStatement selectMappedStatement, String limitId) {
		try{
			return selectMappedStatement.getConfiguration().getMappedStatement(limitId);
		}catch(Exception e){
			log.debug("映射语句:" + limitId + "不存在");
			return null;
		}
	}

	/**
	 * 根据原查询映射语句id生成分页映射语句id
	 * @param id
	 * @return
	 */
	protected String createLimitId(String id) {
		return id + "!!page";
	}
	
	/**
	 * 创建count映射语句id
	 * @param id
	 * @return
	 */
	protected String createCountId(String id) {
		return id+"!!count";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object limitParameter(Object parameter,int start,int size) {
		parameter = exchangeListAndArray(parameter);
		if(Map.class.isAssignableFrom(parameter.getClass())){
			Map<String,Object> map = (Map<String,Object>)parameter;
			map.put(getStartParameterName(), start);
			map.put(getSizeParameterName(), size);
		}
		return parameter;
	}
	
	@Override
	public Object countParameter(Object parameter) {
		return exchangeListAndArray(parameter);
	}
	
	@SuppressWarnings("unchecked")
	private Object exchangeListAndArray(Object parameter){
		Object tmp = parameter;
		if(List.class.isAssignableFrom(parameter.getClass())){
			parameter = new HashMap<String,Object>();
			((Map<String,Object>)parameter).put("list", tmp);
		}
		if(parameter.getClass().isArray()){
			parameter = new HashMap<String,Object>();
			((Map<String,Object>)parameter).put("array", tmp);
		}
		return parameter;
	}

	@Override
	public MappedStatement createCountMappedStatement(MappedStatement selectMappedStatement) throws Exception {
		String countId = createCountId(selectMappedStatement.getId());
		MappedStatement newMs = getExistsMappedStatement(selectMappedStatement, countId);
		if(newMs == null){
			log.debug("创建映射语句:" + countId);
			SqlSource sqlSource = DynamicSqlSourceUtil.copy(selectMappedStatement.getSqlSource());
			addCountPageNode((DynamicSqlSource) sqlSource);
			Builder builder = new MappedStatement.Builder(selectMappedStatement.getConfiguration(),countId , sqlSource, selectMappedStatement.getSqlCommandType());
			builder.resource(selectMappedStatement.getResource());
			builder.fetchSize(selectMappedStatement.getFetchSize());
			builder.statementType(selectMappedStatement.getStatementType());
			builder.keyGenerator(selectMappedStatement.getKeyGenerator());
			builder.keyProperty(delimitedArraytoString(selectMappedStatement.getKeyProperties()));
			builder.timeout(selectMappedStatement.getTimeout());
			builder.parameterMap(selectMappedStatement.getParameterMap());
			builder.resultMaps(selectMappedStatement.getResultMaps());
			builder.cache(selectMappedStatement.getCache());
//			List<ResultMap> resultMaps = new ArrayList<ResultMap>();
//			resultMaps.add(new ResultMap.Builder(selectMappedStatement.getConfiguration(), countId, Integer.class, new ArrayList<ResultMapping>()).build());
//			builder.resultMaps(resultMaps);
			newMs = builder.build();
			selectMappedStatement.getConfiguration().addMappedStatement(newMs);
		}
		return newMs;
	}
	@Override
	public void setStartParameterName(String startParameterName) {
		this.startParameterName = startParameterName;
	}
	protected String getStartParameterName(){
		return startParameterName;
	}
	@Override
	public void setSizeParameterName(String sizeParameterName) {
		this.sizeParameterName = sizeParameterName;
	}
	protected String getSizeParameterName(){
		return sizeParameterName;
	}
	
	/**
	 * 字符数组组装成字符串,逗号分隔
	 * @param array
	 * @return
	 */
	private String delimitedArraytoString(String[] array){
		if(array == null || array.length == 0){
			return null;
		}
		StringBuilder sbd = new StringBuilder();
		for(String s : array){
			sbd.append(s).append(",");
		}
		return sbd.substring(0, sbd.length() - 1).toString();
	}

	/**
	 * 增加分页查询sql节点
	 * @param sqlSource
	 * @param startIndexName
	 * @param sizeName
	 * @throws Exception
	 */
	protected void addPageNode(DynamicSqlSource sqlSource,String startIndexName,String sizeName)throws Exception{
		MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
		SqlNode sqlNode = (SqlNode)metaObject.getValue("rootSqlNode");
		if(MixedSqlNode.class.isAssignableFrom(sqlNode.getClass())){
			metaObject = SystemMetaObject.forObject(sqlNode);
			@SuppressWarnings("unchecked")
			List<SqlNode> sqlNodes = (List<SqlNode>) metaObject.getValue("contents");
			limitNodes(sqlNodes, startIndexName, sizeName);
		}
	}
	
	protected void addCountPageNode(DynamicSqlSource sqlSource){
		MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
		SqlNode sqlNode = (SqlNode)metaObject.getValue("rootSqlNode");
		if(MixedSqlNode.class.isAssignableFrom(sqlNode.getClass())){
			metaObject = SystemMetaObject.forObject(sqlNode);
			@SuppressWarnings("unchecked")
			List<SqlNode> sqlNodes = (List<SqlNode>) metaObject.getValue("contents");
			countNodes(sqlNodes);
		}
	}
	
	/**
	 * 原生sql处理count节点
	 * @param sqlNodes
	 */
	protected void countNodes(List<SqlNode> sqlNodes){
		StaticTextSqlNode textSqlNode = new StaticTextSqlNode("SELECT COUNT(1) FROM ( \t\n");
		sqlNodes.add(0, textSqlNode);
		textSqlNode = new StaticTextSqlNode("\t\n ) COUNT_TMP");
		sqlNodes.add(textSqlNode);
	}
	
	/**
	 * 处理分页节点
	 * @param sqlNodes
	 */
	protected abstract void limitNodes(List<SqlNode> sqlNodes,String startIndexName,String sizeName);
	
	/**
	 * 复制sqlsource
	 * @param sqlSource
	 * @return
	 * @throws Exception
	 */
	protected SqlSource copy(SqlSource sqlSource) throws Exception{
		SqlSource newSqlSource = null;
		if(DynamicSqlSource.class.isAssignableFrom(sqlSource.getClass())){
			newSqlSource = clone((DynamicSqlSource) sqlSource);
		}else{
			throw new RuntimeException("only for DynamicSqlSource,other is not support.");
		}
		return newSqlSource;
	}
	
	/**
	 * 克隆sqlsource
	 * @param dynamicSqlSource
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected DynamicSqlSource clone(DynamicSqlSource dynamicSqlSource) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		MetaObject sqlSourceMeta = SystemMetaObject.forObject(dynamicSqlSource);
		SqlNode sqlNode = (SqlNode) sqlSourceMeta.getValue("rootSqlNode");
		if(MixedSqlNode.class.isAssignableFrom(sqlNode.getClass())){
			MetaObject nodeMeta = SystemMetaObject.forObject(sqlNode);
			@SuppressWarnings("unchecked")
			List<SqlNode> sqlNodes = (List<SqlNode>) nodeMeta.getValue("contents");
			sqlNodes = new ArrayList<SqlNode>(sqlNodes);
			sqlNode = new MixedSqlNode(sqlNodes);
		}
		DynamicSqlSource sqlSource = new DynamicSqlSource((Configuration)sqlSourceMeta.getValue("configuration"), sqlNode);
		return sqlSource;
	}
	
}
