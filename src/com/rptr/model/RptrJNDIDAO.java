package com.rptr.model;

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

import com.rptr.model.RptrDAO_interface;
import com.rptr.model.RptrVO;

public class RptrJNDIDAO implements RptrDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_REPAIR(RPTR_NO,REP_NO,TNT_NO,RPTR_CONTENT) VALUES ('RPTR'||LPAD(SEQ_RPTR_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_REPAIR SET REP_NO=?,TNT_NO=?,RPTR_CONTENT=?,EMP_NO=?,RPTR_STATUS=?,RPTR_RESULT=?,RPTR_NOTE=? WHERE RPTR_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_REPAIR WHERE RPTR_NO=?";
	private static final String GET_RPTR_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR WHERE RPTR_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR ORDER BY RPTR_NO";
	private static final String GET_REP_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR WHERE REP_NO=? ";
	private static final String GET_TNT_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR WHERE TNT_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR WHERE EMP_NO=? ";
	private static final String GET_RESULT_STMT = "SELECT RPTR_NO,REP_NO,TNT_NO,RPTR_TIME,RPTR_CONTENT,EMP_NO,RPTR_DONE_TIME,RPTR_STATUS,RPTR_RESULT,RPTR_NOTE FROM REPORT_REPAIR WHERE RPTR_RESULT=? ";
	private static final String UPDATE_EMP = "UPDATE REPORT_REPAIR SET EMP_NO=?,RPTR_STATUS=? WHERE RPTR_NO=? ";
	private static final String ASSIGN_EMP = "UPDATE REPORT_REPAIR SET EMP_NO=?,RPTR_NOTE=? WHERE RPTR_NO=? ";
	private static final String SAVE_NOTE = "UPDATE REPORT_REPAIR SET RPTR_NOTE=? WHERE RPTR_NO=? ";
	private static final String FAIL_STMT = "UPDATE REPORT_REPAIR SET RPTR_RESULT=?,RPTR_NOTE=? WHERE RPTR_NO=? ";

	@Override
	public void insert(RptrVO rptrVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rptrVO.getRep_no());
			pstmt.setString(2, rptrVO.getTnt_no());
			pstmt.setString(3, rptrVO.getRptr_content());

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
	public void update(RptrVO rptrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rptrVO.getRep_no());
			pstmt.setString(2, rptrVO.getTnt_no());
			pstmt.setString(3, rptrVO.getRptr_content());
			pstmt.setString(4, rptrVO.getEmp_no());
			pstmt.setInt(5, rptrVO.getRptr_status());
			pstmt.setInt(6, rptrVO.getRptr_result());
			pstmt.setString(7, rptrVO.getRptr_note());
			pstmt.setString(8, rptrVO.getRptr_no());

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

	public void updateEmp(RptrVO rptrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMP);

			pstmt.setString(1, rptrVO.getEmp_no());
			pstmt.setInt(2, rptrVO.getRptr_status());
			pstmt.setString(3, rptrVO.getRptr_no());

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
	
	public void assignEmp(RptrVO rptrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ASSIGN_EMP);

			pstmt.setString(1, rptrVO.getEmp_no());
			pstmt.setString(2, rptrVO.getRptr_note());
			pstmt.setString(3, rptrVO.getRptr_no());

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
	
	public void fail(RptrVO rptrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FAIL_STMT);

			pstmt.setInt(1, rptrVO.getRptr_result());
			pstmt.setString(2, rptrVO.getRptr_note());
			pstmt.setString(3, rptrVO.getRptr_no());

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

	public void saveNote(RptrVO rptrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVE_NOTE);

		
			pstmt.setString(1, rptrVO.getRptr_note());
			pstmt.setString(2, rptrVO.getRptr_no());

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
	public void delete(String rptr_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rptr_no);

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
	public RptrVO findByPrimaryKey(String rptr_no) {

		RptrVO rptrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTR_STMT);

			pstmt.setString(1, rptr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptrVO = new RptrVO();
				rptrVO.setRptr_no(rs.getString("rptr_no"));
				rptrVO.setRep_no(rs.getString("rep_no"));
				rptrVO.setTnt_no(rs.getString("tnt_no"));
				rptrVO.setRptr_time(rs.getTimestamp("rptr_time"));
				rptrVO.setRptr_content(rs.getString("rptr_content"));
				rptrVO.setEmp_no(rs.getString("emp_no"));
				rptrVO.setRptr_done_time(rs.getTimestamp("rptr_done_time"));
				rptrVO.setRptr_status(rs.getInt("rptr_status"));
				rptrVO.setRptr_result(rs.getInt("rptr_result"));
				rptrVO.setRptr_note(rs.getString("rptr_note"));
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

		return rptrVO;
	}

	@Override
	public List<RptrVO> getAll() {

		List<RptrVO> list = new ArrayList<RptrVO>();
		RptrVO rptrVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rptrVO = new RptrVO();
				rptrVO.setRptr_no(rs.getString("rptr_no"));
				rptrVO.setRep_no(rs.getString("rep_no"));
				rptrVO.setTnt_no(rs.getString("tnt_no"));
				rptrVO.setRptr_time(rs.getTimestamp("rptr_time"));
				rptrVO.setRptr_content(rs.getString("rptr_content"));
				rptrVO.setEmp_no(rs.getString("emp_no"));
				rptrVO.setRptr_done_time(rs.getTimestamp("rptr_done_time"));
				rptrVO.setRptr_status(rs.getInt("rptr_status"));
				rptrVO.setRptr_result(rs.getInt("rptr_result"));
				rptrVO.setRptr_note(rs.getString("rptr_note"));
				list.add(rptrVO);
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
	public List<RptrVO> findByNo(String Number) {
		List<RptrVO> list = new ArrayList<RptrVO>();
		RptrVO rptrVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            System.out.println("近來多筆查詢");
            System.out.println(Number);
			if (Number.startsWith("RE")) {
				pstmt = con.prepareStatement(GET_REP_STMT);
			} else if (Number.startsWith("T")) {
				pstmt = con.prepareStatement(GET_TNT_STMT);
			} else if (Number.startsWith("R")) {
				pstmt = con.prepareStatement(GET_RPTR_STMT);
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
				rptrVO = new RptrVO();
				rptrVO.setRptr_no(rs.getString("rptr_no"));
				rptrVO.setRep_no(rs.getString("rep_no"));
				rptrVO.setTnt_no(rs.getString("tnt_no"));
				rptrVO.setRptr_time(rs.getTimestamp("rptr_time"));
				rptrVO.setRptr_content(rs.getString("rptr_content"));
				rptrVO.setEmp_no(rs.getString("emp_no"));
				rptrVO.setRptr_done_time(rs.getTimestamp("rptr_done_time"));
				rptrVO.setRptr_status(rs.getInt("rptr_status"));
				rptrVO.setRptr_result(rs.getInt("rptr_result"));
				rptrVO.setRptr_note(rs.getString("rptr_note"));
				list.add(rptrVO);
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
