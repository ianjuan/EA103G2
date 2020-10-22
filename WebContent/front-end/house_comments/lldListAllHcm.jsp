<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>iZU House_comments Home by Landlord</title>
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
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/house_comments/lldListAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/landlord_comments/lldListAllLcm.jsp">我的評價</a>
  </li>
</ul>



<% session.setAttribute("lld_no", "LLD000008");%>

<!-- (String)session.getAttribute("lld_no") -->
<%-- <% --%>


<!-- 0. new 一個house service -->

<!--    HouseService hosSvc = new HouseService();   -->
<!--          List<HouseVO> hosList = hosSvc.getAllbylld( (String)session.getAttribute("lld_no"));  -->
<!--        pageContext.setAttribute("hosList", hosList); -->
<%--   %>	  --%>

<!-- --------1. 取得該lld_no有的hos_noSet，join house table--------- -->

<%-- <c:forEach var="houseVO" items="${houseSvc.all}"> --%>
<%--   	<c:if test="${lld_no==houseVO.lld_no}"> --%>
<%--   		<%Set <String> hosSet = new HashSet(); --%>
<%-- <%--    		hosSet.add(${houseVO.hos_no}); --%> 
<!--    		request.setAttribute("hosSet", hosSet); -->
<!-- 	  	%> -->
<%--    </c:if> --%>
<%-- </c:forEach> --%>

<!------------- 2. 將hos_noSet變成下拉式選單 (顯示房屋名稱要join house table)--------------------->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
	<select size="1" name="hos_no">
			<c:forEach var="hos_no1" items="${hosSet}">
				<option value="${hos_no1}">
				${hos_no1}
<%-- 				<c:forEach var="houseVO" items="${houseSvc.all}"> --%>
<%--                     <c:if test="${hos_no1==houseVO.hos_no}"> --%>
<%-- 	                    	房屋【${houseVO.hos_name}】  --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
			</c:forEach>
		</select>
        <input type="hidden" name="action" value="getHosAll_For_Display">
        <input type="submit" value="送出">
</FORM>




	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<div id="accordion">
	<c:forEach var="house_commentsVO" items="${list}" >
		 <div class="card">
			 <div class="card-header" id="headingOne">
			  <h5 class="mb-0">
			  <button class="btn btn-link" data-toggle="collapse" data-target="# ${house_commentsVO.hcm_no}" aria-expanded="true" aria-controls="collapseOne">
					<c:forEach var="houseVO" items="${houseSvc.all}">
					 <c:if test="${house_commentsVO.hos_no==HosDetVO.hos_no}">
	                 	房屋名稱: ${HosDetVO.hos_name}  評價時間: ${house_commentsVO.hcm_time}
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
			<li>房客編號: ${house_commentsVO.tnt_no}</li>
			<li>房屋編號: ${house_commentsVO.hos_no}</li>
			<li>房屋名稱: 
			<c:forEach var="houseVO" items="${houseSvc.all}">
                    <c:if test="${house_commentsVO.hos_no==houseVO.hos_no}">
	                    	${houseVO.hos_name}
                    </c:if>
                </c:forEach>
            </li>
			<li>房客對於房屋的設施(設備)的滿意度: ${house_commentsVO.hcm_eqpmt}</li>
			<li>房客對於房屋附近的機能滿意度: ${house_commentsVO.hcm_convmt}</li>
			<li>房客對於房屋的左鄰右舍的滿意程度: ${house_commentsVO.hcm_neibor}</li>
			<li>房客對於此房屋的其他評價: ${house_commentsVO.hcm_commnt}</li> 
			<li>房客評價時間: ${house_commentsVO.hcm_time}</li>
			<li>
        	<b>回覆評價:</b>
        	<c:choose>
        	<c:when test="${not empty house_commentsVO.hcm_respon}">
        	<input readonly type="text" name="hcm_respon" value="${house_commentsVO.hcm_respon}">
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        	<input type="text" name="hcm_respon" value="${house_commentsVO.hcm_respon}">
        	<input type="hidden" name="hos_no"  value="${house_commentsVO.hos_no}">
        	<input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}">
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

</body>
</html>