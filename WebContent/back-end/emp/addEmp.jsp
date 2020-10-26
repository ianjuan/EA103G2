<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunctionService" />

<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<%-- <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<style>
form {
text-align:-webkit-center;
}
.btn{
vertical-align:baseline;
}
</style>
</head>

<body >

        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">新增員工資料</h6>
            </div>
            <div class="card-body ">
              <div class="table-responsive">


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
		name="form1" >
		<table>
			<tr>
				<td><input type="TEXT" name="emp_acc" class="form-control form-control-user" placeholder="員工帳號"
					value="<%=(empVO == null) ? "" : empVO.getEmp_acc()%>" /></td>
			</tr>
			<tr>
				<td><input type="password" name="emp_pwd" class="form-control form-control-user" placeholder="員工密碼"
					value="<%=(empVO == null) ? "" : empVO.getEmp_pwd()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>職位:</td> -->
				<td><input type="radio" name="emp_title" 
					value="0" />員工
					<input type="radio" name="emp_title" 
					value="1" />主管
					<input type="radio" name="emp_title" 
					value="2" />經理</td>
			</tr>
			<tr>
				<td><input type="TEXT" name="emp_name" class="form-control form-control-user" placeholder="員工姓名"
					value="<%=(empVO == null) ? "" : empVO.getEmp_name()%>" /></td>
			</tr>
				<tr>
<!-- 				<td>員工權限:</td> -->
				<td>
					<c:forEach var="fun_list" items="${funSvc.all}">
											<br>
						<input type="checkbox" id="${fun_list.fun_no}" name="fun_no" value="${fun_list.fun_no}" data-toggle="toggle">
						<label for="${fun_list.fun_no}">${fun_list.fun_name}</label>
					</c:forEach>
	</td>
			</tr>
			<tr>
				<td><input type="hidden" name="action" value="insert">
				</td>
				<td><input type="submit" value="送出新增"
					class="btn btn-outline-primary"></td>
			</tr>
		</table>
	</FORM>
</div>
            </div>
          </div>

        </div>

</body>
</html>