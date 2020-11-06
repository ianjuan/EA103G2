
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>

<%
	TntVO tntVO = (TntVO) request.getAttribute("TntVO");
	pageContext.setAttribute("tntVO", tntVO);
	
%>
<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="empSvc" scope="page"
	class="com.emp.model.EmployeeService" />

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>I-ZU 查詢房客</title>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back-end/member/css/main_member_back.css"
	type="text/css">


<style>
button.save {
	margin-top: 15px;
	font-size: 16px;
	font-weight: 600;
	text-transform: uppercase;
	padding: 15px 26px;
	color: #FFF;
	background-color: #0f868c;
	border-radius: 4px;
	border: 1px solid #0f868c;
	text-align: center;
	width: 100%;
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
										<div class="row justify-content-between">
											<div class="col">
												<h4 class="m-0 font-weight-bold text-primary">房客查詢</h4>
											</div>
											<div class="col">
												<div class="float-right">
													<form METHOD="post" ACTION="RpttServlet">
														<h5>
															搜尋: <input type="text" size="22" name="Number"
																placeholder="輸入房客編號/身份證字號"> <input type="hidden"
																name="action" value="get_want_member"> <input
																type="submit"
																style="position: absolute; left: -9999px; width: 1px; height: 1px;"
																tabindex="-1" />
														</h5>
													</form>
												</div>
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
													<td width="10%">${tntVO.tnt_reported_count}</td>
													<th class="colum1" width="5%">入住權限</th>
													<c:choose>
														<c:when test="${tntVO.tnt_auth_live==0}">
															<td width="10%">關閉</td>
														</c:when>
														<c:when test="${tntVO.tnt_auth_live==1}">
															<td width="10%">開啟</td>
														</c:when>
														<c:otherwise>
															<td width="10%">錯誤，請檢查資料庫</td>
														</c:otherwise>
													</c:choose>

													<th class="colum1" width="5%">聊天權限</th>
													<c:choose>
														<c:when test="${tntVO.tnt_auth_chat==0}">
															<td width="10%">關閉</td>
														</c:when>
														<c:when test="${tntVO.tnt_auth_chat==1}">
															<td width="10%">開啟</td>
														</c:when>
														<c:otherwise>
															<td width="10%">錯誤，請檢查資料庫</td>
														</c:otherwise>
													</c:choose>

													<th class="colum1" width="5%">預約權限</th>
													<c:choose>
														<c:when test="${tntVO.tnt_auth_res==0}">
															<td width="10%">關閉</td>
														</c:when>
														<c:when test="${tntVO.tnt_auth_res==1}">
															<td width="10%">開啟</td>
														</c:when>
														<c:otherwise>
															<td width="10%">錯誤，請檢查資料庫</td>
														</c:otherwise>
													</c:choose>

													<th class="colum1" width="5%">評價權限</th>
													<c:choose>
														<c:when test="${tntVO.tnt_auth_cmt==0}">
															<td width="10%">關閉</td>
														</c:when>
														<c:when test="${tntVO.tnt_auth_cmt==1}">
															<td width="10%">開啟</td>
														</c:when>
														<c:otherwise>
															<td width="10%">錯誤，請檢查資料庫</td>
														</c:otherwise>
													</c:choose>

													<th class="colum1" width="5%">檢舉權限</th>
													<c:choose>
														<c:when test="${tntVO.tnt_auth_rpt==0}">
															<td width="10%">關閉</td>
														</c:when>
														<c:when test="${tntVO.tnt_auth_rpt==1}">
															<td width="10%">開啟</td>
														</c:when>
														<c:otherwise>
															<td width="10%">錯誤，請檢查資料庫</td>
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
																		<c:when test="${tntVO.tnt_auth_live==0}">
																			<input type="checkbox" id="checkbox5"
																				name="tnt_auth_live" value="0" id="checkbox5">
																		</c:when>
																		<c:when test="${tntVO.tnt_auth_live==1}">
																			<input type="checkbox" id="checkbox5"
																				name="tnt_auth_live" value="1" checked
																				id="checkbox5">
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
														<br>
														<div>
															<input type="hidden" name="tnt_no"
																value="${tntVO.tnt_no}">
															<button type="submit" class="save" name="action"
																value="changeAuth">儲存</button>
														</div>
													</form>
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

							<script
								src="${pageContext.request.contextPath}/back-end/member/js/member.js">
							</script>
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
