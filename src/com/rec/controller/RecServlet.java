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
import com.cash.model.CashService;
import com.cash.model.CashVO;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.HouseService;
import com.housemanage.model.HouseVO;
import com.lld.model.LldService;
import com.lld.model.LldVO;
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
				Integer rec_total = (int) (houseVO.getHos_rentfee()
						+ (houseeleVO.getHos_electfee() * (int) recVO.getRec_elec())
						+ housewatVO.getHos_waterfee() * (int) (recVO.getRec_water()) + houseVO.getHos_gasfee()
						+ houseVO.getHos_manafee() + houseVO.getHos_netfee() + houseVO.getHos_puwaterfee()
						+ houseVO.getHos_puelefee() + houseVO.getHos_parkfee() + houseVO.getHos_gasfee());
				System.out.println(rec_total);

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

		if ("gettntrecdetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tnt_no = req.getParameter("tnt_no");
				String con_no = req.getParameter("con_no");
				String hos_no = req.getParameter("hos_no");
				String rec_no = req.getParameter("rec_no");
				System.out.println(con_no);
				System.out.println(tnt_no);
				System.out.println(hos_no);
				System.out.println(rec_no);

				TntService tntService = new TntService();
				TntVO tntVO = tntService.getOneTntProfile(tnt_no);

				RecService recService = new RecService();
				RecVO recVO = recService.getOneRec(rec_no);

				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(hos_no);
				HouseVO houseeleVO = houseService.getHouseElectfee(hos_no);
				HouseVO housewatVO = houseService.getHouseWaterfee(hos_no);

				LldService lldService = new LldService();
				LldVO lldVO = lldService.getOneLldProfile(houseService.getHouseInfo(hos_no).getLld_no());

				Integer rec_water = recVO.getRec_water();
				Integer rec_elec = recVO.getRec_elec();
				Integer rec_sta = recVO.getRec_sta();
				Integer rec_total = (int) (houseVO.getHos_rentfee()
						+ (houseeleVO.getHos_electfee() * (int) recVO.getRec_elec())
						+ housewatVO.getHos_waterfee() * (int) (recVO.getRec_water()) + houseVO.getHos_gasfee()
						+ houseVO.getHos_manafee() + houseVO.getHos_netfee() + houseVO.getHos_puwaterfee()
						+ houseVO.getHos_puelefee() + houseVO.getHos_parkfee() + houseVO.getHos_gasfee());

				recService.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta, rec_total);
				recVO = recService.getOneRec(rec_no);

				req.setAttribute("tnt_no", tnt_no);
				req.setAttribute("lldVO", lldVO);
				req.setAttribute("recVO", recVO);
				req.setAttribute("tntVO", tntVO);
				req.setAttribute("con_no", con_no);
				req.setAttribute("houseVO", houseVO);
				req.setAttribute("houseeleVO", houseeleVO);
				req.setAttribute("housewatVO", housewatVO);
				String url = "/front-end/rec/tntnowrecdetail.jsp";
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
				System.out.println(lld_no);
				System.out.println(con_no);
				
				ConService conSvc = new ConService();
				System.out.println(conSvc.getOneCon(con_no).getCon_comchkdate());
				

				RecService recService = new RecService();

				List<RecVO> reclist = recService.getLddAllByCon(con_no);
				
				if (reclist == null && reclist.size() == 0) {
					ConService conService = new ConService();
					String hos_no = conService.getOneCon(con_no).getHos_no();
					System.out.println(hos_no);

					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					Integer rec_mon = cal.get(Calendar.MONTH) + 1;
					System.out.println(rec_mon);
					Integer rec_sta = 0;

					recService.autorec(con_no, hos_no, rec_mon, rec_sta);
				}
				
				System.out.println(reclist.size());
				System.out.println(123);

				HttpSession session = req.getSession();
				session.setAttribute("lld_no", lld_no);
				req.setAttribute("con_no", con_no);
				session.setAttribute("reclist", reclist);
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

				List<RecVO> reclist = recService.getLddAllByCon(con_no);
				System.out.println(reclist);
				
				if (reclist == null && reclist.size() == 0) {
					ConService conService = new ConService();
					String hos_no = conService.getOneCon(con_no).getHos_no();
					System.out.println(hos_no);

					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					Integer rec_mon = cal.get(Calendar.MONTH) + 1;
					System.out.println(rec_mon);
					Integer rec_sta = 0;

					recService.autorec(con_no, hos_no, rec_mon, rec_sta);
				}

				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				req.setAttribute("con_no", con_no);
				session.setAttribute("reclist", reclist);
				String url = "/front-end/rec/tntlistrec.jsp";
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
		
		if ("tntpayrec".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String tnt_no = req.getParameter("tnt_no");
				String con_no = req.getParameter("con_no");
				String rec_no = req.getParameter("rec_no");
				System.out.println("test");
				System.out.println(tnt_no);
				System.out.println(con_no);
				System.out.println(rec_no);

				RecService recService = new RecService();
				Integer rec_total = recService.getOneRec(rec_no).getRec_total();
				
				//房客繳交定期費用
				TntService tntService = new TntService();
				Integer tnt_balance = tntService.getOneTntPocket(tnt_no).getTnt_balance() - rec_total;
				tntService.updateTntPocket(tnt_no, tnt_balance);
				
				RecVO recVO = recService.getOneRec(rec_no);
				String hos_no = recVO.getHos_no();
				System.out.println(hos_no);
				Integer rec_mon = recVO.getRec_mon();
				System.out.println(rec_mon);
				Integer rec_water = recVO.getRec_water();
				System.out.println(rec_water);
				Integer rec_elec = recVO.getRec_elec();
				System.out.println(rec_elec);
				Integer rec_sta = 2;
				
				recService.updateRec(con_no, hos_no, rec_mon, rec_water, rec_elec, rec_no, rec_sta, rec_total);
				
				CashService cashSvc = new CashService();
				java.sql.Date cash_date = new java.sql.Date(new java.util.Date().getTime());
				String mem_no = tnt_no;
				String cash_inout = CashVO.cashOut;
				String cash_type = CashVO.tntOut_RecBill;
				Integer cash_amount = -rec_total;
				Integer cash_status = 1;
				cashSvc.addCash(cash_date, mem_no, cash_inout, cash_type, cash_amount, con_no, rec_no, cash_status);
				
				//房東收到錢錢
				ConService conService = new ConService();
				LldService lldService = new LldService();
				HouseService houseService = new HouseService();
				String lld_no = houseService.getHouseInfo(conService.getOneCon(con_no).getHos_no()).getLld_no();

				Integer lld_balance = lldService.getOneLldPocket(lld_no).getLld_balance() + rec_total;
				lldService.updateLldPocket(lld_no, lld_balance);
				
				mem_no = lld_no;
				cash_inout = CashVO.cashIn;
				cash_type = CashVO.lldIn_RecBill;
				cash_amount = rec_total;
				
				cashSvc.addCash(cash_date, mem_no, cash_inout, cash_type, cash_amount, con_no, cash_status);
				
				List<RecVO> reclist = recService.getLddAllByCon(con_no);

				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				session.setAttribute("reclist", reclist);
				req.setAttribute("con_no", con_no);
				String url = "/front-end/rec/tntlistrec.jsp";
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
				HttpSession session = req.getSession();
				req.setAttribute("recVO", recVO);
				req.setAttribute("con_no", con_no);
				session.setAttribute("lld_no", lld_no);
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
				Integer rec_total = (int) (houseVO.getHos_rentfee()
						+ (houseVOelectfee.getHos_electfee() * (int) rec_elec)
						+ houseVOwaterfee.getHos_waterfee() * (int) (rec_water) + houseVO.getHos_gasfee()
						+ houseVO.getHos_manafee() + houseVO.getHos_netfee() + houseVO.getHos_puwaterfee()
						+ houseVO.getHos_puelefee() + houseVO.getHos_parkfee() + houseVO.getHos_gasfee());
				/*************************** 2.開始查詢資料 ****************************************/
				RecService recSvc = new RecService();
				RecVO recVO = recSvc.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta, rec_total);

//				TntService tntService = new TntService();
//				tntService.updateTntPocket((int)tntService.getOneTntPocket(tnt_no).getTnt_blance() - rec_total);
//				System.out.println(1);

				RecService recService = new RecService();
				List<RecVO> reclist = recService.getLddAllByCon(con_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("recVO", recVO);
				session.setAttribute("lld_no", lld_no);
				req.setAttribute("con_no", con_no);
				session.setAttribute("reclist", reclist);
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
