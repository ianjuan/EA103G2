package com.apl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.housemanage.model.HouseVO;

public class Con_aplJDBCDAO implements Con_aplDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2DB";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CONTRACT_APPLICATION(APL_NO, TNT_NO, HOS_NO, APL_STR, APL_END, APL_TIME, APL_STATUS)"
			+ "VALUES('APL' || lpad(SEQ_APL_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET TNT = ?, HOS_NO = ?, APL_TIME = ?, "
			+ "APL_STR = ?, APL_END = ? WHERE APL_NO = ?";
	private static final String TNT_UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET APL_STR= ?, APL_END= ?, APL_TIME= ? WHERE APL_NO= ?";
	private static final String LLD_UPDATE_STMT = "UPDATE CONTRACT_APPLICATION SET APL_STATUS= ? WHERE APL_NO= ?";
	private static final String GET_ONE_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, to_char(APL_STR,'yyyy-mm-dd') APL_STR, to_char(APL_END, 'yyyy-mm-dd')APL_END, "
			+ "to_char(APL_TIME, 'yyyy-mm-dd')APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION WHERE APL_NO= ?";
	private static final String GET_ALL_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, to_char(APL_STR,'yyyy-mm-dd') APL_STR, to_char(APL_END, 'yyyy-mm-dd')APL_END, "
			+ "to_char(APL_TIME, 'yyyy-mm-dd')APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION ORDER BY APL_NO";
	private static final String DELETE = "DELETE FROM CON_APL WHERE APL_NO=?";
	private static final String LLD_GET_ALL_STMT = "SELECT CONTRACT_APPLICATION.APL_NO, CONTRACT_APPLICATION.TNT_NO, CONTRACT_APPLICATION.HOS_NO, "
			+ "CONTRACT_APPLICATION.APL_STR, CONTRACT_APPLICATION.APL_END, CONTRACT_APPLICATION.APL_TIME, CONTRACT_APPLICATION.APL_STATUS "
			+ "FROM CONTRACT_APPLICATION JOIN HOUSE ON CONTRACT_APPLICATION.HOS_NO = HOUSE.HOS_NO "
			+ "JOIN LANDLORD ON LANDLORD.LLD_NO = HOUSE.LLD_NO WHERE LANDLORD.LLD_NO = ?";
	private static final String TNT_GET_ALL_STMT = "SELECT APL_NO, TNT_NO, HOS_NO, APL_STR, APL_END, APL_TIME, APL_STATUS FROM CONTRACT_APPLICATION WHERE TNT_NO = ?";

	@Override
	public void insert(Con_aplVO con_aplVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, con_aplVO.getTnt_no());
			pstmt.setString(2, con_aplVO.getHos_no());
			pstmt.setDate(3, con_aplVO.getApl_str());
			pstmt.setDate(4, con_aplVO.getApl_end());
			pstmt.setDate(5, con_aplVO.getApl_time());
			pstmt.setInt(6, con_aplVO.getApl_status());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, con_aplVO.getTnt_no());
			pstmt.setString(2,  con_aplVO.getHos_no());
			pstmt.setDate(3, con_aplVO.getApl_str());
			pstmt.setDate(4, con_aplVO.getApl_end());
			pstmt.setDate(5, con_aplVO.getApl_time());
			pstmt.setString(6, con_aplVO.getApl_no());

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_UPDATE_STMT);

			pstmt.setDate(1, con_aplVO.getApl_str());
			pstmt.setDate(2, con_aplVO.getApl_end());
			pstmt.setDate(3, con_aplVO.getApl_time());
			pstmt.setString(4, con_aplVO.getApl_no());

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_UPDATE_STMT);

			pstmt.setInt(1, con_aplVO.getApl_status());
			pstmt.setString(2, con_aplVO.getApl_no());

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, apl_no);

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<Con_aplVO> lldgetAll(String lld_no) {
		
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_GET_ALL_STMT);
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
	public List<Con_aplVO> tntgetAll(String tnt_no) {
		
		List<Con_aplVO> list = new ArrayList<Con_aplVO>();
		Con_aplVO con_aplVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	public static void main(String[] args) {
		
		Con_aplJDBCDAO dao = new Con_aplJDBCDAO();
		
		//INSERT
//		Con_aplVO con_aplVO1 = new Con_aplVO();
//		con_aplVO1.setTnt_no("TNT000001");
//		con_aplVO1.setHos_no("HOS000001");
//		con_aplVO1.setApl_str(java.sql.Date.valueOf("2020-09-19"));
//		con_aplVO1.setApl_end(java.sql.Date.valueOf("2021-10-31"));
//		con_aplVO1.setApl_time(java.sql.Date.valueOf("2022-10-31"));
//		con_aplVO1.setApl_status(1);
//		dao.insert(con_aplVO1);
//		System.out.println("新增成功");
//		
//		//UPDATE
//		Con_aplVO con_aplVO2 = new Con_aplVO();
//		con_aplVO2.setApl_no("APL000001");
//		con_aplVO2.setApl_str(java.sql.Date.valueOf("2020-09-19"));
//		con_aplVO2.setApl_end(java.sql.Date.valueOf("2020-10-31"));
//		con_aplVO2.setApl_time(java.sql.Date.valueOf("2020-11-31"));
//		dao.tntUpdate(con_aplVO2);
//		System.out.println("房客修改成功");
		
		//UPDATE
//		Con_aplVO con_aplVO3 = new Con_aplVO();
//		con_aplVO3.setApl_no("APL000002");
//		con_aplVO3.setApl_status(2);
//		dao.lldUpdate(con_aplVO3);
//		System.out.println("房東修改成功");
//		
		//DELETE
//		dao.delete("APL000003");
//		System.out.println("被限制擋住啦");
		
		//SEARCH
//		Con_aplVO con_aplVO4 = dao.findByPrimaryKey("APL000001");
//		System.out.print(con_aplVO4.getApl_no()+ ",");
//		System.out.print(con_aplVO4.getTnt_no()+ ",");
//		System.out.print(con_aplVO4.getHos_no()+ ",");
//		System.out.print(con_aplVO4.getApl_str()+ ",");
//		System.out.print(con_aplVO4.getApl_end()+ ",");
//		System.out.print(con_aplVO4.getApl_time()+ ",");
//		System.out.print(con_aplVO4.getApl_status());
//		System.out.println("---------------------");
		
////		//SEARCH
//		List<Con_aplVO> list = dao.getAll();
//		for (Con_aplVO aApl : list) {
//			System.out.print(aApl.getApl_no()+ ",");
//			System.out.print(aApl.getTnt_no()+ ",");
//			System.out.print(aApl.getHos_no()+ ",");
//			System.out.print(aApl.getApl_str()+ ",");
//			System.out.print(aApl.getApl_end()+ ",");
//			System.out.print(aApl.getApl_time()+ ",");
//			System.out.println(aApl.getApl_status());
//			System.out.println("---------------------");
//		}
		
		//SEARCH
	List<Con_aplVO> list = dao.tntgetAll("TNT000077");
	for (Con_aplVO aApl : list) {
		System.out.print(aApl.getApl_no()+ ",");
		System.out.print(aApl.getTnt_no()+ ",");
		System.out.print(aApl.getHos_no()+ ",");
		System.out.print(aApl.getApl_str()+ ",");
		System.out.print(aApl.getApl_end()+ ",");
		System.out.print(aApl.getApl_time()+ ",");
		System.out.println(aApl.getApl_status());
		System.out.println("---------------------");
	}
	
	}
}
