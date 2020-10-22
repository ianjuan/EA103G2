<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>評價管理</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style>

</style>
</head>
<body>
<ul class="nav nav-tabs">
<li class="nav-item">
    <a class="nav-link active" href="#">Disabled</a>
  </li>
  <li class="nav-item">
    <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/listAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/landlord_comments/listAllLcm.jsp">房東評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/tenant_comments/listAllTcm.jsp">我的評價</a>
  </li>
  
</ul>

 <script type="text/javascript">
 
 </script>
</body>
</html>