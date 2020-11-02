<%@page import="com.rec.model.RecVO"%>
<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.rec.model.*"%>

<%

	String rec_no = (String)request.getAttribute("rec_no");
	RecService recService = new RecService();
	RecVO recVO = recService.getOneRec(rec_no);
	
	ConService conService = new ConService();
	String hos_no = conService.getOneCon((recVO.getCon_no())).getHos_no();
	
	HouseService houseService = new HouseService();
	HouseVO houseVO = houseService.getHouseInfo(hos_no);
	HouseVO houseVOelectfee = houseService.getHouseElectfee(hos_no);
	HouseVO houseVOwaterfee = houseService.getHouseWaterfee(hos_no);
	
	pageContext.setAttribute("recVO", recVO);
	pageContext.setAttribute("houseVO", houseVO);
	pageContext.setAttribute("houseVOelectfee", houseVOelectfee);
	pageContext.setAttribute("houseVOwaterfee", houseVOwaterfee);
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
	<title>帳單明細</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/rec/css/detailrec.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.3.4/gsap.min.js'></script>
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
                 <a href="<%=request.getContextPath()%>/front-end/house_manage/house_index.jsp" class="link">首頁</a>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney()">上架房屋</button>
					</FORM>
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_rent.jsp" class="link">已租房屋</a>
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
						<button type="submit" class="link" style="color: #D37707;">合約管理</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repiar.servlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
					<button type="button" class="link">評價管理</button>
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