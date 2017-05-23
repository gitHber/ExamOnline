/*
 * File name:          CourseService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

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
 * Time:           9:46:33 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface CourseService {
	/**
	 * 得到所有的课程
	 * @return
	 */
	public List<Course> getAllCourse();
}
