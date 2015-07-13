/**
 * UserServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月2日
 */
package com.winplan.service.impl;

import java.text.DecimalFormat;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.winplan.context.SystemContext;
import com.winplan.entity.User;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月2日
 */
@Component
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {
	
	private static final String ACCOUNT = "user.account";
	
	public User findByAccount(String account) {
		return findOne(Query.query(Criteria.where("account").is(account)));
	}

	@Override
	public synchronized void register(User user,User parent,String dir) {
		String account = createAccount();
		user.setAccount(account);
		user.setPassword(SystemContext.getDefaultPassword());
		if(parent != null && parent.getPath() != null){
			user.setPath(parent.getPath() + ("left".equals(dir)?"0":"1"));
		}
		this.add(user);
		if(parent != null){
			if("left".equals(dir)){
				parent.setLeft(user.getAccount());
				update(parent, CollectionUtils.createSet(String.class, "left"));
			}else if("right".equals(dir)){
				parent.setRight(user.getAccount());
				update(parent, CollectionUtils.createSet(String.class, "right"));
			}
		}
		//TODO 扣奖金，发奖金
	}
	
	/**
	 * 创建账号
	 * @return
	 */
	private String createAccount(){
		int index = getNextInt(ACCOUNT);
		String account = SystemContext.getAccountPrefix();
		if(String.valueOf(index).length() >= SystemContext.getAccountPattern().length()){
			account += index;
		}else{
			DecimalFormat df = new DecimalFormat(SystemContext.getAccountPattern());
			account += df.format(index);
		}
		return account;
	}
	
	
}
