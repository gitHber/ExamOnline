/*
 * File name:          IPageQuestionService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.PageQuestion;
import com.likun.dao.PageQuestionDao;
import com.likun.service.PageQuestionService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 30, 2017
 * <p>
 * Time:           9:46:33 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="pageQuestionService")
public class IPageQuestionService implements PageQuestionService {
	@Autowired
	PageQuestionDao pageQuestionDao;
	/**
	 * 向试卷中添加试题
	 * @param pageQuestion
	 * @return
	 */
	public int insertQuestionToPage(PageQuestion pageQuestion){
		return pageQuestionDao.insertQuestionToPage(pageQuestion);
	}
	/**
	 * 从试卷中删除试题
	 * @param pageQuestion
	 * @return
	 */
	public int deleteQuestionFromPage(PageQuestion pageQuestion){
		return pageQuestionDao.deleteQuestionFromPage(pageQuestion);
	}
	/**
	 * 删除试卷所有试题
	 * @param pageId
	 * @return
	 */
	public int deletePageQuestion(int id){
		return pageQuestionDao.deletePageQuestion(id);
	}
}
