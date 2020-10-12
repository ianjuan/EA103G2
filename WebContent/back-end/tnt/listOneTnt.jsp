<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <%@ page import="com.tnt.model.*"%> --%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- <% --%>
<!--  TntVO tntVO = (TntVO) request.getAttribute("tntVO"); //TntServlet.java(Concroller), �s�Jreq��tntVO����  -->
<%-- %>  --%>
<%String[] accStatuses = {"���ҥ�", "�w�ҥ�", "�b������"};%>

<html>
<head>
<title>�Ыȸ�� - listOneTnt.jsp</title>

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
	width: 600px;
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
		<tr>
			<td>
				<h3>�Ыȸ�� - ListOneTnt.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/tnt/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ЫȽs��</th>
			<th>�H�c</th>
			<th>�b��</th>
			<th>�K�X</th>
			<th>������</th>
			<th>�m�W</th>
			<th>�ͤ�</th>
			<th>�ʧO</th>
			<th>���</th>
			<th>����</th>
			<th>�ϰ�</th>
			<th>�a�}</th>
			<th>�Y�K</th>
			<th>���A</th>
			<th>�[�J�ɶ�</th>
		</tr>
		<tr>
			<%-- 		<td><%=tntVO.getTnt_no()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_email()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_acc()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_pwd()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_id()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_name()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_birth()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_sex()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_mobile()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_city()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_dist()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_add()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_pic()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_status()%></td> --%>
			<%-- 		<td><%=tntVO.getTnt_jointime()%></td> --%>
			<td>${tntVO.tnt_no}</td>
			<td>${tntVO.tnt_email}</td>
			<td>${tntVO.tnt_acc}</td>
			<td>${tntVO.tnt_pwd}</td>
			<td>${tntVO.tnt_id}</td>
			<td>${tntVO.tnt_name}</td>
			<td>${tntVO.tnt_birth}</td>
			<td>${(tntVO.tnt_sex)?'�k':'�k'}</td>
			<td>${tntVO.tnt_mobile}</td>
			<td>${tntVO.tnt_city}</td>
			<td>${tntVO.tnt_dist}</td>
			<td>${tntVO.tnt_add}</td>
			<td><img src="<%=request.getContextPath()%>/ImgReader?id=${tntVO.tnt_no}"></td>
			<td><c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName"> 
					${tntVO.tnt_status == varStatusName.index ? accStatus:""}
        		</c:forEach></td>	
			<td><fmt:formatDate value="${tntVO.tnt_jointime}" pattern="yyyy-mm-dd" /></td>
		</tr>
	</table>


</body>
</html>