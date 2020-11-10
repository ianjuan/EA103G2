<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunctionService" />
<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>I-ZU ��x�޲z</title>
<!-- ����icon -->
<link rel="icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<!-- �~��js�פJ -->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

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
					class="fas fa-fw fa-tachometer-alt"></i> <span>�����O</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">
			<!-- Heading -->
			<div class="sidebar-heading">�޲z</div>
			
			<!--���u-->
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-user"></i> <span>���u</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">�޲z</h6>
						<a  class="collapse-item" href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">������u</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">�d�߭��u</a>  -->
						<a	class="collapse-item" href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">�s�W���u</a>
					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwooooooo"
				aria-expanded="true" aria-controls="collapseTwooooooo"> <i
					class="fas fa-user"></i> <span>��ѫ�</span>
			</a>
				<div id="collapseTwooooooo" class="collapse" aria-labelledby="headingTwooooooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">��ѫ�</h6>
						<a  class="collapse-item" href="<%=request.getContextPath()%>/back-end/chat.jsp">��ѫ�</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">�d�߭��u</a>  -->
					</div>
				</div>
			</li>
			<!--���u-->
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwoo"
				aria-expanded="true" aria-controls="collapseTwoo"> <i
					class="fas fa-user"></i> <span>�|���d��</span>
			</a>
				<div id="collapseTwoo" class="collapse" aria-labelledby="headingTwoo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">�d��</h6>
						<a class="collapse-item" href="javascript:void(0)">�d�ߩЪF</a> 
						<a	class="collapse-item" href="<%=request.getContextPath()%>/back-end/member/tenant_main_page.jsp">�d�ߩЫ�</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">�s�W���u</a> -->
					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwooo"
				aria-expanded="true" aria-controls="collapseTwooo"> <i
					class="fas fa-user"></i> <span>��������</span>
			</a>
				<div id="collapseTwooo" class="collapse" aria-labelledby="headingTwooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">��������</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/vrf/vrf_main_page.jsp">��������</a> 

					</div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseTwoooo"
				aria-expanded="true" aria-controls="collapseTwoooo"> <i
					class="fas fa-user"></i> <span>�|�����|</span>
			</a>
				<div id="collapseTwoooo" class="collapse" aria-labelledby="headingTwoooo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">���|</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/rptt/main_page.jsp">���|�Ы�</a> 
						<a class="collapse-item" href="javascript:void(0)">���|�ЪF</a>
						<a class="collapse-item" href="javascript:void(0)">���|�Ы�</a>
						<a class="collapse-item" href="javascript:void(0)">���|�ЪF����</a>
						<a class="collapse-item" href="javascript:void(0)">���|�Ыε���</a>
						<a class="collapse-item" href="javascript:void(0)">���|��µ</a>
<!-- 						<a	class="collapse-item" href="javascript:void(0)">�s�W���u</a> -->
					</div>
				</div>
			</li>
				<!--�e�x -->
		
			<!--�~�Ȭy�{ -->
				<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseSix"
				aria-expanded="true" aria-controls="collapseSix"> <i
					class="fas fa-user"></i> <span>�~�Ȭy�{ </span>
			</a>
				<div id="collapseSix" class="collapse" aria-labelledby="headingSix"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">�޲z</h6>
<!-- 						<a class="collapse-item" href="javascript:void(0)">�b�Ⱥ޲z</a>  -->
						<a	class="collapse-item" href="<%=request.getContextPath()%>/back-end/house_manage/all_house.jsp">�Ыκ޲z</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">�w���޲z</a> -->
<!-- 						<a class="collapse-item" href="javascript:void(0)">�q��޲z</a>  -->
<!-- 						<a	class="collapse-item" href="javascript:void(0)">��µ�޲z</a>  -->
<!-- 							<a	class="collapse-item" href="javascript:void(0)">���|�޲z</a> 				 -->
					</div>
				</div>
			</li>	
			<!-- Divider -->

			<!-- Heading -->
			<div class="sidebar-heading">
				<!-- �ݸ� -->
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
					<!-- auto�m�k -->
					<ul class="navbar-nav ml-auto">
                                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                                <li class="nav-item dropdown no-arrow d-sm-none">
                                    <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-search fa-fw"></i>
                                    </a>
                                    <!-- Dropdown - Messages -->
                                    <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                        <form class="form-inline mr-auto w-100 navbar-search">
                                            <div class="input-group">
                                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                                <div class="input-group-append">
                                                    <button class="btn btn-primary" type="button">
                                                        <i class="fas fa-search fa-sm"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </li>
                                <!-- Nav Item - Alerts -->
                                <li class="nav-item dropdown no-arrow mx-1">
                                    <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-bell fa-fw"></i>
                                        <!-- Counter - Alerts -->
                                        <span id="alert_count" class="badge badge-danger badge-counter"></span>
                                    </a>
                                    <!-- Dropdown - Alerts  fas fa-file-alt text-white fas fa-donate text-white  fas fa-exclamation-triangle text-white--> 
                                    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                                        <h6 id="bell_alert" class="dropdown-header">
                                           		 �q������
                                        </h6>
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
                                        <a class="dropdown-item text-center small text-gray-500" href="#">�i�{�����q��</a>
                                    </div>
                                </li>
<!-- 						Dropdown - Alerts -->
							
								<div class="topbar-divider d-none d-sm-block"></div>
								<!-- Nav Item - User Information -->
								<li class="nav-item dropdown no-arrow">
								<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <span
										class="mr-2v23PnY2C d-none d-lg-inline text-gray-600 small"><c:out value="${empVO.emp_name}" default="�Э��s�n�J"></c:out></span>
										<c:if test="${empVO.emp_pic != null}">
										<img class="img-profile rounded-circle" src='data:image/png;base64,<c:forEach var="employeeVO" items="${empSvc.getAll()}">
										<c:if test="${employeeVO.emp_no == empVO.emp_no }">${employeeVO.emp_pic }</c:if>
										</c:forEach>'></c:if>
										
								</a>
<!-- 								 Dropdown - User Information -->
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="userDropdown">
										<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Display&emp_no=${empVO.emp_no}"> <i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> �ӤH���
										</a> <a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Update&emp_no=${empVO.emp_no}"> <i
											class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> �]�w
										</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=logout"> 
											<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
											�n�X
										</a>
									</div> 
<!-- 									Dropdown - User Information -->
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
<div class="col-lg-6">
<div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">�|���}�q�v��</h6>
                </div>
                <div class="card-body">
				�Ь��D�޶}�q�����v���A���¡I
                </div>
              </div>
            </div>
          </div>

        </div>
</div>
				</div>
			
			<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/vendor/chart.js/Chart.min.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/js/demo/chart-pie-demo.js"></script>
			<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
			<script>
			Swal.fire('�ܩ�p�A�ثe�A�|���}�q�����v���A�Ь��D������')
			</script>
<script id="alert_js">
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
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj);
			if(jsonObj.length>=1){
				console.log("hi");
				$('#alert_count').text(jsonObj.length);
				alert_count==jsonObj.length;
			for(var i=0;i<jsonObj.length ;i++){
				alert_content = JSON.parse(jsonObj[i]).content;
				alert_title =JSON.parse(jsonObj[i]).title;
				alert_url=JSON.parse(jsonObj[i]).url;
				alert_time =new Date(JSON.parse(jsonObj[i]).time);
				alert_day = (alert_time.getMonth()+1)+"��"+alert_time.getDate()+"��";
				bell_html=`<a class="dropdown-item d-flex align-items-center" href="${"${alert_url}"}">
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
			else if(jsonObj.length!=0){
				var a = parseInt($('#alert_count').text(),10);
				alert_count=++a;
				$('#alert_count').text(alert_count);
				alert_content = jsonObj.content;
				alert_title =jsonObj.title;
				alert_url=jsonObj.url;
				alert_time =new Date(jsonObj.time);
				alert_day = (alert_time.getMonth()+1)+"��"+alert_time.getDate()+"��";
				bell_html=`<a class="dropdown-item d-flex align-items-center" href="${"${alert_url}"}">
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