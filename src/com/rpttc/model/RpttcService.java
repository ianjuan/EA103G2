package com.rpttc.model;

import java.sql.Timestamp;
import java.util.List;


public class RpttcService {

	private RpttcDAO_interface dao;

	public RpttcService() {

		dao = new RpttcJNDIDAO();
	}

	public RpttcVO addRpttc(String tcm_no, String tnt_no, String rpttc_content) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setTcm_no(tcm_no);
		rpttcVO.setTnt_no(tnt_no);
		rpttcVO.setRpttc_content(rpttc_content);
		dao.insert(rpttcVO);
		return rpttcVO;
	}

	public RpttcVO updaterpttc(String rpttc_no,String tcm_no, String tnt_no, String rpttc_content, String emp_no,
			Integer rpttc_status,Integer rpttc_result,String rpttc_note) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setRpttc_no(rpttc_no);
		rpttcVO.setTcm_no(tcm_no);
		rpttcVO.setTnt_no(tnt_no);
		rpttcVO.setRpttc_content(rpttc_content);
		rpttcVO.setEmp_no(emp_no);
		rpttcVO.setRpttc_status(rpttc_status);
		rpttcVO.setRpttc_result(rpttc_result);
		rpttcVO.setRpttc_note(rpttc_note);
		dao.update(rpttcVO);
		return rpttcVO;
	}
	
	public RpttcVO updateEmp(String rpttc_no, String emp_no, Integer rpttc_status) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setRpttc_no(rpttc_no);
		rpttcVO.setEmp_no(emp_no);
		rpttcVO.setRpttc_status(rpttc_status);
		dao.updateEmp(rpttcVO);
		return rpttcVO;
	}
	
	public RpttcVO assignEmp(String rpttc_no, String emp_no, String rpttc_note) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setRpttc_no(rpttc_no);
		rpttcVO.setEmp_no(emp_no);
		rpttcVO.setRpttc_note(rpttc_note);
		dao.assignEmp(rpttcVO);
		return rpttcVO;
	}
	
	public RpttcVO saveNote(String rpttc_no, String rpttc_note) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setRpttc_no(rpttc_no);
		rpttcVO.setRpttc_note(rpttc_note);
		dao.saveNote(rpttcVO);
		return rpttcVO;
	}
	
	public RpttcVO fail(String rpttc_no, Integer rpttc_result, String rpttc_note) {

		RpttcVO rpttcVO = new RpttcVO();
		rpttcVO.setRpttc_no(rpttc_no);
		rpttcVO.setRpttc_result(rpttc_result);
		rpttcVO.setRpttc_note(rpttc_note);
		dao.fail(rpttcVO);
		return rpttcVO;
	}

	public void deleteRpttc(String rpttc_no) {
		dao.delete(rpttc_no);
	}

	public RpttcVO getOneRpttc(String rpttc_no) {
		return dao.findByPrimaryKey(rpttc_no);
	}

	public List<RpttcVO> getAllRpttc() {
		return dao.getAll();
	}

	public List<RpttcVO> getRpttc(String Number) {
		return dao.findByNo(Number);
	}
	
	
}
