<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>


<% String tnt_no = (String) session.getAttribute("tnt_no");%>
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />

<%
	TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);
	request.setAttribute("tntVO", tntVO);
%>

<head>
    <title>Verify</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&family=Open+Sans:ital,wght@1,600&family=Pacifico&display=swap" rel="stylesheet">
    <!-- <link rel="stylesheet" href="css/style.css"> -->

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/tnt/images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/animate/animate.css">
    <!--===============================================================================================-->
<!--     <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/haburgers.min.css"> -->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/jquery.datetimepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/util.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/verify_tnt.css">
    <!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css"> 


    <style>
        body.landing #header nav a {
            color: #4E6258;
        }

        #header h1 a {
            font-size: 1em;
        }

        #header h1 a:hover {
            color: #4E6258;
        }

        [data-v-9403d44c] .v-switch-label {
            color: #fff !important
        }

        @media screen and (max-width:575px) {
            .button__small.input__toggle[data-v-9403d44c] {
                top: 5px;
                right: 40px
            }
        }

        .delectBtn[data-v-9403d44c] {
            left: 150px;
            top: 0
        }

        .delectBtn__icon[data-v-9403d44c] {
            width: 25px;
            height: 25px;
            transform: translate(-50%, -50%)
        }
    </style>
    <script>

    </script>
</head>

<body class="landing">
	<jsp:include page="/front-end/navbar/navbar.jsp"/>
    <section>
        <section class="content">
            <div class="container" id="infocontainer">
                <div class="row no-gutters justify-content-between">
                    <!--start info list -->
                    <div class="basicInfo__list col-lg-3">
                        <div class="basicInfo__list__content bg-white px-4 pt-lg-7 pt-md-5 pt-2 sticky-top">
                            <a class="basicInfo__list-backIcon text-primary basicInfo__list__content-backIcon d-xl-none">
                                <svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="arrow-alt-circle-left" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-arrow-alt-circle-left fa-w-16">
                                    <path fill="currentColor" d="M8 256c0 137 111 248 248 248s248-111 248-248S393 8 256 8 8 119 8 256zm448 0c0 110.5-89.5 200-200 200S56 366.5 56 256 145.5 56 256 56s200 89.5 200 200zm-72-20v40c0 6.6-5.4 12-12 12H256v67c0 10.7-12.9 16-20.5 8.5l-99-99c-4.7-4.7-4.7-12.3 0-17l99-99c7.6-7.6 20.5-2.2 20.5 8.5v67h116c6.6 0 12 5.4 12 12z" class=""></path>
                                </svg>
                            </a>
                            <div class="basicInfo__userImg mx-auto mb-3 divBigHeadPic">
                                <!-- <input type="file" accept="image/jpg, image/jpeg, image/png" name="name" class="d-none"> -->
<%--                                 <a class="awrapBigHeadPic" --%>
<!--                                     <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="camera" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-camera fa-w-16"> -->
<!--                                         <path fill="currentColor" d="M512 144v288c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V144c0-26.5 21.5-48 48-48h88l12.3-32.9c7-18.7 24.9-31.1 44.9-31.1h125.5c20 0 37.9 12.4 44.9 31.1L376 96h88c26.5 0 48 21.5 48 48zM376 288c0-66.2-53.8-120-120-120s-120 53.8-120 120 53.8 120 120 120 120-53.8 120-120zm-32 0c0 48.5-39.5 88-88 88s-88-39.5-88-88 39.5-88 88-88 88 39.5 88 88z" class=""></path> -->
<!--                                     </svg> -->
<!--                                 </a> -->
                                <a class="awrapBigHeadPic">
                                     <img src="<%=request.getContextPath()%>/ImgReader?id=${tntVO.tnt_no}" width="110" class="imgBigHeadPic">
                                 </a>
                            </div>
                            <p class="text-gray text-center mb-3">房客</p>
                            <h4 class="text-center">${tntVO.tnt_name} 個人資訊</h4>
                            <ul class="basicInfo__menu mt-4 border-top mx-2 px-4 py-5">
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/info.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/meminfoprofile.png" width="30" class="mr-2">基本資訊
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a class="basicInfo__menu__link nuxt-link-exact-active nuxt-link-active">
                                        <img src="https://www.dd-room.com/_nuxt/img/1d5e617.png" width="30" class="mr-2">身分驗證
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/pocket.jsp" class="basicInfo__menu__link">
                                        <img src="https://www.dd-room.com/_nuxt/img/2dc32e9.png" width="30" class="mr-2">我的錢包
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="/basicInfo/account" class="basicInfo__menu__link">
                                        <img src="https://www.dd-room.com/_nuxt/img/3d03113.png" width="30" class="mr-2">我的預約
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="/basicInfo/account" class="basicInfo__menu__link">
                                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsSAAALEgHS3X78AAACIElEQVR4nO3dwVGDQBhA4V/Hu5ZgBTt2EEpJJ8ZOUkrswNkKKCFWECcz60Vi1A1hH/K+I1wCb4DAJuzN4XAIcdzagsUgMAaBMQiMQWAMAmMQGIPA3F3ycVJKDxGxjoiHwcpl2kfENue8r9366jv1lNJTROwi4n6wctneI6LLOb9NFqQcGb0xvnWM8lhzpNReQ9bGOOu+7KM/qw3iNeNnVfvooov6F6/lmrJEXUSsxtjuMYPscs6bwdIFSCltxgrifQiMQWAMAmMQGIPAGATGIDAGgTEIjEFgDAJjEBiDwBgEZszH77+WUurKGALZcThh8vGdJkFKjOfBUp7Jg3jKgjEIjEFgWl1D5vBjiCafsUmQ8u1lqb9QOctTFoxBYAwCYxAYg8AYBMYgMAaBMQiMQWAMAmMQmFZDuJsZjBi+tPhHmEcIjEFgDAJjEJhWQ7jbGYwY9oMlE2g1hNu32mA6T1kwBoExCIxBYAwCYxAYg8AYBMYgMAaBMQhMqxHDx+N7bQcrLtOXZ2Sz1upp7/oKQ7gvETH7l3B6yoIxCIxBYAwC02rEcPMfLsDX4BECYxAYg8AYBMYgMAaBMQiMQWAMAmMQGIPAjPksqyv/HVyi0V55O2aQ1VhTxy1Z7SmrejbkBanaR7VBtmUCXp32XvbRNEHKLMidUU76nL676gipnk89nOD+lHYT3Os6vA+BMQiMQWAMAmMQGIPAGATGICQR8QGjXWHJ4UpOVwAAAABJRU5ErkJggg==" width="30" class="mr-2">我的合約</a>
                                </li>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--end info list -->
                    <div class="col-xl-8 col-lg-9 pl-xl-0 pl-lg-7 offset-lg-0 basicInfo__nuxt">
                        <div data-v-9403d44c="" class="basicInfo__info">
                            <div data-v-9403d44c="" tabindex="0" aria-label="Loading" class="vld-overlay is-active is-full-page" style="display: none;">
                                <div class="vld-background"></div>
                                <div class="vld-icon"><svg viewBox="0 0 38 38" xmlns="http://www.w3.org/2000/svg" width="64" height="64" stroke="#ec887c">
                                        <g fill="none" fill-rule="evenodd">
                                            <g transform="translate(1 1)" stroke-width="2">
                                                <circle stroke-opacity=".25" cx="18" cy="18" r="18"></circle>
                                                <path d="M36 18c0-9.94-8.06-18-18-18">
                                                    <animateTransform attributeName="transform" type="rotate" from="0 18 18" to="360 18 18" dur="0.8s" repeatCount="indefinite"></animateTransform>
                                                </path>
                                            </g>
                                        </g>
                                    </svg>
                                </div>
                            </div>

                            <!--Start form info Pic-->
                            <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">上傳身分證件
                                    <a data-v-9403d44c="" class="pr-md-3 float-right angleUpDown">
                                        <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-down fa-w-14 angleDown" style="display: none;">
                                            <path data-v-9403d44c="" fill="currentColor" d="M207.029 381.476L12.686 187.132c-9.373-9.373-9.373-24.569 0-33.941l22.667-22.667c9.357-9.357 24.522-9.375 33.901-.04L224 284.505l154.745-154.021c9.379-9.335 24.544-9.317 33.901.04l22.667 22.667c9.373 9.373 9.373 24.569 0 33.941L240.971 381.476c-9.373 9.372-24.569 9.372-33.942 0z" class=""></path>
                                        </svg>
                                        <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-up" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-up fa-w-14 angleUp">
                                            <path data-v-9403d44c="" fill="currentColor" d="M240.971 130.524l194.343 194.343c9.373 9.373 9.373 24.569 0 33.941l-22.667 22.667c-9.357 9.357-24.522 9.375-33.901.04L224 227.495 69.255 381.516c-9.379 9.335-24.544 9.317-33.901-.04l-22.667-22.667c-9.373-9.373-9.373-24.569 0-33.941L207.03 130.525c9.372-9.373 24.568-9.373 33.941-.001z" class=""></path>
                                        </svg>
                                    </a>
                                </h4>
                                <hr class="login100-form-title p-b-10">
                                <!-- <div class="login100-form validate-form"> -->
                                
                                <form class="registerform" enctype="multipart/form-data">
<!--                                 <div class="txt-vrfPics-hint m-b-10">請上傳清晰證件照三張<br>包含身分證正反面及第二證件正面</div> -->
										<div class="txt-vrfPics-hint m-b-10">請上傳清晰證件照 - 身分證正面</div> 
                                        <div class="wrapperBackground">
                                            <div class="wrapperUploadHandler" id=wrapperUploadHandler ondrop="dropUploadHandler(event)">
                                                <label id="fileInput" for="inputF">
                                                    <input ondrop="dropUploadHandler(event)" type="file" id="inputF" name="tnt_id_picf" multiple> Upload Files
                                                </label>
                                                <button type="button" id="del" ondrop="dropDelHandler(event)">Delete</button>
                                            </div>
                                        </div>
                                        <div id="picWrapper">
                                            <!-- <div class="grid" id="grid"> -->
                                            <!-- </div> -->
                                        </div>
                                </form>
                                <form class="registerform" enctype="multipart/form-data">
                                <div class="txt-vrfPics-hint m-b-10">請上傳清晰證件照 - 身分證背面</div> 
                                        <div class="wrapperBackground">
                                            <div class="wrapperUploadHandler" id=wrapperUploadHandler ondrop="dropUploadHandler(event)">
                                                <label id="fileInput2" for="inputF2">
                                                    <input ondrop="dropUploadHandler(event)" type="file" id="inputF2" name="tnt_id_picb" multiple> Upload Files
                                                </label>
                                                <button type="button" id="del2" ondrop="dropDelHandler(event)">Delete</button>
                                            </div>
                                        </div>
                                        <div id="picWrapper2">
                                            <!-- <div class="grid" id="grid"> -->
                                            <!-- </div> -->
                                        </div>
                                </form>
                                <form class="registerform" enctype="multipart/form-data">
                                <div class="txt-vrfPics-hint m-b-10">請上傳清晰證件照 - 第二證件正面</div> 
                                        <div class="wrapperBackground">
                                            <div class="wrapperUploadHandler" id=wrapperUploadHandler ondrop="dropUploadHandler(event)">
                                                <label id="fileInput3" for="inputF3">
                                                    <input ondrop="dropUploadHandler(event)" type="file" id="inputF3" name="tnt_id_pic2" multiple> Upload Files
                                                </label>
                                                <button type="button" id="del3" ondrop="dropDelHandler(event)">Delete</button>
                                            </div>
                                        </div>
                                        <div id="picWrapper3">
                                            <!-- <div class="grid" id="grid"> -->
                                            <!-- </div> -->
                                        </div>
                                </form>

                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-16 infoBtn" id="btninfoPic">儲存資訊</button>
                                </div>
                            </div>
                            <!--End form info Pic-->

                            <!--forms outer -->
                        </div>
                    </div>
                    
                    
                    <!--outer -->
                </div>
            </div>
        </section>

    </section>


    <!--  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
    </script> -->


    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/daterangepicker/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/jquery.datetimepicker.full.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/verify_tnt.js"></script>
    <!--===============================================================================================-->



</body>

</html>