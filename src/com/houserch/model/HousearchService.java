package com.houserch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HousearchService {

	private HousearchDAO dao;
	
	public HousearchService() {
			dao=new HousearchDAO();
		}
	public String getMapfromSearchKey(String add,String sort,String money,String house) {
		System.out.println(add+"排序="+sort);
		Gson gson = new Gson();
		HousearchVO vo=new HousearchVO();
		 vo.setMoney(money);
		 vo.setHos_type(house);
		 vo.setSort(sort);
		 vo.setHos_add(add);
		Map<String,HousearchVO> map=dao.getMapfromSearchKey(vo);

//					Map<String,GmapVO> map=dao.getMapfromSearchKey(jsonStri,jsonStro,jsonStrm,jsonStrh);
//		System.out.println("我要回去囉"+map);
		String backmap=gson.toJson(map);
//		System.out.println("我要回去囉"+backmap);

		return backmap;
	}
	
	public String getAll() {
		Map<String,HousearchVO> map=dao.getAll();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		
		return jsonStr;
	}
}
