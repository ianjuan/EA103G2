package com.news.model;

import java.util.List;

import com.news.model.NewsVO;

public interface NewsDAO_interface {
	public void insert(NewsVO newsVO);
	public NewsVO findByPrimaryKey(String new_no);
	public List<NewsVO>getAll();
	public void update(NewsVO newsVO);

}