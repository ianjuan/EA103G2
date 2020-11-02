<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rptt.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>會員查詢</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
 </script> 
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js">
	
</script>
<script src="https://use.fontawesome.com/16da862410.js"></script>
<script
	src="${pageContext.request.contextPath}/back-end/member/js/member.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back-end/member/css/landlord_search.css"
	type="text/css">

</head>

<body>
	<main>
		<div class="container">
			<h2>請輸入欲查詢的房東</h2>
			<div class="search-box">
				<div class="search-icon">
					<i class="fa fa-search search-icon"></i>
				</div>
				<form action="RptlServlet" method="post" class="search-form">
					<input type="text" placeholder="&nbsp&nbsp房東編號/ 身分證字號" id="search"
						autocomplete="off" name="Number"> <input type="hidden"
						name="action" value="get_want_landlord">
				</form>
				<svg class="search-border" version="1.1"
					xmlns="http://www.w3.org/2000/svg"
					xmlns:xlink="http://www.w3.org/1999/xlink"
					xmlns:a="http://ns.adobe.com/AdobeSVGViewerExtensions/3.0/" x="0px"
					y="0px" viewBox="0 0 671 111"
					style="enable-background: new 0 0 671 111;" xml:space="preserve">
                    <path class="border"
						d="M335.5,108.5h-280c-29.3,0-53-23.7-53-53v0c0-29.3,23.7-53,53-53h280" />
                    <path class="border"
						d="M335.5,108.5h280c29.3,0,53-23.7,53-53v0c0-29.3-23.7-53-53-53h-280" />
                </svg>
				<div class="go-icon">
					<i class="fa fa-arrow-right"></i>
				</div>
			</div>
		</div>
	</main>

</body>

</html>