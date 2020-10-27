<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%@ page import="com.rec.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	
	ConVO conVO = (ConVO)request.getAttribute("conVO");
	
	String checkouttotal = (String)request.getAttribute("checkouttotal");
	
	List<RecVO> list = (List<RecVO>)request.getAttribute("list");
 	
 	String tnt_no = (String) request.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	
	pageContext.setAttribute("houseVO", houseVO);
	pageContext.setAttribute("conVO", conVO);
	pageContext.setAttribute("tnt_no", tnt_no);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("checkouttotal", checkouttotal);
%>

<jsp:useBean id="recSvc" scope="page" class="com.rec.model.RecService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>驗房結果</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/checkroom.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/contract/js/checkroom.js" charset="UTF-8"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
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
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repiar.servlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="getTntRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
                </div>
            </nav>
		</div>
		<form class="table" name="checkForm" METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet" enctype="multipart/form-data">
			<div id="center">
				<div id="chead">
				</div>		
				<div id="cbody">				
					<div id="cbody1">
						<table cellpadding="11">
							
							<tr>
								<th>房屋名稱:</th>
								<td>
									<%=houseVO.getHos_name()%>
								</td>
							</tr>
							
							<tr>
								<th>驗房日期:</th>
								<td>
									<%=conVO.getCon_chkdate()%>
								</td>
							</tr>
							
							<tr>
								<th>是否有損毀:</th>
								<td>
									<c:if test="${conVO.con_chr_fee == 0}">
									否
								</c:if>
								<c:if test="${conVO.con_chr_fee != 0}">
									否
								</c:if>
								</td>
							</tr>
							
							<tr>
								<th>損毀物品:</th>
								<td>
									<%=conVO.getCon_chr_itm_name()%>
								</td>
							</tr>
							
							<tr>
								<th>損毀描述:</th>
								<td>
									<%=conVO.getCon_chr_itm()%>
								</td>
							</tr>
							
							<tr>
								<th>毀損費用:</th>
								<td>
									<%=conVO.getCon_chr_fee()%>元
								</td>
							</tr>
							
							
							<tr>
								<th>是否繳清帳單:</th>
								<td>
								<c:if test="${recVO.rec_total == 0}">
									是
								</c:if>
								<c:if test="${recVO.rec_total != 0}">
									否
								</c:if>
								</td>
							</tr>
							
							<c:forEach var="recVO" items="${list}">
							<tr>
								<th>尚未繳清帳單:</th>
								<td>
									${recSvc.getMonthText(recVO.rec_mon)}
								</td>
							</tr>
							<tr>
								<th>尚未繳清帳單費用:</th>
								<td>
									${recVO.rec_total}元
								</td>
							</tr>
							</c:forEach>
							<tr>
								<th>總共:</th>
								<td>
									<%=checkouttotal%>元
								</td>
							</tr>
							
						</table>
					</div>
				<input type="hidden" name="lld_no" value="<%=houseVO.getLld_no()%>">
				<input type="hidden" name="con_no" value="<%=conVO.getCon_no()%>">
				<input type="hidden" name="hos_no" value="<%=houseVO.getHos_no()%>">
				<div id="cfoot">
					<button class="btn" type="button" onclick="notice2()">全部重填</button>
					<input type="hidden" name="action" value="lldcheckroom">
					<button class="btn" type="button" onclick="notice1()">送出結果</button>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
				</div>
				<div id="rfoot">
					<button class="btn" type="button" onclick="notice2()">全部重填</button>
					<input type="hidden" name="action" value="lldcheckroom">
					<button class="btn" type="button" onclick="notice1()">送出結果</button>
				</div>
			</div>
		</form>
	</div>
	<div id="foot"></div>		
</body>
</html>