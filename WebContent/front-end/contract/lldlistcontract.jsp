<%@page import="com.lld.model.LldVO"%>
<%@page import="com.tnt.model.TntService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cont.model.*"%>

<%
	String lldno = (String) request.getAttribute("lldno");
	if (lldno == null) {
		lldno = request.getParameter("lldno");
	}
	
	List<HouseVO> list = (List<HouseVO>) request.getAttribute("houseVO");
	if (list == null) {
		HouseService houseSvc = new HouseService();
		list = houseSvc.getLldRentHouse(lldno);
	}
	
	LldVO lldVO = (LldVO) request.getAttribute("lldVO");
	HouseVO houseVOlld = (HouseVO) request.getAttribute("houseVOlld");
	pageContext.setAttribute("lldVO", lldVO);
	pageContext.setAttribute("houseVOlld", houseVOlld);

%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>House_Rent</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/house_rent.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/house_rent.js" charset="UTF-8"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
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
                    <a class="nav-item nav-link" href="<%=request.getContextPath()%>/front-end/house_manage/select_page.jsp">我的房屋</a>
                    <li class="nav-item dropdown">
                        <span data-toggle="dropdown" class="member">
                            <input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" class="memberpic" />
                            <span class="membername">彭于晏</span>
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
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/house.do">
						<input type="hidden" name="lld_no" value="<%=lldno%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">待租房屋</button>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/house.do">
						<input type="hidden" name="lld_no" value="<%=lldno%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">已租房屋</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/house.do">
						<input type="hidden" name="lld_no" value="<%=lldno%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="submit" class="link">上架房屋</button>
					</FORM>
					
					<button type="submit" class="link">預約管理</button>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lldno%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">租屋申請</button><br>
						<span id="count">共<%=listapl.size()%>個申請</span>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lldno%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
						<span id="count">共<%=listapl.size()%>個合約</span>
					</FORM>
				</div>
			</nav>
		</div>
		<div id="center">
			<%@ include file="page1.file"%>
			<c:forEach var="con_aplVO" items="${listapl}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="houseinfo">
					<div class="linfo">
						<img
							src="<%=request.getContextPath()%>/front-end/apl/images/aplimage.jpg"
							class="pic" />
					</div>
					<div class="cinfo">
						<ul>
							<li><span class="infotitle">合約申請編號 : </span><span>${conVO.con_no}</span></li>
							<li><span class="infotitle">房屋名稱 : </span><span>${hosSvc.getHouseInfo(con_aplVO.hos_no).hos_name}</span></li>
							<li><span class="infotitle">房客姓名 : </span><span>${tntSvc.getOneTntProfile(con_aplVO.tnt_no).tnt_name}</span></li>
<%-- 							<li><span class="infotitle">租屋申請時間 : </span><span>${con_aplVO.apl_time}</span></li> --%>
<%-- 							<li><span class="infotitle">租屋開始時間 : </span><span>${con_aplVO.apl_str}</span></li> --%>
<%-- 							<li><span class="infotitle">租屋結束時間 : </span><span>${con_aplVO.apl_end}</span></li> --%>
							<li><span class="infotitle">合約狀態 : </span><span>${conSvc.getConstatusText(conVO.getcon_status())}</span></li>
						</ul>
					</div>					
						<div class="rinfo">
							<ul>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
								<li><button id="btn1">接受申請</button></li>
								<input type="hidden" name="apl_no"  value="${con_aplVO.apl_no}">
			     				<input type="hidden" name="apl_status" value=1>
			     				<input type="hidden" name="lld_no" value="<%=lldno%>">
			     				<input type="hidden" name="action"	value="lldupdate">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
								<li><button id="btn2">拒絕申請</button></li>
								<input type="hidden" name="apl_no"  value="${con_aplVO.apl_no}">
			     				<input type="hidden" name="apl_status" value=2>
			     				<input type="hidden" name="lld_no" value="<%=lldno%>">
			     				<input type="hidden" name="action"	value="lldupdate">
			     				</FORM>
			     				
								<li><button id="btn3">聊天</button></li>
								
														
							</ul>
						</div>					
					</div>
			</c:forEach>
			<div id="right"></div>
		</div>
		<div id="foot"></div>
		<div id="outerdiv"
			style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>
</body>
</html>
