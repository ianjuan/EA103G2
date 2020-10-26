<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseVO houseVOwaterfee = (HouseVO) request.getAttribute("houseVOwaterfee");
	HouseVO houseVOelectfee = (HouseVO) request.getAttribute("houseVOelectfee");
	
	ConVO conVO = (ConVO)request.getAttribute("conVO");
 	LldVO lldVO = (LldVO)request.getAttribute("lldVO");
 	
 	String lld_no = (String) session.getAttribute("lld_no");
	if (lld_no == null) {
		lld_no = request.getParameter("lld_no");
	}
	
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
	if (lldInfo == null) {
		HouseService houseSvc = new HouseService();
		lldInfo = houseSvc.getLldInfo(lld_no);
	}
	
	pageContext.setAttribute("houseVO", houseVO);
	pageContext.setAttribute("conVO", conVO);
	pageContext.setAttribute("lldVO", lldVO);
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
	<title>簽合約</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/cont.css">	
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
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldAllHouse">
						<button type="submit" class="link">首頁</button>
					</FORM>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney()">上架房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">已租房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">待租房屋</button>
					</FORM>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldOffHouse">
						<button type="submit" class="link">下架房屋</button>
					</FORM>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">租屋申請</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link"  style="color: #D37707;">合約管理</button>
					</FORM>
					<button type="button" class="link">修繕管理</button>
					<button type="button" class="link">評價管理</button>
                </div>
            </nav>
		</div>
		
		<form class="table" name="contForm" METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet" enctype="multipart/form-data">
			<div id="center">
				<div id="chead">
					<ul>
						<li class="title1"><button type="button" class="titlebtn" id="btn1" onclick="titleshow(event)" value="1">基本資訊</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn2" onclick="titleshow(event)" value="2">簽名圖片</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn3" onclick="titleshow(event)" value="3">家具設備</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn4" onclick="titleshow(event)" value="4">各項費用</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn5" onclick="titleshow(event)" value="5">其他條件</button></li>
					</ul>
				</div>		
				<div id="cbody">				
					<div id="cbody1">
						<table cellpadding="11">
							
							<tr>
								<th>姓名:</th>
								<td>
									<%=lldVO.getLld_name()%>
								</td>
							</tr>
							
							<tr>
								<th>性別:</th>
								<td>
									${(lldVO.lld_sex)?'男':'女'}
								</td>
							</tr>
							
							<tr>
								<th>身份證:</th>
								<td>
									<%=lldVO.getLld_id()%>
								</td>
							</tr>
							
							<tr>
								<th>生日:</th>
								<td>
									<%=lldVO.getLld_birth()%>
								</td>
							</tr>
							
							<tr>
								<th>手機:</th>
								<td>
									<input type="TEXT" name="lld_mobile" value="<%=lldVO.getLld_mobile()%>" />
								</td>
							</tr>
							
						</table>
					</div>
					<div id="cbody2">
							<div class="container">
								<div class="row">
									<div class="col-md-12">
										<h3 style="font-style: italic;">請於下方簽名欄簽名</h3>
									</div>
								</div>	
								<div class="row">
									<div class="col-md-12">
								 		<canvas id="sig-canvas" width="850" height="200" style="z-index:9999;position: relative;">
								 			Get a better browser, bro.
								 		</canvas>
								 	</div>
								</div>
								<div class="row" style="margin-bottom: 5px;">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary" id="sig-submitBtn">確認</button>
										
										<button type="button" class="btn btn-default" id="sig-clearBtn">清除</button>
									</div>
								</div>
								<br>
								您的簽名<br>
								<div class="row">
									<div class="col-md-12">
										<img id="sig-image" src="" alt="簽名圖片底家啦!"/>
										<input id="sign" type="hidden" name="con_lld_sign" value=""/>
										
									</div>
								</div>
							</div>
					</div>
					<div id="cbody3">
						<table>
							<tr>
								<th>家具:</th>
								<td>
									<input type="button" value="全選" class="funbtn" onclick="checkfurAll()"><hr>
									<ul class="item_outer">
										<li>
											<label class="item_name">桌子&nbsp;&nbsp;
												<select name="hos_table">
												  <option value="1">1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">椅子&nbsp;&nbsp;
												<select name="hos_chair">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">床&nbsp;&nbsp;
												<select name="hos_bed">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">衣櫃&nbsp;&nbsp;
												<select name="hos_closet">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">沙發&nbsp;&nbsp;
												<select name="hos_sofa">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
									</ul>									
								</td>
							</tr>
							<tr>
								<th>設備:</th>
								<td>
									<ul class="item_outer">
										<li>
											<label class="item_name">冰箱&nbsp;&nbsp;
												<select name="hos_refrig">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">電視&nbsp;&nbsp;
												<select name="hos_tv">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">飲水機&nbsp;&nbsp;
												<select name="hos_drink">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">冷氣&nbsp;&nbsp;
												<select name="hos_aircon">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">洗衣機&nbsp;&nbsp;
												<select name="hos_wash">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
										<li>
											<label class="item_name">熱水器&nbsp;&nbsp;
												<select name="hos_hoter">
												  <option value="1" selected>1</option>
												  <option value="2">2</option>
												  <option value="3">3</option>
												  <option value="4">4</option>
												  <option value="5">5</option>
												  <option value="6">6</option>
												  <option value="7">7</option>
												  <option value="8">8</option>
												  <option value="9">9</option>
												  <option value="10">10</option>
												</select>
											</label>
										</li>
									</ul>									
								</td>
							</tr>
							<tr>
								<th>其他:</th>
								<td>
									<ul class="item_outer">
										<li>
											<label class="item_name">第四台&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_forth" value="1" class="onoffswitch-checkbox" id="fourth" tabindex="0">
													<label class="onoffswitch-label" for="fourth"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">網路&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_net" value="1" class="onoffswitch-checkbox" id="IE" tabindex="0" onclick="netfee()">
													<label class="onoffswitch-label" for="IE"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">天然瓦斯&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_gas" value="1" class="onoffswitch-checkbox" id="gas" tabindex="0" onclick="gasfee()">
													<label class="onoffswitch-label" for="gas"></label>
												</div>
											</label>
										</li>
									</ul>									
								</td>
							</tr>
						</table>
					</div>
					<div id="cbody4">
						<table cellpadding="11">
							<tr>
								<th>租金</th>
								<td>
									<%=houseVO.getHos_rentfee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>電費</th>
								<td>
									<%=houseVOelectfee.getHos_electfeetype()%>元/度
								</td>
							</tr>
							
							<tr>
								<th>水費</th>
								<td>
									<%=houseVOwaterfee.getHos_waterfeetype()%>元/度
								</td>
							</tr>
							
							<tr>
								<th>瓦斯費</th>
								<td>
									<%=houseVO.getHos_gasfee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>管理費</th>
								<td>
									<%=houseVO.getHos_manafee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>網路費</th>
								<td>
									<%=houseVO.getHos_netfee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>公共水費</th>
								<td>
									<%=houseVO.getHos_puwaterfee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>公共電費</th>
								<td>
									<%=houseVO.getHos_puelefee()%>元/月
								</td>
							</tr>
							
							<tr>
								<th>停車位費:</th>
								<td>
									<%=houseVO.getHos_parkfee()%>元/月
								</td>
							</tr>
							
						</table>
					</div>
					<div id="cbody5">
						<table cellpadding="15">
							<tr>
								<th>可遷入日</th>
								<td>
									<%=houseVO.getHos_mdate()%>
								</td>
							</tr>
							<tr>
								<th>最短租期:</th>
								<td>									
									<%=houseVO.getHos_mindate()%>
								</td>
							</tr>
							<tr>
								<th>車位:</th>
								<td>
									<%=houseVO.getHos_park()%>
								</td>
							</tr>
							<tr>
								<th>性別限制:</th>
								<td>
									<%=houseVO.getHos_sex()%>
								</td>
							</tr>
							<tr>
								<th>身份限制:</th>
								<td>
									<%=houseVO.getHos_iden()%>
								</td>
							</tr>
							<tr>
								<th>開伙:</th>
								<td>
									<%=houseVO.getHos_cook()%>
								</td>
							</tr>
							<tr>
								<th>寵物:</th>
								<td>
									<%=houseVO.getHos_pet()%>
								</td>
							</tr>
							<tr>
								<th>吸菸:</th>
								<td>
									<%=houseVO.getHos_smoke()%>
								</td>
							</tr>
						</table>
					</div>			        				
				</div>
				<input type="hidden" name="lld_no" value="<%=lld_no%>">
				<input type="hidden" name="con_no" value="<%=conVO.getCon_no()%>">
				<input type="hidden" name="hos_no" value="<%=houseVO.getHos_no()%>">
				<div id="cfoot">
					<button class="pagebtn" type="button" onclick="notice2()">全部重填</button>
					<button class="pagebtn" id="pr1" type="button" onclick="previous()">上頁</button>
					<button class="pagebtn" id="ne1" type="button" onclick="next()">下頁</button>	
					<input type="hidden" name="action" value="updateonelldcontract">
					<button class="pagebtn" type="button" onclick="notice1()">送出合約</button>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
					<ul>
						<li class="title1"><button type="button" class="titlebtn" id="btn6" onclick="titleshow(event)" value="1">基本資訊</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn7" onclick="titleshow(event)" value="2">房屋圖片</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn8" onclick="titleshow(event)" value="3">家具設備</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn9" onclick="titleshow(event)" value="4">各項費用</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn10" onclick="titleshow(event)" value="5">其他條件</button></li>
					</ul>
				</div>
				<div id="rfoot">
					<button class="pagebtn" type="button" onclick="notice2()">全部重填</button>
					<button class="pagebtn" id="pr2" type="button" onclick="previous()">上頁</button>
					<button class="pagebtn" id="ne2" type="button" onclick="next()">下頁</button>
					<input type="hidden" name="action" value="updateonelldcontract">
					<button class="pagebtn" type="button" onclick="notice1()">送出合約</button>
				</div>
			</div>
		</form>
	</div>
	<div id="foot"></div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/contract/js/cont.js" charset="UTF-8"></script>
</body>
</html>