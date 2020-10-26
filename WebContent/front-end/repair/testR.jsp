<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>還不給我跑出來 阿阿阿阿</title>
</head>
<body>
123123123123123123123123
<%  
ConService conService = new ConService();
List<ConVO> list = conService.getAll(); 
System.out.println(list.size());
for(ConVO conVO: list){
	System.out.println(conVO.getApl_no());
}
%>



</body>
</html>