<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-Hant">

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
            <div class="login100-form validate-form" id="login100-form-identify">
                <form id="registerform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 選擇您的身份 </span>
                    <hr class="login100-form-title p-b-10">
                    <div class="identity-wrap">
                        <div class="identity-img-wrap m-r-10 m-l-10">
                            <img src="https://www.dd-room.com/_nuxt/img/2b7ea73.png" width="300">
                            <div class="container-login100-form-btn m-t-10">
                               <a href="/EA103G2/front-end/index/lld/login.jsp" class="login100-form-btn btnIdentify">我是房東</a>
                            </div>
                        </div>
                        <div class="identity-img-wrap m-r-10 m-l-10">
                            <img src="https://www.dd-room.com/_nuxt/img/78b1566.png" width="300">
                            <div class="container-login100-form-btn m-t-10">
                                <a href="/EA103G2/front-end/index/tnt/login.jsp" class="login100-form-btn btnIdentify">我是房客</a>
                            </div>
                        </div>
                    </div>
                </form>
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
    
</body>

</html>