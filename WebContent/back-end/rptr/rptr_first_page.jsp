
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptr.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<RptrVO> rptrVO = (List<RptrVO>) request.getAttribute("rptrVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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

<title>檢舉修繕</title>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="${pageContext.request.contextPath}/back-end/vendor/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back-end/rptr/css/main_back.css"
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
						<h4 class="m-0 font-weight-bold text-primary">檢舉修繕</h4>
					</div>
					<div class="col-md">
						<div class="float-right">
							<form METHOD="post" ACTION="RptrServlet">
								<h4>
									搜尋: <input type="text" size="27" name="Number"
										placeholder="輸入檢舉/ 修繕/ 房客/ 員工編號"> <input type="hidden"
										name="action" value="get_want_display"> <input
										type="submit"
										style="position: absolute; left: -9999px; width: 1px; height: 1px;"
										tabindex="-1" />
								</h4>
							</form>
						</div>
						<div class="float-right">
							<a href="rptr_second_page.jsp">
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
								<th>修繕編號</th>
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
								<th>修繕編號</th>
								<th>房客編號</th>
								<th>檢舉時間</th>
								<th>檢舉內容</th>
								<th>員工編號</th>
								<th>處理結果</th>
							</tr>
						</tfoot>
						<tbody>
							<%
								for (RptrVO rptrvo : rptrVO) {
							%>
							<%
								if (rptrvo.getRptr_result()==0) {
							%>
							<tr>
								<td><%=rptrvo.getRptr_no()%></td>
								<td><%=rptrvo.getRep_no()%></td>
								<td><%=rptrvo.getTnt_no()%></td>
								<td><%=rptrvo.getRptr_time()%></td>
								<td><%=rptrvo.getRptr_content()%></td>

								<%
									if (rptrvo.getEmp_no() != null) {
								%>
								<td><%=rptrvo.getEmp_no()%></td>
								<%
									} else {
								%><td><form action="RptrServlet" method="post">
										<input type="hidden" name="action" value="update_employee">
										<input type="hidden" name="emp_no"
											value="<%=pageContext.getAttribute("emp_no")%>"> <input
											type="hidden" name="rptr_no" value="<%=rptrvo.getRptr_no()%>">
										<input type="hidden" name="rptr_status" value="1">
										<button type="submit" class="take">接受</button>
									</form></td>
								<%
									}
								%>

								<%
									if (rptrvo.getRptr_result() == 0) {
								%>
								<td>
									<button class="check" data-toggle="modal"
										data-target="#<%=rptrvo.getRptr_no()%>">查看詳情</button>
								</td>
								<%
									}
								%>
							</tr>
							<%
								}
							%>
							<!-- Modal HTML -->
							<div class="modal fade" id="<%=rptrvo.getRptr_no()%>"
								tabindex="-1" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<div class="modal-body1">
												<%
													if (pageContext.getAttribute("emp_no").equals(rptrvo.getEmp_no())) {
												%>
												<form action="RptrServlet" method="post" name="detail"
													id="detail">
													<input type="hidden" name="rptr_no"
														value="<%=rptrvo.getRptr_no()%>"> <input
														type="hidden" name="tnt_no"
														value="<%=rptrvo.getTnt_no()%>"><label
														for="reason1">檢舉原因:</label>
													<textarea class="reason1" name="rptr_content" readonly><%=rptrvo.getRptr_content()%></textarea>
													<div class="form-group">
														<label for="note">結果註記:</label>
														<textarea id="note" name="rptr_note"><%=rptrvo.getRptr_note()%></textarea>
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
												%><form action="RptrServlet" method="post" name="detail"
													id="detail">
													<input type="hidden" name="rptr_no"
														value="<%=rptrvo.getRptr_no()%>"> <input
														type="hidden" name="hos_no"
														value="<%=rptrvo.getRep_no()%>"><label
														for="reason1">檢舉原因:</label>
													<textarea class="reason1" name="rptr_content" readonly
														style="width: 460px;"><%=rptrvo.getRptr_content()%></textarea>
													<div class="form-group">
														<label for="note">結果註記:</label>
														<textarea id="note" name="rptr_note" readonly
															style="width: 460px;"><%=rptrvo.getRptr_note()%></textarea>
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
