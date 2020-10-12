<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello</title>
</head>
<body>
<FORM ACTION="<%=request.getContextPath()%>/RightServlet" method="post">
	<input type="text" name="emp_no" value="EMP000001">
	<input type="hidden" name="action" value="search">
	<input type="submit" value="¬d¸ß">
</FORM>
<FORM ACTION="<%=request.getContextPath()%>/RightServlet" method="post">
	<input type="text" name="emp_no" value="EMP000001">
	<input type="hidden" name="action" value="update">
	<input type="submit" value="­×§ï">
</FORM>
</body>
</html>