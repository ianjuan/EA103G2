package com.news.model;
import java.util.List;

import com.emp.model.EmployeeVO;


public class NewsService {
	private NewsDAO_interface dao;
	public NewsService() {
		dao = new NewsDAO();
	}
	public NewsVO getOneNews(String new_no) {
		return dao.findByPrimaryKey(new_no);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
	public void updateFun() {

	}
}
