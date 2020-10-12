package com.fun.model;

import java.util.List;



public interface FunctionDAO_interface {
	public void insert(FunctionVO functionVO);
	public FunctionVO findByPrimaryKey(String fun_no);
	public List<FunctionVO>getAll();
	public void update(FunctionVO functionVO);
	//public void delete(EmployeeVO employeeVO);

}