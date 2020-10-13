package com.rptrp.model;

import java.util.List;


public interface RptrpDAO_interface {
	
    public void insert(RptrpVO rptrpVO);
    public void update(RptrpVO rptrpVO);
    public void delete(String rptrp_no);
    public RptrpVO findByPrimaryKey(String rptrp_no);
    public List<RptrpVO> findByRptr(String rptr_no);
    public List<RptrpVO> getAll();
    
}
