<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%	
	HouseService houseSvc = new HouseService();
	List<HouseVO> list = houseSvc.getAllHouse();	
	pageContext.setAttribute("list", list);	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All_House</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/back-end/house_manage/css/all_house.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/house_manage/js/all_house.js" charset="UTF-8"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
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
					<a class="nav-item nav-link" href="<%=request.getContextPath()%>/back-end/house_manage/all_house.jsp">房屋管理</a>
					<li class="nav-item dropdown">
						<span data-toggle="dropdown" class="member">
							<input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" class="memberpic" />
							<span class="membername">管理員</span>
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
		<div id="left"></div>
		<div id="center">
			<div id="chead">
				關鍵字搜尋：<input type="search" class="light-table-filter" data-table="order-table" placeholder="請輸入關鍵字">
				<span class="count2">此頁共</span><span id="count3" class="count3">2000</span><span class="count2">筆</span>
			</div>
			<div id="cbody">
				<%@ include file="page2.file"%>
				<%@ include file="page1.file"%>
				<table id="list" class="order-table">
					<thead>
						<tr>
							<th>編號</th>
							<th>房屋編號</th>
							<th class="lld">房東編號</th>
							<th class="lld">房東</th>
							<th>地址</th>
							<th>房屋類型</th>
							<th>房間類型</th>
							<th>房屋狀態</th>
							<th class="fun">房屋資訊</th>
							<th class="fun">資訊修改</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="houseVO" items="${list}" varStatus="house" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >					
						<tr>
							<td>${house.count}</td>
							<td>${houseVO.hos_no}</td>
							<td>${houseVO.lld_no}</td>
							<td>${houseVO.lld_name}</td>
							<td>${houseVO.hos_add}</td>
							<td>${houseVO.hos_type}</td>
							<td>${houseVO.hos_room}</td>
							<td id="status">${houseVO.hos_status}</td>
							<td><button class="btn">查看</button></td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet_BE">
									<input type="hidden" name="hos_no" value="${houseVO.hos_no}">
									<input type="hidden" name="action" value="getHouseInfo">
									<button class="btn" type="submit">修改</button>
								</FORM>
							</td>
						</tr>					
					</c:forEach>
					</tbody>
				</table>
			</div>
				
		<div id="right"></div>
	</div>
	<div id="foot"></div>
</body>
</html>
