/*
 * File name:          StudentResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

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
public class TeacherResult extends BaseResult {
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "TeacherResult [teacher=" + teacher + "]";
	}


}
