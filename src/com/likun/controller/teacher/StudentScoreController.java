/*
 * File name:          ClassController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likun.bean.Class;
import com.likun.bean.Student;
import com.likun.bean.StudentScore;
import com.likun.result.BaseResult;
import com.likun.result.ClassListResult;
import com.likun.result.StudentIdListResult;
import com.likun.result.StudentScoreListResult;
import com.likun.service.ClassService;
import com.likun.service.StudentScoreService;
import com.likun.service.StudentService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:48:32 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Controller
public class StudentScoreController {
	@Autowired
	StudentScoreService studentScoreService;
	@Autowired
	StudentService studentSerivice;
	/**
	 * 填写学生考试信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addStudentScore.do")
	public @ResponseBody BaseResult addStudentScore(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student) request.getSession().getAttribute("student");
		String examId = request.getParameter("examId");
		StudentScore studentScore=new StudentScore();
		studentScore.setExamId(Integer.valueOf(examId));
		studentScore.setStuId(Integer.valueOf(student.getId()));
		BaseResult rs=new BaseResult();
		if (studentScoreService.addStudentScore(studentScore)>0){
			rs.setCode(200);
			rs.setMsg("成功！");
		}else{
			rs.setCode(100);
			rs.setMsg("失败！");
		}
		return rs;
	}
	/**
	 * 填写学生成绩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setScore.do")
	public @ResponseBody BaseResult setScore(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String stuId = request.getParameter("stuId");
		String examId = request.getParameter("examId");
		StudentScore studentScore=new StudentScore();
		studentScore.setExamId(Integer.valueOf(examId));
		studentScore.setStuId(Integer.valueOf(stuId));
		BaseResult rs=new BaseResult();
		if (studentScoreService.setScore(studentScore)>0){
			rs.setCode(200);
			rs.setMsg("成功！");
		}else{
			rs.setCode(100);
			rs.setMsg("失败！");
		}
		return rs;
	}
	/**
	 * 查询所有未评分的学生
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAllStudentWithNoCent.do")
	public @ResponseBody StudentIdListResult getAllStudentWithNoCent(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String examId = request.getParameter("examId");
		List<Integer> studentIds = studentScoreService.getAllStudentWithNoCent(Integer.valueOf(examId));
		StudentIdListResult rs=new StudentIdListResult();
		if (studentIds!=null){
			rs.setCode(200);
			rs.setStudentIds(studentIds);
			rs.setMsg("成功！");
		}else{
			rs.setCode(100);
			rs.setStudentIds(null);
			rs.setMsg("失败！");
		}
		return rs;
	}
	/**
	 * 得到一个班级某场考试的所有学生成绩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getScoreByGradeAndExam.do")
	public @ResponseBody StudentScoreListResult getScoreByGradeAndExam(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String grade = request.getParameter("grade");
		String examId = request.getParameter("examId");
		int studentNum = studentSerivice.getStudentNumByGrade(grade);
		Map<String,String> map =new HashMap<String,String>();
		map.put("grade", grade);
		map.put("examId", examId);
		List<StudentScore> scores = studentScoreService.getScoreByGradeAndExam(map);
		StudentScoreListResult rs=new StudentScoreListResult();
		if (scores!=null) {
			rs.setCode(200);
			rs.setMsg("成功！");
			rs.setScores(scores);
			rs.setStudentNum(studentNum);
		}else{
			rs.setCode(100);
			rs.setMsg("失败！");
		}
		return rs;
		
	}
}
