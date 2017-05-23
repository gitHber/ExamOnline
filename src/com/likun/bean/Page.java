/*
 * File name:          Page.java
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
 * Date:           Apr 28, 2017
 * <p>
 * Time:           1:57:36 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class Page {
	private int id;
	private String name;
	private int courseId;
	private int total_cent;
	private int diff;
	private int chapter_start;
	private int chapter_end;
	private Date crea_time;
	private Date upda_time;
	private int teacherId;
	private int ans_time;
	private int page_type;//期中、期末、测试
	private int assembly_type;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTotal_cent() {
		return total_cent;
	}
	public void setTotal_cent(int total_cent) {
		this.total_cent = total_cent;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	public int getChapter_start() {
		return chapter_start;
	}
	public void setChapter_start(int chapter_start) {
		this.chapter_start = chapter_start;
	}
	public int getChapter_end() {
		return chapter_end;
	}
	public void setChapter_end(int chapter_end) {
		this.chapter_end = chapter_end;
	}
	public Date getCrea_time() {
		return crea_time;
	}
	public void setCrea_time(Date crea_time) {
		this.crea_time = crea_time;
	}
	public Date getUpda_time() {
		return upda_time;
	}
	public void setUpda_time(Date upda_time) {
		this.upda_time = upda_time;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getAns_time() {
		return ans_time;
	}
	public void setAns_time(int ans_time) {
		this.ans_time = ans_time;
	}
	public int getPage_type() {
		return page_type;
	}
	public void setPage_type(int page_type) {
		this.page_type = page_type;
	}
	public int getAssembly_type() {
		return assembly_type;
	}
	public void setAssembly_type(int assembly_type) {
		this.assembly_type = assembly_type;
	}
	@Override
	public String toString() {
		return "Page [id=" + id + ", name=" + name + ", courseId=" + courseId + ", total_cent=" + total_cent
				+ ", diff=" + diff + ", chapter_start=" + chapter_start + ", chapter_end=" + chapter_end
				+ ", crea_time=" + crea_time + ", upda_time=" + upda_time + ", teacherId=" + teacherId + ", ans_time="
				+ ans_time + ", page_type=" + page_type + ", assembly_type=" + assembly_type + "]";
	}
	
}
