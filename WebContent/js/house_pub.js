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
	if(window.confirm("確定要刊登房屋了嗎?") == false){
		return false;
	} else {
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
		return true;
	}
}

function notice2(){
	var reset = document.getElementById("reset");
	window.confirm("確定要重新填寫嗎?");
}

function notice3(){
	if(window.confirm("確定要修改房屋資訊嗎?") == false){
		return false;
	} else {
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
		return true;
	}
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
	for(var i = 0; i < files.length; i++){
		if(files[i].type.indexOf('image') > -1){
			document.getElementById('preview').style.height = 400 + 'px';
			document.getElementById('dropDIV').style.height = 'auto';
			var reader = new FileReader();
			reader.addEventListener('load', openfile);
			reader.readAsDataURL(files[i]);
		} else {
			alert('請上傳圖片');
		}
	}
}

function openfile(e) {
	var preview = document.getElementById('preview');
	var check = document.createElement('input');
	var label = document.createElement('label');
	var div = document.createElement('div');

	check.setAttribute("type", "checkbox");
	check.setAttribute("class", "check");

	var img = document.createElement('img');
	img.setAttribute('src', e.target.result);

	label.setAttribute("class", "pic");

	label.append(check);
	div.append(img);
	label.append(div);
	preview.append(label);
}        

function del() {
	var optobj = document.querySelectorAll(".check:checked");
	document.getElementById("loadPic").value = "";
	for (var i = 0; i < optobj.length; i++) {
		if (optobj[i].checked) {
			optobj[i].parentElement.remove();					
		}
	}			
	if(document.getElementsByClassName('check').length === 0){
		document.getElementById('preview').style.height = 0 + 'px';
		document.getElementById('dropDIV').style.height = 390 + 'px';
	}
}

function checkpicAll() {
	var check = document.getElementsByClassName("check");
	var optobj = document.querySelectorAll(".check:checked");
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
}

function dragoverHandler(e) {
	e.preventDefault();
}

function dropHandler(e) {//e 為 DragEvent 物件
	e.preventDefault();
    var files = e.dataTransfer.files;//由DataTransfer物件的files屬性取得檔案物件

    for (var i = 0; i < files.length; i++) {
    	if (files[i].type.indexOf('image') > -1) {
    		document.getElementById('preview').style.height = 400 + 'px';
    		document.getElementById('dropDIV').style.height = 'auto';
            var reader = new FileReader(); //將圖片在頁面預覽 
            reader.onload = openfile;
            reader.readAsDataURL(files[i]);
        } else alert('請上傳圖片');                
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