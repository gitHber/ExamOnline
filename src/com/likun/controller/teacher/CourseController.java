/*
 * File name:          LoginController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likun.bean.Course;
import com.likun.bean.Question;
import com.likun.bean.Student;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.CourseListResult;
import com.likun.result.QuestionListResult;
import com.likun.result.StudentResult;
import com.likun.result.TeacherResult;
import com.likun.service.CourseService;
import com.likun.service.QuestionService;
import com.likun.service.StudentService;
import com.likun.service.TeacherService;

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
public class CourseController {
	@Autowired
	CourseService courseService;
	/**
	 * 获取所有的试题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAllCourse.do")
	public @ResponseBody CourseListResult getAllCourse(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> courses = courseService.getAllCourse();
		CourseListResult rs=new CourseListResult();
		rs.setCode(200);
		rs.setMsg("获取成功！");
		rs.setCourses(courses);
		return rs;
	}
}
