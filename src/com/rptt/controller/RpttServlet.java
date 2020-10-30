package com.rptt.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rptt.model.*;

public class RpttServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// -----------------
		if ("get_want_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number").trim();
				if (Number == null || (Number.trim()).length() == 0) {
					errorMsgs.add("請正確輸入欲搜尋編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpttService rpttSvc = new RpttService();
				List<RpttVO> rpttVO = rpttSvc.getRptt(Number);
				if (rpttVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttVO", rpttVO);
				String url = "/back-end/rptt/first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("get_want_all_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number");
				System.out.println(Number);
				if (Number == null || (Number.trim()).length() == 0) {
					errorMsgs.add("請正確輸入欲搜尋編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpttService rpttSvc = new RpttService();
				System.out.println("有來到Service的方法");
				List<RpttVO> rpttVO = rpttSvc.getRptt(Number);
				if (rpttVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttVO", rpttVO);
				String url = "/back-end/rptt/second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_no = req.getParameter("tnt_no");
				String tnt_no_Reg = "^TNT[0-9]{6}$";
				if (tnt_no == null || tnt_no.trim().length() == 0) {
					errorMsgs.add("房客編號: 請勿空白");
				} else if (!tnt_no.trim().matches(tnt_no_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房客編號: 只能是TNT後面接上6位數 ");
				}

				String lld_no = req.getParameter("lld_no");
				String lld_no_Reg = "^LLD[0-9]{6}$";
				if (lld_no == null || lld_no.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if (!lld_no.trim().matches(lld_no_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是LLD後面接上6位數");
				}

				String rptt_content = req.getParameter("rptt_content").trim();
				System.out.println(rptt_content);
				if (rptt_content == null || rptt_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RpttVO rpttVO = new RpttVO();
				rpttVO.setTnt_no(tnt_no);
				rpttVO.setLld_no(lld_no);
				rpttVO.setRptt_content(rptt_content);

				/*************************** 2.開始新增資料 ***************************************/
				RpttService rpttSvc = new RpttService();
				rpttVO = rpttSvc.addRptt(tnt_no, lld_no, rptt_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptt/listAllRptt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rptt/rptt_form.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RpttService rpttSvc = new RpttService();
				RpttVO rpttVO = rpttSvc.getOneRptt(rptt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rpttVO", rpttVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rptt/update_rptt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");
				System.out.println(rptt_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rptt_status = new Integer(req.getParameter("rptt_status").trim());
				System.out.println(rptt_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttVO rpttVO1 = new RpttVO();
//				rpttVO1.setRptt_no(rptt_no);
//				rpttVO1.setEmp_no(emp_no);
//				rpttVO1.setRptt_status(rptt_status);
//				System.out.println("裝入完畢");

				RpttService rpttSvc = new RpttService();
				rpttVO1 = rpttSvc.updateEmp(rptt_no, emp_no, rptt_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rptt/main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("assign_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑assign_employee");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");
				System.out.println(rptt_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RpttService rpttSvc = new RpttService();
					List<RpttVO> rpttVO = rpttSvc.getRptt("0");
					req.setAttribute("rpttVO", rpttVO);
					String url = "/back-end/rptt/first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rptt_note = req.getParameter("rptt_note");
				System.out.println(rptt_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttVO rpttVO1 = new RpttVO();
//				rpttVO1.setRptt_no(rptt_no);
//				rpttVO1.setEmp_no(emp_no);
//				rpttVO1.setRptt_note(rptt_note);
//				System.out.println("裝入完畢");

				RpttService rpttSvc = new RpttService();
				rpttVO1 = rpttSvc.assignEmp(rptt_no, emp_no, rptt_note);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptt/main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("save_note".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑save_note");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");
				System.out.println(rptt_no);
				String rptt_note = req.getParameter("rptt_note");
				System.out.println(rptt_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttVO rpttVO1 = new RpttVO();
//				rpttVO1.setRptt_no(rptt_no);
//				rpttVO1.setRptt_note(rptt_note);
//				System.out.println("裝入完畢");

				RpttService rpttSvc = new RpttService();
				rpttVO1 = rpttSvc.saveNote(rptt_no, rptt_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptt/main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("fail".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");
				System.out.println(rptt_no);
				Integer rptt_result = 2;
				System.out.println(rptt_result);
				String rptt_note = req.getParameter("rptt_note");
				System.out.println(rptt_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttVO rpttVO1 = new RpttVO();
//				rpttVO1.setRptt_no(rptt_no);
//				rpttVO1.setRptt_result(rptt_result);
//				rpttVO1.setRptt_note(rptt_note);
//				System.out.println("裝入完畢");

				RpttService rpttSvc = new RpttService();
				rpttVO1 = rpttSvc.fail(rptt_no, rptt_result, rptt_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptt/main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptt_no = req.getParameter("rptt_no");
				System.out.println(rptt_no);
				Integer rptt_result = 1;
				System.out.println(rptt_result);
				String rptt_note = req.getParameter("rptt_note");
				System.out.println(rptt_note);
				String tnt_no = req.getParameter("tnt_no");
				System.out.println(tnt_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttVO rpttVO1 = new RpttVO();
//				rpttVO1.setRptt_no(rptt_no);
//				rpttVO1.setRptt_result(rptt_result);
//				rpttVO1.setRptt_note(rptt_note);
//				System.out.println("裝入完畢");

				RpttService rpttSvc = new RpttService();
				rpttVO1 = rpttSvc.fail(rptt_no, rptt_result, rptt_note);
				System.out.println("result有更新了");
				TntService tntSvc = new TntService();
				TntVO tntVO1 = tntSvc.getEmail(tnt_no);
				String TntEmail = "xiyuan345@gmail.com";
				String TntName = tntVO1.getTnt_name();
				String TntAcc = tntVO1.getTnt_acc();
				String TntDissaprove = tntVO1.getTnt_id_disapprove();
				Integer type = 1;
				String EmailLink = "http://localhost:8081/EA103G2/front-end/index/index.jsp";
				MailService mailservice = new MailService();
				System.out.println("準備藥檢舉成功了");
				mailservice.sendMail(TntEmail, TntName, TntAcc, TntDissaprove, EmailLink, type);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				TntVO tntVO = new TntVO();
				TntService tntSvc1 = new TntService();
				tntVO = tntSvc1.getOneTntProfile(tnt_no);
				req.setAttribute("TntVO", tntVO);
				String url = "/back-end/member/tenant_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String rptt_no = req.getParameter("rptt_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RpttService rpttSvc = new RpttService();
				rpttSvc.deleteRptt(rptt_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptt/listAllRptt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

// ------------------------------以下是驗證的control---------------------------------------------------------------------------------------------------------
		if ("passVrf".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String tnt_no = req.getParameter("tnt_no");
				System.out.println(tnt_no);
				Integer tnt_id_result = 1;
				System.out.println(tnt_id_result);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);

				/*************************** 2.開始查詢資料 ****************************************/
				TntVO tntVO1 = new TntVO();
//				tntVO1.setTnt_no(tnt_no);
//				tntVO1.setTnt_id_result(tnt_id_result);
//				tntVO1.setEmp_no(emp_no);
//				System.out.println("裝入完畢PASS");

				TntService tntSvc = new TntService();

				tntVO1 = tntSvc.passVrf(tnt_no, tnt_id_result, emp_no);
				System.out.println("tntpass有更新了");

				TntVO tntVO2 = tntSvc.getEmail(tnt_no);
				String TntEmail = "xiyuan345@gmail.com";
				String TntName = tntVO2.getTnt_name();
				String TntAcc = tntVO2.getTnt_acc();
				String TntDissaprove = tntVO2.getTnt_id_disapprove();
				String EmailLink = "http://localhost:8081/EA103G2/front-end/index/index.jsp";
				Integer type = 2;
				MailService mailservice = new MailService();
				mailservice.sendMail(TntEmail, TntName, TntAcc, TntDissaprove, EmailLink, type);
				System.out.println("驗證成功信寄出去了");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/vrf/vrf_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功Pass");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("failVrf".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				System.out.println("又進來這");
				String tnt_no = req.getParameter("tnt_no");
				Integer tnt_id_result = 2;
				String emp_no = req.getParameter("emp_no");
				String tnt_id_disapprove = req.getParameter("tnt_id_disapprove");
				Integer tnt_id_isupload = 0;

				/*************************** 2.開始查詢資料 ****************************************/
				TntVO tntVO1 = new TntVO();
//				tntVO1.setTnt_no(tnt_no);
//				tntVO1.setTnt_id_result(tnt_id_result);
//				tntVO1.setEmp_no(emp_no);
//				tntVO1.setTnt_id_disapprove(tnt_id_disapprove);
//				tntVO1.setTnt_id_isupload(tnt_id_isupload);
//				System.out.println("裝入完畢FAIL");

				TntService tntSvc = new TntService();
				tntVO1 = tntSvc.failVrf(tnt_no, tnt_id_result, emp_no, tnt_id_disapprove, tnt_id_isupload);
				System.out.println("tntfail有更新了");

				TntVO tntVO2 = tntSvc.getEmail(tnt_no);
				String TntEmail = "xiyuan345@gmail.com";
				String TntName = tntVO2.getTnt_name();
				String TntAcc = tntVO2.getTnt_acc();
				String TntDissaprove = tntVO2.getTnt_id_disapprove();
				Integer type = 3;
				String EmailLink = "http://localhost:8081/EA103G2/front-end/index/index.jsp";
				MailService mailservice = new MailService();
				mailservice.sendMail(TntEmail, TntName, TntAcc, TntDissaprove, EmailLink, type);
				System.out.println("驗證失敗信寄出去了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/vrf/vrf_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功fail");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_want_vrf_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number").trim();
				if (Number == null || (Number.trim()).length() == 0) {
					errorMsgs.add("請正確輸入欲搜尋編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TntService tntSvc = new TntService();
				List<TntVO> tntVO = tntSvc.getTnt(Number);
				if (tntVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_main_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("TntVO", tntVO);
				String url = "/back-end/vrf/vrf_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("搜尋不到或是未填寫要查詢的編號! 麻煩重新輸入一次");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_main_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("get_want_vrf".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number").trim();
				System.out.println("來到get_want_vrf:" + Number);
				if (Number == null || (Number.trim()).length() == 0) {
					System.out.println("來到get_want_vrf1" + Number);
					errorMsgs.add("未填寫要查詢的編號! 麻煩重新輸入一次");
					String url = "/back-end/vrf/vrf_second_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_second_page.jsp");
					failureView.forward(req, res);
					System.out.println("sec編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TntService tntSvc = new TntService();
				List<TntVO> tntVO = tntSvc.getTnt(Number);
				if (tntVO.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/vrf/vrf_second_search_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("TntVO", tntVO);
				String url = "/back-end/vrf/vrf_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("vrf_second無法取得");
				errorMsgs.add("搜尋不到或是未填寫要查詢的編號! 麻煩重新輸入一次");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_second_page.jsp");
				failureView.forward(req, res);
			}

		}

//-----------------以下是member的操作---------------------------------------------------------------------------------------------------------------------

		if ("changeAuth".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer tnt_auth_chat;
				Integer tnt_auth_res;
				Integer tnt_auth_cmt;
				Integer tnt_auth_rpt;
				Integer tnt_auth_live;
				String url = null;

				String tnt_no = req.getParameter("tnt_no");
				Integer tnt_reported_count = new Integer(req.getParameter("tnt_reported_count"));
				String a = req.getParameter("tnt_auth_chat");
				String b = req.getParameter("tnt_auth_res");
				String c = req.getParameter("tnt_auth_cmt");
				String d = req.getParameter("tnt_auth_rpt");
				String e = req.getParameter("tnt_auth_live");

				if (a != null) {
					tnt_auth_chat = 1;
				} else {
					tnt_auth_chat = 0;
				}
				if (b != null) {
					tnt_auth_res = 1;
				} else {
					tnt_auth_res = 0;
				}
				if (c != null) {
					tnt_auth_cmt = 1;

				} else {
					tnt_auth_cmt = 0;
				}
				if (d != null) {
					tnt_auth_rpt = 1;
				} else {
					tnt_auth_rpt = 0;
				}
				if (e != null) {
					tnt_auth_live = 1;
				} else {
					tnt_auth_live = 0;
				}

				/*************************** 2.開始查詢資料 ****************************************/
				TntVO tntVO = new TntVO();
				TntService tntSvc = new TntService();
				tntVO = tntSvc.updateTntAuth(tnt_no, tnt_reported_count, tnt_auth_chat, tnt_auth_res, tnt_auth_cmt,
						tnt_auth_rpt,tnt_auth_live);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				TntVO tntVO1 = new TntVO();
				TntService tntSvc1 = new TntService();
				if (tnt_no.startsWith("T")) {
					tntVO1 = tntSvc1.getOneTntProfile(tnt_no);
					url = "/back-end/member/tenant_search_page.jsp";
					System.out.println("成功更新權限並回到tenant_search");
				} else {
					tntVO1 = tntSvc1.getOneLandlordProfile(tnt_no);
					url = "/back-end/member/landlord_search_page.jsp";
					System.out.println("成功更新權限並回到landlord_search");
				}
				req.setAttribute("TntVO", tntVO1);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功更新權限並回到search");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/listAllRptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_want_tenant".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number").toUpperCase(); // Number可轉大寫且為身份證字號或編號
				if (Number == null || (Number.trim()).length() == 0) {
					errorMsgs.add("請正確輸入欲搜尋編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntProfile(Number);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("TntVO", tntVO);
				String url = "/back-end/member/tenant_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("搜尋不到或是未填寫要查詢的編號! 麻煩重新輸入一次");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_main_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("get_want_landlord".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String Number = req.getParameter("Number").toUpperCase(); // Number可轉大寫且為身份證字號或編號
				if (Number == null || (Number.trim()).length() == 0) {
					errorMsgs.add("請正確輸入欲搜尋編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneLandlordProfile(Number);
				System.out.println("成功進入service");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("TntVO", tntVO);
				String url = "/back-end/member/landlord_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555" + e.getMessage());
				errorMsgs.add("搜尋不到或是未填寫要查詢的編號! 麻煩重新輸入一次");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/vrf/vrf_main_page.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
