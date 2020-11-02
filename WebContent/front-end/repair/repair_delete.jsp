<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>

<%
  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!-- 元 nav bar= -->
<link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>回報修繕結果 - repair_report.jsp</title>


<style>
.input{
	border:5px grey solid;
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
      	<h1 class="title">取消修繕申請</h1><br>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1">

 <div class="form-group">
		<label for="exampleFormControlInput1"><h3>修繕申請編號</h3></label>
		<span class="text"><%=repairVO.getRep_no()%></span>
</div>
<div class="form-group">
		
		<label for="exampleFormControlInput1"><h3>待修物品</h3></label>
		<span class="text">  <%=repairVO.getRep_dam_obj()%></span>
		
</div>
<div class="form-group">
		<label for="exampleFormControlInput1"><h3>損壞狀況</h3></label>
		<span class="text"><%=repairVO.getRep_dam_obj_des()%></span>
		
</div>
<div class="form-group">
		<label for="exampleFormControlInput1"><h3>損壞日期<h3></label>
		<span class="text"><%=repairVO.getRep_case_str()%></span>
</div>


<div class="form-group">
<input type="radio" name="rep_pro" value="3" checked> <span class="text">取消修繕申請!</span><br>
<input type="hidden" name="action" value="delRepair">
<input type="hidden" name="rep_no" value="<%=repairVO.getRep_no()%>"> <br>
<a href="${pageContext.request.contextPath }/front-end/repair/listAllRepair.jsp?tnt_no=${tnt_no}"><button class="btn btn-secondary">返回</button></a>	
<input style="text-align:center" class="btn btn-primary" type="submit" value="取消修繕申請">
<button class="btn btn-dark">檢舉</button>

</div>

</FORM>

</div>
    </div>
<!--     <div class="col col-lg-2"> </div>-->
      
    


</div>
</div>
</body>








<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=empVO.getHiredate()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
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