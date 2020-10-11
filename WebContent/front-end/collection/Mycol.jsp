<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=1280, initial-scale=1.0" />
    <title>租屋 - 收藏列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/materialize.min.css" />
    
	<script
		src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/jquery.fancybox.min.css" /> --%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/swiper.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/style.min.css" />
</head>

<body>
			<%@ page import="com.collection.model.*"%>

<%collectionService ser = new collectionService();
			String list = ser.getCollectionVOfromTNTNO("TNT000001");
			pageContext.setAttribute("list", list);//KEY，VALUE%>

<div class="container">
<input id="input-insert-tnt" type="text">
<input id="input-insert-hos" type="text">

                       <button type="button" id="btn-insert"></button>

</div>


    <div id="wrapper">
        <div class="container">
            <h1 class="title">收藏列表</h1>
            <div id="fav-container">
                <div class="fav-head">
                    <div class="fav-search">
<!--                         <div class="input"> -->
<!--                             <input id="dir" type="checkbox" name="" /> -->
<!--                             <label for="dir">距離</label> -->
<!--                         </div> -->
                        <div class="input">
                            <select id="price" name="">
                                <option value="#" selected="selected">選擇租金</option>
                                <option value="<5000">5000以下</option>
                                <option value="BETWEEN 5000 AND 10000">5000-10000</option>
                                <option value="BETWEEN 10000 AND 15000">10000-15000</option>
                                <option value=">15000">15000以上</option>
                                
                            </select>
                        </div>
                        <div class="input">
                            <select id="report" name="">
                                <option value="#" selected="selected">選擇評價</option>
                                <option value="1">1星</option>
                                <option value="2">2星</option>
                                <option value="3">3星</option>
                                <option value="4">4星</option>
                                <option value="5">5星</option>
                            </select>
                        </div>
                        <div class="input">
                            <select id="type" name="">
                                <option value="#" selected="selected">選擇型態</option>
                                <option value="雅房">雅房</option>
                                <option value="分租套房">分租套房</option>
                                <option value="獨立套房">獨立套房</option>
                                <option value="整層住家">整層住家</option>
                            </select>
                        </div>
                        <div class="input">
                            <select id="pnum" name="">
                                <option value="#" selected="selected">選擇坪數</option>
                                <option value="<10">10坪以下</option>
                                <option value="BETWEEN 10 AND 30">10-30</option>
                                <option value="BETWEEN 30 AND 50">30-50</option>
                                <option value=">50">50坪以上</option>
                                
                            </select>
                        </div>
                    </div>
                    <div class="btns">
                        <!-- <button class="waves-effect waves-light btn checkin">我要入住</button> -->
                        <button class="waves-effect waves-light btn" id="sent">篩選</button>
                    </div>
                </div>
                <!--收藏列表-->
                <div class="fav-body">
                    <div class="body-tool">
                        <div class="input">
                            <input id="all" type="checkbox" name="" />
                            <label for="all">全選</label>
                        </div>
                        <button class="waves-effect waves-light btn delete red btn-delall"></button>
                    </div>
                    <!--第一個-->
<!--                     <div class="item"> -->
<!--                         <div class="select"> -->
<!--                             <input id="id1" type="checkbox" name="" /> -->
<!--                             <label for="id1"></label> -->
<!--                         </div> -->
<!--                         <figure><a href="../images/home.jpg" data-fancybox="data-fancybox"><img src="" alt="" /></a></figure> -->
<!--                         <figcaption class="info"> -->
<!--                             <button class="waves-effect waves-light btn delete red"></button> -->
<!--                             <div class="price"> -->
<!--                                 <h4>16,888<i>元/月</i></h4> -->
<!--                                 <h6>含第四台/網路/水費</h6> -->
<!--                             </div> -->
<!--                             <div class="date"> -->
<!--                                 <p>收藏時間</p><i>2020-09-13 13:46:11</i> -->
<!--                             </div> -->
<!--                             <h2>全新.台電大樓站.獨立陽台.明亮設計套房</h2> -->
<!--                             <ul> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>型態</span> -->
<!--                                     <p>公寓</p> -->
<!--                                 </li> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                             <br> -->
<!--                             <button class="waves-effect waves-light btn checkin">我要入住</button> -->
<!--                         </figcaption> -->
<!--                     </div> -->
<!--                     第二個 -->
<!--                     <div class="item"> -->
<!--                         <div class="select"> -->
<!--                             <input id="id2" type="checkbox" name="" /> -->
<!--                             <label for="id2"></label> -->
<!--                         </div> -->
<!--                         <figure><a href="../images/home.jpg" data-fancybox="data-fancybox"><img src="../images/home.jpg" alt="" /></a></figure> -->
<!--                         <figcaption class="info"> -->
<!--                             <button class="waves-effect waves-light btn delete red"></button> -->
<!--                             <div class="price"> -->
<!--                                 <h4>16,888<i>元/月</i></h4> -->
<!--                                 <h6>含第四台/網路/水費</h6> -->
<!--                             </div> -->
<!--                             <div class="date"> -->
<!--                                 <p>收藏時間</p><i>2020-09-13 13:46:11</i> -->
<!--                             </div> -->
<!--                             <h2>全新.台電大樓站.獨立陽台.明亮設計套房</h2> -->
<!--                             <ul> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>型態</span> -->
<!--                                     <p>公寓</p> -->
<!--                                 </li> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                             <br> -->
<!--                             <button class="waves-effect waves-light btn checkin">我要入住</button> -->
<!--                         </figcaption> -->
<!--                     </div> -->
<!--                     第三個 -->
<!--                     <div class="item"> -->
<!--                         <div class="select"> -->
<!--                             <input id="id3" type="checkbox" name="" /> -->
<!--                             <label for="id3"></label> -->
<!--                         </div> -->
<!--                         <figure><a href="../images/home.jpg" data-fancybox="data-fancybox"><img src="../images/home.jpg" alt="" /></a></figure> -->
<!--                         <figcaption class="info"> -->
<!--                             <button class="waves-effect waves-light btn delete red"></button> -->
<!--                             <div class="price"> -->
<!--                                 <h4>16,888<i>元/月</i></h4> -->
<!--                                 <h6>含第四台/網路/水費</h6> -->
<!--                             </div> -->
<!--                             <div class="date"> -->
<!--                                 <p>收藏時間</p><i>2020-09-13 13:46:11</i> -->
<!--                             </div> -->
<!--                             <h2>全新.台電大樓站.獨立陽台.明亮設計套房</h2> -->
<!--                             <ul> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>樓層</span> -->
<!--                                     <p>4F/5F</p> -->
<!--                                 </li> -->
<!--                                 <li><span>型態</span> -->
<!--                                     <p>公寓</p> -->
<!--                                 </li> -->
<!--                                 <li><span>現況</span> -->
<!--                                     <p>獨立套房</p> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                             <br> -->
<!--                             <button class="waves-effect waves-light btn checkin" data-id="0">我要入住</button> -->
<!--                         </figcaption> -->
<!--                     </div> -->
<!--                 </div> -->
                <!--收藏尾部-->
                <div class="fav-foot">
                    <ul class="pagination">
                        <li class="disabled"><a href="#!">
                                </a> </li> <li class="active"><a href="#!">1</a></li>
                        <li class="waves-effect"><a href="#!">2</a></li>
                        <li class="waves-effect"><a href="#!">3</a></li>
                        <li class="waves-effect"><a href="#!">4</a></li>
                        <li class="waves-effect"><a href="#!">5</a></li>
                        <li class="waves-effect"><a href="#!">></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
<div id="notice">
    <p> </p>
</div>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.fancybox.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.cookie.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/materialize.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/app.min.js"></script>
<script type="text/javascript">

obj= JSON.parse('${list}');
var hosNo=[];
$("#btn-insert").click(function(){//新增收藏方法
	$.ajax({//存入資料庫階段
		  url:"collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"insert",
	 		  tnt:$("#input-insert-tnt").val(),
	 		  hos:$("#input-insert-hos").val()
	 		   //JSON.stringify({})
	 	  },
	 	  success:function(data)
	 	  {
	 		  console.log("res棒")
	 	  }//以上成功才執行

	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }
	  
	  })
})

$("#sent").click(function(){
	$.ajax({//存入資料庫階段
		  url:"collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"search",
	 		  tnt:"TNT000001",//-----------------實際需抓會員號碼近來用
			  price:$("#price").val(),
			  report:$("#report").val(),
			  type:$("#type").val(),
			  pnum:$("#pnum").val()
			  
	 		   //JSON.stringify({})
	 	  },
	 	  success:function(data)
	 	  {
	 		 obj= JSON.parse(data);
	 		loading();
	 		 }//以上成功才執行

	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }
	  
	  })
})
	

 $(document).on("click", ".btn-del", function() {
       console.log($(this).val()+"oo");
	var hosno=$(this).val();	
	
	$.ajax({//存入資料庫階段
		  url:"collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"delete",
	 		  tnt:"TNT000001",//-----------------實際需抓會員號碼近來用
	 		  hos:hosno
	 		   //JSON.stringify({})
	 	  },
	 	  success:function(data)
	 	  {	
	 	 $("[id='"+hosno+"']").remove();
	 		  console.log("res棒")
	 	  }//以上成功才執行

	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }
	  
	  })
})
//href='../images/home.jpg' data-fancybox='data-fancybox' 移除A標籤裡的這段

$(document).on("click", ".btn-delall", function() {
	var hosarr=[];
	hosNo.forEach(function(value){
		if($("#del-"+value+"").prop( "checked" ))
		{
			hosarr.push(value);
		}
	});
	
	$.ajax({//存入資料庫階段
		  url:"collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"deleteall",
	 		  tnt:"TNT000001",//-----------------實際需抓會員號碼近來用
	 		 hos:JSON.stringify(hosarr)
	 		   //JSON.stringify({})
	 	  },
	 	  success:function(data)
	 	  {	
	 		 hosarr.forEach(function(value){
	 			$("[id='"+value+"']").remove();
	 		 });
	 		  console.log("res棒")
	 	  }//以上成功才執行

	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("真的不棒")
	 	  }
	  
	  })
})







function loading (){
	$(".item").remove();
	
$.each(obj, function(key, value) {
	console.log(value);
	hosNo.push(value.HOS_NO);
$(".fav-body").append("<div class='item' id='"+value.hos_no+"' >"+
                        "<div class='select'>"+
                            "<input id='del-"+value.hos_no+"' type='checkbox' name='' />"+
                            "<label for='del-"+value.hos_no+"'></label>"+
                        "</div>"+
                        "<figure><a><img src='data:image/png;base64,"+value.hos_pic+"' alt='' /></a></figure>"+
                        "<figcaption class='info'>"+
                            "<button class='waves-effect waves-light btn delete red btn-del' value='"+value.hos_no+"'></button>"+
                            "<div class='price'>"+
                               "<h4>"+value.hos_rentfee+"<i>元/月</i></h4>"+
                                "<h6>含第四台/網路/水費</h6>"+
                            "</div>"+
                            "<div class='date'>"+
                                "<p>收藏時間</p><i>"+value.col_date+"</i>"+
                            "</div>"+
                            "<h2>"+value.hos_name+"</h2>"+
                            "<ul>"+
                                "<li><span>現況</span>"+
                                    "<p>"+value.hos_room+"</p>"+
                                "</li>"+
                                "<li><span>樓層</span>"+
                                    "<p>"+value.hos_floor+"</p>"+
                                "</li>"+
                                "<li><span>坪數</span>"+
                                    "<p>"+value.hos_pnum+"</p>"+
                                "</li>"+
                                "<li><span>型態</span>"+
                                    "<p>"+value.hos_type+"</p>"+
                                "</li>"+
                                "<li><span>最短租期</span>"+
                                    "<p>"+value.hos_mindate+"</p>"+
                               " </li>"+
                            "</ul>"+
                            "<br>"+
                            "<button class='waves-effect waves-light btn checkin'>我要入住</button>"+
                        "</figcaption>"+
                    "</div>");});
}
loading ();
</script>

</html>