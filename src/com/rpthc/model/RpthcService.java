package com.rpthc.model;

import java.util.List;

import com.rpthc.model.RpthcDAO_interface;
import com.rpthc.model.RpthcJNDIDAO;
import com.rpthc.model.RpthcVO;

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

	public RpthcVO updateRpthc(String rpthc_no,String hcm_no, String lld_no, String rpthc_content, String emp_no,
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
