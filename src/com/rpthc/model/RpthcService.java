package com.rpthc.model;

import java.sql.Timestamp;
import java.util.List;


public class RpthcService {

	private RpthcDAO_interface dao;

	public RpthcService() {

		dao = new RpthcJNDIDAO();
	}

	public RpthcVO addRpthc(String hcm_no, String lld_no, String rpthc_content) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setHcm_no(hcm_no);
		rpthcVO.setLld_no(lld_no);
		rpthcVO.setRpthc_content(rpthc_content);
		dao.insert(rpthcVO);
		return rpthcVO;
	}

	public RpthcVO updaterpthc(String rpthc_no,String hcm_no, String lld_no, String rpthc_content, String emp_no,
			Integer rpthc_status,Integer rpthc_result,String rpthc_note) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setRpthc_no(rpthc_no);
		rpthcVO.setHcm_no(hcm_no);
		rpthcVO.setLld_no(lld_no);
		rpthcVO.setRpthc_content(rpthc_content);
		rpthcVO.setEmp_no(emp_no);
		rpthcVO.setRpthc_status(rpthc_status);
		rpthcVO.setRpthc_result(rpthc_result);
		rpthcVO.setRpthc_note(rpthc_note);
		dao.update(rpthcVO);
		return rpthcVO;
	}
	
	public RpthcVO updateEmp(String rpthc_no, String emp_no, Integer rpthc_status) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setRpthc_no(rpthc_no);
		rpthcVO.setEmp_no(emp_no);
		rpthcVO.setRpthc_status(rpthc_status);
		dao.updateEmp(rpthcVO);
		return rpthcVO;
	}
	
	public RpthcVO assignEmp(String rpthc_no, String emp_no, String rpthc_note) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setRpthc_no(rpthc_no);
		rpthcVO.setEmp_no(emp_no);
		rpthcVO.setRpthc_note(rpthc_note);
		dao.assignEmp(rpthcVO);
		return rpthcVO;
	}
	
	public RpthcVO saveNote(String rpthc_no, String rpthc_note) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setRpthc_no(rpthc_no);
		rpthcVO.setRpthc_note(rpthc_note);
		dao.saveNote(rpthcVO);
		return rpthcVO;
	}
	
	public RpthcVO fail(String rpthc_no, Integer rpthc_result, String rpthc_note) {

		RpthcVO rpthcVO = new RpthcVO();
		rpthcVO.setRpthc_no(rpthc_no);
		rpthcVO.setRpthc_result(rpthc_result);
		rpthcVO.setRpthc_note(rpthc_note);
		dao.fail(rpthcVO);
		return rpthcVO;
	}

	public void deleteRpthc(String rpthc_no) {
		dao.delete(rpthc_no);
	}

	public RpthcVO getOneRpthc(String rpthc_no) {
		return dao.findByPrimaryKey(rpthc_no);
	}

	public List<RpthcVO> getAllRpthc() {
		return dao.getAll();
	}

	public List<RpthcVO> getRpthc(String Number) {
		return dao.findByNo(Number);
	}
	
	
}
