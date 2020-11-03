package com.repair.controller;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.repair.model.RepairVO;


import com.repair_picture.model.Repair_pictureVO;

public class Repair_pictureServlet extends HttpServlet {
	private static final String GET_LLDHOUSEPIC = "SELECT REPPIC_PIC FROM REPAIR_PICTURE WHERE REPPIC_NO =?";

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		PreparedStatement pstmt;
		ResultSet rs;

		try {
			String pic_no = req.getParameter("reppic_no").trim();

			pstmt = con.prepareStatement(GET_LLDHOUSEPIC);
			pstmt.setString(1, pic_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("REPPIC_PIC"));
				byte[] buf = new byte[4 * 1024];				
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/front-end/house_manage/images/NoData/null1.jpg");
				byte[] b = new byte[in.available()];
//				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(3);
			InputStream in = getServletContext().getResourceAsStream("/front-end/house_manage/images/NoData/none.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
	
//	Connection con;
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		System.out.println("a============aaaaaaaa");
//		
//		
//		res.setContentType("image/gif");
//		ServletOutputStream out = res.getOutputStream();
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		
//
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			System.out.println(req.getParameter("reppic_no")+"====try1");
//			
//			stmt = con.createStatement();
//			System.out.println(req.getParameter("reppic_no")+"======try1-2");
//			
////			 "update send set name = '" + name2 + "'  where c_id ='" + a[i] + "' "; 
//			rs = stmt.executeQuery(
//				"SELECT REPPIC_PIC FROM REPAIR_PICTURE WHERE REPPIC_NO = '" + req.getParameter("reppic_no")+ "' ");
//			System.out.println(req.getParameter("reppic_no")+"=========try1-3");
//			
//			if (rs.next()) {
//				System.out.println(req.getParameter("reppic_no")+"========while2");
//				
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("REPPIC_PIC"));
//				byte[] buf = new byte[8 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					baos.write(buf, 0, len);
//					out.write(baos.toByteArray());
//					
//				}
//				
//				in.close();
//
//				System.out.println(req.getParameter("reppic_no")+"已out圖片，while3c出");
//			} 
////				else {
////				//(L)有給但給錯的狀況
//////				res.sendError(HttpServletResponse.SC_NOT_FOUND);
////				InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
////				byte[] b = new byte[in.available()];
////				in.read(b);
////				out.write(b);
////				in.close();
////			}
//			rs.close();
//			stmt.close();
//		} catch (Exception e) {
//			//(L)完全沒給的狀況
////			System.out.println(e);
////			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
////			byte[] b = new byte[in.available()];
////			in.read(b);
////			out.write(b);
////			in.close();
//		}
//	}
//
//	public void init() throws ServletException {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "G2DB", "123456");
//		} catch (ClassNotFoundException e) {
//			throw new UnavailableException("Couldn't load JdbcOracleDriver");
//		} catch (SQLException e) {
//			throw new UnavailableException("Couldn't get db connection");
//		}
//	}
//
//	public void destroy() {
//		try {
//			if (con != null) con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}

