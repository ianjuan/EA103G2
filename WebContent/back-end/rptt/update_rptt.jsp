<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rptt.model.*"%>

<%
RpttVO rpttVO = (RpttVO) request.getAttribute("rpttVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>檢舉資料修改 - update_rptt.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 750px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>檢舉資料修改 - update_rptt.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="RpttServlet" name="form1">
<table>
	<tr>
		<td>檢舉編號:<font color=red><b>*</b></font></td>
		<td><%=rpttVO.getRptt_no()%></td>
	</tr>
	<tr>
		<td>被檢舉的房客編號:</td>
		<td><input type="TEXT" name="tnt_no" size="45" value="<%=rpttVO.getTnt_no()%>" /></td>
	</tr>
	<tr>
		<td>檢舉的房東編號:</td>
		<td><input type="TEXT" name="lld_no" size="45"	value="<%=rpttVO.getLld_no()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉內容:</td>
		<td><input type="TEXT" name="rptt_content" size="45" value="<%=rpttVO.getRptt_content()%>" /></td>
	</tr>
	
	<tr>
		<td>處理員工編號:</td>
		<td><input type="TEXT" name="emp_no" size="45" value="<%=rpttVO.getEmp_no()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉狀態:</td>
	       <%if(rpttVO.getRptt_status()==0){%>
			<td><select size="1" name="rptt_status">
					<option value="0">未解決
					<option value="1">已解決
				</select></td>
			<%}else{%><td><select size="1" name="rptt_status">
					<option value="1">已解決
					<option value="0">未解決
				</select> </td><%} %>
	     </tr>          
	<tr>		
		<td>檢舉結果:</td>
		<%if(rpttVO.getRptt_result()==1){%>
			<td><select size="1" name="rptt_result">
					<option value="1">通過
					<option value="0">不通過
				</select></td>
			<%}else{%><td><select size="1" name="rptt_result">
					<option value="0">不通過
					<option value="1">通過
				</select> </td><%} %>
	</tr>
	
	<tr>
		<td>結果註記:</td>
		<td><input type="TEXT" name="rptt_note" size="45" value="<%=rpttVO.getRptt_note() %>" /></td>
	</tr>
	
  


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="rptt_no" value="<%=rpttVO.getRptt_no()%>">
<input type="hidden" name="emp_no" value="<%=rpttVO.getEmp_no()%>">
<input type="hidden" name="rptt_status" value="<%=rpttVO.getRptt_status()%>">
<input type="hidden" name="rptt_result" value="<%=rpttVO.getRptt_result()%>">
<input type="submit" value="送出修改"></FORM>
</body>
