/*
 * File name:          ClassController.java
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

import com.likun.bean.Class;
import com.likun.result.ClassListResult;
import com.likun.service.ClassService;

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
public class ClassController {
	@Autowired
	ClassService classService;
	@RequestMapping("/getAllClass.do")
	public @ResponseBody ClassListResult getAllClass(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Class> allClass = classService.getAllClass();
		ClassListResult rs=new ClassListResult();
		if (allClass!=null) {
			rs.setClasses(allClass);
			rs.setCode(200);
			rs.setMsg("获取成功！");
		}else{
			rs.setClasses(null);
			rs.setCode(101);
			rs.setMsg("获取失败！");
		}
		return rs;
	}
}
