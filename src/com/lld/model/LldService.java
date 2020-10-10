package com.lld.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class LldService {
	LandlordDAO_interface dao;

	public LldService() {
		dao = new LldDAO();
	}

	public LldVO addLld(String lld_email, String lld_acc, String lld_pwd, String lld_id, String lld_name,
			Date lld_birth, Boolean lld_sex, String lld_mobile, String lld_city, String lld_dist, String lld_add, byte[] lld_pic) {

		LldVO lldVO = new LldVO();
//		lldVO.setLld_no(lld_no);
		lldVO.setLld_email(lld_email);
		lldVO.setLld_acc(lld_acc);
		lldVO.setLld_pwd(lld_pwd);
		lldVO.setLld_id(lld_id);
		lldVO.setLld_name(lld_name);
		lldVO.setLld_birth(lld_birth);
		lldVO.setLld_sex(lld_sex);
		lldVO.setLld_mobile(lld_mobile);
		lldVO.setLld_city(lld_city);
		lldVO.setLld_dist(lld_dist);
		lldVO.setLld_add(lld_add);
		lldVO.setLld_pic(lld_pic);
		dao.insert_profile(lldVO);

		return lldVO;
	}

	public LldVO updateLldProfile(String lld_no, String lld_email, String lld_acc, String lld_pwd, String lld_id,
			String lld_name, Date lld_birth, Boolean lld_sex, String lld_mobile, String lld_city, String lld_dist,
			String lld_add, byte[] lld_pic, int lld_status) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_email(lld_email);
		lldVO.setLld_acc(lld_acc);
		lldVO.setLld_pwd(lld_pwd);
		lldVO.setLld_id(lld_id);
		lldVO.setLld_name(lld_name);
		lldVO.setLld_birth(lld_birth);
		lldVO.setLld_sex(lld_sex);
		lldVO.setLld_mobile(lld_mobile);
		lldVO.setLld_city(lld_city);
		lldVO.setLld_dist(lld_dist);
		lldVO.setLld_add(lld_add);
		lldVO.setLld_pic(lld_pic);
		lldVO.setLld_status(lld_status);
		dao.update_profile(lldVO);

		return lldVO;
	}

	public LldVO getOneLldProfile(String lld_no) {
		return dao.findByPK_profile(lld_no);
	}

	public List<LldVO> getAllProfile() {
		return dao.getAll_profile();
	}

	public LldVO updateLldPocket(int lld_blance) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_blance(lld_blance);
		dao.update_pocket(lldVO);
		return lldVO;
	}

	public LldVO getOneLldPocket(String lld_no) {
		return dao.findByPK_pocket(lld_no);
	}
	 
	public LldVO updateLldBankCard(int lld_bank, String lld_bankbrach, String lld_bankacc, long lld_card,
			int lld_cardsvc, Date lld_carddue) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_bank(lld_bank);
		lldVO.setLld_bankbrach(lld_bankbrach);
		lldVO.setLld_bankacc(lld_bankacc);
		lldVO.setLld_card(lld_card);
		lldVO.setLld_cardsvc(lld_cardsvc);
		lldVO.setLld_carddue(lld_carddue);
		dao.update_bank_card(lldVO);

		return lldVO;
	}

	public LldVO getOneLldBankCard(String lld_no) {
		return dao.findByPK_bank_card(lld_no);
	}

	public LldVO updateLldCmt(int lld_cmt_starsum, int lld_cmt_count) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_cmt_starsum(lld_cmt_starsum);
		lldVO.setLld_cmt_count(lld_cmt_count);
		dao.update_cmt(lldVO);
		return lldVO;
	}

	public LldVO getOneLldCmt(String lld_no) {
		return dao.findByPK_cmt(lld_no);
	}

	public LldVO updateLldVrf(byte[] lld_id_picf, byte[] lld_id_picb, byte[] lld_id_pic2,
			Timestamp lld_id_uploadtime, int lld_id_isvrfed, String lld_id_disapprove, Timestamp lld_id_vrftime) {

		LldVO lldVO = new LldVO();

		lldVO.setLld_id_picf(lld_id_picf);
		lldVO.setLld_id_picb(lld_id_picb);
		lldVO.setLld_id_pic2(lld_id_pic2);
		lldVO.setLld_id_uploadtime(lld_id_uploadtime);
		lldVO.setLld_id_isvrfed(lld_id_isvrfed);
		lldVO.setLld_id_disapprove(lld_id_disapprove);
		lldVO.setLld_id_vrftime(lld_id_vrftime);
		dao.update_vrf(lldVO);

		return lldVO;
	}

	public LldVO getOneLldVrf(String lld_no) {
		return dao.findByPK_vrf(lld_no);
	}

	public List<LldVO> getAllVrf() {
		return dao.getAll_vrf();
	}

	public LldVO updateLldRptAuth(
			int lld_reported_count, int lld_auth_chat, int lld_auth_res, int lld_auth_cmt, int lld_auth_rpt) {

		LldVO lldVO = new LldVO();

		lldVO.setLld_reported_count(lld_reported_count);
		lldVO.setLld_auth_chat(lld_auth_chat);
		lldVO.setLld_auth_res(lld_auth_res);
		lldVO.setLld_auth_cmt(lld_auth_cmt);
		lldVO.setLld_auth_rpt(lld_auth_rpt);
		dao.update_rpt_auth(lldVO);

		return lldVO;
	}

	public LldVO getOneRptAuth(String lld_no) {
		return dao.findByPK_rpt_auth(lld_no);
	}


}
