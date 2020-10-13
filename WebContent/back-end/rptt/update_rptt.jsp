<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rptt.model.*"%>

<%
RpttVO rpttVO = (RpttVO) request.getAttribute("rpttVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���|��ƭק� - update_rptt.jsp</title>

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
		 <h3>���|��ƭק� - update_rptt.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="RpttServlet" name="form1">
<table>
	<tr>
		<td>���|�s��:<font color=red><b>*</b></font></td>
		<td><%=rpttVO.getRptt_no()%></td>
	</tr>
	<tr>
		<td>�Q���|���ЫȽs��:</td>
		<td><input type="TEXT" name="tnt_no" size="45" value="<%=rpttVO.getTnt_no()%>" /></td>
	</tr>
	<tr>
		<td>���|���ЪF�s��:</td>
		<td><input type="TEXT" name="lld_no" size="45"	value="<%=rpttVO.getLld_no()%>" /></td>
	</tr>
	
	<tr>
		<td>���|���e:</td>
		<td><input type="TEXT" name="rptt_content" size="45" value="<%=rpttVO.getRptt_content()%>" /></td>
	</tr>
	
	<tr>
		<td>�B�z���u�s��:</td>
		<td><input type="TEXT" name="emp_no" size="45" value="<%=rpttVO.getEmp_no()%>" /></td>
	</tr>
	
	<tr>
		<td>���|���A:</td>
	       <%if(rpttVO.getRptt_status()==0){%>
			<td><select size="1" name="rptt_status">
					<option value="0">���ѨM
					<option value="1">�w�ѨM
				</select></td>
			<%}else{%><td><select size="1" name="rptt_status">
					<option value="1">�w�ѨM
					<option value="0">���ѨM
				</select> </td><%} %>
	     </tr>          
	<tr>		
		<td>���|���G:</td>
		<%if(rpttVO.getRptt_result()==1){%>
			<td><select size="1" name="rptt_result">
					<option value="1">�q�L
					<option value="0">���q�L
				</select></td>
			<%}else{%><td><select size="1" name="rptt_result">
					<option value="0">���q�L
					<option value="1">�q�L
				</select> </td><%} %>
	</tr>
	
	<tr>
		<td>���G���O:</td>
		<td><input type="TEXT" name="rptt_note" size="45" value="<%=rpttVO.getRptt_note() %>" /></td>
	</tr>
	
  


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="rptt_no" value="<%=rpttVO.getRptt_no()%>">
<input type="hidden" name="emp_no" value="<%=rpttVO.getEmp_no()%>">
<input type="hidden" name="rptt_status" value="<%=rpttVO.getRptt_status()%>">
<input type="hidden" name="rptt_result" value="<%=rpttVO.getRptt_result()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
