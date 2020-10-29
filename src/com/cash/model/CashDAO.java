package com.cash.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CashDAO implements CashDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// ===================================================================
	private static final String INSERT_CASH_ALL_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_IDENTIFY, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO, REC_NO)"
			+ "VALUES ('CASH' || lpad(SEQ_TNT_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?)";
	private static final String INSERT_CASH_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_IDENTIFY, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT)"
			+ "VALUES ('CASH' || lpad(SEQ_TNT_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_CASH_CON_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_IDENTIFY, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO)"
			+ "VALUES ('CASH' || lpad(SEQ_TNT_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void insert_cashAll(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CASH_ALL_STMT);

//			pstmt.setString(1, cashVO.getCash_no());
			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setInt(2, cashVO.getMem_identity());
			pstmt.setString(3, cashVO.getMem_no());
			pstmt.setInt(4, cashVO.getCash_inout());
			pstmt.setString(5, cashVO.getCash_type());
			pstmt.setInt(6, cashVO.getCash_amout());
			pstmt.setString(7, cashVO.getCon_no());
			pstmt.setString(8, cashVO.getRec_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert_cash(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CASH_STMT);

			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setInt(2, cashVO.getMem_identity());
			pstmt.setString(3, cashVO.getMem_no());
			pstmt.setInt(4, cashVO.getCash_inout());
			pstmt.setString(5, cashVO.getCash_type());
			pstmt.setInt(6, cashVO.getCash_amout());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert_cash_Con(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CASH_ALL_STMT);

//			pstmt.setString(1, cashVO.getCash_no());
			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setInt(2, cashVO.getMem_identity());
			pstmt.setString(3, cashVO.getMem_no());
			pstmt.setInt(4, cashVO.getCash_inout());
			pstmt.setString(5, cashVO.getCash_type());
			pstmt.setInt(6, cashVO.getCash_amout());
			pstmt.setString(7, cashVO.getCon_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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



}
