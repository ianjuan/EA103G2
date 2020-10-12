package com.news.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;




public class NewsDAO implements NewsDAO_interface{
	
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
			"INSERT INTO NEWS (NEW_NO,NEW_CONTENT,NEW_BLOB,EMP_NO) VALUES('NEW' || lpad(SEQ_NEW_NO.NEXTVAL, 6,'0'),?,NULL,?)";
	private static final String GET_ALL_STMT =
			"SELECT NEW_NO,NEW_CONTENT,NEW_BLOB,EMP_NO FROM NEWS";
	private static final String GET_ONE_STMT = 
			"SELECT NEW_NO,NEW_CONTENT,NEW_BLOB,EMP_NO FROM NEWS WHERE NEW_NO = ? ";
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, newsVO.getNew_content());
			pstmt.setString(2, newsVO.getEmp_no());
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
	public NewsVO findByPrimaryKey(String new_no) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, new_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// funVO �]�٬� Domain objects
				newsVO = new NewsVO();
				newsVO.setNew_no(rs.getString("new_no"));
				//newsVO.setNew_blob(rs.getString("new_blob"));
				
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
		return newsVO;
		
	}

	@Override
	public List<NewsVO> getAll() {

		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// funVO �]�٬� Domain objects
				newsVO = new NewsVO();
				newsVO.setNew_no(rs.getString("new_no"));
				newsVO.setNew_content(rs.getString("new_content"));
				//newsVO.setNew_blob(rs.getString("new_blob"));
				newsVO.setEmp_no(rs.getString("emp_no"));
				list.add(newsVO); // Store the row in the list
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
	public void update(NewsVO newsVO) {
		// TODO Auto-generated method stub
		
	}
}
