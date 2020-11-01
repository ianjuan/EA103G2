
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rpth.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<RpthVO> list = (List<RpthVO>) request.getAttribute("rpthVO");
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("emp_no", "EMP000005");
%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>檢舉房屋</title>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="${pageContext.request.contextPath}/back-end/vendor/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back-end/rpth/css/main_back.css"
	type="text/css">
<style>
button.checkall {
	font-size: 15px;
	font-weight: 600;
	color: #8a97a0;
	background-color: #fff;
	border-radius: 2px;
	border: 1px solid #8a97a0;
	text-align: center;
	margin-right: 15px;
}
</style>


</head>

<body id="page-top">
	<!-- DataTales Example -->
	<div class="container-fluid">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<div class="row">
					<div class="col-md">
						<h4 class="m-0 font-weight-bold text-primary">檢舉房屋</h4>
					</div>

					<div class="col-md">
						<div class="float-right">
							<form METHOD="post" ACTION="RpthServlet">
								<h4>
									搜尋: <input type="text" size="27" name="Number"
										placeholder="輸入檢舉/ 房屋/ 房客/ 員工編號"> <input type="hidden"
										name="action" value="get_want_all_display"> <input
										type="submit"
										style="position: absolute; left: -9999px; width: 1px; height: 1px;"
										tabindex="-1" />
								</h4>
							</form>
						</div>
						<div class="float-right">
							<a href="rpth_second_page.jsp">
								<button class="checkall">查看全部</button>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div>
				<c:if test="${not empty errorMsgs}">
					<p></p>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>檢舉編號</th>
								<th>房屋編號</th>
								<th>房客編號</th>
								<th>檢舉時間</th>
								<th>員工編號</th>
								<th>處理狀態</th>
								<th>處理結果</th>
								<th>檢舉完成時間</th>
								<th>內容註記</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>檢舉編號</th>
								<th>房屋編號</th>
								<th>房客編號</th>
								<th>檢舉時間</th>
								<th>員工編號</th>
								<th>處理狀態</th>
								<th>處理結果</th>
								<th>檢舉完成時間</th>
								<th>內容註記</th>
							</tr>
						</tfoot>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="rpthVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${rpthVO.rpth_no}</td>
									<td>${rpthVO.hos_no}</td>
									<td>${rpthVO.tnt_no}</td>
									<td>${rpthVO.rpth_time}</td>
									<td>${rpthVO.emp_no}</td>
									<c:choose>
										<c:when test="${rpthVO.rpth_status==0}">
											<td>未處理</td>
										</c:when>
										<c:otherwise>
											<td>已處理</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${rpthVO.rpth_result==1}">
											<td><span class="badge badge-pill badge-success"
												style="font-size: 15px; padding: 4px;"> 通過</span></td>
										</c:when>
										<c:when test="${rpthVO.rpth_result==2}">
											<td><span class="badge badge-pill badge-danger"
												style="font-size: 15px; padding: 4x;">未通過</span></td>
										</c:when>
										<c:otherwise>
											<td><span class="badge badge-pill badge-secondary"
												style="font-size: 15px; padding: 4px;">審核中</span></td>
										</c:otherwise>
									</c:choose>
									<td>${rpthVO.rpth_done_time}</td>
									<td>
										<button class="check" data-toggle="modal"
											data-target="#${rpthVO.rpth_no}">查看詳情</button>
									</td>
								</tr>
								<div class="modal fade" id="${rpthVO.rpth_no}" tabindex="-1"
									role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<div class="modal-body">
													<form action="RpthServlet" method="post">
														<label for="reason">檢舉原因:</label>
														<textarea class="reason" name="rpth_content" readonly>${rpthVO.rpth_content}</textarea>
														<div class="form-group">
															<label for="note">結果註記:</label>
															<textarea id="note" name="rpth_note" readonly>${rpthVO.rpth_note}</textarea>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
			</div>
		</div>
	</div>



	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/back-end/css/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/back-end/css/js/demo/datatables-demo.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
</body>

</html>
