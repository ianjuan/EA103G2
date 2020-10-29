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

function notice1(event) {
	let _this=event.target;
	let a = $(_this).parent().parent()[0];
	var list = document.getElementsByClassName("cinfo").length;

	swal({
		title : "確定接受申請？",text:"如果接受, 愛租將幫您拒絕其他" + (list - 1) + "個申請",
		buttons : {
			Btn : false,
			confirm : {
				text : "確認",
				visible : true
			},
			cancel : {
				text : "取消",
				visible : true
			}
		}
	}).then(function(isConfirm) {
		if (isConfirm) {
			swal("已接受此申請", "請開始填寫合約", "success", {
				button : "確認"
			}).then(function() {
				a.submit();
			});
		} else {
			return false;
		}
	});
}

function notice2(event) {
	
	let _this=event.target;
	let a = $(_this).parent().parent()[0];

	swal({
		  title: "確定拒絕?",
		  text: "一旦拒絕, 將跟此房客說掰掰!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		    swal("再也看不到這房客囉!", {
		      icon: "success",
		    }).then(function() {
				a.submit();
		    });
		  } else {
		    swal("還有機會跟他結緣!");
		  }
		});
}

function checkmoney(){
	var money = document.getElementById("lld_balance");
	if(money.value < 1000){
		swal("您的電子錢包餘額為 : " + money.value + "元").then(function(){
			swal({title:"請問是否要儲值?", text:"上架費一次為 1000 元" , icon:"info", buttons: {
			      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
			    }}).then(function(isConfirm){
				if(isConfirm){
					swal("爸爸辛苦了, 等您回來!", {button: "確認"}).then(function(){
						document.pub.submit();
					});
				} else {
					return false;
				}	    				
			})
		});
	} else {
		swal("目前電子錢包金額為" + money.value + "元").then(function(){
			swal({title:"是否上架房屋?", text:"上架費一次為 1000 元" , icon:"info", buttons: {
			      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
			    }}).then(function(isConfirm){
				if(isConfirm){
					swal("開始上架房屋!!", {button: "確認"}).then(function(){
						document.pub.submit();
					});
				} else {
					return false;
				}	    				
			})
		});
	}
}