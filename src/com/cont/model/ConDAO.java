package com.cont.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConDAO implements ConDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String BEFORE_RENT_INSERT_STMT = "INSERT INTO CONTRACT(CON_NO, APL_NO, TNT_NO, HOS_NO) VALUES ('CON' || lpad(SEQ_CON_NO.NEXTVAL, 6, '0'), ?, ?, ?)";

	private static final String BEFORE_RENT_UPDATE_STMT = "UPDATE CONTRACT SET APL_NO = ?, TNT_NO = ?, HOS_NO = ?, CON_LLD_SIGN = ?, CON_TNT_SIGN = ?, CON_CHE_DATE = ?, CON_DEP_STA = ?, HOS_DEP = ?, CON_STA = ? WHERE CON_NO = ?";

	private static final String RENT_UPDATE_STMT = "UPDATE CONTRACT SET HOS_DEP = ?, CON_DEP_STA = ?, CON_CHKDATE = ?, CON_COMCHKDATE = ? WHERE CON_NO = ?";

	private static final String UPDATE_STA = "UPDATE CONTRACT SET CON_STA = ? WHERE CON_NO = ?";
	
	private static final String BEFORE_CHECKOUT_UPDATE_STMT = "UPDATE CONTRACT SET HOS_DEP = ?, CON_DEP_STA = ?, CON_COMCHKDATE = ?, CON_CHKDATE = ?, CON_CHK_STA = ?, CON_CHR_FEE = ?, CON_CHR_ITM = ?, CON_IS_CHR = ? WHERE CON_NO = ?";

	private static final String CHECKOUT_UPDATE_STMT = "UPDATE CONTRACT SET CON_RENT_AGN = ?, CON_BILL_PAID = ?, CON_LASTB_PDATE = ?, CON_DEP_BKDATE = ?, CON_OUT_NORMAL = ? WHERE CON_NO = ?";

	private static final String GET_ALL_STMT = "SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_LLD_SIGNTIME, "
			+ "CON_TNT_SIGN, to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_TNT_SIGNTIME, to_char(CON_DATE, 'yyyy-mm-dd hh:mm:ss')CON_DATE, to_char(CON_CHE_DATE, 'yyyy-mm-dd')CON_CHE_DATE, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd')CON_CHKDATE, "
			+ "CON_COMCHKDATE, CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd')CON_LASTB_PDATE, to_char(CON_DEP_BKDATE, 'yyyy-mm-dd')CON_DEP_BKDATE, CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT ORDER BY CON_NO";

	private static final String GET_ONE_STMT = "SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_LLD_SIGNTIME, "
			+ "CON_TNT_SIGN, to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_TNT_SIGNTIME, to_char(CON_DATE, 'yyyy-mm-dd hh:mm:ss')CON_DATE, to_char(CON_CHE_DATE, 'yyyy-mm-dd')CON_CHE_DATE, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd')CON_CHKDATE, "
			+ "CON_COMCHKDATE, CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd')CON_LASTB_PDATE, to_char(CON_DEP_BKDATE, 'yyyy-mm-dd')CON_DEP_BKDATE, CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT WHERE CON_NO = ?";

	private static final String UPDATE_CONTRACT_STMT = "UPDATE CONTRACT SET APL_NO = ?, TNT_NO = ?, HOS_NO = ?, CON_LLD_SIGN = ?, CON_TNT_SIGH = ?, "
			+ "CON_OUT_NORMAL = ?, CON_CHE_DATE = ?,  CON_DEP_BKDATE = ?, HOS_DEP = ?, CON_DEP_STA = ?, CON_BILL_PAID = ?, CON_LASTB_PDATE = ?, "
			+ "CON_CHKDATE = ?, CON_COMCHKDATE = ?, CON_CHK_STA = ?, CON_CHR_FEE = ?, CON_CHR_ITM = ?, CON_IS_CHR = ?, CON_RENT_AGN = ?, "
			+ "CON_STA = ? WHERE CON_NO = ?";

	private static final String DELETE = "DELETE FROM CONTRACT WHERE CON_NO = ?";

	private static final String GET_CON_LLD = "SELECT CON_NO, CON_STA, C.HOS_NO, C.TNT_NO, C.APL_NO FROM CONTRACT C JOIN HOUSE H ON C.HOS_NO = H.HOS_NO JOIN LANDLORD L ON L.LLD_NO = H.LLD_NO WHERE L.LLD_NO = ?";

	private static final String GET_CON_TNT = "SELECT CON_NO, TNT_NO, APL_NO, CON_STA, HOS_NO FROM CONTRACT WHERE TNT_NO = ?";
	
	private static final String GET_CON_BY_HOS_NO = "SELECT CON_NO , APL_NO FROM CONTRACT WHERE HOS_NO = ?";
	
	@Override
	public void beforerentinsert(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BEFORE_RENT_INSERT_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CONTRACT_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());
			pstmt.setString(4, conVO.getCon_lld_sign());
			pstmt.setString(5, conVO.getCon_tnt_sign());
			pstmt.setInt(6, conVO.getCon_out_normal());
			pstmt.setDate(7, conVO.getCon_che_date());
			pstmt.setDate(8, conVO.getCon_dep_bkdate());
			pstmt.setInt(9, conVO.getHos_dep());
			pstmt.setInt(10, conVO.getCon_dep_sta());
			pstmt.setInt(11, conVO.getCon_bill_paid());
			pstmt.setDate(12, conVO.getCon_lastb_pdate());
			pstmt.setDate(13, conVO.getCon_chkdate());
			pstmt.setInt(14, conVO.getCon_comchkdate());
			pstmt.setInt(15, conVO.getCon_chk_sta());
			pstmt.setInt(16, conVO.getCon_chr_fee());
			pstmt.setString(17, conVO.getCon_chr_itm());
			pstmt.setInt(18, conVO.getCon_is_chr());
			pstmt.setInt(19, conVO.getCon_rent_agn());
			pstmt.setInt(20, conVO.getCon_sta());
			pstmt.setString(21, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void beforerentupdate(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BEFORE_RENT_UPDATE_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());
			pstmt.setString(4, conVO.getCon_lld_sign());
			pstmt.setString(5, conVO.getCon_tnt_sign());		
			pstmt.setDate(6, conVO.getCon_che_date());		
			pstmt.setInt(7, conVO.getCon_dep_sta());
			pstmt.setInt(8, conVO.	getHos_dep());
			pstmt.setInt(9, conVO.getCon_sta());
			pstmt.setString(10, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void updatesta(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STA);

			pstmt.setInt(1, conVO.getCon_sta());
			pstmt.setString(2, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void rentupdate(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(RENT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getHos_dep());
			pstmt.setInt(2, conVO.getCon_dep_sta());
			pstmt.setDate(3, conVO.getCon_chkdate());
			pstmt.setInt(4, conVO.getCon_comchkdate());
			pstmt.setString(5, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void beforecheckoutupdate(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BEFORE_CHECKOUT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getHos_dep());
			pstmt.setInt(2, conVO.getCon_dep_sta());
			pstmt.setInt(3, conVO.getCon_comchkdate());
			pstmt.setDate(4, conVO.getCon_chkdate());
			pstmt.setInt(5, conVO.getCon_chk_sta());
			pstmt.setInt(6, conVO.getCon_chr_fee());
			pstmt.setString(7, conVO.getCon_chr_itm());
			pstmt.setInt(8, conVO.getCon_is_chr());
			pstmt.setString(9, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void checkoutupdate(ConVO conVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKOUT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getCon_rent_agn());
			pstmt.setInt(2, conVO.getCon_bill_paid());
			pstmt.setDate(3, conVO.getCon_dep_bkdate());
			pstmt.setDate(4, conVO.getCon_lastb_pdate());
			pstmt.setInt(5, conVO.getCon_out_normal());
			pstmt.setString(6, conVO.getCon_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String con_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, con_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public ConVO findByPrimaryKey(String con_no) {
		ConVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, con_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				conVO.setCon_lld_sign(rs.getString("CON_LLD_SIGN"));
				conVO.setCon_lld_signtime(rs.getTimestamp("CON_LLD_SIGNTIME"));
				conVO.setCon_tnt_sign(rs.getString("CON_TNT_SIGN"));
				conVO.setCon_tnt_signtime(rs.getTimestamp("CON_TNT_SIGNTIME"));
				conVO.setCon_out_normal(rs.getInt("CON_OUT_NORMAL"));
				conVO.setCon_date(rs.getTimestamp("CON_DATE"));
				conVO.setCon_che_date(rs.getDate("CON_CHE_DATE"));
				conVO.setCon_dep_bkdate(rs.getDate("CON_DEP_BKDATE"));
				conVO.setCon_dep_sta(rs.getInt("CON_DEP_STA"));
				conVO.setCon_bill_paid(rs.getInt("CON_BILL_PAID"));
				conVO.setCon_lastb_pdate(rs.getDate("CON_LASTB_PDATE"));
				conVO.setCon_chkdate(rs.getDate("CON_CHKDATE"));
				conVO.setCon_comchkdate(rs.getInt("CON_COMCHKDATE"));
				conVO.setCon_chk_sta(rs.getInt("CON_CHK_STA"));
				conVO.setCon_chr_fee(rs.getInt("CON_CHR_FEE"));
				conVO.setCon_chr_itm(rs.getString("CON_CHR_ITM"));
				conVO.setCon_is_chr(rs.getInt("CON_IS_CHR"));
				conVO.setCon_rent_agn(rs.getInt("CON_RENT_AGN"));
				conVO.setCon_sta(rs.getInt("CON_STA"));

			}
			// Handle any SQL errors
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
		return conVO;
	}
	
	@Override
	public ConVO findByHosno(String hos_no) {

		ConVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CON_BY_HOS_NO);

			pstmt.setString(1, hos_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setApl_no(rs.getString("APL_NO"));

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
		return conVO;
	}

	@Override
	public List<ConVO> getconlld(String lld_no) {

		List<ConVO> list = new ArrayList<ConVO>();

		ConVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CON_LLD);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setCon_sta(rs.getInt("CON_STA"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				list.add(conVO);

			}

			// Handle any SQL errors
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
	public List<ConVO> getcontnt(String tnt_no) {

		List<ConVO> list = new ArrayList<ConVO>();

		ConVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CON_TNT);

			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setCon_sta(rs.getInt("CON_STA"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				list.add(conVO);

			}

			// Handle any SQL errors
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
	public List<ConVO> getAll() {
		List<ConVO> list = new ArrayList<ConVO>();
		ConVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				conVO.setCon_lld_sign(rs.getString("CON_LLD_SIGN"));
				conVO.setCon_lld_signtime(rs.getTimestamp("CON_LLD_SIGNTIME"));
				conVO.setCon_tnt_sign(rs.getString("CON_TNT_SIGN"));
				conVO.setCon_tnt_signtime(rs.getTimestamp("CON_TNT_SIGNTIME"));
				conVO.setCon_out_normal(rs.getInt("CON_OUT_NORMAL"));
				conVO.setCon_date(rs.getTimestamp("CON_DATE"));
				conVO.setCon_che_date(rs.getDate("CON_CHE_DATE"));
				conVO.setCon_dep_bkdate(rs.getDate("CON_DEP_BKDATE"));
				conVO.setCon_dep_sta(rs.getInt("CON_DEP_STA"));
				conVO.setCon_bill_paid(rs.getInt("CON_BILL_PAID"));
				conVO.setCon_lastb_pdate(rs.getDate("CON_LASTB_PDATE"));
				conVO.setCon_chkdate(rs.getDate("CON_CHKDATE"));
				conVO.setCon_comchkdate(rs.getInt("CON_COMCHKDATE"));
				conVO.setCon_chk_sta(rs.getInt("CON_CHK_STA"));
				conVO.setCon_chr_fee(rs.getInt("CON_CHR_FEE"));
				conVO.setCon_chr_itm(rs.getString("CON_CHR_ITM"));
				conVO.setCon_is_chr(rs.getInt("CON_IS_CHR"));
				conVO.setCon_rent_agn(rs.getInt("CON_RENT_AGN"));
				conVO.setCon_sta(rs.getInt("CON_STA"));
				list.add(conVO);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return list;
	}

}
