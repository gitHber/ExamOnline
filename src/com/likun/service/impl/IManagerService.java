/*
 * File name:          ITeacherService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Manager;
import com.likun.dao.ManagerDao;
import com.likun.service.ManagerService;

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
@Service(value="managerService")
public class IManagerService implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	/**
	 * 通过教师id获得教师信息
	 */
	@Override
	public Manager getManagerById(String managerno) {
		return managerDao.getManagerById(managerno);
	}

}
