package com.tnt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tnt.model.*;
import com.cash.model.*;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import tools.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class TntServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = null;

		if ("logout".equals(action)) {
			System.out.println("action: " + action);
			HttpSession session = req.getSession();
			session.removeAttribute("tnt_no");
			session.removeAttribute("lld_no");
			res.sendRedirect(req.getContextPath() + "/front-end/index/index.jsp");
		}

		if ("login".equals(action)) { // 來自login.jsp的請求-form post
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.removeAttribute("emailVrfMsgs");

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 【取得使用者 帳號(account) 密碼(password)】
				String tnt_email = req.getParameter("tnt_email");
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (tnt_email == null || tnt_email.trim().length() == 0) {
//					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!tnt_email.trim().matches(emailReg)) {
					System.out.println("電子信箱錯誤");
//					errorMsgs.add("電子信箱錯誤");
				}

				String tnt_pwd = req.getParameter("tnt_pwd");
				String pwdReg = "^[\\w]{8,16}$";
				if (tnt_pwd == null || tnt_pwd.trim().length() == 0) {
//					errorMsgs.add("密碼: 請勿空白");
				} else if (!tnt_pwd.trim().matches(pwdReg)) {
					System.out.println("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
//					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				}

				TntVO tntVO = new TntVO();
				tntVO.setTnt_email(tnt_email);
				tntVO.setTnt_pwd(tnt_pwd);

				/*************************** 2.開始比對登入資料 ***************************************/
				// 【檢查該帳號 , 密碼是否有效】
				TntService tntSvc = new TntService();
				List<TntVO> list = tntSvc.getAllAccount();
				String tnt_no = loginValidate(list, tnt_email, tnt_pwd);

				if ("EmailnotRegisterYet".equals(tnt_no)) { // 信箱尚未註冊
					errorMsgs.add("信箱尚未註冊");
					System.out.println(tnt_no + ": 信箱尚未註冊");
					req.setAttribute("errorMsgs", errorMsgs);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("tntVO_req", tntVO); // 含有輸入格式錯誤的tntVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/tnt/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
				}
				if ("Pwdfalse".equals(tnt_no)) { // 密碼錯誤
					errorMsgs.add("帳號密碼錯誤");
					System.out.println(tnt_no + ": 帳號密碼錯誤");

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("tntVO_req", tntVO); // 含有輸入格式錯誤的tntVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/tnt/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
				}
				if (tnt_no.substring(0, 3).equalsIgnoreCase("tnt")) { // 登入成功
					int tnt_status = tntSvc.getOneTntProfile(tnt_no).getTnt_status();  // 檢查帳號是否啟用
					if (tnt_status == 0) {
						errorMsgs.add("帳號未啟用，請前往信箱完成驗證");
						System.out.println(tnt_no + ": 帳號未啟用，請前往信箱完成驗證");
						req.setAttribute("tntVO_req", tntVO); // 含有輸入格式錯誤的tntVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/tnt/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
					System.out.println(tnt_no + ": 登入成功");
					req.removeAttribute("tntVO_req"); // 移除錯誤轉交用的req scope的"tntVO"

					String tnt_name = tntSvc.getOneTntProfile(tnt_no).getTnt_name().trim(); // 幫泓元存session
					Boolean tnt_sex = tntSvc.getOneTntProfile(tnt_no).getTnt_sex(); // 幫泓元存session
					TntVO tntVO_session = new TntVO();
					tntVO_session.setTnt_email(tnt_email);
					tntVO_session.setTnt_name(tnt_name);
					tntVO_session.setTnt_sex(tnt_sex);

					HttpSession session = req.getSession();
					session.removeAttribute("tnt_no");
					session.removeAttribute("lld_no");
					session.removeAttribute("tntVO");
					session.removeAttribute("lldVO");
					session.setAttribute("tnt_no", tnt_no); // *工作1: 在session內做已經登入過的標識
					session.setAttribute("tntVO", tntVO_session);

					String location = (String) session.getAttribute("location");
					if (location != null) { // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						System.out.println("Servlet-redirect:" + location);
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
					// *工作3: (-->如無來源網頁:則重導至login_success.jsp)
					res.sendRedirect(req.getContextPath() + "/front-end/index/index.jsp");
				}

			} catch (Exception e) {
				System.out.println("登入Exception: " + e.getMessage());
			}
		}

		if ("register".equals(action)) { // 來自Register.jsp的請求 - ajax_register(formData)
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_email = req.getParameter("tnt_email");
				String tnt_acc = getAuthCode(); // 信箱驗證碼
				String tnt_pwd = req.getParameter("tnt_pwd");
				String tnt_id = req.getParameter("tnt_id");
				String tnt_name = req.getParameter("tnt_name");
				java.sql.Date tnt_birth = java.sql.Date.valueOf(req.getParameter("tnt_birth").trim());
				Boolean tnt_sex = Boolean.parseBoolean(req.getParameter("tnt_sex"));
				String tnt_mobile = req.getParameter("tnt_mobile");
				String tnt_city = req.getParameter("tnt_city");
				String tnt_dist = req.getParameter("tnt_dist");
				String tnt_add = req.getParameter("tnt_add");
//				System.out.println("註冊後端驗證: " + errorMsgs);

				Part part = req.getPart("tnt_pic");
				byte[] tnt_pic = null;
				System.out.println("part.getSize():" + part.getSize());
				Boolean isupdatePic = false;
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					tnt_pic = getPictureByteArray(in);
					isupdatePic = true;
				}

				TntVO tntVO = new TntVO();
				tntVO.setTnt_email(tnt_email);
				tntVO.setTnt_acc(tnt_acc);
				tntVO.setTnt_pwd(tnt_pwd);
				tntVO.setTnt_id(tnt_id);
				tntVO.setTnt_name(tnt_name);
				tntVO.setTnt_birth(tnt_birth);
				tntVO.setTnt_sex(tnt_sex);
				tntVO.setTnt_mobile(tnt_mobile);
				tntVO.setTnt_city(tnt_city);
				tntVO.setTnt_dist(tnt_dist);
				tntVO.setTnt_add(tnt_add);
				if (isupdatePic) {
					tntVO.setTnt_pic(tnt_pic);
				}

				/*************************** 2.開始新增資料 ***************************************/
				TntService tntSvc = new TntService();
				if (isupdatePic) {
					tntVO = tntSvc.addTnt(tnt_email, tnt_acc, tnt_pwd, tnt_id, tnt_name, tnt_birth, tnt_sex, tnt_mobile,
							tnt_city, tnt_dist, tnt_add, tnt_pic);
				}
				if (!isupdatePic) {
					tntVO = tntSvc.addTnt(tnt_email, tnt_acc, tnt_pwd, tnt_id, tnt_name, tnt_birth, tnt_sex, tnt_mobile,
							tnt_city, tnt_dist, tnt_add);
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String emailVrfLink = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
						+ req.getContextPath() + req.getServletPath() + "?action=emailVrf&tnt_email=" + tnt_email
						+ "&code=" + tnt_acc;
				MailService mailService = new MailService();
				// 記得要改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				String tnt_email_to = "ea103g2@gmail.com";
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				Boolean successSendMail = mailService.sendMail_vrf(tnt_email_to, "愛租會員信箱驗證", "tnt", emailVrfLink);
				if (successSendMail) {
					out = res.getWriter();
					out.print("true");
					out.close();
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("註冊失敗:" + e.getMessage());
				out.print("false");
				out.close();
			}
		}

		if ("emailVrf".equals(action)) {
			System.out.println("action: " + action);
			List<String> emailVrfMsgs = new LinkedList<String>();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_email = req.getParameter("tnt_email");
				String tnt_acc = req.getParameter("code"); // 信箱驗證碼

				/*************************** 2.開始驗證資料 ***************************************/
				TntService tntSvc = new TntService();
				List<TntVO> list = tntSvc.getAllProfile();
				Boolean validateEmail = false;
				String tnt_no = "";

				for (TntVO tntVO : list) {
					if (tnt_email.equals(tntVO.getTnt_email())) {
						validateEmail = true;
						tnt_no = tntVO.getTnt_no();
					}
				}
				if (validateEmail) {
					TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);
					if (tntVO.getTnt_acc().equals(tnt_acc)) {
						tntSvc.updateTntStatus(tnt_no, 1);
					}
				}
				emailVrfMsgs.add("success emailVrf");
				req.setAttribute("emailVrfMsgs", emailVrfMsgs); // 重導無法取得req的參數 forward或include才可以喔

				/*************************** 3.驗證完成,準備轉交(Send the Success view) ***********/
				System.out.println("信箱驗證完成: 轉交login.jsp");
//				res.sendRedirect(req.getContextPath() + "/front-end/index/tnt/login.jsp");

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/index/tnt/login.jsp");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("信箱驗證失敗:" + e.getMessage());
//				out.print("false");

			}
		}

		// 來自forgetPwd.jsp的請求 - ajax_forgetPwd
		if ("forgetPwd".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			out = res.getWriter();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_email = req.getParameter("tnt_email");
				System.out.println(tnt_email);

				/*************************** 2.開始比對資料 ***************************************/
				TntService tntSvc = new TntService();
				List<TntVO> list = tntSvc.getAllAccount();//
				String resString = "";
				Boolean validateEmail = false;
				String tnt_no = "";
				for (TntVO tntVO : list) {
					if (tnt_email.equals(tntVO.getTnt_email())) {
//						resString = "true";
						validateEmail = true;
						tnt_no = tntVO.getTnt_no();
					}
				}
				if (!validateEmail) {
					resString = "false";
					out.print(resString);
					out.close();
					return;
				}
				if (validateEmail) {
					TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);
					String tnt_name = tntVO.getTnt_name();
					String tnt_pwd = getAuthCode();
					String indexPage = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
							+ req.getContextPath() + "/front-end/index/tnt/login.jsp";
//					String messageText = "Hello! " + tnt_name + "\n"+ "您的新密碼:  " + tnt_pwd + "\n" + "請登入後至會員專區修改密碼";
//					MailService0 mailService = new MailService0();
					MailService mailService = new MailService();

					// 記得要改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					tnt_email = "ea103g2@gmail.com";
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//					Boolean successSendMail =  mailService.sendMail(tnt_email, "新密碼通知", messageText);
					Boolean successSendMail = mailService.sendMail(tnt_email, "新密碼通知", tnt_name, tnt_pwd, "tnt",
							indexPage);
					if (successSendMail) {
						resString = "true";
						tntSvc.updateTntPwd(tnt_no, tnt_pwd);
						out.print(resString);
						out.close();
						return;
					}
				}

			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
				System.out.println("忘記密碼Exception:" + e.getMessage());
			}
		}

// ===================================以下來自會員專區 info.jsp==================================================

		// 來自info.jsp的請求 - ajax_infoUpdateProfile(formData)
		if ("infoUpdateProfile".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_email = req.getParameter("tnt_email");
				String tnt_acc = "yjwuws17";
				String tnt_id = req.getParameter("tnt_id");
				String tnt_name = req.getParameter("tnt_name");
				java.sql.Date tnt_birth = java.sql.Date.valueOf(req.getParameter("tnt_birth").trim());
				Boolean tnt_sex = Boolean.parseBoolean(req.getParameter("tnt_sex"));
				String tnt_mobile = req.getParameter("tnt_mobile");
				String tnt_city = req.getParameter("tnt_city");
				String tnt_dist = req.getParameter("tnt_dist");
				String tnt_add = req.getParameter("tnt_add");

				/*************************** 2.開始修改資料 ***************************************/
				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");

				TntService tntSvc = new TntService();
				TntVO tntVO_origin = tntSvc.getOneTntProfile(tnt_no);

				String tnt_pwd = tntVO_origin.getTnt_pwd();
				Integer tnt_status = tntVO_origin.getTnt_status();

				tntSvc.updateTntProfile(tnt_no, tnt_email, tnt_acc, tnt_pwd, tnt_id, tnt_name, tnt_birth, tnt_sex,
						tnt_mobile, tnt_city, tnt_dist, tnt_add, tnt_status);

				out = res.getWriter();
				out.print("true");

			} catch (Exception e) {
//				errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("info profile 修改失敗:" + e.getMessage());
			}
		}

		// 來自info.jsp的請求 - ajax_infoPicUpload(formData)
		if ("infoPicUpload".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Part part = req.getPart("tnt_pic");
				System.out.println("part.getSize():" + part.getSize());
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					byte[] tnt_pic = getPictureByteArray(in);

					TntVO tntVO = new TntVO();
					tntVO.setTnt_pic(tnt_pic);

					HttpSession session = req.getSession();
					String tnt_no = (String) session.getAttribute("tnt_no");

					TntService tntSvc = new TntService();
					tntSvc.updateTntPic(tnt_no, tnt_pic);

					out = res.getWriter();
					out.print("true");
					out.close();
				}

			} catch (Exception e) {
//				errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("info profile 修改失敗:" + e.getMessage());
			}
		}

		if ("infoChgPwd".equals(action)) { // 來自info.jsp infoChgPwd的請求-form post
			System.out.println("action: " + action);

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");
				String tnt_pwd = req.getParameter("tnt_pwd");
				String tnt_pwd_new = req.getParameter("tnt_pwd_new");

				/*************************** 2.開始比對登入資料 ***************************************/
				// 【檢查該帳號 , 密碼是否有效】
				TntService tntSvc = new TntService();
				TntVO tntVO_origin = tntSvc.getOneTntProfile(tnt_no);
				String tnt_pwd_origin = tntVO_origin.getTnt_pwd();
				out = res.getWriter();
				if (tnt_pwd_origin.equals(tnt_pwd)) {
					System.out.println("忘記密碼比對成功");
					tntSvc.updateTntPwd(tnt_no, tnt_pwd_new);
					out.print("true");
				} else {
					out.print("false");
				}
			} catch (Exception e) {
				System.out.println("infoChgPwd Exception: " + e.getMessage());
			}
		}

// ===================================以下來自我的錢包 pocket.jsp=====================================================

		// 來自pocket.jsp的請求 - ajax_balanceWithdraw(formData)
		if ("balanceWithdraw".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				out = res.getWriter();
				int tnt_pocket_withdraw = Integer.valueOf(req.getParameter("pocket_withdraw"));
				System.out.println(tnt_pocket_withdraw);

				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");
//
				System.out.println(tnt_no);
//
				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntPocket(tnt_no);
				int tnt_balance = tntVO.getTnt_balance();
				if (tnt_balance < tnt_pocket_withdraw) { // 提領金額大於餘額 退回
					out.print("false");
					return;
				}
				/*************************** 2.開始修改資料 ***************************************/
				if (tnt_balance > tnt_pocket_withdraw) {
					tnt_balance = tnt_balance - tnt_pocket_withdraw;
					tntSvc.updateTntPocket(tnt_no, tnt_balance);
					out = res.getWriter();
					out.print("true");
					return;
				}

			} catch (Exception e) {
//								errorMsgs.add("pocket提領失敗:" + e.getMessage());
				System.out.println("pocket 提領 失敗:" + e.getMessage());
				out = res.getWriter();
				out.print("false");
			}
		}

		// 來自pocket.jsp的請求 - ajax_balanceDeposit(formData)
		if ("balanceDeposit".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				out = res.getWriter();
				int tnt_pocket_deposit = Integer.valueOf(req.getParameter("pocket_deposit"));

				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");
				TntService tntSvc = new TntService();
				TntVO tntVO = tntSvc.getOneTntPocket(tnt_no);
				int tnt_balance = tntVO.getTnt_balance();
//				if (tnt_pocket_deposit < 0) { // 暫時多寫的
//					out.print("false");
//					return;
//				}
				/*************************** 2.開始修改資料 ***************************************/
//				if (tnt_pocket_deposit > 0) {
//					tnt_balance = tnt_balance + tnt_pocket_deposit;
//					tntSvc.updateTntPocket(tnt_no, tnt_balance);
//					out.print("true");
//					return;
//				}
				CashService cashSvc = new CashService();
				String cash_no="";
				for(int i =0;i<25;i++) {
					java.sql.Date cash_date = new java.sql.Date(new java.util.Date().getTime());
					
					cash_no = cashSvc.addCash(cash_date, tnt_no, CashVO.cashIn, CashVO.tntIn_Deposit, tnt_pocket_deposit,1);
				}
				
//				System.out.println(cash_no);
				tnt_balance = tnt_balance + tnt_pocket_deposit;
				tntSvc.updateTntPocket(tnt_no, tnt_balance);
				
				List<CashVO> list = cashSvc.getOneCashlogs(tnt_no);
				for(CashVO cashVO : list) {
					String cash_no1 = cashVO.getMem_no();
					System.out.println("tntservlet2-cashVO list"+cash_no1);
				}
				
				
				
				String returnURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
				+ req.getContextPath() + req.getServletPath();
//				System.out.println(returnURL);
				String clientBackURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
				+ req.getContextPath() + "/front-end/tnt/pocket.jsp";
//				System.out.println(clientBackURL);
				
				//產生綠界訂單
				AioCheckOutOneTime checkoutonetime = new AioCheckOutOneTime();
				checkoutonetime.setMerchantTradeNo(cash_no); // 訂單編號

				
				checkoutonetime.setItemName("iZu Landlord Deposit"); // 商品名稱
				checkoutonetime.setTotalAmount(Integer.toString(tnt_pocket_deposit)); // 總金額
					
				java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				checkoutonetime.setMerchantTradeDate(sdf.format(time));
//				checkoutonetime.setReturnURL("http://localhost:8081/EA103G2/tnt/TntServlet2");
				checkoutonetime.setReturnURL(returnURL);
//				checkoutonetime.setClientBackURL("http://localhost:8081/EA103G2/front-end/index/index.jsp");
				checkoutonetime.setClientBackURL(clientBackURL);
				checkoutonetime.setTradeDesc("ProductDetailDeposit"); // 商品詳情
				
				
				AllInOne all = new AllInOne("");
				String form = all.aioCheckOut(checkoutonetime, null);
				
				//轉送綠界訂單
				out.print("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"	<meta charset=\"UTF-8\">\r\n" + 
						"	<title>Document</title>\r\n" + 
						"</head>\r\n" + 
						"<body>");
				out.print(form);
				out.print("</body>" +
				"</html>");
				
				
			} catch (Exception e) {
//				errorMsgs.add("pocket 儲值失敗:" + e.getMessage());
				System.out.println("pocket 儲值失敗:" + e.getMessage());
				out = res.getWriter();
				out.print("false");
				out.close();
			}
		}

		// 來自pocket.jsp的請求 - ajax_pocketUpdateBankCard(formData)
		if ("infoUpdateBankCard".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String tnt_bank = req.getParameter("tnt_bank");
				System.out.println(tnt_bank);
				String tnt_bankbranch = req.getParameter("tnt_bankbranch");
				System.out.println(tnt_bankbranch);

				String tnt_bankacc = req.getParameter("tnt_bankacc");
				System.out.println(tnt_bankacc);
				String tnt_card = req.getParameter("tnt_card");
				System.out.println(tnt_card);
//				Integer tnt_cardsvc = Integer.valueOf(req.getParameter("tnt_cardsvc"));
				String tnt_cardsvc = req.getParameter("tnt_cardsvc");
				System.out.println(tnt_cardsvc);
				String tnt_carddueStr = req.getParameter("tnt_carddue");
				tnt_carddueStr = tnt_carddueStr + "-01";
				System.out.println(tnt_carddueStr);
				java.sql.Date tnt_carddue = java.sql.Date.valueOf(tnt_carddueStr);

				System.out.println(tnt_carddue);

				/*************************** 2.開始修改資料 ***************************************/
				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");

				System.out.println(tnt_no);

				TntService tntSvc = new TntService();
				tntSvc.updateTntBankCard(tnt_no, tnt_bank, tnt_bankbranch, tnt_bankacc, tnt_card, tnt_cardsvc,
						tnt_carddue);

				out = res.getWriter();
				out.print("true");

			} catch (Exception e) {
//						errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("pocket 銀行信用卡 修改失敗:" + e.getMessage());
				out = res.getWriter();
				out.print("false");
			}
		}

// ===================================以下來自身分驗證 verify.jsp=====================================================

		// 來自vrf.jsp的請求 - ajax_vrfPicsUpload(formData)
		if ("vrfPicsUpload".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);

			ArrayList<byte[]> byteArray = new ArrayList<byte[]>();
			try {
				ArrayList<Part> parts = new ArrayList<Part>(req.getParts());
//				System.out.println(parts.size());
				for (int i = 0; i < parts.size() - 1; i++) {
//					System.out.println(i);
					Part part = parts.get(i);
					InputStream in = part.getInputStream();
					byte[] byte_pic = getPictureByteArray(in);
//					System.out.println(byte_pic);
					byteArray.add(i, byte_pic);
				}
				byte[] tnt_id_picf = byteArray.get(0);
//				System.out.println(tnt_id_picf);
				byte[] tnt_id_picb = byteArray.get(1);
//				System.out.println(tnt_id_picb);
				byte[] tnt_id_pic2 = byteArray.get(2);
//				System.out.println(tnt_id_pic2);

//
				HttpSession session = req.getSession();
				String tnt_no = (String) session.getAttribute("tnt_no");

				System.out.println(tnt_no);

				TntService tntSvc = new TntService();
				tntSvc.updateTntVrfPics(tnt_no, tnt_id_picf, tnt_id_picb, tnt_id_pic2, 1);

				out = res.getWriter();
				out.print("true");
				out.close();

			} catch (Exception e) {
//						errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("vrf pics 修改失敗:" + e.getMessage());
			}
		}

	}

	// ===================private methods===========================
	// 登入驗證
	private String loginValidate(List<TntVO> list, String tnt_email, String tnt_pwd) {
		String tnt_no = "EmailnotRegisterYet";

		for (TntVO tntVO : list) {
			if (tnt_email.equals(tntVO.getTnt_email())) { // 有該信箱帳號, 進一步比對密碼
				tnt_no = "Pwdfalse";
				if (tnt_pwd.equals(tntVO.getTnt_pwd())) { // 帳密驗證成功
					tnt_no = tntVO.getTnt_no();
					return tnt_no;
				}
			}
		}
		return tnt_no;
	}

	// 身分證驗證
	private Boolean idvalidate(String tnt_id) {
		String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO"; // 字母代號對照表

		char[] c = tnt_id.toUpperCase().toCharArray(); // 建立 c 陣列，同時將s字串轉大寫後，轉成字元陣列放入 c 陣列
		int[] ID = new int[c.length]; // 建立一個運算用的整數陣列，空間為 c 的字元個數
		// 驗證首位字母是否合法 (該字元是否能在checkHead[]找到), 驗證第一位是否為 1 or 2 (1=男生, 2=女生)
		int sum = 0;
		ID[0] = checkHead.indexOf(c[0]) + 10; // 第一個英文字運算
		sum += ID[0] / 10; // .. 將商數加總 sum += ID[0]/10
		ID[0] %= 10; // .. 取餘數放回 ID[0] 以便之後的運算
		for (int i = 1; i < 10; i++) // 將身分證後9碼轉成整數型態 (ASCII碼-48)
			ID[i] = (int) c[i] - 48;
		for (int i = 0; i < 9; i++) { // 代入公式:
			ID[i] *= (9 - i); // 總和 sum += (ID[0])*9 + ID[1]*8 + ID[2]*7 + ... + ID[9]*1
			sum += ID[i];
		}
		// 檢查(10-sum%10)是否相等於檢查碼，且 sum%10(餘數)為0時，檢查碼為0 => (10-sum%10)%10
		if ((10 - sum % 10) % 10 == ID[9])
			return true;
		else
			return false;
	}

	// 使用byte[]方式-讀取二位元資料
	private byte[] getPictureByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 此資料流會把write的位元資料存到一個內建的byte[]
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		in.close();

		return baos.toByteArray(); // toByteArray()可以讓我們取得這個資料流內建的byte[]
	}

	// 信用卡驗證
	private boolean CC_Check(String ccNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	// 產生亂數8碼
	private String getAuthCode() {
		int[] unicodeArr = new int[26 + 26 + 10];
		int element = (int) '0';
		for (int i = 0; i < unicodeArr.length; i++) {
			unicodeArr[i] = element;
			element++;
			element = element == 58 ? 65 : element;
			element = element == 91 ? 97 : element;
		}

		StringBuffer code = new StringBuffer();
		for (int i = 1; i <= 8; i++) {
			code.append((char) unicodeArr[new java.util.Random().nextInt(unicodeArr.length)]);
		}
		return code.toString();
	}

}
