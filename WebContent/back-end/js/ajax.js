 $(function(){
      $(".collapse-item").click(function() {
    	  var ajax_select = this.innerText;
    	  var ajax_url="";
    	  if(ajax_select=="全體員工"){
    		  ajax_url = "listAllEmp.jsp";
    	   }
    	  else if(ajax_select=="查詢員工"){
    		  ajax_url = "select_page.jsp";
    	  }
    	  else if(ajax_select=="新增員工"){
    		  ajax_url = "addEmp.jsp";
    	  }

        $.ajax({
        	
          type: "GET",
          url: ajax_url,
          dataType: "html",
          success: function(data) {
        	  if(ajax_url!=""){
            $("#ajax_result").html(data);
            }},
          error: function(xhr) {
            alert("Ajax發生錯誤:"+xhr.status);
            }     
          });
        });
      });