<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cash.model.*"%>
<%@ page import="java.util.*"%>


<% session.removeAttribute("lld_no"); %>
<% String tnt_no = (String) session.getAttribute("tnt_no");%>
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<%
	TntVO tntVO = tntSvc.getOneTntProfile(tnt_no);
	request.setAttribute("tntVO", tntVO);
%>
<jsp:useBean id="cashSvc" scope="page" class="com.cash.model.CashService" />
<%
	List<CashVO> listCashLog = cashSvc.getOneCashlogs(tnt_no);
 	request.setAttribute("listCashLog", listCashLog);
%>

<head>
    <title>Bills</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&family=Open+Sans:ital,wght@1,600&family=Pacifico&display=swap" rel="stylesheet">
    <!-- <link rel="stylesheet" href="css/style.css"> -->

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--===============================================================================================-->
<%--     <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front-end/tnt/images/icons/favicon.ico" /> --%>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
<%--     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/animate/animate.css"> --%>
    <!--===============================================================================================-->
<%--     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/animsition/css/animsition.min.css"> --%>
    <!--===============================================================================================-->
<%--     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/vendor/select2/select2.min.css"> --%>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/util.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/tnt/css/bills_tnt.css">
    <!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css"> 
	<!--===============================================================================================-->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />


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
   			color: #3A8C68!important;
		}
		
/* 		 @media (max-width:1199.98px) { */
       @media (max-width:3000px) {
            .basicInfo__list {
                position: fixed;
                top: 53px;
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
                text-align: center;
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
         /*現在頁面*/ 
         .dataTables_wrapper .dataTables_paginate .paginate_button.current, .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
			color: #3A8C68!important; 
		    background-color: #AACFBF !important;
		    font-weight: bold !important;
			border: transparent !important;
		    background: transparent !important;
		    border-bottom: 1px solid #3A8C68 !important;
    		border-radius: 0px;
		}
		/*其他頁面按鈕*/
		.dataTables_wrapper .dataTables_paginate .paginate_button {
  			border-radius: 10px !important;
		}
		.dataTables_wrapper .dataTables_paginate .paginate_button:hover{
  			color: #fff!important;
   			border: 1px solid #AACFBF !important; 
 		    background: transparent !important;
  		    background-color: #AACFBF !important;
  		    border-radius: 10px;
		}
		/*不能按的下一頁*/
		.dataTables_wrapper .dataTables_paginate .paginate_button.disabled, .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:hover, .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:active{
 			color: #777 !important;  
 			border: transparent !important; 
 		    background: transparent !important; 
 		    font-weight: normal !important; 
		}
		/*現在頁面*/ 
         .dataTables_wrapper .dataTables_paginate .paginate_button.current, .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
			color: #3A8C68!important; 
		    background-color: #AACFBF !important;
		    font-weight: bold !important;
			border: transparent !important;
		    background: transparent !important;
		    border-bottom: 1px solid #3A8C68 !important;
    		border-radius: 0px !important;
		}
		/*Serch bar*/
		.dataTables_wrapper .dataTables_filter input {
			border: 1px solid #AACFBF !important;
			border-radius: 25px !important;
			padding: 0 13px !important;
		}
		.dataTables_wrapper .dataTables_filter input:focus {
			border: 1px solid #3A8C68 !important;
			border-radius: 25px !important;
			padding: 0 13px !important;
		}
		/*表格顏色*/
 		.table.dataTable.display tbody tr.odd>.sorting_1, table.dataTable.order-column.stripe tbody tr.odd>.sorting_1 { 
 			background-color: #fff !important; 
 		} 
 		tr:nth-child(even){ 
   			background: #F2F7F2 !important; 
 		} 
 		tr:nth-child(odd){ 
    			background: #fff !important;  
 		} 
 		td.sorting_1:nth-child(even){ 
/*   			background-color: #e9e9e9 !important;  */
 		} 
 		td.sorting_1:nth-child(odd){ 
   			background: #F2F7F2 !important; 
 		} 
 		table.dataTable.display tbody tr.odd>.sorting_1, table.dataTable.order-column.stripe tbody tr.odd>.sorting_1 { 
 			background: #fff !important; 
 		} 
 		table.dataTable, table.dataTable th, table.dataTable td {
 			text-align: center !important; 
 		}
 		table.dataTable td:nth-child(4){
 			padding-left: 5.5% !important;
    		text-align: left !important;
 		}
 		    
 		/*dataTable*/
		.dataTables_wrapper {
			padding: 30px 15px 0px;
		}
		.topayrow {
			color: #bf2121 !important; 
		}
		.toreceiverow {
			color: #0d3eb1 !important
		}
		/*本月明細*/
		.btn-thismonth-detail {
		    color: #fff;
		    background-color: #3A8C68 !important;
		    border-color: #3A8C68 !important;
		}
 		.btn-primary { 
 		    color: #fff; 
 		    background-color: #3A8C68!important;
 		    border-color: #3A8C68!important;
 		}
       .modal-dialog-centered {
       		max-width: 35% !important;
       }
       .modal-header {
       		text-align:center !important;
       }
       .modal-header{
           align-items: center !important;
    	   justify-content: center !important;
	   }
    </style>

</head>

<body class="landing">
	<jsp:include page="/front-end/navbar/navbar.jsp"/>
	<!--progressBar 有空再做-->
<!-- 	<div data-v-291437b2="" class="summaryDetail__progressBar bg-gray-light-2 col-md-12 col-11 px-0 progress"> -->
<!-- 		<div role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0" class="progress-bar" style="width: 50%;"> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<!--progressBar 有空再做-->
    <section>
        <section class="content">
            <div class="" id="infocontainer">
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
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/verify.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_vrf.png" width="30" class="mr-2">身分驗證
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/pocket.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_pocket.png" width="30" class="mr-2">我的錢包
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a class="basicInfo__menu__link nuxt-link-exact-active nuxt-link-active">
                                        <img src="data:image/svg+xml;base64,PHN2ZyBpZD0i5ZyW5bGkXzEiIGRhdGEtbmFtZT0i5ZyW5bGkIDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmtnt0JveD0iMCAwIDEwMCAxMDAiPjxkZWZzPjxzdHlsZT4uY2xzLTF7ZmlsbDojMmQyZDJkO308L3N0eWxlPjwvZGVmcz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Ik04Mi42Myw0My4yOWEzMi42OCwzMi42OCwwLDAsMC02NS4yNiwwLDIuMzMsMi4zMywwLDAsMC0uMDYuNTNWNTdDMTcuMzEsNzQuMzcsMzEuMzcsODcuNSw1MCw4Ny41UzgyLjY5LDc0LjM3LDgyLjY5LDU3VjQzLjgyQTIuMzMsMi4zMywwLDAsMCw4Mi42Myw0My4yOVpNNTAsMTcuNUEyNy42OSwyNy42OSwwLDEsMSwyMi4zMSw0NS4xOSwyNy43MiwyNy43MiwwLDAsMSw1MCwxNy41Wm0wLDY1QzM2LjYxLDgyLjUsMjYuMjksNzUsMjMuMjQsNjMuOTJhMzIuNjQsMzIuNjQsMCwwLDAsNTMuNTIsMEM3My43MSw3NSw2My4zOSw4Mi41LDUwLDgyLjVaIi8+PHBhdGggY2xhc3M9ImNscy0xIiBkPSJNNTAsNTMuNzFhMy4xNywzLjE3LDAsMCwxLTMuMTYtMy4xNmgtNWE4LjE2LDguMTYsMCwwLDAsNS42Niw3Ljc2VjYzaDVWNTguMzFBOC4xNiw4LjE2LDAsMCwwLDUwLDQyLjM5YTMuMTYsMy4xNiwwLDEsMSwzLjE2LTMuMTVoNWE4LjE4LDguMTgsMCwwLDAtNS42Ni03Ljc3VjI2LjgzaC01djQuNjRBOC4xNiw4LjE2LDAsMCwwLDUwLDQ3LjM5YTMuMTYsMy4xNiwwLDAsMSwwLDYuMzJaIi8+PC9zdmc+" width="30" class="mr-2">我的帳務</a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/booking.jsp" class="basicInfo__menu__link">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_rsv.png" width="30" class="mr-2">我的預約
                                    </a>
                                </li>
                                <li class="mb-3">
                                    <a href="<%=request.getContextPath()%>/cont/ConServlet?action=gettntcontract" class="basicInfo__menu__link">
                                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsSAAALEgHS3X78AAACIElEQVR4nO3dwVGDQBhA4V/Hu5ZgBTt2EEpJJ8ZOUkrswNkKKCFWECcz60Vi1A1hH/K+I1wCb4DAJuzN4XAIcdzagsUgMAaBMQiMQWAMAmMQGIPA3F3ycVJKDxGxjoiHwcpl2kfENue8r9366jv1lNJTROwi4n6wctneI6LLOb9NFqQcGb0xvnWM8lhzpNReQ9bGOOu+7KM/qw3iNeNnVfvooov6F6/lmrJEXUSsxtjuMYPscs6bwdIFSCltxgrifQiMQWAMAmMQGIPAGATGIDAGgTEIjEFgDAJjEBiDwBgEZszH77+WUurKGALZcThh8vGdJkFKjOfBUp7Jg3jKgjEIjEFgWl1D5vBjiCafsUmQ8u1lqb9QOctTFoxBYAwCYxAYg8AYBMYgMAaBMQiMQWAMAmMQmFZDuJsZjBi+tPhHmEcIjEFgDAJjEJhWQ7jbGYwY9oMlE2g1hNu32mA6T1kwBoExCIxBYAwCYxAYg8AYBMYgMAaBMQhMqxHDx+N7bQcrLtOXZ2Sz1upp7/oKQ7gvETH7l3B6yoIxCIxBYAwC02rEcPMfLsDX4BECYxAYg8AYBMYgMAaBMQiMQWAMAmMQGIPAjPksqyv/HVyi0V55O2aQ1VhTxy1Z7SmrejbkBanaR7VBtmUCXp32XvbRNEHKLMidUU76nL676gipnk89nOD+lHYT3Os6vA+BMQiMQWAMAmMQGIPAGATGICQR8QGjXWHJ4UpOVwAAAABJRU5ErkJggg==" width="30" class="mr-2">我的合約</a>
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
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/info.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/meminfoprofile.png" width="30" height="30">
                                    </a>
                                </li>
                                
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/verify.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_vrf.png" width="30" height="30">
                                    </a>
                                </li>
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/pocket.jsp" class="basicInfo__menu__link shrink__list-icon">
                                        <img src="<%=request.getContextPath()%>/images/infoIcon_pocket.png" width="30" height="30">
                                    </a>
                                </li>
                                <li class="mb-3 w-100">
                                    <a href="" class="basicInfo__menu__link shrink__list-icon nuxt-link-exact-active nuxt-link-active">
                                        <img src="data:image/svg+xml;base64,PHN2ZyBpZD0i5ZyW5bGkXzEiIGRhdGEtbmFtZT0i5ZyW5bGkIDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmlld0JveD0iMCAwIDEwMCAxMDAiPjxkZWZzPjxzdHlsZT4uY2xzLTF7ZmlsbDojMmQyZDJkO308L3N0eWxlPjwvZGVmcz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Ik04Mi42Myw0My4yOWEzMi42OCwzMi42OCwwLDAsMC02NS4yNiwwLDIuMzMsMi4zMywwLDAsMC0uMDYuNTNWNTdDMTcuMzEsNzQuMzcsMzEuMzcsODcuNSw1MCw4Ny41UzgyLjY5LDc0LjM3LDgyLjY5LDU3VjQzLjgyQTIuMzMsMi4zMywwLDAsMCw4Mi42Myw0My4yOVpNNTAsMTcuNUEyNy42OSwyNy42OSwwLDEsMSwyMi4zMSw0NS4xOSwyNy43MiwyNy43MiwwLDAsMSw1MCwxNy41Wm0wLDY1QzM2LjYxLDgyLjUsMjYuMjksNzUsMjMuMjQsNjMuOTJhMzIuNjQsMzIuNjQsMCwwLDAsNTMuNTIsMEM3My43MSw3NSw2My4zOSw4Mi41LDUwLDgyLjVaIi8+PHBhdGggY2xhc3M9ImNscy0xIiBkPSJNNTAsNTMuNzFhMy4xNywzLjE3LDAsMCwxLTMuMTYtMy4xNmgtNWE4LjE2LDguMTYsMCwwLDAsNS42Niw3Ljc2VjYzaDVWNTguMzFBOC4xNiw4LjE2LDAsMCwwLDUwLDQyLjM5YTMuMTYsMy4xNiwwLDEsMSwzLjE2LTMuMTVoNWE4LjE4LDguMTgsMCwwLDAtNS42Ni03Ljc3VjI2LjgzaC01djQuNjRBOC4xNiw4LjE2LDAsMCwwLDUwLDQ3LjM5YTMuMTYsMy4xNiwwLDAsMSwwLDYuMzJaIi8+PC9zdmc+" width="30" height="30">
                                    </a>
                                </li>
                                
                                <li class="mb-3 w-100">
                                    <a href="<%=request.getContextPath()%>/front-end/tnt/booking.jsp" class="basicInfo__menu__link shrink__list-icon">
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
                     <!--Start bill logs-->
								<div data-v-9403d44c="" class="bg-white info-form-wrap px-lg-5 px-md-4 px-3 pt-md-5 pt-4 mb-md-7 mb-4">
                                <h4 data-v-9403d44c="" class="font-size-lg text-center p-b-10 mb-0">交易紀錄</h4>
<!--                                 TAB 有空再做 -->
<!-- 								<nav class="nav nav-pills nav-fill"> -->
<!-- 								  <a class="nav-item nav-link active" href="#">Active</a> -->
<!-- 								  <a class="nav-item nav-link" href="#">Link</a> -->
<!-- 								  <a class="nav-item nav-link" href="#">Link</a> -->
<!-- 								  <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
<!-- 								</nav> -->
<!-- 								TAB 有空再做 -->
									<div class="justify-content-between p-t-15" style="display: flex;">
									 <select class="wrap-register100 validate-input" data-validate="" name="cash_inout" id="cash_inout" >
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">
                                        	  <option value="" >查詢收支
                                              <option value="" >全部
                          					  <option value="in" >收入
                          					  <option value="out">支出
                                        </span>
                                    </select>
                                    <select class="wrap-register100 validate-input" data-validate="" name="cash_type" id="cash_type" >
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">
                                        	  <option value="" >查詢交易種類
                                              <option value="" >全部
                                              <option value="儲值">儲值
                                              <option value="提領">提領
                          					  <option value="押金" >押金
                          					  <option value="每月帳單">每月帳單
											  <option value="退房帳單">退房帳單
											  <option value="上架費">上架費
                                        </span>
                                    </select>
 									<select class="wrap-register100 validate-input" data-validate="" name="cash_status" id="cash_status" >
                                        <span class="focus-register100"></span>
                                        <span class="label-register100">
                                        	  <option value="" >查詢交易狀態
                                              <option value="" >全部
                          					  <option value="完成" >完成
                          					  <option value="未完成">待收/代繳
											  <option value="待收">待收
											  <option value="待繳">待繳
                                        </span>
                                    </select>
                                   </div>
                                    
                                <table id="myDataTalbe"  class="display">
							        <thead>
							            <tr>
							         		<th>項次</th>
							                <th>日期</th>
							                <th>收入/支出</th>
							                <th>交易種類</th>
							                <th>交易金額</th>
							                <th>交易狀態</th>
							                <th>查看明細</th>
							            </tr>
							        </thead>
							        <tbody id="tbody">
							        
							        <c:forEach var="cashVO" items="<%=listCashLog%>"  varStatus="varStatusName">
											<tr>
												<td>${varStatusName.count}</td>
												<td>${cashVO.cash_date}</td>
												<td>${cashVO.cash_inout}</td>
<%-- 												<td><c:if test="${cashVO.cash_inout==\"in\"}">收入</c:if> --%>
<%-- 													<c:if test="${cashVO.cash_inout==\"out\"}">支出</c:if></td> --%>
												<td>
													<c:if test="${cashVO.cash_status==1}">
														<c:if test="${cashVO.cash_type==\"儲值\"}">
								                           <img src="<%=request.getContextPath()%>/images/cash_deposit.png" width="50">
							 	                        </c:if>
										                <c:if test="${cashVO.cash_type==\"提領\"}">
								                            <img src="<%=request.getContextPath()%>/images/cash_withdraw.png" width="50">
								                        </c:if>
														<c:if test="${cashVO.cash_inout==\"in\"}">
									                        <c:if test="${cashVO.cash_type!=\"儲值\"}">
									                            <img src="<%=request.getContextPath()%>/images/cash_in.png" width="50">
									                        </c:if>
								                        </c:if>
														<c:if test="${cashVO.cash_inout==\"out\"}">
									                        <c:if test="${cashVO.cash_type!=\"提領\"}">
									                            <img src="<%=request.getContextPath()%>/images/cash_out.png" width="50">
									                        </c:if>
								                        </c:if>
							                        </c:if>
							                        <c:if test="${cashVO.cash_status==0}">
							                            <img src="<%=request.getContextPath()%>/images/cash_topay.png" width="50">
							                        </c:if>									                    
													&nbsp${cashVO.cash_type}
												</td>
												<td>${cashVO.cash_amount}</td>
												<c:if test="${cashVO.cash_status==1}">
													<td>完成</td>
												</c:if>
												<c:if test="${cashVO.cash_status==0}">
													<c:if test="${cashVO.cash_inout==\"in\"}">
														<td><span class="topayrow">待收</span></td>
													</c:if>
													<c:if test="${cashVO.cash_inout==\"out\"}">
														<td><span class="toreceiverow">待繳</span></td>
													</c:if>
												</c:if>
												<td>
													<c:if test="${cashVO.cash_type==\"每月帳單\"}">
								                    	<button type="button" class="btn btn-info btn-sm btn-thismonth-detail btnRecBills" id="${cashVO.rec_no}" data-toggle="modal" data-target="#exampleModalCenter${varStatusName.count}">本月明細</button>
								                    	<!-- Modal -->
														<div class="modal fade" id="exampleModalCenter${varStatusName.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
														  <div class="modal-dialog modal-dialog-centered" role="document">
														    <div class="modal-content">
														      <div class="modal-header">
														        <h5 class="modal-title" id="exampleModalLongTitle">週期帳單明細</h5>
														      </div>
														      <div class="modal-body" id="modal-body">
														       	<jsp:include page="/front-end/rec/nowrecdetail_bills.jsp?rec_no=${cashVO.rec_no}"/>
														      </div>
														      <div class="modal-footer">
<!-- 														        <button type="button" class="btn btn-primary" id="btnCloseDetail">關閉明細</button> -->
														      </div>
														    </div>
														  </div>
														</div>
														<!-- Modal End-->
								                    </c:if>
												</td>
											</tr>
										</c:forEach>
							        </tbody>
							    </table>
                            </div>
                            <!--End bill logs -->
                    <!--outer -->
            </div>
        </section>
    </section>

    <!--===============================================================================================-->
<%--     <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/jquery/jquery-3.2.1.min.js"></script> --%>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/tnt/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
<!--     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> -->
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/jquery.js"></script>
    <!--===============================================================================================-->
    <script src="<%=request.getContextPath()%>/front-end/tnt/js/bills_tnt.js"></script>
    <!--===============================================================================================-->
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
     
    <script>
    var cashVO;
    $("#cash_inout").on("change",function(){
        $.ajax({
    		  url:"<%=request.getContextPath()%>/tnt/TntServlet2",
    	 	  type:"GET",
    	 	  data:{action:"billsQuery",
    	 		 cash_inout:$('#cash_inout').val(),
   	 		     cash_type:$('#cash_type').val(),
   	 		     cash_status:$('#cash_status').val()
    	 		  },
    	 	  success:function(data){//以上成功才執行
    	 		  console.log("data="+data);
    	 		  cashVO=JSON.parse(data); 
    	          loading(cashVO);
    	 		  console.log("res棒");
    	 		  }
    	 	  ,
    	 	  error:function(data)
    	 	  {
    	 		  console.log("真的不棒")
    	 	  }			  
    	  })
        });
    $("#cash_type").on("change",function(){
        $.ajax({
    		  url:"<%=request.getContextPath()%>/tnt/TntServlet2",
    	 	  type:"GET",
    	 	  data:{action:"billsQuery",
    	 		  cash_inout:$('#cash_inout').val(),
    	 		  cash_type:$('#cash_type').val(),
    	 		  cash_status:$('#cash_status').val()
    	 		  },
    	 	  success:function(data){//以上成功才執行
    	 		  console.log("data="+data);
    	 		  cashVO=JSON.parse(data); 
    	          loading(cashVO);
    	 		  console.log("res棒");
    	 		  }
    	 	  ,
    	 	  error:function(data)
    	 	  {
    	 		  console.log("真的不棒")
    	 	  }			  
    	  })
        });
    $("#cash_status").on("change",function(){
    $.ajax({
		  url:"<%=request.getContextPath()%>/tnt/TntServlet2",
	 	  type:"GET",
	 	  data:{action:"billsQuery",
	 		  cash_inout:$('#cash_inout').val(),
	 		  cash_type:$('#cash_type').val(),
	 		  cash_status:$('#cash_status').val()
	 		  },
	 	  success:function(data){//以上成功才執行
	 		  console.log("data="+data);
	 		  cashVO=JSON.parse(data); 
	          loading(cashVO);
	 		  console.log("res棒");
	 		  }
	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }			  
	  })
    });
    
	function loading(cashlist){
		$("#tbody").remove();
		$('#myDataTalbe').append("<tbody id='tbody'></tbody>");

     $.each(cashlist, function(i,cashVO){      
    	var img;
    	var statusStr;
    	if(cashVO.cash_status==1){
        	// icon 判斷
    		if (cashVO.cash_inout=="in"){
    			if(cashVO.cash_type=="儲值"){
    			 img="<img src='<%=request.getContextPath()%>/images/cash_deposit.png' width='50'>";

    			}else{
    				img="<img src='<%=request.getContextPath()%>/images/cash_in.png' width='50'>";
    			}
    		}
    	 		 if (cashVO.cash_inout=="out"){
    	 			if(cashVO.cash_type=="提領"){
    	 				img="<img src='<%=request.getContextPath()%>/images/cash_withdraw.png' width='50'>";
    	 			}else{
    	 				img="<img src='<%=request.getContextPath()%>/images/cash_out.png' width='50'>";
    	 			}
    	 		}
    	 // status 判斷
    	 statusStr = '完成';
        }
        if(cashVO.cash_status==0){
        	// icon 判斷
        	img="<img src='<%=request.getContextPath()%>/images/cash_topay.png' width='50'>";
        	// status 判斷
        	statusStr = (cashVO.cash_inout=="in")?"<span class='topayrow'>待收</span>":"<span class='toreceiverow'>待繳</span>";
        }
    	// Modal
    	var modalStr = '';
    	if (cashVO.cash_type=='每月帳單'){
    		modalStr = "<button type='button' class='btn btn-info btn-sm btn-thismonth-detail btnRecBills' data-toggle='modal' data-target='#exampleModalCenter"+(i+1)+"'>本月明細</button>"+
    		"<div class='modal fade' id='exampleModalCenter"+(i+1)+"' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>"+
    		  "<div class='modal-dialog modal-dialog-centered' role='document'>"+
    		    "<div class='modal-content'>"+
    		      "<div class='modal-header'>"+
    		        "<h5 class='modal-title' id='exampleModalLongTitle'>週期帳單明細</h5>"+
    		      "</div>"+
    		      "<div class='modal-body' id='modal-body'>"+
    		      
    		      "</div>"+
    		      "<div class='modal-footer'>"+
//     		        "<button type='button' class='btn btn-primary' id='btnCloseDetail'>關閉明細</button>"+
    		      "</div>"+
    		    "</div>"+
    		  "</div>"+
    		"</div>";

    	}
    if(cashVO.cash_type){
     $('#tbody').append(
						"<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td>"+cashVO.cash_date+"</td>"+
							"<td>"+cashVO.cash_inout+"</td>"+
							"<td>"+img+" "+cashVO.cash_type+"</td>"+
							"<td>"+cashVO.cash_amount+"</td>"+
							"<td>"+statusStr+
							"<td>"+modalStr+

							"</td>"+
						"</tr>"
    )}
    if (cashVO.cash_type=='每月帳單'){
    	console.log(cashVO.rec_no);
    	$.ajax({
  		  url:"<%=request.getContextPath()%>/front-end/rec/nowrecdetail_bills.jsp?rec_no="+cashVO.rec_no,
  	 	  type:"GET",
  	 	  data:{},
  	 	  success:function(data){//以上成功才執行
  	 		  console.log("data="+data);
  	 		  console.log("res棒");
  	 		  $('#modal-body').html(data);
  	 	  },
  	 	  error:function(data){
  	 		  console.log("真的不棒")
  	 	  }			  
  	  })
    }
    }); //each
  }
   		// DataTalbe
        $(function () {
            $("#myDataTalbe").DataTable({
                searching: false, //關閉filter功能
                columnDefs: [{
                    targets: [3],
                    orderable: false,
                }]
            });
        });
   		
    </script>
</body>

</html>