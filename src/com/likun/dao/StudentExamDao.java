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

import com.likun.bean.Question;
import com.likun.bean.StudentExam;

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
@Repository(value="studentExamDao")
public class StudentExamDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 打分
	 * @return
	 */
	public int upCent(StudentExam studentExam){
		return sqlSessionTemplate.update("com.likun.dao.StudentExamDao.upCent", studentExam);
	}
	/**
	 *  添加 
	 */
	public int insertStudentExam(StudentExam studentExam){
		return sqlSessionTemplate.insert("com.likun.dao.StudentExamDao.insertStudentExam",studentExam);
	}
	/**
	 * 获取学生未评分的主观题 
	 */
	public List<Question> getQuestionNoScore(Map map){
		List<Question> selectList = sqlSessionTemplate.selectList("com.likun.dao.StudentExamDao.getQuestionNoScore", map);
		return selectList;
	}
	/**
	 * 根据考试id获取学生答题数量
	 */
	public int getAnswerNumByExamId(Map map){
		int num = sqlSessionTemplate.selectOne("com.likun.dao.StudentExamDao.getAnswerNumByExamId", map);
		return num;
	}
}
