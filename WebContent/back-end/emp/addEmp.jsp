<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="<%=request.getContextPath()%>/back-end/css/wu-teacher.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<style>
body {
	margin: 10;
}
</style>
</head>

<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>全體員工資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/emp/index.jsp"><i class="fas fa-home"></i>回首頁</a>
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
		ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
		name="form1">
		<table>
			<tr>
				<td>員工帳號:</td>
				<td><input type="TEXT" name="emp_acc" size="45"
					value="<%=(empVO == null) ? "" : empVO.getEmp_acc()%>" /></td>
			</tr>
			<tr>
				<td>員工密碼:</td>
				<td><input type="TEXT" name="emp_pwd" size="45"
					value="<%=(empVO == null) ? "" : empVO.getEmp_pwd()%>" /></td>
			</tr>
			<tr>
				<td>職位:</td>
				<td><input type="TEXT" name="emp_title" size="45"
					value="<%=(empVO == null) ? "0:員工 1:主管 2:經理" : empVO.getEmp_title()%>" /></td>
			</tr>
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="emp_name" size="45"
					value="<%=(empVO == null) ? "" : empVO.getEmp_name()%>" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="action" value="insert">
				</td>
				<td><input type="submit" value="送出新增"
					class="btn btn-outline-primary"></td>
			</tr>
		</table>
	</FORM>

</body>
</html>