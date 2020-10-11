<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=1280, initial-scale=1.0" />
    <title>租屋 - 房屋資訊</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/materialize.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/jquery.fancybox.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/swiper.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/style.min.css" />
<!-- 	<script -->
<!--   src="https://code.jquery.com/jquery-3.5.1.js" -->
<!--   integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" -->
<!--   crossorigin="anonymous"></script> -->
  <script
		src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
  
</head>
<%@ page import="com.housedet.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.*" %>
<%HouseDetService hds =new HouseDetService();
Gson gson = new Gson();
 	List<HosDetVO> list = hds.getHosDetfromHOSNO("HOS000015");
 	HosDetVO vo=(HosDetVO)list.get(0);
	pageContext.setAttribute("picnum",gson.toJson(vo.getHos_pic().size()));
%> 
<body>
    <div id="wrapper">
        <div class="container">
            <div class="row">
                <!--左側內容-->
                <div class="col s8" id="left-site">
                    <!--標題-->
                    <h1 class="title"><%= vo.getHos_name() %></h1>
                    <h6><%= vo.getHos_add() %></h6>
                    <!--圖片-->
                    <div class="pic-container">
                        <!--大圖-->
                        <div class="swiper-container gallery-top">
                            <div class="swiper-wrapper">
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(0) %>" data-fancybox="data-fancybox"><img src='<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(1) %>' alt="slide-1" /></a></div> --%>
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(1) %>" data-fancybox="data-fancybox"><img src="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(1) %>" alt="slide-2" /></a></div> --%>
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(0) %>" data-fancybox="data-fancybox"><img src="<%=request.getContextPath()%>/resource/dist/images/home3.jpg" alt="slide-3" /></a></div> --%>
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(0) %>" data-fancybox="data-fancybox"><img src="<%=request.getContextPath()%>/resource/dist/images/home.jpg" alt="slide-4" /></a></div> --%>
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHOS_PIC().get(0) %>" data-fancybox="data-fancybox"><img src="<%=request.getContextPath()%>/resource/dist/images/home2.jpg" alt="slide-5" /></a></div> --%>
<%--                                 <div class="swiper-slide"><a href="<%=request.getContextPath()%>/resource/Mycol/images/home3.jpg" data-fancybox="data-fancybox"><img src="<%=request.getContextPath()%>/resource/dist/images/home3.jpg" alt="slide-6" /></a></div> --%>
                            </div>
                            <div class="swiper-pagination"></div>
                            <div class="swiper-button-next swiper-button-white"></div>
                            <div class="swiper-button-prev swiper-button-white"></div>
                        </div>
                        <!--小圖-->
                        <div class="swiper-container gallery-thumbs">
                            <div class="swiper-wrapper">
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home.jpg" alt="slide-1" /></div> --%>
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home2.jpg" alt="slide-2" /></div> --%>
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home3.jpg" alt="slide-3" /></div> --%>
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home.jpg" alt="slide-4" /></div> --%>
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home2.jpg" alt="slide-5" /></div> --%>
<%--                                 <div class="swiper-slide"><img src="<%=request.getContextPath()%>/resource/Mycol/images/home3.jpg" alt="slide-6" /></div> --%>
                            </div>
                        </div>
                    </div>
                    <!--條件-->
                    <div class="info condition">
                        <ul>
                            <li><span>押金</span>
                                <p>二個月</p>
                            </li>
                            <li><span>車 位</span>
                                <p><%= vo.getHos_park() %></p>
                            </li>
                            <li><span>管理費</span>
                                <p id="fee">NT$1,000</p>
                            </li>
                            <li><span>最短租期</span>
                                <p id="MRP"><%= vo.getHos_mindate() %></p>
                            </li>
                            <li><span>開伙</span>
                                <p><%= vo.getHos_cook() %></p>
                            </li>
                            <li><span>養寵物</span>
                                <p><%= vo.getHos_pet() %></p>
                            </li>
                            <li><span>身份要求</span>
                                <p><%= vo.getHos_iden() %></p>
                            </li>
                            <li><span>性別要求</span>
                                <p><%= vo.getHos_sex() %></p>
                            </li>
                            <li><span>可遷入日</span>
                                <p><%= vo.getHos_mdate() %></p>
                            </li>
                            <li><span>格局</span>
                                <p><%= vo.getHos_pat() %></p>
                            </li>
                      
                        </ul>
                    </div>
                    <!--房東提供-->
                    <div class="info provide">
                        <h2>房東提供</h2>
                        <ul>
                            <li>
                                <p class=<%= vo.getHos_table() ==0 ? "no":"yes" %>  >桌子</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_chair() ==0 ? "no":"yes" %> >椅子</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_closet() ==0 ? "no":"yes" %> >衣櫃</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_bed() ==0 ? "no":"yes" %> >床</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_sofa() ==0 ? "no":"yes" %> >沙發</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_hoter() ==0 ? "no":"yes" %> >熱水器</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_tv() ==0 ? "no":"yes" %> >電視</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_refrig() ==0 ? "no":"yes" %> >冰箱</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_table() ==0 ? "no":"yes" %> >冷氣</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_wash() ==0 ? "no":"yes" %> >洗衣機</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_net() ==0 ? "no":"yes" %> >網路</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_drink() ==0 ? "no":"yes" %>>銀水雞</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_forth() ==0 ? "no":"yes" %>>第四台</p>
                            </li>
                            <li>
                                <p class=<%= vo.getHos_gas() ==0 ? "no":"yes" %>>天然瓦斯</p>
                            </li>
                        </ul>
                    </div>
                    <!--生活交通-->
                    <div class="info traffic">
                        <h2>生活交通</h2>
                        <ul class="full">
                            <li><span>生活機能</span>
                                <p><%= vo.getHos_liffun()==null ? "待屋主提供資訊": vo.getHos_liffun() %></p>
                            </li>
                            <li><span>附近交通</span>
                                <p><%= vo.getHos_trans()==null ? "待屋主提供資訊": vo.getHos_trans() %></p>
                            </li>
                        </ul>
                    </div>
                    <!--屋況說明-->
                    <div class="info detail">
                        <h2>屋況說明</h2>
                        <p>回家的位置:<br><br>《捷運》走路7分鐘就到台電大樓站。<br><br>《公園綠地》走路2分鐘有泰順公園，運動、休閒的好去處...假日想要運動久一點，可以到大安森林公園或是到國立台灣大學校園，吸取最高學府的日月精華，感受菁英輩出的人文氣息。<br><br>《便利》走路2分鐘就有小七、全聯，生活購物超方便，附近有許多琳瑯滿目的美食，放假走出門就可以買買逛逛回家展現廚藝，就算不想煮，師大夜市也只要走路5分鐘就到，都可以滿足喔!!<br><br>回家多舒服<br><br>一開門映入眼簾是寬闊舒適的雙人沙發休閒茶几區，還有我們的雙人獨立筒床墊休憩區，對面還有可以埋頭苦幹的工作區及大衣櫥都幫你準備好囉!!最後來到獨立陽台完整三合一陽台門大面採光，想全關可以隔絕聲音，打開也可以空氣流通，這樣的空間，陰雨綿綿都很舒服，難得一見房東貼心設計師用心好套房!!<br><br>回家有多好<br><br>公用空間固定清潔服務、公共空間感應照明、24小時公用空間走道監控設備、住戶單純、精華地區、鬧中取靜。<br><br>房東要求住戶注意生活品質，皆經篩選，若有不良嗜好者，會影響其它房客生活者勿試。我們的房客都非常優質，工作正當，也歡迎單純的上班族或學生入住。我們的套房住房期間，提供優質的租後服務。</p>
                    </div>
                    <!--地圖-->
                    <div class="info">
                        <h2>地圖位置</h2>
                        <div class="map">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3615.396775446296!2d121.52616831500599!3d25.020605483978564!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442a98e596b4a89%3A0xeeee920255b63d69!2z5Y-w6Zu75aSn5qiT56uZ!5e0!3m2!1szh-TW!2stw!4v1600146985805!5m2!1szh-TW!2stw" frameborder="0"></iframe>
                        </div>
                    </div>
                    <div class="info" id="message">
                        <h2>房屋問答</h2>
                        <ul class="full">
                            <li>
                                <div class="date">2020-09-13 13:46:11</div>
                                <div class="reply">
                                    <h4>
                                        <p>感謝您的提問，房東將儘快給您回覆！</p><em>（NO.2157079）</em>
                                    </h4>
                                    <div class="anwser">
                                        <p>包含管理費，且社區倒垃圾，及代收包裹。</p>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="date">2020-09-13 13:46:11</div>
                                <div class="reply">
                                    <h4>
                                        <p>感謝您的提問，房東將儘快給您回覆！</p><em>（NO.2157079）</em>
                                    </h4>
                                    <div class="anwser">
                                        <p>包含管理費，且社區倒垃圾，及代收包裹。</p>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <h3>我要提問<span>（限500字內）</span></h3>
                        <div class="txt-area">
                            <div class="nums"> <i>0</i>/500</div>
                            <textarea name="" placeholder="請在此輸入您的提問，需要刊登者回覆後才會在頁面上顯示出來，為保障您的個資權益，請勿留下您的個人資料！"></textarea>
                        </div>
                        <button class="waves-effect waves-light btn-large" id="sent">送出留言</button>
                    </div>
                </div>
                <!--右側內容-->
                <div class="col s4" id="right-site">
                    <!--價格簡述-->
                    <div class="info description">
                        <div class="fav">
                            <p>收藏</p><img src="<%=request.getContextPath()%>/resource/Mycol/images/star.svg" alt="" />
                        </div>
                        <div class="price">
                            <h4><%= vo.getHos_rentfee() %><i>元/月</i></h4>
                            
                        </div>
                        <ul class="full">
                            <li><span>坪數</span>
                                <p>10坪</p>
                            </li>
                            <li><span>樓層</span>
                                <p>4F/5F</p>
                            </li>
                            <li><span>型態</span>
                                <p>公寓</p>
                            </li>
                            <li><span>現況</span>
                                <p>獨立套房</p>
                            </li>
                        </ul>
                    </div>
                    <!--聯絡資訊-->
                    <div class="info contact">
                        <div class="person">
                            <div class="head"><img src="<%=request.getContextPath()%>/resource/Mycol/images/person_<%= vo.getLld_sex().equals("0") ? "women":"man" %>.png" alt="" /></div>
                            <p><%= vo.getLld_name() %><i>（歡迎來電詢問 預約看屋）</i></p>
                        </div>
                        <div class="phone"><a ><img src="<%=request.getContextPath()%>/resource/Mycol/images/phone.svg" alt="" />
                                <p><%= vo.getLld_mobile() %></p>
                            </a></div>
                        <div class="functions"><a class="no-service" href="#"><img src="<%=request.getContextPath()%>/resource/Mycol/images/tel.svg" alt="" />預約看屋</a><a href="#message"> <img src="<%=request.getContextPath()%>/resource/Mycol/images/comment.svg" alt="" />留言</a><a class="red" href="#"><img src="<%=request.getContextPath()%>/resource/Mycol/images/report.svg" alt="" />檢舉</a>
                            <a class="full" href="#"><img src="<%=request.getContextPath()%>/resource/Mycol/images/report.svg" alt="" />我要入住</a>
                        </div>
                    </div>
                    <!--提醒-->
                    <div class="info tips">
                        <p><img src="<%=request.getContextPath()%>/resource/Mycol/images/error.svg" alt="" />愛租網提醒您: 在簽訂線上租賃契約之前,請詳閱合約內容,以免上當受騙.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script> 

var picnum = JSON.parse('${picnum}');


<%for (int i = 0; i < vo.getHos_pic().size(); i++) {%>		

$(".gallery-top .swiper-wrapper").append(
"<div class='swiper-slide'>"+

"<a href='<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHos_pic().get(i) %>'" +
		
"data-fancybox='data-fancybox'>"+"<img src='<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHos_pic().get(i) %>' "+
"alt='slide-1' /></a></div>"
	)
	
$(".gallery-thumbs .swiper-wrapper").append(
		"<div class='swiper-slide'>"+
		"<img src='<%=request.getContextPath()%>/hos/hosPic?pic=<%= vo.getHos_pic().get(i) %>' alt='slide-1' />"+
		"</div>"
	)	
	<%}%>


console.log(picnum);

</script>



 
<div id="notice">
    <p> </p>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.fancybox.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.cookie.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/materialize.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/swiper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/Mycol/js/app.min.js"></script>
</div>

</html>