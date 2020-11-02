<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%-- <% session.setAttribute("lld_no", "LLD000897");%>  --%>

<% String lld_no = (String) session.getAttribute("lld_no");
	if (lld_no == null) {
		lld_no = request.getParameter("lld_no");
	} %>
<html>
<head>
<title>房東修繕紀錄 - lldListAllRepair.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">

<style>

</style>
</head>
<body bgcolor='white'>

<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />



<!-- 	top nav bar -->
	<div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
	</div>

<section id='second'>
<div class='.container-md'>
<!-- join -->
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
		
		
		
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<!--   <a class="navbar-brand" href="#">Navbar</a> -->
<!--   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> -->
<!--     <span class="navbar-toggler-icon"></span> -->
<!--   </button> -->

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <button class="nav-link"  id="b1" >處理中</button>
      </li>
      <li class="nav-item">
        <button class="nav-link"  id="b0" >待回覆</button>
      </li>
      <li class="nav-item">
        <button class="nav-link"  id="b2" >已婉拒申請</button>
      </li>
       <li class="nav-item">
        <button class="nav-link"  id="b3" >申請被取消</button>
      </li>
       <li class="nav-item">
        <button class="nav-link"  id="b4" >已修畢</button>
      </li>
       <li class="nav-item">
        <button class="nav-link"  id="b5" >再修一次:處理中</button>
      </li>
       <li class="nav-item">
        <button class="nav-link"  id="b6" >再修一次:已修畢</button>
      </li>
      <li class="nav-item">
        <button class="nav-link"  id="b7" >已結案</button>
      </li>
      
<!--       <li class="nav-item dropdown"> -->
<!--         <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!--           Dropdown -->
<!--         </a> -->
<!--         <div class="dropdown-menu" aria-labelledby="navbarDropdown"> -->
<!--           <a class="dropdown-item" href="#">Action</a> -->
<!--           <a class="dropdown-item" href="#">Another action</a> -->
<!--           <div class="dropdown-divider"></div> -->
<!--           <a class="dropdown-item" href="#">Something else here</a> -->
<!--         </div> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
<!--       </li> -->
    </ul>
<!--     <form class="form-inline my-2 my-lg-0"> -->
<!--       <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
<!--       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
<!--     </form> -->
  </div>
</nav>
		
		
			<!-- join -->
	<c:forEach var="HouseVO" items="${hosSvc.allHouse}">
		<c:if test="${lld_no==HouseVO.lld_no}">
			<!-- conSvc.getConbyhos(HouseVO.hos_no)===ConVO -->
					<c:forEach var="repairVO" items="${repSvc.tntGetAll(conSvc.getConbyhos(HouseVO.hos_no).con_no)}">
						<c:if test="${repairVO.con_no==conSvc.getConbyhos(HouseVO.hos_no).con_no}">	
							
					 <c:if test="${repairVO.rep_pro eq 0}">
							
							<div class='row div-hide div0'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">接受/拒絕申請</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						
						<c:if test="${repairVO.rep_pro eq 1}">
							
							<div class='row div-hide div1'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						<c:if test="${repairVO.rep_pro eq 2}">
							
							<div class='row div-hide div2'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    				<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						<c:if test="${repairVO.rep_pro eq 3}">
							
							<div class='row div-hide div3'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						<c:if test="${repairVO.rep_pro eq 4}">
							
							<div class='row div-hide div4'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						<c:if test="${repairVO.rep_pro eq 5}">
							
							<div class='row div-hide div5'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						
							<c:if test="${repairVO.rep_pro eq 6}">
							
							<div class='row div-hide div6'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						
						<c:if test="${repairVO.rep_pro eq 7}">
							
							<div class='row div-hide div7'>
							
							<div class='row' >
							
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<span class='text' style="padding: 30px;">暫無圖片</span>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>
                    					<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
          
                    					</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
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
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br>
										
								      	<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
										<button class='btn' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
						</div><%-- 狀態div --%>
						</c:if><%-- 判斷狀態 --%>
						
						
					</c:if>
				</c:forEach>							
			</c:if>
<%-- 		</c:set> --%>
<%-- 	</c:if> --%>
</c:forEach>
		
	
<!-- join -->						
	</div>						
</div>
</div>
</section>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$(document).ready(function(){
	

	$("#b0").click(function() {
		alert("000")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div0").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b1").click(function() {
		alert("111")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div1").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b2").click(function() {
		alert("222")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div2").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b3").click(function() {
		alert("333")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div3").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b4").click(function() {
		alert("444")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div4").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b5").click(function() {
		alert("555")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div5").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b6").click(function() {
		alert("666")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div6").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b7").click(function() {
		alert("777")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div7").each(function(){
			$(this).show();
		});
		
		
	});
});



        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${repairVO.rep_est_enddate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
//            maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
 </script>
 </body>       
</html>