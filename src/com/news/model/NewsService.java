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
	
	public NewsVO addNews(String new_content, String emp_no) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNew_content(new_content);
		newsVO.setEmp_no(emp_no);
		newsVO.setNew_blob(null);
		dao.insert(newsVO);
		return newsVO;
	}

	public void updateFun() {

	}
}
