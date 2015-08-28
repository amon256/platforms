/**
 * BonusServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月9日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(BonusServiceImpl.class);
			
	@Autowired
	private UserService userService;
	
	@Override
	public void addBonus(String userId, float amount,float taxRate, BonusTypeEnum type, boolean incTotalBonus,String description) {
		if(amount == 0.00f){
			logger.debug("金额为零，不处理");
			return;
		}
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Query query = Query.query(Criteria.where("_id").is(userId));
		Update update = Update.update("lastUpdateTime", new Date()).inc("bonus",(amount * taxRate));
		if(amount > 0 && incTotalBonus){
			update.inc("totalBonus", amount);
		}
		User user = getMongoTemplate().findAndModify(query, update, options, User.class);
		if(user != null){
			BonusHistory his = new BonusHistory();
			his.setDesc(description);
			his.setBonus(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
			his.setAccount(user.getAccount());
			his.setSurplus(user.getBonus());
			his.setTotalBonus(user.getTotalBonus());
			his.setType(type);
			this.add(his);
		}else{
			logger.debug("用户不存在");
		}
	}
	
	@Override
	public void transfer(User from, User to, BigDecimal bonus, String desc) {
		addBonus(from.getId(), BigDecimal.ZERO.subtract(bonus).setScale(2, RoundingMode.HALF_UP).floatValue(),1.0f, BonusTypeEnum.TRANSFER_OUT, false, "奖金转出");
		addBonus(to.getId(),  bonus.setScale(2, RoundingMode.HALF_UP).floatValue(),1.0f, BonusTypeEnum.TRANSFER_IN, false, "奖金转入");
	}

}
