/*
 * File name:          IStudentScoreService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.StudentScore;
import com.likun.dao.StudentScoreDao;
import com.likun.service.StudentScoreService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 19, 2017
 * <p>
 * Time:           2:56:31 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="studentScoreService")
public class IStudentScoreService implements StudentScoreService {
	@Autowired
	StudentScoreDao studentScoreDao;
	/**
	 * 添加学生考试成绩
	 */
	public int addStudentScore(StudentScore studentScore){
		return studentScoreDao.addStudentScore(studentScore);
	}
	 /**
	  * 填写学生成绩 
	  */
	public int setScore(StudentScore studentScore){
		return studentScoreDao.setScore(studentScore);
	}
	/**
	 * 获取所有未评分的学生
	 */
	public List<Integer> getAllStudentWithNoCent(int examId){
		return studentScoreDao.getAllStudentWithNoCent(examId);
	}
	/**
	 * 得到某个班级某场考试的所有学生成绩
	 */
	public List<StudentScore> getScoreByGradeAndExam(Map map) {
		return studentScoreDao.getScoreByGradeAndExam(map);
	}

	/**
	 * 得到某个班级某场考试的所有学生成绩数量 
	 */
	public int getScoreCountByGradeAndExam(Map map) {
		return studentScoreDao.getScoreCountByGradeAndExam(map);
	}
}
