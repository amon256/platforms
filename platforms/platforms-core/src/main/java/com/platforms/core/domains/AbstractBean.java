/**
 * AbstractBean.java
 * @create by fengmengyue
 * @since 2015年1月22日
 */
package com.platforms.core.domains;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.platforms.core.utils.ClassUtils;

/**  
 * <b>Description：</b> 所有实体的基类<br/>
 * <b>@author： </b>fengmengyue<br/>
 * <b>@date：</b>2015年1月22日 <br/>     
 */
public abstract class AbstractBean implements Serializable {
	private static final long serialVersionUID = -3029659610866058094L;

	/**
	 * ID
	 */
	@Id
	@Column(name="f_id",length=40)
	private String id;
	
	/**
	 * 排序字段
	 */
	@Transient
	private String orderBy;
	
	/**
	 * 数据库排序字段
	 */
	@Transient
	private String orderByColumns;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		orderByColumns = "";
		if(StringUtils.isNotEmpty(orderBy)){
			List<Field> fields = ClassUtils.getFields(this.getClass());
			String[] orderByFields = orderBy.split(",");
			for(String orderByField : orderByFields){
				if(orderByField != null && !"".equals(orderByField.trim())){
					orderByFields = orderByField.split("\\s+");
					String fieldName = orderByFields[0].trim();
					boolean exists = false;
					for(Field f : fields){
						if(f.getName().equals(fieldName)){
							if(f.getAnnotation(Column.class) == null){
								throw new RuntimeException("字段:" + fieldName + "没有Column注解");
							}
							fieldName = this.getClass().getSimpleName().toLowerCase() + "." + f.getAnnotation(Column.class).name();
							String orderKey = "ASC";
							if(orderByFields.length > 1){
								if("asc".equalsIgnoreCase(orderByFields[1]) || "desc".equalsIgnoreCase(orderByFields[1])){
									orderKey = orderByFields[1].trim().toUpperCase();
								}else{
									throw new RuntimeException(orderByFields[1] + "标识符非法");
								}
							}
							orderByColumns += fieldName + " " + orderKey + ",";
							exists = true;
						}
					}
					if(!exists){
						throw new RuntimeException("字段" + fieldName + "不存在");
					}
				}
			}
		}
		if(orderByColumns != null && orderByColumns.length() > 0){
			orderByColumns = orderByColumns.substring(0, orderByColumns.length() - 1);
		}
		this.orderBy = orderBy;
	}
	
	public String getOrderByColumns() {
		return orderByColumns;
	}
}
