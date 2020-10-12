<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="<%=request.getContextPath()%>/back-end/css/wu-teacher.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">


<head>
<title>員工資料修改 - update_emp_input.jsp</title>
<!-- 外部js匯入 -->

<style>body{margin:10;}</style>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 
		 <h4><a href="<%=request.getContextPath()%>/back-end/emp/index.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>員工編號:</td>
		<td><%=empVO.getEmp_no()%><input type="hidden" name="emp_no" size="45" value="<%=empVO.getEmp_no()%>"/></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><%=empVO.getEmp_acc()%><input type="hidden" name="emp_acc" size="45" value="<%=empVO.getEmp_acc()%>"/></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="emp_pwd" size="45" value="<%=empVO.getEmp_pwd()%>" /></td>
	</tr>
	<tr>
		<td>職位:</td>
		<td><input type="TEXT" name="emp_title" size="45"	value="<%=empVO.getEmp_title()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="emp_name" size="45"	value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>是否刪除:</td>
		<td><input type="TEXT" name="emp_is_delete" size="45" value="<%=empVO.getEmp_is_delete()%>" /></td>
	</tr>
	<tr>
		<td>照片</td>
		<td><input type="file" id="imgInp" name="upload" accept="image/gif, image/jpeg, image/png">
			<% if(empVO.getEmp_pic()!=null){%>
			<img id="blah" src="data:image/png;base64,<%=empVO.getEmp_pic()%>"width="100px">
			<%}%>
		<% if(empVO.getEmp_pic()==null) {%>
			<img id="blah" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU" width="100px">
		<%}%>
		<input type="hidden" name="emp_pic" value="<%=empVO.getEmp_pic()%>" />
		</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
<input type="submit" class="btn btn-outline-primary" value="送出修改" id="su">
</FORM>
<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();  
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        if(input.files[0].type.match(/^image/)){
        reader.readAsDataURL(input.files[0]);
        }
    }
}
// 判斷是否為圖片
$("#imgInp").change(function(){
	su = document.getElementById("su");
	if(this.files[0].type.match(/^image/)){
	readURL(this);
	su.value="送出修改";
	su.disabled=false;
	}
	else{
	alert("只能上傳圖片哦~");
	su.value="請上傳圖片哦";
	su.disabled=true;
	}
});
</script>


</body>

</html>