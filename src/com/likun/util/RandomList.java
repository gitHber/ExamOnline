/*
 * File name:          RandomList.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.search.FromStringTerm;

import com.likun.bean.Question;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           May 1, 2017
 * <p>
 * Time:           6:20:09 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public class RandomList {
	private static Random random;
	/**
	 * 
	 * @param list 数字列
	 * @param num  要得到的随机数
	 * @return
	 */
	public static List<Integer> generateRandomList(List<Question> list,int num){
		random=new Random();
		List<Integer> rs=new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			int n=random.nextInt(list.size());
			Question question = list.remove(n);
			rs.add(question.getId());
		}
		return rs;
	}
	/**
	 * 比对一个字符串数组在与另一个字符串相同之处
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compare(String s1,String s2){
		s1=s1.replaceAll("\\s", "");
		s2=s2.replaceAll("\\s", "");
		String []s3=s1.split(",");
		boolean a=false;
		boolean b=true;
		for (String s : s3) {
			if (s2.indexOf(s)!=-1) {
				a=true;
			}else{
				b=false;
			}
		}
		if (s3.length>s2.split(",").length) {
			return 3;
		}
		if (a) {
			if (b) {
				if (s3.length==s2.split(",").length) {
					//全相同
					return 1;
				}else{
					//部分相同
					return 2;
				}
			}else{
				//部分相同
				return 2;
			}
		}else{
			//全不同
			return 3;
		}
	}
}
