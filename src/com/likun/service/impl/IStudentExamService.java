/*
 * File name:          IStudentExamService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Question;
import com.likun.bean.StudentExam;
import com.likun.dao.StudentExamDao;
import com.likun.service.StudentExamService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 18, 2017
 * <p>
 * Time:           3:30:09 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="studentExamService")
public class IStudentExamService implements StudentExamService {
	@Autowired
	StudentExamDao studentExamDao;
	/**
	 * 打分
	 * @return
	 */
	public int upCent(StudentExam studentExam){
		return studentExamDao.upCent(studentExam);
	}
	/**
	 *  添加 
	 */
	public int insertStudentExam(StudentExam studentExam){
		return studentExamDao.insertStudentExam(studentExam);
	}
	/**
	 * 获取学生未评分的主观题 
	 */
	public List<Question> getQuestionNoScore(Map map){
		return studentExamDao.getQuestionNoScore(map);
	}
	/**
	 * 根据考试id获取学生答题数量
	 */
	public int getAnswerNumByExamId(Map map){
		return studentExamDao.getAnswerNumByExamId(map);
	}
}
