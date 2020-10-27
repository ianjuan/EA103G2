package com.booking.model;

import java.util.ArrayList;
import java.util.List;

public interface BookingDAO_interface {
	
	public ArrayList<String> insert(ArrayList<String> arrayList,String lld_no);
    public void update(String sta);
    public void delet(String bid);
	public List<BookingVO> getBookingInfoListByhosno(String houseno);
	public void insertorder(BookingVO vo);
	public List<BookingVO> getBookingInfoListBylldno(String lldno);
	public List<BookingVO> getResOrderbylldno(String lldno);
	public List<BookingVO> getBookingInfoListBytntno(String tnt);
	public List<BookingVO> getResOrderbytntno(String tntno);
}
