package com.rptl.model;

import java.util.List;

import com.rptl.model.RptlVO;

public interface RptlDAO_interface {
	public void insert(RptlVO rptlVo);
	public void update(RptlVO rptlVo);
	public void updateEmp(RptlVO rptlVo);
	public void assignEmp(RptlVO rptlVo);
	public void saveNote(RptlVO rptlVo);
	public void fail(RptlVO rptlVo);
	public void delete(String rptl_no);
    public RptlVO findByPrimaryKey(String rptl_no);
    public List<RptlVO> findByNo(String Number);
    public List<RptlVO> getAll();
}
