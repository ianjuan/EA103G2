<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rptt.model.*"%>

<%
	RpttVO rpttVO = (RpttVO) request.getAttribute("rpttVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉資料新增 - addEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table #table-1 h4 {
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
	width: 480px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	border: 0px solid #CCCCFF;
}

th, td {
	border: 0px solid #CCCCFF;
	padding: 1px;
}

td.A {
	width: 200px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>檢舉資料新增 - addRptt.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="RpttServlet" name="form1">
		<table>
			<tr>
				<td class="A">檢舉房客編號:</td>
				<td><input type="TEXT" name="tnt_no" size="45"
					value="<%=(rpttVO == null) ? "TNT000022" : rpttVO.getTnt_no()%>" /></td>
			</tr>
			<tr>
				<td class="A">檢舉的房東編號:</td>
				<td><input type="TEXT" name="lld_no" size="45"
					value="<%=(rpttVO == null) ? "LLD000007" : rpttVO.getLld_no()%>" /></td>
			</tr>
			<tr>
				<td class="A">檢舉內容:</td>
				<td><input type="TEXT" name="rptt_content" size="45"
					value="<%=(rpttVO == null) ? "房客很髒" : rpttVO.getRptt_content()%>" /></td>

			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>