package com.rptr.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rpth.model.LldService;
import com.rpth.model.LldVO;
import com.rpthc.controller.MailService;
import com.rptr.model.*;
import com.tenant_comments.model.Tenant_commentsService;
import com.rptt.model.*;

public class RptrServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptrService rptrSvc = new RptrService();
				List<RptrVO> rptrVO = rptrSvc.getRptr(Number);
				if (rptrVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptrVO", rptrVO);
				String url = "/back-end/rptr/rptr_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptrService rptrSvc = new RptrService();
				System.out.println("有來到Service的方法");
				List<RptrVO> rptrVO = rptrSvc.getRptr(Number);
				if (rptrVO.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rptrVO", rptrVO);
				String url = "/back-end/rptr/rptr_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rptr_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String rep_no = req.getParameter("rep_no");

				String tnt_no = req.getParameter("tnt_no");

				String rptr_content = req.getParameter("rptr_content").trim();
				System.out.println(rptr_content);
				if (rptr_content == null || rptr_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RptrVO rptrVO = new RptrVO();
				rptrVO.setRep_no(rep_no);
				rptrVO.setTnt_no(tnt_no);
				rptrVO.setRptr_content(rptr_content);

				/*************************** 2.開始新增資料 ***************************************/
				RptrService rptrSvc = new RptrService();
				rptrVO = rptrSvc.addRptr(rep_no, tnt_no, rptr_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptr/listAllRptr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("新增完成");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rptr/rptr_form.jsp");
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
				String rptr_no = req.getParameter("rptr_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RptrService rptrSvc = new RptrService();
				RptrVO rptrVO = rptrSvc.getOneRptr(rptr_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rptrVO", rptrVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rptr/update_rptr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptr_no = req.getParameter("rptr_no");
				System.out.println(rptr_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rptr_status = new Integer(req.getParameter("rptr_status").trim());
				System.out.println(rptr_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RptrVO rptrVO1 = new RptrVO();
//				rptrVO1.setRptr_no(rptr_no);
//				rptrVO1.setEmp_no(emp_no);
//				rptrVO1.setRptr_status(rptr_status);
//				System.out.println("裝入完畢");

				RptrService rptrSvc = new RptrService();
				rptrVO1 = rptrSvc.updateEmp(rptr_no, emp_no, rptr_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rptr/rptr_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptr_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
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
				String rptr_no = req.getParameter("rptr_no");
				System.out.println(rptr_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("未填寫要指派的員工編號! 麻煩重新操作一次");
					RptrService rptrSvc = new RptrService();
					List<RptrVO> rptrVO = rptrSvc.getRptr("0");
					req.setAttribute("rptrVO", rptrVO);
					String url = "/back-end/rptr/rptr_first_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rptr_note = req.getParameter("rptr_note");
				System.out.println(rptr_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptrVO rptrVO1 = new RptrVO();
//				rptrVO1.setRptr_no(rptr_no);
//				rptrVO1.setEmp_no(emp_no);
//				rptrVO1.setRptr_note(rptr_note);
//				System.out.println("裝入完畢");

				RptrService rptrSvc = new RptrService();
				rptrVO1 = rptrSvc.assignEmp(rptr_no, emp_no, rptr_note);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptr/rptr_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptr_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
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
				String rptr_no = req.getParameter("rptr_no");
				System.out.println(rptr_no);
				String rptr_note = req.getParameter("rptr_note");
				System.out.println(rptr_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptrVO rptrVO1 = new RptrVO();
//				rptrVO1.setRptr_no(rptr_no);
//				rptrVO1.setRptr_note(rptr_note);
//				System.out.println("裝入完畢");

				RptrService rptrSvc = new RptrService();
				rptrVO1 = rptrSvc.saveNote(rptr_no, rptr_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptr/rptr_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptr_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
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
				String rptr_no = req.getParameter("rptr_no");
				System.out.println(rptr_no);
				Integer rptr_result = 2;
				System.out.println(rptr_result);
				String rptr_note = req.getParameter("rptr_note");
				System.out.println(rptr_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RptrVO rptrVO1 = new RptrVO();
				RptrService rptrSvc = new RptrService();
				rptrVO1 = rptrSvc.fail(rptr_no, rptr_result, rptr_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptr/rptr_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptr_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rptr_no = req.getParameter("rptr_no");
				System.out.println(rptr_no);
				Integer rptr_result = 1;
				System.out.println(rptr_result);
				String rptr_note = req.getParameter("rptr_note");
				System.out.println(rptr_note);
				String tnt_no = req.getParameter("tnt_no");
				System.out.println(tnt_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RptrVO rptrVO1 = new RptrVO();
				RptrService rptrSvc = new RptrService();
				rptrVO1 = rptrSvc.fail(rptr_no, rptr_result, rptr_note);
				System.out.println("result有更新了");

				LldService lldSvc = new LldService();
				LldVO lldVO1 = lldSvc.getRepair(rptr_no);
				String LldEmail = "xiyuan345@gmail.com";
				String LldName = lldVO1.getLld_name();
				String LldAcc = lldVO1.getLld_acc();
				String EmailLink = "http://localhcmt:8081/EA103G2/front-end/index/index.jsp";
				System.out.println("rpthc準備Call寄信方法");
				MailService mailservice = new MailService();
				mailservice.sendMail(LldEmail, LldName, LldAcc, EmailLink);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rptr/rptr_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rptr_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptr/listAllRptr.jsp");
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
				String rptr_no = req.getParameter("rptr_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RptrService rptrSvc = new RptrService();
				rptrSvc.deleteRptr(rptr_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rptr/listAllRptr.jsp";
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