package com.rptl.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.rptl.model.RptlDAO_interface;
import com.rptl.model.RptlVO;

public class RptlJNDIDAO implements RptlDAO_interface {
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_LANDLORD(RPTL_NO,LLD_NO,TNT_NO,RPTL_CONTENT) VALUES ('RPTL'||LPAD(SEQ_RPTL_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_LANDLORD SET LLD_NO=?,TNT_NO=?,RPTL_CONTENT=?,EMP_NO=?,RPTL_STATUS=?,RPTL_RESULT=?,RPTL_NOTE=? WHERE RPTL_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_LANDLORD WHERE RPTL_NO=?";
	private static final String GET_RPTL_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD WHERE RPTL_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD ORDER BY RPTL_NO";
	private static final String GET_TNT_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD WHERE TNT_NO=? ";
	private static final String GET_LLD_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD WHERE LLD_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD WHERE EMP_NO=? ";
	private static final String GET_STATUS_STMT = "SELECT RPTL_NO,LLD_NO,TNT_NO,RPTL_TIME,RPTL_CONTENT,EMP_NO,RPTL_DONE_TIME,RPTL_STATUS,RPTL_RESULT,RPTL_NOTE FROM REPORT_LANDLORD WHERE RPTL_STATUS=? ";

	@Override
	public void insert(RptlVO rptlVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rptlVO.getLld_no());
			pstmt.setString(2, rptlVO.getTnt_no());
			pstmt.setString(3, rptlVO.getRptl_content());

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
	public void update(RptlVO rptlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rptlVO.getLld_no());
			pstmt.setString(2, rptlVO.getTnt_no());
			pstmt.setString(3, rptlVO.getRptl_content());
			pstmt.setString(4, rptlVO.getEmp_no());
			pstmt.setInt(5, rptlVO.getRptl_status());
			pstmt.setInt(6, rptlVO.getRptl_result());
			pstmt.setString(7, rptlVO.getRptl_note());
			pstmt.setString(8, rptlVO.getRptl_no());

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
	public void delete(String rptl_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rptl_no);

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
	public RptlVO findByPrimaryKey(String rptl_no) {

		RptlVO rptlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTL_STMT);

			pstmt.setString(1, rptl_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptlVO = new RptlVO();
				rptlVO.setRptl_no(rs.getString("rptl_no"));
				rptlVO.setLld_no(rs.getString("lld_no"));
				rptlVO.setTnt_no(rs.getString("tnt_no"));
				rptlVO.setRptl_time(rs.getTimestamp("rptl_time"));
				rptlVO.setRptl_content(rs.getString("rptl_content"));
				rptlVO.setEmp_no(rs.getString("emp_no"));
				rptlVO.setRptl_done_time(rs.getTimestamp("rptl_done_time"));
				rptlVO.setRptl_status(rs.getInt("rptl_status"));
				rptlVO.setRptl_result(rs.getInt("rptl_result"));
				rptlVO.setRptl_note(rs.getString("rptl_note"));
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

		return rptlVO;
	}

	@Override
	public List<RptlVO> getAll() {

		List<RptlVO> list = new ArrayList<RptlVO>();
		RptlVO rptlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptlVO = new RptlVO();
				rptlVO.setRptl_no(rs.getString("rptl_no"));
				rptlVO.setLld_no(rs.getString("lld_no"));
				rptlVO.setTnt_no(rs.getString("tnt_no"));
				rptlVO.setRptl_time(rs.getTimestamp("rptl_time"));
				rptlVO.setRptl_content(rs.getString("rptl_content"));
				rptlVO.setEmp_no(rs.getString("emp_no"));
				rptlVO.setRptl_done_time(rs.getTimestamp("rptl_done_time"));
				rptlVO.setRptl_status(rs.getInt("rptl_status"));
				rptlVO.setRptl_result(rs.getInt("rptl_result"));
				rptlVO.setRptl_note(rs.getString("rptl_note"));
				list.add(rptlVO);
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
	public List<RptlVO> findByNo(String Number) {
		List<RptlVO> list = new ArrayList<RptlVO>();
		RptlVO rptlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (Number.startsWith("T")) {
				pstmt = con.prepareStatement(GET_TNT_STMT);
			} else if (Number.startsWith("L")) {
				pstmt = con.prepareStatement(GET_LLD_STMT);
			} else if (Number.startsWith("E")) {
				pstmt = con.prepareStatement(GET_EMP_STMT);
			} else if (Number.equals("0")||Number.equals("1")) {
				pstmt = con.prepareStatement(GET_STATUS_STMT);
			} else {
				System.out.println("wrong sql");
			}
			pstmt.setString(1, Number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptlVO = new RptlVO();
				rptlVO.setRptl_no(rs.getString("rptl_no"));
				rptlVO.setLld_no(rs.getString("lld_no"));
				rptlVO.setTnt_no(rs.getString("tnt_no"));
				rptlVO.setRptl_time(rs.getTimestamp("rptl_time"));
				rptlVO.setRptl_content(rs.getString("rptl_content"));
				rptlVO.setEmp_no(rs.getString("emp_no"));
				rptlVO.setRptl_done_time(rs.getTimestamp("rptl_done_time"));
				rptlVO.setRptl_status(rs.getInt("rptl_status"));
				rptlVO.setRptl_result(rs.getInt("rptl_result"));
				rptlVO.setRptl_note(rs.getString("rptl_note"));
				list.add(rptlVO);
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
