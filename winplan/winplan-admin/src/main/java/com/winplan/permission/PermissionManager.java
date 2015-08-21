package com.winplan.permission;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**  
 * 
 * 功能描述：权限管理
 * 
 * @author FengMy
 * @since 2015年8月19日
 */
public interface PermissionManager {

	/**
	 * 是否受权限控制
	 * @param path
	 * @return
	 */
	public boolean isPermissionControl(String path);
	
	/**
	 * 校验权限
	 * @param path
	 * @param roles
	 * @return
	 */
	public boolean validatePermission(String path,Collection<String> roles);
	
	/**
	 * 获取菜单
	 * @return
	 */
	public List<SystemMenu> getMenus();
	
	/**
	 * 根据MenuId获取Menu
	 * @param id
	 * @return
	 */
	public SystemMenu getMenu(String id);
	
	/**
	 * 默认打开菜单
	 * @return
	 */
	public SystemMenu getDefaultMenu();
	
	/**
	 * 默认打开页面
	 * @return
	 */
	public SystemPage getDefaultPage();
	
	public Map<String, SystemPage> getPageIdMap();
	
	public Map<String, SystemPage> getPageUrlMap();
	
}