<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.landlord_comments.model.*" %>
<%-- <jsp:useBean id="hosSvc" scope="page" class="com.tenant.model.tenantService" /> --%>
<html>
<head>
<title>iZU Landlord_comments Home by back-end</title>

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
   <tr><td><h3>IZU LCM: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is Home page of Landlord comments for back-end</p>

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
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>輸入房東評價編號 (如):</b>
        <input type="text" name="lcm_no" value="${landlord_commentsVO.lcm_no}">
        <input type="hidden" name="action" value="BkgetOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

   
    <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>輸入房客編號 (如):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="BkGetTntAll_For_Display">
        <input type="submit" value="送出">
    </FORM>
  	</li>
   
     <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>輸入房東編號 (如):</b>
        <input type="text" name="lld_no">
        <input type="hidden" name="action" value="BkGetLldAll_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
   
</ul>

<!-- 顯示單一房屋評價編號查詢，結果由servlet回傳資料 -->

		<c:if test="${not empty landlord_commentsVO}">
		<ul>
			<li>評價編號: ${landlord_commentsVO.lcm_no}</li>
			<li>房客編號: ${landlord_commentsVO.tnt_no}</li>
			<li>房屋編號: ${landlord_commentsVO.lld_no}</li>
			<li>設備是否齊全: ${landlord_commentsVO.lcm_clean}</li>
			<li>機能是否方便: ${landlord_commentsVO.lcm_commut}</li>
			<li>鄰居是否友善: ${landlord_commentsVO.lcm_satisfy}</li> 
			<li>其他評價: ${landlord_commentsVO.lcm_commet}</li> 
			<li>評價時間: ${landlord_commentsVO.lcm_time}</li> 
			<li>房東回覆: ${landlord_commentsVO.lcm_respon}</li> 
		</ul>
		</c:if>

<!-- 顯示針對某房客或某房屋的所有評價查詢結果，以TNT000008為例，合併時會動態抓tnt_no -->

<table>
	<c:forEach var="landlord_commentsVO" items="${list}" >
		<tr>
			<td>評價編號: ${landlord_commentsVO.lcm_no}</td>
			<td>房客編號: ${landlord_commentsVO.tnt_no}</td>
			<td>房東編號: ${landlord_commentsVO.lld_no}</td>
			<td>房東是否會定期維持環境清潔: ${landlord_commentsVO.lcm_clean}</td>
			<td>房東是否願意與房客溝通: ${landlord_commentsVO.lcm_commut}</td>
			<td>總體而言，對房東的評價: ${landlord_commentsVO.lcm_satisfy}</td>
			<td>其他評價: ${landlord_commentsVO.lcm_commet}</td> 
			<td>評價時間: ${landlord_commentsVO.lcm_time}</td>
			<td>房東回覆: ${landlord_commentsVO.lcm_respon}</td>
			
		</tr>
	</c:forEach>
</table>





</body>
</html>