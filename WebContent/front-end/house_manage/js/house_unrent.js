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

    $(function(){  
        $(".pic").click(function(){  
                var _this = $(this);//將當前的pimg元素作為_this傳入函式  
                imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
            });  
    });
    
    rowcount();
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

function checkmoney(){
	var money = document.getElementById("lld_balance");
	if(money.value < 1000){
		swal("您的電子錢包餘額為 : " + money.value + "元").then(function(){
			swal({title:"請問是否要儲值?", text:"上架費一次為 1000 元" , icon:"info", buttons: {
			      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
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
			      Btn: false, cancel: {text:"取消", visible: true}, confirm: {text:"確認", visible: true}
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

(function(document) {
	  'use strict';

	  // 建立 LightTableFilter
	  var LightTableFilter = (function(Arr) {

	    var _input;
	    var i = 0;
	    
	    // 資料輸入事件處理函數
	    function _onInputEvent(e) {
	      _input = e.target;
	      
	      var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
	      Arr.forEach.call(tables, function(table) {
	        Arr.forEach.call(table.tBodies, function(tbody) {
	          Arr.forEach.call(tbody.rows, _filter);
	        });
	      });
	      document.getElementById("count1").innerHTML = i;
	      i = 0;
	    }
	   
	    // 資料篩選函數，顯示包含關鍵字的列，其餘隱藏
	    function _filter(row) {
	      var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase().trim();
	      row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
	      if(text.indexOf(val) !== -1) i++;
	    }
	    
	    return {
	      // 初始化函數
	      init: function() {
	        var inputs = document.getElementsByClassName('light-table-filter');
	        Arr.forEach.call(inputs, function(input) {
	          input.oninput = _onInputEvent;
	        });
	      }
	    };        
	  })(Array.prototype);

	  // 網頁載入完成後，啟動 LightTableFilter
	  document.addEventListener('readystatechange', function() {
	    if (document.readyState === 'complete') {    	
	    	LightTableFilter.init();
	    }
	  });

	})(document);

	function rowcount(){
		var list = document.getElementById("list").getElementsByTagName("tr").length;
		document.getElementById("count1").innerHTML = list-1;
	}