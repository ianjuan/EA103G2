package com.lld.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.tnt.model.TntVO;

public class LldService {
	LandlordDAO_interface dao;

	public LldService() {
		dao = new LldDAO();
	}

	public LldVO addLld(String lld_email, String lld_acc, String lld_pwd, String lld_id, String lld_name,
			Date lld_birth, Boolean lld_sex, String lld_mobile, String lld_city, String lld_dist, String lld_add, byte[] lld_pic) {
		//
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
	
	public LldVO addLld(String lld_email, String lld_acc, String lld_pwd, String lld_id, String lld_name,
			Date lld_birth, Boolean lld_sex, String lld_mobile, String lld_city, String lld_dist, String lld_add) {
		//
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
		dao.insert_profile(lldVO, false);

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
	
	public LldVO updateLldProfile(String lld_no, String lld_email, String lld_acc, String lld_pwd, String lld_id,
			String lld_name, Date lld_birth, Boolean lld_sex, String lld_mobile, String lld_city, String lld_dist,
			String lld_add, int lld_status) {

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
		lldVO.setLld_status(lld_status);
		dao.update_profile(lldVO, false);

		return lldVO;
	}

	public LldVO getOneLldProfile(String lld_no) {
		return dao.findByPK_profile(lld_no);
	}

	public List<LldVO> getAllProfile() {
		return dao.getAll_profile();
	}
	
	public LldVO getOneLldAccount(String lld_no) {
		return dao.findByPK_account(lld_no);
	}
	
	public List<LldVO> getAllAccount() {
		return dao.getAll_account();
	}
	
	public LldVO updateLldPwd(String lld_no, String lld_pwd) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_pwd(lld_pwd);
		dao.update_pwd(lldVO);

		return lldVO;
	}
	
	public LldVO updateLldStatus(String lld_no, int lld_status) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_status(lld_status);
		dao.update_status(lldVO);

		return lldVO;
	}
	
	
	public LldVO updateLldPic(String lld_no, byte[] lld_pic) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_pic(lld_pic);
		dao.update_pic(lldVO);

		return lldVO;
	}
	
	public LldVO getOneLldPic(String lld_no) {
		return dao.findByPK_pic(lld_no);
	}

	
	public LldVO updateLldPocket(String lld_no, int lld_balance) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_balance(lld_balance);
		dao.update_pocket(lldVO);
		return lldVO;
	}

	public LldVO getOneLldPocket(String lld_no) {
		return dao.findByPK_pocket(lld_no);
	}
	 
	public LldVO updateLldBankCard(String lld_no, String lld_bank, String lld_bankbranch, String lld_bankacc, String lld_card,
			String lld_cardsvc, Date lld_carddue) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_bank(lld_bank);
		lldVO.setLld_bankbranch(lld_bankbranch);
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

	public LldVO updateLldCmt(String lld_no, int lld_cmt_starsum, int lld_cmt_count) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_cmt_starsum(lld_cmt_starsum);
		lldVO.setLld_cmt_count(lld_cmt_count);
		dao.update_cmt(lldVO);
		return lldVO;
	}

	public LldVO getOneLldCmt(String lld_no) {
		return dao.findByPK_cmt(lld_no);
	}

	public LldVO updateLldVrf(String lld_no, byte[] lld_id_picf, byte[] lld_id_picb, byte[] lld_id_pic2,
			Timestamp lld_id_uploadtime, int lld_id_isupload, int lld_id_result, String lld_id_disapprove, Timestamp lld_id_vrftime) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_id_picf(lld_id_picf);
		lldVO.setLld_id_picb(lld_id_picb);
		lldVO.setLld_id_pic2(lld_id_pic2);
		lldVO.setLld_id_uploadtime(lld_id_uploadtime);
		lldVO.setLld_id_isupload(lld_id_isupload);
		lldVO.setLld_id_result(lld_id_result);
		lldVO.setLld_id_disapprove(lld_id_disapprove);
		lldVO.setLld_id_vrftime(lld_id_vrftime);
		dao.update_vrf(lldVO);

		return lldVO;
	}

	public LldVO getOneLldVrf(String lld_no) {
		return dao.findByPK_vrf(lld_no);
	}
	
	public LldVO getOneLldVrf(String lld_no, Boolean getVrfPics) {
		return dao.findByPK_vrf(lld_no, getVrfPics);
	}

	public List<LldVO> getAllVrf() {
		return dao.getAll_vrf();
	}
	
	public LldVO updateLldVrfPics(String lld_no, byte[] lld_id_picf, byte[] lld_id_picb, byte[] lld_id_pic2, int lld_id_isupload) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_id_picf(lld_id_picf);
		lldVO.setLld_id_picb(lld_id_picb);
		lldVO.setLld_id_pic2(lld_id_pic2);
		lldVO.setLld_id_isupload(lld_id_isupload);
		dao.update_vrf_pics(lldVO);

		return lldVO;
	}

	public LldVO updateLldRpt(String lld_no, int lld_reported_count) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_reported_count(lld_reported_count);
		dao.update_rpt(lldVO);

		return lldVO;
	}

	public LldVO getOneRpt(String lld_no) {
		return dao.findByPK_rpt(lld_no);
	}
	
	
	public LldVO updateLldAuth(String lld_no, int lld_auth_chat, int lld_auth_res, int lld_auth_cmt, int lld_auth_rpt, int lld_auth_hos) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_auth_chat(lld_auth_chat);
		lldVO.setLld_auth_res(lld_auth_res);
		lldVO.setLld_auth_cmt(lld_auth_cmt);
		lldVO.setLld_auth_rpt(lld_auth_rpt);
		lldVO.setLld_auth_rpt(lld_auth_hos);
		dao.update_auth(lldVO);

		return lldVO;
	}

	public LldVO getOneAuth(String lld_no) {
		return dao.findByPK_auth(lld_no);
	}


}
