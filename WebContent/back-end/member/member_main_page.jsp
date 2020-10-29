
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>

<%
	TntService tntSvc = new TntService();
	TntVO tntVO = tntSvc.getOneTnt("TNT000002");
	pageContext.setAttribute("tntVO", tntVO);
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

<title>會員查詢</title>


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
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="main_member_back.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<style>
th.colum1 {
	height: auto;
	text-align: right;
	font-size: 15px;
}

.table-responsive {
	overflow-x: visible;
}

.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

/* Hide default HTML checkbox */
.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

/* The slider */
.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
/*--------------------------------*/
.center1 {
	width: 150px;
	margin: 10px auto 20px auto;
}
</style>



</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="index.html">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					SB Admin <sup>2</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link" href="index.html">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Interface</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>Components</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Custom Components:</h6>
						<a class="collapse-item" href="buttons.html">Buttons</a> <a
							class="collapse-item" href="cards.html">Cards</a>
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-wrench"></i> <span>Utilities</span>
			</a>
				<div id="collapseUtilities" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Custom Utilities:</h6>
						<a class="collapse-item" href="utilities-color.html">Colors</a> <a
							class="collapse-item" href="utilities-border.html">Borders</a> <a
							class="collapse-item" href="utilities-animation.html">Animations</a>
						<a class="collapse-item" href="utilities-other.html">Other</a>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Addons</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Login Screens:</h6>
						<a class="collapse-item" href="login.html">Login</a> <a
							class="collapse-item" href="register.html">Register</a> <a
							class="collapse-item" href="forgot-password.html">Forgot
							Password</a>
						<div class="collapse-divider"></div>
						<h6 class="collapse-header">Other Pages:</h6>
						<a class="collapse-item" href="404.html">404 Page</a> <a
							class="collapse-item" href="blank.html">Blank Page</a>
					</div>
				</div></li>
			<form METHOD="post" ACTION="RpttServlet" name="clickTable">
				<!-- Nav Item - Charts -->
				<li class="nav-item"><a class="nav-link" href="charts.html">
						<i class="fas fa-fw fa-chart-area"></i> <span>Charts</span>
				</a></li>

				<!-- Nav Item - Tables -->
				<li class="nav-item active"><a class="nav-link" href="#"
					onclick="del()"> <i class="fas fa-fw fa-table"></i>
						<form METHOD="post" ACTION="RpttServlet" name="clickTable">
							<input type="hidden" name="action" value="get_member_main_page">
							<span onclick="clickTable.submit()">會員查詢 </span>
						</form></a></li>

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
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="Search for..." aria-label="Search"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
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
								<span class="badge badge-danger badge-counter">3+</span>
						</a> <!-- Dropdown - Alerts -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 class="dropdown-header">Alerts Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-primary">
											<i class="fas fa-file-alt text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 12, 2019</div>
										<span class="font-weight-bold">A new monthly report is
											ready to download!</span>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-success">
											<i class="fas fa-donate text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 7, 2019</div>
										$290.29 has been deposited into your account!
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-warning">
											<i class="fas fa-exclamation-triangle text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 2, 2019</div>
										Spending Alert: We've noticed unusually high spending for your
										account.
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Show All Alerts</a>
							</div></li>

						<!-- Nav Item - Messages -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
								<!-- Counter - Messages --> <span
								class="badge badge-danger badge-counter">7</span>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="messagesDropdown">
								<h6 class="dropdown-header">Message Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
										<div class="status-indicator bg-success"></div>
									</div>
									<div class="font-weight-bold">
										<div class="text-truncate">Hi there! I am wondering if
											you can help me with a problem I've been having.</div>
										<div class="small text-gray-500">Emily Fowler · 58m</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">
										<div class="status-indicator"></div>
									</div>
									<div>
										<div class="text-truncate">I have the photos that you
											ordered last month, how would you like them sent to you?</div>
										<div class="small text-gray-500">Jae Chun · 1d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">
										<div class="status-indicator bg-warning"></div>
									</div>
									<div>
										<div class="text-truncate">Last month's report looks
											great, I am very happy with the progress so far, keep up the
											good work!</div>
										<div class="small text-gray-500">Morgan Alvarez · 2d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
										<div class="status-indicator bg-success"></div>
									</div>
									<div>
										<div class="text-truncate">Am I a good boy? The reason I
											ask is because someone told me that people say this to all
											dogs, even if they aren't good...</div>
										<div class="small text-gray-500">Chicken the Dog · 2w</div>
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Read More Messages</a>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie
									Luna</span> <img class="img-profile rounded-circle"
								src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="row justify-content-between">
								<div class="col-10">
									<h4 class="m-0 font-weight-bold text-primary">會員查詢</h4>
								</div>
								<div class="col-2">
									<form METHOD="post" ACTION="RpttServlet">
										<h4>
											搜尋: <input type="text" size="20" name="Number"
												placeholder="輸入會員編號/身份證字號"> <input type="hidden"
												name="action" value="get_want_vrf_display"> <input
												type="submit"
												style="position: absolute; left: -9999px; width: 1px; height: 1px;"
												tabindex="-1" />
										</h4>
									</form>
								</div>
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable">

									<tbody>
									<thead>
										<tr>
											<th>基本資料</th>
											<td style="border: 0"><button class="alter">修改</button></td>

										</tr>
									</thead>
									<tr>
										<th class="colum1" width="5%">姓名</th>
										<td width="15%">${tntVO.tnt_name}</td>
										<th class="colum1" width="5%">會員編號</th>
										<td width="15%">${tntVO.tnt_no}</td>
										<th class="colum1" width="5%">信箱</th>
										<td width="15%">${tntVO.tnt_email}</td>
										<th class="colum1" width="5%">會員帳號</th>
										<td width="15%">${tntVO.tnt_acc}</td>

									</tr>
									<tr>
										<th class="colum1" width="5%">身分證字號</th>
										<td width="15%">${tntVO.tnt_id}</td>
										<th class="colum1" width="5%">出生日期</th>
										<td width="15%">${tntVO.tnt_birth}</td>
										<th class="colum1" width="5%">手機號碼</th>
										<td width="15%">${tntVO.tnt_mobile}</td>
										<th class="colum1" width="5%">帳號狀態</th>
										<c:choose>
											<c:when test="${tntVO.tnt_status==0}">
												<td><button class="btn btn-secondary"
														style="font-size: 10px; border-radius: 4px; padding: 2px;"
														disabled>未啟用</button></td>
											</c:when>
											<c:when test="${tntVO.tnt_status==1}">
												<td><button class="btn btn-success"
														style="font-size: 10px; border-radius: 4px; padding: 2px;"
														disabled>已啟用</button></td>
											</c:when>
											<c:when test="${tntVO.tnt_status==2}">
												<td><button class="btn btn-danger"
														style="font-size: 10px; border-radius: 4px; padding: 2px;"
														disabled>失效</button></td>
											</c:when>
											<c:otherwise>
												<td>錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>

									</tr>
									<tr>
										<th class="colum1" width="5%">性別</th>
										<c:choose>
											<c:when test="${tntVO.tnt_sex==false}">
												<td>女</td>
											</c:when>
											<c:when test="${tntVO.tnt_sex==true}">
												<td>男</td>
											</c:when>
											<c:otherwise>
												<td>錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>
										<th class="colum1" width="5%">居住城市</th>
										<td width="15%">${tntVO.tnt_city}</td>
										<th class="colum1" width="5%">居住區域</th>
										<td width="15%">${tntVO.tnt_dist}</td>
										<th class="colum1" width="5%">居住地址</th>
										<td width="15%">${tntVO.tnt_add}</td>

									</tr>
									<tr>
										<th class="colum1" width="5%">加入時間</th>
										<td width="15%">${tntVO.tnt_jointime}</td>
									</tr>
									</tbody>
								</table>
								<br>
								<table class="table table-bordered" id="dataTable">

									<tbody>
									<thead>
										<tr>
											<th>儲值支付</th>
											<td style="border: 0"><button class="alter">修改</button></td>
										</tr>
									</thead>
									<tr>
										<th class="colum1" width="5%">電子錢包餘額</th>
										<td width="15%">${tntVO.tnt_balance}</td>
										<th class="colum1" width="5%">銀行代碼</th>
										<td width="15%">${tntVO.tnt_bank}</td>
										<th class="colum1" width="5%">銀行分行</th>
										<td width="15%">${tntVO.tnt_bankbranch}</td>
										<th class="colum1" width="5%">銀行帳號</th>
										<td width="15%">${tntVO.tnt_bankacc}</td>
									</tr>
									<tr>
										<th class="colum1" width="5%">信用卡卡號</th>
										<td width="15%">${tntVO.tnt_card}</td>
										<th class="colum1" width="5%">卡片到期日</th>
										<td width="15%">${tntVO.tnt_carddue}</td>

									</tr>
									</tbody>
								</table>
								<br>
								<table class="table table-bordered" id="dataTable">

									<tbody>
									<thead>
										<tr>
											<th>評價與驗證</th>
											<td style="border: 0"><button class="alter">修改</button></td>

										</tr>
									</thead>
									<tr>
										<th class="colum1" width="5%">評論星數總和</th>
										<td width="15%">${tntVO.tnt_cmt_starsum}</td>
										<th class="colum1" width="5%">總評論數</th>
										<td width="15%">${tntVO.tnt_cmt_count}</td>
										<th class="colum1" width="5%">上傳狀態</th>
										<c:choose>
											<c:when test="${tntVO.tnt_id_isupload==0}">
												<td>未上傳</td>
											</c:when>
											<c:when test="${tntVO.tnt_id_isupload==1}">
												<td>已上傳</td>
											</c:when>
											<c:otherwise>
												<td>錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>
										<th class="colum1" width="5%">證件上傳時間</th>
										<td width="15%">${tntVO.tnt_id_uploadtime}</td>
									</tr>
									<tr>
										<th class="colum1" width="5%">驗證員工編號</th>
										<td width="15%">${tntVO.emp_no}</td>
										<th class="colum1" width="5%">驗證結果</th>
										<c:choose>
											<c:when test="${tntVO.tnt_id_result==0}">
												<td><span class="badge badge-pill badge-secondary"
													style="font-size: 15px; padding: 4px;">未審核</span></td>
											</c:when>
											<c:when test="${tntVO.tnt_id_result==1}">
												<td><span class="badge badge-pill badge-success"
													style="font-size: 15px; padding: 4px;">通過</span></td>
											</c:when>
											<c:when test="${tntVO.tnt_id_result==2}">
												<td><span class="badge badge-pill badge-danger"
													style="font-size: 15px; padding: 4x;">不通過</span></td>
											</c:when>
											<c:otherwise>
												<td>錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>
										<th class="colum1" width="5%">退件原因</th>
										<td width="15%">${tntVO.tnt_id_disapprove}</td>
										<th class="colum1" width="5%">驗證完成時間</th>
										<td width="15%">${tntVO.tnt_id_vrftime}</td>
									</tr>
									</tbody>
								</table>
								<br>
								<table class="table table-bordered" id="dataTable">

									<tbody>
									<thead>
										<tr>
											<th>檢舉與權限</th>
											<td style="border: 0" data-toggle="modal"
												data-target="#myModal"><button class="alter">修改</button></td>
										</tr>

									</thead>
									<tr>
										<th class="colum1" width="5%">被檢舉次數</th>
										<td width="14%">${tntVO.tnt_reported_count}</td>
										<th class="colum1" width="5%">聊天權限</th>
										<c:choose>
											<c:when test="${tntVO.tnt_auth_chat==0}">
												<td width="12%">關閉</td>
											</c:when>
											<c:when test="${tntVO.tnt_auth_chat==1}">
												<td width="12%">開啟</td>
											</c:when>
											<c:otherwise>
												<td width="12%">錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>

										<th class="colum1" width="5%">預約權限</th>
										<c:choose>
											<c:when test="${tntVO.tnt_auth_res==0}">
												<td width="12%">關閉</td>
											</c:when>
											<c:when test="${tntVO.tnt_auth_res==1}">
												<td width="12%">開啟</td>
											</c:when>
											<c:otherwise>
												<td width="12%">錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>

										<th class="colum1" width="5%">評價權限</th>
										<c:choose>
											<c:when test="${tntVO.tnt_auth_cmt==0}">
												<td width="12%">關閉</td>
											</c:when>
											<c:when test="${tntVO.tnt_auth_cmt==1}">
												<td width="12%">開啟</td>
											</c:when>
											<c:otherwise>
												<td width="12%">錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>

										<th class="colum1" width="5%">檢舉權限</th>
										<c:choose>
											<c:when test="${tntVO.tnt_auth_rpt==0}">
												<td width="12%">關閉</td>
											</c:when>
											<c:when test="${tntVO.tnt_auth_rpt==1}">
												<td width="12%">開啟</td>
											</c:when>
											<c:otherwise>
												<td width="12%">錯誤，請檢查資料庫</td>
											</c:otherwise>
										</c:choose>

									</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- Modal HTML -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
						<div class="modal-dialog  modal-full" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<div class="modal-body" style="height: auto">


										<form action="RpttServlet" method="post">
											<div class="row">
												<div class="col">
													<label for="name" style="margin: 0 10px;">被檢舉次數:&nbsp&nbsp<input
														type="text" size="5px" name="tnt_reported_count"
														value=${tntVO.tnt_reported_count}></label>
												</div>
												<div class="col">
													<span style="margin-right: 10px">入住權限</span> <label
														class="switch"> <c:choose>
															<c:when test="${tntVO.tnt_auth_chat==0}">
																<input type="checkbox" id="checkbox1"
																	name="11tnt_auth_chat" value="0" id="checkbox5">
															</c:when>
															<c:when test="${tntVO.tnt_auth_chat==1}">
																<input type="checkbox" id="checkbox1"
																	name="11tnt_auth_chat" value="1" checked id="checkbox5">
															</c:when>
														</c:choose> <span class="slider round"></span>
													</label>
												</div>
											</div>
											<br> <br>
											<div class="row">
												<div class="col">
													<span style="margin: 0 10px">聊天權限</span> <label
														class="switch"> <c:choose>
															<c:when test="${tntVO.tnt_auth_chat==0}">
																<input type="checkbox" id="checkbox1"
																	name="tnt_auth_chat" value="0">
															</c:when>
															<c:when test="${tntVO.tnt_auth_chat==1}">
																<input type="checkbox" id="checkbox1"
																	name="tnt_auth_chat" value="1" checked>
															</c:when>
														</c:choose> <span class="slider round"></span>
													</label>
												</div>
												<div class="col">
													<span style="margin-right: 10px">預約權限</span> <label
														class="switch"> <c:choose>
															<c:when test="${tntVO.tnt_auth_res==0}">
																<input type="checkbox" name="tnt_auth_res" value="0"
																	id="checkbox2">
															</c:when>
															<c:when test="${tntVO.tnt_auth_res==1}">
																<input type="checkbox" name="tnt_auth_res" value="1"
																	checked id="checkbox2">
															</c:when>
														</c:choose><span class="slider round"></span>
													</label>
												</div>
											</div>
											<br> <br>
											<div class="row">
												<div class="col">
													<span style="margin: 0 10px">評價權限</span> <label
														class="switch"><c:choose>
															<c:when test="${tntVO.tnt_auth_cmt==0}">
																<input type="checkbox" name="tnt_auth_cmt" value="0"
																	id="checkbox3">
															</c:when>
															<c:when test="${tntVO.tnt_auth_cmt==1}">
																<input type="checkbox" name="tnt_auth_cmt" value="1"
																	checked id="checkbox3">
															</c:when>
														</c:choose> <span class="slider round"></span> </label>
												</div>
												<div class="col">
													<span style="margin-right: 10px">檢舉權限</span> <label
														class="switch"> <c:choose>
															<c:when test="${tntVO.tnt_auth_rpt==0}">
																<input type="checkbox" name="tnt_auth_rpt" value="0"
																	id="checkbox4">
															</c:when>
															<c:when test="${tntVO.tnt_auth_rpt==1}">
																<input type="checkbox" name="tnt_auth_rpt" value="1"
																	checked id="checkbox4">
															</c:when>
														</c:choose> <span class="slider round"></span>
													</label>
												</div>
											</div>

											<div>
												<input type="hidden" name="tnt_no" value="${tntVO.tnt_no}">
												<button type="submit" class="pass" name="action"
													value="changeAuth">儲存</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Page Wrapper -->
	</div>
	</div>
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select " Logout" below if you are
					ready to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/back-end/css/vendor/jquery/jquery.min.js">
		
	</script>
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
		src="${pageContext.request.contextPath}/back-end/member/member.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>


</body>

</html>