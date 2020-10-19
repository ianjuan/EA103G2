<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
 List <RpttVO> rpttVO = (List <RpttVO>) request.getAttribute("rpttVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>����j�M�����|���G - list_rptt.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>����j�M�����|���G - list_rptt.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���|�ƥ�s��</th>
		<th>�Q���|�ЫȽs��</th>
		<th>���|�ЪF�s��</th>
		<th>���|�ɶ�</th>
		<th>���|���e</th>
		<th>�B�z���u</th>
		<th>�B�z�����ɶ�</th>
		<th>�B�z���A</th>
		<th>�B�z���G</th>
		<th>���G���O</th>
	</tr>
	<% for(RpttVO rpttvo:rpttVO){%>
	<tr>
		<td><%=rpttvo.getRptt_no()%></td>
		<td><%=rpttvo.getTnt_no()%></td>
		<td><%=rpttvo.getLld_no()%></td>
		<td><%=rpttvo.getRptt_time()%></td>
		<td><%=rpttvo.getRptt_content()%></td>
		<td><%=rpttvo.getEmp_no()%></td>
		<td><%=rpttvo.getRptt_done_time()%></td>
	    <%if(rpttvo.getRptt_status()==0){%>
			<td>���B�z</td>
			<%}else{%><td>�B�z��</td> <%} %>
			 
		<%if(rpttvo.getRptt_result()==1){%>
			<td>�q�L</td>
			<%}else{%><td>null</td> <%} %>
		<td><%=rpttvo.getRptt_note()%></td>
	</tr>
	
	<% }%>
</table>

</body>
</html>