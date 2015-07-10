/**
 * Node.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.tree;

import com.winplan.entity.User;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
public class UserNode {
	
	private User value;

	private UserNode left;
	
	private UserNode right;
	
	public UserNode(User value){
		this.value = value;
	}

	public User getValue() {
		return value;
	}

	public void setValue(User value) {
		this.value = value;
	}

	public UserNode getLeft() {
		return left;
	}

	public void setLeft(UserNode left) {
		this.left = left;
	}

	public UserNode getRight() {
		return right;
	}

	public void setRight(UserNode right) {
		this.right = right;
	}
}
 