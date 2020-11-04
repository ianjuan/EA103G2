<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.cont.controller.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.repair_picture.model.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>

<% String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	} %>

<html>
<head>
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
<!-- sweet alert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
    color: rgb(221, 210, 160);
    text-decoration: none;
    background-color: transparent;
}
#seconf a:hover {
    color: darkgrey;
    text-decoration: none;
}

.button {
    position: absolute;
    bottom: 10px;
    left: 50%;
    margin-left: -104.5px; /*104.5px is half of the button's width*/


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
</style>
<script>
    	
</script>

<title>房客修繕紀錄 - listAllRepair.jsp</title>

<!-- 頂層nav-bar -->


</head>

<!-- <button class="learn-more">Learn More</button> -->


<body bgcolor='white'>
<%-- <% request.setAttribute("tnt_no", "TNT000002"); --%>
	


<%-- <% String tnt_no = (String)request.getAttribute("tnt_no"); --%>
	



<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />



<!-- 	top nav bar -->
	<div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
	</div>
<section id='second'>	
<!-- <div class='.container-fluid'> -->
<div class="container">
  <div class="row justify-content-md-center">
			    <div class="col col-lg-2"></div>
		

<!-- <div class='row'> -->
<div class="col-md-auto">

    <DIV style="text-align:center;"><h1 class='title'>修繕申請紀錄</h1></DIV> <br> 

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<!--   <a class="navbar-brand" href="#">Navbar</a> -->
<!--   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> -->
<!--     <span class="navbar-toggler-icon"></span> -->
<!--   </button> -->

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
     <li class="nav-item">
    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
			<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
			<input type="hidden" name="action" value="getTntRepair">
			<button type="submit" class="nav-link">全部</button>
		</FORM>
	</li>
      <li class="nav-item">
        <button class="nav-link"  id="b1" >處理中</button>
      </li>
      <li class="nav-item">
        <button class="nav-link"  id="b0" >待回覆</button>
      </li>
      <li class="nav-item">
        <button class="nav-link"  id="b2" >申請被婉拒</button>
      </li>
       <li class="nav-item">
        <button class="nav-link"  id="b3" >已取消申請</button>
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


<div class="content"> 
 <c:forEach var="ConVO" items="${conSvc.tntgetcon(tnt_no)}"> 

		<c:forEach var="repairVO" items="${repSvc.tntGetAll(ConVO.con_no)}">
<!-- 判斷狀態顯示  0~6	   -->
 <c:if test="${repairVO.rep_pro eq 0}">
        
            <div class='row div-hide div0'>		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
                    	<c:if test="${repairVO.rep_pro eq 0}"><span style="color:orange;">待回覆</span></c:if>
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4)? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_del_rep">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 0 )||(repairVO.rep_pro eq 1)||(repairVO.rep_pro eq 5)? "":' style="display:none" '}>取消申請</button></a><br>
                			</div>
                	</div>
             </div>
</c:if>            


<c:if test="${repairVO.rep_pro eq 1}">
	            <div class='row div-hide div1' >		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
                    	
                    	<c:if test="${repairVO.rep_pro eq 1}"><span style="color:green;">處理中</span></c:if>
                    	
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>


</c:if>            

<c:if test="${repairVO.rep_pro eq 2 }">
	            <div class='row div-hide div2'>		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
                    	
                    	<c:if test="${repairVO.rep_pro eq 2}"><span style="color:green;">申請被婉拒</span></c:if>
                    	
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>


</c:if>            
             
<c:if test="${repairVO.rep_pro eq 3}">
	            <div class='row div-hide div3' >		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
<%--                     ${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"} --%>
                    	
                    	<c:if test="${repairVO.rep_pro eq 3}"><span style="color:red;">已取消申請</span></c:if>
                    	
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>


</c:if>            
 <c:if test="${repairVO.rep_pro eq 4}">
	            <div class='row div-hide div4' >		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
                    	
                    	<c:if test="${repairVO.rep_pro eq 4}"><span style="color:black;">已修繕完畢</span></c:if>
                    	
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>


</c:if>                                   	
<c:if test="${repairVO.rep_pro eq 5}">
        
            <div class='row div-hide div5'>		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態
<%--                     ${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"} --%>
                    	               
              <c:if test="${repairVO.rep_pro eq 5}"><span style="color:darkgrey;">再修一次:處理中</span></c:if>
										
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
							    <!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>
</c:if>            
             	
 
 <c:if test="${repairVO.rep_pro eq 6}">
        
            <div class='row div-hide div6'>		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態         
						<c:if test="${repairVO.rep_pro eq 6}"><span style="color:purple;">再修一次:已修繕完畢</span></c:if>										
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
									<!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )||(repairVO.rep_pro eq 6)? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>
</c:if>            
             	              	
<c:if test="${repairVO.rep_pro eq 7}">
        
            <div class='row div-hide div7'>		

                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>狀態         
						<c:if test="${repairVO.rep_pro eq 7}"><span style="color:lightblue;">已結案</span></c:if>										
                    </h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                
				
            
               		<div class='col-md-2 '>
               				
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td  ></td></tr></table>
                			<div class='row' style="text-align:center;">
                				<h2 style="text-align:center;">${repairVO.rep_dam_obj}</h2>
                				<h3>${repairVO.rep_dam_obj_des}</h3>
                			</div>
                			<div class='row' >
			                	<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
							 	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							    <input type="hidden" name="action" value="getOne_For_updPic">
									<!-- 處理中:1 則顯示上傳圖片 -->
							    <button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 1 || repairVO.rep_pro eq 5)?'上傳圖片':'查看圖片'}</button>
								</FORM></div>
							</div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_case_str}</h2>
                			</div>
                			<div class='row' >
		                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
		                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
		                      	編輯</button></a>
                      		</div>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 預估修畢</h3></th></tr><tr><td></td></tr></table>
                			<div class='row' >
                				<h2>${repairVO.rep_est_enddate}</h2>
                			</div>
                			<div class='row' >
                				<br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
                				<button class="btn btn-primary" ${(repairVO.rep_pro eq 4 )? "":' style="display:none" '}>回報結果</button></a><br>
                			</div>
                	</div>
             </div>
</c:if>           
                
				

                  
                	
            </c:forEach>
<%--        </c:if> --%>
</c:forEach>
</div>
      </div>
      	<div class="col col-lg-2">
      	 
    </div>
     
    </div>

<div>


</div>

</div>


</section>






<%-- <%@ include file="page2.file" %> --%>
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






	
$(document).on("click", ".hos_name", function() {
        	window.location.href='<%=request.getContextPath()%>/HouseDet/HouseDetServlet?hos='+$(this).attr('id');
		});
</script>
</body>
</html>