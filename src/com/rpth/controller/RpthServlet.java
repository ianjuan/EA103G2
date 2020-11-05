package com.rpth.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rpth.model.*;
import com.rptt.model.RpttService;
import com.rptt.model.RpttVO;
import com.rptt.model.TntService;
import com.rptt.model.TntVO;
import com.housemanage.model.*;
import com.notify.controller.NotifyServlet;

public class RpthServlet extends HttpServlet {

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
					errorMsgs.add("親，空白了要怎麼找");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpthService rpthSvc = new RpthService();
				List<RpthVO> rpthVO = rpthSvc.getRpth(Number);
				if (rpthVO.isEmpty()) {
					errorMsgs.add("親，查無此人，確定一下唄!");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
					failureView.forward(req, res);
					System.out.println("親，查無此人，確定一下唄!");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpthVO", rpthVO);
				String url = "/back-end/rpth/rpth_first_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("別給我亂輸入喔!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
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
					errorMsgs.add("親，空白了要怎麼找");
				}

				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_second_page.jsp");
					failureView.forward(req, res);
					System.out.println("編號輸入錯誤");
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpthService rpthSvc = new RpthService();
				System.out.println("有來到Service的方法");
				List<RpthVO> rpthVO = rpthSvc.getRpth(Number);
				if (rpthVO.isEmpty()) {
					errorMsgs.add("親，查無此人，確定一下唄!");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_second_page.jsp");
					failureView.forward(req, res);
					System.out.println("此編號找不到");
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rpthVO", rpthVO);
				String url = "/back-end/rpth/rpth_second_search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("成功轉過去囉rpth_second_search_page");

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				System.out.println("無法取得555");
				errorMsgs.add("別給我亂輸入喔!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_second_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println("housedet近來");
				String hos_no = req.getParameter("hos_no");
                System.out.println("這格hosno++"+hos_no);
				String tnt_no = req.getParameter("tnt_no");
				System.out.println("這格tntno++"+tnt_no);
				String rpth_content = req.getParameter("rpth_content").trim();
				System.out.println(rpth_content);
				if (rpth_content == "" || rpth_content.trim().length() == 0) {
					errorMsgs.add("寶寶檢舉內容空白!寶寶不說");
				}

				RpthVO rpthVO = new RpthVO();
				rpthVO.setHos_no(hos_no);
				rpthVO.setTnt_no(tnt_no);
				rpthVO.setRpth_content(rpth_content);

				/*************************** 2.開始新增資料 ***************************************/
				RpthService rpthSvc = new RpthService();
				rpthVO = rpthSvc.addRpth(hos_no, tnt_no, rpth_content);
				System.out.println("要去RETURN啦");
			    return;

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				System.out.println("開始轉交");
//				String url = req.getParameter("url333");
//				System.out.println("urL3333 是"+url);
//				String QUERY = req.getParameter("urlQUERY");
//				System.out.println("urlQUERY 是"+QUERY);
//				res.sendRedirect(url+"?"+QUERY);

			
				

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // wayne其實沒用到

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpth_no = req.getParameter("rpth_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RpthService rpthSvc = new RpthService();
				RpthVO rpthVO = rpthSvc.getOneRpth(rpth_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rpthVO", rpthVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rpth/update_rpth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/listAllRpth.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_employee".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("現在跑update_employee");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpth_no = req.getParameter("rpth_no");
				System.out.println(rpth_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("找不到員工編號");
				}
				Integer rpth_status = new Integer(req.getParameter("rpth_status").trim());
				System.out.println(rpth_status);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthVO rpthVO1 = new RpthVO();
//				rpthVO1.setRpth_no(rpth_no);
//				rpthVO1.setEmp_no(emp_no);
//				rpthVO1.setRpth_status(rpth_status);
//				System.out.println("裝入完畢");

				RpthService rpthSvc = new RpthService();
				rpthVO1 = rpthSvc.updateEmp(rpth_no, emp_no, rpth_status);
				System.out.println("emp有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				String url = "/back-end/rpth/rpth_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpth_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("奇怪了呢，怎麼不能新增員工呢?");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
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
				String rpth_no = req.getParameter("rpth_no");
				System.out.println(rpth_no);
				String emp_no = req.getParameter("emp_no");
				System.out.println(emp_no);
				if (emp_no == null) {
					System.out.println("空直跑到例外");
					errorMsgs.add("親，別忘了選一個替死鬼阿!");
					RpthService rpthSvc = new RpthService();
					List<RpthVO> rpthVO = rpthSvc.getRpth("0");
					req.setAttribute("rpthVO", rpthVO);
					String url = "/back-end/rpth/rpth_main_page.jsp";
					RequestDispatcher FailView = req.getRequestDispatcher(url);
					FailView.forward(req, res);
					return;
				}
				String rpth_note = req.getParameter("rpth_note");
				System.out.println(rpth_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthService rpthSvc = new RpthService();
				RpthService rpthSvc2 = new RpthService();
				List<RpthVO> rpthVO = rpthSvc.getRpth(rpth_no);
				String old_emp_no = rpthVO.get(0).getEmp_no();
				System.out.println("舊的" + old_emp_no);

				RpthVO rpthVO1 = new RpthVO();
				rpthVO1 = rpthSvc.assignEmp(rpth_no, emp_no, rpth_note);
				System.out.println("emp有更新了");

				new NotifyServlet().broadcast(emp_no, "<b><p style='color:blue;'>【指派】檢舉房屋</p></b>",
						"<b><p style='color:blue;'>【指派】檢舉房屋</p></b><p>單號:" + rpth_no + "</p>",
						"http://localhost:8081/EA103G2/back-end/index.jsp");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpth/rpth_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpth_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("跑到例外");
				errorMsgs.add("不能指派! 難道是她是你對的人?");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
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
				String rpth_no = req.getParameter("rpth_no");
				System.out.println(rpth_no);
				String rpth_note = req.getParameter("rpth_note");
				System.out.println(rpth_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthVO rpthVO1 = new RpthVO();
//				rpthVO1.setRpth_no(rpth_no);
//				rpthVO1.setRpth_note(rpth_note);
//				System.out.println("裝入完畢");

				RpthService rpthSvc = new RpthService();
				rpthVO1 = rpthSvc.saveNote(rpth_no, rpth_note);
				System.out.println("note有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpth/rpth_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpth_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("儲存不了ㄟ! 就自己解決啦!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
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
				String rpth_no = req.getParameter("rpth_no");
				System.out.println(rpth_no);
				Integer rpth_result = 2;
				System.out.println(rpth_result);
				String rpth_note = req.getParameter("rpth_note");
				System.out.println(rpth_note);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthVO rpthVO1 = new RpthVO();
//				rpthVO1.setRpth_no(rpth_no);
//				rpthVO1.setRpth_result(rpth_result);
//				rpthVO1.setRpth_note(rpth_note);
//				System.out.println("裝入完畢");

				RpthService rpthSvc = new RpthService();
				rpthVO1 = rpthSvc.fail(rpth_no, rpth_result, rpth_note);
				System.out.println("result有更新了");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpth/rpth_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpth_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("哪尼!他是好人阿!別誣賴他");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("pass".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rpth_no = req.getParameter("rpth_no");
				System.out.println(rpth_no);
				Integer rpth_result = 1;
				System.out.println(rpth_result);
				String rpth_note = req.getParameter("rpth_note");
				System.out.println(rpth_note);
				String hos_no = req.getParameter("hos_no");
				System.out.println(hos_no);

				/*************************** 2.開始查詢資料 ****************************************/
				RpthVO rpthVO1 = new RpthVO();
//				rpthVO1.setRpth_no(rpth_no);
//				rpthVO1.setRpth_result(rpth_result);
//				rpthVO1.setRpth_note(rpth_note);
//				System.out.println("裝入完畢");

				RpthService rpthSvc = new RpthService();
				rpthVO1 = rpthSvc.fail(rpth_no, rpth_result, rpth_note);
				System.out.println("result有更新了");
				HouseService hosSvc =new HouseService();
				hosSvc.updateStatus("已下架", hos_no);
				System.out.println("house已下架了");
				
				LldService lldSvc = new LldService();
				LldVO lldVO1 = lldSvc.getEmail(hos_no);
				String HosEmail = "xiyuan345@gmail.com";
				String LldName = lldVO1.getLld_name();
				String HosName = lldVO1.getLld_acc();
				String EmailLink = "http://localhost:8081/EA103G2/front-end/index/index.jsp";
				String lld_no= lldVO1.getLld_no();
				MailService mailservice = new MailService();
				mailservice.sendMailRpth(HosEmail, LldName, HosName, EmailLink);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/rpth/rpth_main_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("轉到rpth_main_page");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("壞人為什麼不能受到制裁!天公伯阿!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rpth/rpth_main_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // wayne沒用到

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String rpth_no = req.getParameter("rpth_no");

				/*************************** 2.開始刪除資料 ***************************************/
				RpthService rpthSvc = new RpthService();
				rpthSvc.deleteRpth(rpth_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rpth/listAllRpth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
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
					errorMsgs.add("親，空白了要怎麼找");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/landlord_main_page");
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
				errorMsgs.add("我找我找，就是找不到這個人!給我檢查一下");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/landlord_main_page");
				failureView.forward(req, res);
			}

		}
	}
}