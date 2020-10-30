package com.rpth.model;

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

import com.rpth.model.RpthDAO_interface;
import com.rpth.model.RpthVO;

public class RpthJNDIDAO implements RpthDAO_interface {

	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_HOUSE(RPTH_NO,HOS_NO,TNT_NO,RPTH_CONTENT) VALUES ('RPTH'||LPAD(SEQ_RPTH_NO.NEXTVAL,6,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE REPORT_HOUSE SET HOS_NO=?,TNT_NO=?,RPTH_CONTENT=?,EMP_NO=?,RPTH_STATUS=?,RPTH_RESULT=?,RPTH_NOTE=? WHERE RPTH_NO=?";
	private static final String DELETE = "DELETE FROM REPORT_HOUSE WHERE RPTH_NO=?";
	private static final String GET_RPTH_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE WHERE RPTH_NO=? ";
	private static final String GET_ALL_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE ORDER BY RPTH_NO";
	private static final String GET_HOS_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE WHERE HOS_NO=? ";
	private static final String GET_TNT_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE WHERE TNT_NO=? ";
	private static final String GET_EMP_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE WHERE EMP_NO=? ";
	private static final String GET_RESULT_STMT = "SELECT RPTH_NO,HOS_NO,TNT_NO,RPTH_TIME,RPTH_CONTENT,EMP_NO,RPTH_DONE_TIME,RPTH_STATUS,RPTH_RESULT,RPTH_NOTE FROM REPORT_HOUSE WHERE RPTH_RESULT=? ";
	private static final String UPDATE_EMP = "UPDATE REPORT_HOUSE SET EMP_NO=?,RPTH_STATUS=? WHERE RPTH_NO=? ";
	private static final String ASSIGN_EMP = "UPDATE REPORT_HOUSE SET EMP_NO=?,RPTH_NOTE=? WHERE RPTH_NO=? ";
	private static final String SAVE_NOTE = "UPDATE REPORT_HOUSE SET RPTH_NOTE=? WHERE RPTH_NO=? ";
	private static final String FAIL_STMT = "UPDATE REPORT_HOUSE SET RPTH_RESULT=?,RPTH_NOTE=? WHERE RPTH_NO=? ";

	@Override
	public void insert(RpthVO rpthVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rpthVO.getHos_no());
			pstmt.setString(2, rpthVO.getTnt_no());
			pstmt.setString(3, rpthVO.getRpth_content());

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
	public void update(RpthVO rpthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rpthVO.getHos_no());
			pstmt.setString(2, rpthVO.getTnt_no());
			pstmt.setString(3, rpthVO.getRpth_content());
			pstmt.setString(4, rpthVO.getEmp_no());
			pstmt.setInt(5, rpthVO.getRpth_status());
			pstmt.setInt(6, rpthVO.getRpth_result());
			pstmt.setString(7, rpthVO.getRpth_note());
			pstmt.setString(8, rpthVO.getRpth_no());

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

	public void updateEmp(RpthVO rpthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMP);

			pstmt.setString(1, rpthVO.getEmp_no());
			pstmt.setInt(2, rpthVO.getRpth_status());
			pstmt.setString(3, rpthVO.getRpth_no());

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
	
	public void assignEmp(RpthVO rpthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ASSIGN_EMP);

			pstmt.setString(1, rpthVO.getEmp_no());
			pstmt.setString(2, rpthVO.getRpth_note());
			pstmt.setString(3, rpthVO.getRpth_no());

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
	
	public void fail(RpthVO rpthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FAIL_STMT);

			pstmt.setInt(1, rpthVO.getRpth_result());
			pstmt.setString(2, rpthVO.getRpth_note());
			pstmt.setString(3, rpthVO.getRpth_no());

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

	public void saveNote(RpthVO rpthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVE_NOTE);

		
			pstmt.setString(1, rpthVO.getRpth_note());
			pstmt.setString(2, rpthVO.getRpth_no());

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
	public void delete(String rpth_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rpth_no);

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
	public RpthVO findByPrimaryKey(String rpth_no) {

		RpthVO rpthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTH_STMT);

			pstmt.setString(1, rpth_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpthVO = new RpthVO();
				rpthVO.setRpth_no(rs.getString("rpth_no"));
				rpthVO.setHos_no(rs.getString("hos_no"));
				rpthVO.setTnt_no(rs.getString("tnt_no"));
				rpthVO.setRpth_time(rs.getTimestamp("rpth_time"));
				rpthVO.setRpth_content(rs.getString("rpth_content"));
				rpthVO.setEmp_no(rs.getString("emp_no"));
				rpthVO.setRpth_done_time(rs.getTimestamp("rpth_done_time"));
				rpthVO.setRpth_status(rs.getInt("rpth_status"));
				rpthVO.setRpth_result(rs.getInt("rpth_result"));
				rpthVO.setRpth_note(rs.getString("rpth_note"));
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

		return rpthVO;
	}

	@Override
	public List<RpthVO> getAll() {

		List<RpthVO> list = new ArrayList<RpthVO>();
		RpthVO rpthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpthVO = new RpthVO();
				rpthVO.setRpth_no(rs.getString("rpth_no"));
				rpthVO.setHos_no(rs.getString("hos_no"));
				rpthVO.setTnt_no(rs.getString("tnt_no"));
				rpthVO.setRpth_time(rs.getTimestamp("rpth_time"));
				rpthVO.setRpth_content(rs.getString("rpth_content"));
				rpthVO.setEmp_no(rs.getString("emp_no"));
				rpthVO.setRpth_done_time(rs.getTimestamp("rpth_done_time"));
				rpthVO.setRpth_status(rs.getInt("rpth_status"));
				rpthVO.setRpth_result(rs.getInt("rpth_result"));
				rpthVO.setRpth_note(rs.getString("rpth_note"));
				list.add(rpthVO);
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
	public List<RpthVO> findByNo(String Number) {
		List<RpthVO> list = new ArrayList<RpthVO>();
		RpthVO rpthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (Number.startsWith("T")) {
				pstmt = con.prepareStatement(GET_HOS_STMT);
			} else if (Number.startsWith("L")) {
				pstmt = con.prepareStatement(GET_TNT_STMT);
			} else if (Number.startsWith("R")) {
				pstmt = con.prepareStatement(GET_RPTH_STMT);
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
				rpthVO = new RpthVO();
				rpthVO.setRpth_no(rs.getString("rpth_no"));
				rpthVO.setHos_no(rs.getString("hos_no"));
				rpthVO.setTnt_no(rs.getString("tnt_no"));
				rpthVO.setRpth_time(rs.getTimestamp("rpth_time"));
				rpthVO.setRpth_content(rs.getString("rpth_content"));
				rpthVO.setEmp_no(rs.getString("emp_no"));
				rpthVO.setRpth_done_time(rs.getTimestamp("rpth_done_time"));
				rpthVO.setRpth_status(rs.getInt("rpth_status"));
				rpthVO.setRpth_result(rs.getInt("rpth_result"));
				rpthVO.setRpth_note(rs.getString("rpth_note"));
				list.add(rpthVO);
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
