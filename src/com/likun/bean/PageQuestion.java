/*
 * File name:          PageQuestion.java
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
 * Date:           Apr 30, 2017
 * <p>
 * Time:           9:37:11 AM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class PageQuestion {
	private int pageId;
	private int questionId;
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	@Override
	public String toString() {
		return "PageQuestion [pageId=" + pageId + ", questionId=" + questionId + "]";
	}
	
}
