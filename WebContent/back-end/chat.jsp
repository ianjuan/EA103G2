<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="empSvc1" scope="page" class="com.emp.model.EmployeeService" />

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
</head>

<body onload="connect(); connect_chat();" onunload="disconnect();disconnect_chat();">
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
				<li class="nav-item"><a class="nav-link collapsed" href=""
				data-toggle="collapse" data-target="#collapseThree"
				aria-expanded="true" aria-controls="collapseThree"> <i
					class="fas fa-user"></i> <span>��x</span>
			</a>
				<div id="collapseThree" class="collapse" aria-labelledby="headingThree"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">�޲z</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/emp/announce.jsp">��x���i</a> 
					</div>
				</div>
			</li>
			
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
                                <li class="nav-item dropdown no-arrow mx-1">
                                    <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-envelope fa-fw"></i>
                                        <!-- Counter - Messages -->
                                        <span class="badge badge-danger badge-counter">7</span>
                                    </a>
                                    <!-- Dropdown - Messages -->
                                    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                                        <h6 class="dropdown-header">��ѫ�</h6>
                                        <a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="dropdown-list-image mr-3">
                                                <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
                                                <div class="status-indicator bg-success"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>
                                                <div id="row" class="small text-gray-500"></div>
                                            </div>
                                        </a>
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
										<img class="img-profile rounded-circle" src='data:image/png;base64,<c:forEach var="employeeVO" items="${empSvc1.getAll()}">
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
										</a> <a class="dropdown-item" href="#"> <i
											class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> ���ʬ���
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
					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800"></h1>
					<!-- -- -- -- -- -- -- -- --Content-- -- -- -- -- -- -- -- -- -- -->
<div class="col-xl-3 col-md-6 mb-4">
<ul>

</ul>

</div>
<div class="row">
	<h3 id="statusOutput_chat" class="statusOutput"></h3>
	<div id="row"></div>
	<div id="messagesArea" class="panel message-area" ></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage_chat();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage_chat();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect_chat();" /> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect_chat();" />
	</div>
            </div>
                        </div>


				
			
			<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
			<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
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
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj);
			if(jsonObj.length>1){
				$('#alert_count').text(jsonObj.length);
				alert_count==jsonObj.length;
			for(var i=0;i<jsonObj.length ;i++){
				alert_content = JSON.parse(jsonObj[i]).content;
				alert_title =JSON.parse(jsonObj[i]).title;
				alert_time =new Date(JSON.parse(jsonObj[i]).time);
				alert_day = (alert_time.getMonth()+1)+"��"+alert_time.getDate()+"��";
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
			else if(jsonObj.length==0){
			}
			
			else{
				var alert_count =$('#alert_count').text();
				alert_count++;
				$('#alert_count').text(alert_count);
				console.log(alert_count);
				alert_content = jsonObj.content;
				alert_title =jsonObj.title;
				alert_time =new Date(jsonObj.time);
				alert_day = (alert_time.getMonth()+1)+"��"+alert_time.getDate()+"��";
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
			
				


		};

		webSocket.onclose = function(event) {
		};
	}


	function disconnect() {
		webSocket.close();

	}
	</script>
			<script id="chat">
	var MyPoint_chat = "/FriendWS/${empVO.emp_no}";
	var host_chat = window.location.host;
	var path_chat = window.location.pathname;
	var webCtx_chat = path_chat.substring(0, path.indexOf('/', 1));
	var endPointURL_chat = "ws://" + window.location.host + webCtx_chat + MyPoint_chat;
	var self_chat = '${empVO.emp_no}';
	var webSocket_chat;

	function connect_chat() {
		// create a websocket
		webSocket_chat = new WebSocket(endPointURL_chat);

		webSocket_chat.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket_chat.onmessage = function(event) {
			var jsonObj_chat = JSON.parse(event.data);
			if ("open" === jsonObj_chat.type) {
				refreshFriendList_chat(jsonObj_chat);
			} else if ("history" === jsonObj_chat.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// �o�檺jsonObj.message�O�qredis���X��n�ͪ����v�T���A�Aparse��JSON�榡�B�z
				var messages = JSON.parse(jsonObj_chat.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// �ھڵo�e�̬O�ۤv�٬O���ӵ������P��class�W, �H�F��T�����k�Ϥ�
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj_chat.type) {
				var li = document.createElement('li');
				jsonObj_chat.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj_chat.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj_chat.type) {
				refreshFriendList(jsonObj_chat);
			}
			
		};

		webSocket_chat.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage_chat() {
		var inputMessage_chat = document.getElementById("message");
		var friend_chat = statusOutput_chat.textContent;
		var message_chat = inputMessage_chat.value.trim();

		if (message_chat === "") {
			alert("Input a message");
			inputMessage_chat.focus();
		} else if (friend_chat === "") {
			alert("Choose a friend");
		} else {
			var jsonObj_chat = {
				"type" : "chat",
				"sender" : self_chat,
				"receiver" : friend_chat,
				"message" : message_chat
			};
			webSocket_chat.send(JSON.stringify(jsonObj_chat));
			inputMessage_chat.value = "";
			inputMessage_chat.focus();
		}
	}
	
	// ���n�ͤW�u�����u�N��s�C��
	function refreshFriendList_chat(jsonObj_chat) {
		var friends_chat = jsonObj_chat.users;
		var row_chat = document.getElementById("row");
		row_chat.innerHTML = '';
		for (var i = 0; i < friends_chat.length; i++) {
			if (friends_chat[i] === self_chat) { continue; }
			row_chat.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends_chat[i] + ' ><h2>' + friends_chat[i] + '</h2></div>';
		}
		addListener();
	}
	// ���U�C���I���ƥ�ç���n�ͦW�r�H���o���v�T��
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend_chat = e.srcElement.textContent;
			updateFriendName_chat(friend_chat);
			var jsonObj_chat = {
					"type" : "history",
					"sender" : self_chat,
					"receiver" : friend_chat,
					"message" : ""
				};
			webSocket_chat.send(JSON.stringify(jsonObj_chat));
		});
	}
	
	function disconnect_chat() {
		webSocket_chat.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName_chat(name) {
		statusOutput_chat.innerHTML = name;
	}
</script>
	
</body>

</html>