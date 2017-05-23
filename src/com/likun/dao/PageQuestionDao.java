/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Page;
import com.likun.bean.PageQuestion;
import com.likun.bean.Question;

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
@Repository(value="pageQuestionDao")
public class PageQuestionDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 向试卷中添加试题
	 * @param pageQuestion
	 * @return
	 */
	public int insertQuestionToPage(PageQuestion pageQuestion){
		int i = sqlSessionTemplate.insert("com.likun.dao.PageQuestionDao.insertQuestionToPage", pageQuestion);
		return i;
	}
	/**
	 * 从试卷中删除试题
	 * @param pageQuestion
	 * @return
	 */
	public int deleteQuestionFromPage(PageQuestion pageQuestion){
		int i = sqlSessionTemplate.delete("com.likun.dao.PageQuestionDao.deleteQuestionFromPage", pageQuestion);
		return i;
	}
	/**
	 * 删除试卷所有试题
	 * @param pageId
	 * @return
	 */
	public int deletePageQuestion(int id){
		int delete = sqlSessionTemplate.delete("com.likun.dao.PageQuestionDao.deletePageQuestion",id);
		return delete;
	}
}
