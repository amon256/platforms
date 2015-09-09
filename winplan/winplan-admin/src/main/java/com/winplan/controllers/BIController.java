/**
 * BIController.java.java
 * @author FengMy
 * @since 2015年9月9日
 */
package com.winplan.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.winplan.service.UserService;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年9月9日
 */
@Controller
@RequestMapping(value="bi/*")
public class BIController extends BaseController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="registerTrend")
	public String registerTrend(
				@DateTimeFormat(pattern="yyyy-MM-dd")
				@RequestParam(required=false)
				Date from,
				@DateTimeFormat(pattern="yyyy-MM-dd")
				@RequestParam(required=false)
				Date to,
				ModelMap model){
		
		if(from == null){
			from = getDefaultFrom();
		}
		if(to == null){
			to = getDefaultTo();
		}
		from = setDefaultFromTime(from);
		to = setDefaultToTime(to);
		
		model.put("from", from);
		model.put("to", to);
		
		Query query = Query.query(Criteria.where("createTime").gte(from).lte(to));
		DBObject args = new BasicDBObject("$keyf", "function(doc){var date=new Date(doc.createTime);var dateKey=\"\"+date.getFullYear()+\"-\"+(date.getMonth()+1)+\"-\"+date.getDate();return {date:dateKey}}")
								.append("cond", query.getQueryObject())
								.append("initial", new BasicDBObject("count",0))
								.append("$reduce", "function(doc,rs){rs.count += 1;}");
		DBObject result = userService.group(args);
		Map<String,Integer> resultMap = getDefaultResultMap(from, to);
		if(result != null && result instanceof BasicDBList){
			BasicDBList list = (BasicDBList)result;
			Iterator<Object> iterator = list.iterator();
			while(iterator.hasNext()){
				BasicDBObject object = (BasicDBObject)iterator.next();
				resultMap.put(object.getString("date"), object.getInt("count"));
			}
		}
		List<String> dateList = getDateList(from, to);
		List<Integer> dataList = getDataList(dateList,resultMap);
		model.put("dates", dateList);
		model.put("dataList", dataList);
		
		Calendar cal = Calendar.getInstance();
		model.put("today", cal.getTime());
		cal.add(Calendar.DATE, -6);
		model.put("sevenDayAgo", cal.getTime());
		cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		model.put("firstDayOfThisMonth", cal.getTime());
		cal.add(Calendar.MONTH, -1);
		model.put("firstDayOfLastMonth", cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, false);
		model.put("lastDayOfLastMonth", cal.getTime());
		return "bi/registerTrend";
	}
	
	private List<Integer> getDataList(List<String> dateList, Map<String, Integer> resultMap) {
		List<Integer> dataList = new ArrayList<Integer>(dateList.size());
		for(String date : dateList){
			dataList.add(resultMap.get(date));
		}
		return dataList;
	}

	private List<String> getDateList(Date from,Date to){
		List<String> list = new LinkedList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		do{
			list.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}while(cal.getTime().before(to));
		return list;
	}
	
	private Map<String,Integer> getDefaultResultMap(Date from,Date to){
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		do{
			map.put(sdf.format(cal.getTime()), 0);
			cal.add(Calendar.DATE, 1);
		}while(cal.getTime().before(to));
		return map;
	}
	
	private Date setDefaultFromTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	private Date setDefaultToTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 默认到今天
	 * @return
	 */
	private Date getDefaultTo(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return new Date();
	}
	
	/**
	 * 默认从哪天开始
	 * @return
	 */
	private Date getDefaultFrom(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		return cal.getTime();
	}
}
