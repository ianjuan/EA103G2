package com.comment.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apl.model.Con_aplService;
import com.apl.model.Con_aplVO;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.HouseService;
import com.housemanage.model.HouseVO;
import com.landlord_comments.model.Landlord_commentsService;
import com.lld.model.LldService;
import com.lld.model.LldVO;
import com.rec.model.RecService;
import com.rec.model.RecVO;
import com.tenant_comments.model.Tenant_commentsService;
import com.tenant_comments.model.Tenant_commentsVO;
import com.tnt.model.TntService;
import com.tnt.model.TntVO;

public class CommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("gettntprofile".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String tnt_no = new String(req.getParameter("tnt_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Tenant_commentsService tenant_commentsService = new Tenant_commentsService();
				List<Tenant_commentsVO> list = tenant_commentsService.getAllbyTnt(tnt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list", list);
				String url = "/front-end/apl/lldaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("tntinsert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String tnt_no = new String(req.getParameter("tnt_no"));
				String lld_no = new String(req.getParameter("lld_no"));
				String con_no = new String(req.getParameter("con_no"));
				System.out.println(tnt_no);
				System.out.println(lld_no);
				System.out.println(con_no);
				
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
				
				System.out.println(lcm_clean);
				System.out.println(lcm_commut);
				System.out.println(lcm_satisfy);
				
				
				String lcm_commet = req.getParameter("lcm_commet");
				Date lcm_time =new java.sql.Date(System.currentTimeMillis());
				

				/*************************** 2.開始查詢資料 ****************************************/
				Landlord_commentsService landlord_commentsService = new Landlord_commentsService();
				landlord_commentsService.addLcm(tnt_no, lld_no, lcm_clean, lcm_commut, lcm_satisfy, lcm_commet, lcm_time);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				RecService recService = new RecService();
				List<RecVO> reclist = recService.getLddAllByCon(con_no);
				
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				req.setAttribute("con_no", con_no);
				req.setAttribute("reclist", reclist);
				String url = "/front-end/rec/lldlistrec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
