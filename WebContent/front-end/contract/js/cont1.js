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

$(document).ready(function(){
    $(function(){ $('#fur').click(function(){ 
    	console.log( $('#furdiv')[0].offsetTop );
        $('#cbody').animate({scrollTop:$('#furdiv')[0].offsetTop-150}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#other_show').click(function(){ 
        $('#cbody').animate({scrollTop:$('#other_con_item_show')[0].offsetTop-150}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#other_hide').click(function(){
        $('#cbody').animate({scrollTop:$('#other_con_item_hide')[0].offsetTop-150}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#other_con_item_show').click(function(){
        $('#cbody').animate({scrollTop:$('#other_con_item_hide')[0].offsetTop-150}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#fee').click(function(){
        $('#cbody').animate({scrollTop:$('#feediv')[0].offsetTop-150}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#signhref').click(function(){
        $('#cbody').animate({scrollTop:$('#signdiv')[0].offsetTop-100}, 500);});  
    }); 
});

$(document).ready(function(){
    $(function(){ $('#top').click(function(){
        $('#cbody').animate({scrollTop:$('#cbody')[0].offsetTop-150}, 500);});  
    }); 
});

function notice1(){		
	var sign = document.getElementById("sign").getAttribute("value").trim();
	console.log(sign);
	 if(sign === ""){
		 swal("別忘記要簽名哦~", "你各位簽完記得要按確認阿", "error", {button: "確認"});
	 } else {
		 swal({title:"確定要送出合約了嗎?", text:"" , icon:"info", buttons: {
		      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
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

function notice2(){
	swal({title:"確定要重新填寫嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
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

function showdiv(){
	document.getElementById("other_con_item").style.display = "block";
	document.getElementById("other_con_item_show").style.display = "none";
	document.getElementById("other_con_item_hide").style.display = "block";
	document.getElementById("other_show").style.display = "none";
	document.getElementById("other_hide").style.display = "block";
}

function hidediv(){
	document.getElementById("other_con_item").style.display = "none";
	document.getElementById("other_con_item_show").style.display = "block";
	document.getElementById("other_con_item_hide").style.display = "none";
	document.getElementById("other_show").style.display = "block";
	document.getElementById("other_hide").style.display = "none";
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
	  var color = document.getElementById("color");
	  var size = document.getElementById("size");
	  ctx.strokeStyle = "#1543F6";
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
	  
	  color.addEventListener('change', function(){
		 ctx.strokeStyle = color.value;
		 color.setAttribute("value", color.value);
	  });
	  
	  size.addEventListener('change', function(){
		 ctx.lineWidth = parseInt(size.value);
		 size.setAttribute("value", parseInt(size.value));
	  });
	  
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
	    sigImage.setAttribute("src", "");
	    sign.setAttribute("value", "");
	    ctx.strokeStyle = color.getAttribute("value");
		ctx.lineWidth = size.getAttribute("value");
	  }, false);
	  submitBtn.addEventListener("click", function(e) {
	    var dataUrl = canvas.toDataURL();
	    sigImage.style.display = "block";
	    sigImage.setAttribute("src", dataUrl);
	    sign.setAttribute("value", dataUrl);
	  }, false);

	})();