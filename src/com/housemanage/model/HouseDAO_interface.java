package com.housemanage.model;

import java.util.*;

public interface HouseDAO_interface {
	public void insertHouseInfo(HouseVO houseVO);
	public void updateHouseInfo(HouseVO houseVO);
	public HouseVO getHouseInfo(String hos_no);
	public HouseVO getHouseWaterfee(String hos_no);
	public HouseVO getHouseElectfee(String hos_no);
	public HouseVO getHouseLld(String lld_no);
	public List<HouseVO> getLldRentHouse(String lld_no);
	public List<HouseVO> getLldUnRentHouse(String lld_no);
	
	public void deleteHouseInfo(String hos_no);
	public List<HouseVO> getAllHouse();
}
