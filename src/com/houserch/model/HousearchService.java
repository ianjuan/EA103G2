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
	public String getMapfromSearchKey(String city,String town,String serachbox,String sort,String money,String house,String page,String cook,String pet,String boy,String girl) {
		System.out.println("排序="+sort);
		int pagevo =Integer.parseInt(page);
		Gson gson = new Gson();
		HousearchVO vo=new HousearchVO();
		 vo.setMoney(money);
		 vo.setHos_type(house);
		 vo.setSort(sort);
		 vo.setCity(city);
		 vo.setTown(town);
		 vo.setSerachbox(serachbox.trim());
		 vo.setPage(pagevo);
		 vo.setBoy(boy);
		 vo.setGirl(girl);
		 vo.setCook(cook);
		 vo.setPet(pet);
		Map<String,HousearchVO> map=dao.getMapfromSearchKey(vo);
		String backmap=gson.toJson(map);
		return backmap;
	}
	public String getGMapfromSearchKey(String city,String town,String searchbox ,String sort,String money,String house,String cook,String pet,String boy,String girl) {
		System.out.println("排序="+sort);
		Gson gson = new Gson();
		HousearchVO vo=new HousearchVO();
		 vo.setMoney(money);
		 vo.setHos_type(house);
		 vo.setSort(sort);
		 vo.setCity(city);
		 vo.setTown(town);
		 vo.setBoy(boy);
		 vo.setGirl(girl);
		 vo.setCook(cook);
		 vo.setPet(pet);
		 vo.setSerachbox(searchbox.trim());
		Map<String,HousearchVO> map=dao.getGMapfromSearchKey(vo);
		String backmap=gson.toJson(map);
		return backmap;
	}
	
	public String getAll() {
		Map<String,HousearchVO> map=dao.getAll();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		
		return jsonStr;
	}
	public String getAllGmap() {
		Map<String,HousearchVO> map=dao.getAllGmap();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		
		return jsonStr;
	}
}
