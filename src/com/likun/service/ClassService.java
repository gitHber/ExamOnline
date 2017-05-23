/*
 * File name:          ClassService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.List;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:45:47 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface ClassService {
	/**
	 * 获取所有的班级
	 * @return
	 */
	public List<com.likun.bean.Class> getAllClass();
}
