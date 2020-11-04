<%@page import="com.tnt.model.TntService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>

<%
	String lld_no = (String) session.getAttribute("lld_no");
	if (lld_no == null) {
		lld_no = request.getParameter("lld_no");
	}
	
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
	if (lldInfo == null) {
		HouseService houseSvc = new HouseService();
		lldInfo = houseSvc.getLldInfo(lld_no);
	}

   List<Con_aplVO> apllist = (List<Con_aplVO>)session.getAttribute("apllist");
   if(apllist == null || apllist.size() == 0){
	   Con_aplService con_aplService = new Con_aplService();
	   apllist = con_aplService.lldgetAll(lld_no);
   }
   session.setAttribute("apllist",apllist);
%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>租屋申請</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/contlist_lld.css">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_evaluation.css">
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
					<a href="<%=request.getContextPath()%>/front-end/house_manage/house_index.jsp" class="link">首頁</a>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" id="lld_auth_hos" name="lld_auth_hos" value="<%=lldInfo.getLld_auth_hos()%>">
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
						<button type="submit" class="link" style="color: #D37707;">租屋申請</button>
						<br><span id="count">共<%=apllist.size()%>個申請</span>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link">合約管理</button>
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
		<div id="center">
		<h3 class="houselisttitle">租屋申請</h3><hr>
			<%@ include file="page1.file"%>
			<c:forEach var="con_aplVO" items="${apllist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="con_apl">
				<div class="houseinfo">
					<div class="linfo">
						<c:if test="${con_aplVO.apl_status == 0}">
						<img
							src="<%=request.getContextPath()%>/front-end/apl/images/tntapl.png"
							class="pic" />
						</c:if>
						<c:if test="${con_aplVO.apl_status == 1}">
						<img
							src="<%=request.getContextPath()%>/front-end/apl/images/lldace.png"
							class="pic" />
						</c:if>
						<c:if test="${con_aplVO.apl_status == 2}">
						<img
							src="<%=request.getContextPath()%>/front-end/apl/images/lldrej.png"
							class="pic" />
						</c:if>
						<c:if test="${con_aplVO.apl_status == 3}">
						<img
							src="<%=request.getContextPath()%>/front-end/apl/images/tntcan.png"
							class="pic" />
						</c:if>
					</div>
					<div class="cinfo">
						<ul>
<%-- 							<li><span class="infotitle">租屋申請編號 : </span><span>${con_aplVO.apl_no}</span></li> --%>
							<li><span class="infotitle">房屋名稱 : </span><span>${hosSvc.getHouseInfo(con_aplVO.hos_no).hos_name}</span></li>
							<li><span class="infotitle">房客姓名 / 評價 : </span><span>${tntSvc.getOneTntProfile(con_aplVO.tnt_no).tnt_name} / <%=Math.floor(Math.random()*20)/10 + 3%>分</span></li>
							<li><span class="infotitle">租屋申請時間 : </span><span>${con_aplVO.apl_time}</span></li>
							<li><span class="infotitle">租屋開始時間 : </span><span>${con_aplVO.apl_str}</span></li>
							<li><span class="infotitle">租屋結束時間 : </span><span>${con_aplVO.apl_end}</span></li>
							<li><span class="infotitle">申請狀態 : </span><span>${aplSvc.getCon_statusText(con_aplVO.getApl_status())}</span></li>
						</ul>
					</div>					
						<div class="rinfo">
							<ul>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
								<c:if test="${con_aplVO.apl_status == 0}">
								<li><button id="btn1">接受申請</button></li>
								</c:if>
								<input type="hidden" name="apl_no"  value="${con_aplVO.apl_no}">
			     				<input type="hidden" name="apl_status" value=1>
			     				<input type="hidden" name="lld_no" value="<%=lld_no%>">
			     				<input type="hidden" name="action"	value="lldupdate">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
			     				<c:if test="${con_aplVO.apl_status == 0}">
								<li><button id="btn2">拒絕申請</button></li>
								</c:if>
								<input type="hidden" name="apl_no"  value="${con_aplVO.apl_no}">
			     				<input type="hidden" name="apl_status" value=2>
			     				<input type="hidden" name="lld_no" value="<%=lld_no%>">
			     				<input type="hidden" name="action"	value="lldupdate">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>">
								<li><button type="button" id="btn3" data-toggle="modal" data-target="#tnt_eva1">房客評價</button></li>
			     				<input type="hidden" name="tnt_no" value="${con_aplVO.tnt_no}">
			     				<input type="hidden" name="lld_no" value="<%=lld_no%>">
			     				<input type="hidden" name="action"	value="gettntprofile">
			     				</FORM>
			     				
								<li><button id="btn4">聊天</button></li>																						
							</ul>
						</div>					
					</div>
					
				<div class="modal fade" id="tnt_eva1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
						    	<span class="allhousetitle">房客評價</span>
						    </div>
							<div class="modal-body">
								<div id="accordion">
								<!-- 每篇評價 -->
								  	<div class="card">
								    	<div class="card-header" id="headingOne">
								      		<h5 class="mb-0">
										        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva1" aria-expanded="false" aria-controls="collapseOne" style="float:left;">法蘭克</button>
										        <span class="listlogo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
													<span class="avgpoint">5.0分</span>
												</span>
										        <span class="evatime">2020/11/04</span>
								     		 </h5>
								    	</div>					
								    <div id="eva1" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
								      	<div class="card-body">
								        	<ul>
												<li>
											    	<div class="item">			       				
											       		<ul class="rating">								   										
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3680/3680325.svg">
																<span class="itemtitle">整潔度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											    <li>
											       	<div class="item">			       				
											       		<ul class="rating">								   
															<span class="logo">
																<img src="https://www.flaticon.com/svg/static/icons/svg/3659/3659776.svg">
																<span class="itemtitle">溝通度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											   	<li>
											       	<div class="item">
											       		<ul class="rating">								   								    
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3677/3677063.svg">
																<span class="itemtitle">滿意度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											     </li>					
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
															<span class="itemtitle">房東評論 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>這是我遇過最好的房客,推推!</textarea>			       		
											       	</div>
											     </li>
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
															<span class="itemtitle">房客回覆 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>感謝QQ寶</textarea>			       		
											       	</div>
											     </li>
											</ul>
								      	</div>
									</div>
								</div>
								<!-- 每篇評價 -->
								  	<div class="card">
								    	<div class="card-header" id="headingOne">
								      		<h5 class="mb-0">
										        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva2" aria-expanded="false" aria-controls="collapseOne" style="float:left;">大岩蛇</button>
										        <span class="listlogo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
													<span class="avgpoint">1.0分</span>
												</span>
										        <span class="evatime">2019/10/01</span>
								     		 </h5>
								    	</div>					
								    <div id="eva2" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
								      	<div class="card-body">
								        	<ul>
												<li>
											    	<div class="item">			       				
											       		<ul class="rating">								   										
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3680/3680325.svg">
																<span class="itemtitle">整潔度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											    <li>
											       	<div class="item">			       				
											       		<ul class="rating">								   
															<span class="logo">
																<img src="https://www.flaticon.com/svg/static/icons/svg/3659/3659776.svg">
																<span class="itemtitle">溝通度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											   	<li>
											       	<div class="item">
											       		<ul class="rating">								   								    
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3677/3677063.svg">
																<span class="itemtitle">滿意度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											     </li>					
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
															<span class="itemtitle">房東評論 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>你無法滿足我</textarea>			       		
											       	</div>
											     </li>
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
															<span class="itemtitle">房客回覆 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>抱歉</textarea>			       		
											       	</div>
											     </li>
											</ul>
								      	</div>
									</div>
								</div>
									<!-- 每篇評價 -->
								  	<div class="card">
								    	<div class="card-header" id="headingOne">
								      		<h5 class="mb-0">
										        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva3" aria-expanded="false" aria-controls="collapseOne" style="float:left;">陳元元</button>
										        <span class="listlogo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
													<span class="avgpoint">4.0分</span>
												</span>
										        <span class="evatime">2018/10/23</span>
								     		 </h5>
								    	</div>					
								    <div id="eva3" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
								      	<div class="card-body">
								        	<ul>
												<li>
											    	<div class="item">			       				
											       		<ul class="rating">								   										
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3680/3680325.svg">
																<span class="itemtitle">整潔度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											    <li>
											       	<div class="item">			       				
											       		<ul class="rating">								   
															<span class="logo">
																<img src="https://www.flaticon.com/svg/static/icons/svg/3659/3659776.svg">
																<span class="itemtitle">溝通度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											    </li>
											   	<li>
											       	<div class="item">
											       		<ul class="rating">								   								    
															<span class="logo">
																<img src="https://www.flaticon.com/premium-icon/icons/svg/3677/3677063.svg">
																<span class="itemtitle">滿意度 :</span>
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
																<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
															</span>
														</ul>
											       	</div>
											     </li>					
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
															<span class="itemtitle">房東評論 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>看門狗不好玩</textarea>			       		
											       	</div>
											     </li>
											     <li>
											       	<div class="item">
												       	<span class="logo" style="float:left;margin-top:5px;">
															<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
															<span class="itemtitle">房客回覆 :</span>
														</span>
												       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>2018看門狗是出了?</textarea>			       		
											       	</div>
											     </li>
											</ul>
								      	</div>
									</div>
								</div>
																			
							</div>
						</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>				    
							</div>
						</div>
					</div>
				</div>	
			</c:forEach>
			<%@ include file="page2.file"%>
				
			<div id="right"></div>
		</div>
		<div id="foot"><%@ include file="/front-end/index/footer.jsp" %></div>
		<div id="outerdiv"
			style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>
</body>
</html>
