/**
 * AdminUserServiceImpl.java.java
 * @author FengMy
 * @since 2015年8月18日
 */
package com.winplan.service.impl;

import org.springframework.stereotype.Component;

import com.winplan.entity.AdminUser;
import com.winplan.enums.AdminUserStatusEnum;
import com.winplan.service.AdminUserService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月18日
 */
@Component
public class AdminUserServiceImpl extends DataServiceImpl<AdminUser> implements AdminUserService {

	@Override
	public AdminUser add(AdminUser entity) {
		entity.setStatus(AdminUserStatusEnum.INIT);
		return super.add(entity);
	}
}
