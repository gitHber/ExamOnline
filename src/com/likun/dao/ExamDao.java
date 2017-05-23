/*
 * File name:          ExamDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Exam;
import com.likun.bean.ExamScore;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 1, 2017
 * <p>
 * Time:           10:23:08 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value = "examDao")
public class ExamDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 根据考试id删除考试
	 */
	public int deleteExamById(int id) {
		int delete = sqlSessionTemplate.delete("com.likun.dao.ExamDao.deleteExamById", id);
		return delete;
	}

	/**
	 * 更新考试信息
	 */
	public int updateExamById(Exam exam) {
		int update = sqlSessionTemplate.update("com.likun.dao.ExamDao.updateExamById", exam);
		return update;
	}

	/**
	 * 更新考试状态
	 */
	public int updateExamStatus(Map map) {
		return sqlSessionTemplate.update("com.likun.dao.ExamDao.updateExamStatus", map);
	}

	/**
	 * 新建考试并将自增的id存入参数对象
	 */
	public int addExam(Exam exam) {
		int insert = sqlSessionTemplate.insert("com.likun.dao.ExamDao.addExam", exam);
		return insert;
	}

	/**
	 * 根据考试id得到考试信息
	 */
	public Exam getExamById(int id) {
		Exam exam = sqlSessionTemplate.selectOne("com.likun.dao.ExamDao.getExamById", id);
		return exam;
	}

	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Exam> searchExam(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		List<Exam> exams = sqlSessionTemplate.selectList("com.likun.dao.ExamDao.searchExam", map1);
		return exams;
	}

	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		int count = sqlSessionTemplate.selectOne("com.likun.dao.ExamDao.searchCount", map1);
		return count;
	}

	/**
	 * 获取某个学生的考试
	 */
	public List<Exam> getStudentExam(Map map) {
		List<Exam> exams = sqlSessionTemplate.selectList("com.likun.dao.ExamDao.getStudentExam", map);
		return exams;
	}

	/**
	 * 获取某个学生的考试
	 */
	public int getStudentExamNum(Map map) {
		int num = sqlSessionTemplate.selectOne("com.likun.dao.ExamDao.getStudentExamNum", map);
		return num;
	}
	/**
	 * 获取某个学生的考试成绩
	 */
	public List<ExamScore> getStudentExamScore(Map map) {
		List<ExamScore> exams = sqlSessionTemplate.selectList("com.likun.dao.ExamDao.getStudentExamScore", map);
		return exams;
	}

	/**
	 * 获取某个学生的考试成绩数量
	 */
	public int getStudentExamScoreNum(Map map) {
		int num = sqlSessionTemplate.selectOne("com.likun.dao.ExamDao.getStudentExamScoreNum", map);
		return num;
	}
}
