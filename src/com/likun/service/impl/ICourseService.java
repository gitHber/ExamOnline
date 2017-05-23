/*
 * File name:          ICourseService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Course;
import com.likun.dao.CourseDao;
import com.likun.service.CourseService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           9:47:35 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="courseService")
public class ICourseService implements CourseService {
	@Autowired
	private CourseDao courseDao;
	/**
	 * 得到所有的课程
	 * @return
	 */
	@Override
	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

}
