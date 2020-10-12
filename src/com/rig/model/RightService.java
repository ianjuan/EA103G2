package com.rig.model;
import java.util.List;

import com.emp.model.EmployeeVO;


public class RightService {
	private RightDAO_interface dao;
	public RightService() {
		dao = new RightDAO();
	}
	public List<RightVO> getAll(String emp_no) {
		return dao.getAll(emp_no);
	}
	public RightVO addRig(String emp_no, String fun_no) {

		RightVO rigVO = new RightVO();

		rigVO.setEmp_no(emp_no);
		rigVO.setFun_no(fun_no);
		dao.insert(rigVO);
		return rigVO;
	}

}
