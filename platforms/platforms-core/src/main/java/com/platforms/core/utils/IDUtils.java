/**
 * IDUtils.java
 * @create by fengmengyue
 * @since 2015年1月23日
 */
package com.platforms.core.utils;

import java.util.UUID;

/**  
 * <b>Description：</b> ID生成工具<br/>
 * <b>@author： </b>fengmengyue<br/>
 * <b>@date：</b>2015年1月23日 <br/>     
 */
public class IDUtils {
	
	/**
	 * 创建ID
	 */
	public static String createID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
