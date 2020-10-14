package com.house_comments.model;

import java.util.List;


public interface House_commentsDAO_interface {
	
	public void tnt_insert(House_commentsVO House_commentsVO);
	
	public void lld_update(House_commentsVO House_commentsVO);
	
    public House_commentsVO findByPrimaryKey(String hcm_no);
  
    public List<House_commentsVO> tnt_getAll(String tnt_no);
    
    public List<House_commentsVO> hos_getAll(String hos_no);

}

