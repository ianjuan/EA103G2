<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
        <!DOCTYPE html>
        <html>
		<title>愛租 等待跳轉</title>
        <head>
        <meta http-equiv="refresh" content="5;url=<%=request.getContextPath()%>/back-end/emp/login.jsp"> 
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
                                                <h1 class="h4 text-gray-900 mb-4">認證成功！ 請至認證信箱收取信件!</h1>
                                            </div>
                                            <form class="user" action="<%=request.getContextPath()%>/back-end/emp/login.jsp" method="post">
                                                <div class="form-group">
                                                    
                                                </div>
                                                
                                                <div class=" form-group">
<!--                                                     <div class="custom-control custom-checkbox small"> -->
<!--                                                         <input type="checkbox" class="custom-control-input" id="customCheck"> -->
<!--                                                         <label class="custom-control-label" for="customCheck">記得帳密</label> -->
<!--                                                     </div> -->
                                                </div>
                                                <input type="hidden" name="action" value="forgot">
												<input type="submit" id="su" value="5秒後自動回到登入頁面..." class="btn btn-primary btn-user btn-block">            
                                                <hr>
                                            </form>
                                            <hr>
                                            <div class="text-center">
                                                <a class="small" href="<%=request.getContextPath()%>/back-end/emp/login.jsp">回到登入頁面</a>
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
            $('#su').click(){
            	alert("hi");
            }
            </script>
        </body>

        </html>