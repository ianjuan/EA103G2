<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>房屋評價</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/house_manage/css/house_evaluation.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/house_manage/js/house_evaluation.js" charset="UTF-8"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.3.4/gsap.min.js'></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

	<!-- 評價房屋按鈕 -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">評價房屋</button>
			
	<!-- 評價房屋區塊 -->			
	<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="evaluation" name="evaluation">
					<div class="modal-header">
					    <marquee scrollamount="10" class="evatitle">來對房屋作評價吧~~</marquee>
					</div>
					<div class="modal-body">
						<ul>
							<li>
						    	<div class="item">			       				
						       		<ul class="rating">								   										
										<li><button type="button" value="5" onclick="eqpmtPoint(event)"><div class="star" value="5" onclick="eqpmtPoint(event)"></div></button></li>
										<li><button type="button" value="4" onclick="eqpmtPoint(event)"><div class="star" value="4" onclick="eqpmtPoint(event)"></div></button></li>
										<li><button type="button" value="3" onclick="eqpmtPoint(event)"><div class="star" value="3" onclick="eqpmtPoint(event)"></div></button></li>
										<li><button type="button" value="2" onclick="eqpmtPoint(event)"><div class="star" value="2" onclick="eqpmtPoint(event)"></div></button></li>
										<li><button type="button" value="1" onclick="eqpmtPoint(event)"><div class="star" value="1" onclick="eqpmtPoint(event)"></div></button></li>
										<span class="logo">
											<img src="https://www.flaticon.com/svg/static/icons/svg/169/169302.svg">
											<span class="itemtitle">設備齊全 :</span>
										</span>
									</ul>
						       	</div>
						    </li>
						    <li>
						       	<div class="item">			       				
						       		<ul class="rating">								   
										<li><button type="button" value="5" onclick="convmtPoint(event)"><div class="star" value="5" onclick="convmtPoint(event)"></div></button></li>
										<li><button type="button" value="4" onclick="convmtPoint(event)"><div class="star" value="4" onclick="convmtPoint(event)"></div></button></li>
										<li><button type="button" value="3" onclick="convmtPoint(event)"><div class="star" value="3" onclick="convmtPoint(event)"></div></button></li>
										<li><button type="button" value="2" onclick="convmtPoint(event)"><div class="star" value="2" onclick="convmtPoint(event)"></div></button></li>
										<li><button type="button" value="1" onclick="convmtPoint(event)"><div class="star" value="1" onclick="convmtPoint(event)"></div></button></li>
										<span class="logo">
											<img src="https://www.flaticon.com/svg/static/icons/svg/168/168466.svg">
											<span class="itemtitle">周遭機能 :</span>
										</span>
									</ul>
						       	</div>
						    </li>
						   	<li>
						       	<div class="item">
						       		<ul class="rating">								   
										<li><button type="button" value="5" onclick="neiborPoint(event)"><div class="star" value="5" onclick="neiborPoint(event)"></div></button></li>
										<li><button type="button" value="4" onclick="neiborPoint(event)"><div class="star" value="4" onclick="neiborPoint(event)"></div></button></li>
										<li><button type="button" value="3" onclick="neiborPoint(event)"><div class="star" value="3" onclick="neiborPoint(event)"></div></button></li>
										<li><button type="button" value="2" onclick="neiborPoint(event)"><div class="star" value="2" onclick="neiborPoint(event)"></div></button></li>
										<li><button type="button" value="1" onclick="neiborPoint(event)"><div class="star" value="1" onclick="neiborPoint(event)"></div></button></li>									    
										<span class="logo">
											<img src="https://www.flaticon.com/svg/static/icons/svg/263/263058.svg">
											<span class="itemtitle">友善鄰居 :</span>
										</span>
									</ul>
						       	</div>
						     </li>					
						     <li>
						       	<div class="item">
							       	<span class="logo" style="float:left;margin-top:5px;">
										<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
										<span class="itemtitle">評論 :</span>
									</span>
							       	<textarea rows="2" wrap="hard" onkeyup="checkLen(this)" name="hcm_commnt"></textarea>
									<div class="fontstyle">您還可以輸入 <span id="count">50</span> 個文字</div>			       		
						       	</div>
						     </li>
						</ul>
					</div>
					<div class="modal-footer">			        					
						<button type="button" class="btn btn-primary" onclick="notice()">送出</button>					
						<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
			
	<!-- 房屋所有評價按鈕 -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">房屋所有評價</button>
			
	<!-- 房屋所有評價 -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
			    	<span class="allhousetitle">房屋所有評價</span>
			    </div>
				<div class="modal-body">
					<div id="accordion">
						<!-- 每篇評價 -->
					  	<div class="card">
					    	<div class="card-header" id="headingOne">
					      		<h5 class="mb-0">
							        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva1" aria-expanded="false" aria-controls="collapseOne" style="float:left;">彭于晏</button>
							        <span class="listlogo">
										<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
										<span class="avgpoint">4.5分</span>
									</span>
							        <span class="evatime">2020/10/23</span>
					     		 </h5>
					    	</div>					
					    <div id="eva1" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
					      	<div class="card-body">
					        	<ul>
									<li>
								    	<div class="item">			       				
								       		<ul class="rating">								   										
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/169/169302.svg">
													<span class="itemtitle">設備齊全 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								    <li>
								       	<div class="item">			       				
								       		<ul class="rating">								   
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/168/168466.svg">
													<span class="itemtitle">周遭機能 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								   	<li>
								       	<div class="item">
								       		<ul class="rating">								   								    
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263058.svg">
													<span class="itemtitle">友善鄰居 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								     </li>					
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
												<span class="itemtitle">房客評論 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>我要住這裡一輩子</textarea>			       		
								       	</div>
								     </li>
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
												<span class="itemtitle">房東回覆 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>很高興您喜歡</textarea>			       		
								       	</div>
								     </li>
								</ul>
					      	</div>
						</div>
					</div>
					
					<!-- 每篇評價 -->
					  	<div class="card">
					    	<div class="card-header" id="headingOne">
					      		<h5 class="mb-0">
							        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva2" aria-expanded="false" aria-controls="collapseOne" style="float:left;">金城武</button>
							        <span class="listlogo">
										<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
										<span class="avgpoint">3分</span>
									</span>
							        <span class="evatime">2020/10/23</span>
					     		 </h5>
					    	</div>					
					    <div id="eva2" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
					      	<div class="card-body">
					        	<ul>
									<li>
								    	<div class="item">			       				
								       		<ul class="rating">								   										
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/169/169302.svg">
													<span class="itemtitle">設備齊全 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								    <li>
								       	<div class="item">			       				
								       		<ul class="rating">								   
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/168/168466.svg">
													<span class="itemtitle">周遭機能 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								   	<li>
								       	<div class="item">
								       		<ul class="rating">								   								    
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263058.svg">
													<span class="itemtitle">友善鄰居 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								     </li>					
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
												<span class="itemtitle">房客評論 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>還好啦 覺得普普</textarea>			       		
								       	</div>
								     </li>
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
												<span class="itemtitle">房東回覆 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>喔</textarea>			       		
								       	</div>
								     </li>
								</ul>
					      	</div>
						</div>
					</div>
					
					<!-- 每篇評價 -->
					  	<div class="card">
					    	<div class="card-header" id="headingOne">
					      		<h5 class="mb-0">
							        <button type="button" class="btn btn-outline-info" data-toggle="collapse" data-target="#eva3" aria-expanded="false" aria-controls="collapseOne" style="float:left;">韓國瑜</button>
							        <span class="listlogo">
										<img src="https://www.flaticon.com/svg/static/icons/svg/263/263143.svg">
										<span class="avgpoint">1分</span>
									</span>
							        <span class="evatime">2020/10/23</span>
					     		 </h5>
					    	</div>					
					    <div id="eva3" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
					      	<div class="card-body">
					        	<ul>
									<li>
								    	<div class="item">			       				
								       		<ul class="rating">								   										
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/169/169302.svg">
													<span class="itemtitle">設備齊全 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								    <li>
								       	<div class="item">			       				
								       		<ul class="rating">								   
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/168/168466.svg">
													<span class="itemtitle">周遭機能 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								    </li>
								   	<li>
								       	<div class="item">
								       		<ul class="rating">								   								    
												<span class="logo">
													<img src="https://www.flaticon.com/svg/static/icons/svg/263/263058.svg">
													<span class="itemtitle">友善鄰居 :</span>
													<img src="https://www.flaticon.com/svg/static/icons/svg/616/616489.svg">
												</span>
											</ul>
								       	</div>
								     </li>					
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263062.svg">
												<span class="itemtitle">房客評論 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_commnt" readonly>爛透了 我要回雲林啦</textarea>			       		
								       	</div>
								     </li>
								     <li>
								       	<div class="item">
									       	<span class="logo" style="float:left;margin-top:5px;">
												<img src="https://www.flaticon.com/svg/static/icons/svg/263/263101.svg">
												<span class="itemtitle">房東回覆 :</span>
											</span>
									       	<textarea rows="2" wrap="hard" name="hcm_respon" readonly>慢走不送</textarea>			       		
								       	</div>
								     </li>
								</ul>
					      	</div>
						</div>
					</div>

				</div>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">確認</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>				    
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
