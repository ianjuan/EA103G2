package com.rptrp.model;

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


public class RptrpJNDIDAO implements RptrpDAO_interface {
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public static final String INSERT_STMT = "INSERT INTO REPORT_REPAIR_PICTURE(RPTRP_NO,RPTR_NO,RPTRP_PIC) VALUES ('RPTRP'||LPAD(SEQ_RPTRP_NO.NEXTVAL,6,'0'),?,?)";
	public static final String UPDATE = "UPDATE REPORT_REPAIR_PICTURE SET RPTR_NO=?,RPTRP_PIC=? WHERE RPTRP_NO=?";
	public static final String DELETE = "DELETE REPORT_REPAIR_PICTURE WHERE RPTRP_NO=?";
	public static final String GET_RPTRP_STMT = "SELECT RPTRP_NO,RPTR_NO, RPTRP_PIC FROM REPORT_REPAIR_PICTURE WHERE RPTRP_NO=?";
	public static final String GET_ALL_STMT = " SELECT RPTRP_NO,RPTR_NO,RPTRP_PIC FROM REPORT_REPAIR_PICTURE ORDER BY RPTRP_NO";
	public static final String GET_RPTR_STMT = "SELECT RPTRP_NO,RPTR_NO,RPTRP_PIC FROM REPORT_REPAIR_PICTURE WHERE RPTR_NO=?";

	@Override
	public void insert(RptrpVO rptrpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rptrpVO.getRptr_no());
			pstmt.setBytes(2, rptrpVO.getRptrp_pic());
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
	public void update(RptrpVO rptrpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rptrpVO.getRptr_no());
			pstmt.setBytes(2, rptrpVO.getRptrp_pic());
			pstmt.setString(3, rptrpVO.getRptrp_no());

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
	public void delete(String rptrp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rptrp_no);

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
	public RptrpVO findByPrimaryKey(String rptrp_no) {
		
		RptrpVO rptrpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTRP_STMT);

			pstmt.setString(1, rptrp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptrpVO = new RptrpVO();
				rptrpVO.setRptrp_no(rs.getString("rptrp_no"));
				rptrpVO.setRptr_no(rs.getString("rptr_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rptrp_pic"));
				while ((in.read(buf)) != -1) {
					rptrpVO.setRptrp_pic(buf);
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

		return rptrpVO;
	}

	@Override
	public List<RptrpVO> getAll() {
		
		List<RptrpVO> list = new ArrayList<RptrpVO>();
		RptrpVO rptrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptrpVO = new RptrpVO();
				rptrpVO.setRptrp_no(rs.getString("rptrp_no"));
				rptrpVO.setRptr_no(rs.getString("rptr_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rptrp_pic"));
				while ((in.read(buf)) != -1) {
					rptrpVO.setRptrp_pic(buf);
				}
				in.close();

				list.add(rptrpVO);
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
	public List<RptrpVO> findByRptr(String rptr_no) {

		List<RptrpVO> list = new ArrayList<RptrpVO>();
		RptrpVO rptrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTR_STMT);
			pstmt.setString(1, rptr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptrpVO = new RptrpVO();
				rptrpVO.setRptrp_no(rs.getString("rptrp_no"));
				rptrpVO.setRptr_no(rs.getString("rptr_no"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("rptrp_pic"));
				while ((in.read(buf)) != -1) {
					rptrpVO.setRptrp_pic(buf);
				}
				in.close();

				list.add(rptrpVO);
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
