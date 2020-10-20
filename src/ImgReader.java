
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/ImgReaderMEM")
public class ImgReader extends HttpServlet {

	private static final String GET_TNT_PIC_STMT = "SELECT tnt_pic from TENANT where tnt_no=?";
	private static final String GET_LLD_PIC_STMT = "SELECT lld_pic from LANDLORD where lld_no=?";

	Connection con;
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String GET_PIC_STMT ="";
		String picColumnName="";
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		PreparedStatement pstmt;
		ResultSet rs;

		try {
//			System.out.println("ImgReader:1");
			String mem_no = req.getParameter("id").trim();
//			System.out.println("ImgReader mem_no:" + mem_no);

			if (mem_no.substring(0, 3).equalsIgnoreCase("tnt")) {

				GET_PIC_STMT = GET_TNT_PIC_STMT;
//				System.out.println(GET_PIC_STMT);
				picColumnName = "tnt_pic";

//				System.out.println("ImgReader:?���?");
			}
			if (mem_no.substring(0, 3).equalsIgnoreCase("lld")) {
				GET_PIC_STMT = GET_LLD_PIC_STMT;
				picColumnName = "lld_pic";
//				System.out.println("ImgReader:?��?��");
			}

			pstmt = con.prepareStatement(GET_PIC_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
//			System.out.println(rs);

			if (rs.next()) {
//				System.out.println("ImgReader:rs1");
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(picColumnName));
//				System.out.println("ImgReader:rs2");
				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				System.out.println("ImgReader:rs3");
				int len;
				while ((len = in.read(buf)) != -1) {
//					System.out.println("ImgReader:rs4");
					out.write(buf, 0, len);
					out.flush();
				}
				in.close();
//				System.out.println("ImgReader:rs5");
			} else {
//				System.out.println("ImgReader:rs");
//				InputStream in = getServletContext().getResourceAsStream("/images/NoData/null2.jpg");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				out.write(b);
//				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("Exception from ImgReader.java: " + e.getMessage());
			e.printStackTrace();
//			InputStream in = getServletContext().getResourceAsStream("/images/NoData/none.jpg");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			out.write(b);
//			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
			con = ds.getConnection();
			System.out.println(con);
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

	// 使用byte[]?���?
	public static byte[] getPictureByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 此�?��?��?��?��?�write??��?��?��?��?��?�到�??�內建�?�byte[]
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
//			in.close();

		return baos.toByteArray(); // toByteArray()?��以�?��?��?��?��?��?��?��?��?��?�內建�?�byte[]
	}

}