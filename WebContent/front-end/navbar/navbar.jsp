<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tnt.model.*"%>
    <%@ page import="com.lld.model.*"%>
    
<!DOCTYPE html>
<html>
<head>

<%
	TntVO tntVO = (TntVO) session.getAttribute("tntVO");
	LldVO lldVO = (LldVO) session.getAttribute("lldVO");
	String	tntno=(String)session.getAttribute("tnt_no");
	String	lldno=(String)session.getAttribute("lld_no");	
	boolean lldvolive=true;
	boolean tntvolive=true;
	String user=null;
		if(tntVO==null){
			tntvolive=false;
		}
		
		if(lldVO==null){
			lldvolive=false;
		}
		if(tntVO!=null){
			user=tntno;
		}
		if(lldVO!=null){
			user=lldno;
		}
		pageContext.setAttribute("user",user);
		%>
		<div id="div-nav"></div>
<!-- 		<div class="new-message" > -->
<!-- 		style="display:none" -->
<!-- 			<div class="message-title" style="background-color:yellow;"> -->
<!-- 			<a>您有新訊息</a> -->
<!-- 			</div> -->
<!-- 			<a>可愛的元元向您申請了預約看房</a> -->
<!-- 		</div> -->
		

		
<%--              <%=request.getContextPath()%>/front-end/index/Identify.jsp --%>
<style>
.new-message{
    z-index: 999;
    bottom: 2px;
    position: fixed;
    width: 300px;
    height: 201px;
    background-color: #f4f4f4;
	color:black;
	}
.message-title{	    
/* 	background-color: yellow; */
    width: 100%;
    height: 100px;
       border-style: groove;
    margin-bottom: 5px;
    }
    .notify-title{
        border-bottom: navy;
    border-bottom-style: inherit;
    }
    .notify-group{   
     top: -1px;
    left: -101%;
    padding-top: 0px;
    padding-bottom:0px;
    }
</style>
<script  src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
<script>

if("<%= lldno%>"=="null" && "<%= tntno%>" =="null"){

	$("#div-nav").append(
			"<nav class='navbar navbar-expand-lg navbar-light bg-light' id='nullnav' style='background-color:#AACFBF !important;'>"+
            "<div class='container'>"+
                "<a class='navbar-brand' href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a>"+
                "<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNavAltMarkup' aria-controls='navbarNavAltMarkup' aria-expanded='false' aria-label='Toggle navigation'>"+
                    "<span class='navbar-toggler-icon'></span>"+
                "</button>"+
                "<div class='collapse navbar-collapse' id='navbarNavAltMarkup'>"+
                "<div class='navbar-nav ml-auto'>"+
                "<a class='nav-item nav-link active' href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源<span class='sr-only'>(current)</span></a>"+
                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a>"+
                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/index/Identify.jsp'>會員登入</a>"+
              "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/index/IdentifyRegister.jsp'>註冊會員</a>"+
           " </div>"+   
                "</div>"+
                "</div>"+
            "</nav>"
	)
}
	if("<%= lldno%>"!=="null"){
		$("#div-nav").append(
				"<nav class='navbar navbar-expand-lg navbar-light bg-light' style='background-color:#D7C8B6 !important;'>"+
	            "<div class='container'>"+
	                "<a class='navbar-brand' href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a>"+
	                "<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNavAltMarkup' aria-controls='navbarNavAltMarkup' aria-expanded='false' aria-label='Toggle navigation'>"+
	                    "<span class='navbar-toggler-icon'></span>"+
	                "</button>"+
	                "<div class='collapse navbar-collapse' id='navbarNavAltMarkup'>"+
	                "<div class='navbar-nav ml-auto'>"+
	                "<a class='nav-item nav-link active' href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源<span class='sr-only'>(current)</span></a>"+
	                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a>"+
	                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/house_manage/house_index.jsp'>我的房屋</a>"+
	                "<li class='nav-item dropdown'>"+
	                    "<span data-toggle='dropdown' class='member'>"+
	                        "<input type='image' src='<%=request.getContextPath()%>/ImgReader?id=<%= lldno%>' class='memberpic' />"+
	                        "<span class='membername'><%= lldvolive ? lldVO.getLld_name():" " %></span>"+
	                    "</span>"+
	                    "<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"+
	                    "<a class='dropdown-item'  role='button' id='Notify' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>最新通知</a>"+
                		"<div class='dropdown-menu  notify-group' aria-labelledby='Notify'></div>"+
	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/lld/info.jsp'>個人資訊</a>"+
	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/lld/pocket.jsp'>我的錢包</a>"+
	                        "<a class='dropdown-item' href='<%=request.getContextPath()%>/lld/LldServlet2?action=logout'>登出</a>"+       
	                    "</div>"+
	                "</li>"+
	            "</div>"+   
	                "</div>"+
	                "</div>"+
	            "</nav>"
		)
	
	}
	if("<%= tntno%>"!=="null"){
<%-- 		<img src="<%=request.getContextPath()%>/ImgReader?id=${tntVO.tnt_no}" > --%>
		$("#div-nav").append(
				"<nav class='navbar navbar-expand-lg navbar-light bg-light' style='background-color:#AACFBF !important;'>"+
	            "<div class='container'>"+
	                "<a class='navbar-brand' href='<%=request.getContextPath()%>/front-end/index/index.jsp'>愛租I-ZU</a>"+
	                "<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNavAltMarkup' aria-controls='navbarNavAltMarkup' aria-expanded='false' aria-label='Toggle navigation'>"+
	                    "<span class='navbar-toggler-icon'></span>"+
	                "</button>"+
	                "<div class='collapse navbar-collapse' id='navbarNavAltMarkup'>"+
	                "<div class='navbar-nav ml-auto'>"+
	                "<a class='nav-item nav-link active' href='<%=request.getContextPath()%>/front-end/index/SELECTpage.jsp'>尋找房源<span class='sr-only'>(current)</span></a>"+
	                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/index/gmap.jsp'>地圖找房</a>"+
	                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/collection/Mycol.jsp'>我的收藏</a>"+
	                "<a class='nav-item nav-link' href='<%=request.getContextPath()%>/front-end/apl/tntaplpage.jsp'>我的租屋</a>"+   /*大蟒蛇請改這一行 改完說一下 love u <3*/
	                "<li class='nav-item dropdown '>"+
	                    "<span data-toggle='dropdown' class='member'>"+
	                        "<input type='image' src='<%=request.getContextPath()%>/ImgReader?id=<%= tntno%>' class='memberpic' />"+
	                        "<span class='membername'><%= tntvolive ? tntVO.getTnt_name():" " %></span>"+
	                    "</span>"+
	                    "<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"+
	                    //btn btn-secondary  dropdown-toggle dropleft
	                    "<a class='dropdown-item'  role='button' id='Notify' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>最新通知</a>"+
	                    		"<div class='dropdown-menu  notify-group' aria-labelledby='Notify'></div>"+
	                    "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/info.jsp'>個人資訊</a>"+
	                    "<a class='dropdown-item' href='<%=request.getContextPath()%>/front-end/tnt/pocket.jsp'>我的錢包</a>"+
	                    "<a class='dropdown-item' href='<%=request.getContextPath()%>/tnt/TntServlet2?action=logout'>登出</a>"+  
	                    "</div>"+
	                "</li>"+
	            "</div>"+
	                "</div>"+
	                "</div>"+
	            "</nav>"
		)
	}

	
	var NotifyMyPoint = "/NotifyServlet/${user}";
	var notifyHost = window.location.host;
	var notifyPath = window.location.pathname;
	var notifyWebCtx = notifyPath.substring(0, notifyPath.indexOf('/', 1));
	var notifyEndPointURL = "ws://" + window.location.host + notifyWebCtx + NotifyMyPoint;
	var webSocketForNotify;

	$(document).ready(function (){
		webSocketForNotify = new WebSocket(notifyEndPointURL);
		
		webSocketForNotify.onopen = function(event){
			console.log("Connect Success!");
			
		}
		
		
		webSocketForNotify.onmessage = function(event) {			
			var jsonObj = JSON.parse(event.data);
			var dateForNow=new Date().getTime();
			if(Array.isArray(jsonObj)){//歷史訊息為二維陣列 此處抓住歷史訊息並處理
				var count=0;
				$.each(jsonObj, function(key, value) {
					count++
					if(jsonObj.length-count<5){
						var newtify=JSON.parse(value);
						var herfs="href="+"https://www.judicial.gov.tw/tw/mp-1.html";
						if (newtify.url!="")
							 herfs="href="+newtify.url
						$(".notify-group").prepend(
    						"<a class='notify-a-lebel' "+herfs+" style='color:#904E0E;text-decoration: none'>"+	//Style取消底線跟藍色底色
    							"<div class='message-title'>"+
    								"<div class='notify-title'>"+newtify.title+"</div>"+
    								"<div class='notify-body'>"+newtify.content+"</div>"+
    							"</div>"+
    						"</a>"
						)
					}
				console.log("key="+key+"value="+value)
				});
			}
			if(jsonObj.title!=undefined){
				var herfs="href="+"https://www.judicial.gov.tw/tw/mp-1.html";
				if (jsonObj.url!="")
					 herfs="href="+jsonObj.url
					 
				$('.notify-a-lebel:last').remove() 
				$(".notify-group").prepend(
						"<a class='notify-a-lebel'"+herfs+" style='color:#904E0E;text-decoration: none'>"+	//Style取消底線跟藍色底色
							"<div class='message-title' >"+
								"<div class='notify-title'>"+jsonObj.title+"</div>"+
								"<div class='notify-body'>"+jsonObj.content+"</div>"+
							"</div>"+
						"</a>"
					)
				$(".new-message").remove();
				$("#div-nav").after(
						"<a class='new-message-a-lebel' "+herfs+">"+
						"</a>"
						);
				$(".new-message-a-lebel").append(
						"<div class='new-message'>"+
							"<div class='message-title' style='background-color:yellow;'>"+
									"<a>"+jsonObj.title+"</a>"+
							"</div>"+
						"<a>"+jsonObj.content+"</a>"+
						"</div>"
				);
				$(".new-message").fadeIn(1000);
				setTimeout(function(){
					$(".new-message").fadeOut(1000)
					},3000);

			};
			console.log(jsonObj.title);
			console.log(event);	
		}
	});				
		

	window.unonload=function() {	
		webSocketForNotify.onclose = function(event) {
			webSocket.close();
		}
	}
	
</script>
</html>