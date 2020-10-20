import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ImgReader extends HttpServlet {

	private static final String GET_PIC_STMT = "SELECT tnt_pic from TENANT where tnt_no=?";

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		PreparedStatement pstmt;
		ResultSet rs;

		try {
			String tnt_no = req.getParameter("id").trim();

			pstmt = con.prepareStatement(GET_PIC_STMT);
			pstmt.setString(1, tnt_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("tnt_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/images/NoData/null2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
//			System.out.println(3);
			InputStream in = getServletContext().getResourceAsStream("/images/NoData/none.jpg");
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

	// 使用byte[]方式
	public static byte[] getPictureByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 此資料流回把write的位元資料存到一個內建的byte[]
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
//			in.close();

		return baos.toByteArray(); // toByteArray()可以讓我們取得這個資料流內建的byte[]
	}

}