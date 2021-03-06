package com.rptt.model;

import java.util.List;

public interface TenantDAO_interface {
	

	public void insert_profile(TntVO tntVO);
    public void update_profile(TntVO tntVO);
    public TntVO findByPK_profile(String tnt_no);
    public TntVO findByPK_profile1(String tnt_no);
    public List<TntVO> getAll_profile();
    public void update_vrf(TntVO tntVO);
    public TntVO findByPK_vrf(String tnt_no);
    public List<TntVO> getAll_vrf(Integer number, Integer number2);
    public List<TntVO> get_unvrf(String Number);
	public TntVO findByPK_pic(String tnt_no);
    public void pass_Vrf(TntVO tntVO);
    public void fail_Vrf(TntVO tntVO);
	public List<TntVO> getUnvrf_Unresult(Integer number, Integer number2);
	public List<TntVO> findByNo(String number);
	public TntVO findEmail(String tnt_no);
    public void update_auth(TntVO tntvo);



}
