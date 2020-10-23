<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=1280, initial-scale=1.0" />
    <title>租屋 - 收藏列表</title>
               <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/dist/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/materialize.min.css" />
    
	<script
		src="<%=request.getContextPath()%>/resource/datetimepicker/jquery.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/jquery.fancybox.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/swiper.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Mycol/css/style.min.css" />
    		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/navbar/navbar.css">
    
</head>
<style> .btn-img {
            background-image: "https://www.flaticon.com/svg/static/icons/svg/236/236831.svg";
        }

        .bg-light {
            background-color: #D7C8B6 !important;
        }

        .navbar-light .navbar-nav .nav-link {
            color: #393939 !important;
            font-weight: bold;
        }

        .nav-item .member {
            display: flex;
            margin-left: 10px;
            margin-top:8px;
            color:#393939 !important;		
        }

        .nav-item .member .memberpic {
            width: 40px;
            margin-right: 5px;
            outline: none;
        }

        .nav-item .member .membername {
            float: right;
            align-self: center;
            display: block;
        }
        @media ( max-width :991px) {
        .navbar{
        height:auto;}}
        /* 以上為NAVBAR*/</style>
<body>
			<%@ page import="com.collection.model.*"%>

<%collectionService ser = new collectionService();
String	tntno=(String)session.getAttribute("tnt_no");

			String list = ser.getCollectionVOfromTNTNO(tntno);
			pageContext.setAttribute("list", list);//KEY，VALUE%>

  	 <div><jsp:include page="/front-end/navbar/navbar.jsp"/> </div>



    <div id="wrapper">
        <div class="container">
            <h1 class="title">收藏列表</h1>
            <div id="fav-container">
                <div class="fav-head">
                    <div class="fav-search">

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
	<script
		src="<%=request.getContextPath()%>/resource/dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.fancybox.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/jquery.cookie.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/materialize.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/Mycol/js/app.min.js"></script>

<script type="text/javascript">

obj= JSON.parse('${list}');
var hosNo=[];
// $("#btn-insert").click(function(){//新增收藏方法
// 	$.ajax({//存入資料庫階段
// 		  url:"collectionServlet",
// 	 	  type:"POST",
// 	 	  data:{ action:"insert",
// 	 		  tnt:$("#input-insert-tnt").val(),
// 	 		  hos:$("#input-insert-hos").val()
// 	 		   //JSON.stringify({})
// 	 	  },
// 	 	  success:function(data)
// 	 	  {
// 	 		  console.log("res棒")
// 	 	  }//以上成功才執行

// 	 	  ,
// 	 	  error:function(data)
// 	 	  {
// 	 		  console.log("真的不棒")
// 	 	  }
	  
// 	  })
// })

$("#sent").click(function(){
	$.ajax({//存入資料庫階段
		  url:"<%=request.getContextPath()%>/collection/collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"search",
	 		  tnt:"<%= tntno%>",//-----------------實際需抓會員號碼近來用
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
		  url:"<%=request.getContextPath()%>/collection/collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"delete",
	 		  tnt:"<%= tntno %>",//-----------------實際需抓會員號碼近來用
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
		  url:"<%=request.getContextPath()%>/collection/collectionServlet",
	 	  type:"POST",
	 	  data:{ action:"deleteall",
	 		  tnt:"<%= tntno %>",//-----------------實際需抓會員號碼近來用
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
                           " <div class='contact'>"+
                            "<div class='functions'>"+
                           "<a href='<%=request.getContextPath()%>/HouseDet/HouseDetServlet?hos="+value.hos_no+"'><img src='<%=request.getContextPath()%>/resource/Mycol/images/tel.svg' alt=''/>預約看屋</a>"+
                            "<a class='select-live' href='#'><img src='<%=request.getContextPath()%>/resource/Mycol/images/home.svg' alt=''/>我要入住</a>"+
                            "<a href='#message'> <img src='<%=request.getContextPath()%>/resource/Mycol/images/comment.svg' alt=''/>聊天</a>"+
                           " <a class='red' href='#'><img src='<%=request.getContextPath()%>/resource/Mycol/images/report.svg' alt=''/>檢舉</a>"+
                            "</div>"+
                        "  </div>"+

                        "</figcaption>"+
                    "</div>");});
}
loading ();
</script>

</html>