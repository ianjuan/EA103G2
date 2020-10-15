<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>
<%
  TntVO tntVO = (TntVO) request.getAttribute("tntVO");
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
</head>

<body class="landing">
    <!-- Header -->
    <header id="header">
        <h1>
            <a href="index.html">愛租I-ZU</a>
        </h1>
        <nav id="nav">
            <ul>
                <li><a href="index.html">尋找房源</a></li>
                <li><a href="generic.html">地圖找房</a></li>
                <li><a href="elements.html">會員登入</a></li>
                <li><a href="#" class="special">註冊會員</a></li>
            </ul>
        </nav>
    </header>
    <div class="limiter">
        <div class="container-login100">
            <div class="login100-form validate-form">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet2" id="loginform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 房客登入 </span>
                    <hr class="login100-form-title p-b-10">
                    <div class="wrap-register100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                        <input class="register100" type="text" name="tnt_email" id="tnt_email" value="<%= (tntVO==null)? "" : tntVO.getTnt_email()%>">
                        <div class="focus-register100"></div>
                        <div class="label-register100">信箱</div>
                    </div>
                    <div class="wrap-register100 validate-input" data-validate="Less than 8 [a-zA-Z0-9]">
                        <input class="register100" type="password" name="tnt_pwd" id="tnt_pwd" value="<%= (tntVO==null)? "" : tntVO.getTnt_pwd()%>">
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
                        <a href="<%=request.getContextPath()%>/front-end/index/tnt/forgetPwd.html" class="txt1">忘記密碼?</a>
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
    <canvas class="cyan" width="368" height="294" id="paper-view-7" style="-webkit-user-drag: none; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas>
    <footer id="footer">
        <div class="container">
            <section class="links">
                <div class="row">
                    <section class="3u 6u(medium) 12u$(small)">
                        <h3>Lorem ipsum dolor</h3>
                        <ul class="unstyled">
                            <li><a href="#">Lorem ipsum dolor sit</a></li>
                            <li><a href="#">Nesciunt itaque, alias possimus</a></li>
                            <li><a href="#">Optio rerum beatae autem</a></li>
                            <li><a href="#">Nostrum nemo dolorum facilis</a></li>
                            <li><a href="#">Quo fugit dolor totam</a></li>
                        </ul>
                    </section>
                    <section class="3u 6u$(medium) 12u$(small)">
                        <h3>Culpa quia, nesciunt</h3>
                        <ul class="unstyled">
                            <li><a href="#">Lorem ipsum dolor sit</a></li>
                            <li><a href="#">Reiciendis dicta laboriosam enim</a></li>
                            <li><a href="#">Corporis, non aut rerum</a></li>
                            <li><a href="#">Laboriosam nulla voluptas, harum</a></li>
                            <li><a href="#">Facere eligendi, inventore dolor</a></li>
                        </ul>
                    </section>
                    <section class="3u 6u(medium) 12u$(small)">
                        <h3>Neque, dolore, facere</h3>
                        <ul class="unstyled">
                            <li><a href="#">Lorem ipsum dolor sit</a></li>
                            <li><a href="#">Distinctio, inventore quidem nesciunt</a></li>
                            <li><a href="#">Explicabo inventore itaque autem</a></li>
                            <li><a href="#">Aperiam harum, sint quibusdam</a></li>
                            <li><a href="#">Labore excepturi assumenda</a></li>
                        </ul>
                    </section>
                    <section class="3u$ 6u$(medium) 12u$(small)">
                        <h3>Illum, tempori, saepe</h3>
                        <ul class="unstyled">
                            <li><a href="#">Lorem ipsum dolor sit</a></li>
                            <li><a href="#">Recusandae, culpa necessita nam</a></li>
                            <li><a href="#">Cupiditate, debitis adipisci blandi</a></li>
                            <li><a href="#">Tempore nam, enim quia</a></li>
                            <li><a href="#">Explicabo molestiae dolor labore</a></li>
                        </ul>
                    </section>
                </div>
            </section>
            <div class="row">
                <div class="8u 12u$(medium)">
                    <ul class="copyright">
                        <li>&copy; Untitled. All rights reserved.</li>
                        <li>Design: <a href="http://templated.co">TEMPLATED</a></li>
                        <li>Images: <a href="http://unsplash.com">Unsplash</a></li>
                    </ul>
                </div>
                <div class="4u$ 12u$(medium)">
                    <ul class="icons">
                        <li><a class="icon rounded fa-facebook"><span class="label">Facebook</span></a></li>
                        <li><a class="icon rounded fa-twitter"><span class="label">Twitter</span></a></li>
                        <li><a class="icon rounded fa-google-plus"><span class="label">Google+</span></a></li>
                        <li><a class="icon rounded fa-linkedin"><span class="label">LinkedIn</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
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