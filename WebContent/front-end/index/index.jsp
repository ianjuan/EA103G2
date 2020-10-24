<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tnt.model.*"%>
<%
  TntVO tntVO = (TntVO) session.getAttribute("tntVO");
%>
<% String tnt_no = (String) session.getAttribute("tnt_no");%>
<html lang="zh-Hant">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&family=Open+Sans:ital,wght@1,600&family=Pacifico&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
   

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
</head>

<body>
    <header>
    <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Dropdown
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>
    </div>
</header>
    <section id="intro">
        <div class="jumbotron">
            <div class="container">
                <div class="col-md-12">
                    <h1><span id="iZu"> i-Zu </span> 愛租</h1>
                        <p class="lead">租或不租，只要想租，就選愛租</p>
                        <a class="btn" href="#">立即租屋</a>
                </div>
            </div>
        </div>
    </section>
    ${tntVO.tnt_name}
    <%=tntVO.getTnt_name()%>
     <%=tntVO.getTnt_no()%>
     
<%=tnt_no%>

    <div class="dropdown">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Dropdown button
      </button>
      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="#">Action</a>
        <a class="dropdown-item" href="#">Another action</a>
        <a class="dropdown-item" href="#">Something else here</a>
      </div>
    </div>


    <div class="stepArea member bg-white">
        <div class="container py-lg-8 py-6 px-xl-9 px-md-7 rounded position-relative">
            <h2 class="mb-lg-8 mb-6 text-center">房客使用流程</h2> 
            <div class="position-relative">
                <div class="row no-gutters justify-content-between animate-primary">
                    <div data-aos="fade-in" data-aos-offset="200" data-aos-duration="800" data-aos-easing="ease-in-out" data-aos-anchor=".stepArea" class="col-xl-2 Step aos-init aos-animate">
                        <div class="Img"><img alt="租租通DDROOM-搜尋房源" data-src="/_nuxt/img/549db4a.svg" src="https://www.dd-room.com/_nuxt/img/549db4a.svg" lazy="loaded"></div> 
                        <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6">
                            <p class="font-size-20p mb-3">搜尋房源</p> 
                            <p class="mb-0">按您的需求搜尋房源</p></div> <!----></div> 
                            <div data-aos="fade-in" data-aos-delay="800" data-aos-offset="200" data-aos-duration="800" data-aos-easing="ease-in-out" data-aos-anchor=".stepArea" class="col-xl-2 Step aos-init aos-animate">
                                <div class="Img"><img alt="租租通DDROOM-聯絡房東" data-src="/_nuxt/img/d3565dc.svg" src="https://www.dd-room.com/_nuxt/img/d3565dc.svg" lazy="loaded"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"><p class="mb-3 font-size-20p">聯絡房東</p> 
                                    <p class="mb-0">在房源頁面您可以傳送訊息聯絡房東前往看房</p></div> <!----></div> <div data-aos="fade-in" data-aos-delay="1600" data-aos-offset="200" data-aos-duration="800" data-aos-easing="ease-in-out" data-aos-anchor=".stepArea" class="col-xl-2 Step aos-init aos-animate">
                                        <div class="Img"><img alt="租租通DDROOM-線上簽約申請" data-src="/_nuxt/img/47fa1de.svg" src="https://www.dd-room.com/_nuxt/img/47fa1de.svg" lazy="loaded"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"><p class="mb-3 font-size-20p">線上申請簽約</p> 
                                            <p class="mb-0">對房東提出簽約申請，待房東回覆</p></div> <!----></div> <div data-aos="fade-in" data-aos-delay="2400" data-aos-offset="200" data-aos-duration="800" data-aos-easing="ease-in-out" data-aos-anchor=".stepArea" class="col-xl-2 Step aos-init aos-animate">
                                                <div class="Img"><img alt="租租通DDROOM-平台提醒付租" data-src="/_nuxt/img/58bd61b.svg" src="https://www.dd-room.com/_nuxt/img/58bd61b.svg" lazy="loaded"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6">
                                                    <p class="mb-3 font-size-20p">平台提醒付租</p> <p class="mb-0">租租通DDROOM將透過簡訊及電子郵件定期提醒您繳款，請您按時付款並回覆繳款確認</p></div></div></div> <div class="row no-gutters justify-content-between animate-gray"><div class="col-xl-2 Step"><div class="Img"></div>
                                                    <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"></div></div> <div class="col-xl-2 Step"><div class="Img"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"></div></div> <div class="col-xl-2 Step"><div class="Img"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"></div></div> 
                                                    <div class="col-xl-2 Step"><div class="Img"></div> <div class="Txt px-2 mt-lg-4 mt-2 pt-lg-6"></div></div></div></div></div></div>
    
</body>
</html>