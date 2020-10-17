import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.housemanage.model.HouseVO;

public class UpdatePicID {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2DB";
	String passwd = "123456";
	private static final String GET_HOUSE_ID = "SELECT HOS_NO FROM HOUSE H JOIN HOUSE_PICTURE HS ON H.HOS_ID = HS.HOS_ID WHERE PIC_ID = ?";
	
	public List<HouseVO> uddateID() {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HOUSE_ID);

//			pstmt.setString(1, i);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHos_no(rs.getString("hos_no"));
				list.add(houseVO); 
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
		return list;
	}
}
