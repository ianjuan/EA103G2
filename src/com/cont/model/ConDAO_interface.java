package com.cont.model;

import java.util.List;
import com.cont.model.ConVO;

public interface ConDAO_interface {
	public void insert(ConVO conVO);
	public void update(ConVO conVO);
	public void delete(String con_no);
	public ConVO findByPrimaryKey(String con_no);
	public List<ConVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
