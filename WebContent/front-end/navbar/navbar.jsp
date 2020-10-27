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
	
		if(tntVO==null){
			tntvolive=false;
		}
		
		if(lldVO==null){
			lldvolive=false;
		}
		%>
		<div id="div-nav"></div>
		

		
<%--              <%=request.getContextPath()%>/front-end/index/Identify.jsp --%>
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
	                        "<a class='dropdown-item' href='#'>最新通知</a>"+
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
	                "<li class='nav-item dropdown'>"+
	                    "<span data-toggle='dropdown' class='member'>"+
	                        "<input type='image' src='<%=request.getContextPath()%>/ImgReader?id=<%= tntno%>' class='memberpic' />"+
	                        "<span class='membername'><%= tntvolive ? tntVO.getTnt_name():" " %></span>"+
	                    "</span>"+
	                    "<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"+
	                        "<a class='dropdown-item' href='#'>最新通知</a>"+
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
</script>
</html>