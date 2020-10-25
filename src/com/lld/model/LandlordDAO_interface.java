package com.lld.model;

import java.util.List;

public interface LandlordDAO_interface {
	
	public List<LldVO> getAll_account();
	public LldVO findByPK_account(String lld_no); //暫時沒用到
	public void update_pwd(LldVO lldVO);
	public void update_status(LldVO lldVO);
	
	public void update_pic(LldVO lldVO);
	public LldVO findByPK_pic(String lld_no);

	public void insert_profile(LldVO lldVO);
	public void insert_profile(LldVO lldVO, Boolean updatePic);
    public void update_profile(LldVO lldVO);
    public void update_profile(LldVO lldVO, Boolean updatePic);
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
    public void update_vrf_pics(LldVO lldVO);
    
//    public void update_rpt_auth(LldVO lldVO);
//    public LldVO findByPK_rpt_auth(String lld_no);
    
    public void update_rpt(LldVO lldVO);
    public LldVO findByPK_rpt(String lld_no);
    
    public void update_auth(LldVO lldVO);
    public LldVO findByPK_auth(String lld_no);
    

}
