/*
 * File name:          StudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.List;

import com.likun.bean.QuestionType;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:30:18 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface QuestionTypeService {
	/**
	 * 得到所有的试题
	 * @return
	 */
	public List<QuestionType> getAllQuestionType();
}
