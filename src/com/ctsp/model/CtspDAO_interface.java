package com.ctsp.model;

import java.util.List;

import com.ctsp.model.CtspVO;

public interface CtspDAO_interface {
	 public void insert(CtspVO ctspVO);
	    public void update(CtspVO ctspVO);
	    public void delete(String ctsp_no);
	    public CtspVO findByPrimaryKey(String ctsp_no);
	    public List<CtspVO> findByCts(String cts_no);
	    public List<CtspVO> getAll();
}
