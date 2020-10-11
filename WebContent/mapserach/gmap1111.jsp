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
			<button name="btn" onclick=" geo_coder()">點我查詢</button>
		</p>
	</span><br> <input type="checkbox" id="POI" /> <label for="POI">開關周邊資訊</label>
	<div>
		<input type="range" min="100" max="50000" step="20000" id="ranges">
	</div>
</div>
<div class="row map-row">
	<div class="col-6">
		<div id="map"></div>
	</div>

	<div class="col-6">

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
        var map, geocoder, lat, lng, locationForNow, marker;
        var address = '';
        var mapArraylat = []; //資料庫經度
        var mapArraylng = []; //資料庫緯度
        var Address=[];//資料庫地址
        var HouseName=[];//資料庫房名
        var price=[];//資料庫價錢
        var houseno=[];
        var showAdd=[];
        var showHouseName=[];
        var showprice=[];
        var showhouseno=[];
        var POIControl = true;
        var distance = 0;
        var showalat = []; //符合篩選條件的地點經度
        var showalng = []; //符合篩選條件的地點緯度
        var range = 5000; //初始化搜索範圍
        var markers = [];
        var locationForNow;
        var list;
        obj= JSON.parse('${list}');//JSON轉JS格式

       function clear(){
            mapArraylat.length = 0;
            mapArraylng.length = 0;
            Address.length = 0;
            HouseName.length = 0;
            price.length = 0;
            showalat.length = 0;
            showalng.length = 0;
            showAdd.length = 0;
            showHouseName.length = 0;
            showprice.length = 0;
            showhouseno.length=0;
        	$("#sidebar").remove();
        　　	var div = document.createElement("div");
            div.setAttribute("id","sidebar");
            div.after($("#map"));
        }
    function reshow(){
    	$("#sidebar").remove();
    　　	var div = document.createElement("div");
        div.setAttribute("id","sidebar");
        div.after($("#map"));
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
				  url:"/EA103G2/mapServlet",
			 	  type:"GET",
			 	  data:{action:"search",
			 		  data:$("#serch").val()
			 		   //JSON.stringify({})
			 	  },
			 	  success:function(data){//以上成功才執行
			 		  console.log("data="+data);
			 		  if(data.length>50){
			 			 list=JSON.parse(data);
					  }
			 		  clear();
			 		  loading();
			 		  	console.log("res棒");
			 	  },
			 	  error:function(data)
			 	  {
			 		  console.log("真的不棒")
			 	  }			  
			  })
        }
        function loading(){
        	
        	console.log("開始創建DIVbyLoading");

        	 
         
        	 console.log(obj);  
         $.each(obj, function(key, value) {
		var theid=key;
		
		　　 if($("[id='"+theid+"']").length==1){
			 $("[id='"+theid+"']").remove();
            }
		var img=["https://i.imgur.com/GF6tofw.jpg","https://i.imgur.com/vkocZx3.jpg","https://i.imgur.com/X1t5zFm.jpg","https://i.imgur.com/6DSdsqL.jpg","https://i.imgur.com/w6BlUBN.jpg"]
		var imgnum=Math.floor(Math.random()*img.length);
		$("#sidebar").append("<div class='row house-row' id='"+value.HOS_NO.trim()+"'><div class='col-4'>"+
				"<img class='house-img' src='"+img[imgnum]+"'>"+
				"</div><div class='col-8'><div class='col'><h3>"+value.HOS_NAME+"</h3>"+		
				"</div><div class='col'><p class='price'>$"+value.HOS_DEP+"元/月</p>"+
				"<p class='card-text'>"+value.HOS_ADD+"</p></div></div></div>"		
		)

// 		HouseName.push(value.HOS_NAME);
// 		Address.push(value.HOS_ADD);
// 		price.push(value.HOS_DEP);
// 		houseno.push(value.HOS_NO.trim());
//     	mapArraylng.push(value.HOS_LNG);
//     	mapArraylat.push(value.HOS_LAT);
		value.marker = new google.maps.Marker({ //插上座標
            map: map,
            position: {
                lat:value.HOS_LAT,
                lng:value.HOS_LNG
            },
            markerid:key
       	   });
        /*marker = new google.maps.Marker({ //插上座標
             map: map,
             position: {
                 lat:value.HOS_LAT,
                 lng:value.HOS_LNG
             },
             markerid:key
        	   });
        markers.push(marker);*/
//         console.log(marker.get("markerid"));
    });
         
        console.log(obj);
        }

        
      

        function geo_coder() { //點擊查詢輸入框地點
            console.log("陣列長度" + markers.length);
            if ($("#serch").val() != address) {
            	console.log('跑到轉換');
                address = $("#serch").val();
                geocoder.geocode({ 'address': address }, function(results, status) { //地址轉換經緯度 results取得該地區所有資訊 status回傳成功與否 以'OK'表示
                    if (status == 'OK') {
                        locationForNow = results[0].geometry.location;
                        lat = results[0].geometry.location.lat();
                        lng = results[0].geometry.location.lng();
//                         mapArraylat.push(lat); //緯度儲存點
//                         mapArraylng.push(lng); //經度儲存點
                        flag();
                  
                    } else {
                        console.log(status);
                    }
                });            	
            } else {
                flag();
            }

        }

        function flag() {
            console.log('跑到拔座標');

//             for (var i = obj.length - 1; i >= 0; i--) { //拔除條件外座標
					 showalat.length = 0;
                     showalng.length = 0;
                     showhouseno.length=0;
                     map.setCenter(locationForNow);
                $.each(obj, function(key, value) {
                
                distance = space(lat, lng, value.marker.position.lat(), value.marker.position.lng());
            	var tryita="[id='"+value.HOS_NO+"']";    
            if (distance > range) {
                	$(tryita).remove();
                	console.log("我來拔你這小王八蛋囉"+tryita);
                    value.marker.setMap(null);
//                     markers.splice(i, 1);
                }
            else{
            	console.log("跑到這囉"+value.marker.position.lat());
            	showalat.push(value.marker.position.lat());
                showalng.push(value.marker.position.lng());
                showhouseno.push(value.HOS_NO)
            	}
            });
//             }
//              showalat.length = 0;
//              showalng.length = 0;
//             showAdd.length = 0;
//             showHouseName.length = 0;
//             showprice.length = 0;
//             showhouseno.length=0;
//             map.setCenter(locationForNow); //設定MAP中心點為(方法) ()裡的方法回傳經緯度
//             $.each(obj, function(key, value) {
//                 distance = space(lat, lng, value.marker.position.lat(), value.marker.position.lng());
//                 console.log(distance);
//                 if (distance < range) {
//                 	 showalat.push(value.marker.position.lat());
//                      showalng.push(value.marker.position.lng());	
// //                 	showalat.push(mapArraylat[i]);
// //                     showalng.push(mapArraylng[i]);	
//                                       }
//             }
		
            for (var i = 0; i < showalat.length; i++) { //將範圍內的座標插入 
                markerMaker(i);
            }
        };

        function markerMaker(e) {
			
//     		for (var i = 0; i < markers.length; i++) {
//                 if (markers[i].position.lat() == showalat[e] && markers[i].position.lng() == showalng[e]) {
//                     return;
//                 }
//             }
//     	      showAdd.push(Address[e]);
//               showHouseName.push(HouseName[e]);
//               showprice.push(price[e]);
//               showhouseno.push(houseno[e]);
            marker = new google.maps.Marker({ //插上座標
                map: map,
                position: {
                    lat: showalat[e],
                    lng: showalng[e]
                },
            markerid:showhouseno[e]
            });
            console.log(showhouseno[e]+"新插上去的熱騰騰")
            if (map.getZoom() < 13) {
                map.setZoom(17);
            }

//             markers.push(marker);
           //marker重覆檢驗與插旗結束 以下插旗 
      
            if($("[id='"+showhouseno[e]+"']").length===0){
            	var img=["https://i.imgur.com/GF6tofw.jpg","https://i.imgur.com/vkocZx3.jpg","https://i.imgur.com/X1t5zFm.jpg","https://i.imgur.com/6DSdsqL.jpg","https://i.imgur.com/w6BlUBN.jpg"]
        		var imgnum=Math.floor(Math.random()*img.length);
            	var name=obj[showhouseno[e]].HOS_NAME;
            	console.log(name);
            	$("#sidebar").append("<div class='row house-row' id='"+showhouseno[e]+"'><div class='col-4'>"+
        				"<img class='house-img' src='"+img[imgnum]+"'>"+
        				"</div><div class='col-8'><div class='col'><h3>"+name+"</h3>"+		
        				"</div><div class='col'><p class='price'>$"+obj[showhouseno[e]].HOS_DEP+"元/月</p>"+
        				"<p class='card-text'>"+obj[showhouseno[e]].HOS_ADD+"</p></div></div></div>"		
        		)
            	
    			console.log("進到DIV創建bymarkerMaker");
//                 if(document.getElementById(showhouseno[e]))
//                 var div = document.createElement("div");
//     			var p =document.createElement("p");        　
//     			var sidebar=document.getElementById("sidebar");　
//                 div.setAttribute("id",showhouseno[e]);
//                 div.setAttribute("class", "houselist");
//         		p.setAttribute("id","info");
//         		p.innerHTML="房名:"+showHouseName[e]+" 租金="+showprice[e]+" 地址:"+showAdd[e];	
//         		div.appendChild(p);
//         		sidebar.appendChild(div);
    		}
        }
        
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
            range = $("#ranges").val();
           $("#radiv").innerText = "搜索範圍:方圓" +$("#ranges").val() + "公尺";
            geo_coder();
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

            </script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8&callback=initMap"></script>

</body>

</html>
</body>
</html>