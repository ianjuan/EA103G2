package com.rptlc.model;

import java.util.List;

import com.rptlc.model.RptlcVO;

public interface RptlcDAO_interface {
	public void insert(RptlcVO rptlcVO);
	public void update(RptlcVO rptlcVO);
	public void delete(String rptlc_no);
    public RptlcVO findByPrimaryKey(String rptlc_no);
    public List<RptlcVO> findByNo(String Number);
    public List<RptlcVO> getAll();
}
