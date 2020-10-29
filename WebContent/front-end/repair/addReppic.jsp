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
<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">



<title>申請成功!</title>
<style>
.input{
	border:5px grey solid;
	border-radius:30px;
	backgroundcolor:lightblue;
	padding:100px;
	margin:120px;
	color:grey;
	
}

.text{
	font-size:2em;
	text-align:left;
	color:black;
	
}
.title {
text-align:center;

}

.btn btn-primary{
	width:125px;
    margin-left:auto;
    margin-right:auto;
}
img{
	margin:50px;
	border-radius:20px;
	border:5px lightblue solid;
}

</style>
</head>
<body bgcolor='lightgrey'>
<div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
</div>

<div class="container">
  <div class="row justify-content-md-center">
	    <div class="col col-lg-2"></div>
	    
	<div class="col-md-auto">
      <div class="input">
		 <h1 class="title">申請已完成 </h1><br>
		 <span class="text">修繕申請成功!請等候房東通知預計修畢日期與修繕結果</span><br>
		<span class="text">想要給房東看家具損壞的照片嗎? 這裡可以上傳損壞物品的照片(可多張)
		<a href="${pageContext.request.contextPath }/front-end/repair/lldListAllRepair.jsp?lld_no=${lld_no}"><button class="btn btn-primary">取消</button></a>	
		</span>


 		<div class="upload">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data">
			    <br><input type="file" id="myPic" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG">
			  	<br><br><input class="btn btn-primary" type="reset" id="delete" value="清除全部"> 
			  	<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
				<input type="hidden" name="action" value="pic_insert">
				<input class="btn btn-primary" type="submit" value="送出圖片">
				
			</FORM>
    	</div>
    	<div id="view">
	 	</div>
    </div>
   </div>
    <div class="col col-lg-2">
      	 
    </div>
	      
    
    
	    
  

</div>
</div>
</body>



<!-- <div class="upload"> -->
<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data"> --%>
<!--     <input type="file" id="myPic" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG"> -->
<!--   	<br><input type="reset" id="delete" value="清除全部">  -->
<%--   	<input type="hidden" name="rep_no" value="${repairVO.rep_no}"> --%>
<!-- 	<input type="hidden" name="action" value="pic_insert"> -->
<!-- 	<input class="btn btn-primary" type="submit" value="送出圖片"> -->
<!-- </FORM> -->
<!--  <div id="view"> -->
<!--  </div> -->
<!-- </div> -->
<script>
var myPic = document.getElementById("myPic");
var view = document.getElementById("view");

myPic.addEventListener('change', function(e) {
    var pics = myPic.files;
    console.log('pics');

    //選擇的檔案型別必為image，可選一個以上
    //pics.type.indexOf('image')?
    if (pics !== null) {
    	view.setAttribute('border', '5px lightblue solid');
    	view.setAttribute('border-radius', '30px');
        for (var i = 0; i < pics.length; i++) {
            // 取出pics物件的第i個
            var pic = pics[i];
			
            console.log(pic.name);
            console.log('圖片第 ' + (i + 1) + ' 個');

            //建立讀取檔案的FileReader
            var reader = new FileReader();

            //<!!!>多個圖片沒有跑出來，或有跑出來但是出現一樣(最後)的圖片:已解決，因為reader的參數原為e, 改成pic即可
            reader.addEventListener('load', function(pic) {
                //test:
                console.log('這是reader的' + pic.name);
                // 新增img的DOM元素
                var img = document.createElement('img');
                //設定圖片路徑給img  
                //reader.result?                        
                img.setAttribute('src', this.result);
                img.setAttribute('width', '400px');
                img.setAttribute('height', '300px');
                
                //將img放到view區塊
                view.append(img);
                e.stopPropagation() //停止向上冒泡
                console.log('reader註冊結束');

            }, false); //reader
            reader.readAsDataURL(pic);
        } //for
        pics.src = null;
    } //if
    //刪除已選取的圖片紀錄，這樣刪除圖片後才能再選同樣的圖片
    pics.value = '';
}, false); //pics 冒泡


//刪除全部圖片
var delete1 = document.getElementById("delete");
delete1.addEventListener('click', function(e) {
    view.innerHTML = "";
});



</script>


</html>