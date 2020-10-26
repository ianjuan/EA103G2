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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>



<title>房客修繕紀錄 - listAllRepair.jsp</title>

<style>

</style>

</head>
<body bgcolor='white'>
<% session.setAttribute("tnt_no", "TNT000077");%>

<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />




<table class="table table-hover">
<thead>
	<tr>
		<th>修繕物品圖片</th>
		<th>修繕編號</th>
		<th>合約編號</th>
		<th>待修物品</th>
		<th>損描狀況</th>
		<th>處理狀況</th>
		<th>損壞日期</th>
		<th>預計修畢日期</th>
		<th>房客是否滿意此次修繕</th>
		<th>案件結束日期</th>
	</tr>
</thead>
<c:forEach var="conVO" items="${conSvc.all}"> 
       <c:if test="${tnt_no==conVO.tnt_no}"> 
      
			<c:forEach var="repairVO" items="${repSvc.tntGetAll(conVO.con_no)}">
					
			<tr>
			<td>
				<c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">

					<img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" width="120" height="120">
		
				</c:forEach>
			</td>		
			<td>${repairVO.rep_no}</td> 
			<td>${repairVO.con_no}</td>
			<td>${repairVO.rep_dam_obj}</td> 
			
			<td>${repairVO.rep_dam_obj_des}<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>
			<td>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</td> 		
			<td>${repairVO.rep_case_str}</td>
			<td>${repairVO.rep_est_enddate}</td> 
			
			<td>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}<br>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     <input type="submit" value="回報修繕結果">
			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     <input type="hidden" name="action"	value="getOne_For_Report"></FORM>		
			</td>
			<td>${repairVO.rep_end_time}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/repair.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${repairVO.rep_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
			</tr>
		
			</c:forEach>
       </c:if>
</c:forEach>
</table>



<%-- <%@ include file="page2.file" %> --%>

</body>
</html>