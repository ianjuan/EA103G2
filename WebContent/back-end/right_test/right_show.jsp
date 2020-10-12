<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.rig.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="funSvc" scope="page"
	class="com.fun.model.FunctionService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<%=request.getParameter("emp_no")%>有以下權限
	<br>
	<c:forEach var="rigVO" items="${rigVO}">
		<c:forEach var="funVO" items="${funSvc.all}">
    <c:if test="${funVO.fun_no == rigVO.fun_no }">
    	【${rigVO.fun_no}】 ${funVO.fun_name}<br>
    </c:if>
		</c:forEach>
	</c:forEach>

</body>
</html>