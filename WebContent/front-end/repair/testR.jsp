<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.apl.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
	
	${repSvc.getOneRepair('REP000007').con_no}

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
	
	<c:forEach var="Con_aplVO" items="${aplSvc.all}">
		${Con_aplVO.apl_no}
	</c:forEach>

<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
 <c:forEach var="ConVO" items="${conSvc.all}"> 
	${ConVO.con_no}
</c:forEach>

</body>
</html>