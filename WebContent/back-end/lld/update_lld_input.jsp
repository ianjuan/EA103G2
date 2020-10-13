<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.lld.model.*"%>
<%
  LldVO lldVO = (LldVO) request.getAttribute("lldVO"); //EmpServlet.java (Concroller) 存入req的lldVO物件 (包括幫忙取出的lldVO, 也包括輸入資料錯誤時的lldVO物件)
  %>
<%String[] cities = {"台北市", "新北市", "基隆市", "桃園市", "新竹縣", "新竹市", "苗栗縣", "台中市", "南投縣", "彰化縣", "雲林縣", "嘉義縣", "嘉義市", "台南市", "高雄市", "屏東縣", "宜蘭縣", "花蓮縣", "台東縣", "澎湖縣", "金門縣", "連江縣"};%>
<%String[] accStatuses = {"未啟用", "已啟用", "帳號失效"};%>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>房東資料修改 - update_lld_input.jsp</title>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <style>
        table#table-1 {
       background-color: #CCCCFF;
       border: 2px solid black;
       text-align: center;
     }
     table#table-1 h4 {
      color: red;
      display: block;
      margin-bottom: 1px;
    }
    h4 {
      color: blue;
      display: inline;
    }
  </style>
    <style>
        table {
     width: 450px;
     background-color: white;
     margin-top: 1px;
     margin-bottom: 1px;
   }
   table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>

<body bgcolor='white'>
    ${lldVO.lld_dist!=null}
    <table id="table-1">
        <tr>
            <td>
                <h3>房東資料修改 - update_lld_input.jsp</h3>
                <h4><a href="<%=request.getContextPath()%>/back-end/lld/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
            </td>
        </tr>
    </table>
    <h3>資料修改:</h3>
    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" name="update_lld_input" enctype="multipart/form-data">
        <table>
            <tr>
                <td>房東編號:<font color=red><b>*</b></font>
                </td>
                <td>
                    <%=lldVO.getLld_no()%>
                </td>
            </tr>
            <tr>
                <td>信箱:</td>
                <td><input type="text" name="lld_email" value="<%=lldVO.getLld_email()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>帳號:</td>
                <td><input type="text" name="lld_acc" value="<%=lldVO.getLld_acc()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>密碼:</td>
                <td><input type="password" name="lld_pwd" value="<%=lldVO.getLld_pwd()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>身分證:</td>
                <td><input type="text" name="lld_id" size="10" value="<%=lldVO.getLld_id()%>" /></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="lld_name" value="<%=lldVO.getLld_name()%>"></td>
            </tr>
            <tr>
                <td>生日:</td>
                <td><input type="text" name="lld_birth" id="f_date1" /></td>
            </tr>
            <td>性別:</td>
            <td>
                <input type="radio" name="lld_sex" required value=true ${(lldVO.lld_sex)?'checked':''}>男
                <input type="radio" name="lld_sex" required value=false ${!(lldVO.lld_sex)?'checked':''}>女
            </td>
            </tr>
            <tr>
                <td>手機:</td>
                <td><input type="text" name="lld_mobile" value="<%=lldVO.getLld_mobile()%>" /></td>
            </tr>
            <tr>
                <td>縣市:</td>
                <td>
                    <select name="lld_city" id="lld_city">
                        <!--如果原始資料有lldVO.lld_city, 增加第一個選項"選擇縣市", 用value傳給js判斷原始資料是哪個原始資料, -->
                        <!--設為selected, 判斷完、新增完選項, 再把"選擇縣市"的value值改成0 -->
                        <!--如果沒有原始資料, 不新增"選擇縣市", 在js"選擇縣市"跟所有縣市一起新增-->
                        <c:if test="${lldVO.lld_city!=null}">
                            <option value="${lldVO.lld_city}" id="city_default">選擇縣市
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>區域:</td>
                <td>
                    <select name="lld_dist" id="lld_dist">
                        <c:if test="${lldVO.lld_dist!=null}">
                            <option value="${lldVO.lld_dist}" id="dist_default">選擇區域
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>地址:</td>
                <td><input type="text" name="lld_add" value="<%=lldVO.getLld_add()%>" /></td>
            </tr>
            <tr>
                <td>帳號狀態:</td>
                <td>
                    <select name="lld_status">
                        <c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName">
                            <option value="${lldVO.lld_status}" ${(lldVO.lld_status==varStatusName.index)?'selected':'' }>${accStatus}
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>加入時間:</td>
                <td>
                    <fmt:formatDate value="${lldVO.lld_jointime}" pattern="yyyy-mm-dd" />
                </td>
            </tr>
            <tr>
                <td><label for="myfiles">大頭貼:</label></td>
                <td>
                    <div id="container_pic">
                        <div id="upload"><input id="myfiles" type="file" name="lld_pic" accept="image/*" multiple="multiple">
                            <button id="remove">刪除</button>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="lld_no" value="${lldVO.lld_no}">
        <input type="submit" value="送出修改">
    </FORM>
    <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
    <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
    <style>
        .xdsoft_datetimepicker .xdsoft_datepicker {
            width: 300px;
            /* width:  300px; */
        }
        .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px;
            /* height:  151px; */
        }
    </style>
    <script>
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
    </script>
    <script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
            theme: '', //theme: 'dark',
            timepicker: false, //timepicker:true,
            step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
            format: 'Y-m-d', //format:'Y-m-d H:i:s',
            value: '<%=lldVO.getLld_birth()%>', // value:   new Date(),
        });


        function init() {
            var container_pic = document.getElementById("container_pic");
            var myfiles = document.getElementById("myfiles");
            myfiles.addEventListener('change', function() {
                var files = myfiles.files;
                if (files !== null) {
                    for (let i = 0; i < files.length; i++) {
                        if (files[i].type.indexOf('image') > -1) {
                            var reader = new FileReader();
                            reader.addEventListener('load', function(e) {
                                pre = document.getElementsByClassName("preview");
                                if (typeof(pre[0]) !== "undefined") {
                                    $(".preview").empty();
                                }
                                var div = document.createElement('div');
                                div.setAttribute("class", "preview");
                                innerElement = '<div class="checkbox"><input id="label' + i + '""' +
                                    ' type="checkbox" name="img' + i + '""' +
                                    ' class="check"></div><label for="label' + i + '""' +
                                    '> <img id="img' + i + '""' + ' src="' + e.target.result +
                                    '"></label>';
                                div.innerHTML = innerElement;
                                container_pic.append(div);
                            });
                            reader.readAsDataURL(files[i]); //****** trigger the action to read the file
                        }
                    }
                }
            });
            var remove = document.getElementById("remove");
            remove.addEventListener('click', function(e) {
                var checkbox = document.getElementsByClassName("checkbox");
                for (var i = checkbox.length - 1; i >= 0; i--) {
                    console.log(checkbox[i])
                    if (checkbox[i].children[0].checked)
                        checkbox[i].parentElement.remove();
                }
                e.preventDefault();
            });
        }

        window.onload = init;

        $(document).ready(function() {
            var city_key = Object.keys(area_data);
            //若無原始資料, 把所有縣市選項放上去, 區域放選區域
            if ($('#lld_city').val() === null) {
                $("#lld_city").append('<option value="0" id="city_default">選擇縣市');
                city_key.forEach(function(item, index, array) {
                    $("#lld_city").append('<option value="' + item + '">' + item);
                });
                $("#lld_dist").append('<option value="0" id="dist_default">選擇區域');

                // 若有原始縣市資料, 放上所有縣市選項, 且該縣市為selected
                // 再放上該縣市所有區域選項, 該區域為selected
            } else {
                var index_city;
                city_key.forEach(function(item, index, array) {
                    if (item === $('#lld_city').val()) {
                        var tmp = ' selected';
                        index_city = index;
                    }
                    $("#lld_city").append('<option value="' + item + '"' + tmp + '>' + item);

                });
                $('#city_default').val("0");
                var dist_values_selectedcity = Object.values(area_data)[index_city];
                dist_values_selectedcity.forEach(function(item, index, array) {
                    var tmp = (item === $('#lld_dist').val()) ? ' selected' : '';
                    $("#lld_dist").append('<option value="' + item + '"' + tmp + '>' + item);
                });
                $('#dist_default').val("0");
            }

            //選擇縣市, 區域連動
            $("#lld_city").change(function() {
                city_key.forEach(function(item, index, array) {
                    if (item === $("#lld_city").val()) {
                        $("#dist_default ~ option").remove();
                        var dist_values = Object.values(area_data)[index];
                        dist_values.forEach(function(item, index, array) {
                            $("#lld_dist").append(
                                '<option value="' + item + '">' + item);
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>