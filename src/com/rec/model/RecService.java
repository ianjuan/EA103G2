package com.rec.model;

import java.util.List;

public class RecService {
	
	private RecDAO_interface dao;
	
	public RecService() {
		dao = new RecDAO();
	}
	
	public RecVO addRecFromLld(String con_no, String hos_no, Integer rec_mon, 
			Integer rec_water, Integer rec_elec, Integer rec_sta) {
		
		RecVO recVO = new RecVO();
		
		recVO.setCon_no(con_no);
		recVO.setHos_no(hos_no);
		recVO.setRec_mon(rec_mon);
		recVO.setRec_water(rec_water);
		recVO.setRec_elec(rec_elec);
		recVO.setRec_sta(rec_sta);
		dao.insert(recVO);
		
		return recVO;
	}
	
	public RecVO updateRec(String con_no, String hos_no, Integer rec_mon, 
			Integer rec_water, Integer rec_elec, String rec_no, Integer rec_sta) {
		
		RecVO recVO = new RecVO();
		
		recVO.setCon_no(con_no);
		recVO.setHos_no(hos_no);
		recVO.setRec_mon(rec_mon);
		recVO.setRec_water(rec_water);
		recVO.setRec_elec(rec_elec);
		recVO.setRec_no(rec_no);
		recVO.setRec_sta(rec_sta);
		dao.update(recVO);
		
		return recVO;
	}
	
	public RecVO updateRecFromLld(Integer rec_water, Integer rec_elec, String rec_no, Integer rec_sta) {
		
		RecVO recVO = new RecVO();
		
		recVO.setRec_water(rec_water);
		recVO.setRec_elec(rec_elec);
		recVO.setRec_no(rec_no);
		recVO.setRec_sta(rec_sta);
		dao.update(recVO);
		
		return recVO;
	}
	
	public RecVO getOneRec(String rec_no) {
		return dao.findByPrimaryKey(rec_no);
	}
	
	
	public List<RecVO> getLddAllByCon(String con_no){
		return dao.getLddAllByCon(con_no);
	}
	
	public RecStatus[] getRecStatusall() {
		return RecStatus.values();
	}
	
	public String getRecStatusText(Integer num) {
		return RecStatus.findByPrimaryKey(num).getText();
	}
	

}
