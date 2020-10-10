<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/house.do">
	輸入房東編號 :
	<input type="text" name="lld_no">
	<input type="hidden" name="action" value="getLldUnRentHouse">
	<button id="btn2" type="submit">我的房屋</button></li>
</FORM>