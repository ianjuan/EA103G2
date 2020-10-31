package com.rpth.model;

import java.util.List;

public interface LandlordDAO_interface {
	

	public void insert_profile(LldVO lldVO);
    public void update_profile(LldVO lldVO);
    public LldVO findByPK_profile(String lld_no);
    public LldVO findByPK_profile1(String lld_no);
    public List<LldVO> getAll_profile();
    public void update_vrf(LldVO lldVO);
    public LldVO findByPK_vrf(String lld_no);
    public List<LldVO> getAll_vrf(Integer number, Integer number2);
    public List<LldVO> get_unvrf(String Number);
	public LldVO findByPK_pic(String lld_no);
    public void pass_Vrf(LldVO lldVO);
    public void fail_Vrf(LldVO lldVO);
	public List<LldVO> getUnvrf_Unresult(Integer number, Integer number2);
	public List<LldVO> findByNo(String number);
	public LldVO findEmail(String lld_no);
    public void update_auth(LldVO lldvo);



}
