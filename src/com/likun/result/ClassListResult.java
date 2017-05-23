/*
 * File name:          ClassListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:50:53 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class ClassListResult extends BaseResult {
	List<com.likun.bean.Class> classes;

	public List<com.likun.bean.Class> getClasses() {
		return classes;
	}

	public void setClasses(List<com.likun.bean.Class> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "ClassListResult [classes=" + classes + "]";
	}
	
}
