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
<button name="btn" onclick="change()">變房客</button>

<button name="btn" onclick="changetwo()">變房東</button>
                    <div class="row">

<div class="col-8" id='calendar'></div>
<div class="col-4" id='who'>

<div class="row">
<div class="business-card centered">
  <div class="title">
	即將到來的預約
  </div>
  <div class="content span2">
    <img src="https://i.imgur.com/afYq1aQ.jpg" alt="" class="avatar" />
  </div>

  <div class="content span2">
    預約人:劉德華<br />
    <span class="finer-print">預約時段:12月20號12:30分<br />
     預約房屋物件:中央大學優質宿舍 近7-11<br />
    地址: 桃園市中壢區中大路216巷<br />     
    </span>
  </div>

</div>
</div>
<div class="row">
<div class="business-card centered">
  <div class="title">
	即將到來的預約
  </div>
  <div class="content span2">
    <img src="https://i.imgur.com/afYq1aQ.jpg" alt="avatar" class="avatar" />
  </div>

  <div class="content span2">
    預約人:張學友<br />
    <span class="finer-print">預約時段:12月25號14:00分<br />
     預約房屋物件:澄清湖旁美公寓 近山面湖<br />
    地址: 高雄市鳥松區澄清路三段216號3樓<br />     
    </span>
  </div>

</div>
</div>
<div class="row">
<div class="business-card centered">
  <div class="title">
	即將到來的預約
  </div>
  <div class="content span2">
    <img src="https://i.imgur.com/afYq1aQ.jpg" alt="avatar" class="avatar" />
  </div>

  <div class="content span2">
    預約人:劉德華<br />
    <span class="finer-print">預約時段:12月20號12:30分<br />
     預約房屋物件:中央大學優質宿舍 近7-11<br />
    地址: 桃園市中壢區中大路216巷<br />     
    </span>
  </div>

</div>
</div>
</div>
</div>
</head>
<style>
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
<!-- </style> -->
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
			String list = bks.getinfobyid("LLD000007");
			pageContext.setAttribute("list", list);//KEY，VALUE
	%>
</body>

<script>




//---------------------------------------------------------------
var list= JSON.parse('${list}');//JSON轉JS格式
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
		  url:"bookingServlet",
	 	  type:"POST",
	 	  data:{
	 		  action:"insert",
	 		  data:JSON.stringify(arrayfortime)	 		
	 	 //	  id:request.getParameter("LLD_NO");
	 	  },
		success:function(id){//以上成功才執行
	 		  console.log(id+"HEN棒");
			 var i=0;
			 if(Itallday){
					for (let value of allowtime){
					  	 calendar.addEvent({
					       start:theday+"T"+value,//加入該日期
					       resdno:id//給他ID
					     });
					  	 }
				 
			 }else{
				for (let value of arrayfortime){
					console.log("有沒有進迴圈啦"+value);
				  	 calendar.addEvent({
				       start:value,//加入該日期
				       resdno:id//給他ID
				     });
				  	 }}
				   }	
	 	 	 ,error:function(data)
		 	  {
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

     
     editable: true,     
        
      //hiddenDays: [0], 星期幾不顯示 [0]-[6]=禮拜日-六
      slotMinTime: "09:00:00",//最早與最晚時間
      slotMaxTime: "22:00:00",
      locale: 'zh-tw',
      initialDate: new Date(),//初始時間 目前為當前時間
      navLinks: true, //日期可點嗎?
      selectable: true,/////可以新增日期嗎? ---------------------------------------此條記得改----------房東?房客?
      selectMirror: true,
      selectHelper :true,
      unselectAuto: true,


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
   		console.log(arg.event.extendedProps.status);
 	   if(arg.event.extendedProps.status=='1'){
 		   return;
 	   }
       console.log(arg.event);
    	   console.log(arg.event.extendedProps);       	
    	   console.log(arg.event.extendedProps.resdno);
    	   if(acc){ //如果是房東
    		   if (confirm('確定要刪除該時段嗎?')) {//按鈕確認是否 回傳相對應ture false
    			   $.ajax({//存入資料庫階段
    					  url:"bookingServlet",
    				 	  type:"POST",
    				 	  data:{ action:"delete",
    				 		  data:arg.event.extendedProps.resdno
    				 		   //JSON.stringify({})
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
       else {//房客
    	   if (confirm('確定要預約該時段嗎?')) {//按鈕確認是否 回傳相對應ture false
        	 
        	   $.ajax({//存入資料庫階段
        			  url:"bookingServlet",
				 	  type:"POST",
				 	  data:{
				 		  action:"update",
				 		  data:arg.event.extendedProps.resdno
				 		  //,session.getAttribute("hoid")
				 		  
				 			 //JSON.stringify({})
				 	  },
				 	  success:function(data){//以上成功才執行
				 		 arg.el.style.backgroundColor="pink";
			        	   arg.el.style.pointerEvents = "none";
				 		  	console.log("res棒");
				 	  },
				 	  error:function(data)
				 	  {
				 		  console.log("真的不棒")
				 	  }
				  
				  });
				  
        	        	   }}
    	
       
       },
     editable: false,
      dayMaxEvents: true, // allow "more" link when too many events
//      events: ${list}
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
          }))
     }
     else{calendar.addEvent({
    			title:"可預約",
                start:catc.resd_date,
                resdno:catc.resd_no,
             }); }
     })
    calendar.render();
  });


  </script>
</html>