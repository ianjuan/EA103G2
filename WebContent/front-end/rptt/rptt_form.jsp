<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front-end/css/main.css" type="text/css">
    <meta charset="utf-8">
    <title>檢舉房客</title>
</head>

<body>
    <div class='row'>
        <div class='col-md-12'>
            <h1>檢舉房客</h1>
        </div>
        <HR align=center width="50%" size=5>
        <div class='col-md-12'>一旦接獲您的檢舉，愛租便會立即展開調查，但基於隱私保護原則，恕不回復審查結果，還請見諒！</div>
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
        <label for="name">您的帳號:</label>
        <input type="text" name="lld_no" value="LLD000045" readonly>
        <label for="name">檢舉的房客帳號:</label>
        <input type="text" name="tnt_no" value="TNT000022" readonly>
        <label for="reason">檢舉原因:</label>
        <textarea id="reason" name="rptt_content" required></textarea>
        <input type="hidden" name="action" value="insert">
        <button type="submit">提交</button>
    </form>
</body>

</html>