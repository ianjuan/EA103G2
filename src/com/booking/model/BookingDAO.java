package com.booking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingDAO implements BookingDAO_interface {

	public BookingDAO() {
	}
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	public List<BookingVO> getBookingInfoListByhosno(String hoId){//在房屋頁面根據房屋編號取對應房東的行程表
		List<BookingVO> list = new ArrayList<BookingVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT r.RESD_NO,r.LLD_NO,r.RESD_DATE,r.RESD_STATUS FROM RESERVATION_DATE r "
					+ "INNER JOIN HOUSE h on h.lld_no=r.lld_no " + "WHERE h.hos_no = ? ");
			pstmt.setString(1, hoId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookingVO vo = new BookingVO();
				vo.setResd_no(rs.getString("RESD_NO"));
				vo.setLld_no(rs.getString("LLD_NO"));
				vo.setResd_status(rs.getString("RESD_STATUS"));
				vo.setResd_date(rs.getDate("RESD_DATE").toString() + "T" + rs.getTime("RESD_DATE").toString());
				vo.setTimefordel(rs.getDate("RESD_DATE") + rs.getTime("RESD_DATE").toString());
				list.add(vo);
				System.out.println(list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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

	public List<BookingVO> insert(ArrayList<String> strings, String lld_no){//房東新增可預約日期 丟欲新增日期LIST跟房東編號近來
		List<BookingVO> list = new ArrayList<BookingVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"INSERT INTO RESERVATION_DATE (RESD_NO,RESD_DATE,LLD_NO) "
							+ " VALUES ('RESD' || lpad(SEQ_RESD_NO.NEXTVAL, 5, '0'),TO_DATE(?,'YYYY-MM-DD HH24:mi'),?)",
					new String[] { "RESD_NO" });// 為了取到回傳ID 方法較特殊NEW STRING[](PK)
			
			for (String str : strings) {
				System.out.println(str);
			 if(findTheDayBylld(lld_no, str)) {
				pstmt.setString(1, str);
				pstmt.setString(2, lld_no);
				pstmt.executeUpdate();
				System.out.println("INSERT INTO RESERVATION_DATE (RESD_NO,RESD_DATE,LLD_NO) "
						+ " VALUES ('RESD' || lpad(SEQ_RESD_NO.NEXTVAL, 5, '0'),TO_DATE("+str+",'YYYY-MM-DD HH24:mi'),"+lld_no+")");
				rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					System.out.println("rs.getString(1)=" + rs.getString(1));
					BookingVO vo = new BookingVO();
					vo.setResd_no(rs.getString(1));
					vo.setResd_date(str);
					list.add(vo);
				}
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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
		System.out.println("list送回去囉" + list);
		return list;
	}

	public void deletelld(String resdno){//房東刪除預約 根據預約編號
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("DELETE FROM RESERVATION_DATE WHERE RESD_NO = ? ");
			pstmt.setString(1, resdno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {

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
	public void deletetnt(String hosno,String time){//房客刪除預約 根據房屋編號與時段
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(" DELETE FROM RESERVARTION_ORDER " + 
					" WHERE HOS_NO = ? AND ORDER_DATE= TO_DATE(?,'YYYY-MM-DD HH24:mi:ss') ");
			System.out.println("DELETE FROM RESERVARTION_ORDER " + 
					" WHERE HOS_NO = "+hosno+" AND ORDER_DATE= TO_DATE("+time+",'YYYY-MM-DD HH24:mi:ss')");
			pstmt.setString(1, hosno);
			pstmt.setString(2, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {

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
	public void update(String resdno){//被預約後更新預約狀態 根據預約編號
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE RESERVATION_DATE SET RESD_STATUS = 1 WHERE RESD_NO = ? ");
			pstmt.setString(1, resdno);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {

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

	public void insertorder(BookingVO vo){//新增房客預約時段相關資訊 與上一個方法一起使用
    	Connection con = null;
	    PreparedStatement pstmt=null;
		try {
			Date date = new Date();
		    Timestamp nousedate = new Timestamp(date.getTime());
		    con = ds.getConnection();
		      
		    pstmt = con.prepareStatement(""
		      		+ "insert into reservartion_order (RES_NO, TNT_NO ,HOS_NO ,ORDER_DATE ,RES_TYPE ,RES_STATUS ,RES_ODD)"
		      		+ " VALUES ('RES' || lpad(SEQ_RES_NO.NEXTVAL, 6, '0'),?,?,TO_DATE(?,'YYYY-MM-DD HH24:mi:ss'),?,?,?) ");
			System.out.println("tnt="+vo.getTnt_no()+",hos="+vo.getHos_no()+",Order_date="+vo.getOrder_date()+",Order_type="+vo.getRes_type()+",getRes_status="+vo.getRes_status()+",nousedate="+nousedate);
		    pstmt.setString(1,vo.getTnt_no());
			pstmt.setString(2,vo.getHos_no());
			pstmt.setString(3,vo.getOrder_date());
			pstmt.setString(4,vo.getRes_type());
			pstmt.setString(5,vo.getRes_status());
			pstmt.setTimestamp(6, nousedate);
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
	
	public List<BookingVO> getBookingInfoListBylldno(String lldno){//取得行程表 根據房東編號
		List<BookingVO> list = new ArrayList<BookingVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT RESD_NO,LLD_NO,RESD_DATE,RESD_STATUS FROM RESERVATION_DATE  "
					 + "WHERE lld_no = ? ");
			pstmt.setString(1, lldno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookingVO vo = new BookingVO();
				vo.setResd_no(rs.getString("RESD_NO"));
				vo.setLld_no(rs.getString("LLD_NO"));
				vo.setResd_status(rs.getString("RESD_STATUS"));
				System.out.println(rs.getString("RESD_STATUS") + "拿到STATUS");
				vo.setResd_date(rs.getDate("RESD_DATE").toString() + "T" + rs.getTime("RESD_DATE").toString());
				list.add(vo);
				System.out.println(list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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
	

	public List<BookingVO> getResOrderbylldno(String lldno){//取得行程表 根據房東編號
		List<BookingVO> list = new ArrayList<BookingVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT ro.res_no,TNT.TNT_NO,RO.ORDER_DATE,RO.RES_TYPE,TNT.TNT_NAME,TNT.TNT_SEX,TNT.TNT_PIC,TNT.TNT_MOBILE,H.HOS_NAME,H.HOS_ADD " + 
					"	FROM RESERVARTION_ORDER RO " + 
					"	INNER JOIN TENANT TNT ON RO.TNT_NO=TNT.TNT_NO " + 
					"	INNER JOIN HOUSE H ON RO.HOS_NO=H.HOS_NO " + 
					"    INNER JOIN LANDLORD L ON H.LLD_NO =L.LLD_NO " + 
					"	WHERE L.LLD_NO = ? AND RO.ORDER_DATE >=  sysdate "  + 
					" ORDER BY RO.ORDER_DATE ");
			pstmt.setString(1, lldno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookingVO vo = new BookingVO();
				vo.setRes_no(rs.getString("RES_NO"));
				vo.setRes_type(rs.getString("RES_TYPE"));
				vo.setTnt_name(rs.getString("TNT_NAME").trim());
				vo.setTnt_sex(rs.getString("TNT_SEX"));
				vo.setHos_no(rs.getString("RES_TYPE"));
				vo.setTnt_pic(rs.getBytes("TNT_PIC"));
				vo.setTnt_no(rs.getString("TNT_NO"));
				vo.setTnt_mobile(rs.getString("TNT_MOBILE"));
				vo.setHos_name(rs.getString("HOS_NAME"));
				vo.setHos_add(rs.getString("HOS_ADD"));
				vo.setOrder_date(rs.getString("ORDER_DATE"));

				
				System.out.println(rs.getString("TNT_NAME") + "拿到TNT_NAME");
				System.out.println(rs.getDate("ORDER_DATE") + rs.getTime("ORDER_DATE").toString());
				list.add(vo);
				System.out.println(list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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
	/////////////////////////////////////////////////////////////////////

	public Boolean findTheDayBylld(String lld_no,String time){//防止重複新增
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Boolean newtime =true;
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT  LLD_NO ,RESD_DATE FROM RESERVATION_DATE " + 
					"WHERE LLD_NO=? AND resd_date=TO_DATE(?,'YYYY-MM-DD HH24:mi') ");
			pstmt.setString(1, lld_no);
			pstmt.setString(2, time);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				newtime=false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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

		return newtime;
	}
	
	
	public Boolean findTheDayBytnt(String tnt_no,String time){//防止重複新增
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Boolean newtime =true;
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT * FROM RESERVARTION_ORDER " + 
					"WHERE TNT_NO= ? AND ORDER_DATE = TO_DATE(?,'YYYY-MM-DD HH24:mi:ss') ");
			System.out.println("SELECT * FROM RESERVARTION_ORDER " + 
					"WHERE TNT_NO= "+tnt_no+" AND ORDER_DATE =TO_DATE("+time+",'YYYY-MM-DD HH24:mi') ");
			pstmt.setString(1, tnt_no);
			pstmt.setString(2, time);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				newtime=false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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

		return newtime;
	}
	
	
	/////////////////////////////////////////////////////////////////////
	
	

	public List<BookingVO> getResOrderbytntno(String tntno){//取得行程表 根據房客編號
		List<BookingVO> list = new ArrayList<BookingVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		System.out.println(tntno);
		try {
			con = ds.getConnection();
			// 可把指令在下面這行之前 先做字串化 再用IF 組合SQL指令 可增加指令彈性
			pstmt = con.prepareStatement("SELECT ro.res_no,RO.ORDER_DATE,RO.RES_TYPE,L.LLD_NO, L.LLD_NAME,L.LLD_SEX,L.LLD_PIC,L.LLD_MOBILE, H.HOS_NO,H.HOS_NAME,H.HOS_ADD " + 
					" FROM RESERVARTION_ORDER RO  " + 
					" INNER JOIN HOUSE H ON RO.HOS_NO=H.HOS_NO  " + 
					" INNER JOIN LANDLORD L ON H.LLD_NO =L.LLD_NO  " + 
					" WHERE RO.TNT_NO  = ? AND RO.ORDER_DATE >=  sysdate " + 
					" ORDER BY RO.ORDER_DATE");
			pstmt.setString(1, tntno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookingVO vo = new BookingVO();
				vo.setRes_no(rs.getString("RES_NO"));
				vo.setRes_type(rs.getString("RES_TYPE"));
				vo.setLld_name(rs.getString("LLD_NAME").trim());
				vo.setLld_sex(rs.getString("LLD_SEX"));
				vo.setHos_no(rs.getString("RES_TYPE"));
				vo.setLld_pic(rs.getBytes("LLD_PIC"));
				vo.setLld_mobile(rs.getString("LLD_MOBILE"));
				vo.setHos_name(rs.getString("HOS_NAME"));
				vo.setHos_add(rs.getString("HOS_ADD"));
				vo.setOrder_date(rs.getString("ORDER_DATE"));
				vo.setHos_no(rs.getString("HOS_NO"));
				vo.setLld_no(rs.getString("LLD_NO"));
				vo.setResd_date(rs.getString("ORDER_DATE"));
				vo.setTimefordel(rs.getDate("ORDER_DATE") + rs.getTime("ORDER_DATE").toString());
				System.out.println(rs.getDate("ORDER_DATE") + rs.getTime("ORDER_DATE").toString());
				list.add(vo);
				System.out.println(list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
		} finally {
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
	
}
