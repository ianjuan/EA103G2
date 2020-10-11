<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!DOCTYPE html>
<html>

<head>
<title></title>

<div id='spans'>
	<span>
		<p>
			請輸入文字：<input type="text" id="serch">
			<button name="btn" onclick=" geo_coder()">點我查詢<i class="fas fa-home"></i>

			</button>
		</p>
	</span><br> <input type="checkbox" id="POI" /> <label for="POI">開關周邊資訊</label>
	<div>
		<input type="range" min="5000" max="30000" step="12500" id="ranges">
	</div>
</div>
<div class="row map-row">
	<div class="col-6">
		<div id="map"></div>
	</div>

	<div class="col-6 side">

		<div id="sidebar">
			<div class="row house-row">
				<div class="col-4">
					<img class="house-img"
						src="https://mdbootstrap.com/img/Photos/Others/images/15.jpg">
				</div>
				<div class="col-8">
					<div class="col">
						<h3>龍山寺捷運站1分鐘(公寓2樓)全新裝潢</h3>
					</div>
					<div class="col">
						<p class="price">$15,000 元/月</p>
						<p class="card-text">獨立套房 | 8坪 | 樓層：2/3</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/c8d34ce05b.js" crossorigin="anonymous"></script>
</head>
<style type="text/css" media="screen">
html, body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}

.house-img {
	width: 100%;
	height: 100%;
}

.house-row {
	margin: 20px -15px;
}

.map-row {
	width: 100%;
	height: 600px;
}

#map {
	height: 100%;
	width: 100%;
	float: left;
}

#sidebar {
	width: 600px;
	height: 50%;
	float: left;
}

#sidebar::after {
	clear: left
}

#spans {
	width: 100%;
	height: 100px;
}

#spans div {
	display: inline-block;
}
</style>

<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<%@ page import="com.houserch.model.*"%>

	<script type="text/javascript">
    		<%HousearchService ser = new HousearchService();
			String list = ser.getAll();
			pageContext.setAttribute("list", list);//KEY，VALUE%>

        //目標:以最小化使用API為前提，成功執行應有功能。
        //具有搜索功能的MAP 只要搜索過的地址都會有標籤戳記V1V2
        //使用時記得將底下的YOUR_API_KEY更改為自己的KEY
        //可開關周邊資訊V3
        //搜索後會放大到一定大小V3
        //可用拉桿搜索指定範圍(目前還需按搜索才能更新顯示標籤)V4
        //拉桿不必再搜索才更新標籤V5
        //優化API使用次數V5,V6
        //優化程式碼 V7
        //全JUQERY化V8
        var map, geocoder, lat, lng, locationForNow,firsttime,obj,lastobj;
        var address = '';
        var POIControl = true;
        var firstTime=true;
        var distance = 0;
        var range = 10000; //初始化搜索範圍
        var lastround= 10000;
		
         obj= JSON.parse('${list}');//JSON轉JS格式
       function clear(){
          $("#sidebar").remove();
        　　	var div = document.createElement("div");
            div.setAttribute("id","sidebar");
            $(".side").append(div);
            $.each(lastobj, function(key, value) {
            	console.log(value.marker+"三小");
            	if(value.marker!=undefined){
                value.marker.setMap(null);}
            });
        }
 
        
        function initMap() {
            geocoder = new google.maps.Geocoder();
            map = new google.maps.Map(document.getElementById('map'), { //產生地圖
                center: { lat: 25.04, lng: 121.512 }, //經緯度初始化
                zoom: 17,
                mapTypeControl: false
            });
            loading();
            };

        function restart(){//進階篩選才需要
        	$.ajax({//存入資料庫階段
				  url:"/GMapServlet",
			 	  type:"GET",
			 	  data:{action:"search",
			 		  data:$("#serch").val()
			 		   //JSON.stringify({})
			 	  },
			 	  success:function(data){//以上成功才執行
			 		  console.log("data="+data);
			 	  		lastobj=obj;
// 			 		  if(data.length>50){
			 			 obj=JSON.parse(data);
			 			
			             loading();
			 		  	console.log("res棒");}
			 	  ,
			 	  error:function(data)
			 	  {
			 		  console.log("真的不棒")
			 	  }			  
			  })
        }
        function loading(){
//         	if(firsttime!=undefined){
	$.each(obj, function(key, value) {
			 				console.log("value"+value.HOS_ADD);
			 			});
	 		  clear();
// 	 		}
//         	firsttime=1;
	 		lastround=range;
        	console.log("開始創建DIVbyLoading");

         $.each(obj, function(key, value) {
		　　 if($("[id='"+key+"']").length==1){
			 $("[id='"+key+"']").remove();
            }
		 
		var img=["https://i.imgur.com/GF6tofw.jpg","https://i.imgur.com/vkocZx3.jpg","https://i.imgur.com/X1t5zFm.jpg","https://i.imgur.com/6DSdsqL.jpg","https://i.imgur.com/w6BlUBN.jpg"]
		var imgnum=Math.floor(Math.random()*img.length);
		console.log("開始創建"+value.HOS_NO);
        distance = space(lat, lng,value.HOS_LAT,value.HOS_LNG);
        console.log(distance+"VS"+range);
        if(distance<range||firstTime){
        	console.log("有近來");
		$("#sidebar").append("<div class='row house-row' id='"+value.HOS_NO.trim()+"'><div class='col-4'>"+
				"<img class='house-img' src='"+img[imgnum]+"'>"+
				"</div><div class='col-8'><div class='col'><h3>"+value.HOS_NAME+"</h3>"+		
				"</div><div class='col'><p class='price'>$"+value.HOS_DEP+"元/月</p>"+
				"<p class='card-text'>"+value.HOS_ADD+"</p></div></div></div>"		
		)
		value.marker = new google.maps.Marker({ //插上座標
            map: map,
            position: {
                lat:value.HOS_LAT,
                lng:value.HOS_LNG
            },
            markerid:key
            
       	   });
		console.log("value.marker.position.lat()="+value.marker.position.lat());

        
        var content =  "<img src='https://hp2.591.com.tw/house/active/2014/12/24/141937802675266604_765x517.water3.jpg' alt='Denim Jeans' style='width: 100%'>" +
        "<h3>"+value.HOS_NAME+"</h3>"+
        "<p class='price'>$"+value.HOS_DEP+" 元/月</p>" +
        "<p>"+value.HOS_ADD+"</p>" +
        "<p><button>查看詳情</button></p>" +
        "</div>";
    attachSecretMessage(value.marker, content);}
        
   
    });       
        }

        function geo_coder() { //點擊查詢輸入框地點
            if ($("#serch").val() != address) {
            	console.log('跑到轉換');
                address = $("#serch").val();
                geocoder.geocode({ 'address': address }, function(results, status) { //地址轉換經緯度 results取得該地區所有資訊 status回傳成功與否 以'OK'表示
                    if (status == 'OK') {
                        locationForNow = results[0].geometry.location;
                        map.setCenter(results[0].geometry.location);
					
                        lat = results[0].geometry.location.lat();
                        lng = results[0].geometry.location.lng();
                    	restart();
                    } else {
                        console.log(status);
                    }
                });            	
            } else {
            	restart();
            }

        }

        function flag() {
        	if(lastround<range){
        		restart();
			}
        	else{
        		console.log('跑到拔座標');
        	  map.setCenter(locationForNow);
               $.each(obj, function(key, value){
                	console.log("輸出"+key+value.HOS_NAME);
                distance = space(lat, lng,value.marker.position.lat(),value.marker.position.lng());
            	var tryita="[id='"+value.HOS_NO+"']";    
            if (distance > range) {
            	console.log(value.HOS_NAME+"拔走了");
                	$(tryita).remove();
                    value.marker.setMap(null);
                }
            lastround=range;
            });};
        };

	
        
        $(function() {
            $("#POI").click(function() {
                if (POIControl) {
                    map.setOptions({ styles: styles["hide"] });
                    POIControl = false;
                } else {
                    map.setOptions({ styles: styles["default"] });
                    POIControl = true;
                }
            });

        });
        $("#ranges").change(function() {
            range = parseInt($("#ranges").val());
           $("#radiv").innerText = "搜索範圍:方圓" +$("#ranges").val() + "公尺";
           console.log("range="+range+",last="+lastround);
            flag();
        });


        const styles = { //開關周邊
            default: [],
            hide: [{
                    featureType: "poi",
                    stylers: [{ visibility: "off" }]
                },
                {
                    featureType: "transit.station",
                    elementType: "labels.icon",
                    stylers: [{ visibility: "off" }]
                }
            ]
        };
        

        function space(lat1, lng1, lat2, lng2) { //算甲乙經緯距離

            var radLat1 = lat1 * Math.PI / 180.0;
            var radLat2 = lat2 * Math.PI / 180.0;
            var a = radLat1 - radLat2;
            var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
            var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * 6378.137;
            s = (Math.round(s * 10000) / 10000) * 1000;
            return s // 單位公尺
        }

        function attachSecretMessage(marker, content) { //訊息畫面function
            var infowindow = new google.maps.InfoWindow({
                content: content,
                size: new google.maps.Size(222, 222)
            });
            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(marker.get('map'), marker);
            });
        }
        
        
        
            </script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8&callback=initMap"></script>

</body>

</html>
</body>
</html>