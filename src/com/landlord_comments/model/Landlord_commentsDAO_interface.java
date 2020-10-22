package com.landlord_comments.model;

import java.util.List;
import java.util.Set;


public interface Landlord_commentsDAO_interface {

	public void tnt_insert(Landlord_commentsVO Landlord_commentsVO);
	
    public Landlord_commentsVO findByPrimaryKey(String lcm_no);
 
    public List<Landlord_commentsVO> lld_getAll(String lld_no);

    public List<Landlord_commentsVO> tnt_getAll(String tnt_no);
    
	public void lld_update(Landlord_commentsVO landlord_commentsVO);
	
	public void tnt_update(Landlord_commentsVO landlord_commentsVO);

	public Set<String> getAll_lld_no(String tnt_no);

	public List<Landlord_commentsVO> tnt_getAllByLld(String tnt_no, String lld_no);
}
