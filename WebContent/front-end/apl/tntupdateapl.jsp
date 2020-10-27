<%@page import="com.tnt.model.TntVO"%>
<%@page import="com.tnt.model.TntService"%>
<%@page import="com.apl.model.Con_aplVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.rec.model.*"%>

<%
	String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	String apl_no = (String) session.getAttribute("apl_no");
	
	Con_aplVO con_aplVO = (Con_aplVO)request.getAttribute("con_aplVO");
	
	TntService tntService = new TntService();
	TntVO tntVO = tntService.getOneTntProfile(tnt_no);
%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>修改申請</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/rec/css/house_pub.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/rec/js/house_pub.js" charset="UTF-8"></script>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
</head>
<body>
	<div><jsp:include page="/front-end/navbar/navbar.jsp"/> </div>
	<div id="body">
		<div id="left">
			<nav id="housenav">
                <div class="menu-btn">
                    <div class="line line--1"></div>
                    <div class="line line--2"></div>
                    <div class="line line--3"></div>
                </div>                
                <div class="nav-links">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="tntgetallapl">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="gettntcontract">
						<button type="submit" class="link" style="color: #D37707;">合約管理</button><br>
					</FORM>
                </div>
            </nav>
		</div>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet" name="form1">
			<div id="center">
				<div id="cbody">
				    <div id="cbody1">				
						<table>
							<tr>
								<td>租屋開始時間:</td>
									<td><input name="apl_str" id="f_date1" type="text"></td>
							</tr>
	
							<tr>
									<td>租屋結束時間:</td>
								<td><input name="apl_end" id="f_date2" type="text"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">	
				<input type="hidden" name="apl_no" value="<%=con_aplVO.getApl_no()%>">		
				<div id="cfoot">
					<button class="btn" id="reset" type="reset" onclick="notice2()">重填</button>	
					<input type="hidden" name="action" value="tntupdate">
					<button class="btn" id="submit" type="submit">送出修改</button>					
				</div>				
				<div id="rfoot">
					<button class="btn" id="reset" type="reset" onclick="notice2()">重填</button>
					<input type="hidden" name="action" value="tntupdate">
					<button class="btn" id="submit" type="submit">送出修改</button>
				</div>
			</div>
		</form>
	</div>
	<div id="foot"></div>		
</body>

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
		   value: '<%=con_aplVO.getApl_str()%>', 
           minDate: '<%=con_aplVO.getApl_time()%>',
           maxDate: '<%=con_aplVO.getApl_end()%>'       
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=con_aplVO.getApl_end()%>', // value:   new Date(),
           minDate:  '<%=con_aplVO.getApl_str()%>'
        });
</script>
</html>									