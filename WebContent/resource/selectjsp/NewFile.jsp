<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<div class="col-12 body">
	<!-- 縣市 -->
	<div class="row justify-content-md-center sharchbox">
		<div class="col-2"></div>
		<div class="col-10">
			<div id="city">
				<input type="text" id="citych" value="選擇城市"><br>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="台北市"> 台北市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="新北市"> 新北市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="基隆市"> 基隆市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="桃園市"> 桃園市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="新竹縣"> 新竹縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="新竹市"> 新竹市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="苗栗縣"> 苗栗縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="臺中市"> 臺中市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="南投縣"> 南投縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="彰化縣"> 彰化縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="雲林縣"> 雲林縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="嘉義縣"> 嘉義縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="嘉義市"> 嘉義市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="臺南市"> 臺南市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="高雄市"> 高雄市
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="屏東縣"> 屏東縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="宜蘭縣"> 宜蘭縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="花蓮縣"> 花蓮縣
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="臺東縣"> 臺東縣
					</label>
				</div>
			</div>
			<div id="town">
				<input type="text" id="townch" value="選擇鄉鎮"><br>
			</div>
			<div id="searchbar">
				<form class="form-inline mr-auto">
					<input class="form-control mr-sm-2" id="searchbox" type="text"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-unique btn-rounded btn-sm my-0"
						id="btn-search" type="button">Search</button>
				</form>
			</div>
			<!-- 縣市 -->
		</div>

	</div>
	<div class="row btn-cho">
		<div class="moneyside">
			<div id="money">
				<label for="btn-group">價格區間</label>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left">5000以下</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left2">5000-10000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left3">10000-15000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left4">15001-20000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left5">20000以上</button>
				</div>
			</div>
		</div>
		<div class="houseside">
			<div id="housestyle">
				<label for="btn-group">房屋型態</label>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-secondary housebtn">不限</button>
					<button type="button" class="btn btn-secondary housebtn">整層住家</button>
					<button type="button" class="btn btn-secondary housebtn">獨立套房</button>
					<button type="button" class="btn btn-secondary housebtn">分租套房</button>
					<button type="button" class="btn btn-secondary housebtn">雅房</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row turn">
		<div class="col-6">
			<div class="col-xs-12 someoption">
				<label class="item_name">可開伙&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="coke" tabindex="0"> <label
							class="onoffswitch-label" for="coke"></label>
					</div>
				</label> <label class="item_name"><i class="fas fa-paw"></i>

					可養寵&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="pet" tabindex="0"> <label
							class="onoffswitch-label" for="pet"></label>
					</div> </label> <label class="item_name">可開車&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="deriver" tabindex="0"> <label
							class="onoffswitch-label" for="deriver"></label>
					</div>
				</label> <label class="item_name">可新增&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="try14" tabindex="0"> <label
							class="onoffswitch-label" for="try14"></label>
					</div>
				</label>
			</div>
		</div>
		<div class="col-6">
			<div id="selectforwhat">
				<label class="item_name">排序&nbsp;&nbsp; <select
					class="form-control" id="selectbox">
						<option value="date_up">已上架時間 短->長</option>
						<option value="date_down">已上架時間 長->短</option>
						<option value="price_down ">出租價格 低->高</option>
						<option value="price_up">出租價格 高->低</option>
						<option value="xlhouse">房屋坪數 大->小</option>
						<option value="xshouse">房屋坪數 小->大</option>
				</select>
				</label>
			</div>
		</div>
	</div>
	<body>
	<style>
	.someoption{
    width: 70%;
    height: 100%;
    float: right;
        padding-top: 3%;

}
footer{
    bottom: 0;
    background-color: green;
    height: 100px; 
    width: 100%
}
.btn-group button{
    width: 120px;
    height: 38px;
    border-radius:20px;
}
.moneyside,.houseside{
    width: 730px;
    height: 38px;
    
}
.houseside{
margin-left:20px;
}
#money{float: right}
#selectforwhat{
text-align: center;}
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

        /*開關*/
             /*城市*/
        #searchbar {
            float: left;
            width: 38%;
            margin-left: 3%;
            margin-bottom: 3%;
        }

        .my-0 {
            background-color: pink;
            /*搜索按鈕*/
        }

        .mr-auto {
            margin-top: 5%;
        }


        #town {
            /*外層DIV*/
            height: 20px;
            display: inline;
            margin-top: 2.2%;
            float: left;
            float: left;
            margin-left: 2%;
        }

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
        }

        #city {
            /*外層DIV*/
            height: 20px;
            display: inline;
            margin-left: 10%;
            margin-top: 2.2%;
            float: left;
            border-radius: 50px;
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
	</style>
	<script>
$(".form-control").change(function(){
			
	$.ajax({//存入資料庫階段
		  url:"/EA103G2/mapServlet",
	 	  type:"GET",
	 	  data:{action:"search",
	 		  data:$("#citych").val()+$("#townch").val()+$("#searchbox").val(),
	 		  sort:$("#selectbox").val()
	 	  },
	 	  success:function(data){//以上成功才執行
	 		  console.log("data="+data);
//	 		  if(data.length>50){
	 			 obj=JSON.parse(data); 			
	             loading();
	 		  	console.log("res棒");}
	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }			  
	  })});
		
		
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
        })
        $(document).on("change", ".towninput", function() {
            $("#townch").val($(this).val());
            $(".towns").toggle();

        });
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
	</body>
</html>