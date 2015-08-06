/**
 * 
 */
package com.winplan.tree;

/**  
 * <b>System：</b>NDMP<br/>
 * <b>Title：</b>ZTreeNode.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>fengmengyue<br/>
 * <b>@date：</b>2015年8月6日 上午10:04:06<br/>  
 * <b>@version：</b> 1.0.0.0<br/>
 * <b>Copyright (c) 2015 ASPire Tech.</b>   
 *   
 */
public class ZTreeNode {
	
	private String id;
	
	private String name;
	
	private boolean isParent = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

}
