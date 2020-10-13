package com.rpthc.model;

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

import com.rpthc.model.RpthcDAO_interface;
import com.rpthc.model.RpthcVO;

public class RpthcJNDIDAO implements RpthcDAO_interface{

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_HOUSE_COMMENTS(RPTHC_NO,HCM_NO,LLD_NO,RPTHC_CONTENT) VALUES ('RPTHC'||LPAD(SEQ_RPTHC_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_HOUSE_COMMENTS SET HCM_NO=?,LLD_NO=?,RPTHC_CONTENT=?,EMP_NO=?,RPTHC_STATUS=?,RPTHC_RESULT=?,RPTHC_NOTE=? WHERE RPTHC_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_HOUSE_COMMENTS WHERE RPTHC_NO=?";
	private static final String GET_RPTHC_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS WHERE RPTHC_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS ORDER BY RPTHC_NO";
	private static final String GET_HCM_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS WHERE HCM_NO=? ";
	private static final String GET_LLD_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS WHERE LLD_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS WHERE EMP_NO=? ";
	private static final String GET_STATUS_STMT = "SELECT RPTHC_NO,HCM_NO,LLD_NO,RPTHC_TIME,RPTHC_CONTENT,EMP_NO,RPTHC_DONE_TIME,RPTHC_STATUS,RPTHC_RESULT,RPTHC_NOTE FROM REPORT_HOUSE_COMMENTS WHERE RPTHC_STATUS=? ";

	@Override
	public void insert(RpthcVO rpthcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rpthcVO.getHcm_no());
			pstmt.setString(2, rpthcVO.getLld_no());
			pstmt.setString(3, rpthcVO.getRpthc_content());

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
	public void update(RpthcVO rpthcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rpthcVO.getHcm_no());
			pstmt.setString(2, rpthcVO.getLld_no());
			pstmt.setString(3, rpthcVO.getRpthc_content());
			pstmt.setString(4, rpthcVO.getEmp_no());
			pstmt.setInt(5, rpthcVO.getRpthc_status());
			pstmt.setInt(6, rpthcVO.getRpthc_result());
			pstmt.setString(7, rpthcVO.getRpthc_note());
			pstmt.setString(8, rpthcVO.getRpthc_no());

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
	public void delete(String rpthc_vo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rpthc_vo);

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
	public RpthcVO findByPrimaryKey(String rpthc_no) {

		RpthcVO rpthcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTHC_STMT);

			pstmt.setString(1, rpthc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpthcVO = new RpthcVO();
				rpthcVO.setRpthc_no(rs.getString("rpthc_no"));
				rpthcVO.setHcm_no(rs.getString("hcm_no"));
				rpthcVO.setLld_no(rs.getString("lld_no"));
				rpthcVO.setRpthc_time(rs.getTimestamp("rpthc_time"));
				rpthcVO.setRpthc_content(rs.getString("rpthc_content"));
				rpthcVO.setEmp_no(rs.getString("emp_no"));
				rpthcVO.setRpthc_done_time(rs.getTimestamp("rpthc_done_time"));
				rpthcVO.setRpthc_status(rs.getInt("rpthc_status"));
				rpthcVO.setRpthc_result(rs.getInt("rpthc_result"));
				rpthcVO.setRpthc_note(rs.getString("rpthc_note"));
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

		return rpthcVO;
	}

	@Override
	public List<RpthcVO> getAll() {

		List<RpthcVO> list = new ArrayList<RpthcVO>();
		RpthcVO rpthcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpthcVO = new RpthcVO();
				rpthcVO.setRpthc_no(rs.getString("rpthc_no"));
				rpthcVO.setHcm_no(rs.getString("hcm_no"));
				rpthcVO.setLld_no(rs.getString("lld_no"));
				rpthcVO.setRpthc_time(rs.getTimestamp("rpthc_time"));
				rpthcVO.setRpthc_content(rs.getString("rpthc_content"));
				rpthcVO.setEmp_no(rs.getString("emp_no"));
				rpthcVO.setRpthc_done_time(rs.getTimestamp("rpthc_done_time"));
				rpthcVO.setRpthc_status(rs.getInt("rpthc_status"));
				rpthcVO.setRpthc_result(rs.getInt("rpthc_result"));
				rpthcVO.setRpthc_note(rs.getString("rpthc_note"));
				list.add(rpthcVO);
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
	public List<RpthcVO> findByNo(String Number) {
		List<RpthcVO> list = new ArrayList<RpthcVO>();
		RpthcVO rpthcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (Number.startsWith("H")) {
				pstmt = con.prepareStatement(GET_HCM_STMT);
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
				rpthcVO = new RpthcVO();
				rpthcVO.setRpthc_no(rs.getString("rpthc_no"));
				rpthcVO.setHcm_no(rs.getString("hcm_no"));
				rpthcVO.setLld_no(rs.getString("lld_no"));
				rpthcVO.setRpthc_time(rs.getTimestamp("rpthc_time"));
				rpthcVO.setRpthc_content(rs.getString("rpthc_content"));
				rpthcVO.setEmp_no(rs.getString("emp_no"));
				rpthcVO.setRpthc_done_time(rs.getTimestamp("rpthc_done_time"));
				rpthcVO.setRpthc_status(rs.getInt("rpthc_status"));
				rpthcVO.setRpthc_result(rs.getInt("rpthc_result"));
				rpthcVO.setRpthc_note(rs.getString("rpthc_note"));
				list.add(rpthcVO);
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
