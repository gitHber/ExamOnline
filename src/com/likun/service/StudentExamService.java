/*
 * File name:          StudentExamService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.List;
import java.util.Map;

import com.likun.bean.Question;
import com.likun.bean.StudentExam;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 18, 2017
 * <p>
 * Time:           3:29:10 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface StudentExamService {
	/**
	 * 打分
	 * @return
	 */
	public int upCent(StudentExam studentExam);
	/**
	 *  添加 
	 */
	public int insertStudentExam(StudentExam studentExam);
	/**
	 * 获取学生未评分的主观题 
	 */
	public List<Question> getQuestionNoScore(Map map);
	/**
	 * 根据考试id获取学生答题数量
	 */
	public int getAnswerNumByExamId(Map map);
}
