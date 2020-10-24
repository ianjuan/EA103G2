package com.housemanage.model;

import java.util.*;
import java.sql.*;

public class HouseJDBCDAO implements HouseDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2DB";
	String passwd = "123456";

	private static final String INSERT_HOUSEINFO = "INSERT INTO HOUSE (hos_no,lld_no,hos_name,hos_liffun,hos_trans,hos_add,hos_type,hos_room,hos_pat,hos_floor,hos_pnum,hos_lng,hos_lat,hos_date,hos_status,"
			+ "hos_table,hos_chair,hos_bed,hos_closet,hos_sofa,hos_tv,hos_drink,hos_aircon,hos_refrig,hos_wash,hos_hoter,hos_forth,hos_net,hos_gas,"
			+ "hos_mdate,hos_mindate,hos_park,hos_sex,hos_iden,hos_cook,hos_pet,hos_smoke,"
			+ "hos_rentfee,hos_gasfee,hos_manafee,hos_netfee,hos_puwaterfee,hos_puelefee,hos_parkfee,hos_bro)"
			+ " VALUES ('HOS' || lpad(SEQ_HOS_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_WATERFEE = "INSERT INTO VARFEE_LIST (varf_no,hos_no,var_no,pay_type,pay_amount) VALUES ('VARF' || lpad(SEQ_VARF_NO.NEXTVAL, 6, '0'), 'HOS' || lpad(SEQ_HOS_NO.CURRVAL, 6, '0'), 'VAR000001', ?, ?)";
	private static final String INSERT_ELECTFEE = "INSERT INTO VARFEE_LIST (varf_no,hos_no,var_no,pay_type,pay_amount) VALUES ('VARF' || lpad(SEQ_VARF_NO.NEXTVAL, 6, '0'), 'HOS' || lpad(SEQ_HOS_NO.CURRVAL, 6, '0'), 'VAR000002', ?, ?)";
	private static final String UPDATE_HOUSEINFO = "UPDATE HOUSE set hos_name=?,hos_liffun=?,hos_trans=?,hos_add=?,hos_type=?,hos_room=?,hos_pat=?,hos_floor=?,hos_pnum=?,hos_lng=?,hos_lat=?,hos_date=SYSDATE,hos_status=?, "
			+ "hos_table=?,hos_chair=?,hos_bed=?,hos_closet=?,hos_sofa=?,hos_tv=?,hos_drink=?,hos_aircon=?,hos_refrig=?,hos_wash=?,hos_hoter=?,hos_forth=?,hos_net=?,hos_gas=?, "
			+ "hos_mdate=?,hos_mindate=?,hos_park=?,hos_sex=?,hos_iden=?,hos_cook=?,hos_pet=?,hos_smoke=?, "
			+ "hos_rentfee=?,hos_gasfee=?,hos_manafee=?,hos_netfee=?,hos_puwaterfee=?,hos_puelefee=?,hos_parkfee=? where hos_no=?";
	private static final String UPDATE_HOUSEFurniture = "UPDATE HOUSE set "
			+ "hos_table=?,hos_chair=?,hos_bed=?,hos_closet=?,hos_sofa=?,hos_tv=?,hos_drink=?,hos_aircon=?,hos_refrig=?,hos_wash=?,hos_hoter=?,hos_forth=?,hos_net=?,hos_gas=? "
			+ "where hos_no=?";
	private static final String UPDATE_WATERFEE = "UPDATE VARFEE_LIST set pay_type=?,pay_amount=? where hos_no=? AND var_no='VAR000001'";
	private static final String UPDATE_ELECTFEE = "UPDATE VARFEE_LIST set pay_type=?,pay_amount=? where hos_no=? AND var_no='VAR000002'";
	private static final String UPDATE_HOSPIC = "INSERT INTO HOUSE_PICTURE (pic_no,hos_no,hos_pic) VALUES ('PIC' || lpad(SEQ_PIC_NO.NEXTVAL, 6, '0'), ?, ?)";
	private static final String GET_LLDINFO = "SELECT lld_name,lld_balance FROM LANDLORD where lld_no=?";
	private static final String GET_HOUSEINFO = "SELECT hos_no,hos_name,hos_liffun,hos_trans,hos_add,hos_type,hos_room,hos_pat,hos_floor,hos_pnum,hos_lng,hos_lat,hos_status,"
			+ "hos_table,hos_chair,hos_bed,hos_closet,hos_sofa,hos_tv,hos_drink,hos_aircon,hos_refrig,hos_wash,hos_hoter,hos_forth,hos_net,hos_gas,"
			+ "hos_mdate,hos_mindate,hos_park,hos_sex,hos_iden,hos_cook,hos_pet,hos_smoke,"
			+ "hos_rentfee,hos_gasfee,hos_manafee,hos_netfee,hos_puwaterfee,hos_puelefee,hos_parkfee FROM HOUSE where hos_no=?";
	private static final String GET_WATERFEE = "SELECT pay_type,pay_amount FROM VARFEE_LIST where hos_no=? AND　var_no='VAR000001'";
	private static final String GET_ELECTFEE = "SELECT pay_type,pay_amount FROM VARFEE_LIST where hos_no=? AND　var_no='VAR000002'";
	private static final String GET_LLDHOUSEPIC = "SELECT pic_no FROM HOUSE_PICTURE where hos_no=?";
	private static final String GET_LLDUNRENTHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_bro FROM HOUSE where lld_no=? AND hos_status LIKE '待出租' order by hos_no";
	private static final String GET_LLDRENTHOUSE = "SELECT h.hos_no,hos_add,hos_status,hos_rentfee,apl_str,apl_end,tnt_name "
			+ "FROM HOUSE h JOIN CONTRACT_APPLICATION ca on h.hos_no = ca.hos_no JOIN TENANT t on ca.tnt_no = t.tnt_no where lld_no=? AND hos_status LIKE '出租中' order by hos_no";
	private static final String GET_LLDOFFHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_bro FROM HOUSE where lld_no=? AND hos_status LIKE '已下架' order by hos_no";
	private static final String DELETE_HOUSEPIC = "DELETE FROM HOUSE_PICTURE where pic_no=?";
	private static final String DELETE_HOUSEINFO = "DELETE FROM HOUSE where hos_no=?";
	private static final String GET_ALLHOUSE = "SELECT hos_no,lld_no,hos_name,hos_add,hos_status FROM HOUSE";
	private static final String UPDATE_STATUS = "UPDATE HOUSE SET HOS_STATUS = ? WHERE HOS = ?";

	@Override
	public void insertHouseInfo(HouseVO houseVO, List<HouseVO> hos_picArr) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_HOUSEINFO);

			pstmt.setString(1, houseVO.getLld_no());
			pstmt.setString(2, houseVO.getHos_name());
			pstmt.setString(3, houseVO.getHos_liffun());
			pstmt.setString(4, houseVO.getHos_trans());
			pstmt.setString(5, houseVO.getHos_add());
			pstmt.setString(6, houseVO.getHos_type());
			pstmt.setString(7, houseVO.getHos_room());
			pstmt.setString(8, houseVO.getHos_pat());
			pstmt.setString(9, houseVO.getHos_floor());
			pstmt.setDouble(10, houseVO.getHos_pnum());
			pstmt.setDouble(11, houseVO.getHos_lng());
			pstmt.setDouble(12, houseVO.getHos_lat());
			pstmt.setString(13, houseVO.getHos_status());
			pstmt.setInt(14, houseVO.getHos_table());
			pstmt.setInt(15, houseVO.getHos_chair());
			pstmt.setInt(16, houseVO.getHos_bed());
			pstmt.setInt(17, houseVO.getHos_closet());
			pstmt.setInt(18, houseVO.getHos_sofa());
			pstmt.setInt(19, houseVO.getHos_tv());
			pstmt.setInt(20, houseVO.getHos_drink());
			pstmt.setInt(21, houseVO.getHos_aircon());
			pstmt.setInt(22, houseVO.getHos_refrig());
			pstmt.setInt(23, houseVO.getHos_wash());
			pstmt.setInt(24, houseVO.getHos_hoter());
			pstmt.setInt(25, houseVO.getHos_forth());
			pstmt.setInt(26, houseVO.getHos_net());
			pstmt.setInt(27, houseVO.getHos_gas());
			pstmt.setString(28, houseVO.getHos_mdate());
			pstmt.setString(29, houseVO.getHos_mindate());
			pstmt.setString(30, houseVO.getHos_park());
			pstmt.setString(31, houseVO.getHos_sex());
			pstmt.setString(32, houseVO.getHos_iden());
			pstmt.setString(33, houseVO.getHos_cook());
			pstmt.setString(34, houseVO.getHos_pet());
			pstmt.setString(35, houseVO.getHos_smoke());
			pstmt.setInt(36, houseVO.getHos_rentfee());
			pstmt.setInt(37, houseVO.getHos_gasfee());
			pstmt.setInt(38, houseVO.getHos_manafee());
			pstmt.setInt(39, houseVO.getHos_netfee());
			pstmt.setInt(40, houseVO.getHos_puwaterfee());
			pstmt.setInt(41, houseVO.getHos_puelefee());
			pstmt.setInt(42, houseVO.getHos_parkfee());
			pstmt.setInt(43, houseVO.getHos_bro());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			pstmt = con.prepareStatement(INSERT_WATERFEE);

			pstmt.setInt(1, houseVO.getHos_waterfeetype());
			pstmt.setDouble(2, houseVO.getHos_waterfee());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			pstmt = con.prepareStatement(INSERT_ELECTFEE);

			pstmt.setInt(1, houseVO.getHos_electfeetype());
			pstmt.setDouble(2, houseVO.getHos_electfee());

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
	public void updateHouseInfo(HouseVO houseVO, List<HouseVO> hos_picArr, String[] pic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_HOUSEINFO);

			pstmt.setString(1, houseVO.getHos_name());
			pstmt.setString(2, houseVO.getHos_liffun());
			pstmt.setString(3, houseVO.getHos_trans());
			pstmt.setString(4, houseVO.getHos_add());
			pstmt.setString(5, houseVO.getHos_type());
			pstmt.setString(6, houseVO.getHos_room());
			pstmt.setString(7, houseVO.getHos_pat());
			pstmt.setString(8, houseVO.getHos_floor());
			pstmt.setDouble(9, houseVO.getHos_pnum());
			pstmt.setDouble(10, houseVO.getHos_lng());
			pstmt.setDouble(11, houseVO.getHos_lat());
			pstmt.setString(12, houseVO.getHos_status());
			pstmt.setInt(13, houseVO.getHos_table());
			pstmt.setInt(14, houseVO.getHos_chair());
			pstmt.setInt(15, houseVO.getHos_bed());
			pstmt.setInt(16, houseVO.getHos_closet());
			pstmt.setInt(17, houseVO.getHos_sofa());
			pstmt.setInt(18, houseVO.getHos_tv());
			pstmt.setInt(19, houseVO.getHos_drink());
			pstmt.setInt(20, houseVO.getHos_aircon());
			pstmt.setInt(21, houseVO.getHos_refrig());
			pstmt.setInt(22, houseVO.getHos_wash());
			pstmt.setInt(23, houseVO.getHos_hoter());
			pstmt.setInt(24, houseVO.getHos_forth());
			pstmt.setInt(25, houseVO.getHos_net());
			pstmt.setInt(26, houseVO.getHos_gas());
			pstmt.setString(27, houseVO.getHos_mdate());
			pstmt.setString(28, houseVO.getHos_mindate());
			pstmt.setString(29, houseVO.getHos_park());
			pstmt.setString(30, houseVO.getHos_sex());
			pstmt.setString(31, houseVO.getHos_iden());
			pstmt.setString(32, houseVO.getHos_cook());
			pstmt.setString(33, houseVO.getHos_pet());
			pstmt.setString(34, houseVO.getHos_smoke());
			pstmt.setInt(35, houseVO.getHos_rentfee());
			pstmt.setInt(36, houseVO.getHos_gasfee());
			pstmt.setInt(37, houseVO.getHos_manafee());
			pstmt.setInt(38, houseVO.getHos_netfee());
			pstmt.setInt(39, houseVO.getHos_puwaterfee());
			pstmt.setInt(40, houseVO.getHos_puelefee());
			pstmt.setInt(41, houseVO.getHos_parkfee());
			pstmt.setString(42, houseVO.getHos_no());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			pstmt = con.prepareStatement(UPDATE_WATERFEE);

			pstmt.setInt(1, houseVO.getHos_waterfeetype());
			pstmt.setDouble(2, houseVO.getHos_waterfee());
			pstmt.setString(3, houseVO.getHos_no());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			pstmt = con.prepareStatement(UPDATE_ELECTFEE);

			pstmt.setInt(1, houseVO.getHos_electfeetype());
			pstmt.setDouble(2, houseVO.getHos_electfee());
			pstmt.setString(3, houseVO.getHos_no());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			for (HouseVO hos_pic : hos_picArr) {
				pstmt = con.prepareStatement(UPDATE_HOSPIC);
				pstmt.setString(1, houseVO.getHos_no());
				pstmt.setBytes(2, hos_pic.getHos_pic());
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}

			for (int i = 0; i < pic_no.length; i++) {
				pstmt = con.prepareStatement(DELETE_HOUSEPIC);
				pstmt.setString(1, pic_no[i]);
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}

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
	public void updateHouseFurniture(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_HOUSEFurniture);

			pstmt.setInt(1, houseVO.getHos_table());
			pstmt.setInt(2, houseVO.getHos_chair());
			pstmt.setInt(3, houseVO.getHos_bed());
			pstmt.setInt(4, houseVO.getHos_closet());
			pstmt.setInt(5, houseVO.getHos_sofa());
			pstmt.setInt(6, houseVO.getHos_tv());
			pstmt.setInt(7, houseVO.getHos_drink());
			pstmt.setInt(8, houseVO.getHos_aircon());
			pstmt.setInt(9, houseVO.getHos_refrig());
			pstmt.setInt(10, houseVO.getHos_wash());
			pstmt.setInt(11, houseVO.getHos_hoter());
			pstmt.setInt(12, houseVO.getHos_forth());
			pstmt.setInt(13, houseVO.getHos_net());
			pstmt.setInt(14, houseVO.getHos_gas());
			pstmt.setString(15, houseVO.getHos_no());

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
	public void updateStatus(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, houseVO.getHos_status());
			pstmt.setString(2, houseVO.getHos_no());

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
	public HouseVO getLldInfo(String lld_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LLDINFO);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setLld_name(rs.getString("lld_name"));
				houseVO.setLld_balance(rs.getInt("lld_balance"));
			}

			// Handle any driver errors
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
		return houseVO;
	}

	@Override
	public HouseVO getHouseInfo(String hos_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HOUSEINFO);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_liffun(rs.getString("hos_liffun"));
				houseVO.setHos_trans(rs.getString("hos_trans"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				houseVO.setHos_pat(rs.getString("hos_pat"));
				houseVO.setHos_floor(rs.getString("hos_floor"));
				houseVO.setHos_pnum(rs.getDouble("hos_pnum"));
				houseVO.setHos_lng(rs.getDouble("hos_lng"));
				houseVO.setHos_lat(rs.getDouble("hos_lat"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_table(rs.getInt("hos_table"));
				houseVO.setHos_chair(rs.getInt("hos_chair"));
				houseVO.setHos_bed(rs.getInt("hos_bed"));
				houseVO.setHos_closet(rs.getInt("hos_closet"));
				houseVO.setHos_sofa(rs.getInt("hos_sofa"));
				houseVO.setHos_tv(rs.getInt("hos_tv"));
				houseVO.setHos_drink(rs.getInt("hos_drink"));
				houseVO.setHos_aircon(rs.getInt("hos_aircon"));
				houseVO.setHos_refrig(rs.getInt("hos_refrig"));
				houseVO.setHos_wash(rs.getInt("hos_wash"));
				houseVO.setHos_hoter(rs.getInt("hos_hoter"));
				houseVO.setHos_forth(rs.getInt("hos_forth"));
				houseVO.setHos_net(rs.getInt("hos_net"));
				houseVO.setHos_gas(rs.getInt("hos_gas"));
				houseVO.setHos_mdate(rs.getString("hos_mdate"));
				houseVO.setHos_mindate(rs.getString("hos_mindate"));
				houseVO.setHos_park(rs.getString("hos_park"));
				houseVO.setHos_sex(rs.getString("hos_sex"));
				houseVO.setHos_iden(rs.getString("hos_iden"));
				houseVO.setHos_cook(rs.getString("hos_cook"));
				houseVO.setHos_pet(rs.getString("hos_pet"));
				houseVO.setHos_smoke(rs.getString("hos_smoke"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setHos_gasfee(rs.getInt("hos_gasfee"));
				houseVO.setHos_manafee(rs.getInt("hos_manafee"));
				houseVO.setHos_netfee(rs.getInt("hos_netfee"));
				houseVO.setHos_puwaterfee(rs.getInt("hos_puwaterfee"));
				houseVO.setHos_puelefee(rs.getInt("hos_puelefee"));
				houseVO.setHos_parkfee(rs.getInt("hos_parkfee"));
			}

			// Handle any driver errors
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
		return houseVO;
	}

	@Override
	public HouseVO getHouseWaterfee(String hos_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_WATERFEE);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_waterfeetype(rs.getInt("pay_type"));
				houseVO.setHos_waterfee(rs.getDouble("pay_amount"));
			}

			// Handle any driver errors
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
		return houseVO;
	}

	@Override
	public HouseVO getHouseElectfee(String hos_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ELECTFEE);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_electfeetype(rs.getInt("pay_type"));
				houseVO.setHos_electfee(rs.getDouble("pay_amount"));
			}

			// Handle any driver errors
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
		return houseVO;
	}

	@Override
	public List<HouseVO> getLldHousePic(String hos_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LLDHOUSEPIC);

			pstmt.setString(1, hos_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setPic_no(rs.getString("pic_no"));
				list.add(houseVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public List<HouseVO> getLldAllHouse(String lld_no) {

		return null;
	}

	@Override
	public List<HouseVO> getLldRentHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LLDRENTHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setApl_str(rs.getDate("apl_str"));
				houseVO.setApl_end(rs.getDate("apl_end"));
				houseVO.setTnt_name(rs.getString("tnt_name"));
				list.add(houseVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public List<HouseVO> getLldUnRentHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LLDUNRENTHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_bro(rs.getInt("hos_bro"));
				list.add(houseVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public List<HouseVO> getLldOffHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LLDOFFHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_bro(rs.getInt("hos_bro"));
				list.add(houseVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public void deleteHouseInfo(String hos_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_HOUSEINFO);

			pstmt.setString(1, hos_no);

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
	public List<HouseVO> getAllHouse() {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALLHOUSE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				houseVO = new HouseVO();
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setLld_no(rs.getString("lld_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				list.add(houseVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public HouseVO addMoney(HouseVO houseVO) {

		return null;
	}

	public static void main(String[] args) {

		HouseJDBCDAO dao = new HouseJDBCDAO();

//		// 新增房屋資訊
//		HouseVO houseVO1 = new HouseVO();
//		houseVO1.setLld_no("LLD000001");
//		houseVO1.setHos_name("澳門最大賭場");
//		houseVO1.setHos_liffun("超級棒的啦");
//		houseVO1.setHos_trans("很方便啦");
//		houseVO1.setHos_add("在澳門阿");
//		houseVO1.setHos_type("電梯大樓");
//		houseVO1.setHos_room("獨立套房");
//		houseVO1.setHos_pat("有陽台");
//		houseVO1.setHos_floor("1F/2F");
//		houseVO1.setHos_pnum(100.0);
//		houseVO1.setHos_lng(200.0);
//		houseVO1.setHos_lat(200.0);
//		houseVO1.setHos_bro(0);
//		houseVO1.setHos_status("待出租");
//
//		// 新增房屋家具
//		houseVO1.setHos_table(1);
//		houseVO1.setHos_chair(1);
//		houseVO1.setHos_bed(1);
//		houseVO1.setHos_closet(1);
//		houseVO1.setHos_sofa(0);
//		houseVO1.setHos_tv(0);
//		houseVO1.setHos_drink(1);
//		houseVO1.setHos_aircon(1);
//		houseVO1.setHos_refrig(1);
//		houseVO1.setHos_wash(1);
//		houseVO1.setHos_hoter(1);
//		houseVO1.setHos_forth(0);
//		houseVO1.setHos_net(1);
//		houseVO1.setHos_gas(0);
//
//		// 新增房屋限制
//		houseVO1.setHos_mdate("隨時");
//		houseVO1.setHos_mindate("一年");
//		houseVO1.setHos_park("機械式");
//		houseVO1.setHos_sex("女生");
//		houseVO1.setHos_iden("家庭");
//		houseVO1.setHos_pet("可以");
//		houseVO1.setHos_cook("可以");
//		houseVO1.setHos_smoke("可以");
//
//		// 新增房屋固定費用
//		houseVO1.setHos_rentfee(6000);
//		houseVO1.setHos_gasfee(100);
//		houseVO1.setHos_manafee(800);
//		houseVO1.setHos_netfee(50);
//		houseVO1.setHos_puwaterfee(100);
//		houseVO1.setHos_puelefee(100);
//		houseVO1.setHos_parkfee(3000);
//				
//		// 新增房屋固定費用
//		houseVO1.setHos_waterfee(60.0);
//		houseVO1.setHos_waterfeetype(2);
//		houseVO1.setHos_electfee(4.0);
//		houseVO1.setHos_electfeetype(1);
//
//		dao.insertHouseInfo(houseVO1);

//		// 更新房屋資訊
//		HouseVO houseVO2 = new HouseVO();
//		houseVO2.setHos_name("桃園最大賭場");
//		houseVO2.setHos_liffun("超級棒的啦");
//		houseVO2.setHos_trans("很方便啦");
//		houseVO2.setHos_add("在桃園阿廢話");
//		houseVO2.setHos_type("公寓");
//		houseVO2.setHos_room("雅房");
//		houseVO2.setHos_pat("有陽台");
//		houseVO2.setHos_floor("1F/2F");
//		houseVO2.setHos_pnum(100.0);
//		houseVO2.setHos_lng(150.0);
//		houseVO2.setHos_lat(150.0);
//		houseVO2.setHos_status("已下架");
//		houseVO2.setHos_no("HOS014030");
//		
//		// 更新房屋家具
//		houseVO2.setHos_table(1);
//		houseVO2.setHos_chair(1);
//		houseVO2.setHos_bed(1);
//		houseVO2.setHos_closet(1);
//		houseVO2.setHos_sofa(1);
//		houseVO2.setHos_tv(0);
//		houseVO2.setHos_drink(0);
//		houseVO2.setHos_aircon(1);
//		houseVO2.setHos_refrig(0);
//		houseVO2.setHos_wash(1);
//		houseVO2.setHos_drink(0);
//		houseVO2.setHos_hoter(1);
//		houseVO2.setHos_forth(0);
//		houseVO2.setHos_net(1);
//		houseVO2.setHos_gas(1);
//
//		// 更新房屋限制
//		houseVO2.setHos_mdate("隨時都歡迎");
//		houseVO2.setHos_mindate("一年");
//		houseVO2.setHos_park("機械式");
//		houseVO2.setHos_sex("男生");
//		houseVO2.setHos_iden("學生");
//		houseVO2.setHos_pet("不可以");
//		houseVO2.setHos_cook("可以");
//		houseVO2.setHos_smoke("不可以");
//
//		// 更新房屋固定費用
//		houseVO2.setHos_rentfee(6000);
//		houseVO2.setHos_gasfee(100);
//		houseVO2.setHos_manafee(800);
//		houseVO2.setHos_netfee(50);
//		houseVO2.setHos_puwaterfee(100);
//		houseVO2.setHos_puelefee(100);
//		houseVO2.setHos_parkfee(3000);
//		
//		// 更新房屋浮動費用
//		houseVO2.setHos_waterfeetype(1);
//		houseVO2.setHos_waterfee(3.2);
//		houseVO2.setHos_electfeetype(2);
//		houseVO2.setHos_electfee(100.0);
//
//		dao.updateHouseInfo(houseVO2);

//		// 查詢房屋資訊
//		HouseVO houseVO3 = dao.getHouseInfo("HOS000001");
//		System.out.print(houseVO3.getHos_no() + ",");
//		System.out.print(houseVO3.getHos_name() + ",");
//		System.out.print(houseVO3.getHos_liffun() + ",");
//		System.out.print(houseVO3.getHos_trans() + ",");
//		System.out.print(houseVO3.getHos_add() + ",");
//		System.out.print(houseVO3.getHos_type() + ",");
//		System.out.print(houseVO3.getHos_room() + ",");
//		System.out.print(houseVO3.getHos_pat() + ",");
//		System.out.print(houseVO3.getHos_floor() + ",");
//		System.out.print(houseVO3.getHos_pnum() + ",");
//		System.out.print(houseVO3.getHos_lng() + ",");
//		System.out.print(houseVO3.getHos_lat() + ",");
//		System.out.print(houseVO3.getHos_status() + ",");
//		System.out.println("---------------------");
//
//		// 查詢房屋家具
//		System.out.print(houseVO3.getHos_table() + ",");
//		System.out.print(houseVO3.getHos_chair() + ",");
//		System.out.print(houseVO3.getHos_bed() + ",");
//		System.out.print(houseVO3.getHos_closet() + ",");
//		System.out.print(houseVO3.getHos_sofa() + ",");
//		System.out.print(houseVO3.getHos_tv() + ",");
//		System.out.print(houseVO3.getHos_drink() + ",");
//		System.out.print(houseVO3.getHos_aircon() + ",");
//		System.out.print(houseVO3.getHos_refrig() + ",");
//		System.out.print(houseVO3.getHos_wash() + ",");
//		System.out.print(houseVO3.getHos_hoter() + ",");
//		System.out.print(houseVO3.getHos_forth() + ",");
//		System.out.print(houseVO3.getHos_net() + ",");
//		System.out.print(houseVO3.getHos_gas() + ",");
//		System.out.println("---------------------");
//
//		// 查詢房屋限制
//		System.out.print(houseVO3.getHos_mdate() + ",");
//		System.out.print(houseVO3.getHos_mindate() + ",");
//		System.out.print(houseVO3.getHos_park() + ",");
//		System.out.print(houseVO3.getHos_sex() + ",");
//		System.out.print(houseVO3.getHos_iden() + ",");
//		System.out.print(houseVO3.getHos_pet() + ",");
//		System.out.print(houseVO3.getHos_cook() + ",");
//		System.out.print(houseVO3.getHos_smoke() + ",");
//		System.out.println("---------------------");
//
//		// 查詢房屋固定費用
//		System.out.print(houseVO3.getHos_rentfee() + ",");
//		System.out.print(houseVO3.getHos_gasfee() + ",");
//		System.out.print(houseVO3.getHos_manafee() + ",");
//		System.out.print(houseVO3.getHos_netfee() + ",");
//		System.out.print(houseVO3.getHos_puwaterfee() + ",");
//		System.out.print(houseVO3.getHos_puelefee() + ",");
//		System.out.print(houseVO3.getHos_parkfee() + ",");
//		System.out.println("---------------------");
//		
//		// 查詢房屋浮動費用
//		HouseVO houseVO4 = dao.getHouseWaterfee("HOS000001");
//		System.out.print(houseVO4.getHos_waterfeetype() + ",");
//		System.out.print(houseVO4.getHos_waterfee() + ",");
//
//		System.out.println("---------------------");
//		
//		HouseVO houseVO5 = dao.getHouseElectfee("HOS000001");
//		System.out.print(houseVO5.getHos_electfeetype() + ",");
//		System.out.print(houseVO5.getHos_electfee() + ",");
//
//		System.out.println("---------------------");

		// 查詢房東名字
		HouseVO houseVO6 = dao.getLldInfo("LLD000001");
		System.out.print(houseVO6.getLld_name() + ",");
		System.out.print(houseVO6.getLld_balance() + ",");

		System.out.println("---------------------");

		// 查詢房屋圖片編號
//		List<HouseVO> list1 = dao.getLldHousePic("HOS014046");
//		System.out.print(list1.get(0).getPic_no() + ",");
//		for (HouseVO aHouse : list1) {
//			System.out.print(aHouse.getPic_no() + ",");
//			System.out.println("---------------------");
//			System.out.println();
//		}

//		// 查詢房東已出租房屋
//		List<HouseVO> list2 = dao.getLldRentHouse("LLD000001");
//		for (HouseVO aHouse : list2) {
//			System.out.print(aHouse.getHos_no() + ",");
//			System.out.print(aHouse.getHos_add() + ",");
//			System.out.print(aHouse.getHos_status() + ",");
//			System.out.print(aHouse.getHos_rentfee() + ",");
//			System.out.print(aHouse.getApl_str() + ",");
//			System.out.print(aHouse.getApl_end() + ",");
//			System.out.print(aHouse.getTnt_name() + ",");
//			System.out.println("---------------------");
//			System.out.println();
//		}
//
//		// 查詢房東未出租房屋
//		List<HouseVO> list3 = dao.getLldUnRentHouse("LLD000001");
//		for (HouseVO aHouse : list3) {
//			System.out.print(aHouse.getHos_no() + ",");
//			System.out.print(aHouse.getHos_name() + ",");
//			System.out.print(aHouse.getHos_add() + ",");
//			System.out.print(aHouse.getHos_status() + ",");
//			System.out.print(aHouse.getHos_bro() + ",");
//			System.out.println("---------------------");
//			System.out.println();
//		}

//		// 刪除房屋
//		dao.deleteHouseInfo("HOS03194");
//		
//		// 查詢所有房屋
//		List<HouseVO> list1 = dao.getAllHouse();
//		for (HouseVO aHouse : list1) {
//			System.out.print(aHouse.getHos_no() + ",");
//			System.out.print(aHouse.getLld_no() + ",");
//			System.out.print(aHouse.getHos_name() + ",");
//			System.out.print(aHouse.getHos_add() + ",");
//			System.out.print(aHouse.getHos_status() + ",");
//			System.out.println("---------------------");
//			System.out.println();		
//		}
	}
}
