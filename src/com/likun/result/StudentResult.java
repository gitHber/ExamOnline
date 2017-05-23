/*
 * File name:          StudentResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.Student;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 19, 2017
 * <p>
 * Time:           11:25:54 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentResult extends BaseResult {
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentResult [student=" + student + "]";
	}
	
}
