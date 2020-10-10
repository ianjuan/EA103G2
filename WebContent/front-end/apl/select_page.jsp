<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CONTRACT APPLICATION</title>

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
   <tr><td><h3>CONTRACT APPLICATION</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CONTRACT APPLICATION</p>

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
  <li><a href='<%=request.getContextPath()%>/front-end/apl/listAllCon_apl.jsp'>List</a> all APL.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" >
        <b>輸入租屋申請編號 (如APL000001):</b>
        <input type="text" name="apl_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                  
    </FORM>
  </li>
  
   <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" >
        <b>輸入房東編號 (如LLD000001):</b>
        <input type="text" name="lld_no">
        <input type="hidden" name="action" value="lldgetAll">
        <input type="submit" value="送出">                  
    </FORM>
  </li>
  
  <jsp:useBean id="Con_aplSvc" scope="page" class="com.apl.model.Con_aplService" />
  
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" >
       <b>選擇租屋申請編號:</b>
       <select size="1" name="apl_no">
         <c:forEach var="con_aplVO" items="${Con_aplSvc.all}" > 
          <option value="${con_aplVO.apl_no}">${con_aplVO.apl_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" >
       <b>選擇房客編號:</b>
       <select size="1" name="apl_no">
         <c:forEach var="con_aplVO" items="${Con_aplSvc.all}" > 
          <option value="${con_aplVO.apl_no}">${con_aplVO.tnt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>申請新增</h3>

<ul>
  <li><a href='addCon_apl.jsp'>Add</a> a new Con_apl.</li>
</ul>

</body>
</html>