package com.rpttc.model;

import java.util.List;

import com.rpttc.model.RpttcDAO_interface;
import com.rpttc.model.RpttcJNDIDAO;
import com.rpttc.model.RpttcVO;

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

	public RpttcVO updateRpttc(String rpttc_no,String tcm_no, String tnt_no, String rpttc_content, String emp_no,
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
