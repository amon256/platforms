/**
 * BonusServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.UserService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月9日
 */
@Component
public class BonusServiceImpl extends DataServiceImpl<BonusHistory> implements BonusService {

	@Autowired
	private UserService userService;
	
	@Override
	public void transfer(User from, User to, BigDecimal bonus, String desc) {
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//源账号扣除bonus
		from = getMongoTemplate().findAndModify(
				Query.query(Criteria.where("_id").is(from.getId())), 
				Update.update("lastUpdateTime", new Date()).inc("bonus", BigDecimal.ZERO.subtract(bonus).setScale(2, RoundingMode.HALF_UP).doubleValue()),
				options, 
				User.class);
		//目标账号增加bonus及累计bonus
		to = getMongoTemplate().findAndModify(
				Query.query(Criteria.where("_id").is(to.getId())), 
				Update.update("lastUpdateTime", new Date()).inc("bonus", bonus.setScale(2, RoundingMode.HALF_UP).doubleValue())
					.inc("totalBonus", bonus.setScale(2, RoundingMode.HALF_UP).doubleValue()), 
				options,
				User.class);
		//增加转账记录
		BonusHistory his = new BonusHistory();
		his.setDesc(desc);
		his.setBonus(bonus);
		his.setAccount(from.getAccount());
		his.setOtherAccount(to.getAccount());
		his.setSurplus(from.getBonus());
		his.setTotalBonus(from.getTotalBonus());
		his.setType(BonusTypeEnum.TRANSFER_OUT);
		this.add(his);//转出方记录
		his.setAccount(to.getAccount());
		his.setOtherAccount(from.getAccount());
		his.setSurplus(to.getBonus());
		his.setTotalBonus(to.getTotalBonus());
		his.setType(BonusTypeEnum.TRANSFER_IN);
		his.setId(null);
		this.add(his);//转入方记录
	}

}
