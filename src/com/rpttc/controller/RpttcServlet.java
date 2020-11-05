package com.rpttc.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rpttc.model.*;
import com.tenant_comments.model.Tenant_commentsService;
import com.notify.controller.NotifyServlet;
import com.rptt.model.*;

public class RpttcServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpttcService rpttcSvc = new RpttcService();
				List<RpttcVO> rpttcVO = rpttcSvc.getRpttc(Number);
				if (rpttcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttcVO", rpttcVO);
				String url = "/back-end/rpttc/rpttc_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpttcService rpttcSvc = new RpttcService();
				System.out.println("有來到Service的方法");
				List<RpttcVO> rpttcVO = rpttcSvc.getRpttc(Number);
				if (rpttcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttcVO", rpttcVO);
				String url = "/back-end/rpttc/rpttc_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rpttc_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tcm_no = req.getParameter("tcm_no");

				String tnt_no = req.getParameter("tnt_no");

				String rpttc_content = req.getParameter("rpttc_content").trim();
				System.out.println(rpttc_content);
				if (rpttc_content == null || rpttc_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RpttcVO rpttcVO = new RpttcVO();
				rpttcVO.setTcm_no(tcm_no);
				rpttcVO.setTnt_no(tnt_no);
				rpttcVO.setRpttc_content(rpttc_content);

				/*************************** 2.開始新增資料 ***************************************/
				RpttcService rpttcSvc = new RpttcService();
				rpttcVO = rpttcSvc.addRpttc(tcm_no, tnt_no, rpttc_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rpttc/listAllRpttc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rpttc/rpttc_form.jsp");
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
				String rpttc_no = req.getParameter("rpttc_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcService rpttcSvc = new RpttcService();
				RpttcVO rpttcVO = rpttcSvc.getOneRpttc(rpttc_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rpttcVO", rpttcVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rpttc/update_rpttc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpttc_no = req.getParameter("rpttc_no");
				System.out.println(rpttc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rpttc_status = new Integer(req.getParameter("rpttc_status").trim());
				System.out.println(rpttc_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcVO rpttcVO1 = new RpttcVO();
//				rpttcVO1.setRpttc_no(rpttc_no);
//				rpttcVO1.setEmp_no(emp_no);
//				rpttcVO1.setRpttc_status(rpttc_status);
//				System.out.println("裝入完畢");

				RpttcService rpttcSvc = new RpttcService();
				rpttcVO1 = rpttcSvc.updateEmp(rpttc_no, emp_no, rpttc_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rpttc/rpttc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpttc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
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
				String rpttc_no = req.getParameter("rpttc_no");
				System.out.println(rpttc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RpttcService rpttcSvc = new RpttcService();
					List<RpttcVO> rpttcVO = rpttcSvc.getRpttc("0");
					req.setAttribute("rpttcVO", rpttcVO);
					String url = "/back-end/rpttc/rpttc_first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rpttc_note = req.getParameter("rpttc_note");
				System.out.println(rpttc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcService rpttcSvc = new RpttcService();
				RpttcService rpttcSvc2 = new RpttcService();
				List<RpttcVO> rpttcVO = rpttcSvc.getRpttc(rpttc_no);
				String old_emp_no = rpttcVO.get(0).getEmp_no();
				System.out.println("舊的" + old_emp_no);

				RpttcVO rpttcVO1 = new RpttcVO();
				rpttcVO1 = rpttcSvc.assignEmp(rpttc_no, emp_no, rpttc_note);
				System.out.println("emp有更新了");

				new NotifyServlet().broadcast(emp_no, "<b><p style='color:blue;'>【指派】檢舉房客評價</p></b>",
						"<b><p style='color:blue;'>【指派】檢舉房客評價</p></b><p>單號:" + rpttc_no + "</p>",
						"http://localhost:8081/EA103G2/back-end/index.jsp");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpttc/rpttc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpttc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
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
				String rpttc_no = req.getParameter("rpttc_no");
				System.out.println(rpttc_no);
				String rpttc_note = req.getParameter("rpttc_note");
				System.out.println(rpttc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcVO rpttcVO1 = new RpttcVO();
//				rpttcVO1.setRpttc_no(rpttc_no);
//				rpttcVO1.setRpttc_note(rpttc_note);
//				System.out.println("裝入完畢");

				RpttcService rpttcSvc = new RpttcService();
				rpttcVO1 = rpttcSvc.saveNote(rpttc_no, rpttc_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpttc/rpttc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpttc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
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
				String rpttc_no = req.getParameter("rpttc_no");
				System.out.println(rpttc_no);
				Integer rpttc_result = 2;
				System.out.println(rpttc_result);
				String rpttc_note = req.getParameter("rpttc_note");
				System.out.println(rpttc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcVO rpttcVO1 = new RpttcVO();
				RpttcService rpttcSvc = new RpttcService();
				rpttcVO1 = rpttcSvc.fail(rpttc_no, rpttc_result, rpttc_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpttc/rpttc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpttc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpttc_no = req.getParameter("rpttc_no");
				System.out.println(rpttc_no);
				Integer rpttc_result = 1;
				System.out.println(rpttc_result);
				String rpttc_note = req.getParameter("rpttc_note");
				System.out.println(rpttc_note);
				String tnt_no = req.getParameter("tnt_no");
				System.out.println(tnt_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RpttcVO rpttcVO1 = new RpttcVO();
				RpttcService rpttcSvc = new RpttcService();
				rpttcVO1 = rpttcSvc.fail(rpttc_no, rpttc_result, rpttc_note);
				System.out.println("result有更新了");

				TntService tntSvc = new TntService();
				TntVO tntVO1 = tntSvc.getEmail(tnt_no);
				String TntEmail = "xiyuan345@gmail.com";
				String TntName = tntVO1.getTnt_name();
				String TntAcc = tntVO1.getTnt_acc();
				String EmailLink = "http://localtcmt:8081/EA103G2/front-end/index/index.jsp";
				System.out.println("rpttc準備Call寄信方法");
				MailService mailservice = new MailService();
				mailservice.sendMailRpttc(TntEmail, TntName, TntAcc, EmailLink);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpttc/rpttc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpttc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpttc/listAllRpttc.jsp");
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
				String rpttc_no = req.getParameter("rpttc_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RpttcService rpttcSvc = new RpttcService();
				rpttcSvc.deleteRpttc(rpttc_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rpttc/listAllRpttc.jsp";
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