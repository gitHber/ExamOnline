/*
 * File name:          StudentScoreListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.StudentScore;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 21, 2017
 * <p>
 * Time:           3:47:19 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentScoreListResult extends BaseResult {
	private List<StudentScore> scores;
	private int studentNum;
	public List<StudentScore> getScores() {
		return scores;
	}
	public void setScores(List<StudentScore> scores) {
		this.scores = scores;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	@Override
	public String toString() {
		return "StudentScoreListResult [scores=" + scores + ", studentNum=" + studentNum + "]";
	}
	
	
}
