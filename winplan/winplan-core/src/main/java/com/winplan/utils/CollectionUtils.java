/**
 * CollectionUtils.java
 * 2015年5月27日
 */
package com.winplan.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**  
 * <b>功能：</b>CollectionUtils.java<br/>
 * <b>描述：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class CollectionUtils {

	@SuppressWarnings("unchecked")
	public static <T> Set<T> createSet(Class<T> argType,T... values){
		Set<T> set = new HashSet<T>();
		if(values != null){
			for(T v : values){
				set.add(v);
			}
		}
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> createList(Class<T> argType,T... values){
		List<T> list = new LinkedList<T>();
		if(values != null){
			for(T v : values){
				list.add(v);
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> createMap(Class<K> keyType,Class<V> valueType,Object... keyValuePair){
		Map<K,V> map = new HashMap<K, V>();
		if(keyValuePair != null){
			if(keyValuePair.length % 2 != 0){
				throw new IllegalArgumentException("argument:keyValuePair is not for pair.");
			}
			boolean isPair = true;
			K key = null;
			for(Object obj : keyValuePair){
				if(isPair){
					key = (K) obj;
				}else{
					map.put(key, (V) obj);
				}
				isPair = !isPair;
			}
		}
		return map;
	}
}
