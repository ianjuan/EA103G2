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
            <form>�п�J��r�G<input type="text" id="serch"></form>
            <tr><button name="btn" onclick=" geo_coder()">�I�ڬd��</button></tr>
            <br>
            <input type="checkbox" id="POI" />
            <label for="POI">�}���P���T</label>
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
//     		String list=ser.getMapfromSearchKey("���");
//     		pageContext.setAttribute("list", list);//KEY�AVALUE
<%--     		%> --%>

        //�ؼ�:�H�̤p�ƨϥ�API���e���A���\���������\��C
        //�㦳�j���\�઺MAP �u�n�j���L���a�}���|�������W�OV1V2
        //�ϥήɰO�o�N���U��YOUR_API_KEY��אּ�ۤv��KEY
        //�i�}���P���TV3
        //�j����|��j��@�w�j�pV3
        //�i�ΩԱ�j�����w�d��(�ثe�ٻݫ��j���~���s��ܼ���)V4
        //�Ա줣���A�j���~��s����V5
        //�u��API�ϥΦ���V5,V6
        //�u�Ƶ{���X V7
        //��JUQERY��V8
        var map, geocoder, lat, lng, locationForNow, marker;
        var address = '';
        var mapArraylat = []; //��Ʈw�g��
        var mapArraylng = []; //��Ʈw�n��
        
        var Address=[];//��Ʈw�a�}
        var HouseName=[];//��Ʈw�ЦW
        var price=[];//��Ʈw����
        var houseno=[];
        var showAdd=[];
        var showHouseName=[];
        var showprice=[];
        var showhouseno=[];
        var POIControl = true;
        var distance = 0;
        var showalat = []; //�ŦX�z����󪺦a�I�g��
        var showalng = []; //�ŦX�z����󪺦a�I�n��
        var range = 5000; //��l�Ʒj���d��
        var markers = [];
        var locationForNow;
        
        var list= JSON.parse('${list}');//JSON��JS�榡

       
    
        function initMap() {
            geocoder = new google.maps.Geocoder();
            map = new google.maps.Map(document.getElementById('map'), { //���ͦa��
                center: { lat: 25.04, lng: 121.512 }, //�g�n�ת�l��
                zoom: 17,
                mapTypeControl: false
            });
            list.forEach(function(catc){
            �@�@		var div = document.createElement("div");
				var p =document.createElement("p");        �@
				var sidebar=document.getElementById("sidebar");�@�@�@
	            div.setAttribute("id",catc.HOS_NO.trim());
	            div.setAttribute("class", "houselist");
        		p.setAttribute("id","info");
        		p.innerHTML="�ЦW:"+catc.HOS_NAME+" ����="+catc.HOS_DEP+" �a�}:"+catc.HOS_ADD;	
        		div.appendChild(p);
        		sidebar.appendChild(div);
        		HouseName.push(catc.HOS_NAME);
        		Address.push(catc.HOS_ADD);
        		price.push(catc.HOS_DEP);
        		houseno.push(catc.HOS_NO.trim());
            	mapArraylng.push(catc.HOS_LNG);
            	mapArraylat.push(catc.HOS_LAT);
                marker = new google.maps.Marker({ //���W�y��
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
           $("#radiv").innerHTML = "�j���d��:���" +$("#ranges").val() + "����";
            geo_coder();
        });


        function geo_coder() { //�I���d�߿�J�ئa�I

            console.log("�}�C����" + markers.length);
            if ($("#serch").val() != address) {
                console.log('�]���ഫ');
                address = $("#serch").val();
                geocoder.geocode({ 'address': address }, function(results, status) { //�a�}�ഫ�g�n�� results���o�Ӧa�ϩҦ���T status�^�Ǧ��\�P�_ �H'OK'���
                    if (status == 'OK') {
                        locationForNow = results[0].geometry.location;
                        lat = results[0].geometry.location.lat();
                        lng = results[0].geometry.location.lng();
//                         mapArraylat.push(lat); //�n���x�s�I
//                         mapArraylng.push(lng); //�g���x�s�I
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
            console.log('�]��ޮy��' + markers.length);
            for (var i = markers.length - 1; i >= 0; i--) { //�ް�����~�y��
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
            map.setCenter(locationForNow); //�]�wMAP�����I��(��k) ()�̪���k�^�Ǹg�n��
			 for (var i = 0; i < mapArraylng.length; i++) { //�d�򤺮y�йL����
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

            for (var i = 0; i < showalat.length; i++) { //�N�d�򤺪��y�д��J 
                markerMaker(i);
            }
        }

        function markerMaker(e) {
		if($("[id='"+showhouseno[e]+"']").length===0){
//             if(document.getElementById(showhouseno[e]))
            var div = document.createElement("div");
			var p =document.createElement("p");        �@
			var sidebar=document.getElementById("sidebar");�@
            div.setAttribute("id",showhouseno[e]);
            div.setAttribute("class", "houselist");
    		p.setAttribute("id","info");
    		p.innerHTML="�ЦW:"+showHouseName[e]+" ����="+showprice[e]+" �a�}:"+showAdd[e];	
    		div.appendChild(p);
    		sidebar.appendChild(div);
		}
    		for (var i = 0; i < markers.length; i++) {
                if (markers[i].position.lat() == showalat[e] && markers[i].position.lng() == showalng[e]) {
                    return;
                }
            }
            marker = new google.maps.Marker({ //���W�y��
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
       

        const styles = { //�}���P��
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

        function space(lat1, lng1, lat2, lng2) { //��ҤA�g�n�Z��

            var radLat1 = lat1 * Math.PI / 180.0;
            var radLat2 = lat2 * Math.PI / 180.0;
            var a = radLat1 - radLat2;
            var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
            var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * 6378.137;
            s = (Math.round(s * 10000) / 10000) * 1000;
            return s // ��줽��
        }

            </script>
                        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8&callback=initMap"></script>

        </body>

        </html>
</body>
</html>