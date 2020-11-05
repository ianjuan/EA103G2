<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
        <!DOCTYPE html>
        <html>

        <head>
<link rel="icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/back-end/images/castle.ico" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<style>
.bg-gradient-primary {
    background-color: #36b9cc;
    background-image: linear-gradient(180deg,#36b9cc 10%,#258391 100%);
    background-size: cover;
}
.btn-primary {
    color: #fff;
    background-color: #36b9cc;
    border-color: #4e73df;
}
</style>
        </head>

        <body class="bg-gradient-primary">
            <div class="container">
                <!-- Outer Row -->
                <div class="row justify-content-center">
                    <div class="col-xl-10 col-lg-12 col-md-9">
                        <div class="card o-hidden border-0 shadow-lg my-5">
                            <div class="card-body p-0">
                                <!-- Nested Row within Card Body -->
                                <div class="row">
                                    <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                    <div class="col-lg-6">
                                        <div class="p-5">
                                            <div class="text-center">
                                                <h1 class="h4 text-gray-900 mb-4">登入</h1>
                                            </div>
                                            <form class="user" action="<%=request.getContextPath()%>/back-end/emp/emp.do" method="post">
                                                <div class="form-group">
                                                    <input type="text" class="form-control form-control-user" id="Account"  placeholder="帳號：" name="emp_acc" >
                                                </div>
                                                <div class="form-group">
                                                    <input type="password" class="form-control form-control-user" id="Password" placeholder="密碼：" name="emp_pwd" >
                                                </div>
                                                <div class=" form-group">
                                                    
                                                </div>
                                                <input type="hidden" name="action" value="login">
												<input type="submit" value="送出" class="btn btn-primary btn-user btn-block">            
                                                <hr>
                                            </form>
                                            <input id="master" type="submit" value="經理快速登入" class="btn btn-primary btn-user">
                                            <input id="employee" type="submit" value="員工3號" class="btn btn-primary btn-user">
<!--                                             韋恩借放按鈕 -->
                                            <input id="employee1" type="submit" value="員工21號" class="btn btn-primary btn-user" style="margin-top:5px";>
                                            <hr>
                                            <div class="text-center">
                                                <a class="small" href="<%=request.getContextPath()%>/back-end/emp/forgot.jsp">忘記密碼?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
            $('#master').click(function(){
            	$('#Account').val('yybwDf0M');
            	$('#Password').val('v23PnY2C');
            })
            $('#employee').click(function(){
            	$('#Account').val('jo1Otouz');
            	$('#Password').val('ub5jWVpH');
            })
               $('#employee1').click(function(){
            	$('#Account').val('BY2PR5AR');
            	$('#Password').val('kHv0ZQa3');
            })
            </script>
        </body>

        </html>