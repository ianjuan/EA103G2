<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/css/main.css" type="text/css">
    <meta charset="utf-8">
    <title>���|�Ы�</title>
</head>

<body>
    <div class='row'>
        <div class='col-md-12'>
            <h1>���|�Ы�</h1>
        </div>
        <HR align=center width="50%" size=5>
        <div class='col-md-12'>�@������z�����|�A�R���K�|�ߧY�i�}�լd�A��������p�O�@��h�A�����^�_�f�d���G�A�ٽШ��̡I</div>
    </div>
    <c:if test="${not empty errorMsgs}">
        <c:forEach var="message" items="${errorMsgs}">
            <div class='row1'>
                <div class='col-md-12'>
                    <p>${message}</p>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <form action="RpttServlet" method="post">
        <label for="name">�z���b��:</label>
        <input type="text" name="lld_no" value="LLD000045" readonly>
        <label for="name">���|���Ыȱb��:</label>
        <input type="text" name="tnt_no" value="TNT000022" readonly>
        <label for="reason">���|��]:</label>
        <textarea id="reason" name="rptt_content" required></textarea>
        <input type="hidden" name="action" value="insert">
        <button type="submit">����</button>
    </form>
</body>

</html>