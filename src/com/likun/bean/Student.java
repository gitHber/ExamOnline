package com.likun.bean;

/*
 * File name:          Student.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */

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
public class Student {
	private int id;
	private int no;//学生编号
	private String name="";//姓名
	private int grade;//班级
	private String password="";//密码
	private String email="";//邮箱
	private String img="";//头像

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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", no=" + no + ", name=" + name + ", grade=" + grade + ", password=" + password
				+ ", email=" + email + ", img=" + img + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Student) {
			Student student = (Student) arg0;
			if (student.getId() == this.getId() && student.getEmail().equals(this.getEmail())
					&& student.getGrade() == this.getGrade() && student.getImg().equals(this.getImg())
					&& student.getName().equals(this.getName()) && student.getNo() == this.getNo()
					&& student.getPassword().equals(this.getPassword())) {
				return true;
			}
			return false;
		}
		return false;
	}
}
