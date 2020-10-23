<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	String lld_no = (String) session.getAttribute("lld_no");

	HouseService houseSvc = new HouseService();
	HouseVO lldInfo = houseSvc.getLldInfo(lld_no);
	List<HouseVO> listall = houseSvc.getLldAllHouse(lld_no);
	List<HouseVO> listrent = houseSvc.getLldRentHouse(lld_no);
	List<HouseVO> listunrent = houseSvc.getLldUnRentHouse(lld_no);
	List<HouseVO> listoff = houseSvc.getLldOffHouse(lld_no);
	
	pageContext.setAttribute("listall", listall);
	pageContext.setAttribute("listrent", listrent);
	pageContext.setAttribute("listunrent", listunrent);
	pageContext.setAttribute("listoff", listoff);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>房屋管理首頁</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_index.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_index.js" charset="UTF-8"></script>
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
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldAllHouse">
						<button type="submit" class="link" style="color: #D37707;">首頁</button>
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
						<button type="submit" class="link">合約管理</button>
					</FORM>
					<button type="button" class="link">修繕管理</button>
					<button type="button" class="link">評價管理</button>					
				</div>
			</nav>
		</div>
		<div id="center">
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/on.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<span class="fontstyle">來刊登自己的房子吧~</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" name="pub1" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney1()">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/rent.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<label class="logo">
						<button type="button" class="picbtn" data-toggle="modal" data-target="#rentHouse">查看房屋清單</button>
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQU73d5nlhuberXnZMzkC5N2BtLkCVvcwrJCQ&usqp=CAU">
						<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listrent.size()%>&nbsp;</span>
						<span class="fontstyle">間</span>
					</label>										
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">查看清單</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/unrent.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<label class="logo">
						<button type="button" class="picbtn" data-toggle="modal" data-target="#unrentHouse">查看房屋清單</button>
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQU73d5nlhuberXnZMzkC5N2BtLkCVvcwrJCQ&usqp=CAU">
						<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listunrent.size()%>&nbsp;</span>
						<span class="fontstyle">間</span>
					</label>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">查看清單</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/off.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<label class="logo">
						<button type="button" class="picbtn" data-toggle="modal" data-target="#offHouse">查看房屋清單</button>
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQU73d5nlhuberXnZMzkC5N2BtLkCVvcwrJCQ&usqp=CAU">
						<span class="fontstyle">共</span><span class="numberstyle">&nbsp;<%=listoff.size()%>&nbsp;</span>
						<span class="fontstyle">間</span>
					</label>				
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldOffHouse">
						<button type="submit" class="link">查看清單</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/apply.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看有哪些搖錢樹吧~</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/contract.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看有哪些爸爸們吧~</span>
				</div>
				<div class="rinfo">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link">點我進入</button>
					</FORM>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/fix.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<span class="fontstyle">看看又有那些東西壞掉啦~</span>
				</div>
				<div class="rinfo">
					<button type="submit" class="link">點我進入</button>
				</div>
			</div>
			<div class="houseinfo">
				<div class="linfo">
					<img src="<%=request.getContextPath()%>/front-end/house_manage/images/house_index/evaluation.jpg" class="pic" />
				</div>
				<div class="cinfo">
					<span class="fontstyle">來看看自己的評價吧~</span>
				</div>
				<div class="rinfo">
					<button type="submit" class="link">點我進入</button>
				</div>
			</div>
		</div>
		<div id="right">
						
			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="allHouse">
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
					<c:forEach var="houseVO" items="${listall}" varStatus="house">					
						<tr>
							<td width="5%">${house.count}</td>
							<td width="11%">${houseVO.hos_no}</td>
							<td width="24%">${houseVO.hos_name}</td>
							<td width="24%">${houseVO.hos_add}</td>
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
			
			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="rentHouse">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">			      			      	
				  	<table>
					<thead>
						<tr>
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
					<c:forEach var="houseVO" items="${listrent}" varStatus="house">					
						<tr>
							<td width="5%">${house.count}</td>
							<td width="11%">${houseVO.hos_no}</td>
							<td width="24%">${houseVO.hos_name}</td>
							<td width="24%">${houseVO.hos_add}</td>
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
			
			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="unrentHouse">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">		      		      	
				  	<table>
					<thead>
						<tr>
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
					<c:forEach var="houseVO" items="${listunrent}" varStatus="house">					
						<tr>
							<td width="5%">${house.count}</td>
							<td width="11%">${houseVO.hos_no}</td>
							<td width="24%">${houseVO.hos_name}</td>
							<td width="24%">${houseVO.hos_add}</td>
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
			
			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="offHouse">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">		      		      	
				  	<table>
					<thead>
						<tr>
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
					<c:forEach var="houseVO" items="${listoff}" varStatus="house">					
						<tr>
							<td width="5%">${house.count}</td>
							<td width="11%">${houseVO.hos_no}</td>
							<td width="24%">${houseVO.hos_name}</td>
							<td width="24%">${houseVO.hos_add}</td>
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
				<button type="button" class="picbtn" data-toggle="modal" data-target="#allHouse">查看房屋清單</button>
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSFTQNrcbfc-1RezQZWT6dYWJcLIiUPNnpL4Q&usqp=CAU" id="logo">
			</label>		
		</div>
	</div>
	<div id="foot"></div>
</body>
</html>
