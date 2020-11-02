package com.rptlc.model;

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

import com.rptlc.model.RptlcDAO_interface;
import com.rptlc.model.RptlcVO;

public class RptlcJNDIDAO implements RptlcDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_LANDLORD_COMMENTS(RPTLC_NO,LCM_NO,LLD_NO,RPTLC_CONTENT) VALUES ('RPTLC'||LPAD(SEQ_RPTLC_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_LANDLORD_COMMENTS SET LCM_NO=?,LLD_NO=?,RPTLC_CONTENT=?,EMP_NO=?,RPTLC_STATUS=?,RPTLC_RESULT=?,RPTLC_NOTE=? WHERE RPTLC_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_LANDLORD_COMMENTS WHERE RPTLC_NO=?";
	private static final String GET_RPTLC_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS WHERE RPTLC_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS ORDER BY RPTLC_NO";
	private static final String GET_LCM_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS WHERE LCM_NO=? ";
	private static final String GET_LLD_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS WHERE LLD_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS WHERE EMP_NO=? ";
	private static final String GET_RESULT_STMT = "SELECT RPTLC_NO,LCM_NO,LLD_NO,RPTLC_TIME,RPTLC_CONTENT,EMP_NO,RPTLC_DONE_TIME,RPTLC_STATUS,RPTLC_RESULT,RPTLC_NOTE FROM REPORT_LANDLORD_COMMENTS WHERE RPTLC_RESULT=? ";
	private static final String UPDATE_EMP = "UPDATE REPORT_LANDLORD_COMMENTS SET EMP_NO=?,RPTLC_STATUS=? WHERE RPTLC_NO=? ";
	private static final String ASSIGN_EMP = "UPDATE REPORT_LANDLORD_COMMENTS SET EMP_NO=?,RPTLC_NOTE=? WHERE RPTLC_NO=? ";
	private static final String SAVE_NOTE = "UPDATE REPORT_LANDLORD_COMMENTS SET RPTLC_NOTE=? WHERE RPTLC_NO=? ";
	private static final String FAIL_STMT = "UPDATE REPORT_LANDLORD_COMMENTS SET RPTLC_RESULT=?,RPTLC_NOTE=? WHERE RPTLC_NO=? ";

	@Override
	public void insert(RptlcVO rptlcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rptlcVO.getLcm_no());
			pstmt.setString(2, rptlcVO.getLld_no());
			pstmt.setString(3, rptlcVO.getRptlc_content());

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
	public void update(RptlcVO rptlcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rptlcVO.getLcm_no());
			pstmt.setString(2, rptlcVO.getLld_no());
			pstmt.setString(3, rptlcVO.getRptlc_content());
			pstmt.setString(4, rptlcVO.getEmp_no());
			pstmt.setInt(5, rptlcVO.getRptlc_status());
			pstmt.setInt(6, rptlcVO.getRptlc_result());
			pstmt.setString(7, rptlcVO.getRptlc_note());
			pstmt.setString(8, rptlcVO.getRptlc_no());

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

	public void updateEmp(RptlcVO rptlcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMP);

			pstmt.setString(1, rptlcVO.getEmp_no());
			pstmt.setInt(2, rptlcVO.getRptlc_status());
			pstmt.setString(3, rptlcVO.getRptlc_no());

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
	
	public void assignEmp(RptlcVO rptlcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ASSIGN_EMP);

			pstmt.setString(1, rptlcVO.getEmp_no());
			pstmt.setString(2, rptlcVO.getRptlc_note());
			pstmt.setString(3, rptlcVO.getRptlc_no());

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
	
	public void fail(RptlcVO rptlcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FAIL_STMT);

			pstmt.setInt(1, rptlcVO.getRptlc_result());
			pstmt.setString(2, rptlcVO.getRptlc_note());
			pstmt.setString(3, rptlcVO.getRptlc_no());

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

	public void saveNote(RptlcVO rptlcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVE_NOTE);

		
			pstmt.setString(1, rptlcVO.getRptlc_note());
			pstmt.setString(2, rptlcVO.getRptlc_no());

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
	public void delete(String rptlc_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rptlc_no);

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
	public RptlcVO findByPrimaryKey(String rptlc_no) {

		RptlcVO rptlcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTLC_STMT);

			pstmt.setString(1, rptlc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptlcVO = new RptlcVO();
				rptlcVO.setRptlc_no(rs.getString("rptlc_no"));
				rptlcVO.setLcm_no(rs.getString("lcm_no"));
				rptlcVO.setLld_no(rs.getString("lld_no"));
				rptlcVO.setRptlc_time(rs.getTimestamp("rptlc_time"));
				rptlcVO.setRptlc_content(rs.getString("rptlc_content"));
				rptlcVO.setEmp_no(rs.getString("emp_no"));
				rptlcVO.setRptlc_done_time(rs.getTimestamp("rptlc_done_time"));
				rptlcVO.setRptlc_status(rs.getInt("rptlc_status"));
				rptlcVO.setRptlc_result(rs.getInt("rptlc_result"));
				rptlcVO.setRptlc_note(rs.getString("rptlc_note"));
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

		return rptlcVO;
	}

	@Override
	public List<RptlcVO> getAll() {

		List<RptlcVO> list = new ArrayList<RptlcVO>();
		RptlcVO rptlcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptlcVO = new RptlcVO();
				rptlcVO.setRptlc_no(rs.getString("rptlc_no"));
				rptlcVO.setLcm_no(rs.getString("lcm_no"));
				rptlcVO.setLld_no(rs.getString("lld_no"));
				rptlcVO.setRptlc_time(rs.getTimestamp("rptlc_time"));
				rptlcVO.setRptlc_content(rs.getString("rptlc_content"));
				rptlcVO.setEmp_no(rs.getString("emp_no"));
				rptlcVO.setRptlc_done_time(rs.getTimestamp("rptlc_done_time"));
				rptlcVO.setRptlc_status(rs.getInt("rptlc_status"));
				rptlcVO.setRptlc_result(rs.getInt("rptlc_result"));
				rptlcVO.setRptlc_note(rs.getString("rptlc_note"));
				list.add(rptlcVO);
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
	public List<RptlcVO> findByNo(String Number) {
		List<RptlcVO> list = new ArrayList<RptlcVO>();
		RptlcVO rptlcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            System.out.println("近來多筆查詢");
            System.out.println(Number);
			if (Number.startsWith("LC")) {
				pstmt = con.prepareStatement(GET_LCM_STMT);
			} else if (Number.startsWith("L")) {
				pstmt = con.prepareStatement(GET_LLD_STMT);
			} else if (Number.startsWith("R")) {
				pstmt = con.prepareStatement(GET_RPTLC_STMT);
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
				rptlcVO = new RptlcVO();
				rptlcVO.setRptlc_no(rs.getString("rptlc_no"));
				rptlcVO.setLcm_no(rs.getString("lcm_no"));
				rptlcVO.setLld_no(rs.getString("lld_no"));
				rptlcVO.setRptlc_time(rs.getTimestamp("rptlc_time"));
				rptlcVO.setRptlc_content(rs.getString("rptlc_content"));
				rptlcVO.setEmp_no(rs.getString("emp_no"));
				rptlcVO.setRptlc_done_time(rs.getTimestamp("rptlc_done_time"));
				rptlcVO.setRptlc_status(rs.getInt("rptlc_status"));
				rptlcVO.setRptlc_result(rs.getInt("rptlc_result"));
				rptlcVO.setRptlc_note(rs.getString("rptlc_note"));
				list.add(rptlcVO);
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
