package com.booking.model;

import java.util.ArrayList;
import java.util.List;

public interface BookingDAO_interface {
	
	public List<BookingVO> insert(ArrayList<String> arrayList,String lld_no);
    public void update(String sta);
    public void deletelld(String bid);
    public void deletetnt(String hosno,String time);
	public List<BookingVO> getBookingInfoListByhosno(String houseno);
	public void insertorder(BookingVO vo);
	public List<BookingVO> getBookingInfoListBylldno(String lldno);
	public List<BookingVO> getResOrderbylldno(String lldno);
	public List<BookingVO> getResOrderbytntno(String tntno);
	public Boolean findTheDayBytnt(String lld_no,String time);
	public Boolean findTheDayBylld(String lld_no,String time);

}
