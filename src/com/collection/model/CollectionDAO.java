package com.collection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CollectionDAO implements CollectionDAO_Interface{
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
	
	 private  static List<String> moneyList =Arrays.asList(
			 new String[] {"<5000","BETWEEN 5000 AND 10000","BETWEEN 10000 AND 15000",">15000"});

	 private  static List<String> PUNMList =Arrays.asList(
			 new String[] {"<10","BETWEEN 10 AND 30","BETWEEN 30 AND 50",">50"});
	 private  static List<String> houseList =Arrays.asList(
			 new String[] {"整層住家","獨立套房","分租套房","雅房"});

	 
		 public List<CollectionVO> getAllCollectionVOfromTNTNO(CollectionVO vo){

		      String i="select HOUSE_PICTURE.HOS_PIC ,HOUSE.HOS_NO,house.hos_name,house.HOS_RENTFEE,house.HOS_PNUM,house.HOS_TYPE,house.HOS_FLOOR,house.HOS_ROOM,house.HOS_MINDATE,my_collection.COL_DATE from HOUSE_PICTURE" + 
						" INNER JOIN HOUSE on HOUSE.HOS_ID =house_picture.hos_no" + 
						" INNER JOIN my_collection on my_collection.hos_no =HOUSE.hos_NO"  + 
						" where my_collection.tnt_no = ? ";
	    	  
	    	  if(PUNMList.contains(vo.getHos_pnum())) {
	    		  i=i+" AND HOUSE.HOS_PNUM "+vo.getHos_pnum();
	    	  }
	    	  if(moneyList.contains(vo.getHos_price())) {
	    		  i=i+" AND HOUSE.HOS_RENTFEE " + vo.getHos_price();
	    	  }
	    	  if(houseList.contains(vo.getHos_type())) {
	    		  i=i+" AND HOUSE.HOS_ROOM LIKE '%"+vo.getHos_type()+"%'";
	 	 }
	    	  

	 List <CollectionVO> list = new ArrayList<CollectionVO>();
		Connection con = null;
	    ResultSet rs = null;
	    PreparedStatement pstmt=null;
		ArrayList<String> checklist = new ArrayList<String>();
		final Base64.Encoder encoder = Base64.getEncoder();
		System.out.println(i);
	    try {
		      con = ds.getConnection();
		     
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
			pstmt = con.prepareStatement(i);
			pstmt.setString(1,vo.getTnt_no());

			rs = pstmt.executeQuery();

	    	      while (rs.next()) {
	    	    	  CollectionVO revo = new CollectionVO();
	    	    	  if(checklist.contains(rs.getString("HOS_NAME"))!=true) {
	    	    		  checklist.add(rs.getString("HOS_NAME"));
	    	    		  String encodedText = encoder.encodeToString(rs.getBytes("HOS_PIC"));
	    	    	
	    	    		  revo.setHos_pic(encodedText);
	    	    		  revo.setHos_no(rs.getString("HOS_NO"));
	    	    		  revo.setHos_name(rs.getString("HOS_NAME"));
	    	    		  revo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));
	    	    		  revo.setCol_date(rs.getTimestamp("COL_DATE"));
	    	       	 
	    	    		  revo.setHos_pnum(rs.getString("HOS_PNUM"));
	    	    		  revo.setHos_type(rs.getString("HOS_TYPE"));
	    	    		  revo.setHos_floor(rs.getString("HOS_FLOOR"));
	    	    		  revo.setHos_room(rs.getString("HOS_ROOM"));
	    	    		  revo.setHos_mindate(rs.getString("HOS_MINDATE"));
	    	    	  
	    	    	  System.out.println(rs.getString("HOS_NAME"));
	    	    	  
	    	    	  list.add(revo); }
					System.out.println(list);
	    	      }
							
	    	      
	      } catch(SQLException e){
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
	    return list;

	}

		
	 
		public List<CollectionVO> getCollectionVOfromTNTNO(String tnt_no){
		List <CollectionVO> list = new ArrayList<CollectionVO>();
		Connection con = null;
	    ResultSet rs = null;
	    PreparedStatement pstmt=null;
		ArrayList<String> checklist = new ArrayList<String>();
		final Base64.Encoder encoder = Base64.getEncoder();

	    try {
		      con = ds.getConnection();
		     
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
			pstmt = con.prepareStatement("select HOUSE_PICTURE.HOS_PIC ,HOUSE.HOS_NO,house.hos_name,house.HOS_RENTFEE,house.HOS_PNUM,house.HOS_TYPE,house.HOS_FLOOR,house.HOS_ROOM,house.HOS_MINDATE,my_collection.COL_DATE from HOUSE_PICTURE" + 
					" INNER JOIN HOUSE on HOUSE.HOS_ID =house_picture.hos_no" + 
					" INNER JOIN my_collection on my_collection.hos_no =HOUSE.hos_NO"  + 
					" where my_collection.tnt_no = ? ");
			pstmt.setString(1,tnt_no);

			rs = pstmt.executeQuery();

	    	      while (rs.next()) {
	    	    	  CollectionVO vo = new CollectionVO();
	    	    	  if(checklist.contains(rs.getString("HOS_NAME"))!=true) {
	    	    		  checklist.add(rs.getString("HOS_NAME"));
	    	    		  String encodedText = encoder.encodeToString(rs.getBytes("HOS_PIC"));
	    	    	
	    	    	  vo.setHos_pic(encodedText);
    	    		  vo.setHos_no(rs.getString("HOS_NO"));
    	    		  vo.setHos_name(rs.getString("HOS_NAME"));
    	    		  vo.setHos_rentfee(rs.getInt("HOS_RENTFEE"));
    	    		  vo.setCol_date(rs.getTimestamp("COL_DATE"));
    	       	 
    	    		  vo.setHos_pnum(rs.getString("HOS_PNUM"));
    	    		  vo.setHos_type(rs.getString("HOS_TYPE"));
    	    		  vo.setHos_floor(rs.getString("HOS_FLOOR"));
    	    		  vo.setHos_room(rs.getString("HOS_ROOM"));
    	    		  vo.setHos_mindate(rs.getString("HOS_MINDATE"));
	    	    	  System.out.println(rs.getString("HOS_NAME"));
	    	    	  
	    	    	  list.add(vo); }
//					System.out.println(list);
	    	      }
							
	    	      
	      } catch(SQLException e){
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
	    return list;

	}
	public void insertCollection(String tnt_no,String hos_no) {
		Connection con = null;
	    PreparedStatement pstmt=null;
	    try {	
	    	Date date = new Date();       
	    Timestamp nousedate = new Timestamp(date.getTime());
	    System.out.println(tnt_no+"來到這");
		      con = ds.getConnection();
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
			pstmt = con.prepareStatement("INSERT INTO MY_COLLECTION (TNT_NO,HOS_NO,COL_DATE,COL_NO) VALUES (?,?,?,'COL' || lpad(SEQ_COL_NO.NEXTVAL, 6, '0'))");
			pstmt.setString(1,tnt_no);
			pstmt.setString(2,hos_no);
			pstmt.setTimestamp(3, nousedate);
			pstmt.executeQuery();
			
	    	   						
	    	      
	      } catch(SQLException e){
	      	e.printStackTrace();
	    	System.out.println("SQL壞了 ");
	    }
	    	finally {
	        	
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
	   		
		}
	public void deleteCollection(String tnt_no, String hos_no) {
		Connection con = null;
	    PreparedStatement pstmt=null;
	    try {						
		      con = ds.getConnection();
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
			pstmt = con.prepareStatement("DELETE FROM MY_COLLECTION WHERE TNT_NO = ? AND HOS_NO = ? ");
			pstmt.setString(1,tnt_no);
			pstmt.setString(2,hos_no);
			pstmt.executeQuery();			   	     
	    	   						
	    	      
	      } catch(SQLException e){
	      	e.printStackTrace();
	    	System.out.println("SQL壞了 ");
	    }
	    	finally {
	        	
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
	   		
		}
	public void deleteallCollection(String tnt_no, ArrayList<String> hos_no) {
		Connection con = null;
	    PreparedStatement pstmt=null;
	    try {						
		      con = ds.getConnection();
	      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
				for(String str:hos_no) {
		    pstmt = con.prepareStatement("DELETE FROM MY_COLLECTION WHERE TNT_NO = ? AND HOS_NO = ? ");
			pstmt.setString(1,tnt_no);
			pstmt.setString(2,str);
			pstmt.executeQuery();			   	     
		      };
	      } catch(SQLException e){
	      	e.printStackTrace();
	    	System.out.println("SQL壞了 ");
	    }
	    	finally {
	        	
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
	   		
		}
	}
	


