<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	RpttVO rpttVO = (RpttVO) request.getAttribute("rpttVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>該房客被檢舉的所有結果 - list_tnt.jsp</title>

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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>該房客所有被檢舉的結果 - list_reported_tenant.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>檢舉事件編號</th>
			<th>被檢舉房客編號</th>
			<th>檢舉房東編號</th>
			<th>檢舉時間</th>
			<th>檢舉內容</th>
			<th>處理員工</th>
			<th>處理完成時間</th>
			<th>處理狀態</th>
			<th>處理結果</th>
			<th>結果註記</th>
		</tr>
		<tr>
			<td><%=rpttVO.getRptt_no()%></td>
			<td><%=rpttVO.getTnt_no()%></td>
			<td><%=rpttVO.getLld_no()%></td>
			<td><%=rpttVO.getRptt_time()%></td>
			<td><%=rpttVO.getRptt_content()%></td>
			<td><%=rpttVO.getEmp_no()%></td>
			<td><%=rpttVO.getRptt_done_time()%></td>
			<%if(rpttVO.getRptt_status()==0){%>
			<td>未處理</td>
			<%}else{%><td>處理中</td> <%} %>
			
			<%if(rpttVO.getRptt_result()==1){%>
			<td>通過</td>
			<%}else{%><td>null</td> <%} %>
			<td><%=rpttVO.getRptt_note()%></td>
		</tr>

	</table>

</body>
</html>