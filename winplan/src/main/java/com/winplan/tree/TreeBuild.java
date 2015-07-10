/**
 * TreeBuild.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.tree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.winplan.entity.User;


/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
public class TreeBuild {

	public static UserNode buildUserTree(Collection<User> values,User rootUser){
		if(values == null || values.isEmpty()){
			return null;
		}
		Map<String,User> userMap = new HashMap<String, User>();
		try{
			for(User value : values){
				String key = value.getAccount();
				userMap.put(key, value);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return buildUserNode(userMap, rootUser);
	}
	
	private static UserNode buildUserNode(Map<String,User> userMap,User rootUser){
		if(rootUser == null){
			return null;
		}
		UserNode root = new UserNode(rootUser);
		if(rootUser.getLeft() != null){
			root.setLeft(buildUserNode(userMap, userMap.get(rootUser.getLeft())));
		}
		if(rootUser.getRight() != null){
			root.setRight(buildUserNode(userMap, userMap.get(rootUser.getRight())));
		}
		return root;
	}
	
}
