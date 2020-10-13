package com.rptlc.model;

import java.util.List;

import com.rptlc.model.RptlcDAO_interface;
import com.rptlc.model.RptlcJNDIDAO;
import com.rptlc.model.RptlcVO;

public class RptlcService {
	private RptlcDAO_interface dao;

	public RptlcService() {

		dao = new RptlcJNDIDAO();
	}

	public RptlcVO addRptlc(String lcm_no, String lld_no, String rptlc_content) {

		RptlcVO rptlcVO = new RptlcVO();
		rptlcVO.setLcm_no(lcm_no);
		rptlcVO.setLld_no(lld_no);
		rptlcVO.setRptlc_content(rptlc_content);
		dao.insert(rptlcVO);
		return rptlcVO;
	}

	public RptlcVO updaterptlc(String rptlc_no,String lcm_no, String lld_no, String rptlc_content, String emp_no,
			Integer rptlc_status,Integer rptlc_result,String rptlc_note) {

		RptlcVO rptlcVO = new RptlcVO();
		rptlcVO.setRptlc_no(rptlc_no);
		rptlcVO.setLcm_no(lcm_no);
		rptlcVO.setLld_no(lld_no);
		rptlcVO.setRptlc_content(rptlc_content);
		rptlcVO.setEmp_no(emp_no);
		rptlcVO.setRptlc_status(rptlc_status);
		rptlcVO.setRptlc_result(rptlc_result);
		rptlcVO.setRptlc_note(rptlc_note);
		dao.update(rptlcVO);
		return rptlcVO;
	}

	public void deleteRptlc(String rptlc_no) {
		dao.delete(rptlc_no);
	}

	public RptlcVO getOneRptlc(String rptlc_no) {
		return dao.findByPrimaryKey(rptlc_no);
	}

	public List<RptlcVO> getAllRptlc() {
		return dao.getAll();
	}

	public List<RptlcVO> getRptlc(String Number) {
		return dao.findByNo(Number);
	}
}
