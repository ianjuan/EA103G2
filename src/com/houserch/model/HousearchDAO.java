package com.houserch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HousearchDAO implements HousearchDAO_interface{
	
	private static DataSource ds =null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
			
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
    
	 private  static List<String> sortList =Arrays.asList(
			new String[] {"HOS_DATE DESC","HOS_DATE","HOS_RENTFEE DESC","HOS_RENTFEE","HOS_PNUM DESC","HOS_PNUM"});

	 private  static List<String> moneyList =Arrays.asList(
			 new String[] {"<5000","BETWEEN 5000 AND 10000","BETWEEN 10000 AND 15000",">15000"});

	 private  static List<String> houseList =Arrays.asList(
			 new String[] {"整層住家","獨立套房","分租套房","雅房"});
	 

	 public byte[] getpic(String pic_no) {
		 Connection con = null;
		    ResultSet rs = null;
		    PreparedStatement pstmt=null;
		    byte[] pic=null;
		    try {
			      con = ds.getConnection();
		    	String i=  "select HOS_PIC FROM HOUSE_PICTURE WHERE PIC_NO = ? ";
						pstmt = con.prepareStatement(i);
						pstmt.setString(1,pic_no);
					rs = pstmt.executeQuery();
					 while (rs.next()) {
					pic=rs.getBytes("HOS_PIC");
					 }
		    }
		    catch(SQLException e){
			      	e.printStackTrace();
			    	System.out.println("SQL壞了 ");
			    }
			    	finally {
			        	if (rs != null) {
			    			try {
			    				rs.close();
			    			} catch (SQLException se) {
			    				se.printStackTrace(System.err);
			    			}
			    		}
			    		if (pstmt != null) {
			    			try {
			    				pstmt.close();
			    			} catch (SQLException se) {
			    				se.printStackTrace(System.err);
			    			}
			    		}
			    		if (con != null) {
			    			try {
			    				con.close();
			    			} catch (Exception e) {
			    				e.printStackTrace(System.err);
			    			}
			    		}
			        	}
			    	return pic;}
	 
	 
    public Map<String,HousearchVO> getAll(){
		Map<String,HousearchVO> map =new LinkedHashMap<String,HousearchVO>();
		Connection con = null;
	    ResultSet rs = null;
	    PreparedStatement pstmt=null;
		ArrayList<String> checklist = new ArrayList<String>();
    	try {		
	      con = ds.getConnection();
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
	      String i=  "select hp.PIC_NO,h.HOS_NO, rownum r, h.hos_name,h.hos_room,h.hos_floor, h.hos_pnum,h.hos_rentfee"
	       	   +" from HOUSE_PICTURE hp INNER JOIN HOUSE h on h.HOS_NO =hp.hos_no  AND h.hos_status ='待出租' WHERE HOS_ADD LIKE '%桃園市中壢區%' "
	       	   +"AND hp.pic_no in(select min(PIC_NO) from HOUSE_PICTURE group by HOS_NO ) ";
	      i="SELECT * from( "+i+" )where r > 0 and r <= 15";

    	  System.out.println(i);
		pstmt = con.prepareStatement(i);


	      pstmt = con.prepareStatement(i);

			rs = pstmt.executeQuery();
			  while (rs.next()) { 
				  HousearchVO revo=new HousearchVO();
    	    	  if(checklist.contains(rs.getString("HOS_NAME"))!=true) {
    	    	checklist.add(rs.getString("HOS_NAME"));
    	    	revo.setHos_pic(rs.getString("PIC_NO"));
				  revo.setHos_no(rs.getString("HOS_NO"));
				  revo.setHos_name(rs.getString("HOS_NAME"));
				  revo.setHos_room(rs.getString("HOS_ROOM"));
				  revo.setHos_floor(rs.getString("HOS_FLOOR"));
				  revo.setHos_pnum(rs.getDouble("HOS_PNUM"));
				  revo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));

				  map.put(revo.getHos_no(), revo);}
				 			  }

		
    	
    }catch(SQLException e){
    	e.printStackTrace();
    	System.out.println("SQL壞了 ");
    }
    	finally {
        	if (rs != null) {
    			try {
    				rs.close();
    			} catch (SQLException se) {
    				se.printStackTrace(System.err);
    			}
    		}
    		if (pstmt != null) {
    			try {
    				pstmt.close();
    			} catch (SQLException se) {
    				se.printStackTrace(System.err);
    			}
    		}
    		if (con != null) {
    			try {
    				con.close();
    			} catch (Exception e) {
    				e.printStackTrace(System.err);
    			}
    		}
        	}
    	return map;}

    
        public Map<String,HousearchVO> getMapfromSearchKey(HousearchVO vo){//搜索條件

    	Map<String,HousearchVO> map =new LinkedHashMap<String,HousearchVO>();
    	Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt=null;
		int rownum=vo.getPage()*15;

    	try {
			System.out.println("傳進來的地址是"+vo.getHos_add()+"排序條件"+vo.getSort()+"價位區間"+vo.getMoney()+"房屋型態"+vo.getHos_type());

	      con = ds.getConnection();
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
	 	

	      String i=  "select hp.PIC_NO,h.HOS_NO, rownum r, h.hos_name,h.hos_room,h.hos_floor, h.hos_pnum,h.hos_rentfee"
    	   +" from HOUSE_PICTURE hp INNER JOIN HOUSE h on h.HOS_NO =hp.hos_no  AND h.hos_status ='待出租' WHERE HOS_ADD LIKE ? "
    	   +"AND hp.pic_no in(select min(PIC_NO) from HOUSE_PICTURE group by HOS_NO ) ";
	      System.out.println("city="+vo.getCity());
	      System.out.println("serachbox="+vo.getSerachbox());
	      if(vo.getTown()!="") {
    		  i=i+" AND h.hos_add LIKE '%"+vo.getTown()+"%'";
	      }
	      if(vo.getSerachbox()!="") {
    		  i=i+" AND h.hos_add LIKE '%"+vo.getSerachbox()+"%'";
	      }
	      if("true".equals(vo.getBoy())) {
	    	  i=i+" AND h.hos_sex ='男生' ";
	      }
	      if("true".equals(vo.getGirl())) {
	    	  i=i+" AND h.hos_sex ='女生' ";
	      }
	      if("true".equals(vo.getCook())) {
	    	  i=i+" AND h.hos_cook ='可以' ";
	      }
	      if("true".equals(vo.getPet())) {
	    	  i=i+" AND h.hos_pet ='可以' ";
	      }
    	  if(houseList.contains(vo.getHos_type())) {
    		  i=i+" AND h.hos_room LIKE '%"+vo.getHos_type()+"%'";
    	  }
    	  if(moneyList.contains(vo.getMoney())) {
    		  i=i+" AND h.hos_rentfee " + vo.getMoney();
    	  }
    	  if(sortList.contains(vo.getSort())) {
  	 		 i=i+" ORDER BY h."+vo.getSort();
  	 	 }
    	  
    	  i="SELECT * from( "+i+" )where r > ? and r <= ?";

	    	  System.out.println(i);
			pstmt = con.prepareStatement(i);
			
			pstmt.setString(1,"%"+vo.getCity()+"%");
			pstmt.setInt(2, rownum-15);
			pstmt.setInt(3, rownum);

			rs = pstmt.executeQuery();
			System.out.println("空部空"+rs);
			  while (rs.next()) {
				  HousearchVO revo=new HousearchVO();
//    	    	String encodedText = encoder.encodeToString(rs.getBytes("HOS_PIC"));
//    	    	revo.setHos_pic(encodedText);
    	    	revo.setHos_pic(rs.getString("PIC_NO"));
				  revo.setHos_no(rs.getString("HOS_NO"));
				  revo.setHos_name(rs.getString("HOS_NAME"));
				  revo.setHos_room(rs.getString("HOS_ROOM"));
				  revo.setHos_floor(rs.getString("HOS_FLOOR"));
				  revo.setHos_pnum(rs.getDouble("HOS_PNUM"));
				  revo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));

				  map.put(revo.getHos_no(), revo);
    	    	  };

		
    	
    }catch(SQLException e){
    	e.printStackTrace();
    	System.out.println("SQL壞了 ");
    }	finally {
    	if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
    	}
		
	
		return map;}

        public  ArrayList<String> getAlladd(){
    		Map<String,HousearchVO> map =new LinkedHashMap<String,HousearchVO>();
    		Connection con = null;
    	    ResultSet rs = null;
    	    PreparedStatement pstmt=null;
    		ArrayList<String> checklist = new ArrayList<String>();
        	try {		
    	      con = ds.getConnection();
    	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
    		      String i=  "select HOS_ADD FROM HOUSE ";
    				pstmt = con.prepareStatement(i);

    			rs = pstmt.executeQuery();
    			  while (rs.next()) { 
    				  checklist.add( rs.getString("HOS_ADD"));
    				 			  }
    			  
        }catch(SQLException e){
        	e.printStackTrace();
        	System.out.println("SQL壞了 ");
        }
        	finally {
            	if (rs != null) {
        			try {
        				rs.close();
        			} catch (SQLException se) {
        				se.printStackTrace(System.err);
        			}
        		}
        		if (pstmt != null) {
        			try {
        				pstmt.close();
        			} catch (SQLException se) {
        				se.printStackTrace(System.err);
        			}
        		}
        		if (con != null) {
        			try {
        				con.close();
        			} catch (Exception e) {
        				e.printStackTrace(System.err);
        			}
        		}
            	}
        	return checklist;}
        
        public Map<String,HousearchVO> getGMapfromSearchKey(HousearchVO vo){//搜索條件

        	Map<String,HousearchVO> map =new LinkedHashMap<String,HousearchVO>();
        	Connection con = null;
            ResultSet rs = null;
            PreparedStatement pstmt=null;


        	try {
    			System.out.println("傳進來的地址是"+vo.getHos_add()+"排序條件"+vo.getSort()+"價位區間"+vo.getMoney()+"房屋型態"+vo.getHos_type());

    	      con = ds.getConnection();
    	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
    	 	

    	      String i=  "select hp.PIC_NO,h.HOS_NO, h.hos_add ,h.hos_name,h.hos_room,h.hos_floor, h.hos_pnum,h.hos_rentfee,h.hos_lng,h.hos_lat"
        	   +" from HOUSE_PICTURE hp INNER JOIN HOUSE h on h.HOS_NO =hp.hos_no  AND h.hos_status ='待出租' WHERE HOS_ADD LIKE ? AND hp.pic_no in(select min(PIC_NO) from HOUSE_PICTURE group by HOS_NO ) ";
        	
    	      if(vo.getTown()!="") {
        		  i=i+" AND h.hos_add LIKE '%"+vo.getTown()+"%'";
    	      }
    	      if(vo.getSerachbox()!="") {
        		  i=i+" AND h.hos_add LIKE '%"+vo.getSerachbox()+"%'";
    	      }
        	  if(houseList.contains(vo.getHos_type())) {
        		  i=i+" AND h.hos_room LIKE '%"+vo.getHos_type()+"%'";
        	  }
        	  if(moneyList.contains(vo.getMoney())) {
        		  i=i+" AND h.hos_rentfee " + vo.getMoney();
        	  }
    	      if("true".equals(vo.getBoy())) {
    	    	  i=i+" AND h.hos_sex ='男生' ";
    	      }
    	      if("true".equals(vo.getGirl())) {
    	    	  i=i+" AND h.hos_sex ='女生' ";
    	      }
    	      if("true".equals(vo.getCook())) {
    	    	  i=i+" AND h.hos_cook ='可以' ";
    	      }
    	      if("true".equals(vo.getPet())) {
    	    	  i=i+" AND h.hos_pet ='可以' ";
    	      }
        	  if(sortList.contains(vo.getSort())) {
      	 		 i=i+" ORDER BY h."+vo.getSort();
      	 	 }

    	    	  System.out.println(i);
    			pstmt = con.prepareStatement(i);
    			
    			pstmt.setString(1,"%"+vo.getCity()+"%");
    			rs = pstmt.executeQuery();
    			System.out.println("空部空"+rs);
    			  while (rs.next()) {
    				  HousearchVO revo=new HousearchVO();
//        	    	String encodedText = encoder.encodeToString(rs.getBytes("HOS_PIC"));
//        	    	revo.setHos_pic(encodedText);
        	    	revo.setHos_pic(rs.getString("PIC_NO"));
    				  revo.setHos_no(rs.getString("HOS_NO"));
    				  revo.setHos_name(rs.getString("HOS_NAME"));
    				  revo.setHos_room(rs.getString("HOS_ROOM"));
    				  revo.setHos_floor(rs.getString("HOS_FLOOR"));
    				  revo.setHos_pnum(rs.getDouble("HOS_PNUM"));
    				  revo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));
    				  revo.setHos_lat(rs.getDouble("HOS_LAT"));
    				  revo.setHos_lng(rs.getDouble("HOS_LNG"));
    				  revo.setHos_add(rs.getString("HOS_ADD"));
    				  map.put(revo.getHos_no(), revo);
        	    	  };

    		
        	
        }catch(SQLException e){
        	e.printStackTrace();
        	System.out.println("SQL壞了 ");
        }	finally {
        	if (rs != null) {
    			try {
    				rs.close();
    			} catch (SQLException se) {
    				se.printStackTrace(System.err);
    			}
    		}
    		if (pstmt != null) {
    			try {
    				pstmt.close();
    			} catch (SQLException se) {
    				se.printStackTrace(System.err);
    			}
    		}
    		if (con != null) {
    			try {
    				con.close();
    			} catch (Exception e) {
    				e.printStackTrace(System.err);
    			}
    		}
        	}
    		
    	
    		return map;}
        
        public Map<String,HousearchVO> getAllGmap(){
    		Map<String,HousearchVO> map =new LinkedHashMap<String,HousearchVO>();
    		Connection con = null;
    	    ResultSet rs = null;
    	    PreparedStatement pstmt=null;
        	try {		
    	      con = ds.getConnection();
    	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
    		      String i=  "select hp.PIC_NO,h.HOS_NO,h.hos_add,  h.hos_name,h.hos_room,h.hos_floor, h.hos_pnum,h.hos_rentfee,h.hos_lng,h.hos_lat"
    		       	   +" from HOUSE_PICTURE hp INNER JOIN HOUSE h on h.HOS_NO =hp.hos_no  AND h.hos_status ='待出租' AND h.hos_add LIKE '%台北市信義區%' AND hp.pic_no in(select min(PIC_NO) from HOUSE_PICTURE group by HOS_NO ) " + 
    		       	   "";
    				pstmt = con.prepareStatement(i);

    			rs = pstmt.executeQuery();
    			  while (rs.next()) { 
    				  HousearchVO revo=new HousearchVO();
        	    	revo.setHos_pic(rs.getString("PIC_NO"));
    				  revo.setHos_no(rs.getString("HOS_NO"));
    				  revo.setHos_add(rs.getString("HOS_ADD"));
    				  revo.setHos_name(rs.getString("HOS_NAME"));
    				  revo.setHos_room(rs.getString("HOS_ROOM"));
    				  revo.setHos_floor(rs.getString("HOS_FLOOR"));
    				  revo.setHos_pnum(rs.getDouble("HOS_PNUM"));
    				  revo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));
    				  revo.setHos_lat(rs.getDouble("HOS_LAT"));
    				  revo.setHos_lng(rs.getDouble("HOS_LNG"));
    				  map.put(revo.getHos_no(), revo);}	
        }catch(SQLException e){
        	e.printStackTrace();
        	System.out.println("SQL壞了 ");
        }
        	finally {
            	if (rs != null) {
        			try {
        				rs.close();
        			} catch (SQLException se) {
        				se.printStackTrace(System.err);
        			}
        		}
        		if (pstmt != null) {
        			try {
        				pstmt.close();
        			} catch (SQLException se) {
        				se.printStackTrace(System.err);
        			}
        		}
        		if (con != null) {
        			try {
        				con.close();
        			} catch (Exception e) {
        				e.printStackTrace(System.err);
        			}
        		}
            	}
        	return map;}
}
