package com.tenant_comments.model;

import java.util.List;
import java.util.Set;


public interface Tenant_commentsDAO_interface {
	
	public void lld_insert(Tenant_commentsVO Tenant_commentsVO);
	
    public void lld_update(Tenant_commentsVO tenant_commentsVO);
	
	public void tnt_insert(Tenant_commentsVO Tenant_commentsVO);
    
    public Tenant_commentsVO findByPrimaryKey(String tcm_no);
   
    public List<Tenant_commentsVO> lld_getAll(String lld_no);
    
    public List<Tenant_commentsVO> tnt_getAll(String tnt_no);

	public Set<String> lld_getAllTnt(String lld_no);

	public List<Tenant_commentsVO> lld_getAllTntTcm(String tnt_no, String lld_no);
	


   
}
