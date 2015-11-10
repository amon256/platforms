/**
 *  CreateFileFromBean.java
 */
package platforms.codegenerate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.platforms.core.domains.AbstractBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 根据Bean创建java dao service文件和mybatis sql文件
 * 依赖jpa注解
 * @author fengmengyue
 * @since 2014年6月28日
 */
public class CodeGenerator {
	private static final String PRJ_ROOT = System.getProperty("user.dir");
	private static final String JAVA_DAO_ROOT = PRJ_ROOT + "/src/main/java/com/platforms/core/dao";
	private static final String JAVA_SERVICE_ROOT = PRJ_ROOT + "/src/main/java/com/platforms/core/service";
	private static final String MYBATIS_ROOT = PRJ_ROOT + "/src/main/resources/mapper";
	private static final String DDL_ROOT = PRJ_ROOT + "/src/main/resources/ddl";
	private static final String TEMPLATE_ROOT = PRJ_ROOT + "/src/main/java/platforms/codegenerate/template/";
	
	public static void main(String[] args) throws IOException, TemplateException {
		Class<?> clazz = null;//这里填写要生成的实体类名
		generate(clazz);
	}
	
	public static void generate(Class<?> clazz) throws IOException, TemplateException {
		Configuration config = new Configuration();
		config.setDefaultEncoding("utf-8");
		config.setDirectoryForTemplateLoading(new File(TEMPLATE_ROOT));
		createDao(clazz, config);
		createDaoImpl(clazz, config);
		createService(clazz, config);
		createServiceImpl(clazz, config);
		createMybatis(clazz, config);
		createDDL(clazz,config);
	}
	
	private static void createDDL(Class<?> clazz, Configuration config) throws TemplateException, IOException {
		Table table = clazz.getAnnotation(Table.class);
		if(table == null){
			System.out.println("实体未定义表名");
			return;
		}
		Template template = config.getTemplate("ddl.temp");
		File file = new File(DDL_ROOT + File.separator + clazz.getSimpleName()+".sql");
//		if(!file.exists()){
			Map<String,Object> rootMap = new HashMap<String, Object>();
			rootMap.put("tableName", table.name());
			List<DbField> fields = getFieldsOfBean(clazz);
			rootMap.put("beanName", clazz.getSimpleName());
			rootMap.put("fullBeanName", clazz.getName());
			rootMap.put("fields", fields);
			rootMap.put("tableAlias", clazz.getSimpleName().toLowerCase());
			rootMap.put("joinFields", getJoinFields(clazz));
			Writer writer = new FileWriter(file);
			template.process(rootMap, writer);
			writer.close();
//		}else{
//			System.out.println(file.getAbsolutePath() + "己存在，不能生成");
//		}
			System.out.println("生成DDL脚本:" + file.getName());
	}

	private static void createMybatis(Class<?> clazz,Configuration config) throws TemplateException, IOException{
		Table table = clazz.getAnnotation(Table.class);
		if(table == null){
			System.out.println("实体未定义表名");
			return;
		}
		Template template = config.getTemplate("mybatis.temp");
		File file = new File(MYBATIS_ROOT + File.separator + clazz.getSimpleName()+"Mapper.xml");
//		if(!file.exists()){
			Map<String,Object> rootMap = new HashMap<String, Object>();
			rootMap.put("tableName", table.name());
			List<DbField> fields = getFieldsOfBean(clazz);
			rootMap.put("beanName", clazz.getSimpleName());
			rootMap.put("fullBeanName", clazz.getName());
			rootMap.put("fields", fields);
			rootMap.put("tableAlias", clazz.getSimpleName().toLowerCase());
			rootMap.put("joinFields", getJoinFields(clazz));
			Writer writer = new FileWriter(file);
			template.process(rootMap, writer);
			writer.close();
//		}else{
//			System.out.println(file.getAbsolutePath() + "己存在，不能生成");
//		}
		System.out.println("生成mybatis文件:" + file.getName());
	}
	
	private static void createDao(Class<?> clazz,Configuration config) throws IOException, TemplateException{
		File file = new File(JAVA_DAO_ROOT + File.separator + clazz.getSimpleName() + "Dao.java");
		createJavaCode(file, "dao.temp", clazz, config);
	}
	
	private static void createDaoImpl(Class<?> clazz,Configuration config) throws IOException, TemplateException{
		File file = new File(JAVA_DAO_ROOT + File.separator + "impl" + File.separator + clazz.getSimpleName() + "DaoImpl.java");
		createJavaCode(file, "daoImpl.temp", clazz, config);
	}
	
	private static void createService(Class<?> clazz,Configuration config) throws IOException, TemplateException{
		File file = new File(JAVA_SERVICE_ROOT + File.separator + clazz.getSimpleName() + "Service.java");
		createJavaCode(file, "service.temp", clazz, config);
	}
	
	private static void createServiceImpl(Class<?> clazz,Configuration config) throws IOException, TemplateException{
		File file = new File(JAVA_SERVICE_ROOT + File.separator + "impl" + File.separator + clazz.getSimpleName() + "ServiceImpl.java");
		createJavaCode(file, "serviceImpl.temp", clazz, config);
	}
	
	private static void createJavaCode(File file,String templateName,Class<?> clazz,Configuration config) throws IOException, TemplateException{
		Template template = config.getTemplate(templateName);
		if(!file.exists()){
			Map<String,Object> rootMap = new HashMap<String, Object>();
			rootMap.put("beanName", clazz.getSimpleName());
			rootMap.put("fullBeanName", clazz.getName());
			Writer writer = new FileWriter(file);
			template.process(rootMap, writer);
			writer.close();
			System.out.println("生成java文件:" + file.getName());
		}else{
			System.out.println(file.getAbsolutePath() + "己存在，不能生成");
		}
	}
	
	private static List<DbField> getFieldsOfBean(Class<?> clazz){
		List<DbField> fields = new LinkedList<DbField>();
		Field[] jfields = clazz.getDeclaredFields();
		if(clazz != AbstractBean.class){
			fields.addAll(getFieldsOfBean(clazz.getSuperclass()));
		}
		for(Field field : jfields){
			Transient trans = field.getAnnotation(Transient.class);
			if(trans != null){
				continue;
			}
			if(field.getAnnotation(Column.class) != null || field.getAnnotation(JoinColumn.class) != null){
				DbField dbField = new DbField();
				dbField.setFieldName(field.getName());
				if(field.getAnnotation(Column.class) != null){
					Column column = field.getAnnotation(Column.class);
					if(column.name() != null){
						dbField.setColumnName(column.name());
					}else{
						dbField.setColumnName(getDbName("f_",field.getName()));
					}
					if(StringUtils.isNotEmpty(column.columnDefinition())){
						dbField.setDbType(column.columnDefinition());
					}else{
						dbField.setDbType(getDbTypeByClass(field.getType()));
					}
					dbField.setLength(column.length());
				}else{
					JoinColumn column = field.getAnnotation(JoinColumn.class);
					if(column.name() != null){
						dbField.setColumnName(column.name());
					}else{
						dbField.setColumnName(getDbName("fk_",field.getName()) + "_id");
					}
					dbField.setJoinField(true);
					dbField.setDbType("int");
				}
				dbField.setJdbcType(getJdbcTypeByClass(field.getType()));
				fields.add(dbField);
			}
		}
		return fields;
	}
	
	private static List<JoinField> getJoinFields(Class<?> clazz){
		List<JoinField> joinFields = new ArrayList<JoinField>();
		Field[] jfields = clazz.getDeclaredFields();
		for(Field field : jfields){
			if(AbstractBean.class.isAssignableFrom(field.getType())){
				Table table = field.getType().getAnnotation(Table.class);
				if(table == null || StringUtils.isEmpty(table.name())){
					continue;
				}
				//连接类型
				JoinField jf = new JoinField();
				String alias = field.getName();
				jf.setTableAlias(alias);
				jf.setTableName(table.name());
				JoinColumn column = field.getAnnotation(JoinColumn.class);
				String columnName = null;
				if(column.name() != null){
					columnName = column.name();
				}else{
					columnName = getDbName("fk_",field.getName()) + "_id";
				}
				jf.setJoinColumn(columnName);
				List<DbField> subFields = getFieldsOfBean(field.getType());
				Iterator<DbField> iterator = subFields.iterator();
				while(iterator.hasNext()){
					if("id".equals(iterator.next().getFieldName())){
						iterator.remove();
					}
				}
				for(DbField f : subFields){
						f.setFieldName(alias + "." + f.getFieldName());
						f.setColumnName(alias + "." + f.getColumnName());
				}
				jf.setTableFields(subFields);
				joinFields.add(jf);
			}
		}
		return joinFields;
	}
	
	private static String getDbTypeByClass(Class<?> c){
		if(Integer.class.isAssignableFrom(c)){
			return "int";
		}else if(Number.class.isAssignableFrom(c)){
			return "number";
		}else if(Boolean.class.isAssignableFrom(c)){
			return "int";
		}else if(Date.class.isAssignableFrom(c)){
			return "date";
		}else if(AbstractBean.class.isAssignableFrom(c)){
			return "int";
		}else{
			return "varchar";
		}
	}
	
	private static String getJdbcTypeByClass(Class<?> c){
		if(Integer.class.isAssignableFrom(c)){
			return "INTEGER";
		}else if(Number.class.isAssignableFrom(c)){
			return "NUMERIC";
		}else if(Boolean.class.isAssignableFrom(c)){
			return "BOOLEAN";
		}else if(Date.class.isAssignableFrom(c)){
			return "TIMESTAMP";
		}else if(AbstractBean.class.isAssignableFrom(c)){
			return "INTEGER";
		}else{
			return "VARCHAR";
		}
	}
	
	private static String getDbName(String prefix,String fieldName){
		StringBuilder sb = new StringBuilder(prefix);
		for(int i = 0; i < fieldName.length(); i++){
			char c = fieldName.charAt(i);
			if(Character.isUpperCase(c) && i != 0){
				sb.append("_").append(Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static void createMainDdl(){
		File root = new File(DDL_ROOT);
		if(root.exists()){
			try{
				File main = new File(DDL_ROOT + "/main.sql");
				BufferedWriter bw = new BufferedWriter(new FileWriter(main));
				File[] sqlFiles = root.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						return file.isFile() && file.exists() && file.getName().endsWith(".sql");
					}
				});
				BufferedReader br = null;
				String line;
				for(File sqlFile : sqlFiles){
					br = new BufferedReader(new FileReader(sqlFile));
					while((line = br.readLine()) != null){
						bw.write(line);
						bw.write("\n");
					}
					bw.write("\n");
				}
				bw.close();
				br.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
