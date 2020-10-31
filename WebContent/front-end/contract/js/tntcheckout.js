var i = 1;

window.onload = function(){
		
	var menuBtn = document.querySelector('.menu-btn');
	var nav = document.querySelector('#housenav');
	var lineOne = document.querySelector('#housenav .menu-btn .line--1');
	var lineTwo = document.querySelector('#housenav .menu-btn .line--2');
	var lineThree = document.querySelector('#housenav .menu-btn .line--3');
	var link = document.querySelector('#housenav .nav-links');
	menuBtn.addEventListener('click', function() {
		nav.classList.toggle('nav-open');
		lineOne.classList.toggle('line-cross');
		lineTwo.classList.toggle('line-fade-out');
		lineThree.classList.toggle('line-cross');
		link.classList.toggle('fade-in');
	})			
}

function checkmoney(){
	var money = parseInt(document.getElementById("tnt_balance").value);
	var total = parseInt(document.getElementById("tnt_total").value);
	
	console.log(money.value);
	console.log(total.value);
	if(money < total){
		swal("您的電子錢包餘額為 : " + money + "元").then(function(){
			swal({title:"請問是否要儲值?", text:"退房總共要繳交" + total + "元" , icon:"info", buttons: {
			      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
			    }}).then(function(isConfirm){
				if(isConfirm){
					swal("扣款成功!", {button: "確認"}).then(function(){
						document.checkForm.submit();
					});
				} else {
					return false;
				}	    				
			})
		});
	} else {
		swal("目前電子錢包金額為" + money + "元").then(function(){
			swal({title:"是否繳交退房金額?", text:"退房金額為" + total + "元" , icon:"info", buttons: {
			      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
			    }}).then(function(isConfirm){
				if(isConfirm){
					swal("退房成功!!", {button: "確認"}).then(function(){
						document.checkForm.submit();
					});
				} else {
					return false;
				}	    				
			})
		});
	}
}

function cancel(){		
	swal({title:"暫時取消退房？", text:"如有超過退房時限,將依照合約規定收費" , icon:"warning", buttons: true,
		  dangerMode: true,}).then((willDelete) => {
			if(willDelete){
				swal("取消成功", "", "success", {button: "確認"})
				.then(function(){
					document.checkForm.submit();
				});
			} else {
				swal("再好好想想吧!");
			}
	    });
}