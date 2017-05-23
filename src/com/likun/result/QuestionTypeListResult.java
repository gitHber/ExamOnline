/*
 * File name:          QuestonTypeListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.QuestionType;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           9:23:58 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class QuestionTypeListResult extends BaseResult {
	private List<QuestionType> questionTypes;

	public List<QuestionType> getQuestionTypes() {
		return questionTypes;
	}

	public void setQuestionTypes(List<QuestionType> questionTypes) {
		this.questionTypes = questionTypes;
	}

	@Override
	public String toString() {
		return "QuestonTypeListResult [questionTypes=" + questionTypes + "]";
	}
	
}
