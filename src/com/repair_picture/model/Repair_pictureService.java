package com.repair_picture.model;

import java.util.List;

import com.repair.*;


public class Repair_pictureService {

	private Repair_pictureDAO_interface dao;
	
	public Repair_pictureService() {
		dao = new Repair_pictureJDBCDAO();
	}
	
	public Repair_pictureVO addRepair_picture(String reppic_no, String rep_no, byte[] reppic_pic) {
		Repair_pictureVO repair_pictureVO= new Repair_pictureVO();
		
		repair_pictureVO.setReppic_no(reppic_no);
		repair_pictureVO.setRep_no(rep_no);
		repair_pictureVO.setReppic_pic(reppic_pic);
		dao.insert(repair_pictureVO);
		return repair_pictureVO;
		
	}
	
	public List<Repair_pictureVO> getRepair_picture(String rep_no){
		return dao.lld_getAll(rep_no);
	}
	
	
	
	
}
