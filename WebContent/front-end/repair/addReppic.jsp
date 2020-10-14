<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair_picture.model.*"%>
<%@ page import="com.repair.controller.*"%>

<%
Repair_pictureVO repair_pictureVO = (Repair_pictureVO) request.getAttribute("repair_pictureVO");
%>

<%
RepairVO repairVO = (RepairVO) session.getAttribute("repairVO");
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">
<!-- =========================================以下為 upload img 之相關設定========================================== -->
<script>
$(document).on('click', '#yes', function(event){
	$("#uploadPic").show();
});
$(document).on('click', '#no', function(event){
	$("#uploadPic").hide();
});  

// var pics = documents.getElementById("pics");

// pics.addEventListener('change', function(e){
// 	var imgList = [];
// 	var picf = pics.files;
// 	if(picf!=null){
		
// 		for(var i=0; i<pics.length;i++){
// 			//取出第i張圖片
// 			var pic=pics[i];
// 			//創造img的<>放上傳的圖片src
// 			var img = document.createElement('img');
// 			img.src = URL.createObjectURL(pic);
// 			//刪除鈕
// 	        var del = document.createElement("input");
// 	        var div = document.createElement('div');
// 	        div.append(del); //div爸爸把刪除del包起來
// 	 		del.setAttribute('type', 'button');
//           	del.setAttribute('value', '刪除圖片');
//             	del.addEventListener('click', function(e) {
//             		e.target.parentElement.remove(); //  e.target是del按鈕，讓爸爸div消失
//             		e.stopPropagation(); //停止向上冒泡

//             	}, false);
            
//           	//將img放到preview的div區塊
//          	div.append(img);
//          	preview.append(div);
         	
//          	//把pic放到imgList裡，準備上傳到DB
//          	imgList[i]=pic;

// 		}
// 		pics.src = null;
// 	}
// }, false);



	


// // 刪除全部圖片
// var delete1 = document.getElementById("delete");
// delete1.addEventListener('click', function(e) {
//     preview.innerHTML = "";
// });
</script>

<title>申請成功!</title>

</head>
<body>
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
<div class="jumbotron jumbotron-fluid">
<div class="container">
		 <h1 class="display-4">申請已完成 </h1>
		 <p class="lead">- addReppic.jsp</p>
</div>
</div>

<%-- <% --%>
<!-- // 	out.print("<script>alert('修繕申請成功!請等候房東通知預計修畢日期與修繕結果，想要給房東看家具損壞的照片嗎?');window.location.href='addReppic.jsp';</script>"); -->
<%-- %> --%>
<table>
<tr><td>${repairVO.rep_no}</td></tr>
<tr><td>${repairVO.con_no}</td></tr>
<tr><td>${repairVO.rep_dam_obj}</td></tr>
<tr><td>${repairVO.rep_dam_obj_des}</td></tr>
<tr><td>${repairVO.rep_case_str}</td></tr>
</table>

<h1>修繕申請成功!請等候房東通知預計修畢日期與修繕結果</h1>
<h2>想要給房東看家具損壞的照片嗎?</h2>
<button id="yes" type="button" class="btn btn-outline-primary">好啊</button>
<button id="no" type="button" class="btn btn-outline-secondary">不用了</button>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="uploadPic" >

<div class="alert alert-info" role="alert">
  1. 請先拍下物品毀損的照片
</div>
<h4>2. 這裡可以上傳損壞物品的照片(可多張):</h4>
<div class="upload">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data">


    <input type="file" id="pics" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG">
   
       
<%--   	<input type="hidden" name="rep_no" value="${repairVO.rep_no}"> --%>

<!--   	<input type="button" id="delete" value="刪除圖片"> -->
<!--     <h5>圖片預覽</h5> -->
<!-- 	<div id="preview"> -->
 		
<!--    	</div> -->
<!-- </div> -->


<input type="hidden" name="action" value="pic_insert">
<input class="btn btn-primary" type="submit" value="送出圖片">
</FORM>
 </div>
 </div>




</body>
</html>