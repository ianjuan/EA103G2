package com.rec.model;

import java.util.List;

public interface RecDAO_interface {
	public void insert(RecVO recVO);
	public void autorec(RecVO recVO);
	public void update(RecVO recVO);
	public void lldupdate(RecVO recVO);
	public void delete(String rec_no);
	public RecVO findByPrimaryKey(String rec_no);
	public List<RecVO> getAll();
	public List<RecVO> getLddAllByCon(String con_no);
}
