<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	RepairService repairSvc = new RepairService();
	List<RepairVO> list = repairSvc.lldGetAll("LLD000171");
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>房東修繕紀錄 - lldListAllRepair.jsp</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
  table#table-1 {
	background-color: orange;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<div class="jumbotron jumbotron-fluid">
<div class="container">
		  <h1 class="display-4">所有修繕資料</h1>
		  <p class="lead"> - lldListAllRepair.jsp</p>
	</div>
</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>修繕編號</th>
		<th>合約編號</th>
		<th>待修物品</th>
		<th>損描狀況</th>
		<th>房東處理進度</th>
		<th>損壞日期</th>
		<th>預計修畢日期</th>
		<th>房客是否滿意此次修繕</th>
		<th>案件結束日期</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="repairVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${repairVO.rep_no}</td>
			<td>${repairVO.con_no}</td>
			<td>${repairVO.rep_dam_obj}</td>
			<td>${repairVO.rep_dam_obj_des}</td>
			<td>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
			     <input type="submit" value="更改修繕進度為已修畢">
			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
			     <input type="hidden" name="action"	value="lld_getOne_For_Update_pro"></FORM>
			</td>

			<td>${repairVO.rep_case_str}</td>
			<td>${repairVO.rep_est_enddate}
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
				     <input type="submit" value="更新預計修畢日期">
				     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
				     <input type="hidden" name="action"	value="lld_getOne_For_Update_enddate">
				</FORM> 
			</td> 
			<td>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}</td>
			<td>${repairVO.rep_end_time}</td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/report.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="檢舉房客"> -->
<%-- 			     <input type="hidden" name="rep_no"  value="${repairVO.rep_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="report"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>