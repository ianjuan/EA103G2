package com.apl.controller;

import java.io.IOException;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apl.model.Con_aplDAO;
import com.apl.model.Con_aplService;
import com.apl.model.Con_aplVO;
import com.cont.model.ConService;
import com.cont.model.ConVO;
import com.housemanage.model.HouseService;
import com.housemanage.model.HouseVO;
import com.notify.controller.NotifyServlet;

public class Con_aplServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("lldgetAll".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String lld_no = req.getParameter("lld_no");

				/*************************** 2.開始查詢資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				List<Con_aplVO> apllist = con_aplService.lldgetAll(lld_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("lld_no", lld_no);
				String url = "/front-end/apl/lldaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntgetallapl".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String tnt_no = req.getParameter("tnt_no");

				/*************************** 2.開始查詢資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				List<Con_aplVO> apllist = con_aplService.tntgetAll(tnt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				String url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// from fu house page
		if ("hosgetall".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String lld_no = req.getParameter("lld_no");
				String hos_no = req.getParameter("hos_no");
				System.out.println(hos_no);

				Con_aplService con_aplService = new Con_aplService();
				List<Con_aplVO> hosapllist = con_aplService.hosgetall(hos_no);

				HttpSession session = req.getSession();
				req.setAttribute("hos_no", hos_no);
				session.setAttribute("lld_no", lld_no);
				String url = "/front-end/apl/hoslistapl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// from Eason's page
		if ("addfromhouse".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String tnt_no = req.getParameter("tnt_no");
				String hos_no = req.getParameter("hos_no");

				java.sql.Date apl_str = java.sql.Date.valueOf(req.getParameter("apl_str").replace("/", "-"));
				java.sql.Date apl_end = java.sql.Date.valueOf(req.getParameter("apl_end").replace("/", "-"));

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				con_aplSvc.addfromhouse(tnt_no, hos_no, apl_str, apl_end);

				HouseService houseService = new HouseService();
				String userNo = houseService.getHouseInfo(hos_no).getLld_no();
				String title = "您有一則新的租屋申請";
				String content = "一位房客向您提出申請";
				String url = "/EA103G2/front-end/apl/lldaplpage.jsp";
				new NotifyServlet().broadcast(userNo, title, content, url);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/updateCon_apl_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("lldupdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = new String(req.getParameter("apl_no").trim());
				String lld_no = req.getParameter("lld_no");
				String hos_no = req.getParameter("hos_no");

				Integer apl_status = new Integer(req.getParameter("apl_status").trim());

				/*************************** 來自租屋申請頁面 *****************************************/
				if (hos_no == null) {

					Con_aplService con_aplService = new Con_aplService();

					if (apl_status == 1) {

						Con_aplVO con_aplVO = new Con_aplVO();
						con_aplVO = con_aplService.getOneCon_apl(apl_no);
						String tnt_no = con_aplVO.getTnt_no();
						hos_no = con_aplVO.getHos_no();

						String tem = apl_no;

						// reject others application
						List<Con_aplVO> list = con_aplService.hosgetall(hos_no);
						for (Con_aplVO hos : list) {
							apl_no = hos.getApl_no();
							apl_status = 2;

							con_aplService.lldUpdateCon_apl(apl_no, apl_status);
						}

						// get the origin one back
						apl_no = tem;
						apl_status = 1;

						// create new contract
						ConService conService = new ConService();
						conService.addbeforerent(apl_no, tnt_no, hos_no);

						//notify lld
						String userNo = lld_no;
						String title = "已產生新合約";
						String content = "請準備簽署合約";
						String url = "/EA103G2/front-end/contract/lldlistcontract.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);
						
						//notify tnt
						userNo = tnt_no;
						title = "已產生新合約";
						content = "請準備簽署合約";
						url = "/EA103G2/front-end/contract/tntlistcontract.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);

						// house no longer be sold
						HouseService houseService = new HouseService();
						String hos_status = "出租中";
						houseService.updateStatus(hos_status, hos_no);

						userNo = lld_no;
						title = "已更新房屋狀態";
						content = "已將房屋更改為出租中";
						url = "/EA103G2/front-end/house_manage/house_rent.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);

						con_aplService.lldUpdateCon_apl(apl_no, apl_status);
						List<Con_aplVO> conlist = con_aplService.lldgetAll(lld_no);

						HttpSession session = req.getSession();
						session.setAttribute("lld_no", lld_no);
						session.setAttribute("conlist", conlist);
						url = "/front-end/contract/lldlistcontract.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					
					String userNo = con_aplService.getOneCon_apl(apl_no).getTnt_no();
					String title = "已更新租屋申請";
					String content = "您的租屋申請已被拒絕,可以前往查看愛租幫您推薦的網站";
					String url = "/EA103G2/front-end/apl/tntaplpage.jsp";
					
					new NotifyServlet().broadcast(userNo, title, content, url);
					
					con_aplService.lldUpdateCon_apl(apl_no, apl_status);
					List<Con_aplVO> apllist = con_aplService.lldgetAll(lld_no);
					
					HttpSession session = req.getSession();
					session.setAttribute("lld_no", lld_no);
					url = "/front-end/apl/lldaplpage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;

				} else {
					// 來自房屋網頁
					Con_aplService con_aplService = new Con_aplService();

					if (apl_status == 1) {

						Con_aplVO con_aplVO = new Con_aplVO();
						con_aplVO = con_aplService.getOneCon_apl(apl_no);
						String tnt_no = con_aplVO.getTnt_no();

						String tem = apl_no;

						// reject others application
						List<Con_aplVO> list = con_aplService.hosgetall(hos_no);
						for (Con_aplVO hos : list) {
							apl_no = hos.getApl_no();
							apl_status = 2;

							con_aplService.lldUpdateCon_apl(apl_no, apl_status);
						}

						// get the origin one back
						apl_no = tem;
						apl_status = 1;

						ConService conService = new ConService();
						conService.addbeforerent(apl_no, tnt_no, hos_no);

						HouseService houseService = new HouseService();
						String hos_status = "出租中";
						houseService.updateStatus(hos_status, hos_no);
						
						String userNo = tnt_no;
						String title = "房屋申請已核准";
						String content = "請準備簽署合約";
						String url = "/EA103G2/front-end/contract/tntlistcontract.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);
						
						userNo = lld_no;
						title = "已產生新合約";
						content = "請準備簽署合約";
						url = "/EA103G2/front-end/contract/tntlistcontract.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);

						userNo = lld_no;
						title = "已更新房屋狀態";
						content = "已將房屋更改為出租中";
						url = "/EA103G2/front-end/house_manage/house_rent.jsp";
						new NotifyServlet().broadcast(userNo, title, content, url);

					}

					con_aplService.lldUpdateCon_apl(apl_no, apl_status);

					System.out.println(apl_status);
					HttpSession session = req.getSession();

					// once lld accepts it, forward to con page
					if (apl_status == 1) {
						ConService conService = new ConService();
						List<ConVO> conlist = conService.lldgetcon(lld_no);
						session.setAttribute("lld_no", lld_no);
						session.setAttribute("conlist", conlist);

						String url = "/front-end/contract/lldlistcontract.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;

					} else {
						// or keep checking
						List<Con_aplVO> hosapllist = con_aplService.hosgetall(hos_no);
						req.setAttribute("hosapllist", hosapllist);
						session.setAttribute("lld_no", lld_no);
						req.setAttribute("hos_no", hos_no);

						String url = "/front-end/apl/hoslistapl.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
					}
				}

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/lldaplpage.jsp");
				failureView.forward(req, res);
			}
		}

		// tnt update apl date
		if ("getOne_For_tntUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String apl_no = new String(req.getParameter("apl_no"));
				String tnt_no = new String(req.getParameter("tnt_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Con_aplService con_aplSvc = new Con_aplService();
				Con_aplVO con_aplVO = con_aplSvc.getOneCon_apl(apl_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				req.setAttribute("con_aplVO", con_aplVO);
				req.setAttribute("apl_no", apl_no);
				session.setAttribute("tnt_no", tnt_no);
				String url = "/front-end/apl/tntupdateapl.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/listAllCon_apl.jsp");
				failureView.forward(req, res);
			}
		}

		// update application date
		if ("tntupdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = req.getParameter("apl_no");
				String tnt_no = req.getParameter("tnt_no");
				Date apl_str = Date.valueOf(req.getParameter("apl_str"));
				Date apl_end = Date.valueOf(req.getParameter("apl_end"));
				
				System.out.println(apl_no);
				System.out.println(apl_str);
				System.out.println(apl_end);

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				con_aplService.tntupdateCon_apl(apl_no, apl_str, apl_end);

				List<Con_aplVO> apllist = con_aplService.tntgetAll(tnt_no);

				HouseService houseService = new HouseService();
				String userNo = houseService.getHouseInfo(con_aplService.getOneCon_apl(apl_no).getHos_no()).getLld_no();
				String title = "租屋申請更新";
				String content = "房客已更新租屋申請時間";
				String url = "/EA103G2/front-end/apl/lldaplpage.jsp";
				new NotifyServlet().broadcast(userNo, title, content, url);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntcancelapl".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = req.getParameter("apl_no");
				String tnt_no = req.getParameter("tnt_no");
				Integer apl_status = 3;

				/*************************** 2.開始修改資料 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				con_aplService.lldUpdateCon_apl(apl_no, apl_status);

				List<Con_aplVO> apllist = con_aplService.tntgetAll(tnt_no);

				HouseService houseService = new HouseService();
				String userNo = houseService.getHouseInfo(con_aplService.getOneCon_apl(apl_no).getHos_no()).getLld_no();
				String title = "租屋申請更新";
				String content = "房客已取消租屋申請";
				String url = "/EA103G2/front-end/apl/lldaplpage.jsp";
				new NotifyServlet().broadcast(userNo, title, content, url);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				url = "/front-end/apl/tntaplpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("tntrecommend".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String apl_no = req.getParameter("apl_no");
				String tnt_no = req.getParameter("tnt_no");

				/*************************** 尋找類似房源 *****************************************/
				Con_aplService con_aplService = new Con_aplService();
				String hos_no = con_aplService.getOneCon_apl(apl_no).getHos_no();

				HouseService houseService = new HouseService();
				HouseVO houseVO = houseService.getHouseInfo(hos_no);
				String hos_rentfee = String.valueOf(houseVO.getHos_rentfee());
				String hos_add = (String) houseVO.getHos_add().subSequence(0, 5);
				String hos_room = houseVO.getHos_room();

				Map<String, String> map = new HashMap<String, String>();
				map.put("hos_rentfee", hos_rentfee);
				map.put("hos_add", hos_add);
				map.put("hos_room", hos_room);

				List<HouseVO> reccomandlist = con_aplService.getAll(map);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("tnt_no", tnt_no);
				req.setAttribute("reccomandlist", reccomandlist);
				String url = "/front-end/apl/recommendhos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/apl/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}