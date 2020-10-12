<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <%@ page import="com.tnt.model.*"%> --%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- <% --%>
<!--  TntVO tntVO = (TntVO) request.getAttribute("tntVO"); //TntServlet.java(Concroller), 存入req的tntVO物件  -->
<%-- %>  --%>
<%String[] accStatuses = {"未啟用", "已啟用", "帳號失效"};%>

<html>
<head>
<title>房客資料 - listOneTnt.jsp</title>

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
				<h3>房客資料 - ListOneTnt.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/tnt/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房客編號</th>
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
			<%-- 		<td><%=tntVO.getTnt_no()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_email()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_acc()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_pwd()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_id()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_name()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_birth()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_sex()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_mobile()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_city()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_dist()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_add()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_pic()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_status()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_jointime()%></td> --%>
			<td>${tntVO.tnt_no}</td>
			<td>${tntVO.tnt_email}</td>
			<td>${tntVO.tnt_acc}</td>
			<td>${tntVO.tnt_pwd}</td>
			<td>${tntVO.tnt_id}</td>
			<td>${tntVO.tnt_name}</td>
			<td>${tntVO.tnt_birth}</td>
			<td>${(tntVO.tnt_sex)?'男':'女'}</td>
			<td>${tntVO.tnt_mobile}</td>
			<td>${tntVO.tnt_city}</td>
			<td>${tntVO.tnt_dist}</td>
			<td>${tntVO.tnt_add}</td>
			<td><img src="<%=request.getContextPath()%>/ImgReader?id=${tntVO.tnt_no}"></td>
			<td><c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName"> 
					${tntVO.tnt_status == varStatusName.index ? accStatus:""}
        		</c:forEach></td>	
			<td><fmt:formatDate value="${tntVO.tnt_jointime}" pattern="yyyy-mm-dd" /></td>
		</tr>
	</table>


</body>
</html>