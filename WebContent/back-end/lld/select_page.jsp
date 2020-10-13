<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Lld: Home</title>

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
   <tr><td><h3>IBM Lld: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Lld: Home</p>

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
  <li><a href='listAllLld.jsp'>List</a> all Llds.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" >
        <b>輸入會員編號 (如LLD000001):</b>
        <input type="text" name="lld_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="LldSvc" scope="page" class="com.lld.model.LldService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" >
       <b>選擇會員編號:</b>
       <select size="1" name="lld_no">
         <c:forEach var="LldVO" items="${LldSvc.allProfile}" > 
          <option value="${LldVO.lld_no}">${LldVO.lld_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" >
       <b>選擇房東姓名:</b>
       <select size="1" name="lld_no">
         <c:forEach var="LldVO" items="${LldSvc.allProfile}" > 
          <option value="${LldVO.lld_no}">${LldVO.lld_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='addLld.jsp'>Add</a> a new Lld.</li>
</ul>

</body>
</html>