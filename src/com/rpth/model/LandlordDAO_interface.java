package com.rpth.model;

import java.util.List;

public interface LandlordDAO_interface {
	

	public void insert_profile(LldVO lldVO);
    public void update_profile(LldVO lldVO);
    public LldVO findByPK_profile(String lld_no);
    public List<LldVO> getAll_profile();
	public LldVO findByPK_pic(String lld_no);
	public List<LldVO> findByNo(String number);
	public LldVO findEmail(String lld_no);
    public void update_auth(LldVO lldvo);



}
