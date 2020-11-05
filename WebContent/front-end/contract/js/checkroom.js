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

function notice1(){
	if(document.getElementById("torf").value.trim() == 2 && (document.getElementById("con_chr_itm_name").value.trim().length === 0 || document.getElementById("con_chr_itm").value.trim().length === 0 || document.getElementById("con_chr_fee").value.trim() == 0)){
		swal("請填寫損壞物品名稱,描述和費用", "因為您已經勾選有物品損毀", "error", {button: "確認"});
	} else if(document.getElementById("torf").value == 1 && (document.getElementById("con_chr_itm_name").value.trim().length !== 0 || document.getElementById("con_chr_itm").value.trim().length !== 0 || document.getElementById("con_chr_fee").value.trim() != 0)){
		swal("請勾選有損毀", "因為您已經填寫損毀物品名稱,描述或費用", "error", {button: "確認"});
	}else if(document.getElementById("con_chr_itm_name").value.trim().length === 0 && document.getElementById("con_chr_itm").value.trim().length !== 0){
			swal("請填寫損壞物品名稱...", "因為您已經填寫損毀物品名稱或描述", "error", {button: "確認"});
	} else{
	swal({title:"確定送出驗房結果?", text:"如有損壞物品,將跟房客索取賠償費用" , icon:"info", buttons: {
	      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
	    }}).then(function(isConfirm){
			if(isConfirm){
				swal("更新成功!!", "", "success", {button: "確認"}).then(function(){
					document.checkForm.submit();
				});
			} else {
				return false;
			}
	    });
	}
}

function notice2(){
	swal({title:"確定要重新填寫嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){
			swal("已重置欄位!", "" , "success", {button: "確認"}).then(function(){
				document.checkForm.reset();
			});
		} else {
			return false;
		}
	});
}


function show(){
	document.getElementById("cbody"+i).style.display = "block";
	document.getElementById("btn"+i).style.backgroundColor = '#FFA64F';
	document.getElementById("btn"+(i+5)).style.backgroundColor = '#FFA64F';
	controlbtn();
}

function hide(){
	document.getElementById("cbody"+i).style.display = "none";
	document.getElementById("btn"+i).style.backgroundColor = '#83DFFD';
	document.getElementById("btn"+(i+5)).style.backgroundColor = '#83DFFD';
}

function previous(){
	hide();
	--i;			
	show();
}

function next(){
	hide();
	++i;
	show();
}

function titleshow(e){
	for(var j=1; j<=5; j++){
		if(j == e.target.value){
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			i = parseInt(e.target.value);
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';
		}		
	}
	controlbtn();
}

function controlbtn(){
	if(i === 1){
		document.getElementById("pr1").disabled = true;
		document.getElementById("ne1").disabled = false;
		document.getElementById("pr2").disabled = true;
		document.getElementById("ne2").disabled = false;
	} else if(i === 5){
		document.getElementById("pr1").disabled = false;
		document.getElementById("ne1").disabled = true;
		document.getElementById("pr2").disabled = false;
		document.getElementById("ne2").disabled = true;
	} else {
		document.getElementById("pr1").disabled = false;
		document.getElementById("ne1").disabled = false;
		document.getElementById("pr2").disabled = false;
		document.getElementById("ne2").disabled = false;
	}
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