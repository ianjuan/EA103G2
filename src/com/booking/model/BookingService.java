package com.booking.model;

import java.sql.SQLException;
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
		List<BookingVO> list=dao.getBookingInfoListBylldno(hos_no);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		return jsonStr;
	}
	public ArrayList<String> chang(String strings) {
		Gson gson = new Gson();
		ArrayList<String> list = gson.fromJson(strings, new TypeToken<ArrayList<String>>(){}.getType());
		
		return list;

	}

	
	
		
}
