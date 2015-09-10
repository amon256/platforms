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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.winplan.context.BonusContext;
import com.winplan.context.SystemContext;
import com.winplan.entity.User;
import com.winplan.enums.BonusTypeEnum;
import com.winplan.service.BonusService;
import com.winplan.service.ConsumeRecordService;
import com.winplan.service.UserService;
import com.winplan.utils.CollectionUtils;
import com.winplan.utils.SecurityUtil;
import com.winplan.utils.ThreadUtils;

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
	
	@Autowired
	private ConsumeRecordService consumeRecordService;
	
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
		bonusService.addBonus(loginUser.getId(), 
				BigDecimal.ZERO.subtract(BigDecimal.valueOf(SystemContext.getBonusPerRegisterUser())).setScale(2, RoundingMode.HALF_UP).floatValue(), 
				1.0f,
				BonusTypeEnum.BONUS_KOU, false, MessageFormat.format("注册{0}，扣除{1}元",user.getAccount(),SystemContext.getBonusPerRegisterUser()));
		//发奖金
		//推荐奖
		giveTj(user);
		//层奖
		giveCen(user);
		addRegisterBonus(loginUser, user);
//		addConsumeRecord(user);
	}
	
	/**
	 * 增加报单奖
	 * @param loginUser
	 * @param user
	 */
	private void addRegisterBonus(User loginUser,User user){
		bonusService.addBonus(loginUser.getId(), BonusContext.getRegisterBonus(), 1.0f, BonusTypeEnum.ADD, true, MessageFormat.format("报单奖({0})", user.getAccount()));
	}
	
//	/**
//	 * 增加返现记录
//	 * @param user
//	 */
//	private void addConsumeRecord(User user){
//		ConsumeRecord record = new ConsumeRecord();
//		record.setAccount(user.getAccount());
//		record.setAmount(new BigDecimal(SystemContext.getBonusPerRegisterUser()).setScale(2, RoundingMode.HALF_UP));
//		record.setPeriodNumber(60);
//		record.setType(ConsumeTypeEnum.REG);
//		record.setDescription("注册资金");
//		consumeRecordService.add(record);
//	}
	
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
				//多线程并行计算
				int threadSize = parentUsers.size();
				ExecutorService executor = ThreadUtils.getPublicExecutorService();
				final CountDownLatch latch = new CountDownLatch(threadSize);
				for(final User pu : parentUsers){
					executor.execute(new Thread(){
						public void run() {
							Query pathQuery = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "0").and("level").gt(pu.getLevel()));
							long leftCount = count(pathQuery);
							pathQuery = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "1").and("level").gt(pu.getLevel()));
							long rightCount = count(pathQuery);
							update(Query.query(Criteria.where("_id").is(pu.getId())), Update.update("leftCount", leftCount).set("rightCount", rightCount));
							latch.countDown();
							System.out.println("计算人数线程号:" + this.getId());
						};
					});
				}
				try {
					latch.await();
				} catch (InterruptedException e) {
					logger.error("计算左右人数执行异常", e);
				}
			}
		}
	}
	
	private void giveCen(final User user){
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
		if(parentUsers != null){
			int threadSize = parentUsers.size();
			ExecutorService executor = ThreadUtils.getPublicExecutorService();
			final CountDownLatch latch = new CountDownLatch(threadSize);
			for(final User pu : parentUsers){
				executor.execute(new Thread(){
					@Override
					public void run() {
						Query query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "0").and("level").is(user.getLevel()));
						long leftCount = count(query);
						query = Query.query(Criteria.where("path").regex("^" + pu.getPath() + "1").and("level").is(user.getLevel()));
						long rightCount = count(query);
						if(leftCount > 0 && rightCount > 0 && ((leftCount == 1 && user.getPath().startsWith(pu.getPath() + "0")) || (rightCount == 1 && user.getPath().startsWith(pu.getPath() + "1")))){
							//左右都有，并且第一次成层
							//按层级计算奖金
							float bonus = BonusContext.getCenBonus(pu.getLevel(), user.getLevel());
							//增加奖金
							bonusService.addBonus(pu.getId(), bonus,BonusContext.getTaxRate(), BonusTypeEnum.BONUS_CEN, true, MessageFormat.format("层奖{0}元", bonus));
						}
						latch.countDown();
						System.out.println("计算层奖线程号:" + this.getId());
					}
				});
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				logger.error("计算层次线程异常", e);
			}
		}
	}
	
	/**
	 * 发推荐奖
	 * @param user
	 */
	private void giveTj(User user){
		if(user.getRecommend() != null){
			int bonus = BonusContext.getRecommendBonus();
			User rec = findByAccount(user.getRecommend());
			if(rec != null){
				bonusService.addBonus(rec.getId(), bonus,1.0f, BonusTypeEnum.BONUS_TJ, true, MessageFormat.format("推荐{0}获推荐奖{1}",user.getAccount(),bonus));
				logger.debug("{}推荐奖:{}",rec.getAccount(),bonus);
			}
		}
	}
	
	@Override
	public Map<String, Integer> groupRecommend(List<String> accounts) {
		String collection = User.class.getAnnotation(Document.class).collection();
		Query query = Query.query(Criteria.where("recommend").in(accounts));
		String reduce = "function(doc, aggr){aggr.count += 1;}";
		DBObject result = getMongoTemplate().getCollection(collection).group(new BasicDBObject("recommend",1), query.getQueryObject(), new BasicDBObject("count", 0), reduce);
		Map<String,Integer> resultMap = new HashMap<String, Integer>();
		if(result != null && result instanceof BasicDBList){
			BasicDBList list = (BasicDBList)result;
			Iterator<Object> iterator = list.iterator();
			while(iterator.hasNext()){
				BasicDBObject object = (BasicDBObject)iterator.next();
				resultMap.put(object.getString("recommend"), object.getInt("count"));
			}
		}
		return resultMap;
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
