/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Question;
import com.likun.bean.QuestionType;
import com.likun.bean.Teacher;

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
@Repository(value="questionTypeDao")
public class QuestionTypeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 得到所有的试题
	 * @return
	 */
	public List<QuestionType> getAllQuestionType() {
		List<QuestionType> questionTypes = sqlSessionTemplate.selectList("com.likun.dao.QuestionTypeDao.getAllQuestionType");
		return questionTypes;
	}
}
