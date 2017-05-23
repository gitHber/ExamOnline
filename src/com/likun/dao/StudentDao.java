/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Exam;
import com.likun.bean.Student;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:28:31 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value="studentDao")
public class StudentDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 根据用户学号得到用户信息
	 * @return
	 */
	public Student getStudentById(String studentno) {
		Student student = sqlSessionTemplate.selectOne("com.likun.dao.StudentDao.getStudentById", studentno);
		return student;
	}
	/**
	 * 得到一个班的学生数量
	 */
	public int getStudentNumByGrade(String grade){
		int num = sqlSessionTemplate.selectOne("com.likun.dao.StudentDao.getStudentNumByGrade", grade);
		return num;
	}
	/**
	 * 添加学生 
	 */
	public int addStudent(Student student){
		int flag=sqlSessionTemplate.insert("com.likun.dao.StudentDao.addStudent", student);
		return flag;
	}
	/**
	 * 删除学生
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id){
		int flag=sqlSessionTemplate.insert("com.likun.dao.StudentDao.deleteStudent", id);
		return flag;
	}
	/**
	 * 修改学生信息
	 */
	public int upStudent(Student student){
		int flag=sqlSessionTemplate.update("com.likun.dao.StudentDao.upStudent", student);
		return flag;
	}
	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Student> searchStudent(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		List<Student> students = sqlSessionTemplate.selectList("com.likun.dao.StudentDao.searchStudent", map1);
		return students;
	}

	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		int count = sqlSessionTemplate.selectOne("com.likun.dao.StudentDao.searchCount", map1);
		return count;
	}
}
