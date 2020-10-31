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
	String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	List<ConVO> list = (List<ConVO>)session.getAttribute("list");
	session.setAttribute("list",list);
	
	TntService tntService = new TntService();
	TntVO tntVO = tntService.getOneTntProfile(tnt_no);
%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合約管理</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/contlist_tnt.css">
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
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="tntgetallapl">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="gettntcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
						<span id="count">共<%=list.size()%>個合約</span>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="getTntRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
					
				</div>
			</nav>
		</div>
		<div id="center">
		<h3 class="houselisttitle">合約管理</h3><hr>
			<%@ include file="tntpage1"%>
			<c:forEach var="conVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="houseinfo">
					<div class="linfo">
						<c:if test="${conVO.con_sta == 0}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/lldunsign.png"
							class="pic" />
						</c:if>
						<c:if test="${conVO.con_sta == 1}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/tntunsign.png"
							class="pic" />
						</c:if>
						<c:if test="${conVO.con_sta == 2}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/signed.png"
							class="pic" />
						</c:if>
						<c:if test="${conVO.con_sta == 3}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/checked.png"
							class="pic" />
						</c:if>
						<c:if test="${conVO.con_sta == 4 || conVO.con_sta == 5}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/lldcheckroom.png"
							class="pic" />
						</c:if>
						<c:if test="${conVO.con_sta == 6}">
						<img
							src="<%=request.getContextPath()%>/front-end/contract/images/tntleft.png"
							class="pic" />
						</c:if>
					</div>
					<div class="cinfo">
						<ul>
							<li><span class="infotitle">合約編號 : </span><span>${conVO.con_no}</span></li>
							<li><span class="infotitle">房屋名稱 : </span><span>${hosSvc.getHouseInfo(conVO.hos_no).hos_name}</span></li>
							<li><span class="infotitle">房東姓名 : </span><span>${lldSvc.getOneLldProfile(hosSvc.getHouseInfo(conVO.hos_no).lld_no).lld_name}</span></li>
<%-- 							<li><span class="infotitle">租屋申請時間 : </span><span>${con_aplVO.apl_time}</span></li> --%>
<%-- 							<li><span class="infotitle">租屋開始時間 : </span><span>${con_aplVO.apl_str}</span></li> --%>
<%-- 							<li><span class="infotitle">租屋結束時間 : </span><span>${con_aplVO.apl_end}</span></li> --%>
							<li><span class="infotitle">合約狀態 : </span><span>${conSvc.getConstatusText(conVO.con_sta)}</span></li>
						</ul>
					</div>					
						<div class="rinfo">
							<ul>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
								<c:if test="${conVO.con_sta != 0 && conVO.con_sta != 1}">
								<li><button id="btn1">合約書</button></li>
								</c:if>
			     				<input type="hidden" name="con_no" value="${conVO.con_no}">
			     				<input type="hidden" name="hos_no"  value="${conVO.hos_no}">
			     				<input type="hidden" name="action"	value="checktntcontract">
			     				</FORM>
			     				
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
								<c:if test="${conVO.con_sta == 1}">
								<li><button id="btn1">簽署合約</button></li>
								</c:if>
								<input type="hidden" name="con_no"  value="${conVO.con_no}">
								<input type="hidden" name="hos_no"  value="${conVO.hos_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="getonetntcontract">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
								<c:if test="${conVO.con_sta != 0 && conVO.con_sta != 1 && conVO.con_sta != 2}">
								<li><button id="btn2">定期費用</button></li>
								</c:if>
			     				<input type="hidden" name="con_no" value="${conVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="gettntrec">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
								<c:if test="${conVO.con_sta == 3 || conVO.con_sta == 4}">
								<li><button id="btn3">修繕申請</button></li>
								</c:if>
			     				<input type="hidden" name="con_no" value="${conVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="getTntRepair">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
			     				<c:if test="${conVO.con_sta == 5}">
								<li><button id="btn4">準備退房</button></li>
								</c:if>
			     				<input type="hidden" name="con_no" value="${conVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="tntcheckout">
			     				</FORM>
																					
							</ul>
						</div>					
					</div>
			</c:forEach>
			<%@ include file="tntpage2.file"%>
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

