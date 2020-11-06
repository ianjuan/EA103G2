
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptlc.model.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<RptlcVO> list = (List<RptlcVO>) request.getAttribute("rptlcVO");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>I-ZU 檢舉房東評價</title>

<!-- 網頁icon -->
<link rel="icon"
	href="<%=request.getContextPath()%>/back-end/images/castle.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/back-end/images/castle.ico"
	type="image/x-icon" />
<!-- 外部js匯入 -->
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css"
	rel="stylesheet">
<!-- wayne匯入 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back-end/rptlc/css/main_back.css"
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

input {
	font-size: 18px;
}
</style>


</head>

<body onload="connect();" onunload="disconnect();">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="<%=request.getContextPath()%>/back-end/index.jsp">
				<div class="sidebar-brand-icon">
					<i class="fas fa-igloo"></i>
				</div>
				<div class="sidebar-brand-text mx-3">I-ZU</div>
			</a>
			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index.jsp"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>儀錶板</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">
			<!-- Heading -->
			<div class="sidebar-heading">管理</div>
			<!--員工-->
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-user"></i> <span>員工</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">管理</h6>
						<a  class="collapse-item" href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">全體員工</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">查詢員工</a>  -->
						<a	class="collapse-item" href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">新增員工</a>
					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwooooooo"
				aria-expanded="true" aria-controls="collapseTwooooooo"> <i
					class="fas fa-user"></i> <span>聊天室</span>
			</a>
				<div id="collapseTwooooooo" class="collapse" aria-labelledby="headingTwooooooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">聊天室</h6>
						<a  class="collapse-item" href="<%=request.getContextPath()%>/back-end/chat.jsp">聊天室</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">查詢員工</a>  -->
					</div>
				</div>
			</li>
			<!--員工-->
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwoo"
				aria-expanded="true" aria-controls="collapseTwoo"> <i
					class="fas fa-user"></i> <span>會員查詢</span>
			</a>
				<div id="collapseTwoo" class="collapse" aria-labelledby="headingTwoo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">查詢</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/member/landlord_main_page.jsp">查詢房東</a> 
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/member/tenant_main_page.jsp">查詢房客</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">新增員工</a> -->
					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwooo"
				aria-expanded="true" aria-controls="collapseTwooo"> <i
					class="fas fa-user"></i> <span>身分驗證</span>
			</a>
				<div id="collapseTwooo" class="collapse" aria-labelledby="headingTwooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">身分驗證</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/vrf/vrf_main_page.jsp">身分驗證</a> 

					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwoooo"
				aria-expanded="true" aria-controls="collapseTwoooo"> <i
					class="fas fa-user"></i> <span>會員檢舉</span>
			</a>
				<div id="collapseTwoooo" class="collapse" aria-labelledby="headingTwoooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">檢舉</h6>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rptl/rptl_main_page.jsp">檢舉房東</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rptt/rptt_main_page.jsp">檢舉房客</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rpth/rpth_main_page.jsp">檢舉房屋</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rptlc/rptlc_main_page.jsp">檢舉房東評價</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rpttc/rpttc_main_page.jsp">檢舉房客評價</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rpthc/rpthc_main_page.jsp">檢舉房屋評價</a>
						<a class="collapse-item"
							href="<%=request.getContextPath()%>/back-end/rptr/rptr_main_page.jsp">檢舉修繕</a>
<!-- 						<a	class="collapse-item" href="javascript:void(0)">新增員工</a> -->
					</div>
				</div>
			<!--業務流程 -->
				<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseSix"
				aria-expanded="true" aria-controls="collapseSix"> <i
					class="fas fa-user"></i> <span>業務流程 </span>
			</a>
				<div id="collapseSix" class="collapse" aria-labelledby="headingSix"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">管理</h6>
<!-- 						<a class="collapse-item" href="javascript:void(0)">帳務管理</a>  -->
						<a	class="collapse-item" href="<%=request.getContextPath()%>/back-end/house_manage/all_house.jsp">房屋管理</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">預約管理</a> -->
<!-- 						<a class="collapse-item" href="javascript:void(0)">訂單管理</a>  -->
<!-- 						<a	class="collapse-item" href="javascript:void(0)">修繕管理</a>  -->
<!-- 							<a	class="collapse-item" href="javascript:void(0)">檢舉管理</a> 				 -->
					</div>
				</div>
			</li>	
			<!-- Divider -->

			<!-- Heading -->
			<div class="sidebar-heading">
				<!-- 待補 -->
			</div>
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">
			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>
		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>
					<!-- Topbar Navbar -->
					<!-- auto置右 -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
								<span id="alert_count" class="badge badge-danger badge-counter"></span>
						</a> <!-- Dropdown - Alerts  fas fa-file-alt text-white fas fa-donate text-white  fas fa-exclamation-triangle text-white-->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 id="bell_alert" class="dropdown-header">通知中心</h6>
								<!--                                         <a class="dropdown-item d-flex align-items-center" href="#"> -->
								<!--                                             <div class="mr-3"> -->
								<!--                                                 <div class="icon-circle bg-primary"> -->
								<!--                                                     <i class="fas fa-file-alt text-white"></i> -->
								<!--                                                 </div> -->
								<!--                                             </div> -->
								<!--                                             <div> -->
								<!--                                                 <div class="small text-gray-500">December 12, 2019</div> -->
								<!--                                                 <span class="font-weight-bold">A new monthly report is ready to download!</span> -->
								<!--                                             </div> -->
								<!--                                         </a> -->
								<a class="dropdown-item text-center small text-gray-500"
									href="#">展現全部通知</a>
							</div></li>
						<!-- 						Dropdown - Alerts -->

						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2v23PnY2C d-none d-lg-inline text-gray-600 small"><c:out
										value="${empVO.emp_name}" default="請重新登入"></c:out></span> <c:if
									test="${empVO.emp_pic != null}">
									<img class="img-profile rounded-circle"
										src='data:image/png;base64,<c:forEach var="employeeVO" items="${empSvc.getAll()}">
										<c:if test="${employeeVO.emp_no == empVO.emp_no }">${employeeVO.emp_pic }</c:if>
										</c:forEach>'>
								</c:if>

						</a> <!-- 								 Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item"
									href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Display&emp_no=${empVO.emp_no}">
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 個人資料
								</a> <a class="dropdown-item"
									href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Update&emp_no=${empVO.emp_no}">
									<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 設定
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> 活動紀錄
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item"
									href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=logout">
									<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									登出
								</a>
							</div> <!-- 									Dropdown - User Information -->
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800"></h1>
					<!-- -- -- -- -- -- -- -- --Content-- -- -- -- -- -- -- -- -- -- -->
					<div id="ajax_result">
						<div class="col-xl-3 col-md-6 mb-4">
							<ul>

							</ul>

						</div>
						<div class="row">
							<div class="container-fluid">
								<div class="card shadow mb-4">
									<div class="card-header py-3">
										<div class="row">
											<div class="col-md">
												<h4 class="m-0 font-weight-bold text-primary">檢舉房東評價</h4>
											</div>

											<div class="col-md">
												<div class="float-right">
													<form METHOD="post" ACTION="RptlcServlet">
														<h5>
															搜尋: <input type="text" size="27" name="Number"
																placeholder="輸入檢舉/ 評價/ 房東/ 員工編號"> <input
																type="hidden" name="action" value="get_want_all_display">
															<input type="submit"
																style="position: absolute; left: -9999px; width: 1px; height: 1px;"
																tabindex="-1" />
														</h5>
													</form>
												</div>
												<div class="float-right">
													<a href="rptlc_second_page.jsp">
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
														<th>房東評價編號</th>
														<th>房東編號</th>
														<th width="10%">檢舉時間</th>
														<th>員工編號</th>
														<th>處理狀態</th>
														<th>處理結果</th>
														<th width="10%">檢舉完成時間</th>
														<th>內容註記</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
														<th>檢舉編號</th>
														<th>房東評價編號</th>
														<th>房東編號</th>
														<th width="10%">檢舉時間</th>
														<th>員工編號</th>
														<th>處理狀態</th>
														<th>處理結果</th>
														<th width="10%">檢舉完成時間</th>
														<th>內容註記</th>
													</tr>
												</tfoot>
												<tbody>
													<%@ include file="page1.file"%>
													<c:forEach var="rptlcVO" items="${list}"
														begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
														<tr>
															<td>${rptlcVO.rptlc_no}</td>
															<td>${rptlcVO.lcm_no}</td>
															<td>${rptlcVO.lld_no}</td>
															<td>${rptlcVO.rptlc_time}</td>
															<td>${rptlcVO.emp_no}</td>
															<c:choose>
																<c:when test="${rptlcVO.rptlc_status==0}">
																	<td>未處理</td>
																</c:when>
																<c:otherwise>
																	<td>已處理</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${rptlcVO.rptlc_result==1}">
																	<td><span class="badge badge-pill badge-success"
																		style="font-size: 15px; padding: 4px;"> 通過</span></td>
																</c:when>
																<c:when test="${rptlcVO.rptlc_result==2}">
																	<td><span class="badge badge-pill badge-danger"
																		style="font-size: 15px; padding: 4x;">未通過</span></td>
																</c:when>
																<c:otherwise>
																	<td><span class="badge badge-pill badge-secondary"
																		style="font-size: 15px; padding: 4px;">審核中</span></td>
																</c:otherwise>
															</c:choose>
															<td>${rptlcVO.rptlc_done_time}</td>
															<td>
																<button class="check" data-toggle="modal"
																	data-target="#${rptlcVO.rptlc_no}">查看詳情</button>
															</td>
														</tr>
														<div class="modal fade" id="${rptlcVO.rptlc_no}"
															tabindex="-1" role="dialog">
															<div class="modal-dialog">
																<div class="modal-content">
																	<div class="modal-header">
																		<div class="modal-body">
																			<form action="RptlcServlet" method="post">
																				<label for="reason">檢舉原因:</label>
																				<textarea class="reason" name="rptlc_content"
																					readonly>${rptlcVO.rptlc_content}</textarea>
																				<div class="form-group">
																					<label for="note">結果註記:</label>
																					<textarea id="note" name="rptlc_note" readonly>${rptlcVO.rptlc_note}</textarea>
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
						</div>


					</div>
				</div>

			</div>
		</div>
	</div>



	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/chart.js/Chart.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/chart-pie-demo.js"></script>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
			
	var now =new Date();
	var MyPoint = "/NotifyServlet/${empVO.emp_no}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket;
	var alert_content;
	var bell_html;


	function connect() {
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
		};
		
		webSocket.onmessage = function(event) {
			var alert_count=0;
			var jsonObj = JSON.parse(event.data);
			if(jsonObj.length>=1){
				$('#alert_count').text(jsonObj.length);
				alert_count==jsonObj.length;

			for(var i=0;i<jsonObj.length ;i++){
				alert_content = JSON.parse(jsonObj[i]).content;
				alert_title =JSON.parse(jsonObj[i]).title;
				alert_time =new Date(JSON.parse(jsonObj[i]).time);
				alert_day = (alert_time.getMonth()+1)+"月"+alert_time.getDate()+"日";
				bell_html=`<a class="dropdown-item d-flex align-items-center" href="#">
				    <div class="mr-3">
				    <div class="icon-circle bg-primary">
				        <i class="fas fa-file-alt text-white"></i>
				    </div>
					</div>
					<div>
				    <div class="small text-gray-500">${"${alert_day}"}</div>
				    <span class="font-weight-bold">${"${alert_content}"}</span>
				</div></a>`;

				$('#bell_alert').after(bell_html);
			}
			}
		
			
				


		};

		webSocket.onclose = function(event) {
		};
	}


	function disconnect() {
		webSocket.close();

	}

</script>
</body>

</html>
