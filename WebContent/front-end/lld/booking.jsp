<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lld.model.*"%>

<% session.removeAttribute("tnt_no"); %>
<% String lld_no = (String) session.getAttribute("lld_no");%>
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />

<%
	LldVO lldVO = lldSvc.getOneLldProfile(lld_no);
	request.setAttribute("lldVO", lldVO);
%>

<head>
    <title>Booking</title>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
<%--     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/vendor/select2/select2.min.css"> --%>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/css/util.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/lld/css/booking_lld.css">
    <!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css"> 


    <style>
        [data-v-9403d44c] .v-switch-label {
            color: #fff !important;
        }
        @media screen and (max-width:575px) {
            .button__small.input__toggle[data-v-9403d44c] {
                top: 5px;
                right: 40px;
            }
        }

		#infocontainer {
		    margin-right: auto;
		    margin-left: auto;
		    padding-right: 15px;
		    padding-left: 15px;
		    width: 80%;
		    margin-top: 3%; */
		 }
		 
		.text-primary {
   			color: #916a3c!important;
		}
		
/* 		 @media (max-width:1199.98px) { */
       @media (max-width:3000px) {
            .basicInfo__list {
                position: fixed;
                top: 54px;
                left: 0;
                background: #fff;
                box-shadow: 0 2px 4px 0 rgba(45, 45, 45, .2);
                z-index: 2;
                width: 320px;
                height: 100vh
            }

            .basicInfo__list__content {
                position: absolute;
                left: 0;
                top: 0;
                width: 100%
            }

            .basicInfo__list__content-backIcon {
                text-align: left;
            }
        }

        @media (max-width:575.98px) {
            .basicInfo__list {
                width: 70%
            }
        }
        
        .basicInfo__list-backIcon { 
             font-size: 30px; 
             display: block !important; 
             text-align: center; 
         } 
		.booking-iframe{
			    width: 100%;
    			height: 100%;
		}
		.booking-outside{
			    height: 850px;
		}
         
		.booking-iframe{
			    width: 100%;
    			height: 100%;
		}
		.booking-outside{
			    height: 850px;
		}
		.booking-iframe{
			    width: 100%;
    			height: 100%;
		}
		.booking-outside{
			    height: 850px;
		}
		#date-ok{
			width: 10px;
    		height: 10px;
    		background-color: blue;
    		border-radius: 30px;
    		float: left;
    		margin-top: 6px;
		}
		#date-ok-word{
			width: 81px;
    		float: left;
		}
		#date-no{
			width: 10px;
    		height: 10px;
    		background-color: yellow;
    		border-radius: 30px;
    		margin-left: 10px;
    	    float: left;
   		    margin-top: 6px;
		}
		#date-no-word{
			width: 81px;
    		float: left;
		}
    </style>

</head>

<body class="landing">
	<jsp:include page="/front-end/navbar/navbar.jsp"/>
    <section>
        <section class="content">
            <div class="" id="infocontainer">
<!--                 <div class="row no-gutters justify-content-center"> -->
                    <!--start info list -->
                    <div class="basicInfo__list"  id="basicinfolist" style="left:-320px;"> 
                        <div class="basicInfo__list__content bg-white px-4 pt-lg-7 pt-md-5 pt-2 sticky-top">
                            <a class="basicInfo__list-backIcon text-primary basicInfo__list__content-backIcon d-xl-none">
                                <svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="arrow-alt-circle-left" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-arrow-alt-circle-left fa-w-16">
                                    <path fill="currentColor" d="M8 256c0 137 111 248 248 248s248-111 248-248S393 8 256 8 8 119 8 256zm448 0c0 110.5-89.5 200-200 200S56 366.5 56 256 145.5 56 256 56s200 89.5 200 200zm-72-20v40c0 6.6-5.4 12-12 12H256v67c0 10.7-12.9 16-20.5 8.5l-99-99c-4.7-4.7-4.7-12.3 0-17l99-99c7.6-7.6 20.5-2.2 20.5 8.5v67h116c6.6 0 12 5.4 12 12z" class=""></path>
                                </svg>
                            </a>
                            <div class="basicInfo__userImg mx-auto mb-3 divBigHeadPic">
                                <a class="awrapBigHeadPic">
                                     <img src="<%=request.getContextPath()%>/ImgReader?id=${lldVO.lld_no}" width="110" class="imgBigHeadPic">
                                 </a>
                            </div>
                            <p class="text-gray text-center mb-3">房客</p>
                            <h4 class="text-center">${tntVO.tnt_name} 個人資訊</h4>
                            <ul class="basicInfo__menu mt-4 border-top mx-2 px-4 py-5">
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/info.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/meminfoprofile.png" width="30" class="mr-3">基本資訊
                                    </a>
                                </li>
                                <li class="mb-3">
                                    
                                    <a href="<%=request.getContextPath()%>/front-end/lld/verify.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_vrf.png" width="30" class="mr-3">身分驗證
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/pocket.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_pocket.png" width="30" class="mr-3">我的錢包
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/bills.jsp" class="basicInfo__menu__link">
                                        <img src="data:image/svg+xml;base64,PHN2ZyBpZD0i5ZyW5bGkXzEiIGRhdGEtbmFtZT0i5ZyW5bGkIDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmlld0JveD0iMCAwIDEwMCAxMDAiPjxkZWZzPjxzdHlsZT4uY2xzLTF7ZmlsbDojMmQyZDJkO308L3N0eWxlPjwvZGVmcz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Ik04Mi42Myw0My4yOWEzMi42OCwzMi42OCwwLDAsMC02NS4yNiwwLDIuMzMsMi4zMywwLDAsMC0uMDYuNTNWNTdDMTcuMzEsNzQuMzcsMzEuMzcsODcuNSw1MCw4Ny41UzgyLjY5LDc0LjM3LDgyLjY5LDU3VjQzLjgyQTIuMzMsMi4zMywwLDAsMCw4Mi42Myw0My4yOVpNNTAsMTcuNUEyNy42OSwyNy42OSwwLDEsMSwyMi4zMSw0NS4xOSwyNy43MiwyNy43MiwwLDAsMSw1MCwxNy41Wm0wLDY1QzM2LjYxLDgyLjUsMjYuMjksNzUsMjMuMjQsNjMuOTJhMzIuNjQsMzIuNjQsMCwwLDAsNTMuNTIsMEM3My43MSw3NSw2My4zOSw4Mi41LDUwLDgyLjVaIi8+PHBhdGggY2xhc3M9ImNscy0xIiBkPSJNNTAsNTMuNzFhMy4xNywzLjE3LDAsMCwxLTMuMTYtMy4xNmgtNWE4LjE2LDguMTYsMCwwLDAsNS42Niw3Ljc2VjYzaDVWNTguMzFBOC4xNiw4LjE2LDAsMCwwLDUwLDQyLjM5YTMuMTYsMy4xNiwwLDEsMSwzLjE2LTMuMTVoNWE4LjE4LDguMTgsMCwwLDAtNS42Ni03Ljc3VjI2LjgzaC01djQuNjRBOC4xNiw4LjE2LDAsMCwwLDUwLDQ3LjM5YTMuMTYsMy4xNiwwLDAsMSwwLDYuMzJaIi8+PC9zdmc+" width="30" class="mr-3">我的帳務</a>
                                </li>
                                <li class="mb-3">
                               		<a class="basicInfo__menu__link nuxt-link-exact-active nuxt-link-active">
<%--                                     <a href="<%=request.getContextPath()%>/front-end/lld/booking.jsp" class="basicInfo__menu__link"> --%>
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_rsv.png" width="30" class="mr-3">我的預約
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/cont/ConServlet?action=gettntcontract" class="basicInfo__menu__link">
                                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsSAAALEgHS3X78AAACIElEQVR4nO3dwVGDQBhA4V/Hu5ZgBTt2EEpJJ8ZOUkrswNkKKCFWECcz60Vi1A1hH/K+I1wCb4DAJuzN4XAIcdzagsUgMAaBMQiMQWAMAmMQGIPA3F3ycVJKDxGxjoiHwcpl2kfENue8r9366jv1lNJTROwi4n6wctneI6LLOb9NFqQcGb0xvnWM8lhzpNReQ9bGOOu+7KM/qw3iNeNnVfvooov6F6/lmrJEXUSsxtjuMYPscs6bwdIFSCltxgrifQiMQWAMAmMQGIPAGATGIDAGgTEIjEFgDAJjEBiDwBgEZszH77+WUurKGALZcThh8vGdJkFKjOfBUp7Jg3jKgjEIjEFgWl1D5vBjiCafsUmQ8u1lqb9QOctTFoxBYAwCYxAYg8AYBMYgMAaBMQiMQWAMAmMQmFZDuJsZjBi+tPhHmEcIjEFgDAJjEJhWQ7jbGYwY9oMlE2g1hNu32mA6T1kwBoExCIxBYAwCYxAYg8AYBMYgMAaBMQhMqxHDx+N7bQcrLtOXZ2Sz1upp7/oKQ7gvETH7l3B6yoIxCIxBYAwC02rEcPMfLsDX4BECYxAYg8AYBMYgMAaBMQiMQWAMAmMQGIPAjPksqyv/HVyi0V55O2aQ1VhTxy1Z7SmrejbkBanaR7VBtmUCXp32XvbRNEHKLMidUU76nL676gipnk89nOD+lHYT3Os6vA+BMQiMQWAMAmMQGIPAGATGICQR8QGjXWHJ4UpOVwAAAABJRU5ErkJggg==" width="30" class="mr-3">我的合約</a>
                                </li>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--end info list -->
                    <!--start shrink bar -->
                    <div class="shrink">
                        <div class="bg-white pt-5 pb-0 sticky-top h-100">
	                        <a class="basicInfo__list-backIcon text-primary mb-3" id="shrinkArrow">
		                        <svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="arrow-alt-circle-right" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-arrow-alt-circle-right fa-w-16">
		                             <path fill="currentColor" d="M504 256C504 119 393 8 256 8S8 119 8 256s111 248 248 248 248-111 248-248zm-448 0c0-110.5 89.5-200 200-200s200 89.5 200 200-89.5 200-200 200S56 366.5 56 256zm72 20v-40c0-6.6 5.4-12 12-12h116v-67c0-10.7 12.9-16 20.5-8.5l99 99c4.7 4.7 4.7 12.3 0 17l-99 99c-7.6 7.6-20.5 2.2-20.5-8.5v-67H140c-6.6 0-12-5.4-12-12z" class="">
		                             </path>
		                        </svg>
	                        </a>	                        
                            <div class="border-top w-50 mx-auto"></div>
                            <ul class="basicInfo__menu mt-4 shrink__list mb-0 pl-0">
                                <li class="mb-3 w-100">
                              		<a href="<%=request.getContextPath()%>/front-end/lld/info.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/meminfoprofile.png" width="30" height="30">
                                    </a>
                                </li>
                                
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/verify.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_vrf.png" width="30" height="30">
                                    </a>
                                </li>
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/pocket.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_pocket.png" width="30" height="30">
                                    </a>
                                </li>
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/lld/bills.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="data:image/svg+xml;base64,PHN2ZyBpZD0i5ZyW5bGkXzEiIGRhdGEtbmFtZT0i5ZyW5bGkIDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmlld0JveD0iMCAwIDEwMCAxMDAiPjxkZWZzPjxzdHlsZT4uY2xzLTF7ZmlsbDojMmQyZDJkO308L3N0eWxlPjwvZGVmcz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Ik04Mi42Myw0My4yOWEzMi42OCwzMi42OCwwLDAsMC02NS4yNiwwLDIuMzMsMi4zMywwLDAsMC0uMDYuNTNWNTdDMTcuMzEsNzQuMzcsMzEuMzcsODcuNSw1MCw4Ny41UzgyLjY5LDc0LjM3LDgyLjY5LDU3VjQzLjgyQTIuMzMsMi4zMywwLDAsMCw4Mi42Myw0My4yOVpNNTAsMTcuNUEyNy42OSwyNy42OSwwLDEsMSwyMi4zMSw0NS4xOSwyNy43MiwyNy43MiwwLDAsMSw1MCwxNy41Wm0wLDY1QzM2LjYxLDgyLjUsMjYuMjksNzUsMjMuMjQsNjMuOTJhMzIuNjQsMzIuNjQsMCwwLDAsNTMuNTIsMEM3My43MSw3NSw2My4zOSw4Mi41LDUwLDgyLjVaIi8+PHBhdGggY2xhc3M9ImNscy0xIiBkPSJNNTAsNTMuNzFhMy4xNywzLjE3LDAsMCwxLTMuMTYtMy4xNmgtNWE4LjE2LDguMTYsMCwwLDAsNS42Niw3Ljc2VjYzaDVWNTguMzFBOC4xNiw4LjE2LDAsMCwwLDUwLDQyLjM5YTMuMTYsMy4xNiwwLDEsMSwzLjE2LTMuMTVoNWE4LjE4LDguMTgsMCwwLDAtNS42Ni03Ljc3VjI2LjgzaC01djQuNjRBOC4xNiw4LjE2LDAsMCwwLDUwLDQ3LjM5YTMuMTYsMy4xNiwwLDAsMSwwLDYuMzJaIi8+PC9zdmc+" width="30" height="30">
                                    </a>
                                </li>
                                
                                <li class="mb-3 w-100">
                               		<a href="" class="basicInfo__menu__link shrink__list-icon nuxt-link-exact-active nuxt-link-active">
<%--                                     <a href="<%=request.getContextPath()%>/front-end/lld/booking.jsp" class="basicInfo__menu__link shrink__list-icon"> --%>
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_rsv.png" width="30" height="30">
                                    </a>
                                </li>
                                
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/cont/ConServlet?action=gettntcontract" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsSAAALEgHS3X78AAACIElEQVR4nO3dwVGDQBhA4V/Hu5ZgBTt2EEpJJ8ZOUkrswNkKKCFWECcz60Vi1A1hH/K+I1wCb4DAJuzN4XAIcdzagsUgMAaBMQiMQWAMAmMQGIPA3F3ycVJKDxGxjoiHwcpl2kfENue8r9366jv1lNJTROwi4n6wctneI6LLOb9NFqQcGb0xvnWM8lhzpNReQ9bGOOu+7KM/qw3iNeNnVfvooov6F6/lmrJEXUSsxtjuMYPscs6bwdIFSCltxgrifQiMQWAMAmMQGIPAGATGIDAGgTEIjEFgDAJjEBiDwBgEZszH77+WUurKGALZcThh8vGdJkFKjOfBUp7Jg3jKgjEIjEFgWl1D5vBjiCafsUmQ8u1lqb9QOctTFoxBYAwCYxAYg8AYBMYgMAaBMQiMQWAMAmMQmFZDuJsZjBi+tPhHmEcIjEFgDAJjEJhWQ7jbGYwY9oMlE2g1hNu32mA6T1kwBoExCIxBYAwCYxAYg8AYBMYgMAaBMQhMqxHDx+N7bQcrLtOXZ2Sz1upp7/oKQ7gvETH7l3B6yoIxCIxBYAwC02rEcPMfLsDX4BECYxAYg8AYBMYgMAaBMQiMQWAMAmMQGIPAjPksqyv/HVyi0V55O2aQ1VhTxy1Z7SmrejbkBanaR7VBtmUCXp32XvbRNEHKLMidUU76nL676gipnk89nOD+lHYT3Os6vA+BMQiMQWAMAmMQGIPAGATGICQR8QGjXWHJ4UpOVwAAAABJRU5ErkJggg==" width="30" height="30">
                                    </a>
                                </li>
                            </ul>
                       </div>
                    </div>
                    <!--end shrink bar -->

                            <!--Start form Profile-->
<!--                             <div  class="bg-white info-form-wrap"> -->
								<div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4 booking-outside">
                                <h2 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">預約行程表
                                    
                                </h2>
                                <hr class="login100-form-title p-b-10">
                                <form class="registerform" enctype="multipart/form-data">
                                   <div id="date-ok"></div><div id="date-ok-word">可預約日期</div>
                                   <div id="date-no"></div><div id="date-no-word">已預約日期</div>
                                </form>
                                <div class="">
                                     <iframe class="booking-iframe" src="<%= request.getContextPath() %>/front-end/booking/bookingforlld.jsp" frameborder ="0">
                  					 </iframe>    
                                </div>
                            </div>
                            <!--End form Profile -->
                    <!--outer -->
<!--                 </div> -->
            </div>
        </section>

    </section>

    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/lld/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
<%--     <script src="<%=request.getContextPath()%>/front-end/lld/vendor/select2/select2.min.js"></script> --%>
    <!--===============================================================================================-->
<%--     <script src="<%=request.getContextPath()%>/front-end/lld/vendor/daterangepicker/moment.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/front-end/lld/vendor/daterangepicker/daterangepicker.js"></script> --%>
    <!--===============================================================================================-->
<%--     <script src="<%=request.getContextPath()%>/front-end/lld/vendor/countdowntime/countdowntime.js"></script> --%>
    <!--===============================================================================================-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/js/jquery.js"></script>
<%--     <script src="<%=request.getContextPath()%>/front-end/lld/js/jquery.datetimepicker.full.js"></script> --%>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/lld/js/booking_lld.js"></script>
    <!--===============================================================================================-->
    
<script>


</script>
</body>

</html>