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
	private static final String UPDATE_PROFILE_STMT = "UPDATE TENANT set TNT_EMAIL=?, TNT_ACC=?, TNT_ID=?, TNT_NAME=?, TNT_BIRTH=?, TNT_SEX=?, TNT_MOBILE=?, TNT_CITY=?, TNT_DIST=?, TNT_ADD=?, TNT_STATUS=? where TNT_NO = ?";
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

	private static final String GET_ONE_PROFILE_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_ACC, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_STATUS,TNT_JOINTIME,TNT_BALANCE,TNT_BANK,TNT_BANKBRANCH, TNT_BANKACC,TNT_CARD,TNT_CARDDUE,TNT_CMT_STARSUM,TNT_CMT_COUNT, EMP_NO,TNT_ID_UPLOADTIME,TNT_ID_ISUPLOAD,TNT_ID_RESULT,TNT_ID_VRFTIME,TNT_ID_DISAPPROVE,TNT_REPORTED_COUNT,TNT_AUTH_CHAT,TNT_AUTH_RES,TNT_AUTH_CMT,TNT_AUTH_RPT FROM TENANT where TNT_NO =? or TNT_ID=?";
	private static final String GET_LANDLORD_ONE_PROFILE_STMT = "SELECT LLD_NO AS TNT_NO, LLD_EMAIL AS TNT_EMAIL, LLD_ACC AS TNT_ACC, LLD_ID AS TNT_ID, LLD_NAME AS TNT_NAME, LLD_BIRTH AS TNT_BIRTH, LLD_SEX AS TNT_SEX, LLD_MOBILE AS TNT_MOBILE, LLD_CITY AS TNT_CITY, LLD_DIST AS TNT_DIST, LLD_ADD AS TNT_ADD, LLD_STATUS AS TNT_STATUS,LLD_JOINTIME AS  TNT_JOINTIME,LLD_BALANCE AS TNT_BALANCE,LLD_BANK AS TNT_BANK,LLD_BANKBRANCH AS TNT_BANKBRANCH, LLD_BANKACC AS TNT_BANKACC,LLD_CARD AS TNT_CARD,LLD_CARDDUE AS TNT_CARDDUE,LLD_CMT_STARSUM AS TNT_CMT_STARSUM ,LLD_CMT_COUNT AS TNT_CMT_COUNT, EMP_NO,LLD_ID_UPLOADTIME AS TNT_ID_UPLOADTIME,LLD_ID_ISUPLOAD AS TNT_ID_ISUPLOAD,LLD_ID_RESULT AS TNT_ID_RESULT,LLD_ID_VRFTIME AS TNT_ID_VRFTIME,LLD_ID_DISAPPROVE AS TNT_ID_DISAPPROVE,LLD_REPORTED_COUNT AS TNT_REPORTED_COUNT,LLD_AUTH_CHAT AS TNT_AUTH_CHAT,LLD_AUTH_RES AS TNT_AUTH_RES,LLD_AUTH_CMT AS TNT_AUTH_CMT,LLD_AUTH_RPT AS TNT_AUTH_RPT FROM LANDLORD where LLD_NO =? or LLD_ID=?";

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
			pstmt.setString(2, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				// -------profile-------
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_acc(rs.getString("tnt_acc"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_sex(rs.getBoolean("tnt_sex"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_city(rs.getString("tnt_city"));
				tntVO.setTnt_dist(rs.getString("tnt_dist"));
				tntVO.setTnt_add(rs.getString("tnt_add"));
				tntVO.setTnt_status(rs.getInt("tnt_status"));
				tntVO.setTnt_jointime(rs.getTimestamp("tnt_jointime"));
				// -------pocket-------
				tntVO.setTnt_balance(rs.getInt("tnt_balance"));
				// -------card/bank-------
				tntVO.setTnt_bank(rs.getInt("tnt_bank"));
				tntVO.setTnt_bankbranch(rs.getString("tnt_bankbranch"));
				tntVO.setTnt_bankacc(rs.getString("tnt_bankacc"));
				tntVO.setTnt_card(rs.getLong("tnt_card"));
				tntVO.setTnt_carddue(rs.getDate("tnt_carddue"));
				// -------CMT-------
				tntVO.setTnt_cmt_starsum(rs.getInt("tnt_cmt_starsum"));
				tntVO.setTnt_cmt_count(rs.getInt("tnt_cmt_count"));
				// -------VRF-------
				tntVO.setEmp_no(rs.getString("emp_no"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				// -------RPT&AUTH-------
				tntVO.setTnt_reported_count(rs.getInt("tnt_reported_count"));
				tntVO.setTnt_auth_chat(rs.getInt("tnt_auth_chat"));
				tntVO.setTnt_auth_res(rs.getInt("tnt_auth_res"));
				tntVO.setTnt_auth_cmt(rs.getInt("tnt_auth_cmt"));
				tntVO.setTnt_auth_rpt(rs.getInt("tnt_auth_rpt"));

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
	public TntVO findByPK_profile1(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LANDLORD_ONE_PROFILE_STMT);

			pstmt.setString(1, tnt_no);
			pstmt.setString(2, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				// -------profile-------
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_acc(rs.getString("tnt_acc"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_sex(rs.getBoolean("tnt_sex"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_city(rs.getString("tnt_city"));
				tntVO.setTnt_dist(rs.getString("tnt_dist"));
				tntVO.setTnt_add(rs.getString("tnt_add"));
				tntVO.setTnt_status(rs.getInt("tnt_status"));
				tntVO.setTnt_jointime(rs.getTimestamp("tnt_jointime"));
				// -------pocket-------
				tntVO.setTnt_balance(rs.getInt("tnt_balance"));
				// -------card/bank-------
				tntVO.setTnt_bank(rs.getInt("tnt_bank"));
				tntVO.setTnt_bankbranch(rs.getString("tnt_bankbranch"));
				tntVO.setTnt_bankacc(rs.getString("tnt_bankacc"));
				tntVO.setTnt_card(rs.getLong("tnt_card"));
				tntVO.setTnt_carddue(rs.getDate("tnt_carddue"));
				// -------CMT-------
				tntVO.setTnt_cmt_starsum(rs.getInt("tnt_cmt_starsum"));
				tntVO.setTnt_cmt_count(rs.getInt("tnt_cmt_count"));
				// -------VRF-------
				tntVO.setEmp_no(rs.getString("emp_no"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				// -------RPT&AUTH-------
				tntVO.setTnt_reported_count(rs.getInt("tnt_reported_count"));
				tntVO.setTnt_auth_chat(rs.getInt("tnt_auth_chat"));
				tntVO.setTnt_auth_res(rs.getInt("tnt_auth_res"));
				tntVO.setTnt_auth_cmt(rs.getInt("tnt_auth_cmt"));
				tntVO.setTnt_auth_rpt(rs.getInt("tnt_auth_rpt"));

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
	private static final String GET_ALL_VRF_STMT = "SELECT tnt_no AS tnt_no, tnt_name as tnt_name, tnt_id as tnt_id, tnt_birth as tnt_birth , tnt_mobile as tnt_mobile, tnt_email as tnt_email, tnt_id_uploadtime as tnt_id_uploadtime, tnt_id_isupload as tnt_id_isupload, emp_no as emp_no,tnt_id_result as tnt_id_result, tnt_id_disapprove as tnt_id_disapprove,TNT_ID_VRFTIME as TNT_ID_VRFTIME from TENANT where tnt_id_isupload=? or tnt_id_result=? union SELECT lld_no AS tnt_no, lld_name as tnt_name, lld_id as tnt_id, lld_birth as tnt_birth , lld_mobile as tnt_mobile, lld_email as tnt_email, lld_id_uploadtime as tnt_id_uploadtime, lld_id_isupload as tnt_id_isupload, emp_no as emp_no, lld_id_result as tnt_id_result, lld_id_disapprove as tnt_id_disapprove,lld_ID_VRFTIME as TNT_ID_VRFTIME from landlord where lld_id_isupload=? or lld_id_result=? ORDER BY TNT_ID_UPLOADTIME";
	private static final String GET_UNVRF_STMT = "SELECT tnt_no, tnt_name, tnt_id, tnt_birth, tnt_mobile, tnt_email, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove,TNT_ID_VRFTIME from TENANT where tnt_id_isupload=?";

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
	public List<TntVO> getAll_vrf(Integer Number, Integer Number2) {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_VRF_STMT);
			pstmt.setInt(1, Number);
			pstmt.setInt(2, Number2);
			pstmt.setInt(3, Number);
			pstmt.setInt(4, Number2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setEmp_no(rs.getString("emp_no"));
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

	private static final String GET_UNVRF_UNRESULT_STMT = "SELECT tnt_no, tnt_name, tnt_id, tnt_birth, tnt_mobile, tnt_email, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove,TNT_ID_VRFTIME from TENANT where tnt_id_isupload=? AND tnt_id_result!=? ORDER BY TNT_ID_UPLOADTIME";
	private static final String GET_ALL_UNVRF_UNRESULT_STMT = "SELECT tnt_no AS tnt_no, tnt_name as tnt_name, tnt_id as tnt_id, tnt_birth as tnt_birth , tnt_mobile as tnt_mobile, tnt_email as tnt_email, tnt_id_uploadtime as tnt_id_uploadtime, tnt_id_isupload as tnt_id_isupload, tnt_id_result as tnt_id_result, tnt_id_disapprove as tnt_id_disapprove,TNT_ID_VRFTIME as TNT_ID_VRFTIME from TENANT where tnt_id_isupload=? AND tnt_id_result!=? union SELECT lld_no AS tnt_no, lld_name as tnt_name, lld_id as tnt_id, lld_birth as tnt_birth , lld_mobile as tnt_mobile, lld_email as tnt_email, lld_id_uploadtime as tnt_id_uploadtime, lld_id_isupload as tnt_id_isupload, lld_id_result as tnt_id_result, lld_id_disapprove as tnt_id_disapprove,lld_ID_VRFTIME as TNT_ID_VRFTIME from landlord where lld_id_isupload=? AND lld_id_result!=? ORDER BY TNT_ID_UPLOADTIME";

	public List<TntVO> getUnvrf_Unresult(Integer Number, Integer Number2) {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNVRF_UNRESULT_STMT);
			pstmt.setInt(1, Number);
			pstmt.setInt(2, Number2);
			pstmt.setInt(3, Number);
			pstmt.setInt(4, Number2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
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

	private static final String GET_VRF_PICF_STMT = "select TNT_ID_PICF,TNT_ID_PICB ,TNT_ID_PIC2 from TENANT where TNT_NO=?";
	private static final String GET_LVRF_PICF_STMT = "select LLD_ID_PICF AS TNT_ID_PICF,LLD_ID_PICB AS TNT_ID_PICB,LLD_ID_PIC2 AS TNT_ID_PIC2 from LANDLORD where LLD_NO=?";

	@Override
	public TntVO findByPK_pic(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (tnt_no.startsWith("T")) {
				pstmt = con.prepareStatement(GET_VRF_PICF_STMT);
			} else if (tnt_no.startsWith("L")) {
				pstmt = con.prepareStatement(GET_LVRF_PICF_STMT);
			} else {
				System.out.println("照片:wrong sql");
			}
			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_id_picf(rs.getBytes("tnt_id_picf"));
				tntVO.setTnt_id_picb(rs.getBytes("tnt_id_picb"));
				tntVO.setTnt_id_pic2(rs.getBytes("tnt_id_pic2"));

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

	// ---------------------------------------------------------------------------------

	private static final String UPDATE_PASS_VRF = "UPDATE TENANT SET TNT_ID_RESULT=?, EMP_NO=? where TNT_NO=?";
	private static final String UPDATE_PASS_LVRF = "UPDATE LANDLORD SET LLD_ID_RESULT=?, EMP_NO=? where LLD_NO=?";
	private static final String UPDATE_FAIL_VRF = "UPDATE TENANT SET TNT_ID_RESULT=?, EMP_NO=?,TNT_ID_DISAPPROVE=?,TNT_ID_ISUPLOAD=? where TNT_NO=?";
	private static final String UPDATE_FAIL_LVRF = "UPDATE LANDLORD SET LLD_ID_RESULT=?, EMP_NO=?,LLD_ID_DISAPPROVE=?,LLD_ID_ISUPLOAD=? where LLD_NO=?";

	public void pass_Vrf(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			if (tntVO.getTnt_no().startsWith("T")) {
				pstmt = con.prepareStatement(UPDATE_PASS_VRF);
			} else if (tntVO.getTnt_no().startsWith("L")) {
				pstmt = con.prepareStatement(UPDATE_PASS_LVRF);
			} else {
				System.out.println("失敗wrong sql");
			}
			pstmt.setInt(1, tntVO.getTnt_id_result());
			pstmt.setString(2, tntVO.getEmp_no());
			pstmt.setString(3, tntVO.getTnt_no());

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

	public void fail_Vrf(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			if (tntVO.getTnt_no().startsWith("T")) {
				pstmt = con.prepareStatement(UPDATE_FAIL_VRF);
			} else if (tntVO.getTnt_no().startsWith("L")) {
				pstmt = con.prepareStatement(UPDATE_FAIL_LVRF);
			} else {
				System.out.println("失敗wrong sql");
			}

			pstmt.setInt(1, tntVO.getTnt_id_result());
			pstmt.setString(2, tntVO.getEmp_no());
			pstmt.setString(3, tntVO.getTnt_id_disapprove());
			pstmt.setInt(4, tntVO.getTnt_id_isupload());
			pstmt.setString(5, tntVO.getTnt_no());

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

	private static final String GET_WANT_VRF_STMT = "SELECT* FROM (SELECT tnt_no AS tnt_no, tnt_name as tnt_name, tnt_id as tnt_id, tnt_birth as tnt_birth , tnt_mobile as tnt_mobile, tnt_email as tnt_email, tnt_id_uploadtime as tnt_id_uploadtime, tnt_id_isupload as tnt_id_isupload, emp_no as emp_no,tnt_id_result as tnt_id_result, tnt_id_disapprove as tnt_id_disapprove,TNT_ID_VRFTIME as TNT_ID_VRFTIME from TENANT where tnt_id_isupload=? or tnt_id_result=? union SELECT lld_no AS tnt_no, lld_name as tnt_name, lld_id as tnt_id, lld_birth as tnt_birth , lld_mobile as tnt_mobile, lld_email as tnt_email, lld_id_uploadtime as tnt_id_uploadtime, lld_id_isupload as tnt_id_isupload, emp_no as emp_no, lld_id_result as tnt_id_result, lld_id_disapprove as tnt_id_disapprove,lld_ID_VRFTIME as TNT_ID_VRFTIME from landlord where lld_id_isupload=? or lld_id_result=? ) WHERE TNT_NO=? OR EMP_NO=? OR TNT_MOBILE=? OR TNT_EMAIL=? OR TNT_ID=? OR TNT_NAME LIKE ? ORDER BY TNT_ID_UPLOADTIME";

	@Override
	public List<TntVO> findByNo(String Number) {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_WANT_VRF_STMT);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, 2);
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 2);
			pstmt.setString(5, Number);
			pstmt.setString(6, Number);
			pstmt.setString(7, Number);
			pstmt.setString(8, Number);
			pstmt.setString(9, Number);
			pstmt.setString(10, "%" + Number + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_birth(rs.getDate("tnt_birth"));
				tntVO.setTnt_id(rs.getString("tnt_id"));
				tntVO.setTnt_mobile(rs.getString("tnt_mobile"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
				tntVO.setEmp_no(rs.getString("emp_no"));
				tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				tntVO.setTnt_id_uploadtime(rs.getTimestamp("tnt_id_uploadtime"));
				tntVO.setTnt_id_vrftime(rs.getTimestamp("tnt_id_vrftime"));
				list.add(tntVO);
			}

		} catch (

		SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());

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

	private static final String TENANT_GET_EMAIL = "select TNT_NAME,TNT_EMAIL,TNT_ACC, TNT_ID_DISAPPROVE from TENANT where TNT_NO=?";
	private static final String LANDLORD_GET_EMAIL = "select LLD_NAME AS TNT_NAME,LLD_EMAIL AS TNT_EMAIL,LLD_ACC AS TNT_ACC , LLD_ID_DISAPPROVE AS TNT_ID_DISAPPROVE from LANDLORD where LLD_NO=?";

	public TntVO findEmail(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			System.out.println("來到dao email");
			if (tnt_no.startsWith("T")) {
				pstmt = con.prepareStatement(TENANT_GET_EMAIL);
			} else if (tnt_no.startsWith("L")) {
				pstmt = con.prepareStatement(LANDLORD_GET_EMAIL);
			} else {
				System.out.println("email:wrong sql");
			}
			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_name(rs.getString("tnt_name"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_acc(rs.getString("tnt_acc"));
				tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));

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

	// --------以下為member--------------------------------------------------------------------------------------------
	private static final String UPDATE_AUTH_STMT = "UPDATE TENANT set tnt_auth_chat=?, tnt_auth_res=?, tnt_auth_cmt=?, tnt_auth_rpt=?, tnt_reported_count=? where tnt_no=?";
	private static final String UPDATE_LLDAUTH_STMT = "UPDATE LANDLORD set lld_auth_chat=?, lld_auth_res=?, lld_auth_cmt=?, lld_auth_rpt=?, lld_reported_count=? where lld_no=?";

	@Override
	public void update_auth(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			if (tntVO.getTnt_no().startsWith("T")) {
				pstmt = con.prepareStatement(UPDATE_AUTH_STMT);
			} else {
				pstmt = con.prepareStatement(UPDATE_LLDAUTH_STMT);
			}
			pstmt.setInt(1, tntVO.getTnt_auth_chat());
			pstmt.setInt(2, tntVO.getTnt_auth_res());
			pstmt.setInt(3, tntVO.getTnt_auth_cmt());
			pstmt.setInt(4, tntVO.getTnt_auth_rpt());
			pstmt.setInt(5, tntVO.getTnt_reported_count());
			pstmt.setString(6, tntVO.getTnt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());

		} catch (Exception se) {
			se.printStackTrace();
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
