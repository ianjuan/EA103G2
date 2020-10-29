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
	private static final String INSERT_CASH_ALL_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO, REC_NO)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?)";
	private static final String INSERT_CASH_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?)";
	private static final String INSERT_CASH_CON_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?)";

	@Override
	public String insert_cash(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 掘取對應的自增主鍵值
		String next_cash_no = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			// 指定自增主鍵
			String cols[] = { "cash_no" };
			pstmt = con.prepareStatement(INSERT_CASH_STMT, cols);

			pstmt.setDate(1, cashVO.getCash_date());
//			pstmt.setInt(2, cashVO.getMem_identity());
			pstmt.setString(2, cashVO.getMem_no());
			pstmt.setString(3, cashVO.getCash_inout());
			pstmt.setString(4, cashVO.getCash_type());
			pstmt.setInt(5, cashVO.getCash_amout());

			pstmt.executeUpdate();

			// 掘取對應的自增主鍵值
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_cash_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_cash_no + "(剛新增成功的金流編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}

			con.commit();

		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		return next_cash_no;
	}

	@Override

	public void insert_cashAll(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 掘取對應的自增主鍵值
		String next_cash_no = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			// 指定自增主鍵
			String cols[] = { "cash_no" };
			pstmt = con.prepareStatement(INSERT_CASH_ALL_STMT);

			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setString(2, cashVO.getMem_no());
			pstmt.setString(3, cashVO.getCash_inout());
			pstmt.setString(4, cashVO.getCash_type());
			pstmt.setInt(5, cashVO.getCash_amout());
			pstmt.setString(6, cashVO.getCon_no());
			pstmt.setString(7, cashVO.getRec_no());

			pstmt.executeUpdate();

			// 掘取對應的自增主鍵值
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_cash_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_cash_no + "(剛新增成功的金流編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}

			con.commit();

		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
//		return next_cash_no;
	}

	@Override
	public void insert_cash_Con(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 掘取對應的自增主鍵值
		String next_cash_no = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			// 指定自增主鍵
			String cols[] = { "cash_no" };
			pstmt = con.prepareStatement(INSERT_CASH_ALL_STMT);

			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setString(2, cashVO.getMem_no());
			pstmt.setString(3, cashVO.getCash_inout());
			pstmt.setString(4, cashVO.getCash_type());
			pstmt.setInt(5, cashVO.getCash_amout());
			pstmt.setString(6, cashVO.getCon_no());

			pstmt.executeUpdate();

			// 掘取對應的自增主鍵值
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_cash_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_cash_no + "(剛新增成功的金流編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}

			con.commit();

		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
//		return next_cash_no;
	}

}
