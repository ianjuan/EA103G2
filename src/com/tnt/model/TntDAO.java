package com.tnt.model;

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
			+ "VALUES ('TNT' || lpad(SEQ_TNT_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_PROFILEE_NOPIC_STMT = "INSERT INTO TENANT (TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD)"
			+ "VALUES ('TNT' || lpad(SEQ_TNT_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PROFILE_STMT = "UPDATE TENANT set TNT_EMAIL=?, TNT_ACC=?, TNT_PWD=?, TNT_ID=?, TNT_NAME=?, TNT_BIRTH=?, TNT_SEX=?, TNT_MOBILE=?, TNT_CITY=?, TNT_DIST=?, TNT_ADD=?, TNT_PIC=?, TNT_STATUS=? where TNT_NO = ?";
	private static final String UPDATE_PROFILE_NOPIC_STMT = "UPDATE TENANT set TNT_EMAIL=?, TNT_ACC=?, TNT_PWD=?, TNT_ID=?, TNT_NAME=?, TNT_BIRTH=?, TNT_SEX=?, TNT_MOBILE=?, TNT_CITY=?, TNT_DIST=?, TNT_ADD=?, TNT_STATUS=? where TNT_NO = ?";
	private static final String GET_ONE_PROFILE_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_PIC, TNT_STATUS, TNT_JOINTIME FROM TENANT where TNT_NO = ?";
	private static final String GET_ALL_PROFILE_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_ACC, TNT_PWD, TNT_ID, TNT_NAME, TNT_BIRTH, TNT_SEX, TNT_MOBILE, TNT_CITY, TNT_DIST, TNT_ADD, TNT_PIC, TNT_STATUS, TNT_JOINTIME FROM TENANT order by TNT_NO";

	private static final String GET_ALL_ACCOUNT_STMT = "SELECT tnt_no, tnt_email, tnt_pwd from TENANT";
	private static final String GET_ONE_ACCOUNT_STMT = "SELECT TNT_NO, TNT_EMAIL, TNT_PWD FROM TENANT where TNT_NO =?";
	private static final String UPDATE_PWD_STMT = "UPDATE TENANT set TNT_PWD=? where TNT_NO=?";
	private static final String UPDATE_STATUS_STMT = "UPDATE TENANT set TNT_STATUS=? where TNT_NO=?";

	private static final String UPDATE_PIC_STMT = "UPDATE TENANT set TNT_PIC=? where TNT_NO = ?";
	private static final String GET_ONE_PIC_STMT = "SELECT TNT_NO, TNT_PIC FROM TENANT where TNT_NO = ?";

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
	public void insert_profile(TntVO tntVO, Boolean updatePic) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			if (updatePic) {
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

			} else {
				pstmt = con.prepareStatement(INSERT_PROFILEE_NOPIC_STMT);

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
			}

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
	public void update_profile(TntVO tntVO, Boolean updatePic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();

			if (updatePic) {
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

			} else {
				pstmt = con.prepareStatement(UPDATE_PROFILE_NOPIC_STMT);

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
				pstmt.setInt(12, tntVO.getTnt_status());
				pstmt.setString(13, tntVO.getTnt_no());

				pstmt.executeUpdate();

			}

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

	@Override
	public List<TntVO> getAll_account() {
		List<TntVO> list = new ArrayList<TntVO>();
		TntVO tntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ACCOUNT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tntVO 也稱為 Domain objects
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_pwd(rs.getString("tnt_pwd"));
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

	@Override
	public TntVO findByPK_account(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT_STMT);

			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_email(rs.getString("tnt_email"));
				tntVO.setTnt_pwd(rs.getString("tnt_pwd"));
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
	public void update_pwd(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			System.out.println(tntVO.getTnt_pwd());
//			System.out.println(tntVO.getTnt_no());
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_STMT);

			pstmt.setString(1, tntVO.getTnt_pwd());
			pstmt.setString(2, tntVO.getTnt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("12323A database error occured. " + se.getMessage());
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
	public void update_status(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			System.out.println(tntVO.getTnt_pwd());
//			System.out.println(tntVO.getTnt_no());
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, tntVO.getTnt_status());
			pstmt.setString(2, tntVO.getTnt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("12323A database error occured. " + se.getMessage());
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
	public void update_pic(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PIC_STMT);

			pstmt.setBytes(1, tntVO.getTnt_pic());
			pstmt.setString(2, tntVO.getTnt_no());

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
	public TntVO findByPK_pic(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PIC_STMT);

			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_no(rs.getString("tnt_no"));
				tntVO.setTnt_pic(rs.getBytes("tnt_pic"));

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

	// =================================2.pocket==================================
	private static final String UPDATE_POCKET_STMT = "UPDATE TENANT set tnt_balance=? where tnt_no=?";
	private static final String GET_ONE_POCKET_STMT = "SELECT tnt_balance from TENANT where tnt_no=?";

	@Override
	public void update_pocket(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POCKET_STMT);

			pstmt.setInt(1, tntVO.getTnt_balance());
			pstmt.setString(2, tntVO.getTnt_no());

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

	@Override
	public TntVO findByPK_pocket(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_POCKET_STMT);

			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_balance(rs.getInt("tnt_balance"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
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

	// =================================3.bank/card==================================
	private static final String UPDATE_BANK_CARD_STMT = "UPDATE TENANT set tnt_card=?, tnt_cardsvc=?, tnt_carddue=?, tnt_bank=?, tnt_bankbranch=?, tnt_bankacc=? where tnt_no=?";
	private static final String GET_ONE_BANK_CARD_STMT = "SELECT tnt_card, tnt_cardsvc, tnt_carddue, tnt_bank, tnt_bankbranch, tnt_bankacc from TENANT where tnt_no=?";

	@Override
	public void update_bank_card(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BANK_CARD_STMT);
			pstmt.setString(1, tntVO.getTnt_card());
			pstmt.setString(2, tntVO.getTnt_cardsvc());
			pstmt.setDate(3, tntVO.getTnt_carddue());
			pstmt.setString(4, tntVO.getTnt_bank());
			pstmt.setString(5, tntVO.getTnt_bankbranch());
			pstmt.setString(6, tntVO.getTnt_bankacc());
			pstmt.setString(7, tntVO.getTnt_no());

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
	public TntVO findByPK_bank_card(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BANK_CARD_STMT);
			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO = new TntVO();
				tntVO.setTnt_card(rs.getString("tnt_card"));
				tntVO.setTnt_cardsvc(rs.getString("tnt_cardsvc"));
				tntVO.setTnt_carddue(rs.getDate("tnt_carddue"));
				tntVO.setTnt_bank(rs.getString("tnt_bank"));
				tntVO.setTnt_bankbranch(rs.getString("tnt_bankbranch"));
				tntVO.setTnt_bankacc(rs.getString("tnt_bankacc"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// =================================4.cmt==================================
	private static final String UPDATE_CMT_STMT = "UPDATE TENANT set tnt_cmt_starsum=?, tnt_cmt_count=? where tnt_no=?";
	private static final String GET_ONE_CMT_STMT = "SELECT tnt_cmt_starsum, tnt_cmt_count from TENANT where tnt_no=?";

	@Override
	public void update_cmt(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CMT_STMT);

			pstmt.setInt(1, tntVO.getTnt_cmt_starsum());
			pstmt.setInt(2, tntVO.getTnt_cmt_count());
			pstmt.setString(3, tntVO.getTnt_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public TntVO findByPK_cmt(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CMT_STMT);

			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO.setTnt_cmt_starsum(rs.getInt("tnt_cmt_starsum"));
				tntVO.setTnt_cmt_count(rs.getInt("tnt_cmt_count"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
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

	// =================================5.vrf==================================
	private static final String UPDATE_VRF_STMT = "UPDATE TENANT set tnt_id_picf=?, tnt_id_picb=?, tnt_id_pic2=?, tnt_id_uploadtime=?, tnt_id_isupload=?, tnt_id_result=?, tnt_id_disapprove=?, tnt_id_vrftime=? where tnt_no=?";
	private static final String GET_ONE_VRF_STMT = "SELECT tnt_id_picf, tnt_id_picb, tnt_id_pic2, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove, tnt_id_vrftime from TENANT where tnt_no=?";
	private static final String GET_ONE_VRF_NOPICS_STMT = "SELECT tnt_id_isupload, tnt_id_result, tnt_id_disapprove, from TENANT where tnt_no=?";
	private static final String GET_ALL_VRF_STMT = "SELECT tnt_id_picf, tnt_id_picb, tnt_id_pic2, tnt_id_uploadtime, tnt_id_isupload, tnt_id_result, tnt_id_disapprove, tnt_id_vrftime from TENANT ORDER BY tnt_no";

	private static final String UPDATE_VRF_PICS_STMT = "UPDATE TENANT set tnt_id_picf=?, tnt_id_picb=?, tnt_id_pic2=?, tnt_id_isupload=? where tnt_no=?";

	@Override
	public void update_vrf_pics(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VRF_PICS_STMT);

			pstmt.setBytes(1, tntVO.getTnt_id_picf());
			pstmt.setBytes(2, tntVO.getTnt_id_picb());
			pstmt.setBytes(3, tntVO.getTnt_id_pic2());
			pstmt.setInt(4, tntVO.getTnt_id_isupload());
			pstmt.setString(5, tntVO.getTnt_no());

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
	public TntVO findByPK_vrf(String tnt_no, Boolean getVrfPics) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VRF_NOPICS_STMT);

			if (!getVrfPics) {
				pstmt.setString(1, tnt_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					tntVO.setTnt_id_isupload(rs.getInt("tnt_id_isupload"));
					tntVO.setTnt_id_result(rs.getInt("tnt_id_result"));
					tntVO.setTnt_id_disapprove(rs.getString("tnt_id_disapprove"));
				}
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

	// =================================6.rpt==================================
	private static final String UPDATE_RPT_STMT = "UPDATE TENANT set tnt_reported_count=? where tnt_no=?";
	private static final String GET_ONE_RPT_STMT = "SELECT tnt_reported_count from TENANT where tnt_no=?";

	@Override
	public void update_rpt(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RPT_STMT);

			pstmt.setInt(1, tntVO.getTnt_reported_count());
			pstmt.setString(2, tntVO.getTnt_no());

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
	public TntVO findByPK_rpt(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RPT_STMT);

			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO.setTnt_reported_count(rs.getInt("tnt_reported_count"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
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

	// =================================7.auth==================================
	private static final String UPDATE_AUTH_STMT = "UPDATE TENANT set tnt_auth_chat=?, tnt_auth_res=?, tnt_auth_cmt=?, tnt_auth_rpt=? where tnt_no=?";
	private static final String GET_ONE_AUTH_STMT = "SELECT tnt_auth_chat, tnt_auth_res, tnt_auth_cmt, tnt_auth_rpt from TENANT where tnt_no=?";

	@Override
	public void update_auth(TntVO tntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AUTH_STMT);

			pstmt.setInt(1, tntVO.getTnt_auth_chat());
			pstmt.setInt(2, tntVO.getTnt_auth_res());
			pstmt.setInt(3, tntVO.getTnt_auth_cmt());
			pstmt.setInt(4, tntVO.getTnt_auth_rpt());
			pstmt.setString(5, tntVO.getTnt_no());

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
	public TntVO findByPK_auth(String tnt_no) {
		TntVO tntVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AUTH_STMT);

			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tntVO.setTnt_auth_chat(rs.getInt("tnt_auth_chat"));
				tntVO.setTnt_auth_res(rs.getInt("tnt_auth_res"));
				tntVO.setTnt_auth_cmt(rs.getInt("tnt_auth_cmt"));
				tntVO.setTnt_auth_rpt(rs.getInt("tnt_auth_rpt"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
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

}
