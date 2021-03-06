package com.housemanage.model;

import java.util.*;

public interface HouseDAO_interface {
	public void insertHouseInfo(HouseVO houseVO, List<HouseVO> hos_picArr);
	public void updateHouseInfo(HouseVO houseVO, List<HouseVO> hos_picArr, String[] pic_no, Integer differday);
	public void updateHouseFurniture(HouseVO houseVO);
	public void updateStatus(HouseVO houseVO);
	public HouseVO getLldInfo(String lld_no);
	public HouseVO getHosno(String lld_no);
	public HouseVO getHouseInfo(String hos_no);
	public HouseVO getHouseWaterfee(String hos_no);
	public HouseVO getHouseElectfee(String hos_no);
	public List<HouseVO> getLldHousePic(String hos_no);
	public List<HouseVO> getLldAllHouse(String lld_no);
	public List<HouseVO> getLldRentHouse(String lld_no);
	public List<HouseVO> getLldUnRentHouse(String lld_no);
	public List<HouseVO> getLldOffHouse(String lld_no);
	public void deleteHouseInfo(String hos_no);
	public List<HouseVO> getAllHouse();
	public HouseVO addMoney(HouseVO houseVO);
}
