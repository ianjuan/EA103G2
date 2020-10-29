<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair_picture.model.*"%>

<%
	String tnt_no = (String) session.getAttribute("tnt_no");
%>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFileBtn.css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/lightslider.css"">
<script src="<%=request.getContextPath()%>/front-end/repair/js/lightslider.js"></script> 

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/train.css"">

<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
<style>
	
	.demo .item .clearfix .image-gallery {
		bottom:0;
		right:0;
		z-index:10000;
		position: absolute;
	}
	.demo .item {
    margin-bottom: 0px;
    width: 0;
    height: 0;
	}
	img{
	border-radius: 10px;
	}
	
	#view img{
	width:90%;
	}

	.image-gallery li{
		bottom:0;
		right:0;
		z-index:10000;
		position: absolute;
	}
	.image-gallery img{
		bottom:0;
		right:0;
		z-index:10000;
		position: absolute;
	}
	
	.clearfix{
		width:300px;
		height:200px;
		
	}
/* 	輪播視窗大小 */
	div.lSSlideOuter{
		width:300px;
		height:200px;
		position : fixed;
		bottom:0;
		right:0;
		z-index:9999;
		border-radius: 10px;
		border: 5px solid pink;
	}
    	ul{
			list-style: none outside none;
		    padding-left: 0;
            margin: 0;
		}
        .demo .item{
            margin-bottom: 60px;
        }
		.content-slider li{
		    background-color: #ed3020;
/* 		    text-align: center; */
			text-align: left;
		    color: #FFF;
		}
		.content-slider h3 {
		    margin: 0;
		    padding: 70px 0;
		}
		.demo{
			width: 800px;
		}
/* 		怡晴 */
		 #picShow  {
      background-color: lightBlue;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
      text-align: center;
      height: 1000px;
      border-radius: 20px;
      
    }
     body {
      margin-top: 90px;
    }
     img:hover {
      box-shadow: 0 5px 30px rgba(0, 0, 0, 0.8);
    }
    .removeDiv {
      width: 300px;
      height: 300px;
      margin-bottom: 30px;
    }
    .removeDiv>img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
    }
/*     left nav bar */
    #left {
	width: 20%;
	height: 700px;
	float: left;
}

#noPic #picNon {
    font-size: xxx-large;
    font-color: grey;
    position: relative;
    bottom: 500px;
}

#goBcck{
	position: relative;
    bottom: 500px;
}
    
</style>
<script>
    	 $(document).ready(function() {
			$("#content-slider").lightSlider({
                loop:true,
                keyPress:true
            });
            $('#image-gallery').lightSlider({
                gallery:true,
                item:1,
                thumbItem:9,
                slideMargin: 0,
                speed:500,
                auto:true,
                loop:true,
                onSliderLoad: function() {
                    $('#image-gallery').removeClass('cS-hidden');
                }  
            });
		});
</script>
</head>
<body>
<!-- <div class='row'> -->
<%--   <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div> --%>
<!-- </div> -->

<%-- 錯誤表列 --%>


<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />

<%-- <c:choose> --%>
<%-- 	    	<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">	 --%>
<!-- 	    		<div id="trainDiv"> -->
<!-- 					<div class="center" id="center"> -->
<!-- 						  <div class="mountains"></div> -->
<!-- 						  <div class="frame"> -->
<%-- 						    <div class="train"><span id="${repairVO.rep_pro eq 0? 'noPic':'PicNon'}"><a> ${repairVO.rep_pro eq 0?"暫   無   圖   片，點  我  新  增 ":" 暫   無   圖   片 "}</a> </span> --%>
<%-- 						      	<a id="goBack" href="${pageContext.request.contextPath }/front-end/repair/listAllRepair.jsp?tnt_no=${tnt_no}"><button class="learn-more">回上一頁</button></a> --%>
<!-- 						      <div class="engine-front"> -->
<!-- 						        <div class="chimney"> -->
<!-- 						          <div class="smoke"></div> -->
<!-- 						          <div class="smoke smoke-2"></div> -->
<!-- 						          <div class="smoke smoke-3"></div> -->
<!-- 						          <div class="smoke smoke-4"></div> -->
<!-- 						        </div> -->
<!-- 						      </div> -->
						
<!-- 						      <div class="engine-body"></div> -->
						
<!-- 						      <div class="compartment"> -->
<!-- 						        <div class="compartment-window"></div> -->
<!-- 						      </div> -->
						
<!-- 						      <div class="compartment compartment-two"> -->
<!-- 						        <div class="compartment-window"></div> -->
<!-- 						      </div> -->
						
<!-- 						      <div class="compartment compartment-three"> -->
<!-- 						        <div class="compartment-window"></div> -->
<!-- 						      </div> -->
						
<!-- 						      <div class="wheel-holder"> -->
<!-- 						        <div class="wheel"></div> -->
<!-- 						        <div class="wheel wheel-2"> -->
<!-- 						          <div class="wheel-joint"></div> -->
<!-- 						          <div class="wheel-joint wheel-joint-2"></div> -->
<!-- 						        </div> -->
<!-- 						        <div class="wheel wheel-3"></div> -->
<!-- 						        <div class="wheel wheel-4"></div> -->
<!-- 						        <div class="wheel wheel-5"></div> -->
<!-- 						        <div class="wheel wheel-6"></div> -->
<!-- 						        <div class="wheel wheel-7"></div> -->
<!-- 						        <div class="wheel wheel-8"></div> -->
<!-- 						        <div class="wheel wheel-9"></div> -->
<!-- 						      </div> -->
<!-- 						    </div> -->
<!-- 						  </div> -->
<!-- 						  <div class="bridge"></div> -->
<!-- 					</div> -->
<!-- 	  	  		</div> -->
<%-- 	  	  	</c:when> --%>
<%-- 	  	  <c:otherwise>	 --%>



<!-- top/left bar -->
<%-- 	<div><jsp:include page="/front-end/navbar/navbar.jsp"/> </div> --%>
				<div id="left">
			<nav id="housenav">
				<div class="menu-btn">
					<div class="line line--1"></div>
					<div class="line line--2"></div>
					<div class="line line--3"></div>
				</div>
				<div class="nav-links">		
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="tntgetallapl">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="gettntcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repiar.servlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="getTntRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
					
				</div>
			</nav>
		</div>

		
		
<div class="container-fluid">
<!-- 輪播 -->
<!--  <div class="row"> -->
<!-- 				<div class="col-5"></div>    -->
<!-- 				<div class="col-6">   -->
						<div class="demo">
		        			<div class="item">            
		            			<div class="clearfix" style="max-width:400px;">
		    						<ul id="image-gallery" class="gallery list-unstyled cS-hidden">
					                  <c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">
											<c:if test="${(repair_pictureVO.reppic_no).indexOf('D')== -1}">
												<li data-thumb="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" > 
												
													<img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" width="300px" height="200px">
		
												</li>
											</c:if>
									  </c:forEach>
									</ul>
		            			</div>
		        			</div>
						</div>	
<!-- 				</div> -->
<!-- 				<div class="col-1"></div> -->
<!-- 			</div> -->
			
<!--   </div> -->
	
	
  <div class="row">
	
    <div class="col-3">
    	<div id="picShow">
      		<h2>預覽</h2><br>
	    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data"><br>
	    		<input type="file" id="myPic" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG"><br>
	  			<br><input type="reset" id="delete" value="清除全部"> 
	  			<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
	   			<input type="hidden" name="action" value="pic_upd_insert" >
				<button class="btn btn-outline-primary" type="submit" value="上傳" ${(repairVO.rep_pro eq 0)?'':'disabled'}>${(repairVO.rep_pro eq 0)?'上傳':'無法上傳'}</button>
	<%-- 			<input type="button" value="reppic_pic" onclick="document.forms[0].reppic_pic.outerHTML='<input type=file name=reppic_pic>'"> --%>
			</FORM> 
			<div id="view">
			</div>
		</div>
    </div>
    
    
    <div class="col-7" id="picShow"><h1>圖片區</h1><br>
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" style="margin-bottom: 0px;">
	    <button type="submit" class="btn btn-outline-warning" ${(repairVO.rep_pro eq 0)?'':'disabled'}>刪除</button>
	    	<div class="row">
	    		<c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">
		      			<c:if test="${(repair_pictureVO.reppic_no).indexOf('D')== -1}">
		      				<div class="col-6 photoDiv">
								<div class="removeDiv">
									<div class="form-check">
			  	  					<input id="checked${repair_pictureVO.reppic_no}" class="form-check-input" name="reppic_no[]" type="checkbox" value="${repair_pictureVO.reppic_no}" id="defaultCheck1">
									<label class="form-check-label" for="checked${repair_pictureVO.reppic_no}">
									    <img src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" class="card-img-top" alt="..." width="200"> 
									</label>
									</div>
		  	  					</div>
		  	  				</div>
		  	  			</c:if>	
		  	  			
	  	  		</c:forEach>
	  	  	</div>
  	  	<input type="hidden" name="rep_no"  value="${repairVO.rep_no}">
		<input type="hidden" name="action"	value="delPic">
  	  </FORM>
  	 </div>
  	  		
                 
	<div class="col-2"><a href="${pageContext.request.contextPath }/front-end/repair/listAllRepair.jsp?tnt_no=${tnt_no}"><button class="learn-more">回上一頁</button></a>
  	</div>
  </div> 
</div>


<%-- </c:otherwise> --%>
<%-- </c:choose> --%>
		
			
			
<script>
var noPic = document.getElementById("noPic");
var center = document.getElementById("center");
var trainDiv = document.getElementById("trainDiv");
noPic.addEventListener('click', function(e) {
	var childs = trainDiv.childNodes; 
	 for(var i = 0; i < childs.length; i++) {  
		 trainDiv.removeChild(childs[i]); 
		}
});

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
                img.setAttribute('style', "margin:20px" );
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