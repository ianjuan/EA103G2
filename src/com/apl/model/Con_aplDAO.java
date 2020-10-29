package com.apl.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.housemanage.model.HouseVO;

public class Con_aplDAO implements Con_aplDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CONTRACT_APPLICATION(APL_NO, TNT_NO, HOS_NO, APL_STR, APL_END, APL_STATUS)"
			+ "VALUES('APL' || lpad(SEQ_APL_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?)";
	private static final String TNT_UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET APL_STR= ?, APL_END= ?, APL_TIME= CURRENT_TIMESTAMP WHERE APL_NO= ?";
	private static final String LLD_UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET APL_STATUS= ? WHERE APL_NO= ?";
	private static final String UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET TNT_NO = ?, HOS_NO = ?, APL_STR = ?, APL_END = ?, APL_TIME = ?, APL_STATUS = ? WHERE APL_NO = ?";
	private static final String GET_ONE_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, to_char(APL_STR,'yyyy-mm-dd') APL_STR, to_char(APL_END, 'yyyy-mm-dd')APL_END, "
			+ "to_char(APL_TIME, 'yyyy-mm-dd')APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION WHERE APL_NO= ?";
	private static final String GET_ALL_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, to_char(APL_STR,'yyyy-mm-dd') APL_STR, to_char(APL_END, 'yyyy-mm-dd')APL_END, "
			+ "to_char(APL_TIME, 'yyyy-mm-dd')APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION ORDER BY APL_NO";
	private static final String DELETE = "DELETE FROM CON_APL WHERE APL_NO=?";
	private static final String LLD_GET_ALL_STMT = "SELECT APL_NO, APL.HOS_NO, APL.TNT_NO, APL_STR, APL_END, APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION APL "
			+ "JOIN HOUSE H ON APL.HOS_NO = H.HOS_NO JOIN TENANT TNT ON TNT.TNT_NO = APL.TNT_NO WHERE LLD_NO = ?";
	private static final String TNT_GET_ALL_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, APL_STR, APL_END, APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION WHERE TNT_NO = ?";
	private static final String GET_APL_BY_HOS = "SELECT APL_NO FROM CONTRACT_APPLICATION WHERE HOS_NO = ?";
	private static final String GET_ALL_BY_HOS = "SELECT APL_NO, TNT_NO, HOS_NO, to_char(APL_STR,'yyyy-mm-dd') APL_STR, to_char(APL_END, 'yyyy-mm-dd')APL_END, "
			+ "to_char(APL_TIME, 'yyyy-mm-dd')APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION WHERE HOS_NO= ? and APL_STATUS = 0";
	
	@Override
	public void insert(Con_aplVO con_aplVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, con_aplVO.getTnt_no());
			pstmt.setString(2, con_aplVO.getHos_no());
			pstmt.setDate(3, con_aplVO.getApl_str());
			pstmt.setDate(4, con_aplVO.getApl_end());
			pstmt.setInt(5, con_aplVO.getApl_status());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
			// forName
		} finally {
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
	
	@Override
	public void update(Con_aplVO con_aplVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1,  con_aplVO.getTnt_no());
			pstmt.setString(2, con_aplVO.getHos_no());		
			pstmt.setDate(3, con_aplVO.getApl_str());
			pstmt.setDate(4, con_aplVO.getApl_end());
			pstmt.setDate(5, con_aplVO.getApl_time());
			pstmt.setInt(6, con_aplVO.getApl_status());
			pstmt.setString(7, con_aplVO.getApl_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	@Override
	public void tntUpdate(Con_aplVO con_aplVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_UPDATE_STMT);

			pstmt.setDate(1, con_aplVO.getApl_str());
			pstmt.setDate(2, con_aplVO.getApl_end());
			pstmt.setString(3, con_aplVO.getApl_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	@Override
	public void lldUpdate(Con_aplVO con_aplVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_UPDATE_STMT);

			pstmt.setInt(1, con_aplVO.getApl_status());
			pstmt.setString(2, con_aplVO.getApl_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	@Override
	public void delete(String apl_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, apl_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	@Override
	public Con_aplVO findByPrimaryKey(String apl_no) {

		Con_aplVO con_aplVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, apl_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
				con_aplVO.setTnt_no(rs.getString("TNT_NO"));
				con_aplVO.setHos_no(rs.getString("HOS_NO"));
				con_aplVO.setApl_str(rs.getDate("APL_STR"));
				con_aplVO.setApl_end(rs.getDate("APL_END"));
				con_aplVO.setApl_time(rs.getDate("APL_TIME"));
				con_aplVO.setApl_status(rs.getInt("APL_STATUS"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
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
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
		}
		return con_aplVO;
	}
	
	@Override
	public Con_aplVO getaplbyhos(String hos_no) {

		Con_aplVO con_aplVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APL_BY_HOS);

			pstmt.setString(1, hos_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
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
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
		}
		return con_aplVO;
	}

	@Override
	public List<Con_aplVO> getAll() {
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
				con_aplVO.setTnt_no(rs.getString("TNT_NO"));
				con_aplVO.setHos_no(rs.getString("HOS_NO"));
				con_aplVO.setApl_str(rs.getDate("APL_STR"));
				con_aplVO.setApl_end(rs.getDate("APL_END"));
				con_aplVO.setApl_time(rs.getDate("APL_TIME"));
				con_aplVO.setApl_status(rs.getInt("APL_STATUS"));
				list.add(con_aplVO);
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
	public List<Con_aplVO> lldgetAll(String lld_no) {
		
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_GET_ALL_STMT);
			System.out.println(lld_no);
			pstmt.setString(1, lld_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
				con_aplVO.setTnt_no(rs.getString("TNT_NO"));
				con_aplVO.setHos_no(rs.getString("HOS_NO"));
				con_aplVO.setApl_str(rs.getDate("APL_STR"));
				con_aplVO.setApl_end(rs.getDate("APL_END"));
				con_aplVO.setApl_time(rs.getDate("APL_TIME"));
				con_aplVO.setApl_status(rs.getInt("APL_STATUS"));
				list.add(con_aplVO);
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
	public List<Con_aplVO> hosgetall(String hos_no) {
		
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_HOS);
			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
				con_aplVO.setTnt_no(rs.getString("TNT_NO"));
				con_aplVO.setHos_no(rs.getString("HOS_NO"));
				con_aplVO.setApl_str(rs.getDate("APL_STR"));
				con_aplVO.setApl_end(rs.getDate("APL_END"));
				con_aplVO.setApl_time(rs.getDate("APL_TIME"));
				con_aplVO.setApl_status(rs.getInt("APL_STATUS"));
				list.add(con_aplVO);
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
	public List<Con_aplVO> tntgetAll(String tnt_no) {
		
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_GET_ALL_STMT);
			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				con_aplVO = new Con_aplVO();
				con_aplVO.setApl_no(rs.getString("APL_NO"));
				con_aplVO.setTnt_no(rs.getString("TNT_NO"));
				con_aplVO.setHos_no(rs.getString("HOS_NO"));
				con_aplVO.setApl_str(rs.getDate("APL_STR"));
				con_aplVO.setApl_end(rs.getDate("APL_END"));
				con_aplVO.setApl_time(rs.getDate("APL_TIME"));
				con_aplVO.setApl_status(rs.getInt("APL_STATUS"));
				list.add(con_aplVO);
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
	public List<HouseVO> getAll(Map<String, String> map) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from house "
		          + CompositeQuery.get_WhereCondition(map)
		          + " and rownum <= 3";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				houseVO.setHos_name(rs.getString("hos_name"));
				houseVO.setHos_add(rs.getString("hos_add"));
//				houseVO.setHos_pic(rs.getBytes("hos_pic"));
				
				list.add(houseVO); 
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
