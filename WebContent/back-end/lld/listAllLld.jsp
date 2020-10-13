<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.lld.model.*"%> --%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- <% --%>
<!--    	LldService lldSvc = new LldService();  -->
<!--    	List<LldVO> list = lldSvc.getAllProfile();  -->
<!--    	pageContext.setAttribute("list", list);  -->
<%-- %>  --%>
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<%String[] accStatuses = {"未啟用", "已啟用", "帳號失效"};%>


<html>
<head>
<title>所有房東資料 - listAllLld.jsp</title>

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
		<tr>
			<td>
				<h3>所有員工資料 - listAllLld.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/lld/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
			<th>修改</th>
		</tr>
		<%-- 	<%@ include file="page1.file" %>  --%>
		<%-- 	<c:forEach var="lldVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

		<%-- 		<c:forEach var="lldVO" items="${list}"> --%>
		<%-- 		<c:forEach var="lldVO" items="<%=lldSvc.getAllProfile()%>"> --%>
		<c:forEach var="lldVO" items="${lldSvc.allProfile}">

			<tr>
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
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="lld_no" value="${lldVO.lld_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>