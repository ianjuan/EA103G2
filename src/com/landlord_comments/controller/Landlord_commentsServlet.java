package com.landlord_comments.controller;

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

import com.house_comments.model.House_commentsService;
import com.house_comments.model.House_commentsVO;
import com.landlord_comments.model.Landlord_commentsService;
import com.landlord_comments.model.Landlord_commentsVO;


public class Landlord_commentsServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //iso-8859-1
		String action = req.getParameter("action");
		System.out.println("ACTION = " + action);
		
		//(房客用)
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//從評價管理列表取得(不需錯誤處理)
				String tnt_no = req.getParameter("tnt_no");
				String lld_no = req.getParameter("lld_no");
				System.out.println("tnt_no = " + tnt_no);
				System.out.println("lld_no = " + lld_no);
				
				//取得參數lcm_clean
				String str1= req.getParameter("lcm_clean");
				Integer lcm_clean = null;
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請勿空白:您是否滿意房東維持房屋的整潔環境");
				}
				
				if (errorMsgs.isEmpty()) {
				try {
					lcm_clean=Integer.valueOf(str1.trim());
				}catch(Exception e){
					errorMsgs.add("格式錯誤:您是否滿意房東維持房屋的整潔環境");
				}
				}
				//取得參數lcm_commut
				String str2= req.getParameter("lcm_commut");
				Integer lcm_commut=null;
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請勿空白:房東是否願意與房客溝通");
				}
				
				if(errorMsgs.isEmpty()) {
				try {
				lcm_commut = Integer.valueOf(str2.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤 :房東是否願意與房客溝通");
				}
				}
				
				//取得參數lcm_commet
				String str3 = req.getParameter("lcm_satisfy");
				Integer lcm_satisfy =null;
				if (str3 == null || (str3.trim()).length() == 0) {
					errorMsgs.add("請勿空白:整體而言，對房東的滿意度");
				}
				if(errorMsgs.isEmpty()) {
				try {
					lcm_satisfy = Integer.valueOf(str3.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:整體而言，對房東的滿意度");
				}
				}
				
				String lcm_commet = req.getParameter("lcm_commet");
				Date lcm_time =new java.sql.Date(System.currentTimeMillis());
				
				Landlord_commentsVO landlord_commentsVO= new Landlord_commentsVO();
				landlord_commentsVO.setTnt_no(tnt_no);
				landlord_commentsVO.setLld_no(lld_no);
				landlord_commentsVO.setLcm_clean(lcm_clean);
				landlord_commentsVO.setLcm_commut(lcm_commut);
				landlord_commentsVO.setLcm_satisfy(lcm_satisfy);
				landlord_commentsVO.setLcm_commet(lcm_commet);
				landlord_commentsVO.setLcm_time(lcm_time);
			
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("landlord_commentsVO", landlord_commentsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/landlord_comments/addLcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//轉交資料庫
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				landlord_commentsVO = lcmSvc.addLcm(tnt_no, lld_no, lcm_clean, lcm_commut, lcm_satisfy, lcm_commet, lcm_time);
				//成功新增一筆房東評價
				Landlord_commentsService landlord_commentsSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = landlord_commentsSvc.getAllbyTnt_lld(tnt_no, lld_no);
				req.setAttribute("list", list);
				String url = "/front-end/landlord_comments/listAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				
				//發生錯誤
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/landlord_comments/addLcm.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		
		//(房東用)回覆一則房屋評價
		if("getOne_For_Respon".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String lcm_no = req.getParameter("lcm_no");
				String str = req.getParameter("lcm_respon");
				//返回使用者lld_no的結果
				String lld_no = req.getParameter("lld_no");
				
				/***************************2.開始錯誤處理****************************************/
				String lcm_respon;
				//1.回覆是否回空字串
				if(str.trim() == null || str.trim() == ""|| str.trim().length()==0) {
					errorMsgs.add("未填寫評價回覆");
				}

//				//2.髒字留言處理
//				if
				
				//返回搜尋使用者hos_no的搜尋結果
				Landlord_commentsService landlord_commentsSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = landlord_commentsSvc.getAllbyLld(lld_no);
				
				if (!errorMsgs.isEmpty()) {
					//返回搜尋lld_no的搜尋結果list
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//str 不為空
				lcm_respon=str;
				Landlord_commentsVO landlord_commentsVO = landlord_commentsSvc.replyLcm(lcm_no, lcm_respon);
				System.out.println("有更新reply");
				//新增有更新回覆的資料的List，返回搜尋使用者lld_no的搜尋結果
				List<Landlord_commentsVO> list2 = landlord_commentsSvc.getAllbyLld(lld_no); 
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				
				//返回搜尋使用者lld_no的搜尋結果
				req.setAttribute("list", list2); 
				String url = "/front-end/landlord_comments/lldListAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/			
				
			} catch (Exception e) {
				errorMsgs.add("無法讀取/修改此筆評價回覆:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
				failureView.forward(req, res);
				
			}	
		}
		
		//(房東用)輸入lld_no 後取得所有該房東的lcm_comments 
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
							.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
					failureView.forward(req, res);
					return;//�{�����_
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
							.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = lcmSvc.getAllbyLld(lld_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房東尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/landlord_comments/lldListAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/landlord_comments/lldListAllLcm.jsp");
				failureView.forward(req, res);
			}
		}
		//房客:輸入tnt_no列出所有房東評論
		if("getAll_For_Display".equals(action)) {
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
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
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
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = lcmSvc.getAllbyTnt(tnt_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("尚無該房東評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/landlord_comments/listAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
				failureView.forward(req, res);
			}
		}
		//(房客用)輸入tnt_no 與 lld後取得所有該房客的lcm_comments 
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
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
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
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				String lld_no = req.getParameter("lld_no");
				/***************************2.�}�l�d�߸��*****************************************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = lcmSvc.getAllbyTnt_lld(tnt_no, lld_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("尚無該房東評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/landlord_comments/listAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
				failureView.forward(req, res);
			}
		}
		
		//(後台用)以lcm_no取得該評價
				if ("BkgetOne_For_Display".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***************************1.取得參數**********************/
						String str = req.getParameter("lcm_no");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入房東評價編號:如 LCM014992");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						String lcm_no = null;
						try {
							lcm_no = str.trim();
						} catch (Exception e) {
							errorMsgs.add("房東評價查詢失敗");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************2.�}�l�d�߸��*****************************************/
						Landlord_commentsService lcmSvc = new Landlord_commentsService();
						Landlord_commentsVO lcmVO = lcmSvc.getOneLcm(lcm_no);
						if (lcmVO == null) {
							errorMsgs.add("找不到此筆房東評價");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
						req.setAttribute("landlord_commentsVO", lcmVO); 
						String url = "/back-end/landlord_comments/select_page.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z*************************************/
					} catch (Exception e) {
						errorMsgs.add("查詢失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
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
					errorMsgs.add("請填寫房客編號，如TNT000028");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
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
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = lcmSvc.getAllbyTnt(tnt_no);
				System.out.println("11111");
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房客尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}	
				System.out.println("22222222");
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/landlord_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println("3333333");
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
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
					errorMsgs.add("請填寫房東編號，如LLD000502");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
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
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = lcmSvc.getAllbyLld(lld_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房東尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/landlord_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landlord_comments/select_page.jsp");
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
				String str = req.getParameter("lcm_commet");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫評價後再送出");
				}
				
				String lcm_commet = str;
				String lcm_no = req.getParameter("lcm_no");
				String tnt_no = req.getParameter("tnt_no");
				String lld_no = req.getParameter("lld_no");
				
				Landlord_commentsService landlord_commentsSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list = landlord_commentsSvc.getAllbyTnt_lld(tnt_no, lld_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				Landlord_commentsVO landlord_commentsVO = landlord_commentsSvc.updateLcm(lcm_no, lcm_commet);
				System.out.println("有更新update");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				Landlord_commentsService lcmSvc = new Landlord_commentsService();
				List<Landlord_commentsVO> list2 = lcmSvc.getAllbyTnt_lld(tnt_no, lld_no);
				req.setAttribute("list", list2); 
				String url = "/front-end/landlord_comments/listAllLcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/landlord_comments/listAllLcm.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
