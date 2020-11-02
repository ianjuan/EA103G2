package com.rptlc.model;

import java.util.List;

import com.rptlc.model.RptlcVO;

public interface RptlcDAO_interface {
	public void insert(RptlcVO rptlcVo);
	public void update(RptlcVO rptlcVo);
	public void updateEmp(RptlcVO rptlcVo);
	public void assignEmp(RptlcVO rptlcVo);
	public void saveNote(RptlcVO rptlcVo);
	public void fail(RptlcVO rptlcVo);
	public void delete(String rptlc_no);
    public RptlcVO findByPrimaryKey(String rptlc_no);
    public List<RptlcVO> findByNo(String Number);
    public List<RptlcVO> getAll();
}
