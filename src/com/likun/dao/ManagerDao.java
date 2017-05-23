/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Manager;

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
@Repository(value="managerDao")
public class ManagerDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 根据管理员id得到用户信息
	 * @return
	 */
	public Manager getManagerById(String managerno) {
		Manager manager = sqlSessionTemplate.selectOne("com.likun.dao.ManagerDao.getManagerById", managerno);
		return manager;
	}
}
