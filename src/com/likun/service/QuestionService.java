/*
 * File name:          StudentService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.likun.bean.Question;
import com.likun.bean.Teacher;

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
public interface QuestionService {
	/**
	 * 获取需要的试题（用于添加到试卷）
	 * @param map
	 * @return
	 */
	public List<Question> getQuestionNeedToAdd(Map<String,Object> map);
	/**
	 * 获取需要的试题数量（用于添加到试卷）
	 * @param map
	 * @return
	 */
	public int getQuestionCountNeedToAdd(Map<String,Object> map);
	/**
	 * 根据试卷id与试题类型查找到相应试题
	 * @param map
	 * @return
	 */
	public List<Question> getQuestionsByPageIdAndQuestionType(Map<String,String> map);
	/**
	 * 得到所有的试题
	 * @return
	 */
	public List<Question> getAllQuestion();
	/**
	 * 更新试题
	 * @param question
	 * @return
	 */
	public int updateQuestionById(Question question);
	/**
	 * 添加试题
	 * @param question
	 * @return
	 */
	public int addQuestion(Question question);
	/**
	 * 根据id得到试题
	 * @param id
	 * @return
	 */
	public Question getQuestionById(int id);
	/**
	 * 根据试题id删除试题
	 * @param id
	 * @return
	 */
	public int deleteQuestionById(int id);
	/**
	 * 根据条件查询相应试题数量
	 * @param map
	 * @return
	 */
	public int searchCount(Map<String, Object> map);

	/**
	 * 根据条件查询相应试题集合
	 * @param map
	 * @return
	 */
	public List<Question> searchQuestion(Map<String,Object> map);
}
