<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair_picture.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修繕物品照片增刪</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		${repairVO.rep_no}
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
		
<div class="container">
  <div class="row">
    <div class="col-sm">
      	<h2>預覽</h2><br>
    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data">
    		<input type="file" id="myPic" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG">
  			<br><input type="reset" id="delete" value="清除全部"> 
  			<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
   			<input type="hidden" name="action" value="pic_upd_insert">
			<button class="btn btn-outline-primary" type="submit" value="上傳">上傳</button>
<%-- 			<input type="button" value="reppic_pic" onclick="document.forms[0].reppic_pic.outerHTML='<input type=file name=reppic_pic>'"> --%>
		</FORM> 
		<div id="view">
		</div>
    </div>
    
    
    <div class="col-sm"><h1>圖片區</h1><br>
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
	    <button type="submit" class="btn btn-outline-warning">刪除</button>
	    		<c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">
	      			
		      			<c:if test="${(repair_pictureVO.reppic_no).indexOf('D')== -1}">
		      				<div class="form-check">
		  	  					<input class="form-check-input" name="reppic_no[]" type="checkbox" value="${repair_pictureVO.reppic_no}" id="defaultCheck1">
								<label class="form-check-label" for="defaultCheck1">
								    刪除這張照片
								</label>
							</div>
		  					<img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" class="card-img-top" alt="..." >
		  	  			</c:if>	
		  	  			
	  	  		</c:forEach>
  	  	<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
		<input type="hidden" name="action"	value="delPic">
  	  </FORM>
  	 </div>
  	  		
<%-- 		</c:forEach> --%>

    </div>
	
  </div>
<!-- </div> -->

<script>
var myPic = document.getElementById("myPic");
// var filename = document.getElementById("filename");

myPic.addEventListener('change', function(e) {
    var pics = myPic.files;
    console.log('pics');

    //選擇的檔案型別必為image，可選一個以上
    //pics.type.indexOf('image')?
    if (pics !== null) {

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
                //將img放到preview區塊
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

</body>
</html>