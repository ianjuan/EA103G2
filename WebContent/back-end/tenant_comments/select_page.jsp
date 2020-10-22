<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.landlord_comments.model.*" %>
<%-- <jsp:useBean id="hosSvc" scope="page" class="com.tenant.model.tenantService" /> --%>
<html>
<head>
<title>iZU Tenant_comments Home by back-end</title>

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
   <tr><td><h3>IZU TCM: Home</h3><h4>( MVC )</h4></td></tr>
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
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
        <b>��J�Ыȵ����s�� (�p):</b>
        <input type="text" name="tcm_no" value="${tenant_commentsVO.tcm_no}">
        <input type="hidden" name="action" value="BkgetOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

   
    <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
        <b>��J�ЫȽs�� (�p):</b>
        <input type="text" name="tnt_no">
        <input type="hidden" name="action" value="BkGetTntAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  	</li>
   
     <li>
    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/tenant_comments/tenant_comments.servlet" >
        <b>��J�ЪF�s�� (�p):</b>
        <input type="text" name="lld_no">
        <input type="hidden" name="action" value="BkGetLldAll_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
   
</ul>

<!-- ��ܳ�@�ЪF�����s���d�ߡA���G��servlet�^�Ǹ�� -->

		<c:if test="${not empty tenant_commentsVO}">
		<ul>
			<li>�����s��: ${tenant_commentsVO.tcm_no}</li>
			<li>�ЫȽs��: ${tenant_commentsVO.tnt_no}</li>
			<li>�ЪF�s��: ${tenant_commentsVO.lld_no}</li>
			<li>�]�ƬO�_����: ${tenant_commentsVO.tcm_clean}</li>
			<li>����O�_��K: ${tenant_commentsVO.tcm_commut}</li>
			<li>�F�~�O�_�͵�: ${tenant_commentsVO.tcm_satisfy}</li> 
			<li>��L����: ${tenant_commentsVO.tcm_commet}</li> 
			<li>�����ɶ�: ${tenant_commentsVO.tcm_time}</li> 
			<li>�ЪF�^��: ${tenant_commentsVO.tcm_respon}</li> 
		</ul>
		</c:if>

<!-- ��ܰw��Y�ЫȩάY�ЫΪ��Ҧ������d�ߵ��G�A�HTNT000008���ҡA�X�֮ɷ|�ʺA��tnt_no -->

<table>
	<c:forEach var="tenant_commentsVO" items="${list}" >
		<tr>
			<td>�����s��: ${tenant_commentsVO.tcm_no}</td>
			<td>�ЫȽs��: ${tenant_commentsVO.tnt_no}</td>
			<td>�ЪF�s��: ${tenant_commentsVO.lld_no}</td>
			<td>�ЪF�O�_�|�w���������ҲM��: ${tenant_commentsVO.tcm_clean}</td>
			<td>�ЪF�O�_�@�N�P�Ыȷ��q: ${tenant_commentsVO.tcm_commut}</td>
			<td>�`��Ө��A��ЪF������: ${tenant_commentsVO.tcm_satisfy}</td>
			<td>��L����: ${tenant_commentsVO.tcm_commet}</td> 
			<td>�����ɶ�: ${tenant_commentsVO.tcm_time}</td>
			<td>�ЪF�^��: ${tenant_commentsVO.tcm_respon}</td>
			
		</tr>
	</c:forEach>
</table>





</body>
</html>