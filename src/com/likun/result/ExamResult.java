/*
 * File name:          PageResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.Exam;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 28, 2017
 * <p>
 * Time:           10:14:56 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ExamResult extends BaseResult{
	private Exam exam;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "ExamResult [exam=" + exam + "]";
	}
}
