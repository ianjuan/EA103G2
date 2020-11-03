<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Elegant Success Modal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="main.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
.trigger-btn {
	display: inline-block;
	margin: 100px auto;
}
</style>
</head>

<body>
	<div class="text-center">
		<!-- Button HTML (to Trigger Modal) -->
		<a href="#"  data-toggle="modal"
			data-target="#exampleModal">Click to Open Success Modal</a>
	</div>
	<!-- Modal HTML -->
	<div class="modal fade" id="exampleModal" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class='row' id="row_rpth">
						<div class='col-md-11'>
							<h3 class="modal-title">檢舉房客</h3>
						</div>
						<div class='col-md-1'>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
					</div>
					<div class="modal-body">
						<form action="RpttServlet" method="post" id="myForm"
							class="myForm">
							<label for="name">您的帳號:</label> <input class="rpth" type="text" name="lld_no"
								value="LLD000045" id="iacc" readonly> <label for="name" >檢舉的房客帳號:</label>
							<input class="rpth" type="text" name="tnt_no" value="TNT000022" id="yacc"
								readonly>
							<div class="form-group">
								<label for="reason">檢舉原因:</label>
								<textarea id="reason" name="rptt_content"></textarea>
							</div>

							<input class="rpth" type="hidden" name="action" value="insert">
							<button id="demo">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
	<script
		src="${pageContext.request.contextPath}/front-end/rptt/member.js"></script>

</html>