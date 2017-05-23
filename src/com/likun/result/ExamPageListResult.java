/*
 * File name:          ExamPageListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import javax.sql.rowset.Predicate;

import com.likun.bean.ExamPage;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 22, 2017
 * <p>
 * Time:           12:35:40 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ExamPageListResult extends BaseResult {
	private List<ExamPage> examPages;

	public List<ExamPage> getExamPages() {
		return examPages;
	}

	public void setExamPages(List<ExamPage> examPages) {
		this.examPages = examPages;
	}

	@Override
	public String toString() {
		return "ExamPageListResult [examPages=" + examPages + "]";
	}
	
}
