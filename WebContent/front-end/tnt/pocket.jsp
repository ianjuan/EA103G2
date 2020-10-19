<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <title>myPocket</title>
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
    <!-- <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/haburgers.min.css"> -->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/jquery.datetimepicker.css">
    <!--===============================================================================================-->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/util.css">
    <!-- <link rel="stylesheet" type="text/css" href="css/main_register.css"> -->

    <!--===============================================================================================-->
    <!-- <link rel="stylesheet" type="text/css" href="../css/headerfooter.css"> -->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/pocket_tnt.css">
    <!-- <link rel="stylesheet" type="text/css" href="/EA103/front-end/tnt/css/tnt_info.css"> -->
    <!--===============================================================================================-->



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
    <!-- Header -->
    <header id="header">
        <h1>
            <a href="index.html" style="color: #555">愛租I-ZU</a>
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
    <section>
        <section class="content">
            <div class="container">
                <!--             style="border:1px solid orange" -->
                <div class="row no-gutters justify-content-between">
                    <!--start info list -->
                    <div class="basicInfo__list col-lg-3">
                        <div class="basicInfo__list__content bg-white px-4 pt-lg-7 pt-md-5 pt-2 sticky-top">
                            <a class="basicInfo__list-backIcon text-primary basicInfo__list__content-backIcon d-xl-none">
                                <svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="arrow-alt-circle-left" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-arrow-alt-circle-left fa-w-16">
                                    <path fill="currentColor" d="M8 256c0 137 111 248 248 248s248-111 248-248S393 8 256 8 8 119 8 256zm448 0c0 110.5-89.5 200-200 200S56 366.5 56 256 145.5 56 256 56s200 89.5 200 200zm-72-20v40c0 6.6-5.4 12-12 12H256v67c0 10.7-12.9 16-20.5 8.5l-99-99c-4.7-4.7-4.7-12.3 0-17l99-99c7.6-7.6 20.5-2.2 20.5 8.5v67h116c6.6 0 12 5.4 12 12z" class=""></path>
                                </svg>
                            </a>
                            <div class="basicInfo__userImg mx-auto mb-3">
                                <!-- <input type="file" accept="image/jpg, image/jpeg, image/png" name="name" class="d-none"> -->
                                <a class="upload">
                                    <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="camera" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-camera fa-w-16">
                                        <path fill="currentColor" d="M512 144v288c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V144c0-26.5 21.5-48 48-48h88l12.3-32.9c7-18.7 24.9-31.1 44.9-31.1h125.5c20 0 37.9 12.4 44.9 31.1L376 96h88c26.5 0 48 21.5 48 48zM376 288c0-66.2-53.8-120-120-120s-120 53.8-120 120 53.8 120 120 120 120-53.8 120-120zm-32 0c0 48.5-39.5 88-88 88s-88-39.5-88-88 39.5-88 88-88 88 39.5 88 88z" class=""></path>
                                    </svg>
                                </a>
                            </div>
                            <p class="text-gray text-center mb-3">房客</p>
                            <h4 class="text-center">吳宜靜 個人資訊</h4>
                            <ul class="basicInfo__menu mt-4 border-top mx-2 px-4 py-5">
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/info.jsp" class="basicInfo__menu__link">
                                        <img src="infoprofile.png" width="30" class="mr-2">基本資訊
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="/basicInfo/account" class="basicInfo__menu__link">
                                        <img src="https://www.dd-room.com/_nuxt/img/1d5e617.png" width="30" class="mr-2">身分驗證
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a class="basicInfo__menu__link nuxt-link-exact-active nuxt-link-active">
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
                            <!--Start form1 Pocket-->
                            <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">金額總覽
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
                                <form id="registerform" enctype="multipart/form-data">
                                    form1
                                </form>
                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button>
                                    <!--id="btnProfile"-->
                                </div>
                            </div>
                            <!--End form1 Pocket -->

                            <!--Start form2 upcoming Money-->
                            <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">待收/待繳紀錄
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
                                <form id="registerform" enctype="multipart/form-data">
                                    form1
                                </form>
                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button>
                                    <!--id="btnProfile"-->
                                </div>
                            </div>
                            <!--End form2 upcoming Money -->

                            <!--Start form3 history Money-->
                            <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">歷史紀錄
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
                                <form id="registerform" enctype="multipart/form-data">
                                    form1
                                </form>
                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button>
                                    <!--id="btnProfile"-->
                                </div>
                            </div>
                            <!--End form3 history Money -->

                            <!--Start form4 BankCard-->
                            <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">收付款設定
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
                                <form id="registerform" enctype="multipart/form-data">
                                    <div class="titlebankcard">收款帳號設定</div>
                                    <select class="wrap-register100 validate-input" data-validate="Bank is required" name="tnt_bank" id="tnt_bank">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">
                                            <option value="">選擇銀行及代號
                                            <option data-v-9403d44c="" value="004">004 台灣銀行</option>
                                            <option data-v-9403d44c="" value="005">005 土地銀行</option>
                                            <option data-v-9403d44c="" value="006">006 合庫商銀</option>
                                            <option data-v-9403d44c="" value="007">007 第一銀行</option>
                                            <option data-v-9403d44c="" value="008">008 華南銀行</option>
                                            <option data-v-9403d44c="" value="009">009 彰化銀行</option>
                                            <option data-v-9403d44c="" value="011">011 上海銀行</option>
                                            <option data-v-9403d44c="" value="012">012 台北富邦</option>
                                            <option data-v-9403d44c="" value="013">013 國泰世華</option>
                                            <option data-v-9403d44c="" value="016">016 高雄銀行</option>
                                            <option data-v-9403d44c="" value="017">017 兆豐商銀</option>
                                            <option data-v-9403d44c="" value="018">018 農業金庫</option>
                                            <option data-v-9403d44c="" value="021">021 花旗銀行</option>
                                            <option data-v-9403d44c="" value="039">039 澳盛銀行</option>
                                            <option data-v-9403d44c="" value="048">048 王道銀行</option>
                                            <option data-v-9403d44c="" value="050">050 台灣企銀</option>
                                            <option data-v-9403d44c="" value="052">052 渣打商銀</option>
                                            <option data-v-9403d44c="" value="053">053 台中商銀</option>
                                            <option data-v-9403d44c="" value="054">054 京城商銀</option>
                                            <option data-v-9403d44c="" value="081">081 匯豐銀行</option>
                                            <option data-v-9403d44c="" value="101">101 瑞興銀行</option>
                                            <option data-v-9403d44c="" value="102">102 華泰銀行</option>
                                            <option data-v-9403d44c="" value="103">103 臺灣新光銀行</option>
                                            <option data-v-9403d44c="" value="700">700 中華郵政</option>
                                            <option data-v-9403d44c="" value="803">803 聯邦銀行</option>
                                            <option data-v-9403d44c="" value="805">805 遠東銀行</option>
                                            <option data-v-9403d44c="" value="806">806 元大銀行</option>
                                            <option data-v-9403d44c="" value="807">807 永豐銀行</option>
                                            <option data-v-9403d44c="" value="808">808 玉山銀行</option>
                                            <option data-v-9403d44c="" value="809">809 凱基銀行</option>
                                            <option data-v-9403d44c="" value="810">810 星展銀行</option>
                                            <option data-v-9403d44c="" value="812">812 台新銀行</option>
                                            <option data-v-9403d44c="" value="814">814 大眾銀行</option>
                                            <option data-v-9403d44c="" value="815">815 日盛銀行</option>
                                            <option data-v-9403d44c="" value="816">816 安泰銀行</option>
                                            <option data-v-9403d44c="" value="822">822 中國信託</option>
                                            <option data-v-9403d44c="" value="022">022 美國銀行</option>
                                            <option data-v-9403d44c="" value="025">025 首都銀行</option>
                                            <option data-v-9403d44c="" value="040">040 中華開發</option>
                                            <option data-v-9403d44c="" value="072">072 德意志銀行</option>
                                            <option data-v-9403d44c="" value="075">075 東亞銀行</option>
                                            <option data-v-9403d44c="" value="082">082 巴黎銀行</option>
                                            <option data-v-9403d44c="" value="104">104 台北五信</option>
                                            <option data-v-9403d44c="" value="106">106 台北九信</option>
                                            <option data-v-9403d44c="" value="108">108 陽信銀行</option>
                                            <option data-v-9403d44c="" value="114">114 基隆一信</option>
                                            <option data-v-9403d44c="" value="115">115 基隆二信</option>
                                            <option data-v-9403d44c="" value="118">118 板信銀行</option>
                                            <option data-v-9403d44c="" value="119">119 淡水一信</option>
                                            <option data-v-9403d44c="" value="120">120 淡水信合社</option>
                                            <option data-v-9403d44c="" value="124">124 宜蘭信合社</option>
                                            <option data-v-9403d44c="" value="127">127 桃園信合社</option>
                                            <option data-v-9403d44c="" value="130">130 新竹一信</option>
                                            <option data-v-9403d44c="" value="132">132 新竹三信</option>
                                            <option data-v-9403d44c="" value="146">146 台中二信</option>
                                            <option data-v-9403d44c="" value="147">147 三信銀行</option>
                                            <option data-v-9403d44c="" value="158">158 彰化一信</option>
                                            <option data-v-9403d44c="" value="161">161 彰化五信</option>
                                            <option data-v-9403d44c="" value="162">162 彰化六信</option>
                                            <option data-v-9403d44c="" value="163">163 彰化十信</option>
                                            <option data-v-9403d44c="" value="165">165 鹿港信合社</option>
                                            <option data-v-9403d44c="" value="178">178 嘉義三信</option>
                                            <option data-v-9403d44c="" value="179">179 嘉義四信</option>
                                            <option data-v-9403d44c="" value="188">188 台南三信</option>
                                            <option data-v-9403d44c="" value="204">204 高雄三信</option>
                                            <option data-v-9403d44c="" value="215">215 花蓮一信</option>
                                            <option data-v-9403d44c="" value="216">216 花蓮二信</option>
                                            <option data-v-9403d44c="" value="222">222 澎湖一信</option>
                                            <option data-v-9403d44c="" value="223">223 澎湖二信</option>
                                            <option data-v-9403d44c="" value="224">224 金門信合社</option>
                                            <option data-v-9403d44c="" value="508">508 通苑區漁會</option>
                                            <option data-v-9403d44c="" value="510">510 南龍區漁會</option>
                                            <option data-v-9403d44c="" value="511">511 彰化區漁會</option>
                                            <option data-v-9403d44c="" value="512">512 雲林區漁會</option>
                                            <option data-v-9403d44c="" value="515">515 嘉義區漁會</option>
                                            <option data-v-9403d44c="" value="517">517 南市區漁會</option>
                                            <option data-v-9403d44c="" value="518">518 南縣區漁會</option>
                                            <option data-v-9403d44c="" value="520">520 小港漁會</option>
                                            <option data-v-9403d44c="" value="521">521 彌陀永安興達林園漁會</option>
                                            <option data-v-9403d44c="" value="523">523 東港林邊琉球區漁會</option>
                                            <option data-v-9403d44c="" value="524">524 新港漁會</option>
                                            <option data-v-9403d44c="" value="525">525 澎湖區漁會</option>
                                            <option data-v-9403d44c="" value="542">542 麻豆農會</option>
                                            <option data-v-9403d44c="" value="549">549 下營農會</option>
                                            <option data-v-9403d44c="" value="551">551 官田農會</option>
                                            <option data-v-9403d44c="" value="552">552 大內農會</option>
                                            <option data-v-9403d44c="" value="557">557 新市農會</option>
                                            <option data-v-9403d44c="" value="558">558 安定農會</option>
                                            <option data-v-9403d44c="" value="562">562 仁德農會</option>
                                            <option data-v-9403d44c="" value="567">567 南化農會</option>
                                            <option data-v-9403d44c="" value="568">568 七股區農會</option>
                                            <option data-v-9403d44c="" value="600">600 農金資中心</option>
                                            <option data-v-9403d44c="" value="605">605 高雄市農會</option>
                                            <option data-v-9403d44c="" value="606">606 新北市地區農會</option>
                                            <option data-v-9403d44c="" value="607">607 宜蘭農會</option>
                                            <option data-v-9403d44c="" value="608">608 桃園地區農會</option>
                                            <option data-v-9403d44c="" value="612">612 神岡鄉農會</option>
                                            <option data-v-9403d44c="" value="613">613 名間鄉農會</option>
                                            <option data-v-9403d44c="" value="614">614 彰化地區農會</option>
                                            <option data-v-9403d44c="" value="616">616 雲林地區農會</option>
                                            <option data-v-9403d44c="" value="617">617 嘉義地區農會</option>
                                            <option data-v-9403d44c="" value="618">618 台南地區農會</option>
                                            <option data-v-9403d44c="" value="619">619 高雄地區農會</option>
                                            <option data-v-9403d44c="" value="620">620 屏東地區農會</option>
                                            <option data-v-9403d44c="" value="621">621 花蓮地區農會</option>
                                            <option data-v-9403d44c="" value="622">622 台東地區農會</option>
                                            <option data-v-9403d44c="" value="624">624 澎湖區農會</option>
                                            <option data-v-9403d44c="" value="625">625 台中市農會</option>
                                            <option data-v-9403d44c="" value="627">627 連江縣農會</option>
                                            <option data-v-9403d44c="" value="633">633 北斗農會</option>
                                            <option data-v-9403d44c="" value="635">635 線西農會</option>
                                            <option data-v-9403d44c="" value="636">636 伸港鄉農會</option>
                                            <option data-v-9403d44c="" value="650">650 福興農會</option>
                                            <option data-v-9403d44c="" value="651">651 彰化市農會</option>
                                            <option data-v-9403d44c="" value="870">870 梧棲區農會</option>
                                            <option data-v-9403d44c="" value="882">882 大肚區農會</option>
                                            <option data-v-9403d44c="" value="901">901 大里市農會</option>
                                            <option data-v-9403d44c="" value="903">903 汐止市農會</option>
                                            <option data-v-9403d44c="" value="904">904 新莊市農會</option>
                                            <option data-v-9403d44c="" value="910">910 桃園新竹區農會</option>
                                            <option data-v-9403d44c="" value="912">912 冬山鄉農會</option>
                                            <option data-v-9403d44c="" value="915">915 西湖鄉農會</option>
                                            <option data-v-9403d44c="" value="916">916 草屯鎮農會</option>
                                            <option data-v-9403d44c="" value="919">919 三義農會</option>
                                            <option data-v-9403d44c="" value="921">921 南庄鄉農會</option>
                                            <option data-v-9403d44c="" value="922">922 台南市農會</option>
                                            <option data-v-9403d44c="" value="928">928 板橋市農會</option>
                                            <option data-v-9403d44c="" value="951">951 新北市農會北區共用中心</option>
                                            <option data-v-9403d44c="" value="953">953 田尾鄉農會</option>
                                            <option data-v-9403d44c="" value="954">954 農漁會中區共用中心</option>
                                            <option data-v-9403d44c="" value="995">995 關貿網路</option>
                                            <option data-v-9403d44c="" value="996">996 台北區支付處</option>
                                            <option data-v-9403d44c="" value="997">997 信合社南區資訊中心</option>
                                            <option data-v-9403d44c="" value="998">998 金融聯合資訊中心</option>
                                            <option data-v-9403d44c="" value="999">999 票據交換所</option>

                                        </span>
                                    </select>
                                    <div class="wrap-register100 validate-input" data-validate="Bank branch is required">
                                        <input class="register100" type="text" name="tnt_bankbranch" id="tnt_bankbranch">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">銀行分行</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Bank account is required">
                                        <input class="register100" type="text" name="tnt_bankacc" id="tnt_bankacc">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">銀行帳號</span>
                                    </div>
									<hr style="margin: 25px -40px;">
                                    <div class="titlebankcard">付款資訊設定</div>
                                    <div class="wrap-register100 validate-input" data-validate="Credit card is required">
                                        <input class="register100" type="text" name="tnt_card" id="tnt_card">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">信用卡卡號</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Card security code is required">
                                        <input class="register100" type="text" name="tnt_cardsvc" id="tnt_cardsvc">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">信用卡安全碼</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Due date is required">
                                        <input class="register100" type="month" name="tnt_carddue" id="tnt_carddue">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100 label-register100-carddue"></span>
                                    </div>

                                </form>
                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button>
                                    <!--id="btnProfile"-->
                                </div>
                            </div>
                            <!--End form4 BankCard -->






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
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/jquery.datetimepicker.full.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/pocket_tnt.js"></script>
    <!--===============================================================================================-->



</body>

</html>