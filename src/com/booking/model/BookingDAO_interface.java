package com.booking.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookingDAO_interface {
	
	public ArrayList<String> insert(ArrayList<String> arrayList)throws SQLException;
    public void update(String sta)throws SQLException;
    public void delet(String bid)throws SQLException;
	public List<BookingVO> getBookingInfoListBylldno(String id);
	
}
