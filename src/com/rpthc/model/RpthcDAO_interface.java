package com.rpthc.model;

import java.util.List;

import com.rpthc.model.RpthcVO;

public interface RpthcDAO_interface {
	public void insert(RpthcVO rpthcVo);
	public void update(RpthcVO rpthcVo);
	public void updateEmp(RpthcVO rpthcVo);
	public void assignEmp(RpthcVO rpthcVo);
	public void saveNote(RpthcVO rpthcVo);
	public void fail(RpthcVO rpthcVo);
	public void delete(String rpthc_no);
    public RpthcVO findByPrimaryKey(String rpthc_no);
    public List<RpthcVO> findByNo(String Number);
    public List<RpthcVO> getAll();
}
