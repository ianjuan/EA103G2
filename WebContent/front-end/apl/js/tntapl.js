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

function notice(e){
	 swal({title:"確定要送出評價了嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){
			swal("成功送出!!", "", "success", {button: "確認"}).then(function(){
				document.getElementById("evaluation" + e.target.getAttribute("value")).submit();
			});
		} else {
			return false;
		}
	});
}

function fillnotice(e){
	 swal({title:"確定要修改申請時間嗎?", text:"請動作將會變動您的權益" , icon:"info", buttons: {
	      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){
			swal("成功送出!!", "", "success", {button: "確認"}).then(function(){
				document.getElementById("fullin" + e.target.getAttribute("value")).submit();
			});
		} else {
			return false;
		}	    				
	});
}

