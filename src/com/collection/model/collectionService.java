package com.collection.model;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class collectionService {

	private CollectionDAO dao;
	
	public collectionService() {
			dao=new CollectionDAO();
		}
	public String getCollectionVOfromTNTNO(String i) {
		List<CollectionVO> list=dao.getCollectionVOfromTNTNO(i);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
	public String getAllCollectionVOfromTNTNO(String tnt_no ,String hos_price,String hos_type,String hos_pnum,String hos_report) {
		CollectionVO vo=new CollectionVO();
		vo.setTnt_no(tnt_no);
		vo.setHos_price(hos_price);
		vo.setHos_type(hos_type);
		vo.setHos_pnum(hos_pnum);
		vo.setHos_report(hos_report);
		List<CollectionVO> list=dao.getAllCollectionVOfromTNTNO(vo);
				
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
	public void deleteallCollection(String tnt_no, String HOS_NO) {
		Gson gson = new Gson();
		ArrayList<String> list =gson.fromJson(HOS_NO,new TypeToken<ArrayList<String>>(){}.getType());
		dao.deleteallCollection(tnt_no, list);
}}

