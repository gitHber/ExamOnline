/*
 * File name:          PageResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.Page;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 28, 2017
 * <p>
 * Time:           10:14:56 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class PageResult extends BaseResult{
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "PageResult [page=" + page + "]";
	}
	
}
