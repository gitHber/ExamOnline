/*
 * File name:          LoginController.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.likun.bean.Manager;
import com.likun.bean.Student;
import com.likun.bean.Teacher;
import com.likun.result.BaseResult;
import com.likun.result.ManagerResult;
import com.likun.result.StudentResult;
import com.likun.result.TeacherResult;
import com.likun.service.ManagerService;
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
public class LoginController {
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherservice;
	@Autowired
	ManagerService managerService;
	
	/**
	 * 学生登录
	 */
	@RequestMapping("/studentLogin.do")
	public @ResponseBody BaseResult studentLogin(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String studentno = request.getParameter("studentno");
		String password = request.getParameter("password");
		Student student = studentService.getStudentById(studentno);
		BaseResult rs=new BaseResult();
		if (student.getPassword().equals(password)) {
			rs.setCode(200);
			rs.setMsg("验证成功！");
			request.getSession().setAttribute("student", student);		
		}else{
			rs.setCode(100);
			rs.setMsg("验证失败！");
		}
		return rs;
	}
	/**
	 * 从session获取学生信息
	 */
	@RequestMapping("/getStudentInfoFromSession.do")
	public @ResponseBody StudentResult getStudentInfoFromSession(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Student student = (Student) request.getSession().getAttribute("student");
		StudentResult rs=new StudentResult();
		rs.setCode(200);
		rs.setMsg("获取成功");
		rs.setStudent(student);
		return rs;
	}
	
	
	/**
	 * 教师登录
	 */
	@RequestMapping("/teacherLogin.do")
	public @ResponseBody BaseResult teacherLogin(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String teacherno = request.getParameter("teacherno");
		String password = request.getParameter("password");
		Teacher teacher = teacherservice.getTeacherById(teacherno);
		BaseResult rs=new BaseResult();
		if (teacher.getPassword().equals(password)) {
			rs.setCode(200);
			rs.setMsg("验证成功！");
			request.getSession().setAttribute("teacher", teacher);
		}else{
			rs.setCode(100);
			rs.setMsg("验证失败！");
		}
		return rs;
	}
	/**
	 * 从session获取教师信息
	 */
	@RequestMapping("/getTeacherInfoFromSession.do")
	public @ResponseBody TeacherResult getTeacherInfoFromSession(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		TeacherResult rs=new TeacherResult();
		rs.setCode(200);
		rs.setMsg("获取成功");
		rs.setTeacher(teacher);
		return rs;
	}
	/**
	 * 管理员登录
	 */
	@RequestMapping("/managerLogin.do")
	public @ResponseBody BaseResult managerLogin(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String managerno = request.getParameter("managerno");
		String password = request.getParameter("password");
		Manager manager = managerService.getManagerById(managerno);
		BaseResult rs=new BaseResult();
		if (manager.getPassword().equals(password)) {
			rs.setCode(200);
			rs.setMsg("验证成功！");
			request.getSession().setAttribute("manager", manager);
		}else{
			rs.setCode(100);
			rs.setMsg("验证失败！");
		}
		return rs;
	}
	/**
	 * 从session获取管理员信息
	 */
	@RequestMapping("/getManagerInfoFromSession.do")
	public @ResponseBody ManagerResult getManagerInfoFromSession(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		ManagerResult rs=new ManagerResult();
		rs.setCode(200);
		rs.setMsg("获取成功");
		rs.setManager(manager);
		return rs;
	}
	/**
	 * 注销
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/loginOut.do")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String role = request.getParameter("role");
		System.err.println(role);
		if(role.equals("student")){
			request.getSession().removeAttribute("student");
		}else if(role.equals("teacher")){
			request.getSession().removeAttribute("teacher");
		}else if(role.equals("manager")){
			request.getSession().removeAttribute("manager");
		}
		response.sendRedirect("view/"+role+"/"+role+"-login.html");
	}
}
