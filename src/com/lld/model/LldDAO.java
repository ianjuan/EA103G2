package com.lld.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	private static final String INSERT_PROFILE_STMT = "INSERT INTO LANDLORD (LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC)"
			+ "VALUES ('LLD' || lpad(SEQ_LLD_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_PROFILEE_NOPIC_STMT = "INSERT INTO LANDLORD (LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD)"
			+ "VALUES ('LLD' || lpad(SEQ_LLD_NO.NEXTVAL, 6, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PROFILE_STMT = "UPDATE LANDLORD set LLD_EMAIL=?, LLD_ACC=?, LLD_PWD=?, LLD_ID=?, LLD_NAME=?, LLD_BIRTH=?, LLD_SEX=?, LLD_MOBILE=?, LLD_CITY=?, LLD_DIST=?, LLD_ADD=?, LLD_PIC=?, LLD_STATUS=? where LLD_NO = ?";
	private static final String UPDATE_PROFILE_NOPIC_STMT = "UPDATE LANDLORD set LLD_EMAIL=?, LLD_ACC=?, LLD_PWD=?, LLD_ID=?, LLD_NAME=?, LLD_BIRTH=?, LLD_SEX=?, LLD_MOBILE=?, LLD_CITY=?, LLD_DIST=?, LLD_ADD=?, LLD_STATUS=? where LLD_NO = ?";
	private static final String GET_ONE_PROFILE_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC, LLD_STATUS, LLD_JOINTIME FROM LANDLORD where LLD_NO = ?";
	private static final String GET_ALL_PROFILE_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC, LLD_STATUS, LLD_JOINTIME FROM LANDLORD order by LLD_NO";

	private static final String GET_ALL_ACCOUNT_STMT = "SELECT lld_no, lld_email, lld_pwd from LANDLORD";
	private static final String GET_ONE_ACCOUNT_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_PWD FROM LANDLORD where LLD_NO =?";
	private static final String UPDATE_PWD_STMT = "UPDATE LANDLORD set LLD_PWD=? where LLD_NO=?";
	private static final String UPDATE_STATUS_STMT = "UPDATE LANDLORD set LLD_STATUS=? where LLD_NO=?";

	private static final String UPDATE_PIC_STMT = "UPDATE LANDLORD set LLD_PIC=? where LLD_NO = ?";
	private static final String GET_ONE_PIC_STMT = "SELECT LLD_NO, LLD_PIC FROM LANDLORD where LLD_NO = ?";

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
	public void insert_profile(LldVO lldVO, Boolean updatePic) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			if (updatePic) {
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

			} else {
				pstmt = con.prepareStatement(INSERT_PROFILEE_NOPIC_STMT);

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

	@Override
	public void update_profile(LldVO lldVO, Boolean updatePic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();

			if (updatePic) {
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

			} else {
				pstmt = con.prepareStatement(UPDATE_PROFILE_NOPIC_STMT);

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
				pstmt.setInt(12, lldVO.getLld_status());
				pstmt.setString(13, lldVO.getLld_no());

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
	public LldVO findByPK_profile(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PROFILE_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public List<LldVO> getAll_account() {
		List<LldVO> list = new ArrayList<LldVO>();
		LldVO lldVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ACCOUNT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// lldVO 也稱為 Domain objects
				lldVO = new LldVO();
				lldVO.setLld_no(rs.getString("lld_no"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_pwd(rs.getString("lld_pwd"));
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
	public LldVO findByPK_account(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_no(rs.getString("lld_no"));
				lldVO.setLld_email(rs.getString("lld_email"));
				lldVO.setLld_pwd(rs.getString("lld_pwd"));
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
	public void update_pwd(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			System.out.println(lldVO.getLld_pwd());
//			System.out.println(lldVO.getLld_no());
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_STMT);

			pstmt.setString(1, lldVO.getLld_pwd());
			pstmt.setString(2, lldVO.getLld_no());

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
	public void update_status(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
//			System.out.println(lldVO.getLld_pwd());
//			System.out.println(lldVO.getLld_no());
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, lldVO.getLld_status());
			pstmt.setString(2, lldVO.getLld_no());

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
	public void update_pic(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PIC_STMT);

			pstmt.setBytes(1, lldVO.getLld_pic());
			pstmt.setString(2, lldVO.getLld_no());

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
	public LldVO findByPK_pic(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PIC_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_no(rs.getString("lld_no"));
				lldVO.setLld_pic(rs.getBytes("lld_pic"));

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

	// =================================2.pocket==================================
	private static final String UPDATE_POCKET_STMT = "UPDATE LANDLORD set lld_balance=? where lld_no=?";
	private static final String GET_ONE_POCKET_STMT = "SELECT lld_balance from LANDLORD where lld_no=?";

	@Override
	public void update_pocket(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POCKET_STMT);

			pstmt.setInt(1, lldVO.getLld_balance());
			pstmt.setString(2, lldVO.getLld_no());

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
	public LldVO findByPK_pocket(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_POCKET_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_balance(rs.getInt("lld_balance"));
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
		return lldVO;
	}

	// =================================3.bank/card==================================
	private static final String UPDATE_BANK_CARD_STMT = "UPDATE LANDLORD set lld_card=?, lld_cardsvc=?, lld_carddue=?, lld_bank=?, lld_bankbranch=?, lld_bankacc=? where lld_no=?";
	private static final String GET_ONE_BANK_CARD_STMT = "SELECT lld_card, lld_cardsvc, lld_carddue, lld_bank, lld_bankbranch, lld_bankacc from LANDLORD where lld_no=?";

	@Override
	public void update_bank_card(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BANK_CARD_STMT);
			pstmt.setString(1, lldVO.getLld_card());
			pstmt.setString(2, lldVO.getLld_cardsvc());
			pstmt.setDate(3, lldVO.getLld_carddue());
			pstmt.setString(4, lldVO.getLld_bank());
			pstmt.setString(5, lldVO.getLld_bankbranch());
			pstmt.setString(6, lldVO.getLld_bankacc());
			pstmt.setString(7, lldVO.getLld_no());

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
	public LldVO findByPK_bank_card(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("lldDAO-findByPK_bank_card");
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BANK_CARD_STMT);
			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();
			System.out.println("lldDAO-findByPK_bank_card1");
			
			while (rs.next()) {
				System.out.println("lldDAO-findByPK_bank_card2");
				lldVO = new LldVO();
				System.out.println("lldDAO-findByPK_bank_card3");
				lldVO.setLld_card(rs.getString("lld_card"));
				System.out.println("lldDAO-findByPK_bank_card4");
				lldVO.setLld_cardsvc(rs.getString("lld_cardsvc"));
				System.out.println("lldDAO-findByPK_bank_card5");
				lldVO.setLld_carddue(rs.getDate("lld_carddue"));
				System.out.println("lldDAO-findByPK_bank_card6");
				lldVO.setLld_bank(rs.getString("lld_bank"));
				System.out.println("lldDAO-findByPK_bank_card7");
				lldVO.setLld_bankbranch(rs.getString("lld_bankbranch"));
				System.out.println("lldDAO-findByPK_bank_card8");
				lldVO.setLld_bankacc(rs.getString("lld_bankacc"));
				
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
		return lldVO;
	}

	// =================================4.cmt==================================
	private static final String UPDATE_CMT_STMT = "UPDATE LANDLORD set lld_cmt_starsum=?, lld_cmt_count=? where lld_no=?";
	private static final String GET_ONE_CMT_STMT = "SELECT lld_cmt_starsum, lld_cmt_count from LANDLORD where lld_no=?";

	@Override
	public void update_cmt(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CMT_STMT);

			pstmt.setInt(1, lldVO.getLld_cmt_starsum());
			pstmt.setInt(2, lldVO.getLld_cmt_count());
			pstmt.setString(3, lldVO.getLld_no());

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
	public LldVO findByPK_cmt(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CMT_STMT);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_cmt_starsum(rs.getInt("lld_cmt_starsum"));
				lldVO.setLld_cmt_count(rs.getInt("lld_cmt_count"));
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
		return lldVO;
	}

	// =================================5.vrf==================================
	private static final String UPDATE_VRF_STMT = "UPDATE LANDLORD set lld_id_picf=?, lld_id_picb=?, lld_id_pic2=?, lld_id_uploadtime=?, lld_id_isupload=?, lld_id_result=?, lld_id_disapprove=?, lld_id_vrftime=? where lld_no=?";
	private static final String GET_ONE_VRF_STMT = "SELECT lld_id_picf, lld_id_picb, lld_id_pic2, lld_id_uploadtime, lld_id_isupload, lld_id_result, lld_id_disapprove, lld_id_vrftime from LANDLORD where lld_no=?";
	private static final String GET_ONE_VRF_NOPICS_STMT = "SELECT lld_id_isupload, lld_id_result, lld_id_disapprove from LANDLORD where lld_no=?";
	private static final String GET_ALL_VRF_STMT = "SELECT lld_id_picf, lld_id_picb, lld_id_pic2, lld_id_uploadtime, lld_id_isupload, lld_id_result, lld_id_disapprove, lld_id_vrftime from LANDLORD ORDER BY lld_no";

	private static final String UPDATE_VRF_PICS_STMT = "UPDATE LANDLORD set lld_id_picf=?, lld_id_picb=?, lld_id_pic2=?, lld_id_isupload=? where lld_no=?";

	@Override
	public void update_vrf_pics(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VRF_PICS_STMT);

			pstmt.setBytes(1, lldVO.getLld_id_picf());
			pstmt.setBytes(2, lldVO.getLld_id_picb());
			pstmt.setBytes(3, lldVO.getLld_id_pic2());
			pstmt.setInt(4, lldVO.getLld_id_isupload());
			pstmt.setString(5, lldVO.getLld_no());

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
	public void update_vrf(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VRF_STMT);

			pstmt.setBytes(1, lldVO.getLld_id_picf());
			pstmt.setBytes(2, lldVO.getLld_id_picb());
			pstmt.setBytes(3, lldVO.getLld_id_pic2());
			pstmt.setTimestamp(4, lldVO.getLld_id_uploadtime());
			pstmt.setInt(5, lldVO.getLld_id_isupload());
			pstmt.setInt(6, lldVO.getLld_id_result());
			pstmt.setString(7, lldVO.getLld_id_disapprove());
			pstmt.setTimestamp(8, lldVO.getLld_id_vrftime());
			pstmt.setString(9, lldVO.getLld_no());

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
	public LldVO findByPK_vrf(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VRF_STMT);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_id_picf(rs.getBytes("lld_id_picf"));
				lldVO.setLld_id_picb(rs.getBytes("lld_id_picb"));
				lldVO.setLld_id_pic2(rs.getBytes("lld_id_pic2"));
				lldVO.setLld_id_uploadtime(rs.getTimestamp("lld_id_uploadtime"));
				lldVO.setLld_id_isupload(rs.getInt("lld_id_isupload"));
				lldVO.setLld_id_result(rs.getInt("lld_id_result"));
				lldVO.setLld_id_disapprove(rs.getString("lld_id_disapprove"));
				lldVO.setLld_id_vrftime(rs.getTimestamp("lld_id_vrftime"));
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
		return lldVO;
	}

	@Override
	public LldVO findByPK_vrf(String lld_no, Boolean getVrfPics) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VRF_NOPICS_STMT);

			if (!getVrfPics) {
				
				pstmt.setString(1, lld_no);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					lldVO = new LldVO();
					lldVO.setLld_id_isupload(rs.getInt("lld_id_isupload"));
					lldVO.setLld_id_result(rs.getInt("lld_id_result"));
					lldVO.setLld_id_disapprove(rs.getString("lld_id_disapprove"));
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
		return lldVO;
	}

	@Override
	public List<LldVO> getAll_vrf() {
		List<LldVO> list = new ArrayList<LldVO>();
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_VRF_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_id_picf(rs.getBytes("lld_id_picf"));
				lldVO.setLld_id_picb(rs.getBytes("lld_id_picb"));
				lldVO.setLld_id_pic2(rs.getBytes("lld_id_pic2"));
				lldVO.setLld_id_uploadtime(rs.getTimestamp("lld_id_uploadtime"));
				lldVO.setLld_id_isupload(rs.getInt("lld_id_isupload"));
				lldVO.setLld_id_result(rs.getInt("lld_id_result"));
				lldVO.setLld_id_disapprove(rs.getString("lld_id_disapprove"));
				lldVO.setLld_id_vrftime(rs.getTimestamp("lld_id_vrftime"));
				list.add(lldVO);
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
	private static final String UPDATE_RPT_STMT = "UPDATE LANDLORD set lld_reported_count=? where lld_no=?";
	private static final String GET_ONE_RPT_STMT = "SELECT lld_reported_count from LANDLORD where lld_no=?";

	@Override
	public void update_rpt(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RPT_STMT);

			pstmt.setInt(1, lldVO.getLld_reported_count());
			pstmt.setString(2, lldVO.getLld_no());

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
	public LldVO findByPK_rpt(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RPT_STMT);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_reported_count(rs.getInt("lld_reported_count"));
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
		return lldVO;
	}

	// =================================7.auth==================================
	private static final String UPDATE_AUTH_STMT = "UPDATE LANDLORD set lld_auth_chat=?, lld_auth_res=?, lld_auth_cmt=?, lld_auth_rpt=? where lld_no=?";
	private static final String GET_ONE_AUTH_STMT = "SELECT lld_auth_chat, lld_auth_res, lld_auth_cmt, lld_auth_rpt from LANDLORD where lld_no=?";

	@Override
	public void update_auth(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AUTH_STMT);

			pstmt.setInt(1, lldVO.getLld_auth_chat());
			pstmt.setInt(2, lldVO.getLld_auth_res());
			pstmt.setInt(3, lldVO.getLld_auth_cmt());
			pstmt.setInt(4, lldVO.getLld_auth_rpt());
			pstmt.setString(5, lldVO.getLld_no());

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
	public LldVO findByPK_auth(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AUTH_STMT);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_auth_chat(rs.getInt("lld_auth_chat"));
				lldVO.setLld_auth_res(rs.getInt("lld_auth_res"));
				lldVO.setLld_auth_cmt(rs.getInt("lld_auth_cmt"));
				lldVO.setLld_auth_rpt(rs.getInt("lld_auth_rpt"));
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
		return lldVO;
	}

}
