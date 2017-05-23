/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Course;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:28:31 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value="courseDao")
public class CourseDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 得到所有的课程
	 * @return
	 */
	public List<Course> getAllCourse() {
		List<Course> courses = sqlSessionTemplate.selectList("com.likun.dao.CourseDao.getAllCourse");
		return courses;
	}
}
