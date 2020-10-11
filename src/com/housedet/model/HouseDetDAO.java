package com.housedet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class HouseDetDAO implements HouseDetDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		}
	
	catch(NamingException e) {
		e.printStackTrace();
	}
  }	
	


 public List<HosDetVO> getHosDetfromHOSNO (String HOS_NO) {
	 List <HosDetVO> list = new ArrayList<HosDetVO>();
	    Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<String> checklist = new ArrayList<String>();
		List<String> pic =new ArrayList<String>();

	 try {		
		 con = ds.getConnection();
	 pstmt = con.prepareStatement("SELECT h.*, hp.PIC_NO,l.lld_name,l.lld_mobile ,l.lld_sex FROM HOUSE h " + 
	 		" INNER JOIN house_picture hp on h.HOS_ID =hp.hos_no " + 
            "INNER JOIN LANDLORd l on l.LLD_no = h.lld_no"+
	 		"  WHERE h.HOS_NO = ? " );	 
	 pstmt.setString(1,HOS_NO);
//	 SELECT * FROM HOUSE WHERE HOS_NO = HOS00001
	 rs = pstmt.executeQuery();
	 System.out.println();
	 while(rs.next()) {
	   		HosDetVO vo = new HosDetVO();

		 pic.add(rs.getString("PIC_NO"));
   	  if(checklist.contains(rs.getString("HOS_NAME"))!=true) {
		
		vo.setHos_no(rs.getString("HOS_NO"));
		vo.setLld_no(rs.getString("LLD_NO"));
		vo.setHos_name(rs.getString("HOS_NAME"));
		vo.setHos_bro(rs.getInt("HOS_BRO"));
		vo.setHos_status(rs.getString("HOS_STATUS"));
		vo.setHos_add(rs.getString("HOS_ADD"));
		vo.setHos_type(rs.getString("HOS_TYPE"));
		vo.setHos_floor(rs.getString("HOS_FLOOR"));
		vo.setHos_pnum(rs.getDouble("HOS_PNUM"));
		vo.setHos_date(rs.getString("HOS_DATE"));
		vo.setHos_lng(rs.getDouble("HOS_LNG"));
		vo.setHos_lat(rs.getDouble("HOS_LAT"));
		vo.setHos_pat(rs.getString("HOS_PAT"));
		vo.setHos_room(rs.getString("HOS_ROOM"));
		vo.setHos_liffun(rs.getString("HOS_LIFFUN"));
		vo.setHos_trans(rs.getString("HOS_TRANS"));
		vo.setHos_id(rs.getString("HOS_ID"));	
		vo.setHos_add(rs.getString("HOS_ADD"));
		vo.setHos_type(rs.getString("HOS_TYPE"));
		vo.setHos_floor(rs.getString("HOS_FLOOR"));
		vo.setHos_table(rs.getInt("HOS_TABLE"));
		vo.setHos_chair(rs.getInt("HOS_CHAIR"));
		vo.setHos_bed(rs.getInt("HOS_BED"));
		vo.setHos_closet(rs.getInt("HOS_CLOSET"));
		vo.setHos_sofa(rs.getInt("HOS_SOFA"));
		vo.setHos_tv(rs.getInt("HOS_TV"));
		vo.setHos_drink(rs.getInt("HOS_DRINK"));
		vo.setHos_aircon(rs.getInt("HOS_AIRCON"));
		vo.setHos_refrig(rs.getInt("HOS_REFRIG"));
		vo.setHos_wash(rs.getInt("HOS_WASH"));
		vo.setHos_hoter(rs.getInt("HOS_HOTER"));
		vo.setHos_forth(rs.getInt("HOS_FORTH"));
		vo.setHos_net(rs.getInt("HOS_NET"));
		vo.setHos_gas(rs.getInt("HOS_GAS"));
		vo.setHos_mdate(rs.getString("HOS_MDATE"));
		vo.setHos_mindate(rs.getString("HOS_MINDATE"));
		vo.setHos_park(rs.getString("HOS_PARK"));
		vo.setHos_sex(rs.getString("HOS_SEX"));
		vo.setHos_iden(rs.getString("HOS_IDEN"));
		vo.setHos_cook(rs.getString("HOS_COOK"));
		vo.setHos_pet(rs.getString("HOS_PET"));
		vo.setHos_smoke(rs.getString("HOS_SMOKE"));
		vo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));
		vo.setHos_gasfee(rs.getInt("HOS_GASFEE"));
		vo.setHos_manafee(rs.getInt("HOS_MANAFEE"));
		vo.setHos_netfee(rs.getInt("HOS_NETFEE"));
		vo.setHos_puwaterfee(rs.getInt("HOS_PUWATERFEE"));
		vo.setHos_puelefee(rs.getInt("HOS_PUELEFEE"));
		vo.setHos_parkfee((rs.getInt("HOS_PARKFEE")));;
   	  	vo.setLld_name(rs.getString("LLD_NAME"));
		vo.setLld_mobile(rs.getString("LLD_MOBILE"));
		vo.setLld_sex(rs.getString("LLD_SEX"));
		System.out.println(rs.getString("HOS_NAME"));
	 }vo.setHos_pic(pic);		
   	  list.add(vo);
}
	 
	 
	 
	 
	 
	 }catch(Exception se){
		 se.printStackTrace();
		 
	 }
	 return list;
	 
 }
 
}