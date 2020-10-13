package com.rpthc.model;

import java.util.List;

import com.rpthc.model.RpthcVO;

public interface RpthcDAO_interface {
	public void insert(RpthcVO rpthcVO);
	public void update(RpthcVO rpthcVO);
	public void delete(String rpthc_no);
    public RpthcVO findByPrimaryKey(String rpthc_no);
    public List<RpthcVO> findByNo(String Number);
    public List<RpthcVO> getAll();
}
