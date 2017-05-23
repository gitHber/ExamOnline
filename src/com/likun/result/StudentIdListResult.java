/*
 * File name:          StudentIdListResult.java
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
 * Date:           May 19, 2017
 * <p>
 * Time:           3:22:42 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentIdListResult extends BaseResult {
	private List<Integer> studentIds;

	public List<Integer> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Integer> studentIds) {
		this.studentIds = studentIds;
	}

	@Override
	public String toString() {
		return "StudentIdListResult [studentIds=" + studentIds + "]";
	}
	
}
