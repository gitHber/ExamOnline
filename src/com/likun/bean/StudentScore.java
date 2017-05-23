/*
 * File name:          ExamStudent.java
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
 * Date:           May 19, 2017
 * <p>
 * Time:           2:32:51 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentScore {
	private int examId;
	private int stuId;
	private int score;
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getCent() {
		return score;
	}
	public void setCent(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "StudentScore [examId=" + examId + ", stuId=" + stuId + ", score=" + score + "]";
	}
	
}
