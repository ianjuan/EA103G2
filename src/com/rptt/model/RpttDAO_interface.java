package com.rptt.model;

import java.util.List;

public interface RpttDAO_interface {

	public void insert(RpttVO rpttVo);
	public void update(RpttVO rpttVo);
	public void updateEmp(RpttVO rpttVo);
	public void delete(String rptt_no);
    public RpttVO findByPrimaryKey(String rptt_no);
    public List<RpttVO> findByNo(String Number);
    public List<RpttVO> getAll();
	 
}
