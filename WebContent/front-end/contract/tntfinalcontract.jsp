<%@page import="com.rec.model.RecVO"%>
<%@page import="com.cont.model.ConVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.housemanage.model.*"%>
<%@ page import="com.cont.model.*"%>
<%@ page import="com.lld.model.*"%>
<%@ page import="com.tnt.model.*"%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO");
	HouseVO houseVOwaterfee = (HouseVO) request.getAttribute("housewatVO");
	HouseVO houseVOelectfee = (HouseVO) request.getAttribute("houseeleVO");
	
	RecVO recVO = (RecVO) request.getAttribute("recVO");
	TntVO tntVO = (TntVO) request.getAttribute("tntVO");
	LldVO lldVO = (LldVO) request.getAttribute("lldVO");
 	
 	String tnt_no = (String) session.getAttribute("tnt_no");
	if (tnt_no == null) {
		tnt_no = request.getParameter("tnt_no");
	}
	
	String con_no = (String) request.getAttribute("con_no");
	
	pageContext.setAttribute("houseVO", houseVO);
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
	<title>House_Pub</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/front-end/rec/css/detailrec.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/rec/js/detailrec.js" charset="UTF-8"></script>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdZqJc7_LPn4ktRl62V9tbknvkyHbMK4w" async defer></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div><jsp:include page="/front-end/navbar/navbar.jsp"/> </div>
	<div id="body">
		<div id="left">
			<nav id="housenav">
                <div class="menu-btn">
                    <div class="line line--1"></div>
                    <div class="line line--2"></div>
                    <div class="line line--3"></div>
                </div>                
                <div class="nav-links">
                
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apl/Con_aplServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="tntgetallapl">
						<button type="submit" class="link">租屋申請</button><br>
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cont/ConServlet">
						<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
						<input type="hidden" name="action" value="gettntcontract">
						<button type="submit" class="link">歷史合約</button><br>
					</FORM>
                </div>
            </nav>
		</div>
		<form class="table" name="recForm" METHOD="post" ACTION="<%=request.getContextPath()%>/rec/RecServlet">
			<div id="center">
				<div id="chead">
					<ul>
						
					</ul>
				</div>		
				<div id="cbody">				
						<tr>立契約書人承租人    ，出租人<%=tntVO.getTnt_name()%>茲為房屋租賃事宜，雙方同意本契約條款如下：</tr><br>
						<tr>第一條 房屋租賃標的</tr><br>
 						<tr>一、房屋標示：</tr><br>
						<tr>(一)門牌<%=houseVO.getHos_add()%>。</tr><br>
						<tr>(二)面積共計<%=houseVO.getHos_pnum()%>平方公尺。</tr><br>
						<tr>(三)車位：</tr><br>
						<tr>1.車位種類：</tr><br>
						<tr><%=houseVO.getHos_park()%>。</tr><br>
						<tr>2.使用時間：</tr><br>
						<tr>■全日□日間□夜間□其他___。</tr><br>
						<tr>(三)租賃附屬設備：</tr><br>
						<tr>□有□無附屬設備，若有，除另有附屬設備清單外，詳如後附房屋租賃標的現況確認書。</tr><br>
						<tr>(寫code讓他自動載入房屋傢俱資訊, 房東再自行新增/修改, 如: 床墊等)</tr><br>
						<tr>(四)其他：   。</tr><br>
						<tr>第二條 租賃期間</tr><br>
    						<tr>租賃期間自民國  年  月  日起至民國  年  月  日止。</tr><br>
						<tr>第三條 租金約定及支付</tr><br>
  						<tr> 承租人每月租金為新臺幣(下同)   元整，每期應繳納   個月租金，並於每□月□期  日前支付，不得藉任何理由拖延或拒絕；出租人亦不得任意要求調整租金。</tr><br>
						<tr>租金支付方式：□現金繳付□轉帳繳付：金融機構：____，戶名：____，帳號：____。□其他：____。</tr><br>
						<tr>第四條 擔保金（押金）約定及返還</tr><br>
						<tr>擔保金（押金）由租賃雙方約定為___個月租金，金額為   元整(最高不得超過二個月房屋租金之總額)。承租人應於簽訂本契約之同時給付出租人。</tr><br>
   						<tr>前項擔保金（押金），除有第十一條第三項、第十二條第四項及第十六條第二項之情形外，出租人應於租期屆滿或租賃契約終止，承租人交還房屋時返還之。</tr><br>
						<tr>第五條 租賃期間相關費用之支付</tr><br>
						<tr>租賃期間，使用房屋所生之相關費用：</tr><br>
						<tr>一、管理費：</tr><br>
 						<tr>□由出租人負擔。</tr><br>
						<tr> □由承租人負擔。</tr><br>
						<tr>房屋每月    元整。</tr><br>
						<tr>停車位每月   元整。</tr><br>
						<tr>租賃期間因不可歸責於雙方當事人之事由，致本費用增加者，承租人就增加部分之金額，以負擔百分之十為限；如本費用減少者，承租人負擔減少後之金額。</tr><br>
						<tr>□其他：      。</tr><br>
						<tr>二、水費：</tr><br>
						<tr>□由出租人負擔。</tr><br>
						<tr>□由承租人負擔。</tr><br>
						<tr>□每度  元</tr><br>
						<tr>□每月__元</tr><br>
						<tr>□其他：______。(例如每度  元整)</tr><br>
						<tr>三、電費：</tr><br>
						<tr>□由出租人負擔。</tr><br>
						<tr>□由承租人負擔。</tr><br>
						<tr>□每度  元</tr><br>
						<tr>□每月__元</tr><br>
						<tr>□其他：______。(例如每度  元整)</tr><br>
						<tr>四、瓦斯費：(求解天然氣?)</tr><br>
						<tr>□由出租人負擔。</tr><br>
						<tr>□由承租人負擔。</tr><br>
						<tr>□其他：______。</tr><br>
						<tr>五、其他費用及其支付方式：______。</tr><br>
						<tr>□公共水費___元</tr><br>
						<tr>□公共電費___元</tr><br>
						<tr>□網路</tr><br>
						<tr>□第四台</tr><br>
						<tr>□其他：______。</tr><br>
						<tr>第六條 稅費負擔之約定 </tr><br>
本租賃契約有關稅費、代辦費，依下列約定辦理：
一、房屋稅、地價稅由出租人負擔。
二、銀錢收據之印花稅由出租人負擔。
三、簽約代辦費     元
□由出租人負擔。
□由承租人負擔。
□由租賃雙方平均負擔。
□其他：     。
四、公證費      元
□由出租人負擔。
□由承租人負擔。
□由租賃雙方平均負擔。
□其他：     。
五、公證代辦費      元
□由出租人負擔。
□由承租人負擔。
□由租賃雙方平均負擔。
□其他：     。
六、其他稅費及其支付方式：______。
第七條 使用房屋之限制
本房屋係供住宅使用。非經出租人同意，不得變更用途。
承租人同意遵守住戶規約，不得違法使用，或存放有爆炸性或易燃性物品，影響公共安全。
出租人□同意□不同意將本房屋之全部或一部分轉租、出借或 以其他方式供他人使用，或將租賃權轉讓於他人。
前項出租人同意轉租者，承租人應提示出租人同意轉租之證明文件。
第八條 修繕及改裝
房屋或附屬設備損壞而有修繕之必要時，應由出租人負責修繕。但租賃雙方另有約定、習慣或可歸責於承租人之事由者，不在此限。
前項由出租人負責修繕者，如出租人未於承租人所定相當期限內修繕時，承租人得自行修繕並請求出租人償還其費用或於第三條約定之租金中扣除。
房屋有改裝設施之必要，承租人應經出租人同意，始得依相關法令自行裝設，但不得損害原有建築之結構安全。
前項情形承租人返還房屋時，□應負責回復原狀□現況返還□其他_____。
第九條 承租人之責任
    承租人應以善良管理人之注意保管房屋，如違反此項義務，致房屋毀損或滅失者，應負損害賠償責任。但依約定之方法或依房屋之性質使用、收益，致房屋有毀損或滅失者，不在此限。
第十條 房屋部分滅失
    租賃關係存續中，因不可歸責於承租人之事由，致房屋之一部滅失者，承租人得按滅失之部分，請求減少租金。
第十一條 提前終止租約
本契約於期限屆滿前，租賃雙方□得□不得終止租約。
依約定得終止租約者，租賃之一方應於□一個月前□  個月前通知他方。一方未為先期通知而逕行終止租約者，應賠償他方___個月(最高不得超過一個月)租金額之違約金。
前項承租人應賠償之違約金得由第四條之擔保金(押金)中扣抵。
租期屆滿前，依第二項終止租約者，出租人已預收之租金應返還予承租人。
第十二條 房屋之返還
 租期屆滿或租賃契約終止時，承租人應即將房屋返還出租人並遷出戶籍或其他登記。
前項房屋之返還，應由租賃雙方共同完成屋況及設備之點交手續。租賃之一方未會同點交，經他方定相當期限催告仍不會同者，視為完成點交。
承租人未依第一項約定返還房屋時，出租人得向承租人請求未返還房屋期間之相當月租金額外，並得請求相當月租金額一倍(未足一個月者，以日租金折算)之違約金至返還為止。
    前項金額及承租人未繳清之相關費用，出租人得由第四條之擔保金(押金)中扣抵。
第十三條 房屋所有權之讓與
出租人於房屋交付後，承租人占有中，縱將其所有權讓與第三人，本契約對於受讓人仍繼續存在。
前項情形，出租人應移交擔保金（押金）及已預收之租金與受讓人，並以書面通知承租人。
本契約如未經公證，其期限逾五年或未定期限者，不適用前二項之約定。
第十四條 出租人終止租約
  承租人有下列情形之一者，出租人得終止租約：
一、遲付租金之總額達二個月之金額(???)，並經出租人定相當期限催告，承租人仍不為支付。
二、違反第七條規定而為使用。
三、違反第八條第三項規定而為使用。
四、積欠管理費或其他應負擔之費用達相當二個月之租金額，經出租人定相當期限催告，承租人仍不為支付。
第十五條 承租人終止租約
出租人有下列情形之一者，承租人得終止租約：
一、房屋損害而有修繕之必要時，其應由出租人負責修繕者，經承租人定相當期限催告，仍未修繕完畢。
二、有第十條規定之情形，減少租金無法議定，或房屋存餘部分不能達租賃之目的。
三、房屋有危及承租人或其同居人之安全或健康之瑕疵時。
第十六條 遺留物之處理
租期屆滿或租賃契約終止後，承租人之遺留物依下列方式處理：
一、承租人返還房屋時，任由出租人處理。
二、承租人未返還房屋時，經出租人定相當期限催告搬離仍不搬離時，視為廢棄物任由出租人處理。
 前項遺留物處理所需費用，由擔保金(押金)先行扣抵，如有不足，出租人得向承租人請求給付不足之費用。
第十七條 通知送達及寄送
    除本契約另有約定外，出租人與承租人雙方相互間之通知，以郵寄為之者，應以本契約所記載之地址為準；並得以□電子郵件□簡訊□其他__方式為之(無約定通知方式者，應以郵寄為之)；如因地址變更未通知他方或因__，致通知無法到達時（包括拒收），以他方第一次郵遞或通知之日期推定為到達日。
第十八條 疑義處理
    本契約各條款如有疑義時，應為有利於承租人之解釋。
第十九條 其他約定
    本契約雙方同意□辦理公證□不辦理公證。
本契約經辦理公證者，租賃雙方□不同意；□同意公證書載明下列事項應逕受強制執行：
□一、承租人如於租期屆滿後不返還房屋。
□二、承租人未依約給付之欠繳租金、出租人代繳之管理費，或違約時應支付之金額。
□三、出租人如於租期屆滿或租賃契約終止時，應返還之全部或一部擔保金（押金）。
公證書載明金錢債務逕受強制執行時，如有保證人者，前項後段第__款之效力及於保證人。
第二十條 爭議處理
因本契約發生之爭議，雙方得依下列方式處理：
一、向房屋所在地之直轄市、縣（市）不動產糾紛調處委員會申請調處。
二、向直轄市、縣（市）消費爭議調解委員會申請調解。
三、向鄉鎮市(區)調解委員會申請調解。
四、向房屋所在地之法院聲請調解或進行訴訟。
第二十一條 契約及其相關附件效力
    本契約自簽約日起生效，雙方各執一份契約正本。
    本契約廣告及相關附件視為本契約之一部分。
    本契約所定之權利義務對雙方之繼受人均有效力。
第二十二條 未盡事宜之處置
    本契約如有未盡事宜，依有關法令、習慣、平等互惠及誠實信用原則公平解決之。
附件
□建物所有權狀影本
□使用執照影本
□雙方身分證影本
□保證人身分證影本
□授權代理人簽約同意書
□房屋租賃標的現況確認書
□附屬設備清單
□房屋位置格局示意圖
□其他（測量成果圖、室內空間現狀照片）
立契約書人
出租人：
姓名(名稱)：　　　　簽章
統一編號：
戶籍地址：
通訊地址：
聯絡電話：
負責人：         （簽章）
統一編號：
電子郵件信箱：
承租人：
姓名(名稱)：　　　　簽章
統一編號：
戶籍地址：
通訊地址：
聯絡電話：
電子郵件信箱：
保證人：
姓名(名稱)：　　　（簽章）
統一編號：
戶籍地址：
通訊地址：
聯絡電話：
電子郵件信箱：
不動產經紀業：
名稱（公司或商號）：
地址：
電話：
統一編號：
負責人：　　　　（簽章）
統一編號：
電子郵件信箱：
不動產經紀人：
姓名：　　　　 　（簽章）
統一編號：
通訊地址：
聯絡電話：
證書字號：
電子郵件信箱：
中華民國           年          月            日
						
					</div>		        				
				</div>
				<input type="hidden" name="tnt_no" value="<%=tnt_no%>">
				<input type="hidden" name="con_no" value="<%=con_no%>">
				<div id="cfoot">
					<button class="btn" type="button" onclick="notice2()">修改申請</button>
					<input type="hidden" name="action" value="gettntrec">
					<button class="btn" type="button" onclick="notice1()">確定</button>					
				</div>				
			</div>
			<div id="right">
				<div id="rhead">
					<ul>
					</ul>
				</div>
				<div id="rfoot">
					<button class="btn" type="button" onclick="notice2()">修改申請</button>
					<input type="hidden" name="action" value="gettntrec">
					<button class="btn" type="button" onclick="notice1()">確定</button>
				</div>
			</div>
		</FORM>			
	</div>
	<div id="foot"></div>		
</body>
</html>