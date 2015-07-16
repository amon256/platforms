/**
 * Pagination.java.java
 * @author FengMy
 * @since 2015年7月15日
 */
package com.winplan.utils;

import java.io.Serializable;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月15日
 */
public class Pagination implements Serializable {
	private static final long serialVersionUID = 1178299039073862046L;
	private static final int DEFAULT_PAGE = 1;
	public static int DEFAULT_PAGE_SIZE = 10;
	private int recordCount;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int currentPage = DEFAULT_PAGE;
	public Pagination(){
	}
	public Pagination(int pageSize, int page) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("页码必须大于0!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
		}
	}
	
	public String getCount(){
		return this.getRecordCount()+"";
	}
	
	public void setPageSize(int countOnEachPage) {
		this.pageSize = countOnEachPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}


	public void setRecordCount(int totalCount) {
		this.recordCount = totalCount;
	}
	

	public int getPageCount() {
		return (recordCount == 0) ? 1 : ((recordCount % pageSize == 0) ? (recordCount / pageSize)
				: (recordCount / pageSize) + 1);
	}

	public int getPreviousPage() {
		if(currentPage > 1) return currentPage - 1;
		else return DEFAULT_PAGE;
	}
	
	public int getNextPage() {
		if(currentPage < getPageCount()) return currentPage + 1;
		else return getPageCount();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getStartIndex(){
		return (getCurrentPage() - 1) * getPageSize();
	}
}
