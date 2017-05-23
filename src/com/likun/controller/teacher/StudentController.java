/*
 * File name:          ClassController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ContainerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.likun.bean.Exam;
import com.likun.bean.Student;
import com.likun.result.BaseResult;
import com.likun.result.SpyExamResult;
import com.likun.result.StudentListResult;
import com.likun.service.ExamService;
import com.likun.service.StudentExamService;
import com.likun.service.StudentService;
import com.likun.util.PageInfo;

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
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	ExamService examService;
	@Autowired
	StudentExamService studentExamService;

	/**
	 * 添加学生
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addStudent.do")
	public @ResponseBody BaseResult addStudent(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Student student = new Student();
		student.setNo(Integer.valueOf(no));
		student.setName(name);
		student.setGrade(Integer.valueOf(grade));
		student.setPassword(password);
		student.setEmail(email);
		int flag = studentService.addStudent(student);
		BaseResult rs = new BaseResult();
		if (flag != 0) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
		}
		return rs;
	}

	/**
	 * 删除学生
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteStudent.do")
	public @ResponseBody BaseResult deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int flag = studentService.deleteStudent(Integer.valueOf(id));
		BaseResult rs = new BaseResult();
		if (flag != 0) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
		}
		return rs;
	}

	/**
	 * 修改学生信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateStudentById.do")
	public @ResponseBody BaseResult upStudent(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Student student = new Student();
		student.setId(Integer.valueOf(id));
		student.setName(name);
		student.setPassword(password);
		student.setEmail(email);
		int flag = studentService.upStudent(student);
		BaseResult rs = new BaseResult();
		if (flag != 0) {
			rs.setCode(200);
			rs.setMsg("获取成功！");
		} else {
			rs.setCode(101);
			rs.setMsg("获取失败！");
		}
		return rs;
	}

	/**
	 * 获取页数指定学生信息
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getStudentByPageIndex.do")
	public @ResponseBody StudentListResult getStudentByPageIndex(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String paraName = names.nextElement();
			String paraVal = new String(request.getParameter(paraName).getBytes(), "UTF-8");
			if (!paraName.equals("pageCurrent") && !paraName.equals("pageSize") && !paraVal.equals("")) {
				map.put(paraName, paraVal);
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
		//得到学生的数量
		int itemCount = studentService.searchCount(map);
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
		List<Student> students = studentService.searchStudent(map);
		StudentListResult rs = new StudentListResult();
		rs.setCode(200);
		rs.setMsg("成功");
		rs.setStudents(students);
		rs.setPage(page);
		return rs;
	}

	/**
	 * 获取考试中的学生信息
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/spyExamById.do")
	public @ResponseBody SpyExamResult spyExamById(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response. setContentType("text/html;charset=UTF-8");
		String examId = request.getParameter("examId");
		Exam exam = examService.getExamById(Integer.valueOf(examId));
		String grade = exam.getGrade();
		//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		//		ServletContext servletContext = webApplicationContext.getServletContext();
		//获取所有学生
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grade", grade);
		List<Student> students = studentService.searchStudent(map);
		//获取application中的正在考试人数
		ServletContext servletContext = request.getServletContext();
		HashMap<Integer, List<Student>> examsInfo = (HashMap<Integer, List<Student>>) servletContext
				.getAttribute("spyExam");
		HashMap<String, String> map2=new HashMap<String, String>();
		map2.put("examId", examId);
		List<Student> notExamStudents=new ArrayList<Student>();
		List<Student> examingStudents=new ArrayList<Student>();
		List<Student> examedStudents=new ArrayList<Student>();
		if (examsInfo != null) {
			System.err.println(examsInfo.toString());
			//参加过考试的（包括正在考试与一交卷的）
			List<Student> examStudent = examsInfo.get(Integer.valueOf(examId));
			System.err.println(""+examStudent);
			for (Student student : students) {
				if(contains(examStudent, student)){
					map2.put("stuId", student.getId()+"");
					//判断是否交卷
					if (studentExamService.getAnswerNumByExamId(map2)>0) {
						examedStudents.add(student);
					}else{
						examingStudents.add(student);
					}
				}else {
					notExamStudents.add(student);
				}
			}
		}
		SpyExamResult rs = new SpyExamResult();
		if (examsInfo==null) {
			rs.setCode(100);
			rs.setMsg("没有考试信息！");
		}else{
			rs.setCode(200);
			rs.setMsg("成功！");
			rs.setNotExamStudents(notExamStudents);
			rs.setExamingStudents(examingStudents);
			rs.setExamedStudents(examedStudents);
		}
		return rs;
	}
	public boolean contains(List<Student> list,Student student){
		for (Student student1 : list) {
			if (student1.equals(student)) {
				return true;
			}
		}
		return false;
	}
}
