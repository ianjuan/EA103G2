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
	LldVO lldVO = (LldVO) request.getAttribute("lldVO");
 	
 	String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	String con_no = (String) request.getAttribute("con_no");
	
	pageContext.setAttribute("houseVO", houseVO);
	pageContext.setAttribute("tnt_no", tnt_no);
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
	<title>定期帳單</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/rec/css/tntdetailrec.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div><jsp:include page="/front-end/navbar/navbar.jsp"/> </div>
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
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="getTntRepair">
						<button type="submit" class="link">修繕管理</button><br>
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
								<th>房東姓名:</th>
								<td>
									<%=lldVO.getLld_name()%>
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
				<input type="hidden" name="rec_no" value="<%=recVO.getRec_no()%>">
				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
				<input type="hidden" name="con_no" value="<%=con_no%>">
				<input type="hidden" id="tnt_total" name="tnt_total" value="<%=recVO.getRec_total()%>">
				<input type="hidden" id="tnt_balance" name="tnt_balance" value="<%=tntSvc.getOneTntPocket(tnt_no).getTnt_balance()%>">
				<div id="cfoot">
					<c:if test="${recVO.rec_sta == 1}">
					<button class="btn" type="button" onclick="notice2()">修改申請</button>
					<input type="hidden" name="action" value="tntpayrec">
					<button class="btn" type="button" onclick="checkmoney()">繳交費用</button>
					</c:if>
					<c:if test="${recVO.rec_sta == 2}">
					<button class="btn">確定</button>
					</c:if>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
					<ul>
					</ul>
				</div>
				<div id="rfoot">
					<c:if test="${recVO.rec_sta == 1}">
					<button class="btn" type="button" onclick="notice2()">修改申請</button>
					<input type="hidden" name="action" value="tntpayrec">
					<button class="btn" type="button" onclick="checkmoney()">繳交費用</button>
					</c:if>
					<c:if test="${recVO.rec_sta == 2}">
					<input type="hidden" name="action" value="tntpayrec">
					<button class="btn">確定</button>
					</c:if>	
				</div>
			</div>
		</FORM>			
	</div>
	<div id="foot"><%@ include file="/front-end/index/footer.jsp" %></div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/rec/js/detailrec.js" charset="UTF-8"></script>		
</body>
</html>