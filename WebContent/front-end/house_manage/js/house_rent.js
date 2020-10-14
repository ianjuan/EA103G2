window.onload = function(){
    var menuBtn = document.querySelector('.menu-btn');
    var nav = document.querySelector('#housenav');
    var lineOne = document.querySelector('#housenav .menu-btn .line--1');
    var lineTwo = document.querySelector('#housenav .menu-btn .line--2');
    var lineThree = document.querySelector('#housenav .menu-btn .line--3');
    var link = document.querySelector('#housenav .nav-links');
    menuBtn.addEventListener('click', function(){
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