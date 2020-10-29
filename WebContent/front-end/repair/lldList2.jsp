<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.apl.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<% session.setAttribute("lld_no", "LLD000224");%>
<html>
<head>
<title>房東修繕紀錄 - lldListAllRepair.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/NewFile.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<style>
</style>
</head>
<body bgcolor='white'>
${lld_no}
<jsp:useBean id="repSvc" scope="page" class="com.repair.model.RepairService" />
<jsp:useBean id="conSvc" scope="page" class="com.cont.model.ConService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<section id='second'>
<div class='.container-md'>
<!-- join -->
<%-- 	<c:forEach var="HouseVO" items="${hosSvc.allHouse}">  --%>
<%-- 		<c:if test="${lld_no==HouseVO.lld_no}">  --%>
<%-- 			<c:forEach var="ConVO" items="${conSvc.getConbyhos(HouseVO.hos_no)}">  --%>
<%-- 				<c:if test="${HouseVO.hos_no==ConVO.hos_no}">  --%>
<%-- 					<c:forEach var="repVO" items="${repSvc.tntGetAll(conVO.con_no)}"> --%>
<%-- 						<c:if test="${repVO.con_no==conVO.con_no}">	 --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- join -->
<div class='row'>
	<div class='col-md-2' id="leftSpace"></div>						
	<div class='col-md-8  text-center'>						
		<h3 class='subtitle'>修繕申請紀錄</h3>				
			<!-- join -->
	<c:forEach var="HouseVO" items="${hosSvc.allHouse}">
		<c:if test="${lld_no==HouseVO.lld_no}">
			<!-- conSvc.getConbyhos(HouseVO.hos_no)===ConVO -->
					<c:forEach var="repairVO" items="${repSvc.tntGetAll(conSvc.getConbyhos(HouseVO.hos_no).con_no)}">
						<c:if test="${repairVO.con_no==conSvc.getConbyhos(HouseVO.hos_no).con_no}">	
							<div class='row' >
								<!--修繕圖片 -->
								<c:choose>
								<c:when test="${repSvc.getAllPicNo(repairVO.rep_no).size() eq 0}">
									<button type="submit" class="btn btn-primary" >暫無圖片</button>
								</c:when>
								<c:otherwise>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet">
							 		<input type="hidden" name="rep_no" value="${repairVO.rep_no}">
							        <input type="hidden" name="action" value="getOne_For_seePic">
							     	<button type="submit" class="btn btn-primary" >查看圖片</button>
								</FORM>
								</c:otherwise>
								</c:choose>
								<!--房屋訊息 -->
                				<div class='col-md-4  text-center'>
                  					<div class='text'>
                    					<h6 class='rep_status'>${repairVO.rep_pro eq 0?"處理中":"已修繕完畢"}</h6>
                    					<a href='#'>修繕編號<br>${repairVO.rep_no}</a>
                    					<h5 class='hos_name'>${HouseVO.hos_name}</h5>
                    					<br>居住日期<br>
                    					<h6>${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_str}~${aplSvc.getOneCon_apl(conSvc.getConbyhos(HouseVO.hos_no).apl_no).apl_end}</h6>
                    				</div>
               	 				</div>
								<!--修繕起訖 -->
               	 				<div class='col-md-4 '>
               	 					<table class='repair_info'>
                      					<tr>
                        					<th scope="col">| 修繕物件</th>
                        					<th scope="col">| 申請日期</th>
                        					<th scope="col">| 修繕完成日期</th>
                      					</tr>
                      					<tr>
                        					<td><code><h3> ${repairVO.rep_dam_obj}</h3><h6>${repairVO.rep_dam_obj_des}</h6></code></td>
                        					<td><code><h4> ${repairVO.rep_case_str}</h4></code></td>
                        					<td><code><h4> ${repairVO.rep_est_enddate}</h4></code></td>
                      					</tr>
                      				</table>
               	 				</div>
								<!--按鈕 -->
								<div class='col-md-2'>
									<div class='allbtn'>
										<!--btn1 -->
										 <button class='btn'  data-toggle="modal" data-target="#report" }>${repairVO.rep_tnt_rpt eq 0 ? "未評價" : (repairVO.rep_tnt_rpt eq 2? "不滿意":"滿意")}</button><br>
										<!--btn2 -->
										<button class='btn'>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro" >更新日期</a></button><br>
										
								      	<!--btn3 -->			
										<button class='btn'>
										<A href="<%=request.getContextPath()%>/repair/repair.servlet?rep_no=${repairVO.rep_no}&action=lld_getOne_For_Update_pro">更新進度</a></button><br>
										
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>							
			</c:if>
<%-- 		</c:set> --%>
<%-- 	</c:if> --%>
</c:forEach>
		
	
<!-- join -->						
	</div>						
</div>
</div>
</section>

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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${repairVO.rep_est_enddate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
//            maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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
        
</html>