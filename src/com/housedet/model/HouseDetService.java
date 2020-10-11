package com.housedet.model;

import java.util.List;

//import com.google.gson.Gson;

public class HouseDetService {
	public List<HosDetVO> getHosDetfromHOSNO (String HOS_NO){
//				Gson gson=new Gson();

		HouseDetDAO dao = new HouseDetDAO();
		
		List<HosDetVO> list =dao.getHosDetfromHOSNO(HOS_NO);
		
//		String listStr =gson.toJson(list);
//		System.out.println(listStr);
//		
		return list;
		
	}


}
