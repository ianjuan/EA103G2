<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href='<%=request.getContextPath()%>/resource/fullcalendar/main.css'
	rel='stylesheet' />
<script
	src='<%=request.getContextPath()%>/resource/fullcalendar/main.js'></script>
<script
	src='<%=request.getContextPath()%>/resource/fullcalendar/locales-all.js'></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resource/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.datetimepicker.full.js"></script>

<script
	src="<%=request.getContextPath()%>/resource/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/bootstrap.min.css">


<title>Insert title here</title>



</head>
<style>
body {
	background-color: transparent;
}

.centered {
	margin: 0 20px;
}

.finer-print {
	font-size: 15px;
}
.order-list{
padding-right:0px;
padding-left:0px;
}
.span2 {
	padding: 0;
	float: left;
}
.order-row{
   margin-right: 0px; 
    margin-left: 0px;
}
.calendar-row{
   margin-right: 0px; 
    margin-left: 0px;
}
.business-card {
	position: relative;
	background: #eeeeec;
	color: #2e3436;
	font-size: 20px;
	border-radius: 15px;
	box-shadow: 0 0 3px #000;
	margin-top: 3%;
}

.business-card .title {
	background: #c1b4a3;
	color: #fff;
	font-weight: bold;
	font-size: larger;
	border-top: 2px solid #c1b4a3;
	border-left: 2px solid #c1b4a3;
	border-radius: 14px ;
}

.business-card .content {
	font-weight: bold;
	padding-top: 15px;
	padding-left: 15px;
}

.business-card .avatar {
	max-width: 100px;
	max-height: 100px;
	box-shadow: 0 0 10px 1px #777;
	border-radius: 3px;
}

.business-card .footer {
	position: absolute;
	bottom: 10px;
	left: 2%;
	font-size: 15px;
	padding-top: 5px;
	border-top: 1px solid;
	width: 96%;
}
</style>
<body>
	
<div class="row calendar-row">
	<div class="col-8" id='calendar'></div>
	<div class="col-4 order-list">
	</div>
</div>
	<!-- Modal -->

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">請輸入欲添加時段</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="datetimeStart">
					<td><input id="date" type="text"></td>
				</div>
				<div class="modal-footer">
					<input type="checkbox" id="allday" />選擇整天
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id='save'>Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
	<%@ page import="com.booking.model.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.booking.model.BookingVO"%>
	<%@ page import="com.google.gson.*"%>
	<%
		BookingService bks = new BookingService();
	String hosno=(String)session.getAttribute("HOS");
	String lldno=(String)session.getAttribute("lld_no");

			String list = bks.getBookingInfoListBylldno(lldno);
			String resorder = bks.getResOrderbylldno(lldno);
			pageContext.setAttribute("list", list);//KEY，VALUE
			pageContext.setAttribute("order", resorder);//KEY，VALUE
			String tntno=(String)session.getAttribute("tnt_no");

	%>
</body>

<script>




//---------------------------------------------------------------
var list= JSON.parse('${list}');//JSON轉JS格式
var order= JSON.parse('${order}');
var ordercount=0;
var ob;
var allowtime=['10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00'];


$('#save').click(function(){//確認新增預約
	  $('#exampleModal').modal('hide');	
	   	var theday=$("#date").val()
	   	var arrayfortime=[];
	   	var Itallday= true;
     	if($('#allday').prop('checked')){
     		theday=ob;
     	};
	   	if(theday.length<12){	   		
	 		 for (let value of allowtime) { 
	 			console.log(theday+value);
	 			arrayfortime.push(theday+value)}}
	   	else{
	   		Itallday=false;
	   		arrayfortime.push(theday)
	   	}
	   	console.log(arrayfortime);
	  $.ajax({//存入資料庫階段
		  url:"<%=request.getContextPath()%>/booking/bookingServlet",
	 	  type:"POST",
	 	  data:{
	 		  action:"insert",
	 		  data:JSON.stringify(arrayfortime),	
	 	 	  lld_no:"<%=lldno %>"
	 	  },
		success:function(id){//以上成功才執行
	 		  console.log(id+"HEN棒");
			 var data=JSON.parse(id);
				 data.forEach(function(obj){
					  	 calendar.addEvent({
					       start:obj.resd_date,
					       resdno:obj.resd_no//給他ID
					     });
				});			 
		
		},error:function(data){
		 		  console.log("真的不棒")
		 	  }	 		    
	 		});	  			
	 	  });
	 	 	
  document.addEventListener('DOMContentLoaded', function() {
	 
    var calendarEl = document.getElementById('calendar');   
    calendar = new FullCalendar.Calendar(calendarEl, {
     headerToolbar: {
    	        left: 'prev,next today',
    	        center: 'title',
    	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    	      },
    	      height: 650,
     editable: true,     
      //hiddenDays: [0], 星期幾不顯示 [0]-[6]=禮拜日-六
      slotMinTime: "09:00:00",//最早與最晚時間
      slotMaxTime: "22:00:00",
      locale: 'zh-tw',
      initialDate: new Date(),//初始時間 目前為當前時間
      navLinks: true, //日期可點嗎?
      selectable: true,/////可以新增日期嗎?
      selectMirror: true,
      selectHelper :true,
      unselectAuto: true,
      validRange: {
    	    start: new Date()
    	  },

      select: function(info) {
    	  dateStr = info.startStr;
		 ob=info.startStr;
    	  console.log(dateStr);
    	  $('#exampleModal').modal('show');	//預約設定畫面SHOW  	  
    	 $("#date").datetimepicker({//預約設定選時間的初始化
    		 value:dateStr+"T10:00",
    		 format:'Y-m-d H:i',
    		 autoclose: true,
    		    todayBtn: true,
    		    language: 'zh-CN',
    		    step:30,
    		    allowTimes:allowtime,
    		    minView: 1      
    		});
    	
           calendar.unselect()
        },
       eventClick: function(arg) {//點選日曆上已成立的
    	   console.log(arg);
    	   console.log(arg.event.extendedProps.timemoment);
   		console.log(arg.event.extendedProps.status);
 	   if(arg.event.extendedProps.status=='1'){
 		   alert("已被預約無法直接刪除時段，請與房客聯繫");
 		   return;
 	   }
       console.log(arg.event);
    	   console.log(arg.event.extendedProps);       	
    	   console.log(arg.event.extendedProps.resdno);
    	   if(open){ //如果是房東
    		   if (confirm('確定要刪除該時段嗎?')) {//按鈕確認是否 回傳相對應ture false
    			   $.ajax({//存入資料庫階段
    					  url:"<%=request.getContextPath()%>/booking/bookingServlet",
    				 	  type:"POST",
    				 	  data:{ action:"deletelld",
    				 		  data:arg.event.extendedProps.resdno
    				 	  },
    				 	  success:function(data){//以上成功才執行
    				 		  console.log(data);
    		        			 arg.event.remove();

    				 		  	console.log("res棒");
    				 	  },
    				 	  error:function(data)
    				 	  {
    				 		  console.log("真的不棒")
    				 	  }
    				  
    				  })
        			 
       				}}
   
   
    	
       
       },
     editable: false,
      dayMaxEvents: true, // allow "more" link when too many events
       
    });
    
    order.forEach(function(order){
    	console.log("resno="+order.res_no);
    	if(ordercount==0){
    		$(".order-list").append(
    			"<div class='row order-row'>"+
    			"<div class='business-card centered'>"+
    				"<div class='title' style='border-radius:14px 14px 0 0'>即將到來的預約</div>"+
    				"<div class='content span2'>"+
    					"<img src='<%=request.getContextPath()%>/ImgReader?id="+order.tnt_no+"' alt='' class='avatar' />"+
    				"</div>"+
    				"<div class='content span2'>"+
    					"<span class='finer-print'>預約人:"+order.tnt_name+"<br /> 預約時段:"+order.order_date+ "<br />"+
    						"連絡電話:"+order.tnt_mobile+"<br /> 預約房屋物件:"+order.hos_name+ "<br /> 地址:"+
    						order.hos_add+"<br />"+
    					"</span>"+
//     					"<button>查看房客資訊</button>"+
//     					"<button>取消預約</button>"+
    				"</div>"+
    			"</div>"+
    		"</div>"
    	)}
    	else{
    		$(".order-list").append(
    				"<div class='row order-row'>"+
        			"<div class='business-card centered'>"+
        				"<div class='title'>預約時段:"+order.order_date+ "</div>"+
        				"<div class='content span2' style='display:none'>"+
        					"<img src='<%=request.getContextPath()%>/ImgReader?id="+order.tnt_no+"' alt='' class='avatar' />"+
        				"</div>"+
        				"<div class='content span2' style='display:none' > "+
    					"<span class='finer-print'>預約人:"+order.tnt_name+"<br /> 預約時段:"+order.order_date+ "<br />"+
        						"連絡電話:"+order.tnt_mobile+"<br /> 預約房屋物件:"+order.hos_name+ "<br /> 地址:"+
        						order.hos_add+"<br />"+
        					"</span>"+
//         					"<button>查看房客資訊</button>"+
//         					"<button>取消預約</button>"+
        				"</div>"+
        			"</div>"+
        		"</div>"
		)
    	}
    	ordercount++;
    });
     list.forEach(function(data){
    	 var titles;//讓被預約的STATUS不能再點選
		 console.log(data.resd_status);
		console.log(data)
    	 if(data.resd_status=="1"){
    	 calendar.addEvent({
 			start:data.resd_date,
 			resdno:data.resd_no,
             status:data.resd_status,
             backgroundColor:"yellow",
             timemoment:data.resd_date
          })
     }
     else{calendar.addEvent({
                start:data.resd_date,
                resdno:data.resd_no,
                timemoment:data.resd_date
             }); }
     })
    calendar.render();
  });
  $(document).on("click", ".title", function() {
	  $(".content").css("display","none");
	  $(".title").css("border-radius","14px");
	  $(this).css("border-radius","14px 14px 0 0");
  	$(this).next().css("display","inline");
  	$(this).next().next().css("display","inline");

	});
  
  </script>
</html>