/*
 * File name:          IStudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.QuestionType;
import com.likun.dao.QuestionTypeDao;
import com.likun.service.QuestionTypeService;

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
@Service(value="questionTypeService")
public class IQuestionTypeService implements QuestionTypeService {
	@Autowired
	private QuestionTypeDao questionTypeDao;
	/**
	 * 得到所有的试题
	 * @return
	 */

	@Override
	public List<QuestionType> getAllQuestionType() {
		return questionTypeDao.getAllQuestionType();
	}
	
}
