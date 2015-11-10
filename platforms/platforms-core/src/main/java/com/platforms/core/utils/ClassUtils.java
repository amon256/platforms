/**
 * ClassUtils.java
 * create by FengMy at 2014年7月28日
 */
package com.platforms.core.utils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 描述：class工具类
 * @author FengMy
 * @since 2014年7月28日
 */
public class ClassUtils {

	/**
	 * 根据属性名查找属性
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	public static Field getField(Object bean,String fieldName){
		Field field = null;
		List<Field> fields = null;
		Class<?> clazz = bean.getClass();
		if(!StringUtils.isEmpty(fieldName)){
			String[] fieldAttr = fieldName.split("\\.");
			for(String fname : fieldAttr){
				field = null;
				if(fname != null && !"".equals(fname)){
					fields = getFields(clazz);
					for(Field f : fields){
						if(f.getName().equals(fname)){
							field = f;
							clazz = f.getType();
						}
					}
				}
			}
		}
		return field;
	}
	
	/**
	 * 获取类的所有属性，包含父类属性
	 * @param clazz
	 * @return
	 */
	public static List<Field> getFields(Class<?> clazz){
		List<Field> fields = new LinkedList<Field>();
		if(clazz != Object.class){
			fields.addAll(getFields(clazz.getSuperclass()));
		}
		Field[] jfields = clazz.getDeclaredFields();
		for(Field f : jfields){
			fields.add(f);
		}
		return fields;
	}
}
