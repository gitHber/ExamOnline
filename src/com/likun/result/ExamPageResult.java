/*
 * File name:          ExamPageResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.ExamPage;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 7, 2017
 * <p>
 * Time:           5:57:45 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ExamPageResult extends BaseResult {
	private ExamPage examPage;

	public ExamPage getExamPage() {
		return examPage;
	}

	public void setExamPage(ExamPage examPage) {
		this.examPage = examPage;
	}

	@Override
	public String toString() {
		return "ExamPageResult [examPage=" + examPage + "]";
	}
	 
}
