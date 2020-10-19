package com.apl.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.apl.model.Con_aplDAO;
import com.apl.model.Con_aplService;
import com.apl.model.Con_aplVO;
import com.cont.model.ConService;

public class Con_aplServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/*************************** 開始查詢資料 ****************************************/
			Con_aplDAO dao = new Con_aplDAO();
			List<Con_aplVO> list = dao.getAll();

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			// 資料庫取出的list物件,存入session
			session.setAttribute("list", list);
			// Send the Success view
			String url = "/front-end/apl/listAllCon_apl.jsp";
			// 成功轉交listAllCon_apl2_getFromSession.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

		// 來自select_page.jsp的請求
		if ("lldgetAll".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("lld_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房東編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					// 程式中斷
					return;
				}

				String lldno = null;
				try {
					lldno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("房東編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				// 確認有此LLD_NO
//				LldService lldService = new LldService();
//				LldVO lldVO = lldService.getOneLldProfile(lld_no);
//				if (lldVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}

				// 撈申請資料
				Con_aplService con_aplService = new Con_aplService();
				List<Con_aplVO> list = con_aplService.lldgetAll(lldno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				// 資料庫取出的list物件,存入session
				req.setAttribute("lld_no", lldno);
				session.setAttribute("list", list);
				// Send the Success view
				String url = "/front-end/apl/lldaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntgetallapl".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String tnt_no = req.getParameter("tnt_no");
				System.out.println(tnt_no);

				/*************************** 2.開始查詢資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				List<Con_aplVO> list = con_aplService.tntgetAll(tnt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				// Send the Success view
				String url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 來自select_page.jsp的請求
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("apl_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入租屋申請編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					// 程式中斷
					return;
				}

				String apl_no = null;
				try {
					apl_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("租屋申請編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				Con_aplVO con_aplVO = con_aplSvc.getOneCon_apl(apl_no);
				if (con_aplVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("con_aplVO", con_aplVO); // 資料庫取出的con_aplVO物件,存入req
				String url = "/front-end/apl/listOneCon_apl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCon_apl.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_tntUpdate".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String apl_no = new String(req.getParameter("apl_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				Con_aplVO con_aplVO = con_aplSvc.getOneCon_apl(apl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("tnt_no", tnt_no);
				String url = "/front-end/apl/tntupdateapl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/listAllCon_apl.jsp");
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
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{9}$";
				if (tnt_no == null || tnt_no.trim().length() == 0) {
					errorMsgs.add("房客編號: 請勿空白");
				} else if (!tnt_no.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房客編號: 只能英文字母、數字, 且長度必需為9");
				}

				String hos_no = req.getParameter("hos_no");
				if (hos_no == null || hos_no.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if (!hos_no.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能英文字母、數字, 且長度必需為9");
				}

				java.sql.Date apl_time = null;
				try {
					apl_time = java.sql.Date.valueOf(req.getParameter("apl_time").trim());
				} catch (IllegalArgumentException e) {
					apl_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date apl_str = null;
				try {
					apl_str = java.sql.Date.valueOf(req.getParameter("apl_str").trim());
				} catch (IllegalArgumentException e) {
					apl_str = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date apl_end = null;
				try {
					apl_end = java.sql.Date.valueOf(req.getParameter("apl_end").trim());
				} catch (IllegalArgumentException e) {
					apl_end = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer apl_status = new Integer(req.getParameter("apl_status").trim());

				Con_aplVO con_aplVO = new Con_aplVO();
				con_aplVO.setTnt_no(tnt_no);
				con_aplVO.setHos_no(hos_no);
				con_aplVO.setApl_time(apl_time);
				con_aplVO.setApl_str(apl_str);
				con_aplVO.setApl_end(apl_end);
				con_aplVO.setApl_status(apl_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("con_aplVO", con_aplVO); // 含有輸入格式錯誤的con_aplVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/addCon_apl.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				con_aplVO = con_aplSvc.addCon_apl(tnt_no, hos_no, apl_str, apl_end, apl_time, apl_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/apl/listAllCon_apl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/addCon_apl.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_con_apl_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = new String(req.getParameter("apl_no").trim());

				String tnt_no = req.getParameter("tnt_no");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{9}$";
				if (tnt_no == null || tnt_no.trim().length() == 0) {
					errorMsgs.add("房客編號: 請勿空白");
				} else if (!tnt_no.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房客編號: 只能英文字母、數字, 且長度必需為9");
				}

				String hos_no = req.getParameter("hos_no");
				if (hos_no == null || hos_no.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if (!hos_no.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能英文字母、數字, 且長度必需為9");
				}

				java.sql.Date apl_time = null;
				try {
					apl_time = java.sql.Date.valueOf(req.getParameter("apl_time").trim());
				} catch (IllegalArgumentException e) {
					apl_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date apl_str = null;
				try {
					apl_str = java.sql.Date.valueOf(req.getParameter("apl_str").trim());
				} catch (IllegalArgumentException e) {
					apl_str = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date apl_end = null;
				try {
					apl_end = java.sql.Date.valueOf(req.getParameter("apl_end").trim());
				} catch (IllegalArgumentException e) {
					apl_end = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer apl_status = new Integer(req.getParameter("apl_status").trim());
//				Integer apl_status = new Integer(0);

				Con_aplVO con_aplVO = new Con_aplVO();
				con_aplVO.setTnt_no(tnt_no);
				con_aplVO.setHos_no(hos_no);
				con_aplVO.setApl_time(apl_time);
				con_aplVO.setApl_str(apl_str);
				con_aplVO.setApl_end(apl_end);
				con_aplVO.setApl_status(apl_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("con_aplVO", con_aplVO); // 含有輸入格式錯誤的con_aplVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/updateCon_apl_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				con_aplVO = con_aplSvc.updateCon_apl(apl_no, tnt_no, hos_no, apl_str, apl_end, apl_time, apl_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("con_aplVO", con_aplVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/apl/listOneCon_apl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/updateCon_apl_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("addfromhouse".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String tnt_no = req.getParameter("tnt_no");
				String hos_no = req.getParameter("hos_no");

				java.sql.Date apl_str = null;
				try {
					apl_str = java.sql.Date.valueOf(req.getParameter("apl_str").trim());
				} catch (IllegalArgumentException e) {
					apl_str = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date apl_end = null;
				try {
					apl_end = java.sql.Date.valueOf(req.getParameter("apl_end").trim());
				} catch (IllegalArgumentException e) {
					apl_end = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Con_aplVO con_aplVO = new Con_aplVO();
				con_aplVO.setTnt_no(tnt_no);
				con_aplVO.setHos_no(hos_no);

//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("con_aplVO", con_aplVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/updateCon_apl_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				con_aplSvc.addfromhouse(tnt_no, hos_no, apl_str, apl_end);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/updateCon_apl_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("lldupdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = new String(req.getParameter("apl_no").trim());
				String lldno = req.getParameter("lld_no");

				Integer apl_status = new Integer(req.getParameter("apl_status").trim());
				System.out.println(apl_no);
				System.out.println(apl_status);

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				con_aplService.lldUpdateCon_apl(apl_no, apl_status);
				List<Con_aplVO> list = con_aplService.lldgetAll(lldno);

				if (apl_status == 1) {
					Con_aplVO con_aplVO = new Con_aplVO();
					con_aplVO = con_aplService.getOneCon_apl(apl_no);
					String tnt_no = con_aplVO.getTnt_no();
					String hos_no = con_aplVO.getHos_no();
					System.out.println(tnt_no);
					System.out.println(hos_no);

					ConService conService = new ConService();
					conService.addbeforerent(apl_no, tnt_no, hos_no);
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("con_aplSvc", con_aplService);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/lldaplpage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				// 資料庫取出的list物件,存入session
				req.setAttribute("lldno", lldno);
				session.setAttribute("list", list);
				// Send the Success view
				String url = "/front-end/apl/lldaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/lldaplpage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntupdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = req.getParameter("apl_no");
				String tnt_no = req.getParameter("tnt_no");
				Date apl_str = Date.valueOf(req.getParameter("apl_str"));
				Date apl_end = Date.valueOf(req.getParameter("apl_end"));
				System.out.println(apl_no);
				System.out.println(apl_str);
				System.out.println(apl_end);

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				con_aplService.tntupdateCon_apl(apl_no, apl_str, apl_end);

				List<Con_aplVO> list = con_aplService.tntgetAll(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				// Send the Success view
				String url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntcancelapl".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
//		 Store this set in the request scope, in case we need to
//		 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = req.getParameter("apl_no");
				String tnt_no = req.getParameter("tnt_no");
				Integer apl_status = 3;

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				con_aplService.lldUpdateCon_apl(apl_no, apl_status);

				List<Con_aplVO> list = con_aplService.tntgetAll(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				req.setAttribute("tnt_no", tnt_no);
				session.setAttribute("list", list);
				// Send the Success view
				String url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}