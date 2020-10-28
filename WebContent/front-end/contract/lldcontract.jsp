<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%@ page import="com.tnt.model.*"%>
<%@page import="com.apl.model.Con_aplVO"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseVO houseVOwaterfee = (HouseVO) request.getAttribute("houseVOwaterfee");
	HouseVO houseVOelectfee = (HouseVO) request.getAttribute("houseVOelectfee");
	
	ConVO conVO = (ConVO)request.getAttribute("conVO");
	TntVO tntVO = (TntVO) request.getAttribute("tntVO");
 	LldVO lldVO = (LldVO)request.getAttribute("lldVO");
 	Con_aplVO con_aplVO = (Con_aplVO) request.getAttribute("con_aplVO");
 	
 	String lld_no = (String) session.getAttribute("lld_no");
	if (lld_no == null) {
		lld_no = request.getParameter("lld_no");
	}
	
	HouseVO lldInfo = (HouseVO) request.getAttribute("lldInfo");
	if (lldInfo == null) {
		HouseService houseSvc = new HouseService();
		lldInfo = houseSvc.getLldInfo(lld_no);
	}
	
	pageContext.setAttribute("houseVO", houseVO);
	pageContext.setAttribute("conVO", conVO);
	pageContext.setAttribute("lldVO", lldVO);
%>

<jsp:useBean id="aplSvc" scope="page" class="com.apl.model.Con_aplService" />
<jsp:useBean id="tntSvc" scope="page" class="com.tnt.model.TntService" />
<jsp:useBean id="lldSvc" scope="page" class="com.lld.model.LldService" />
<jsp:useBean id="hosSvc" scope="page" class="com.housemanage.model.HouseService" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>簽合約</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/contract/css/cont.css">	
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div><jsp:include page="/front-end/navbar/navbar.jsp"/></div>
	<div id="body">
		<div id="left">
			<nav id="housenav">
                <div class="menu-btn">
                    <div class="line line--1"></div>
                    <div class="line line--2"></div>
                    <div class="line line--3"></div>
                </div>                
                <div class="nav-links">
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldAllHouse">
						<button type="submit" class="link">首頁</button>
					</FORM>
					<FORM METHOD="post" name="pub" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" id="lld_balance" name="lld_balance" value="<%=lldInfo.getLld_balance()%>">
						<input type="hidden" name="action" value="getLldPub">
						<button type="button" class="link" onclick="checkmoney()">上架房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldRentHouse">
						<button type="submit" class="link">已租房屋</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldUnRentHouse">
						<button type="submit" class="link">待租房屋</button>
					</FORM>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/house_manage/HouseServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getLldOffHouse">
						<button type="submit" class="link">下架房屋</button>
					</FORM>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="lldgetAll">
						<button type="submit" class="link">租屋申請</button>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="lld_no" value="<%=lld_no%>">
						<input type="hidden" name="action" value="getlldcontract">
						<button type="submit" class="link"  style="color: #D37707;">合約管理</button>
					</FORM>
					<button type="button" class="link">修繕管理</button>
					<button type="button" class="link">評價管理</button>
                </div>
            </nav>
		</div>
		
		<form class="table" name="contForm" METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet" enctype="multipart/form-data">
			<div id="center">
				<div id="chead">
					<h2>房屋租賃契約書</h2><a id="top" href="#">置頂</a><a id="fur" href="#">傢俱設備</a><a id="fee" href="#">費用</a><a id="signhref" href="#">簽名</a><a id="other_show" href="#">其他項目</a><a id="other_hide" href="#">其他項目</a>
				</div>		
				<div id="cbody">
				        <br>			
						 立契約書人出租人<mark><b><%=lldVO.getLld_name().trim()%></b></mark>，承租人<mark><b><%=tntVO.getTnt_name().trim()%></b></mark>&nbsp;茲為房屋租賃事宜，雙方同意本契約條款如下： <br><br>
						<strong>第一條 房屋租賃標的</strong><br><br>
						&nbsp;&nbsp;<b>一、地址：</b><mark><b><%=houseVO.getHos_add()%>。</b></mark><br><br>
						&nbsp;&nbsp;<b>二、面積共計：</b><mark><b><%=houseVO.getHos_pnum()%></b></mark>坪。<br><br>
						&nbsp;&nbsp;<b>三、車位： </b><mark><b><%=houseVO.getHos_park()%>。</b></mark><br><br>
						<div id="furdiv">
							&nbsp;&nbsp;<b>四、租賃附屬設備： </b><br><br>
							<%=houseVO.getHos_table()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>桌子</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_table'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp張。</label><br><br><br>":"<input type='hidden' name='hos_table' value='0'>"%>
							<%=houseVO.getHos_chair()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>椅子</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_chair'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp張。</label><br><br><br>":"<input type='hidden' name='hos_chair' value='0'>"%>
							<%=houseVO.getHos_bed()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>床</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_bed'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp張。</label><br><br><br>":"<input type='hidden' name='hos_bed' value='0'>"%>
							<%=houseVO.getHos_closet()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>衣櫃</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_closet'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp個。</label><br><br><br>":"<input type='hidden' name='hos_closet' value='0'>"%>
							<%=houseVO.getHos_sofa()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>沙發</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_sofa'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp張。</label><br><br><br>":"<input type='hidden' name='hos_sofa' value='0'>"%>
							<%=houseVO.getHos_refrig()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>冰箱</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_refrig'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_refrig' value='0'>"%>
							<%=houseVO.getHos_tv()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>電視</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_tv'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_tv' value='0'>"%>
							<%=houseVO.getHos_drink()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>飲水機</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_drink'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_drink' value='0'>"%>
							<%=houseVO.getHos_aircon()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>冷氣</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_aircon'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_aircon' value='0'>"%>
							<%=houseVO.getHos_wash()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>洗衣機</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_wash'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_wash' value='0'>"%>
							<%=houseVO.getHos_hoter()>0?"<label class='item_name'>&nbsp;&nbsp<mark><b>熱水器</b></mark>&nbsp;&nbsp;"+
								"<select name='hos_hoter'>"+
								  "<option value='1'>1</option>"+
								  "<option value='2'>2</option>"+
								  "<option value='3'>3</option>"+
								  "<option value='4'>4</option>"+
								  "<option value='5'>5</option>"+
								"</select>"+
							"&nbsp;&nbsp台。</label><br><br><br>":"<input type='hidden' name='hos_hoter' value='0'>"%>
						</div>						
						&nbsp;&nbsp;<b>五、其他： </b><%=houseVO.getHos_forth()>0?"<mark><b>第四台</b></mark> ":""%><%=houseVO.getHos_net()>0?"<mark><b>網路</b></mark> ":""%><%=houseVO.getHos_gas()>0?"<mark><b>天然瓦斯</b></mark>":""%>。<br><br>
						<strong>第二條 租賃期間</strong><br><br>
    					&nbsp;&nbsp;租賃期間自西元<mark><b><%=con_aplVO.getApl_str()%></b></mark>起至西元<mark><b><%=con_aplVO.getApl_end()%></b></mark>止。<br><br>
						<div id="feediv">
							<strong>第三條 租金約定及支付</strong><br><br>
	  						&nbsp;&nbsp;承租人每月租金為新臺幣(下同)<mark><b><%=houseVO.getHos_rentfee()%>元</b></mark>整，每期應繳納<mark ><b>一個月</b></mark>租金，並於每月一日前支付，不得藉任何理由拖延或拒絕；<br>
	  						&nbsp;&nbsp;出租人亦不得任意要求調整租金。<br><br>
							&nbsp;&nbsp;租金支付方式：□現金繳付■轉帳繳付 <br><br>
						</div>						
						<strong>第四條 擔保金（押金）約定及返還</strong><br><br>
						&nbsp;&nbsp;擔保金（押金）由租賃雙方約定為<mark><b>兩個月</b></mark>租金，金額為<mark><b><%=houseVO.getHos_rentfee()*2%></b></mark>元整。承租人應於簽訂本契約之同時給付出租人。<br><br>
   						&nbsp;&nbsp;前項擔保金（押金），除有第十一條第三項、第十二條第四項及第十六條第二項之情形外，<br>
   						&nbsp;&nbsp;出租人應於租期屆滿或租賃契約終止，承租人交還房屋時返還之。<br><br>
						<strong>第五條 租賃期間相關費用之支付</strong><br><br>
						&nbsp;&nbsp;租賃期間，使用房屋所生之相關費用： <br><br>
						&nbsp;&nbsp;<b>一、管理費：</b><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;房屋每月<mark ><b><%=houseVO.getHos_manafee()%></b></mark>元整。 <br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;租賃期間因不可歸責於雙方當事人之事由，致本費用增加者，承租人就增加部分之金額，以負擔百分之十為限；<br>
						&nbsp;&nbsp;&nbsp;&nbsp;如本費用減少者，承租人負擔減少後之金額。<br><br>
						&nbsp;&nbsp;<b>二、水費：</b><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOwaterfee.getHos_waterfeetype()==0?"■<mark><b>由出租人負擔</b></mark>。":"□<mark><b>由出租人負擔</b></mark>。"%><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOwaterfee.getHos_waterfeetype()==1?"■":"□"%>每度<%=houseVOwaterfee.getHos_waterfeetype()==1?"<mark><b>"+houseVOwaterfee.getHos_waterfee()+"</b></mark>":"__"%>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOwaterfee.getHos_waterfeetype()==2?"■":"□"%>每月<%=houseVOwaterfee.getHos_waterfeetype()==2?"<mark><b>"+houseVOwaterfee.getHos_waterfee()+"</b></mark>":"__"%>元。<br><br>
						&nbsp;&nbsp;<b>三、電費：</b><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOelectfee.getHos_electfeetype()==0?"■<mark><b>由出租人負擔</b></mark>。":"□<mark><b>由出租人負擔</b></mark>。"%><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOelectfee.getHos_electfeetype()==1?"■":"□"%>每度<%=houseVOelectfee.getHos_electfeetype()==1?"<mark><b>"+houseVOelectfee.getHos_electfee()+"</b></mark>":"__"%>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVOelectfee.getHos_electfeetype()==2?"■":"□"%>每月<%=houseVOelectfee.getHos_electfeetype()==2?"<mark><b>"+houseVOelectfee.getHos_electfee()+"</b></mark>":"__"%>元。<br><br>
						&nbsp;&nbsp;<b>四、其他費用及其支付方式：</b><br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVO.getHos_gasfee()>0?"■":"□"%>瓦斯費：<mark><b><%=houseVO.getHos_gasfee()%></b></mark>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVO.getHos_puwaterfee()>0?"■":"□"%>公共水費：<mark><b><%=houseVO.getHos_puwaterfee()%></b></mark>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVO.getHos_puelefee()>0?"■":"□"%>公共電費：<mark><b><%=houseVO.getHos_puelefee()%></b></mark>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVO.getHos_netfee()>0?"■":"□"%>網路費：<mark><b><%=houseVO.getHos_netfee()%></b></mark>元。<br><br>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=houseVO.getHos_parkfee()>0?"■":"□"%>停車位費：<mark><b><%=houseVO.getHos_parkfee()%></b></mark>元。<br><br>
						
						<button type="button" class="btn" id="other_con_item_show" onclick="showdiv()">展開其他項目</button>
						<button type="button" class="btn" id="other_con_item_hide" onclick="hidediv()">隱藏</button>
						<div id="other_con_item">
							<br><strong>第六條 稅費負擔之約定</strong><br><br>
	                        &nbsp;&nbsp;本租賃契約有關稅費、代辦費，依下列約定辦理：<br><br>
							&nbsp;&nbsp;一、房屋稅、地價稅由出租人負擔。<br><br>
							&nbsp;&nbsp;二、銀錢收據之印花稅由出租人負擔。<br><br>
							<strong>第七條 使用房屋之限制</strong><br><br>
							&nbsp;&nbsp;本房屋係供住宅使用。非經出租人同意，不得變更用途。<br>
							&nbsp;&nbsp;承租人同意遵守住戶規約，不得違法使用，或存放有爆炸性或易燃性物品，影響公共安全。<br>
							&nbsp;&nbsp;出租人□同意■不同意將本房屋之全部或一部分轉租、出借或 以其他方式供他人使用，或將租賃權轉讓於他人。<br>
							&nbsp;&nbsp;前項出租人同意轉租者，承租人應提示出租人同意轉租之證明文件。 <br><br>
							<strong>第八條 修繕及改裝</strong><br><br>
							&nbsp;&nbsp;房屋或附屬設備損壞而有修繕之必要時，應由出租人負責修繕。 <br>
							&nbsp;&nbsp;但租賃雙方另有約定、習慣或可歸責於承租人之事由者，不在此限。 <br>
							&nbsp;&nbsp;前項由出租人負責修繕者，如出租人未於承租人所定相當期限內修繕時，<br>
							&nbsp;&nbsp;承租人得自行修繕並請求出租人償還其費用或於第三條約定之租金中扣除。 <br>
							&nbsp;&nbsp;房屋有改裝設施之必要，承租人應經出租人同意，始得依相關法令自行裝設，但不得損害原有建築之結構安全。 <br>
							&nbsp;&nbsp;前項情形承租人返還房屋時，■應負責回復原狀□現況返還□其他__。 <br><br>
							<strong>第九條 承租人之責任</strong><br><br>
	    					&nbsp;&nbsp;承租人應以善良管理人之注意保管房屋，如違反此項義務，致房屋毀損或滅失者，應負損害賠償責任。<br>
	    					&nbsp;&nbsp;但依約定之方法或依房屋之性質使用、收益，致房屋有毀損或滅失者，不在此限。 <br><br>
							<strong>第十條 房屋部分滅失</strong><br><br>
	   						&nbsp;&nbsp;租賃關係存續中，因不可歸責於承租人之事由，致房屋之一部滅失者，承租人得按滅失之部分，請求減少租金。 <br><br>
							<strong>第十一條 提前終止租約</strong><br><br>
							&nbsp;&nbsp;本契約於期限屆滿前，租賃雙方■得□不得終止租約。 <br>
							&nbsp;&nbsp;依約定得終止租約者，租賃之一方應於■一個月前□  個月前通知他方。<br>
							&nbsp;&nbsp;一方未為先期通知而逕行終止租約者，應賠償他方兩個月(最高不得超過一個月)租金額之違約金。 <br>
							&nbsp;&nbsp;前項承租人應賠償之違約金得由第四條之擔保金(押金)中扣抵。 <br>
							&nbsp;&nbsp;租期屆滿前，依第二項終止租約者，出租人已預收之租金應返還予承租人。 <br><br>
							<strong>第十二條 房屋之返還</strong><br><br>
							&nbsp;&nbsp;租期屆滿或租賃契約終止時，承租人應即將房屋返還出租人並遷出戶籍或其他登記。 <br>
							&nbsp;&nbsp;前項房屋之返還，應由租賃雙方共同完成屋況及設備之點交手續。<br>
							&nbsp;&nbsp;租賃之一方未會同點交，經他方定相當期限催告仍不會同者，視為完成點交。 <br>
							&nbsp;&nbsp;承租人未依第一項約定返還房屋時，出租人得向承租人請求未返還房屋期間之相當月租金額外，<br>
							&nbsp;&nbsp;並得請求相當月租金額一倍(未足一個月者，以日租金折算)之違約金至返還為止。 <br>
	    					&nbsp;&nbsp;前項金額及承租人未繳清之相關費用，出租人得由第四條之擔保金(押金)中扣抵。 <br><br>
							<strong>第十三條 房屋所有權之讓與</strong><br><br>
							&nbsp;&nbsp;出租人於房屋交付後，承租人占有中，縱將其所有權讓與第三人，本契約對於受讓人仍繼續存在。 <br>
							&nbsp;&nbsp;前項情形，出租人應移交擔保金（押金）及已預收之租金與受讓人，並以書面通知承租人。 <br>
							&nbsp;&nbsp;本契約如未經公證，其期限逾五年或未定期限者，不適用前二項之約定。 <br><br>
							<strong>第十四條 出租人終止租約</strong><br><br>
	  						&nbsp;&nbsp;承租人有下列情形之一者，出租人得終止租約： <br>
							&nbsp;&nbsp;一、遲付租金之總額達二個月之金額，並經出租人定相當期限催告，承租人仍不為支付。 <br>
							&nbsp;&nbsp;二、違反第七條規定而為使用。 <br>
							&nbsp;&nbsp;三、違反第八條第三項規定而為使用。 <br>
							&nbsp;&nbsp;四、積欠管理費或其他應負擔之費用達相當二個月之租金額，經出租人定相當期限催告，承租人仍不為支付。 <br><br>
							<strong>第十五條 承租人終止租約</strong><br><br>
							&nbsp;&nbsp;出租人有下列情形之一者，承租人得終止租約： <br>
							&nbsp;&nbsp;一、房屋損害而有修繕之必要時，其應由出租人負責修繕者，經承租人定相當期限催告，仍未修繕完畢。 <br>
							&nbsp;&nbsp;二、有第十條規定之情形，減少租金無法議定，或房屋存餘部分不能達租賃之目的。 <br>
							&nbsp;&nbsp;三、房屋有危及承租人或其同居人之安全或健康之瑕疵時。 <br><br>
							<strong>第十六條 遺留物之處理</strong><br><br>
							&nbsp;&nbsp;租期屆滿或租賃契約終止後，承租人之遺留物依下列方式處理： <br>
							&nbsp;&nbsp;一、承租人返還房屋時，任由出租人處理。 <br>
							&nbsp;&nbsp;二、承租人未返還房屋時，經出租人定相當期限催告搬離仍不搬離時，視為廢棄物任由出租人處理。 <br>
	 						&nbsp;&nbsp;前項遺留物處理所需費用，由擔保金(押金)先行扣抵，如有不足，出租人得向承租人請求給付不足之費用。 <br><br>
							<strong>第十七條 通知送達及寄送</strong><br><br>
	    					&nbsp;&nbsp;除本契約另有約定外，出租人與承租人雙方相互間之通知，以郵寄為之者，應以本契約所記載之地址為準； <br>
					        &nbsp;&nbsp;並得以■電子郵件□簡訊□其他__方式為之(無約定通知方式者，應以郵寄為之)； <br>
					        &nbsp;&nbsp;如因地址變更未通知他方或因__，致通知無法到達時（包括拒收），以他方第一次郵遞或通知之日期推定為到達日。 <br><br>
							<strong>第十八條 疑義處理</strong><br><br>
	    					&nbsp;&nbsp;本契約各條款如有疑義時，應為有利於承租人之解釋。 <br><br>
							<strong>第十九條 其他約定</strong><br><br>
	    					&nbsp;&nbsp;本契約雙方同意■辦理公證□不辦理公證。 <br>
							&nbsp;&nbsp;本契約經辦理公證者，租賃雙方□不同意；■同意公證書載明下列事項應逕受強制執行： <br>
							&nbsp;&nbsp;一、承租人如於租期屆滿後不返還房屋。 <br>
							&nbsp;&nbsp;二、承租人未依約給付之欠繳租金、出租人代繳之管理費，或違約時應支付之金額。 <br>
							&nbsp;&nbsp;三、出租人如於租期屆滿或租賃契約終止時，應返還之全部或一部擔保金（押金）。 <br><br>
							<strong>第二十條 爭議處理</strong><br><br>
							&nbsp;&nbsp;因本契約發生之爭議，雙方得依下列方式處理： <br>
							&nbsp;&nbsp;一、向房屋所在地之直轄市、縣（市）不動產糾紛調處委員會申請調處。 <br>
							&nbsp;&nbsp;二、向直轄市、縣（市）消費爭議調解委員會申請調解。 <br>
							&nbsp;&nbsp;三、向鄉鎮市(區)調解委員會申請調解。 <br>
							&nbsp;&nbsp;四、向房屋所在地之法院聲請調解或進行訴訟。 <br><br>
							<strong>第二十一條 契約及其相關附件效力</strong><br><br>
	    					&nbsp;&nbsp;本契約自簽約日起生效，雙方各執一份契約正本。 <br>
	    					&nbsp;&nbsp;本契約廣告及相關附件視為本契約之一部分。 <br>
	    					&nbsp;&nbsp;本契約所定之權利義務對雙方之繼受人均有效力。 <br><br>
							<strong>第二十二條 未盡事宜之處置</strong><br><br>
	    					&nbsp;&nbsp;本契約如有未盡事宜，依有關法令、習慣、平等互惠及誠實信用原則公平解決之。 <br>
							&nbsp;&nbsp;附件 <br>
							&nbsp;&nbsp;&nbsp;&nbsp;■雙方身分證影本 <br>
							&nbsp;&nbsp;&nbsp;&nbsp;■附屬設備清單 <br>
							&nbsp;&nbsp;&nbsp;&nbsp;□其他（測量成果圖、室內空間現狀照片） <br><br>
						</div>
						
						<div id="signdiv"><br><strong>立契約書人</strong><br><br></div>						
						出租人： <br><br>
						&nbsp;&nbsp;姓名：<mark><b><%=lldVO.getLld_name()%></b></mark><br><br>
						&nbsp;&nbsp;簽章 (<span style="mark-style: italic;">請於下方簽名欄簽名</span>)
						<div class="container">
							<div class="row">
								 <canvas id="sig-canvas" width="800" height="200" style="z-index:9999;position: relative;"></canvas>
							</div>
							<div class="row" id="signbtn">
								<button type="button" class="btn btn-primary" id="sig-submitBtn" style="margin-right: 5px;">確認</button>										
								<button type="button" class="btn btn-default" id="sig-clearBtn">清除</button>									
								<input type="color" id="color" value="#1543F6">
								<span class="signfunfont">顏色</span>
								<input type="range" id="size" value="4" min="1" max="10" step="1">
								<span class="signfunfont">粗細</span>
							</div>
							<div class="row">
								<img class="region" id="sig-image" src="" alt="簽名圖片底家啦!"/>
								<span id="signnotice">您的簽名</span>
								<input class="region" id="sign" type="hidden" name="con_lld_sign" value=""/>										
							</div>
						</div>
						 
						&nbsp;&nbsp;統一編號：<mark><b><%=lldVO.getLld_id()%></b></mark><br><br>
						&nbsp;&nbsp;戶籍地址：<mark><b><%=lldVO.getLld_city()%><%=lldVO.getLld_dist()%><%=lldVO.getLld_add()%></b></mark><br><br>
						&nbsp;&nbsp;通訊地址：<mark><b><%=lldVO.getLld_city()%><%=lldVO.getLld_dist()%><%=lldVO.getLld_add()%></b></mark><br><br>
						&nbsp;&nbsp;聯絡電話：<mark><b><%=lldVO.getLld_mobile()%></b></mark><br><br>
						&nbsp;&nbsp;電子郵件信箱：<mark><b><%=lldVO.getLld_email()%></b></mark><br><br>
												
						承租人<br><br>
						&nbsp;&nbsp;姓名：<mark><b><%=tntVO.getTnt_name()%></b></mark><br><br>
						&nbsp;&nbsp;簽章 <br><br>
						 
						&nbsp;&nbsp;統一編號：<mark><b><%=tntVO.getTnt_id()%></b></mark><br><br>
						&nbsp;&nbsp;戶籍地址：<mark><b><%=tntVO.getTnt_city()%><%=tntVO.getTnt_dist()%><%=tntVO.getTnt_add()%></b></mark><br><br>
						&nbsp;&nbsp;通訊地址：<mark><b><%=tntVO.getTnt_city()%><%=tntVO.getTnt_dist()%><%=tntVO.getTnt_add()%></b></mark><br><br>
						&nbsp;&nbsp;聯絡電話：<mark><b><%=tntVO.getTnt_mobile()%></b></mark><br><br>
						&nbsp;&nbsp;電子郵件信箱：<mark><b><%=tntVO.getTnt_email()%></b></mark><br><br><br>
						
						名稱：愛租　 <br>
						戶籍地址：桃園區中壢市中央路300號 <br>
						通訊地址：桃園區中壢市中央路300號 <br>
						聯絡電話：0988755012 <br>
						電子郵件信箱：ea103g2@gmail.com <br>
<%-- 						 西元<%=conVO.getCon_date()%><br> --%>
				</div>
				<input type="hidden" name="lld_no" value="<%=lld_no%>">
				<input type="hidden" name="con_no" value="<%=conVO.getCon_no()%>">
				<input type="hidden" name="hos_no" value="<%=houseVO.getHos_no()%>">
				<input type="hidden" name="lld_mobile" value="<%=lldVO.getLld_mobile()%>">
				<div id="cfoot">
					<button class="pagebtn" type="button" onclick="notice2()">重新填寫</button>	
					<input type="hidden" name="action" value="updateonelldcontract">
					<button class="pagebtn" type="button" onclick="notice1()">送出合約</button>					
				</div>				
			</div>
			<div id="right"></div>
		</form>
	</div>
	<div id="foot"></div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/contract/js/cont1.js" charset="UTF-8"></script>
</body>
</html>