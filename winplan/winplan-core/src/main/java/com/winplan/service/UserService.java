/**
 * UserService.java.java
 * @author FengMy
 * @since 2015年7月2日
 */
package com.winplan.service;

import java.util.List;
import java.util.Map;

import com.winplan.entity.User;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月2日
 */
public interface UserService extends DataService<User> {

	/**
	 * 注册用户
	 * @param user
	 * @param parent
	 * @param dir
	 */
	public void register(User user,User parent,User loginUser,String dir); 
	
	/**
	 * 按账号查询
	 * @param account
	 * @return
	 */
	public User findByAccount(String account);
	
	/**
	 * 根据账号统计推荐人数
	 * @param accounts
	 * @return
	 */
	public Map<String,Integer> groupRecommend(List<String> accounts);
}
