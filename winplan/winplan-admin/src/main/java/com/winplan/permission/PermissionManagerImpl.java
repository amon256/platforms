/**
 * PermissionManagerImpl.java.java
 * @author FengMy
 * @since 2015年8月19日
 */
package com.winplan.permission;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**  
 * 功能描述：权限分组管理
 * 
 * @author FengMy
 * @since 2015年8月19日
 */
public class PermissionManagerImpl implements PermissionManager {
	
	private Map<String,SystemPage> pageIdMap = new HashMap<String, SystemPage>();
	
	private Map<String,SystemPage> pageUrlMap = new HashMap<String, SystemPage>();
	
	private List<SystemMenu> menus = null;
	
	private Map<String,SystemMenu> menuMap = new HashMap<String, SystemMenu>();
	
	private SystemMenu defaultMenu;
	
	private SystemPage defaultPage;
	
	@SuppressWarnings("unchecked")
	public void setMenuFile(Resource[] resourceArray) throws Exception{
		if(resourceArray == null){
			return;
		}
		SAXBuilder builder = new SAXBuilder();
		List<SystemMenu> menuList = new LinkedList<SystemMenu>();
		for(Resource resourceFile : resourceArray){
			Document doc = builder.build(resourceFile.getInputStream());
			Element root = doc.getRootElement();
			List<Element> children = root.getChildren();
			for(Element ele : children){
				SystemMenu menu = null;
				if("group".equals(ele.getName())){
					menu = readGroup(ele,null);
				}else if("menu".equals(ele.getName())){
					menu = readMenu(ele,null);
				}
				if(menu != null){
					menuList.add(menu);
				}
			}
		}
		this.menus = menuList;
	}
	
	@SuppressWarnings("unchecked")
	private SystemMenu readGroup(Element element,SystemMenu parentMenu){
		SystemMenu group = new SystemMenu();
		List<Element> children = element.getChildren();
		group.setId(element.getAttributeValue("id"));
		group.setName(element.getAttributeValue("name"));
		for(Element ele : children){
			if("group".equals(ele.getName())){
				group.addSubMenu(readGroup(ele,group));
			}else if("menu".equals(ele.getName())){
				group.addSubMenu(readMenu(ele,group));
			}
		}
		group.setParentMenu(parentMenu);
		return group;
	}
	@SuppressWarnings("unchecked")
	private SystemMenu readMenu(Element element,SystemMenu parentMenu){
		SystemMenu menu = new SystemMenu();
		menu.setId(element.getAttributeValue("id"));
		menu.setName(element.getAttributeValue("name"));
		menu.setPage(element.getAttributeValue("page"));
		menu.setDefaultMenu(element.getAttributeValue("default"));
		List<Element> children = element.getChildren("page");
		for(Element ele : children){
			menu.addPage(readPage(ele,menu));
		}
		menuMap.put(menu.getId(), menu);
		menu.setParentMenu(parentMenu);
		if(!StringUtils.isEmpty(menu.getDefaultMenu())){
			defaultMenu = menu;
		}
		return menu;
	}
	
	private SystemPage readPage(Element element,SystemMenu menu){
		SystemPage page = new SystemPage();
		page.setId(element.getAttributeValue("id"));
		page.setName(element.getAttributeValue("name"));
		page.setDefaultPage(element.getAttributeValue("default"));
		page.setUrl(element.getTextTrim());
		page.setParentMenu(menu);
		pageIdMap.put(page.getId(), page);
		pageUrlMap.put(page.getUrl(), page);
		if(!StringUtils.isEmpty(page.getDefaultPage())){
			defaultPage = page;
		}
		return page;
	}
	
	@Override
	public boolean isPermissionControl(String path) {
		return pageUrlMap.containsKey(path);
	}


	@Override
	public boolean validatePermission(String path, Collection<String> roles) {
		if(roles != null && path != null && !"".equals(path.trim())){
			SystemMenu menu;
			SystemPage page = this.pageUrlMap.get(path);
			for(String role : roles){
				menu = menuMap.get(role);
				if(menu != null && menu.getPageMap() != null && menu.getPageMap().containsValue(page)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public SystemMenu getMenu(String id) {
		return menuMap.get(id);
	}

	public List<SystemMenu> getMenus() {
		return menus;
	}
	
	@Override
	public SystemMenu getDefaultMenu() {
		return defaultMenu;
	}
	
	@Override
	public SystemPage getDefaultPage() {
		return defaultPage;
	}
	
	public Map<String, SystemPage> getPageIdMap() {
		return pageIdMap;
	}
	
	public Map<String, SystemPage> getPageUrlMap() {
		return pageUrlMap;
	}
}
