package com.rptt.model;

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

import com.rptt.model.RpttDAO_interface;
import com.rptt.model.RpttVO;

public class RpttJNDIDAO implements RpttDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_TENANT(RPTT_NO,TNT_NO,LLD_NO,RPTT_CONTENT) VALUES ('RPTT'||LPAD(SEQ_RPTT_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_TENANT SET TNT_NO=?,LLD_NO=?,RPTT_CONTENT=?,EMP_NO=?,RPTT_STATUS=?,RPTT_RESULT=?,RPTT_NOTE=? WHERE RPTT_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_TENANT WHERE RPTT_NO=?";
	private static final String GET_RPTT_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT WHERE RPTT_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT ORDER BY RPTT_NO";
	private static final String GET_TNT_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT WHERE TNT_NO=? ";
	private static final String GET_LLD_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT WHERE LLD_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT WHERE EMP_NO=? ";
	private static final String GET_RESULT_STMT = "SELECT RPTT_NO,TNT_NO,LLD_NO,RPTT_TIME,RPTT_CONTENT,EMP_NO,RPTT_DONE_TIME,RPTT_STATUS,RPTT_RESULT,RPTT_NOTE FROM REPORT_TENANT WHERE RPTT_RESULT=? ";
	private static final String UPDATEEMP = "UPDATE REPORT_TENANT SET EMP_NO=?,RPTT_STATUS=? WHERE RPTT_NO=? ";


	@Override
	public void insert(RpttVO rpttVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rpttVO.getTnt_no());
			pstmt.setString(2, rpttVO.getLld_no());
			pstmt.setString(3, rpttVO.getRptt_content());

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
	public void update(RpttVO rpttVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rpttVO.getTnt_no());
			pstmt.setString(2, rpttVO.getLld_no());
			pstmt.setString(3, rpttVO.getRptt_content());
			pstmt.setString(4, rpttVO.getEmp_no());
			pstmt.setInt(5, rpttVO.getRptt_status());
			pstmt.setInt(6, rpttVO.getRptt_result());
			pstmt.setString(7, rpttVO.getRptt_note());
			pstmt.setString(8, rpttVO.getRptt_no());

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
	
	
	public void updateEmp(RpttVO rpttVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEEMP);

		
			pstmt.setString(1, rpttVO.getEmp_no());
			pstmt.setInt(2, rpttVO.getRptt_status());
			pstmt.setString(3, rpttVO.getRptt_no());

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
	public void delete(String rptt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rptt_no);

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
	public RpttVO findByPrimaryKey(String rptt_no) {

		RpttVO rpttVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTT_STMT);

			pstmt.setString(1, rptt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttVO = new RpttVO();
				rpttVO.setRptt_no(rs.getString("rptt_no"));
				rpttVO.setTnt_no(rs.getString("tnt_no"));
				rpttVO.setLld_no(rs.getString("lld_no"));
				rpttVO.setRptt_time(rs.getTimestamp("rptt_time"));
				rpttVO.setRptt_content(rs.getString("rptt_content"));
				rpttVO.setEmp_no(rs.getString("emp_no"));
				rpttVO.setRptt_done_time(rs.getTimestamp("rptt_done_time"));
				rpttVO.setRptt_status(rs.getInt("rptt_status"));
				rpttVO.setRptt_result(rs.getInt("rptt_result"));
				rpttVO.setRptt_note(rs.getString("rptt_note"));
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

		return rpttVO;
	}

	@Override
	public List<RpttVO> getAll() {

		List<RpttVO> list = new ArrayList<RpttVO>();
		RpttVO rpttVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttVO = new RpttVO();
				rpttVO.setRptt_no(rs.getString("rptt_no"));
				rpttVO.setTnt_no(rs.getString("tnt_no"));
				rpttVO.setLld_no(rs.getString("lld_no"));
				rpttVO.setRptt_time(rs.getTimestamp("rptt_time"));
				rpttVO.setRptt_content(rs.getString("rptt_content"));
				rpttVO.setEmp_no(rs.getString("emp_no"));
				rpttVO.setRptt_done_time(rs.getTimestamp("rptt_done_time"));
				rpttVO.setRptt_status(rs.getInt("rptt_status"));
				rpttVO.setRptt_result(rs.getInt("rptt_result"));
				rpttVO.setRptt_note(rs.getString("rptt_note"));
				list.add(rpttVO);
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
	public List<RpttVO> findByNo(String Number) {
		List<RpttVO> list = new ArrayList<RpttVO>();
		RpttVO rpttVO = null;

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
				pstmt = con.prepareStatement(GET_RESULT_STMT);
			} else {
				System.out.println("wrong sql");
			}
			pstmt.setString(1, Number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttVO = new RpttVO();
				rpttVO.setRptt_no(rs.getString("rptt_no"));
				rpttVO.setTnt_no(rs.getString("tnt_no"));
				rpttVO.setLld_no(rs.getString("lld_no"));
				rpttVO.setRptt_time(rs.getTimestamp("rptt_time"));
				rpttVO.setRptt_content(rs.getString("rptt_content"));
				rpttVO.setEmp_no(rs.getString("emp_no"));
				rpttVO.setRptt_done_time(rs.getTimestamp("rptt_done_time"));
				rpttVO.setRptt_status(rs.getInt("rptt_status"));
				rpttVO.setRptt_result(rs.getInt("rptt_result"));
				rpttVO.setRptt_note(rs.getString("rptt_note"));
				list.add(rpttVO);
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
