package com.rec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecJDBCDAO implements RecDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2DB";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO RECURRING_ORDER (REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC, REC_STA, REC_TOTAL) VALUES"
			+ " ('REC' || lpad(SEQ_REC_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String AUTO_INSERT = "INSERT INTO RECURRING_ORDER (REC_NO, CON_NO, HOS_NO, REC_MON, REC_STA) VALUES"
			+ " ('REC' || lpad(SEQ_REC_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC, to_char(REC_TIME,'yyyy-mm-dd hh:mm:ss') REC_TIME, REC_STA, REC_TOTAL FROM RECURRING_ORDER ORDER BY REC_NO";
	private static final String GET_ONE_STMT = "SELECT REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC, to_char(REC_TIME,'yyyy-mm-dd hh:mm:ss') REC_TIME, REC_STA, REC_TOTAL FROM RECURRING_ORDER WHERE REC_NO = ?";
	private static final String DELETE = "DELETE FROM RECURRING_ORDER WHERE REC_NO = ?";
	private static final String UPDATE = "UPDATE RECURRING_ORDER SET CON_NO = ?, HOS_NO = ?, REC_MON = ?, REC_WATER = ?, REC_ELEC = ?, REC_STA = ?, REC_TOTAL WHERE REC_NO = ?";
	private static final String LLDUPDATE = "UPDATE RECURRING_ORDER SET REC_WATER = ?, REC_ELEC = ?, REC_STA = ?, REC_TOTAL = ? WHERE REC_NO = ?";
	private static final String GET_ONE_REC_FRON_CON = "SELECT REC_NO, C.CON_NO, C.HOS_NO, REC_MON, REC_WATER, REC_ELEC, to_char(REC_TIME,'yyyy-mm-dd hh:mm:ss') REC_TIME, REC_STA, REC_TOTAL "
			+ "FROM recurring_order R JOIN CONTRACT C ON R.CON_NO = C.CON_NO WHERE C.CON_NO = ?";

	@Override
	public void insert(RecVO recVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recVO.getCon_no());
			pstmt.setString(2, recVO.getHos_no());
			pstmt.setInt(3, recVO.getRec_mon());
			pstmt.setInt(4, recVO.getRec_water());
			pstmt.setInt(5, recVO.getRec_elec());
			pstmt.setInt(6, recVO.getRec_sta());
			pstmt.setInt(7, recVO.getRec_total());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
	@Override
	public void autorec(RecVO recVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(AUTO_INSERT);

			pstmt.setString(1, recVO.getCon_no());
			pstmt.setString(2, recVO.getHos_no());
			pstmt.setInt(3, recVO.getRec_mon());
			pstmt.setInt(4, recVO.getRec_sta());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void update(RecVO recVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recVO.getCon_no());
			pstmt.setString(2, recVO.getHos_no());
			pstmt.setInt(3, recVO.getRec_mon());
			pstmt.setInt(4, recVO.getRec_water());
			pstmt.setInt(5, recVO.getRec_elec());
			pstmt.setInt(6, recVO.getRec_sta());
			pstmt.setInt(7, recVO.getRec_total());
			pstmt.setString(8, recVO.getRec_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
	@Override
	public void lldupdate(RecVO recVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLDUPDATE);
			
			pstmt.setInt(1, recVO.getRec_water());
			pstmt.setInt(2, recVO.getRec_elec());
			pstmt.setInt(3, recVO.getRec_sta());
			pstmt.setInt(4, recVO.getRec_total());
			pstmt.setString(5, recVO.getRec_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void delete(String rec_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rec_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public RecVO findByPrimaryKey(String rec_no) {

		RecVO recVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rec_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new RecVO();
				recVO.setRec_no(rs.getString("REC_NO"));
				recVO.setCon_no(rs.getString("CON_NO"));
				recVO.setHos_no(rs.getString("HOS_NO"));
				recVO.setRec_mon(rs.getInt("REC_MON"));
				recVO.setRec_water(rs.getInt("REC_WATER"));
				recVO.setRec_elec(rs.getInt("REC_ELEC"));
				recVO.setRec_time(rs.getTimestamp("REC_TIME"));
				recVO.setRec_sta(rs.getInt("REC_STA"));
				recVO.setRec_total(rs.getInt("REC_TOTAL"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return recVO;
	}

	@Override
	public List<RecVO> getAll() {
		List<RecVO> list = new ArrayList<RecVO>();
		RecVO recVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new RecVO();
				recVO.setRec_no(rs.getString("REC_NO"));
				recVO.setCon_no(rs.getString("CON_NO"));
				recVO.setHos_no(rs.getString("HOS_NO"));
				recVO.setRec_mon(rs.getInt("REC_MON"));
				recVO.setRec_water(rs.getInt("REC_WATER"));
				recVO.setRec_elec(rs.getInt("REC_ELEC"));
				recVO.setRec_time(rs.getTimestamp("REC_TIME"));
				recVO.setRec_sta(rs.getInt("REC_STA"));
				recVO.setRec_total(rs.getInt("REC_TOTAL"));
				list.add(recVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public List<RecVO> getLddAllByCon(String con_no) {
		List<RecVO> list = new ArrayList<RecVO>();
		RecVO recVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_REC_FRON_CON);
			pstmt.setString(1, con_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new RecVO();
				recVO.setRec_no(rs.getString("REC_NO"));
				recVO.setCon_no(rs.getString("CON_NO"));
				recVO.setHos_no(rs.getString("HOS_NO"));
				recVO.setRec_mon(rs.getInt("REC_MON"));
				recVO.setRec_water(rs.getInt("REC_WATER"));
				recVO.setRec_elec(rs.getInt("REC_ELEC"));
				recVO.setRec_time(rs.getTimestamp("REC_TIME"));
				recVO.setRec_sta(rs.getInt("REC_STA"));
				recVO.setRec_total(rs.getInt("REC_TOTAL"));
				list.add(recVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	public static void main(String[] args) {

		RecJDBCDAO dao = new RecJDBCDAO();

//		// INSERT
		RecVO recVO1 = new RecVO();
		recVO1.setCon_no("CON000001");
		recVO1.setHos_no("HOS000001");
		recVO1.setRec_mon(10);
//		recVO1.setRec_water(10);
//		recVO1.setRec_elec(10);
		recVO1.setRec_sta(1);
		dao.autorec(recVO1);
		System.out.println("新增成功");

		// UPDATE
//		RecVO recVO2 = new RecVO();
//		recVO2.setRec_no("REC000003");
////		recVO2.setCon_no("CON000002");
////		recVO2.setHos_no("HOS000002");
////		recVO2.setRec_mon(2);
//		recVO2.setRec_water(20);
//		recVO2.setRec_elec(20);
//		recVO2.setRec_sta(0);
//		dao.update(recVO2);
//		System.out.println("修改成功");
//		
//		// DELETE
////		dao.delete("REC000002");
////		System.out.println("刪除成功");
//		
////		//SEARCH
//		RecVO recVO3 = dao.findByPrimaryKey("REC000023");
//		System.out.print(recVO3.getRec_no()+ ",");
//		System.out.print(recVO3.getCon_no()+ ",");
//		System.out.print(recVO3.getHos_no()+ ",");
//		System.out.print(recVO3.getRec_mon()+ ",");
//		System.out.print(recVO3.getRec_water()+ ",");
//		System.out.print(recVO3.getRec_elec()+ ",");
//		System.out.print(recVO3.getRec_time());
//		System.out.println("---------------------");
//		
//		//SEARCH
//		List<RecVO> list = dao.getLddAllByCon("CON000001");
//		for (RecVO aRec : list) {
//			System.out.print(aRec.getRec_no()+ ",");
//			System.out.print(aRec.getCon_no()+ ",");
//			System.out.print(aRec.getHos_no()+ ",");
//			System.out.print(aRec.getRec_mon()+ ",");
//			System.out.print(aRec.getRec_water()+ ",");
//			System.out.print(aRec.getRec_elec()+ ",");
//			System.out.print(aRec.getRec_time());
//			System.out.println(aRec.getRec_sta());
//			System.out.println("---------------------");
//	}

//		//SEARCH
//		List<RecVO> list = dao.getAll();
//		for (RecVO aRec : list) {
//			System.out.print(aRec.getRec_no()+ ",");
//			System.out.print(aRec.getCon_no()+ ",");
//			System.out.print(aRec.getHos_no()+ ",");
//			System.out.print(aRec.getRec_mon()+ ",");
//			System.out.print(aRec.getRec_water()+ ",");
//			System.out.print(aRec.getRec_elec()+ ",");
//			System.out.println(aRec.getRec_time());
//			System.out.println("---------------------");
//		}
	}
}
