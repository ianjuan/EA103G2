<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.house_comments.model.*" %>
<%-- <jsp:useBean id="hosSvc" scope="page" class="com.tenant.model.tenantService" /> --%>
<html>
<head>
<title>iZU House_comments Home by back-end</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IZU HCM: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is Home page of House comments for back-end</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>��J�Ыε����s�� (�p):</b>
        <input type="text" name="hcm_no" value="${house_commentsVO.hcm_no}">
        <input type="hidden" name="action" value="BkgetOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

   
    <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>��J�ЫȽs�� (�p):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="BkGetTntAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  	</li>
   
     <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/house_comments/house_comments.servlet" >
        <b>��J�Ыνs�� (�p):</b>
        <input type="text" name="hos_no">
        <input type="hidden" name="action" value="BkGetHosAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
   
</ul>

<!-- ��ܳ�@�Ыε����s���d�ߡA���G��servlet�^�Ǹ�� -->

		<c:if test="${not empty house_commentsVO}">
		<ul>
			<li>�����s��: ${house_commentsVO.hcm_no}</li>
			<li>�ЫȽs��: ${house_commentsVO.tnt_no}</li>
			<li>�Ыνs��: ${house_commentsVO.hos_no}</li>
			<li>�]�ƬO�_����: ${house_commentsVO.hcm_eqpmt}</li>
			<li>����O�_��K: ${house_commentsVO.hcm_convmt}</li>
			<li>�F�~�O�_�͵�: ${house_commentsVO.hcm_neibor}</li> 
			<li>��L����: ${house_commentsVO.hcm_commnt}</li> 
			<li>�����ɶ�: ${house_commentsVO.hcm_time}</li> 
			<li>�ЪF�^��: ${house_commentsVO.hcm_respon}</li> 
		</ul>
		</c:if>

<!-- ��ܰw��Y�ЫȩάY�ЫΪ��Ҧ������d�ߵ��G�A�HTNT000008���ҡA�X�֮ɷ|�ʺA��tnt_no -->

<table>
	<c:forEach var="house_commentsVO" items="${list}" >
		<tr>
			<td>�����s��: ${house_commentsVO.hcm_no}</td>
			<td>�ЫȽs��: ${house_commentsVO.tnt_no}</td>
			<td>�Ыνs��: ${house_commentsVO.hos_no}</td>
			<td>�]�ƬO�_����: ${house_commentsVO.hcm_eqpmt}</td>
			<td>����O�_��K: ${house_commentsVO.hcm_convmt}</td>
			<td>�F�~�O�_�͵�: ${house_commentsVO.hcm_neibor}</td>
			<td>��L����: ${house_commentsVO.hcm_commnt}</td> 
			<td>�����ɶ�: ${house_commentsVO.hcm_time}</td>
			<td>�ЪF�^��: ${house_commentsVO.hcm_respon}</td>
			<td><c:forEach var="house_commentsVO" items="${tenantSvc.all}">
                    <c:if test="${house_commentsVO.tnt_no==tenantVO.tnt_no}">
	                    ${tenantVO.tnt_no}�i${tenantVO.tnt_name} - ${tenantVO.gender}�j
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>





</body>
</html>