package com.house_comments.controller;

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
import com.repair.model.RepairService;
import com.repair.model.RepairVO;

public class House_commentsServlet extends HttpServlet {
	
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
				String hos_no = req.getParameter("hos_no");
				System.out.println("tnt_no = " + tnt_no);
				System.out.println("hos_no = " + hos_no);
				//取得參數hcm_eqpmt
				String str1=req.getParameter("hcm_eqpmt");
				Integer hcm_eqpmt = null;
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請勿空白:您是否滿意房屋提供的所有設備 ");
				}	
				if (errorMsgs.isEmpty()) {
					try {
						hcm_eqpmt=Integer.valueOf(str1.trim());
					}catch(Exception e) {
						errorMsgs.add("格式錯誤:您是否滿意房屋提供的所有設備");
					}
				}
				//取得參數hcm_convmt
				String str2 = req.getParameter("hcm_convmt");
				Integer hcm_convmt =null;
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請勿空白:您是否滿意房屋周遭的機能與方便程度 ");
				}
				
				if (errorMsgs.isEmpty()) {
					try {
						hcm_convmt=Integer.valueOf(str2.trim());
					}catch(Exception e) {
						errorMsgs.add("格式錯誤:房屋周遭機能是否方便");
					}
				}
				
				//取得參數hcm_neibor
				String str3 = req.getParameter("hcm_neibor");
				Integer hcm_neibor = null;
				if (str3 == null || (str3.trim()).length() == 0) {
					errorMsgs.add("請勿空白:房屋鄰居是否友善 ");
				}
				if (errorMsgs.isEmpty()) {
					try{
						hcm_neibor=Integer.valueOf(str3.trim());
					}catch(Exception e) {
						errorMsgs.add("格式錯誤:房屋鄰居是否友善");
					}
				}
				
		
				String hcm_commnt = req.getParameter("hcm_commnt");
				Date hcm_time =new java.sql.Date(System.currentTimeMillis());
				
				House_commentsVO house_commentsVO= new House_commentsVO();
				house_commentsVO.setTnt_no(tnt_no);
				house_commentsVO.setHos_no(hos_no);
				house_commentsVO.setHcm_eqpmt(hcm_eqpmt);
				house_commentsVO.setHcm_convmt(hcm_convmt);
				house_commentsVO.setHcm_neibor(hcm_neibor);
				house_commentsVO.setHcm_commnt(hcm_commnt);
				house_commentsVO.setHcm_time(hcm_time);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("house_commentsVO", house_commentsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/house_comments/addHcm.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交資料庫
				House_commentsService hcmSvc = new House_commentsService();
				house_commentsVO = hcmSvc.addHcm(tnt_no, hos_no, hcm_eqpmt, hcm_convmt, hcm_neibor, hcm_commnt, hcm_time);
				
				//成功新增一筆房屋評價
				List<House_commentsVO> list = hcmSvc.getAllbyTnt_hos(hos_no, tnt_no);
				req.setAttribute("list", list); 
				String url = "/front-end/house_comments/listAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				
				//發生錯誤
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/house_comments/addHcm.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		//房客用，新增文字留言
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String hcm_no = req.getParameter("hcm_no");
				String str = req.getParameter("hcm_commet");
				//返回搜尋使用者hos_no的搜尋結果
				String hos_no = req.getParameter("hos_no");
				
				/***************************2.開始錯誤處理****************************************/
				String hcm_commnt;
				//1.回覆是否回空字串
				if(str.trim() == null || str.trim() == ""|| str.trim().length()==0) {
					errorMsgs.add("未填寫文字評價");
				}
				
				String tnt_no = req.getParameter("tnt_no");
				
				//返回搜尋使用者hos_no的搜尋結果
				House_commentsService house_commentsSvc = new House_commentsService();
				List<House_commentsVO> list = house_commentsSvc.getAllbyTnt_hos(hos_no, tnt_no);
				
				if (!errorMsgs.isEmpty()) {
					//返回搜尋使用者hos_no的搜尋結果list
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//str 不為空
				hcm_commnt=str;
				House_commentsVO house_commentsVO = house_commentsSvc.updateHcm(hcm_no, hcm_commnt);
				System.out.println("有更新update");
				//新增有更新回覆的資料的List，返回搜尋使用者hos_no的搜尋結果
				List<House_commentsVO> list2 = house_commentsSvc.getAllbyTnt_hos(hos_no, tnt_no); 
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("house_commentsVO", house_commentsVO); 
				//返回搜尋使用者hos_no的搜尋結果
				req.setAttribute("list", list2); 
				String url = "/front-end/house_comments/listAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/			
				
			} catch (Exception e) {
				errorMsgs.add("無法讀取/修改此筆評價回覆:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
				failureView.forward(req, res);
				
			}	
		}
		
		
		//(房東用)回覆一則房屋評價
		if("getOne_For_Respon".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String hcm_no = req.getParameter("hcm_no");
				String str = req.getParameter("hcm_respon");
				//返回搜尋使用者hos_no的搜尋結果
				String hos_no = req.getParameter("hos_no");
				
				/***************************2.開始錯誤處理****************************************/
				String hcm_respon;
				//1.回覆是否回空字串
				if(str.trim() == null || str.trim() == ""|| str.trim().length()==0) {
					errorMsgs.add("未填寫評價回覆");
				}

//				//2.髒字留言處理
//				if
				
				//返回搜尋使用者hos_no的搜尋結果
				House_commentsService house_commentsSvc = new House_commentsService();
				List<House_commentsVO> list = house_commentsSvc.getAllbyHos(hos_no);
				
				if (!errorMsgs.isEmpty()) {
					//返回搜尋使用者hos_no的搜尋結果list
					req.setAttribute("list", list); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//str 不為空
				hcm_respon=str;
				House_commentsVO house_commentsVO = house_commentsSvc.replyHcm(hcm_no, hcm_respon);
				System.out.println("有更新reply");
				//新增有更新回覆的資料的List，返回搜尋使用者hos_no的搜尋結果
				List<House_commentsVO> list2 = house_commentsSvc.getAllbyHos(hos_no); 
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("house_commentsVO", house_commentsVO); 
				//返回搜尋使用者hos_no的搜尋結果
				req.setAttribute("list", list2); 
				String url = "/front-end/house_comments/lldListAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/			
				
			} catch (Exception e) {
				errorMsgs.add("無法讀取/修改此筆評價回覆:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
				failureView.forward(req, res);
				
			}	
		}
		
		//(房東用)輸入hos_no 後取得所有該房屋的hcm_comments 
		if("getHosAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.**********************/
				String str = req.getParameter("hos_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房屋編號 如:HOS007985");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String hos_no = null;
				try {
					hos_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				House_commentsService hcmSvc = new House_commentsService();
				List<House_commentsVO> list = hcmSvc.getAllbyHos(hos_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房屋尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/house_comments/lldListAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/house_comments/lldListAllHcm.jsp");
				failureView.forward(req, res);
			}
		}
		//tnt 顯示全部hcm
		if("getAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1");
			try {
				/***************************1.**********************/
				String str = req.getParameter("tnt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房客編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("2");
				String tnt_no = null;
				try {
					tnt_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.*****************************************/
				House_commentsService hcmSvc = new House_commentsService();
				List<House_commentsVO> list = hcmSvc.getAllbyTnt(tnt_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房屋尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.(Send the Success view)*************/
				System.out.println("3");
				req.setAttribute("list", list); 
				String url = "/front-end/house_comments/listAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
				failureView.forward(req, res);
			}
		}
		
			
		//(房客用)輸入tnt_no與hos_no 後取得所有該房客的hcm_commemts 
		if("getTntAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("hos_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請選擇房屋");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String hos_no = null;
				try {
					hos_no = str.trim();
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				String tnt_no = req.getParameter("tnt_no");
				/***************************2.�}�l�d�߸��*****************************************/
				House_commentsService hcmSvc = new House_commentsService();
				List<House_commentsVO> list = hcmSvc.getAllbyTnt_hos(hos_no, tnt_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房屋尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/house_comments/listAllHcm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/house_comments/listAllHcm.jsp");
				failureView.forward(req, res);
			}
		}
		
		//(後台用)以hcm_no取得該評價
				if ("BkgetOne_For_Display".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***************************1.取得參數**********************/
						String str = req.getParameter("hcm_no");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入房屋評價編號:如 HCM099940");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						String hcm_no = null;
						try {
							hcm_no = str.trim();
						} catch (Exception e) {
							errorMsgs.add("房屋評價查詢失敗");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************2.�}�l�d�߸��*****************************************/
						House_commentsService hcmSvc = new House_commentsService();
						House_commentsVO hcmVO = hcmSvc.getOneHcm(hcm_no);
						if (hcmVO == null) {
							errorMsgs.add("找不到此筆房屋評價");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
							failureView.forward(req, res);
							return;//終止程式
						}
						
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
						req.setAttribute("house_commentsVO", hcmVO); 
						String url = "/back-end/house_comments/select_page.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z*************************************/
					} catch (Exception e) {
						errorMsgs.add("查詢失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
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
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
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
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				House_commentsService hcmSvc = new House_commentsService();
				List<House_commentsVO> list = hcmSvc.getAllbyTnt(tnt_no);
				System.out.println("11111");
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房客尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}	
				System.out.println("22222222");
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/house_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println("3333333");
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
				failureView.forward(req, res);
			}
		
		}
		
		//(後台用)以hos_no搜尋
		if("BkGetHosAll_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("hos_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請填寫房屋編號，如HOS007985");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String hos_no = null;
				try {
					hos_no = str;
				} catch (Exception e) {
					errorMsgs.add("資料查詢失敗");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				House_commentsService hcmSvc = new House_commentsService();
				List<House_commentsVO> list = hcmSvc.getAllbyHos(hos_no);
				if ((list==null) || (list.isEmpty())) {
					errorMsgs.add("該房屋尚無評價");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
					failureView.forward(req, res);
					return;//程式終止
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/back-end/house_comments/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("資料有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/house_comments/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
