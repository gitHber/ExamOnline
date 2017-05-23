/*
 * File name:          QuestionListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.util.PageInfo;
import java.util.List;

import com.likun.bean.Question;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           12:17:56 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class QuestionListResult extends BaseResult {
	private List<Question> questions;
	private PageInfo page;
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "QuestionListResult [questions=" + questions + ", page=" + page + "]";
	}
}
