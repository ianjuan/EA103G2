package com.ctsp.model;

import java.io.BufferedInputStream;
import java.io.IOException;
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

import com.ctsp.model.CtspVO;

public class CtspJNDIDAO implements CtspDAO_interface{
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public static final String INSERT_STMT = "INSERT INTO CUSTOMER_SERVICE_PICTURE(CTSP_NO,CTS_NO,CTSP_PIC) VALUES ('CTSP'||LPAD(SEQ_CTSP_NO.NEXTVAL,6,'0'),?,?)";
	public static final String UPDATE = "UPDATE CUSTOMER_SERVICE_PICTURE SET CTS_NO=?,CTSP_PIC=? WHERE CTSP_NO=?";
	public static final String DELETE = "DELETE CUSTOMER_SERVICE_PICTURE WHERE CTSP_NO=?";
	public static final String GET_CTSP_STMT = "SELECT CTSP_NO,CTS_NO, CTSP_PIC FROM CUSTOMER_SERVICE_PICTURE WHERE CTSP_NO=?";
	public static final String GET_ALL_STMT = " SELECT CTSP_NO,CTS_NO,CTSP_PIC FROM CUSTOMER_SERVICE_PICTURE ORDER BY CTSP_NO";
	public static final String GET_CTS_STMT = "SELECT CTSP_NO,CTS_NO,CTSP_PIC FROM CUSTOMER_SERVICE_PICTURE WHERE CTS_NO=?";

	@Override
	public void insert(CtspVO ctspVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ctspVO.getCts_no());
			pstmt.setBytes(2, ctspVO.getCtsp_pic());
			pstmt.executeUpdate();
			pstmt.clearParameters();
		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}

	@Override
	public void update(CtspVO ctspVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ctspVO.getCts_no());
			pstmt.setBytes(2, ctspVO.getCtsp_pic());
			pstmt.setString(3, ctspVO.getCtsp_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void delete(String ctsp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ctsp_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public CtspVO findByPrimaryKey(String ctsp_no) {
		
		CtspVO ctspVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CTSP_STMT);

			pstmt.setString(1, ctsp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctspVO = new CtspVO();
				ctspVO.setCtsp_no(rs.getString("ctsp_no"));
				ctspVO.setCts_no(rs.getString("cts_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ctsp_pic"));
				while ((in.read(buf)) != -1) {
					ctspVO.setCtsp_pic(buf);
				}

				in.close();
			}

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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

		return ctspVO;
	}

	@Override
	public List<CtspVO> getAll() {
		
		List<CtspVO> list = new ArrayList<CtspVO>();
		CtspVO ctspVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctspVO = new CtspVO();
				ctspVO.setCtsp_no(rs.getString("ctsp_no"));
				ctspVO.setCts_no(rs.getString("cts_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ctsp_pic"));
				while ((in.read(buf)) != -1) {
					ctspVO.setCtsp_pic(buf);
				}
				in.close();

				list.add(ctspVO);
			}

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public List<CtspVO> findByCts(String cts_no) {

		List<CtspVO> list = new ArrayList<CtspVO>();
		CtspVO ctspVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CTS_STMT);
			pstmt.setString(1, cts_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctspVO = new CtspVO();
				ctspVO.setCtsp_no(rs.getString("ctsp_no"));
				ctspVO.setCts_no(rs.getString("cts_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ctsp_pic"));
				while ((in.read(buf)) != -1) {
					ctspVO.setCtsp_pic(buf);
				}
				in.close();

				list.add(ctspVO);
			}

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
