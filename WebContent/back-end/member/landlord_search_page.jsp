
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>

<%
	TntVO tntVO = (TntVO) request.getAttribute("TntVO");
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

<title>房東查詢</title>

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
</style>



</head>

<body id="page-top">
	<div class="container-fluid">
		<!-- Page Heading -->
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<div class="row ">
					<div class="col">
						<h4 class="m-0 font-weight-bold text-primary">房東查詢</h4>
					</div>
					<div class="col">
						<div class="float-right">
							<form METHOD="post" ACTION="RpttServlet">
								<h4>
									搜尋: <input type="text" size="20" name="Number"
										placeholder="輸入房東編號/身份證字號"> <input type="hidden"
										name="action" value="get_want_landlord"> <input
										type="submit"
										style="position: absolute; left: -9999px; width: 1px; height: 1px;"
										tabindex="-1" />
								</h4>
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
								<th>檢舉與權限</th>
								<td style="border: 0" data-toggle="modal" data-target="#myModal"><button
										class="alter">修改</button></td>
							</tr>

						</thead>
						<tr>

							<th class="colum1" width="5%">被檢舉次數</th>
							<td width="10%">${tntVO.tnt_reported_count}</td>
							<th class="colum1" width="5%">上架權限</th>
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
										<span style="margin-right: 10px">上架權限</span> <label
											class="switch"> <c:choose>
												<c:when test="${tntVO.tnt_auth_live==0}">
													<input type="checkbox" id="checkbox5"
														name="tnt_auth_live" value="0" id="checkbox5">
												</c:when>
												<c:when test="${tntVO.tnt_auth_live==1}">
													<input type="checkbox" id="checkbox5"
														name="tnt_auth_live" value="1" checked id="checkbox5">
												</c:when>
											</c:choose> <span class="slider round"></span>
										</label>
									</div>
								</div>
								<br> <br>
								<div class="row">
									<div class="col">
										<span style="margin: 0 10px">聊天權限</span> <label class="switch">
											<c:choose>
												<c:when test="${tntVO.tnt_auth_chat==0}">
													<input type="checkbox" id="checkbox1" name="tnt_auth_chat"
														value="0">
												</c:when>
												<c:when test="${tntVO.tnt_auth_chat==1}">
													<input type="checkbox" id="checkbox1" name="tnt_auth_chat"
														value="1" checked>
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
										<span style="margin: 0 10px">評價權限</span> <label class="switch"><c:choose>
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
									<input type="hidden" name="tnt_no" value="${tntVO.tnt_no}">
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


	<!-- Bootstrap core JavaScript-->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back-end/member/js/member.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>


</body>

</html>
