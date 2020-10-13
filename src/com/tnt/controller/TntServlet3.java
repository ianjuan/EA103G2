package com.tnt.controller;

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
import javax.servlet.http.Part;

import com.tnt.model.TntService;
import com.tnt.model.TntVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class TntServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = null;

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

				String tnt_email = req.getParameter("tnt_email");
				System.out.println(tnt_email);
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (tnt_email == null || tnt_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!tnt_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}

				String tnt_acc = "aa123456";
//				String tnt_acc = req.getParameter("tnt_acc");
//				String accReg = "^[\\w]{8,}$";
//				if (tnt_acc == null || tnt_acc.trim().length() == 0) {
//					errorMsgs.add("帳號: 請勿空白");
//				} else if (!tnt_acc.trim().matches(accReg)) {
//					errorMsgs.add("帳號: 只能是中、英文字母 , 且長度必需是8碼以上");
//				}

				String tnt_pwd = req.getParameter("tnt_pwd");
				String tnt_pwd2 = req.getParameter("tnt_pwd2");
				String pwdReg = "^[\\w]{7,}$";
				if (tnt_pwd == null || tnt_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!tnt_pwd.trim().matches(pwdReg)) {
					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				} else if (!tnt_pwd.equals(tnt_pwd2)) {
					errorMsgs.add("輸入密碼不相同");
				}

				String tnt_id = req.getParameter("tnt_id");
				String idReg = "^[A-Z][12][\\d]{8}$";
				if (tnt_id == null || tnt_id.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if (!tnt_id.trim().matches(idReg)) {
					errorMsgs.add("身份證字號錯誤");
				} else if (!idvalidate(tnt_id)) {
					errorMsgs.add("身份證字號錯誤");
				}

				String tnt_name = req.getParameter("tnt_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,19}$";
				if (tnt_name == null || tnt_name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!tnt_name.trim().matches(nameReg)) {
					errorMsgs.add("姓名: 只能是中、英文字母 , 且長度必需在2到19之間");
				}

				java.sql.Date tnt_birth = null;
				String tnt_birth_Str = req.getParameter("tnt_birth").trim();
				java.sql.Date sysdate = new java.sql.Date(System.currentTimeMillis());
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				if (df.format(sysdate).equals(tnt_birth_Str)) {
					errorMsgs.add("生日日期:請選擇!");
				} else {
					tnt_birth = java.sql.Date.valueOf(tnt_birth_Str);
				}

				Boolean tnt_sex = null;
				if (req.getParameter("tnt_sex").trim() == null || req.getParameter("tnt_sex").trim().length() == 0) {
					errorMsgs.add("性別: 請勿空白");
				} else {
					tnt_sex = Boolean.parseBoolean(req.getParameter("tnt_sex"));
				}

				String tnt_mobile = req.getParameter("tnt_mobile");
				String mobileReg = "^[0][9][\\d]{8}$";
				if (tnt_mobile == null || tnt_mobile.trim().length() == 0) {
					errorMsgs.add("手機: 請勿空白");
				} else if (!tnt_mobile.trim().matches(mobileReg)) {
					errorMsgs.add("手機號碼錯誤");
				}

				String tnt_city = req.getParameter("tnt_city");
				if ("0".equals(tnt_city)) {
					errorMsgs.add("縣市:請選擇!");
				}
				String tnt_dist = req.getParameter("tnt_dist");
				if ("0".equals(tnt_dist)) {
					errorMsgs.add("區域:請選擇!");
				}

				String tnt_add = req.getParameter("tnt_add");
				if (tnt_add == null || tnt_add.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}
				System.out.println("3");
				System.out.println(errorMsgs);

				System.out.println("3a");
				Part part = req.getPart("tnt_pic");

				System.out.println(part);

				InputStream in = part.getInputStream();
				byte[] tnt_pic = getPictureByteArray(in);

				System.out.println(tnt_pwd);
				System.out.println(tnt_id);
				System.out.println(tnt_name);
				System.out.println(tnt_birth);
				System.out.println(tnt_sex);
				System.out.println(tnt_mobile);

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
				tntVO.setTnt_pic(tnt_pic);
				System.out.println("4");

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tntVO", tntVO); // 含有輸入格式錯誤的tntVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/tnt/addTnt.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

				/*************************** 2.開始新增資料 ***************************************/
				TntService tntSvc = new TntService();
//				System.out.println(tnt_email + tnt_acc + tnt_pwd2 + tnt_id + tnt_name + tnt_birth + tnt_sex + tnt_mobile
//						+ tnt_city + tnt_dist + tnt_add + tnt_pic);
//				tntVO = tntSvc.addTnt2(tnt_email);// , tnt_pic
				tntVO = tntSvc.addTnt(tnt_email, tnt_acc, tnt_pwd, tnt_id, tnt_name, tnt_birth, tnt_sex, tnt_mobile,
						tnt_city, tnt_dist, tnt_add, tnt_pic);//

				System.out.println('5');
				out = res.getWriter();
//				System.out.println("data="+data);
				out.print("Success Sign up");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/tnt/listAllTnt.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				System.out.println('6');
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				System.out.println(e.getMessage());
				out.print("Fail Sign up");
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/tnt/addTnt.jsp");
//				failureView.forward(req, res);
			}
		}

		if ("login".equals(action)) {
			System.out.println('1' + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println('2');

				String tnt_email = req.getParameter("tnt_email");
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (tnt_email == null || tnt_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!tnt_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}
				System.out.println(tnt_email);

				String tnt_pwd = req.getParameter("tnt_pwd");
				String pwdReg = "^[\\w]{8,}$";
				if (tnt_pwd == null || tnt_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!tnt_pwd.trim().matches(pwdReg)) {
					errorMsgs.add("密碼: 只能是中、英文字母 , 且長度必需是8碼以上");
				}
				System.out.println(tnt_pwd);

				// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("tntVO", tntVO); // 含有輸入格式錯誤的tntVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/tnt/addTnt.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}

				/*************************** 2.開始比對資料 ***************************************/
				TntService tntSvc = new TntService();
				List<TntVO> list = tntSvc.getAllAccount();//
				String resString = "";
				for (TntVO tntVO : list) {
					if ( tnt_email.equals(tntVO.getTnt_email()) ) {
						resString = (tnt_pwd.equals(tntVO.getTnt_pwd()))?"true":"false";
					}

				}
				System.out.println('3'+resString);
				out = res.getWriter();
				out.print(resString);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/tnt/listAllTnt.jsp";
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
		
		if ("forgetPwd".equals(action)) {
			System.out.println('1' + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println('2');

				String tnt_email = req.getParameter("tnt_email");
				String emailReg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\\]?)$";
				if (tnt_email == null || tnt_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!tnt_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}
				System.out.println(tnt_email);

				/*************************** 2.開始比對資料 ***************************************/
				TntService tntSvc = new TntService();
				List<TntVO> list = tntSvc.getAllAccount();//
				String resString = "false";
				for (TntVO tntVO : list) {
					if ( tnt_email.equals(tntVO.getTnt_email()) ) {
						resString = "true";
					}

				}
				System.out.println('3'+resString);
				out = res.getWriter();
				out.print(resString);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/tnt/listAllTnt.jsp";
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

	// 使用byte[]方式
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
