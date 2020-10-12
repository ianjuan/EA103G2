package com.rig.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;




public class RightDAO implements RightDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT =
			//Insert into  EMPLOYEE (EMP_NO,EMP_ACC,EMP_PWD,EMP_TITLE,EMP_NAME,EMP_IS_DELETE) values ('EMP' || lpad(SEQ_EMP_NO.NEXTVAL, 6,'0'),'2VLwRwro','XCkD3nh7','0','丁孟穎',0);
			"INSERT INTO  RIGHT (EMP_NO,FUN_NO) VALUES(?,?)";
	private static final String GET_EMP_ALL_RIGHT =
			"SELECT EMP_NO,FUN_NO FROM RIGHT WHERE EMP_NO = ?";

	@Override
	public void insert(RightVO rightVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, rightVO.getEmp_no());
			pstmt.setString(2, rightVO.getFun_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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

	}

	

	@Override
	public List<RightVO> getAll(String emp_no) {

		List<RightVO> list = new ArrayList<RightVO>();
		RightVO rigVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMP_ALL_RIGHT);
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// funVO �]�٬� Domain objects
				rigVO = new RightVO();
				rigVO.setEmp_no(rs.getString("emp_no"));
				rigVO.setFun_no(rs.getString("fun_no"));
				list.add(rigVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
