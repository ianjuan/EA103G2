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
	  	 <div><jsp:include page="/front-end/navbar.jsp"/> </div>
  
            <div class="col-12 body">
                <!-- 縣市 -->
                <div class="container sharchbox">

                    <div class="row ">
                    <div class="col-xl-3 col-lg-4">
                        <div id="city">
                            <input type="text" id="citych" value="選擇城市" autocomplete="off"><br>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="台北市"> 台北市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="新北市"> 新北市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="基隆市"> 基隆市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="桃園市"> 桃園市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="新竹縣"> 新竹縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="新竹市"> 新竹市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="苗栗縣"> 苗栗縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="臺中市"> 臺中市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="南投縣"> 南投縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="彰化縣"> 彰化縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="雲林縣"> 雲林縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="嘉義縣"> 嘉義縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="嘉義市"> 嘉義市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="臺南市"> 臺南市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel" > <input type="radio" name="city" class="cityinput" value="高雄市"> 高雄市
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="屏東縣"> 屏東縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="宜蘭縣"> 宜蘭縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="花蓮縣"> 花蓮縣
                                </label>
                            </div>
                            <div class="allcity">
                                <label id="citylabel"> <input type="radio" name="city" class="cityinput" value="臺東縣"> 臺東縣
                                </label>
                            </div>
                        </div>
                        <div  id="town">
                            <input type="text" id="townch" value="選擇鄉鎮" autocomplete="off"><br>
                        </div>
                        </div>
                        <div class="col-xl-9 col-lg-8" id="searchbar">
                            <form class="form-inline mr-auto">
                                <input class="form-control mr-sm-2" id="searchbox"type="text" placeholder="Search" aria-label="Search">
                                <button class="btn btn-unique btn-rounded btn-sm my-0" id="btn-search"type="button">Search</button>
                            </form>
                        </div>
                        <!-- 縣市 -->
                    </div>
                   
                </div>
                <div class="container" id="btn-choice">
                <div class="row btn-cho">
                    <div class="col-12 col-lg-4 col-xl-6 moneyside">
                        <div id="money">
                            <div class="btn-group" role="group">
                            <button type="button" class="btn btn-secondary moneybtn" id="btn-left1" value="">不限</button>
                            <button type="button" class="btn btn-secondary moneybtn" id="btn-left2" value="<5000">5000以下</button>
                                <button type="button" class="btn btn-secondary moneybtn" id="btn-left3" value="BETWEEN 5000 AND 10000">5000-10000</button>
                                <button type="button" class="btn btn-secondary moneybtn" id="btn-left4" value="BETWEEN 10000 AND 15000">10000-15000</button>
                                <button type="button" class="btn btn-secondary moneybtn" id="btn-left5" value=">15000">15000以上</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4 offset-lg-2 offset-xl-0 col-xl-6 houseside">
                        <div id="housestyle">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-secondary housebtn" value="">不限</button>
                                <button type="button" class="btn btn-secondary housebtn" value="整層住家">整層住家</button>
                                <button type="button" class="btn btn-secondary housebtn" value="獨立套房">獨立套房</button>
                                <button type="button" class="btn btn-secondary housebtn" value="分租套房">分租套房</button>
                                <button type="button" class="btn btn-secondary housebtn" value="雅房">雅房</button>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="container" id="sort">
                <div class="row turn">
                    <div class="col-12 col-md-6"><div class="someoption"><label class="item_name">可開伙&nbsp;&nbsp;
                            <div class="onoffswitch">
                                <input type="checkbox" name="furniture" class="onoffswitch-checkbox" id="coke" tabindex="0"> <label class="onoffswitch-label" for="coke"></label>
                            </div>
                        </label> <label class="item_name"><i class="fas fa-paw"></i>

可養寵&nbsp;&nbsp;
                            <div class="onoffswitch">
                                <input type="checkbox" name="furniture" class="onoffswitch-checkbox" id="pet" tabindex="0"> <label class="onoffswitch-label" for="pet"></label>
                            </div>
                        </label> <label class="item_name">可開車&nbsp;&nbsp;
                            <div class="onoffswitch">
                                <input type="checkbox" name="furniture" class="onoffswitch-checkbox" id="deriver" tabindex="0">
                                <label class="onoffswitch-label" for="deriver"></label>
                            </div>
                        </label> <label class="item_name">可新增&nbsp;&nbsp;
                            <div class="onoffswitch">
                                <input type="checkbox" name="furniture" class="onoffswitch-checkbox" id="try14" tabindex="0"> <label class="onoffswitch-label" for="try14"></label>
                            </div>
                        </label></div></div>
                    <div class="col-12 col-md-4 offset-md-2 offset-lg-2">
                        <div id="selectforwhat">
                           <select class="form-control" id="selectbox">
                                    <option value="HOS_DATE DESC" selected disabled hidden>選擇排序</option>
                                    <option value="HOS_DATE DESC">已上架時間 短->長</option>
                                    <option value="HOS_DATE">已上架時間 長->短</option>                                    
                                    <option value="HOS_RENTFEE">出租價格 低->高</option>
                                    <option value="HOS_RENTFEE DESC">出租價格 高->低</option>
                                    <option value="HOS_PNUM DESC">房屋坪數 大->小</option>
                                    <option value="HOS_PNUM">房屋坪數 小->大</option>
                                   </select>
                        </div>
                    </div>
                    </div>                </div>
                    <!-- 以上與搜索頁一致 -->
                    <div class="container map-con ">
                    <div class="row  range">
                    	<h3 id="far">目前顯示範圍 : 距離畫面中心點 3000 公尺內的房屋</h3><div ></div> <br>
                    	<input type="range" min="1000" max="10000" step="1000" value="3000" id="ranges">
                           	
                    </div>
<div class="row map-row">
	<div class="col-8">
		<div id="map"></div>
	</div>

	<div class="col-4 side">
	
		<div id="sidebar">
			
		</div>
	</div>
</div>
</div>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar.css">
	
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
	margin-top:37px;
}
.price{
margin:0px;}
#map {
	height: 100%;
	width: 100%;
	float: left;
}

#sidebar {
	width: 100%;
	height: 50%;
	float: left;
}
.side{
overflow: scroll;
	height: 600px;
padding-left: 15px;
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
.gm-style-iw-c{
max-width:300px !important;
}

 .card-text{
        margin-bottom:0px !important
        }
/*
以下搜索列表
*/
.someoption{
    width: 100%%;
    padding-top: 2.5%;
    padding-bottom:1%;
}
.map-con{
max-width:1480px;}
footer{
    bottom: 0;
    background-color: green;
    height: 100px; 
    width: 100%
}
.btn-group button{
    height: 38px;
    border-radius:20px;
}
.btn-group{
	display: flex;
}
@media (max-width:550px){

.moneybtn{
padding:0px 0px;
margin-right:14px;
}
}
.moneyside,.houseside{
    width: 50%;
    
}
@media (min-width:551px) and (max-width:767px){
#selectforwhat{
margin-bottom:8px;}

}

@media (min-width:999px){
.houseside{
border-left-color:white;
border-left-width:7px;
border-left-style:outset;

}
}
/* .moneyside{ */
/* border-right-color:white; */
/* border-right-width:7px; */
/* border-right-style:inset;} */

 @media (min-width:1200px){ 
 #money{margin-left: 55px;} 
} 
#selectforwhat{
margin-top:6px;
text-align: center;}

@media (min-width:768px) and (max-width:996px){
#selectforwhat{
margin-top:20px;
}
}
#sort{
margin-top:10px;}
#btn-choice{
margin-top:25px;
margin-bottom:7px;

}
      .head {
            /*background-color: green;*/
            height: 10%;
        }

        .body {
            /*background-color: navy;*/

        }

        .foot {
            /*background-color: orange;*/
            height: 10%;
        }

        .sharchbox {
            /*background-color: orange;*/

        }

        .btn-cho {
            background-color: #6c757d;
			border-radius:60px;
        }

        .turn {
            background-color: lightblue;
            border-radius:2.25rem;

        }

        .card {
            /*background-color: cyan;*/

        }

        /*城市*/
       @media screen and(max-width:1200px) {
       }#searchbar {
       	margin-top:6px;
        }

        .my-0 {
            background-color: pink;
            /*搜索按鈕*/
        }

        .mr-auto {
            margin-top: 33px;
        }

	.form-control{
	border-radius:2.25rem;}
	#btn-search{
	width:80px;
	border-radius:25px;}
        #town {
            /*外層DIV*/
            height: 20px;
            display: inline;
            float: left;
            float: left;
 margin-top:38px;
                        margin-left:20px;
                        width:110px;        }

        .form-inline .form-control {
            width: 70%;
        }

        #townch {
            /*搜索框*/
            text-align: center;
            width: 110px;
            height: 40px;
            border-radius: 20px;
            font-size: 20px;
            font-weight: bold;
            letter-spacing: 3px;
            font-family: Microsoft JhengHei;
            outline:none;
            
        }

        #city {
            /*外層DIV*/
            height: 20px;
            display: inline;
            float: left;
            border-radius: 50px;
            margin-top:38px;
            width:110px;
        }

        #citych {
            /*搜索框*/
            text-align: center;
            width: 110px;
            height: 40px;
            border-radius: 50px;
            font-weight: bold;
            letter-spacing: 3px;
            font-family: "黑體-繁", "微軟正黑體", sans-serif;
            font-size: 20px;
            outline:none;
        }

        .allcity {
            /*城市列表選單*/
            font-weight: bold;
            letter-spacing: 3px;
            font-family: "黑體-繁", "微軟正黑體", sans-serif;
            text-align: center;
            font-size: 20px;
            border: 2px #A67F78 ridge;
            width: 150px;
            height: 40px;
            border-radius: 5px;
            z-index: 5;
            position: relative;
            display: none;
            background-color: #E1DCD9;
        }

        .allcity:hover {
            background-color: green;
        }

        .towns {
            /*鄉鎮列表選單*/
            font-weight: bold;
            letter-spacing: 3px;
            font-family: Microsoft JhengHei;
            text-align: center;
            border: 2px #FFAC55 ridge;
            font-size: 20px;
            width: 150px;
            height: 40px;
            border-radius: 50px;
            z-index: 5;
            position: relative;
            display: none;
            background-color: #FBE1FA;
        }

        .towns:hover {
            background-color: green;
        }

        /*城市--------------------------------*/
        /*開關*/
        .onoffswitch {
            position: relative;
            width: 50px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            float: right;
        }

        .onoffswitch-checkbox {
            position: absolute;
            opacity: 0;
            pointer-events: none;
        }

        .onoffswitch-label {
            display: block;
            overflow: hidden;
            cursor: pointer;
            height: 21px;
            width: 50px;
            padding: 0;
            line-height: 21px;
            border: 2px solid #E3E3E3;
            border-radius: 21px;
            background-color: #FFFFFF;
            transition: background-color 0.3s ease-in;
            margin-bottom:0px;
        }

        .onoffswitch-label:before {
            content: "";
            display: block;
            width: 21px;
            margin: 0px;
            background: #FFFFFF;
            position: absolute;
            top: 0;
            bottom: 0;
            right: 27px;
            border: 2px solid #E3E3E3;
            border-radius: 21px;
            transition: all 0.3s ease-in 0s;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label {
            background-color: #49E845;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label, .onoffswitch-checkbox:checked+.onoffswitch-label:before {
            border-color: #49E845;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label:before {
            right: 0px;
        }
   .hos-name{
   margin-top:5px;}
</style>

<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/resource/dist/js/bootstrap.min.js"></script>
	<%@ page import="com.houserch.model.*"%>

	<script type="text/javascript">
    		<%HousearchService ser = new HousearchService();
			String list = ser.getAllGmap();
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
        var map, geocoder, lat, lng, listener,locationForNow,obj,lastobj,action;
    	var moneybtn="getall";
    	var housebtn="getall";
        var address = '';
        var POIControl = true;
        var firstTime=true;
        var infowin=false;
        var distance = 0;
        var range = 3000; //初始化搜索範圍
        var prev_infowindow=false;
         obj= JSON.parse('${list}');//JSON轉JS格式
         
       function clear(){
          $("#sidebar").remove();
        　　	var div = document.createElement("div");
            div.setAttribute("id","sidebar");
            $(".side").append(div);
            
     
            	$.each(lastobj, function(key, value) {
                	if(value.marker!=undefined){
                    value.marker.setMap(null);}
                });
            
        }
       function ajax(){
      		$.ajax({//存入資料庫階段
      			  url:"<%=request.getContextPath()%>/Housearch/GMapServlet",
      		 	  type:"GET",
      		 	  data:{action:"gmapsearch",
      		 		  data:$("#citych").val()+$("#townch").val()+$("#searchbox").val(),
      		 		  sort:$("#selectbox").val(),
      		 		  money:moneybtn,
      		 		  house:housebtn
      		 		  },
      		 	  success:function(data){//以上成功才執行
      		 		  console.log("data="+data);
      		 		action="ajax";
      		 			lastobj=obj;
   		 			 obj=JSON.parse(data);
   		             loading();
      		 		  	console.log("res棒");
      		 		  	}
      		 	  ,
      		 	  error:function(data)
      		 	  {
      		 		  console.log("真的不棒")
      		 	  }			  
      		  } )
      		
      	}
        
        function initMap() {
            geocoder = new google.maps.Geocoder();
            map = new google.maps.Map(document.getElementById('map'), { //產生地圖
                center: { lat: 25.04, lng: 121.512 }, //經緯度初始化
                zoom: 15,
                mapTypeControl: false
            });
            loading();
            };

       
        function loading(){
        	if(Object.keys(obj).length==0){
        		alert("找不到相符的房屋商品,您搜索的地址區域為"+$("#citych").val()+$("#townch").val()+$("#searchbox").val());
        	}
				if(action=="ajax"){
	 		  clear();};

	 		lastround=range;

         $.each(obj, function(key, value) {
		　　 if($("[id='"+key+"']").length==1){
				 $("[id='"+key+"']").remove();
            	};
			if(value.marker!=undefined){
                value.marker.setMap(null);};
                
                
        distance = space(map.getCenter().lat(), map.getCenter().lng(),value.hos_lat,value.hos_lng);
        console.log(distance+"VS"+range);

        if(distance<range){
        	console.log("有近來");
       	
		$("#sidebar").append("<div class='row house-row' id='"+value.hos_no+"'><div class='col-5'>"+
				"<img class='house-img' src='<%=request.getContextPath()%>/hos/hosPic?pic="+value.hos_pic+"'>"+
				"</div><div class='col-7'><div class='hos-name'><h5>"+value.hos_name+"</h5>"+		
				"</div><div><p class='price'>租金:$"+value.hos_rentfee+"元/月</p>"+
				"<p class='card-text'>地址:"+value.hos_add+"</p></div>"+
                "<button type='button' class='btn btn-light-blue btn-md' value='"+value.hos_no+"' style='background: #AACFBF;margin:10px'>查看詳情</button>"+
"</div></div>"
		)
		value.marker = new google.maps.Marker({ //插上座標
            map: map,
            position: {
                lat:value.hos_lat,
                lng:value.hos_lng
            },
            markerid:key
            
       	   });
		console.log("value.marker.position.lat()="+value.marker.position.lat());

        
        var content =  "<img src='<%=request.getContextPath()%>/hos/hosPic?pic="+value.hos_pic+"' alt='Denim Jeans' style='width: 100%'>" +
        "<h3>"+value.hos_name+"</h3>"+
        "<p class='price'>$"+value.hos_rentfee+" 元/月</p>" +
        "<p>"+value.hos_add+"</p>" +
        "<button type='button' class='btn btn-light-blue btn-md btn-det' value='"+value.hos_no+"' style='background: #AACFBF;'>查看詳情</button>" +
        "</div>";
    attachSecretMessage(value.marker, content);}
        
   
    });    
        
//         	  console.log(map.getCenter().lng());
 			google.maps.event.removeListener(listener);
		listener=map.addListener("idle", () => {action="";
		if(infowin==false){
  	 	 loading()};
  	  });
         
//  		google.maps.event.removeListener(listener1);
//         map.removeListener("idle",idleListener);
        }
        
        function geo_coder() { //點擊查詢輸入框地點
            	console.log('跑到轉換');
                address = $("#serch").val();
                geocoder.geocode({ 'address': $("#citych").val()+$("#townch").val()+$("#searchbox").val() }, function(results, status) { //地址轉換經緯度 results取得該地區所有資訊 status回傳成功與否 以'OK'表示
                    if (status == 'OK') {
//                         locationForNow = results[0].geometry.location;
                        map.setCenter(results[0].geometry.location);
                        lat = results[0].geometry.location.lat();
                        lng = results[0].geometry.location.lng();
                    	ajax();
                    } else {
                        console.log(status);
                    }
                });            	
            

        }

//         function flag() {
//         	if(lastround<range){
//         		geo_coder();
// 			}
//         	else{
//         		console.log('跑到拔座標');
//                $.each(obj, function(key, value){
//                 distance = space(lat, lng,value.marker.position.lat(),value.marker.position.lng());
//             	var tryita="[id='"+value.hos_no+"']";    
//             if (distance > range) {
//             	console.log(value.hos_name+"拔走了");
//                 	$(tryita).remove();
//                     value.marker.setMap(null);
//                 }
//             lastround=range;
//             });loading()};
//         };

	
        
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
           $("#far").text("目前顯示範圍 : 距離畫面中心點 " +$("#ranges").val() + " 公尺內的房屋");
           console.log("range="+range+",last="+lastround);
            loading();
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
            	 if( prev_infowindow ) {
                     prev_infowindow.close();
                     }
                  prev_infowindow = infowindow;
                  infowin=true;
                infowindow.open(marker.get('map'), marker);
            });
            
            google.maps.event.addListener(infowindow,'closeclick',function(){//配合監聽畫面移動使用
            infowin=false;
            })
        };
    	/*--------------------以下按鈕方法------------------*/
		$(".form-control").change(function(){
			geo_coder();
 } );
		 $("#btn-search").click(function() {
			 geo_coder();
		   });
  
		
		$("#city").click(function() {
            $(".allcity").toggle();
        });
        $("#town").click(function() {
            $(".towns").toggle();
        });
        $(".cityinput").click(function() {
            $(".allcity").toggle();
            $("#citych").val($(this).val());
            $(".towns").remove();
            for (let i of studentMap) {
                if (i[0] == $(this).val()) {
                    $("#townch").val("");
                    for (let o of i[1]) { 
                        $("#town").append(
                            "<div class='towns'><label id='townlabel'>" +
                            "<input type='radio'  name='town' class='towninput' value='" + o + "'>" +
                             o + "</label></div>");
                    }
                }
            }
        });
        $(document).on("change", ".towninput", function() {
            $("#townch").val($(this).val());
            $(".towns").toggle();

        });
        $(document).on("click", ".moneybtn", function() {
			moneybtn=$(this).val();
			   geo_coder();
		}
		);
        $(document).on("click", ".housebtn", function() {
        	housebtn=$(this).val();
     	   geo_coder();

		});
        $(document).on("click", ".btn-md", function() {
        	window.location.href='<%=request.getContextPath()%>/HouseDet/HouseDetServlet?hos='+$(this).val();
		}
        
		);
		var area_data = {
	            '台北市': [
	                '中正區', '大同區', '中山區', '萬華區', '信義區', '松山區', '大安區', '南港區', '北投區', '內湖區', '士林區', '文山區'
	            ],
	            '新北市': [
	                '板橋區', '新莊區', '泰山區', '林口區', '淡水區', '金山區', '八里區', '萬里區', '石門區', '三芝區', '瑞芳區', '汐止區', '平溪區', '貢寮區', '雙溪區', '深坑區', '石碇區', '新店區', '坪林區', '烏來區', '中和區', '永和區', '土城區', '三峽區', '樹林區', '鶯歌區', '三重區', '蘆洲區', '五股區'
	            ],
	            '基隆市': [
	                '仁愛區', '中正區', '信義區', '中山區', '安樂區', '暖暖區', '七堵區'
	            ],
	            '桃園市': [
	                '桃園區', '中壢區', '平鎮區', '八德區', '楊梅區', '蘆竹區', '龜山區', '龍潭區', '大溪區', '大園區', '觀音區', '新屋區', '復興區'
	            ],
	            '新竹縣': [
	                '竹北市', '竹東鎮', '新埔鎮', '關西鎮', '峨眉鄉', '寶山鄉', '北埔鄉', '橫山鄉', '芎林鄉', '湖口鄉', '新豐鄉', '尖石鄉', '五峰鄉'
	            ],
	            '新竹市': [
	                '東區', '北區', '香山區'
	            ],
	            '苗栗縣': [
	                '苗栗市', '通霄鎮', '苑裡鎮', '竹南鎮', '頭份鎮', '後龍鎮', '卓蘭鎮', '西湖鄉', '頭屋鄉', '公館鄉', '銅鑼鄉', '三義鄉', '造橋鄉', '三灣鄉', '南庄鄉', '大湖鄉', '獅潭鄉', '泰安鄉'
	            ],
	            '臺中市': [
	                '中區', '東區', '南區', '西區', '北區', '北屯區', '西屯區', '南屯區', '太平區', '大里區', '霧峰區', '烏日區', '豐原區', '后里區', '東勢區', '石岡區', '新社區', '和平區', '神岡區', '潭子區', '大雅區', '大肚區', '龍井區', '沙鹿區', '梧棲區', '清水區', '大甲區', '外埔區', '大安區'
	            ],
	            '南投縣': [
	                '南投市', '埔里鎮', '草屯鎮', '竹山鎮', '集集鎮', '名間鄉', '鹿谷鄉', '中寮鄉', '魚池鄉', '國姓鄉', '水里鄉', '信義鄉', '仁愛鄉'
	            ],
	            '彰化縣': [
	                '彰化市', '員林鎮', '和美鎮', '鹿港鎮', '溪湖鎮', '二林鎮', '田中鎮', '北斗鎮', '花壇鄉', '芬園鄉', '大村鄉', '永靖鄉', '伸港鄉', '線西鄉', '福興鄉', '秀水鄉', '埔心鄉', '埔鹽鄉', '大城鄉', '芳苑鄉', '竹塘鄉', '社頭鄉', '二水鄉', '田尾鄉', '埤頭鄉', '溪州鄉'
	            ],
	            '雲林縣': [
	                '斗六市', '斗南鎮', '虎尾鎮', '西螺鎮', '土庫鎮', '北港鎮', '莿桐鄉', '林內鄉', '古坑鄉', '大埤鄉', '崙背鄉', '二崙鄉', '麥寮鄉', '臺西鄉', '東勢鄉', '褒忠鄉', '四湖鄉', '口湖鄉', '水林鄉', '元長鄉'
	            ],
	            '嘉義縣': [
	                '太保市', '朴子市', '布袋鎮', '大林鎮', '民雄鄉', '溪口鄉', '新港鄉', '六腳鄉', '東石鄉', '義竹鄉', '鹿草鄉', '水上鄉', '中埔鄉', '竹崎鄉', '梅山鄉', '番路鄉', '大埔鄉', '阿里山鄉'
	            ],
	            '嘉義市': [
	                '東區', '西區'
	            ],
	            '臺南市': [
	                '中西區', '東區', '南區', '北區', '安平區', '安南區', '永康區', '歸仁區', '新化區', '左鎮區', '玉井區', '楠西區', '南化區', '仁德區', '關廟區', '龍崎區', '官田區', '麻豆區', '佳里區', '西港區', '七股區', '將軍區', '學甲區', '北門區', '新營區', '後壁區', '白河區', '東山區', '六甲區', '下營區', '柳營區', '鹽水區', '善化區', '大內區', '山上區', '新市區', '安定區'
	            ],
	            '高雄市': [
	                '楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區', '前金區', '新興區', '苓雅區', '前鎮區', '小港區', '旗津區', '鳳山區', '大寮區', '鳥松區', '林園區', '仁武區', '大樹區', '大社區', '岡山區', '路竹區', '橋頭區', '梓官區', '彌陀區', '永安區', '燕巢區', '田寮區', '阿蓮區', '茄萣區', '湖內區', '旗山區', '美濃區', '內門區', '杉林區', '甲仙區', '六龜區', '茂林區', '桃源區', '那瑪夏區'
	            ],
	            '屏東縣': [
	                '屏東市', '潮州鎮', '東港鎮', '恆春鎮', '萬丹鄉', '長治鄉', '麟洛鄉', '九如鄉', '里港鄉', '鹽埔鄉', '高樹鄉', '萬巒鄉', '內埔鄉', '竹田鄉', '新埤鄉', '枋寮鄉', '新園鄉', '崁頂鄉', '林邊鄉', '南州鄉', '佳冬鄉', '琉球鄉', '車城鄉', '滿州鄉', '枋山鄉', '霧台鄉', '瑪家鄉', '泰武鄉', '來義鄉', '春日鄉', '獅子鄉', '牡丹鄉', '三地門鄉'
	            ],
	            '宜蘭縣': [
	                '宜蘭市', '羅東鎮', '蘇澳鎮', '頭城鎮', '礁溪鄉', '壯圍鄉', '員山鄉', '冬山鄉', '五結鄉', '三星鄉', '大同鄉', '南澳鄉'
	            ],
	            '花蓮縣': [
	                '花蓮市', '鳳林鎮', '玉里鎮', '新城鄉', '吉安鄉', '壽豐鄉', '秀林鄉', '光復鄉', '豐濱鄉', '瑞穗鄉', '萬榮鄉', '富里鄉', '卓溪鄉'
	            ],
	            '臺東縣': [
	                '臺東市', '成功鎮', '關山鎮', '長濱鄉', '海端鄉', '池上鄉', '東河鄉', '鹿野鄉', '延平鄉', '卑南鄉', '金峰鄉', '大武鄉', '達仁鄉', '綠島鄉', '蘭嶼鄉', '太麻里鄉'
	            ],
	            '澎湖縣': [
	                '馬公市', '湖西鄉', '白沙鄉', '西嶼鄉', '望安鄉', '七美鄉'
	            ],
	            '金門縣': [
	                '金城鎮', '金湖鎮', '金沙鎮', '金寧鄉', '烈嶼鄉', '烏坵鄉'
	            ],
	            '連江縣': [
	                '南竿鄉', '北竿鄉', '莒光鄉', '東引鄉'
	            ]
	        }
        const studentMap = new Map(Object.entries(area_data));
		
		$(".moneybtn").click(function(){
			$(".moneybtn").css("color","white");
			$(this).css("color","#CD4A2D");
		})
		$(".housebtn").click(function(){
			$(".housebtn").css("color","white");
			$(this).css("color","#CD4A2D");
		})
        
        
            </script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8&callback=initMap"></script>

</body>

</html>
</body>
</html>