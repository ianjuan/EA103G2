package com.rpthc.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rpthc.model.*;
import com.tenant_comments.model.Tenant_commentsService;
import com.notify.controller.NotifyServlet;
import com.rpth.model.*;

public class RpthcServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpthcService rpthcSvc = new RpthcService();
				List<RpthcVO> rpthcVO = rpthcSvc.getRpthc(Number);
				if (rpthcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpthcVO", rpthcVO);
				String url = "/back-end/rpthc/rpthc_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpthcService rpthcSvc = new RpthcService();
				System.out.println("有來到Service的方法");
				List<RpthcVO> rpthcVO = rpthcSvc.getRpthc(Number);
				if (rpthcVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpthcVO", rpthcVO);
				String url = "/back-end/rpthc/rpthc_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rpthc_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String hcm_no = req.getParameter("hcm_no");

				String lld_no = req.getParameter("lld_no");

				String rpthc_content = req.getParameter("rpthc_content").trim();
				System.out.println(rpthc_content);
				if (rpthc_content == null || rpthc_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RpthcVO rpthcVO = new RpthcVO();
				rpthcVO.setHcm_no(hcm_no);
				rpthcVO.setLld_no(lld_no);
				rpthcVO.setRpthc_content(rpthc_content);

				/*************************** 2.開始新增資料 ***************************************/
				RpthcService rpthcSvc = new RpthcService();
				rpthcVO = rpthcSvc.addRpthc(hcm_no, lld_no, rpthc_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rpthc/listAllRpthc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rpthc/rpthc_form.jsp");
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
				String rpthc_no = req.getParameter("rpthc_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RpthcService rpthcSvc = new RpthcService();
				RpthcVO rpthcVO = rpthcSvc.getOneRpthc(rpthc_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rpthcVO", rpthcVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rpthc/update_rpthc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpthc_no = req.getParameter("rpthc_no");
				System.out.println(rpthc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rpthc_status = new Integer(req.getParameter("rpthc_status").trim());
				System.out.println(rpthc_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthcVO rpthcVO1 = new RpthcVO();
//				rpthcVO1.setRpthc_no(rpthc_no);
//				rpthcVO1.setEmp_no(emp_no);
//				rpthcVO1.setRpthc_status(rpthc_status);
//				System.out.println("裝入完畢");

				RpthcService rpthcSvc = new RpthcService();
				rpthcVO1 = rpthcSvc.updateEmp(rpthc_no, emp_no, rpthc_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rpthc/rpthc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpthc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
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
				String rpthc_no = req.getParameter("rpthc_no");
				System.out.println(rpthc_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RpthcService rpthcSvc = new RpthcService();
					List<RpthcVO> rpthcVO = rpthcSvc.getRpthc("0");
					req.setAttribute("rpthcVO", rpthcVO);
					String url = "/back-end/rpthc/rpthc_first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rpthc_note = req.getParameter("rpthc_note");
				System.out.println(rpthc_note);

				/*************************** 2.開始查詢資料 ****************************************/

				RpthcService rpthcSvc = new RpthcService();
				RpthcService rpthcSvc2 = new RpthcService();
				List<RpthcVO> rpthcVO = rpthcSvc.getRpthc(rpthc_no);
				String old_emp_no = rpthcVO.get(0).getEmp_no();
				System.out.println("舊的" + old_emp_no);

				RpthcVO rpthcVO1 = new RpthcVO();
				rpthcVO1 = rpthcSvc.assignEmp(rpthc_no, emp_no, rpthc_note);
				System.out.println("emp有更新了");

				new NotifyServlet().broadcast(emp_no, "<b><p style='color:blue;'>【指派】檢舉房屋評價</p></b>",
						"<b><p style='color:blue;'>【指派】檢舉房屋評價</p></b><p>單號:" + rpthc_no + "</p>",
						"http://localhost:8081/EA103G2/back-end/index.jsp");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpthc/rpthc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpthc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
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
				String rpthc_no = req.getParameter("rpthc_no");
				System.out.println(rpthc_no);
				String rpthc_note = req.getParameter("rpthc_note");
				System.out.println(rpthc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthcVO rpthcVO1 = new RpthcVO();
//				rpthcVO1.setRpthc_no(rpthc_no);
//				rpthcVO1.setRpthc_note(rpthc_note);
//				System.out.println("裝入完畢");

				RpthcService rpthcSvc = new RpthcService();
				rpthcVO1 = rpthcSvc.saveNote(rpthc_no, rpthc_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpthc/rpthc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpthc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
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
				String rpthc_no = req.getParameter("rpthc_no");
				System.out.println(rpthc_no);
				Integer rpthc_result = 2;
				System.out.println(rpthc_result);
				String rpthc_note = req.getParameter("rpthc_note");
				System.out.println(rpthc_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthcVO rpthcVO1 = new RpthcVO();
				RpthcService rpthcSvc = new RpthcService();
				rpthcVO1 = rpthcSvc.fail(rpthc_no, rpthc_result, rpthc_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpthc/rpthc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpthc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpthc_no = req.getParameter("rpthc_no");
				System.out.println(rpthc_no);
				Integer rpthc_result = 1;
				System.out.println(rpthc_result);
				String rpthc_note = req.getParameter("rpthc_note");
				System.out.println(rpthc_note);
				String lld_no = req.getParameter("lld_no");
				System.out.println(lld_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthcVO rpthcVO1 = new RpthcVO();
				RpthcService rpthcSvc = new RpthcService();
				rpthcVO1 = rpthcSvc.fail(rpthc_no, rpthc_result, rpthc_note);
				System.out.println("result有更新了");

				LldService lldSvc = new LldService();
				LldVO lldVO1 = lldSvc.getEmail_normal(lld_no);
				String LldEmail = "xiyuan345@gmail.com";
				String LldName = lldVO1.getLld_name();
				String LldAcc = lldVO1.getLld_acc();
				String EmailLink = "http://localhcmt:8081/EA103G2/front-end/index/index.jsp";
				System.out.println("rpthc準備Call寄信方法");
				MailService mailservice = new MailService();
				mailservice.sendMailRpthc(LldEmail, LldName, LldAcc, EmailLink);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpthc/rpthc_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpthc_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpthc/listAllRpthc.jsp");
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
				String rpthc_no = req.getParameter("rpthc_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RpthcService rpthcSvc = new RpthcService();
				rpthcSvc.deleteRpthc(rpthc_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rpthc/listAllRpthc.jsp";
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