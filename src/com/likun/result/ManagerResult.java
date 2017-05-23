/*
 * File name:          StudentResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import com.likun.bean.Manager;
import com.likun.bean.Student;
import com.likun.bean.Teacher;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 19, 2017
 * <p>
 * Time:           11:25:54 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ManagerResult extends BaseResult {
	private Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "ManagerResult [manager=" + manager + "]";
	}
	
}
