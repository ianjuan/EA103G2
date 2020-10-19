<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
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
<title>���u��ƭק�</title>
<!-- �~��js�פJ -->
</head>
<body>

        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">�s�W���u���</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">

<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	<tr><td>
		 <h3>���u��ƭק�</h3>
		 
		 <h4><a href="<%=request.getContextPath()%>/back-end/emp/index.jsp">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" name="form1" enctype="multipart/form-data">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	<tr>
		<td>���u�s��:</td>
		<td><%=empVO.getEmp_no()%><input type="hidden" name="emp_no" size="45" value="<%=empVO.getEmp_no()%>"/></td>
	</tr>
	<tr>
		<td>�b��:</td>
		<td><%=empVO.getEmp_acc()%><input type="hidden" name="emp_acc" size="45" value="<%=empVO.getEmp_acc()%>"/></td>
	</tr>
	<tr>
		<td>�K�X:</td>
		<td><input type="TEXT" name="emp_pwd" size="10" value="<%=empVO.getEmp_pwd()%>" /></td>
	</tr>
	<tr>
		<td>¾��:</td>
		<td><input type="TEXT" name="emp_title" size="10"	value="<%=empVO.getEmp_title()%>" /></td>
	</tr>
	<tr>
		<td>�m�W:</td>
		<td><input type="TEXT" name="emp_name" size="10"	value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>�O�_��¾:</td>
		<td><input type="checkbox" name="emp_is_delete" data-toggle="toggle" value="<%=empVO.getEmp_is_delete() == null ? 1:0%>" /></td>
	</tr>
	<tr>

		<td>�Ӥ�</td>
		<td>
		<label class="btn btn-info">
		<input type="file" id="imgInp" style="display:none "name="upload" accept="image/gif, image/jpeg, image/png">
		<i class="fa fa-photo">�W�ǹϤ�</i> 
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
	<td>�v���G</td>
	<td>
	<c:forEach var="fun_list" items="${funSvc.all}">
	<input type="checkbox" id="${fun_list.fun_no}" name="fun_no" value="${fun_list.fun_no}" data-toggle="toggle">  
	<label for="${fun_list.fun_no}">${fun_list.fun_name}</label>
	<c:forEach var="rigVO" items="${rigVO}">
	<c:if test="${fun_list.fun_no == rigVO.fun_no }">
<!-- 	�w�]�Ŀ�즳���v�� -->
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
<input type="submit" class="btn btn-outline-primary" value="�e�X�ק�" id="su">
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
// �P�_�O�_���Ϥ�
$("#imgInp").change(function(){
	su = document.getElementById("su");
	if(this.files[0].type.match(/^image/)){
	readURL(this);
	su.value="�e�X�ק�";
	su.disabled=false;
	}
	else{
	alert("�u��W�ǹϤ��@~");
	su.value="�ФW�ǹϤ��@";
	su.disabled=true;
	}
});
</script>


</body>

</html>