package com.house_comments.model;

import java.util.List;
import java.util.Set;


public interface House_commentsDAO_interface {
	
	public void tnt_insert(House_commentsVO House_commentsVO);
	
	public void lld_update(House_commentsVO House_commentsVO);
	
    public House_commentsVO findByPrimaryKey(String hcm_no);
  
    public List<House_commentsVO> tnt_getAll(String tnt_no);
    
    public List<House_commentsVO> tnt_getAllByHos(String hos_no, String tnt_no);
    
    public List<House_commentsVO> hos_getAll(String hos_no);

	public Set<String> getAll_hos_no(String tnt_no);

	public void tnt_update(House_commentsVO house_commentsVO);

}

