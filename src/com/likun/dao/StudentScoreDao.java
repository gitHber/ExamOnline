/*
 * File name:          ClassDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.StudentScore;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:41:17 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value = "studentScoreDao")
public class StudentScoreDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 添加学生考试成绩
	 */
	public int addStudentScore(StudentScore studentScore) {
		return sqlSessionTemplate.insert("com.likun.dao.StudentScoreDao.addStudentScore", studentScore);
	}

	/**
	 * 填写学生成绩 
	 */
	public int setScore(StudentScore studentScore) {
		return sqlSessionTemplate.update("com.likun.dao.StudentScoreDao.setScore", studentScore);
	}

	/**
	 * 获取所有未评分的学生
	 */
	public List<Integer> getAllStudentWithNoCent(int examId) {
		List<Integer> selectList = sqlSessionTemplate.selectList(
				"com.likun.dao.StudentScoreDao.getAllStudentWithNoCent", examId);
		return selectList;
	}

	/**
	 * 得到某个班级某场考试的所有学生成绩
	 */
	public List<StudentScore> getScoreByGradeAndExam(Map map) {
		List<StudentScore> selectList = sqlSessionTemplate.selectList(
				"com.likun.dao.StudentScoreDao.getScoreByGradeAndExam", map);
		return selectList;
	}

	/**
	 * 得到某个班级某场考试的所有学生成绩数量 
	 */
	public int getScoreCountByGradeAndExam(Map map) {
		int num = sqlSessionTemplate.selectOne("com.likun.dao.StudentScoreDao.getScoreCountByGradeAndExam", map);
		return num;
	}
}
