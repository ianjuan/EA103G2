package com.lld.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.lld.model.LldService;
import com.lld.model.LldVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class LldServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String lld_no = req.getParameter("lld_no");
				String regex = "^(LLD|lld)[0-9]{6}$";
				if (!lld_no.matches(regex)) {
					errorMsgs.add("房東編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no.toUpperCase());
				if (lldVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("lldVO", lldVO);
				String url = "/back-end/lld/listOneLld.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllLld.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 **********************/
				String lld_no = req.getParameter("lld_no");

				/*************************** 2.開始查詢資料 ****************************************/
				LldService lldSvc = new LldService();
				LldVO lldVO = lldSvc.getOneLldProfile(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("lldVO", lldVO); // 資料庫取出的lldVO物件,存入req
				String url = "/back-end/lld/update_lld_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_lld_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/listAllLld.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_lld_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String lld_no = req.getParameter("lld_no").trim();

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
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!lld_name.trim().matches(nameReg)) {
					errorMsgs.add("姓名: 只能是中、英文字母 , 且長度必需在2到19之間");
				}

				java.sql.Date lld_birth = null;
				Calendar cal = new GregorianCalendar(2000, Calendar.JANUARY, 1);
				try {
					lld_birth = java.sql.Date.valueOf(req.getParameter("lld_birth").trim());
				} catch (Exception e) {
					lld_birth = (Date) cal.getTime();
					errorMsgs.add("請輸入生日日期!");
				}

				String lld_mobile = req.getParameter("lld_mobile");
				String mobileReg = "^[0][9][\\d]{8}$";
				if (lld_mobile == null || lld_mobile.trim().length() == 0) {
					errorMsgs.add("手機: 請勿空白");
				} else if (!lld_mobile.trim().matches(mobileReg)) {
					errorMsgs.add("手機號碼錯誤");
				}

				String lld_add = req.getParameter("lld_add");
				if (lld_add == null || lld_add.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}

				String lld_email = req.getParameter("lld_email");
				String lld_acc = req.getParameter("lld_acc");
				String lld_pwd = req.getParameter("lld_pwd");
				Boolean lld_sex = Boolean.parseBoolean(req.getParameter("lld_sex"));
				String lld_city = req.getParameter("lld_city");
//				String lld_dist = req.getParameter("lld_dist");
				String lld_dist = "吉安鄉";

				Part part = req.getPart("lld_pic");
				InputStream in = part.getInputStream();
				byte[] lld_pic = getPictureByteArray(in);

				Integer lld_status = Integer.valueOf(req.getParameter("lld_status"));

				LldVO lldVO = new LldVO();
				lldVO.setLld_no(lld_no);
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
				lldVO.setLld_status(lld_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lldVO", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/update_lld_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				LldService lldSvc = new LldService();
//				System.out.println(lld_no+lld_email+lld_acc+lld_pwd+lld_id+lld_name+lld_birth+lld_sex+lld_mobile+lld_city+lld_dist+lld_add+lld_pic+lld_status);
				lldVO = lldSvc.updateLldProfile(lld_no, lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth,
						lld_sex, lld_mobile, lld_city, lld_dist, lld_add, lld_pic, lld_status);
//				System.out.println(4);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("lldVO", lldVO); // 資料庫update成功後,正確的的lldVO物件,存入req
				String url = "/back-end/lld/listOneLld.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLld.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/update_lld_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addLld.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lld_email = req.getParameter("lld_email");
				String emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				if (lld_email == null || lld_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!lld_email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱錯誤");
				}

				String lld_acc = req.getParameter("lld_acc");
				String accReg = "^[\\w]{8,}$";
				if (lld_acc == null || lld_acc.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if (!lld_acc.trim().matches(accReg)) {
					errorMsgs.add("帳號: 只能是中、英文字母 , 且長度必需是8碼以上");
				}

				String lld_pwd = req.getParameter("lld_pwd");
				String lld_pwd2 = req.getParameter("lld_pwd2");
				String pwdReg = "^[\\w]{7,}$";
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



				Part part = req.getPart("lld_pic");
				InputStream in = part.getInputStream();
				byte[] lld_pic = getPictureByteArray(in);

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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("lldVO", lldVO); // 含有輸入格式錯誤的lldVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/addLld.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				LldService lldSvc = new LldService();
//				System.out.println(lld_email + lld_acc + lld_pwd2 + lld_id + lld_name + lld_birth + lld_sex + lld_mobile
//						+ lld_city + lld_dist + lld_add + lld_pic);
				lldVO = lldSvc.addLld(lld_email, lld_acc, lld_pwd, lld_id, lld_name, lld_birth, lld_sex, lld_mobile,
						lld_city, lld_dist, lld_add, lld_pic);// 

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/lld/listAllLld.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/lld/addLld.jsp");
				failureView.forward(req, res);
			}
		}
	}

	Boolean idvalidate(String lld_id) {
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

	// 使用byte[]方式
	byte[] getPictureByteArray(InputStream in) throws IOException {

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
