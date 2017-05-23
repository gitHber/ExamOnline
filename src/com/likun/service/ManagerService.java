/*
 * File name:          StudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import com.likun.bean.Manager;

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
public interface ManagerService {
	/**
	 * 根据管理员id得到用户信息
	 * @return
	 */
	public Manager getManagerById(String managerno);
}
