<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseVO houseVOwaterfee = (HouseVO) request.getAttribute("houseVOwaterfee");
	HouseVO houseVOelectfee = (HouseVO) request.getAttribute("houseVOelectfee");
	List<HouseVO> piclist = (List<HouseVO>) request.getAttribute("houseVOpicno");
	pageContext.setAttribute("piclist", piclist);	
	String lld_no = (String) request.getAttribute("lld_no");
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
%>

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
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_pub.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_pub.js" charset="UTF-8"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
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
                            <span class="membername"><%=lldInfo.getLld_name()%>></span>
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
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="submit" class="link">上架房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link">歷史合約</button><br>
					</FORM>
					<span class="link" style="color: #D37707;">資訊修改</span>
					<span class="link" style="color: #265895;font-size:85%"><%=houseVO.getHos_no()%></span>					
				</div>
			</nav>
		</div>
		<form class="table" name="houseForm" METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet" enctype="multipart/form-data">
			<div id="center">
				<div id="chead">
					<ul>
						<li class="title1"><button type="button" class="titlebtn" id="btn1" onclick="show1()">基本資訊</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn2" onclick="show2()">房屋圖片</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn3" onclick="show3()">家具設備</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn4" onclick="show4()">各項費用</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn5" onclick="show5()">其他條件</button></li>
					</ul>
				</div>			
				<div id="cbody">				
					<div id="cbody1">
						<table>
							<tr>
								<th>*房屋名稱:</th>
								<td>
									<input type="text" class="text1" name="hos_name" value="<%=houseVO.getHos_name()%>" required>
								</td>
							</tr>
							<tr>
								<th>生活機能:</th>
								<td>									
									<textarea rows="2" wrap="hard" onkeyup="checkLen1(this)" name="hos_liffun"><%=houseVO.getHos_liffun()==null ? "" : houseVO.getHos_liffun()%></textarea>
									<div class="fontstyle">您還可以輸入 <span id="count1">200</span> 個文字</div>
								</td>
							</tr>
							<tr>
								<th>附近交通:</th>
								<td>									
									<textarea rows="2" wrap="hard" onkeyup="checkLen2(this)" name="hos_trans"><%=houseVO.getHos_trans()==null ? "" : houseVO.getHos_trans()%></textarea>
									<div class="fontstyle">您還可以輸入 <span id="count2">200</span> 個文字</div>
								</td>
							</tr>
							<tr>
								<th>*地址:</th>
								<td>
									<input type="text" class="text1" name="hos_add" value="<%=houseVO.getHos_add()%>" id="hos_add" onchange="map()" required>
									<input type="hidden" name="hos_lng" value="121.194406" id="lng">
									<input type="hidden" name="hos_lat" value="24.9656967" id="lat">
								</td>
							</tr>
							<tr>
								<th>*房屋類型:</th>
								<td>									
									<label><input type="radio" name="hos_type" value="透天厝" <%=(houseVO.getHos_type().equals("透天厝")) ? "checked" : ""%> required>透天厝</label>
									<label><input type="radio" name="hos_type" value="公寓" <%=(houseVO.getHos_type().equals("公寓")) ? "checked" : ""%> required>公寓</label>
									<label><input type="radio" name="hos_type" value="電梯大樓" <%=(houseVO.getHos_type().equals("電梯大樓")) ? "checked" : ""%> required>電梯大樓</label>
									<label><input type="radio" name="hos_type" value="別墅" <%=(houseVO.getHos_type().equals("別墅")) ? "checked" : ""%> required>別墅</label>
								</td>
							</tr>
							<tr>
								<th>*房間類型:</th>
								<td>									
									<label><input type="radio" name="hos_room" value="雅房" <%=(houseVO.getHos_room().equals("雅房")) ? "checked" : ""%> required>雅房</label>
									<label><input type="radio" name="hos_room" value="獨立套房" <%=(houseVO.getHos_room().equals("獨立套房")) ? "checked" : ""%> required>獨立套房</label>
									<label><input type="radio" name="hos_room" value="分租套房" <%=(houseVO.getHos_room().equals("分租套房")) ? "checked" : ""%> required>分租套房</label>
									<label><input type="radio" name="hos_room" value="整層住家" <%=(houseVO.getHos_room().equals("整層住家")) ? "checked" : ""%> required>整層住家</label>
								</td>
							</tr>
							<tr>
								<th>格局:</th>
								<td>
									<input type="text" class="text1" name="hos_pat"
									value="<%=houseVO.getHos_pat()==null ? "" : houseVO.getHos_pat()%>">
								</td>
							</tr>
							<tr>
								<th>*樓層:</th>
								<td>
									<input type="text" class="text1" name="hos_floor" value="<%=houseVO.getHos_floor()%>" required>
								</td>
							</tr>
							<tr>
								<th>*坪數:</th>
								<td>
									<input type="number" class="num1" min="1" step="0.01" name="hos_pnum" value="<%=houseVO.getHos_pnum()%>" required>
									<font size="2">坪</font>
								</td>
							</tr>
						</table>
					</div>
					<div id="cbody2">
						<table>
							<tr>
								<th rowspan="2">新增圖片:</th>
								<td>
									
									<label>
										<input type="file" id="loadPic" multiple onchange="load1()" name="hos_pic">
										<img src="https://www.flaticon.com/svg/static/icons/svg/3378/3378213.svg" class="uploadpic"/>
										<span class="uploadfont" for="loadPic">&nbsp;&nbsp;上傳圖片&nbsp;&nbsp;</span>
									</label>									
									<input type="button" class="funbtn" value="刪除" onclick="del()" style="margin-top: 6px;">
								</td>
							</tr>
							<tr>	
								<td>
									<div id="preview" style="height:200px;"></div>
								</td>
							</tr>
							<tr>	
								<th rowspan="2">原有圖片:</th>
								<td>									
									<input type="button" class="funbtn" value="圖片放大" onclick="checkpic()" style="float:left;">
									<input type="button" class="funbtn" value="刪除" onclick="delpic()">
									<input type="button" class="funbtn" value="全選" onclick="checkpicAll()">
								</td>
							</tr>
							<tr>
								<td>
									<div id="preview1">
										<c:forEach var="houseVO" items="${piclist}" varStatus="picno">
											<div class="pic1div">
												<label>
													<input type="checkbox" class="checkpic">
													<input type="hidden" name="picno" value="${houseVO.pic_no}">												
													<img src="<%=request.getContextPath()%>/HouseImgReader?id=${houseVO.pic_no}" class="pic1" />
												</label>
											</div>											
										</c:forEach>
										<input type="hidden" name="pic_no" value="">
									</div>
								</td>
							</tr>
						</table>
						<div id="outerdiv"
							style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
							<div id="innerdiv" style="position: absolute;">
								<img id="bigimg" style="border: 5px solid #fff;" src="" />
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
												<div class="onoffswitch">
													<input type="checkbox" name="hos_table" value="1" id="desk" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_table() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="desk"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">椅子&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_chair" value="1" id="chair" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_chair() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="chair"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">床&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_bed" value="1" id="bed" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_bed() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="bed"></label>											    	
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">衣櫃&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_closet" value="1" id="cabinet" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_closet() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="cabinet"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">沙發&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_sofa" value="1" id="sofa" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_sofa() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="sofa"></label>
												</div>
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
												<div class="onoffswitch">
													<input type="checkbox" name="hos_refrig" value="1" id="refri" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_refrig() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="refri"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">電視&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_tv" value="1" id="TV" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_tv() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="TV"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">飲水機&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_drink" value="1" id="drink" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_drink() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="drink"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">冷氣&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_aircon" value="1" id="air" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_aircon() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="air"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">洗衣機&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_wash" value="1" id="wash" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_wash() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="wash"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">熱水器&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_hoter" value="1" id="hotwater" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_hoter() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="hotwater"></label>
												</div>
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
													<input type="checkbox" name="hos_forth" value="1" id="fourth" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_forth() > 0) ? "checked" : ""%>>
													<label class="onoffswitch-label" for="fourth"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">網路&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_net" value="1" id="IE" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_net() > 0) ? "checked" : ""%> onclick="netfee()">
													<label class="onoffswitch-label" for="IE"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">天然瓦斯&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_gas" value="1" id="gas" class="onoffswitch-checkbox" tabindex="0" <%=(houseVO.getHos_gas() > 0) ? "checked" : ""%> onclick="gasfee()">
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
						<table>
							<tr>
								<th rowspan="9">出租費用:</th>
								<td>
									<label>*租金: 每月<input type="number" class="num1" min="0" placeholder="0" step="1" name="hos_rentfee" value="<%=houseVO.getHos_rentfee()%>" required>元</label>
								</td>
							</tr>
							<tr>
								<td>
									*水費: <label><input type="radio" id="watertype1" name="hos_waterfeetype" value="1" onclick="floatfee1()" <%=(houseVOwaterfee.getHos_waterfeetype() == 1) ? "checked" : ""%> required>&nbsp;每度<input type="number" id="water1" class="num1" min="0" placeholder="0" step="0.1" name="hos_waterfee1" value="<%=(houseVOwaterfee.getHos_waterfeetype() == 1) ? houseVOwaterfee.getHos_waterfee() : ""%>" <%=(houseVOwaterfee.getHos_waterfeetype() == 1) ? "" : "disabled"%>>元</label>
									<label><input type="radio" id="watertype2" name="hos_waterfeetype" value="2" onclick="floatfee1()" <%=(houseVOwaterfee.getHos_waterfeetype() == 2) ? "checked" : ""%> required>&nbsp;每月<input type="number" id="water2" class="num1" min="0" min="0" placeholder="0" name="hos_waterfee2" value="<%=(houseVOwaterfee.getHos_waterfeetype() == 2) ? houseVOwaterfee.getHos_waterfee() : ""%>" <%=(houseVOwaterfee.getHos_waterfeetype() == 2) ? "" : "disabled"%>>元</label>
									<label><input type="radio" id="watertype0" name="hos_waterfeetype" value="0" onclick="floatfee1()" <%=(houseVOwaterfee.getHos_waterfeetype() == 0) ? "checked" : ""%> required>&nbsp;房東付費</label>
								</td>
							</tr>
							<tr>
								<td>
									*電費: <label><input type="radio" id="electtype1" name="hos_electfeetype" value="1" onclick="floatfee2()" <%=(houseVOelectfee.getHos_electfeetype() == 1) ? "checked" : ""%> required>&nbsp;每度<input type="number" id="elect1" class="num1" placeholder="0" step="0.1" name="hos_electfee1" value="<%=(houseVOelectfee.getHos_electfeetype() == 1) ? houseVOelectfee.getHos_electfee() : ""%>" <%=(houseVOelectfee.getHos_electfeetype() == 1) ? "" : "disabled"%>>元</label>
									<label><input type="radio" id="electtype2" name="hos_electfeetype" value="2" onclick="floatfee2()" <%=(houseVOelectfee.getHos_electfeetype() == 2) ? "checked" : ""%> required>&nbsp;每月<input type="number" id="elect2" class="num1" min="0" placeholder="0" name="hos_electfee2" value="<%=(houseVOelectfee.getHos_electfeetype() == 2) ? houseVOelectfee.getHos_electfee() : ""%>" <%=(houseVOelectfee.getHos_electfeetype() == 2) ? "" : "disabled"%>>元</label>
									<label><input type="radio" id="electtype0" name="hos_electfeetype" value="0" onclick="floatfee2()" <%=(houseVOelectfee.getHos_electfeetype() == 0) ? "checked" : ""%> required>&nbsp;房東付費</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>瓦斯費:&nbsp;<input type="number" id="gasfee1" class="num1" min="0" placeholder="0" name="hos_gasfee" value="<%=houseVO.getHos_gasfee()%>" <%=houseVO.getHos_gasfee()==0?"disabled":""%>>元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>管理費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_manafee" value="<%=houseVO.getHos_manafee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>網路費:&nbsp;<input type="number" id="netfee1" class="num1" min="0" placeholder="0" name="hos_netfee" value="<%=houseVO.getHos_netfee()%>" <%=houseVO.getHos_netfee()==0?"disabled":""%>>元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>公共水費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_puwaterfee" value="<%=houseVO.getHos_puwaterfee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>公共電費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_puelefee" value="<%=houseVO.getHos_puelefee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>停車位費:&nbsp;<input type="number" id="parkfee1" class="num1" min="0" placeholder="0" step="1" name="hos_parkfee" value="<%=houseVO.getHos_parkfee()%>">元</label>
								</td>
							</tr>
						</table>
					</div>
					<div id="cbody5">
						<table>
							<tr>
								<th>*可遷入日:</th>
								<td>
									<input type="text" class="text1" placeholder="隨時" name="hos_mdate" value="<%=houseVO.getHos_mdate()%>" required>
								</td>
							</tr>
							<tr>
								<th>*最短租期:</th>
								<td>									
									<label><input type="radio" name="hos_mindate" value="兩年" <%=(houseVO.getHos_mindate().equals("兩年")) ? "checked" : ""%> required>兩年</label>
									<label><input type="radio" name="hos_mindate" value="一年" <%=(houseVO.getHos_mindate().equals("一年")) ? "checked" : ""%> required>一年</label>
									<label><input type="radio" name="hos_mindate" value="半年" <%=(houseVO.getHos_mindate().equals("半年")) ? "checked" : ""%> required>半年</label>
									<label><input type="radio" name="hos_mindate" value="三個月" <%=(houseVO.getHos_mindate().equals("三個月")) ? "checked" : ""%> required>三個月</label>
									<label><input type="radio" name="hos_mindate" value="不限" <%=(houseVO.getHos_mindate().equals("不限")) ? "checked" : ""%> required>不限</label>
								</td>
							</tr>
							<tr>
								<th>*車位:</th>
								<td>
									<label><input type="radio" id="park1" name="hos_park" value="無" <%=(houseVO.getHos_park().equals("無")) ? "checked" : ""%> onclick="parkfee()" required>無</label>
									<label><input type="radio" name="hos_park" value="平面式" <%=(houseVO.getHos_park().equals("平面式")) ? "checked" : ""%> onclick="parkfee()" required>平面式</label>
									<label><input type="radio" name="hos_park" value="機械式" <%=(houseVO.getHos_park().equals("機械式")) ? "checked" : ""%> onclick="parkfee()" required>機械式</label>
								</td>
							</tr>
							<tr>
								<th>*性別:</th>
								<td>
									<label><input type="radio" name="hos_sex" value="無" <%=(houseVO.getHos_sex().equals("無")) ? "checked" : ""%> required>無</label>
									<label><input type="radio" name="hos_sex" value="女生" <%=(houseVO.getHos_sex().equals("女生")) ? "checked" : ""%> required>女生</label>
									<label><input type="radio" name="hos_sex" value="男生" <%=(houseVO.getHos_sex().equals("男生")) ? "checked" : ""%> required>男生</label>
								</td>
							</tr>
							<tr>
								<th>*身份:</th>
								<td>
									<label><input type="radio" name="hos_iden" value="無" <%=(houseVO.getHos_iden().equals("無")) ? "checked" : ""%> required>無</label>
									<label><input type="radio" name="hos_iden" value="學生" <%=(houseVO.getHos_iden().equals("學生")) ? "checked" : ""%> required>學生</label>
									<label><input type="radio" name="hos_iden" value="上班族" <%=(houseVO.getHos_iden().equals("上班族")) ? "checked" : ""%> required>上班族</label>
									<label><input type="radio" name="hos_iden" value="家庭" <%=(houseVO.getHos_iden().equals("家庭")) ? "checked" : ""%> required>家庭</label>
								</td>
							</tr>
							<tr>
								<th>*開伙:</th>
								<td>
									<label><input type="radio" name="hos_cook" value="可以" <%=(houseVO.getHos_cook().equals("可以")) ? "checked" : ""%> required>可以</label>
									<label><input type="radio" name="hos_cook" value="不可以" <%=(houseVO.getHos_cook().equals("不可以")) ? "checked" : ""%> required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*寵物:</th>
								<td>
									<label><input type="radio" name="hos_pet" value="可以" <%=(houseVO.getHos_pet().equals("可以")) ? "checked" : ""%> required>可以</label>
									<label><input type="radio" name="hos_pet" value="不可以" <%=(houseVO.getHos_pet().equals("不可以")) ? "checked" : ""%> required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*吸菸:</th>
								<td>
									<label><input type="radio" name="hos_smoke" value="可以" <%=(houseVO.getHos_smoke().equals("可以")) ? "checked" : ""%> required>可以</label>
									<label><input type="radio" name="hos_smoke" value="不可以" <%=(houseVO.getHos_smoke().equals("不可以")) ? "checked" : ""%> required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*出租狀態:</th>
								<td>
									<label><input type="radio" name="hos_status" value="待出租" <%=(houseVO.getHos_status().equals("待出租")) ? "checked" : ""%> <%=(houseVO.getHos_status().equals("出租中")) ? "disabled" : ""%> required>待出租</label>
									<label><input type="radio" name="hos_status" value="已下架" <%=(houseVO.getHos_status().equals("已下架")) ? "checked" : ""%> <%=(houseVO.getHos_status().equals("出租中")) ? "disabled" : ""%> required>已下架</label>
									<label><input type="hidden" name="hos_status" value="出租中" <%=(houseVO.getHos_status().equals("出租中")) ? "checked" : ""%> required></label>
								</td>
							</tr>
						</table>
					</div>			        				
				</div>
				<input type="hidden" name="hos_no" value="<%=houseVO.getHos_no()%>">
				<input type="hidden" name="lld_no" value="<%=lld_no%>">
				
				<div id="cfoot">
					<button class="btn" id="reset" type="reset" onclick="notice2()">全部重填</button>
					<button class="btn" id="pr1" type="button" onclick="previous()">上頁</button>
					<button class="btn" id="ne1" type="button" onclick="next()">下頁</button>
					<input type="hidden" name="action" value="updateHouseInfo">
					<button class="btn" id="submit" type="submit" onclick="return notice3();">修改資訊</button>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
					<ul>
						<li class="title1"><button type="button" class="titlebtn" id="btn6" onclick="show1()">基本資訊</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn7" onclick="show2()">房屋圖片</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn8" onclick="show3()">家具設備</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn9" onclick="show4()">各項費用</button></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn10" onclick="show5()">其他條件</button></li>
					</ul>
				</div>
				<div id="rfoot">					
					<button class="btn" id="reset" type="reset" onclick="notice2()">全部重填</button>
					<button class="btn" id="pr2" type="button" onclick="previous()">上頁</button>
					<button class="btn" id="ne2" type="button" onclick="next()">下頁</button>
					<button class="btn" id="submit" type="submit" onclick="notice3();">修改資訊</button>
					<input type="hidden" name="action" value="updateHouseInfo">
				</div>
			</div>
		</form>
	</div>
	<div id="foot"></div>
</body>
</html>