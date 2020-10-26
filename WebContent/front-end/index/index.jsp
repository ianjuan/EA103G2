<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>

<%
		TntVO tntVO = (TntVO) session.getAttribute("tntVO");
		boolean volive=true;
		if(tntVO==null){
			volive=false;
		}
		String	tntno=(String)session.getAttribute("tnt_no");
		String	lldno=(String)session.getAttribute("lld_no");		
		%>
<html lang="zh-Hant-TW">

        <head>
<script src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
		            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/dist/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/resource/dist/js/bootstrap.min.js"></script> 
		    <meta charset="UTF-8">
            <title>Transit by TEMPLATED</title>
            <meta http-equiv="content-type" content="text/html; charset=utf-8" />
            <meta name="description" content="" />
            <meta name="keywords" content="" />
            <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
            <script src="<%=request.getContextPath()%>/front-end/index/js/jquery.min.js"></script>
            <script src="<%=request.getContextPath()%>/front-end/index/js/skel.min.js"></script>
            <script src="<%=request.getContextPath()%>/front-end/index/js/skel-layers.min.js"></script>
            <script src="<%=request.getContextPath()%>/front-end/index/js/init.js"></script>
                <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/skel.css" />
                <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/style.css" />
                <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/style-xlarge.css" />
                
        </head>

        <body class="landing">
            <!-- Header -->
            <header id="header">

            </header>
            <!-- Banner -->
            <section id="banner">
                <h2>你好.歡迎使用愛租</h2>
                <p>準備好尋找一個美好的住處了嗎?</p>
                <ul class="actions">
                    <li>
                        <a href="<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp" class="button big">開始使用</a>
                    </li>
                </ul>
            </section>
            <!-- One -->
            <section id="one" class="wrapper style1 special">
                <div class="container">
                    <header class="major">
                        <h2>愛租是為了懶人而生</h2>
                        <p>輕鬆找房，快速入住，簡單付款</p>
                    </header>
                    <div class="row 150%">
                        <div class="4u$ 12u$(medium)">
                            <section class="box">
                                <i class="icon big rounded color1 fa-cloud"></i>
                                <h3>線上繳費</h3>
                                <p>忙碌的生活讓你常常忘了繳費
                                    <br>愛租使用線上付款的方式<br>
                                    讓你隨時隨地輕鬆付款 </p>
                            </section>
                        </div>
                        <div class="4u 12u$(medium)">
                            <section class="box">
                                <i class="icon big rounded color8 fas fa-star"></i>
                                <h3>評分系統</h3>
                                <p>讓你在尋找房屋/房屋出租時 <br>多一份參考的選項不再輕易 <br>
                                    遇到糟糕的房東/房客</p>
                            </section>
                        </div>
                        <div class="4u 12u$(medium)">
                            <section class="box">
                                <i class="icon big rounded color9 fa-desktop"></i>
                                <h3>線上簽約</h3>
                                <p>
                                    怕不小心就簽了不合理的合約? <br>
                                    愛租使用內政部制式化合約 <br>
                                    讓你避免潛藏不平等合約的危機</p>
                            </section>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Two -->
            <section id="two" class="wrapper style2 special">
                <div class="container">
                    <header class="major">
                        <h2>優質房東推薦</h2>
                        <p>看看這些由房客評選出來的優質房東吧</p>
                    </header>
                    <section class="profiles">
                        <div class="row">
                            <section class="3u 6u(medium) 12u$(xsmall) profile">
                                <img src="<%=request.getContextPath()%>/front-end/index/images/吳永志.jpg" style="width: 92px;height: 92px" alt="" />
                                <h4>吳永志</h4>
                                <p>死小孩 <br>你還活著嗎?</p>
                            </section>
                            <section class="3u 6u$(medium) 12u$(xsmall) profile">
                                <img src="<%=request.getContextPath()%>/front-end/index/images/阮奕安.png" style="width: 92px;height: 92px" alt="" />
                                <h4>阮奕安</h4>
                                <p>小姐要不要來看看<br>我拉鍊裡的猛獸 </p>
                            </section>
                            <section class="3u 6u(medium) 12u$(xsmall) profile">
                                <img src="<%=request.getContextPath()%>/front-end/index/images/吳宜靜.png" style="width: 92px;height: 92px" alt="" />
                                <h4>吳宜靜</h4>
                                <p>Hands up <br>讓我看到你們雙手 </p>
                            </section>
                            <section class="3u$ 6u$(medium) 12u$(xsmall) profile">
                                <img src="<%=request.getContextPath()%>/front-end/index/images/吳冠宏.jpg" style="width: 92px;height: 92px" alt="" />
                                <h4>吳冠宏</h4>
                                <p>Make Java <br>Great Again</p>
                            </section>
                        </div>
                    </section>
                    <footer>
                        <p>還有許多非常優質的房東選擇使用愛租來曝光房屋<br>趕緊加入愛租的使用行列<br>一起來看看他們的房屋物件吧</p>
                        <ul class="actions">
                            <li>
                                <a href="<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp" class="button big">點擊查看</a>
                            </li>
                        </ul>
                    </footer>
                </div>
            </section>
            <!-- Three -->
            <section id="three" class="wrapper style3 special">
                <div class="container">
                    <header class="major">
                        <h2>Consectetur adipisicing elit</h2>
                        <p>Lorem ipsum dolor sit amet. Delectus consequatur, similique quia!</p>
                    </header>
                </div>
                <div class="container 50%">
                    <form action="#" method="post">
                        <div class="row uniform">
                            <div class="6u 12u$(small)">
                                <input name="name" id="name" value="" placeholder="Name" type="text">
                            </div>
                            <div class="6u$ 12u$(small)">
                                <input name="email" id="email" value="" placeholder="Email" type="email">
                            </div>
                            <div class="12u$">
                                <textarea name="message" id="message" placeholder="Message" rows="6"></textarea>
                            </div>
                            <div class="12u$">
                                <ul class="actions">
                                    <li><input value="Send Message" class="special big" type="submit"></li>
                                </ul>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
            <!-- Footer -->
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
                                <li>
                                    <a class="icon rounded fa-facebook"><span class="label">Facebook</span></a>
                                </li>
                                <li>
                                    <a class="icon rounded fa-twitter"><span class="label">Twitter</span></a>
                                </li>
                                <li>
                                    <a class="icon rounded fa-google-plus"><span class="label">Google+</span></a>
                                </li>
                                <li>
                                    <a class="icon rounded fa-linkedin"><span class="label">LinkedIn</span></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
            <style>
            
            #header h1 a{
                font-size: 2em;
            }
            .nav-item .member{
    display: flex;
}
.nav-item .member .memberpic{
    width: 40px;
    margin-right: 5px;
    outline: none;
}
.nav-item .member .membername{
    float: right;
    align-self:center;
    display: block;
    align-item:center;
        color: #4E6258;
}
@media ( min-width :981px) {
	.nav-item.dropdown{
float:right;
margin-top: 15px;
}
#header nav > ul > li  a{
margin-top: 15px;
}
}
@media ( max-width :980px) {
.nav-item .member{
/* border-top:none !important; */
}
.nav-item .member .memberpic{
float:left;
}
.nav-item .member .membername{
border-top:none !important;
padding-top: 0px !important;
float:left;
}

}


#nav{
    line-height: 3em;
}        
#header nav{
    line-height: 50px;
}
            
#header nav > ul > li{

display: inline-flex;
}

            </style>
            <script>
        
        if("<%= lldno%>"=="null" && "<%= tntno%>" =="null"){

        	$("#header").append(
        			 "<h1><a href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a></h1>"+
        	            "<nav id='nav'>"+
        	               " <ul>"+
        	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源</a></li>"+
        	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a></li>"+
        	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/Identify.jsp'>會員登入</a></li>"+
        	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/IdentifyRegister.jsp' class='special'>註冊會員</a></li>"+
        	                "</ul>"+
        	            "</nav>"
        	)
        }
        	if("<%= lldno%>"!=="null"){
        		$("#header").append(
        				 "<h1><a href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a></h1>"+
         	            "<nav id='nav'>"+
         	               " <ul>"+
         	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源</a></li>"+
         	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a></li>"+
         	                    "<li><a href='<%=request.getContextPath()%>/front-end/house_manage/house_index.jsp'>我的房屋</a></li>"+         	                    
         	                   "<li class='nav-item dropdown'>"+
       	                    "<span data-toggle='dropdown' class='member'>"+
       	                        "<input type='image' src='https://www.flaticon.com/svg/static/icons/svg/236/236831.svg' class='memberpic' />"+
       	                     "<span class='membername'><%= volive ? tntVO.getTnt_name().trim():" " %></span>"+
       	                    "</span>"+
       	                    "<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"+
       	                        "<a class='dropdown-item' href='#'>最新通知</a>"+
       	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/info.jsp'>個人資訊</a>"+
       	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/pocket.jsp'>我的錢包</a>"+
       	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/tnt/TntServlet2?action=logout'>登出</a>"+       
       	                    "</div>"+
       	                "</li>"+
         	                    
         	                "</ul>"+
         	            "</nav>"
         	            
        				
        		)
        	
        	}
        	if("<%= tntno %>"!=="null"){
        <%-- 		<img src="<%=request.getContextPath()%>/ImgReader?id=${tntVO.tnt_no}" > --%>
        		$("#header").append(
        				 "<h1><a href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a></h1>"+
          	            "<nav id='nav'>"+
          	               " <ul>"+
          	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源</a></li>"+
          	                    "<li><a href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a></li>"+
          	                    "<li><a href='<%=request.getContextPath()%>/front-end/collection/Mycol.jsp'>我的收藏</a></li>"+     
          	                    "<li><a href='<%=request.getContextPath()%>/front-end/apl/tntaplpage.jsp'>我的租屋</a></li>"+         	                    
          	                   "<li class='nav-item dropdown'>"+
        	                    "<span data-toggle='dropdown' class='member'>"+
        	                        "<input type='image' src='https://www.flaticon.com/svg/static/icons/svg/236/236831.svg' class='memberpic' />"+
           	                        "<span class='membername'><%= volive ? tntVO.getTnt_name().trim():" " %></span>"+
        	                    "</span>"+
        	                    "<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"+
        	                        "<a class='dropdown-item' href='#'>最新通知</a>"+
        	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/info.jsp'>個人資訊</a>"+
        	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/pocket.jsp'>我的錢包</a>"+
        	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/tnt/TntServlet2?action=logout'>登出</a>"+       
        	                    "</div>"+
        	                "</li>"+
          	                    
          	                "</ul>"+
          	            "</nav>"
        		)
        	}
        	
            </script>
        </body>
        </html>