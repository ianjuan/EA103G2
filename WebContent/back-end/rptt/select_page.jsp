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

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下所有的錯誤:</font>
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
				<b>檢舉事件編號 (e.g.RPTT000001):</b> <input type="text" name="Rptt_no">
				<input type="hidden" name="action" value="get_one_display">
				<input type="submit" value="送出">
			</FORM>
		</li>


		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>被檢舉的房客編號 (e.g.TNT000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>提出檢舉的房東編號 (e.g. LLD000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>處理的員工編號 (e.g. EMP000001):</b> <input type="text" name="Number">
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="RpttServlet">
				<b>檢舉狀態:</b> <select size="1" name="Number">
					<option value="0">未處理
					<option value="1">處理中
				</select> 
				<input type="hidden" name="action" value="get_want_display">
				<input type="submit" value="送出">
			</FORM>
		</li>

	</ul>

	<h3>檢舉管理</h3>

	<ul>
		<li><a href='add_rptt.jsp'>Add</a>新增檢舉</li>
	</ul>

</body>
</html>