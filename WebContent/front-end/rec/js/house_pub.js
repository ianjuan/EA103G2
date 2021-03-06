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

function map(){
	var geocoder = new google.maps.Geocoder();
	address = document.getElementById("hos_add").value;
	geocoder.geocode({ 'address': address }, function(results, status) { //地址轉換經緯度 results取得該地區所有資訊 status回傳成功與否 以'OK'表示
		if (status == 'OK') {
			var lat = results[0].geometry.location.lat();
			var lng = results[0].geometry.location.lng();
			document.getElementById("lat").setAttribute("value", lat);
			document.getElementById("lng").setAttribute("value", lng);
		} else {
			console.log(status);
		}
	});
}


function notice2(){
	var reset = document.getElementById("reset");
	window.confirm("確定要重新填寫嗎?");
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
	} else if(watertype2.checked == true){
		water1.disabled = true;
		water2.disabled = false;
	} else if(watertype0.checked == true){
		water1.disabled = true;
		water2.disabled = true;
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
	} else if(electtype2.checked == true){
		elect1.disabled = true;
		elect2.disabled = false;
	} else if(electtype0.checked == true){
		elect1.disabled = true;
		elect2.disabled = true;
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
	var parkfee = document.getElementById("parkfee1");
	
	if(park1.checked == true){
		parkfee.disabled = true;
		parkfee.value = "";
	} else {
		parkfee.disabled = false;
	}
}

function show(){
	document.getElementById("cbody"+i).style.display = "block";
	document.getElementById("btn"+i).style.backgroundColor = '#FFA64F';
	document.getElementById("btn"+(i+5)).style.backgroundColor = '#FFA64F';
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

function show1(){
	for(var j=1; j<=5; j++){
		if(j === 1){
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			document.getElementById("pr1").disabled = true;
			document.getElementById("pr2").disabled = true;
			i = j;
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';
			document.getElementById("ne1").disabled = false;
			document.getElementById("ne2").disabled = false;
		}
	}
}

function show2(){
	for(var j=1; j<=5; j++){
		if(j === 2){
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			i = j;
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';
		}
	}
	document.getElementById("pr1").disabled = false;
	document.getElementById("ne1").disabled = false;
	document.getElementById("pr2").disabled = false;
	document.getElementById("ne2").disabled = false;
}

function show3(){
	for(var j=1; j<=5; j++){
		if(j === 3){
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			i = j;
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';			
		}				
	}
	document.getElementById("pr1").disabled = false;
	document.getElementById("ne1").disabled = false;
	document.getElementById("pr2").disabled = false;
	document.getElementById("ne2").disabled = false;
}

function show4(){
	for(var j=1; j<=5; j++){
		if(j === 4){
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			i = j;
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';
		}
	}
	document.getElementById("pr1").disabled = false;
	document.getElementById("ne1").disabled = false;
	document.getElementById("pr2").disabled = false;
	document.getElementById("ne2").disabled = false;
}

function show5(){
	for(var j=1; j<=5; j++){
		if(j === 5){					
			document.getElementById("cbody"+j).style.display = "block";
			document.getElementById("btn"+j).style.backgroundColor = '#FFA64F';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#FFA64F';
			document.getElementById("ne1").disabled = true;
			document.getElementById("ne2").disabled = true;
			i = j;
		} else {
			document.getElementById("cbody"+j).style.display = "none";
			document.getElementById("btn"+j).style.backgroundColor = '#83DFFD';
			document.getElementById("btn"+(j+5)).style.backgroundColor = '#83DFFD';
			document.getElementById("pr1").disabled = false;
			document.getElementById("pr2").disabled = false;
		}
	}
}

function load(){
	var files = document.getElementById("loadPic").files;
	var pic1 = document.querySelectorAll(".pic1");
	
	if(files.length + pic1.length > 10){
		alert("大家都只能傳10張圖片，就你" + (files.length+pic1.length) + "張，請再少一點！");
		document.getElementById("loadPic").value = "";
	} else if(files.length + pic1.length < 5){
		alert("拜託至少來個5張圖片吧，只有" + (files.length+pic1.length) + "張還是別租了，請再多一點！");
		document.getElementById("loadPic").value = "";
	} else {						
		var optobj = document.querySelectorAll(".pic");
		for (var i = 0; i < optobj.length; i++) {
			optobj[i].remove();		
		}
		alert("上傳了" + files.length + "張圖片，目前共" + (files.length+pic1.length) + "張，棒棒！");
		for(var i = 0; i < files.length; i++){							
			if(files[i].type.indexOf('image') > -1){				
				var reader = new FileReader();
				reader.addEventListener('load', openfile);
				reader.readAsDataURL(files[i]);
			} else {
				alert('請上傳圖片');
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
	if(window.confirm("確定要移除選取的圖片嗎?") == false){
		return false;
	} else {		
		var preview1 = document.getElementById("preview1");		
		var optpic = document.querySelectorAll(".checkpic:checked");
		console.log(optpic.length);
		
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
	}
}

function checkpic(){
//	$(function(){  
//        $(".pic1").click(function(){  
//        	var _this = $(this);  
//            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
//        });
//    });
	
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
	
	var gas = document.getElementById("gas");
	var gasfee = document.getElementById("gasfee1");
	
	if(gas.checked == true){
		gasfee.disabled = false;
	} else {
		gasfee.disabled = true;
		gasfee.value = "";
	}
	
	var net = document.getElementById("IE");
	var netfee = document.getElementById("netfee1");
	
	if(net.checked == true){
		netfee.disabled = false;
	} else {
		netfee.disabled = true;
		netfee.value = "";
	}
}

function checkLen1(obj) {
	var maxChars = 200;//最多字符数
	if (obj.value.length > maxChars)
		obj.value = obj.value.substring(0,maxChars);
	var curr = maxChars - obj.value.length;
	document.getElementById("count1").innerHTML = curr.toString();
}

function checkLen2(obj) {
	var maxChars = 200;//最多字符数
	if (obj.value.length > maxChars)
		obj.value = obj.value.substring(0,maxChars);
	var curr = maxChars - obj.value.length;
	document.getElementById("count2").innerHTML = curr.toString();
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