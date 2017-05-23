/*
 * File name:          PageQuestionService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import com.likun.bean.PageQuestion;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 30, 2017
 * <p>
 * Time:           9:45:36 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface PageQuestionService {
	/**
	 * 向试卷中添加试题
	 * @param pageQuestion
	 * @return
	 */
	public int insertQuestionToPage(PageQuestion pageQuestion);
	/**
	 * 从试卷中删除试题
	 * @param pageQuestion
	 * @return
	 */
	public int deleteQuestionFromPage(PageQuestion pageQuestion);
	/**
	 * 删除试卷所有试题
	 * @param pageId
	 * @return
	 */
	public int deletePageQuestion(int id);
}
