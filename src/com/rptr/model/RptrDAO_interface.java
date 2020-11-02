package com.rptr.model;

import java.util.List;

import com.rptr.model.RptrVO;

public interface RptrDAO_interface {
	public void insert(RptrVO rptrVo);
	public void update(RptrVO rptrVo);
	public void updateEmp(RptrVO rptrVo);
	public void assignEmp(RptrVO rptrVo);
	public void saveNote(RptrVO rptrVo);
	public void fail(RptrVO rptrVo);
	public void delete(String rptr_no);
    public RptrVO findByPrimaryKey(String rptr_no);
    public List<RptrVO> findByNo(String Number);
    public List<RptrVO> getAll();
}
