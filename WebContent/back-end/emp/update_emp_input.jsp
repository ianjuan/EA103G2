<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunctionService" />
<html>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/img/castle.ico" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<head>
<title>員工資料修改</title>
<!-- 外部js匯入 -->
</head>
<body>

        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">新增員工資料</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">

<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	<tr><td>
		 <h3>員工資料修改</h3>
		 
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" name="form1" enctype="multipart/form-data">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
		<td><input type="TEXT" name="emp_pwd" size="10" value="<%=empVO.getEmp_pwd()%>" /></td>
	</tr>
	<tr>
		<td>職位:</td>
		<td><input type="TEXT" name="emp_title" size="10"	value="<%=empVO.getEmp_title()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="emp_name" size="10"	value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>是否離職:</td>
		<td><input type="checkbox" name="emp_is_delete" data-toggle="toggle" value="<%=empVO.getEmp_is_delete() == null ? 1:0%>" /></td>
	</tr>
	<tr>

		<td>照片</td>
		<td>
		<label class="btn btn-info">
		<input type="file" id="imgInp" style="display:none "name="upload" accept="image/gif, image/jpeg, image/png">
		<i class="fa fa-photo">上傳圖片</i> 
		</label>
			<% if(empVO.getEmp_pic()!=null){%>
			<img id="blah" src="data:image/png;base64,<%=empVO.getEmp_pic()%>"width="100px">
			<%}%>
		<% if(empVO.getEmp_pic()==null) {%>
			<img id="blah" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU" width="100px">
		<%}%>
		<input type="hidden" name="emp_pic" value="<%=empVO.getEmp_pic()%>" />
		</td>
	</tr>
	<tr>
	<td>權限：</td>
	<td>
	<c:forEach var="fun_list" items="${funSvc.all}">
	<input type="checkbox" id="${fun_list.fun_no}" name="fun_no" value="${fun_list.fun_no}" data-toggle="toggle">  
	<label for="${fun_list.fun_no}">${fun_list.fun_name}</label>
	<c:forEach var="rigVO" items="${rigVO}">
	<c:if test="${fun_list.fun_no == rigVO.fun_no }">
<!-- 	預設勾選原有的權限 -->
	<script>${fun_list.fun_no}.checked=true</script>
	</c:if>
	</c:forEach>
	</c:forEach>
	</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
<input type="submit" class="btn btn-outline-primary" value="送出修改" id="su">
</FORM>
</div>
            </div>
          </div>

        </div>
<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();  
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
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