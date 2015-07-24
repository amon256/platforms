/**
 * UserServiceImpl.java.java
 * @author FengMy
 * @since 2015年7月2日
 */
package com.winplan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.winplan.context.BonusContext;
import com.winplan.context.SystemContext;
import com.winplan.entity.BonusHistory;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;
import com.winplan.utils.SecurityUtil;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月2日
 */
@Component
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String ACCOUNT = "user.account";
	
	@Autowired
	private BonusService bonusService;
	
	public User findByAccount(String account) {
		return findOne(Query.query(Criteria.where("account").is(account)));
	}

	@Override
	public synchronized void register(User user,User parent,User loginUser,String dir) {
		logger.debug("注册新用户:");
		String account = createAccount();
		user.setAccount(account);
		logger.debug("账号:{}",user.getAccount());
		user.setPassword(SecurityUtil.encryptSHA(SystemContext.getDefaultPassword()));
		logger.debug("密码:{}",user.getPassword());
		if(parent != null && parent.getPath() != null){
			user.setPath(parent.getPath() + ("left".equals(dir)?"0":"1"));
			logger.debug("path:{}",user.getPath());
			user.setLevel(parent.getLevel() + 1);
			logger.debug("level:{}",user.getLevel());
		}
		this.add(user);
		if(parent != null){
			logger.debug("parent:{},dir:{}",parent.getAccount(),dir);
			if("left".equals(dir)){
				parent.setLeft(user.getAccount());
				update(parent, CollectionUtils.createSet(String.class, "left"));
			}else if("right".equals(dir)){
				parent.setRight(user.getAccount());
				update(parent, CollectionUtils.createSet(String.class, "right"));
			}
		}
		//重算左右区域人数
		recalUserCount(user);
		//扣注册奖金
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		//源账号扣除bonus
		loginUser = getMongoTemplate().findAndModify(
				Query.query(Criteria.where("_id").is(loginUser.getId())), 
				Update.update("lastUpdateTime", new Date()).inc("bonus", BigDecimal.ZERO.subtract(BigDecimal.valueOf(SystemContext.getBonusPerRegisterUser())).setScale(2, RoundingMode.HALF_UP).doubleValue()),
				options, 
				User.class);
		logger.debug("扣除账号{}奖金{}",loginUser.getAccount(),SystemContext.getBonusPerRegisterUser());
		//扣奖金记录
		BonusHistory his = new BonusHistory();
		his.setAccount(loginUser.getAccount());
		his.setBonus(BigDecimal.valueOf(SystemContext.getBonusPerRegisterUser()));
		his.setDesc(MessageFormat.format("注册{0}，扣除{1}元",account,his.getBonus()));
		his.setSurplus(loginUser.getBonus());
		his.setType(BonusTypeEnum.BONUS_KOU);
		bonusService.add(his);
		//发奖金
		//推荐奖
		giveTj(user);
		//层奖
		giveCen(user);
	}
	
	private void recalUserCount(User user){
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Set<String> paths = new HashSet<String>();
		String path = user.getPath();
		while(path.length() > 1){
			path = path.substring(0, path.length() - 1);
			paths.add(path);
		}
		List<User> parentUsers = null;
		if(paths.size() > 0){
			Query query = Query.query(Criteria.where("path").in(paths)).with(new Sort(Direction.DESC, "path"));
			parentUsers = this.findList(query);
			if(parentUsers != null){
				for(User pu : parentUsers){
					query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "0").and("level").gt(pu.getLevel()));
					long leftCount = this.count(query);
					query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "1").and("level").gt(pu.getLevel()));
					long rightCount = this.count(query);
					this.update(Query.query(Criteria.where("_id").is(pu.getId())), Update.update("leftCount", leftCount).set("rightCount", rightCount));
				}
			}
		}
	}
	
	private void giveCen(User user){
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Set<String> paths = new HashSet<String>();
		String path = user.getPath();
		while(path.length() > 1){
			path = path.substring(0, path.length() - 1);
			paths.add(path);
		}
		List<User> parentUsers = null;
		if(paths.size() > 0){
			Query query = Query.query(Criteria.where("path").in(paths)).with(new Sort(Direction.DESC, "path"));
			parentUsers = this.findList(query);
		}
		BonusHistory his = null;
		if(parentUsers != null){
			for(User pu : parentUsers){
				Query query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "0").and("level").is(user.getLevel()));
				long leftCount = this.count(query);
				query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "1").and("level").is(user.getLevel()));
				long rightCount = this.count(query);
				if(leftCount > 0 && rightCount > 0 && (leftCount == 1 || rightCount == 1)){
					//左右都有，并且第一次成层
					//按层级计算奖金
					double bonus = BonusContext.getCenBonus(pu.getLevel(), user.getLevel());
					//增加奖金
					pu = getMongoTemplate().findAndModify(
							Query.query(Criteria.where("_id").is(pu.getId())), 
							Update.update("lastUpdateTime", new Date()).inc("bonus", bonus*BonusContext.getTaxRate()).inc("totalBonus", bonus),
							options, 
							User.class);
					if(pu != null){
						logger.debug("{}发{}层奖(left:{},right:{})",pu.getAccount(),bonus,leftCount,rightCount);
						//记录奖金历史
						his = new BonusHistory();
						his.setBonus(BigDecimal.valueOf(bonus*BonusContext.getTaxRate()));
						his.setAccount(pu.getAccount());
						his.setSurplus(pu.getBonus());
						his.setTotalBonus(pu.getTotalBonus());
						his.setType(BonusTypeEnum.BONUS_CEN);
						his.setDesc(MessageFormat.format("层奖{0}元", bonus));
						bonusService.add(his);
					}
				}
			}
		}
	}
	
	/**
	 * 发推荐奖
	 * @param user
	 */
	private void giveTj(User user){
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		if(user.getRecommend() != null){
			int bonus = BonusContext.getRecommendBonus();
			User rec = getMongoTemplate().findAndModify(
						Query.query(Criteria.where("account").is(user.getRecommend())), 
						Update.update("lastUpdateTime", new Date()).inc("bonus", bonus).inc("totalBonus", bonus),
						options, 
						User.class);
			if(rec != null){
				logger.debug("{}推荐奖:{}",rec.getAccount(),bonus);
				BonusHistory his = new BonusHistory();
				his.setAccount(rec.getAccount());
				his.setBonus(BigDecimal.valueOf(bonus));
				his.setSurplus(rec.getBonus());
				his.setType(BonusTypeEnum.BONUS_TJ);
				his.setTotalBonus(rec.getTotalBonus());
				his.setDesc(MessageFormat.format("推荐{0}获推荐奖{1}",user.getAccount(),bonus));
				bonusService.add(his);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(MessageFormat.format("层奖{0}元", 50));
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
