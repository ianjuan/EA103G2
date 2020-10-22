package com.rptt.model;

import java.util.List;

public interface TenantDAO_interface {
	

	public void insert_profile(TntVO tntVO);
    public void update_profile(TntVO tntVO);
    public TntVO findByPK_profile(String tnt_no);
    public List<TntVO> getAll_profile();
    public void update_vrf(TntVO tntVO);
    public TntVO findByPK_vrf(String tnt_no);
    public List<TntVO> getAll_vrf();
    public List<TntVO> get_unvrf(String Number);
	public TntVO findByPK_pic(String tnt_no);
    


}
