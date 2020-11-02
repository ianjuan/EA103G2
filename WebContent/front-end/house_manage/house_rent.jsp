<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	String lld_no = (String) session.getAttribute("lld_no");

	HouseService houseSvc = new HouseService();
	HouseVO lldInfo = houseSvc.getLldInfo(lld_no);
	List<HouseVO> list = houseSvc.getLldRentHouse(lld_no);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>已租房屋</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_rent.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_unrent.js" charset="UTF-8"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div><jsp:include page="/front-end/navbar/navbar.jsp"/></div>
	<div id="body">
		<div id="left">
			<nav id="housenav">
				<div class="menu-btn">
					<div class="line line--1"></div>
					<div class="line line--2"></div>
					<div class="line line--3"></div>
				</div>
				<div class="nav-links">
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_index.jsp" class="link">首頁</a>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney()">上架房屋</button>
					</FORM>
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_rent.jsp" class="link" style="color: #D37707;">已租房屋</a>
					<span id="count">共<%=list.size()%>間</span>
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_unrent.jsp" class="link">待租房屋</a>
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_off.jsp" class="link">下架房屋</a>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">租屋申請</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link">合約管理</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
					<button type="button" class="link">評價管理</button>
				</div>
			</nav>
		</div>
		<div id="center">
			<h3 class="houselisttitle">已租房屋</h3><hr>
			<%@ include file="page1.file"%>
			<c:forEach var="houseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/HouseSingleImgReader?id=${houseVO.hos_no}" class="pic" />
				</div>
				<div class="cinfo">
					<ul>
						<li><span class="infotitle">房屋編號 : </span><span>${houseVO.hos_no}</span></li>
						<li><span class="infotitle">房屋名稱 : </span><span>${houseVO.hos_name}</span></li>
						<li><span class="infotitle">地址 : </span><span>${houseVO.hos_add}</span></li>
						<li><span class="infotitle">房間類型 : </span><span>${houseVO.hos_room}</span></li>
						<li><span class="infotitle">每月租金 : </span><span>${houseVO.hos_rentfee}</span></li>
						<li><span class="infotitle">房屋狀態 : </span><span>${houseVO.hos_status}</span></li>
					</ul>
				</div>
					<div class="rinfo">
						<ul>
							<li><button id="btn1" type="submit" value="${houseVO.hos_no}">房屋資訊</button></li>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
								<input type="hidden" name="hos_no" value="${houseVO.hos_no}">
								<input type="hidden" name="lld_no" value="<%=lld_no%>">
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     				<input type="hidden" name="whichPage"	value="<%=whichPage%>">
								<input type="hidden" name="action" value="getHouseInfo">
								<li><button id="btn2" type="submit">資訊修改</button></li>
							</FORM>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
								<li><button id="btn3" type="submit">合約書</button></li>
			     				<input type="hidden" name="hos_no" value="${houseVO.hos_no}">
			     				<input type="hidden" name="lld_no"  value="<%=lld_no%>">
			     				<input type="hidden" name="action"	value="checklldcontract">
			     			</FORM>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
								<li><button id="btn4">定期費用</button></li>
								<input type="hidden" name="hos_no" value="${houseVO.hos_no}">
			     				<input type="hidden" name="lld_no" value="<%=lld_no%>">
			     				<input type="hidden" name="action"	value="getlldrec">
			     			</FORM>
							<li><button id="btn5">聊天</button></li>
						</ul>
					</div>
				</div>
			</c:forEach>
			<%@ include file="page2.file"%>
		</div>
			<div id="right">				
				<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="rentHouse">
					<div class="modal-dialog modal-lg">
				    	<div class="modal-content">
					      	<div id="search">
					      		<span class="count1">關鍵字搜尋：</span><input type="search" class="light-table-filter" data-table="order-table" placeholder="請輸入關鍵字">
						  		<span class="count1">共</span><span id="count1" class="count2"></span><span class="count1">筆</span>
					      		<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float:right; margin-top: 8px;margin-right: 8px;">
						        	<span aria-hidden="true">&times;</span>
						        </button>
					      	</div>
						  	<table id="list" class="order-table">
								<thead>
									<tr>
										<th class="fun">頁數</th>
										<th class="hos">編號</th>
										<th class="hos">房屋編號</th>
										<th>名稱</th>
										<th>地址</th>
										<th>房屋</th>
										<th>房間</th>
										<th>租金</th>
										<th class="fun">狀態</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="houseVO" items="${list}" varStatus="house">
									<tr>
										<td width="5%">${fn:substring((house.index/3)+1,0,fn:indexOf((house.count/3)+1, '.'))}</td>
										<td width="5%">${house.count}</td>
										<td width="11%">${houseVO.hos_no}</td>
										<td width="22%">${houseVO.hos_name}</td>
										<td width="21%">${houseVO.hos_add}</td>
										<td width="10%">${houseVO.hos_type}</td>
										<td width="10%">${houseVO.hos_room}</td>
										<td width="8%">${houseVO.hos_rentfee}</td>
										<td class="status" width="8%">${houseVO.hos_status}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			<label>
				<button type="button" class="picbtn" data-toggle="modal" data-target="#rentHouse">查看房屋清單</button>
				<img src="<%=request.getContextPath()%>/front-end/house_manage/images/pic/house.jpg" id="logo">
			</label>
		</div>
		<div id="foot"></div>
		<div id="outerdiv" style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>
		
	<script>
		$(document).on("click", "#btn1", function() {
			window.location.href='<%=request.getContextPath()%>/front-end/index/HouseDet.jsp?hos='+$(this).val();
		});
	</script>
</body>
</html>
