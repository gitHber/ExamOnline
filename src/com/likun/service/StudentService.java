/*
 * File name:          StudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.List;
import java.util.Map;

import com.likun.bean.Exam;
import com.likun.bean.Student;
import com.mchange.v2.async.StrandedTaskReporting;

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
public interface StudentService {
	/**
	 * 根据用户学号得到用户信息
	 * @return
	 */
	public Student getStudentById(String studentno);
	/**
	 * 得到一个班的学生数量
	 */
	public int getStudentNumByGrade(String grade);
	/**
	 * 添加学生 
	 */
	public int addStudent(Student student);
	/**
	 * 删除学生
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id);
	/**
	 * 修改学生信息
	 */
	public int upStudent(Student student);
	/**
	 * 上传用户头像
	 */
	public int upUserHead(Map map);
	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Student> searchStudent(Map<String, Object> map);

	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String, Object> map);
}
