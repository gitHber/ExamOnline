/*
 * File name:          LoginController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likun.bean.Page;
import com.likun.bean.PageQuestion;
import com.likun.bean.Question;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.PageListResult;
import com.likun.result.PageResult;
import com.likun.result.PracticeQuestionListResult;
import com.likun.result.QuestionListResult;
import com.likun.service.PageQuestionService;
import com.likun.service.PageService;
import com.likun.service.QuestionService;
import com.likun.util.PageInfo;
import com.likun.util.RandomList;

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
public class PageController {
	@Autowired
	PageService pageService;
	@Autowired
	QuestionService questionService;
	@Autowired
	PageQuestionService pageQuestionService;
	/**
	 * 自动组卷
	 */
	@RequestMapping("/generatePage.do")
	public @ResponseBody BaseResult generatePage(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int singleNum = Integer.valueOf(request.getParameter("singleNum"));
		int multipleNum = Integer.valueOf(request.getParameter("multipleNum"));
		int blankNum = Integer.valueOf(request.getParameter("blankNum"));
		int subjectiveNum = Integer.valueOf(request.getParameter("subjectiveNum"));
		String pageId = request.getParameter("pageId");
		//获得试卷信息
		Page page = pageService.getPageById(Integer.valueOf(pageId));
		//设置所需条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("diff", page.getDiff());
		map.put("chapter_start", page.getChapter_start());
		map.put("chapter_end", page.getChapter_end());
		map.put("courseId", page.getCourseId());
		map.put("pageId", pageId);
		//获得所需试题
		map.put("questionType", 1);
		List<Question> singleQuestions = questionService.getQuestionNeedToAdd(map);//单选题
		map.put("questionType", 2);
		List<Question> multipleQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		map.put("questionType", 3);
		List<Question> blankQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		map.put("questionType", 4);
		List<Question> subjectiveQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		System.err.println("====================================");
		System.err.println("");
		System.err.println("====================================");
		BaseResult rs=new BaseResult();
		List<Integer> singleList;
		List<Integer> multipleList;
		List<Integer> blankList;
		List<Integer> subjectiveList;
		//生成随机序列
		if (singleNum>singleQuestions.size()) {
			rs.setCode(100);
			rs.setMsg("单选题试题数量不足，请添加足够试题再自动生成！");
			pageService.deletePageById(Integer.valueOf(pageId));
		}else if (multipleNum>multipleQuestions.size()) {
			rs.setCode(101);
			rs.setMsg("多选题试题数量不足，请添加足够试题再自动生成！");
			pageService.deletePageById(Integer.valueOf(pageId));
		}else if (blankNum>blankQuestions.size()) {
			rs.setCode(102);
			rs.setMsg("填空题试题数量不足，请添加足够试题再自动生成！");
			pageService.deletePageById(Integer.valueOf(pageId));
		}else if (subjectiveNum>subjectiveQuestions.size()) {
			rs.setCode(103);
			rs.setMsg("简答题试题数量不足，请添加足够试题再自动生成！");
			pageService.deletePageById(Integer.valueOf(pageId));
		}else{
			singleList = RandomList.generateRandomList(singleQuestions, singleNum);
			multipleList = RandomList.generateRandomList(multipleQuestions, multipleNum);
			blankList = RandomList.generateRandomList(blankQuestions, blankNum);
			subjectiveList = RandomList.generateRandomList(subjectiveQuestions, subjectiveNum);
			for (Integer integer : singleList) {
				PageQuestion pageQuestion=new PageQuestion();
				pageQuestion.setPageId(Integer.valueOf(pageId));
				pageQuestion.setQuestionId(integer);
				pageQuestionService.insertQuestionToPage(pageQuestion);
			}
			for (Integer integer : multipleList) {
				PageQuestion pageQuestion=new PageQuestion();
				pageQuestion.setPageId(Integer.valueOf(pageId));
				pageQuestion.setQuestionId(integer);
				pageQuestionService.insertQuestionToPage(pageQuestion);
			}
			for (Integer integer : blankList) {
				PageQuestion pageQuestion=new PageQuestion();
				pageQuestion.setPageId(Integer.valueOf(pageId));
				pageQuestion.setQuestionId(integer);
				pageQuestionService.insertQuestionToPage(pageQuestion);
			}
			for (Integer integer : subjectiveList) {
				PageQuestion pageQuestion=new PageQuestion();
				pageQuestion.setPageId(Integer.valueOf(pageId));
				pageQuestion.setQuestionId(integer);
				pageQuestionService.insertQuestionToPage(pageQuestion);
			}
			rs.setCode(200);
			rs.setMsg("生成成功！");
		}
		return rs;
	}

	
	/**
	 * 自动生成练习题
	 */
	@RequestMapping("/generatePractice.do")
	public @ResponseBody PracticeQuestionListResult generatePractice(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int singleNum = Integer.valueOf(request.getParameter("singleNum"));
		int multipleNum = Integer.valueOf(request.getParameter("multipleNum"));
		int blankNum = Integer.valueOf(request.getParameter("blankNum"));
		int subjectiveNum = Integer.valueOf(request.getParameter("subjectiveNum"));
		String courseId = request.getParameter("courseId");
		String chapter_start = request.getParameter("chapter_start");
		String chapter_end = request.getParameter("chapter_end");
		String diff = request.getParameter("diff");
		
		//设置所需条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("diff", diff);
		map.put("chapter_start", chapter_start);
		map.put("chapter_end", chapter_end);
		map.put("courseId", courseId);
		//获得所需试题
		map.put("questionType", 1);
		List<Question> singleQuestions = questionService.getQuestionNeedToAdd(map);//单选题
		map.put("questionType", 2);
		List<Question> multipleQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		map.put("questionType", 3);
		List<Question> blankQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		map.put("questionType", 4);
		List<Question> subjectiveQuestions = questionService.getQuestionNeedToAdd(map);//多选题
		System.err.println("====================================");
		System.err.println("");
		System.err.println("====================================");
		PracticeQuestionListResult rs=new PracticeQuestionListResult();
		List<Integer> singleList;
		List<Integer> multipleList;
		List<Integer> blankList;
		List<Integer> subjectiveList;
		//生成随机序列
		if (singleNum>singleQuestions.size()) {
			rs.setCode(100);
			rs.setMsg("单选题试题数量不足，请添加足够试题再自动生成！");
		}else if (multipleNum>multipleQuestions.size()) {
			rs.setCode(101);
			rs.setMsg("多选题试题数量不足，请添加足够试题再自动生成！");
		}else if (blankNum>blankQuestions.size()) {
			rs.setCode(102);
			rs.setMsg("填空题试题数量不足，请添加足够试题再自动生成！");
		}else if (subjectiveNum>subjectiveQuestions.size()) {
			rs.setCode(103);
			rs.setMsg("简答题试题数量不足，请添加足够试题再自动生成！");
		}else{
			singleList = RandomList.generateRandomList(singleQuestions, singleNum);
			multipleList = RandomList.generateRandomList(multipleQuestions, multipleNum);
			blankList = RandomList.generateRandomList(blankQuestions, blankNum);
			subjectiveList = RandomList.generateRandomList(subjectiveQuestions, subjectiveNum);
			
			List<Question> singleQuestion=new ArrayList<Question>();
			List<Question> multipleQuestion=new ArrayList<Question>();
			List<Question> blankQuestion=new ArrayList<Question>();
			List<Question> subjectiveQuestion=new ArrayList<Question>();
			for (Integer integer : singleList) {
				Question question= questionService.getQuestionById(integer);
				singleQuestion.add(question);
			}
			for (Integer integer : multipleList) {
				Question question= questionService.getQuestionById(integer);
				multipleQuestion.add(question);
			}
			for (Integer integer : blankList) {
				Question question= questionService.getQuestionById(integer);
				blankQuestion.add(question);
			}
			for (Integer integer : subjectiveList) {
				Question question= questionService.getQuestionById(integer);
				subjectiveQuestion.add(question);
			}
			rs.setSingleQuestions(singleQuestion);
			rs.setMultipleQuestions(multipleQuestion);
			rs.setBlankQuestions(blankQuestion);
			rs.setSubjectiveQuestions(subjectiveQuestion);
			rs.setCode(200);
			rs.setMsg("生成成功！");
		}
		return rs;
	}
	/**
	 * 得到试卷当前总分数
	 * @return
	 */
	@RequestMapping("/getCurrCentByPageId.do")
	public @ResponseBody BaseResult getCurrCentByPageId(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pageId = request.getParameter("pageId");
		int currCent = pageService.getCurrCentByPageId(pageId);
		BaseResult rs = new BaseResult();
		rs.setCode(200);
		rs.setMsg(currCent + "");
		return rs;
	}

	/**
	 * 更新试卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatePageById.do")
	public @ResponseBody BaseResult updatePageById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String courseId = request.getParameter("courseId");
		String total_cent = request.getParameter("total_cent");
		String chapter_start = request.getParameter("chapter_start");
		String chapter_end = request.getParameter("chapter_end");
		String teacherId = request.getParameter("teacherId");
		String ans_time = request.getParameter("ans_time");
		String page_type = request.getParameter("page_type");
		String assembly_type = request.getParameter("assembly_type");
		String diff = request.getParameter("diff");
		String id = request.getParameter("id");
		Page page = new Page();
		page.setId(Integer.valueOf(id));
		page.setName(name);
		page.setCourseId(Integer.valueOf(courseId));
		page.setTotal_cent(Integer.valueOf(total_cent));
		page.setChapter_start(Integer.valueOf(chapter_start));
		page.setChapter_end(Integer.valueOf(chapter_end));
		page.setTeacherId(Integer.valueOf(teacherId));
		page.setAns_time(Integer.valueOf(ans_time));
		page.setPage_type(Integer.valueOf(page_type));
		page.setAssembly_type(Integer.valueOf(assembly_type));
		page.setDiff(Integer.valueOf(diff));
		int i = pageService.updatePageById(page);
		BaseResult rs = new BaseResult();
		if (i > 0) {
			rs.setCode(200);
			rs.setMsg("更新成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("更新失败！");
		}
		return rs;
	}

	/**
	 * 添加试卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addPage.do")
	public @ResponseBody PageResult addPage(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String courseId = request.getParameter("courseId");
		String total_cent = request.getParameter("total_cent");
		String chapter_start = request.getParameter("chapter_start");
		String chapter_end = request.getParameter("chapter_end");
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		//int teacherId = teacher.getId();    //完成之后再解开注释，目前测试为1
		int teacherId = 1;
		String ans_time = request.getParameter("ans_time");
		String page_type = request.getParameter("page_type");
		String assembly_type = request.getParameter("assembly_type");
		String diff = request.getParameter("diff");
		Page page = new Page();
		page.setName(name);
		page.setCourseId(Integer.valueOf(courseId));
		page.setTotal_cent(Integer.valueOf(total_cent));
		page.setChapter_start(Integer.valueOf(chapter_start));
		page.setChapter_end(Integer.valueOf(chapter_end));
		page.setTeacherId(teacherId);
		page.setAns_time(Integer.valueOf(ans_time));
		page.setPage_type(Integer.valueOf(page_type));
		page.setAssembly_type(Integer.valueOf(assembly_type));
		page.setDiff(Integer.valueOf(diff));
		int i = pageService.addPage(page);
		PageResult rs = new PageResult();
		if (i > 0) {
			rs.setPage(page);
			rs.setCode(200);
			rs.setMsg("添加成功！");
		} else {
			rs.setPage(page);
			rs.setCode(101);
			rs.setMsg("添加失败！");
		}
		return rs;
	}

	/**
	 * 根据id获取试卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPageById.do")
	public @ResponseBody PageResult getPageById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Page page = pageService.getPageById(Integer.valueOf(id));
		PageResult rs = new PageResult();
		if (page != null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setPage(page);
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setPage(null);
		}
		return rs;
	}

	/**
	 * 删除试卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deletePageById.do")
	public @ResponseBody BaseResult deletePageById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int i = pageService.deletePageById(Integer.valueOf(id));
		int j=pageQuestionService.deletePageQuestion(Integer.valueOf(id));
		BaseResult rs = new BaseResult();
		if (i > 0) {
			rs.setCode(200);
			rs.setMsg("删除成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("删除失败！");
		}
		return rs;
	}

	/**
	 * 获取所有的试卷分页
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getPageByPageIndex.do")
	public @ResponseBody PageListResult getPageByPageIndex(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String paraName = names.nextElement();
			String paraVal = new String(request.getParameter(paraName).getBytes(), "UTF-8");
			if (!paraName.equals("pageCurrent") && !paraName.equals("pageSize")) {
				map.put(paraName, paraVal);
			}
		}
		//分页查找所有试卷信息
		String parameter = request.getParameter("pageCurrent");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent;
		if (parameter == null) {
			pageCurrent = 1;
		} else {
			pageCurrent = Integer.valueOf(parameter);
		}
		//得到试题信息的总数
		int itemCount = pageService.searchCount(map);
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
		List<Page> pages = pageService.searchPage(map);
		PageListResult rs = new PageListResult();
		rs.setCode(200);
		rs.setMsg("成功");
		rs.setPages(pages);
		rs.setPage(page);
		return rs;
	}
	/**
	 * 获取所有的试卷
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getAllPage.do")
	public @ResponseBody PageListResult getAllPage(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Page> pages = pageService.getAllPage();
		PageListResult rs = new PageListResult();
		rs.setCode(200);
		rs.setMsg("成功");
		rs.setPages(pages);
		rs.setPage(null);
		return rs;
	}
}
