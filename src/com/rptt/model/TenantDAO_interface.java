package com.rptt.model;

import java.util.List;

public interface TenantDAO_interface {
	
	public List<TntVO> getAll_account();

	public void insert_profile(TntVO tntVO);
    public void update_profile(TntVO tntVO);
    public TntVO findByPK_profile(String tnt_no);
    public List<TntVO> getAll_profile();
    
    public void update_pocket(TntVO tntVO);
    public TntVO findByPK_pocket(String tnt_no);
    
    public void update_bank_card(TntVO tntVO);
    public TntVO findByPK_bank_card(String tnt_no);
    
    public void update_cmt(TntVO tntVO);
    public TntVO findByPK_cmt(String tnt_no);
    
    public void update_vrf(TntVO tntVO);
    public TntVO findByPK_vrf(String tnt_no);
    public List<TntVO> getAll_vrf();
    
//    public void update_rpt_auth(TntVO tntVO);
//    public TntVO findByPK_rpt_auth(String tnt_no);
    
    public void update_rpt(TntVO tntVO);
    public TntVO findByPK_rpt(String tnt_no);
    
    public void update_auth(TntVO tntVO);
    public TntVO findByPK_auth(String tnt_no);
    

}
