package com.rpth.model;

import java.util.List;

import com.rpth.model.RpthDAO_interface;
import com.rpth.model.RpthJNDIDAO;
import com.rpth.model.RpthVO;

public class RpthService {
	private RpthDAO_interface dao;

	public RpthService() {

		dao = new RpthJNDIDAO();
	}

	public RpthVO addRpth(String tnt_no, String hos_no, String rpth_content) {

		RpthVO rpthVO = new RpthVO();
		rpthVO.setTnt_no(tnt_no);
		rpthVO.setHos_no(hos_no);
		rpthVO.setRpth_content(rpth_content);
		dao.insert(rpthVO);
		return rpthVO;
	}

	public RpthVO updaterpth(String rpth_no,String tnt_no, String hos_no, String rpth_content, String emp_no,
			Integer rpth_status,Integer rpth_result,String rpth_note) {

		RpthVO rpthVO = new RpthVO();
		rpthVO.setRpth_no(rpth_no);
		rpthVO.setTnt_no(tnt_no);
		rpthVO.setHos_no(hos_no);
		rpthVO.setRpth_content(rpth_content);
		rpthVO.setEmp_no(emp_no);
		rpthVO.setRpth_status(rpth_status);
		rpthVO.setRpth_result(rpth_result);
		rpthVO.setRpth_note(rpth_note);
		dao.update(rpthVO);
		return rpthVO;
	}

	public void deleteRpth(String rpth_no) {
		dao.delete(rpth_no);
	}

	public RpthVO getOneRpth(String rpth_no) {
		return dao.findByPrimaryKey(rpth_no);
	}

	public List<RpthVO> getAllRpth() {
		return dao.getAll();
	}

	public List<RpthVO> getRpth(String Number) {
		return dao.findByNo(Number);
	}
	
}
