package com.rpttc.model;

import java.util.List;

import com.rpttc.model.RpttcVO;

public interface RpttcDAO_interface {
	public void insert(RpttcVO rpttcVO);
	public void update(RpttcVO rpttcVO);
	public void delete(String rpttc_no);
    public RpttcVO findByPrimaryKey(String rpttc_no);
    public List<RpttcVO> findByNo(String Number);
    public List<RpttcVO> getAll();
}
