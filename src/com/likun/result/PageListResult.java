/*
 * File name:          PageListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.Page;
import com.likun.bean.Question;
import com.likun.util.PageInfo;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 28, 2017
 * <p>
 * Time:           10:18:59 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class PageListResult extends BaseResult{
	private List<Page> pages;
	private PageInfo page;
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PageListResult [pages=" + pages + ", page=" + page + "]";
	}
	
}
