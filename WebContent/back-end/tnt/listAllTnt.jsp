<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.tnt.model.*"%> --%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%-- <% --%>
<!--    	TntService tntSvc = new TntService();  -->
<!--    	List<TntVO> list = tntSvc.getAllProfile();  -->
<!--    	pageContext.setAttribute("list", list);  -->
<%-- %>  --%>
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<%String[] accStatuses = {"���ҥ�", "�w�ҥ�", "�b������"};%>


<html>
<head>
<title>�Ҧ��Ыȸ�� - listAllTnt.jsp</title>

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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����u��� - listAllTnt.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/tnt/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
			<th>�ק�</th>
		</tr>
		<%-- 	<%@ include file="page1.file" %>  --%>
		<%-- 	<c:forEach var="tntVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

		<%-- 		<c:forEach var="tntVO" items="${list}"> --%>
		<%-- 		<c:forEach var="tntVO" items="<%=tntSvc.getAllProfile()%>"> --%>
		<c:forEach var="tntVO" items="${tntSvc.allProfile}">

			<tr>
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
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tnt/TntServlet" style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> 
						<input type="hidden" name="tnt_no" value="${tntVO.tnt_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%-- <%@ include file="page2.file" %> --%>

</body>
</html>