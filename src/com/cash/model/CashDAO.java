package com.cash.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cash.model.CashVO;

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
	private static final String INSERT_CASH_ALL_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO, REC_NO, CASH_STATUS)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?)";
	private static final String INSERT_CASH_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CASH_STATUS)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?)";
	private static final String INSERT_CASH_CON_STMT = "INSERT INTO CASH (CASH_NO, CASH_DATE, MEM_NO, CASH_INOUT, CASH_TYPE, CASH_AMOUNT, CON_NO, CASH_STATUS)"
			+ "VALUES ('CASH' || lpad(SEQ_CASH_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?)";

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
			pstmt.setInt(5, cashVO.getCash_amount());
			pstmt.setInt(6, cashVO.getCash_status());
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

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_CASH_ALL_STMT);

			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setString(2, cashVO.getMem_no());
			pstmt.setString(3, cashVO.getCash_inout());
			pstmt.setString(4, cashVO.getCash_type());
			pstmt.setInt(5, cashVO.getCash_amount());
			pstmt.setString(6, cashVO.getCon_no());
			pstmt.setString(7, cashVO.getRec_no());
			pstmt.setInt(8, cashVO.getCash_status());
			pstmt.executeUpdate();

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
	}

	@Override
	public void insert_cash_Con(CashVO cashVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_CASH_CON_STMT);

			pstmt.setDate(1, cashVO.getCash_date());
			pstmt.setString(2, cashVO.getMem_no());
			pstmt.setString(3, cashVO.getCash_inout());
			pstmt.setString(4, cashVO.getCash_type());
			pstmt.setInt(5, cashVO.getCash_amount());
			pstmt.setString(6, cashVO.getCon_no());
			pstmt.setInt(7, cashVO.getCash_status());
			pstmt.executeUpdate();

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
	}
	
	// ===================================================================
	private static final String GET_ONE_CASHLogs_STMT = "SELECT cash_no, cash_date, mem_no, cash_inout, cash_type, cash_amount, con_no, rec_no, cash_status FROM CASH where mem_no = ? order by cash_date";
	private static final String GET_ONE_CASHno_STMT = "SELECT cash_no FROM CASH where rec_no = ?";
	private static final String UPDATE_CASH_STATUS_STMT = "UPDATE CASH set cash_status=? where CASH_no=?";
	@Override
	public List<CashVO> findByMemNo_Cashlogs(String mem_no) {
		System.out.println(mem_no);
		
		List<CashVO> list = new ArrayList<CashVO>();
		CashVO cashVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(GET_ONE_CASHLogs_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cashVO 也稱為 Domain objects
				cashVO = new CashVO();
				cashVO.setCash_no(rs.getString("cash_no"));
				cashVO.setCash_date(rs.getDate("cash_date"));
				cashVO.setMem_no(rs.getString("mem_no"));
				cashVO.setCash_inout(rs.getString("cash_inout"));
				cashVO.setCash_type(rs.getString("cash_type"));
				cashVO.setCash_amount(rs.getInt("cash_amount"));
				cashVO.setCon_no(rs.getString("con_no"));
				cashVO.setRec_no(rs.getString("rec_no"));
				cashVO.setCash_status(rs.getInt("cash_status"));
				list.add(cashVO);
			}
			con.commit();

			// Handle any driver errors
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
		return list;
	}
	
	
	
	@Override
	public String findByRec_no(String rec_no) {
		String cash_no = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CASHno_STMT);

			pstmt.setString(1, rec_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cash_no = rs.getString("cash_no");
			}

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
		return cash_no;
	}


	@Override
	public void update_status(CashVO cashVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CASH_STATUS_STMT);

			pstmt.setInt(1, cashVO.getCash_status());
			pstmt.setString(2, cashVO.getCash_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
