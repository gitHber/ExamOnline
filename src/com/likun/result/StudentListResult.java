/*
 * File name:          StudentListResult.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.result;

import java.util.List;

import com.likun.bean.Exam;
import com.likun.bean.Student;
import com.likun.util.PageInfo;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 22, 2017
 * <p>
 * Time:           10:39:50 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class StudentListResult extends BaseResult {
	private List<Student> students;
	private PageInfo page;
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "StudentListResult [students=" + students + ", page=" + page + "]";
	}
	
}
