package com.booking.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookingDAO implements BookingDAO_interface	{

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
			    public BookingDAO() {}
			    
			    public List<BookingVO> getBookingInfoListBylldno(String hoId)
			    {
			    	List<BookingVO> list =new ArrayList<BookingVO>();
			    	Connection con = null;
				    ResultSet rs = null;
				    PreparedStatement pstmt=null;
			    	  try {						
						      con = ds.getConnection();
					      //可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令  可增加指令彈性
							pstmt = con.prepareStatement("SELECT r.RESD_NO,r.LLD_NO,r.RESD_DATE,r.RESD_STATUS FROM RESERVATION_DATE r " + 
									"INNER JOIN HOUSE h on h.lld_no=r.lld_no " + 
									"WHERE h.hos_no = ? ");
							pstmt.setString(1,hoId);

							rs = pstmt.executeQuery();

					    	      while (rs.next()) {
					    	    	  BookingVO vo = new BookingVO();
					    	    	  vo.setResd_no(rs.getString("RESD_NO"));
					    	    	  vo.setLld_no(rs.getString("LLD_NO"));
					    	    	  vo.setResd_status(rs.getString("RESD_STATUS"));
					    	    	  System.out.println(rs.getString("RESD_STATUS")+"拿到STATUS");
					    	    	  vo.setResd_date(rs.getDate("RESD_DATE").toString()+"T"+rs.getTime("RESD_DATE").toString());
					    	    	  list.add(vo);
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
			
				public ArrayList<String> insert(ArrayList<String> strings,String lld_no) throws SQLException {
//			    	public String insert(String date,String id) throws SQLException{
					ArrayList<String> list = new ArrayList<String>();
					Connection con = null;
				    ResultSet rs = null;
				    PreparedStatement pstmt=null;
					try {
					      con = ds.getConnection();

//			    		
						pstmt = con.prepareStatement("INSERT INTO RESERVATION_DATE (RESD_NO,RESD_DATE,LLD_NO) "
								+ " VALUES ('RESD' || lpad(SEQ_RESD_NO.NEXTVAL, 5, '0'),TO_DATE(?,'YYYY-MM-DD HH24:mi'),?)",new String[]{"RESD_NO"});//為了取到回傳ID 方法較特殊NEW STRING[](PK)
//						con.setAutoCommit(false);
//						System.out.println(strings.size()+"hello");
//						int i=0;
						for(String str:strings) {
							System.out.println(str);

							
							pstmt.setString(1,str);
							String ran=	"LLD000007";
//							if(dim<10)ran="LLD0000"+dim;
//							if(dim>10&&dim<100)ran="LLD000"+dim;
//							if(dim>100&&dim<1000)ran="LLD00"+dim;

							pstmt.setString(2,lld_no);

							pstmt.executeUpdate();
							
							  rs = pstmt.getGeneratedKeys();
							    while(rs.next())
							    {
							        System.out.println("rs.getString(1)="+rs.getString(1)); 
							        list.add(rs.getString(1));
							        System.out.println("recall="+list);
							        
							    }
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
					System.out.println("list送回去囉"+list);
			    	return list;
			    }
			    
			    public void delet(String bid) throws SQLException{
			    	Connection con = null;
				    PreparedStatement pstmt=null;
					try {
					      con = ds.getConnection();
					      pstmt = con.prepareStatement("DELETE FROM RESERVATION_DATE WHERE RESD_NO = ? ");
						pstmt.setString(1,bid);
						pstmt.executeUpdate();
					}catch(SQLException e){
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
			    public void update(String sta) throws SQLException{
			    	Connection con = null;
				    PreparedStatement pstmt=null;
					try {
					      con = ds.getConnection();
					      pstmt = con.prepareStatement("UPDATE RESERVATION_DATE SET RESD_STATUS = 1 WHERE RESD_NO = ? ");
						pstmt.setString(1,sta);
						pstmt.executeUpdate();
						
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

			    
//			    insert into reservartion_order (RES_NO,TNT_NO,HOS_NO,ORDER_DATE,RES_TYPE,RES_STATUS,RES_ODD)
//			    value('RES' || lpad(SEQ_RESD_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?);
				
		  }


