function checkmoney() {
	var money = document.getElementById("lld_balance");
	if (money.value < 1000) {
		swal("您的電子錢包餘額為 : " + money.value + "元").then(function() {
			swal({
				title : "請問是否要儲值?",
				text : "上架費一次為 1000 元",
				icon : "info",
				buttons : {
					Btn : false,
					cancel : {
						text : "取消",
						visible : true
					},
					confirm : {
						text : "確認",
						visible : true
					}
				}
			}).then(function(isConfirm) {
				if (isConfirm) {
					swal("爸爸辛苦了, 等您回來!", {
						button : "確認"
					}).then(function() {
						document.pub.submit();
					});
				} else {
					return false;
				}
			})
		});
	} else {
		swal("目前電子錢包金額為" + money.value + "元").then(function() {
			swal({
				title : "是否上架房屋?",
				text : "上架費一次為 1000 元",
				icon : "info",
				buttons : {
					Btn : false,
					cancel : {
						text : "取消",
						visible : true
					},
					confirm : {
						text : "確認",
						visible : true
					}
				}
			}).then(function(isConfirm) {
				if (isConfirm) {
					swal("開始上架房屋!!", {
						button : "確認"
					}).then(function() {
						document.pub.submit();
					});
				} else {
					return false;
				}
			})
		});
	}
}

function checkmoney1() {
	var money = document.getElementById("lld_balance");
	if (money.value < 1000) {
		swal("您的電子錢包餘額為 : " + money.value + "元", {
			button : "確認"
		}).then(function() {
			swal({
				title : "請問是否要儲值?",
				text : "上架費一次為 1000 元",
				icon : "info",
				buttons : {
					Btn : false,
					cancel : {
						text : "取消",
						visible : true
					},
					confirm : {
						text : "確認",
						visible : true
					}
				}
			}).then(function(isConfirm) {
				if (isConfirm) {
					swal("爸爸辛苦了, 等您回來!", {
						button : "確認"
					}).then(function() {
						document.pub1.submit();
					});
				} else {
					return false;
				}
			})
		});
	} else {
		swal("目前電子錢包金額為" + money.value + "元", {
			button : "確認"
		}).then(function() {
			swal({
				title : "是否上架房屋?",
				text : "上架費一次為 1000 元",
				icon : "info",
				buttons : {
					Btn : false,
					cancel : {
						text : "取消",
						visible : true
					},
					confirm : {
						text : "確認",
						visible : true
					}
				}
			}).then(function(isConfirm) {
				if (isConfirm) {
					swal("開始上架房屋!!", {
						button : "確認"
					}).then(function() {
						document.pub1.submit();
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

	window.onload = function(){
		rowcount();
		
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