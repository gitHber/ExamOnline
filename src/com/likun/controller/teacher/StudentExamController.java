/*
 * File name:          ClassController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.util.Enumeration;
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
import com.likun.bean.Question;
import com.likun.bean.Student;
import com.likun.bean.StudentExam;
import com.likun.result.BaseResult;
import com.likun.result.ClassListResult;
import com.likun.result.QuestionListResult;
import com.likun.service.ClassService;
import com.likun.service.StudentExamService;

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
public class StudentExamController {
	@Autowired
	StudentExamService studentExamService;
	/**
	 * 拿到用户未评分的题目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getQuestionNoScore.do")
	public @ResponseBody QuestionListResult getQuestionNoScore(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String stuId = request.getParameter("stuId");
		String examId = request.getParameter("examId");
		Map<String, String> map=new HashMap<String,String>();
		map.put("stuId", stuId);
		map.put("examId", examId);
		List<Question> questions = studentExamService.getQuestionNoScore(map);
		QuestionListResult rs=new QuestionListResult();
		if (questions!=null) {
			rs.setQuestions(questions);
			rs.setCode(200);
			rs.setMsg("获取成功！");
		}else{
			rs.setQuestions(null);
			rs.setCode(101);
			rs.setMsg("获取失败！");
		}
		return rs;
	}
	/**
	 * 题目评分
	 */
	@RequestMapping("/score.do")
	public  void score(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String stuId = request.getParameter("stuId");
		String examId = request.getParameter("examId");
		Enumeration<String> names = request.getParameterNames();
		StudentExam studentExam=new StudentExam();
		studentExam.setExamId(Integer.valueOf(examId));
		studentExam.setStuId(Integer.valueOf(stuId));
		
		while (names.hasMoreElements()) {
			String questionId = names.nextElement();
			if (!questionId.equals("stuId")&&!questionId.equals("examId")) {
				String cent = request.getParameter(questionId);
				studentExam.setQuestionId(Integer.valueOf(questionId));
				studentExam.setCent(Integer.valueOf(cent));
				studentExamService.upCent(studentExam);
			}
		}
	}
	/**
	 * 判断考生是否考过试
	 */
	@RequestMapping("/isExamed.do")
	public  @ResponseBody BaseResult isExamed(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student) request.getSession().getAttribute("student");
		String examId = request.getParameter("examId");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("examId", examId);
		map.put("stuId", student.getId()+"");
		int num = studentExamService.getAnswerNumByExamId(map);
		BaseResult rs=new BaseResult();
		if (num==0) {
			rs.setCode(200);
		}else {
			rs.setCode(101);
			rs.setMsg("你已经考过了！");
		}
		return rs;
	}
}
