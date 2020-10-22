<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iZU House_comments Home by Tenant</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="nav-item">
    <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/addHcm.jsp">新增評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/house_comments/listAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/landlord_comments/listAllLcm.jsp">房東評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/tenant_comments/listAllTcm.jsp">我的評價</a>
  </li>
  
</ul>
<br>



<!-- 針對某房客的所有評價查詢結果，以TNT000009為例，合併時會動態抓tnt_no -->
	
<% session.setAttribute("tnt_no", "TNT000009");%>


<%
  	House_commentsService hcmSvc = new House_commentsService();  
      Set<String> hosSet = hcmSvc.getAllHos_no( (String)session.getAttribute("tnt_no"));  
      pageContext.setAttribute("hosSet", hosSet);  
  %>
  
<%-- <c:if test="${hos_no}==${hos_no}" selected> --%>
<!-- 選擇房屋查看房屋評論	 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
       <!--   取得此房客評論過的房子編號 -->
		<select size="1" name="hos_no">
			<c:forEach var="hos_no1" items="${hosSet}">
				<option value="${hos_no1}">
				${hos_no1}
				<c:forEach var="houseVO" items="${houseSvc.all}">
                    <c:if test="${hos_no1==houseVO.hos_no}">
	                    	房屋【${houseVO.hos_name}】 
                    </c:if>
                </c:forEach>
			</c:forEach>
		</select>
		<input type="hidden" name="tnt_no" value="${tnt_no}">
        <input type="hidden" name="action" value="getTntAll_For_Display">
        <input type="submit" value="送出">
 </FORM>
  
<!--  列出所有評論 -->
 <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet">       
 		<input type="hidden" name="tnt_no" value="${tnt_no}">
        <input type="hidden" name="action" value="getAll_For_Display">
        <input type="submit" value="列出全部房屋評論">
      
</FORM>
 
<%-- <% --%>
<!--    	House_commentsService hcmSvc = new House_commentsService();   -->
<!--        List<House_commentsVO> list = hcmSvc.getAllbyTnt( (String)session.getAttribute("tnt_no"));   -->
<!--       	pageContext.setAttribute("list", list);   -->
<%--   %> --%>





 <div id="accordion">

		<c:forEach var="house_commentsVO" items="${list}">
			 <div class="card">
			 <div class="card-header" id="headingOne">
			  <h5 class="mb-0">
        		<button class="btn btn-link" data-toggle="collapse" data-target="# ${house_commentsVO.hcm_no}" aria-expanded="true" aria-controls="collapseOne">
         			<c:forEach var="houseVO" items="${houseSvc.all}">
                    <c:if test="${house_commentsVO.hos_no==HosDetVO.hos_no}">
	                 	房屋: ${HosDetVO.hos_name}  評價時間: ${house_commentsVO.hcm_time}
                    </c:if>
                	</c:forEach>
         			${house_commentsVO.hcm_no}
       			</button>
     		 </h5>
   			</div>

   <div id=" ${house_commentsVO.hcm_no}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
      <ul>
        	<li>評價編號: ${house_commentsVO.hcm_no}</li>
			<li>房屋編號: ${house_commentsVO.hos_no}</li>
			<li>房屋編號: ${house_commentsVO.tnt_no}</li>
			<li>設備是否齊全: ${house_commentsVO.hcm_eqpmt}</li>
			<li>機能是否方便: ${house_commentsVO.hcm_convmt}</li>
			<li>鄰居是否友善: ${house_commentsVO.hcm_neibor}</li> 
			
<%-- 			<li>其他評論:${house_commentsVO.hcm_commnt}</li> --%>
			 
			 <c:choose>
        	<c:when test="${not empty house_commentsVO.hcm_commnt}">
        	<li>其他評論:${house_commentsVO.hcm_commnt}</li>
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        	<input type="text" name="hcm_commet" value="${house_commentsVO.hcm_commnt}">
        	<input type="hidden" name="tnt_no"  value="${house_commentsVO.tnt_no}">
        	<input type="hidden" name="hos_no"  value="${house_commentsVO.hos_no}">
        	<input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}">
        	<input type="hidden" name="action"  value="getOne_For_Update">
        	<input type="submit" value="送出文字評價" >
        	</FORM>
        	</c:otherwise>
        	</c:choose>
			 
			<li>評價時間: ${house_commentsVO.hcm_time}</li> 
			<li>房東回覆: ${house_commentsVO.hcm_respon}</li> 
		</ul>	
      	</div>
    	</div>
  		</div>
</c:forEach>
 </div>  
  




 
<%-- 	<c:forEach var="house_commentsVO" items="${list}" > --%>
		
<!-- 		<tr> -->
<%-- 			<td>${house_commentsVO.hcm_no}</td> --%>
<%-- 			<td>${house_commentsVO.hos_no}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_eqpmt}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_convmt}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_neibor}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_commnt}</td>  --%>
<%-- 			<td>${house_commentsVO.hcm_time}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_respon}</td> --%>
<%-- 			<td><c:forEach var="house_commentsVO" items="${tenantSvc.all}"> --%>
<%--                     <c:if test="${house_commentsVO.tnt_no==tenantVO.tnt_no}"> --%>
<%-- 	                    ${tenantVO.tnt_no}【${tenantVO.tnt_name} - ${tenantVO.gender}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="回覆評價"> -->
<%-- 			     <input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="getOne_For_Reply"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
	
		
	



</body>
</html>