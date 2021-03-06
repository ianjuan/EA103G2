        /*------------------------------------------------------------------
        [ Origin-Register ]*/
        (function($) {
            "use strict";

            /*
             * ================================================================== 
             *  [ Focus Contact2 ]
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
             * ================================================================== 
             * [ Validate for each input By myself]
             */
            // 信箱
            $('#lld_email.register100').on('blur', function() {
                if (/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/.test($(this).val().trim()) === false) {
                    // if (validate($(this)) == false) {
                    showValidate($(this));
                }
            })
            // 姓名
            $('#lld_name.register100').on('blur', function() {
                if (/^[(\u4e00-\u9fa5)(a-zA-Z)]{2,19}$/.test($(this).val()
                        .trim()) === false) {
                    showValidate($(this));
                }
            })
            // 身分證
            $('#lld_id.register100').on('blur', function() {
                if (/^[A-Za-z][12][0-9]{8}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                } else if (id_validate($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 手機
            $('#lld_mobile.register100').on('blur', function() {
                if (/^[0][9][0-9]{8}$/.test($(this).val().trim()) === false) {
                    showValidate($(this));
                }
            })
            // 密碼
            $('#lld_pwd.register100').on('blur', function() {
                if (/^[\w]{8,16}$/.test($(this).val()) === false) {
                    showValidate($(this));
                }
            })
            // 新密碼
            $('#lld_pwd_new.register100').on('blur', function() {
                if (/^[\w]{8,16}$/.test($(this).val()) === false) {
                    showValidate($(this));
                }
            })
            // 確認新密碼
            $('#lld_pwd_new2.register100').on('blur', function() {
                if ($(this).val() !== $('#lld_pwd_new.register100').val()) {
                    showValidate($(this));
                }
            })
            // 地址
            $('#lld_add.register100').on('blur', function() {
                if ($('#lld_add').val().trim() === '') {
                    showValidate($(this));
                }
            })
            //下拉 - 性別
            $('#lld_sex').on('blur', function() {
                if ($('#lld_sex').val().trim() == '') {
                    $('#lld_sex').addClass('alert-validate');
                    $('#lld_sex').addClass('alert-validate-select');
                } else {
                    $('#lld_sex').addClass('select-has-val');
                }
            })
            $('#lld_sex').on('focus', function() {
                $('#lld_sex').removeClass('alert-validate');
                $('#lld_sex').removeClass('alert-validate-select');
                $('#lld_sex').removeClass('alert-validate-selects');
            })
            //下拉 - 縣市
            $('#lld_city').on('blur', function() {
                if ($('#lld_city').val().trim() == '') {
                    $('#lld_city').addClass('alert-validate');
                    $('#lld_city').addClass('alert-validate-select');
                } else {
                    $('#lld_city').addClass('select-has-val');
                }
            })
            $('#lld_city').on('focus', function() {
                $('#lld_city').removeClass('alert-validate');
                $('#lld_city').removeClass('alert-validate-select');
                $('#lld_city').removeClass('alert-validate-selects');
            })
            //下拉 - 區域
            $('#lld_dist').on('blur', function() {
                if ($('#lld_dist').val().trim() == '') {
                    $('#lld_dist').addClass('alert-validate');
                    $('#lld_dist').addClass('alert-validate-select');
                } else {
                    $('#lld_dist').addClass('select-has-val');
                }
            })
            $('#lld_dist').on('focus', function() {
                $('#lld_dist').removeClass('alert-validate');
                $('#lld_dist').removeClass('alert-validate-select');
                $('#lld_dist').removeClass('alert-validate-selects');
            })


            var letter = ["A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M",
                "N", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z", "I",
                "O"
            ];

            function id_validate(idNumber) {
                var numOfid = letter.indexOf(idNumber[0].toUpperCase()) + 10 +
                    idNumber.substring(1);
                var weighted = 0;
                for (var i = 1; i < 10; i++) {
                    weighted += numOfid[i] * (10 - i);
                }
                weighted += numOfid[0] * 1;
                weighted = weighted % 10;
                if (idNumber[10] === 0 && weighted === 0)
                    return true
                else if ((10 - weighted) === parseInt(idNumber[9]))
                    return true
                else
                    return false
            }

        })(jQuery);

        /*
         * ================================================================== 
         * [ Validate for next button]
         */

        var showValidate = function(input) {
            var thisAlert = $(input).parent();

            $(thisAlert).addClass('alert-validate');
        }

        var hideValidate = function(input) {
            var thisAlert = $(input).parent();

            $(thisAlert).removeClass('alert-validate');
        }

        /*
         * ================================================================== 
            [datetimepicker Birthday Limt 18 ]
         * 
         */

//        var somedate2 = new Date();
//        somedate2.setFullYear(somedate2.getFullYear() - 18);
//        // console.log(somedate2);
//        var somedate3 = new Date();
//        somedate3.setFullYear(somedate3.getFullYear() - 18);
//        somedate3.setDate(somedate3.getDate() + 1);
//        $.datetimepicker.setLocale('zh');
//        $('#f_date1').datetimepicker({
//            theme: '', // theme: 'dark',
//            timepicker: false, // timepicker:true,
//            step: 1, // step: 60 (這是timepicker的預設間隔60分鐘)
//            format: 'Y-m-d', // format:'Y-m-d H:i:s',
//            value: somedate3, // '<%=lld_birth%>', // value: new Date(),
//            // disabledDates: ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            // startDate: '2017/07/10', // 起始日
//            // minDate: '-1970-01-01', // 去除今日(不含)之前
//            // maxDate: '+1970-01-01' // 去除今日(不含)之後
//        });
//
//        // 2.以下為某一天之後的日期無法選擇--未滿18不能選
//
//        $('#f_date1')
//            .datetimepicker({
//                beforeShowDay: function(date) {
//                    if (date.getYear() > somedate2.getYear() ||
//                        (date.getYear() == somedate2.getYear() && date
//                            .getMonth() > somedate2.getMonth()) ||
//                        (date.getYear() == somedate2.getYear() &&
//                            date.getMonth() == somedate2
//                            .getMonth() && date.getDate() > somedate2
//                            .getDate())) {
//                        return [false, ""]
//                    }
//                    return [true, ""];
//                }
//            });
        /*
         * ================================================================== 
         *    [ 縣市區域 ]
         */

        var area_data = {
            '台北市': ['中正區', '大同區', '中山區', '萬華區', '信義區', '松山區', '大安區', '南港區', '北投區',
                '內湖區', '士林區', '文山區'
            ],
            '新北市': ['板橋區', '新莊區', '泰山區', '林口區', '淡水區', '金山區', '八里區', '萬里區', '石門區',
                '三芝區', '瑞芳區', '汐止區', '平溪區', '貢寮區', '雙溪區', '深坑區', '石碇區', '新店區',
                '坪林區', '烏來區', '中和區', '永和區', '土城區', '三峽區', '樹林區', '鶯歌區', '三重區',
                '蘆洲區', '五股區'
            ],
            '基隆市': ['仁愛區', '中正區', '信義區', '中山區', '安樂區', '暖暖區', '七堵區'],
            '桃園市': ['桃園區', '中壢區', '平鎮區', '八德區', '楊梅區', '蘆竹區', '龜山區', '龍潭區', '大溪區',
                '大園區', '觀音區', '新屋區', '復興區'
            ],
            '新竹縣': ['竹北市', '竹東鎮', '新埔鎮', '關西鎮', '峨眉鄉', '寶山鄉', '北埔鄉', '橫山鄉', '芎林鄉',
                '湖口鄉', '新豐鄉', '尖石鄉', '五峰鄉'
            ],
            '新竹市': ['東區', '北區', '香山區'],
            '苗栗縣': ['苗栗市', '通霄鎮', '苑裡鎮', '竹南鎮', '頭份鎮', '後龍鎮', '卓蘭鎮', '西湖鄉', '頭屋鄉',
                '公館鄉', '銅鑼鄉', '三義鄉', '造橋鄉', '三灣鄉', '南庄鄉', '大湖鄉', '獅潭鄉', '泰安鄉'
            ],
            '臺中市': ['中區', '東區', '南區', '西區', '北區', '北屯區', '西屯區', '南屯區', '太平區', '大里區',
                '霧峰區', '烏日區', '豐原區', '后里區', '東勢區', '石岡區', '新社區', '和平區', '神岡區',
                '潭子區', '大雅區', '大肚區', '龍井區', '沙鹿區', '梧棲區', '清水區', '大甲區', '外埔區',
                '大安區'
            ],
            '南投縣': ['南投市', '埔里鎮', '草屯鎮', '竹山鎮', '集集鎮', '名間鄉', '鹿谷鄉', '中寮鄉', '魚池鄉',
                '國姓鄉', '水里鄉', '信義鄉', '仁愛鄉'
            ],
            '彰化縣': ['彰化市', '員林鎮', '和美鎮', '鹿港鎮', '溪湖鎮', '二林鎮', '田中鎮', '北斗鎮', '花壇鄉',
                '芬園鄉', '大村鄉', '永靖鄉', '伸港鄉', '線西鄉', '福興鄉', '秀水鄉', '埔心鄉', '埔鹽鄉',
                '大城鄉', '芳苑鄉', '竹塘鄉', '社頭鄉', '二水鄉', '田尾鄉', '埤頭鄉', '溪州鄉'
            ],
            '雲林縣': ['斗六市', '斗南鎮', '虎尾鎮', '西螺鎮', '土庫鎮', '北港鎮', '莿桐鄉', '林內鄉', '古坑鄉',
                '大埤鄉', '崙背鄉', '二崙鄉', '麥寮鄉', '臺西鄉', '東勢鄉', '褒忠鄉', '四湖鄉', '口湖鄉',
                '水林鄉', '元長鄉'
            ],
            '嘉義縣': ['太保市', '朴子市', '布袋鎮', '大林鎮', '民雄鄉', '溪口鄉', '新港鄉', '六腳鄉', '東石鄉',
                '義竹鄉', '鹿草鄉', '水上鄉', '中埔鄉', '竹崎鄉', '梅山鄉', '番路鄉', '大埔鄉', '阿里山鄉'
            ],
            '嘉義市': ['東區', '西區'],
            '臺南市': ['中西區', '東區', '南區', '北區', '安平區', '安南區', '永康區', '歸仁區', '新化區',
                '左鎮區', '玉井區', '楠西區', '南化區', '仁德區', '關廟區', '龍崎區', '官田區', '麻豆區',
                '佳里區', '西港區', '七股區', '將軍區', '學甲區', '北門區', '新營區', '後壁區', '白河區',
                '東山區', '六甲區', '下營區', '柳營區', '鹽水區', '善化區', '大內區', '山上區', '新市區',
                '安定區'
            ],
            '高雄市': ['楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區', '前金區', '新興區', '苓雅區', '前鎮區',
                '小港區', '旗津區', '鳳山區', '大寮區', '鳥松區', '林園區', '仁武區', '大樹區', '大社區',
                '岡山區', '路竹區', '橋頭區', '梓官區', '彌陀區', '永安區', '燕巢區', '田寮區', '阿蓮區',
                '茄萣區', '湖內區', '旗山區', '美濃區', '內門區', '杉林區', '甲仙區', '六龜區', '茂林區',
                '桃源區', '那瑪夏區'
            ],
            '屏東縣': ['屏東市', '潮州鎮', '東港鎮', '恆春鎮', '萬丹鄉', '長治鄉', '麟洛鄉', '九如鄉', '里港鄉',
                '鹽埔鄉', '高樹鄉', '萬巒鄉', '內埔鄉', '竹田鄉', '新埤鄉', '枋寮鄉', '新園鄉', '崁頂鄉',
                '林邊鄉', '南州鄉', '佳冬鄉', '琉球鄉', '車城鄉', '滿州鄉', '枋山鄉', '霧台鄉', '瑪家鄉',
                '泰武鄉', '來義鄉', '春日鄉', '獅子鄉', '牡丹鄉', '三地門鄉'
            ],
            '宜蘭縣': ['宜蘭市', '羅東鎮', '蘇澳鎮', '頭城鎮', '礁溪鄉', '壯圍鄉', '員山鄉', '冬山鄉', '五結鄉',
                '三星鄉', '大同鄉', '南澳鄉'
            ],
            '花蓮縣': ['花蓮市', '鳳林鎮', '玉里鎮', '新城鄉', '吉安鄉', '壽豐鄉', '秀林鄉', '光復鄉', '豐濱鄉',
                '瑞穗鄉', '萬榮鄉', '富里鄉', '卓溪鄉'
            ],
            '臺東縣': ['臺東市', '成功鎮', '關山鎮', '長濱鄉', '海端鄉', '池上鄉', '東河鄉', '鹿野鄉', '延平鄉',
                '卑南鄉', '金峰鄉', '大武鄉', '達仁鄉', '綠島鄉', '蘭嶼鄉', '太麻里鄉'
            ],
            '澎湖縣': ['馬公市', '湖西鄉', '白沙鄉', '西嶼鄉', '望安鄉', '七美鄉'],
            '金門縣': ['金城鎮', '金湖鎮', '金沙鎮', '金寧鄉', '烈嶼鄉', '烏坵鄉'],
            '連江縣': ['南竿鄉', '北竿鄉', '莒光鄉', '東引鄉']
        }

        $(document).ready(function() {
            var city_key = Object.keys(area_data);

            // 若無原始lldVO資料, 新增"選擇縣市"跟所有縣市, 區域僅新增"選擇區域"
            if ($('#lld_city').val() === null) {
                // console.log('aa');
                $("#lld_city").append(
                    '<option value="" id="city_default">選擇縣市');
                city_key.forEach(function(item, index, array) {
                    // console.log('lld_city item' + item);
                    $("#lld_city").append('<option value="' + item + '">' + item);
                });
                $('#lld_dist').append('<option value="" id="dist_default">選擇區域');
                // 若有原始lldVO資料, 新增所有縣市, 判斷有無selected縣市
            } else {
                var index_city;
                city_key.forEach(function(item, index, array) {
                    if (item === $('#lld_city').val()) {
                        var tmp = ' selected';
                        index_city = index;
                    }
                    $("#lld_city").append('<option value="' + item + '"' + tmp + '>' +item);
                });
                $('#city_default').val("");
                // 若有selected縣市,新增所有區域, 如無selected縣市, 不動
                if (typeof(index_city) !== "undefined") {
                    var dist_values_selectedcity = Object.values(area_data)[index_city];
                    dist_values_selectedcity.forEach(function(item, index, array) {
//                        console.log(item);
                        var tmp = (item === $('#lld_dist').val()) ? ' selected' : '';
                        $("#lld_dist").append('<option value="' + item + '"' + tmp + '>' + item);
                    });
                }
                $('#dist_default').val("");
            }

            // 選擇縣市, 區域連動
            $("#lld_city").change(
                function() {
                    city_key.forEach(function(item, index, array) {
                        if (item === $("#lld_city").val()) {
                            $("#dist_default ~ option").remove();
                            var dist_values = Object
                                .values(area_data)[index];
                            dist_values.forEach(function(item,
                                index, array) {
                                $("#lld_dist").append('<option value="' + item +'">' + item);
                            });
                        }
                    });
                });
            
            //借放這裡 幫身分資料focus and blur
            var inputs = $('form:eq(0) .validate-input .register100');
            var selects = $('form:eq(0) select.wrap-register100');
            for (var i = 0; i < inputs.length; i++) {
                    $(inputs[i]).focus();
                    $(inputs[i]).blur();
            }
            for (var i = 0; i < selects.length; i++) {
                $(selects[i]).focus();
                $(selects[i]).blur();
            }
            $('#f_date1').blur();
            console.log($('.xdsoft_datetimepicker').css('display'));
//            $('.xdsoft_datetimepicker').css('display','none');
            
            /*
             * ================================================================== 
             * [ Shrink]
             */
            $('.text-primary:eq(1)').click(function(){  
        		$('#basicinfolist').show();
            	$('.shrink').animate({left:'-72px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','right');
            	});
            	$('.shrink').fadeOut('fast');
            	$('#basicinfolist').animate({left:'0px'},'normal');
			});
			$('.text-primary:eq(0)').click(function(){
				$('#basicinfolist').animate({left:'-320px'},'fast',function(){
            		$('.basicInfo__list-backIcon').css('text-align','center');
            		$('#basicinfolist').hide();
            	});
            	$('.shrink').fadeIn('fast');
            	$('.shrink').animate({left:'0px'},'fast');
			});
     
        });
        
        /*
         * ================================================================== 
         *    [ 圖片上傳(程昕) ]
         */
        var img = document.createElement("img");
        var inputF = document.getElementById("inputF");
        var fileInput = document.getElementById("fileInput");



        inputF.addEventListener("change", fileUpload);

        function fileUpload2(files) { //preview
            for (var i = 0; i < files.length; i++) {
                if (files[i].type.indexOf("image") !== -1) { // or picture.type.includes("image");

                    var fileReader = new FileReader();
                    fileReader.readAsDataURL(files[i]); // readyState 1
                    // console.log(fileReader);
                    let filename = files[i].name; //  Must be let!! !!!!
                    // console.log('set Filename=' + filename);
                    fileReader.addEventListener("load", function(e) { // readyState2
                        var picPreview = document.getElementsByClassName("picPreview");
                        if (typeof(picPreview[0]) !== "undefined") {
                            $(".picPreview").empty();
                        }
                        var img = document.createElement("img");
                        var input = document.createElement("input");
                        var div = document.createElement("div");
                        img.setAttribute("src", this.result); // 只能用e.target 不能用 fileReader(readystate:1, Asynchronous)
                        //e.target 是在捕獲冒泡 
                        input.setAttribute("type", "checkbox");
                        div.appendChild(input);
                        div.appendChild(img);
                        document.getElementById("picWrapper").append(div);

                        img.addEventListener("click", (e) => { // click pic to checked checkbox
                            e.currentTarget.previousSibling.click();
                        });

                        div.addEventListener("dragstart", dragStartHandler); //adding dragEvent to newly created pic
                        div.setAttribute("draggable", true);
                        div.setAttribute("name", filename); //LIne 122 important
                        div.setAttribute("class", "picPreview");
                        input.setAttribute("class", "picCheckbox");
                        img.setAttribute("id", "lld_pic");
                    });

                } else {
                    alert("請上傳圖片");
                }
            }
        }
        //----------------------------------------------
        var del = document.getElementById("del");
        del.addEventListener("click", deletFilePreview);

        function deletFilePreview() {
            var checkbox = document.querySelectorAll('div input[type="checkbox"]');
            var isChecked = false;
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    isChecked = true;
                    var filename = checkbox[i].parentElement.getAttribute("name"); //getFilename from checked div
                    checkbox[i].parentElement.remove();

                    console.log("out i=" + i);
                    console.log("out filename=" + filename);

                    DeleteFileNameFile(filename);

                }
            }
            if (isChecked === false) {
            	Swal.fire({
            		icon: 'warning',
            		title: '請勾選刪除項目',
            		showConfirmButton: false,
            		timer: 1000,
            		animation: false
            	});
            }
        }

        //=================================================
        function fileUpload(e) {
            var picture = inputF.files;
             fileUpload2(picture);
             handleFile(picture)
        }

        function dropUploadHandler(e) {
            var dtFiles = e.dataTransfer.files; //return FileList

             handleFile(dtFiles);
             fileUpload2(dtFiles);
        }
        //=============================================
        function handleFile(files) {
            ([...files]).forEach(uploadFileDrop); //convert arraylist to array
        }

        function uploadFileDrop(file) {}


        ['dragover', 'dragleave', 'dragenter', 'drop'].forEach(ev => {
            window.addEventListener(ev, function(e) {
                e.preventDefault();
            })
        });

        function dropDelHandler(e) {

            var domName = e.dataTransfer.getData("text");
            document.getElementsByName(domName)[0].remove();
            DeleteFileNameFile(domName);
        }

        function dragStartHandler(e) {

            e.dataTransfer.setData("text", e.target.parentElement.getAttribute("name"));
        }
        /*------------------------------------------------------------------
        [ End-Origin-Register ]*/

        /*
         * ================================================================== 
         *    [ validateAll(the form) ]
         */

        function validateAll(theform) {
            console.log(theform);
            //selects
            var selects = theform.find('select.wrap-register100');
            selects.each(function() {
                if ($(this).val().trim() == '') {
                    $(this).addClass('alert-validate-selects');
                }
            });

            //inputs
            var inputs = theform.find('.validate-input .register100');
            var check = true;

            for (var i = 0; i < inputs.length; i++) {
                if ($(inputs).val().trim() == '') {
                    showValidate(inputs[i]);
                    check = false;
                }
            }

            inputs.each(function() {
                $(this).focus(function() {
                    hideValidate(this);
                });
            });

            return check;
        }

        /*
         * ================================================================== 
         *    [ Angle-down-up ]
         */

        $('.angleUpDown').each(function(index, element) {
            $(this).click(function() {
                var infoformwrap = $(this).closest('div');
                //              infoformwrap.children('form').toggle();  //form toggle
                ////                 $(this).closest('div').children('form').toggle();  
                //                 var angleDown = '.angleDown:eq(' + index + ')';  //angleDownUP toggle
                //                 var angleUp = '.angleUp:eq(' + index + ')';
                //                 $(angleDown).toggle();
                //                 $(angleUp).toggle();
                //                 
                //                 if(infoformwrap.css('padding-bottom')==='48px'){ //padding-bottom toggle
                //                  pbtmp = '10px';
                //                 }else{
                //                    pbtmp = '48px';
                //                 }
                //                 infoformwrap.css('padding-bottom',pbtmp);
                //                 infoformwrap.children('div.container-login100-form-btn').toggle(); //btn toggle
                togglethefrom(infoformwrap, index);
            });
        });

        function togglethefrom(infoformwrap, index) {
            infoformwrap.children('form').toggle(); //form toggle 
            var angleDown = '.angleDown:eq(' + index + ')'; //angleDownUP toggle
            var angleUp = '.angleUp:eq(' + index + ')';
            $(angleDown).toggle();
            $(angleUp).toggle();

            if (infoformwrap.css('padding-bottom') === '48px') { //padding-bottom toggle
                pbtmp = '10px';
            } else {
                pbtmp = '48px';
            }
            infoformwrap.css('padding-bottom', pbtmp);
            infoformwrap.children('div.container-login100-form-btn').toggle(); //btn toggle
        }


        /*
         * ================================================================== 
         *    [ Info List Icon click ]
         */
        $('a.awrapBigHeadPic').click(function() {
            if ($('.info-form-wrap:eq(0)').css('padding-bottom') === '48px') {
                togglethefrom($('.info-form-wrap:eq(0)'), 0);
            }
            if ($('.info-form-wrap:eq(1)').css('padding-bottom') !== '48px') {
                togglethefrom($('.info-form-wrap:eq(1)'), 1);
            }
            if ($('.info-form-wrap:eq(2)').css('padding-bottom') === '48px') {
                togglethefrom($('.info-form-wrap:eq(2)'), 2);
            }

        });


        /*
         * ================================================================== 
         *    [ 按鈕Ajax infoProfile ]
         */

        //info按鈕1 - btninfoProfile
        $('#btninfoProfile').click(function(e) {
            e.preventDefault();
            console.log('btn - info update profile');
            var theform = $(this).parent().prev('form');
            validateAll(theform);
            if ($('.alert-validate').length === 0) {
                var formData = new FormData(theform.get(0));
                formData.append('action', 'infoUpdateProfile');
                ajax_infoUpdateProfile(formData);
            }
        });

        function ajax_infoUpdateProfile(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/lld/LldServlet2",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                    console.log("res棒");
                    if (data === 'true') {
                    	Swal.fire({
                    		icon: 'success',
                    		title: '修改成功!',
                    		showConfirmButton: false,
                    		timer: 1500
                    	})
                   }
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
        
      //info按鈕2 - btninfoPic //---------------------------------------
        $('#btninfoPic').click(function(e) {
            e.preventDefault();
            console.log('btn - info pic');
            var theform = $(this).parent().prev('form');
//            validateAll(theform);
            if ($('.picPreview').find('img').length===1) {
                var formData = new FormData(theform.get(0));
                formData.append('action', 'infoPicUpload');
//              console.log(formData);
                for (let key of formData.keys()) {
                     console.log(key + " : " + formData.get(key));
                }
                ajax_infoPicUpload(formData);
            }
        });

        function ajax_infoPicUpload(formData) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/lld/LldServlet2",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                    console.log("res棒");
                    if (data === 'true') {
                    	Swal.fire({
                    		icon: 'success',
                    		title: '上傳成功!',
                    		showConfirmButton: false,
                    		timer: 1000
                    	}).then((result) => {
                    		location.reload(true);
                    	});
                   }
                },
                error: function() {
                    console.log("真的不棒")
                }
            })
        }
        
        //info按鈕3 - btninfoChgPwd
        $('#btninfoChgPwd').click(function(e) {
            e.preventDefault();
            console.log('btn - info ChgPwd');
            var theform = $(this).parent().prev('form');
            validateAll(theform);
            var anyalert = theform.find('.alert-validate');
            if (anyalert.length === 0) {
                var formData = new FormData(theform.get(0));
                formData.append('action', 'infoChgPwd');
                ajax_infoChgPwd(formData,theform);
            }
        });

        function ajax_infoChgPwd(formData,theform) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/lld/LldServlet2",
                type: "POST",
                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function(data) { // 以上成功才執行
                	console.log("data:" + data);
                    console.log("res棒");
                    if (data === 'false') { //密碼錯誤
                        $('.wrap-validate-login').addClass('validate-input alert-validate-login');
                    } 
                    if (data === 'true') {
                    	Swal.fire({
                    		icon: 'success',
                    		title: '密碼修改成功!',
                    		text: "請用新的密碼重新登入",
                    		showConfirmButton: true,
                    		confirmButtonText: '重新登入',
                    	}).then((result) => {
                    		  if (result.isConfirmed) {
                    			  redirect();
                    		  } 
                    	});
                    	
//                    	 //inputs
//                        var inputs = theform.find('.validate-input .register100');
//                    	console.log(inputs);
//                        inputs.each(function() {
//                            $(this).val('');
//                        });
                   }
                },
                error: function() {
                    console.log("真的不棒");
                	Swal.fire({
                		icon: 'warning',
                		title: '發生錯誤',
                		text: "請稍後重新點選送出",
                	    showDenyButton: true,
                		});
                }
            });
        }
        
        function ajax_logout_ChgPwd(formData,theform) {
            $.ajax({ // 存入資料庫階段
                url: "/EA103G2/front-end/index/lld/login.jsp?action=logout_ChgPwd",
                type: "GET",
//                data: formData,
                // 告訴jQuery不要去處理髮送的資料
                processData: false,
                // 告訴jQuery不要去設定Content-Type請求頭
                contentType: false,

                success: function() { // 以上成功才執行
//                	console.log("data:" + data);
                    console.log("res棒");
                },
                error: function() {
                    console.log("真的不棒");
                }
            });
        }
        
        function redirect() {
            window.location.href = "/EA103G2/front-end/index/lld/login.jsp?action=logout_ChgPwd";
        }