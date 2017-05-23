/*
 * File name:          IPageService.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.likun.bean.Page;
import com.likun.dao.PageDao;
import com.likun.service.PageService;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 28, 2017
 * <p>
 * Time:           2:34:02 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Service(value="pageService")
public class IPageService implements PageService {
	@Autowired
	private PageDao pageDao;
	/**
	 * 查询试卷当前总分数
	 * @param pageId
	 * @return
	 */
	public int getCurrCentByPageId(String pageId){
		return pageDao.getCurrCentByPageId(pageId);
	}
	/**
	 * 更新试卷
	 * @param question
	 * @return
	 */
	public int updatePageById(Page page){
		return pageDao.updatePageById(page);
	}
	/**
	 * 添加试卷
	 * @param question
	 * @return
	 */
	public int addPage(Page page){
		return pageDao.addPage(page);
	}
	/**
	 * 根据id得到试卷
	 * @param id
	 * @return
	 */
	public Page getPageById(int id){
		return pageDao.getPageById(id);
	}
	/**
	 * 根据试卷id删除试卷
	 * @param id
	 * @return
	 */
	public int deletePageById(int id){
		return pageDao.deletePageById(id);
	}
	/**
	 * 根据条件查询相应试卷数量
	 * @param map
	 * @return
	 */
	public int searchCount(Map<String, Object> map) {
		return pageDao.searchCount(map);
	}

	/**
	 * 根据条件查询相应试卷集合
	 * @param map
	 * @return
	 */
	public List<Page> searchPage(Map<String,Object> map) {
		return pageDao.searchPage(map);
	}
	/**
	 * 得到所有的试卷
	 * @return
	 */
	public List<Page> getAllPage(){
		return pageDao.getAllPage();
	}
}
