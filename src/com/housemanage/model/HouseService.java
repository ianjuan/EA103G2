package com.housemanage.model;

import java.util.List;

public class HouseService {
	private HouseDAO_interface dao;

	public HouseService() {
		dao = new HouseDAO();
	}

	public HouseVO insertHouseInfo(String lld_no, String hos_name, String hos_liffun, String hos_trans, String hos_add,
			String hos_type, String hos_room, String hos_pat, String hos_floor, Double hos_pnum, Double hos_lng,
			Double hos_lat, String hos_status, Integer hos_table, Integer hos_chair, Integer hos_bed,
			Integer hos_closet, Integer hos_sofa, Integer hos_tv, Integer hos_drink, Integer hos_aircon,
			Integer hos_refrig, Integer hos_wash, Integer hos_hoter, Integer hos_forth, Integer hos_net,
			Integer hos_gas, String hos_mdate, String hos_mindate, String hos_park, String hos_sex, String hos_iden,
			String hos_pet, String hos_cook, String hos_smoke, Integer hos_rentfee, Integer hos_gasfee,
			Integer hos_manafee, Integer hos_netfee, Integer hos_puwaterfee, Integer hos_puelefee,
			Integer hos_parkfee, Integer hos_bro, Integer hos_waterfeetype, Double hos_waterfee,
			Integer hos_electfeetype, Double hos_electfee, List<HouseVO> hos_picArr) {
		HouseVO houseVO = new HouseVO();

		houseVO.setLld_no(lld_no);
		houseVO.setHos_name(hos_name);
		houseVO.setHos_liffun(hos_liffun);
		houseVO.setHos_trans(hos_trans);
		houseVO.setHos_add(hos_add);
		houseVO.setHos_type(hos_type);
		houseVO.setHos_room(hos_room);
		houseVO.setHos_pat(hos_pat);
		houseVO.setHos_floor(hos_floor);
		houseVO.setHos_pnum(hos_pnum);
		houseVO.setHos_lng(hos_lng);
		houseVO.setHos_lat(hos_lat);
		houseVO.setHos_status(hos_status);
		houseVO.setHos_table(hos_table);
		houseVO.setHos_chair(hos_chair);
		houseVO.setHos_bed(hos_bed);
		houseVO.setHos_closet(hos_closet);
		houseVO.setHos_sofa(hos_sofa);
		houseVO.setHos_tv(hos_tv);
		houseVO.setHos_drink(hos_drink);
		houseVO.setHos_aircon(hos_aircon);
		houseVO.setHos_refrig(hos_refrig);
		houseVO.setHos_wash(hos_wash);
		houseVO.setHos_hoter(hos_hoter);
		houseVO.setHos_forth(hos_forth);
		houseVO.setHos_net(hos_net);
		houseVO.setHos_gas(hos_gas);		
		houseVO.setHos_mdate(hos_mdate);
		houseVO.setHos_mindate(hos_mindate);
		houseVO.setHos_park(hos_park);
		houseVO.setHos_sex(hos_sex);
		houseVO.setHos_iden(hos_iden);
		houseVO.setHos_pet(hos_pet);
		houseVO.setHos_cook(hos_cook);
		houseVO.setHos_smoke(hos_smoke);
		houseVO.setHos_rentfee(hos_rentfee);
		houseVO.setHos_gasfee(hos_gasfee);
		houseVO.setHos_manafee(hos_manafee);
		houseVO.setHos_netfee(hos_netfee);
		houseVO.setHos_puwaterfee(hos_puwaterfee);
		houseVO.setHos_puelefee(hos_puelefee);
		houseVO.setHos_parkfee(hos_parkfee);
		houseVO.setHos_bro(hos_bro);
		houseVO.setHos_waterfeetype(hos_waterfeetype);
		houseVO.setHos_waterfee(hos_waterfee);
		houseVO.setHos_electfeetype(hos_electfeetype);
		houseVO.setHos_electfee(hos_electfee);
		dao.insertHouseInfo(houseVO, hos_picArr);

		return houseVO;
	}
	
	public HouseVO updateHouseInfo(String hos_name, String hos_liffun, String hos_trans, String hos_add,
			String hos_type, String hos_room, String hos_pat, String hos_floor, Double hos_pnum, Double hos_lng,
			Double hos_lat, String hos_status, Integer hos_table, Integer hos_chair, Integer hos_bed,
			Integer hos_closet, Integer hos_sofa, Integer hos_tv, Integer hos_drink, Integer hos_aircon,
			Integer hos_refrig, Integer hos_wash, Integer hos_hoter, Integer hos_forth, Integer hos_net,
			Integer hos_gas, String hos_mdate, String hos_mindate, String hos_park, String hos_sex, String hos_iden,
			String hos_pet, String hos_cook, String hos_smoke, Integer hos_rentfee, Integer hos_gasfee,
			Integer hos_manafee, Integer hos_netfee, Integer hos_puwaterfee, Integer hos_puelefee, Integer hos_parkfee,
			Integer hos_waterfeetype, Double hos_waterfee, Integer hos_electfeetype, Double hos_electfee,
			List<HouseVO> hos_picArr, String[] pic_no, String hos_no) {
		HouseVO houseVO = new HouseVO();

		houseVO.setHos_name(hos_name);
		houseVO.setHos_liffun(hos_liffun);
		houseVO.setHos_trans(hos_trans);
		houseVO.setHos_add(hos_add);
		houseVO.setHos_type(hos_type);
		houseVO.setHos_room(hos_room);
		houseVO.setHos_pat(hos_pat);
		houseVO.setHos_floor(hos_floor);
		houseVO.setHos_pnum(hos_pnum);
		houseVO.setHos_lng(hos_lng);
		houseVO.setHos_lat(hos_lat);
		houseVO.setHos_status(hos_status);
		houseVO.setHos_table(hos_table);
		houseVO.setHos_chair(hos_chair);
		houseVO.setHos_bed(hos_bed);
		houseVO.setHos_closet(hos_closet);
		houseVO.setHos_sofa(hos_sofa);
		houseVO.setHos_tv(hos_tv);
		houseVO.setHos_drink(hos_drink);
		houseVO.setHos_aircon(hos_aircon);
		houseVO.setHos_refrig(hos_refrig);
		houseVO.setHos_wash(hos_wash);
		houseVO.setHos_hoter(hos_hoter);
		houseVO.setHos_forth(hos_forth);
		houseVO.setHos_net(hos_net);
		houseVO.setHos_gas(hos_gas);
		houseVO.setHos_mdate(hos_mdate);
		houseVO.setHos_mindate(hos_mindate);
		houseVO.setHos_park(hos_park);
		houseVO.setHos_sex(hos_sex);
		houseVO.setHos_iden(hos_iden);
		houseVO.setHos_pet(hos_pet);
		houseVO.setHos_cook(hos_cook);
		houseVO.setHos_smoke(hos_smoke);
		houseVO.setHos_rentfee(hos_rentfee);
		houseVO.setHos_gasfee(hos_gasfee);
		houseVO.setHos_manafee(hos_manafee);
		houseVO.setHos_netfee(hos_netfee);
		houseVO.setHos_puwaterfee(hos_puwaterfee);
		houseVO.setHos_puelefee(hos_puelefee);
		houseVO.setHos_parkfee(hos_parkfee);
		houseVO.setHos_waterfeetype(hos_waterfeetype);
		houseVO.setHos_waterfee(hos_waterfee);
		houseVO.setHos_electfeetype(hos_electfeetype);
		houseVO.setHos_electfee(hos_electfee);
		houseVO.setHos_no(hos_no);
		dao.updateHouseInfo(houseVO, hos_picArr, pic_no);

		return houseVO;
	}
	
	public HouseVO getHouseInfo(String hos_no) {
		return dao.getHouseInfo(hos_no);
	}

	public HouseVO getHouseWaterfee(String hos_no) {
		return dao.getHouseWaterfee(hos_no);
	}
	
	public HouseVO getHouseElectfee(String hos_no) {
		return dao.getHouseElectfee(hos_no);
	}
	
	public List<HouseVO> getLldHousePic(String hos_no) {
		return dao.getLldHousePic(hos_no);
	}
	
	public List<HouseVO> getLldRentHouse(String lld_no) {
		return dao.getLldRentHouse(lld_no);
	}

	public List<HouseVO> getLldUnRentHouse(String lld_no) {
		return dao.getLldUnRentHouse(lld_no);
	}
	
	public List<HouseVO> getLldOffHouse(String lld_no) {
		return dao.getLldOffHouse(lld_no);
	}

	public void deleteHouseInfo(String hos_no) {
		dao.deleteHouseInfo(hos_no);
	}

	public List<HouseVO> getAllHouse() {
		return dao.getAllHouse();
	}
}
