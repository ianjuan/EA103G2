<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lld.model.*"%>
<%
  LldVO lldVO = (LldVO) request.getAttribute("lldVO");
%>
<html lang="zh-Hant">

<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/index/lld/images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/css/util.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/index/lld/css/main_login.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
</head>

<body class="landing">
<jsp:include page="/front-end/navbar/navbar.jsp"/>
    <div class="limiter">
        <div class="container-login100">
            <div class="login100-form validate-form">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet2" id="loginform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 房東登入 </span>
                    <hr class="login100-form-title p-b-10">
                    <div class="wrap-register100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                        <input class="register100" type="text" name="lld_email" id="lld_email" value="<%= (lldVO==null)? "" : lldVO.getLld_email()%>">
                        <div class="focus-register100"></div>
                        <div class="label-register100">信箱</div>
                    </div>
                    <div class="wrap-register100 validate-input" data-validate="Less than 8 [a-zA-Z0-9]">
                        <input class="register100" type="password" name="lld_pwd" id="lld_pwd" value="<%= (lldVO==null)? "" : lldVO.getLld_pwd()%>">
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
                        <a href="<%=request.getContextPath()%>/front-end/index/lld/forgetPwd.jsp" class="txt1 forgetPwd">忘記密碼?</a>
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
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/daterangepicker/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/js/jquery.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/lld/js/main_login.js"></script>
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
    
	</script>
</body>

</html>