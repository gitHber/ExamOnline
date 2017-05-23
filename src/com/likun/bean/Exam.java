/*
 * File name:          Exam.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.bean;

import java.util.Date;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 1, 2017
 * <p>
 * Time:           10:11:58 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class Exam {
	private int id;
	private String name;
	private int pageId;
	private Date time;
	private String grade;
	private int flag;
	private int teacherId;
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", pageId=" + pageId + ", time=" + time + ", grade=" + grade
				+ ", flag=" + flag + ", teacherId=" + teacherId + "]";
	}
	
}
