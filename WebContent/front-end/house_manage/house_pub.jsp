<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	String lld_no = (String) request.getAttribute("lld_no");
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>上架房屋</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_pub.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_pub.js" charset="UTF-8"></script>
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
						<button type="button" class="link" onclick="checkmoney()" style="color: #D37707;">上架房屋</button>
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
						<button type="submit" class="link">合約管理</button>
					</FORM>
					<button type="button" class="link">修繕管理</button>
					<button type="button" class="link">評價管理</button>
                </div>
            </nav>
		</div>
		<form class="table" name="houseForm" METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet" enctype="multipart/form-data">
			<div id="center">
				<div id="chead">
					<ul>
						<li class="title1"><button type="button" class="titlebtn" id="btn1" onclick="titleshow(event)" value="1">基本資訊</button></li>
						<li class="title2">></li>
						<li class="title1"><button type="button" class="titlebtn" id="btn2" onclick="titleshow(event)" value="2">房屋圖片</button></li>
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
						<table>
							<tr>
								<th>*房屋名稱:</th>
								<td>
									<input type="text" class="text1" name="hos_name" value="<%=(houseVO==null) ? "" : houseVO.getHos_name()%>" required>
								</td>
							</tr>
							<tr>
								<th>生活機能:</th>
								<td>									
									<textarea rows="2" wrap="hard" onkeyup="checkLen1(this)" name="hos_liffun"><%=(houseVO==null) ? "" : houseVO.getHos_liffun()%></textarea>
									<div class="fontstyle">您還可以輸入 <span id="count1">200</span> 個文字</div>
								</td>
							</tr>
							<tr>
								<th>附近交通:</th>
								<td>									
									<textarea rows="2" wrap="hard" onkeyup="checkLen2(this)" name="hos_trans"><%=(houseVO==null) ? "" : houseVO.getHos_trans()%></textarea>
									<div class="fontstyle">您還可以輸入 <span id="count2">200</span> 個文字</div>
								</td>
							</tr>
							<tr>
								<th>*地址:</th>
								<td>
									<input type="text" class="text1" name="hos_add" value="<%=(houseVO==null) ? "" : houseVO.getHos_add()%>" id="hos_add" required>									
									<input type="hidden" name="hos_lng" value="121.194406" id="lng">
									<input type="hidden" name="hos_lat" value="24.9656967" id="lat">
								</td>
							</tr>
							<tr>
								<th>*房屋類型:</th>
								<td>									
									<label><input type="radio" name="hos_type" value="透天厝" required>透天厝</label>
									<label><input type="radio" name="hos_type" value="公寓" required>公寓</label>
									<label><input type="radio" name="hos_type" value="電梯大樓" required>電梯大樓</label>
									<label><input type="radio" name="hos_type" value="別墅" required>別墅</label>
								</td>
							</tr>
							<tr>
								<th>*房間類型:</th>
								<td>									
									<label><input type="radio" name="hos_room" value="雅房" required>雅房</label>
									<label><input type="radio" name="hos_room" value="獨立套房" required>獨立套房</label>
									<label><input type="radio" name="hos_room" value="分租套房" required>分租套房</label>
									<label><input type="radio" name="hos_room" value="整層住家" required>整層住家</label>
								</td>
							</tr>
							<tr>
								<th>格局:</th>
								<td>
									<input type="text" class="text1" name="hos_pat" value="<%=(houseVO==null) ? "" : houseVO.getHos_pat()%>">
								</td>
							</tr>
							<tr>
								<th>*樓層:</th>
								<td>
									<input type="text" class="text1" name="hos_floor"
									value="<%=(houseVO==null) ? "" : houseVO.getHos_floor()%>" required>
								</td>
							</tr>
							<tr>
								<th>*坪數:</th>
								<td>
									<input type="number" class="num1" min="1" step="0.01" name="hos_pnum"
									value="<%=(houseVO==null) ? "" : houseVO.getHos_pnum()%>" required>
									<font size="2">坪</font>
								</td>
							</tr>
						</table>
					</div>
					<div id="cbody2">
						<table>
							<tr>
								<th rowspan="2">*房屋圖片:</th>
								<td>
									<label>
										<input type="file" id="loadPic" multiple onchange="load()" name="hos_pic" required>
										<img src="https://www.flaticon.com/svg/static/icons/svg/3378/3378213.svg" class="uploadpic"/>
										<span class="uploadfont" for="loadPic">&nbsp;&nbsp;上傳圖片&nbsp;&nbsp;</span>									
									</label>
									<input type="button" class="funbtn" value="刪除" onclick="del()" style="margin-top: 6px;">
								</td>
							</tr>
							<tr>	
								<td>
									<div id="preview"></div>
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
													<input type="checkbox" name="hos_table" value="1" class="onoffswitch-checkbox" id="desk" tabindex="0">
													<label class="onoffswitch-label" for="desk"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">椅子&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_chair" value="1" class="onoffswitch-checkbox" id="chair" tabindex="0">
													<label class="onoffswitch-label" for="chair"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">床&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_bed" value="1" class="onoffswitch-checkbox" id="bed" tabindex="0">
													<label class="onoffswitch-label" for="bed"></label>											    	
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">衣櫃&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_closet" value="1" class="onoffswitch-checkbox" id="cabinet" tabindex="0">
													<label class="onoffswitch-label" for="cabinet"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">沙發&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_sofa" value="1" class="onoffswitch-checkbox" id="sofa" tabindex="0">
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
													<input type="checkbox" name="hos_refrig" value="1" class="onoffswitch-checkbox" id="refri" tabindex="0">
													<label class="onoffswitch-label" for="refri"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">電視&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_tv" value="1" class="onoffswitch-checkbox" id="TV" tabindex="0">
													<label class="onoffswitch-label" for="TV"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">飲水機&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_drink" value="1" class="onoffswitch-checkbox" id="drink" tabindex="0">
													<label class="onoffswitch-label" for="drink"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">冷氣&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_aircon" value="1" class="onoffswitch-checkbox" id="air" tabindex="0">
													<label class="onoffswitch-label" for="air"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">洗衣機&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_wash" value="1" class="onoffswitch-checkbox" id="wash" tabindex="0">
													<label class="onoffswitch-label" for="wash"></label>
												</div>
											</label>
										</li>
										<li>
											<label class="item_name">熱水器&nbsp;&nbsp;
												<div class="onoffswitch">
													<input type="checkbox" name="hos_hoter" value="1" class="onoffswitch-checkbox" id="hotwater" tabindex="0">
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
						<table>
							<tr>
								<th rowspan="9">出租費用:</th>
								<td>
									<label>*租金: 每月<input type="number" class="num1" min="0" placeholder="0" step="100" name="hos_rentfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_rentfee()%>" required>元</label>
								</td>
							</tr>
							<tr>
								<td>
									*水費: <label><input type="radio" id="watertype1" name="hos_waterfeetype" value="1" onclick="floatfee1()" required>&nbsp;每度<input type="number" id="water1" class="num1" min="0" placeholder="0" step="0.1" name="hos_waterfee1" value="<%=(houseVO==null) ? "" : houseVO.getHos_waterfee()%>" disabled>元</label>
									<label><input type="radio" id="watertype2" name="hos_waterfeetype" value="2" onclick="floatfee1()" required>&nbsp;每月<input type="number" id="water2" class="num1" min="0" min="0" placeholder="0" name="hos_waterfee2" value="<%=(houseVO==null) ? "" : houseVO.getHos_waterfee()%>" disabled>元</label>
									<label><input type="radio" id="watertype0" name="hos_waterfeetype" value="0" onclick="floatfee1()" required>&nbsp;房東付費</label>
								</td>
							</tr>
							<tr>
								<td>
									*電費: <label><input type="radio" id="electtype1" name="hos_electfeetype" value="1" onclick="floatfee2()" required>&nbsp;每度<input type="number" id="elect1" class="num1" placeholder="0" step="0.1" name="hos_electfee1" value="<%=(houseVO==null) ? "" : houseVO.getHos_electfee()%>" disabled>元</label>
									<label><input type="radio" id="electtype2" name="hos_electfeetype" value="2" onclick="floatfee2()" required>&nbsp;每月<input type="number" id="elect2" class="num1" min="0" placeholder="0" name="hos_electfee2" value="<%=(houseVO==null) ? "" : houseVO.getHos_electfee()%>" disabled>元</label>
									<label><input type="radio" id="electtype0" name="hos_electfeetype" value="0" onclick="floatfee2()" required>&nbsp;房東付費</label>
								</td>
							</tr>
							<tr>
								<td>
									
									<label>瓦斯費:&nbsp;<input type="number" id="gasfee1" class="num1" min="0" placeholder="0" name="hos_gasfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_gasfee()%>" disabled>元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>管理費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_manafee" value="<%=(houseVO==null) ? "" : houseVO.getHos_manafee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>網路費:&nbsp;<input type="number" id="netfee1" class="num1" min="0" placeholder="0" name="hos_netfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_netfee()%>" disabled>元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>公共水費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_puwaterfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_puwaterfee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>公共電費:&nbsp;<input type="number" class="num1" min="0" placeholder="0" name="hos_puelefee" value="<%=(houseVO==null) ? "" : houseVO.getHos_puelefee()%>">元</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>停車位費:&nbsp;<input type="number" id="parkfee1" class="num1" min="0" placeholder="0" step="100" name="hos_parkfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_parkfee()%>">元</label>
								</td>
							</tr>
						</table>
					</div>
					<div id="cbody5">
						<table>
							<tr>
								<th>*可遷入日:</th>
								<td>
									<input type="text" class="text1" placeholder="隨時" name="hos_mdate" value="<%=(houseVO==null) ? "" : houseVO.getHos_mdate()%>" required>
								</td>
							</tr>
							<tr>
								<th>*最短租期:</th>
								<td>									
									<label><input type="radio" name="hos_mindate" value="兩年" required>兩年</label>
									<label><input type="radio" name="hos_mindate" value="一年" required>一年</label>
									<label><input type="radio" name="hos_mindate" value="半年" required>半年</label>
									<label><input type="radio" name="hos_mindate" value="三個月" required>三個月</label>
									<label><input type="radio" name="hos_mindate" value="不限" required>不限</label>
								</td>
							</tr>
							<tr>
								<th>*車位:</th>
								<td>
									<label><input type="radio" id="park1" name="hos_park" value="無" onclick="parkfee()" required>無</label>
									<label><input type="radio" name="hos_park" value="平面式" onclick="parkfee()" required>平面式</label>
									<label><input type="radio" name="hos_park" value="機械式" onclick="parkfee()" required>機械式</label>
								</td>
							</tr>
							<tr>
								<th>*性別:</th>
								<td>
									<label><input type="radio" name="hos_sex" value="無" required>無</label>
									<label><input type="radio" name="hos_sex" value="女生" required>女生</label>
									<label><input type="radio" name="hos_sex" value="男生" required>男生</label>
								</td>
							</tr>
							<tr>
								<th>*身份:</th>
								<td>
									<label><input type="radio" name="hos_iden" value="無" required>無</label>
									<label><input type="radio" name="hos_iden" value="學生" required>學生</label>
									<label><input type="radio" name="hos_iden" value="上班族" required>上班族</label>
									<label><input type="radio" name="hos_iden" value="家庭" required>家庭</label>
								</td>
							</tr>
							<tr>
								<th>*開伙:</th>
								<td>
									<label><input type="radio" name="hos_cook" value="可以" required>可以</label>
									<label><input type="radio" name="hos_cook" value="不可以" required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*寵物:</th>
								<td>
									<label><input type="radio" name="hos_pet" value="可以" required>可以</label>
									<label><input type="radio" name="hos_pet" value="不可以" required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*吸菸:</th>
								<td>
									<label><input type="radio" name="hos_smoke" value="可以" required>可以</label>
									<label><input type="radio" name="hos_smoke" value="不可以" required>不可以</label>
								</td>
							</tr>
							<tr>
								<th>*出租狀態:</th>
								<td>
									<label><input type="radio" name="hos_status" value="待出租" required>待出租</label>
									<label><input type="radio" name="hos_status" value="已下架" required>下架</label>
								</td>
							</tr>
						</table>
					</div>			        				
				</div>
				<input type="hidden" name="lld_no" value="<%=lld_no%>">
				<input type="hidden" name="hos_bro" value="0">
				<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
				<div id="cfoot">
					<button class="btn" type="button" onclick="notice2()">全部重填</button>
					<button class="btn" id="pr1" type="button" onclick="previous()">上頁</button>
					<button class="btn" id="ne1" type="button" onclick="next()">下頁</button>	
					<input type="hidden" name="action" value="insertHouseInfo">
					<button class="btn" type="button" onclick="notice1()">刊登房屋</button>					
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
					<button class="btn" type="button" onclick="notice2()">全部重填</button>
					<button class="btn" id="pr2" type="button" onclick="previous()">上頁</button>
					<button class="btn" id="ne2" type="button" onclick="next()">下頁</button>
					<input type="hidden" name="action" value="insertHouseInfo">
					<button class="btn" type="button" onclick="notice1()">刊登房屋</button>
				</div>
			</div>
		</form>
	</div>
	<div id="foot"></div>		
</body>
</html>