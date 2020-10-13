package com.cts.model;

import java.util.List;

import com.cts.model.CtsVO;

public interface CtsDAO_interface {
	public void insert(CtsVO ctsVO);
	public void update(CtsVO ctsVO);
	public void delete(String cts_no);
    public CtsVO findByPrimaryKey(String cts_no);
    public List<CtsVO> findByType(Integer cts_type);
    public List<CtsVO> getAll();
}
