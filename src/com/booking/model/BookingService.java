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
	public String getinfobyid(String hos_no) {
		List<BookingVO> list=dao.getBookingInfoListByhosno(hos_no);
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
		vo.setOrder_type(type);
		vo.setRes_status(resstatus);
		
			dao.insertorder(vo);
		
	}
	
	
		
}
