
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.tnt.model.*;

//@WebServlet("/ImgReader2")
public class ImgReader extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			ServletOutputStream out = res.getOutputStream();
			
			String mem_no = req.getParameter("id").trim();
			

			if (mem_no.substring(0, 3).equalsIgnoreCase("tnt")) {
				String tnt_no = mem_no;
				TntService TntSvc = new TntService();
				TntVO tntVO = TntSvc.getOneTntPic(tnt_no);
					byte[] b = tntVO.getTnt_pic();
					out.write(b);
					out.close();
			}
			if (mem_no.substring(0, 3).equalsIgnoreCase("lld")) {
				String lld_no = mem_no;
				TntService TntSvc = new TntService();
				TntVO tntVO = TntSvc.getOneTntPic(lld_no);
					byte[] b = tntVO.getTnt_pic();
					out.write(b);
					out.close();
			}
			
			


	}
}