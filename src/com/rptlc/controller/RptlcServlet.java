package com.rptlc.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rptlc.model.*;
import com.tenant_comments.model.Tenant_commentsService;
import com.rpth.model.*;

public class RptlcServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptlcService rptlcSvc = new RptlcService();
				List<RptlcVO> rptlcVO = rptlcSvc.getRptlc(Number);
				if (rptlcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptlcVO", rptlcVO);
				String url = "/back-end/rptlc/rptlc_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptlcService rptlcSvc = new RptlcService();
				System.out.println("有來到Service的方法");
				List<RptlcVO> rptlcVO = rptlcSvc.getRptlc(Number);
				if (rptlcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptlcVO", rptlcVO);
				String url = "/back-end/rptlc/rptlc_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rptlc_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lcm_no = req.getParameter("lcm_no");

				String lld_no = req.getParameter("lld_no");

				String rptlc_content = req.getParameter("rptlc_content").trim();
				System.out.println(rptlc_content);
				if (rptlc_content == null || rptlc_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RptlcVO rptlcVO = new RptlcVO();
				rptlcVO.setLcm_no(lcm_no);
				rptlcVO.setLld_no(lld_no);
				rptlcVO.setRptlc_content(rptlc_content);

				/*************************** 2.開始新增資料 ***************************************/
				RptlcService rptlcSvc = new RptlcService();
				rptlcVO = rptlcSvc.addRptlc(lcm_no, lld_no, rptlc_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptlc/listAllRptlc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rptlc/rptlc_form.jsp");
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
				String rptlc_no = req.getParameter("rptlc_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcService rptlcSvc = new RptlcService();
				RptlcVO rptlcVO = rptlcSvc.getOneRptlc(rptlc_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rptlcVO", rptlcVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rptlc/update_rptlc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptlc_no = req.getParameter("rptlc_no");
				System.out.println(rptlc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rptlc_status = new Integer(req.getParameter("rptlc_status").trim());
				System.out.println(rptlc_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcVO rptlcVO1 = new RptlcVO();
//				rptlcVO1.setRptlc_no(rptlc_no);
//				rptlcVO1.setEmp_no(emp_no);
//				rptlcVO1.setRptlc_status(rptlc_status);
//				System.out.println("裝入完畢");

				RptlcService rptlcSvc = new RptlcService();
				rptlcVO1 = rptlcSvc.updateEmp(rptlc_no, emp_no, rptlc_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rptlc/rptlc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptlc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
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
				String rptlc_no = req.getParameter("rptlc_no");
				System.out.println(rptlc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RptlcService rptlcSvc = new RptlcService();
					List<RptlcVO> rptlcVO = rptlcSvc.getRptlc("0");
					req.setAttribute("rptlcVO", rptlcVO);
					String url = "/back-end/rptlc/rptlc_first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rptlc_note = req.getParameter("rptlc_note");
				System.out.println(rptlc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcVO rptlcVO1 = new RptlcVO();
//				rptlcVO1.setRptlc_no(rptlc_no);
//				rptlcVO1.setEmp_no(emp_no);
//				rptlcVO1.setRptlc_note(rptlc_note);
//				System.out.println("裝入完畢");

				RptlcService rptlcSvc = new RptlcService();
				rptlcVO1 = rptlcSvc.assignEmp(rptlc_no, emp_no, rptlc_note);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptlc/rptlc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptlc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
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
				String rptlc_no = req.getParameter("rptlc_no");
				System.out.println(rptlc_no);
				String rptlc_note = req.getParameter("rptlc_note");
				System.out.println(rptlc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcVO rptlcVO1 = new RptlcVO();
//				rptlcVO1.setRptlc_no(rptlc_no);
//				rptlcVO1.setRptlc_note(rptlc_note);
//				System.out.println("裝入完畢");

				RptlcService rptlcSvc = new RptlcService();
				rptlcVO1 = rptlcSvc.saveNote(rptlc_no, rptlc_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptlc/rptlc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptlc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
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
				String rptlc_no = req.getParameter("rptlc_no");
				System.out.println(rptlc_no);
				Integer rptlc_result = 2;
				System.out.println(rptlc_result);
				String rptlc_note = req.getParameter("rptlc_note");
				System.out.println(rptlc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcVO rptlcVO1 = new RptlcVO();
				RptlcService rptlcSvc = new RptlcService();
				rptlcVO1 = rptlcSvc.fail(rptlc_no, rptlc_result, rptlc_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptlc/rptlc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptlc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptlc_no = req.getParameter("rptlc_no");
				System.out.println(rptlc_no);
				Integer rptlc_result = 1;
				System.out.println(rptlc_result);
				String rptlc_note = req.getParameter("rptlc_note");
				System.out.println(rptlc_note);
				String lld_no = req.getParameter("lld_no");
				System.out.println(lld_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlcVO rptlcVO1 = new RptlcVO();
				RptlcService rptlcSvc = new RptlcService();
				rptlcVO1 = rptlcSvc.fail(rptlc_no, rptlc_result, rptlc_note);
				System.out.println("result有更新了");

				LldService lldSvc = new LldService();
				LldVO lldVO1 = lldSvc.getEmail_normal(lld_no);
				String LldEmail = "xiyuan345@gmail.com";
				String LldName = lldVO1.getLld_name();
				String LldAcc = lldVO1.getLld_acc();
				String EmailLink = "http://locallcmt:8081/EA103G2/front-end/index/index.jsp";
				System.out.println("rptlc準備Call寄信方法");
				MailService mailservice = new MailService();
				mailservice.sendMail(LldEmail, LldName, LldAcc, EmailLink);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptlc/rptlc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptlc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptlc/listAllRptlc.jsp");
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
				String rptlc_no = req.getParameter("rptlc_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RptlcService rptlcSvc = new RptlcService();
				rptlcSvc.deleteRptlc(rptlc_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptlc/listAllRptlc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}