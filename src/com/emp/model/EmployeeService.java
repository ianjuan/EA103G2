package com.emp.model;
import java.util.List;


public class EmployeeService {
	private EmployeeDAO_interface dao;
	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	public EmployeeVO getOneEmp(String emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	
	public EmployeeVO addEmp(String emp_acc, String emp_pwd,Integer emp_title,
			String emp_name) {

		EmployeeVO empVO = new EmployeeVO();

		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_title(emp_title);
		empVO.setEmp_name(emp_name);
		dao.insert(empVO);
		return empVO;
	}

	public EmployeeVO updateEmp(String emp_no,String emp_acc,String emp_pwd, Integer emp_title,String emp_name,
			Integer emp_is_delete ,byte[] emp_pic) {

		EmployeeVO empVO = new EmployeeVO();
		empVO.setEmp_no(emp_no);
		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_title(emp_title);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_is_delete(emp_is_delete);
		empVO.setEmp_pic(emp_pic);
		dao.update(empVO);

		return empVO;
	}
}
