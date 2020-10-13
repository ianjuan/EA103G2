package com.ctsp.model;

import java.util.List;

import com.ctsp.model.CtspDAO_interface;
import com.ctsp.model.CtspJNDIDAO;
import com.ctsp.model.CtspVO;

public class CtspService {
	private CtspDAO_interface dao;

	public CtspService() {
		dao = new CtspJNDIDAO();
	}

	public CtspVO addCtsp(String ctsp_no, String cts_no, byte[] ctsp_pic) {

		CtspVO ctspVO = new CtspVO();
		ctspVO.setCtsp_no(ctsp_no);
		ctspVO.setCts_no(cts_no);
		ctspVO.setCtsp_pic(ctsp_pic);
		dao.insert(ctspVO);
		return ctspVO;
	}

	public CtspVO updateCtsp(String ctsp_no, String cts_no, byte[] ctsp_pic) {

		CtspVO ctspVO = new CtspVO();
		ctspVO.setCtsp_no(ctsp_no);
		ctspVO.setCts_no(cts_no);
		ctspVO.setCtsp_pic(ctsp_pic);
		return ctspVO;
	}

	public void deleteCtsp(String ctsp_no) {
		dao.delete(ctsp_no);
	}

	public CtspVO getOneCtsp(String ctsp_no) {
		return dao.findByPrimaryKey(ctsp_no);
	}

	public List<CtspVO> getAllCtsp() {
		return dao.getAll();
	}

	public List<CtspVO> getCtsp(String cts_no) {
		return dao.findByCts(cts_no);
	}

}
