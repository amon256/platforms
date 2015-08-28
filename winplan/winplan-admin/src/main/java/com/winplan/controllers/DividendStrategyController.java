/**
 * DividendStrategyController.java.java
 * @author FengMy
 * @since 2015年8月27日
 */
package com.winplan.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winplan.entity.DividendStrategy;
import com.winplan.entity.DividendStrategy.Strategy;
import com.winplan.service.DividendStrategyService;
import com.winplan.utils.CollectionUtils;
import com.winplan.utils.Pagination;

/**  
 * 功能描述：分红策略
 * 
 * @author FengMy
 * @since 2015年8月27日
 */
@Controller
@RequestMapping(value="dividendstrategy/*")
public class DividendStrategyController extends BaseController {

	@Autowired
	private DividendStrategyService dividendStrategyService;
	
	@RequestMapping(value="list")
	public String list(Pagination pagination,ModelMap model){
		Query query = new Query();
		pagination.setRecordCount((int) dividendStrategyService.count(query));
		query = query.with(new Sort(Direction.DESC, "createTime"));
		query.skip((pagination.getCurrentPage() - 1) * pagination.getPageSize()).limit(pagination.getPageSize());
		List<DividendStrategy> consumeRecords = dividendStrategyService.findList(query);
		model.put("datas", consumeRecords);
		model.put("pagination", pagination);
		return "dividendstrategy/list";
	}
	
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap model){
		if(!model.containsKey("data")){
			DividendStrategy entity = new DividendStrategy();
			entity.setDate(new Date());
			model.put("data", entity);
		}
		return "dividendstrategy/add";
	}
	
	@RequestMapping(value="add")
	public String add(DividendStrategy entity,ModelMap model){
		validateBeforeSave(entity, model);
		if(!model.containsKey(MSG)){
			entity.setComplete(false);
			dividendStrategyService.add(entity);
			model.put(SUCC, "新增保存成功");
			return list(new Pagination(), model);
		}
		model.put("data", entity);
		return toAdd(model);
	}
	
	private void validateBeforeSave(DividendStrategy entity,ModelMap model){
		if(StringUtils.isEmpty(entity.getName())){
			model.put(MSG, "名称不能为空");
		}else if(entity.getDate() == null){
			model.put(MSG, "发放日期不能为空");
		}else if(entity.getExpect() == null || entity.getExpect().compareTo(BigDecimal.ZERO) <= 0){
			model.put(MSG, "发放金额必须大于0");
		}else{
			if(entity.getStrategys() != null){
				for(Strategy stra : entity.getStrategys()){
					if(StringUtils.isEmpty(stra.getFieldName())){
						model.put(MSG, "人员范围策略，字段不能为空");
						break;
					}else if(StringUtils.isEmpty(stra.getOperation())){
						model.put(MSG, "人员范围策略，比较方式不能为空");
						break;
					}else if(StringUtils.isEmpty(stra.getValue())){
						model.put(MSG, "人员范围策略，比较值不能为空");
						break;
					}
				}
			}
		}
	}
	
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam(value="id",required=true)String id,ModelMap model){
		DividendStrategy entity = dividendStrategyService.findById(id);
		model.put("data", entity);
		return "dividendstrategy/update";
	}
	
	@RequestMapping(value="update")
	public String update(DividendStrategy entity,ModelMap model){
		validateBeforeSave(entity, model);
		if(!model.containsKey(MSG)){
			dividendStrategyService.update(entity, CollectionUtils.createSet(String.class, "name","expect","date","strategys"));
			model.put(SUCC, "更新成功");
			return list(new Pagination(), model);
		}
		model.put("data", entity);
		return toUpdate(entity.getId(),model);
	}
	
	@RequestMapping(value="detail")
	public String detail(@RequestParam(value="id",required=true)String id,ModelMap model){
		DividendStrategy entity = dividendStrategyService.findById(id);
		model.put("data", entity);
		return "dividendstrategy/detail";
	}
}
