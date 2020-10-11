package com.cont.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apl.model.Con_aplVO;

public class ConJDBCDAO implements ConDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "G2DB";
	String password = "123456";

	private static final String BEFORE_RENT_INSERT_STMT = "INSERT INTO CONTRACT(CON_NO, APL_NO, TNT_NO, HOS_NO) VALUES ('CON' || lpad(SEQ_CON_NO.NEXTVAL, 6, '0'), ?, ?, ?)";

	private static final String BEFORE_RENT_UPDATE_STMT = "UPDATE CONTRACT SET APL_NO = ?, TNT_NO = ?, HOS_NO = ?, CON_LLD_SIGN = ?, CON_TNT_SIGN = ?, CON_DEP_STA = ?, CON_CHE_DATE = ? WHERE CON_NO = ?";

	private static final String RENT_UPDATE_STMT = "UPDATE CONTRACT SET HOS_DEP = ?, CON_DEP_STA = ?, CON_CHKDATE = ?, CON_COMCHKDATE = ? WHERE CON_NO = ?";

	private static final String BEFORE_CHECKOUT_UPDATE_STMT = "UPDATE CONTRACT SET HOS_DEP = ?, CON_DEP_STA = ?, CON_CHK_STA = ?, CON_CHR_FEE = ?, CON_CHR_ITM = ?, CON_IS_CHR = ? WHERE CON_NO = ?";

	private static final String CHECKOUT_UPDATE_STMT = "UPDATE CONTRACT SET CON_RENT_AGN = ?, CON_BILL_PAID = ?, CON_LASTB_PDATE = ?, CON_DEP_BKDATE = ?, CON_OUT_NORMAL = ? WHERE CON_NO = ?";

	private static final String GET_ALL_STMT = "SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_LLD_SIGNTIME, "
			+ "CON_TNT_SIGN, to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_TNT_SIGNTIME, to_char(CON_DATE, 'yyyy-mm-dd hh:mm:ss')CON_DATE, to_char(CON_CHE_DATE, 'yyyy-mm-dd')CON_CHE_DATE, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd')CON_CHKDATE, "
			+ "to_char(CON_COMCHKDATE, 'yyyy-mm-dd')CON_COMCHKDATE, CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd')CON_LASTB_PDATE, to_char(CON_DEP_BKDATE, 'yyyy-mm-dd')CON_DEP_BKDATE, CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT ORDER BY CON_NO";

	private static final String GET_ONE_STMT = "SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_LLD_SIGNTIME, "
			+ "CON_TNT_SIGN, to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd hh:mm:ss')CON_TNT_SIGNTIME, to_char(CON_DATE, 'yyyy-mm-dd hh:mm:ss')CON_DATE, to_char(CON_CHE_DATE, 'yyyy-mm-dd')CON_CHE_DATE, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd')CON_CHKDATE, "
			+ "to_char(CON_COMCHKDATE, 'yyyy-mm-dd')CON_COMCHKDATE, CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd')CON_LASTB_PDATE, to_char(CON_DEP_BKDATE, 'yyyy-mm-dd')CON_DEP_BKDATE, CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT WHERE CON_NO = ?";

	private static final String UPDATE_CONTRACT_STMT = "UPDATE CONTRACT SET APL_NO = ?, TNT_NO = ?, HOS_NO = ?, CON_LLD_SIGN = ?, CON_TNT_SIGN = ?, "
			+ "CON_OUT_NORMAL = ?, CON_CHE_DATE = ?,  CON_DEP_BKDATE = ?, HOS_DEP = ?, CON_DEP_STA = ?, CON_BILL_PAID = ?, CON_LASTB_PDATE = ?, "
			+ "CON_CHKDATE = ?, CON_COMCHKDATE = ?, CON_CHK_STA = ?, CON_CHR_FEE = ?, CON_CHR_ITM = ?, CON_IS_CHR = ?, CON_RENT_AGN = ?, "
			+ "CON_STA = ? WHERE CON_NO = ?";

	private static final String DELETE = "DELETE FROM CONTRACT WHERE CON_NO = ?";

	@Override
	public void beforerentinsert(ConVO conVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(BEFORE_RENT_INSERT_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE_CONTRACT_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());
			pstmt.setBytes(4, conVO.getCon_lld_sign());
			pstmt.setBytes(5, conVO.getCon_tnt_sign());
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(BEFORE_RENT_UPDATE_STMT);

			pstmt.setString(1, conVO.getApl_no());
			pstmt.setString(2, conVO.getTnt_no());
			pstmt.setString(3, conVO.getHos_no());
			pstmt.setBytes(4, conVO.getCon_lld_sign());
			pstmt.setBytes(5, conVO.getCon_tnt_sign());
			pstmt.setInt(6, conVO.getCon_dep_sta());
			pstmt.setDate(7, conVO.getCon_che_date());
			pstmt.setString(8, conVO.getCon_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(RENT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getHos_dep());
			pstmt.setInt(2, conVO.getCon_dep_sta());
			pstmt.setDate(3, conVO.getCon_chkdate());
			pstmt.setInt(4, conVO.getCon_comchkdate());
			pstmt.setString(5, conVO.getCon_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(BEFORE_CHECKOUT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getHos_dep());
			pstmt.setInt(2, conVO.getCon_dep_sta());
			pstmt.setInt(3, conVO.getCon_chk_sta());
			pstmt.setInt(4, conVO.getCon_chr_fee());
			pstmt.setString(5, conVO.getCon_chr_itm());
			pstmt.setInt(6, conVO.getCon_is_chr());
			pstmt.setString(7, conVO.getCon_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(CHECKOUT_UPDATE_STMT);

			pstmt.setInt(1, conVO.getCon_rent_agn());
			pstmt.setInt(2, conVO.getCon_bill_paid());
			pstmt.setDate(3, conVO.getCon_dep_bkdate());
			pstmt.setDate(4, conVO.getCon_lastb_pdate());
			pstmt.setInt(5, conVO.getCon_out_normal());
			pstmt.setString(6, conVO.getCon_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, con_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, con_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				conVO.setCon_lld_sign(rs.getBytes("CON_LLD_SIGN"));
				conVO.setCon_lld_signtime(rs.getTimestamp("CON_LLD_SIGNTIME"));
				conVO.setCon_tnt_sign(rs.getBytes("CON_TNT_SIGN"));
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<ConVO> getAll() {

		List<ConVO> list = new ArrayList<ConVO>();
		ConVO conVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				conVO = new ConVO();

				conVO.setCon_no(rs.getString("CON_NO"));
				conVO.setApl_no(rs.getString("APL_NO"));
				conVO.setTnt_no(rs.getString("TNT_NO"));
				conVO.setHos_no(rs.getString("HOS_NO"));
				conVO.setCon_lld_sign(rs.getBytes("CON_LLD_SIGN"));
				conVO.setCon_lld_signtime(rs.getTimestamp("CON_LLD_SIGNTIME"));
				conVO.setCon_tnt_sign(rs.getBytes("CON_TNT_SIGN"));
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
			// TODO: handle exception
		}
		return list;
	}

	public static void main(String[] args) {

		ConJDBCDAO dao = new ConJDBCDAO();

		// Insert
//		ConVO conVO1 = new ConVO();

//		conVO1.setApl_no("APL000001");
//		conVO1.setTnt_no("TNT000001");
//		conVO1.setHos_no("HOS000001");
//		conVO1.setCon_out_normal(0);
//		conVO1.setCon_che_date(java.sql.Date.valueOf("2020-10-01"));
//		conVO1.setCon_dep_bkdate(java.sql.Date.valueOf("2020-10-02"));
//		conVO1.setCon_dep_sta(1);
//		conVO1.setCon_bill_paid(0);
//		conVO1.setCon_lastb_pdate(java.sql.Date.valueOf("2020-10-03"));
//		conVO1.setCon_chkdate(java.sql.Date.valueOf("2020-10-04"));
//		conVO1.setCon_comchkdate(1);
//		conVO1.setCon_chk_sta(0);
//		conVO1.setCon_chr_fee(1);
//		conVO1.setCon_chr_itm("有損壞");
//		conVO1.setCon_is_chr(1);
//		conVO1.setCon_rent_agn(0);
//		conVO1.setCon_sta(3);
//		dao.insert(conVO1);
//		System.out.println("Insert successfully");

//		conVO1.setApl_no("APL000001");
//		conVO1.setTnt_no("TNT000001");
//		conVO1.setHos_no("HOS000001");
//		conVO1.setCon_dep_sta(1);
//		conVO1.setCon_che_date(java.sql.Date.valueOf("2020-10-02"));
//		dao.beforerentinsert(conVO1);
//		System.out.println("Insert successfully");

		// Update
		ConVO conVO2 = new ConVO();

		conVO2.setCon_no("CON000001");
		conVO2.setApl_no("APL000011");
		conVO2.setTnt_no("TNT000011");
		conVO2.setHos_no("HOS000011");
		conVO2.setCon_dep_sta(1);
		conVO2.setCon_che_date(java.sql.Date.valueOf("2020-10-02"));
		
		conVO2.setHos_dep(32970);
		conVO2.setCon_dep_sta(1);
		conVO2.setCon_chkdate(java.sql.Date.valueOf("2020-10-02"));
		conVO2.setCon_comchkdate(1);

		conVO2.setHos_dep(32970);
		conVO2.setCon_dep_sta(2);
		conVO2.setCon_chk_sta(2);
		conVO2.setCon_chr_fee(32789);
		conVO2.setCon_chr_itm("hiwoehiof");
		conVO2.setCon_is_chr(8);

		conVO2.setCon_rent_agn(1);
		conVO2.setCon_bill_paid(2);
		conVO2.setCon_dep_bkdate(java.sql.Date.valueOf("2020-10-02"));
		conVO2.setCon_lastb_pdate(java.sql.Date.valueOf("2020-10-02"));
		conVO2.setCon_out_normal(2);
		conVO2.setCon_sta(1);

		dao.update(conVO2);
		System.out.println("Update successfully");

//		//Delete
//		dao.delete("CON000004");
//		System.out.println("Delete successfully");

		// Search
//		ConVO conVO3 = dao.findByPrimaryKey("CON000002");
//		System.out.println(conVO3.getHos_no());
//		System.out.println(conVO3.getApl_no());
//		System.out.println(conVO3.getCon_chr_itm());
//		System.out.println(conVO3.getCon_no());
//		System.out.println(conVO3.getTnt_no());
//		System.out.println(conVO3.getCon_bill_paid());
//		System.out.println(conVO3.getCon_che_date());
//		System.out.println(conVO3.getCon_chk_sta());
//		System.out.println(conVO3.getCon_chkdate());
//		System.out.println(conVO3.getCon_chr_fee());
//		System.out.println(conVO3.getCon_comchkdate());
//		System.out.println(conVO3.getCon_date());
//		System.out.println(conVO3.getCon_dep_bkdate());
//		System.out.println(conVO3.getCon_dep_sta());
//		System.out.println(conVO3.getCon_is_chr());
//		System.out.println(conVO3.getHos_dep());
//		System.out.println(conVO3.getCon_tnt_signtime());
//		System.out.println(conVO3.getCon_tnt_sign());
//		System.out.println(conVO3.getCon_sta());
//		System.out.println(conVO3.getCon_rent_agn());
//		System.out.println(conVO3.getCon_out_normal());
//		System.out.println(conVO3.getCon_lld_signtime());

//		List<ConVO> list = dao.getAll();
//		for (ConVO aCon : list) {
//			System.out.print(aCon.getHos_no());
//			System.out.print(aCon.getApl_no());
//			System.out.print(aCon.getCon_chr_itm());
//			System.out.print(aCon.getCon_no());
//			System.out.print(aCon.getTnt_no());
//			System.out.print(aCon.getCon_bill_paid());
//			System.out.print(aCon.getCon_che_date());
//			System.out.print(aCon.getCon_chk_sta());
//			System.out.print(aCon.getCon_chkdate());
//			System.out.print(aCon.getCon_chr_fee());
//			System.out.print(aCon.getCon_comchkdate());
//			System.out.print(aCon.getCon_date());
//			System.out.print(aCon.getCon_dep_bkdate());
//			System.out.print(aCon.getCon_dep_sta());
//			System.out.print(aCon.getCon_is_chr());
//			System.out.print(aCon.getHos_dep());
//			System.out.print(aCon.getCon_tnt_signtime());
//			System.out.print(aCon.getCon_tnt_sign());
//			System.out.print(aCon.getCon_sta());
//			System.out.print(aCon.getCon_rent_agn());
//			System.out.print(aCon.getCon_out_normal());
//			System.out.println(aCon.getCon_lld_signtime());
//			System.out.println("---------------------");
//		}
	}
}
