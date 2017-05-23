/*
 * File name:          IClassService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Class;
import com.likun.dao.ClassDao;
import com.likun.service.ClassService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:46:29 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="classService")
public class IClassService implements ClassService {
	@Autowired
	ClassDao classDao;
	@Override
	public List<Class> getAllClass() {
		return classDao.getAllClass();
	}

}
