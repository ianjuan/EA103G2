<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>

<% session.setAttribute("lld_no", "LLD000224");%>

<%-- 此測試頁，練習採用 EL 的寫法取值 --%>

<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />

<html>
<head>
<title>所有員工資料</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    font: 20px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    font: 18px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: blue;
    display: inline;
    margin: 5px;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

<style>
.myTable{
  width: 100%;
}
.myTable *{
  text-align: center;
}
</style>

</head>
<body bgcolor='white'>

<h4>此測試頁，練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料<br><font color=red>0000000<font color=blue>Bootstrap_modal</font>.jsp</font></h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>職位</th>
		<th>雇用日期</th>
		<th>薪水</th>
		<th>獎金</th>
		<th>部門</th>
	</tr>
 <c:forEach var="HouseVO" items="${hosSvc.allHouse}">
		<c:if test="${lld_no==HouseVO.lld_no}">
			<!-- conSvc.getConbyhos(HouseVO.hos_no)===ConVO -->
					<c:forEach var="repairVO" items="${repSvc.tntGetAll(conSvc.getConbyhos(HouseVO.hos_no).con_no)}">
						<c:if test="${repairVO.con_no==conSvc.getConbyhos(HouseVO.hos_no).con_no}">	
		<tr>
			<td><A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">${repairVO.rep_no}</a></td>
			<td>${repairVO.con_no}</td>
			<td>${repairVO.rep_no}</td>
			<td>${repairVO.rep_est_enddate}</td>
			<td>${repairVO.rep_dam_obj}</td>
			<td>${repairVO.rep_dam_obj}</td>			
			
		</tr>
		</c:if>
		</c:forEach>
		</c:if>
		
	</c:forEach>
</table> 

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="lld_update_repair_progress.jsp"/>
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
</body>
</html>