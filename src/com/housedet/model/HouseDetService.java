package com.housedet.model;

import java.util.List;


public class HouseDetService {
	public List<HosDetVO> getHosDetfromHOSNO (String hos_no){

		HouseDetDAO dao = new HouseDetDAO();
		
		List<HosDetVO> list =dao.getHosDetfromHOSNO(hos_no);
		
	
		return list;
		
	}


}
