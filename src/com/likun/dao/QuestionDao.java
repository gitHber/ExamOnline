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
@Repository(value="questionDao")
public class QuestionDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 获取需要的试题（用于添加到试卷）
	 * @param map
	 * @return
	 */
	public List<Question> getQuestionNeedToAdd(Map<String,Object> map){
		List<Question> questions = sqlSessionTemplate.selectList("com.likun.dao.QuestionDao.getQuestionNeedToAdd", map);
		return questions;
	}
	/**
	 * 获取需要的试题数量（用于添加到试卷）
	 * @param map
	 * @return
	 */
	public int getQuestionCountNeedToAdd(Map<String,Object> map){
		int num = sqlSessionTemplate.selectOne("com.likun.dao.QuestionDao.getQuestionCountNeedToAdd", map);
		return num;
	}
	/**
	 * 根据试卷id与试题类型查找到相应试题
	 * @param map
	 * @return
	 */
	public List<Question> getQuestionsByPageIdAndQuestionType(Map<String,String> map){
		List<Question> questions = sqlSessionTemplate.selectList("com.likun.dao.QuestionDao.getQuestionsByPageIdAndQuestionType", map);
		return questions;
	}
	/**
	 * 得到所有的试题
	 * @return
	 */
	public List<Question> getAllQuestion() {
		List<Question> questions = sqlSessionTemplate.selectList("com.likun.dao.QuestionDao.getAllQuestion");
		return questions;
	}
	/**
	 * 更新试题
	 * @param question
	 * @return
	 */
	public int updateQuestionById(Question question){
		int update = sqlSessionTemplate.update("com.likun.dao.QuestionDao.updateQuestionById",question);
		return update;
	}
	/**
	 * 添加试题
	 * @param question
	 * @return
	 */
	public int addQuestion(Question question){
		int insert = sqlSessionTemplate.insert("com.likun.dao.QuestionDao.addQuestion",question);
		return insert;
	}
	/**
	 * 根据id得到试题
	 * @param id
	 * @return
	 */
	public Question getQuestionById(int id){
		Question question = sqlSessionTemplate.selectOne("com.likun.dao.QuestionDao.getQuestionById", id);
		return question;
	}
	/**
	 * 根据试题id删除试题
	 * @param id
	 * @return
	 */
	public int deleteQuestionById(int id){
		int delete = sqlSessionTemplate.delete("com.likun.dao.QuestionDao.deleteQuestionById",id);
		return delete;
	}
	/**
	 * 根据条件查询相应试题数量
	 * @param map
	 * @return
	 */
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		return sqlSessionTemplate.selectOne("com.likun.dao.QuestionDao.searchCount", map1);
	}

	/**
	 * 根据条件查询相应试题集合
	 * @param map
	 * @return
	 */
	public List<Question> searchQuestion(Map<String,Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		return sqlSessionTemplate.selectList("com.likun.dao.QuestionDao.searchQuestion", map1);
	}
}
