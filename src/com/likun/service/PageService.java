/*
 * File name:          PageService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.likun.bean.Page;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 28, 2017
 * <p>
 * Time:           2:32:56 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
public interface PageService {
	/**
	 * 更新试卷
	 * @param question
	 * @return
	 */
	public int updatePageById(Page page);
	/**
	 * 查询试卷当前总分数
	 * @param pageId
	 * @return
	 */
	public int getCurrCentByPageId(String pageId);
	/**
	 * 添加试卷
	 * @param question
	 * @return
	 */
	public int addPage(Page page);
	/**
	 * 根据id得到试卷
	 * @param id
	 * @return
	 */
	public Page getPageById(int id);
	/**
	 * 根据试卷id删除试卷
	 * @param id
	 * @return
	 */
	public int deletePageById(int id);
	/**
	 * 根据条件查询相应试卷数量
	 * @param map
	 * @return
	 */
	public int searchCount(Map<String, Object> map);

	/**
	 * 根据条件查询相应试卷集合
	 * @param map
	 * @return
	 */
	public List<Page> searchPage(Map<String,Object> map) ;
	/**
	 * 得到所有的试卷
	 * @return
	 */
	public List<Page> getAllPage();
}
