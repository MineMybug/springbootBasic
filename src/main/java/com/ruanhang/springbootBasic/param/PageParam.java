package com.ruanhang.springbootBasic.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

/** 
 * @author  阮航  
 * @date 创建时间：2017年11月24日 下午4:11:51 
 * @version 1.0 
*/
public class PageParam {
	
	private int beginLine;                   //起始行
    private Integer pageSize = 3;			//默认行数
    private Integer currentPage=0;        // 当前页
	
    public int getBeginLine() {
		return beginLine;                //自动计算当前行数
	}
	
    public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	 @Override
	 public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	 }
}
