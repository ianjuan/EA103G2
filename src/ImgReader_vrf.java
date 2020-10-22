
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.rptt.model.*;

public class ImgReader_vrf extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		String mem_no = req.getParameter("id").trim();
		String mem_type = req.getParameter("type").trim();
		TntService TntSvc = new TntService();

		TntVO tntVO = TntSvc.getVrfTntPic(mem_no);
		if ("front".equals(mem_type)) {
			byte[] b = tntVO.getTnt_id_picf();
			out.write(b);
		} else if ("back".equals(mem_type)) {
			byte[] b = tntVO.getTnt_id_picb();
			out.write(b);

		} else if ("second".equals(mem_type)) {
			byte[] b = tntVO.getTnt_id_pic2();
			out.write(b);
		}

		out.close();
	}

}