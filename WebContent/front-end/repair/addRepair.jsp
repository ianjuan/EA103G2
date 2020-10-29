<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>

<%

  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO");
	String con_no = (String)request.getAttribute("con_no");
%>

<%-- <% session.setAttribute("con_no", "CON000204");%> --%>

<% request.getAttribute("con_no");%>


<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增修繕申請 </title>
<style>
.input{
	border:5px lightblue solid;
	border-radius:30px;
	backgroundcolor:lightblue;
	padding:100px;
	margin:120px;
	color:grey;
	
}

.text{
	font-size:2em;
	text-align:left;
	color:black;
	
}
.title {
text-align:center;

}

.btn btn-primary{
	width:125px;
    margin-left:auto;
    margin-right:auto;
}
img{
	margin:50px;
	border-radius:20px;
	border:5px lightblue solid;
}

</style>

</head>
<body bgcolor='lightgrey'>

 <div class='row'>
  <div class='col-12 '><jsp:include page="/front-end/navbar/navbar.jsp" /></div>
</div>

	<div class="container">
  <div class="row justify-content-md-center">
<!-- 	    <div class="col col-lg-2"></div> -->
	    <div class="col-md-auto">
	    <div class="input">
		 <h1 class="title">新增修繕申請 </h1><br><br>
	
	


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" >
 <div class="form-group">
		<label for="exampleFormControlInput1"><h3>合約編號:</h3></label>
		<input type="TEXT" readonly name="con_no" class="form-control" id="exampleFormControlInput1"
			 value="<%= (repairVO==null)? session.getAttribute("con_no") :repairVO.getCon_no()%>" />
	 </div>
<div class="form-group">
		<label for="exampleFormControlSelect1"><h3>待修物品:</h3></label>
		<input type="text" name="rep_dam_obj" class="form-control" id="exampleFormControlSelect1" 
			 value="<%= (repairVO==null)? "" : repairVO.getRep_dam_obj()%>" />
</div>			
	
<div class="form-group">	
		<label for="exampleFormControlInput1"><h3>損壞狀況:</h3></label>
		<input type="TEXT" name="rep_dam_obj_des" size="45" class="form-control" id="exampleFormControlSelect1" 
			 value="<%= (repairVO==null)? "" : repairVO.getRep_dam_obj_des()%>" />
</div>	

 <div class="form-group">
		<label for="exampleFormControlSelect1"><h3>損壞日期:</h3></label>
		<input name="rep_case_str" id="f_date1" type="text" >
</div>

<br>
<input type="hidden" name="action" value="tnt_insert">
<a href="${pageContext.request.contextPath }/front-end/repair/lldListAllRepair.jsp?lld_no=${lld_no}"><button class="btn btn-primary">取消</button></a>	
<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">送出新增</button>
</FORM>



 </div>
   </div>
<!--     <div class="col col-lg-2"> -->
      	 
    </div>

</div>
</div>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date rep_case_str= null;
  try {
	  rep_case_str = repairVO.getRep_case_str();
   } catch (Exception e) {
	   rep_case_str = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>


        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=rep_case_str%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
//            minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


    </script> 
</html>