package com.cont.controller;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.cont.model.ConVO;

public class ContractSingleImgReader extends HttpServlet {

	private static final String GET_LLDHOUSEPIC = "SELECT CON_LLD_SIGN FROM CONTRACT where CON_NO = ?";

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		PreparedStatement pstmt;
		ResultSet rs;

		try {
			String con_no = req.getParameter("id").trim();

			pstmt = con.prepareStatement(GET_LLDHOUSEPIC);
			pstmt.setString(1, con_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("CON_LLD_SIGN"));
				byte[] buf = new byte[4 * 1024];				
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/front-end/house_manage/images/NoData/null1.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
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