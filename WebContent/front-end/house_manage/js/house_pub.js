var i = 1;

window.onload = function(){
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

function notice1(){
	var pic = document.getElementsByClassName("pic").length;
	
	if(document.getElementById("hos_name").value.trim().length === 0){
		swal("房屋名稱還沒填哦...", "快去填!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_add").value.trim().length === 0){
		swal("地址還沒填哦...", "快去填!!!", "error", {button: "確認"});
	} else if(document.querySelectorAll(".hos_type:checked").length === 0){
		swal("房屋類型還沒選哦...", "快去選!!!", "error", {button: "確認"});
	} else if(document.querySelectorAll(".hos_room:checked").length === 0){
		swal("房間類型還沒選哦...", "快去選!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_floor").value.trim().length === 0){
		swal("樓層還沒填哦...", "快去填!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_pnum").value.trim().length === 0){
		swal("坪數還沒填哦...", "快去填!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_rentfee").value.trim().length === 0){
		swal("租金還沒填哦...", "快去填!!!", "error", {button: "確認"});
	} else if(pic < 5){
		swal("目前才" + pic + "張圖片而已欸...", "請上傳 5~10 張圖片", "error", {button: "確認"});
	} else {
		swal({title:"確定要送出房屋資訊了嗎?", text:"" , icon:"info", buttons: {
		     Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
		   }}).then(function(isConfirm){
		if(isConfirm){
			var geocoder = new google.maps.Geocoder();
			address = document.getElementById("hos_add").value;
			geocoder.geocode({ 'address': address }, function(results, status) {
				if (status === google.maps.GeocoderStatus.OK && results.length > 0) {
					var lat = results[0].geometry.location.lat();
					var lng = results[0].geometry.location.lng();
					document.getElementById("lat").setAttribute("value", lat);
					document.getElementById("lng").setAttribute("value", lng);
						
					var bank = document.getElementById("lld_balance").value;
					swal("上架房屋成功!!", "電子錢包扣款1000元, 目前還剩"+(bank-1000)+"元" , "success", {button: "確認"}).then(function(){
						document.houseForm.submit();
					});
				} else {
					swal("這個地址不行哦...", "請重新輸入地址", "error", {button: "確認"});
					return false;
				}
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
				document.houseForm.reset();
				del();
				floatfee1();
				floatfee2();
				gasfee();
				netfee();
				parkfee();
			});
		} else {
			return false;
		}
	});
}

function notice3(){		
	var pic = document.getElementsByClassName("pic").length;
	var pic1 = document.getElementsByClassName("pic1").length;
	
	if(document.getElementById("hos_name").value.trim().length === 0){
		swal("房屋名稱別亂改阿...都沒了", "快去改好!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_floor").value.trim().length === 0){
		swal("樓層別亂改阿...都沒了", "快去改好!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_pnum").value.trim().length === 0){
		swal("坪數別亂改阿...都沒了", "快去改好!!!", "error", {button: "確認"});
	} else if(document.getElementById("hos_rentfee").value.trim().length === 0){
		swal("租金別亂改阿...都沒了", "快去改好!!!", "error", {button: "確認"});
	} else if(pic + pic1 < 5){
		swal("目前才" + (pic + pic1) + "圖片欸...", "請再上傳"+(5-pic1)+"~"+(10-pic1)+"張圖片", "error", {button: "確認"});
	} else {
		swal({title:"確定要更新房屋資訊了嗎?", text:"" , icon:"info", buttons: {
		     Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
		   }}).then(function(isConfirm){
			if(isConfirm){
				swal("更新成功!!", "", "success", {button: "確認"}).then(function(){
					document.houseForm.submit();
				});
			} else {
				return false;
			}
		});
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

function load(){
	var files = document.getElementById("loadPic").files;
	
	if(files.length > 10){
		swal("您上傳了" + files.length + "張圖片，已超過上限！", "請上傳5~10張圖片", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else if(files.length < 5){
		swal("您上傳了" + files.length + "張圖片，太少了吧！", "請上傳5~10張圖片", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else {						
		var optobj = document.querySelectorAll(".pic");
		for (var i = 0; i < optobj.length; i++) {
			optobj[i].remove();		
		}
		swal("您上傳了" + files.length + "張圖片", "", "success", {button: "棒棒"});
		for(var i = 0; i < files.length; i++){							
			if(files[i].type.indexOf('image') > -1){				
				var reader = new FileReader();
				reader.addEventListener('load', openfile);
				reader.readAsDataURL(files[i]);
			} else {
				swal("您上傳的不是圖片檔哦", "請上傳5~10張圖片", "warning", {button: "重新上傳"});
				document.getElementById("loadPic").value = "";
			}			
		}		
	}
}

function load1(){
	var files = document.getElementById("loadPic").files;
	var pic1 = document.querySelectorAll(".pic1").length;
	
	if(files.length + pic1 > 10){
		swal("您上傳了" + files.length + "張圖片，已超過上限！", "只能上傳"+(5-pic1)+"~"+(10-pic1)+"張圖片", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else if(files.length + pic1 < 5){
		swal("您上傳了" + files.length + "張圖片哪夠啊！", "至少要上傳"+(5-pic1)+"~"+(10-pic1)+"張圖片", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else {						
		var optobj = document.querySelectorAll(".pic");
		for (var i = 0; i < optobj.length; i++) {
			optobj[i].remove();		
		}
		swal("上傳了" + files.length + "張圖片", "目前共" + (files.length+pic1) + "張", "success", {button: "棒棒"});
		for(var i = 0; i < files.length; i++){							
			if(files[i].type.indexOf('image') > -1){				
				var reader = new FileReader();
				reader.addEventListener('load', openfile);
				reader.readAsDataURL(files[i]);
			} else {
				swal("您上傳的不是圖片檔哦", "請上傳"+(5-pic1)+"~"+(10-pic1)+"張圖片", "warning", {button: "重新上傳"});
				document.getElementById("loadPic").value = "";
			}			
		}		
	}
}

function openfile(e) {
	var preview = document.getElementById('preview');
	var img = document.createElement('img');
	
	img.setAttribute('src', e.target.result);
	img.setAttribute("class", "pic");
	preview.append(img);
	
	$(function(){  
        $(".pic").click(function(){
            var _this = $(this);  
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
        });  
    });
}        

function del() {
	var optobj = document.querySelectorAll(".pic");
	document.getElementById("loadPic").value = "";
	for (var i = 0; i < optobj.length; i++) {
		optobj[i].remove();		
	}
}

function delpic(){
	var optpic = document.querySelectorAll(".checkpic:checked");
	var preview1 = document.getElementById("preview1");
	
	swal({title:"確定要移除選取的圖片嗎?", text:"您選取了"+optpic.length+"張圖片" , icon:"warning", buttons: {
	      Btn: false, danger: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){						
			for (var i = 0; i < optpic.length; i++) {
				var input = document.createElement("input");
				input.setAttribute("type", "hidden");
				input.setAttribute("name", "pic_no");
				input.setAttribute("value", optpic[i].nextElementSibling.getAttribute("value"));
				preview1.append(input);								
			}
			for (var i = 0; i < optpic.length; i++) {
				optpic[i].parentElement.parentElement.remove();
			}
			swal("移除成功", "", "success", {button: "確定"});
		} else {
			return false;
		}	    				
	});	
}

function checkpic(){	
	$(function(){  
        $(".pic1").click(function(){  
        	var _this = $(this);
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
    });
}

function checkpicAll() {
	var check = document.getElementsByClassName("checkpic");
	var optobj = document.querySelectorAll(".checkpic:checked");
	for(var i = 0; i < check.length; i++){
		if(optobj.length !== check.length){
			check[i].checked = true;
		} else {
			check[i].checked = false;
		}
	}
}

function checkfurAll() {
	var check = document.getElementsByClassName("onoffswitch-checkbox");
	var optobj = document.querySelectorAll(".onoffswitch-checkbox:checked");
	for(var i = 0; i < check.length; i++){
		if(optobj.length !== check.length){
			check[i].checked = true;
		} else {
			check[i].checked = false;
		}
	}
	gasfee();
	netfee();
}

function floatfee1(){
	var watertype0 = document.getElementById("watertype0");
	var watertype1 = document.getElementById("watertype1");
	var watertype2 = document.getElementById("watertype2");
	var water1 = document.getElementById("water1");
	var water2 = document.getElementById("water2");

	if(watertype1.checked == true){
		water1.disabled = false;
		water2.disabled = true;
		water2.value = "";
	} else if(watertype2.checked == true){
		water1.disabled = true;
		water2.disabled = false;
		water1.value = "";
	} else {
		water1.disabled = true;
		water2.disabled = true;
		water1.value = "";
		water2.value = "";
	}
}

function floatfee2(){
	var electtype0 = document.getElementById("electtype0");
	var electtype1 = document.getElementById("electtype1");
	var electtype2 = document.getElementById("electtype2");
	var elect1 = document.getElementById("elect1");
	var elect2 = document.getElementById("elect2");
	
	if(electtype1.checked == true){
		elect1.disabled = false;
		elect2.disabled = true;
		elect2.value = "";
	} else if(electtype2.checked == true){
		elect1.disabled = true;
		elect2.disabled = false;
		elect1.value = "";
	} else {
		elect1.disabled = true;
		elect2.disabled = true;
		elect1.value = "";
		elect2.value = "";
	}
}

function gasfee(){
	var gas = document.getElementById("gas");
	var gasfee = document.getElementById("gasfee1");
	
	if(gas.checked == true){
		gasfee.disabled = false;
	} else {
		gasfee.disabled = true;
		gasfee.value = "";
	}
}

function netfee(){
	var net = document.getElementById("IE");
	var netfee = document.getElementById("netfee1");
	
	if(net.checked == true){
		netfee.disabled = false;
	} else {
		netfee.disabled = true;
		netfee.value = "";
	}
}

function parkfee(){
	var park1 = document.getElementById("park1");
	var park2 = document.getElementById("park2");
	var parkfee = document.getElementById("parkfee1");
	
	if(park1.checked == true || park2.checked == true){
		parkfee.disabled = false;
	} else {
		parkfee.disabled = true;
		parkfee.value = "";
	}
}

function checkLen1(e) {
	var maxChars = 200;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
	document.getElementById("count1").innerHTML = maxChars - e.value.length;
}

function checkLen2(e) {
	var maxChars = 200;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
	document.getElementById("count2").innerHTML = maxChars - e.value.length;
}

function checkLen3(e) {
	var maxChars = 6;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
}

function checkLen4(e) {
	var maxChars = 8;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
}

function checkLen5(e) {
	var maxChars = 4;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
}

function map(){
	var geocoder = new google.maps.Geocoder();
	address = document.getElementById("hos_add").value;
	geocoder.geocode({ 'address': address }, function(results, status) { //地址轉換經緯度 results取得該地區所有資訊 status回傳成功與否 以'OK'表示
		if (status === google.maps.GeocoderStatus.OK && results.length > 0) {
			var lat = results[0].geometry.location.lat();
			var lng = results[0].geometry.location.lng();
			document.getElementById("lat").setAttribute("value", lat);
			document.getElementById("lng").setAttribute("value", lng);
		} else {
			swal("這個地址不行哦...", "請重新輸入地址", "error", {button: "確認"});
		}
	});
}

function imgShow(outerdiv, innerdiv, bigimg, _this){  
    var src = _this.attr("src");//獲取當前點選的pimg元素中的src屬性  
    $(bigimg).attr("src", src);//設定#bigimg元素的src屬性  

    /*獲取當前點選圖片的真實大小，並顯示彈出層及大圖*/  
    $("<img/>").attr("src", src).load(function(){  
        var windowW = $(window).width();//獲取當前視窗寬度  
        var windowH = $(window).height();//獲取當前視窗高度  
        var realWidth = this.width;//獲取圖片真實寬度  
        var realHeight = this.height;//獲取圖片真實高度  
        var imgWidth, imgHeight;  
        var scale = 0.8;//縮放尺寸，當圖片真實寬度和高度大於視窗寬度和高度時進行縮放  

        if(realHeight>windowH*scale) {//判斷圖片高度  
            imgHeight = windowH*scale;//如大於視窗高度，圖片高度進行縮放  
            imgWidth = imgHeight/realHeight*realWidth;//等比例縮放寬度  
            if(imgWidth>windowW*scale) {//如寬度扔大於視窗寬度  
                imgWidth = windowW*scale;//再對寬度進行縮放  
            }  
        } else if(realWidth>windowW*scale) {//如圖片高度合適，判斷圖片寬度  
            imgWidth = windowW*scale;//如大於視窗寬度，圖片寬度進行縮放  
                        imgHeight = imgWidth/realWidth*realHeight;//等比例縮放高度  
        } else {//如果圖片真實高度和寬度都符合要求，高寬不變  
            imgWidth = realWidth;  
            imgHeight = realHeight;  
        }  
                $(bigimg).css("width",imgWidth);//以最終的寬度對圖片縮放  

        var w = (windowW-imgWidth)/2;//計算圖片與視窗左邊距  
        var h = (windowH-imgHeight)/2;//計算圖片與視窗上邊距  
        $(innerdiv).css({"top":h, "left":w});//設定#innerdiv的top和left屬性  
        $(outerdiv).fadeIn("fast");//淡入顯示#outerdiv及.pimg  
    });  

    $(outerdiv).click(function(){//再次點選淡出消失彈出層  
        $(this).fadeOut("fast");  
    });  
}

function quickvalue(){
	var hos_type = document.getElementsByClassName("hos_type");
	var hos_room = document.getElementsByClassName("hos_room");
	var hos_waterfeetype = document.getElementsByClassName("hos_waterfeetype");
	var hos_electfeetype = document.getElementsByClassName("hos_electfeetype");
	var fur = document.getElementsByClassName("onoffswitch-checkbox");
	var hos_mindate = document.getElementsByClassName("hos_mindate");
	var hos_park = document.getElementsByClassName("hos_park");
	var hos_sex = document.getElementsByClassName("hos_sex");
	var hos_iden = document.getElementsByClassName("hos_iden");
	var hos_cook = document.getElementsByClassName("hos_cook");
	var hos_pet = document.getElementsByClassName("hos_pet");
	var hos_smoke = document.getElementsByClassName("hos_smoke");
	var hos_status = document.getElementsByClassName("hos_status");
		
	document.getElementById("hos_name").value = "中央大學資策會204教室";
	document.getElementById("hos_liffun").value = "環境優美, 生活機能便利, 離'全家','漢堡王','露易莎'都很近!!";
	document.getElementById("hos_trans").value = "公車班次多, 很方便! 可惜附記沒捷運, 可憐哪!";
	document.getElementById("hos_add").value = "桃園市中壢區中大路300號工程二館(資策會大樓)";
	document.getElementById("hos_pat").value = "一房一廳 衛浴二樓在整修 沒陽台";
	document.getElementById("hos_floor").value = "2F";
	document.getElementById("hos_pnum").value = 30;
	hos_type[2].checked = true;
	hos_room[1].checked = true;
	
	fur[0].checked = true;
	fur[1].checked = true;
	fur[5].checked = true;
	fur[6].checked = true;
	fur[7].checked = true;
	fur[8].checked = true;
	fur[11].checked = true;
	fur[12].checked = true;
	
	document.getElementById("hos_rentfee").value = 120000;
	hos_waterfeetype[1].checked = true;
	document.getElementById("water2").disabled = false;
	document.getElementById("water2").value = 500;
	hos_electfeetype[0].checked = true;
	document.getElementById("elect1").disabled = false;
	document.getElementById("elect1").value = 5;
	document.getElementById("hos_manafee1").value = 2000;
	document.getElementById("netfee1").disabled = false;
	document.getElementById("netfee1").value = 500;
	document.getElementById("hos_puwaterfee1").value = 0;
	document.getElementById("hos_puelefee1").value = 0;
	document.getElementById("parkfee1").disabled = false;
	document.getElementById("parkfee1").value = 2000;
	
	document.getElementById("hos_mdate").value = "隨時都行~ 要搶要快唷!";
	hos_mindate[1].checked = true;
	hos_park[1].checked = true;
	hos_sex[0].checked = true;
	hos_iden[1].checked = true;
	hos_cook[0].checked = true;
	hos_pet[1].checked = true;
	hos_smoke[1].checked = true;
	hos_status[0].checked = true;
}