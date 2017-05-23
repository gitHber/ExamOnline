/*
 * File name:          PracticeQuestionListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.Question;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 18, 2017
 * <p>
 * Time:           10:16:22 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class PracticeQuestionListResult extends BaseResult{
	private List<Question> singleQuestions;
	private List<Question> multipleQuestions;
	private List<Question> blankQuestions;
	private List<Question> subjectiveQuestions;
	public List<Question> getSingleQuestions() {
		return singleQuestions;
	}
	public void setSingleQuestions(List<Question> singleQuestions) {
		this.singleQuestions = singleQuestions;
	}
	public List<Question> getMultipleQuestions() {
		return multipleQuestions;
	}
	public void setMultipleQuestions(List<Question> multipleQuestions) {
		this.multipleQuestions = multipleQuestions;
	}
	public List<Question> getBlankQuestions() {
		return blankQuestions;
	}
	public void setBlankQuestions(List<Question> blankQuestions) {
		this.blankQuestions = blankQuestions;
	}
	public List<Question> getSubjectiveQuestions() {
		return subjectiveQuestions;
	}
	public void setSubjectiveQuestions(List<Question> subjectiveQuestions) {
		this.subjectiveQuestions = subjectiveQuestions;
	}
	@Override
	public String toString() {
		return "PracticeQuestionListResult [singleQuestions=" + singleQuestions + ", multipleQuestions="
				+ multipleQuestions + ", blankQuestions=" + blankQuestions + ", subjectiveQuestions="
				+ subjectiveQuestions + "]";
	}
	
}
