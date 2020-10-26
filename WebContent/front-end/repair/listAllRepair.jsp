<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.cont.controller.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.repair_picture.model.*"%>
<%@ page import="com.housemanage.model.*"%>

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script src="<%=request.getContextPath()%>/front-end/repair/css/navbar.css"></script> 

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
a{
    color: rgb(221, 210, 160);
    text-decoration: none;
    background-color: transparent;
}
a:hover {
    color: darkgrey;
    text-decoration: none;
}

/* 文字 */
h1, h2, h3, h4, h5, h6, table{
    font-family: Microsoft JhengHei;
    font-family: 'Montserrat', sans-serif;
    
}
</style>
<script>
    	
</script>

<title>房客修繕紀錄 - listAllRepair.jsp</title>

<!-- 頂層nav-bar -->


</head>

<!-- <button class="learn-more">Learn More</button> -->


<body bgcolor='white'>



<% session.setAttribute("tnt_no", "TNT000012");%>

<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />



<section id='second'>

<div class='.container-fluid'>
<!-- 	top nav bar -->
	<div class='row'>
		<div class='col-md-12 nav'><jsp:include page="/front-end/repair/navbar.jsp"/></div>
	</div>
<!-- left nav bar -->
<!-- 側邊navbar -->
<!-- 		 <div id="left"> -->
<!-- 					<nav id="housenav"> -->
<!-- 		                <div class="menu-btn"> -->
<!-- 		                    <div class="line line--1"></div> -->
<!-- 		                    <div class="line line--2"></div> -->
<!-- 		                    <div class="line line--3"></div> -->
<!-- 		                </div>                 -->
<!-- 		                <div class="nav-links"> -->
		<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet"> --%>
		<%-- 						<input type="hidden" name="tnt_no" value="<%=tnt_no%>"> --%>
		<!-- 						<input type="hidden" name="action" value="tntgetallapl"> -->
<!-- 								<button type="submit" class="link">租屋申請</button><br> -->
		<!-- 					</FORM> -->
							
		<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet"> --%>
		<%-- 						<input type="hidden" name="tnt_no" value="<%=tnt_no%>"> --%>
		<!-- 						<input type="hidden" name="action" value="gettntcontract"> -->
<!-- 								<button type="submit" class="link" style="color: #D37707;">歷史合約</button><br> -->
		<!-- 					</FORM> -->
<!-- 		                </div> -->
<!-- 		            </nav> -->
<!-- 			</div> -->

<div class='row'>

<div class='col-md-12  text-center'>
            <h3 class='subtitle'>修繕申請紀錄</h3> 
<div class="content">      
 <c:forEach var="conVO" items="${conSvc.all}"> 
     <c:if test="${tnt_no==conVO.tnt_no}"> 

		<c:forEach var="repairVO" items="${repSvc.tntGetAll(conVO.con_no)}">

            <div class='row' >
            
				<!--修改圖片 -->
              <div class='col-md-2'>
<%--                 <c:if test="${repairVO.rep_pro eq 0}"> --%>
                	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
				 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
				        <input type="hidden" name="action" value="getOne_For_updPic">
				     	<button type="submit" class="btn btn-primary" >${(repairVO.rep_pro eq 0)?'上傳圖片':"查看圖片"}</button>
					</FORM>
<%--                 </c:if> --%>


              </div>
                <div class='col-md-4  text-center'>
                  <div class='text'>
                    <h6 class='rep_status'>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</h6>
                    <a href='#'>
                    	房屋編號<br>${conVO.hos_no}
                    
<!--                     <h5 class='hos_name'>【文化中心第一排】【黃金學區】db重灌後再試 -->
							
<%--                     			${hosSvc.getHouseInfo(conVO.hos_no).hos_name} --%>
<%--      							${conVO.hos_no} --%>

        
<!--                     </h5> -->
					</a>
                    <br>居住日期<br>
                    <h6>${aplSvc.getOneCon_apl(conVO.apl_no).apl_str}~${aplSvc.getOneCon_apl(conVO.apl_no).apl_end}</h6>   
                  </div>       
                </div>
                
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
                
                

                <div class='col-md-2'>
                  <div class='allbtn'>
					<!--編輯(狀態為處理中可編輯) -->
                      <button class='btn'  data-toggle="modal" data-target="#detail"  ${(repairVO.rep_pro eq 0)?'':"disabled"}>編輯</button><br>
                      		<!-- Modal -->
							<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLongTitle">損壞細節</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								      	
								        ${repairVO.rep_dam_obj_des}
								        
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			
								        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     							<button type="submit" class="btn btn-primary" >前往編輯</button>
			     							<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     							<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			     						
								      </div>
								    </div>
								  </div>
								</div>
                    
                    
                    
                    
                    <button class='btn'  data-toggle="modal" data-target="#report" ${(repairVO.rep_pro eq 1)?'':"disabled"} >回報結果</button><br>
                      		<!-- Modal -->
							<div class="modal fade" id="report" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLongTitle">回報修繕結果</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								      	
								        ${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}
								        
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								       	
								       	<c:if test="${repairVO.rep_tnt_rpt eq 0}">
								        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     							<button type="submit" class="btn btn-primary" onClick="window.alert('成功送出');">滿意</button>
			     							<input type="hidden" name="rep_tnt_rpt"  value="1">
			     							<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     							<input type="hidden" name="action"	value="updateRpt"></FORM>
			     							
			     							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     							<button type="submit" class="btn btn-primary" onClick="window.alert('成功送出');">不滿意</button>
			     							<input type="hidden" name="rep_tnt_rpt"  value="2">
			     							<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     							<input type="hidden" name="action"	value="updateRpt"></FORM>
			     						</c:if>
			     						
			     						</div>
								      </div>
								    </div>
								  </div>
								</div>
                    
          
                      <a class='btn' href='#' style="display:none">催一下房東</a><br>
                    </div>
                </div>  
                
             
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



<!-- <table class="table table-hover"> -->
<!-- <thead> -->
<!-- 	<tr> -->
<!-- 		<th>修繕物品圖片</th> -->
<!-- 		<th>修繕編號</th> -->
<!-- 		<th>合約編號</th> -->
<!-- 		<th>待修物品</th> -->
<!-- 		<th>損描狀況</th> -->
<!-- 		<th>處理狀況</th> -->
<!-- 		<th>損壞日期</th> -->
<!-- 		<th>預計修畢日期</th> -->
<!-- 		<th>房客是否滿意此次修繕</th> -->
<!-- 		<th>案件結束日期</th> -->
<!-- 	</tr> -->
<!-- </thead> -->
<%-- <c:forEach var="conVO" items="${conSvc.all}">  --%>
<%--        <c:if test="${tnt_no==conVO.tnt_no}">  --%>
      
<%-- 			<c:forEach var="repairVO" items="${repSvc.tntGetAll(conVO.con_no)}"> --%>
					
<!-- 			<tr> -->
<!-- 			<td> -->
<%-- 				<c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}"> --%>

<%-- 					<img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" width="120" height="120"> --%>
		
<%-- 				</c:forEach> --%>
<!-- 			</td>		 -->
<%-- 			<td>${repairVO.rep_no}</td>  --%>
<%-- 			<td>${repairVO.con_no}</td> --%>
<%-- 			<td>${repairVO.rep_dam_obj}</td>  --%>
			
<%-- 			<td>${repairVO.rep_dam_obj_des}<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM></td> -->
<%-- 			<td>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</td> 		 --%>
<%-- 			<td>${repairVO.rep_case_str}</td> --%>
<%-- 			<td>${repairVO.rep_est_enddate}</td>  --%>
			
<%-- 			<td>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}<br> --%>
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="回報修繕結果"> -->
<%-- 			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Report"></FORM>		 -->
<!-- 			</td> -->
<%-- 			<td>${repairVO.rep_end_time}</td> --%>
<!-- <!-- 			<td> --> -->
<%-- <%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/repair.servlet" style="margin-bottom: 0px;"> --%> 
<!-- <!-- 			     <input type="submit" value="刪除"> --> 
<%-- <%-- 			     <input type="hidden" name="empno"  value="${repairVO.rep_no}"> --%> 
<!-- <!-- 			     <input type="hidden" name="action" value="delete"></FORM> --> 
<!-- <!-- 			</td> --> 
<!-- 			</tr> -->
		
<%-- 			</c:forEach> --%>
<%--        </c:if> --%>
<%-- </c:forEach> --%>
<!-- </table> -->



<%-- <%@ include file="page2.file" %> --%>

</body>
</html>