<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<jsp:useBean id="newsSvc" scope="page" class="com.news.model.NewsService" />

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Custom fonts for this template -->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<style>
.table td{
	    padding: .2rem;
}
</style>
<body  onload="connect1();" onunload="disconnect1();">

			     <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">最新公告</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
          <input type="text" id="announce" value="" placeholder="請輸入公告內容">
          <button id="btn_send" onclick="sendMessage1();" class="btn btn-outline-primary">點我發送公告</button>
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="1000">
                  <thead>
                    <tr>
                    	<th>職位</th>
                    	<th>發布者</th>
                 	    <th>公告</th>
						<th>日期</th>
						<th>刪除</th>
                    </tr>
                  </thead>
                  <tbody id="tbody">
                  <c:forEach var="newsVO" items="${newsSvc.all}">
                <tr>
                <td><c:choose>
						<c:when test="${newsVO.emp_title eq 0}"> 員工</c:when>
						<c:when test="${newsVO.emp_title eq 1}"> 主管</c:when>
						<c:when test="${newsVO.emp_title eq 2}"> 經理</c:when>
					</c:choose></td>
                <td>${newsVO.emp_name}</td>
                <td>${newsVO.new_content}</td>
				<td>${newsVO.new_date}</td>
				<td><input type="text" value="刪除" class="btn btn-outline-primary"></td>
				</tr>
                   </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
  <!-- Bootstrap core JavaScript-->
<%--   <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script> --%>
<%--   <script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script> --%>
  <script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
			
</body>

</html>
