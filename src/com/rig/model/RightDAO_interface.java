package com.rig.model;

import java.util.List;

import com.emp.model.EmployeeVO;

public interface RightDAO_interface {
	public void insert(RightVO rightVO);
	public void delete(String emp_no);
	public List<RightVO>getAll(String emp_no);


}