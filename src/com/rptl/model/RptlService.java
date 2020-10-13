package com.rptl.model;

import java.util.List;

public class RptlService {

	private RptlDAO_interface dao;

	public RptlService() {

		dao = new RptlJNDIDAO();
	}

	public RptlVO addRptl(String tnt_no, String lld_no, String rptl_content) {

		RptlVO rptlVO = new RptlVO();
		rptlVO.setTnt_no(tnt_no);
		rptlVO.setLld_no(lld_no);
		rptlVO.setRptl_content(rptl_content);
		dao.insert(rptlVO);
		return rptlVO;
	}

	public RptlVO updaterptl(String rptl_no,String tnt_no, String lld_no, String rptl_content, String emp_no,
			Integer rptl_status,Integer rptl_result,String rptl_note) {

		RptlVO rptlVO = new RptlVO();
		rptlVO.setRptl_no(rptl_no);
		rptlVO.setTnt_no(tnt_no);
		rptlVO.setLld_no(lld_no);
		rptlVO.setRptl_content(rptl_content);
		rptlVO.setEmp_no(emp_no);
		rptlVO.setRptl_status(rptl_status);
		rptlVO.setRptl_result(rptl_result);
		rptlVO.setRptl_note(rptl_note);
		dao.update(rptlVO);
		return rptlVO;
	}

	public void deleteRptl(String rptl_no) {
		dao.delete(rptl_no);
	}

	public RptlVO getOneRptl(String rptl_no) {
		return dao.findByPrimaryKey(rptl_no);
	}

	public List<RptlVO> getAllRptl() {
		return dao.getAll();
	}

	public List<RptlVO> getRptl(String Number) {
		return dao.findByNo(Number);
	}
	
}
