package com.tenant_comments.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.landlord_comments.model.Landlord_commentsService;
import com.landlord_comments.model.Landlord_commentsVO;
import com.tenant_comments.model.Tenant_commentsService;
import com.tenant_comments.model.Tenant_commentsVO;


public class Tenant_commentsServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //iso-8859-1
		String action = req.getParameter("action");
		System.out.println("ACTION = " + action);
		
		//(房東用)
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//從評價管理列表取得(不需錯誤處理)
				String tnt_no = req.getParameter("tnt_no");
				String lld_no = req.getParameter("lld_no");
				System.out.println("tnt_no = " + tnt_no);
				System.out.println("lld_no = " + lld_no);
				
				//取得參數:tcm_clean
				String str1=req.getParameter("tcm_clean");
				System.out.println(str1);
				Integer tcm_clean = null;
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請勿空白:您是否滿意房客維持房屋的整潔環境");
				}
				

				
				if (errorMsgs.isEmpty()) {
				try {
					tcm_clean=Integer.valueOf(str1.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:您是否滿意房客維持房屋的整潔環境");
				}
				}
				
				//取得參數:tcm_commut
				String str2 = req.getParameter("tcm_commut");
				Integer tcm_commut = null;
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請勿空白:您是否滿意房客維持房屋的整潔環境");
				}
				

				if (errorMsgs.isEmpty()) {
				try {
					tcm_commut = Integer.valueOf(str2.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:您是否滿意房客維持房屋的整潔環境");
				}
				}
				
				//取得參數
				String str3 = req.getParameter("tcm_satisfy");
				Integer tcm_satisfy = null;		
				if (str3 == null || (str3.trim()).length() == 0) {
					errorMsgs.add("請勿空白:整體而言，對房客的滿意度");
				}

				
				if (errorMsgs.isEmpty()) {
				try {
					tcm_satisfy=Integer.valueOf(str3.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:整體而言，對房客的滿意度");
				}
				}		
					
				
				String tcm_commet = req.getParameter("tcm_commet").trim();
				Date tcm_time =new java.sql.Date(System.currentTimeMillis());
				
				Tenant_commentsVO tenant_commentsVO= new Tenant_commentsVO();
				tenant_commentsVO.setTnt_no(tnt_no);
				tenant_commentsVO.setLld_no(lld_no);
				tenant_commentsVO.setTcm_clean(tcm_clean);
				tenant_commentsVO.setTcm_commut(tcm_commut);
				tenant_commentsVO.setTcm_satisfy(tcm_satisfy);
				tenant_commentsVO.setTcm_commet(tcm_commet);
				tenant_commentsVO.setTcm_time(tcm_time);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("tenant_commentsVO", tenant_commentsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tenant_comments/addTcm.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交資料庫
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				tenant_commentsVO = tcmSvc.addTcm(tnt_no, lld_no, tcm_clean, tcm_commut, tcm_satisfy, tcm_commet, tcm_time);
				//成功新增一筆房客評價，回傳該房東所有的房客評論
				List<Tenant_commentsVO> list = tcmSvc.getAllbyLld(lld_no);
				req.setAttribute("list", list);
				String url = "/front-end/tenant_comments/lldListAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				
				//發生錯誤
			}catch(Exception e) {
				errorMsgs.add("can not process the data, please try again."+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tenant_comments/addTcm.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		
		//(房東用)回覆一則房屋評價
		if("getOne_For_Respon".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String tcm_no = req.getParameter("tcm_no");
				String str = req.getParameter("tcm_respon");
				//返回使用者tnt_no的結果
				String tnt_no = req.getParameter("tnt_no");
				
				/***************************2.開始錯誤處理****************************************/
				String tcm_respon;
				//1.回覆是否回空字串
				if(str.trim() == null || str.trim() == ""|| str.trim().length()==0) {
					errorMsgs.add("未填寫評價回覆");
				}

//				//2.髒字留言處理
//				if
				
				//返回搜尋使用者hos_no的搜尋結果
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyTnt(tnt_no);
				
				if (!errorMsgs.isEmpty()) {
					//返回搜尋lld_no的搜尋結果list
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//str 不為空
				tcm_respon=str;
				Tenant_commentsVO tenant_commentsVO = tcmSvc.replyTcm(tcm_no, tcm_respon);
				System.out.println("有更新reply");
				//新增有更新回覆的資料的List，返回搜尋使用者lld_no的搜尋結果
				List<Tenant_commentsVO> list2 = tcmSvc.getAllbyTnt(tnt_no); 
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				
				//返回搜尋使用者lld_no的搜尋結果
				req.setAttribute("list", list2); 
				String url = "/front-end/tenant_comments/listAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/			
				
			} catch (Exception e) {
				errorMsgs.add("無法讀取/修改此筆評價回覆:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
				failureView.forward(req, res);
				
			}	
		}
		
		//(房東用)輸入lld_no 後取得所有該房東的tcm_comments 
		if("getLldAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("lld_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房東編號 如:Lld007985");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String lld_no = null;
				try {
					lld_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyLld(lld_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房東尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/tenant_comments/lldListAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
				failureView.forward(req, res);
			}
		}
		
		//(房東用)輸入lld_no, tnt_no 後取得所有該房東的tcm_comments 
		if("getLldTntAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("0");
			try {
				/***************************1.**********************/
				System.out.println("1");
				String str = req.getParameter("tnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請選擇房客編號 如:TNT007985");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String tnt_no = null;
				try {
					tnt_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				String lld_no = req.getParameter("lld_no");
				/***************************2.*****************************************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyLld_tnt(tnt_no, lld_no);
				System.out.println("2");
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房客尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				System.out.println("servlet有回傳list");
				System.out.println(list);
				String url = "/front-end/tenant_comments/lldListAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
				failureView.forward(req, res);
			}
		}
			
		//(房客用)輸入tnt_no 後取得所有該房客的tcm_comments 
		if("getTntAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("tnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房客編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String tnt_no = null;
				try {
					tnt_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyTnt(tnt_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房客尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/tenant_comments/listAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tenant_comments/listAllTcm.jsp");
				failureView.forward(req, res);
			}
		}
		
		//(後台用)以tcm_no取得該評價
				if ("BkgetOne_For_Display".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***************************1.取得參數**********************/
						String str = req.getParameter("tcm_no");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入房東評價編號:如 TCM000022");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						String tcm_no = null;
						try {
							tcm_no = str.trim();
						} catch (Exception e) {
							errorMsgs.add("房客評價查詢失敗");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************2.�}�l�d�߸��*****************************************/
						Tenant_commentsService tcmSvc = new Tenant_commentsService();
						Tenant_commentsVO tcmVO = tcmSvc.getOneTcm(tcm_no);
						if (tcmVO == null) {
							errorMsgs.add("找不到此筆房客評價");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
						req.setAttribute("tenant_commentsVO", tcmVO); 
						String url = "/back-end/tenant_comments/select_page.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z*************************************/
					} catch (Exception e) {
						errorMsgs.add("查詢失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
						failureView.forward(req, res);
					}
				}
		//後台搜尋tnt_no
		if("BkGetTntAll_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("tnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房客編號，如TNT000034");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String tnt_no = null;
				try {
					tnt_no = str;
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyTnt(tnt_no);
				System.out.println("11111");
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房客尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}	
				System.out.println("22222222");
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/tenant_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println("3333333");
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
				failureView.forward(req, res);
			}
		
		}
		
		//(後台用)以hos_no搜尋
		if("BkGetLldAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("lld_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房東編號，如LLD000023");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String lld_no = null;
				try {
					lld_no = str;
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tcmSvc.getAllbyLld(lld_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房東尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/tenant_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/tenant_comments/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("tcm_commet");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫評價後再送出");
				}
				
				String tcm_commet = str;
				String tcm_no = req.getParameter("tcm_no");
				String lld_no = req.getParameter("lld_no");
				
				Tenant_commentsService tenant_commemtsSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tenant_commemtsSvc.getAllbyLld(lld_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				Tenant_commentsVO tenant_commentsVO = tenant_commemtsSvc.updateTcm(tcm_no, tcm_commet);
				System.out.println("有更新reply");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				Tenant_commentsService tcmSvc = new Tenant_commentsService();
				List<Tenant_commentsVO> list2 = tcmSvc.getAllbyLld(lld_no);
				String url = "/front-end/tenant_comments/lldListAllTcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/tenant_comments/lldListAllTcm.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
