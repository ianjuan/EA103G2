<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tenant_comments.model.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>iZU Tenant_comments Home by Landlord</title>
</head>
<body>
<ul class="nav nav-tabs">
<li class="nav-item">
	<a class="nav-link " href="<%=request.getContextPath()%>/front-end/tenant_comments/addTcm.jsp">新增評價</a>
  </li>
   <li class="nav-item">
    <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/tenant_comments/lldListAllTcm.jsp">房客評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/house_comments/lldListAllHcm.jsp">房屋評價</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/landlord_comments/lldListAllLcm.jsp">我的評價</a>
  </li>
</ul>

	
 
<%-- 	<c:forEach var="house_commentsVO" items="${list}" > --%>
		
<!-- 		<tr> -->
<%-- 			<td>${house_commentsVO.hcm_no}</td> --%>
<%-- 			<td>${house_commentsVO.hos_no}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_eqpmt}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_convmt}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_neibor}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_commnt}</td>  --%>
<%-- 			<td>${house_commentsVO.hcm_time}</td> --%>
<%-- 			<td>${house_commentsVO.hcm_respon}</td> --%>
<%-- 			<td><c:forEach var="house_commentsVO" items="${tenantSvc.all}"> --%>
<%--                     <c:if test="${house_commentsVO.tnt_no==tenantVO.tnt_no}"> --%>
<%-- 	                    ${tenantVO.tnt_no}【${tenantVO.tnt_name} - ${tenantVO.gender}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-page/house_comments.servlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="回覆評價"> -->
<%-- 			     <input type="hidden" name="hcm_no"  value="${house_commentsVO.hcm_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="getOne_For_Reply"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
	
		
	<!-- 針對某房客的所有評價查詢結果，以TNT000009為例，合併時會動態抓tnt_no -->
	
<% session.setAttribute("lld_no", "LLD000023");%>
<!-- (String)session.getAttribute("tnt_no") -->


 <!-- 取得該房東評價過的tnt集合 -->
<%
	Tenant_commentsService tcmSvc = new Tenant_commentsService();  
      Set<String> tntSet = tcmSvc.getAllTntByLld( (String)session.getAttribute("lld_no"));  
      pageContext.setAttribute("tntSet", tntSet);  
  %>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
		<select size="1" name="tnt_no">
			<c:forEach var="tnt_no1" items="${tntSet}">
				<option value="${tnt_no1}">
				${tnt_no1}

			</c:forEach>
		</select>
		<input type="hidden" name="lld_no" value="${lld_no}">
        <input type="hidden" name="action" value="getLldTntAll_For_Display">
        <input type="submit" value="送出">
 </FORM>
<br>

<%--         		錯誤表列 --%>
<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 				<font style="color:red">請修正以下錯誤:</font> -->
<!-- 				<ul> -->
<%-- 					<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li style="color:red">${message}</li> --%>
<%-- 					</c:forEach> --%>
<!-- 				</ul> -->
<%-- 				</c:if> --%>
				
				
				
<div id="accordion">
<c:forEach var="tenant_commentsVO" items="${list}">
<!-- 瓶蓋	 -->
	<div class="card">
	<div class="card-header" id="headingOne">
		<h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="# ${tenant_commentsVO.tcm_no}" aria-expanded="true" aria-controls="collapseOne">
			<c:forEach var="TntVO" items="${TntService.getAllVrf}">
                <c:if test="${tenant_commentsVO.tnt_no==TntVO.tnt_no}">
	                房客:${TntVO.tnt_name} 評論時間:${tenant_commentsVO.tcm_time}
                </c:if>
            </c:forEach>
         	${tenant_commentsVO.tcm_no}
       	</button>
		</h5>
	</div>	
<!-- 瓶子	 -->
	<div id=" ${tenant_commentsVO.tcm_no}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
      	<ul>
			<li>評價編號: ${tenant_commentsVO.tcm_no}</li>
			<li>房客編號: ${tenant_commentsVO.tnt_no}</li>
			<li>房客姓名:
			</li>
			<li>房東編號: ${tenant_commentsVO.lld_no}</li>
			<li>房客是否將房屋維持清潔: ${tenant_commentsVO.tcm_clean}</li>
			<li>房客是否願意與房東(您)溝通: ${tenant_commentsVO.tcm_commut}</li>
			<li>對房客的滿意度: ${tenant_commentsVO.tcm_satisfy}</li> 
			
		<b>其他評論: </b>
		
			<c:choose> 
        	<c:when test="${not empty tenant_commentsVO.tcm_commet}">
<%--         	${tenant_commentsVO.tcm_commet} --%>
	<input readonly type="text" name="tcm_commet" value="${tenant_commentsVO.tcm_commet}">
        	</c:when>
        	<c:otherwise>
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
        	<input type="text" name="tcm_commet" value="${tenant_commentsVO.tcm_commet}">
        	<input type="hidden" name="tnt_no"  value="${tenant_commentsVO.tnt_no}">
        	<input type="hidden" name="lld_no"  value="${tenant_commentsVO.lld_no}">
        	<input type="hidden" name="tcm_no"  value="${tenant_commentsVO.tcm_no}">
        	<input type="hidden" name="action"  value="getOne_For_Update">
        	<input type="submit" value="送出文字評價" >
        	</FORM>
        	</c:otherwise>
        	</c:choose>
        	
			<li>評價時間: ${tenant_commentsVO.tcm_time}</li> 
			<li>房客回覆: ${tenant_commentsVO.tcm_respon}</li>
		</ul>
			</div>
    		</div>
			</div>
</c:forEach>
</div>

</body>
</html>


