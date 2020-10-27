<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="newsSvc" scope="page" class="com.news.model.NewsService" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>I-ZU 後台管理</title>
<!-- 網頁icon -->
<link rel="icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<!-- 外部js匯入 -->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="<%=request.getContextPath()%>/back-end/emp/index.jsp">
				<div class="sidebar-brand-icon">
					<i class="fas fa-igloo"></i>
				</div>
				<div class="sidebar-brand-text mx-3">I-ZU</div>
			</a>
			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/emp/index.jsp"> <i
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
						<a  class="collapse-item" href="javascript:void(0)">全體員工</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">查詢員工</a>  -->
						<a	class="collapse-item" href="javascript:void(0)">新增員工</a>
					</div>
				</div>
			</li>
			<!--員工-->
			<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseFive"
				aria-expanded="true" aria-controls="collapseFive"> <i
					class="fas fa-user"></i> <span>會員</span>
			</a>
				<div id="collapseFive" class="collapse" aria-labelledby="headingFive"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">管理</h6>
						<a class="collapse-item" href="javascript:void(0)">房東</a> 
						<a	class="collapse-item" href="javascript:void(0)">房客</a> 
<!-- 						<a	class="collapse-item" href="javascript:void(0)">新增員工</a> -->
					</div>
				</div>
			</li>
				<!--前台 -->
				<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseThree"
				aria-expanded="true" aria-controls="collapseThree"> <i
					class="fas fa-user"></i> <span>前台</span>
			</a>
				<div id="collapseThree" class="collapse" aria-labelledby="headingThree"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">管理</h6>
						<a class="collapse-item" href="javascript:void(0)">最新消息</a> 
					</div>
				</div>
			</li>
			
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
						<a class="collapse-item" href="javascript:void(0)">帳務管理</a> 
						<a	class="collapse-item" href="javascript:void(0)">房屋管理</a> 
						<a	class="collapse-item" href="javascript:void(0)">預約管理</a>
						<a class="collapse-item" href="javascript:void(0)">訂單管理</a> 
						<a	class="collapse-item" href="javascript:void(0)">修繕管理</a> 
							<a	class="collapse-item" href="javascript:void(0)">檢舉管理</a> 				
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
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> 
<!-- 							通知小鈴鐺可配合後台推播系統 -->
								<span id="bellcount" class="badge badge-danger badge-counter"><c:out value="${newsSvc.all.size() <3 ? newsSvc.all.size() : '3+' }"></c:out></span>
						</a> 
<!-- 						Dropdown - Alerts -->
							<div 
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 id="bell" class="dropdown-header">推播</h6>
							 <c:forEach var="newsVO" items="${newsSvc.all}">
<!-- 								第一個展開推播 -->
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-primary">
											<i class="fas fa-file-alt text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">${newsVO.new_date}</div>
										<span id="alert" class="font-weight-bold">${newsVO.new_content}</span>
									</div>
								</a>
							 </c:forEach>
<!-- 								Nav Item - Messages -->
								<li class="nav-item dropdown no-arrow mx-1"><a
									class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
<!-- 										Counter - Messages  -->
										<span
										class="badge badge-danger badge-counter">1</span>
								</a>
<!-- 								 Dropdown - Messages -->
									<div
										class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="messagesDropdown">
										<h6 class="dropdown-header">訊息</h6>
<!-- 										第一個展開訊息 -->
										<a class="dropdown-item d-flex align-items-center" href="#">
											<div class="dropdown-list-image mr-3">
												<img class="rounded-circle"
													src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRVmpzAF6CZLuTuOp8aC6-Xru-scdCLz4MXZA&usqp=CAU"
													alt="">
												<div class="status-indicator bg-success"></div>
											</div>
											<div class="font-weight-bold">
												<div class="text-truncate">0.0</div>
												<div class="small text-gray-500">Lucian · 58m</div>
											</div>
										</a>
<!-- 										第二個展開訊息 -->
										<a class="dropdown-item d-flex align-items-center" href="#">
											<div class="dropdown-list-image mr-3">
												<img class="rounded-circle"
													src="https://hk.portal-pokemon.com/play/resources/pokedex/img/pm/5794f0251b1180998d72d1f8568239620ff5279c.png"
													alt="">
												<div class="status-indicator bg-success"></div>
											</div>
											<div class="font-weight-bold">
												<div class="text-truncate">0.0</div>
												<div class="small text-gray-500">jinigui · 102m</div>
											</div>
										</a>
									</div>
									</li>
								<div class="topbar-divider d-none d-sm-block"></div>
								<!-- Nav Item - User Information -->
								<li class="nav-item dropdown no-arrow">
								<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <span
										class="mr-2v23PnY2C d-none d-lg-inline text-gray-600 small"><c:out value="${empVO.emp_name}" default="請重新登入"></c:out></span>
										<c:if test="${empVO.emp_pic != null}">
										<img class="img-profile rounded-circle"
										src="data:image/png;base64,${empVO.emp_pic}"></c:if>
<%-- 										<c:if test="${empVO.emp_pic == null}"> --%>
<!-- 										<img class="img-profile rounded-circle" -->
<%-- 										src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU"></c:if> --%>
										
								</a>
<!-- 								 Dropdown - User Information -->
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="userDropdown">
										<a class="dropdown-item" href="javascript:void(0)"> <i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 個人資料
										</a> <a class="dropdown-item" href="#"> <i
											class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 設定
										</a> <a class="dropdown-item" href="#"> <i
											class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> 活動紀錄
										</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=logout"> 
											<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
											登出
										</a>
									</div> 
<!-- 									Dropdown - User Information -->
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800"></h1>
					<!-- -- -- -- -- -- -- -- --Content-- -- -- -- -- -- -- -- -- -- -->
					<div id="ajax_result">

<div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">員工編號：</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${empVO.emp_no}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          <button id="btn_con" onclick="connect();">點我連線WebSocket</button>
          <button id="btn_close" onclick="disconnect();">點我關閉WebSocket連線</button>
          <input type="text" id="announce" value="">
          <button id="btn_send" onclick="sendMessage();">點我發送訊息</button>
          
          <h1 id="h1">Chat Room</h1>
	<h3 id="statusOutput" class="statusOutput"></h3>
	<div class="panel input-area">
	</div>
          </div>
					</div>
				</div>
			
			<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
			<script>
			 $(function(){
				 var ajax_select;
				 var ajax_url="";
			      $(".collapse-item").click(function() {
			    	  console.log(this.innerText);
			    	  ajax_select = this.innerText;
			    	  if(ajax_select=="全體員工"){
			    		  ajax_url = "listAllEmp.jsp";
			    	   }
			    	  else if(ajax_select=="查詢員工"){
			    		  ajax_url = "select_page.jsp";
			    	  }
			    	  else if(ajax_select=="新增員工"){
			    		  ajax_url = "addEmp.jsp";
			    	  }
			    	  console.log(ajax_url);
			        $.ajax({
			          type: "GET",
			          url: ajax_url,
			          dataType: "html",
			          async:true,
			          success: function(data) {
			        	  if(ajax_url!=""){
			        		  ajax_url="";
			            $("#ajax_result").html(data);
			            }},
			          error: function(xhr) {
			            alert("Ajax發生錯誤:"+xhr.status);
			            }     	        
			    	  });
			      });  
			      
			      $(".dropdown-item").click(function() {
			    	  console.log(this.innerText);
			    	  ajax_select = this.innerText;
			    	  if(ajax_select==" 個人資料"){
			    		  ajax_url = "<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Display&emp_no=${empVO.emp_no}";
			    	   }
			    	  console.log(ajax_url);
			        $.ajax({
			          type: "GET",
			          url: ajax_url,
			          dataType: "html",
			          async:true,
			          success: function(data) {
			        	  if(ajax_url!=""){
			        		  
			            $("#ajax_result").html(data);
			            }},
			          error: function(xhr) {
			            alert("Ajax發生錯誤:"+xhr.status);
			            }     	        
			    	  });
			      });  
			 });
			 
		
		 
		  
// 		 btn-outline-primary
			</script>
			
			<script>
			
	var MyPoint = "/TogetherWS/melon";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			updateStatus("WebSocket Connected");
			document.getElementById('btn_con').disabled = true;
			document.getElementById('btn_close').disabled = false;
			document.getElementById('btn_send').disabled = false;
		};
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			var new_content = jsonObj.new_content;
			var bell='<a class="dropdown-item d-flex align-items-center" href="#">';
				bell+='<div class="mr-3">';
				bell+='<div class="icon-circle bg-primary">';
				bell+='<i class="fas fa-file-alt text-white"></i>';
				bell+='</div>';
				bell+='</div>';
				bell+='<div>';
				bell+='<div class="small text-gray-500">2020年10月25日</div>';
				bell+= '<span id="alert" class="font-weight-bold">'+new_content+'</span>';
				bell+= '</div>';
				bell+='</a>';
			$('#bell').after(bell);
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}


	function sendMessage() {
		var inputMessage = document.getElementById("announce");
		var new_content = inputMessage.value.trim();
		if (new_content === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
			    "new_no" : ${newsSvc.all.size()},
				"new_content" : new_content,
				"new_date" : "2020年10月26日"
			};
			webSocket.send(JSON.stringify(jsonObj));
		}
	}

	function disconnect() {
		document.getElementById('btn_con').disabled = false;
		document.getElementById('btn_close').disabled = true;
		document.getElementById('btn_send').disabled = true;
		webSocket.close();

	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
</script>

	
</body>

</html>