/*
 * File name:          ITeacherService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Teacher;
import com.likun.dao.TeacherDao;
import com.likun.service.TeacherService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           11:13:15 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="teacherservice")
public class ITeacherService implements TeacherService {
	@Autowired
	TeacherDao teacherDao;
	/**
	 * 通过教师id获得教师信息
	 */
	@Override
	public Teacher getTeacherById(String teacherno) {
		return teacherDao.getTeacherById(teacherno);
	}

}
