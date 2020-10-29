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
	background: #99BAAB;
	color: #fff;
	font-weight: bold;
	font-size: larger;
	border-top: 2px solid #aacfbf;
	border-left: 2px solid #aacfbf;
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
	String tntno=(String)session.getAttribute("tnt_no");

			String list = bks.getBookingInfoListBylldno(lldno);
			String resorder = bks.getResOrderbytntno(tntno);
			pageContext.setAttribute("list", list);//KEY，VALUE
			pageContext.setAttribute("order", resorder);//KEY，VALUE

	%>
</body>

<script>




//---------------------------------------------------------------
var list= JSON.parse('${list}');//JSON轉JS格式
var order= JSON.parse('${order}');
var ordercount=0;
var ob;

	 	 	
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
       eventClick: function(arg) {//點選日曆上已成立的 	   
    	   console.log(arg.event.extendedProps.timemoment);
    	   if (confirm('確定要刪除該時段嗎?')) {//按鈕確認是否 回傳相對應ture false
			   $.ajax({//存入資料庫階段
					  url:"<%=request.getContextPath()%>/booking/bookingServlet",
				 	  type:"POST",
				 	  data:{ action:"deletetnt",
				 		  data:arg.event.extendedProps.hosno,
				 		  time:arg.event.extendedProps.timemoment
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
    			 
   				}

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
    					"<img src='https://i.imgur.com/afYq1aQ.jpg' alt='' class='avatar' />"+
    				"</div>"+
    				"<div class='content span2'>"+
    					"<span class='finer-print'>房東:"+order.lld_name+"<br /> 預約時段:"+order.order_date+ "<br />"+
    						"連絡電話:"+order.lld_mobile+"<br /> 預約房屋物件:"+order.hos_name+ "<br /> 地址:"+
    						order.hos_add+"<br />"+
    					"</span>"+
    					"<button>查看房東資訊</button>"+
    					"<button>取消預約</button>"+
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
        					"<img src='https://i.imgur.com/afYq1aQ.jpg' alt='' class='avatar' />"+
        				"</div>"+
        				"<div class='content span2' style='display:none' > "+
    					"<span class='finer-print'>房東:"+order.lld_name+"<br /> 預約時段:"+order.order_date+ "<br />"+
        						"連絡電話:"+order.lld_mobile+"<br /> 預約房屋物件:"+order.hos_name+ "<br /> 地址:"+
        						order.hos_add+"<br />"+
        					"</span>"+
        					"<button>查看房東資訊</button>"+
        					"<button>取消預約</button>"+
        				"</div>"+
        			"</div>"+
        		"</div>"
		)
    	}
    	ordercount++;
    });
    order.forEach(function(data){
    	 var titles;//讓被預約的STATUS不能再點選
    	 calendar.addEvent({
 			start:data.resd_date,
 			hosno:data.hos_no,
 			timemoment:data.timefordel,
             backgroundColor:"yellow",
          })
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