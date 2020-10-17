package com.lld.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class LldServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = null;
		System.out.println("0"+action);
		
		if ("login".equals(action)) {
			System.out.println("1:" + action);
//			String errorMsgs = null;
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
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
				System.out.println(lld_email);

				String lld_pwd = req.getParameter("lld_pwd");
				String pwdReg = "^[\\w]{8,16}$";
				if (lld_pwd == null || lld_pwd.trim().length() == 0) {
//					errorMsgs.add("密碼: 請勿空白");
				} else if (!lld_pwd.trim().matches(pwdReg)) {
					System.out.println("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
//					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				}
//				System.out.println(lld_pwd);

				LldVO lldVO = new LldVO();
				lldVO.setLld_email(lld_email);
				lldVO.setLld_pwd(lld_pwd);

				/*************************** 2.開始比對登入資料 ***************************************/
				// 【檢查該帳號 , 密碼是否有效】
				LldService lldSvc = new LldService();
				List<LldVO> list = lldSvc.getAllAccount();
				String lld_no = loginValidate(list, lld_email, lld_pwd);

				if ("EmailnotRegisterYet".equals(lld_no)) { // 信箱尚未註冊
//					errorMsgs = "信箱尚未註冊";
					errorMsgs.add("信箱尚未註冊");
					System.out.println("EmailnotRegisterYet:" + lld_no);
					req.setAttribute("errorMsgs", errorMsgs);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lldVO", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/lld/login.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
				}
				if ("Pwdfalse".equals(lld_no)) { // 密碼錯誤
					errorMsgs.add("帳號密碼錯誤");
//					errorMsgs = "帳號密碼錯誤";
					System.out.println("Pwdfalse:" + lld_no);
					System.out.println(errorMsgs);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lldVO", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
						System.out.println("錯誤重導1");
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/index/lld/login.jsp");
						System.out.println("錯誤重導2");
						failureView.forward(req, res);

						System.out.println("錯誤重導3");
						return; // 程式中斷
					}
				}
				if (lld_no.substring(0, 3).equalsIgnoreCase("lld")) { // 登入成功
					System.out.println("Success login:" + lld_no);
					lldVO.setLld_no(lld_no);
					req.setAttribute("lldVO", lldVO);

					HttpSession session = req.getSession();
					session.setAttribute("lld_no", lld_no); // *工作1: 才在session內做已經登入過的標識
					String location = (String) session.getAttribute("location");
					if (location != null) {
						System.out.println("Servlet-redirect:" +location );
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
					res.sendRedirect(req.getContextPath() + "/front-end/index/index_lld.html"); // *工作3:
																								// (-->如無來源網頁:則重導至login_success.jsp)
				}

			} catch (Exception e) {
				System.out.println("6Fail Sign in");
//				errorMsgs.add("登入失敗:" + e.getMessage());
				System.out.println(e.getMessage());
			}
		}

		if ("insert".equals(action)) { // 來自Register.html的請求
			System.out.println('1' + action);
//			res.sendRedirect("https://www.google.com/"); //失敗
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println('2');

				String lld_email = req.getParameter("lld_email");
				System.out.println(lld_email);
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (lld_email == null || lld_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!lld_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}

				String lld_acc = "aa123456";
//				String lld_acc = req.getParameter("lld_acc");
//				String accReg = "^[\\w]{8,}$";
//				if (lld_acc == null || lld_acc.trim().length() == 0) {
//					errorMsgs.add("帳號: 請勿空白");
//				} else if (!lld_acc.trim().matches(accReg)) {
//					errorMsgs.add("帳號: 只能是中、英文字母 , 且長度必需是8碼以上");
//				}

				String lld_pwd = req.getParameter("lld_pwd");
				String lld_pwd2 = req.getParameter("lld_pwd2");
				String pwdReg = "^[\\w]{8,16}$";
				if (lld_pwd == null || lld_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!lld_pwd.trim().matches(pwdReg)) {
					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				} else if (!lld_pwd.equals(lld_pwd2)) {
					errorMsgs.add("輸入密碼不相同");
				}

				String lld_id = req.getParameter("lld_id");
				String idReg = "^[A-Z][12][\\d]{8}$";
				if (lld_id == null || lld_id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if (!lld_id.trim().matches(idReg)) {
					errorMsgs.add("身份證字號錯誤");
				} else if (!idvalidate(lld_id)) {
					errorMsgs.add("身份證字號錯誤");
				}

				String lld_name = req.getParameter("lld_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,19}$";
				if (lld_name == null || lld_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!lld_name.trim().matches(nameReg)) {
					errorMsgs.add("姓名: 只能是中、英文字母 , 且長度必需在2到19之間");
				}

				java.sql.Date lld_birth = null;
				String lld_birth_Str = req.getParameter("lld_birth").trim();
				java.sql.Date sysdate = new java.sql.Date(System.currentTimeMillis());
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				if (df.format(sysdate).equals(lld_birth_Str)) {
					errorMsgs.add("生日日期:請選擇!");
				} else {
					lld_birth = java.sql.Date.valueOf(lld_birth_Str);
				}

				Boolean lld_sex = null;
				if (req.getParameter("lld_sex").trim() == null || req.getParameter("lld_sex").trim().length() == 0) {
					errorMsgs.add("性別: 請勿空白");
				} else {
					lld_sex = Boolean.parseBoolean(req.getParameter("lld_sex"));
				}

				String lld_mobile = req.getParameter("lld_mobile");
				String mobileReg = "^[0][9][\\d]{8}$";
				if (lld_mobile == null || lld_mobile.trim().length() == 0) {
					errorMsgs.add("手機: 請勿空白");
				} else if (!lld_mobile.trim().matches(mobileReg)) {
					errorMsgs.add("手機號碼錯誤");
				}

				String lld_city = req.getParameter("lld_city");
				if ("0".equals(lld_city)) {
					errorMsgs.add("縣市:請選擇!");
				}
				String lld_dist = req.getParameter("lld_dist");
				if ("0".equals(lld_dist)) {
					errorMsgs.add("區域:請選擇!");
				}

				String lld_add = req.getParameter("lld_add");
				if (lld_add == null || lld_add.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}
				System.out.println("3");
				System.out.println(errorMsgs);

				System.out.println("3a");
				Part part = req.getPart("lld_pic");

				System.out.println(part);

				InputStream in = part.getInputStream();
				byte[] lld_pic = getPictureByteArray(in);

				System.out.println(lld_pwd);
				System.out.println(lld_id);
				System.out.println(lld_name);
				System.out.println(lld_birth);
				System.out.println(lld_sex);
				System.out.println(lld_mobile);

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
				lldVO.setLld_pic(lld_pic);
				System.out.println("4");

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("lldVO", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/addLld.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

				/*************************** 2.開始新增資料 ***************************************/
				LldService lldSvc = new LldService();
//				System.out.println(lld_email + lld_acc + lld_pwd2 + lld_id + lld_name + lld_birth + lld_sex + lld_mobile
//						+ lld_city + lld_dist + lld_add + lld_pic);
//				lldVO = lldSvc.addLld2(lld_email);// , lld_pic
				lldVO = lldSvc.addLld(lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex, lld_mobile,
						lld_city, lld_dist, lld_add, lld_pic);//

				System.out.println('5');
				out = res.getWriter();
//				System.out.println("data="+data);
				out.print("Success Sign up");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/lld/listAllLld.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				System.out.println('6');
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				System.out.println(e.getMessage());
				out.print("Fail Sign up");
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/addLld.jsp");
//				failureView.forward(req, res);
			}
		}

		

		if ("forgetPwd".equals(action)) {
			System.out.println('1' + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println('2');

				String lld_email = req.getParameter("lld_email");
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (lld_email == null || lld_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!lld_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}
				System.out.println(lld_email);

				/*************************** 2.開始比對資料 ***************************************/
				LldService lldSvc = new LldService();
				List<LldVO> list = lldSvc.getAllAccount();//
				String resString = "false";
				for (LldVO lldVO : list) {
					if (lld_email.equals(lldVO.getLld_email())) {
						resString = "true";
					}

				}
				System.out.println('3' + resString);
				out = res.getWriter();
				out.print(resString);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/lld/listAllLld.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				System.out.println('6');
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				System.out.println(e.getMessage());
				out.print("Fail Sign in");

			}
		}
	}

	// ===================privatemethods===========================

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

}
