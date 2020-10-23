window.onload = function(){
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

function eqpmtPoint(e){
	var evaluation = document.getElementById("evaluation");
	var input = document.createElement("input");
	var hcm_eqpmt = document.getElementsByName("hcm_eqpmt");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "hcm_eqpmt");
	input.setAttribute("value", e.target.getAttribute("value"));
	evaluation.append(input);
	
	if(hcm_eqpmt.length > 0){
		for(var i=0; i<hcm_eqpmt.length-1; i++){
			hcm_eqpmt[i].remove();
		}
	}
}

function convmtPoint(e){
	var evaluation = document.getElementById("evaluation");
	var input = document.createElement("input");
	var hcm_convmt = document.getElementsByName("hcm_convmt");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "hcm_convmt");
	input.setAttribute("value", e.target.getAttribute("value"));
	evaluation.append(input);
	
	if(hcm_convmt.length > 0){
		for(var i=0; i<hcm_convmt.length-1; i++){
			hcm_convmt[i].remove();
		}
	}
}

function neiborPoint(e){
	var evaluation = document.getElementById("evaluation");
	var input = document.createElement("input");
	var hcm_neibor = document.getElementsByName("hcm_neibor");
	
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "hcm_neibor");
	input.setAttribute("value", e.target.getAttribute("value"));
	evaluation.append(input);
	
	if(hcm_neibor.length > 0){
		for(var i=0; i<hcm_neibor.length-1; i++){
			hcm_neibor[i].remove();
		}
	}
}

function checkLen(e) {
	var maxChars = 50;//最多字符数
	if (e.value.length > maxChars)
		e.value = e.value.substring(0,maxChars);
	document.getElementById("evacount").innerHTML = maxChars - e.value.length;
}

function notice(){
	 swal({title:"確定要送出評價了嗎?", text:"" , icon:"info", buttons: {
	      Btn: false, confirm: {text:"確認", visible: true}, cancel: {text:"取消", visible: true}
	    }}).then(function(isConfirm){
		if(isConfirm){
			swal("成功送出!!", "", "success", {button: "確認"}).then(function(){
				document.evaluation.submit();
			});
		} else {
			return false;
		}	    				
	});
}
