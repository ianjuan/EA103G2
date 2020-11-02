package com.emp.controller;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.emp.model.*;
import com.rig.model.RightService;
import com.rig.model.RightVO;
import com.websocket.jedis.JedisHandleMessage;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.news.model.NewsVO;
import com.notify.controller.NotifyServlet;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected EmployeeVO allowUser(String emp_acc, String emp_pwd) {
		EmployeeService empSvc = new EmployeeService();
		List<EmployeeVO> list_empVO = empSvc.getAll();
		for (EmployeeVO empVO : list_empVO) {
			if (empVO.getEmp_acc().equals(emp_acc) && empVO.getEmp_pwd().equals(emp_pwd))
				return empVO;
		}
		return null;
	}

	protected EmployeeVO allowMail(String emp_mail) {
		EmployeeService empSvc = new EmployeeService();
		List<EmployeeVO> list_empVO = empSvc.getAll();
		for (EmployeeVO empVO : list_empVO) {
			if (emp_mail.equals(empVO.getEmp_mail())) {
				return empVO;
			}
		}
		return null;

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		HttpSession session = null;
		Gson gson = new Gson();
		if ("forgot".equals(action)) {
			String emp_mail = req.getParameter("emp_mail");
			if (allowMail(emp_mail) == null) {
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的信箱無效!<BR>");
				out.println("請按此 <A HREF=" + req.getContextPath() + "/back-end/emp/forgot.jsp>重新輸入</A>");
				out.println("</BODY></HTML>");
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				EmployeeVO empVO = allowMail(emp_mail);
				session = req.getSession();
				req.setAttribute("empVO", empVO);
				RequestDispatcher forgot = req.getRequestDispatcher("/back-end/emp/forgot");// MailService
				forgot.forward(req, res);
			}
		}
		if ("logout".equals(action)) {
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/back-end/emp/login.jsp"); // *工作3:
																				// (-->如無來源網頁:則重導至login_success.jsp)
		}
		if ("login".equals(action)) {
			out = res.getWriter();

			// 【取得使用者 帳號(account) 密碼(password)】
			String emp_acc = req.getParameter("emp_acc");
			String emp_pwd = req.getParameter("emp_pwd");
			// 【檢查該帳號 , 密碼是否有效】
			if (allowUser(emp_acc, emp_pwd) == null) { // 【帳號 , 密碼無效時】
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的帳號 , 密碼無效!<BR>");
				out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/back-end/emp/login.jsp>重新登入</A>");
				out.println("</BODY></HTML>");
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				session = req.getSession();
				EmployeeVO empVO = allowUser(emp_acc, emp_pwd);
				session.setAttribute("empVO", empVO); // *工作1: 才在session內做已經登入過的標識

				try {
					String location = (String) session.getAttribute("location");

					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						System.out.println(location);
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/back-end/index.jsp"); // *工作3:
																				// (-->如無來源網頁:則重導至login_success.jsp)
			}
		}

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
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";// \u4e00-\u9fa5為中文範圍
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String emp_pwd = req.getParameter("emp_pwd").trim();
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				Integer emp_title = null;
				try {
					emp_title = new Integer(req.getParameter("emp_title").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("職位出問題啦.");
				}
				Integer emp_is_delete = (req.getParameter("emp_is_delete") != null) ? 1 : 0;

				// ───────────────照片修改───────────────────────
				Part part = req.getPart("upload"); // 來自於update_emp_input的type="file" name="upload"
				InputStream in = part.getInputStream();
				byte[] emp_pic = new byte[in.available()];
				String part_contentype = part.getContentType();
				String[] tokens = part_contentype.split("/");
				if (tokens[0].equals("image")) {
					in.read(emp_pic);//
				} else {
					final Base64.Decoder decoder = Base64.getDecoder();
					if (req.getParameter("emp_pic").equals("null")) {
						String path = getServletContext().getRealPath("\\back-end\\images\\images.png");
						String img64 = encryptToBase64(path);
						emp_pic = decoder.decode(img64);
					} else {
						emp_pic = decoder.decode(req.getParameter("emp_pic"));
					}
				}
				in.close();
				// ───────────────照片修改───────────────────────
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
				empVO = empSvc.updateEmp(emp_no, emp_acc, emp_pwd, emp_title, emp_name, emp_is_delete, emp_pic);
				// 權限
				String[] fun_no = req.getParameterValues("fun_no");
//				System.out.println(fun_no);
				StringBuilder sb = new StringBuilder();
				RightService rigSvc = new RightService();
				if(fun_no!=null) {
				rigSvc.delRig(emp_no);
				}
				if (fun_no != null) {
					for (int i = 0; i < fun_no.length; i++) {
						rigSvc.addRig(emp_no, fun_no[i]);
						if (fun_no[i].equals("A")) {
							sb.append("更改其他員工權限 ");
						} else if (fun_no[i].equals("B")) {
							sb.append("審核檢舉 ");
						} else if (fun_no[i].equals("C")) {
							sb.append("審核房屋內容 ");
						} else if (fun_no[i].equals("D")) {
							sb.append("審核身分驗證 ");
						} else if (fun_no[i].equals("E")) {
							sb.append("審核修繕狀態 ");
						} else if (fun_no[i].equals("F")) {
							sb.append("管理最新消息 ");
						} 
						
						
					}
					
				}
				String name =empVO.getEmp_name().substring(0,1);
				System.out.println("name"+name);
				new NotifyServlet().broadcast(emp_no, "權限變更", "<span style='color:blue;'>"+name+"經理	</span>"+"將你的權限變更為：<br>" + sb + "<br>其餘權限將無法繼續使用！", "emp/emp.do?action=getOne_For_Display&emp_no="+emp_no);
				

				System.out.println(sb);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				// 權限
				List<RightVO> list = rigSvc.getAll(emp_no);
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
		// ───────────────新增員工───────────────────────
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO_new = empSvc.addEmp(emp_acc, emp_pwd, emp_title, emp_name);
				empVO_new.setEmp_is_delete(0);
				String emp_no = empVO_new.getEmp_no();
				RightService rigSvc = new RightService();
				String[] fun_no = req.getParameterValues("fun_no");

				if (fun_no != null) {
					for (int i = 0; i < fun_no.length; i++) {
						rigSvc.addRig(emp_no, fun_no[i]);
					}
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("employeeVO", empVO_new);
				// 權限
				List<RightVO> list = rigSvc.getAll(emp_no);
				req.setAttribute("rigVO", list);
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
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
