/*
 * File name:          ExamScore.java
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
 * Date:           May 20, 2017
 * <p>
 * Time:           11:13:52 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ExamScore extends Exam {
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ExamScore [score=" + score + "]";
	}
	
}
