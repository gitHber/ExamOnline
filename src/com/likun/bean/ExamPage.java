/*
 * File name:          ExamPage.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.bean;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 7, 2017
 * <p>
 * Time:           5:35:56 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ExamPage extends Exam{
	private Page page;

	public Page getPaeg() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "ExamPage [page=" + page + "]";
	}
}
