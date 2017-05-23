/*
 * File name:          ClassDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:41:17 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value="classDao")
public class ClassDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 获取所有的班级
	 * @return
	 */
	public List<com.likun.bean.Class> getAllClass(){
		List<com.likun.bean.Class> classes = sqlSessionTemplate.selectList("com.likun.dao.ClassDao.getAllClass");
		return classes;
	}
}
