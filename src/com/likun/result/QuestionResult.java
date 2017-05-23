/*
 * File name:          QuestionResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.Question;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 27, 2017
 * <p>
 * Time:           9:48:11 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class QuestionResult extends BaseResult {
	private Question question;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuestionResult [question=" + question + "]";
	}
	
}
