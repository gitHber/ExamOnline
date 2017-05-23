/*
 * File name:          LoginController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likun.bean.Exam;
import com.likun.bean.ExamPage;
import com.likun.bean.ExamScore;
import com.likun.bean.Page;
import com.likun.bean.PageQuestion;
import com.likun.bean.Question;
import com.likun.bean.Student;
import com.likun.bean.StudentExam;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.ExamListResult;
import com.likun.result.ExamPageListResult;
import com.likun.result.ExamPageResult;
import com.likun.result.ExamResult;
import com.likun.result.ExamScoreListResult;
import com.likun.result.PageListResult;
import com.likun.result.PageResult;
import com.likun.service.ExamService;
import com.likun.service.PageQuestionService;
import com.likun.service.PageService;
import com.likun.service.QuestionService;
import com.likun.service.StudentExamService;
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
public class ExamController {
	@Autowired
	ExamService examService;
	@Autowired
	PageService pageService;
	@Autowired
	QuestionService questionService;
	@Autowired
	StudentExamService studentExamService;
	/**
	 * 更新考试信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/updateExamById.do")
	public @ResponseBody BaseResult updateExamById(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String pageId = request.getParameter("pageId");
		String time = request.getParameter("time");
		String grade = request.getParameter("grade");
		String id = request.getParameter("id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Exam exam = new Exam();
		exam.setId(Integer.valueOf(id));
		exam.setName(name);
		exam.setPageId(Integer.valueOf(pageId));
		exam.setGrade(grade);
		exam.setTime(sdf.parse(time));
		int i = examService.updateExamById(exam);
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
	 * 更新考试状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/updateExamStatus.do")
	public @ResponseBody BaseResult updateExamStatus(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String flag = request.getParameter("flag");
		String id = request.getParameter("id");
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", id);
		map.put("flag", flag);
		int i = examService.updateExamStatus(map);
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
	 * 新建考试
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/addExam.do")
	public @ResponseBody ExamResult addExam(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
		String name = request.getParameter("name");
		String pageId = request.getParameter("pageId");
		String time = request.getParameter("time");
		String grade = request.getParameter("grade");
		System.err.println("============================" + time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Exam exam = new Exam();
		exam.setName(name);
		exam.setPageId(Integer.valueOf(pageId));
		exam.setGrade(grade);
		exam.setTime(sdf.parse(time));
		exam.setTeacherId(teacher.getId());
		int i = examService.addExam(exam);
		ExamResult rs = new ExamResult();
		if (i > 0) {
			rs.setExam(exam);
			rs.setCode(200);
			rs.setMsg("添加成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("添加失败！");
		}
		return rs;
	}

	/**
	 * 根据id获取考试信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getExamById.do")
	public @ResponseBody ExamResult getExamById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Exam exam = examService.getExamById(Integer.valueOf(id));
		ExamResult rs = new ExamResult();
		if (exam != null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setExam(exam);
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setExam(null);
		}
		return rs;
	}

	/**
	 * 根据id获取考试信息及试卷信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getExamPageById.do")
	public @ResponseBody ExamPageResult getExamPageById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Exam exam = examService.getExamById(Integer.valueOf(id));
		Page page = pageService.getPageById(exam.getPageId());
		ExamPage examPage = new ExamPage();
		examPage.setFlag(exam.getFlag());
		examPage.setGrade(exam.getGrade());
		examPage.setId(exam.getId());
		examPage.setName(exam.getName());
		examPage.setPageId(exam.getPageId());
		examPage.setTime(exam.getTime());
		examPage.setPage(page);
		ExamPageResult rs = new ExamPageResult();
		if (exam != null) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setExamPage(examPage);
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
			rs.setExamPage(null);
		}
		return rs;
	}
	/**
	 * 获取考试信息及试卷信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getExamPage.do")
	public @ResponseBody ExamPageListResult getExamPage(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Exam> exams = examService.searchExam(map);
		List<ExamPage> examPages=new ArrayList<ExamPage>();
		for (int i=0;i<exams.size();i++) {
			Exam exam=exams.get(i);
			Date time = exam.getTime();
			Date date=new Date();
			if (date.after(time)) {
				Page page = pageService.getPageById(exam.getPageId());
				int ans_time = page.getAns_time();
				int hour=ans_time/60;
				int min=ans_time%60;
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(time);
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+hour);
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+min);
				Date time2 = calendar.getTime();
				if (date.before(time2)) {
					ExamPage examPage=new ExamPage();
					examPage.setFlag(exam.getFlag());
					examPage.setGrade(exam.getGrade());
					examPage.setId(exam.getId());
					examPage.setName(exam.getName());
					examPage.setPage(page);
					examPage.setPageId(exam.getPageId());
					examPage.setTeacherId(exam.getTeacherId());
					examPage.setTime(time);
					examPages.add(examPage);
				}
				}
		}
		ExamPageListResult rs = new ExamPageListResult();
			rs.setCode(200);
			rs.setMsg("获取成功！");
			rs.setExamPages(examPages);
		return rs;
	}
	/**
	 * 根据学生id获取考试信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getStudentExam.do")
	public @ResponseBody ExamListResult getStudentExam(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student)request.getSession().getAttribute("student");
		//分页查找所有考试信息
		String parameter = request.getParameter("pageCurrent");
		String flag=request.getParameter("flag");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent;
		if (parameter == null) {
			pageCurrent = 1;
		} else {
			pageCurrent = Integer.valueOf(parameter);
		}
		
		Map map=new HashMap();
		map.put("pageSize", pageSize);
		map.put("itemIndex", (pageCurrent - 1) * pageSize);
		map.put("flag", flag);
		map.put("id", student.getId());
		
		PageInfo pageInfo=new PageInfo();
		int itemCount = examService.getStudentExamNum(map);
		List<Exam> studentExam = examService.getStudentExam(map);
		pageInfo.setItemCount(itemCount);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageCurrent(pageCurrent);
		pageInfo.setPageCount((itemCount+pageSize-1)/pageSize);
		ExamListResult rs=new ExamListResult();
		rs.setCode(200);
		rs.setExams(studentExam);
		rs.setMsg("获取成功！");
		rs.setPage(pageInfo);
		return rs;
	}
	/**
	 * 根据学生id获取考试成绩信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getStudentExamScore.do")
	public @ResponseBody ExamScoreListResult getStudentExamScore(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student)request.getSession().getAttribute("student");
		//分页查找所有考试信息
		String parameter = request.getParameter("pageCurrent");
		String flag=request.getParameter("flag");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent;
		if (parameter == null) {
			pageCurrent = 1;
		} else {
			pageCurrent = Integer.valueOf(parameter);
		}
		
		Map map=new HashMap();
		map.put("pageSize", pageSize);
		map.put("itemIndex", (pageCurrent - 1) * pageSize);
		map.put("flag", flag);
		map.put("id", student.getId());
		
		PageInfo pageInfo=new PageInfo();
		int itemCount = examService.getStudentExamScoreNum(map);
		List<ExamScore> studentExam = examService.getStudentExamScore(map);
		pageInfo.setItemCount(itemCount);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageCurrent(pageCurrent);
		pageInfo.setPageCount((itemCount+pageSize-1)/pageSize);
		ExamScoreListResult rs=new ExamScoreListResult();
		rs.setCode(200);
		rs.setExams(studentExam);
		rs.setMsg("获取成功！");
		rs.setPage(pageInfo);
		return rs;
	}
	/**
	 * 取消考试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteExamById.do")
	public @ResponseBody BaseResult deletePageById(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int i = examService.deleteExamById(Integer.valueOf(id));
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
	 * 获取页数指定考试信息
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getExamByPageIndex.do")
	public @ResponseBody ExamListResult getExamByPageIndex(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String paraName = names.nextElement();
			String paraVal = new String(request.getParameter(paraName).getBytes(), "UTF-8");
			if (!paraName.equals("pageCurrent") && !paraName.equals("pageSize")&&!paraVal.equals("")) {
				map.put(paraName, paraVal);
			}
		}
		//分页查找所有考试信息
		String parameter = request.getParameter("pageCurrent");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent;
		if (parameter == null) {
			pageCurrent = 1;
		} else {
			pageCurrent = Integer.valueOf(parameter);
		}
		//得到考试的数量
		int itemCount = examService.searchCount(map);
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
		List<Exam> exams = examService.searchExam(map);
		ExamListResult rs = new ExamListResult();
		rs.setCode(200);
		rs.setMsg("成功");
		rs.setExams(exams);
		rs.setPage(page);
		return rs;
	}
	/**
	 * 交卷评分
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/handInExam.do")
	public @ResponseBody BaseResult handInExam(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student) request.getSession().getAttribute("student");
		int studentId = student.getId();
		String examId=request.getParameter("examId");
		Enumeration<String> attributeNames = request.getParameterNames();
		while (attributeNames.hasMoreElements()) {
			String paraName = attributeNames.nextElement();
			System.err.println("==================="+paraName);
			String paraVal = new String(request.getParameter(paraName).getBytes(), "UTF-8");
			if (!paraName.equals("examId")) {
				StudentExam studentExam=new StudentExam();
				//先判断题目类型
				Question question = questionService.getQuestionById(Integer.valueOf(paraName));
				studentExam.setQuestionId(Integer.valueOf(paraName));
				studentExam.setAnswer(paraVal);
				studentExam.setStuId(studentId);
				studentExam.setExamId(Integer.valueOf(examId));
				studentExamService.insertStudentExam(studentExam);
				if (question.getType()==1) {
					//评分
					if (paraVal.equals(question.getAnswer())) {
						studentExam.setCent(2);
					}else{
						studentExam.setCent(0);
					}
					studentExamService.upCent(studentExam);
				}else if (question.getType()==2) {
					int compare = RandomList.compare(paraVal, question.getAnswer());
					//评分
					if (compare==1) {
						studentExam.setCent(5);
					}else if(compare==2){
						studentExam.setCent(2);
					}else{
						studentExam.setCent(0);
					}
					studentExamService.upCent(studentExam);
				}else if (question.getType()==3) {
					int compare = RandomList.compare(paraVal, question.getAnswer());
					//评分
					if (compare==1) {
						studentExam.setCent(5);
					}else if(compare==2){
						studentExam.setCent(2);
					}else{
						studentExam.setCent(0);
					}
					studentExamService.upCent(studentExam);
				}else if (question.getType()==4) {
					
				}
			}
		}
		BaseResult rs=new BaseResult();
		rs.setCode(200);
		rs.setMsg("成功！");
		return rs;
	}
	/**
	 * 添加到ServletContext
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addToServletContext.do")
	public void addToServletContext(HttpServletRequest request, HttpServletResponse response) {
		Student student = (Student) request.getSession().getAttribute("student");
		String examId = request.getParameter("examId");
		synchronized (request.getServletContext()) {
			ServletContext servletContext = request.getServletContext();
			HashMap<Integer, List<Student>> examsInfo=(HashMap<Integer, List<Student>>) servletContext.getAttribute("spyExam");
			if (examsInfo==null) {
				examsInfo=new HashMap<Integer, List<Student>>();
				List<Student> list=new ArrayList<Student>();
				list.add(student);
				examsInfo.put(Integer.valueOf(examId), list);
				servletContext.setAttribute("spyExam", examsInfo);
			}else{
				List<Student> list = examsInfo.get(Integer.valueOf(examId));
				if (list==null) {
					list=new ArrayList<Student>();
					list.add(student);
				}else{
					list.add(student);
				}
			}
		}
	}
	/**
	 * 添加到ServletContext
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteFromServletContext.do")
	public void deleteFromServletContext(HttpServletRequest request, HttpServletResponse response) {
		Student student = (Student) request.getSession().getAttribute("student");
		synchronized (request.getServletContext()) {
			ServletContext servletContext = request.getServletContext();
			HashMap<Integer, List<Student>> examsInfo=(HashMap<Integer, List<Student>>) servletContext.getAttribute("spyExam");
			if (examsInfo!=null) {
				List<Student> list = examsInfo.get(4);
				list.remove(student);
			}
		}
	}
}
