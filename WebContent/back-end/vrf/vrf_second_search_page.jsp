
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<TntVO> list = (List<TntVO>) request.getAttribute("TntVO");
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

<title>身分驗證</title>

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
	href="${pageContext.request.contextPath}/back-end/vrf/css//main_vrf_back.css"
	type="text/css">
<style>
.toggle.ios, .toggle-on.ios, .toggle-off.ios {
	border-radius: 20px;
}

.toggle.ios .toggle-handle {
	border-radius: 20px;
}

.pic {
	width: 420px;
	height: 250px;
	margin: 10px;
}
</style>


</head>

<body id="page-top">


	<!-- DataTales Example -->
	<div class="container-fluid">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<div class="row justify-content-between">
					<div class="col-8">
						<h4 class="m-0 font-weight-bold text-primary">身分驗證</h4>
					</div>
					<div class="col-1">
						<a href="vrf_second_page.jsp">
							<button class="checkall">查看全部</button>
						</a>
					</div>
					<div class="col-3">
						<form METHOD="post" ACTION="RpttServlet">
							<h4>
								搜尋: <input type="text" size="32" name="Number"
									placeholder="輸入會員編號/姓名/電話/信箱/身份證字號"> <input
									type="hidden" name="action" value="get_want_vrf_display">
								<input type="submit"
									style="position: absolute; left: -9999px; width: 1px; height: 1px;"
									tabindex="-1" />
							</h4>
						</form>
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
								<th>會員編號</th>
								<th>會員姓名</th>
								<th>會員生日</th>
								<th>會員手機</th>
								<th>會員信箱</th>
								<th>上傳時間</th>
								<th>驗證結果</th>
								<th>詳情瀏覽</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>會員編號</th>
								<th>會員姓名</th>
								<th>會員生日</th>
								<th>會員手機</th>
								<th>會員信箱</th>
								<th>上傳時間</th>
								<th>驗證結果</th>
								<th>詳情瀏覽</th>
							</tr>
						</tfoot>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="tntVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${tntVO.tnt_no}</td>
									<td>${tntVO.tnt_name}</td>
									<td>${tntVO.tnt_birth}</td>
									<td>${tntVO.tnt_mobile}</td>
									<td>${tntVO.tnt_email}</td>
									<td>${tntVO.tnt_id_uploadtime}</td>
									<c:choose>
										<c:when test="${tntVO.tnt_id_result==1}">
											<td><span class="badge badge-pill badge-success"
												style="font-size: 15px; padding: 4px;">通過</span></td>
										</c:when>
										<c:when test="${tntVO.tnt_id_result==2}">
											<td><span class="badge badge-pill badge-danger"
												style="font-size: 15px; padding: 4x;">不通過</span></td>
										</c:when>
										<c:otherwise>
											<td>審核中</td>
										</c:otherwise>
									</c:choose>
									<td>
										<button class="check" data-toggle="modal"
											data-target="#${tntVO.tnt_no}">查看詳情</button>
									</td>
								</tr>
								<div class="modal fade" id="${tntVO.tnt_no}" tabindex="-1"
									role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<div class="modal-body">
													<img
														src="<%=request.getContextPath()%>/ImgReader_vrf?id=${tntVO.tnt_no}&type=front"
														class="pic" /> <img
														src="<%=request.getContextPath()%>/ImgReader_vrf?id=${tntVO.tnt_no}&type=back"
														class="pic" /> <img
														src="<%=request.getContextPath()%>/ImgReader_vrf?id=${tntVO.tnt_no}&type=second"
														class="pic" /><br> <br>
													<div style="margin: 0; outline: 0; color: #8a97a0;">
														<label for="name">驗證員工:</label> ${tntVO.emp_no} <br>
														<label for="name">驗證時間:</label> ${tntVO.tnt_id_vrftime} <br>
														<label for="name">退件原因:</label> ${tntVO.tnt_id_disapprove}
														<br>
													</div>
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
	<script>
		$(function() {
			$('#showornot').change(function() {
				document.getElementById("#show").submit();
			})
		})
	</script>
</body>

</html>
