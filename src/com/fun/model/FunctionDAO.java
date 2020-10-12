package com.fun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;




public class FunctionDAO implements FunctionDAO_interface{
	
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
			"INSERT INTO function(fun_no,fun_name) values(?,?)";
	private static final String GET_ALL_STMT =
			"SELECT FUN_NO,FUN_NAME FROM FUNCTION";
	private static final String GET_ONE_STMT = 
			"SELECT FUN_NO,FUN_NAME FROM FUNCTION WHERE FUN_NO = ? ";
	@Override
	public void insert(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, functionVO.getFun_no());
			pstmt.setString(2, functionVO.getFun_name());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public FunctionVO findByPrimaryKey(String fun_no) {
		FunctionVO funVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fun_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// funVO �]�٬� Domain objects
				funVO = new FunctionVO();
				funVO.setFun_no(rs.getString("fun_no"));
				funVO.setFun_name(rs.getString("fun_name"));
				
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
		return funVO;
		
	}

	@Override
	public List<FunctionVO> getAll() {

		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO funVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// funVO �]�٬� Domain objects
				funVO = new FunctionVO();
				funVO.setFun_no(rs.getString("fun_no"));
				funVO.setFun_name(rs.getString("fun_name"));
				list.add(funVO); // Store the row in the list
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

	@Override
	public void update(FunctionVO functionVO) {
		// TODO Auto-generated method stub
		
	}
}
