/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Teacher;

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
@Repository(value="teacherDao")
public class TeacherDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 根据教师id得到用户信息
	 * @return
	 */
	public Teacher getTeacherById(String teacherno) {
		Teacher teacher = sqlSessionTemplate.selectOne("com.likun.dao.TeacherDao.getTeacherById", teacherno);
		return teacher;
	}
}
