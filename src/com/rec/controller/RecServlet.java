package com.rec.controller;

import java.io.IOException;
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
import com.rec.model.RecService;
import com.rec.model.RecVO;

public class RecServlet extends HttpServlet{
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
				System.out.println(1);
				String con_no = req.getParameter("con_no");
				String hos_no = req.getParameter("hos_no");
				Integer rec_water = new Integer(req.getParameter("rec_water").trim());
				Integer rec_elec = new Integer(req.getParameter("rec_elec").trim());
				Integer rec_mon = new Integer(req.getParameter("rec_mon").trim());
				Integer rec_sta = new Integer(req.getParameter("rec_sta").trim());

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
				recVO = recSvc.addRecFromLld(con_no, hos_no, rec_mon, rec_water, rec_elec, rec_sta);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/apl/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
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

				RecService recService = new RecService();
				List<RecVO> list = recService.getLddAllByCon(con_no);

				req.setAttribute("lld_no", lld_no);
				req.setAttribute("list", list);
				String url = "/front-end/rec/lldlistrec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

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
				Integer rec_water= new Integer(req.getParameter("rec_water"));
				Integer rec_elec= new Integer(req.getParameter("rec_elec"));
				Integer rec_sta = 1;
				
				System.out.println(rec_no);
				System.out.println(lld_no);
				System.out.println(con_no);
				System.out.println(rec_water);
				System.out.println(rec_elec);

				/*************************** 2.開始查詢資料 ****************************************/
				RecService recSvc = new RecService();
				RecVO recVO = recSvc.updateRecFromLld(rec_water, rec_elec, rec_no, rec_sta);
				
				RecService recService = new RecService();
				List<RecVO> list = recService.getLddAllByCon(con_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("recVO", recVO); 
				req.setAttribute("lld_no", lld_no);
				req.setAttribute("list", list);
				String url = "/front-end/rec/lldlistrec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/listAllCon_apl.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
