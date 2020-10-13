package com.rptr.model;

import java.util.List;

import com.rptr.model.RptrDAO_interface;
import com.rptr.model.RptrJNDIDAO;
import com.rptr.model.RptrVO;

public class RptrService {

	private RptrDAO_interface dao;

	public RptrService() {

		dao = new RptrJNDIDAO();
	}

	public RptrVO addRptr(String rep_no, String tnt_no, String rptr_content) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRep_no(rep_no);
		rptrVO.setTnt_no(tnt_no);
		rptrVO.setRptr_content(rptr_content);
		dao.insert(rptrVO);
		return rptrVO;
	}

	public RptrVO updateRptr(String rptr_no,String rep_no, String tnt_no, String rptr_content, String emp_no,
			Integer rptr_status,Integer rptr_result,String rptr_note) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRptr_no(rptr_no);
		rptrVO.setRep_no(rep_no);
		rptrVO.setTnt_no(tnt_no);
		rptrVO.setRptr_content(rptr_content);
		rptrVO.setEmp_no(emp_no);
		rptrVO.setRptr_status(rptr_status);
		rptrVO.setRptr_result(rptr_result);
		rptrVO.setRptr_note(rptr_note);
		dao.update(rptrVO);
		return rptrVO;
	}

	public void deleteRptr(String rptr_no) {
		dao.delete(rptr_no);
	}

	public RptrVO getOneRptr(String rptr_no) {
		return dao.findByPrimaryKey(rptr_no);
	}

	public List<RptrVO> getAllRptr() {
		return dao.getAll();
	}

	public List<RptrVO> getRptr(String Number) {
		return dao.findByNo(Number);
	}
}
