
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rpth.model.*"%>
<%@ page import="java.util.*"%>

<%
	RpthService rpthSvc = new RpthService();
	List<RpthVO> rpthVO1 = rpthSvc.getRpth("0");
	pageContext.setAttribute("rpthVO", rpthVO1);
	List<RpthVO> rpthVO = (List<RpthVO>) pageContext.getAttribute("rpthVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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


<!-- Custom fonts for this template -->


<link
	href="${pageContext.request.contextPath}/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="${pageContext.request.contextPath}/back-end/vendor/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
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

button.pass {
	margin-top: 15px;
	font-size: 16px;
	font-weight: 600;
	text-transform: uppercase;
	padding: 15px 26px;
	color: #FFF;
	background-color: #FB6565;
	border-radius: 4px;
	border: 1px solid #FB6565;
	text-align: center;
	width: 100%;
}
</style>


</head>

<body id="page-top">

	<!-- Begin Page Content -->
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
										name="action" value="get_want_display"> <input
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
								<th>檢舉內容</th>
								<th>員工編號</th>
								<th>處理結果</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>檢舉編號</th>
								<th>房屋編號</th>
								<th>房客編號</th>
								<th>檢舉時間</th>
								<th>檢舉內容</th>
								<th>員工編號</th>
								<th>處理結果</th>
							</tr>
						</tfoot>
						<tbody>
							<%
								for (RpthVO rpthvo : rpthVO) {
							%>
							<tr>
								<td><%=rpthvo.getRpth_no()%></td>
								<td><%=rpthvo.getHos_no()%></td>
								<td><%=rpthvo.getTnt_no()%></td>
								<td><%=rpthvo.getRpth_time()%></td>
								<td><%=rpthvo.getRpth_content()%></td>
								<%
									if (rpthvo.getEmp_no() != null) {
								%>
								<td><%=rpthvo.getEmp_no()%></td>
								<%
									} else {
								%><td><form action="RpthServlet" method="post">
										<input type="hidden" name="action" value="update_employee">
										<input type="hidden" name="emp_no"
											value="<%=pageContext.getAttribute("emp_no")%>"> <input
											type="hidden" name="rpth_no" value="<%=rpthvo.getRpth_no()%>">
										<input type="hidden" name="rpth_status" value="1">
										<button type="submit" class="take">接受</button>
									</form></td>
								<%
									}
								%>
								<%
									if (rpthvo.getRpth_result() == 0) {
								%>
								<td>
									<button class="check" data-toggle="modal"
										data-target="#<%=rpthvo.getRpth_no()%>">查看詳情</button>
								</td>
								<%
									}
								%>
							</tr>
							<!-- Modal HTML -->
							<div class="modal fade" id="<%=rpthvo.getRpth_no()%>"
								tabindex="-1" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<div class="modal-body1">
												<%
													if (pageContext.getAttribute("emp_no").equals(rpthvo.getEmp_no())) {
												%>
												<form action="RpthServlet" method="post" name="detail"
													id="detail">
													<input type="hidden" name="rpth_no"
														value="<%=rpthvo.getRpth_no()%>"> <input
														type="hidden" name="hos_no"
														value="<%=rpthvo.getHos_no()%>"><label
														for="reason1">檢舉原因:</label>
													<textarea class="reason1" name="rpth_content" readonly><%=rpthvo.getRpth_content()%></textarea>
													<div class="form-group">
														<label for="note">結果註記:</label>
														<textarea id="note" name="rpth_note"><%=rpthvo.getRpth_note()%></textarea>
													</div>

													<button type="submit" class="pass" name="action"
														value="pass">通過</button>
													<button type="submit" class="fail" name="action"
														value="fail">不通過</button>
													<button type="submit" class="send" name="action"
														value="assign_employee">指派</button>
													<button type="submit" class="save" name="action"
														value="save_note">儲存</button>
													<select class="emp_no" name="emp_no" size="1">
														<option value="" disabled selected>---請選擇將指派的同仁---</option>
														<option value="EMP000021">EMP000021</option>
														<option value="EMP000022">EMP000022</option>
														<option value="EMP000023">EMP000023</option>
														<option value="EMP000024">EMP000024</option>
														<option value="EMP000025">EMP000025</option>
													</select>

												</form>
												<%
													} else {
												%><form action="RpthServlet" method="post" name="detail"
													id="detail">
													<input type="hidden" name="rpth_no"
														value="<%=rpthvo.getRpth_no()%>"> <input
														type="hidden" name="hos_no"
														value="<%=rpthvo.getHos_no()%>"><label
														for="reason1">檢舉原因:</label>
													<textarea class="reason1" name="rpth_content" readonly
														style="width: 460px;"><%=rpthvo.getRpth_content()%></textarea>
													<div class="form-group">
														<label for="note">結果註記:</label>
														<textarea id="note" name="rpth_note" readonly
															style="width: 460px;"><%=rpthvo.getRpth_note()%></textarea>
													</div>
												</form>
												<%
													}
												%>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%
								}
							%>
						</tbody>
					</table>
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

</body>

</html>
