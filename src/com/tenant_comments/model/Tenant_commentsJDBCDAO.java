package com.tenant_comments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.landlord_comments.model.Landlord_commentsVO;

public class Tenant_commentsJDBCDAO implements Tenant_commentsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2DB";
	String passwd = "123456";

//	private static final String TNT_INSERT_STMT="INSERT INTO HOUSE_COMMENTS (HCM_NO, TNT_NO, HOS_NO, HCM_EQPMT, HCM_CONVMT, HCM_NEIBOR, HCM_COMMNT, HCM_TIME) VALUES('HCM' || lpad(SEQ_HCM_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String LLD_INSERT_STMT = "INSERT INTO TENANT_COMMENTS (TCM_NO, LLD_NO, TNT_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMET, TCM_TIME) VALUES ('TCM' || lpad(SEQ_TCM_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?)";

	private static final String TNT_RESP_STMT = "UPDATE TENANT_COMMENTS SET TCM_RESPON=?  WHERE TCM_NO=?";

	private static final String GET_ONE_STMT = "SELECT TCM_NO, LLD_NO, TNT_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMET, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME, TCM_RESPON FROM TENANT_COMMENTS WHERE TCM_NO=?";

	private static final String TNT_GET_ALL_TCM_STMT = "SELECT TNT_NO, TCM_NO, LLD_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMET, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME, TCM_RESPON FROM TENANT_COMMENTS WHERE TNT_NO=?";

	private static final String GET_ALL_TCM_STMT = "SELECT LLD_NO, TNT_NO, TCM_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMET, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME, TCM_RESPON FROM TENANT_COMMENTS WHERE LLD_NO=? ORDER BY TCM_NO DESC";

	private static final String LLD_UPD_STMT = "UPDATE TENANT_COMMENTS SET TCM_COMMET=? WHERE TCM_NO=?";

	private static final String GET_ALL_TNT_TCM = "SELECT TNT_NO, TCM_NO, LLD_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMET, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME, TCM_RESPON FROM TENANT_COMMENTS WHERE TNT_NO=? AND LLD_NO=?";

	@Override
	public void lld_insert(Tenant_commentsVO Tenant_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("有道dao");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_INSERT_STMT);

			pstmt.setString(1, Tenant_commentsVO.getLld_no());
			pstmt.setString(2, Tenant_commentsVO.getTnt_no());
			pstmt.setInt(3, Tenant_commentsVO.getTcm_clean());
			pstmt.setInt(4, Tenant_commentsVO.getTcm_commut());
			pstmt.setInt(5, Tenant_commentsVO.getTcm_satisfy());
			pstmt.setString(6, Tenant_commentsVO.getTcm_commet());
			pstmt.setDate(7, Tenant_commentsVO.getTcm_time());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load Tenant_comments database driver.");
			e.printStackTrace(System.err);

		} catch (SQLException e) {
			System.out.println("Couldn't load  Tenant_comments database error occured.");
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
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
	}

	@Override
	public void tnt_insert(Tenant_commentsVO Tenant_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_RESP_STMT);

			pstmt.setString(1, Tenant_commentsVO.getTcm_respon());
			pstmt.setString(2, Tenant_commentsVO.getTcm_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load  Tenant_comments database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			System.out.println("Couldn't load  Tenant_comments database driver.");
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
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

	}

	@Override
	public Tenant_commentsVO findByPrimaryKey(String tcm_no) {
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tcm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTcm_no(rs.getString("TCM_NO"));
				Tenant_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Tenant_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commet(rs.getString("TCM_COMMET"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException se) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			se.printStackTrace(System.err);
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

		return Tenant_commentsVO;
	}

	@Override
	public List<Tenant_commentsVO> lld_getAll(String lld_no) {
		List<Tenant_commentsVO> list = new ArrayList<Tenant_commentsVO>();
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TCM_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTcm_no(rs.getString("TCM_NO"));
				Tenant_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Tenant_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commet(rs.getString("TCM_COMMET"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
				list.add(Tenant_commentsVO);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException se) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			se.printStackTrace(System.err);
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
	public List<Tenant_commentsVO> tnt_getAll(String tnt_no) {
		List<Tenant_commentsVO> list = new ArrayList<Tenant_commentsVO>();
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_GET_ALL_TCM_STMT);

			pstmt.setString(1, tnt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Tenant_commentsVO.setTcm_no(rs.getString("TCM_NO"));
				Tenant_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commet(rs.getString("TCM_COMMET"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
				list.add(Tenant_commentsVO);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException se) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			se.printStackTrace(System.err);
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
	public void lld_update(Tenant_commentsVO tenant_commentsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_UPD_STMT);

			pstmt.setString(1, tenant_commentsVO.getTcm_commet());
			pstmt.setString(2, tenant_commentsVO.getTcm_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver." + e.getMessage());
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver." + e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
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

	}

	@Override
	public Set<String> lld_getAllTnt(String lld_no) {
		Set<String> set = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TCM_STMT);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				set.add(rs.getString("TNT_NO"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException se) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			se.printStackTrace(System.err);
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
		return set;

	}

	@Override
	public List<Tenant_commentsVO> lld_getAllTntTcm(String tnt_no, String lld_no) {
		List<Tenant_commentsVO> list = new ArrayList<Tenant_commentsVO>();
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TNT_TCM);

			pstmt.setString(1, tnt_no);
			pstmt.setString(2, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTcm_no(rs.getString("TCM_NO"));
				Tenant_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Tenant_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commet(rs.getString("TCM_COMMET"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
				list.add(Tenant_commentsVO);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			e.printStackTrace(System.err);
		} catch (SQLException se) {
			System.out.println("Couldn't load TENANT_COMMENTS database driver.");
			se.printStackTrace(System.err);
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
