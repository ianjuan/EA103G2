<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	String lld_no = (String) request.getAttribute("lld_no");
	List<HouseVO> listunrent = (List<HouseVO>) request.getAttribute("houseVOunrent");
	List<HouseVO> listrent = (List<HouseVO>) request.getAttribute("houseVOrent");
	List<HouseVO> listoff = (List<HouseVO>) request.getAttribute("houseVOoff");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>House_Unrent</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_index.css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
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
							<span class="membername">彭于晏</span>
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
						<button type="submit" class="link" style="color: #D37707;">首頁</button>
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
					<button type="submit" class="link">預約管理</button>
					<button type="submit" class="link">租屋申請</button>
					<button type="submit" class="link">合約管理</button>
				</div>
			</nav>
		</div>
		<div id="center">
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/unrent.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listunrent.size()%>&nbsp;</span><span class="fontstyle">間</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/rent.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listrent.size()%>&nbsp;</span><span class="fontstyle">間</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/off.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listoff.size()%>&nbsp;</span><span class="fontstyle">間</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldOffHouse">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/on.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">來刊登自己的房子吧~</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/reservation.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看自己的行程吧~</span>
				</div>
				<div class="rinfo">
					<button id="btn1">點我進入</button>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/apply.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看有哪些搖錢樹吧~</span>
				</div>
				<div class="rinfo">
					<button id="btn1">點我進入</button>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/contract.jpg" class="pic"/>
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看收入來源吧~</span>
				</div>
				<div class="rinfo">
					<button id="btn1">點我進入</button>
				</div>
			</div>
		</div>					
		<div id="right"></div>
	</div>
	<div id="foot"></div>
	<div id="outerdiv" style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
	
	<script>
		var menuBtn = document.querySelector('.menu-btn');
	    var nav = document.querySelector('#housenav');
	    var lineOne = document.querySelector('#housenav .menu-btn .line--1');
	    var lineTwo = document.querySelector('#housenav .menu-btn .line--2');
	    var lineThree = document.querySelector('#housenav .menu-btn .line--3');
	    var link = document.querySelector('#housenav .nav-links');
	    menuBtn.addEventListener('click', function() {
	        nav.classList.toggle('nav-open');
	        lineOne.classList.toggle('line-cross');
	        lineTwo.classList.toggle('line-fade-out');
	        lineThree.classList.toggle('line-cross');
	        link.classList.toggle('fade-in');
	    })
	</script>
</body>
</html>