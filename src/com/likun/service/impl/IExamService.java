/*
 * File name:          IExamService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likun.bean.Exam;
import com.likun.bean.ExamScore;
import com.likun.dao.ExamDao;
import com.likun.service.ExamService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 1, 2017
 * <p>
 * Time:           10:37:18 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="examService")
public class IExamService implements ExamService {
	@Autowired
	ExamDao examDao;
	/**
	 * 根据考试id删除考试
	 */
	public int deleteExamById(int id){
		return examDao.deleteExamById(id);
	}
	/**
	 * 更新考试信息
	 */
	public int updateExamById(Exam exam){
		return examDao.updateExamById(exam);
	}
	
	/**
	 * 更新考试状态
	 */
	public int updateExamStatus(Map map){
		return examDao.updateExamStatus(map);
	}
	/**
	 * 新建考试并将自增的id存入参数对象
	 */
	public int addExam(Exam exam){
		return examDao.addExam(exam);
	}
	/**
	 * 根据考试id得到考试信息
	 */
	public Exam getExamById(int id){
		return examDao.getExamById(id);
	}
	/**
	 * 根据条件查询要显示的数据 (分页)
	 */
	public List<Exam> searchExam(Map<String,Object> map){
		return examDao.searchExam(map);
	}
	/**
	 * 符合条件的数据一共有多少条 
	 */
	public int searchCount(Map<String,Object> map){
		return examDao.searchCount(map);
	}
	/**
	 * 获取某个学生的考试
	 */
	@Override
	public List<Exam> getStudentExam(Map map) {
		return examDao.getStudentExam(map);
	}
	/**
	 * 获取某个学生的考试数量
	 */
	@Override
	public int getStudentExamNum(Map map) {
		return examDao.getStudentExamNum(map);
	}
	/**
	 * 获取某个学生的考试成绩
	 */
	public List<ExamScore> getStudentExamScore(Map map) {
		return examDao.getStudentExamScore(map);
	}

	/**
	 * 获取某个学生的考试成绩数量
	 */
	public int getStudentExamScoreNum(Map map) {
		return examDao.getStudentExamScoreNum(map);
	}
}
