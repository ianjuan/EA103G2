package com.rptl.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rptl.model.*;
import com.rptt.model.*;
import com.rpth.model.*;


public class RptlServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptlService rptlSvc = new RptlService();
				List<RptlVO> rptlVO = rptlSvc.getRptl(Number);
				if (rptlVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptlVO", rptlVO);
				String url = "/back-end/rptl/rptl_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptlService rptlSvc = new RptlService();
				System.out.println("有來到Service的方法");
				List<RptlVO> rptlVO = rptlSvc.getRptl(Number);
				if (rptlVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptlVO", rptlVO);
				String url = "/back-end/rptl/rptl_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rptl_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lld_no = req.getParameter("lld_no");
				

				String tnt_no = req.getParameter("tnt_no");
				
				String rptl_content = req.getParameter("rptl_content").trim();
				System.out.println(rptl_content);
				if (rptl_content == null || rptl_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RptlVO rptlVO = new RptlVO();
				rptlVO.setLld_no(lld_no);
				rptlVO.setTnt_no(tnt_no);
				rptlVO.setRptl_content(rptl_content);

				/*************************** 2.開始新增資料 ***************************************/
				RptlService rptlSvc = new RptlService();
				rptlVO = rptlSvc.addRptl(lld_no, tnt_no, rptl_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptl/listAllRptl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rptl/rptl_form.jsp");
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
				String rptl_no = req.getParameter("rptl_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RptlService rptlSvc = new RptlService();
				RptlVO rptlVO = rptlSvc.getOneRptl(rptl_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rptlVO", rptlVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rptl/update_rptl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptl_no = req.getParameter("rptl_no");
				System.out.println(rptl_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rptl_status = new Integer(req.getParameter("rptl_status").trim());
				System.out.println(rptl_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlVO rptlVO1 = new RptlVO();
//				rptlVO1.setRptl_no(rptl_no);
//				rptlVO1.setEmp_no(emp_no);
//				rptlVO1.setRptl_status(rptl_status);
//				System.out.println("裝入完畢");

				RptlService rptlSvc = new RptlService();
				rptlVO1 = rptlSvc.updateEmp(rptl_no, emp_no, rptl_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rptl/rptl_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptl_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
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
				String rptl_no = req.getParameter("rptl_no");
				System.out.println(rptl_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RptlService rptlSvc = new RptlService();
					List<RptlVO> rptlVO = rptlSvc.getRptl("0");
					req.setAttribute("rptlVO", rptlVO);
					String url = "/back-end/rptl/rptl_first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rptl_note = req.getParameter("rptl_note");
				System.out.println(rptl_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlVO rptlVO1 = new RptlVO();
//				rptlVO1.setRptl_no(rptl_no);
//				rptlVO1.setEmp_no(emp_no);
//				rptlVO1.setRptl_note(rptl_note);
//				System.out.println("裝入完畢");

				RptlService rptlSvc = new RptlService();
				rptlVO1 = rptlSvc.assignEmp(rptl_no, emp_no, rptl_note);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptl/rptl_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptl_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
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
				String rptl_no = req.getParameter("rptl_no");
				System.out.println(rptl_no);
				String rptl_note = req.getParameter("rptl_note");
				System.out.println(rptl_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlVO rptlVO1 = new RptlVO();
//				rptlVO1.setRptl_no(rptl_no);
//				rptlVO1.setRptl_note(rptl_note);
//				System.out.println("裝入完畢");

				RptlService rptlSvc = new RptlService();
				rptlVO1 = rptlSvc.saveNote(rptl_no, rptl_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptl/rptl_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptl_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
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
				String rptl_no = req.getParameter("rptl_no");
				System.out.println(rptl_no);
				Integer rptl_result = 2;
				System.out.println(rptl_result);
				String rptl_note = req.getParameter("rptl_note");
				System.out.println(rptl_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlVO rptlVO1 = new RptlVO();
//				rptlVO1.setRptl_no(rptl_no);
//				rptlVO1.setRptl_result(rptl_result);
//				rptlVO1.setRptl_note(rptl_note);
//				System.out.println("裝入完畢");

				RptlService rptlSvc = new RptlService();
				rptlVO1 = rptlSvc.fail(rptl_no, rptl_result, rptl_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptl/rptl_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptl_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptl_no = req.getParameter("rptl_no");
				System.out.println(rptl_no);
				Integer rptl_result = 1;
				System.out.println(rptl_result);
				String rptl_note = req.getParameter("rptl_note");
				System.out.println(rptl_note);
				String lld_no = req.getParameter("lld_no");
				System.out.println(lld_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RptlVO rptlVO1 = new RptlVO();
//				rptlVO1.setRptl_no(rptl_no);
//				rptlVO1.setRptl_result(rptl_result);
//				rptlVO1.setRptl_note(rptl_note);
//				System.out.println("裝入完畢");

				RptlService rptlSvc = new RptlService();
				rptlVO1 = rptlSvc.fail(rptl_no, rptl_result, rptl_note);
				System.out.println("result有更新了");
				
				
				LldService lldSvc = new LldService();
				LldVO lldVO1 = lldSvc.getEmail_normal(lld_no);
				String LldEmail = "xiyuan345@gmail.com";
				String LldName = lldVO1.getLld_name();
				String LldAcc = lldVO1.getLld_acc();
				String EmailLink = "http://locallldt:8081/EA103G2/front-end/index/index.jsp";
				System.out.println("rptl準備Call寄信方法");
				MailService mailservice = new MailService();
				mailservice.sendMail(LldEmail, LldName, LldAcc, EmailLink);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptl/rptl_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptl_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptl/listAllRptl.jsp");
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
				String rptl_no = req.getParameter("rptl_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RptlService rptlSvc = new RptlService();
				rptlSvc.deleteRptl(rptl_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptl/listAllRptl.jsp";
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