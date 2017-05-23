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
import com.likun.bean.PageQuestion;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.PageListResult;
import com.likun.result.PageResult;
import com.likun.service.PageQuestionService;
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
public class PageQuestionController {
	@Autowired
	PageQuestionService pageQuestionService;
	/**
	 * 向试卷中添加试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/insertQuestionToPage.do")
	public @ResponseBody BaseResult insertQuestionToPage(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pageId = request.getParameter("pageId");
		String questionId = request.getParameter("questionId");
		PageQuestion pageQuestion=new PageQuestion();
		pageQuestion.setPageId(Integer.valueOf(pageId));
		pageQuestion.setQuestionId(Integer.valueOf(questionId));
		int i = pageQuestionService.insertQuestionToPage(pageQuestion);
		BaseResult rs=new BaseResult();
		if(i>0){
			rs.setCode(200);
			rs.setMsg("添加成功！");
		}else{
			rs.setCode(101);
			rs.setMsg("添加失败！");
		}
		return rs;
	}
	/**
	 * 从试卷中删除试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteQuestionFromPage.do")
	public @ResponseBody BaseResult deleteQuestionFromPage(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pageId = request.getParameter("pageId");
		String questionId = request.getParameter("questionId");
		PageQuestion pageQuestion=new PageQuestion();
		pageQuestion.setPageId(Integer.valueOf(pageId));
		pageQuestion.setQuestionId(Integer.valueOf(questionId));
		int i = pageQuestionService.deleteQuestionFromPage(pageQuestion);
		BaseResult rs=new BaseResult();
		if(i>0){
			rs.setCode(200);
			rs.setMsg("删除成功！");
		}else{
			rs.setCode(101);
			rs.setMsg("删除失败！");
		}
		return rs;
	}
}
