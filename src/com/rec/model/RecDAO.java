package com.rec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecDAO implements RecDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO RECURRING_ORDER (REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC) VALUES"
			+ " (SEQ_REC_NO.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC, to_char(REC_TIME,'yyyy-mm-dd hh:mm:ss') REC_TIME FROM RECURRING_ORDER ORDER BY REC_NO";
	private static final String GET_ONE_STMT = "SELECT REC_NO, CON_NO, HOS_NO, REC_MON, REC_WATER, REC_ELEC, to_char(REC_TIME,'yyyy-mm-dd hh:mm:ss') REC_TIME FROM RECURRING_ORDER WHERE REC_NO = ?";
	private static final String DELETE = "DELETE FROM RECURRING_ORDER WHERE REC_NO = ?";
	private static final String UPDATE = "UPDATE RECURRING_ORDER SET CON_NO = ?, HOS_NO = ?, REC_MON = ?, REC_WATER = ?, REC_ELEC = ? WHERE REC_NO = ?";

	@Override
	public void insert(RecVO recVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recVO.getCon_no());
			pstmt.setString(2, recVO.getHos_no());
			pstmt.setInt(3, recVO.getRec_mon());
			pstmt.setInt(4, recVO.getRec_water());
			pstmt.setInt(5, recVO.getRec_elec());

			pstmt.executeUpdate();
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recVO.getCon_no());
			pstmt.setString(2, recVO.getHos_no());
			pstmt.setInt(3, recVO.getRec_mon());
			pstmt.setInt(4, recVO.getRec_water());
			pstmt.setInt(5, recVO.getRec_elec());
			pstmt.setString(6, recVO.getRec_no());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rec_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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
			}

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

			con = ds.getConnection();
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
				list.add(recVO); // Store the row in the list
			}

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
}
