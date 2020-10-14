<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Report Tenant: Home</title>

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
		<tr>
			<td><h3>Report Tenant: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Report Tenant: Home</p>

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U�Ҧ������~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllRptt.jsp'>List</a> all Report Tenant. <br>
			<br></li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>���|�ƥ�s�� (e.g.RPTT000001):</b> <input type="text" name="Rptt_no">
				<input type="hidden" name="action" value="get_one_display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>


		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>�Q���|���ЫȽs�� (e.g.TNT000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>���X���|���ЪF�s�� (e.g. LLD000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>�B�z�����u�s�� (e.g. EMP000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>���|���A:</b> <select size="1" name="Number">
					<option value="0">���B�z
					<option value="1">�B�z��
				</select> 
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

	</ul>

	<h3>���|�޲z</h3>

	<ul>
		<li><a href='add_rptt.jsp'>Add</a>�s�W���|</li>
	</ul>

</body>
</html>