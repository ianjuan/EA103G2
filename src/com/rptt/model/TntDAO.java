package com.rptt.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TntDAO implements TenantDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// =================================1.profile==================================
	private static final String INSERT_PROFILE_STMT = "INSERT INTO TENANT (TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_PIC)"
			+ "VALUES ('TNT' || lpad(SEQ_TNT_NO.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_PROFILE_STMT2 = "INSERT INTO TENANT (TNT_NO, TNT_EMAIL)"
			+ "VALUES ('TNT' || lpad(SEQ_TNT_NO.NEXTVAL, 5, '0'),?)";
	private static final String UPDATE_PROFILE_STMT = "UPDATE TENANT set TNT_EMAIL=?, TNT_ACC=?, TNT_PWD=?, TNT_ID=?, TNT_NAME=?, TNT_BIRTH=?, TNT_SEX=?, TNT_MOBILE=?, TNT_CITY=?, TNT_DIST=?, TNT_ADD=?, TNT_PIC=?, TNT_STATUS=? where TNT_NO = ?";
	private static final String GET_ONE_PROFILE_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_PIC, TNT_STATUS, TNT_JOINTIME FROM TENANT where TNT_NO = ?";
	private static final String GET_ALL_PROFILE_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_PIC, TNT_STATUS, TNT_JOINTIME FROM TENANT order by TNT_NO";
	private static final String GET_ALL_ACCOUNT_STMT = "SELECT tnt_no, tnt_email, tnt_pwd from TENANT";

	@Override
	public void insert_profile(TntVO tntVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PROFILE_STMT);

			pstmt.setString(1, tntVO.getTnt_email());
			pstmt.setString(2, tntVO.getTnt_acc());
			pstmt.setString(3, tntVO.getTnt_pwd());
			pstmt.setString(4, tntVO.getTnt_id());
			pstmt.setString(5, tntVO.getTnt_name());
			pstmt.setDate(6, tntVO.getTnt_birth());
			pstmt.setBoolean(7, tntVO.getTnt_sex());
			pstmt.setString(8, tntVO.getTnt_mobile());
			pstmt.setString(9, tntVO.getTnt_city());
			pstmt.setString(10, tntVO.getTnt_dist());
			pstmt.setString(11, tntVO.getTnt_add());
			pstmt.setBytes(12, tntVO.getTnt_pic());

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
	public void update_profile(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PROFILE_STMT);

			pstmt.setString(1, tntVO.getTnt_email());
			pstmt.setString(2, tntVO.getTnt_acc());
			pstmt.setString(3, tntVO.getTnt_pwd());
			pstmt.setString(4, tntVO.getTnt_id());
			pstmt.setString(5, tntVO.getTnt_name());
			pstmt.setDate(6, tntVO.getTnt_birth());
			pstmt.setBoolean(7, tntVO.getTnt_sex());
			pstmt.setString(8, tntVO.getTnt_mobile());
			pstmt.setString(9, tntVO.getTnt_city());
			pstmt.setString(10, tntVO.getTnt_dist());
			pstmt.setString(11, tntVO.getTnt_add());
			pstmt.setBytes(12, tntVO.getTnt_pic());
			pstmt.setInt(13, tntVO.getTnt_status());
			pstmt.setString(14, tntVO.getTnt_no());

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
	public TntVO findByPK_profile(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PROFILE_STMT);

			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_acc(rs.getString("tnt_acc"));
				tntVO.setTnt_pwd(rs.getString("tnt_pwd"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_sex(rs.getBoolean("tnt_sex"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_city(rs.getString("tnt_city"));
				tntVO.setTnt_dist(rs.getString("tnt_dist"));
				tntVO.setTnt_add(rs.getString("tnt_add"));
				tntVO.setTnt_pic(rs.getBytes("tnt_pic"));
				tntVO.setTnt_status(rs.getInt("tnt_status"));
				tntVO.setTnt_jointime(rs.getTimestamp("tnt_jointime"));
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

		return tntVO;
	}

	@Override
	public List<TntVO> getAll_profile() {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PROFILE_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tntVO 也稱為 Domain objects
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_acc(rs.getString("tnt_acc"));
				tntVO.setTnt_pwd(rs.getString("tnt_pwd"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_sex(rs.getBoolean("tnt_sex"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_city(rs.getString("tnt_city"));
				tntVO.setTnt_dist(rs.getString("tnt_dist"));
				tntVO.setTnt_add(rs.getString("tnt_add"));
				tntVO.setTnt_pic(rs.getBytes("tnt_pic"));
				tntVO.setTnt_status(rs.getInt("tnt_status"));
				tntVO.setTnt_jointime(rs.getTimestamp("tnt_jointime"));
				list.add(tntVO);
			}

			// Handle any driver errors
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

	// =================================5.vrf==================================
	private static final String UPDATE_VRF_STMT = "UPDATE TENANT set tnt_id_picf=?, tnt_id_picb=?, tnt_id_pic2=?, tnt_id_uploadtime=?, tnt_id_isupload=?, tnt_id_result=?, tnt_id_disapprove=?, tnt_id_vrftime=? where tnt_no=?";
	private static final String GET_ONE_VRF_STMT = "SELECT tnt_id_picf, tnt_id_picb, tnt_id_pic2, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove, tnt_id_vrftime from TENANT where tnt_no=?";
	private static final String GET_ALL_VRF_STMT = "SELECT tnt_id_picf, tnt_id_picb, tnt_id_pic2, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove, tnt_id_vrftime from TENANT ORDER BY tnt_no";
	private static final String GET_UNVRF_STMT = "SELECT tnt_no, tnt_name, tnt_id, tnt_birth, tnt_mobile, tnt_email, tnt_id_picf, tnt_id_picb, tnt_id_pic2, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove,TNT_ID_VRFTIME from TENANT where tnt_id_isupload=?";

	@Override
	public void update_vrf(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VRF_STMT);

			pstmt.setBytes(1, tntVO.getTnt_id_picf());
			pstmt.setBytes(2, tntVO.getTnt_id_picb());
			pstmt.setBytes(3, tntVO.getTnt_id_pic2());
			pstmt.setTimestamp(4, tntVO.getTnt_id_uploadtime());
			pstmt.setInt(5, tntVO.getTnt_id_isupload());
			pstmt.setInt(6, tntVO.getTnt_id_result());
			pstmt.setString(7, tntVO.getTnt_id_disapprove());
			pstmt.setTimestamp(8, tntVO.getTnt_id_vrftime());
			pstmt.setString(9, tntVO.getTnt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());
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

	@Override
	public TntVO findByPK_vrf(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VRF_STMT);

			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO.setTnt_id_picf(rs.getBytes("tnt_id_picf"));
				tntVO.setTnt_id_picb(rs.getBytes("tnt_id_picb"));
				tntVO.setTnt_id_pic2(rs.getBytes("tnt_id_pic2"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
			}

		} catch (SQLException se) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
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
		return tntVO;
	}

	@Override
	public List<TntVO> getAll_vrf() {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_VRF_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO.setTnt_id_picf(rs.getBytes("tnt_id_picf"));
				tntVO.setTnt_id_picb(rs.getBytes("tnt_id_picb"));
				tntVO.setTnt_id_pic2(rs.getBytes("tnt_id_pic2"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
				list.add(tntVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());
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
	public List<TntVO> get_unvrf(String Number) {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_UNVRF_STMT);
			pstmt.setString(1, Number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_id_picf(rs.getBytes("tnt_id_picf"));
				tntVO.setTnt_id_picb(rs.getBytes("tnt_id_picb"));
				tntVO.setTnt_id_pic2(rs.getBytes("tnt_id_pic2"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
				list.add(tntVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());
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
