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
	private static final String INSERT_PROFILE_STMT = "INSERT INTO TENANT (LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC)"
			+ "VALUES ('TNT' || lpad(SEQ_LLD_NO.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PROFILE_STMT = "UPDATE TENANT set LLD_EMAIL=?, LLD_ACC=?, LLD_PWD=?, LLD_ID=?, LLD_NAME=?, LLD_BIRTH=?, LLD_SEX=?, LLD_MOBILE=?, LLD_CITY=?, LLD_DIST=?, LLD_ADD=?, LLD_PIC=?, LLD_STATUS=? where LLD_NO = ?";
	private static final String GET_ONE_PROFILE_STMT = "SELECT LLD_NO, LLD_EMAIL, LLD_ACC, LLD_PWD, LLD_ID, LLD_NAME, LLD_BIRTH, LLD_SEX, LLD_MOBILE, LLD_CITY, LLD_DIST, LLD_ADD, LLD_PIC, LLD_STATUS, LLD_JOINTIME FROM TENANT where LLD_NO = ?";
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

	// =================================2.pocket==================================
			private static final String UPDATE_POCKET_STMT = "UPDATE TENANT set lld_blance=? where lld_no=?";
			private static final String GET_ONE_POCKET_STMT = "SELECT lld_blance from TENANT where lld_no=?";

			@Override
			public void update_pocket(LldVO lldVO) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE_POCKET_STMT);

					pstmt.setInt(1, lldVO.getLld_blance());
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
						lldVO.setLld_blance(rs.getInt("lld_blance"));
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
	private static final String UPDATE_BANK_CARD_STMT = "UPDATE TENANT set lld_card=?, lld_cardsvc=?, lld_carddue=?, lld_bank=?, lld_bankbrach=?, lld_bankacc=? where lld_no=?";
	private static final String GET_ONE_BANK_CARD_STMT = "SELECT lld_card, lld_cardsvc, lld_carddue, lld_bank, lld_bankbrach, lld_bankacc from TENANT where lld_no=?";

	@Override
	public void update_bank_card(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BANK_CARD_STMT);
			pstmt.setLong(1, lldVO.getLld_card());
			pstmt.setInt(2, lldVO.getLld_cardsvc());
			pstmt.setDate(3, lldVO.getLld_carddue());
			pstmt.setInt(4, lldVO.getLld_bank());
			pstmt.setString(5, lldVO.getLld_bankbrach());
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

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BANK_CARD_STMT);
			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO = new LldVO();
				lldVO.setLld_card(rs.getLong("lld_card"));
				lldVO.setLld_cardsvc(rs.getInt("lld_cardsvc"));
				lldVO.setLld_carddue(rs.getDate("lld_carddue"));
				lldVO.setLld_bank(rs.getInt("lld_bank"));
				lldVO.setLld_bankbrach(rs.getString("lld_bank"));
				lldVO.setLld_bankacc(rs.getString("lld_bank"));
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
	private static final String UPDATE_CMT_STMT = "UPDATE TENANT set lld_cmt_starsum=?, lld_cmt_count=? where lld_no=?";
	private static final String GET_ONE_CMT_STMT = "SELECT lld_cmt_starsum, lld_cmt_count from TENANT where lld_no=?";

	@Override
	public void update_cmt(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CMT_STMT);

			pstmt.setInt(1, lldVO.getLld_cmt_starsum());
			pstmt.setInt(2, lldVO.getLld_cmt_count());

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
	private static final String UPDATE_VRF_STMT = "UPDATE TENANT set lld_id_picf=?, lld_id_picb=?, lld_id_pic2=?, lld_id_uploadtime=?, lld_id_isvrfed=?, lld_id_disapprove=?, lld_id_vrftime=? where lld_no=?";
	private static final String GET_ONE_VRF_STMT = "SELECT lld_id_picf, lld_id_picb, lld_id_pic2, lld_id_uploadtime, lld_id_isvrfed, lld_id_disapprove, lld_id_vrftime from TENANT where lld_no=?";
	private static final String GET_ALL_VRF_STMT = "SELECT lld_id_picf, lld_id_picb, lld_id_pic2, lld_id_uploadtime, lld_id_isvrfed, lld_id_disapprove, lld_id_vrftime from TENANT ORDER BY lld_no";

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
			pstmt.setInt(5, lldVO.getLld_id_isvrfed());
			pstmt.setString(6, lldVO.getLld_id_disapprove());
			pstmt.setTimestamp(7, lldVO.getLld_id_vrftime());
			pstmt.setString(8, lldVO.getLld_no());

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
				lldVO.setLld_id_picf(rs.getBytes("lld_id_picf"));
				lldVO.setLld_id_picb(rs.getBytes("lld_id_picb"));
				lldVO.setLld_id_pic2(rs.getBytes("lld_id_pic2"));
				lldVO.setLld_id_uploadtime(rs.getTimestamp("lld_id_uploadtime"));
				lldVO.setLld_id_isvrfed(rs.getInt("lld_id_isvrfed"));
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
				lldVO.setLld_id_picf(rs.getBytes("lld_id_picf"));
				lldVO.setLld_id_picb(rs.getBytes("lld_id_picb"));
				lldVO.setLld_id_pic2(rs.getBytes("lld_id_pic2"));
				lldVO.setLld_id_uploadtime(rs.getTimestamp("lld_id_uploadtime"));
				lldVO.setLld_id_isvrfed(rs.getInt("lld_id_isvrfed"));
				lldVO.setLld_id_disapprove(rs.getString("lld_id_disapprove"));
				lldVO.setLld_id_vrftime(rs.getTimestamp("lld_id_vrftime"));
				list.add(lldVO);
			}
			
		}catch(SQLException se) {
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

	// =================================6.rpt&auth==================================
	private static final String UPDATE_RPT_AUTH_STMT = "UPDATE TENANT set lld_reported_count=?, lld_auth_chat=?, lld_auth_res=?, lld_auth_cmt=?, lld_auth_rpt=?, lld_auth_hos=? where lld_no=?";
	private static final String GET_ONE_RPT_AUTH_STMT = "SELECT lld_reported_count, lld_auth_chat, lld_auth_res, lld_auth_cmt, lld_auth_rpt, lld_auth_hos from TENANT where lld_no=?";
	
	@Override
	public void update_rpt_auth(LldVO lldVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RPT_AUTH_STMT);

			pstmt.setInt(1, lldVO.getLld_reported_count());
			pstmt.setInt(2, lldVO.getLld_auth_chat());
			pstmt.setInt(3, lldVO.getLld_auth_res());
			pstmt.setInt(4, lldVO.getLld_auth_cmt());
			pstmt.setInt(5, lldVO.getLld_auth_rpt());
			pstmt.setInt(6, lldVO.getLld_auth_hos());
			pstmt.setString(7, lldVO.getLld_no());

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
	public LldVO findByPK_rpt_auth(String lld_no) {
		LldVO lldVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RPT_AUTH_STMT);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lldVO.setLld_reported_count(rs.getInt("lld_reported_count"));
				lldVO.setLld_auth_chat(rs.getInt("lld_auth_chat"));
				lldVO.setLld_auth_res(rs.getInt("lld_auth_res"));
				lldVO.setLld_auth_cmt(rs.getInt("lld_auth_cmt"));
				lldVO.setLld_auth_rpt(rs.getInt("lld_auth_rpt"));
				lldVO.setLld_auth_hos(rs.getInt("lld_auth_hos"));
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
