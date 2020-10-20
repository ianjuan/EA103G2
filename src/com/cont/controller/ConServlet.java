package com.cont.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.apl.model.Con_aplService;
import com.apl.model.Con_aplVO;
import com.cont.model.ConDAO;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.*;
import com.lld.model.LldService;
import com.lld.model.LldVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ConServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/*************************** 開始查詢資料 ****************************************/
			ConDAO dao = new ConDAO();
			List<ConVO> list = dao.getAll();

			/*************************** 查詢完成,準備轉交 *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			String url = "/front-end/apl/listAllCon_apl.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

		if ("getlldcontract".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = new String(req.getParameter("lld_no"));
				System.out.println(lld_no);

				ConService conService = new ConService();
				List<ConVO> list = conService.lldgetcon(lld_no);

				HttpSession session = req.getSession();
				session.setAttribute("lld_no", lld_no);
				session.setAttribute("list", list);
				String url = "/front-end/contract/lldlistcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("gettntcontract".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tnt_no = new String(req.getParameter("tnt_no"));
				System.out.println(tnt_no);

				ConService conService = new ConService();
				List<ConVO> list = conService.tntgetcon(tnt_no);

				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				String url = "/front-end/contract/tntlistcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("con_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入合約編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					// 程式中斷
					return;
				}

				String con_no = null;
				try {
					con_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("合約編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);
				if (conVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("conVO", conVO); // 資料庫取出的con_aplVO物件,存入req
				String url = "/front-end/apl/listOneCon_apl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCon_apl.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getonelldcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String lld_no = new String(req.getParameter("lld_no"));
				String hos_no = new String(req.getParameter("hos_no"));
				System.out.println(con_no);
				System.out.println(lld_no);
				System.out.println(hos_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);

				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
				List<HouseVO> houseVOpicno = houseSvc.getLldHousePic(hos_no);
				HouseVO lldInfo = houseSvc.getLldInfo(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);
				req.setAttribute("houseVOpicno", houseVOpicno);// lld_sign
				req.setAttribute("lldInfo", lldInfo);
				String url = "/front-end/contract/lldcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateonelldcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String lld_no = new String(req.getParameter("lld_no"));
				String hos_no = new String(req.getParameter("hos_no"));
//				Date con_che_date = Date.valueOf(req.getParameter("apl_str"));
				System.out.println(lld_no);
				System.out.println(con_no);
				System.out.println(hos_no);

				/*************************** 修正房東資訊 ****************************************/
				String lld_mobile = new String(req.getParameter("lld_mobile"));
				System.out.println(lld_mobile);
				
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);
				String lld_email = lldVO.getLld_email();
				String lld_acc = lldVO.getLld_acc();
				String lld_pwd = lldVO.getLld_pwd();
				String lld_id = lldVO.getLld_id();
				String lld_name = lldVO.getLld_name();
				Date lld_birth = lldVO.getLld_birth();
				Boolean lld_sex = lldVO.getLld_sex();
				String lld_city = lldVO.getLld_city();
				String lld_dist = lldVO.getLld_dist();
				String lld_add = lldVO.getLld_add();
				byte[] lld_pic = lldVO.getLld_pic();
				Integer lld_status = lldVO.getLld_status();
							
				lldSvc.updateLldProfile(lld_no, lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex, lld_mobile, lld_city, lld_dist, lld_add, lld_pic, lld_status);

				/*************************** 房東簽名 ****************************************/
				Part part = req.getPart("con_lld_sign");
				InputStream in = part.getInputStream();
				byte[] con_lld_sign = getPictureByteArray(in);

				byte[] con_tnt_sign = null;
//
//				/*************************** 更新房屋家具 **********************/
//				Integer hos_table = new Integer(req.getParameter("hos_table"));
//				Integer hos_chair = new Integer(req.getParameter("hos_chair"));
//				Integer hos_bed = new Integer(req.getParameter("hos_bed"));
//				Integer hos_closet = new Integer(req.getParameter("hos_closet"));
//				Integer hos_sofa = new Integer(req.getParameter("hos_sofa"));
//				Integer hos_tv = new Integer(req.getParameter("hos_tv"));
//				Integer hos_drink = new Integer(req.getParameter("hos_drink"));
//				Integer hos_aircon = new Integer(req.getParameter("hos_aircon"));
//				Integer hos_refrig = new Integer(req.getParameter("hos_refrig"));
//				Integer hos_wash = new Integer(req.getParameter("hos_wash"));
//				Integer hos_hoter = new Integer(req.getParameter("hos_hoter"));
//				Integer hos_forth = new Integer(req.getParameter("hos_forth"));
//				Integer hos_net = new Integer(req.getParameter("hos_net"));
//				Integer hos_gas = new Integer(req.getParameter("hos_gas"));

				/*************************** 更新合約 **********************/
				ConService conSvc = new ConService();
				ConVO conVOGET = conSvc.getOneCon(con_no);
				String apl_no = conVOGET.getApl_no();
				String tnt_no = conVOGET.getTnt_no();
				Integer con_dep_sta = conVOGET.getCon_dep_sta();				
				
				Con_aplService aplSvcAplService = new Con_aplService();
				Con_aplVO con_aplVO = aplSvcAplService.getOneCon_apl(apl_no);
				Date con_che_date = con_aplVO.getApl_str();
				
				conSvc.updatebeforerent(apl_no, tnt_no, hos_no, con_lld_sign, con_tnt_sign, con_dep_sta, con_che_date,
						con_no);
//				ConVO conVO = conSvc.getOneCon(con_no);
//
				
//
//				HouseService houseSvc = new HouseService();
//				HouseVO houseVO = houseSvc.updateHouseFurniture(hos_table, hos_chair, hos_bed, hos_closet, hos_sofa,
//						hos_tv, hos_drink, hos_aircon, hos_refrig, hos_wash, hos_hoter, hos_forth, hos_net, hos_gas,
//						hos_no);
//
//				HouseVO lldInfo = houseSvc.getLldInfo(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				ConService conService = new ConService();
				List<ConVO> list = conService.lldgetcon(lld_no);

				HttpSession session = req.getSession();
				session.setAttribute("lld_no", lld_no);
				session.setAttribute("list", list);
				String url = "/front-end/contract/lldlistcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("createcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String lld_no = new String(req.getParameter("lld_no"));
				String hos_no = new String(req.getParameter("hos_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getConbyhos(hos_no);

				if (conVO == null) {
					String pattern = "%06d";

					Calendar cal = new GregorianCalendar(2015, Calendar.JANUARY, 1, 0, 0, 0);
					long init = cal.getTimeInMillis();

					SimpleDateFormat formatWithDays = new SimpleDateFormat("yyyy-MM-dd");

					long start = init + (long) (Math.random() * 157766400000L); // for 5 years
					long stop = start + (long) (Math.random() * 63158400000L); // for 2 years
					long apply = start - (long) (Math.random() * 2678400000L + 2678400000L); // minus at least 1 mouth

					String tnt_no = "TNT" + String.format(pattern, (int) (Math.random() * 99 + 1));
					Date apl_str = java.sql.Date.valueOf(formatWithDays.format(start));
					Date apl_end = java.sql.Date.valueOf(formatWithDays.format(stop));
					Date apl_time = java.sql.Date.valueOf(formatWithDays.format(apply));

					Con_aplService aplService = new Con_aplService();
					System.out.println(tnt_no);
					System.out.println(hos_no);
					System.out.println(apl_str);
					System.out.println(apl_end);
					System.out.println(apl_time);
					aplService.addCon_apl(tnt_no, hos_no, apl_str, apl_end, apl_time, 1);

					String apl_no = aplService.getaplbyhos(hos_no).getApl_no();

					conVO = conSvc.addbeforerent(apl_no, tnt_no, hos_no);
					System.out.println(apl_no);

				}

				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
				List<HouseVO> houseVOpicno = houseSvc.getLldHousePic(hos_no);
				HouseVO lldInfo = houseSvc.getLldInfo(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);
				req.setAttribute("houseVOpicno", houseVOpicno);// lld_sign
				req.setAttribute("lldInfo", lldInfo);
				String url = "/front-end/contract/lldcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// 使用byte[]方式(copy by YiJing)
	byte[] getPictureByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 此資料流會把write的位元資料存到一個內建的byte[]
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		in.close();

		return baos.toByteArray(); // toByteArray()可以讓我們取得這個資料流內建的byte[]

	}
}
