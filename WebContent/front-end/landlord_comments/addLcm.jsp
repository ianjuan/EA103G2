<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.landlord_comments.model.*"%>
<%@ page import="com.landlord_comments.controller.*"%>

<%
  Landlord_commentsVO landlord_commentsVO = (Landlord_commentsVO) request.getAttribute("Landlord_commentsVO");
%>

<% session.setAttribute("tnt_no", "TNT000090");%>
<% request.setAttribute("lld_no", "LLD000014");%>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
​
  <a class="navbar-brand" href="#">愛租I-ZU</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav ml-auto">
 <a class="nav-item nav-link active" href="https://codepen.io/">尋找房源<span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="https://www.gamer.com.tw/">地圖找房</a>
      <a class="nav-item nav-link" href="https://www.gamer.com.tw/">我的收藏</a>
      <li class="nav-item dropdown">
        <span data-toggle="dropdown">
        <input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" style="width:40px" />
          我是房客
</span>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">最新通知</a>
          <a class="dropdown-item" href="#">個人資訊</a>
          <a class="dropdown-item" href="#">我的錢包</a>
          <a class="dropdown-item" href="#">登出</a>
        </div>
      </li>
    </div>
  </div></div>
</nav>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增房屋評價 - addHcm.jsp</title>
</head>
<body bgcolor='white'>
<div class="jumbotron jumbotron-fluid">
<div class="container">
		 <h1 class="display-4">新增房東評價 </h1>
		 <p class="lead">- addLcm.jsp</p>
	</div>
</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<div class="alert alert-light" role="alert">請修正以下錯誤:</div>
		<c:forEach var="message" items="${errorMsgs}">
		<div class="alert alert-warning" role="alert">${message}</div>
		</c:forEach>
</c:if>

<form METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" name="form1">
  <div class="form-group">
    <label for="exampleFormControlInput1">房客編號</label>
    <input type="text" name="tnt_no" class="form-control" id="exampleFormControlInput1" value="${tnt_no}" readonly/>
  </div>
   <div class="form-group">
    <label for="exampleFormControlInput1">房東編號</label>
    <input type="text" name="lld_no" class="form-control" id="exampleFormControlInput1" value="${lld_no}" readonly/>
  </div>
  
<!-- 第一題 -->
<label for="exampleInputEmail1"> 1. 您是否滿意房東維持房屋的整潔環境 </label>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_clean" value="1" ${(landlord_commentsVO.lcm_clean==1 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio1">非常不滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_clean" value="2" ${(landlord_commentsVO.lcm_clean==2 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">不太滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_clean" value="3" ${(landlord_commentsVO.lcm_clean==3 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">普通</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_clean" value="4" ${(landlord_commentsVO.lcm_clean==4 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_clean" value="5" ${(landlord_commentsVO.lcm_clean==5 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">非常滿意</label>
</div>
<br>
<!-- 第二題 -->
<label for="exampleInputEmail1"> 2. 您是否覺得房東是個善於溝通，並願意與房客溝通的人 </label>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_commut" value="1" ${(landlord_commentsVO.lcm_commut==1 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio1">非常不認同</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_commut" value="2" ${(landlord_commentsVO.lcm_commut==2 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">不太認同</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_commut" value="3" ${(landlord_commentsVO.lcm_commut==3 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">普通</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_commut" value="4" ${(landlord_commentsVO.lcm_commut==4 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">認同</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_commut" value="5" ${(landlord_commentsVO.lcm_commut==5 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">非常認同</label>
</div>
<br>
<!-- 第三題 -->
<label for="exampleInputEmail1"> 3. 整體而言，您是否滿意這位房東   </label>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_satisfy" value="1" ${(landlord_commentsVO.lcm_satisfy==1 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio1">非常不滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_satisfy" value="2" ${(landlord_commentsVO.lcm_satisfy==2 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">不太滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_satisfy" value="3" ${(landlord_commentsVO.lcm_satisfy==3 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">普通</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_satisfy" value="4" ${(landlord_commentsVO.lcm_satisfy==4 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">滿意</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="lcm_satisfy" value="5" ${(landlord_commentsVO.lcm_satisfy==5 ? 'checked':'') }>
  <label class="form-check-label" for="inlineRadio2">非常滿意</label>
</div>
<br>
<!-- 第四題 -->
<div class="form-group">
    <label for="exampleFormControlTextarea1">給房東的其他評價或建議</label>
    <textarea name="lcm_commet" class="form-control" id="exampleFormControlTextarea1" rows="5">
    	${(landlord_commentsVO.lcm_commet == null ? '':landlord_commentsVO.lcm_commet) }
    </textarea>
  </div>
  
<br>
<input type="hidden" name="action" value="insert">
<input class="btn btn-primary" type="submit" value="送出評價">
</form>
</body>
</html>

