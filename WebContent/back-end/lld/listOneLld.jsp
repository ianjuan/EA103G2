<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <%@ page import="com.lld.model.*"%> --%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- <% --%>
<!--  LldVO lldVO = (LldVO) request.getAttribute("lldVO"); //LldServlet.java(Concroller), 存入req的lldVO物件  -->
<%-- %>  --%>
<%String[] accStatuses = {"未啟用", "已啟用", "帳號失效"};%>

<html>
<head>
<title>房東資料 - listOneLld.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>房東資料 - ListOneLld.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/lld/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房東編號</th>
			<th>信箱</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>身分證</th>
			<th>姓名</th>
			<th>生日</th>
			<th>性別</th>
			<th>手機</th>
			<th>縣市</th>
			<th>區域</th>
			<th>地址</th>
			<th>頭貼</th>
			<th>狀態</th>
			<th>加入時間</th>
		</tr>
		<tr>
			<%-- 		<td><%=lldVO.getLld_no()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_email()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_acc()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_pwd()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_id()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_name()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_birth()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_sex()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_mobile()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_city()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_dist()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_add()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_pic()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_status()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_jointime()%></td> --%>
			<td>${lldVO.lld_no}</td>
			<td>${lldVO.lld_email}</td>
			<td>${lldVO.lld_acc}</td>
			<td>${lldVO.lld_pwd}</td>
			<td>${lldVO.lld_id}</td>
			<td>${lldVO.lld_name}</td>
			<td>${lldVO.lld_birth}</td>
			<td>${(lldVO.lld_sex)?'男':'女'}</td>
			<td>${lldVO.lld_mobile}</td>
			<td>${lldVO.lld_city}</td>
			<td>${lldVO.lld_dist}</td>
			<td>${lldVO.lld_add}</td>
			<td><img src="<%=request.getContextPath()%>/ImgReader?id=${lldVO.lld_no}"></td>
			<td><c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName"> 
					${lldVO.lld_status == varStatusName.index ? accStatus:""}
        		</c:forEach></td>	
			<td><fmt:formatDate value="${lldVO.lld_jointime}" pattern="yyyy-mm-dd" /></td>
		</tr>
	</table>


</body>
</html>