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
</head>
<body>
我是房屋評價總攬

 
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
	
		
	<!-- 針對某房客的所有評價查詢結果，以TNT000009為例，合併時會動態抓tnt_no -->
	
<% session.setAttribute("tnt_no", "TNT000009");%>
<!-- (String)session.getAttribute("tnt_no") -->

<%
  	House_commentsService hcmSvc = new House_commentsService();  
      List<House_commentsVO> list = hcmSvc.getAllbyTnt( (String)session.getAttribute("tnt_no"));  
      pageContext.setAttribute("list", list);  
  %>


<c:forEach var="house_commentsVO" items="${list}">
			
			<li>評價編號: ${house_commentsVO.hcm_no}</li>
			<li>房屋編號: ${house_commentsVO.hos_no}</li>
			<li>設備是否齊全: ${house_commentsVO.hcm_eqpmt}</li>
			<li>機能是否方便: ${house_commentsVO.hcm_convmt}</li>
			<li>鄰居是否友善: ${house_commentsVO.hcm_neibor}</li> 
			<li>其他評論:${house_commentsVO.hcm_commnt}</li> 
<!-- 			<li> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" > --%>
<!--         	<b>其他評價:</b> -->
<%--         	<input type="text" name="hcm_commnt" value="${house_commentsVO.hcm_commnt}"> --%>
<%--         	<input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}"> --%>
<!--         	<input type="hidden" name="action" value="getOne_For_Update"> -->
<!--         	<input type="submit" value="送出"> -->
<!--    			</FORM> -->
<!--     		</li> -->
			<li>評價時間: ${house_commentsVO.hcm_time}</li> 
			<li>房東回覆: ${house_commentsVO.hcm_respon}</li> 
			<br>
</c:forEach>


</body>
</html>