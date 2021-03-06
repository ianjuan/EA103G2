package com.rpth.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.rpth.model.LldVO;
import com.rptt.model.RpttVO;

public class LldDAO implements LandlordDAO_interface {

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
	private static final String INSERT_PROFILE_STMT = "INSERT INTO TENANT (LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC)"
			+ "VALUES ('LLD' || lpad(SEQ_LLD_NO.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PROFILE_STMT = "UPDATE TENANT set LLD_EMAIL=?, LLD_ACC=?, LLD_ID=?, LLD_NAME=?, LLD_BIRTH=?, LLD_SEX=?, LLD_MOBILE=?, LLD_CITY=?, LLD_DIST=?, LLD_ADD=?, LLD_STATUS=? where LLD_NO = ?";
	private static final String GET_ALL_PROFILE_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC, LLD_STATUS, LLD_JOINTIME FROM TENANT order by LLD_NO";

	@Override
	public void insert_profile(LldVO lldVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PROFILE_STMT);

			pstmt.setString(1, lldVO.getLld_email());
			pstmt.setString(2, lldVO.getLld_acc());
			pstmt.setString(3, lldVO.getLld_pwd());
			pstmt.setString(4, lldVO.getLld_id());
			pstmt.setString(5, lldVO.getLld_name());
			pstmt.setDate(6, lldVO.getLld_birth());
			pstmt.setBoolean(7, lldVO.getLld_sex());
			pstmt.setString(8, lldVO.getLld_mobile());
			pstmt.setString(9, lldVO.getLld_city());
			pstmt.setString(10, lldVO.getLld_dist());
			pstmt.setString(11, lldVO.getLld_add());
			pstmt.setBytes(12, lldVO.getLld_pic());

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
	public void update_profile(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PROFILE_STMT);

			pstmt.setString(1, lldVO.getLld_email());
			pstmt.setString(2, lldVO.getLld_acc());
			pstmt.setString(3, lldVO.getLld_pwd());
			pstmt.setString(4, lldVO.getLld_id());
			pstmt.setString(5, lldVO.getLld_name());
			pstmt.setDate(6, lldVO.getLld_birth());
			pstmt.setBoolean(7, lldVO.getLld_sex());
			pstmt.setString(8, lldVO.getLld_mobile());
			pstmt.setString(9, lldVO.getLld_city());
			pstmt.setString(10, lldVO.getLld_dist());
			pstmt.setString(11, lldVO.getLld_add());
			pstmt.setBytes(12, lldVO.getLld_pic());
			pstmt.setInt(13, lldVO.getLld_status());
			pstmt.setString(14, lldVO.getLld_no());

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

	private static final String GET_ONE_PROFILE_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_ACC, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_STATUS,LLD_JOINTIME,LLD_BALANCE,LLD_BANK,LLD_BANKBRANCH, LLD_BANKACC,LLD_CARD,LLD_CARDDUE,LLD_CMT_STARSUM,LLD_CMT_COUNT, EMP_NO,LLD_ID_UPLOADTIME,LLD_ID_ISUPLOAD,LLD_ID_RESULT,LLD_ID_VRFTIME,LLD_ID_DISAPPROVE,LLD_REPORTED_COUNT,LLD_AUTH_CHAT,LLD_AUTH_RES,LLD_AUTH_CMT,LLD_AUTH_RPT,LLD_AUTH_HOS FROM LANDLORD where LLD_NO =? or LLD_ID=?";

	@Override
	public LldVO findByPK_profile(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("準備進入DAO TRY");

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PROFILE_STMT);

			pstmt.setString(1, lld_no);
			pstmt.setString(2, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				// -------profile-------
				lldVO.setLld_no(rs.getString("lld_no"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_acc(rs.getString("lld_acc"));
				lldVO.setLld_id(rs.getString("lld_id"));
				lldVO.setLld_name(rs.getString("lld_name"));
				lldVO.setLld_birth(rs.getDate("lld_birth"));
				lldVO.setLld_sex(rs.getBoolean("lld_sex"));
				lldVO.setLld_mobile(rs.getString("lld_mobile"));
				lldVO.setLld_city(rs.getString("lld_city"));
				lldVO.setLld_dist(rs.getString("lld_dist"));
				lldVO.setLld_add(rs.getString("lld_add"));
				lldVO.setLld_status(rs.getInt("lld_status"));
				lldVO.setLld_jointime(rs.getTimestamp("lld_jointime"));
				// -------pocket-------
				lldVO.setLld_balance(rs.getInt("lld_balance"));
				// -------card/bank-------
				lldVO.setLld_bank(rs.getString("lld_bank"));
				lldVO.setLld_bankbranch(rs.getString("lld_bankbranch"));
				lldVO.setLld_bankacc(rs.getString("lld_bankacc"));
				lldVO.setLld_card(rs.getString("lld_card"));
				lldVO.setLld_carddue(rs.getDate("lld_carddue"));
				// -------CMT-------
				lldVO.setLld_cmt_starsum(rs.getInt("lld_cmt_starsum"));
				lldVO.setLld_cmt_count(rs.getInt("lld_cmt_count"));
				// -------VRF-------
				lldVO.setEmp_no(rs.getString("emp_no"));
				lldVO.setLld_id_uploadtime(rs.getTimestamp("lld_id_uploadtime"));
				lldVO.setLld_id_isupload(rs.getInt("lld_id_isupload"));
				lldVO.setLld_id_result(rs.getInt("lld_id_result"));
				lldVO.setLld_id_vrftime(rs.getTimestamp("lld_id_vrftime"));
				lldVO.setLld_id_disapprove(rs.getString("lld_id_disapprove"));
				// -------RPT&AUTH-------
				lldVO.setLld_reported_count(rs.getInt("lld_reported_count"));
				lldVO.setLld_auth_chat(rs.getInt("lld_auth_chat"));
				lldVO.setLld_auth_res(rs.getInt("lld_auth_res"));
				lldVO.setLld_auth_cmt(rs.getInt("lld_auth_cmt"));
				lldVO.setLld_auth_rpt(rs.getInt("lld_auth_rpt"));
				lldVO.setLld_auth_hos(rs.getInt("lld_auth_hos"));

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

		return lldVO;
	}

	@Override
	public List<LldVO> getAll_profile() {
		List<LldVO> list = new ArrayList<LldVO>();
		LldVO lldVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PROFILE_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// lldVO 也稱為 Domain objects
				lldVO = new LldVO();
				lldVO.setLld_no(rs.getString("lld_no"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_acc(rs.getString("lld_acc"));
				lldVO.setLld_pwd(rs.getString("lld_pwd"));
				lldVO.setLld_id(rs.getString("lld_id"));
				lldVO.setLld_name(rs.getString("lld_name"));
				lldVO.setLld_birth(rs.getDate("lld_birth"));
				lldVO.setLld_sex(rs.getBoolean("lld_sex"));
				lldVO.setLld_mobile(rs.getString("lld_mobile"));
				lldVO.setLld_city(rs.getString("lld_city"));
				lldVO.setLld_dist(rs.getString("lld_dist"));
				lldVO.setLld_add(rs.getString("lld_add"));
				lldVO.setLld_pic(rs.getBytes("lld_pic"));
				lldVO.setLld_status(rs.getInt("lld_status"));
				lldVO.setLld_jointime(rs.getTimestamp("lld_jointime"));
				list.add(lldVO);
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

	@Override
	public LldVO findByPK_pic(String lld_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LldVO> findByNo(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String LANDLORD_GET_EMAIL = "SELECT R.RPTH_NO,L.LLD_NAME,L.LLD_EMAIL,H.LLD_NO,H.HOS_NAME FROM REPORT_HOUSE R JOIN HOUSE H ON ( H.HOS_NO=R.HOS_NO)JOIN LANDLORD L ON H.LLD_NO=L.LLD_NO WHERE R.HOS_NO=?";

	@Override
	public LldVO findEmail(String hos_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			System.out.println("來到dao email");

			pstmt = con.prepareStatement(LANDLORD_GET_EMAIL);
			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_name(rs.getString("lld_name"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_acc(rs.getString("hos_name"));
				lldVO.setLld_no(rs.getString("lld_no"));

			}
			System.out.println("dao結束傳回去");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (Exception se) {
			se.getMessage();

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

		return lldVO;

	}
	
	private static final String LANDLORD_GET_EMAIL_NORMAL = "SELECT LLD_NAME,LLD_EMAIL,LLD_NO,LLD_ACC FROM LANDLORD WHERE LLD_NO=?";

	@Override
	public LldVO findEmail_normal(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			System.out.println("來到normal_dao email");

			pstmt = con.prepareStatement(LANDLORD_GET_EMAIL_NORMAL);
			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_name(rs.getString("lld_name"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_acc(rs.getString("lld_acc"));
				lldVO.setLld_no(rs.getString("lld_no"));

			}
			System.out.println("dao結束傳回去");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (Exception se) {
			se.getMessage();

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

		return lldVO;

	}

	@Override
	public void update_auth(LldVO lldvo) {
		// TODO Auto-generated method stub

	}
	
	private static final String LANDLORD_GET_REPAIR = "SELECT L.LLD_NO, L.LLD_NAME, L.LLD_EMAIL,H.HOS_NAME FROM REPORT_REPAIR P JOIN REPAIR R ON P.REP_NO= R.REP_NO JOIN CONTRACT C ON R.CON_NO = C.CON_NO JOIN HOUSE H ON C.HOS_NO = H.HOS_NO JOIN LANDLORD L ON H.LLD_NO=L.LLD_NO WHERE RPTR_NO=?";

	@Override
	public LldVO findRepair(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			System.out.println("來到normal_dao email");

			pstmt = con.prepareStatement(LANDLORD_GET_REPAIR);
			pstmt.setString(1, lld_no);
			System.out.println("LLD_NO實際上為RPTR_NO"+lld_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_name(rs.getString("lld_name"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_acc(rs.getString("hos_name"));
				lldVO.setLld_no(rs.getString("lld_no"));

			}
			System.out.println("dao結束傳回去");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (Exception se) {
			se.getMessage();

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

		return lldVO;

	}

}
