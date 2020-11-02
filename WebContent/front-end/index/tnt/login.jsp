<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>
<%
	session.removeAttribute("lld_no");
	session.removeAttribute("tnt_no");
%>
<%
//   String action = request .getParameter("action");
//   if ("logout_ChgPwd".equals(action)){
// 	  session.removeAttribute("tnt_no");
//   }
%>
<%
  TntVO tntVO_req = (TntVO) request.getAttribute("tntVO_req");
%>

<html lang="zh-Hant">

<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/index/tnt/images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/css/util.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/tnt/css/main_login.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css"> 
</head>

<body class="landing">
  <jsp:include page="/front-end/navbar/navbar.jsp"/>
  <div id="quickpick">Quick Input</div>
    <div class="limiter">
        <div class="container-login100">
            <div class="login100-form validate-form">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet2" id="loginform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 房客登入 </span>
                    <hr class="login100-form-title p-b-10">
                    <div class="wrap-register100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                        <input class="register100" type="text" name="tnt_email" id="tnt_email" value="<%= (tntVO_req==null)? "" : tntVO_req.getTnt_email()%>">
                        <div class="focus-register100"></div>
                        <div class="label-register100">信箱</div>
                    </div>
                    <div class="wrap-register100 validate-input" data-validate="Less than 8 [a-zA-Z0-9]">
                        <input class="register100" type="password" name="tnt_pwd" id="tnt_pwd" value="<%= (tntVO_req==null)? "" : tntVO_req.getTnt_pwd()%>">
                        <span class="focus-register100"></span>
                        <span class="label-register100">密碼</span>
                    </div>
<!--                 </form> -->
                <div class="wrap-validate-login" data-validate="帳號密碼錯誤">
<!--                      validate-input alert-validate-login -->
                </div>
                <input type="hidden" name="action" value="login">
                <div class=" container-login100-form-btn">
                    <button class="login100-form-btn m-t-16" id="btnLogin">Login</button>
                </div>
                </form> 
                <!--divbtnRegister-->
                <div class="flex-sb-m w-full p-t-3 p-t-20 p-b-20">
                    <div class="contact100-form-checkbox">
                        <!-- <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me"> -->
                        <!-- <label class="label-checkbox100" for="ckb1"> -->
                        <!-- Remember me -->
                        <!-- </label> -->
                    </div>
                    <div class="m-r-10">
                        <a href="<%=request.getContextPath()%>/front-end/index/tnt/forgetPwd.jsp" class="txt1 forgetPwd">忘記密碼?</a>
                    </div>
                </div>
                <!-- <div class="text-center p-r-10"> -->
                <!-- <span class="txt2">or sign up using </span> -->
                <!-- <a href="#" class="login100-form-social-item flex-c-m bg1 m-r-5"> -->
                <!-- <i class="fa fa-facebook-f" aria-hidden="true"></i> -->
                <!-- </a> -->
                <!-- </div> -->
            </div>
            <!--login100-form-->
        </div>
    </div>
   
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/daterangepicker/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/js/jquery.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/js/main_login.js"></script>
    <!--===============================================================================================-->
    <script>
    var errorMsgsJs;
    <c:if test="${not empty errorMsgs}">
        <c:forEach var="message" items="${errorMsgs}">
            $('.wrap-validate-login').attr('data-validate', '${message}');
        	$('.wrap-validate-login').addClass('validate-input alert-validate-login');
            errorMsgsJs = '${message}';
        </c:forEach>
	</c:if>

	if (typeof(errorMsgsJs)!=='undefined'){
		var inputs = $('.register100');
		console.log(inputs);
		for (var i = 0; i < inputs.length; i++) {
            $(inputs[i]).focus();
            $(inputs[i]).blur();
    	}
	}

	
    var emailVrfMsgsJs;
    console.log(emailVrfMsgsJs);
    <c:if test="${not empty emailVrfMsgs}">
        <c:forEach var="msg" items="${emailVrfMsgs}">
        	emailVrfMsgsJs = '${msg}';
        </c:forEach>
	</c:if>
	console.log(emailVrfMsgsJs);
	if (typeof(emailVrfMsgsJs)!=='undefined'){
		Swal.fire({
    		icon: 'success',
    		title: '信箱驗成功',
    		text: "帳號已啟用, 請登入以繼續",
    	    showDenyButton: true,
    	    animation: false,
    		});
	}
	</script>
</body>

</html>