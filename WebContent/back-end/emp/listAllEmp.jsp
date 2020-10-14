<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>

<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<!-- 外部js匯入 -->
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
					<a href="index.jsp"><i class="fas fa-home"></i>回首頁</a>
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
			<th>員工編號</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>員工職稱</th>
			<th>姓名</th>
			<th>是否刪除</th>
			<th>照片</th>
			<th>修改</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${employeeVO.emp_no}</td>
				<td>${employeeVO.emp_acc}</td>
				<td>${employeeVO.emp_pwd}</td>
				<td><c:choose>
						<c:when test="${employeeVO.emp_title eq 0}"> 員工</c:when>
						<c:when test="${employeeVO.emp_title eq 1}"> 主管</c:when>
						<c:when test="${employeeVO.emp_title eq 2}"> 經理</c:when>
					</c:choose></td>
				<td>${employeeVO.emp_name}</td>
				<td>${employeeVO.emp_is_delete eq 0? "未刪除":"已刪除"}</td>
				<td><c:if test="${not empty employeeVO.emp_pic}">
						<img src="data:image/png;base64,${employeeVO.emp_pic}"
							width="100px">
					</c:if> <c:if test="${empty employeeVO.emp_pic}">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU"
							width="100px">
					</c:if></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改" class="btn btn-outline-primary">
						<input type="hidden" name="emp_no" value="${employeeVO.emp_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>