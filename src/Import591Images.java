import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

class hospic {
	public String filePath;
	public String hos_no;

	public hospic(String filePath, String hos_no) {
		this.filePath = filePath;
		this.hos_no = hos_no;
	}
}

public class Import591Images {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "G2DB";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO HOUSE_PICTURE(PIC_NO, HOS_NO, HOS_PIC)" + "VALUES('PIC' || lpad(SEQ_PIC_NO.NEXTVAL, 6,'0'), ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// 當初pstmt不給過 只好把變數塞進list
		ArrayList<hospic> list = new ArrayList<hospic>();
		try {
			// 走訪所有資料夾下的所有檔案
			Files.walk(Paths.get("E:/591/591"))
					// 只抓.jpg檔
					.filter(p -> p.getFileName().toString().endsWith(".jpg")).forEach(f -> {
						// 取得檔案路徑
						String filePath = f.toString();
						File file = new File(filePath);
						// 取得房屋ID的資料夾名稱
						String hos_no = file.getParentFile().getParentFile().getName();
						list.add(new hospic(filePath, hos_no));
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);


			for (int i = 0; i < list.size(); i++) {
				// 避免拿到舊的parameters
				pstmt.clearParameters();
				hospic obj = list.get(i);
				pstmt.setString(1, obj.hos_no);
				InputStream is = getPictureStream(obj.filePath);
				pstmt.setBinaryStream(2, is, is.available());
				pstmt.executeUpdate();
				System.out.println(i);
			}
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
}
