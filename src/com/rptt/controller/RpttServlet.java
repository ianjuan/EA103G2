package com.rptt.controller;

import java.io.IOException;
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

				String Number = req.getParameter("Number");
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
				String url = "/back-end/rptt/list_reported_tenant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("get_one_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String rptt_no = req.getParameter("Rptt_no");
				if (rptt_no == null || (rptt_no.trim()).length() == 0) {
					errorMsgs.add("請輸入欲搜尋的檢舉編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("檢舉編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpttService rpttSvc = new RpttService();
				RpttVO rpttVO = rpttSvc.getOneRptt(rptt_no);
				if (rpttVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttVO", rpttVO);
				System.out.println(rpttVO.getRptt_result()+22);
				System.out.println(rpttVO.getRptt_result());
				System.out.println(rpttVO.getRptt_note());
				String url = "/back-end/rptt/list_one_rpttno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
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

				String rptt_content = req.getParameter("rptt_content");
				if (rptt_content == null || rptt_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RpttVO rpttVO = new RpttVO();
				rpttVO.setTnt_no(tnt_no);
				rpttVO.setLld_no(lld_no);
				rpttVO.setRptt_content(rptt_content);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rpttVO", rpttVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rptt/rptt_form.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RpttService rpttSvc = new RpttService();
				rpttVO = rpttSvc.addRptt(tnt_no, lld_no, rptt_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/rptt/rptt_form.jsp";
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

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String rptt_no = req.getParameter("rptt_no");
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

				String rptt_content = req.getParameter("rptt_content");
				if (rptt_content == null || rptt_content.trim().length() == 0) {
					errorMsgs.add("檢舉內容: 請勿空白");
				}

				String emp_no = req.getParameter("emp_no");
				String emp_no_Reg = "^EMP[0-9]{6}$";
				if (emp_no == null || emp_no.trim().length() == 0) {
					errorMsgs.add("員工編號: 請勿空白");
				} else if (!emp_no.trim().matches(emp_no_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工編號: 只能是EMP後面接上6位數");
				}
				String rptt_note = req.getParameter("rptt_note");
				Integer rptt_status = new Integer(req.getParameter("rptt_status").trim());
				Integer rptt_result = new Integer(req.getParameter("rptt_result").trim());

				RpttVO rpttVO = new RpttVO();
				rpttVO.setRptt_no(rptt_no);
				rpttVO.setTnt_no(tnt_no);
				rpttVO.setLld_no(lld_no);
				rpttVO.setRptt_content(rptt_content);
				rpttVO.setEmp_no(emp_no);
				rpttVO.setRptt_note(rptt_note);
				rpttVO.setRptt_status(rptt_status);
				rpttVO.setRptt_result(rptt_result);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rpttVO", rpttVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/update_rptt.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RpttService rpttSvc = new RpttService();
				rpttVO = rpttSvc.updaterptt(rptt_no, tnt_no, lld_no, rptt_content, emp_no, rptt_status, rptt_result,
						rptt_note);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpttVO", rpttVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/rptt/list_one_rpttno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rptt/update_rptt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String rptt_no = req.getParameter("rptt_no");
				
				/***************************2.開始刪除資料***************************************/
				RpttService rpttSvc = new RpttService();
				rpttSvc.deleteRptt(rptt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/rptt/listAllRptt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
