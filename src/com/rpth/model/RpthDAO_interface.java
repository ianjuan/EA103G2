package com.rpth.model;

import java.util.List;

import com.rpth.model.RpthVO;

public interface RpthDAO_interface {
	public void insert(RpthVO rpthVO);
	public void update(RpthVO rpthVO);
	public void delete(String rpth_no);
    public RpthVO findByPrimaryKey(String rpth_no);
    public List<RpthVO> findByNo(String Number);
    public List<RpthVO> getAll();
}
