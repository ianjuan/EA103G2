package com.rec.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apl.model.Con_aplService;
import com.apl.model.Con_aplVO;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.HouseService;
import com.housemanage.model.HouseVO;
import com.rec.model.RecService;
import com.rec.model.RecVO;
import com.tnt.model.TntService;
import com.tnt.model.TntVO;

public class RecServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String con_no = req.getParameter("con_no");
				String hos_no = req.getParameter("hos_no");
				Integer rec_water = new Integer(req.getParameter("rec_water").trim());
				Integer rec_elec = new Integer(req.getParameter("rec_elec").trim());
				Integer rec_mon = new Integer(req.getParameter("rec_mon").trim());
				Integer rec_sta = new Integer(req.getParameter("rec_sta").trim());
				//add total
				Integer rec_total = 0;

				System.out.println(con_no);
				System.out.println(hos_no);
				System.out.println(rec_water);
				System.out.println(rec_mon);
				System.out.println(rec_sta);

				RecVO recVO = new RecVO();
				recVO.setCon_no(con_no);
				recVO.setHos_no(hos_no);
				recVO.setRec_water(rec_water);
				recVO.setRec_elec(rec_elec);
				recVO.setRec_mon(rec_mon);
				recVO.setRec_sta(rec_sta);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recVO", recVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				RecService recSvc = new RecService();
				recVO = recSvc.addRecFromLld(con_no, hos_no, rec_mon, rec_water, rec_elec, rec_sta, rec_total);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/apl/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getlldrecdetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = req.getParameter("lld_no");
				String con_no = req.getParameter("con_no");
				String hos_no = req.getParameter("hos_no");
				String rec_no = req.getParameter("rec_no");
				System.out.println(lld_no);
				System.out.println(con_no);

				ConService conService = new ConService();
				TntService tntService = new TntService();
				TntVO tntVO = tntService.getOneTntProfile(conService.getOneCon(con_no).getTnt_no());
				
				RecService recService = new RecService();
				RecVO recVO = recService.getOneRec(rec_no);
				
				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(hos_no);
				HouseVO houseeleVO = houseService.getHouseElectfee(hos_no);
				HouseVO housewatVO = houseService.getHouseWaterfee(hos_no);
				
				
				Integer rec_water = recVO.getRec_water();
				Integer rec_elec = recVO.getRec_elec();
				Integer rec_sta = recVO.getRec_sta();
				Integer rec_total = (int) (houseVO.getHos_rentfee() + (houseeleVO.getHos_electfee() * (int) recVO.getRec_elec()) + 
						housewatVO.getHos_waterfee() *(int) (recVO.getRec_water()) + houseVO.getHos_gasfee() + 
						houseVO.getHos_manafee() + houseVO.getHos_netfee() + houseVO.getHos_puwaterfee() +
						houseVO.getHos_puelefee() + houseVO.getHos_parkfee() + houseVO.getHos_gasfee());

				recService.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta, rec_total);
				recVO = recService.getOneRec(rec_no);
				
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("recVO", recVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("con_no", con_no);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("houseeleVO", houseeleVO);
				req.setAttribute("housewatVO", housewatVO);
				String url = "/front-end/rec/lldnowrecdetail.jsp";
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

		if ("getlldrec".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = req.getParameter("lld_no");
				String con_no = req.getParameter("con_no");

				RecService recService = new RecService();

				ConService conService = new ConService();
				String hos_no = conService.getOneCon(con_no).getHos_no();
				System.out.println(hos_no);

				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				Integer rec_mon = cal.get(Calendar.MONTH) + 1;
				System.out.println(rec_mon);
				Integer rec_sta = 0;

				recService.autorec(con_no, hos_no, rec_mon, rec_sta);
				List<RecVO> list = recService.getLddAllByCon(con_no);

				HttpSession session = req.getSession();
				req.setAttribute("lld_no", lld_no);
				session.setAttribute("list", list);
				String url = "/front-end/rec/lldlistrec.jsp";
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
		
		if ("gettntrec".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tnt_no = req.getParameter("tnt_no");
				String con_no = req.getParameter("con_no");
				System.out.println(tnt_no);
				System.out.println(con_no);

				RecService recService = new RecService();

				ConService conService = new ConService();
				String hos_no = conService.getOneCon(con_no).getHos_no();
				System.out.println(hos_no);

				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				Integer rec_mon = cal.get(Calendar.MONTH) + 1;
				System.out.println(rec_mon);
				Integer rec_sta = 0;

				recService.autorec(con_no, hos_no, rec_mon, rec_sta);
				List<RecVO> list = recService.getLddAllByCon(con_no);

				HttpSession session = req.getSession();
				req.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				String url = "/front-end/rec/lldlistrec.jsp";
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

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rec_no = req.getParameter("rec_no");
				String lld_no = req.getParameter("lld_no");
				String con_no = req.getParameter("con_no");

				System.out.println(rec_no);
				System.out.println(lld_no);
				System.out.println(con_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RecService recSvc = new RecService();
				RecVO recVO = recSvc.getOneRec(rec_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("recVO", recVO);
				req.setAttribute("con_no", con_no);
				req.setAttribute("lld_no", lld_no);
				String url = "/front-end/rec/lldfillrec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/listAllCon_apl.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_lld_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rec_no = req.getParameter("rec_no");
				String lld_no = req.getParameter("lld_no");
				String con_no = req.getParameter("con_no");
				Integer rec_water = new Integer(req.getParameter("rec_water"));
				Integer rec_elec = new Integer(req.getParameter("rec_elec"));
				Integer rec_sta = 1;
				
				ConService conService = new ConService();
				String hos_no = conService.getOneCon(con_no).getHos_no();
				String tnt_no = conService.getOneCon(con_no).getTnt_no();
				
				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(hos_no);
				HouseVO houseVOelectfee = houseService.getHouseElectfee(hos_no);
				HouseVO houseVOwaterfee = houseService.getHouseWaterfee(hos_no);
				Integer rec_total = (int) (houseVO.getHos_rentfee() + (houseVOelectfee.getHos_electfee() * (int) rec_elec) + 
						houseVOwaterfee.getHos_waterfee() *(int) (rec_water) + houseVO.getHos_gasfee() + 
						houseVO.getHos_manafee() + houseVO.getHos_netfee() + houseVO.getHos_puwaterfee() +
						houseVO.getHos_puelefee() + houseVO.getHos_parkfee() + houseVO.getHos_gasfee());
				/*************************** 2.開始查詢資料 ****************************************/
				RecService recSvc = new RecService();
				RecVO recVO = recSvc.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta, rec_total);
				
//				TntService tntService = new TntService();
//				tntService.updateTntPocket((int)tntService.getOneTntPocket(tnt_no).getTnt_blance() - rec_total);
//				System.out.println(1);

				RecService recService = new RecService();
				List<RecVO> list = recService.getLddAllByCon(con_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("recVO", recVO);
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("con_no", con_no);
				session.setAttribute("list", list);
				String url = "/front-end/rec/lldlistrec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/listAllCon_apl.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
