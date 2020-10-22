<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tenant_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iZU Tenant_comments Home by Landlord</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript">

</script>
</head>
<body>
<ul class="nav nav-tabs">
<li class="nav-item">
     <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/addHcm.jsp">新增評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/listAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/landlord_comments/listAllLcm.jsp">房東評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/tenant_comments/listAllTcm.jsp">我的評價</a>
  </li>
  
</ul>



<!-- 針對某房東的所有房子查詢結果，以LLD000008為例，合併時會動態抓lld_no -->
<% session.setAttribute("tnt_no", "TNT000020"); %>
<!-- (String)session.getAttribute("lld_no") -->
<%
 	Tenant_commentsService tcmSvc = new Tenant_commentsService(); 
     List <Tenant_commentsVO> list = tcmSvc.getAllbyTnt( (String)session.getAttribute("tnt_no"));  
     pageContext.setAttribute("list", list); 
  %>	  


<!-- 方法一:選擇房屋編號(下拉式選單) -->

<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" > --%>
<!--        <b>選擇房屋編號:</b> -->
<!--        <select size="1" name="hos_no"> -->
<%--          <c:forEach var="houseVO" items="${hosList}" >  --%>
<%--           <option name="hos_no" value="${houseVO.hos_no}">${houseVO.house_title} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getHosAll_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->

	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<!-- <!-- 方法二:選擇房客編號(輸入房客號碼) --> 
<%--     <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" > --%>
<!--         <b>輸入房東編號 (如):</b> -->
<!--         <input type="text" name="tnt_no" > -->
<!--         <input type="hidden" name="action" value="getTntAll_For_Display"> -->
<!--         <input type="submit" value="送出" > -->
<!--     </FORM> -->

<div id="accordion">
	<c:forEach var="tenant_commentsVO" items="${list}" >
	<div class="card">
			 <div class="card-header" id="headingOne">
			  <h5 class="mb-0">
        		<button class="btn btn-link" data-toggle="collapse" data-target="# ${tenant_commentsVO.tcm_no}" aria-expanded="true" aria-controls="collapseOne">
         			<c:forEach var="TntVO" items="${TntService.getAllVrf}">
                    <c:if test="${tenant_commentsVO.tnt_no==TntVO.tnt_no}">
	                 	評價編號:${tenant_commentsVO.tcm_no} 評論時間:${tenant_commentsVO.tcm_time}
                    </c:if>
                	</c:forEach>
         			 ${tenant_commentsVO.tcm_no}
        		</button>
      			</h5>
			</div>
	
	<div id=" ${tenant_commentsVO.tcm_no}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
	
			<li>評價編號: ${tenant_commentsVO.tcm_no}</li>
			<li>房客編號: ${tenant_commentsVO.tnt_no}</li>
			<li>房東編號: ${tenant_commentsVO.lld_no}</li>
			<li>房客是否維持房屋整潔: ${tenant_commentsVO.tcm_clean}</li>
			<li>房客是否願意與房東溝通: ${tenant_commentsVO.tcm_commut}</li>
			<li>房東對房客的滿意度: ${tenant_commentsVO.tcm_satisfy}</li>
			<li>房東對房客的評論與建議: ${tenant_commentsVO.tcm_commet}</li> 
			<li>評論時間: ${tenant_commentsVO.tcm_time}</li>
			
        	<b>回覆評價:</b>
        	<c:choose>
        	<c:when test="${not empty tenant_commentsVO.tcm_respon}">
<%--         	<input readonly type="text" name="tcm_respon" value="${tenant_commentsVO.tcm_respon}"> --%>
        	${tenant_commentsVO.tcm_respon}
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
        	<input type="text" name="tcm_respon" value="${tenant_commentsVO.tcm_respon}">
        	<input type="hidden" name="tnt_no"  value="${tenant_commentsVO.tnt_no}">
        	<input type="hidden" name="tcm_no"  value="${tenant_commentsVO.tcm_no}">
        	<input type="hidden" name="action"  value="getOne_For_Respon">
        	<input type="submit" value="回覆" >
        	</FORM>
        	</c:otherwise>
        	</c:choose>
			
			</div>
    		</div>
			</div>
	</c:forEach>
</div>

</body>
</html>