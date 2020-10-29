	var notifyHost = window.location.host;
	var notifyPath = window.location.pathname;
	var notifyWebCtx = notifyPath.substring(0, notifyPath.indexOf('/', 1));
	var notifyEndPointURL = "ws://" + window.location.host + notifyWebCtx + NotifyMyPoint;
	var webSocketForNotify;
	
	$(document).ready(function (){
		webSocketForNotify = new WebSocket(notifyEndPointURL);
		
		webSocketForNotify.onopen = function(event){
			console.log("Connect Success!");
		}
			
		webSocketForNotify.onmessage = function(event) {			
			var jsonObj = JSON.parse(event.data);
			var dateForNow=new Date().getTime();
			console.log(josnObj);
			console.log(event);
            Swal.fire(
            {
                position: "top-end",
                type: "success",
                title: "event.data",
                showConfirmButton: false,
                timer: 1000
            });					
		}
	});				
	
	
	window.unonload=function() {	
		webSocketForNotify.onclose = function(event) {
			webSocket.close();
		}
	}	