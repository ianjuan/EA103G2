<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apl.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Con_aplDAO dao = new Con_aplDAO();
    List<Con_aplVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有租屋申請資料 - listAllCon_apl1_byDAO.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有租屋申請資料 - listAllCon_apl1_byDAO.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/123.png" width="100" height="70" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>租屋申請編號</th>
		<th>房客編號</th>
		<th>房屋編號</th>
		<th>申請時間</th>
		<th>開始租屋時間</th>
		<th>結束租屋時間</th>
		<th>申請狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="con_aplVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${con_aplVO.apl_no}</td>
			<td>${con_aplVO.tnt_no}</td>
			<td>${con_aplVO.hos_no}</td>
			<td>${con_aplVO.apl_time}</td>
			<td>${con_aplVO.apl_str}</td>
			<td>${con_aplVO.apl_end}</td>
			<td>${con_aplVO.apl_status}</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>
</body>
</html>