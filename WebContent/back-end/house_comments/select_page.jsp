<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house_comments.model.*" %>
<%-- <jsp:useBean id="hosSvc" scope="page" class="com.tenant.model.tenantService" /> --%>
<html>
<head>
<title>iZU House_comments Home by back-end</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IZU HCM: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is Home page of House comments for back-end</p>

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

<ul>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>輸入房屋評價編號 (如):</b>
        <input type="text" name="hcm_no" value="${house_commentsVO.hcm_no}">
        <input type="hidden" name="action" value="BkgetOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

   
    <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>輸入房客編號 (如):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="BkGetTntAll_For_Display">
        <input type="submit" value="送出">
    </FORM>
  	</li>
   
     <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>輸入房屋編號 (如):</b>
        <input type="text" name="hos_no">
        <input type="hidden" name="action" value="BkGetHosAll_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
   
</ul>

<!-- 顯示單一房屋評價編號查詢，結果由servlet回傳資料 -->

		<c:if test="${not empty house_commentsVO}">
		<ul>
			<li>評價編號: ${house_commentsVO.hcm_no}</li>
			<li>房客編號: ${house_commentsVO.tnt_no}</li>
			<li>房屋編號: ${house_commentsVO.hos_no}</li>
			<li>設備是否齊全: ${house_commentsVO.hcm_eqpmt}</li>
			<li>機能是否方便: ${house_commentsVO.hcm_convmt}</li>
			<li>鄰居是否友善: ${house_commentsVO.hcm_neibor}</li> 
			<li>其他評價: ${house_commentsVO.hcm_commnt}</li> 
			<li>評價時間: ${house_commentsVO.hcm_time}</li> 
			<li>房東回覆: ${house_commentsVO.hcm_respon}</li> 
		</ul>
		</c:if>

<!-- 顯示針對某房客或某房屋的所有評價查詢結果，以TNT000008為例，合併時會動態抓tnt_no -->

<table>
	<c:forEach var="house_commentsVO" items="${list}" >
		<tr>
			<td>評價編號: ${house_commentsVO.hcm_no}</td>
			<td>房客編號: ${house_commentsVO.tnt_no}</td>
			<td>房屋編號: ${house_commentsVO.hos_no}</td>
			<td>設備是否齊全: ${house_commentsVO.hcm_eqpmt}</td>
			<td>機能是否方便: ${house_commentsVO.hcm_convmt}</td>
			<td>鄰居是否友善: ${house_commentsVO.hcm_neibor}</td>
			<td>其他評價: ${house_commentsVO.hcm_commnt}</td> 
			<td>評價時間: ${house_commentsVO.hcm_time}</td>
			<td>房東回覆: ${house_commentsVO.hcm_respon}</td>
			<td><c:forEach var="house_commentsVO" items="${tenantSvc.all}">
                    <c:if test="${house_commentsVO.tnt_no==tenantVO.tnt_no}">
	                    ${tenantVO.tnt_no}【${tenantVO.tnt_name} - ${tenantVO.gender}】
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>





</body>
</html>