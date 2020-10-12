<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Tnt: Home</title>

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
   <tr><td><h3>IBM Tnt: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Tnt: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTnt.jsp'>List</a> all Tnts.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet" >
        <b>��J�|���s�� (�pTNT000001):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="TntSvc" scope="page" class="com.tnt.model.TntService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="tnt_no">
         <c:forEach var="TntVO" items="${TntSvc.allProfile}" > 
          <option value="${TntVO.tnt_no}">${TntVO.tnt_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet" >
       <b>��ܩЫȩm�W:</b>
       <select size="1" name="tnt_no">
         <c:forEach var="TntVO" items="${TntSvc.allProfile}" > 
          <option value="${TntVO.tnt_no}">${TntVO.tnt_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�|���޲z</h3>

<ul>
  <li><a href='addTnt.jsp'>Add</a> a new Tnt.</li>
</ul>

</body>
</html>