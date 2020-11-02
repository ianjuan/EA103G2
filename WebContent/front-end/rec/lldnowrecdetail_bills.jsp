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
	String rec_no = request.getParameter("rec_no");
	RecService recService = new RecService();
	RecVO recVO = recService.getOneRec(rec_no);
	String con_no = recVO.getCon_no();
	
	ConService conService = new ConService();
	String hos_no = (conService.getOneCon(con_no)).getHos_no();
	
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
	<div id="body">
		<div id="left">

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
				
			</div>
		</FORM>			
	</div>
	<div id="foot"></div>		
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/rec/js/detailrec.js" charset="UTF-8"></script>	
</body>
</html>