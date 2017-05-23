/*
 * File name:          PageListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.Exam;
import com.likun.bean.ExamScore;
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
public class ExamListResult extends BaseResult{
	private List<Exam> exams;
	private PageInfo page;
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "ExamListResult [exams=" + exams + ", page=" + page + "]";
	}
}
