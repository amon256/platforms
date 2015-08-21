/**
 * SystemPage.java.java
 * @author FengMy
 * @since 2015年8月19日
 */
package com.winplan.permission;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月19日
 */
public class SystemPage {
	
	private String id;
	
	private String name;
	
	private String url;
	
	private String defaultPage;
	
	private SystemMenu parentMenu;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public SystemMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SystemMenu parentMenu) {
		this.parentMenu = parentMenu;
	}
}
