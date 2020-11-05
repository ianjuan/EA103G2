<%@page import="com.lld.model.LldVO"%>
<%@page import="com.tnt.model.TntService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.rec.model.*"%>

<%
	String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	String con_no = (String) request.getAttribute("con_no");
	
	List<RecVO> reclist = (List<RecVO>)session.getAttribute("reclist");
	if(reclist == null || reclist.size() == 0){
		
	}
	session.setAttribute("reclist",reclist);

%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="recSvc" scope="page" class="com.rec.model.RecService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>定期費用</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/contlist_tnt.css">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_evaluation.css">
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
					</FORM>
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
		<div id="center">
		<h3 class="houselisttitle">定期帳單</h3><hr>
			<%@ include file="tntpage1"%>
			<c:forEach var="recVO" items="${reclist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="rec">
				<div class="houseinfo">
					<div class="linfo">
						<c:if test="${recVO.rec_sta == 0}">
						<img
							src="<%=request.getContextPath()%>/front-end/rec/images/lldunfill.png"
							class="pic" />
					</c:if>
					<c:if test="${recVO.rec_sta == 1}">
						<img
							src="<%=request.getContextPath()%>/front-end/rec/images/tntunpaid.png"
							class="pic" />
					</c:if>
					<c:if test="${recVO.rec_sta == 2}">
						<img
							src="<%=request.getContextPath()%>/front-end/rec/images/tntpaid.png"
							class="pic" />
					</c:if>
					<c:if test="${recVO.rec_sta == 3}">
						<img
							src="<%=request.getContextPath()%>/front-end/rec/images/tntexp.png"
							class="pic" />
					</c:if>
					</div>
					<div class="cinfo">
						<ul>
							<li><span class="infotitle">訂單編號 : </span><span>${recVO.rec_no}</span></li>							
							<li><span class="infotitle">訂單月份 : </span><span>${recSvc.getMonthText(recVO.rec_mon)}</span></li>
							<c:if test="${recVO.rec_sta != 0}">
							<li><span class="infotitle">本月使用水量 : </span><span>${recVO.rec_water}度</span></li>
							<li><span class="infotitle">本月使用電量 : </span><span>${recVO.rec_elec}度</span></li>
							<li><span class="infotitle">總金額 : </span><span>${recVO.rec_total}元</span></li>

							
							</c:if>
							<li><span class="infotitle">帳單狀態 : </span><span>${recSvc.getRecStatusText(recVO.rec_sta)}</span></li>
						</ul>
					</div>					
						<div class="rinfo">
							
							<ul>					
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
								<c:if test="${recVO.rec_sta == 1}">
								<li><button id="btn1">繳交帳單</button></li>
								</c:if>
								<input type="hidden" name="rec_no"  value="${recVO.rec_no}">
								<input type="hidden" name="hos_no"  value="${recVO.hos_no}">
								<input type="hidden" name="con_no"  value="${recVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="gettntrecdetail">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
			     				<c:if test="${recVO.rec_sta == 2}">
								<li><button id="btn1">當月詳情</button></li>
								</c:if>
								<input type="hidden" name="rec_no"  value="${recVO.rec_no}">
								<input type="hidden" name="hos_no"  value="${recVO.hos_no}">
								<input type="hidden" name="con_no"  value="${recVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="gettntrecdetail">
			     				</FORM>

			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>">
			     				<c:if test="${recVO.rec_sta == 2}">
			     				<li><button type="button" id="btn2" data-toggle="modal" data-target="#evaluationdiv${rec.count}">評價房東</button></li>
								</c:if>
								<input type="hidden" name="rec_no"  value="${recVO.rec_no}">
								<input type="hidden" name="hos_no"  value="${recVO.hos_no}">
								<input type="hidden" name="con_no"  value="${recVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				</FORM>
			     				
			     				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
			     				<c:if test="${recVO.rec_sta != 0}">
								<li><button id="btn3">檢舉帳單</button></li>
								</c:if>
								<input type="hidden" name="rec_no"  value="${recVO.rec_no}">
								<input type="hidden" name="hos_no"  value="${recVO.hos_no}">
								<input type="hidden" name="con_no"  value="${recVO.con_no}">
			     				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			     				<input type="hidden" name="action"	value="getOne_For_Update">
			     				</FORM>
								
														
							</ul>
						</div>					
					</div>
					
					<!-- 評價房東區塊 -->
					<div class="modal fade" id="evaluationdiv${rec.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<form id="evaluation${rec.count}" name="evaluation" METHOD="post" ACTION="<%=request.getContextPath()%>/com/ComServlet">
								<div class="modal-header">
								    <marquee scrollamount="10" class="evatitle">來對房東作評價吧~~</marquee>
								</div>
								<div class="modal-body">
									<ul>
										<li>
									    	<div class="item">			       				
									       		<ul class="rating">								   										
													<li><button type="button" value="5" onclick="lcmcleanPoint(event)"><div class="star" value="10" onclick="lcmcleanPoint(event)"></div></button></li>
													<li><button type="button" value="4" onclick="lcmcleanPoint(event)"><div class="star" value="9" onclick="lcmcleanPoint(event)"></div></button></li>
													<li><button type="button" value="3" onclick="lcmcleanPoint(event)"><div class="star" value="8" onclick="lcmcleanPoint(event)"></div></button></li>
													<li><button type="button" value="2" onclick="lcmcleanPoint(event)"><div class="star" value="7" onclick="lcmcleanPoint(event)"></div></button></li>
													<li><button type="button" value="1" onclick="lcmcleanPoint(event)"><div class="star" value="6" onclick="lcmcleanPoint(event)"></div></button></li>
													<span class="logo">
														<img src="https://www.flaticon.com/premium-icon/icons/svg/3680/3680325.svg">
														<span class="itemtitle">整潔度 :</span>
													</span>
												</ul>
									       	</div>
									    </li>
									    <li>
									       	<div class="item">			       				
									       		<ul class="rating">								   
													<li><button type="button" value="5" onclick="lcmcommutPoint(event)"><div class="star" value="10" onclick="lcmcommutPoint(event)"></div></button></li>
													<li><button type="button" value="4" onclick="lcmcommutPoint(event)"><div class="star" value="9" onclick="lcmcommutPoint(event)"></div></button></li>
													<li><button type="button" value="3" onclick="lcmcommutPoint(event)"><div class="star" value="8" onclick="lcmcommutPoint(event)"></div></button></li>
													<li><button type="button" value="2" onclick="lcmcommutPoint(event)"><div class="star" value="7" onclick="lcmcommutPoint(event)"></div></button></li>
													<li><button type="button" value="1" onclick="lcmcommutPoint(event)"><div class="star" value="6" onclick="lcmcommutPoint(event)"></div></button></li>
													<span class="logo">
														<img src="https://www.flaticon.com/svg/static/icons/svg/3659/3659776.svg">
														<span class="itemtitle">溝通度 :</span>
													</span>
												</ul>
									       	</div>
									    </li>
									   	<li>
									       	<div class="item">
									       		<ul class="rating">								   
													<li><button type="button" value="5" onclick="lcmsatisfyPoint(event)"><div class="star" value="10" onclick="lcmsatisfyPoint(event)"></div></button></li>
													<li><button type="button" value="4" onclick="lcmsatisfyPoint(event)"><div class="star" value="9" onclick="lcmsatisfyPoint(event)"></div></button></li>
													<li><button type="button" value="3" onclick="lcmsatisfyPoint(event)"><div class="star" value="8" onclick="lcmsatisfyPoint(event)"></div></button></li>
													<li><button type="button" value="2" onclick="lcmsatisfyPoint(event)"><div class="star" value="7" onclick="lcmsatisfyPoint(event)"></div></button></li>
													<li><button type="button" value="1" onclick="lcmsatisfyPoint(event)"><div class="star" value="6" onclick="lcmsatisfyPoint(event)"></div></button></li>									    
													<span class="logo">
														<img src="https://www.flaticon.com/premium-icon/icons/svg/3677/3677063.svg">
														<span class="itemtitle">滿意度 :</span>
													</span>
												</ul>
									       	</div>
									     </li>					
									     <li>
									       	<div class="item">
										       	<span class="logo" style="float:left;margin-top:5px;">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
													<span class="itemtitle">評論 :</span>
												</span>
										       	<textarea rows="2" wrap="hard" onkeyup="checkLen(this)" name="lcm_commet"></textarea>	       		
									       	</div>
									     </li>
									</ul>
								</div>
								<div class="modal-footer">
									<input type="hidden" name="con_no" value="${recVO.con_no}">
									<input type="hidden" name="lld_no" value="${hosSvc.getHouseInfo(conSvc.getOneCon(recVO.con_no).hos_no).lld_no}">
									<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
									<input type="hidden" name="action" value="tntinsert">
									<button type="button" class="btn btn-primary" onclick="notice(event)" value="${rec.count}">送出</button>					
									<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
			<%@ include file="tntpage2.file"%>
			<div id="right"></div>
		</div>
		<div id="foot"><%@ include file="/front-end/index/footer.jsp" %></div>
		<div id="outerdiv"
			style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_evaluation.js" charset="UTF-8"></script>				
</body>
</html>
