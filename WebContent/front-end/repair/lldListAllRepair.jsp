<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>


<%
	RepairService repairSvc = new RepairService();
	List<RepairVO> list = repairSvc.lldGetAll("LLD000171");
	pageContext.setAttribute("list",list);
%>

<% session.setAttribute("lld_no", "LLD000171");%>

<html>
<head>
<title>房東修繕紀錄 - lldListAllRepair.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <div class="container">
​
  <a class="navbar-brand" href="#">愛租I-ZU</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav ml-auto">
 <a class="nav-item nav-link active" href="https://codepen.io/">尋找房源<span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="https://www.gamer.com.tw/">地圖找房</a>
      <a class="nav-item nav-link" href="https://www.gamer.com.tw/">我的收藏</a>
      <li class="nav-item dropdown">
        <span data-toggle="dropdown">
        <input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" style="width:40px" />
          我是房東
</span>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">最新通知</a>
          <a class="dropdown-item" href="#">個人資訊</a>
          <a class="dropdown-item" href="#">我的錢包</a>
          <a class="dropdown-item" href="#">登出</a>
        </div>
      </li>
    </div>
  </div></div>
</nav>
<style>

</style>

</head>
<body bgcolor='white'>

${lld_no}
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />


<section id='second'>
<div class='.container-md'>

<!-- <!-- join --> -->
<%-- 	<c:forEach var="HouseVO" items="${hosSvc.allHouse}">  --%>
<%-- 		<c:if test="${lld_no==HouseVO.lld_no}">  --%>
<%-- 			<c:forEach var="ConVO" items="${conSvc.getConbyhos(HouseVO.hos_no)}">  --%>
<%-- 				<c:if test="${HouseVO.hos_no==ConVO.hos_no}">  --%>
<%-- 					<c:forEach var="repVO" items="${repSvc.tntGetAll(conVO.con_no)}"> --%>
<%-- 						<c:if test="${repVO.con_no==conVO.con_no}">	 --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- join -->
<div class='row'>
	<div class='col-md-2' id="leftSpace"></div>						
	<div class='col-md-8  text-center'>						
		<h3 class='subtitle'>修繕申請紀錄</h3>				
			<!-- join -->
	<c:forEach var="HouseVO" items="${hosSvc.allHouse}"> 
		<c:if test="${lld_no==HouseVO.lld_no}"> 
			<c:forEach var="ConVO" items="${conSvc.getConbyhos(HouseVO.hos_no)}"> 
				<c:if test="${HouseVO.hos_no==ConVO.hos_no}"> 
					<c:forEach var="repVO" items="${repSvc.tntGetAll(conVO.con_no)}">
						<c:if test="${repVO.con_no==conVO.con_no}">	
							<div class='row' >
								<!--修繕圖片 -->
								<div class='col-md-2'>
                  					<a href='#'><c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">
										<img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" width="120" height="120">
									</c:forEach></a>
                				</div>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</h6>
                    					<a href='#'>房屋編號<br>${conVO.hos_no}</a>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(conVO.apl_no).apl_end}</h6>   
                    				</div>       
               	 				</div>
								<!--修繕起訖 -->
               	 				<div class='col-md-4 '>
               	 					<table class='repair_info'>
                      					<tr>
                        					<th scope="col">| 修繕物件</th>
                        					<th scope="col">| 申請日期</th>
                        					<th scope="col">| 修繕完成日期</th>
                      					</tr>
                      					<tr>
                        					<td><code><h3> ${repairVO.rep_dam_obj}</h3><h6>${repairVO.rep_dam_obj_des}</h6></code></td>
                        					<td><code><h4>${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn'  data-toggle="modal" data-target="#estEnd" ${(repairVO.rep_pro eq 0)?'':"disabled"} >更新日期</button><br>
										<!-- Modal -->
										<div class="modal fade" id="estEnd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
								  			<div class="modal-dialog" role="document">
								    			<div class="modal-content">
								      				<div class="modal-header">
								       					 <h5 class="modal-title" id="exampleModalLongTitle">預計修繕結束日期</h5>
								        					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          						<span aria-hidden="true">&times;</span>
								        					</button>
								      				</div>
								      				<div class="modal-body">
								      	
								        				${repairVO.rep_est_enddate}
								        
								      				</div>
								      				<div class="modal-footer">
								      					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								      					<c:if test="${repairVO.rep_pro eq 0}">
								      						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
				     										<button type="submit" class="btn btn-primary">編輯</button>
				     										<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
				     										<input type="hidden" name="action"	value="lld_getOne_For_Update_enddate">
															</FORM> 
								      					</c:if>
								      				</div>
								      			</div>
											</div>	
								      	<!--btn3 -->			
										<button class='btn'  data-toggle="modal" data-target="#progress" ${(repairVO.rep_pro eq 0)?'':"disabled"} >更新進度</button><br>		
										<!-- Modal -->
										<div class="modal fade" id="progress" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
								  			<div class="modal-dialog" role="document">
								    			<div class="modal-content">
								      				<div class="modal-header">
								       					 <h5 class="modal-title" id="exampleModalLongTitle">目前修繕進度</h5>
								        					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          						<span aria-hidden="true">&times;</span>
								        					</button>
								      				</div>
													<div class="modal-body">
								      	
								        				${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}
								        
								      				</div>						
													<div class="modal-footer">
								      					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								      					<c:if test="${repairVO.rep_pro eq 0}">
								      						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     											<button type="submit" class="btn btn-primary" onClick="window.alert('更新成功');">修繕完畢</button>
			     											<input type="hidden" name="rep_pro"  value="1">
			     											<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     											<input type="hidden" name="action"	value="updatePro"></FORM> 
								      					</c:if>
								      				</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>							
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
<!-- join -->						
	</div>						
</div>

</div>
</section>

















<%-- <%-- 錯誤表列 --%> --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>修繕編號</th> -->
<!-- 		<th>合約編號</th> -->
<!-- 		<th>待修物品</th> -->
<!-- 		<th>損描狀況</th> -->
<!-- 		<th>房東處理進度</th> -->
<!-- 		<th>損壞日期</th> -->
<!-- 		<th>預計修畢日期</th> -->
<!-- 		<th>房客是否滿意此次修繕</th> -->
<!-- 		<th>案件結束日期</th> -->
<!-- 	</tr> -->
<%-- 	<%@ include file="page1.file" %>  --%>
<%-- 	<c:forEach var="repairVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${repairVO.rep_no}</td> --%>
<%-- 			<td>${repairVO.con_no}</td> --%>
<%-- 			<td>${repairVO.rep_dam_obj}</td> --%>
<%-- 			<td>${repairVO.rep_dam_obj_des}</td> --%>
<%-- 			<td>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"} --%>
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="更改修繕進度為已修畢"> -->
<%-- 			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="lld_getOne_For_Update_pro"></FORM> -->
<!-- 			</td> -->

<%-- 			<td>${repairVO.rep_case_str}</td> --%>
<%-- 			<td>${repairVO.rep_est_enddate} --%>
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;"> --%>
<!-- 				     <input type="submit" value="更新預計修畢日期"> -->
<%-- 				     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}"> --%>
<!-- 				     <input type="hidden" name="action"	value="lld_getOne_For_Update_enddate"> -->
<!-- 				</FORM>  -->
<!-- 			</td>  -->
<%-- 			<td>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}</td> --%>
<%-- 			<td>${repairVO.rep_end_time}</td> --%>


<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>