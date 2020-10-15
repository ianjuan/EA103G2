package com.housedet.model;

import java.util.List;

//import com.google.gson.Gson;

public class HouseDetService {
	public List<HosDetVO> getHosDetfromHOSNO (String hos_no){
//				Gson gson=new Gson();

		HouseDetDAO dao = new HouseDetDAO();
		
		List<HosDetVO> list =dao.getHosDetfromHOSNO(hos_no);
		
//		String listStr =gson.toJson(list);
//		System.out.println(listStr);
//		
		return list;
		
	}


}
