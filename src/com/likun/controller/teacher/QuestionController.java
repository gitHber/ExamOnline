/*
 * File name:          LoginController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.io.UnsupportedEncodingException;
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

import com.likun.bean.Page;
import com.likun.bean.Question;
import com.likun.bean.Student;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.QuestionListResult;
import com.likun.result.QuestionResult;
import com.likun.result.StudentResult;
import com.likun.result.TeacherResult;
import com.likun.service.QuestionService;
import com.likun.service.StudentService;
import com.likun.service.TeacherService;
import com.likun.util.PageInfo;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:32:53 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Controller
public class QuestionController {
	@Autowired
	QuestionService questionService;
	/**
	 * 获取需要的试题（用于添加到试卷）
	 * @param map
	 * @return
	 */
	@RequestMapping("/getQuestionNeedToAdd.do")
	public @ResponseBody QuestionListResult getQuestionNeedToAdd(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String diff = request.getParameter("diff");
		String questionType = request.getParameter("questionType");
		String chapter_start = request.getParameter("chapter_start");
		String chapter_end = request.getParameter("chapter_end");
		String courseId = request.getParameter("courseId");
		int pageCurrent = Integer.valueOf(request.getParameter("pageCurrent"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		String pageId = request.getParameter("pageId");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("diff", diff);
		map.put("questionType", questionType);
		map.put("chapter_start", chapter_start);
		map.put("chapter_end", chapter_end);
		map.put("courseId", courseId);
		map.put("pageId", pageId);
		int itemCount = questionService.getQuestionCountNeedToAdd(map);//获得总记录数
		map.put("itemIndex", (pageCurrent-1)*pageSize);
		map.put("pageSize", pageSize);
		List<Question> questions = questionService.getQuestionNeedToAdd(map);
		PageInfo pageInfo=new PageInfo();
		pageInfo.setItemCount(itemCount);
		pageInfo.setPageCurrent(pageCurrent);
		pageInfo.setPageCount((itemCount + pageSize - 1) / pageSize);
		pageInfo.setPageSize(pageSize);
		QuestionListResult rs=new QuestionListResult();
		if (questions!=null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setPage(pageInfo);
			rs.setQuestions(questions);
		}else{
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setQuestions(null);
		}
		return rs;
	}
	/**
	 * 根据试卷id与试题类型查找到相应试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getQuestionsByPageIdAndQuestionType.do")
	public @ResponseBody QuestionListResult getQuestionsByPageIdAndQuestionType(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pageId = request.getParameter("pageId");
		String questionType = request.getParameter("questionType");
		Map<String, String> map=new HashMap<String, String>();
		map.put("pageId", pageId);
		map.put("questionType", questionType);
		List<Question> questions = questionService.getQuestionsByPageIdAndQuestionType(map);
		QuestionListResult rs=new QuestionListResult();
		if (questions!=null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setQuestions(questions);
		}else{
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setQuestions(null);
		}
		return rs;
	}
	/**
	 * 获取所有的试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAllQuestion.do")
	public @ResponseBody QuestionListResult getAllQuestion(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Question> questions = questionService.getAllQuestion();
		QuestionListResult rs=new QuestionListResult();
		rs.setCode(200);
		rs.setMsg("获取成功！");
		rs.setQuestions(questions);
		return rs;
	}
	/**
	 * 更新试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateQuestionById.do")
	public @ResponseBody BaseResult updateQuestionById(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String courseId = request.getParameter("courseId");
		String content = request.getParameter("content");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String answer = request.getParameter("answer");
		String chapter = request.getParameter("chapter");
		String diff = request.getParameter("diff");
		String id = request.getParameter("id");
		Question question=new Question();
		question.setId(Integer.valueOf(id));
		question.setCourseId(Integer.valueOf(courseId));
		question.setContent(content);
		question.setOption1(option1);
		question.setOption2(option2);
		question.setOption3(option3);
		question.setOption4(option4);
		question.setAnswer(answer);
		question.setChapter(chapter);
		question.setDiff(Integer.valueOf(diff));
		question.setType(Integer.valueOf(type));
		int i = questionService.updateQuestionById(question);
		BaseResult rs=new BaseResult();
		if (i>0) {
			rs.setCode(200);
			rs.setMsg("更新成功！");
		}else{
			rs.setCode(101);
			rs.setMsg("更新失败！");
		}
		return rs;
	}
	/**
	 * 添加试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addQuestion.do")
	public @ResponseBody BaseResult addQuestion(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String courseId = request.getParameter("courseId");
		String content = request.getParameter("content");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String answer = request.getParameter("answer");
		String chapter = request.getParameter("chapter");
		String diff = request.getParameter("diff");
		Question question=new Question();
		question.setCourseId(Integer.valueOf(courseId));
		question.setContent(content);
		question.setOption1(option1);
		question.setOption2(option2);
		question.setOption3(option3);
		question.setOption4(option4);
		question.setAnswer(answer);
		question.setChapter(chapter);
		question.setDiff(Integer.valueOf(diff));
		question.setType(Integer.valueOf(type));
		int i = questionService.addQuestion(question);
		BaseResult rs=new BaseResult();
		if (i>0) {
			rs.setCode(200);
			rs.setMsg("添加成功！");
		}else{
			rs.setCode(101);
			rs.setMsg("添加失败！");
		}
		return rs;
	}
	/**
	 * 根据id获取试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getQuestionById.do")
	public @ResponseBody QuestionResult getQuestionById(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Question question = questionService.getQuestionById(Integer.valueOf(id));
		QuestionResult rs=new QuestionResult();
		if (question!=null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setQuestion(question);
		}else{
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setQuestion(null);
		}
		return rs;
	}
	/**
	 * 删除试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteQuestionById.do")
	public @ResponseBody BaseResult deleteQuestionById(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int i = questionService.deleteQuestionById(Integer.valueOf(id));
		BaseResult rs=new BaseResult();
		if (i>0) {
			rs.setCode(200);
			rs.setMsg("删除成功！");
		}else{
			rs.setCode(101);
			rs.setMsg("删除失败！");
		}
		return rs;
	}
	/**
	 * 获取所有的试题
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getQuestionByPageIndex.do")
	public @ResponseBody QuestionListResult getQuestionByPageIndex(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String paraName = names.nextElement();
			String paraVal = new String(request.getParameter(paraName).getBytes(),"UTF-8");
			if (!paraName.equals("pageCurrent")&&!paraName.equals("pageSize")) {
				map.put(paraName,paraVal);
			}
		}
		//分页查找所有学生信息
		String parameter = request.getParameter("pageCurrent");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent;
		if (parameter == null) {
			pageCurrent = 1;
		} else {
			pageCurrent = Integer.valueOf(parameter);
		}
		//得到试题信息的总数
		int itemCount = questionService.searchCount(map);
		//得到页数（除开前后页）
		int pageCount = 0;
		pageCount = (itemCount + pageSize - 1) / pageSize;
		//存入数据
		PageInfo page = new PageInfo();
		page.setItemCount(itemCount);
		page.setPageCount(pageCount);
		page.setPageSize(pageSize);
		page.setPageCurrent(pageCurrent);
		//处理并存入map集合中
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageCurrent - 1) * pageSize);
		List<Question> questions = questionService.searchQuestion(map);
		QuestionListResult rs = new QuestionListResult();
		rs.setCode(200);
		rs.setMsg("成功");
		rs.setQuestions(questions);
		rs.setPage(page);
		return rs;
	}
}
