<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.landlord_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iZU Landlord_comments Home by Tenant</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<ul class="nav nav-tabs">
<li class="nav-item">
     <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/addHcm.jsp">新增評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link " href="<%=request.getContextPath()%>/front-end/house_comments/listAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/landlord_comments/listAllLcm.jsp">房東評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/tenant_comments/listAllTcm.jsp">我的評價</a>
  </li>
  
</ul>
	
		
	<!-- 針對某房客的所有評價查詢結果，以TNT000009為例，合併時會動態抓tnt_no -->
	
<% session.setAttribute("tnt_no", "TNT000090");%>
<!-- (String)session.getAttribute("tnt_no") -->



<%
	Landlord_commentsService lcmSvc = new Landlord_commentsService();  
      Set<String> lldSet = lcmSvc.getAllLld_no( (String)session.getAttribute("tnt_no"));  
      pageContext.setAttribute("lldSet", lldSet);  
  %>
  
  <!--   取得此房客評論過的房東編號 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
		<select size="1" name="lld_no">
			<c:forEach var="lld_no1" items="${lldSet}">
				<option value="${lld_no1}">
				${lld_no1} 
				<c:forEach var="LldVO" items="${LldService.getAllVrf}">
                    <c:if test="${lld_no1==LldVO.lld_no}">
	                    	房東【${LldVO.lld_name}】 ${(LldVO.lld_sex==0)?'先生':'女士'} 
                    </c:if>
                </c:forEach>
			</c:forEach>
		</select>
		<input type="hidden" name="tnt_no" value="${tnt_no}">
        <input type="hidden" name="action" value="getTntAll_For_Display">
        <input type="submit" value="送出">
    </FORM>
    
<!--  列出所有評論 -->
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet">       
 		<input type="hidden" name="tnt_no" value="${tnt_no}">
        <input type="hidden" name="action" value="getAll_For_Display">
        <input type="submit" value="列出全部房屋評論">
</FORM>

<%-- <% --%>
<!--    	Landlord_commentsService lcmSvc = new Landlord_commentsService();   -->
<!--        List<Landlord_commentsVO> list = lcmSvc.getAllbyTnt( (String)session.getAttribute("tnt_no"));   -->
<!--        pageContext.setAttribute("list", list);   -->
<!--   %>  -->
<br>
        		<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
				</c:if>

<!-- 蓋子	 -->
	<div id="accordion">
		<c:forEach var="landlord_commentsVO" items="${list}">
			 <div class="card">
			 <div class="card-header" id="headingOne">
			  <h5 class="mb-0">
        		<button class="btn btn-link" data-toggle="collapse" data-target="# ${landlord_commentsVO.lcm_no}" aria-expanded="true" aria-controls="collapseOne">
         			<c:forEach var="LldVO" items="${LldService.getAllVrf}">
                    	<c:if test="${landlord_commentsVO.lld_no==LldVO.lld_no}">
	                		房東姓名: ${LldVO.lld_name} - 評論時間: ${landlord_commentsVO.lcm_time}
                    	</c:if>
                	</c:forEach>
        		</button>
      			</h5>
			</div>
<!-- 瓶子 -->
	<div id=" ${landlord_commentsVO.lcm_no}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
      
			<li>評價編號: ${landlord_commentsVO.lcm_no}</li>
			<li>房東姓名:   </li>
			<li>房東編號: ${landlord_commentsVO.lld_no}</li>
			<li>房客編號: ${landlord_commentsVO.tnt_no}</li>
			<li>房東是否將房屋維持清潔(交屋前): ${landlord_commentsVO.lcm_clean}</li>
			<li>房東是否願意與房客溝通協商: ${landlord_commentsVO.lcm_commut}</li>
			<li>對房東的滿意度: ${landlord_commentsVO.lcm_satisfy}</li> 
<%-- 			<li>其他評論:${landlord_commentsVO.lcm_commet}</li>  --%>
			<c:choose>
        	<c:when test="${not empty landlord_commentsVO.lcm_commet}">
        	<li>其他評論:${landlord_commentsVO.lcm_commet}</li>
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        	<input type="text" name="lcm_commet" value="${landlord_commentsVO.lcm_commet}">
        	<input type="hidden" name="lld_no"  value="${landlord_commentsVO.lld_no}">
        	<input type="hidden" name="tnt_no"  value="${landlord_commentsVO.tnt_no}">
        	<input type="hidden" name="lcm_no"  value="${landlord_commentsVO.lcm_no}">
        	<input type="hidden" name="action"  value="getOne_For_Update">
        	<input type="submit" value="送出文字評價" >
        	</FORM>
        	</c:otherwise>
        	</c:choose>
			<li>評價時間: ${landlord_commentsVO.lcm_time}</li> 
			<li>房東回覆: ${landlord_commentsVO.lcm_respon}</li> 
			</div>
    		</div>
			</div>
</c:forEach>
</div>

</body>
</html>