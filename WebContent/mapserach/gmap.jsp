<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
        <!DOCTYPE html>
        <html>

        <head>
            <title></title>
            <div id="map"></div><div id="sidebar"></div>
            <form>請輸入文字：<input type="text" id="serch"></form>
            <tr><button name="btn" onclick=" geo_coder()">點我查詢</button></tr>
            <br>
            <input type="checkbox" id="POI" />
            <label for="POI">開關周邊資訊</label>
            <div><input type="range" min="100" max="50000" step="20000" id="ranges">
                <div id=radiv></div>
            </div>
        </head>
        <style type="text/css" media="screen">
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        #map {
            height: 50%;

            width: 50%;
        }
        
        #sidebar{
        	width:50%;
        	height:50%;
        }
        #sidebar::after{
        clear:left}
        </style>

        <body>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<%@ page import="com.booking.model.*"%>

            <script type="text/javascript">
<%--     		<% --%>
//     		GmapService ser= new GmapService();
//     		String list=ser.getMapfromSearchKey("桃園");
//     		pageContext.setAttribute("list", list);//KEY，VALUE
<%--     		%> --%>

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
        
        var list= JSON.parse('${list}');//JSON轉JS格式

       
    
        function initMap() {
            geocoder = new google.maps.Geocoder();
            map = new google.maps.Map(document.getElementById('map'), { //產生地圖
                center: { lat: 25.04, lng: 121.512 }, //經緯度初始化
                zoom: 17,
                mapTypeControl: false
            });
            list.forEach(function(catc){
            　　		var div = document.createElement("div");
				var p =document.createElement("p");        　
				var sidebar=document.getElementById("sidebar");　　　
	            div.setAttribute("id",catc.HOS_NO.trim());
	            div.setAttribute("class", "houselist");
        		p.setAttribute("id","info");
        		p.innerHTML="房名:"+catc.HOS_NAME+" 租金="+catc.HOS_DEP+" 地址:"+catc.HOS_ADD;	
        		div.appendChild(p);
        		sidebar.appendChild(div);
        		HouseName.push(catc.HOS_NAME);
        		Address.push(catc.HOS_ADD);
        		price.push(catc.HOS_DEP);
        		houseno.push(catc.HOS_NO.trim());
            	mapArraylng.push(catc.HOS_LNG);
            	mapArraylat.push(catc.HOS_LAT);
                marker = new google.maps.Marker({ //插上座標
                     map: map,
                     position: {
                         lat:catc.HOS_LAT,
                         lng:catc.HOS_LNG
                     },
                     markerid:catc.HOS_NO.trim()
                	   });
                markers.push(marker);
                console.log(marker.get("markerid"));
            });
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
            range = $("#ranges").val();
           $("#radiv").innerHTML = "搜索範圍:方圓" +$("#ranges").val() + "公尺";
            geo_coder();
        });


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
            console.log('跑到拔座標' + markers.length);
            for (var i = markers.length - 1; i >= 0; i--) { //拔除條件外座標
                distance = space(lat, lng, markers[i].position.lat(), markers[i].position.lng());
                if (distance > range) {
                	console.log("'#"+markers[i].get("markerid")+"'");
                	$("[id='"+markers[i].get("markerid")+"']").remove();
                    markers[i].setMap(null);
                    markers.splice(i, 1);
                }
            }
            showalat.length = 0;
            showalng.length = 0;
            showAdd.length = 0;
            showHouseName.length = 0;
            showprice.length = 0;
            showhouseno.length=0;
            map.setCenter(locationForNow); //設定MAP中心點為(方法) ()裡的方法回傳經緯度
			 for (var i = 0; i < mapArraylng.length; i++) { //範圍內座標過此關
                distance = space(lat, lng, mapArraylat[i], mapArraylng[i]);
                console.log(distance);
                if (distance < range) {
                    showalat.push(mapArraylat[i]);
                    showalng.push(mapArraylng[i]);	
                    showAdd.push(Address[i]);
                    showHouseName.push(HouseName[i]);
                    showprice.push(price[i]);
                    showhouseno.push(houseno[i]);
                     }
            }

            for (var i = 0; i < showalat.length; i++) { //將範圍內的座標插入 
                markerMaker(i);
            }
        }

        function markerMaker(e) {
		if($("[id='"+showhouseno[e]+"']").length===0){
//             if(document.getElementById(showhouseno[e]))
            var div = document.createElement("div");
			var p =document.createElement("p");        　
			var sidebar=document.getElementById("sidebar");　
            div.setAttribute("id",showhouseno[e]);
            div.setAttribute("class", "houselist");
    		p.setAttribute("id","info");
    		p.innerHTML="房名:"+showHouseName[e]+" 租金="+showprice[e]+" 地址:"+showAdd[e];	
    		div.appendChild(p);
    		sidebar.appendChild(div);
		}
    		for (var i = 0; i < markers.length; i++) {
                if (markers[i].position.lat() == showalat[e] && markers[i].position.lng() == showalng[e]) {
                    return;
                }
            }
            marker = new google.maps.Marker({ //插上座標
                map: map,
                position: {
                    lat: showalat[e],
                    lng: showalng[e]
                },
            markerid:showhouseno[e]

            });
            if (map.getZoom() < 13) {
                map.setZoom(17);
            }

            markers.push(marker);
        }
       

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
                        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8&callback=initMap"></script>

        </body>

        </html>
</body>
</html>