/*
 * File name:          Student.java
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
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:24:34 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class Teacher {
	private int id;
	private int no;//教师编号
	private String name;//姓名
	private String password;//密码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", no=" + no + ", name=" + name + ", password=" + password + "]";
	}
}
