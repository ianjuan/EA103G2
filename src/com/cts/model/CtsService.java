package com.cts.model;

import java.util.List;

import com.cts.model.CtsJNDIDAO;
import com.cts.model.CtsVO;
import com.cts.model.CtsDAO_interface;

public class CtsService {
	private CtsDAO_interface dao;
	public CtsService() {

		dao = new CtsJNDIDAO();
	}

	public CtsVO addCts(String cts_no, Integer cts_type, String cts_question, String cts_answer,String emp_no) {

		CtsVO ctsVO = new CtsVO();
		ctsVO.setCts_no(cts_no);
		ctsVO.setCts_type(cts_type);
		ctsVO.setCts_question(cts_question);
		ctsVO.setCts_answer(cts_answer);
		ctsVO.setEmp_no(emp_no);
		dao.insert(ctsVO);
		return ctsVO;
	}

	public CtsVO updateCts(String cts_no, Integer cts_type, String cts_question,String cts_answer, String emp_no,
			Integer cts_show) {

		CtsVO ctsVO = new CtsVO();
		ctsVO.setCts_no(cts_no);
		ctsVO.setCts_no(cts_no);
		ctsVO.setCts_type(cts_type);
		ctsVO.setCts_question(cts_question);
		ctsVO.setEmp_no(emp_no);
		ctsVO.setCts_show(cts_show);
		dao.update(ctsVO);
		return ctsVO;
	}

	public void deleteCts(String cts_no) {
		dao.delete(cts_no);
	}

	public CtsVO getOneCts(String cts_no) {
		return dao.findByPrimaryKey(cts_no);
	}

	public List<CtsVO> getAllCts() {
		return dao.getAll();
	}
	
	public List<CtsVO> getType(Integer cts_type){
		return dao.findByType(cts_type);
	}

}
