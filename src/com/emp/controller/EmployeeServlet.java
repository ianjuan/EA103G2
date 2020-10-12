package com.emp.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.emp.model.*;
import com.rig.model.RightService;
import com.rig.model.RightVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String emp_no = null;
				try {
					emp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				//
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(emp_no);
				RightService rigSvc = new RightService();
				List<RightVO> list = rigSvc.getAll(emp_no);
				
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// 
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rigVO", list);
				req.setAttribute("employeeVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_no = new String(req.getParameter("emp_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(emp_no);
				RightService rigSvc = new RightService();
				List<RightVO> list = rigSvc.getAll(emp_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("employeeVO", empVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("rigVO", list);
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
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
				String emp_no = req.getParameter("emp_no");
				String emp_acc = req.getParameter("emp_acc");
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String emp_pwd = req.getParameter("emp_pwd").trim();
				if ( emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				Integer emp_title = null;
				try {
					emp_title = new Integer(req.getParameter("emp_title").trim());
				} catch (NumberFormatException e) {
					emp_title = 0;
					errorMsgs.add("職位請填數字.");
				}
				Integer emp_is_delete = new Integer(req.getParameter("emp_is_delete").trim());	
				
				//───────────────照片修改───────────────────────

				req.setCharacterEncoding("UTF-8"); // 處理中文檔名
				res.setContentType("text/html; charset=UTF-8");
				Part part = req.getPart("upload"); //來自於update_emp_input的type="file" name="upload"
				InputStream in = part.getInputStream();
				byte[] emp_pic = new byte[in.available()];
				
				if(in.available()!=0) {
				in.read(emp_pic);//
				}
				else {
					final Base64.Decoder decoder = Base64.getDecoder();
					if(req.getParameter("emp_pic").equals("null")) {
					String path = getServletContext().getRealPath("")+"\\back-end\\img\\images.png";
					String img64 = encryptToBase64(path);
					emp_pic=decoder.decode(img64);
					}
					else {
					emp_pic= decoder.decode(req.getParameter("emp_pic"));
				}
				}
				in.close();
				//───────────────照片修改───────────────────────
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_no(emp_no);
				empVO.setEmp_acc(emp_acc);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_title(emp_title);
				empVO.setEmp_is_delete(emp_is_delete);
				empVO.setEmp_pic(emp_pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.updateEmp(emp_no,emp_acc,emp_pwd,emp_title,emp_name,emp_is_delete,emp_pic);
				RightService rigSvc = new RightService();
				List<RightVO> list = rigSvc.getAll(emp_no);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("rigVO", list); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}
		//───────────────新增員工───────────────────────
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String emp_acc = req.getParameter("emp_acc").trim();
				if (emp_acc == null || emp_acc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}
				String emp_pwd = req.getParameter("emp_pwd").trim();
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				Integer emp_title = null;
				try {
					emp_title = new Integer(req.getParameter("emp_title").trim());
				} catch (NumberFormatException e) {
					emp_title = 0;
					errorMsgs.add("職位請填數字.");
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_acc(emp_acc);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_title(emp_title);
				empVO.setEmp_name(emp_name);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.addEmp(emp_acc, emp_pwd, emp_title, emp_name);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	public String encryptToBase64(String filePath) {
		if (filePath == null) {
			return null;
		}
		try {
			byte[] b = Files.readAllBytes(Paths.get(filePath));
			return Base64.getEncoder().encodeToString(b);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
