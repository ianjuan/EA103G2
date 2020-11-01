package com.rpth.model;

import java.util.List;


public interface RpthDAO_interface {

	public void insert(RpthVO rpthVo);
	public void update(RpthVO rpthVo);
	public void updateEmp(RpthVO rpthVo);
	public void assignEmp(RpthVO rpthVo);
	public void saveNote(RpthVO rpthVo);
	public void fail(RpthVO rpthVo);
	public void delete(String rpth_no);
    public RpthVO findByPrimaryKey(String rpth_no);
    public List<RpthVO> findByNo(String Number);
    public List<RpthVO> getAll();

	 
}
