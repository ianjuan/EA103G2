<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />

<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="<%=request.getContextPath()%>/back-end/css/wu-teacher.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/emp/index.jsp"><i
						class="fas fa-home"></i>回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>員工職稱</th>
			<th>姓名</th>
			<th>是否刪除</th>
			<th>照片</th>

		</tr>
		<tr>
			<td><%=empVO.getEmp_no()%></td>
			<td><%=empVO.getEmp_acc()%></td>
			<td><%=empVO.getEmp_pwd()%></td>
			<td>
				<%
					if (empVO.getEmp_title() == 0) {
						out.print("員工");
					} else if (empVO.getEmp_title() == 1) {
						out.print("主管");
					} else if (empVO.getEmp_title() == 2) {
						out.print("經理");
					}
				%>
			</td>
			<td><%=empVO.getEmp_name()%></td>
			<td><%=empVO.getEmp_is_delete() == 0 ? "未刪除":"已刪除"%></td>
			<td>
				<%
					if (empVO.getEmp_pic()!=null) {
						
				%> <img
				src="data:image/png;base64,<%=empVO.getEmp_pic()%>" width="100px">
				<%
					}else{
				%> 
				<img id="blah" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU" width="100px">
				<%
					}
				%>
			</td>

		</tr>
		<tr><td style="text-align:left">
		<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunctionService" />
	擁有的權限<br>
		<c:forEach var="rigVO" items="${rigVO}">
		<c:forEach var="funVO" items="${funSvc.all}">
    <c:if test="${funVO.fun_no == rigVO.fun_no }">【${rigVO.fun_no}】 ${funVO.fun_name}<br>
    </c:if>
		</c:forEach>
	</c:forEach>
	</td>
		</tr>
	</table>
	
</body>
</html>