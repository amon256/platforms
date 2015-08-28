/**
 * DividendRecordController.java.java
 * @author FengMy
 * @since 2015年8月28日
 */
package com.winplan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winplan.entity.DividendRecord;
import com.winplan.entity.DividendStrategy;
import com.winplan.service.DividendRecordService;
import com.winplan.service.DividendStrategyService;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年8月28日
 */
@Controller
@RequestMapping(value="dividendrecord/*")
public class DividendRecordController extends BaseController {
	
	@Autowired
	private DividendStrategyService dividendStrategyService;
	
	@Autowired
	private DividendRecordService dividendRecordService;

	@RequestMapping(value="list")
	public String list(@RequestParam(value="strategy",required=true)String strategy,Pagination pagination,ModelMap model){
		DividendStrategy strategyData = dividendStrategyService.findById(strategy);
		if(strategy != null){
			Query query = Query.query(Criteria.where("strategy.$id").is(strategyData.getId()));
			pagination.setRecordCount((int) dividendRecordService.count(query));
			query = query.with(new Sort(Direction.ASC, "createTime"));
			query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
			List<DividendRecord> consumeRecords = dividendRecordService.findList(query);
			model.put("datas", consumeRecords);
		}
		model.put("strategy", strategyData);
		model.put("pagination", pagination);
		return "dividendstrategy/recordList";
	}
}
