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



<div id='calendar'></div>

</head>
<style>
body{
background-color:transparent;
}
  .centered {  
    margin: 0 auto;  
  }  

  .finer-print {  
    font-size: 15px;  
  }  

  .span2 {  
    padding: 0;  
    float: left;  
  }  

  .business-card {  
    position: relative;  
    background: #eeeeec;  
    color: #2e3436;  
      width: 400px;    
    font-size: 20px;  
    border-bottom: 2px solid #d3d7df;  
    border-radius: 15px;  
    box-shadow: 0 0 10px 1px #000;  
    margin-top: 3%;  
  }  

  .business-card .title {  
    background: #c00;  
    color: #fff;  
    font-weight: bold;  
    font-size: 25px;  
    border-top: 2px solid #ef2929;  
    border-left: 2px solid #ef2929;  
    border-radius: 14px 14px 0 0;  
  }  

  .business-card .content {  
    font-weight: bold;  
  padding-top: 15px;  
      padding-left: 15px;}  

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
			String list = bks.getBookingInfoListByhosno(hosno);
			pageContext.setAttribute("list", list);//KEY，VALUE
			String tntno=(String)session.getAttribute("tnt_no");
			String lldno=(String)session.getAttribute("lld_no");
	%>
</body>

<script>




//---------------------------------------------------------------
var list= JSON.parse('${list}');//JSON轉JS格式
// if(list[0].lld_no.substr(0,2))
var acc=true;
var ob;
var allowtime=['10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30','18:00','18:30','19:00','19:30','20:00','20:30','21:00','21:30','22:00'];
//var id=request.getParameter("LLD_NO")
function change(){//測試用
	acc=false;
}
function changetwo(){//測試用
	acc=true;
}

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
	 	 	  lld_no:list[0].lld_no
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
	  var open=false;
	  var booking=true;
	  console.log(list[0]==undefined);
 	  if(list[0]!==undefined){
	 	 if(list[0].lld_no=="<%=lldno %>"){open=true};
	  	 if("<%=tntno %>".substr(0,3)!=="TNT"&&open==false){
		  booking=false;
	  	}
	  }
    var calendarEl = document.getElementById('calendar');   
    calendar = new FullCalendar.Calendar(calendarEl, {
     headerToolbar: {
    	        left: 'prev,next today',
    	        center: 'title',
    	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    	      },

     
     editable: true,     
        
      //hiddenDays: [0], 星期幾不顯示 [0]-[6]=禮拜日-六
      slotMinTime: "09:00:00",//最早與最晚時間
      slotMaxTime: "22:00:00",
      locale: 'zh-tw',
      initialDate: new Date(),//初始時間 目前為當前時間
      navLinks: true, //日期可點嗎?
      selectable: open,/////可以新增日期嗎? ---------------------------------------此條記得改----------房東?房客?
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
       else if(booking) {//房客
    	   if (confirm('確定要預約該時段嗎?')) {//按鈕確認是否 回傳相對應ture false
    		   $.ajax({//查詢房客是否該時段已有預約
     			  url:"<%=request.getContextPath()%>/booking/bookingServlet",
				 	  type:"POST",
				 	  data:{
				 		  action:"findTheDayBytnt",
				 		  time:arg.event.extendedProps.timemoment,
				 		  tntno:"<%= tntno %>",
				 	  },
				 	  success:function(data){//以上成功才執行
				 		  console.log(data);
				 		  if(data.trim()=="true"){
				 		 $.ajax({//存入資料庫階段
		        			  url:"<%=request.getContextPath()%>/booking/bookingServlet",
						 	  type:"POST",
						 	  data:{
						 		  action:"update",
						 		 resdno: arg.event.extendedProps.resdno,
						 		  date:arg.event.extendedProps.timemoment,
						 		  house:"<%= hosno%>",
						 		  tntno:"<%= tntno %>",
						 		  type:"look",
						 		  resstatus:0
						 	  },
						 	  success:function(data){//以上成功才執行
						 		 arg.el.style.backgroundColor="pink";
					        	   arg.el.style.pointerEvents = "none";
					        	   arg.event.title="WW";

						 		  	console.log("res棒第二層");
						 	  },
						 	  error:function(data)
						 	  {
						 		  console.log("真的不棒")
						 	  }
						  
						  });
				 		 }
				 		  else{
				 			  alert("該時段你已經有預約行程囉!");
				 		  }
				 		  	console.log("res棒第一層");
				 	  },
				 	  error:function(data)
				 	  {
				 		  console.log("真的不棒")
				 	  }
    		   });
				  
        	        	   }}
       else{
    	   alert("請先註冊並登入房客帳號");
       }
    	
       
       },
     editable: false,
      dayMaxEvents: true,
       events: [
         {'title':123,'start':'2020-07-02','color':'green'}, 
        	 {'title': '預約',
        	 'start': '2020-09-06T10:30:00',
           'end': '2020-09-06T11:30:00',
           'color': 'lightBlue'    	   
       }]
    });
    
    
     list.forEach(function(catc){
    	 var titles;//讓被預約的STATUS不能再點選
		 console.log(catc.resd_status);
		console.log(catc)
    	 if(catc.resd_status=="1"){
    	 console.log(calendar.addEvent({
 			title:"已預約",
 			start:catc.resd_date,
 			resdno:catc.resd_no,
             status:catc.resd_status,
             backgroundColor:"yellow",
             timemoment:catc.timefordel
          }))
     }
     else{calendar.addEvent({
    			title:"可預約",
                start:catc.resd_date,
                resdno:catc.resd_no,
                timemoment:catc.timefordel
             }); }
     })
    calendar.render();
  });
  </script>
</html>