/*
 * File name:          IStudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Exam;
import com.likun.bean.Student;
import com.likun.dao.StudentDao;
import com.likun.service.StudentService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:31:26 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="studentservice")
public class IStudentService implements StudentService {
	@Autowired
	private StudentDao studentDao;
	/**
	 * 根据用户学号得到用户信息
	 * @return
	 */
	@Override
	public Student getStudentById(String studentno) {
		Student student = studentDao.getStudentById(studentno);
		return student;
	}
	/**
	 * 得到一个班的学生数量
	 */
	public int getStudentNumByGrade(String grade){
		return studentDao.getStudentNumByGrade(grade);
	}
	/**
	 * 添加学生 
	 */
	public int addStudent(Student student){
		return studentDao.addStudent(student);
	}
	/**
	 * 删除学生
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id){
		return studentDao.deleteStudent(id);
	}
	/**
	 * 修改学生信息
	 */
	public int upStudent(Student student){
		return studentDao.upStudent(student);
	}
	/**
	 * 上传用户头像
	 */
	public int upUserHead(Map map){
		return studentDao.upUserHead(map);
	}
	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Student> searchStudent(Map<String,Object> map){
		return studentDao.searchStudent(map);
	}
	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String,Object> map){
		return studentDao.searchCount(map);
	}
	
}
