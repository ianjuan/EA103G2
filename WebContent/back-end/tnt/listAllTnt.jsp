<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.tnt.model.*"%> --%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- <% --%>
<!--    	TntService tntSvc = new TntService();  -->
<!--    	List<TntVO> list = tntSvc.getAllProfile();  -->
<!--    	pageContext.setAttribute("list", list);  -->
<%-- %>  --%>
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<%String[] accStatuses = {"未啟用", "已啟用", "帳號失效"};%>


<html>
<head>
<title>所有房客資料 - listAllTnt.jsp</title>

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
				<h3>所有員工資料 - listAllTnt.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/tnt/select_page.jsp"><img src="images/back1.gif"
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
			<th>修改</th>
		</tr>
		<%-- 	<%@ include file="page1.file" %>  --%>
		<%-- 	<c:forEach var="tntVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

		<%-- 		<c:forEach var="tntVO" items="${list}"> --%>
		<%-- 		<c:forEach var="tntVO" items="<%=tntSvc.getAllProfile()%>"> --%>
		<c:forEach var="tntVO" items="${tntSvc.allProfile}">

			<tr>
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
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="tnt_no" value="${tntVO.tnt_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>