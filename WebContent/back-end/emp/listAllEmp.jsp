<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Custom fonts for this template -->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body id="page-top">

        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">������u���</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                 	    <th>���u�s��</th>
						<th>���u�b��</th>
						<th>���u�K�X</th>
						<th>���u¾��</th>
						<th>�m�W</th>
						<th>�O�_��¾</th>
						<th>�Ӥ�</th>
						<th>�ק�</th>
						<th>�d��</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                 	    <th>���u�s��</th>
						<th>���u�b��</th>
						<th>���u�K�X</th>
						<th>���u¾��</th>
						<th>�m�W</th>
						<th>�O�_��¾</th>
						<th>�Ӥ�</th>
						<th>�ק�</th>
						<th>�d��</th>
                    </tr>
                  </tfoot>
                  <tbody>
                  <c:forEach var="employeeVO" items="${list}">
                    <tr>
				<td>${employeeVO.emp_no}</td>
				<td>${employeeVO.emp_acc}</td>
				<td>${employeeVO.emp_pwd}</td>
				<td><c:choose>
						<c:when test="${employeeVO.emp_title eq 0}"> ���u</c:when>
						<c:when test="${employeeVO.emp_title eq 1}"> �D��</c:when>
						<c:when test="${employeeVO.emp_title eq 2}"> �g�z</c:when>
					</c:choose></td>
				<td>${employeeVO.emp_name}</td>
				<td>${employeeVO.emp_is_delete eq 0? "�b¾":"��¾"}</td>
				<td><c:if test="${not empty employeeVO.emp_pic}">
						<img src="data:image/png;base64,${employeeVO.emp_pic}"
							width="100px">
					</c:if> <c:if test="${empty employeeVO.emp_pic}">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTMF9nq4kTIfW-uuGD9R0-wyLcPACsO3CHbag&usqp=CAU"
							width="100px">
					</c:if></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do">
						<input type="text" value="�ק�" class="btn btn-outline-primary">
						<input type="hidden" name="emp_no" value="${employeeVO.emp_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do">
						<input type="text" value="�d��" class="btn btn-outline-primary">
						<input type="hidden" name="emp_no" value="${employeeVO.emp_no}">
						<input type="hidden" name="action" value="getOne_For_Display">
					</FORM>
				</td>
			</tr>
                    		</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->
      <!-- End of Main Content -->


  <!-- Bootstrap core JavaScript-->
<%--   <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script> --%>
<%--   <script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script> --%>
  <script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
  <script>
  $(".btn").click(function() {
	  var ajax_select;
	  var ajax_url="";
	  var emp_no = $(this).next().eq(0).attr("value");//���o�U�@�檺value �o�̬Oemp_no
// 	  var action = $(this).next().next().attr("value");
	  ajax_select = this.value;
	  if(ajax_select=="�d��"){
		  ajax_url = "<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Display&emp_no="+ emp_no;
	   }
	  else if(ajax_select=="�ק�"){
		  ajax_url = "<%=request.getContextPath()%>/back-end/emp/emp.do?action=getOne_For_Update&emp_no="+ emp_no;
  		}
  		console.log(ajax_url);
    $.ajax({
      type: "GET",
      url: ajax_url,
      dataType: "html",
      async:true,
      success: function(data) {
    	  if(ajax_url!=""){
        $("#ajax_result").html(data);
        }},
      error: function(xhr) {
        alert("Ajax�o�Ϳ��~:"+xhr.status);
        }     	        
	  });
  }); 
  </script>
</body>

</html>
