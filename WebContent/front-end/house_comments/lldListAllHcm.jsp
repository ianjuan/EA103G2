<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iZU House_comments Home by Landlord</title>
<script type="text/javascript">
var hcm_respon = document.getElementsByClassName('hcm_respon');
// var replyBtn = 	document.getElementsByClassName('replyBtn');

console.log("js");
document.getElementsByClassName('hcm_respon');
console.log(document.getElementsByClassName('hcm_respon'));
// document.getElementsByClassName("Btn").style.visibility="hidden";
Btn=document.getElementsByName('Btn');
if(hcm_respon==""){
Btn.style.display="none";
}


</script>
</head>
<body>


<label>我是房東的房屋評價總攬</label>

<!-- 針對某房東的所有房子查詢結果，以LLD000008為例，合併時會動態抓lld_no -->
<%-- <% session.setAttribute("lld_no", "LLD000008");%>: 登入後會設定 --%>
<!-- (String)session.getAttribute("lld_no") -->
<%-- <% --%>
<!--  HouseService hosSvc = new HouseService();  -->
<!--        List<HouseVO> hosList = hosSvc.getAllbylld( (String)session.getAttribute("lld_no"));  -->
<!--       pageContext.setAttribute("hosList", hosList); -->
<%--  %>	  --%>


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

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<!-- 方法二:選擇房屋編號(輸入房屋號碼) -->
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>輸入房屋編號 (如):</b>
        <input type="text" name="hos_no" >
        <input type="hidden" name="action" value="getHosAll_For_Display">
        <input type="submit" value="送出" >
    </FORM>

<table>
 
	<c:forEach var="house_commentsVO" items="${list}" >
		<tr>
			<td>${house_commentsVO.hcm_no}</td>
			<td>${house_commentsVO.tnt_no}</td>
			<td>${house_commentsVO.hos_no}</td>
			<td>${house_commentsVO.hcm_eqpmt}</td>
			<td>${house_commentsVO.hcm_convmt}</td>
			<td>${house_commentsVO.hcm_neibor}</td>
			<td>${house_commentsVO.hcm_commnt}</td> 
			<td>${house_commentsVO.hcm_time}</td>
			<td>
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
			</td>
			
<%-- 			<td><c:forEach var="house_commentsVO" items="${tenantSvc.all}"> --%>
<%--                     <c:if test="${house_commentsVO.tnt_no==tenantVO.tnt_no}"> --%>
<%-- 	                    ${tenantVO.tnt_no}【${tenantVO.tnt_name} - ${tenantVO.gender}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			
		</tr>
	</c:forEach>
</table>

</body>
</html>