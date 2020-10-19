package com.emp.model;

import java.util.List;

//DAO CRUD
public interface EmployeeDAO_interface {
	public String insert(EmployeeVO employeeVO);
	public EmployeeVO findByPrimaryKey(String emp_no);
	public List<EmployeeVO>getAll();
	public void update(EmployeeVO employeeVO);
	//public void delete(EmployeeVO employeeVO);

}
