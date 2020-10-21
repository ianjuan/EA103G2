package com.cont.model;

import java.sql.Date;
import java.util.List;

import com.apl.model.Con_aplStatus;
import com.apl.model.Con_aplVO;

public class ConService {

	private ConDAO_interface dao;

	public ConService() {
		dao = new ConDAO();
	}

	public ConVO addbeforerent(String apl_no, String tnt_no, String hos_no) {

		ConVO conVO = new ConVO();

		conVO.setApl_no(apl_no);
		conVO.setTnt_no(tnt_no);
		conVO.setHos_no(hos_no);
		dao.beforerentinsert(conVO);

		return conVO;
	}

	public ConVO updatebeforerent(String apl_no, String tnt_no, String hos_no, byte[] con_lld_sign, byte[] con_tnt_sign,
			Integer con_dep_sta, Integer hos_dep, Integer con_sta, Date con_che_date, String con_no) {

		ConVO conVO = new ConVO();

		conVO.setApl_no(apl_no);
		conVO.setTnt_no(tnt_no);
		conVO.setHos_no(hos_no);
		conVO.setCon_lld_sign(con_lld_sign);
		conVO.setCon_tnt_sign(con_tnt_sign);
		conVO.setCon_dep_sta(con_dep_sta);
		conVO.setHos_dep(hos_dep);
		conVO.setCon_sta(con_sta);
		conVO.setCon_che_date(con_che_date);
		conVO.setCon_no(con_no);
		dao.beforerentupdate(conVO);

		return conVO;
	}

	public ConVO updaterent(Integer hos_dep, Integer con_dep_sta, Date con_chkdate, Integer con_comchkdate,
			String con_no) {

		ConVO conVO = new ConVO();

		conVO.setHos_dep(hos_dep);
		conVO.setCon_dep_sta(con_dep_sta);
		conVO.setCon_chkdate(con_chkdate);
		conVO.setCon_comchkdate(con_comchkdate);
		conVO.setCon_no(con_no);
		dao.rentupdate(conVO);

		return conVO;
	}

	public ConVO updatebeforecheckout(Integer hos_dep, Integer con_dep_sta, Integer con_chk_sta, Integer con_chr_fee,
			String con_chr_itm, Integer con_is_chr, String con_no) {

		ConVO conVO = new ConVO();

		conVO.setHos_dep(hos_dep);
		conVO.setCon_dep_sta(con_dep_sta);
		conVO.setCon_chk_sta(con_chk_sta);
		conVO.setCon_chr_fee(con_chr_fee);
		conVO.setCon_chr_itm(con_chr_itm);
		conVO.setCon_is_chr(con_is_chr);
		conVO.setCon_no(con_no);
		dao.beforecheckoutupdate(conVO);

		return conVO;
	}

	public ConVO updatecheckout(Integer con_rent_agn, Integer con_bill_paid, Date con_dep_bkdate, Date con_lastb_pdate,
			Integer con_out_normal, String con_no) {

		ConVO conVO = new ConVO();

		conVO.setCon_rent_agn(con_rent_agn);
		conVO.setCon_bill_paid(con_bill_paid);
		conVO.setCon_dep_bkdate(con_dep_bkdate);
		conVO.setCon_lastb_pdate(con_lastb_pdate);
		conVO.setCon_out_normal(con_out_normal);
		conVO.setCon_no(con_no);
		dao.checkoutupdate(conVO);

		return conVO;
	}

	public ConVO update(String apl_no, String tnt_no, String hos_no, byte[] con_lld_sign, byte[] con_tnt_sign,
			Integer con_dep_sta, Date con_che_date, String con_no, Integer hos_dep,  Date con_chkdate, Integer con_comchkdate,
			 Integer con_chk_sta, Integer con_chr_fee,Integer con_out_normal,String con_chr_itm, Integer con_is_chr,
			 Integer con_rent_agn, Integer con_bill_paid, Date con_dep_bkdate, Date con_lastb_pdate) {

		ConVO conVO = new ConVO();
		
		conVO.setApl_no(apl_no);
		conVO.setTnt_no(tnt_no);
		conVO.setHos_no(hos_no);
		conVO.setCon_lld_sign(con_lld_sign);
		conVO.setCon_tnt_sign(con_tnt_sign);
		conVO.setCon_dep_sta(con_dep_sta);
		conVO.setCon_che_date(con_che_date);
		conVO.setCon_no(con_no);
		conVO.setHos_dep(hos_dep);
		conVO.setCon_chkdate(con_chkdate);
		conVO.setCon_comchkdate(con_comchkdate);
		conVO.setCon_chk_sta(con_chk_sta);
		conVO.setCon_chr_fee(con_chr_fee);
		conVO.setCon_chr_itm(con_chr_itm);
		conVO.setCon_is_chr(con_is_chr);
		conVO.setCon_rent_agn(con_rent_agn);
		conVO.setCon_bill_paid(con_bill_paid);
		conVO.setCon_dep_bkdate(con_dep_bkdate);
		conVO.setCon_lastb_pdate(con_lastb_pdate);
		conVO.setCon_out_normal(con_out_normal);
		dao.update(conVO);
		
		return conVO;
	}
	
	public ConVO getOneCon(String con_no) {
		return dao.findByPrimaryKey(con_no);
	}
	
	public ConVO getConbyhos(String hos_no) {
		return dao.findByHosno(hos_no);
	}

	public List<ConVO> getAll() {
		return dao.getAll();
	}
	
	public List<ConVO> lldgetcon(String lld_no){
		return dao.getconlld(lld_no);
	}
	
	public List<ConVO> tntgetcon(String tnt_no){
		return dao.getcontnt(tnt_no);
	}

	public ConStatus[] getConStatusAll() {
		return ConStatus.values();
	}

	public String getConstatusText(Integer num) {
		return ConStatus.findByPrimaryKey(num).getText();
	}
	
	public NumEnum[] getNumEnumAll() {
		return NumEnum.values();
	}

	public String getNumEnumText(Integer num) {
		return NumEnum.findByPrimaryKey(num).getText();
	}
	
	public DepStatus[] getDepStatusAll() {
		return DepStatus.values();
	}

	public String getDepstatusText(Integer num) {
		return DepStatus.findByPrimaryKey(num).getText();
	}

}
