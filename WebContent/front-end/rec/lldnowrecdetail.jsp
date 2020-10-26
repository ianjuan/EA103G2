<%@page import="com.rec.model.RecVO"%>
<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%@ page import="com.tnt.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseVO houseVOwaterfee = (HouseVO) request.getAttribute("housewatVO");
	HouseVO houseVOelectfee = (HouseVO) request.getAttribute("houseeleVO");
	
	RecVO recVO = (RecVO) request.getAttribute("recVO");
	TntVO tntVO = (TntVO) request.getAttribute("tntVO");
 	
 	String lld_no = (String) session.getAttribute("lld_no");
	if (lld_no == null) {
		lld_no = request.getParameter("lld_no");
	}
	
	String con_no = (String) request.getAttribute("con_no");
	
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
	if (lldInfo == null) {
		HouseService houseSvc = new HouseService();
		lldInfo = houseSvc.getLldInfo(lld_no);
	}
	
	pageContext.setAttribute("houseVO", houseVO);
%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>House_Pub</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/rec/css/detailrec.css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">愛租I-ZU</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav ml-auto">
                    <a class="nav-item nav-link active" href="#">尋找房源<span class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="#">地圖找房</a>
                    <a class="nav-item nav-link" href="<%=request.getContextPath()%>/front-end/house_manage/housemanage_index.jsp">我的房屋</a>
                    <li class="nav-item dropdown">
                        <span data-toggle="dropdown" class="member">
                            <input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" class="memberpic" />
                            <span class="membername"><%=lldInfo.getLld_name()%></span>
                        </span>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">最新通知</a>
                            <a class="dropdown-item" href="#">個人資訊</a>
                            <a class="dropdown-item" href="#">我的錢包</a>
                            <a class="dropdown-item" href="#">登出</a>
                        </div>
                    </li>
                </div>
            </div>
        </div>
    </nav>
	<div id="body">
		<div id="left">
			<nav id="housenav">
                <div class="menu-btn">
                    <div class="line line--1"></div>
                    <div class="line line--2"></div>
                    <div class="line line--3"></div>
                </div>                
                <div class="nav-links">
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldAllHouse">
						<button type="submit" class="link">首頁</button>
					</FORM>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">待租房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">已租房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldOffHouse">
						<button type="submit" class="link">下架房屋</button>
					</FORM>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney()">上架房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
					</FORM>
                </div>
            </nav>
		</div>
		<form class="table" name="recForm" METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
			<div id="center">
				<div id="chead">
					<ul>
						
					</ul>
				</div>		
				<div id="cbody">				
					<div id="cbody1">
						<table cellpadding="11">
							
							<tr>
								<th>房客姓名:</th>
								<td>
									<%=tntVO.getTnt_name()%>
								</td>
							</tr>
							
							<tr>
								<th>房屋名稱:</th>
								<td>
									<%=houseVO.getHos_name()%>
								</td>
							</tr>
							
							<tr>
								<th>每月租金:</th>
								<td>
									<%=houseVO.getHos_rentfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>每度電費:</th>
								<td>
									<%=houseVOelectfee.getHos_electfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>本月電用量:</th>
								<td>
									<%=recVO.getRec_elec()%>度
								</td>
							</tr>
							
							<tr>
								<th>每度水費:</th>
								<td>
									<%=houseVOwaterfee.getHos_waterfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>本月水用量:</th>
								<td>
									<%=recVO.getRec_water()%>度
								</td>
							</tr>
							
							<tr>
								<th>瓦斯費:</th>
								<td>
									<%=houseVO.getHos_gasfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>管理費:</th>
								<td>
									<%=houseVO.getHos_manafee()%>元
								</td>
							</tr>
							
							<tr>
								<th>網路費:</th>
								<td>
									<%=houseVO.getHos_netfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>公共水費:</th>
								<td>
									<%=houseVO.getHos_puwaterfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>公共電費:</th>
								<td>
									<%=houseVO.getHos_puelefee()%>元
								</td>
							</tr>
							
							<tr>
								<th>停車位費:</th>
								<td>
									<%=houseVO.getHos_parkfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>瓦斯費:</th>
								<td>
									<%=houseVO.getHos_gasfee()%>元
								</td>
							</tr>
							
							<tr>
								<th>總共:</th>
								<td>
									<%=recVO.getRec_total()%>元
								</td>
							</tr>
							
							
							
						</table>
					</div>		        				
				</div>
				<input type="hidden" name="lld_no" value="<%=lld_no%>">
				<input type="hidden" name="con_no" value="<%=con_no%>">
				<div id="cfoot">
					<button class="btn" type="button" onclick="notice1">修改申請</button>
					<input type="hidden" name="action" value="getlldrec">
					<button class="btn">確定</button>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
					<ul>
					</ul>
				</div>
				<div id="rfoot">
					<button class="btn" type="button" onclick="notice1">修改申請</button>
					<input type="hidden" name="action" value="getlldrec">
					<button class="btn">確定</button>
				</div>
			</div>
		</FORM>			
	</div>
	<div id="foot"></div>		
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/rec/js/detailrec.js" charset="UTF-8"></script>	
</body>
</html>