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
import java.util.Timer;

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
import com.housedet.model.HosDetVO;
import com.housemanage.model.*;
import com.lld.model.LldService;
import com.lld.model.LldVO;
import com.notify.controller.NotifyServlet;
import com.rec.model.RecService;
import com.rec.model.RecVO;
//import com.sun.javafx.font.directwrite.RECT;
import com.tnt.model.TntService;
import com.tnt.model.TntVO;

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

		// the tenant final version
		if ("checktntcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String hos_no = new String(req.getParameter("hos_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				Con_aplService con_aplService = new Con_aplService();
				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				System.out.println(apl_no);

				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				ConVO conVO = conSvc.getOneCon(con_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);

				LldService lldService = new LldService();
				String lld_no = houseSvc.getHouseInfo(hos_no).getLld_no();
				LldVO lldVO = lldService.getOneLldProfile(lld_no);

				TntService tntSvc = new TntService();
				String tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);

				String url = "/front-end/contract/tntfinalcontract.jsp";
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

		// the landlord final version
		if ("checklldcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String lld_no = new String(req.getParameter("lld_no"));
				String hos_no = new String(req.getParameter("hos_no"));
				String con_no = new String(req.getParameter("con_no"));
				System.out.println(lld_no);
				System.out.println(hos_no);
				System.out.println(con_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				
				Con_aplService con_aplService = new Con_aplService();
				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				
				System.out.println(hos_no);
				System.out.println(con_no);
				System.out.println(apl_no);

				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				ConVO conVO = conSvc.getOneCon(con_no);
				
				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);

				lld_no = houseSvc.getHouseInfo(hos_no).getLld_no();
				LldService lldService = new LldService();
				LldVO lldVO = lldService.getOneLldProfile(lld_no);

				TntService tntSvc = new TntService();
				String tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);
				String url = "/front-end/contract/lldfinalcontract.jsp";
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

		if ("getlldcontract".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = new String(req.getParameter("lld_no"));
				System.out.println(lld_no);
				System.out.println("test");

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
				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");
				System.out.println(tnt_no);

				ConService conService = new ConService();
				List<ConVO> list = conService.tntgetcon(tnt_no);

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
				System.out.println(conVO.getCon_lld_sign());

				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
				List<HouseVO> houseVOpicno = houseSvc.getLldHousePic(hos_no);
				HouseVO lldInfo = houseSvc.getLldInfo(lld_no);

				String tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				TntService tntService = new TntService();
				TntVO tntVO = tntService.getOneTntProfile(tnt_no);

				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				Con_aplService con_aplService = new Con_aplService();
				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("con_aplVO", con_aplVO);
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

		if ("getonetntcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				String hos_no = new String(req.getParameter("hos_no"));
				System.out.println(con_no);
				System.out.println(tnt_no);
				System.out.println(hos_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);

				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
				List<HouseVO> houseVOpicno = houseSvc.getLldHousePic(hos_no);

				String lld_no = houseSvc.getHouseInfo((conSvc.getOneCon(con_no).getHos_no())).getLld_no();
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);

				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				Con_aplService con_aplService = new Con_aplService();
				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("tnt_no", tnt_no);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);
				req.setAttribute("houseVOpicno", houseVOpicno);// lld_sign
				String url = "/front-end/contract/tntcontract.jsp";
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

		if ("gettntfinalcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				String hos_no = new String(req.getParameter("hos_no"));
				System.out.println(con_no);
				System.out.println(tnt_no);
				System.out.println(hos_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				Con_aplService con_aplService = new Con_aplService();
				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				System.out.println(apl_no);

				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				ConVO conVO = conSvc.getOneCon(con_no);

				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);

				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
				List<HouseVO> houseVOpicno = houseSvc.getLldHousePic(hos_no);

				String lld_no = houseSvc.getHouseInfo(hos_no).getLld_no();
				LldService lldService = new LldService();
				LldVO lldVO = lldService.getOneLldProfile(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("tnt_no", tnt_no);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);
				req.setAttribute("houseVOpicno", houseVOpicno);// lld_sign
				String url = "/front-end/contract/tntfinalcontract.jsp";
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

				/*************************** 修正房東資訊 ****************************************/
				String lld_mobile = new String(req.getParameter("lld_mobile"));

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

				lldSvc.updateLldProfile(lld_no, lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex,
						lld_mobile, lld_city, lld_dist, lld_add, lld_pic, lld_status);

				/*************************** 更新房屋家具 **********************/
				Integer hos_table = new Integer(req.getParameter("hos_table"));
				Integer hos_chair = new Integer(req.getParameter("hos_chair"));
				Integer hos_bed = new Integer(req.getParameter("hos_bed"));
				Integer hos_closet = new Integer(req.getParameter("hos_closet"));
				Integer hos_sofa = new Integer(req.getParameter("hos_sofa"));
				Integer hos_tv = new Integer(req.getParameter("hos_tv"));
				Integer hos_drink = new Integer(req.getParameter("hos_drink"));
				Integer hos_aircon = new Integer(req.getParameter("hos_aircon"));
				Integer hos_refrig = new Integer(req.getParameter("hos_refrig"));
				Integer hos_wash = new Integer(req.getParameter("hos_wash"));
				Integer hos_hoter = new Integer(req.getParameter("hos_hoter"));
				System.out.println(hos_table);
				System.out.println(hos_bed);
				System.out.println(hos_chair);

				HouseService houseSvc = new HouseService();
				houseSvc.updateHouseFurniture(hos_table, hos_chair, hos_bed, hos_closet, hos_sofa, hos_tv, hos_drink,
						hos_aircon, hos_refrig, hos_wash, hos_hoter, hos_no);

				/*************************** 房東簽名 ****************************************/
				String con_lld_sign = req.getParameter("con_lld_sign");
				System.out.println(con_lld_sign);

				String con_tnt_sign = null;
				/*************************** 更新合約 **********************/
				ConService conSvc = new ConService();
				ConVO conVOGET = conSvc.getOneCon(con_no);
				String apl_no = conVOGET.getApl_no();
				String tnt_no = conVOGET.getTnt_no();
				Integer con_dep_sta = conVOGET.getCon_dep_sta();

				Con_aplService aplSvcAplService = new Con_aplService();
				Con_aplVO con_aplVO1 = aplSvcAplService.getOneCon_apl(apl_no);
				Date con_che_date = con_aplVO1.getApl_str();
//				HouseService hosSvc = new HouseService();
//				Integer hos_dep = hosSvc.getHouseInfo(hos_no).getHos_rentfee();
				Integer hos_dep = 0;
				Integer con_sta = 1;

				conSvc.updatebeforerent(apl_no, tnt_no, hos_no, con_lld_sign, con_tnt_sign, con_dep_sta, hos_dep,
						con_sta, con_che_date, con_no);

				/****************************
				 * 準備跳轉房東閱覽合約
				 ****************************************/

				Con_aplService con_aplService = new Con_aplService();

				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				ConVO conVO = conSvc.getOneCon(con_no);

				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);

				lld_no = houseSvc.getHouseInfo(hos_no).getLld_no();
				LldService lldService = new LldService();
				lldVO = lldService.getOneLldProfile(lld_no);

				TntService tntSvc = new TntService();
				tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);

				List<ConVO> list = conSvc.lldgetcon(lld_no);

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);

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

		if ("afterpreviewlldcontract".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = (String) req.getParameter("lld_no");
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

		if ("updateonetntcontract".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				String hos_no = new String(req.getParameter("hos_no"));
				/*************************** 修正房東資訊 ****************************************/
				String tnt_mobile = new String(req.getParameter("tnt_mobile"));

				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);
				String tnt_email = tntVO.getTnt_email();
				String tnt_acc = tntVO.getTnt_acc();
				String tnt_pwd = tntVO.getTnt_pwd();
				String tnt_id = tntVO.getTnt_id();
				String tnt_name = tntVO.getTnt_name();
				Date tnt_birth = tntVO.getTnt_birth();
				Boolean tnt_sex = tntVO.getTnt_sex();
				String tnt_city = tntVO.getTnt_city();
				String tnt_dist = tntVO.getTnt_dist();
				String tnt_add = tntVO.getTnt_add();
				byte[] tnt_pic = tntVO.getTnt_pic();
				Integer tnt_status = tntVO.getTnt_status();

				tntSvc.updateTntProfile(tnt_no, tnt_email, tnt_acc, tnt_pwd, tnt_id, tnt_name, tnt_birth, tnt_sex,
						tnt_mobile, tnt_city, tnt_dist, tnt_add, tnt_status);
				/*************************** 房東簽名 ****************************************/
				ConService conSvc = new ConService();
				String con_lld_sign = conSvc.getOneCon(con_no).getCon_lld_sign();

				String con_tnt_sign = req.getParameter("con_tnt_sign");
				/*************************** 更新合約 **********************/
				ConVO conVOGET = conSvc.getOneCon(con_no);
				String apl_no = conVOGET.getApl_no();

				Con_aplService aplSvc = new Con_aplService();
				Con_aplVO con_aplVO = aplSvc.getOneCon_apl(apl_no);
				Date con_che_date = con_aplVO.getApl_str();

				/*************************** 更新押金 **********************/
				HouseService hosSvc = new HouseService();
				Integer hos_dep = (hosSvc.getHouseInfo(hos_no).getHos_rentfee()) * 2;
				Integer con_sta = 2;
				Integer con_dep_sta = 1;

				conSvc.updatebeforerent(apl_no, tnt_no, hos_no, con_lld_sign, con_tnt_sign, con_dep_sta, hos_dep,
						con_sta, con_che_date, con_no);
				/*************************** 扣房客押金 **********************/
				Integer tnt_blance = tntSvc.getOneTntPocket(tnt_no).getTnt_balance() - hos_dep;
				tntSvc.updateTntPocket(tnt_no, tnt_blance);
				/*************************** 加房東錢錢 **********************/
				String lld_no = hosSvc.getHouseInfo(hos_no).getLld_no();
				System.out.println(lld_no);
				LldService lldService = new LldService();
				Integer lld_blance = lldService.getOneLldPocket(lld_no).getLld_balance() + hos_dep;
				lldService.updateLldPocket(lld_no, lld_blance);
				/*************************** 改租屋狀態 **********************/
				con_sta = 3;

				Timer timer = new Timer();
				timer.schedule(new Schedule(con_no, con_sta), 3000);
				/*************************** 每月帳單 **********************/
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.util.Date());
				Integer rec_mon = cal.get(Calendar.MONTH) + 1;
				Integer rec_sta = 0;

				timer.schedule(new RecSchedule(con_no, hos_no, rec_mon, rec_sta), 5000, 100000);
				/*************************** 準備退房 **********************/
				Date apl_end = aplSvc.getOneCon_apl(apl_no).getApl_end();
				long apl_end_long = apl_end.getTime();
				long con_chkdatelong = apl_end_long - (long) (Math.random() * 2678400000L); // 一個月前驗房
				SimpleDateFormat formatWithDays = new SimpleDateFormat("yyyy-MM-dd");
				Date con_chkdate = java.sql.Date.valueOf(formatWithDays.format(con_chkdatelong));

				con_sta = 4; // 準備驗房
				Integer con_comchkdate = 1; // 房東確認驗房時間
				Integer con_chk_sta = 0;
				Integer con_chr_fee = 0;
				String con_chr_itm = null;
				String con_chr_itm_name = null;
				Integer con_is_chr = 0;
				Timer checkouttimer = new Timer();
				checkouttimer.schedule(new CheckoutSchedule(hos_dep, con_dep_sta, con_chkdate, con_comchkdate,
						con_chk_sta, con_chr_fee, con_chr_itm, con_chr_itm_name, con_no, con_sta, con_is_chr), 100000);
				/***************************
				 * 準備跳轉房東閱覽合約
				 ****************************************/

				Con_aplService con_aplService = new Con_aplService();

				con_aplVO = con_aplService.getOneCon_apl(apl_no);

				ConVO conVO = conSvc.getOneCon(con_no);

				HouseService houseSvc = new HouseService();

				HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
				HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
				HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);

				lld_no = houseSvc.getHouseInfo(hos_no).getLld_no();
				lldService = new LldService();
				LldVO lldVO = lldService.getOneLldProfile(lld_no);

				tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				tntVO = tntSvc.getOneTntProfile(tnt_no);

				ConService conService = new ConService();
				List<ConVO> list = conService.tntgetcon(tnt_no);

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("houseVOwaterfee", houseVOwaterfee);
				req.setAttribute("houseVOelectfee", houseVOelectfee);

				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);

				String url = "/front-end/contract/tntlistcontract.jsp";
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

		if ("afterpreviewtntcontract".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tnt_no = (String) req.getParameter("tnt_no");
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

		if ("lldbeforecheckroom".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String lld_no = new String(req.getParameter("lld_no"));
				String con_no = new String(req.getParameter("con_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);
				String hos_no = conVO.getHos_no();

				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(hos_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("conVO", conVO);
				req.setAttribute("houseVO", houseVO);
				String url = "/front-end/contract/lldcheckroom.jsp";
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

		if ("lldcheckroom".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String lld_no = new String(req.getParameter("lld_no"));
				System.out.println(lld_no);
				System.out.println(con_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);
				Date con_chkdate = conVO.getCon_che_date();
				System.out.println(con_chkdate);
				Integer con_comchkdate = 2;
				Integer con_chk_sta = conVO.getCon_chk_sta();
				System.out.println(con_chk_sta);
				Integer con_is_chr = conVO.getCon_is_chr();
				Integer hos_dep = conVO.getHos_dep();
				System.out.println(hos_dep);
				if (hos_dep == null) {
					HouseService houseService = new HouseService();
					hos_dep = houseService.getHouseInfo(conVO.getHos_no()).getHos_rentfee();
				}
				Integer con_dep_sta = conVO.getCon_dep_sta();
				Integer con_sta = 5;

				Integer con_chr_fee = getReqNum(req, "con_chr_fee");

				String con_chr_itm_name = req.getParameter("con_chr_itm_name");
				String con_chr_itm = req.getParameter("con_chr_itm");
				if (con_chr_itm_name == null) {
					con_chr_itm_name = "無";
					con_chr_itm = "無";
				}

				conSvc.updatebeforecheckout(hos_dep, con_dep_sta, con_chkdate, con_comchkdate, con_chk_sta, con_chr_fee,
						con_chr_itm, con_chr_itm_name, con_is_chr, con_sta, con_no);

				List<ConVO> list = conSvc.lldgetcon(lld_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("lld_no", lld_no);
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

		if ("tntcheckout".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				System.out.println(tnt_no);
				System.out.println(con_no);

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(con_no);
				System.out.println(conVO.getCon_chr_itm_name());

				RecService recService = new RecService();
				List<RecVO> list = recService.getAllUpaidByCon(con_no);

				Integer checktotal = conVO.getCon_chr_fee();
				for (RecVO recVO : list) {
					checktotal += recVO.getRec_total();
				}

				String checkouttotal = Integer.toString(checktotal);

				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(conVO.getHos_no());

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("tnt_no", tnt_no);
				req.setAttribute("conVO", conVO);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("list", list);
				req.setAttribute("checkouttotal", checkouttotal);
				String url = "/front-end/contract/tntcheckroom.jsp";
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
		
		if ("tntcheckroom".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				System.out.println(tnt_no);
				System.out.println(con_no);
				System.out.println("good");

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();

				RecService recService = new RecService();
				List<RecVO> reclist = recService.getAllUpaidByCon(con_no);
				for (RecVO recVO : reclist) {
					Integer rec_water = recVO.getRec_water();
					Integer rec_elec = recVO.getRec_elec();
					String rec_no = recVO.getRec_no();
					Integer rec_total = recVO.getRec_total();
					Integer rec_sta = 2;
					recService.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta, rec_total);
				}

				HouseService houseService = new HouseService();
				String hos_status = "已下架";
				String hos_no = conSvc.getOneCon(con_no).getHos_no();
				houseService.updateStatus(hos_status, hos_no);
				
				Integer con_sta = 6;
				conSvc.updatesta(con_sta, con_no);
				List<ConVO> list = conSvc.tntgetcon(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				String url = "/front-end/contract/tntlistcontract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/

		}
		
		if ("tntcheckback".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String con_no = new String(req.getParameter("con_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));
				System.out.println(tnt_no);
				System.out.println(con_no);
				System.out.println("back");

				/*************************** 2.開始查詢資料 ****************************************/
				ConService conSvc = new ConService();		
				List<ConVO> list = conSvc.tntgetcon(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				String url = "/front-end/contract/tntlistcontract.jsp";
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

				String con_no = conSvc.getConbyhos(hos_no).getCon_no();

				String tnt_no = conSvc.getOneCon(con_no).getTnt_no();
				TntService tntService = new TntService();
				TntVO tntVO = tntService.getOneTntProfile(tnt_no);

				String apl_no = conSvc.getOneCon(con_no).getApl_no();
				Con_aplService con_aplService = new Con_aplService();
				Con_aplVO con_aplVO = con_aplService.getOneCon_apl(apl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("conVO", conVO);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("con_aplVO", con_aplVO);
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

	public Integer getReqNum(HttpServletRequest req, String reqKey) {
		String reqValue = req.getParameter(reqKey);
		Integer result;

		if (reqValue == null || (reqValue.trim()).length() == 0) {
			result = 0;
		} else {
			result = new Integer(reqValue);
		}
		return result;
	}

}
