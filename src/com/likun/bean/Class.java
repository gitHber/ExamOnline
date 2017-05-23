/*
 * File name:          Class.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.bean;

import java.security.KeyStore.PrivateKeyEntry;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 2, 2017
 * <p>
 * Time:           10:36:53 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class Class {
	private int id;
	private String name;
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
	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + "]";
	}
	
}
