import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdatePicID {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "G2DB";
	private static final String passwd = "123456";
	private static final String GET_HOUSE_ID = "SELECT H.HOS_NO FROM HOUSE H JOIN HOUSE_PICTURE HS ON H.HOS_ID = HS.HOS_NO WHERE PIC_NO = ?";
	private static final String CHANGE_ID = "UPDATE HOUSE_PICTURE SET HOS_NO = ? WHERE PIC_NO = ?";
	private static final String pattern = "%06d";

	public Map<String, String> getID() {

		Map<String, String> hashMap = new HashMap<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HOUSE_ID);

			for (int i = 0; i < 141463; i++) {

				pstmt.setString(1, "PIC" + String.format(pattern, i));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					hashMap.put(("PIC" + String.format(pattern, i)), (rs.getString("hos_no")));
				}
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return hashMap;
	}

	public void setID() throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGE_ID);

			UpdatePicID id = new UpdatePicID();
			Map<String, String> a = id.getID();
			
			for (Map.Entry<String, String> entry : a.entrySet()) {
				pstmt.clearParameters();
				pstmt.setString(1, entry.getValue());
				pstmt.setString(2, entry.getKey());

				System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
				pstmt.execute();
			}
				

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL壞了 ");
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
	}

	public static void main(String[] args) throws Exception {
		UpdatePicID id = new UpdatePicID();
		id.setID();
	}
}
