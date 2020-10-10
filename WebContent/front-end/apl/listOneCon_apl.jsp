<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.apl.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Con_aplVO con_aplVO = (Con_aplVO) request.getAttribute("con_aplVO"); //EmpServlet.java(Controller), 存入req的empVO物件
  Con_aplService con_aplSvc = new Con_aplService();
%>

<html>
<head>
<title>租屋申請資料 - listOneCon_apl.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>租屋申請資料 - ListOneCon_apl.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/apl/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/front-end/apl/images/123.png" width="100" height="70" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>租屋申請編號</th>
		<th>房客編號</th>
		<th>房屋編號</th>
		<th>申請時間</th>
		<th>開始租屋時間</th>
		<th>結束租屋時間</th>
		<th>申請狀態</th>
	</tr>
	<tr>
		<td><%=con_aplVO.getApl_no()%></td>
		<td><%=con_aplVO.getTnt_no()%></td>
		<td><%=con_aplVO.getHos_no()%></td>
		<td><%=con_aplVO.getApl_time()%></td>
		<td><%=con_aplVO.getApl_str()%></td>
		<td><%=con_aplVO.getApl_end()%></td>
		<td><%=con_aplSvc.getCon_statusText(con_aplVO.getApl_status())%></td>
	</tr>
</table>

</body>
</html>