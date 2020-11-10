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


<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFile.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFileBtn.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<!-- boostrape -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<!-- boostrape -->

<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">


<style>

/*     left nav bar */
    #left {
	width: 20%;
	height: 700px;
	float: left;
}

.navbar {
    position: relative;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    padding: .5rem 1rem;
}

/* 上傳圖片btn */
.btn-primary {
    color: #fff;
    background-color: rgb(221, 210, 160);
    border-color: rgb(221, 210, 160);
}
#second a {
    color: white;
    text-decoration: none;
    background-color: transparent;
}
#second a:hover {
    color: darkgrey;
    text-decoration: none;
}
/* 調整修繕頁面 */
.navbar-center{
	float:left;
	left: 50%;
	position: relative;
	margin:0  auto;

}
.navbar-collapse-center{
	 float: right;
  	left: -50%;
  	position: relative;
}

#second .content {
 
    width: 1200;

    
}

.button {
    position: absolute;
    bottom: 10px;
    left: 50%;
    margin-left: -104.5px; /*104.5px is half of the button's width*/
}
.nav-link{
	height:40px;
	
}

/* 文字 */
h1, h2, h3, h4, h5, h6, table{
    font-family: Microsoft JhengHei;
    font-family: 'Montserrat', sans-serif;
    
}
.hos_name{
	color: pink;
}

.title {
text-align:center;

}
.rep_no{
	color: darkblue;
}

</style>
</head>
<body bgcolor='white'>


<!-- 	top nav bar -->
	<div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
	</div>

<section id='second'>
<div class='container'>
  <div class="row justify-content-md-center">

<!-- join -->

<div class='col-md-auto'>
<!-- 	<div class=' text-center'>						 -->
		<DIV style="text-align:center;"><h1 class='title'>修繕申請紀錄</h1></DIV> <br> 

		
<!-- 修繕的狀態bar-->
<nav class="navbar navbar-expand-lg navbar-light">

  <div class="collapse navbar-collapse navbar-collapse-center" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto navbar-center">
      <li class="nav-item">
    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
			<input type="hidden" name="lld_no" value="<%=lld_no%>">
			<input type="hidden" name="action" value="getLldRepair">
			<button type="submit" class="nav-link">全部</button>
		</FORM> 	
      </li>
      <li class="nav-item">
        <button  class="nav-link" id="b1" >處理中</button>
      </li>
      <li class="nav-item">
        <button class="nav-link" id="b0" >待回覆</button>
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
      
    </ul>

  </div>
</nav>
		
<div class="content"> 		

			<!-- join -->

<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />	
	
		<c:forEach var="conVO" items="${conSvc.lldgetcon(lld_no)}">		
				
				<c:forEach var="repairVO" items="${repSvc.tntGetAll(conVO.con_no)}">
							
					 <c:if test="${repairVO.rep_pro eq 0}">
							
							<div class='row div-hide div0'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'>${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				
                  				</div>       
                				</div>
                				
                				<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row' style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">接受/拒絕</a></button><br><br>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
<!--   					                	<div class='row'>    	 -->
<!-- 		                					<button  style="margin:0 auto" class='btn btn-primary' > -->
<%-- 											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br><br> --%>
<!-- 		                				</div> -->
<%-- <%-- 		                				</c:if> --%> 
								</div>
						</div>	
			</c:if>						
			
			
			<c:if test="${repairVO.rep_pro eq 1}">
							
							<div class='row div-hide div1'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'> ${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                				                				<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row' style="text-align:center;" style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row'  style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br><br>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	<div class='row' >
		                					<button  style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
											<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</A></button><br><br>
		                				</div>
								</div>
						</div>	
			</c:if>			
			<c:if test="${repairVO.rep_pro eq 2}">
							
							<div class='row div-hide div2'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name' >${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
          					<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	
								</div>
						</div>	
			</c:if>					
			<c:if test="${repairVO.rep_pro eq 3}">
							
							<div class='row div-hide div3'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'  >${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                				                				<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	
								</div>
						</div>	
			</c:if>			
			<c:if test="${repairVO.rep_pro eq 4}">
							
							<div class='row div-hide div4'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name' >${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                				                				<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
<%-- 											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br><br> --%>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	<div class='row' >
<%-- 		                					<button  style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" }>更新日期</a></button><br><br> --%>
		                				</div>
								</div>
						</div>	
			</c:if>				
			<c:if test="${repairVO.rep_pro eq 5}">
							
							<div class='row div-hide div5'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'  >${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                		<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row text-center'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto;text-align:center" >
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br><br>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	<div class='row' >
		                					<button  style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}>
											<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" >更新日期</a></button><br><br>
		                				</div>
								</div>
						</div>	
			</c:if>	
			<c:if test="${repairVO.rep_pro eq 6}">
							
							<div class='row div-hide div6'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'>${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                				                				<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2><br>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
<%-- 											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br><br> --%>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	<div class='row' >
<%-- 		                					<button  style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_enddate" >更新日期</a></button><br><br> --%>
		                				</div>
								</div>
						</div>	
			</c:if>	
			<c:if test="${repairVO.rep_pro eq 7}">
							
							<div class='row div-hide div7'>
							
							<div class='col-md-6  text-center'>
								 <div class='text'>
									<h3 class='rep_status'>狀態
										<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 2}"><span style="color:pink;">已婉拒申請</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">申請被取消</span></c:if>
                    					<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>                    	
                    					<c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>
          								<c:if test="${repairVO.rep_pro eq 7}"><span style="color:white;">已結案</span></c:if>
										<button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "再修一次":"滿意")}</button><br>
                    				</h3>
<!-- 								<a href='#' style="color:darkgrey;"> -->
                    				<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              						<h3 class='hos_name'  >${hosSvc.getHouseInfo(conSvc.getOneCon(conVO.con_no).hos_no).hos_name}</h3>
<!-- 								</a> -->
								
								<br><h4>居住日期</h4>
                    			<h2>${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getOneCon(conVO.con_no).apl_no).apl_end}</h2>   
                  				</div>       
                				</div>
                				
                		<div class='col-md-2 ' style="margin:0 auto">
                				
                					<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                						<div class='row'  style="margin:0 auto">
                							<h2 style="margin:0 auto; margin-bottom: 8px;" >${repairVO.rep_dam_obj}</h2><br>
                							<h3 style="margin:0 auto">${repairVO.rep_dam_obj_des}</h3>
                						</div>
                						
                						<div class='row' style="text-align:center;" style="margin:0 auto">
			                				<br><div style="margin:0 auto">
			                					<!--修繕圖片 -->
												<c:choose>
												<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
													<span class='text' style="padding: 30px;">暫無圖片</span>
												</c:when>
												<c:otherwise>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
											 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
											        <input type="hidden" name="action" value="getOne_For_seePic">
											     	<button type="submit" class="btn btn-primary" style="margin:0 auto">查看圖片</button>
												</FORM>
												</c:otherwise>
												</c:choose>
											</div>
										</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto" style="text-align:center;">
									<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
										<div class='row' >
			                				<h2>${repairVO.rep_case_str}</h2>
			                			</div>
										<div class='row' style="margin:0 auto">
		                					<!--btn3 待回覆/處理中/再修一次:處理中 才可更新-->			
<%-- 											<button style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 0) || (repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A style="margin:0 auto" href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br><br> --%>
		                				</div>
								</div>
								<div class='col-md-2 ' style="margin:0 auto">
									<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
										<div class='row'>
			                				<h2 >${repairVO.rep_est_enddate}</h2>
			                			</div>
					                	<div class='row' >
<%-- 		                					<button  style="margin:0 auto" class='btn btn-primary' ${(repairVO.rep_pro eq 1 )|| (repairVO.rep_pro eq 5)? "":' style="display:none" '}> --%>
<%-- 											<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新日期</a></button><br><br> --%>
		                				</div>
								</div>
						</div>	
			</c:if>	
						
	</c:forEach>
</c:forEach>		
	
<!-- join -->						
	</div>						
</div>
</div>
</div>
</div>
</section>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<script>
$(document).ready(function(){
	

	$("#b0").click(function() {
// 		alert("000")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div0").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b1").click(function() {
// 		alert("111")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div1").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b2").click(function() {
// 		alert("222")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div2").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b3").click(function() {
// 		alert("333")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div3").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b4").click(function() {
// 		alert("444")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div4").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b5").click(function() {
// 		alert("555")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div5").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b6").click(function() {
// 		alert("666")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div6").each(function(){
			$(this).show();
		});
		
		
	});
	
	$("#b7").click(function() {
// 		alert("777")
		$(".div-hide").each(function(){
			$(this).hide();
		});
		
		$(".div7").each(function(){
			$(this).show();
		});
		
		
	});
});




// $(document).on("click", ".hos_name", function() {
<%--         	window.location.href='<%=request.getContextPath()%>/front-end/index/HouseDet.jsp?hos='+$(this).attr('id'); --%>
// 		});       
 </script>
 </body>       
</html>