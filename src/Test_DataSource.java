import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Test_DataSource")
public class Test_DataSource extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException,IOException{
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
			if (ds != null) {
				Connection conn = ds.getConnection();

				if (conn != null) {
					out.print("GOT Connection:" + conn.toString());
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM CONTRACT_APPLICATION");
					while (rs.next()) {
						out.println("APL_NO = " + rs.getString(1));
					}
					conn.close();
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
