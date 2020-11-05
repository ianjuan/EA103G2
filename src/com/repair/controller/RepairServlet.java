package com.repair.controller;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.HouseService;
import com.housemanage.model.HouseVO;
import com.repair.model.*;
import com.repair_picture.model.Repair_pictureVO;
import com.notify.controller.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RepairServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); 
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); 
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		System.out.println("action="+action);
	
		
		if ("delPic".equals(action)) { 
			System.out.println("有近來");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("還沒進到try");
			try {
				/***************************1.接收請求參數****************************************/

				String reppic_no_array[] = req.getParameterValues("reppic_no[]");
				if(reppic_no_array!=null) {
					for(int i=0;i<reppic_no_array.length;i++) {
						String reppic_no = reppic_no_array[i];
						String new_reppic_no=reppic_no_array[i].concat("D");
						System.out.println(new_reppic_no);
						/***************************2.開始修改資料*****************************************/
						RepairService repairSvc = new RepairService();
						repairSvc.delPic(reppic_no, new_reppic_no);
					}
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
			
				//為了讓圖片在updPic.jsp新增上傳後能在同業面看到所有pic
				String rep_no = req.getParameter("rep_no");
				RepairService repSvc = new RepairService();
				RepairVO repairVO = repSvc.getOneRepair(rep_no);
				req.setAttribute("repairVO", repairVO);
				//準備轉交
				String url = "/front-end/repair/updPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法刪除此圖片:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/updPic.jsp");
				failureView.forward(req, res);
				
			}	
		}
		
		//房客新增修繕圖片
	if("pic_insert".equals(action)||"pic_upd_insert".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		/***************************1.接收請求參數，錯誤處理**********************/
		try {
			
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				String filename = getFileNameFromPart(part);
				if (filename!= null && part.getContentType()!=null) {
				System.out.println("在for");
				System.out.println("name= "+part.getName());
				System.out.println("size= "+part.getSize());
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
			
			
			byte[] reppic_pic = buf;
	

			/***************************2.開始新增資料***************************************/
			System.out.println("準備新增資料");
//			String rep_no =((RepairVO)session.getAttribute("repairVO")).getRep_no();
			String rep_no = req.getParameter("rep_no");
			System.out.println("rep_no = "+rep_no);
			RepairService repairSvc = new RepairService();
			Repair_pictureVO repair_pictureVO = repairSvc.addRepair_pic(rep_no, reppic_pic);
			}
				
		}//for 
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url=null;
			if("pic_insert".equals(action)) {
				url = "/front-end/repair/listAllRepair.jsp";
			}else {
				url = "/front-end/repair/updPic.jsp";
			}
			//以下為了取得房東編號:通知
			RepairService repSvc = new RepairService();
			ConService conSvc = new ConService();
			HouseService hosSvc = new HouseService();
			RepairVO repVO = repSvc.getOneRepair(req.getParameter("rep_no"));
			ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
			HouseVO hosVO = hosSvc.getHouseInfo(conVO.getHos_no());
			String lld_no = hosVO.getLld_no();
			new NotifyServlet().broadcast(
					lld_no, "您的房客新增修繕照片!", "您的房客剛剛更新修繕照片，請至修繕管理查看", "");
			
			//為了讓圖片在updPic.jsp新增上傳後能在同業面看到所有pic
			String rep_no = req.getParameter("rep_no");
//			RepairService repSvc = new RepairService();
			RepairVO repairVO = repSvc.getOneRepair(rep_no);
			req.setAttribute("repairVO", repairVO);
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			System.out.println("3");
			/***************************其他可能的錯誤處理**********************************/
		}catch(Exception e){
			System.out.println("4");
			errorMsgs.add("新增圖片失敗");
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/addReppic.jsp");
			failureView.forward(req, res);
			
		}
	}
	
	//房東
	if("getOne_For_seePic".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
	
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			System.out.println(rep_no);
			/***************************2.開始查詢資料****************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO = repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/	
			req.setAttribute("repairVO", repairVO);
			System.out.println(repairVO.getRep_no());
			String url = "/front-end/repair/lldSeePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法查看此筆修繕紀錄:" + e.getMessage());
			e.printStackTrace();
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/lldListAllRepair.jsp");
			//返回上一頁
			failureView.forward(req, res);
			
		}	
	}
	
	//房客
	if("getOne_For_updPic".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
	
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			System.out.println(rep_no);
			/***************************2.開始查詢資料****************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO = repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/	
			req.setAttribute("repairVO", repairVO);

			String url = "/front-end/repair/updPic.jsp";
			//
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
			e.printStackTrace();
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
			//返回上一頁
			failureView.forward(req, res);
			
		}	
	}
	if("getTntRepair".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("getTntRepair:1");
		
		try {
			/***************************1.接收請求參數，錯誤處理**********************/
		
			String tnt_no = req.getParameter("tnt_no");
	
			System.out.println(tnt_no);
			/***************************2.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("tnt_no", tnt_no);
			String url = "/front-end/repair/listAllRepair.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			/***************************其他可能的錯誤處理**********************************/
		}catch(Exception e){
			System.out.println("跳轉失敗");
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/contract/tntlistcontract.jsp");
			failureView.forward(req, res);
			errorMsgs.add("頁面連結失敗");
		}
	
		
	}
	
	if("getLldRepair".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("getLldRepair:1");
		
		try {
			/***************************1.接收請求參數，錯誤處理**********************/
			String lld_no = req.getParameter("lld_no");
			/***************************2.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("lld_no", lld_no);
			String url = "/front-end/repair/lldListAllRepair.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			/***************************其他可能的錯誤處理**********************************/
		}catch(Exception e){
			System.out.println("跳轉失敗");
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/contract/lldlistcontract.jsp");
			failureView.forward(req, res);
			errorMsgs.add("頁面連結失敗");
		}
	
		
	}
	
	
	
	if("tnt_into_insert".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("tnt_into_insert:1");
		
		try {
			/***************************1.接收請求參數，錯誤處理**********************/
			String con_no = req.getParameter("con_no");
			
			RepairVO repairVO = new RepairVO();
			repairVO.setCon_no(con_no);
			
			/***************************2.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("repairVO", repairVO);
			String url = "/front-end/repair/addRepair.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			/***************************其他可能的錯誤處理**********************************/
		}catch(Exception e){
			System.out.println("跳轉失敗");
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/contract/tntlistcontract.jsp");
			failureView.forward(req, res);
			errorMsgs.add("頁面連結失敗");
		}
	}

	
		
		//tenant new a repair //房客新增一筆修繕申請
	if("tnt_insert".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("1");
		try {
			/***************************1.接收請求參數，錯誤處理**********************/
			ConService conSrv = new ConService();
//			Integer con_sta = conSrv.getOneCon(req.getParameter("con_no")).getCon_sta();
//			上線時要打開
//			if(!(con_sta==1)) {
//				errorMsgs.add("非入住中，無法申請修繕");
//			}
			
			
			String con_no = req.getParameter("con_no");
			if (con_no == null || con_no.trim().length() == 0) {
				errorMsgs.add("合約編號: 請勿空白");
			}
			
			System.out.println("2");
			String rep_dam_objReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
			String	rep_dam_obj = req.getParameter("rep_dam_obj");
		
			if (rep_dam_obj == null || rep_dam_obj.trim().length() == 0||rep_dam_obj.trim().equals("")) {
				errorMsgs.add("待修物品: 請勿空白");
			} else if(!rep_dam_obj.trim().matches(rep_dam_objReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("待修物品: 請勿空白");
            }
			
			String fQty = req.getParameter("fQty");
			System.out.println(fQty);
			
//			if (fQty == null || fQty.trim().length() == 0) {
//				errorMsgs.add("物品數量: 請勿空白");
//			} else if(Integer.valueOf(fQty)==0){ 
//				errorMsgs.add("待修物品: 請填入大於0的數字");
//            }else {
//            	errorMsgs.add("待修物品:請填入數字");
//            }
			
			
			String rep_dam_obj_des = req.getParameter("rep_dam_obj_des");
			if (rep_dam_obj_des == null || rep_dam_obj_des.trim().length() == 0) {
				errorMsgs.add("損壞狀況: 請勿空白，只能是中、英文字母、數字和_ , 且長度必需在1到250之間");
			} else if(!rep_dam_obj_des.trim().matches(rep_dam_objReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("損壞狀況: 只能是中、英文字母、數字和_ , 且長度必需在1到250之間");
            }
			
			java.sql.Date rep_case_str =null;
			try {
			rep_case_str = java.sql.Date.valueOf(req.getParameter("rep_case_str").trim());
			}catch(IllegalArgumentException e) {
				rep_case_str =new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入損壞日期!");
			}
			System.out.println("3");
			RepairVO repairVO = new RepairVO();
			repairVO.setCon_no(con_no);
			repairVO.setRep_dam_obj(rep_dam_obj);
			repairVO.setRep_dam_obj_des(rep_dam_obj_des);
			repairVO.setRep_case_str(rep_case_str);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("repairVO", repairVO); 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/addRepair.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************2.開始新增資料***************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO2 = repairSvc.addRepair(con_no, rep_dam_obj, rep_dam_obj_des, rep_case_str);
			//以下為了取得房東編號:通知
			RepairService repSvc = new RepairService();
			ConService conSvc = new ConService();
			HouseService hosSvc = new HouseService();
			RepairVO repVO = repSvc.getOneRepair(repairVO2.getRep_no());
			ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
			HouseVO hosVO = hosSvc.getHouseInfo(conVO.getHos_no());
			String lld_no = hosVO.getLld_no();
			new NotifyServlet().broadcast(
					lld_no, "您的房客新增一筆修繕!", "您的房客剛剛更新了一筆修繕，請至修繕管理查看", "");
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			session.setAttribute("repairVO", repairVO2);
			String url = "/front-end/repair/addReppic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				System.out.println("4");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/addRepair.jsp");
				failureView.forward(req, res);
				errorMsgs.add("新增申請失敗");
			}
			
		}
	

	
	
	
	if("lld_getOne_For_Update_pro".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
	
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			
			/***************************2.開始查詢資料****************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO = repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/	
			req.setAttribute("repairVO", repairVO);
			
			String url = "/front-end/repair/lld_update_repair_progress.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			


		} catch (Exception e) {
			errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/lldListAllRepair.jsp");

			failureView.forward(req, res);
			
		}	
	}
	
	//房東更新狀態:
	if ("updatePro".equals(action)) { 
		System.out.println("updatePro");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數****************************************/
			//接收請求參數
			String rep_no = req.getParameter("rep_no"); 
			System.out.println(rep_no);
			String str = req.getParameter("rep_pro");
			System.out.println("rep_no="+str);
			String lld_no = req.getParameter("lld_no");
			System.out.println("lld_no="+lld_no);
			//以下為了取得房客編號，通知
			RepairService repairSvc = new RepairService();
			RepairVO repVO = repairSvc.getOneRepair(rep_no);
			ConService conSvc = new ConService();
			ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
			String tnt_no = conVO.getTnt_no();
			
			//錯誤處理
			
			if (str == null || (str.trim()).length() == 0 ) {
				errorMsgs.add("請更新修繕狀態");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			Integer newRep_pro=null;
			
				try {
					newRep_pro=Integer.valueOf(str.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:請選擇更新狀態");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
			
			RepairVO repair_oldVO = repairSvc.getOneRepair(rep_no);
			RepairVO repairVO = new RepairVO();
			Integer rep_pro=null;
			repairVO.setRep_no(rep_no);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("repairVO", repair_oldVO); 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始修改資料****************************************/
			//若為rep_pro==1:接受申請
			if (newRep_pro.equals(1)) {
				System.out.println("進1");
			System.out.println("接受申請");
			rep_pro=1;
			repairVO =repairSvc.updatePro(rep_no, rep_pro);
//			new NotifyServlet().broadcast(
//					tnt_no, "您的房東接受了修繕申請", "請至修繕管理查看", "");
			
			
			
			}
			
			if (newRep_pro == 2) {
				System.out.println("進2");
				System.out.println("拒絕申請");
				rep_pro=2;
				repairVO =repairSvc.updatePro(rep_no, rep_pro);
//				new NotifyServlet().broadcast(
//						tnt_no, "您的房東婉拒了修繕申請", "請至修繕管理查看", "");
					
			}
			if (newRep_pro == 4) {
				System.out.println("已修畢");
				rep_pro=4;
				repairVO =repairSvc.updatePro(rep_no, rep_pro);
//				new NotifyServlet().broadcast(
//						tnt_no, "您的房東剛剛完成一筆修繕", "請至修繕管理確認", "");
				
			}
				
			
			if (newRep_pro == 6) {
				System.out.println("再修一次:已修畢");
				rep_pro=6;
				repVO =repairSvc.updatePro(rep_no, rep_pro);
//				new NotifyServlet().broadcast(
//						tnt_no, "您的房東剛剛完成一筆<再修繕>", "請至修繕管理確認", "");
				
			}
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("lld_no", lld_no); 
			req.setAttribute("repairVO", repairVO);
			System.out.println("準備轉交回家");
			String url = "/front-end/repair/lldListAllRepair.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		
			
		} catch (Exception e) {
			errorMsgs.add("無法讀取/修改此筆修繕紀錄:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
			failureView.forward(req, res);
			
		}
	}	
	
	//房客要進入取消申請頁面
	if("getOne_For_del_rep".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
	
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			
			/***************************2.開始查詢資料****************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO = repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/	
			req.setAttribute("repairVO", repairVO);
			String url = "/front-end/repair/repair_delete.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
			//返回上一頁
			failureView.forward(req, res);
			
		}	
	}
	
	
	//房客取消申請
	if ("delRepair".equals(action)) { 
		System.out.println("有近來");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("還沒進到try");
		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			//以下為了取得房東編號，通知
			RepairService repSvc = new RepairService();
			ConService conSvc = new ConService();
			HouseService hosSvc = new HouseService();
			RepairVO repVO = repSvc.getOneRepair(rep_no);
			ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
			HouseVO hosVO = hosSvc.getHouseInfo(conVO.getHos_no());
			String lld_no = hosVO.getLld_no();
			//接收請求參數
			String str = req.getParameter("rep_pro");
			Integer newRep_pro=null;
			if (str == null || (str.trim()).length() == 0 ) {
				errorMsgs.add("是否取消此筆修繕申請");
			}
			
			
			if (errorMsgs.isEmpty()) {
				try {
					newRep_pro=Integer.valueOf(str.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:是否取消此筆修繕申請");
				}
			}
			Integer rep_pro = newRep_pro;
			
			RepairVO repairVO = new RepairVO();
			repairVO.setRep_no(rep_no);
			repairVO.setRep_pro(rep_pro);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("repairVO", repairVO); 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始比對資料****************************************/
//			RepairService old_repairSvc = new RepairService();
//			RepairVO repair_oldVO = old_repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成(Send the Success view)************/	
//			Integer oldRep_pro = new Integer(repair_oldVO.getRep_pro());
//			if (oldRep_pro.equals(newRep_pro)) { //進度更新狀態是否與原狀態一樣
//					errorMsgs.add("無更新修繕進度");
//	            }
		
			
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("repairVO", repair_oldVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
//				failureView.forward(req, res);
//				return; //程式中斷
//			}
			
			/***************************4.比對成功(Send the Success view)************/	
//			Integer rep_pro = newRep_pro;
//			
//			RepairVO repairVO = new RepairVO();
//			repairVO.setRep_no(rep_no);
//			repairVO.setRep_pro(rep_pro);
//			
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("repairVO", repair_oldVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/repair/lld_update_repair_progress.jsp");
//				failureView.forward(req, res);
//				return; //程式中斷
//			}
			/***************************2.開始修改資料*****************************************/
			RepairService repairSvc = new RepairService();
			repairVO = repairSvc.updatePro(rep_no, rep_pro);
			new NotifyServlet().broadcast(
					lld_no, "您的房客取消了修繕申請", "請至修繕管理查看", "");
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("repairVO", repairVO); 
			System.out.println(repairVO.getRep_dam_obj());
			String url = "/front-end/repair/listAllRepair.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		
			
		} catch (Exception e) {
			errorMsgs.add("無法讀取/修改此筆修繕紀錄:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/repair_delete.jsp");
			failureView.forward(req, res);
			
		}	
	}
	
	if("getOne_For_Report".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
	
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數****************************************/
			String rep_no = req.getParameter("rep_no");
			
			/***************************2.開始查詢資料****************************************/
			RepairService repairSvc = new RepairService();
			RepairVO repairVO = repairSvc.getOneRepair(rep_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/	
			req.setAttribute("repairVO", repairVO);
			String url = "/front-end/repair/repair_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
			//返回上一頁
			failureView.forward(req, res);
			
		}	
	}
	//房客回報修繕結果
		if("updateRpt".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				
				String rep_no = req.getParameter("rep_no");
				//以下為了取得房東編號，通知
				RepairService repSvc = new RepairService();
				ConService conSvc = new ConService();
				HouseService hosSvc = new HouseService();
				RepairVO repVO = repSvc.getOneRepair(rep_no);
				ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
				HouseVO hosVO = hosSvc.getHouseInfo(conVO.getHos_no());
				String lld_no = hosVO.getLld_no();
				//接收請求參數
				String str= req.getParameter("rep_tnt_rpt");
				Integer rep_tnt_rpt=null;
				
				if (str == null || (str.trim()).length() == 0 ) {
					errorMsgs.add("請更新修繕結果");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/repair_report.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
			
				try {
					rep_tnt_rpt=Integer.valueOf(str.trim());
				}catch(Exception e) {
					errorMsgs.add("格式錯誤:請選擇更修繕結果");
					}
				
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/repair_report.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				

				
				java.sql.Date rep_tnt_rpttime=new java.sql.Date(System.currentTimeMillis());
				
				
				RepairService repairSvc = new RepairService();
				RepairVO repairVO_old =repairSvc.getOneRepair(rep_no);
				java.sql.Date rep_end_time=repairVO_old.getRep_end_time();
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairVO", repairVO_old); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/repair_report.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				RepairVO repairVO = new RepairVO();
				Integer rep_pro=null;
				//若房客滿意修繕結果
				if (rep_tnt_rpt == 1) {
					System.out.println("滿意");
				repairVO.setRep_no(rep_no);
				repairVO.setRep_tnt_rpt(rep_tnt_rpt);
				repairVO.setRep_pro(7);//狀態改為已結案:7
				repairVO.setRep_tnt_rpttime(rep_tnt_rpttime);
				repairVO.setRep_end_time(new java.sql.Date(System.currentTimeMillis()));
				rep_pro=7;
				repairSvc = new RepairService();
				repairVO =repairSvc.updateRpt(rep_no, rep_tnt_rpt, rep_pro, rep_tnt_rpttime, rep_end_time);
				new NotifyServlet().broadcast(
						lld_no, "房客更新修繕結果", "您的房客非常滿意您的修繕結果!", "");
				}
				//若房客不滿意修繕結果
				if (rep_tnt_rpt == 2) {
					System.out.println("不滿意");
					repairVO = new RepairVO();
					repairVO.setRep_no(rep_no);
					repairVO.setRep_tnt_rpt(rep_tnt_rpt);
					repairVO.setRep_pro(5);//狀態改為再修一次:5
					repairVO.setRep_tnt_rpttime(rep_tnt_rpttime);
					repairVO.setRep_end_time(repairVO_old.getRep_end_time());
					rep_pro=5;
					repairSvc = new RepairService();
					repairVO =repairSvc.updateRpt(rep_no, rep_tnt_rpt, rep_pro, rep_tnt_rpttime, rep_end_time );
//					new NotifyServlet().broadcast(
//							lld_no, "房客更新修繕結果", "您的房客不太滿意您的修繕結果，請您再次修繕", "");
					
				}
				System.out.println("rep_pro"+rep_pro);
				/***************************2.開始修改資料****************************************/
//				RepairService repairSvc = new RepairService();
//				repairVO =repairSvc.updateRpt(rep_no, rep_tnt_rpt, rep_tnt_rpt, rep_tnt_rpttime, rep_end_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
		
				req.setAttribute("repairVO", repairVO);
				String url = "/front-end/repair/listAllRepair.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("房客回報修繕結果失敗，請重新操作:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
					failureView.forward(req, res);
				}
			
			}
		
		
		
		if("lld_getOne_For_Update_enddate".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數****************************************/
				String rep_no = req.getParameter("rep_no");
				
				/***************************2.開始查詢資料****************************************/
				RepairService repairSvc = new RepairService();
				RepairVO repairVO = repairSvc.getOneRepair(rep_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/	
				req.setAttribute("repairVO", repairVO);
				String url = "/front-end/repair/lld_update_repair_enddate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/lldListAllRepair.jsp");
				//返回上一頁
				failureView.forward(req, res);
				
			}	
		}
if ("updateEnddate".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String rep_no = req.getParameter("rep_no");
				//以下為了取得房客編號，通知
				RepairService repairSvc = new RepairService();
				RepairVO repVO = repairSvc.getOneRepair(rep_no);
				ConService conSvc = new ConService();
				ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
				String tnt_no = conVO.getTnt_no();
				//
				java.sql.Date rep_est_enddate = null;
				try {
					rep_est_enddate = java.sql.Date.valueOf(req.getParameter("rep_est_enddate").trim());
				} catch (IllegalArgumentException e) {
					rep_est_enddate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入預計修畢日期!");
				}

				RepairVO repairVO = new RepairVO();
				repairVO.setRep_no(rep_no);
				repairVO.setRep_est_enddate(rep_est_enddate);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairVO", repairVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/lldListAllRepair.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				repairSvc = new RepairService();
				repairVO = repairSvc.addEnddate(rep_no, rep_est_enddate);
				new NotifyServlet().broadcast(
						tnt_no, "房東更新修繕日期", "您的房東剛剛更新了修繕日期，請至修繕管理查看", "");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repairVO", repairVO); 
				String url = "/front-end/repair/lldListAllRepair.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/repair/lldListAllRepair.jsp");
				failureView.forward(req, res);
			}
		}

			//(表單)房客要進去更新修繕
			if("getOne_For_Update".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
			
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數****************************************/
					String rep_no = req.getParameter("rep_no");
					
					/***************************2.開始查詢資料****************************************/
					RepairService repairSvc = new RepairService();
					RepairVO repairVO = repairSvc.getOneRepair(rep_no);
					/***************************3.查詢完成,準備轉交(Send the Success view)************/	
					req.setAttribute("repairVO", repairVO);
					String url = "/front-end/repair/update_repair_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法更新此筆修繕紀錄:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
					//返回上一頁
					failureView.forward(req, res);
					
				}	
			}
			//房客更改修繕的rep_dam_obj_des
			if ("tnt_update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String rep_no = req.getParameter("rep_no");
					//以下為取得lld_no作通知
					RepairService repSvc = new RepairService();
					ConService conSvc = new ConService();
					HouseService hosSvc = new HouseService();
					RepairVO repVO = repSvc.getOneRepair(rep_no);
					ConVO conVO = conSvc.getOneCon(repVO.getCon_no());
					HouseVO hosVO = hosSvc.getHouseInfo(conVO.getHos_no());
					String lld_no = hosVO.getLld_no();
					//
					String rep_dam_obj_des = req.getParameter("rep_dam_obj_des");
			
					RepairVO repairVO = new RepairVO();
					repairVO.setRep_no(rep_no);
					repairVO.setRep_dam_obj_des(rep_dam_obj_des);
				

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("repairVO", repairVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					RepairService repairSvc = new RepairService();
					repairVO = repairSvc.updateDes(rep_no, rep_dam_obj_des);
					new NotifyServlet().broadcast(
							lld_no, "房客更新修繕內容", "您的房客剛剛更新了修繕內容，請至修繕管理查看", "");
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("repairVO", repairVO); 
					String url = "/front-end/repair/listAllRepair.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			
			
			
			if("getOne_For_Display".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String rep_no = req.getParameter("rep_no");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(req.getRequestURL().toString());
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始查詢資料*****************************************/
				RepairService repairSvc = new RepairService();
				RepairVO repairVO = repairSvc.getOneRepair(rep_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repairVO", repairVO); 
				String url = "/front-end/repair/listOneRepair.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/repair/listAllRepair.jsp");
					failureView.forward(req, res);
				}
				
				}
			
			
				
	}
			}

		
	



		
	

