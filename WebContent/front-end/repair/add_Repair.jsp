<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.repair.controller.*"%>


<%
  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO");
%>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/repair/css/addRepair.css">


<script>
//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches

$(".next").click(function(){
	console.log("next");
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
      'transform': 'scale('+scale+')',
      'position': 'absolute'
    });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".previous").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();
	
	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".submit").click(function(){
	return false;
})

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<!-- multistep form -->
<div id="msform" >
  <!-- progressbar -->
  <ul id="progressbar">
    <li class="active">修繕申請</li>
    <li>上傳照片 !</li>
    <li>申請成功 !</li>
  </ul>
  <!-- fieldsets -->
  <fieldset>
    <h2 class="fs-title">修繕申請</h2>
    <h3 class="fs-subtitle">-請填入欲修繕物品-</h3>
    
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" >
    	
    <input type="text" name="con_no" value="<%= (repairVO==null)? session.getAttribute("con_no") :repairVO.getCon_no()%>" readonly/>
    <input type="text" name="rep_dam_obj"  placeholder="待修物品" value="<%= (repairVO==null)? "" : repairVO.getRep_dam_obj()%>"/>
    <input type="text" name="rep_case_str" id="f_date1" placeholder="損壞日期">
    <textarea name="rep_dam_obj_des" placeholder="損壞狀況" value="<%= (repairVO==null)? "" : repairVO.getRep_dam_obj_des()%>"></textarea>
    <input type="hidden" name="action" value="tnt_insert">

<!--     <input type="submit" name="submit"  value="送出新增" class="action-button"/></FORM> -->
    <input type="button" name="next" class="next action-button" value="Next" />
    	
  </fieldset>
  
<!--   <div class="upload"> -->
  <fieldset>
    <h2 class="fs-title">上傳照片 !</h2>
    <h3 class="fs-subtitle">讓房東清楚知道損壞的狀況會增加修復的速度喔!(可多張)</h3>
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repair/repair.servlet" name="form1" enctype="multipart/form-data"> --%>
    <input type="file" id="myPic" name="reppic_pic" multiple accept="image/png,image/jpg,image/gif,image/JPEG" />
    <input type="reset" id="delete" value="清除全部" class="action-button">
    <input type="hidden" name="rep_no" value="${repairVO.rep_no}">
	<input type="hidden" name="action" value="pic_insert">
<!-- 	<input class="action-button" type="submit" value="送出圖片"> -->
<!--     </FORM> -->
    <input type="button" name="previous" class="previous action-button" value="Previous" />
    <input type="button" name="next" class="next action-button" value="Next" />
  </fieldset>
  
    	<div id="view">
 		</div>
<!--   </div> -->

  <fieldset>
    <h2 class="fs-title">申請成功 !</h2>
    <h3 class="fs-subtitle">我們會通知您修繕進度，請您留意通知中心</h3>
    <img>
    <input type="button" name="previous" class="previous action-button" value="回首頁" />
    <input type="submit" name="submit" class="submit action-button" value="回修繕管理" />
  </fieldset>
  </FORM>
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
//    minDate:               '-1970-01-01', // 去除今日(不含)之前
   maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});



//圖片上傳
var myPic = document.getElementById("myPic");
// var filename = document.getElementById("filename");

myPic.addEventListener('change', function(e) {
    var pics = myPic.files;
    console.log('pics');

    //選擇的檔案型別必為image，可選一個以上
    //pics.type.indexOf('image')?
    if (pics !== null) {

        for (var i = 0; i < pics.length; i++) {
            // 取出pics物件的第i個
            var pic = pics[i];

            console.log(pic.name);
            console.log('圖片第 ' + (i + 1) + ' 個');

            //建立讀取檔案的FileReader
            var reader = new FileReader();

            //<!!!>多個圖片沒有跑出來，或有跑出來但是出現一樣(最後)的圖片:已解決，因為reader的參數原為e, 改成pic即可
            reader.addEventListener('load', function(pic) {
                //test:
                console.log('這是reader的' + pic.name);
                // 新增img的DOM元素
                var img = document.createElement('img');
                //設定圖片路徑給img  
                //reader.result?                        
                img.setAttribute('src', this.result);
                //將img放到preview區塊
                view.append(img);
                e.stopPropagation() //停止向上冒泡
                console.log('reader註冊結束');

            }, false); //reader
            reader.readAsDataURL(pic);
        } //for
        pics.src = null;
    } //if
    //刪除已選取的圖片紀錄，這樣刪除圖片後才能再選同樣的圖片
    pics.value = '';
}, false); //pics 冒泡


//刪除全部圖片
var delete1 = document.getElementById("delete");
delete1.addEventListener('click', function(e) {
    view.innerHTML = "";
});




</script>

</html>