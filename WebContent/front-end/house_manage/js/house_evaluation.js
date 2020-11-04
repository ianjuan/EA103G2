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
	document.querySelectorAll('.rating').forEach(rating => {

	    let entries = rating.querySelectorAll('li')

	    entries.forEach(function(entry, index) {

	        entry.addEventListener('click', e => {

	            let active = [...entries].filter((e, i) => i >= index && !e.classList.contains('active')),
	                inactive = [...entries].filter((e, i) => i < index && e.classList.contains('active'))

	            if(active.length) {
	                gsap.to(active.reverse(), {
	                    onStart() {
	                        this._targets.forEach(e => e.classList.add('active', 'activeColor'))
	                    },
	                    keyframes: [{
	                        '--star-scale': 0,
	                        '--star-y': 0,
	                        duration: 0
	                    }, {
	                        '--star-scale': 1,
	                        '--dot-scale': 0,
	                        duration: .9,
	                        ease: 'elastic.out(1, .7)',
	                        stagger: .03
	                    }]
	                })
	            }

	            if(inactive.length) {
	                gsap.to(inactive, {
	                    onStart() {
	                        this._targets.forEach(e => e.classList.remove('activeColor'))
	                    },
	                    onComplete() {
	                        this._targets.forEach(e => e.classList.remove('active'))
	                    },
	                    '--star-before-r': -20,
	                    '--star-before-y': -8,
	                    '--star-after-r': 20,
	                    '--star-after-y': 8,
	                    duration: .65,
	                    clearProps: true
	                })
	                gsap.to(inactive, {
	                    '--star-o': 0,
	                    '--star-blur': 10,
	                    '--star-y': 48,
	                    '--dot-scale': .8,
	                    duration: .5,
	                    delay: .15,
	                    clearProps: true
	                })
	            }

	        })

	    })

	})
}

function tcmcleanPoint(e){
	var input = document.createElement("input");
	var tcm_clean = document.getElementsByName("tcm_clean");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "tcm_clean");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(tcm_clean.length > 0){
		for(var i=0; i<tcm_clean.length-1; i++){
			tcm_clean[i].remove();
		}
	}
}

function tcmcommutPoint(e){
	var input = document.createElement("input");
	var tcm_commut = document.getElementsByName("tcm_commut");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "tcm_commut");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(tcm_commut.length > 0){
		for(var i=0; i<tcm_commut.length-1; i++){
			tcm_commut[i].remove();
		}
	}
}

function tcmsatisfyPoint(e){
	var input = document.createElement("input");
	var tcm_satisfy = document.getElementsByName("tcm_satisfy");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "tcm_satisfy");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(tcm_satisfy.length > 0){
		for(var i=0; i<tcm_satisfy.length-1; i++){
			tcm_satisfy[i].remove();
		}
	}
}

function lcmcleanPoint(e){
	var input = document.createElement("input");
	var lcm_clean = document.getElementsByName("lcm_clean");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "lcm_clean");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(lcm_clean.length > 0){
		for(var i=0; i<lcm_clean.length-1; i++){
			lcm_clean[i].remove();
		}
	}
}

function lcmcommutPoint(e){
	var input = document.createElement("input");
	var lcm_commut = document.getElementsByName("lcm_commut");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "lcm_commut");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(lcm_commut.length > 0){
		for(var i=0; i<lcm_commut.length-1; i++){
			lcm_commut[i].remove();
		}
	}
}

function lcmsatisfyPoint(e){
	var input = document.createElement("input");
	var lcm_satisfy = document.getElementsByName("lcm_satisfy");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "lcm_satisfy");
	
	if(e.target.getAttribute("value") > 5){
		input.setAttribute("value", e.target.getAttribute("value")-5);
		e.target.parentElement.parentElement.parentElement.append(input);
	} else{
		input.setAttribute("value", e.target.getAttribute("value"));
		e.target.parentElement.parentElement.append(input);
	}
		
	if(lcm_satisfy.length > 0){
		for(var i=0; i<lcm_satisfy.length-1; i++){
			lcm_satisfy[i].remove();
		}
	}
}


function checkLen(e) {
	var maxChars = 50;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
	document.getElementById("evacount").innerHTML = maxChars - e.value.length;
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
	 swal({title:"確定要送出帳單了嗎?", text:"" , icon:"info", buttons: {
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
