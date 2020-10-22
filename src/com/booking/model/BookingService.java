package com.booking.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class BookingService {

	private BookingDAO dao;
		
	public BookingService() {
			dao=new BookingDAO();
		}
	public String getBookingInfoListByhosno(String hos_no) {
		List<BookingVO> list=dao.getBookingInfoListByhosno(hos_no);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		return jsonStr;
	}
	public String getBookingInfoListBylldno(String lldno) {
		List<BookingVO> list=dao.getBookingInfoListBylldno(lldno);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
	public String getResOrderbylldno(String lldno) {
		List<BookingVO> list=dao.getResOrderbylldno(lldno);
		for(int i=0;i<list.size();i++) {
			String date=list.get(i).getOrder_date();
			String[] month=date.split("-");
			String[] day=month[2].split(" ");
			String[] time=day[1].split(":");
			list.get(i).setOrder_date(month[1]+"月"+day[0]+"日"+time[0]+"點"+time[1]+"分");
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
	public ArrayList<String> change(String strings) {
		Gson gson = new Gson();
		ArrayList<String> list = gson.fromJson(strings, new TypeToken<ArrayList<String>>(){}.getType());
		
		return list;

	}
	public void insertorder(String resdno,String house,String date,String tntno,String type,String resstatus) {
		BookingVO	vo=new BookingVO();
		
			dao.update(resdno);
			String a[] = date.split("T");
			String order_date=a[0]+" "+a[1];
		vo.setResd_no(resdno);
		vo.setHos_no(house);
		vo.setOrder_date(order_date);
		vo.setTnt_no(tntno);
		vo.setRes_type(type);
		vo.setRes_status(resstatus);
		
			dao.insertorder(vo);
		
	}
	
	
		
}
