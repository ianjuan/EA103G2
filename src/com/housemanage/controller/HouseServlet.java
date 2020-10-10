package com.housemanage.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.housemanage.model.*;

public class HouseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getLldRentHouse".equals(action)) {
			String lld_no = new String(req.getParameter("lld_no"));

			HouseService houseSvc = new HouseService();
			List<HouseVO> houseVO = houseSvc.getLldRentHouse(lld_no);
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();

			req.setAttribute("houseVO", houseVO);
			req.setAttribute("lldno", lldno);
			String url = "/front-end/house_manage/house_rent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getLldUnRentHouse".equals(action)) {
			String lld_no = new String(req.getParameter("lld_no"));

			HouseService houseSvc = new HouseService();
			List<HouseVO> houseVO = houseSvc.getLldUnRentHouse(lld_no);
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();
			
			req.setAttribute("houseVO", houseVO);
			req.setAttribute("lldno", lldno);
			String url = "/front-end/house_manage/house_unrent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("getLldPub".equals(action)) {
			String lld_no = new String(req.getParameter("lld_no"));

			HouseService houseSvc = new HouseService();
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();

			req.setAttribute("lldno", lldno);
			String url = "/front-end/house_manage/house_pub.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getHouseInfo".equals(action)) {
			String hos_no = new String(req.getParameter("hos_no"));
			String lld_no = new String(req.getParameter("lld_no"));

			HouseService houseSvc = new HouseService();
			HouseVO houseVO = houseSvc.getHouseInfo(hos_no);
			HouseVO houseVOwaterfee = houseSvc.getHouseWaterfee(hos_no);
			HouseVO houseVOelectfee = houseSvc.getHouseElectfee(hos_no);
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();

			req.setAttribute("houseVO", houseVO);
			req.setAttribute("houseVOwaterfee", houseVOwaterfee);
			req.setAttribute("houseVOelectfee", houseVOelectfee);
			req.setAttribute("lldno", lldno);
			String url = "/front-end/house_manage/house_modify.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insertHouseInfo".equals(action)) {

			/*************************** 新增房屋資訊 **********************/

			String lld_no = req.getParameter("lld_no");
			String hos_name = req.getParameter("hos_name").trim();
			String hos_liffun = req.getParameter("hos_liffun").trim();
			String hos_trans = req.getParameter("hos_trans").trim();
			String hos_add = req.getParameter("hos_add").trim();
			String hos_type = req.getParameter("hos_type");
			String hos_room = req.getParameter("hos_room");
			String hos_pat = req.getParameter("hos_pat").trim();
			String hos_floor = req.getParameter("hos_floor").trim();
			Double hos_pnum = new Double(req.getParameter("hos_pnum").trim());
			Double hos_lng = new Double(req.getParameter("hos_lng").trim());
			Double hos_lat = new Double(req.getParameter("hos_lat").trim());
			String hos_status = req.getParameter("hos_status");
			Integer hos_bro = getReqNum(req, "hos_bro");
			
			/*************************** 新增房屋家具 **********************/

			Integer hos_table = getReqNum(req, "hos_table");
			Integer hos_chair = getReqNum(req, "hos_chair");
			Integer hos_bed = getReqNum(req, "hos_bed");
			Integer hos_closet = getReqNum(req, "hos_closet");
			Integer hos_sofa = getReqNum(req, "hos_sofa");
			Integer hos_tv = getReqNum(req, "hos_tv");
			Integer hos_drink = getReqNum(req, "hos_drink");
			Integer hos_aircon = getReqNum(req, "hos_aircon");
			Integer hos_refrig = getReqNum(req, "hos_refrig");
			Integer hos_wash = getReqNum(req, "hos_wash");
			Integer hos_hoter = getReqNum(req, "hos_hoter");
			Integer hos_forth = getReqNum(req, "hos_forth");
			Integer hos_net = getReqNum(req, "hos_net");
			Integer hos_gas = getReqNum(req, "hos_gas");

			/*************************** 新增房屋限制 **********************/

			String hos_mdate = req.getParameter("hos_mdate").trim();
			String hos_mindate = req.getParameter("hos_mindate");
			String hos_park = req.getParameter("hos_park");
			String hos_sex = req.getParameter("hos_sex");
			String hos_iden = req.getParameter("hos_iden");
			String hos_pet = req.getParameter("hos_pet");
			String hos_cook = req.getParameter("hos_cook");
			String hos_smoke = req.getParameter("hos_smoke");

			/*************************** 新增房屋固定費用 **********************/

			Integer hos_rentfee = new Integer(req.getParameter("hos_rentfee").trim());
			Integer hos_gasfee = new Integer(req.getParameter("hos_gasfee").trim());
			Integer hos_manafee = new Integer(req.getParameter("hos_manafee").trim());
			Integer hos_netfee = new Integer(req.getParameter("hos_netfee").trim());
			Integer hos_puwaterfee = new Integer(req.getParameter("hos_puwaterfee").trim());
			Integer hos_puelefee = new Integer(req.getParameter("hos_puelefee").trim());
			Integer hos_parkfee = new Integer(req.getParameter("hos_parkfee").trim());
			
			/*************************** 新增房屋浮動費用 **********************/

			Integer hos_waterfeetype = new Integer(req.getParameter("hos_waterfeetype"));
			
			Double hos_waterfee;
			if (hos_waterfeetype == 1) {
				hos_waterfee = new Double(req.getParameter("hos_waterfee1").trim());
			} else if (hos_waterfeetype == 2) {
				hos_waterfee = new Double(req.getParameter("hos_waterfee2").trim());
			} else {
				hos_waterfee = 0.0;
			}
			
			Integer hos_electfeetype = new Integer(req.getParameter("hos_electfeetype"));
			
			Double hos_electfee;
			if (hos_electfeetype == 1) {
				hos_electfee = new Double(req.getParameter("hos_electfee1").trim());
			} else if (hos_electfeetype == 2) {
				hos_electfee = new Double(req.getParameter("hos_electfee2").trim());
			} else {
				hos_electfee = 0.0;
			}

			/*************************** 傳入參數 **********************/

			HouseService houseSvc = new HouseService();
			HouseVO houseVOinfo = houseSvc.insertHouseInfo(lld_no, hos_name, hos_liffun, hos_trans, hos_add, hos_type, hos_room,
					hos_pat, hos_floor, hos_pnum, hos_lng, hos_lat, hos_status, hos_table, hos_chair, hos_bed,
					hos_closet, hos_sofa, hos_tv, hos_drink, hos_aircon, hos_refrig, hos_wash, hos_hoter, hos_forth,
					hos_net, hos_gas, hos_mdate, hos_mindate, hos_park, hos_sex, hos_iden, hos_pet, hos_cook, hos_smoke,
					hos_rentfee, hos_gasfee, hos_manafee, hos_netfee, hos_puwaterfee, hos_puelefee, hos_parkfee, hos_bro,
					hos_waterfeetype, hos_waterfee, hos_electfeetype, hos_electfee);
			List<HouseVO> houseVO = houseSvc.getLldUnRentHouse(lld_no);
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();
			
			req.setAttribute("houseVO", houseVO);
			req.setAttribute("lldno", lldno);
			
			String url = "/front-end/house_manage/house_unrent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("updateHouseInfo".equals(action)) {

			/*************************** 更新房屋資訊 **********************/

			String hos_name = req.getParameter("hos_name").trim();
			String hos_liffun = req.getParameter("hos_liffun").trim();
			String hos_trans = req.getParameter("hos_trans").trim();
			String hos_add = req.getParameter("hos_add").trim();
			String hos_type = req.getParameter("hos_type");
			String hos_room = req.getParameter("hos_room");
			String hos_pat = req.getParameter("hos_pat").trim();
			String hos_floor = req.getParameter("hos_floor").trim();
			Double hos_pnum = new Double(req.getParameter("hos_pnum").trim());
			Double hos_lng = new Double(req.getParameter("hos_lng").trim());
			Double hos_lat = new Double(req.getParameter("hos_lat").trim());
			String hos_status = req.getParameter("hos_status");
			String hos_no = req.getParameter("hos_no");
			String lld_no = req.getParameter("lld_no");
			
			/*************************** 更新房屋家具 **********************/

			Integer hos_table = getReqNum(req, "hos_table");
			Integer hos_chair = getReqNum(req, "hos_chair");
			Integer hos_bed = getReqNum(req, "hos_bed");
			Integer hos_closet = getReqNum(req, "hos_closet");
			Integer hos_sofa = getReqNum(req, "hos_sofa");
			Integer hos_tv = getReqNum(req, "hos_tv");
			Integer hos_drink = getReqNum(req, "hos_drink");
			Integer hos_aircon = getReqNum(req, "hos_aircon");
			Integer hos_refrig = getReqNum(req, "hos_refrig");
			Integer hos_wash = getReqNum(req, "hos_wash");
			Integer hos_hoter = getReqNum(req, "hos_hoter");
			Integer hos_forth = getReqNum(req, "hos_forth");
			Integer hos_net = getReqNum(req, "hos_net");
			Integer hos_gas = getReqNum(req, "hos_gas");

			/*************************** 更新房屋限制 **********************/

			String hos_mdate = req.getParameter("hos_mdate").trim();
			String hos_mindate = req.getParameter("hos_mindate");
			String hos_park = req.getParameter("hos_park");
			String hos_sex = req.getParameter("hos_sex");
			String hos_iden = req.getParameter("hos_iden");
			String hos_pet = req.getParameter("hos_pet");
			String hos_cook = req.getParameter("hos_cook");
			String hos_smoke = req.getParameter("hos_smoke");

			/*************************** 更新房屋固定費用 **********************/

			Integer hos_rentfee = new Integer(req.getParameter("hos_rentfee").trim());
			Integer hos_gasfee = new Integer(req.getParameter("hos_gasfee").trim());
			Integer hos_manafee = new Integer(req.getParameter("hos_manafee").trim());
			Integer hos_netfee = new Integer(req.getParameter("hos_netfee").trim());
			Integer hos_puwaterfee = new Integer(req.getParameter("hos_puwaterfee").trim());
			Integer hos_puelefee = new Integer(req.getParameter("hos_puelefee").trim());
			Integer hos_parkfee = new Integer(req.getParameter("hos_parkfee").trim());
			
			/*************************** 更新房屋浮動費用 **********************/

			Integer hos_waterfeetype = new Integer(req.getParameter("hos_waterfeetype"));
			
			Double hos_waterfee;
			if (hos_waterfeetype == 1) {
				hos_waterfee = new Double(req.getParameter("hos_waterfee1").trim());
			} else if (hos_waterfeetype == 2) {
				hos_waterfee = new Double(req.getParameter("hos_waterfee2").trim());
			} else {
				hos_waterfee = 0.0;
			}
			
			Integer hos_electfeetype = new Integer(req.getParameter("hos_electfeetype"));
			
			Double hos_electfee;
			if (hos_electfeetype == 1) {
				hos_electfee = new Double(req.getParameter("hos_electfee1").trim());
			} else if (hos_electfeetype == 2) {
				hos_electfee = new Double(req.getParameter("hos_electfee2").trim());
			} else {
				hos_electfee = 0.0;
			}
			
			/*************************** 傳入參數 **********************/

			HouseService houseSvc = new HouseService();
			
			HouseVO houseVOinfo = houseSvc.updateHouseInfo(hos_name, hos_liffun, hos_trans, hos_add, hos_type, hos_room, hos_pat,
					hos_floor, hos_pnum, hos_lng, hos_lat, hos_status, hos_table, hos_chair, hos_bed, hos_closet,
					hos_sofa, hos_tv, hos_drink, hos_aircon, hos_refrig, hos_wash, hos_hoter, hos_forth, hos_net,
					hos_gas, hos_mdate, hos_mindate, hos_park, hos_sex, hos_iden, hos_pet, hos_cook, hos_smoke,
					hos_rentfee, hos_gasfee, hos_manafee, hos_netfee, hos_puwaterfee, hos_puelefee, hos_parkfee,
					hos_waterfeetype, hos_waterfee, hos_electfeetype, hos_electfee, hos_no);
			List<HouseVO> houseVO = houseSvc.getLldUnRentHouse(lld_no);
			HouseVO houseVOlld = houseSvc.getHouseLld(lld_no);
			String lldno = houseVOlld.getLld_no();
			
			req.setAttribute("houseVO", houseVO);
			req.setAttribute("lldno", lldno);
			
			String url;
			if (hos_status.equals("出租中")) {
				url = "/front-end/house_manage/house_rent.jsp";
			} else {
				url = "/front-end/house_manage/house_unrent.jsp";
			}
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

	public Integer getReqNum(HttpServletRequest req, String reqKey) {
		String reqValue = req.getParameter(reqKey);
		Integer result;
		try {
			result = new Integer(reqValue);
		} catch (NumberFormatException e) {
			result = 0;
		}
		return result;
	}
}
