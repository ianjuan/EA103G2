package com.rpttc.model;

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

import com.rpttc.model.RpttcDAO_interface;
import com.rpttc.model.RpttcVO;

public class RpttcJNDIDAO implements RpttcDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_TENANT_COMMENTS(RPTTC_NO,TCM_NO,TNT_NO,RPTTC_CONTENT) VALUES ('RPTTC'||LPAD(SEQ_RPTTC_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_TENANT_COMMENTS SET TCM_NO=?,TNT_NO=?,RPTTC_CONTENT=?,EMP_NO=?,RPTTC_STATUS=?,RPTTC_RESULT=?,RPTTC_NOTE=? WHERE RPTTC_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_TENANT_COMMENTS WHERE RPTTC_NO=?";
	private static final String GET_RPTTC_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS WHERE RPTTC_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS ORDER BY RPTTC_NO";
	private static final String GET_TCM_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS WHERE TCM_NO=? ";
	private static final String GET_TNT_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS WHERE TNT_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS WHERE EMP_NO=? ";
	private static final String GET_RESULT_STMT = "SELECT RPTTC_NO,TCM_NO,TNT_NO,RPTTC_TIME,RPTTC_CONTENT,EMP_NO,RPTTC_DONE_TIME,RPTTC_STATUS,RPTTC_RESULT,RPTTC_NOTE FROM REPORT_TENANT_COMMENTS WHERE RPTTC_RESULT=? ";
	private static final String UPDATE_EMP = "UPDATE REPORT_TENANT_COMMENTS SET EMP_NO=?,RPTTC_STATUS=? WHERE RPTTC_NO=? ";
	private static final String ASSIGN_EMP = "UPDATE REPORT_TENANT_COMMENTS SET EMP_NO=?,RPTTC_NOTE=? WHERE RPTTC_NO=? ";
	private static final String SAVE_NOTE = "UPDATE REPORT_TENANT_COMMENTS SET RPTTC_NOTE=? WHERE RPTTC_NO=? ";
	private static final String FAIL_STMT = "UPDATE REPORT_TENANT_COMMENTS SET RPTTC_RESULT=?,RPTTC_NOTE=? WHERE RPTTC_NO=? ";

	@Override
	public void insert(RpttcVO rpttcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rpttcVO.getTcm_no());
			pstmt.setString(2, rpttcVO.getTnt_no());
			pstmt.setString(3, rpttcVO.getRpttc_content());

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
	public void update(RpttcVO rpttcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rpttcVO.getTcm_no());
			pstmt.setString(2, rpttcVO.getTnt_no());
			pstmt.setString(3, rpttcVO.getRpttc_content());
			pstmt.setString(4, rpttcVO.getEmp_no());
			pstmt.setInt(5, rpttcVO.getRpttc_status());
			pstmt.setInt(6, rpttcVO.getRpttc_result());
			pstmt.setString(7, rpttcVO.getRpttc_note());
			pstmt.setString(8, rpttcVO.getRpttc_no());

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

	public void updateEmp(RpttcVO rpttcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMP);

			pstmt.setString(1, rpttcVO.getEmp_no());
			pstmt.setInt(2, rpttcVO.getRpttc_status());
			pstmt.setString(3, rpttcVO.getRpttc_no());

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
	
	public void assignEmp(RpttcVO rpttcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ASSIGN_EMP);

			pstmt.setString(1, rpttcVO.getEmp_no());
			pstmt.setString(2, rpttcVO.getRpttc_note());
			pstmt.setString(3, rpttcVO.getRpttc_no());

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
	
	public void fail(RpttcVO rpttcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FAIL_STMT);

			pstmt.setInt(1, rpttcVO.getRpttc_result());
			pstmt.setString(2, rpttcVO.getRpttc_note());
			pstmt.setString(3, rpttcVO.getRpttc_no());

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

	public void saveNote(RpttcVO rpttcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVE_NOTE);

		
			pstmt.setString(1, rpttcVO.getRpttc_note());
			pstmt.setString(2, rpttcVO.getRpttc_no());

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
	public void delete(String rpttc_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rpttc_no);

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
	public RpttcVO findByPrimaryKey(String rpttc_no) {

		RpttcVO rpttcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTTC_STMT);

			pstmt.setString(1, rpttc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttcVO = new RpttcVO();
				rpttcVO.setRpttc_no(rs.getString("rpttc_no"));
				rpttcVO.setTcm_no(rs.getString("tcm_no"));
				rpttcVO.setTnt_no(rs.getString("tnt_no"));
				rpttcVO.setRpttc_time(rs.getTimestamp("rpttc_time"));
				rpttcVO.setRpttc_content(rs.getString("rpttc_content"));
				rpttcVO.setEmp_no(rs.getString("emp_no"));
				rpttcVO.setRpttc_done_time(rs.getTimestamp("rpttc_done_time"));
				rpttcVO.setRpttc_status(rs.getInt("rpttc_status"));
				rpttcVO.setRpttc_result(rs.getInt("rpttc_result"));
				rpttcVO.setRpttc_note(rs.getString("rpttc_note"));
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

		return rpttcVO;
	}

	@Override
	public List<RpttcVO> getAll() {

		List<RpttcVO> list = new ArrayList<RpttcVO>();
		RpttcVO rpttcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttcVO = new RpttcVO();
				rpttcVO.setRpttc_no(rs.getString("rpttc_no"));
				rpttcVO.setTcm_no(rs.getString("tcm_no"));
				rpttcVO.setTnt_no(rs.getString("tnt_no"));
				rpttcVO.setRpttc_time(rs.getTimestamp("rpttc_time"));
				rpttcVO.setRpttc_content(rs.getString("rpttc_content"));
				rpttcVO.setEmp_no(rs.getString("emp_no"));
				rpttcVO.setRpttc_done_time(rs.getTimestamp("rpttc_done_time"));
				rpttcVO.setRpttc_status(rs.getInt("rpttc_status"));
				rpttcVO.setRpttc_result(rs.getInt("rpttc_result"));
				rpttcVO.setRpttc_note(rs.getString("rpttc_note"));
				list.add(rpttcVO);
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
	public List<RpttcVO> findByNo(String Number) {
		List<RpttcVO> list = new ArrayList<RpttcVO>();
		RpttcVO rpttcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            System.out.println("近來多筆查詢");
            System.out.println(Number);
			if (Number.startsWith("TC")) {
				pstmt = con.prepareStatement(GET_TCM_STMT);
			} else if (Number.startsWith("T")) {
				pstmt = con.prepareStatement(GET_TNT_STMT);
			} else if (Number.startsWith("R")) {
				pstmt = con.prepareStatement(GET_RPTTC_STMT);
			} else if (Number.startsWith("E")) {
				pstmt = con.prepareStatement(GET_EMP_STMT);
			} else if (Number.equals("0") || Number.equals("1")) {
				pstmt = con.prepareStatement(GET_RESULT_STMT);
			} else {
				System.out.println("wrong sql");
			}
			pstmt.setString(1, Number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpttcVO = new RpttcVO();
				rpttcVO.setRpttc_no(rs.getString("rpttc_no"));
				rpttcVO.setTcm_no(rs.getString("tcm_no"));
				rpttcVO.setTnt_no(rs.getString("tnt_no"));
				rpttcVO.setRpttc_time(rs.getTimestamp("rpttc_time"));
				rpttcVO.setRpttc_content(rs.getString("rpttc_content"));
				rpttcVO.setEmp_no(rs.getString("emp_no"));
				rpttcVO.setRpttc_done_time(rs.getTimestamp("rpttc_done_time"));
				rpttcVO.setRpttc_status(rs.getInt("rpttc_status"));
				rpttcVO.setRpttc_result(rs.getInt("rpttc_result"));
				rpttcVO.setRpttc_note(rs.getString("rpttc_note"));
				list.add(rpttcVO);
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
