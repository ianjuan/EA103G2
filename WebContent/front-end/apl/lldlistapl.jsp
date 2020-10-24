<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apl.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Con_aplService Con_aplSvc = new Con_aplService();
//     List<Con_aplVO> list = Con_aplSvc.getAll();
    List<Con_aplVO> list = (List<Con_aplVO>)session.getAttribute("list");
    pageContext.setAttribute("list",list);
    pageContext.setAttribute("Con_aplSvc",Con_aplSvc);
%>


<html>
<head>
<title>�Ҧ����Υӽи�� - listAllCon_apl.jsp</title>

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
	width: 800px;
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

<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����Υӽи�� - listAllCon_apl.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/apl/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/front-end/apl/images/123.png" width="100" height="70" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
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
		<th>���Υӽнs��</th>
		<th>�ЫȽs��</th>
		<th>�Ыνs��</th>
		<th>�ӽЮɶ�</th>
		<th>�}�l���ήɶ�</th>
		<th>�������ήɶ�</th>
		<th>�ӽЪ��A</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="con_aplVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${con_aplVO.apl_no}</td>
			<td>${con_aplVO.tnt_no}</td>
			<td>${con_aplVO.hos_no}</td>
			<td>${con_aplVO.apl_time}</td>
			<td>${con_aplVO.apl_str}</td>
			<td>${con_aplVO.apl_end}</td>
			<td>${Con_aplSvc.getCon_statusText(con_aplVO.apl_status)}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="apl_no"  value="${con_aplVO.apl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>