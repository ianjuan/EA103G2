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
	swal({title:"確定要送出合約了嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
	    }}).then(function(isConfirm){
			if(isConfirm){
				swal("更新成功!!", "", "success", {button: "確認"}).then(function(){
					document.contForm.submit();
				});
			} else {
				return false;
			}
	    });
}

function notice2(){
	swal({title:"確定要重新填寫嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){
			swal("已重置欄位!", "" , "success", {button: "確認"}).then(function(){
				document.contForm.reset();
			});
		} else {
			return false;
		}
	});
}

function notice3(){		
	var pic = document.getElementsByClassName("pic").length;
	var pic1 = document.getElementsByClassName("pic1").length;
	 if(pic + pic1 < 5){
		 swal("目前才" + (pic + pic1) + "圖片欸...", "請再上傳"+(5-pic1)+"~"+(10-pic1)+"張圖片", "error", {button: "確認"});
	 } else {
		 swal({title:"確定要更新房屋資訊了嗎?", text:"" , icon:"info", buttons: {
		      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
		    }}).then(function(isConfirm){
			if(isConfirm){
				swal("更新成功!!", "", "success", {button: "確認"}).then(function(){
					document.contForm.submit();
				});
			} else {
				return false;
			}	    				
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
	
	if(files.length > 1){
		swal("您上傳了" + files.length + "張簽名，已超過上限！", "請上傳一張簽名就好", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else if(files.length < 1){
		swal("您沒送出簽名！", "請簽名!", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else {						
		var optobj = document.querySelectorAll(".pic");
		for (var i = 0; i < optobj.length; i++) {
			optobj[i].remove();		
		}
		swal("您成功送出簽名", "", "success", {button: "棒棒"});
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
	
	if(files.length + pic1 > 1){
		swal("您多簽了" + files.length + "次，已超過上限！", "只能簽一次", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else if(files.length + pic1 < 1){
		swal("您沒有簽名！", "請簽名後再送出", "error", {button: "重新上傳"});
		document.getElementById("loadPic").value = "";
	} else {						
		var optobj = document.querySelectorAll(".pic");
		for (var i = 0; i < optobj.length; i++) {
			optobj[i].remove();		
		}
		swal("成功送出簽名", "success", {button: "棒棒"});
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
	
	swal({title:"確定要移除選取的簽名嗎?", text:"您選取了"+optpic.length+"張簽名" , icon:"warning", buttons: {
	      Btn: false, cancel: {text:"取消", visible: true}, danger: {text:"確認", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){						
			for (var i = 0; i < optpic.length; i++) {
				var input = document.createElement("input");
				input.setAttribute("type", "hidden");
				input.setAttribute("name", "con_lld_sign");
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

(function() {
	  window.requestAnimFrame = (function(callback) {
	    return window.requestAnimationFrame ||
	      window.webkitRequestAnimationFrame ||
	      window.mozRequestAnimationFrame ||
	      window.oRequestAnimationFrame ||
	      window.msRequestAnimaitonFrame ||
	      function(callback) {
	        window.setTimeout(callback, 1000 / 60);
	      };
	  })();

	  var canvas = document.getElementById("sig-canvas");
	  var ctx = canvas.getContext("2d");
	  ctx.strokeStyle = "#222222";
	  ctx.lineWidth = 4;

	  var drawing = false;
	  var mousePos = {
	    x: 0,
	    y: 0
	  };
	  var lastPos = mousePos;

	  canvas.addEventListener("mousedown", function(e) {
	    drawing = true;
	    lastPos = getMousePos(canvas, e);
	  }, false);

	  canvas.addEventListener("mouseup", function(e) {
	    drawing = false;
	  }, false);

	  canvas.addEventListener("mousemove", function(e) {
	    mousePos = getMousePos(canvas, e);
	  }, false);

	  // Add touch event support for mobile
	  canvas.addEventListener("touchstart", function(e) {

	  }, false);

	  canvas.addEventListener("touchmove", function(e) {
	    var touch = e.touches[0];
	    var me = new MouseEvent("mousemove", {
	      clientX: touch.clientX,
	      clientY: touch.clientY
	    });
	    canvas.dispatchEvent(me);
	  }, false);

	  canvas.addEventListener("touchstart", function(e) {
	    mousePos = getTouchPos(canvas, e);
	    var touch = e.touches[0];
	    var me = new MouseEvent("mousedown", {
	      clientX: touch.clientX,
	      clientY: touch.clientY
	    });
	    canvas.dispatchEvent(me);
	  }, false);

	  canvas.addEventListener("touchend", function(e) {
	    var me = new MouseEvent("mouseup", {});
	    canvas.dispatchEvent(me);
	  }, false);

	  function getMousePos(canvasDom, mouseEvent) {
	    var rect = canvasDom.getBoundingClientRect();
	    return {
	      x: mouseEvent.clientX - rect.left,
	      y: mouseEvent.clientY - rect.top
	    }
	  }

	  function getTouchPos(canvasDom, touchEvent) {
	    var rect = canvasDom.getBoundingClientRect();
	    return {
	      x: touchEvent.touches[0].clientX - rect.left,
	      y: touchEvent.touches[0].clientY - rect.top
	    }
	  }

	  function renderCanvas() {
	    if (drawing) {
	      ctx.moveTo(lastPos.x, lastPos.y);
	      ctx.lineTo(mousePos.x, mousePos.y);
	      ctx.stroke();
	      lastPos = mousePos;
	    }
	  }

	  // Prevent scrolling when touching the canvas
	  document.body.addEventListener("touchstart", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);
	  document.body.addEventListener("touchend", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);
	  document.body.addEventListener("touchmove", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);

	  (function drawLoop() {
	    requestAnimFrame(drawLoop);
	    renderCanvas();
	  })();

	  function clearCanvas() {
	    canvas.width = canvas.width;
	  }
	  
	  

	// Set up the UI
	  var sigImage = document.getElementById("sig-image");
	  var clearBtn = document.getElementById("sig-clearBtn");
	  var submitBtn = document.getElementById("sig-submitBtn");
	  var sign = document.getElementById("sign");
	  clearBtn.addEventListener("click", function(e) {
		sigImage.style.display = "none";
		clearCanvas();
	    var ctx = canvas.getContext("2d");
		ctx.strokeStyle = "#222222";
		ctx.lineWidth = 4;
	    sigImage.setAttribute("src", "");
	    sign.setAttribute("value", "");
	  }, false);
	  submitBtn.addEventListener("click", function(e) {
	    var dataUrl = canvas.toDataURL();
	    sigImage.style.display = "block";
	    sigImage.setAttribute("src", dataUrl);
	    sign.setAttribute("value", dataUrl);
	  }, false);

	})();