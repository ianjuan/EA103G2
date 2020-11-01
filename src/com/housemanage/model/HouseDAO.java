package com.housemanage.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HouseDAO implements HouseDAO_interface {
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_HOUSEINFO = "INSERT INTO HOUSE (hos_no,lld_no,hos_name,hos_liffun,hos_trans,hos_add,hos_type,hos_room,hos_pat,hos_floor,hos_pnum,hos_lng,hos_lat,hos_date,hos_status,"
			+ "hos_table,hos_chair,hos_bed,hos_closet,hos_sofa,hos_tv,hos_drink,hos_aircon,hos_refrig,hos_wash,hos_hoter,hos_forth,hos_net,hos_gas,"
			+ "hos_mdate,hos_mindate,hos_park,hos_sex,hos_iden,hos_cook,hos_pet,hos_smoke,"
			+ "hos_rentfee,hos_gasfee,hos_manafee,hos_netfee,hos_puwaterfee,hos_puelefee,hos_parkfee,hos_bro)"
			+ " VALUES ('HOS' || lpad(SEQ_HOS_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
	private static final String INSERT_WATERFEE = "INSERT INTO VARFEE_LIST (varf_no,hos_no,var_no,pay_type,pay_amount) VALUES ('VARF' || lpad(SEQ_VARF_NO.NEXTVAL, 6, '0'), 'HOS' || lpad(SEQ_HOS_NO.CURRVAL, 6, '0'), 'VAR000001', ?, ?)";
	private static final String INSERT_ELECTFEE = "INSERT INTO VARFEE_LIST (varf_no,hos_no,var_no,pay_type,pay_amount) VALUES ('VARF' || lpad(SEQ_VARF_NO.NEXTVAL, 6, '0'), 'HOS' || lpad(SEQ_HOS_NO.CURRVAL, 6, '0'), 'VAR000002', ?, ?)";
	private static final String INSERT_HOSPIC = "INSERT INTO HOUSE_PICTURE (pic_no,hos_no,hos_pic) VALUES ('PIC' || lpad(SEQ_PIC_NO.NEXTVAL, 6, '0'), 'HOS' || lpad(SEQ_HOS_NO.CURRVAL, 6, '0'), ?)";
	private static final String UPDATE_HOUSEINFO = "UPDATE HOUSE set hos_name=?,hos_liffun=?,hos_trans=?,hos_add=?,hos_type=?,hos_room=?,hos_pat=?,hos_floor=?,hos_pnum=?,hos_lng=?,hos_lat=?,hos_status=?, "
			+ "hos_table=?,hos_chair=?,hos_bed=?,hos_closet=?,hos_sofa=?,hos_tv=?,hos_drink=?,hos_aircon=?,hos_refrig=?,hos_wash=?,hos_hoter=?,hos_forth=?,hos_net=?,hos_gas=?, "
			+ "hos_mdate=?,hos_mindate=?,hos_park=?,hos_sex=?,hos_iden=?,hos_cook=?,hos_pet=?,hos_smoke=?, "
			+ "hos_rentfee=?,hos_gasfee=?,hos_manafee=?,hos_netfee=?,hos_puwaterfee=?,hos_puelefee=?,hos_parkfee=?,hos_date=SYSDATE where hos_no=?";
	private static final String UPDATE_HOUSEFurniture = "UPDATE HOUSE set "
			+ "hos_table=?,hos_chair=?,hos_bed=?,hos_closet=?,hos_sofa=?,hos_tv=?,hos_drink=?,hos_aircon=?,hos_refrig=?,hos_wash=?,hos_hoter=? "
			+ "where hos_no=?";
	private static final String UPDATE_WATERFEE = "UPDATE VARFEE_LIST set pay_type=?,pay_amount=? where hos_no=? AND var_no='VAR000001'";
	private static final String UPDATE_ELECTFEE = "UPDATE VARFEE_LIST set pay_type=?,pay_amount=? where hos_no=? AND var_no='VAR000002'";
	private static final String UPDATE_HOSPIC = "INSERT INTO HOUSE_PICTURE (pic_no,hos_no,hos_pic) VALUES ('PIC' || lpad(SEQ_PIC_NO.NEXTVAL, 6, '0'), ?, ?)";
	private static final String UPDATE_LLDBALANCE = "UPDATE LANDLORD set lld_balance=? where lld_no=?";
	private static final String GET_LLDINFO = "SELECT lld_name,lld_balance FROM LANDLORD where lld_no=?";
	private static final String GET_HOUSEINFO = "SELECT lld_no,hos_no,hos_name,hos_liffun,hos_trans,hos_add,hos_type,hos_room,hos_pat,hos_floor,hos_pnum,hos_lng,hos_lat,hos_status,hos_date,"
			+ "hos_table,hos_chair,hos_bed,hos_closet,hos_sofa,hos_tv,hos_drink,hos_aircon,hos_refrig,hos_wash,hos_hoter,hos_forth,hos_net,hos_gas,"
			+ "hos_mdate,hos_mindate,hos_park,hos_sex,hos_iden,hos_cook,hos_pet,hos_smoke,"
			+ "hos_rentfee,hos_gasfee,hos_manafee,hos_netfee,hos_puwaterfee,hos_puelefee,hos_parkfee FROM HOUSE where hos_no=?";
	private static final String GET_WATERFEE = "SELECT pay_type,pay_amount FROM VARFEE_LIST where hos_no=? AND　var_no='VAR000001'";
	private static final String GET_ELECTFEE = "SELECT pay_type,pay_amount FROM VARFEE_LIST where hos_no=? AND　var_no='VAR000002'";
	private static final String GET_LLDHOUSEPIC = "SELECT pic_no FROM HOUSE_PICTURE where hos_no=?";
	private static final String GET_LLDALLHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_type,hos_room,hos_rentfee,hos_date FROM HOUSE where lld_no=?";
	private static final String GET_LLDUNRENTHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_type,hos_room,hos_rentfee,hos_date FROM HOUSE where lld_no=? AND hos_status LIKE '待出租' order by hos_no DESC";
	private static final String GET_LLDRENTHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_type,hos_room,hos_rentfee,hos_date FROM HOUSE where lld_no=? AND hos_status LIKE '出租中' order by hos_no DESC";
	private static final String GET_LLDOFFHOUSE = "SELECT hos_no,hos_name,hos_add,hos_status,hos_type,hos_room,hos_rentfee,hos_date FROM HOUSE where lld_no=? AND hos_status LIKE '已下架' order by hos_no DESC";
	private static final String DELETE_HOUSEPIC = "DELETE FROM HOUSE_PICTURE where pic_no=?";
	private static final String DELETE_HOUSEINFO = "DELETE FROM HOUSE where hos_no=?";
	private static final String GET_ALLHOUSE = "SELECT hos_no,h.lld_no,lld_name,hos_name,hos_add,hos_status,hos_type,hos_room FROM HOUSE h JOIN LANDLORD l on h.lld_no = l.lld_no";
	private static final String UPDATE_STATUS = "UPDATE HOUSE SET HOS_STATUS = ? WHERE HOS_NO = ?";
	private static final String GET_HOSNO = "SELECT HOS_NO FROM HOUSE WHERE LLD_NO = ?";
	
	@Override
	public void insertHouseInfo(HouseVO houseVO, List<HouseVO> hos_picArr) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			pstmt.clearParameters();
									
			for(HouseVO hos_pic : hos_picArr) {				
				pstmt = con.prepareStatement(INSERT_HOSPIC);
				pstmt.setBytes(1, hos_pic.getHos_pic());
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}
			
			pstmt = con.prepareStatement(UPDATE_LLDBALANCE);

			pstmt.setInt(1, houseVO.getLld_balance());
			pstmt.setString(2, houseVO.getLld_no());

			pstmt.executeUpdate();
						
			// Handle any driver errors
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
			con = ds.getConnection();
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
			
			for(HouseVO hos_pic : hos_picArr) {				
				pstmt = con.prepareStatement(UPDATE_HOSPIC);
				pstmt.setString(1, houseVO.getHos_no());
				pstmt.setBytes(2, hos_pic.getHos_pic());
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}
			
			for(int i=0; i<pic_no.length; i++) {
				if(!"".equals(pic_no[i])) {
					pstmt = con.prepareStatement(DELETE_HOUSEPIC);
					pstmt.setString(1, pic_no[i]);
					pstmt.executeUpdate();
					pstmt.clearParameters();
				}
			}
			
			pstmt = con.prepareStatement(UPDATE_LLDBALANCE);

			pstmt.setInt(1, houseVO.getLld_balance());
			pstmt.setString(2, houseVO.getLld_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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
			pstmt.setString(12, houseVO.getHos_no());

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
	public void updateStatus(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, houseVO.getHos_status());
			pstmt.setString(2, houseVO.getHos_no());

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
	public HouseVO getLldInfo(String lld_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDINFO);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setLld_name(rs.getString("lld_name"));
				houseVO.setLld_balance(rs.getInt("lld_balance"));
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
		return houseVO;
	}

	@Override
	public HouseVO getHouseInfo(String hos_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HOUSEINFO);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setLld_no(rs.getString("lld_no"));
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
				houseVO.setHos_date(rs.getDate("hos_date"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_WATERFEE);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_waterfeetype(rs.getInt("pay_type"));
				houseVO.setHos_waterfee(rs.getDouble("pay_amount"));
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
		return houseVO;
	}

	@Override
	public HouseVO getHouseElectfee(String hos_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ELECTFEE);

			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_electfeetype(rs.getInt("pay_type"));
				houseVO.setHos_electfee(rs.getDouble("pay_amount"));
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
		return houseVO;
	}
	
	@Override
	public HouseVO getHosno(String lld_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HOSNO);

			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("HOS_NO"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDHOUSEPIC);

			pstmt.setString(1, hos_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setPic_no(rs.getString("pic_no"));
				list.add(houseVO); // Store the row in the list
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
	public List<HouseVO> getLldAllHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDALLHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setHos_date(rs.getDate("hos_date"));
				list.add(houseVO); // Store the row in the list
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
	public List<HouseVO> getLldRentHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDRENTHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setHos_date(rs.getDate("hos_date"));
				list.add(houseVO); // Store the row in the list
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
	public List<HouseVO> getLldUnRentHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDUNRENTHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setHos_date(rs.getDate("hos_date"));
				list.add(houseVO); // Store the row in the list
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
	public List<HouseVO> getLldOffHouse(String lld_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LLDOFFHOUSE);

			pstmt.setString(1, lld_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				houseVO.setHos_rentfee(rs.getInt("hos_rentfee"));
				houseVO.setHos_date(rs.getDate("hos_date"));
				list.add(houseVO); // Store the row in the list
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
	public void deleteHouseInfo(String hos_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_HOUSEINFO);

			pstmt.setString(1, hos_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLHOUSE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setLld_no(rs.getString("lld_no"));
				houseVO.setLld_name(rs.getString("lld_name"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
				houseVO.setHos_status(rs.getString("hos_status"));
				houseVO.setHos_type(rs.getString("hos_type"));
				houseVO.setHos_room(rs.getString("hos_room"));
				list.add(houseVO); // Store the row in the list
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
	public HouseVO addMoney(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_LLDBALANCE);

			pstmt.setInt(1, houseVO.getLld_balance());
			pstmt.setString(2, houseVO.getLld_no());
			pstmt.executeQuery();

			// Handle any driver errors
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
		return houseVO;
	}
	
}
