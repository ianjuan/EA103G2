<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	String lld_no = (String) request.getAttribute("lld_no");
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>儲值</title>
	
	<style>
		html,
		body {
			padding: 0;
			margin: 0;
			color: #292929;
			background-color: #eaeaea;
		}

		.container {
			text-align: center;
			color: #2c3e50;
			width: 100%;
			height: 100%;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
		}

		form {
			transition: all 0.5s;
			position: absolute;
			top: 50%;
			transform: translateY(-50%);
		}

		.finder {
			border: 1px solid #fff;
			background-color: #f6f5f0;
			border-radius: 15px;
			padding: 8px;
			box-shadow: 9px 9px 16px rgba(189, 189, 189, 0.6), -9px -9px 16px rgba(255, 255, 255, 0.5);
		}

		.finder__outer {
			display: flex;
			width: 600px;
			padding: 1.5rem 2rem;
			border-radius: 10px;
			box-shadow: inset 10px 10px 15px -10px #c3c3c3, inset -10px -10px 15px -10px #ffffff;
		}

		.finder__inner {
			display: flex;
			align-items: center;
			position: relative;
			flex: 1;
		}

		.finder__form {
			flex: 1;
			height: calc(100% + 3rem);
		}

		.finder__input {
			height: calc(100% + 3rem);
			border: none;
			background-color: transparent;
			outline: none;
			font-size: 1.5rem;
			letter-spacing: 0.75px;
		}

		.finder__icon {
			width: 40px;
			height: 40px;
			margin-right: 1rem;
			transition: all 0.2s;
			box-shadow: inset 0 0 0 20px #292929;
			border-radius: 50%;
			position: relative;
		}
		.finder__icon:after, .finder__icon:before {
			display: block;
			content: "";
			position: absolute;
			transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
		}
		.finder__icon:after {
			width: 10px;
			height: 10px;
			background-color: #292929;
			border: 3px solid #f6f5f0;
			top: 50%;
			position: absolute;
			transform: translateY(-50%);
			left: 0px;
			right: 0;
			margin: auto;
			border-radius: 50%;
		}
		.active .finder__icon:after {
			border-width: 10px;
			background-color: #f6f5f0;
		}
		.finder__icon:before {
			width: 4px;
			height: 13px;
			background-color: #f6f5f0;
			top: 50%;
			left: 20px;
			transform: rotateZ(45deg) translate(-50%, 0);
			transform-origin: 0 0;
			border-radius: 4px;
		}
		.active .finder__icon:before {
			background-color: #292929;
			width: 6px;
			transform: rotateZ(45deg) translate(-50%, 25px);
		}
		.processing .finder__icon {
			transform-origin: 50%;
			animation: spinner 0.3s linear infinite;
			animation-delay: 0.5s;
		}
		.active .finder__icon {
			transform: translateY(-5px);
		}

		@keyframes spinner {
			0% {
				transform: rotateZ(45deg);
			}
			100% {
				transform: rotateZ(405deg);
			}
		}
		
button {
	width:150px;
	height:50px;
	margin:5px;
	font-family:'Krona One',sans-serif;
	font-size:16px;
	color:rgba(255,255,255,0.9);
	text-shadow:1px 1px 2px rgba(0,0,0,0.5);
	cursor:pointer;
	border:none;
	border-radius:5px;
	transition:300ms;
	font-weight:bold;
}

.black {
	background:#474747;
	box-shadow:0px 5px #292929,0px 10px 10px rgba(0,0,0,0.25);
}
button:hover {
	transform:translateY(1px);
	box-shadow:0px 4px #292929,0px 5px 10px rgba(0,0,0,0.25);
}
button:focus {
	outline:none;
}
button:active {
	transform:translateY(3px);
	box-shadow:0px 0px transparent,0px 5px 10px rgba(0,0,0,0.25);
}
			
		#notice {
			font-size: 200%;
			font-weight: bold;
		}
	</style>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>	
</head>
<body>	
	<div class="container">		
		<form autocomplete="off" name="addmoney" METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
			<span id="notice">請輸入要加值的金額 :</span>
			<div class="finder">
				<div class="finder__outer">
					<div class="finder__inner">
						<div class="finder__icon" ref="icon"></div>						
						<input class="finder__input" id="lld_balance" min="0" step="100" type="number" name="hos_rentfee" value="<%=(houseVO==null) ? "" : houseVO.getHos_rentfee()%>"/>
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" id="bank" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="addmoney">
						<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js"></script>
						<div>
							<button class="black noselect" type="button" onclick="checkmoney()">儲值</button>
						</div>
					</div>
				</div>
			</div>
		</form>		
	</div>
	
	<script>
			const input = document.querySelector(".finder__input");
			const finder = document.querySelector(".finder");

			input.addEventListener("focus", function() {
				finder.classList.add("active");
			});

			input.addEventListener("blur", function() {
				if (input.value.length === 0) {
					finder.classList.remove("active");
				}
			});
			
			function checkmoney(){
				var money = document.getElementById("lld_balance").value;
				var bank = document.getElementById("bank").value;
				var status1 = money < 1000 ? "也儲太少了, 可憐哪" : "挺有錢的呢";
				var status2 = parseInt(money)+parseInt(bank) < 1000 ? "真窮, 可憐哪" : "爸爸歡迎回家";
		    	
				swal({title:"是否確定儲值" + money + "元?", text:status1 , icon:"info", buttons: {
				      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
				    }}).then(function(isConfirm){
					if(isConfirm){	
						swal("儲值成功!! 電子錢包增加" + money + "元", "電子錢包目前共" + (parseInt(money)+parseInt(bank)) + "元! " + status2, "success", {button: "確認"}).then(function(){
							document.addmoney.submit();
						});
					} else {
						return false;
					}
				});
			}
	</script>
</body>
</html>