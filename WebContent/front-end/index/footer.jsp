<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
	.copyright li{
		float:left;
		list-style:none;
		margin-left:1.3em;
	}
	.icons{
		margin-top: 22px;
		margin-left: 60px;
	}
	.icons li{
		float:left;
		list-style:none;
		margin-left:1.3em;
	}
	.copyright{
		margin-top: 27px;
	}
	.footer-real{
		background-color: #383b43;
	    color: white;
	    padding: 0px 15px;
	    margin-top:50px;
	}
	@media (min-width: 1200px){
		.footer-real {
			height: 80px;
		    max-width: 100%;
		}
	}
	@media (max-width: 1486px){
		.copyright li {
		    margin-left: 0.3em;
		}
	}
	@media (min-width: 1403px){
		.icons{
			margin-left: 400px;
		}
	}
	@media (max-width: 1390px){
		.copyright {
		    margin-top: 16px;
		}
	}
	@media (max-width: 1199px){
		.copyright {
		    margin-top: 0px;
		}
		.icons{
			margin-top: 5px;
		}
	}
	@media (max-width: 801px){
		.icons {
		    margin-top: 17px;
		}
	}
	@media (max-width: 745px){
		.icons {
		    margin-top: 26px;
		    margin-left:0px;
		}
	}
	@media (max-width: 602px){
		.icons li{
		    margin-left: 3.3em;
		}
		.icons {
		    margin-top: 15px;
		}
	}
	@media (max-width: 507px){
		.icons li{
		    margin-left: 0.3em;
		}
		.icons {
		    margin-top: 35px;
		}
	}
</style>
</head>

<body>
	<div class=" footer-real">
		<div class="row">
			<div class="col-6">
	        	<ul class="copyright">
	            	<li>Copyright&copy; 2020 I-ZU  lnc.</li>
	                <li>Design: <a href="<%=request.getContextPath()%>/front-end/index/index.jsp">EA103-G2</a></li>
	                <li>Address: <a href="<%=request.getContextPath()%>/front-end/index/index.jsp">桃園市中壢區中大路300號</a></li>
	            </ul>
	        </div>
	        <div class="col-6">
	            <ul class="icons">
	                <li>
	                	<a class="icon" href="https://www.facebook.com"><img src="<%=request.getContextPath()%>/front-end/index/images/fb.svg" width="35px" height="35px"></a>
	                </li>
	                <li>
	                    <a class="icon" href="https://line.me/zh-hant"><img src="<%=request.getContextPath()%>/front-end/index/images/line.svg" width="35px" height="35px"></a>
	                </li>
	                <li>
	                    <a class="icon" href="https://mail.google.com/mail"><img src="<%=request.getContextPath()%>/front-end/index/images/gplus.svg" width="35px" height="35px"></a>
	                </li>
	                <li>
	                    <a class="icon" href="<%=request.getContextPath()%>/back-end/emp/login.jsp"><img src="<%=request.getContextPath()%>/front-end/index/images/ig.svg" width="35px" height="35px"></a>
	                </li>
	            </ul>
	         </div>
		</div>
	</div>
</body>
