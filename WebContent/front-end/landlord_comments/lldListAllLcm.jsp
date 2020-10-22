<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.landlord_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>iZU Landlord_comments Home by Landlord</title>
</head>
<body>
<ul class="nav nav-tabs">
<li class="nav-item">
	<a class="nav-link " href="<%=request.getContextPath()%>/front-end/tenant_comments/addTcm.jsp">新增評價</a>
  </li>
   <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/tenant_comments/lldListAllTcm.jsp">房客評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/house_comments/lldListAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/landlord_comments/lldListAllLcm.jsp">我的評價</a>
  </li>
</ul>


<!-- 針對某房東的所有房子查詢結果，以LLD000008為例，合併時會動態抓lld_no -->
<% session.setAttribute("lld_no", "LLD000008");%>
<!-- (String)session.getAttribute("lld_no") -->

<%
 Landlord_commentsService lcmSvc = new Landlord_commentsService(); 
       List<Landlord_commentsVO> list = lcmSvc.getAllbyLld( (String)session.getAttribute("lld_no")); 
       pageContext.setAttribute("list", list); 
 %>	 
	
<%-- <%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 	    <c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>


<!-- <!-- 方法二:選擇房屋編號(輸入房屋號碼) --> 
<%--     <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" > --%>
<!--         <b>輸入房東編號 (如):</b> -->
<!--         <input type="text" name="lld_no" > -->
<!--         <input type="hidden" name="action" value="getLldAll_For_Display"> -->
<!--         <input type="submit" value="送出" > -->
<!--     </FORM> -->


<!-- 蓋子	 -->
	<div id="accordion">
	
<%-- 	<%@ include file="page1.file" %> --%>
<%-- 		<c:forEach var="landlord_commentsVO" items="${list}"　begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
		<c:forEach var="landlord_commentsVO" items="${list}">
			 <div class="card">
			 <div class="card-header" id="headingOne">
			  <h5 class="mb-0">
        		<button class="btn btn-link" data-toggle="collapse" data-target="# ${landlord_commentsVO.lcm_no}" aria-expanded="true" aria-controls="collapseOne">
         			<c:forEach var="LldVO" items="${LldService.getAllVrf}">
                    	<c:if test="${landlord_commentsVO.lld_no==LldVO.lld_no}">
	                		評論編號: ${landlord_commentsVO.lcm_no} - 評論時間: ${landlord_commentsVO.lcm_time}
                    	</c:if>
                	</c:forEach>
                	${landlord_commentsVO.lcm_no}
        		</button>
      			</h5>
			</div>
<!-- 瓶子 -->
	<div id=" ${landlord_commentsVO.lcm_no}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
      	<ul>
			<li>評價編號: ${landlord_commentsVO.lcm_no}</li>
			<li>房客編號: ${landlord_commentsVO.tnt_no}</li>
			<li>房東編號: ${landlord_commentsVO.lld_no}</li>
			<li>房東是否會在交屋前維持房屋清潔: ${landlord_commentsVO.lcm_clean}</li>
			<li>房東是否願意與房客溝通協商: ${landlord_commentsVO.lcm_commut}</li>
			<li>整體而言對房東的滿意度: ${landlord_commentsVO.lcm_satisfy}</li>
			<li>房客對您的其他評論: ${landlord_commentsVO.lcm_commet}</li> 
			<li>房客留下此評論的時間: ${landlord_commentsVO.lcm_time}</li>
        	<li><b>我的回覆評價:</b>
        	<c:choose>
        	<c:when test="${not empty landlord_commentsVO.lcm_respon}">
        	    ${landlord_commentsVO.lcm_respon}
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        	<input type="text" name="lcm_respon" value="${landlord_commentsVO.lcm_respon}">
        	<input type="hidden" name="lld_no"  value="${landlord_commentsVO.lld_no}">
        	<input type="hidden" name="lcm_no"  value="${landlord_commentsVO.lcm_no}">
        	<input type="hidden" name="action"  value="getOne_For_Respon">
        	<input type="submit" value="回覆" >
        	</FORM>
        	</c:otherwise>
        	</c:choose>
        	</li>
       </ul>
			</div>
    		</div>
			</div>
	</c:forEach>
</div>
<%-- <%@ include file="page2.file" %> --%>
</body>
</html>