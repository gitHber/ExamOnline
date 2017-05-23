/*
 * File name:          StudentDao.java
 * Copyright@Lanqiao (China)
 * Editor:           JDK1.6.32
 */
package com.likun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.likun.bean.Page;
import com.likun.bean.Question;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:          李坤
 * <p>
 * Date:           Apr 18, 2017
 * <p>
 * Time:           9:28:31 PM
 * <p>
 * Director:        李坤
 * <p>
 * <p>
 */
@Repository(value="pageDao")
public class PageDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 更新试卷
	 * @param question
	 * @return
	 */
	public int updatePageById(Page page){
		int update = sqlSessionTemplate.update("com.likun.dao.PageDao.updatePageById",page);
		return update;
	}
	/**
	 * 查询试卷当前总分数
	 * @param pageId
	 * @return
	 */
	public int getCurrCentByPageId(String pageId){
		int currCent=sqlSessionTemplate.selectOne("com.likun.dao.PageDao.getCurrCentByPageId", pageId);
		return currCent;
	}
	/**
	 * 添加试卷
	 * @param question
	 * @return
	 */
	public int addPage(Page page){
		int insert = sqlSessionTemplate.insert("com.likun.dao.PageDao.addPage",page);
		return insert;
	}
	/**
	 * 根据id得到试卷
	 * @param id
	 * @return
	 */
	public Page getPageById(int id){
		Page page = sqlSessionTemplate.selectOne("com.likun.dao.PageDao.getPageById", id);
		return page;
	}
	/**
	 * 根据试卷id删除试卷
	 * @param id
	 * @return
	 */
	public int deletePageById(int id){
		int delete = sqlSessionTemplate.delete("com.likun.dao.PageDao.deletePageById",id);
		return delete;
	}
	/**
	 * 根据条件查询相应试卷数量
	 * @param map
	 * @return
	 */
	public int searchCount(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		return sqlSessionTemplate.selectOne("com.likun.dao.PageDao.searchCount", map1);
	}

	/**
	 * 根据条件查询相应试卷集合
	 * @param map
	 * @return
	 */
	public List<Page> searchPage(Map<String,Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("aa", map);
		return sqlSessionTemplate.selectList("com.likun.dao.PageDao.searchPage", map1);
	}
	/**
	 * 得到所有的试卷
	 * @return
	 */
	public List<Page> getAllPage(){
		List<Page> pages = sqlSessionTemplate.selectList("com.likun.dao.PageDao.getAllPage");
		return pages;
	}
}
