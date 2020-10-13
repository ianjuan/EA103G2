<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rptt.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    RpttService rpttSvc = new RpttService();
    List<RpttVO> list = rpttSvc.getAllRptt();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��Ы����|��� - listAllRptt.jsp</title>

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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��Ы����|��� - listAllRptt.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

���G��C
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���|�s��</th>
		<th>�Q���|�ЫȽs��</th>
		<th>���|�ЪF�s��</th>
		<th>���|�ɶ�</th>
		<th>���|���e</th>
		<th>�B�z���u�s��</th>
		<th>�B�̧����ɶ�</th>
		<th>�B�z���A</th>
		<th>�B�z���G</th>
		<th>���G���O</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="rpttVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rpttVO.rptt_no}</td>
			<td>${rpttVO.tnt_no}</td>
			<td>${rpttVO.lld_no}</td>
			<td>${rpttVO.rptt_time}</td>
			<td>${rpttVO.rptt_content}</td>
			<td>${rpttVO.emp_no}</td> 
			<td>${rpttVO.rptt_done_time}</td>
			<td>${rpttVO.rptt_status}</td>
			<td>${rpttVO.rptt_result}</td>
			<td>${rpttVO.rptt_note}</td>
			<td>
			  <FORM METHOD="post" ACTION="RpttServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="rptt_no"  value="${rpttVO.rptt_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="RpttServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="rptt_no"  value="${rpttVO.rptt_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>