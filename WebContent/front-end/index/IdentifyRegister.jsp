<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-Hant">

<% session.removeAttribute("tnt_no"); %>
<% session.removeAttribute("lld_no"); %>
<head>
    <title>Identify</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/index/tnt/images/icons/favicon.ico"/>
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
	
	<style>
	.container-login100 {
            align-items: baseline;
        }
    .footer-real {
    	margin-top: 0px !important;
    }
    
	#btnIdentifylld {
		background: #D7C8B6;
		color: #916A3C;
	}
	#btnIdentifylld:hover {
		background: #916A3C;
        color: #fff;
        font-size: 17px;
	}
	</style>
</head>

<body class="landing">
<jsp:include page="/front-end/navbar/navbar.jsp"/>
    <div class="limiter">
        <div class="container-login100">
            <div class="login100-form validate-form" id="login100-form-identify">
                <form id="registerform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 選擇註冊身份 </span>
                    <hr class="login100-form-title p-b-10">
                    <div class="identity-wrap">
                        <div class="identity-img-wrap m-r-10 m-l-10">
<%--                             <img src="<%=request.getContextPath()%>/images/lld.png" width="300"> --%>
							<img src="<%=request.getContextPath()%>/images/identify_lld.png" width="300">
                            <div class="container-login100-form-btn m-t-10">
                               <a href="/EA103G2/front-end/index/lld/register.jsp" class="login100-form-btn btnIdentify" id="btnIdentifylld">我是房東</a>
                            </div>
                        </div>
                        <div class="identity-img-wrap m-r-10 m-l-10">
<%--                         	<img src="<%=request.getContextPath()%>/images/tnt.png" width="300"> --%>
							<img src="<%=request.getContextPath()%>/images/identify_tnt.png" width="300">
                            <div class="container-login100-form-btn m-t-10">
                                <a href="/EA103G2/front-end/index/tnt/register.jsp" class="login100-form-btn btnIdentify">我是房客</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!--login100-form-->
        </div>
    </div>
   	<%@ include file="/front-end/index/footer.jsp"%>
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
    
</body>

</html>