/**
 * SystemMenu.java.java
 * @author FengMy
 * @since 2015年8月19日
 */
package com.winplan.permission;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月19日
 */
public class SystemMenu {
	
	private String id;
	
	private String name;
	
	private String page;
	
	private String defaultMenu;
	
	private SystemMenu parentMenu;
	
	private List<SystemMenu> subMenus;
	
	private List<SystemPage> pages;
	
	private Map<String,SystemPage> pageMap;
	
	public void addSubMenu(SystemMenu menu){
		if(subMenus == null){
			subMenus = new LinkedList<SystemMenu>();
		}
		subMenus.add(menu);
	}
	
	public void addPage(SystemPage page){
		if(pages == null){
			pages = new LinkedList<SystemPage>();
		}
		if(pageMap == null){
			pageMap = new HashMap<String, SystemPage>();
		}
		pages.add(page);
		pageMap.put(page.getId(), page);
	}

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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<SystemMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SystemMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public List<SystemPage> getPages() {
		return pages;
	}

	public void setPages(List<SystemPage> pages) {
		this.pages = pages;
	}

	public Map<String, SystemPage> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, SystemPage> pageMap) {
		this.pageMap = pageMap;
	}

	public SystemMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SystemMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getDefaultMenu() {
		return defaultMenu;
	}

	public void setDefaultMenu(String defaultMenu) {
		this.defaultMenu = defaultMenu;
	}
	
	

}
