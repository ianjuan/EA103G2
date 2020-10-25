var i = 1;

window.onload = function() {
	show();

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

function notice1() {
	var list = document.getElementsByClassName("cinfo").length;

	swal({
		title : "如果接受此申請, 愛租將幫您自動拒絕其他" + (list - 1) + "個申請",text:"",
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
			swal("已接受此申請", "success", {
				button : "確認"
			}).then(function() {
				document.aplForm.submit();
			});
		} else {
			return false;
		}
	});
}