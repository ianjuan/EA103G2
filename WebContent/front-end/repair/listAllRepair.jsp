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

/* 文字 */
h1, h2, h3, h4, h5, h6, table{
    font-family: Microsoft JhengHei;
    font-family: 'Montserrat', sans-serif;
    
}
.hos_name{
	color: pink;
}
</style>
<script>
    	
</script>

<title>房客修繕紀錄 - listAllRepair.jsp</title>

<!-- 頂層nav-bar -->


</head>

<!-- <button class="learn-more">Learn More</button> -->


<body bgcolor='white'>

<% session.setAttribute("tnt_no", "TNT000008");%>
<% String tnt_no = (String)session.getAttribute("tnt_no"); %>


<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />



<!-- 	top nav bar -->
	<div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
	</div>
<section id='second'>	
<div class='.container-fluid'>



<div class='row'>

<div class='col-md-12  text-center'>
            <h1 class='subtitle'>修繕申請紀錄</h1> 
     
<div class="content"> 
 <c:forEach var="ConVO" items="${conSvc.all}"> 
     <c:if test="${tnt_no==ConVO.tnt_no}"> 
		<c:forEach var="repairVO" items="${repSvc.tntGetAll(ConVO.con_no)}">
            <div class='row' >		
<!--               <div class='col-md-2'> -->
<%--                 <c:if test="${repairVO.rep_pro eq 0}"> --%>
<%--                 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> --%>
<%-- 				 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}"> --%>
<!-- 				        <input type="hidden" name="action" value="getOne_For_updPic"> -->
<%-- 				     	<button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 0)?'上傳圖片':'查看圖片'}</button> --%>
<!-- 					</FORM> -->
<%--                 </c:if> --%>
<!--               </div> -->
                <div class='col-md-6  text-center'>
                  <div class='text'>
                    <h3 class='rep_status'>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</h3>
                    <a href='#'>
                    	<h4>修繕編號<h4><h3>${repairVO.rep_no}</h3>
              			<h3 class='hos_name' id="${ConVO.hos_no}" > ${hosSvc.getHouseInfo(ConVO.hos_no).hos_name} </h3>
					</a>
                    <br><h4>居住日期</h4>
                    <h2>${aplSvc.getOneCon_apl(ConVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(ConVO.apl_no).apl_end}</h2>   
                  </div>       
                </div>
                

            
               		<div class='col-md-2 '>
                		<table><tr><th><h3>| 修繕物件</h3></th></tr><tr><td><h2>${repairVO.rep_dam_obj}</h2><br><h4>${repairVO.rep_dam_obj_des}</h4></td></tr></table>
                		<br><div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> 
				 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
				        <input type="hidden" name="action" value="getOne_For_updPic">
				     	<button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 0)?'上傳圖片':'查看圖片'}</button>
						</FORM></div>
					</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 申請日期</h3></th></tr><tr><td><h2>${repairVO.rep_case_str}</h2></td></tr></table>
                		<br><br><br><a href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">
                		<button class="btn btn-primary"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}>
                      	編輯</button></a>
                	</div>
                	<div class='col-md-2 '>
                		<table><tr><th><h3>| 修繕完成日期</h3></th></tr><tr><td><h2>${repairVO.rep_est_enddate}</h2></td></tr></table>
                		<br><br><br><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">
<%--                     <button class="btn btn-primary" ${repairVO.rep_tnt_rpt eq null && repairVO.rep_pro eq 1? "":' style="display:none" '} >回報結果</button></a></button><br> --%>
                		<button class="btn btn-primary" >回報結果</button></a></button><br> 
                	</div>
             </div>
                	
                	
                	
                
                
				

<!--                 	<div class='row'> -->
<!--                   		<div class='allbtn'> -->
                  
<%--                   		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet"> --%>
<%-- 				 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}"> --%>
<!-- 				        <input type="hidden" name="action" value="getOne_For_updPic"> -->
<%-- 				     	<button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 0)?'上傳圖片':'查看圖片'}</button> --%>
<!-- 						</FORM> -->
						
<!-- <!-- 					編輯(狀態為處理中可編輯) -->
<%--                       <button class='btn'  data-toggle="modal" data-target="#detail"  ${repairVO.rep_pro eq 0? "":' style="display:none" '}> --%>
<%--                       	<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Update">編輯</a></button><br> --%>

                    
                    
                    
<%--                     <button class='btn'  data-toggle="modal" data-target="#report" ${repairVO.rep_pro eq 0? "":' style="display:none" '} > --%>
<%--                     <A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=getOne_For_Report">回報結果</a></button><br> --%>
<!--  					</div> -->
<!--                     </div> -->
                  
                	
            </c:forEach>
       </c:if>
</c:forEach>
</div>
      </div>
     
    </div>

<div>


</div>

</div>


</section>






<%-- <%@ include file="page2.file" %> --%>
<script>
$(document).on("click", ".hos_name", function() {
        	window.location.href='<%=request.getContextPath()%>/HouseDet/HouseDetServlet?hos='+$(this).attr('id');
		});
</script>
</body>
</html>