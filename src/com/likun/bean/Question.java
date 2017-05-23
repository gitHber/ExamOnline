/*
 * File name:          Question.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.bean;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 23, 2017
 * <p>
 * Time:           11:57:03 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class Question {
	private int id;
	private String content;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String chapter;//章节
	private int type;//试题类型
	private int diff;//试题难度
	private int courseId;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer + ", chapter=" + chapter
				+ ", type=" + type + ", diff=" + diff + ", courseId=" + courseId + "]";
	}
	
	
}
