<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <%@ page import="com.lld.model.*"%> --%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- <% --%>
<!--  LldVO lldVO = (LldVO) request.getAttribute("lldVO"); //LldServlet.java(Concroller), �s�Jreq��lldVO����  -->
<%-- %>  --%>
<%String[] accStatuses = {"���ҥ�", "�w�ҥ�", "�b������"};%>

<html>
<head>
<title>�ЪF��� - listOneLld.jsp</title>

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
				<h3>�ЪF��� - ListOneLld.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/lld/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ЪF�s��</th>
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
			<%-- 		<td><%=lldVO.getLld_no()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_email()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_acc()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_pwd()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_id()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_name()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_birth()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_sex()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_mobile()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_city()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_dist()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_add()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_pic()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_status()%></td> --%>
			<%-- 		<td><%=lldVO.getLld_jointime()%></td> --%>
			<td>${lldVO.lld_no}</td>
			<td>${lldVO.lld_email}</td>
			<td>${lldVO.lld_acc}</td>
			<td>${lldVO.lld_pwd}</td>
			<td>${lldVO.lld_id}</td>
			<td>${lldVO.lld_name}</td>
			<td>${lldVO.lld_birth}</td>
			<td>${(lldVO.lld_sex)?'�k':'�k'}</td>
			<td>${lldVO.lld_mobile}</td>
			<td>${lldVO.lld_city}</td>
			<td>${lldVO.lld_dist}</td>
			<td>${lldVO.lld_add}</td>
			<td><img src="<%=request.getContextPath()%>/ImgReader?id=${lldVO.lld_no}"></td>
			<td><c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName"> 
					${lldVO.lld_status == varStatusName.index ? accStatus:""}
        		</c:forEach></td>	
			<td><fmt:formatDate value="${lldVO.lld_jointime}" pattern="yyyy-mm-dd" /></td>
		</tr>
	</table>


</body>
</html>