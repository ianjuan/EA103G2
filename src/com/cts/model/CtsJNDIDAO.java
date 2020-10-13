package com.cts.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cts.model.CtsVO;

public class CtsJNDIDAO implements CtsDAO_interface{
	
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	private static final String INSERT_STMT = "INSERT INTO CUSTOMER_SERVICE(CTS_NO,CTS_TYPE,CTS_QUESTION,CTS_ANSWER,EMP_NO) VALUES ('CTS'||LPAD(SEQ_CTS_NO.NEXTVAL,6,'0'),?,?,?,?)";
	private static final String UPDATE = "UPDATE CUSTOMER_SERVICE SET CTS_TYPE=?,CTS_QUESTION=?,CTS_ANSWER=?,EMP_NO=?,CTS_SHOW=? WHERE CTS_NO=?";
	private static final String DELETE = "DELETE FROM CUSTOMER_SERVICE WHERE CTS_NO=?";
	private static final String GET_CTS_STMT = "SELECT CTS_NO,CTS_TYPE,CTS_QUESTION,CTS_ANSWER,CTS_TIME,EMP_NO,CTS_SHOW FROM CUSTOMER_SERVICE WHERE CTS_NO=? ";
	private static final String GET_ALL_STMT = "SELECT CTS_NO,CTS_TYPE,CTS_QUESTION,cts_TIME,EMP_NO,cts_DONE_TIME,cts_STATUS,cts_RESULT,CTS_SHOW FROM CUSTOMER_SERVICE ORDER BY CTS_NO";
	private static final String GET_TYPE_STMT = "SELECT CTS_NO,CTS_TYPE,CTS_QUESTION,CTS_ANSWER,CTS_TIME,EMP_NO,CTS_SHOW FROM CUSTOMER_SERVICE WHERE CTS_TYPE=? ";

	
	
	@Override
	public void insert(CtsVO ctsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ctsVO.getCts_no());
			pstmt.setInt(2, ctsVO.getCts_type());
			pstmt.setString(3, ctsVO.getCts_question());
			pstmt.setString(4, ctsVO.getCts_answer());
			pstmt.setString(5, ctsVO.getEmp_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
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
	public void update(CtsVO ctsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ctsVO.getCts_type());
			pstmt.setString(2, ctsVO.getCts_question());
			pstmt.setString(3, ctsVO.getCts_answer());
			pstmt.setString(4, ctsVO.getEmp_no());
			pstmt.setInt(5, ctsVO.getCts_show());
			pstmt.setString(6, ctsVO.getCts_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}
		
	}

	@Override
	public void delete(String cts_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cts_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

		
	}

	@Override
	public CtsVO findByPrimaryKey(String cts_no) {
		CtsVO ctsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CTS_STMT);

			pstmt.setString(1, cts_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctsVO = new CtsVO();
				ctsVO.setCts_no(rs.getString("cts_no"));
				ctsVO.setCts_type(rs.getInt("cts_type"));
				ctsVO.setCts_question(rs.getString("cts_question"));
				ctsVO.setCts_answer(rs.getString("cts_answer"));
				ctsVO.setCts_time(rs.getTimestamp("cts_time"));
				ctsVO.setEmp_no(rs.getString("emp_no"));
				ctsVO.setCts_show(rs.getInt("cts_show"));
				
			}

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());

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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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

		return ctsVO;
	}


	@Override
	public List<CtsVO> getAll() {
		List<CtsVO> list = new ArrayList<CtsVO>();
		CtsVO ctsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctsVO = new CtsVO();
				ctsVO.setCts_no(rs.getString("cts_no"));
				ctsVO.setCts_type(rs.getInt("cts_type"));
				ctsVO.setCts_question(rs.getString("cts_question"));
				ctsVO.setCts_answer(rs.getString("cts_answer"));
				ctsVO.setCts_time(rs.getTimestamp("cts_time"));
				ctsVO.setEmp_no(rs.getString("emp_no"));
				ctsVO.setCts_show(rs.getInt("cts_show"));
				
				list.add(ctsVO);
			}

		} catch (SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());

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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public List<CtsVO> findByType(Integer cts_type) {
		List<CtsVO> list = new ArrayList<CtsVO>();
		CtsVO ctsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

		
			pstmt = con.prepareStatement(GET_TYPE_STMT);
			
			pstmt.setInt(1, cts_type);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ctsVO = new CtsVO();
				ctsVO.setCts_no(rs.getString("cts_no"));
				ctsVO.setCts_type(rs.getInt("cts_type"));
				ctsVO.setCts_question(rs.getString("cts_question"));
				ctsVO.setCts_answer(rs.getString("cts_answer"));
				ctsVO.setCts_time(rs.getTimestamp("cts_time"));
				ctsVO.setEmp_no(rs.getString("emp_no"));
				ctsVO.setCts_show(rs.getInt("cts_show"));
				list.add(ctsVO);
			}

		} catch (

		SQLException se) {

			throw new RuntimeException("A DataBase error occured." + se.getMessage());

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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
