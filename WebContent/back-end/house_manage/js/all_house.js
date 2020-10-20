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
      document.getElementById("count3").innerHTML = i;
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
