package com.lld.model;

import java.util.List;

public interface LandlordDAO_interface {
	public void insert_profile(LldVO lldVO);
    public void update_profile(LldVO lldVO);
    public LldVO findByPK_profile(String lld_no);
    public List<LldVO> getAll_profile();
    
    public void update_pocket(LldVO lldVO);
    public LldVO findByPK_pocket(String lld_no);
    
    public void update_bank_card(LldVO lldVO);
    public LldVO findByPK_bank_card(String lld_no);
    
    public void update_cmt(LldVO lldVO);
    public LldVO findByPK_cmt(String lld_no);
    
    public void update_vrf(LldVO lldVO);
    public LldVO findByPK_vrf(String lld_no);
    public List<LldVO> getAll_vrf();
    
    public void update_rpt_auth(LldVO lldVO);
    public LldVO findByPK_rpt_auth(String lld_no);
    

}
