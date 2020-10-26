package com.lld.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
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

import com.lld.model.*;

import tools.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class LldServlet2 extends HttpServlet {

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
				String lld_email = req.getParameter("lld_email");
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (lld_email == null || lld_email.trim().length() == 0) {
//					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!lld_email.trim().matches(emailReg)) {
					System.out.println("電子信箱錯誤");
//					errorMsgs.add("電子信箱錯誤");
				}

				String lld_pwd = req.getParameter("lld_pwd");
				String pwdReg = "^[\\w]{8,16}$";
				if (lld_pwd == null || lld_pwd.trim().length() == 0) {
//					errorMsgs.add("密碼: 請勿空白");
				} else if (!lld_pwd.trim().matches(pwdReg)) {
					System.out.println("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
//					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				}

				LldVO lldVO = new LldVO();
				lldVO.setLld_email(lld_email);
				lldVO.setLld_pwd(lld_pwd);

				/*************************** 2.開始比對登入資料 ***************************************/
				// 【檢查該帳號 , 密碼是否有效】
				LldService lldSvc = new LldService();
				List<LldVO> list = lldSvc.getAllAccount();
				String lld_no = loginValidate(list, lld_email, lld_pwd);

				if ("EmailnotRegisterYet".equals(lld_no)) { // 信箱尚未註冊
					errorMsgs.add("信箱尚未註冊");
					System.out.println(lld_no + ": 信箱尚未註冊");
					req.setAttribute("errorMsgs", errorMsgs);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lldVO_req", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/lld/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
				}
				if ("Pwdfalse".equals(lld_no)) { // 密碼錯誤
					errorMsgs.add("帳號密碼錯誤");
					System.out.println(lld_no + ": 帳號密碼錯誤");

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lldVO_req", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/lld/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
				}
				if (lld_no.substring(0, 3).equalsIgnoreCase("lld")) { // 登入成功
					System.out.println(lld_no + ": 登入成功");
//					lldVO.setLld_no(lld_no);
					req.removeAttribute("lldVO_req"); // 移除錯誤轉交用的req scope的"lldVO"

					String lld_name = lldSvc.getOneLldProfile(lld_no).getLld_name(); // 幫泓元存session
					Boolean lld_sex = lldSvc.getOneLldProfile(lld_no).getLld_sex(); // 幫泓元存session
					LldVO lldVO_session = new LldVO();
					lldVO_session.setLld_email(lld_email);
					lldVO_session.setLld_name(lld_name);
					lldVO_session.setLld_sex(lld_sex);

					HttpSession session = req.getSession();
					session.setAttribute("lld_no", lld_no); // *工作1: 在session內做已經登入過的標識
					session.setAttribute("lldVO", lldVO_session);

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
				String lld_email = req.getParameter("lld_email");
				String lld_acc = getAuthCode(); // 信箱驗證碼
				String lld_pwd = req.getParameter("lld_pwd");
				String lld_id = req.getParameter("lld_id");
				String lld_name = req.getParameter("lld_name");
				java.sql.Date lld_birth = java.sql.Date.valueOf(req.getParameter("lld_birth").trim());
				Boolean lld_sex = Boolean.parseBoolean(req.getParameter("lld_sex"));
				String lld_mobile = req.getParameter("lld_mobile");
				String lld_city = req.getParameter("lld_city");
				String lld_dist = req.getParameter("lld_dist");
				String lld_add = req.getParameter("lld_add");
//				System.out.println("註冊後端驗證: " + errorMsgs);

				Part part = req.getPart("lld_pic");
				byte[] lld_pic = null;
				System.out.println("part.getSize():" + part.getSize());
				Boolean isupdatePic = false;
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					lld_pic = getPictureByteArray(in);
					isupdatePic = true;
				}

				LldVO lldVO = new LldVO();
				lldVO.setLld_email(lld_email);
				lldVO.setLld_acc(lld_acc);
				lldVO.setLld_pwd(lld_pwd);
				lldVO.setLld_id(lld_id);
				lldVO.setLld_name(lld_name);
				lldVO.setLld_birth(lld_birth);
				lldVO.setLld_sex(lld_sex);
				lldVO.setLld_mobile(lld_mobile);
				lldVO.setLld_city(lld_city);
				lldVO.setLld_dist(lld_dist);
				lldVO.setLld_add(lld_add);
				if (isupdatePic) {
					lldVO.setLld_pic(lld_pic);
				}

				/*************************** 2.開始新增資料 ***************************************/
				LldService lldSvc = new LldService();
				if (isupdatePic) {
					lldVO = lldSvc.addLld(lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex, lld_mobile,
							lld_city, lld_dist, lld_add, lld_pic);
				}
				if (!isupdatePic) {
					lldVO = lldSvc.addLld(lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex, lld_mobile,
							lld_city, lld_dist, lld_add);
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String emailVrfLink = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
						+ req.getContextPath() + req.getServletPath() + "?action=emailVrf&lld_email=" + lld_email
						+ "&code=" + lld_acc;
				MailService mailService = new MailService();
				// 記得要改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				String lld_email_to = "ea103g2@gmail.com";
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				Boolean successSendMail = mailService.sendMail_vrf(lld_email_to, "愛租會員信箱驗證", "lld", emailVrfLink);
				if (successSendMail) {
					out = res.getWriter();
					out.print("true");
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add("註冊失敗:" + e.getMessage());
				System.out.println("註冊失敗:" + e.getMessage());
				out.print("false");

			}
		}

		if ("emailVrf".equals(action)) {
			System.out.println("action: " + action);
			List<String> emailVrfMsgs = new LinkedList<String>();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lld_email = req.getParameter("lld_email");
				String lld_acc = req.getParameter("code"); // 信箱驗證碼

				/*************************** 2.開始驗證資料 ***************************************/
				LldService lldSvc = new LldService();
				List<LldVO> list = lldSvc.getAllProfile();
				Boolean validateEmail = false;
				String lld_no = "";

				for (LldVO lldVO : list) {
					if (lld_email.equals(lldVO.getLld_email())) {
						validateEmail = true;
						lld_no = lldVO.getLld_no();
					}
				}
				if (validateEmail) {
					LldVO lldVO = lldSvc.getOneLldProfile(lld_no);
					if (lldVO.getLld_acc().equals(lld_acc)) {
						lldSvc.updateLldStatus(lld_no, 1);
					}
				}
				emailVrfMsgs.add("success emailVrf");
				req.setAttribute("emailVrfMsgs", emailVrfMsgs); // 重導無法取得req的參數 forward或include才可以喔

				/*************************** 3.驗證完成,準備轉交(Send the Success view) ***********/
				System.out.println("信箱驗證完成: 轉交login.jsp");
//				res.sendRedirect(req.getContextPath() + "/front-end/index/lld/login.jsp");

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/index/lld/login.jsp");
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
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lld_email = req.getParameter("lld_email");
				System.out.println(lld_email);

				/*************************** 2.開始比對資料 ***************************************/
				LldService lldSvc = new LldService();
				List<LldVO> list = lldSvc.getAllAccount();//
				String resString = "";
				Boolean validateEmail = false;
				String lld_no = "";
				for (LldVO lldVO : list) {
					if (lld_email.equals(lldVO.getLld_email())) {
//						resString = "true";
						validateEmail = true;
						lld_no = lldVO.getLld_no();
					}
				}
				if (!validateEmail) {
					resString = "false";
				}
				if (validateEmail) {
					LldVO lldVO = lldSvc.getOneLldProfile(lld_no);
					String lld_name = lldVO.getLld_name();
					String lld_pwd = getAuthCode();
					String indexPage = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
							+ req.getContextPath() + "/front-end/index/index.jsp";
//					String messageText = "Hello! " + lld_name + "\n"+ "您的新密碼:  " + lld_pwd + "\n" + "請登入後至會員專區修改密碼";
//					MailService0 mailService = new MailService0();
					MailService mailService = new MailService();

					// 記得要改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					lld_email = "ea103g2@gmail.com";
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//					Boolean successSendMail =  mailService.sendMail(lld_email, "新密碼通知", messageText);
					Boolean successSendMail = mailService.sendMail(lld_email, "新密碼通知", lld_name, lld_pwd, "lld",
							indexPage);
					if (successSendMail) {
						resString = "true";
						lldSvc.updateLldPwd(lld_no, lld_pwd);
						out = res.getWriter();
						out.print(resString);
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
				String lld_email = req.getParameter("lld_email");
				String lld_acc = "yjwuws17";
				String lld_id = req.getParameter("lld_id");
				String lld_name = req.getParameter("lld_name");
				java.sql.Date lld_birth = java.sql.Date.valueOf(req.getParameter("lld_birth").trim());
				Boolean lld_sex = Boolean.parseBoolean(req.getParameter("lld_sex"));
				String lld_mobile = req.getParameter("lld_mobile");
				String lld_city = req.getParameter("lld_city");
				String lld_dist = req.getParameter("lld_dist");
				String lld_add = req.getParameter("lld_add");

				/*************************** 2.開始修改資料 ***************************************/
				HttpSession session = req.getSession();
				String lld_no = (String) session.getAttribute("lld_no");

//				System.out.println(lld_no);

				LldService lldSvc = new LldService();
				LldVO lldVO_origin = lldSvc.getOneLldProfile(lld_no);

//				System.out.println(0);

				String lld_pwd = lldVO_origin.getLld_pwd();
				Integer lld_status = lldVO_origin.getLld_status();
//				System.out.println(lld_pwd);
//				System.out.println(lld_status);

				lldSvc.updateLldProfile(lld_no, lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex,
						lld_mobile, lld_city, lld_dist, lld_add, lld_status);

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
				Part part = req.getPart("lld_pic");
				System.out.println("part.getSize():" + part.getSize());
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					byte[] lld_pic = getPictureByteArray(in);

//					System.out.println("1");

					LldVO lldVO = new LldVO();
					lldVO.setLld_pic(lld_pic);

//					System.out.println("2");

					HttpSession session = req.getSession();
					String lld_no = (String) session.getAttribute("lld_no");

//					System.out.println(lld_no);

					LldService lldSvc = new LldService();
					lldSvc.updateLldPic(lld_no, lld_pic);

//					System.out.println("3");

					out = res.getWriter();
					out.print("true");
//					out.close();
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
				String lld_no = (String) session.getAttribute("lld_no");
				String lld_pwd = req.getParameter("lld_pwd");
				String lld_pwd_new = req.getParameter("lld_pwd_new");

//				System.out.println(lld_pwd_new);

				/*************************** 2.開始比對登入資料 ***************************************/
				// 【檢查該帳號 , 密碼是否有效】
				LldService lldSvc = new LldService();
//				System.out.println("1");
//				LldVO lldVO_origin = lldSvc.getOneLldAccount(lld_no);
				LldVO lldVO_origin = lldSvc.getOneLldProfile(lld_no);
//				System.out.println("2");
				String lld_pwd_origin = lldVO_origin.getLld_pwd();
//				System.out.println("3");
				out = res.getWriter();
				if (lld_pwd_origin.equals(lld_pwd)) {
					System.out.println("忘記密碼比對成功");
					lldSvc.updateLldPwd(lld_no, lld_pwd_new);
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
				int lld_pocket_withdraw = Integer.valueOf(req.getParameter("pocket_withdraw"));
				System.out.println(lld_pocket_withdraw);

				HttpSession session = req.getSession();
				String lld_no = (String) session.getAttribute("lld_no");
//
				System.out.println(lld_no);
//
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldPocket(lld_no);
				int lld_balance = lldVO.getLld_balance();
				if (lld_balance < lld_pocket_withdraw) { // 提領金額大於餘額 退回
					out.print("false");
					return;
				}
				/*************************** 2.開始修改資料 ***************************************/
				if (lld_balance > lld_pocket_withdraw) {
					lld_balance = lld_balance - lld_pocket_withdraw;
					lldSvc.updateLldPocket(lld_no, lld_balance);
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
				int lld_pocket_deposit = Integer.valueOf(req.getParameter("pocket_deposit"));
				System.out.println(lld_pocket_deposit);

				HttpSession session = req.getSession();
				String lld_no = (String) session.getAttribute("lld_no");
				//
				System.out.println(lld_no);
				//
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldPocket(lld_no);
				int lld_balance = lldVO.getLld_balance();
				if (lld_pocket_deposit < 0) { // 暫時多寫的
					out.print("false");
					return;
				}
				/*************************** 2.開始修改資料 ***************************************/
				if (lld_pocket_deposit > 0) {
					lld_balance = lld_balance + lld_pocket_deposit;
					lldSvc.updateLldPocket(lld_no, lld_balance);
					out = res.getWriter();
					out.print("true");
					return;
				}
			} catch (Exception e) {
//				errorMsgs.add("pocket 儲值失敗:" + e.getMessage());
				System.out.println("pocket 儲值失敗:" + e.getMessage());
				out = res.getWriter();
				out.print("false");
			}
		}

		// 來自pocket.jsp的請求 - ajax_pocketUpdateBankCard(formData)
		if ("infoUpdateBankCard".equals(action)) {
			System.out.println("action: " + action);
			List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lld_bank = req.getParameter("lld_bank");
				System.out.println(lld_bank);
				String lld_bankbranch = req.getParameter("lld_bankbranch");
				System.out.println(lld_bankbranch);

				String lld_bankacc = req.getParameter("lld_bankacc");
				System.out.println(lld_bankacc);
				String lld_card = req.getParameter("lld_card");
				System.out.println(lld_card);
//				Integer lld_cardsvc = Integer.valueOf(req.getParameter("lld_cardsvc"));
				String lld_cardsvc = req.getParameter("lld_cardsvc");
				System.out.println(lld_cardsvc);
				String lld_carddueStr = req.getParameter("lld_carddue");
				lld_carddueStr = lld_carddueStr + "-01";
				System.out.println(lld_carddueStr);
				java.sql.Date lld_carddue = java.sql.Date.valueOf(lld_carddueStr);

				System.out.println(lld_carddue);

				/*************************** 2.開始修改資料 ***************************************/
				HttpSession session = req.getSession();
				String lld_no = (String) session.getAttribute("lld_no");

				System.out.println(lld_no);

				LldService lldSvc = new LldService();
				lldSvc.updateLldBankCard(lld_no, lld_bank, lld_bankbranch, lld_bankacc, lld_card, lld_cardsvc,
						lld_carddue);

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
				for (int i=0; i<parts.size()-1; i++) {
//					System.out.println(i);
					Part part = parts.get(i);
					InputStream in = part.getInputStream();
					byte[] byte_pic = getPictureByteArray(in);
//					System.out.println(byte_pic);
					byteArray.add(i, byte_pic);
				}
				byte[] lld_id_picf = byteArray.get(0);
//				System.out.println(lld_id_picf);
				byte[] lld_id_picb = byteArray.get(1);
//				System.out.println(lld_id_picb);
				byte[] lld_id_pic2 = byteArray.get(2);
//				System.out.println(lld_id_pic2);

//
					HttpSession session = req.getSession();
					String lld_no = (String) session.getAttribute("lld_no");

							System.out.println(lld_no);

					LldService lldSvc = new LldService();
					lldSvc.updateLldVrfPics(lld_no, lld_id_picf, lld_id_picb, lld_id_pic2, 1 );

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
	private String loginValidate(List<LldVO> list, String lld_email, String lld_pwd) {
		String lld_no = "EmailnotRegisterYet";

		for (LldVO lldVO : list) {
			if (lld_email.equals(lldVO.getLld_email())) { // 有該信箱帳號, 進一步比對密碼
				lld_no = "Pwdfalse";
				if (lld_pwd.equals(lldVO.getLld_pwd())) { // 帳密驗證成功
					lld_no = lldVO.getLld_no();
					return lld_no;
				}
			}
		}
		return lld_no;
	}

	// 身分證驗證
	private Boolean idvalidate(String lld_id) {
		String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO"; // 字母代號對照表

		char[] c = lld_id.toUpperCase().toCharArray(); // 建立 c 陣列，同時將s字串轉大寫後，轉成字元陣列放入 c 陣列
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
