<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-Hant">

<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/haburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
<!--     <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"> -->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main_register.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css">
    <!--===============================================================================================-->
</head>

<body class="landing">
    <!-- Header -->
    <header id="header">
        <div id="quickpick">Quick Input</div>
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
                <form id="registerform" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-10"> 註冊成為房客 </span>
                    <hr class="login100-form-title p-b-10">
                    <div id="divProfile">
                        <div class="wrap-register100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                            <input class="register100" type="text" name="tnt_email" id="tnt_email">
                            <div class="focus-register100"></div>
                            <div class="label-register100">信箱</div>
                        </div>
                        <div class="wrap-register100 validate-input" data-validate="Must be between 2-19 characters">
                            <input class="register100" type="text" name="tnt_name" id="tnt_name">
                            <span class="focus-register100"></span>
                            <span class="label-register100">姓名</span>
                        </div>
                        <select class="wrap-register100 validate-input" data-validate="Gender is required" name="tnt_sex" id="tnt_sex" style="background-color: #f7f7f7;">
                            <span class="focus-register100"></span>
                            <span class="label-register100">
                                <option value="">選擇性別
                                <option value="1">男
                                <option value="0">女
                            </span>
                        </select>
                        <div class="wrap-register100 validate-input" data-validate="Valid ID is required">
                            <input class="register100" type="text" name="tnt_id" id="tnt_id">
                            <span class="focus-register100"></span>
                            <span class="label-register100">身分證號碼</span>
                        </div>
                        <div class="wrap-register100 validate-input" data-validate="Mobile is required">
                            <input class="register100" type="text" name="tnt_mobile" id="tnt_mobile">
                            <span class="focus-register100"></span>
                            <span class="label-register100">手機</span>
                        </div>
                        <div class="wrap-register100 validate-input" data-validate="Less than 8 [a-zA-Z0-9]">
                            <input class="register100" type="password" name="tnt_pwd" id="tnt_pwd">
                            <span class="focus-register100"></span>
                            <span class="label-register100">密碼</span>
                        </div>
                        <div class="wrap-register100 validate-input" data-validate="Password is not the same">
                            <input class="register100" type="password" name="tnt_pwd2" id="tnt_pwd2">
                            <span class="focus-register100"></span> <span class="label-register100">確認密碼</span>
                        </div>
                        <div class="wrap-register100 validate-input" data-validate="Birthday is required">
                            <input class="register100" type="text" name="tnt_birth" id="f_date1">
                            <span class="focus-register100"></span>
                            <span class="label-register100 label-register100-birth"></span>
                        </div>
                        <!--                                ------------------------------------ -->
                        <select class="wrap-register100 validate-input" data-validate="City is required" name="tnt_city" id="tnt_city" style="background-color: #f7f7f7;">
                            <span class="focus-register100"></span>
                            <span class="label-register100">
                                <option id="city_default" value="">選擇縣市
                            </span>
                        </select>
                        <select class="wrap-register100 validate-input" data-validate="District is required" name="tnt_dist" id="tnt_dist" style="background-color: #f7f7f7;">
                            <option id="dist_default" value="">選擇區域
                        </select>
                        <div class="wrap-register100 validate-input" data-validate="Address is required">
                            <input class="register100" type="text" name="tnt_add" id="tnt_add">
                            <span class="focus-register100"></span>
                            <span class="label-register100">地址</span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn m-t-16" id="btnProfile">Next</button>
                        </div>
                    </div>
                    <div id="divPic">
                        <div class="wrapperBackground">
                            <div id=wrapperUploadHandler ondrop="dropUploadHandler(event)">
                                <label id="fileInput" for="inputF">
                                    <input ondrop="dropUploadHandler(event)" type="file" id="inputF" name="tnt_pic"> Upload Files
                                </label>
                                <button type="button" id="del" ondrop="dropDelHandler(event)">Delete</button>
                            </div>
                        </div>
                        <div id="picWrapper">
                            <!-- <div class="grid" id="grid"> -->
                            <!-- </div> -->
                        </div>
                    </div>
                    <!--divPic-->
                </form>
                <div id="divbtnRegister">
                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn m-t-16" id="btnRegister">Sign up</button>
                    </div>
                </div>
                <!--divbtnRegister-->
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
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/js/jquery.datetimepicker.full.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/index/tnt/js/main_register.js"></script>
    <!--===============================================================================================-->
    
</body>

</html>