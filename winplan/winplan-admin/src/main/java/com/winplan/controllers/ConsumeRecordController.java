/**
 * ConsumeRecordController.java.java
 * @author FengMy
 * @since 2015年8月25日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.winplan.annotations.TokenCreate;
import com.winplan.annotations.TokenValidate;
import com.winplan.entity.ConsumeRecord;
import com.winplan.entity.ReturnRecord;
import com.winplan.service.ConsumeRecordService;
import com.winplan.service.ReturnRecordService;
import com.winplan.service.UserService;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月25日
 */
@Controller
@RequestMapping(value="consume/*")
public class ConsumeRecordController extends BaseController {
	
	@Autowired
	private ConsumeRecordService consumeRecordService;
	@Autowired 
	private ReturnRecordService returnRecordService;
	@Autowired
	private UserService userService;

	@RequestMapping(value="list")
	public String list(Pagination pagination,ConsumeRecord record,ModelMap model){
		Query query = new Query();
		if(record != null && record.getAccount() != null && !"".equals(record.getAccount().trim())){
			query.addCriteria(Criteria.where("account").is(record.getAccount()));
		}
		pagination.setRecordCount((int) consumeRecordService.count(query));
		query = query.with(new Sort(Direction.DESC, "createTime"));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<ConsumeRecord> consumeRecords = consumeRecordService.findList(query);
		if(consumeRecords != null && !consumeRecords.isEmpty()){
			List<String> ids = new ArrayList<String>(consumeRecords.size());
			for(ConsumeRecord rec : consumeRecords){
				ids.add(rec.getId());
			}
			String reduce = "function(doc, aggr){aggr.count += 1;aggr.returnedAmount += doc.amount}";
			query = new Query(Criteria.where("consumeRecord.$id").in(ids).and("complete").is(true));
			DBObject result = returnRecordService.group(new BasicDBObject("consumeRecord.$id", 1), query.getQueryObject() , new BasicDBObject("count", 0).append("returnedAmount", 0d), reduce, null);
			Map<String,Integer> resultMap = new HashMap<String, Integer>();
			Map<String,BigDecimal> amountMap = new HashMap<String, BigDecimal>();
			if(result != null && result instanceof BasicDBList){
				BasicDBList list = (BasicDBList)result;
				Iterator<Object> iterator = list.iterator();
				while(iterator.hasNext()){
					BasicDBObject object = (BasicDBObject)iterator.next();
					resultMap.put(object.getString("consumeRecord.$id"), object.getInt("count"));
					amountMap.put(object.getString("consumeRecord.$id"), new BigDecimal(object.getDouble("returnedAmount", 0d)).setScale(2, RoundingMode.HALF_UP));
				}
				for(ConsumeRecord rec : consumeRecords){
					if(resultMap.get(rec.getId()) != null){
						rec.setReturned(resultMap.get(rec.getId()));
					}
					rec.setUnreturn(rec.getPeriodNumber() - rec.getReturned());
					if(amountMap.get(rec.getId()) != null){
						rec.setReturnedAmount(amountMap.get(rec.getId()));
					}
				}
			}
		}
		model.put("datas", consumeRecords);
		model.put("pagination", pagination);
		model.put("record", record);
		return "consume/list";
	}
	
	@RequestMapping(value="toAdd")
	@TokenCreate
	public String toAdd(ModelMap model){
		return "consume/add";
	}
	
	@RequestMapping(value="add")
	@TokenCreate
	@TokenValidate
	public String add(ConsumeRecord record,ModelMap model){
		if(record.getAccount() == null || "".equals(record.getAccount())){
			model.put(MSG, "消费人账号不能为空");
		}else if(userService.findByAccount(record.getAccount().trim()) == null){
			model.put(MSG, "消费人账号不存在");
		}else if(record.getPeriodNumber() < 0){
			model.put(MSG, "返还期数不能为负数");
		}else if(record.getAmount() == null || record.getAmount().compareTo(BigDecimal.ZERO) <= 0){
			model.put(MSG, "消费金额非法");
		}else{
			record.setAmount(record.getAmount().setScale(2, RoundingMode.HALF_DOWN));
			consumeRecordService.add(record);
			return list(new Pagination(), record, model);
		}
		model.put("record", record);
		return "consume/add";
	}
	
	@RequestMapping(value="detail")
	public String detail(@RequestParam(value="id",required=true)String id,ModelMap model){
		ConsumeRecord record = consumeRecordService.findById(id);
		List<ReturnRecord> returnRecords = returnRecordService.findByConsumeRecordId(record.getId());
		int returned = 0;
		BigDecimal returnedAmount = BigDecimal.ZERO;
		for(ReturnRecord rec : returnRecords){
			if(rec.getComplete()){
				returned++;
				returnedAmount = returnedAmount.add(rec.getAmount());
			}
		}
		record.setReturned(returned);
		record.setReturnedAmount(returnedAmount);
		model.put("record", record);
		model.put("returnRecords", returnRecords);
		if(record.getPeriodNumber() > 0){
			model.put("percent", new BigDecimal(returned).divide(new BigDecimal(record.getPeriodNumber()),2,RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(0, RoundingMode.UP));
		}
		return "consume/detail";
	}
}
