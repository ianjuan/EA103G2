package com.rptt.model;

import java.sql.Timestamp;
import java.util.List;

public class RpttService {

	private RpttDAO_interface dao;

	public RpttService() {

		dao = new RpttJNDIDAO();
	}

	public RpttVO addRptt(String tnt_no, String lld_no, String rptt_content) {

		RpttVO rpttVO = new RpttVO();
		rpttVO.setTnt_no(tnt_no);
		rpttVO.setLld_no(lld_no);
		rpttVO.setRptt_content(rptt_content);
		dao.insert(rpttVO);
		return rpttVO;
	}

	public RpttVO updaterptt(String rptt_no,String tnt_no, String lld_no, String rptt_content, String emp_no,
			Integer rptt_status,Integer rptt_result,String rptt_note) {

		RpttVO rpttVO = new RpttVO();
		rpttVO.setRptt_no(rptt_no);
		rpttVO.setTnt_no(tnt_no);
		rpttVO.setLld_no(lld_no);
		rpttVO.setRptt_content(rptt_content);
		rpttVO.setEmp_no(emp_no);
		rpttVO.setRptt_status(rptt_status);
		rpttVO.setRptt_result(rptt_result);
		rpttVO.setRptt_note(rptt_note);
		dao.update(rpttVO);
		return rpttVO;
	}
	
	public RpttVO updateEmp(String rptt_no, String emp_no) {

		RpttVO rpttVO = new RpttVO();
		rpttVO.setRptt_no(rptt_no);
		rpttVO.setEmp_no(emp_no);
		dao.updateEmp(rpttVO);
		return rpttVO;
	}

	public void deleteRptt(String rptt_no) {
		dao.delete(rptt_no);
	}

	public RpttVO getOneRptt(String rptt_no) {
		return dao.findByPrimaryKey(rptt_no);
	}

	public List<RpttVO> getAllRptt() {
		return dao.getAll();
	}

	public List<RpttVO> getRptt(String Number) {
		return dao.findByNo(Number);
	}
	
}
