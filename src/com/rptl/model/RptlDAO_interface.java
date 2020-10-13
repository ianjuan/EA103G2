package com.rptl.model;

import java.util.List;

import com.rptl.model.RptlVO;

public interface RptlDAO_interface {
	public void insert(RptlVO rptlVO);
	public void update(RptlVO rptlVO);
	public void delete(String rptl_no);
    public RptlVO findByPrimaryKey(String rptl_no);
    public List<RptlVO> findByNo(String Number);
    public List<RptlVO> getAll();
}
