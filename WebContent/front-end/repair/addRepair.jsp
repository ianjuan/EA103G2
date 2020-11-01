<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>

<%

  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO");
 // 	String con_no = (String)request.getAttribute("con_no"); -->
 %> 

<% session.setAttribute("con_no", "CON000204");%> 
<% request.getAttribute("con_no");%>

<% String con_no = (String) session.getAttribute("con_no");
	if (con_no == null) {
		con_no = request.getParameter("con_no");
	} %>

<% HouseService hosSvc = new HouseService();
	
		ConService conSvc = new ConService();
		ConVO conVO = conSvc.getOneCon(con_no);
		String hos_no = conVO.getHos_no();
		HouseVO hosVO = hosSvc.getHouseInfo(hos_no);
		pageContext.setAttribute("conVO", conVO);
		pageContext.setAttribute("hosVO", hosVO);
		pageContext.setAttribute("hosSvc", hosSvc);
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
<title>新增修繕申請 </title>
<style>
.input{
	border:5px lightblue solid;
	border-radius:30px;
/* 	background-color:lightgrey; */
	padding:100px;
	margin:120px;
	color:grey;
	box-shadow: 10px 10px 5px #888888;

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
#qtyList{
	margin:20px;
}
.rep_dam_obj{
	margin:20px;
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
 <jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
 
		<label for="exampleFormControlInput1"><span class="text">To:房東</span>
		<span style="font-size:50px;">${lldSvc.getOneLldProfile(hosVO.lld_no).lld_name}</span></label>
</div>
 
 
 <div class="form-group">
		<label for="exampleFormControlInput1"><h3>房屋名稱:</h3></label>
		<span class="text">${hosVO.hos_name}</span>
		<input type="hidden" readonly name="con_no" class="form-control" id="exampleFormControlInput1"
			 value="<%= (repairVO==null)? session.getAttribute("con_no") :repairVO.getCon_no()%>" />	
</div>
	

		
<div class="form-group">
		<label for="exampleFormControlSelect1"><h3>待修物品:</h3></label>
		----${repairVO.rep_dam_obj}----
		<select id="rep_dam_obj" name="rep_dam_obj" >
			<option value="" ${(repairVO.rep_dam_obj==null) or (empty repairVO.rep_dam_obj)? selected :''}>請選擇修繕物品</option>
			<c:if test="${hosVO.hos_chair > 0}">
				　<option value="椅子" ${repairVO.rep_dam_obj=='椅子'? 'selected' :'' }>椅子 共${hosVO.hos_chair} 把</option>
			</c:if>
			<c:if test="${hosVO.hos_bed > 0}">
				　<option value="床鋪" ${(repairVO.rep_dam_obj=="床鋪")? 'selected' :'' }>床鋪  共${hosVO.hos_bed} 張</option>
			</c:if>
			<c:if test="${hosVO.hos_closet > 0}"> 
				　<option value="衣櫥" ${(repairVO.rep_dam_obj=="衣櫥")? 'selected' :'' }>衣櫥  共${hosVO.hos_closet} 組</option>
			</c:if>
			<c:if test="${hosVO.hos_sofa > 0}"> 
				  <option value="沙發" ${(repairVO.rep_dam_obj=="沙發")? 'selected' :'' }>沙發  共${hosVO.hos_sofa} 組</option>
			</c:if>
			<c:if test="${hosVO.hos_tv > 0}"> 
				　 <option value="電視" ${(repairVO.rep_dam_obj=="電視")? 'selected' :'' }>電視  共${hosVO.hos_tv} 台</option>
			</c:if>
			<c:if test="${hosVO.hos_drink > 0}"> 
				　<option value="飲水機" ${(repairVO.rep_dam_obj=="飲水機")? 'selected' :'' }>飲水機  共${hosVO.hos_drink} 台</option>
			</c:if>
			<c:if test="${hosVO.hos_aircon > 0}"> 
				 <option value="冷氣" ${(repairVO.rep_dam_obj=="冷氣")? 'selected' :'' }>冷氣  共${hosVO.hos_aircon} 台</option>
			</c:if>
			<c:if test="${hosVO.hos_refrig > 0}">
				　<option value="冰箱" ${(repairVO.rep_dam_obj=="冰箱")? 'selected' :'' }>冰箱 共${hosVO.hos_refrig} 台</option>
			</c:if>
			<c:if test="${hosVO.hos_wash > 0}"> 
				　<option value="洗衣機" ${(repairVO.rep_dam_obj=="洗衣機")? 'selected' :'' }>洗衣機 共 ${hosVO.hos_wash} 台</option>
			</c:if>
			<c:if test="${hosVO.hos_hoter > 0}"> 
				 <option value="熱水器" ${(repairVO.rep_dam_obj=="熱水器")? 'selected' :'' }>熱水器  共${hosVO.hos_hoter} 組</option>
			</c:if>
			<c:if test="${hosVO.hos_forth > 0}"> 
				　<option value="第四台" ${(repairVO.rep_dam_obj=="第四台")? 'selected' :'' }>第四台  共${hosVO.hos_forth} 條</option>
			</c:if>
			<c:if test="${hosVO.hos_net > 0}">
				　<option value="網路" ${(repairVO.rep_dam_obj=="網路")? 'selected' :'' }>網路  共${hosVO.hos_net} 條</option>
			</c:if>
			<c:if test="${hosVO.hos_gas > 0}">
				　<option value="天然瓦斯" ${(repairVO.rep_dam_obj=="天然瓦斯")? 'selected' :'' }>天然瓦斯</option>
			</c:if>
			
		</select>
		
		<!-- 	js放數量name="fQty"輸入框 -->
		<span id="qtyList"></span>
		
<!-- 		<input type="text" name="rep_dam_obj" class="form-control" id="exampleFormControlSelect1"  -->
<%-- 			 value="<%= (repairVO==null)? "" : repairVO.getRep_dam_obj()%>" /> --%>
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
<!-- 連結有問題 -->
<%-- <a href="${pageContext.request.contextPath }/front-end/repair/lldListAllRepair.jsp?lld_no=${lld_no}"><button class="btn btn-primary">取消</button></a>	 --%>

<input type="hidden" name="action" value="tnt_insert">
<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">送出新增</button>
</FORM>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
	<input type="hidden" name="tnt_no" value="${conVO.tnt_no}">
	<input type="hidden" name="action" value="getTntRepair">
	<button type="submit" class="btn btn-primary"> 取 消  </button><br>
</FORM>


 </div>
   </div>
<!--     <div class="col col-lg-2"> -->
      	 
    </div>

</div>


<script type="text/javascript">
// $("#rep_dam_obj").on("change", changelist());

// $(document).on('change', '#rep_dam_obj',function(){
// 	changelist();
// 	console.log("有近來change");
// 	});
// function changelist(e){
// 	console.log("有近來");
// 	var rep_dam_obj = document.getElementById("rep_dam_obj");
// 	var index=rep_dam_obj.selectedIndex; //序號，取當前選中選項的序號
// 	console.log(index);
// 	var val = rep_dam_obj.options[index].value;
	
// 	console.log(val);
	
// 	var qtyList = document.getElementById("qtyList");
// 	qtyList.innerText
// switch(val)
// {
// // 13種數量控制(14項家具)
// case "桌子":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_table}' name='fQty'>"
// break;
// case "椅子":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_chair}' name='fQty'>"
// break;
// case "床鋪":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_bed}' name='fQty'>"
// break;
// case "衣櫥":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_closet}' name='fQty'>"
// break;
// case "沙發":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_sofa}' name='fQty'>"
// break;
// case "電視":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max=' ${hosVO.hos_tv}' name='fQty'>"
// break;
// case "飲水機":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_drink}' name='fQty'>"
// break;
// case "冷氣":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max=' ${hosVO.hos_aircon}' name='fQty'>"
// break;
// case "冰箱":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_refrig}' name='fQty'>"
// break;
// case "洗衣機":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_wash}' name='fQty'>"
// break;
// case "熱水器":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_hoter}' name='fQty'>"
// break;
// case "網路":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_net}' name='fQty'>"
// break;
// case "第四台":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_forth}' name='fQty'>"
// break;
// case "椅子":
// qtyList.innerHTML="<input type='number' value='1' step='1' min='1' max='${hosVO.hos_chair}' name='fQty'>"
// break;
// }
// }                                                                                
</script>  
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