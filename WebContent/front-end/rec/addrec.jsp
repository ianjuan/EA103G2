<%@page import="com.rec.model.RecVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	RecVO recVO = (RecVO)request.getAttribute("recVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>手動新增定期租約 - addCon_apl.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>手動新增定期租約 - addrec.jsp</h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/apl/select_page.jsp"><img
						src="<%=request.getContextPath()%>/front-end/apl/images/123.png"
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/rec/RecServlet" name="form1">
		<table>
			<tr>
				<td>合約編號:</td>
				<td><input type="TEXT" name="con_no" size="45"
					value="<%=(recVO == null) ? "CON000001" : recVO.getCon_no()%>" /></td>
			</tr>
			<tr>
				<td>房屋編號:</td>
				<td><input type="TEXT" name="hos_no" size="45"
					value="<%=(recVO == null) ? "HOS000001" : recVO.getHos_no()%>" /></td>
			</tr>
			<tr>
				<td>本月水用量:</td>
				<td><input type="TEXT" name="rec_water" size="45"
					value="<%=(recVO == null) ? 10 : recVO.getRec_water()%>" /></td>
			</tr>
			<tr>
				<td>本月電用量:</td>
				<td><input type="TEXT" name="rec_elec" size="45"
					value="<%=(recVO == null) ? 10 : recVO.getRec_elec()%>" /></td>
			</tr>
			<tr>
				<td>本月月份:</td>
				<td><input type="TEXT" name="rec_mon" size="45"
					value="<%=(recVO == null) ? 10 : recVO.getRec_mon()%>" /></td>
			</tr>
			<tr>
				<td>帳單狀態:</td>
				<td><input type="TEXT" name="rec_sta" size="45"
					value="<%=(recVO == null) ? 0 : recVO.getRec_sta()%>" /></td>
			</tr>


			
<!-- 				<tr> -->
<!-- 					<td>租屋狀態:<font color=red><b>*</b></font></td> -->
<!-- 					<td><select size="1" name="apl_status"> -->
<%-- 						<c:forEach var="apl_status" items="${con_aplSvc.getCon_aplStatusAll()}"> --%>
<%-- 							<option value="${apl_status.num}" ${(con_aplVO.apl_status==apl_status.num)? 'selected':'' } >${apl_status.text}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select></td> -->
<!-- 				</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
</html>