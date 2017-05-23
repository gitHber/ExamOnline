/*
 * File name:          CourseListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.Course;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           9:50:21 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class CourseListResult extends BaseResult {
	private List<Course> courses;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "CourseListResult [courses=" + courses + "]";
	}
	
}
