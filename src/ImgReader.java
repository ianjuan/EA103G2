
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.tnt.model.*;
import com.lld.model.*;

public class ImgReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		
		// 判斷身分 取得byteArray與性別
		String mem_no = req.getParameter("id").trim();
		byte[] byteArray = null;
		Boolean mem_sex = false;
		if (mem_no.substring(0, 3).equalsIgnoreCase("tnt")) {
			TntService tntSvc = new TntService();
			TntVO tntVO = tntSvc.getOneTntPic(mem_no);
			byteArray = tntVO.getTnt_pic();
			mem_sex = tntSvc.getOneTntProfile(mem_no).getTnt_sex();
		}
		if (mem_no.substring(0, 3).equalsIgnoreCase("lld")) {
			LldService lldSvc = new LldService();
			LldVO lldVO = lldSvc.getOneLldPic(mem_no);
			byteArray = lldVO.getLld_pic();
			mem_sex = lldSvc.getOneLldProfile(mem_no).getLld_sex();
		}
		
		// 讀圖
		if (byteArray != null) {
			out.write(byteArray);
			out.close();
		} else {  //還沒有大頭貼
			String resourceString = "";
			if (mem_sex) {
				resourceString = "/resource/Mycol/images/person_man.png";
			} else {
				resourceString = "/resource/Mycol/images/person_women.png";					
			}
			InputStream in = getServletContext().getResourceAsStream(resourceString);
//			InputStream in = getServletContext().getResourceAsStream("/images/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
			out.close();
		}
	}

}