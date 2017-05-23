/*
 * File name:          StudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import com.likun.bean.Teacher;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:30:18 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface TeacherService {
	/**
	 * 根据教师id得到用户信息
	 * @return
	 */
	public Teacher getTeacherById(String teacherno);
}
