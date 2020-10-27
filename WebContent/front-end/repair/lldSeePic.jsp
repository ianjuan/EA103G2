<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair_picture.model.*"%>
<%
	String lld_no = (String) session.getAttribute("lld_no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看修繕物品照片</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFileBtn.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/animate.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/lightslider.css"">
<script src="<%=request.getContextPath()%>/front-end/repair/js/lightslider.js"></script> 
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

	.max{
		width:1000px;
		height:auto;
		}
		
	.min{
		width:100%;
		height:auto;
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
/*       background-color:lightgrey; */
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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


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
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRepair">
						<button type="submit" class="link">修繕管理</button><br>
					</FORM>
					
				</div>
			</nav>
		</div>

<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
		<c:choose>
	    	<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">	
	    	
	  	  		
					<div class="center">
						  <div class="mountains"></div>
						  <div class="frame">
						    <div class="train">
						      <div class="engine-front">
						        <div class="chimney">
						          <div class="smoke"></div>
						          <div class="smoke smoke-2"></div>
						          <div class="smoke smoke-3"></div>
						          <div class="smoke smoke-4"></div>
						        </div>
						      </div>
						
						      <div class="engine-body"></div>
						
						      <div class="compartment">
						        <div class="compartment-window"></div>
						      </div>
						
						      <div class="compartment compartment-two">
						        <div class="compartment-window"></div>
						      </div>
						
						      <div class="compartment compartment-three">
						        <div class="compartment-window"></div>
						      </div>
						
						      <div class="wheel-holder">
						        <div class="wheel"></div>
						        <div class="wheel wheel-2">
						          <div class="wheel-joint"></div>
						          <div class="wheel-joint wheel-joint-2"></div>
						        </div>
						        <div class="wheel wheel-3"></div>
						        <div class="wheel wheel-4"></div>
						        <div class="wheel wheel-5"></div>
						        <div class="wheel wheel-6"></div>
						        <div class="wheel wheel-7"></div>
						        <div class="wheel wheel-8"></div>
						        <div class="wheel wheel-9"></div>
						      </div>
						    </div>
						  </div>
						  <div class="bridge"></div>
					</div>
	  	  		
	  	  		</c:when>
	  	  		
	  	  		
	  	  		
	  	  	
	    	
	    	
	    	
	    	
	    	<c:otherwise>
	    		
<div class="container">
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
												
													<img class="min" src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" width="300px" height="200px">
		
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
    
    
    <div class="col-md-10" id="picShow"><h1>圖片區</h1><br>
	    	<div class="row">
	    		

		    		<c:forEach var="repair_pictureVO" items="${repSvc.getAllPicNo(repairVO.rep_no)}">
			      			<c:if test="${(repair_pictureVO.reppic_no).indexOf('D')== -1}">
			      				<div class="col-6 photoDiv">
									<div class="removeDiv">
										
										    <img class="imageresource" src="<%=request.getContextPath()%>/repair/repair_picture.servlet?reppic_no=${repair_pictureVO.reppic_no}" class="card-img-top" alt="..." width="200"> 
										
			  	  					</div>
			  	  				</div>
			  	  			</c:if>	
			  	  			
		  	  		</c:forEach>
	  	  		
	  	  		
	  	  	</div>
  	 </div>
  	  		
                 
	<div class="col-md-2"><a href="${pageContext.request.contextPath }/front-end/repair/lldListAllRepair.jsp?lld_no=${lld_no}"><button class="learn-more">回上一頁</button></a>
  	</div>
  </div> 
</div>
</c:otherwise>
</c:choose>
        
			
			
			



<script>

</script>
</body>
</html>