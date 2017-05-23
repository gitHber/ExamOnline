/*
 * File name:          QuestionType.java
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
 * Time:           9:14:27 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class QuestionType {
	private int id;
	private String name;
	private int mode;//主观1 客观2
	private int cent;//分值
	private int num;
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
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getCent() {
		return cent;
	}
	public void setCent(int cent) {
		this.cent = cent;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "QuestionType [id=" + id + ", name=" + name + ", mode=" + mode + ", cent=" + cent + ", num=" + num + "]";
	}
}
