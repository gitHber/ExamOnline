/*
 * File name:          StudentExam.java
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
 * Date:           May 18, 2017
 * <p>
 * Time:           3:16:16 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentExam {
	private int stuId;
	private int examId;
	private int questionId;
	private String answer;
	private int cent;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getCent() {
		return cent;
	}
	public void setCent(int cent) {
		this.cent = cent;
	}
	@Override
	public String toString() {
		return "StudentExam [stuId=" + stuId + ", examId=" + examId + ", questionId=" + questionId + ", answer="
				+ answer + ", cent=" + cent + "]";
	}
	
}
