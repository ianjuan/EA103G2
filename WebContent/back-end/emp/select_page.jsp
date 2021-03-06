<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">查詢員工資料</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/emp/index.jsp"><i
						class="fas fa-home"></i>回首頁</a>
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

	<ul>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do">
				<b>輸入員工編號 (如EMP000001):</b> <input type="text" name="emp_no"
					value="EMP000001"> <input type="hidden" name="action"
					value="getOne_For_Display"> <input type="submit" value="送出"
					class="btn btn-outline-primary">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.emp.model.EmployeeService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do">
				<b>選擇員工編號:</b> <select size="1" name="emp_no">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.emp_no}">${empVO.emp_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出" class="btn btn-outline-primary">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do">
				<b>選擇員工姓名:</b> <select size="1" name="emp_no">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.emp_no}">${empVO.emp_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出" class="btn btn-outline-primary">
			</FORM>
		</li>
	</ul>
	</div>
            </div>
          </div>

        </div>
</body>
</html>