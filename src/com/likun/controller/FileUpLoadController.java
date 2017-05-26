package com.likun.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Decoder.BASE64Decoder;

import com.likun.bean.Student;
import com.likun.result.BaseResult;
import com.likun.service.StudentService;

@Controller
public class FileUpLoadController {
	@Autowired
	StudentService studentService;
	@RequestMapping("/file.do")
	public @ResponseBody BaseResult fileUp(HttpServletRequest req,
			HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//图片保存地址
		HttpSession session = req.getSession();
		String realPath = session.getServletContext().getRealPath("/upload");
		System.err.println(realPath);
		Student student=(Student)session.getAttribute("student");
		String imgName="userHead-"+student.getId()+".png";
		//转码保存
		String data = req.getParameter("data");
		String picData = data.substring(22, data.length());
		BaseResult rs=new BaseResult();
		String contextPath = req.getServletContext().getContextPath();
		try {
			//转码保存
			convertBase64DataToImage(picData, realPath+"/"+imgName);
			Map<String,String> map=new HashMap<String,String>();
			map.put("imgSrc", contextPath+"/upload/"+imgName);
			map.put("id", student.getId()+"");
			//数据库保存地址
			int flag = studentService.upUserHead(map);
			if (flag>0) {
				rs.setCode(200);
				rs.setMsg(realPath+"/"+imgName);
			}else{
				rs.setCode(100);
				rs.setMsg("数据库写入失败！");
			}
		} catch (IOException e) {
			rs.setCode(100);
			rs.setMsg("图片转码失败");
			e.printStackTrace();
		}
		return rs;
	}
	/** 
	 * 把base64图片数据转为本地图片 
	 * @param base64ImgData 
	 * @param filePath 
	 * @throws IOException 
	 */
	public static void convertBase64DataToImage(String base64ImgData, String filePath) throws IOException {
		BASE64Decoder d = new BASE64Decoder();
		byte[] bs = d.decodeBuffer(base64ImgData);
		FileOutputStream os = new FileOutputStream(filePath);
		os.write(bs);
		os.close();
	}
}
