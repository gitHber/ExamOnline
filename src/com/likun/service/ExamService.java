/*
 * File name:          ExamService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.likun.bean.Exam;
import com.likun.bean.ExamScore;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 1, 2017
 * <p>
 * Time:           10:36:16 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface ExamService {
	/**
	 * 根据考试id删除考试
	 */
	public int deleteExamById(int id);

	/**
	 * 更新考试信息
	 */
	public int updateExamById(Exam exam);

	/**
	 * 更新考试状态
	 */
	public int updateExamStatus(Map map);
	
	/**
	 * 新建考试并将自增的id存入参数对象
	 */
	public int addExam(Exam exam);

	/**
	 * 根据考试id得到考试信息
	 */
	public Exam getExamById(int id);

	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Exam> searchExam(Map<String, Object> map);

	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String, Object> map);

	/**
	 * 获取某个学生的考试
	 */
	public List<Exam> getStudentExam(Map map);
	/**
	 * 获取某个学生的考试数量
	 */
	public int getStudentExamNum(Map map);
	/**
	 * 获取某个学生的考试成绩
	 */
	public List<ExamScore> getStudentExamScore(Map map);

	/**
	 * 获取某个学生的考试成绩数量
	 */
	public int getStudentExamScoreNum(Map map);
}
