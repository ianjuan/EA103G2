<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.lld.model.*"%>

<% session.removeAttribute("tnt_no"); %>
<% String lld_no = (String) session.getAttribute("lld_no");%>
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />

<%
	LldVO lldVO = lldSvc.getOneLldProfile(lld_no);
	request.setAttribute("lldVO", lldVO);
	LldVO lldVO_pocket = lldSvc.getOneLldPocket(lld_no);
	request.setAttribute("lldVO_pocket", lldVO_pocket);
	LldVO lldVO_bankcard = lldSvc.getOneLldBankCard(lld_no);
	request.setAttribute("lldVO_bankcard", lldVO_bankcard);
%>
<%
    String bank = lldVO_bankcard.getLld_bank();
	String bankinfoStr = "";
	Boolean hasBankinfoJS = false;
	if (bank != null){
		String bankAccEnd = lldVO_bankcard.getLld_bankacc();
		int tmp = bankAccEnd.length();
		bankAccEnd = bankAccEnd.substring(tmp-4);
		bankinfoStr = bank + "&nbsp&nbsp****&nbsp" + bankAccEnd;
		hasBankinfoJS = true;
	} else {
		bankinfoStr = "尚未更新收款資訊";
	}
	String card = lldVO_bankcard.getLld_card();
	String cardinfoStr = "";
	Boolean hasCardinfoJS = false;
	if (card != null){
// 		String cardEnd = lldVO_bankcard.getLld_card();
		int tmp = card.length();
		card = card.substring(tmp-4);
		cardinfoStr = "台新銀行&nbsp&nbsp****&nbsp" + card;
		hasCardinfoJS = true;
	} else {
		cardinfoStr = "尚未更新付款資訊";
	}
	
	
	
	
// 	String bankAccEnd = lldVO_bankcard.getLld_bankacc();
// 	if (bankAccEnd != null){
// 		int tmp = bankAccEnd.length();
// 		bankAccEnd = bankAccEnd.substring(tmp-4);
// 	}
// 	String cardEnd = lldVO_bankcard.getLld_card();
// 	if (cardEnd != null){
// 		int tmp = cardEnd.length();
// 		cardEnd = cardEnd.substring(tmp-4);
// 	}
	
	
%> 

<head>
    <title>myPocket</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&family=Open+Sans:ital,wght@1,600&family=Pacifico&display=swap" rel="stylesheet">
    <!-- <link rel="stylesheet" href="css/style.css"> -->

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/lld/images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <!-- <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/haburgers.min.css"> -->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/css/jquery.datetimepicker.css">
    <!--===============================================================================================-->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/css/util.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/css/pocket_lld.css">
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
                                     <img src="<%=request.getContextPath()%>/ImgReader?id=${lldVO.lld_no}" width="110" class="imgBigHeadPic">
                                 </a>
                            </div>
                            <p class="text-gray text-center mb-3">房東</p>
                            <h4 class="text-center">${lldVO.lld_name} 個人資訊</h4>
                            <ul class="basicInfo__menu mt-4 border-top mx-2 px-4 py-5">
                                <li class="mb-3">                                  
                                    <a href="<%=request.getContextPath()%>/front-end/lld/info.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/meminfoprofile.png" width="30" class="mr-2">基本資訊
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/verify.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_vrf.png" width="30" class="mr-2">身分驗證
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a class="basicInfo__menu__link nuxt-link-exact-active nuxt-link-active">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_pocket.png" width="30" class="mr-2">我的錢包
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="/basicInfo/account" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_rsv.png" width="30" class="mr-2">我的預約
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/cont/ConServlet?action=getlldcontract" class="basicInfo__menu__link">
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
                                <div class="row no-gutters justify-content-around">
<!--                                     justify-content-between -->
                                        <div class="pocketTitleSmall" style="font-size:30px; margin:0px">
                                        	<p class="pocketTitleSmall" style="display: contents;color: #212529; width:40%">錢包餘額:<span style="color:#fff;">_</span>
                                        	</p>$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${lldVO_pocket.lld_balance}" /> 元</div>
                                    </div>
                                
                                    <hr class="login100-form-title p-b-10">
<!--                                 class="balanceInputform"    id="withdrawform" id="registerform"-->
                                    <form  class="balanceInputform" id="withdrawform" enctype="multipart/form-data">
		                                    <div class="wrap-register100 validate-input" data-validate="Please enter a positive integer">
		                                        <input class="register100" type="text" name="pocket_withdraw" id="pocket_withdraw">
		                                        <span class="focus-register100"></span>
		                                        <span class="label-register100">請輸入提款金額</span>
		                                    </div>
                                    </form>
                                    
                                    <div class="row no-gutters justify-content-around" > 
				                            <div class="bankTitleSmall">收款帳號<br><span style="color: #444;margin-left:30px"><%=bankinfoStr%></span></div>
				                            <div class="wrap-btnBalanceSmall" style=" display: inline-block;">
				                               <button class="btnBalanceSmall" id="btnbalanceWithdraw">提領</button>
				                            </div>
				                    </div>
				                    <hr style="margin: 25px 40px;">
				                    <form class="balanceInputform" id="depositform" enctype="multipart/form-data">
		                                    <div class="wrap-register100 validate-input" data-validate="Please enter a positive integer">
		                                        <input class="register100" type="text" name="pocket_deposit" id="pocket_deposit">
		                                        <span class="focus-register100"></span>
		                                        <span class="label-register100">請輸入儲值金額</span>
		                                    </div>
                                    </form>
				                    <div class="row no-gutters justify-content-around" > 
				                            <div class="bankTitleSmall">付款信用卡<br><span style="color: #444;margin-left:30px"><%=cardinfoStr%></span></div>
				                            <div class="wrap-btnBalanceSmall" style=" display: inline-block;">
				                               <button class="btnBalanceSmall" id="btnbalanceDeposit">儲值</button>
				                            </div>
				                    </div>

                                
                            </div>
                            <!--End form1 Pocket -->

                            <!--Start form2 upcoming Money-->
<!--                             <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4"> -->
<!--                                 <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">待收/待繳紀錄 -->
<!--                                     <a data-v-9403d44c="" class="pr-md-3 float-right angleUpDown"> -->
<!--                                         <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-down fa-w-14 angleDown" style="display: none;"> -->
<!--                                             <path data-v-9403d44c="" fill="currentColor" d="M207.029 381.476L12.686 187.132c-9.373-9.373-9.373-24.569 0-33.941l22.667-22.667c9.357-9.357 24.522-9.375 33.901-.04L224 284.505l154.745-154.021c9.379-9.335 24.544-9.317 33.901.04l22.667 22.667c9.373 9.373 9.373 24.569 0 33.941L240.971 381.476c-9.373 9.372-24.569 9.372-33.942 0z" class=""></path> -->
<!--                                         </svg> -->
<!--                                         <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-up" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-up fa-w-14 angleUp"> -->
<!--                                             <path data-v-9403d44c="" fill="currentColor" d="M240.971 130.524l194.343 194.343c9.373 9.373 9.373 24.569 0 33.941l-22.667 22.667c-9.357 9.357-24.522 9.375-33.901.04L224 227.495 69.255 381.516c-9.379 9.335-24.544 9.317-33.901-.04l-22.667-22.667c-9.373-9.373-9.373-24.569 0-33.941L207.03 130.525c9.372-9.373 24.568-9.373 33.941-.001z" class=""></path> -->
<!--                                         </svg> -->
<!--                                     </a> -->
<!--                                 </h4> -->
<!--                                 <hr class="login100-form-title p-b-10"> -->
<!--                                 <div class="login100-form validate-form"> -->
<!--                                 <form id="registerform" enctype="multipart/form-data"> -->
<!--                                     form1 -->
<!--                                 </form> -->
<!--                                 <div class="container-login100-form-btn"> -->
<!--                                     <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button> -->
<!--                                     id="btnProfile" -->
<!--                                 </div> -->
<!--                             </div> -->
                            <!--End form2 upcoming Money -->

                            <!--Start form3 history Money-->
<!--                             <div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4"> -->
<!--                                 <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">歷史紀錄 -->
<!--                                     <a data-v-9403d44c="" class="pr-md-3 float-right angleUpDown"> -->
<!--                                         <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-down fa-w-14 angleDown" style="display: none;"> -->
<!--                                             <path data-v-9403d44c="" fill="currentColor" d="M207.029 381.476L12.686 187.132c-9.373-9.373-9.373-24.569 0-33.941l22.667-22.667c9.357-9.357 24.522-9.375 33.901-.04L224 284.505l154.745-154.021c9.379-9.335 24.544-9.317 33.901.04l22.667 22.667c9.373 9.373 9.373 24.569 0 33.941L240.971 381.476c-9.373 9.372-24.569 9.372-33.942 0z" class=""></path> -->
<!--                                         </svg> -->
<!--                                         <svg data-v-9403d44c="" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="chevron-up" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-chevron-up fa-w-14 angleUp"> -->
<!--                                             <path data-v-9403d44c="" fill="currentColor" d="M240.971 130.524l194.343 194.343c9.373 9.373 9.373 24.569 0 33.941l-22.667 22.667c-9.357 9.357-24.522 9.375-33.901.04L224 227.495 69.255 381.516c-9.379 9.335-24.544 9.317-33.901-.04l-22.667-22.667c-9.373-9.373-9.373-24.569 0-33.941L207.03 130.525c9.372-9.373 24.568-9.373 33.941-.001z" class=""></path> -->
<!--                                         </svg> -->
<!--                                     </a> -->
<!--                                 </h4> -->
<!--                                 <hr class="login100-form-title p-b-10"> -->
<!--                                 <div class="login100-form validate-form"> -->
<!--                                 <form id="registerform" enctype="multipart/form-data"> -->
<!--                                     form1 -->
<!--                                 </form> -->
<!--                                 <div class="container-login100-form-btn"> -->
<!--                                     <button class="login100-form-btn m-t-10 infoBtn" id="btninfoProfile">儲存資訊</button> -->
<!--                                     id="btnProfile" -->
<!--                                 </div> -->
<!--                             </div> -->
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
                                    <div class="pocketTitleSmall">收款帳號設定</div>
                                    <select class="wrap-register100 validate-input" data-validate="Bank is required" name="lld_bank" id="lld_bank">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">
                                            <option value="">選擇銀行及代號
                                            <option data-v-9403d44c="" value="台灣銀行">004 台灣銀行</option>
                                            <option data-v-9403d44c="" value="土地銀行">005 土地銀行</option>
                                            <option data-v-9403d44c="" value="合庫商銀">006 合庫商銀</option>
                                            <option data-v-9403d44c="" value="第一銀行">007 第一銀行</option>
                                            <option data-v-9403d44c="" value="華南銀行">008 華南銀行</option>
                                            <option data-v-9403d44c="" value="彰化銀行">009 彰化銀行</option>
                                            <option data-v-9403d44c="" value="上海銀行">011 上海銀行</option>
                                            <option data-v-9403d44c="" value="台北富邦">012 台北富邦</option>
                                            <option data-v-9403d44c="" value="國泰世華">013 國泰世華</option>
                                            <option data-v-9403d44c="" value="高雄銀行">016 高雄銀行</option>
                                            <option data-v-9403d44c="" value="兆豐商銀">017 兆豐商銀</option>
                                            <option data-v-9403d44c="" value="農業金庫">018 農業金庫</option>
                                            <option data-v-9403d44c="" value="花旗銀行">021 花旗銀行</option>
                                            <option data-v-9403d44c="" value="澳盛銀行">039 澳盛銀行</option>
                                            <option data-v-9403d44c="" value="王道銀行">048 王道銀行</option>
                                            <option data-v-9403d44c="" value="台灣企銀">050 台灣企銀</option>
                                            <option data-v-9403d44c="" value="渣打商銀">052 渣打商銀</option>
                                            <option data-v-9403d44c="" value="台中商銀">053 台中商銀</option>
                                            <option data-v-9403d44c="" value="京城商銀">054 京城商銀</option>
                                            <option data-v-9403d44c="" value="匯豐銀行">081 匯豐銀行</option>
                                            <option data-v-9403d44c="" value="瑞興銀行">101 瑞興銀行</option>
                                            <option data-v-9403d44c="" value="華泰銀行">102 華泰銀行</option>
                                            <option data-v-9403d44c="" value="臺灣新光銀行">103 臺灣新光銀行</option>
                                            <option data-v-9403d44c="" value="中華郵政">700 中華郵政</option>
                                            <option data-v-9403d44c="" value="聯邦銀行">803 聯邦銀行</option>
                                            <option data-v-9403d44c="" value="遠東銀行">805 遠東銀行</option>
                                            <option data-v-9403d44c="" value="元大銀行">806 元大銀行</option>
                                            <option data-v-9403d44c="" value="永豐銀行">807 永豐銀行</option>
                                            <option data-v-9403d44c="" value="玉山銀行">808 玉山銀行</option>
                                            <option data-v-9403d44c="" value="凱基銀行">809 凱基銀行</option>
                                            <option data-v-9403d44c="" value="星展銀行">810 星展銀行</option>
                                            <option data-v-9403d44c="" value="台新銀行">812 台新銀行</option>
                                            <option data-v-9403d44c="" value="大眾銀行">814 大眾銀行</option>
                                            <option data-v-9403d44c="" value="日盛銀行">815 日盛銀行</option>
                                            <option data-v-9403d44c="" value="安泰銀行">816 安泰銀行</option>
                                            <option data-v-9403d44c="" value="中國信託">822 中國信託</option>
                                            <option data-v-9403d44c="" value="美國銀行">022 美國銀行</option>
                                            <option data-v-9403d44c="" value="首都銀行">025 首都銀行</option>
                                            <option data-v-9403d44c="" value="中華開發">040 中華開發</option>
                                            <option data-v-9403d44c="" value="德意志銀行">072 德意志銀行</option>
                                            <option data-v-9403d44c="" value="東亞銀行">075 東亞銀行</option>
                                            <option data-v-9403d44c="" value="巴黎銀行">082 巴黎銀行</option>
                                            <option data-v-9403d44c="" value="台北五信">104 台北五信</option>
                                            <option data-v-9403d44c="" value="台北九信">106 台北九信</option>
                                            <option data-v-9403d44c="" value="陽信銀行">108 陽信銀行</option>
                                            <option data-v-9403d44c="" value="基隆一信">114 基隆一信</option>
                                            <option data-v-9403d44c="" value="基隆二信">115 基隆二信</option>
                                            <option data-v-9403d44c="" value="板信銀行">118 板信銀行</option>
                                            <option data-v-9403d44c="" value="淡水一信">119 淡水一信</option>
                                            <option data-v-9403d44c="" value="淡水信合社">120 淡水信合社</option>
                                            <option data-v-9403d44c="" value="宜蘭信合社">124 宜蘭信合社</option>
                                            <option data-v-9403d44c="" value="桃園信合社">127 桃園信合社</option>
                                            <option data-v-9403d44c="" value="新竹一信">130 新竹一信</option>
                                            <option data-v-9403d44c="" value="新竹三信">132 新竹三信</option>
                                            <option data-v-9403d44c="" value="台中二信">146 台中二信</option>
                                            <option data-v-9403d44c="" value="三信銀行">147 三信銀行</option>
                                            <option data-v-9403d44c="" value="彰化一信">158 彰化一信</option>
                                            <option data-v-9403d44c="" value="彰化五信">161 彰化五信</option>
                                            <option data-v-9403d44c="" value="彰化六信">162 彰化六信</option>
                                            <option data-v-9403d44c="" value="彰化十信">163 彰化十信</option>
                                            <option data-v-9403d44c="" value="鹿港信合社">165 鹿港信合社</option>
                                            <option data-v-9403d44c="" value="嘉義三信">178 嘉義三信</option>
                                            <option data-v-9403d44c="" value="嘉義四信">179 嘉義四信</option>
                                            <option data-v-9403d44c="" value="台南三信">188 台南三信</option>
                                            <option data-v-9403d44c="" value="高雄三信">204 高雄三信</option>
                                            <option data-v-9403d44c="" value="花蓮一信">215 花蓮一信</option>
                                            <option data-v-9403d44c="" value="花蓮二信">216 花蓮二信</option>
                                            <option data-v-9403d44c="" value="澎湖一信">222 澎湖一信</option>
                                            <option data-v-9403d44c="" value="澎湖二信">223 澎湖二信</option>
                                            <option data-v-9403d44c="" value="金門信合社">224 金門信合社</option>
                                            <option data-v-9403d44c="" value="通苑區漁會">508 通苑區漁會</option>
                                            <option data-v-9403d44c="" value="南龍區漁會">510 南龍區漁會</option>
                                            <option data-v-9403d44c="" value="彰化區漁會">511 彰化區漁會</option>
                                            <option data-v-9403d44c="" value="雲林區漁會">512 雲林區漁會</option>
                                            <option data-v-9403d44c="" value="嘉義區漁會">515 嘉義區漁會</option>
                                            <option data-v-9403d44c="" value="南市區漁會">517 南市區漁會</option>
                                            <option data-v-9403d44c="" value="南縣區漁會">518 南縣區漁會</option>
                                            <option data-v-9403d44c="" value="小港漁會">520 小港漁會</option>
                                            <option data-v-9403d44c="" value="彌陀永安興達林園漁會">521 彌陀永安興達林園漁會</option>
                                            <option data-v-9403d44c="" value="東港林邊琉球區漁會">523 東港林邊琉球區漁會</option>
                                            <option data-v-9403d44c="" value="新港漁會">524 新港漁會</option>
                                            <option data-v-9403d44c="" value="澎湖區漁會">525 澎湖區漁會</option>
                                            <option data-v-9403d44c="" value="麻豆農會">542 麻豆農會</option>
                                            <option data-v-9403d44c="" value="下營農會">549 下營農會</option>
                                            <option data-v-9403d44c="" value="官田農會">551 官田農會</option>
                                            <option data-v-9403d44c="" value="大內農會">552 大內農會</option>
                                            <option data-v-9403d44c="" value="新市農會">557 新市農會</option>
                                            <option data-v-9403d44c="" value="安定農會">558 安定農會</option>
                                            <option data-v-9403d44c="" value="仁德農會">562 仁德農會</option>
                                            <option data-v-9403d44c="" value="南化農會">567 南化農會</option>
                                            <option data-v-9403d44c="" value="七股區農會">568 七股區農會</option>
                                            <option data-v-9403d44c="" value="農金資中心">600 農金資中心</option>
                                            <option data-v-9403d44c="" value="高雄市農會">605 高雄市農會</option>
                                            <option data-v-9403d44c="" value="新北市地區農會">606 新北市地區農會</option>
                                            <option data-v-9403d44c="" value="宜蘭農會">607 宜蘭農會</option>
                                            <option data-v-9403d44c="" value="桃園地區農會">608 桃園地區農會</option>
                                            <option data-v-9403d44c="" value="神岡鄉農會">612 神岡鄉農會</option>
                                            <option data-v-9403d44c="" value="名間鄉農會">613 名間鄉農會</option>
                                            <option data-v-9403d44c="" value="彰化地區農會">614 彰化地區農會</option>
                                            <option data-v-9403d44c="" value="雲林地區農會">616 雲林地區農會</option>
                                            <option data-v-9403d44c="" value="嘉義地區農會">617 嘉義地區農會</option>
                                            <option data-v-9403d44c="" value="台南地區農會">618 台南地區農會</option>
                                            <option data-v-9403d44c="" value="高雄地區農會">619 高雄地區農會</option>
                                            <option data-v-9403d44c="" value="屏東地區農會">620 屏東地區農會</option>
                                            <option data-v-9403d44c="" value="花蓮地區農會">621 花蓮地區農會</option>
                                            <option data-v-9403d44c="" value="台東地區農會">622 台東地區農會</option>
                                            <option data-v-9403d44c="" value="澎湖區農會">624 澎湖區農會</option>
                                            <option data-v-9403d44c="" value="台中市農會">625 台中市農會</option>
                                            <option data-v-9403d44c="" value="連江縣農會">627 連江縣農會</option>
                                            <option data-v-9403d44c="" value="北斗農會">633 北斗農會</option>
                                            <option data-v-9403d44c="" value="線西農會">635 線西農會</option>
                                            <option data-v-9403d44c="" value="伸港鄉農會">636 伸港鄉農會</option>
                                            <option data-v-9403d44c="" value="福興農會">650 福興農會</option>
                                            <option data-v-9403d44c="" value="彰化市農會">651 彰化市農會</option>
                                            <option data-v-9403d44c="" value="梧棲區農會">870 梧棲區農會</option>
                                            <option data-v-9403d44c="" value="大肚區農會">882 大肚區農會</option>
                                            <option data-v-9403d44c="" value="大里市農會">901 大里市農會</option>
                                            <option data-v-9403d44c="" value="汐止市農會">903 汐止市農會</option>
                                            <option data-v-9403d44c="" value="新莊市農會">904 新莊市農會</option>
                                            <option data-v-9403d44c="" value="桃園新竹區農會">910 桃園新竹區農會</option>
                                            <option data-v-9403d44c="" value="冬山鄉農會">912 冬山鄉農會</option>
                                            <option data-v-9403d44c="" value="西湖鄉農會">915 西湖鄉農會</option>
                                            <option data-v-9403d44c="" value="草屯鎮農會">916 草屯鎮農會</option>
                                            <option data-v-9403d44c="" value="三義農會">919 三義農會</option>
                                            <option data-v-9403d44c="" value="南庄鄉農會">921 南庄鄉農會</option>
                                            <option data-v-9403d44c="" value="台南市農會">922 台南市農會</option>
                                            <option data-v-9403d44c="" value="板橋市農會">928 板橋市農會</option>
                                            <option data-v-9403d44c="" value="新北市農會北區共用中心">951 新北市農會北區共用中心</option>
                                            <option data-v-9403d44c="" value="田尾鄉農會">953 田尾鄉農會</option>
                                            <option data-v-9403d44c="" value="農漁會中區共用中心">954 農漁會中區共用中心</option>
                                            <option data-v-9403d44c="" value="關貿網路">995 關貿網路</option>
                                            <option data-v-9403d44c="" value="台北區支付處">996 台北區支付處</option>
                                            <option data-v-9403d44c="" value="信合社南區資訊中心">997 信合社南區資訊中心</option>
                                            <option data-v-9403d44c="" value="金融聯合資訊中心">998 金融聯合資訊中心</option>
                                            <option data-v-9403d44c="" value="票據交換所">999 票據交換所</option>

                                        </span>
                                    </select>
                                    <div class="wrap-register100 validate-input" data-validate="Bank branch is required">
                                        <input class="register100" type="text" name="lld_bankbranch" id="lld_bankbranch">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">銀行分行</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Bank account is required">
                                        <input class="register100" type="text" name="lld_bankacc" id="lld_bankacc">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">銀行帳號</span>
                                    </div>
									<hr style="margin: 25px -40px;">
                                    <div class="pocketTitleSmall">付款資訊設定</div>
                                    <div class="wrap-register100 validate-input" data-validate="Valid credit card is required">
                                        <input class="register100" type="text" name="lld_card" id="lld_card">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">信用卡卡號</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Card security code is required">
                                        <input class="register100" type="text" name="lld_cardsvc" id="lld_cardsvc" maxlength="3" size="3">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">信用卡安全碼</span>
                                    </div>
                                    <div class="wrap-register100 validate-input" data-validate="Valid due date is required">
                                        <input class="register100" type="month" name="lld_carddue" id="lld_carddue" min="">
                                        <span class="focus-register100"></span>
                                        <span class="label-register100 label-register100-carddue"></span>
                                    </div>

                                </form>
                                <div class="container-login100-form-btn">
                                    <button class="login100-form-btn m-t-10 infoBtn" id="btnBankCard">儲存資訊</button>
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
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/daterangepicker/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/lld/js/jquery.datetimepicker.full.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/js/pocket_lld.js"></script>
    <!--===============================================================================================-->
    
<%--     <% --%>
<!--      String[] bankArray = {"台灣銀行","土地銀行","合庫商銀","第一銀行","華南銀行","彰化銀行","上海銀行","台北富邦","國泰世華","高雄銀行","兆豐商銀","農業金庫","花旗銀行","澳盛銀行","王道銀行","台灣企銀","渣打商銀","台中商銀","京城商銀","匯豐銀行","瑞興銀行","華泰銀行","臺灣新光銀行","中華郵政","聯邦銀行","遠東銀行","元大銀行","永豐銀行","玉山銀行","凱基銀行","星展銀行","台新銀行","大眾銀行","日盛銀行","安泰銀行","中國信託","美國銀行","首都銀行","中華開發","德意志銀行","東亞銀行","巴黎銀行","台北五信","台北九信","陽信銀行","基隆一信","基隆二信","板信銀行","淡水一信","淡水信合社","宜蘭信合社","桃園信合社","新竹一信","新竹三信","台中二信","三信銀行","彰化一信","彰化五信","彰化六信","彰化十信","鹿港信合社","嘉義三信","嘉義四信","台南三信","高雄三信","花蓮一信","花蓮二信","澎湖一信","澎湖二信","金門信合社","通苑區漁會","南龍區漁會","彰化區漁會","雲林區漁會","嘉義區漁會","南市區漁會","南縣區漁會","小港漁會","彌陀永安興達林園漁會","東港林邊琉球區漁會","新港漁會","澎湖區漁會","麻豆農會","下營農會","官田農會","大內農會","新市農會","安定農會","仁德農會","南化農會","七股區農會","農金資中心","高雄市農會","新北市地區農會","宜蘭農會","桃園地區農會","神岡鄉農會","名間鄉農會","彰化地區農會","雲林地區農會","嘉義地區農會","台南地區農會","高雄地區農會","屏東地區農會","花蓮地區農會","台東地區農會","澎湖區農會","台中市農會","連江縣農會","北斗農會","線西農會","伸港鄉農會","福興農會","彰化市農會","梧棲區農會","大肚區農會","大里市農會","汐止市農會","新莊市農會","桃園新竹區農會","冬山鄉農會","西湖鄉農會","草屯鎮農會","三義農會","南庄鄉農會","台南市農會","板橋市農會","新北市農會北區共用中心","田尾鄉農會","農漁會中區共用中心","關貿網路","台北區支付處","信合社南區資訊中心","金融聯合資訊中心","票據交換所"}; -->
<!--      String[] bankCode = {"004","005","006","007","008","009","011","012","013","016","017","018","021","039","048","050","052","053","054","081","101","102","103","700","803","805","806","807","808","809","810","812","814","815","816","822","022","025","040","072","075","082","104","106","108","114","115","118","119","120","124","127","130","132","146","147","158","161","162","163","165","178","179","188","204","215","216","222","223","224","508","510","511","12","515","517","518","520","521","523","524","525","542","549","551","552","557","558","562","567","568","600","605","606","607","608","612","613","614","616","617","618","619","620","621","622","624","625","627","633","635","636","650","651","870","882","901","903","904","910","912","915","916","919","921","922","928","951","953","954","995","996","997","998","999"}; -->
<%--     %> --%>

<script>
	var hasBankinfoJS = <%=hasBankinfoJS%>;
	var hasCardinfoJS = <%=hasCardinfoJS%>;
</script>

</body>

</html>