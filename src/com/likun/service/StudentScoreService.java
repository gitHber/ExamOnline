/*
 * File name:          StudentScoreService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.List;
import java.util.Map;

import com.likun.bean.StudentScore;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 19, 2017
 * <p>
 * Time:           2:55:48 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface StudentScoreService {
	/**
	 * 添加学生考试成绩
	 */
	public int addStudentScore(StudentScore studentScore);
	 /**
	  * 填写学生成绩 
	  */
	public int setScore(StudentScore studentScore);
	/**
	 * 获取所有未评分的学生
	 */
	public List<Integer> getAllStudentWithNoCent(int examId);
	/**
	 * 得到某个班级某场考试的所有学生成绩
	 */
	public List<StudentScore> getScoreByGradeAndExam(Map map);

	/**
	 * 得到某个班级某场考试的所有学生成绩数量 
	 */
	public int getScoreCountByGradeAndExam(Map map); 
}
