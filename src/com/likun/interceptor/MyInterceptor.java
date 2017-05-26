/*
 * File name:          LoginInterceptor.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.likun.bean.Manager;
import com.likun.bean.Student;
import com.likun.bean.Teacher;

/**
 * TODO: File comments
 * <p>	登陆拦截
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Mar 24, 2017
 * <p>
 * Time:           12:38:44 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class MyInterceptor implements HandlerInterceptor {
	@Value("/ExamOnline/view/student/student-login.html")
	private String LOGIN_URL;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		preHandle(arg0, arg1, arg2);
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		preHandle(arg0, arg1, arg2);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.err.println("=================进入拦截器========================");
		System.err.println("servlet:"+request.getServletPath());
		System.err.println("html:"+request.getHeader("Referer"));
		String servletPath = request.getServletPath();
		String reqHtmlName = request.getHeader("Referer");
		if (servletPath.endsWith("Login.do")) {
			return true;
		}
		if (reqHtmlName.contains("/ExamOnline/view/student")) {
			Student student = (Student) request.getSession().getAttribute("student");
			if (student!=null) {
				
				return true;
			}else{
				System.err.println("未登录");
				return false;
			}
		}
		if (reqHtmlName.contains("/ExamOnline/view/teacher")){
			Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
			if (teacher!=null) {
				return true;
			}else{
				return false;
			}
		}
		if (reqHtmlName.contains("/ExamOnline/view/manager")){
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			if (manager!=null) {
				return true;
			}else{
				return false;
			}
		}
		if (reqHtmlName.contains("welcom.html")) {
			return true;
		}
		return false;
	}

}
