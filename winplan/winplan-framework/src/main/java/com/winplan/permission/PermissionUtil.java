/**
 * PermissionUtil.java.java
 * @author FengMy
 * @since 2015年8月20日
 */
package com.winplan.permission;

import com.winplan.spring.ApplicationContextAware;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月20日
 */
public class PermissionUtil {
	private static PermissionManager manager;
	
	private static PermissionManager getManager(){
		if(manager == null){
			manager = ApplicationContextAware.getApplicationContext().getBean(PermissionManager.class);
		}
		return manager;
	}
	
	public static String getPageIdByUrl(String url){
		SystemPage page = getManager().getPageUrlMap().get(url);
		return page == null ? null : page.getId();
	}
	
	public static String getPageUrlById(String id){
		SystemPage page = getManager().getPageIdMap().get(id);
		return page == null? null : page.getUrl();
	}
	
	public static String createUrl(String defaultMenuId,String pageUrl){
		if(defaultMenuId == null || "".equals(defaultMenuId.trim())){
			SystemPage page = getManager().getPageUrlMap().get(pageUrl);
			if(page != null && page.getParentMenu() != null){
				defaultMenuId = page.getParentMenu().getId();
			}
		}
		return "index?_m=" + defaultMenuId + "&_p=" + getPageIdByUrl(pageUrl);
	}
}
