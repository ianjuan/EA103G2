<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.landlord_comments.model.*" %>
<%-- <jsp:useBean id="hosSvc" scope="page" class="com.tenant.model.tenantService" /> --%>
<html>
<head>
<title>iZU Landlord_comments Home by back-end</title>

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
   <tr><td><h3>IZU LCM: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is Home page of Landlord comments for back-end</p>

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
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>��J�ЪF�����s�� (�p):</b>
        <input type="text" name="lcm_no" value="${landlord_commentsVO.lcm_no}">
        <input type="hidden" name="action" value="BkgetOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

   
    <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>��J�ЫȽs�� (�p):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="BkGetTntAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  	</li>
   
     <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/landlord_comments/landlord_comments.servlet" >
        <b>��J�ЪF�s�� (�p):</b>
        <input type="text" name="lld_no">
        <input type="hidden" name="action" value="BkGetLldAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
   
</ul>

<!-- ��ܳ�@�Ыε����s���d�ߡA���G��servlet�^�Ǹ�� -->

		<c:if test="${not empty landlord_commentsVO}">
		<ul>
			<li>�����s��: ${landlord_commentsVO.lcm_no}</li>
			<li>�ЫȽs��: ${landlord_commentsVO.tnt_no}</li>
			<li>�Ыνs��: ${landlord_commentsVO.lld_no}</li>
			<li>�]�ƬO�_����: ${landlord_commentsVO.lcm_clean}</li>
			<li>����O�_��K: ${landlord_commentsVO.lcm_commut}</li>
			<li>�F�~�O�_�͵�: ${landlord_commentsVO.lcm_satisfy}</li> 
			<li>��L����: ${landlord_commentsVO.lcm_commet}</li> 
			<li>�����ɶ�: ${landlord_commentsVO.lcm_time}</li> 
			<li>�ЪF�^��: ${landlord_commentsVO.lcm_respon}</li> 
		</ul>
		</c:if>

<!-- ��ܰw��Y�ЫȩάY�ЫΪ��Ҧ������d�ߵ��G�A�HTNT000008���ҡA�X�֮ɷ|�ʺA��tnt_no -->

<table>
	<c:forEach var="landlord_commentsVO" items="${list}" >
		<tr>
			<td>�����s��: ${landlord_commentsVO.lcm_no}</td>
			<td>�ЫȽs��: ${landlord_commentsVO.tnt_no}</td>
			<td>�ЪF�s��: ${landlord_commentsVO.lld_no}</td>
			<td>�ЪF�O�_�|�w���������ҲM��: ${landlord_commentsVO.lcm_clean}</td>
			<td>�ЪF�O�_�@�N�P�Ыȷ��q: ${landlord_commentsVO.lcm_commut}</td>
			<td>�`��Ө��A��ЪF������: ${landlord_commentsVO.lcm_satisfy}</td>
			<td>��L����: ${landlord_commentsVO.lcm_commet}</td> 
			<td>�����ɶ�: ${landlord_commentsVO.lcm_time}</td>
			<td>�ЪF�^��: ${landlord_commentsVO.lcm_respon}</td>
			
		</tr>
	</c:forEach>
</table>





</body>
</html>