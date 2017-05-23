/*
 * File name:          SpyExamResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.HashMap;
import java.util.List;

import com.likun.bean.Student;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 22, 2017
 * <p>
 * Time:           1:43:10 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class SpyExamResult extends BaseResult{
	private List<Student> notExamStudents;
	private List<Student> examingStudents;
	private List<Student> examedStudents;
	
	public List<Student> getNotExamStudents() {
		return notExamStudents;
	}
	public void setNotExamStudents(List<Student> notExamStudents) {
		this.notExamStudents = notExamStudents;
	}
	public List<Student> getExamingStudents() {
		return examingStudents;
	}
	public void setExamingStudents(List<Student> examingStudents) {
		this.examingStudents = examingStudents;
	}
	public List<Student> getExamedStudents() {
		return examedStudents;
	}
	public void setExamedStudents(List<Student> examedStudents) {
		this.examedStudents = examedStudents;
	}
	@Override
	public String toString() {
		return "SpyExamResult [notExamStudents=" + notExamStudents + ", examingStudents=" + examingStudents
				+ ", examedStudents=" + examedStudents + "]";
	}
 }
