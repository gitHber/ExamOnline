package com.likun.util;

/**
 * Page类
 * @author ACER-
 *
 */
public class PageInfo {
	private int pageCount;//总页数
	private int pageSize;//每页显示多少条数据
	private int pageCurrent;//当前页数
	private int itemCount;//总计条数

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {
		return "Page [pageCount=" + pageCount + ", pageSize=" + pageSize + ", pageCurrent=" + pageCurrent
				+ ", itemCount=" + itemCount + "]";
	}

}
