(function($) {
	"use strict";

	/*
	 * ================================================================== [
	 * Focus Contact2 ]
	 */
	$('.register100').each(function() {
		$(this).on('blur', function() {
			if ($(this).val().trim() != "") {
				$(this).addClass('has-val');
			} else {
				$(this).removeClass('has-val');
			}
		})
	})

	/*
	 * ================================================================== [
	 * Validate ]
	 */
	var input = $('.validate-input .register100');

	$('.validate-form').on('submit', function() {
		var check = true;

		for (var i = 0; i < input.length; i++) {
			if (validate(input[i]) == false) {
				showValidate(input[i]);
				check = false;
			}
		}
		return check;

	});

	$('.validate-form .register100').each(function() {
		$(this).focus(function() {
			hideValidate(this);
		});
	});

	function validate(input) {
		if ($(input).attr('type') == 'email'
				|| $(input).attr('name') == 'tnt_email') {
			if ($(input)
					.val()
					.trim()
					.match(
							/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
				return false;
			}
		} else {
			if ($(input).val().trim() == '') {
				return false;
			}
		}
	}

	function showValidate(input) {
		var thisAlert = $(input).parent();

		$(thisAlert).addClass('alert-validate');
	}

	function hideValidate(input) {
		var thisAlert = $(input).parent();

		$(thisAlert).removeClass('alert-validate');
	}

})(jQuery);


/*
 * ================================================================== [
 * datetimepicker Birthday Limt 18 ]
 */

var somedate2 = new Date();
somedate2.setFullYear(somedate2.getFullYear() - 18);
var somedate3 = new Date();
somedate3.setFullYear(somedate3.getFullYear() - 18);
somedate3 = somedate3.setDate(somedate3.getDate() + 1);
$.datetimepicker.setLocale('zh');
$('#f_date1').datetimepicker({
	theme : '', // theme: 'dark',
	timepicker : false, // timepicker:true,
	step : 1, // step: 60 (這是timepicker的預設間隔60分鐘)
	format : 'Y-m-d', // format:'Y-m-d H:i:s',
	value : somedate3, // '<%=tnt_birth%>', // value: new Date(),
// disabledDates: ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
// startDate: '2017/07/10', // 起始日
// minDate: '-1970-01-01', // 去除今日(不含)之前
// maxDate: '+1970-01-01' // 去除今日(不含)之後
});

// 2.以下為某一天之後的日期無法選擇--未滿18不能選
// var somedate2 = new Date('2017-06-15');

$('#f_date1')
		.datetimepicker(
				{
					beforeShowDay : function(date) {
						if (date.getYear() > somedate2.getYear()
								|| (date.getYear() == somedate2.getYear() && date
										.getMonth() > somedate2.getMonth())
								|| (date.getYear() == somedate2.getYear()
										&& date.getMonth() == somedate2
												.getMonth() && date.getDate() > somedate2
										.getDate())) {
							return [ false, "" ]
						}
						return [ true, "" ];
					}
				});
/*
 * ================================================================== [ 縣市區域 ]
 */

var area_data = {
	'台北市' : [ '中正區', '大同區', '中山區', '萬華區', '信義區', '松山區', '大安區', '南港區', '北投區',
			'內湖區', '士林區', '文山區' ],
	'新北市' : [ '板橋區', '新莊區', '泰山區', '林口區', '淡水區', '金山區', '八里區', '萬里區', '石門區',
			'三芝區', '瑞芳區', '汐止區', '平溪區', '貢寮區', '雙溪區', '深坑區', '石碇區', '新店區',
			'坪林區', '烏來區', '中和區', '永和區', '土城區', '三峽區', '樹林區', '鶯歌區', '三重區',
			'蘆洲區', '五股區' ],
	'基隆市' : [ '仁愛區', '中正區', '信義區', '中山區', '安樂區', '暖暖區', '七堵區' ],
	'桃園市' : [ '桃園區', '中壢區', '平鎮區', '八德區', '楊梅區', '蘆竹區', '龜山區', '龍潭區', '大溪區',
			'大園區', '觀音區', '新屋區', '復興區' ],
	'新竹縣' : [ '竹北市', '竹東鎮', '新埔鎮', '關西鎮', '峨眉鄉', '寶山鄉', '北埔鄉', '橫山鄉', '芎林鄉',
			'湖口鄉', '新豐鄉', '尖石鄉', '五峰鄉' ],
	'新竹市' : [ '東區', '北區', '香山區' ],
	'苗栗縣' : [ '苗栗市', '通霄鎮', '苑裡鎮', '竹南鎮', '頭份鎮', '後龍鎮', '卓蘭鎮', '西湖鄉', '頭屋鄉',
			'公館鄉', '銅鑼鄉', '三義鄉', '造橋鄉', '三灣鄉', '南庄鄉', '大湖鄉', '獅潭鄉', '泰安鄉' ],
	'臺中市' : [ '中區', '東區', '南區', '西區', '北區', '北屯區', '西屯區', '南屯區', '太平區', '大里區',
			'霧峰區', '烏日區', '豐原區', '后里區', '東勢區', '石岡區', '新社區', '和平區', '神岡區',
			'潭子區', '大雅區', '大肚區', '龍井區', '沙鹿區', '梧棲區', '清水區', '大甲區', '外埔區',
			'大安區' ],
	'南投縣' : [ '南投市', '埔里鎮', '草屯鎮', '竹山鎮', '集集鎮', '名間鄉', '鹿谷鄉', '中寮鄉', '魚池鄉',
			'國姓鄉', '水里鄉', '信義鄉', '仁愛鄉' ],
	'彰化縣' : [ '彰化市', '員林鎮', '和美鎮', '鹿港鎮', '溪湖鎮', '二林鎮', '田中鎮', '北斗鎮', '花壇鄉',
			'芬園鄉', '大村鄉', '永靖鄉', '伸港鄉', '線西鄉', '福興鄉', '秀水鄉', '埔心鄉', '埔鹽鄉',
			'大城鄉', '芳苑鄉', '竹塘鄉', '社頭鄉', '二水鄉', '田尾鄉', '埤頭鄉', '溪州鄉' ],
	'雲林縣' : [ '斗六市', '斗南鎮', '虎尾鎮', '西螺鎮', '土庫鎮', '北港鎮', '莿桐鄉', '林內鄉', '古坑鄉',
			'大埤鄉', '崙背鄉', '二崙鄉', '麥寮鄉', '臺西鄉', '東勢鄉', '褒忠鄉', '四湖鄉', '口湖鄉',
			'水林鄉', '元長鄉' ],
	'嘉義縣' : [ '太保市', '朴子市', '布袋鎮', '大林鎮', '民雄鄉', '溪口鄉', '新港鄉', '六腳鄉', '東石鄉',
			'義竹鄉', '鹿草鄉', '水上鄉', '中埔鄉', '竹崎鄉', '梅山鄉', '番路鄉', '大埔鄉', '阿里山鄉' ],
	'嘉義市' : [ '東區', '西區' ],
	'臺南市' : [ '中西區', '東區', '南區', '北區', '安平區', '安南區', '永康區', '歸仁區', '新化區',
			'左鎮區', '玉井區', '楠西區', '南化區', '仁德區', '關廟區', '龍崎區', '官田區', '麻豆區',
			'佳里區', '西港區', '七股區', '將軍區', '學甲區', '北門區', '新營區', '後壁區', '白河區',
			'東山區', '六甲區', '下營區', '柳營區', '鹽水區', '善化區', '大內區', '山上區', '新市區',
			'安定區' ],
	'高雄市' : [ '楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區', '前金區', '新興區', '苓雅區', '前鎮區',
			'小港區', '旗津區', '鳳山區', '大寮區', '鳥松區', '林園區', '仁武區', '大樹區', '大社區',
			'岡山區', '路竹區', '橋頭區', '梓官區', '彌陀區', '永安區', '燕巢區', '田寮區', '阿蓮區',
			'茄萣區', '湖內區', '旗山區', '美濃區', '內門區', '杉林區', '甲仙區', '六龜區', '茂林區',
			'桃源區', '那瑪夏區' ],
	'屏東縣' : [ '屏東市', '潮州鎮', '東港鎮', '恆春鎮', '萬丹鄉', '長治鄉', '麟洛鄉', '九如鄉', '里港鄉',
			'鹽埔鄉', '高樹鄉', '萬巒鄉', '內埔鄉', '竹田鄉', '新埤鄉', '枋寮鄉', '新園鄉', '崁頂鄉',
			'林邊鄉', '南州鄉', '佳冬鄉', '琉球鄉', '車城鄉', '滿州鄉', '枋山鄉', '霧台鄉', '瑪家鄉',
			'泰武鄉', '來義鄉', '春日鄉', '獅子鄉', '牡丹鄉', '三地門鄉' ],
	'宜蘭縣' : [ '宜蘭市', '羅東鎮', '蘇澳鎮', '頭城鎮', '礁溪鄉', '壯圍鄉', '員山鄉', '冬山鄉', '五結鄉',
			'三星鄉', '大同鄉', '南澳鄉' ],
	'花蓮縣' : [ '花蓮市', '鳳林鎮', '玉里鎮', '新城鄉', '吉安鄉', '壽豐鄉', '秀林鄉', '光復鄉', '豐濱鄉',
			'瑞穗鄉', '萬榮鄉', '富里鄉', '卓溪鄉' ],
	'臺東縣' : [ '臺東市', '成功鎮', '關山鎮', '長濱鄉', '海端鄉', '池上鄉', '東河鄉', '鹿野鄉', '延平鄉',
			'卑南鄉', '金峰鄉', '大武鄉', '達仁鄉', '綠島鄉', '蘭嶼鄉', '太麻里鄉' ],
	'澎湖縣' : [ '馬公市', '湖西鄉', '白沙鄉', '西嶼鄉', '望安鄉', '七美鄉' ],
	'金門縣' : [ '金城鎮', '金湖鎮', '金沙鎮', '金寧鄉', '烈嶼鄉', '烏坵鄉' ],
	'連江縣' : [ '南竿鄉', '北竿鄉', '莒光鄉', '東引鄉' ]
}

$(document)
		.ready(
				function() {
					var city_key = Object.keys(area_data);

					// 若無原始tntVO資料, 新增"選擇縣市"跟所有縣市, 區域僅新增"選擇區域"
					if ($('#tnt_city').val() === null) {
						$("#tnt_city").append(
								'<option value="0" id="city_default">選擇縣市');
						city_key.forEach(function(item, index, array) {
							$("#tnt_city").append(
									'<option value="' + item + '">' + item);
						});
						$('#tnt_dist').append(
								'<option value="0" id="dist_default">選擇區域');
						// 若有原始tntVO資料, 新增所有縣市, 判斷有無selected縣市
					} else {
						var index_city;
						city_key.forEach(function(item, index, array) {
							if (item === $('#tnt_city').val()) {
								var tmp = ' selected';
								index_city = index;
							}
							$("#tnt_city").append(
									'<option value="' + item + '"' + tmp + '>'
											+ item);
						});
						$('#city_default').val("0");
						// 若有selected縣市,新增所有區域, 如無selected縣市, 不動
						if (typeof (index_city) !== "undefined") {
							var dist_values_selectedcity = Object
									.values(area_data)[index_city];
							dist_values_selectedcity
									.forEach(function(item, index, array) {
										var tmp = (item === $('#tnt_dist')
												.val()) ? ' selected' : '';
										$("#tnt_dist").append(
												'<option value="' + item + '"'
														+ tmp + '>' + item);
									});
						}
						$('#dist_default').val("0");
					}

					// 選擇縣市, 區域連動
					$("#tnt_city").change(
							function() {
								city_key.forEach(function(item, index, array) {
									if (item === $("#tnt_city").val()) {
										$("#dist_default ~ option").remove();
										var dist_values = Object
												.values(area_data)[index];
										dist_values.forEach(function(item,
												index, array) {
											$("#tnt_dist").append(
													'<option value="' + item
															+ '">' + item);
										});
									}
								});
							});
				});
/*
 * ================================================================== [ 按鈕Ajax ]
 */
//var formData = new FormData();
//$('button.login100-form-btn').click(function(e) {
//	e.preventDefault();
//	console.log('123');
////	console.log($('#registerform')[0]);
//	// console.log($("#tnt_email"))
//	// console.log($("#tnt_email").val())
//	// ajax_register();
////	var formData = new FormData($('#registerform')[0]);
//	var formData = new FormData();
//	 formData.append('action','insert');
//	ajax_register();
//
//});

function ajax_register() {
	$.ajax({// 存入資料庫階段
		url : "/EA103G2/tnt/TntServlet2",
		type : "POST",
//		data : formData,
		data :{
		 action : "insert",
		 tnt_email : $("#tnt_email").val(),
		// tnt_pwd:$("#tnt_pwd").val(),
		// tnt_pwd2:$("#tnt_pwd2").val(),
		// tnt_name:$("#tnt_name").val(),
		// tnt_sex:$("#tnt_sex").val(),
		// tnt_id:$("#tnt_id").val(),
		// tnt_birth:$("#tnt_birth").val(),
		// tnt_city:$("#tnt_city").val(),
		// tnt_dist:$("#tnt_dist").val(),
		// tnt_add:$("#tnt_add").val()
		 },
		success : function() {// 以上成功才執行
			console.log("res棒");
		},
		error : function() {
			console.log("真的不棒")
		}
	})
}

//function ajax_register2() {
//	$.ajax({// 存入資料庫階段
//		url : "http://localhost:8081/EA103G2/back-end/tnt/select_page.jsp",
//		type : "GET",
//		success : function(data) {// 以上成功才執行
//			console.log("data=" + data);
//
//			window.html(data);
//			console.log("res棒");
//		},
//		error : function() {
//			console.log("真的不棒")
//		}
//	})
//}

