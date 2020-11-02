package com.rptr.model;

import java.sql.Timestamp;
import java.util.List;


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

	public RptrVO updaterptr(String rptr_no,String rep_no, String tnt_no, String rptr_content, String emp_no,
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
	
	public RptrVO updateEmp(String rptr_no, String emp_no, Integer rptr_status) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRptr_no(rptr_no);
		rptrVO.setEmp_no(emp_no);
		rptrVO.setRptr_status(rptr_status);
		dao.updateEmp(rptrVO);
		return rptrVO;
	}
	
	public RptrVO assignEmp(String rptr_no, String emp_no, String rptr_note) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRptr_no(rptr_no);
		rptrVO.setEmp_no(emp_no);
		rptrVO.setRptr_note(rptr_note);
		dao.assignEmp(rptrVO);
		return rptrVO;
	}
	
	public RptrVO saveNote(String rptr_no, String rptr_note) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRptr_no(rptr_no);
		rptrVO.setRptr_note(rptr_note);
		dao.saveNote(rptrVO);
		return rptrVO;
	}
	
	public RptrVO fail(String rptr_no, Integer rptr_result, String rptr_note) {

		RptrVO rptrVO = new RptrVO();
		rptrVO.setRptr_no(rptr_no);
		rptrVO.setRptr_result(rptr_result);
		rptrVO.setRptr_note(rptr_note);
		dao.fail(rptrVO);
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
