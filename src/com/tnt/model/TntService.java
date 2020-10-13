package com.tnt.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class TntService {
	TenantDAO_interface dao;

	public TntService() {
		dao = new TntDAO();
	}

	public TntVO addTnt(String tnt_email, String tnt_acc, String tnt_pwd, String tnt_id, String tnt_name,
			Date tnt_birth, Boolean tnt_sex, String tnt_mobile, String tnt_city, String tnt_dist, String tnt_add, byte[] tnt_pic) {
		//
		TntVO tntVO = new TntVO();
//		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_email(tnt_email);
		tntVO.setTnt_acc(tnt_acc);
		tntVO.setTnt_pwd(tnt_pwd);
		tntVO.setTnt_id(tnt_id);
		tntVO.setTnt_name(tnt_name);
		tntVO.setTnt_birth(tnt_birth);
		tntVO.setTnt_sex(tnt_sex);
		tntVO.setTnt_mobile(tnt_mobile);
		tntVO.setTnt_city(tnt_city);
		tntVO.setTnt_dist(tnt_dist);
		tntVO.setTnt_add(tnt_add);
		tntVO.setTnt_pic(tnt_pic);
		dao.insert_profile(tntVO);

		return tntVO;
	}
	public TntVO addTnt2(String tnt_email) {
		System.out.println(tnt_email + 1);
		//, byte[] tnt_pic
		TntVO tntVO = new TntVO();
//		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_email(tnt_email);
		System.out.println(tnt_email + 2);
		dao.insert_profile(tntVO);

		return tntVO;
	}

	public TntVO updateTntProfile(String tnt_no, String tnt_email, String tnt_acc, String tnt_pwd, String tnt_id,
			String tnt_name, Date tnt_birth, Boolean tnt_sex, String tnt_mobile, String tnt_city, String tnt_dist,
			String tnt_add, byte[] tnt_pic, int tnt_status) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_email(tnt_email);
		tntVO.setTnt_acc(tnt_acc);
		tntVO.setTnt_pwd(tnt_pwd);
		tntVO.setTnt_id(tnt_id);
		tntVO.setTnt_name(tnt_name);
		tntVO.setTnt_birth(tnt_birth);
		tntVO.setTnt_sex(tnt_sex);
		tntVO.setTnt_mobile(tnt_mobile);
		tntVO.setTnt_city(tnt_city);
		tntVO.setTnt_dist(tnt_dist);
		tntVO.setTnt_add(tnt_add);
		tntVO.setTnt_pic(tnt_pic);
		tntVO.setTnt_status(tnt_status);
		dao.update_profile(tntVO);

		return tntVO;
	}

	public TntVO getOneTntProfile(String tnt_no) {
		return dao.findByPK_profile(tnt_no);
	}

	public List<TntVO> getAllProfile() {
		return dao.getAll_profile();
	}
	
	public List<TntVO> getAllAccount() {
		return dao.getAll_account();
	}

	public TntVO updateTntPocket(int tnt_blance) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_blance(tnt_blance);
		dao.update_pocket(tntVO);
		return tntVO;
	}

	public TntVO getOneTntPocket(String tnt_no) {
		return dao.findByPK_pocket(tnt_no);
	}
	 
	public TntVO updateTntBankCard(int tnt_bank, String tnt_bankbrach, String tnt_bankacc, long tnt_card,
			int tnt_cardsvc, Date tnt_carddue) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_bank(tnt_bank);
		tntVO.setTnt_bankbrach(tnt_bankbrach);
		tntVO.setTnt_bankacc(tnt_bankacc);
		tntVO.setTnt_card(tnt_card);
		tntVO.setTnt_cardsvc(tnt_cardsvc);
		tntVO.setTnt_carddue(tnt_carddue);
		dao.update_bank_card(tntVO);

		return tntVO;
	}

	public TntVO getOneTntBankCard(String tnt_no) {
		return dao.findByPK_bank_card(tnt_no);
	}

	public TntVO updateTntCmt(int tnt_cmt_starsum, int tnt_cmt_count) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_cmt_starsum(tnt_cmt_starsum);
		tntVO.setTnt_cmt_count(tnt_cmt_count);
		dao.update_cmt(tntVO);
		return tntVO;
	}

	public TntVO getOneTntCmt(String tnt_no) {
		return dao.findByPK_cmt(tnt_no);
	}

	public TntVO updateTntVrf(byte[] tnt_id_picf, byte[] tnt_id_picb, byte[] tnt_id_pic2,
			Timestamp tnt_id_uploadtime, int tnt_id_isupload, int tnt_id_result, String tnt_id_disapprove, Timestamp tnt_id_vrftime) {

		TntVO tntVO = new TntVO();

		tntVO.setTnt_id_picf(tnt_id_picf);
		tntVO.setTnt_id_picb(tnt_id_picb);
		tntVO.setTnt_id_pic2(tnt_id_pic2);
		tntVO.setTnt_id_uploadtime(tnt_id_uploadtime);
		tntVO.setTnt_id_isupload(tnt_id_isupload);
		tntVO.setTnt_id_result(tnt_id_result);
		tntVO.setTnt_id_disapprove(tnt_id_disapprove);
		tntVO.setTnt_id_vrftime(tnt_id_vrftime);
		dao.update_vrf(tntVO);

		return tntVO;
	}

	public TntVO getOneTntVrf(String tnt_no) {
		return dao.findByPK_vrf(tnt_no);
	}

	public List<TntVO> getAllVrf() {
		return dao.getAll_vrf();
	}

	public TntVO updateTntRptAuth(
			int tnt_reported_count, int tnt_auth_chat, int tnt_auth_res, int tnt_auth_cmt, int tnt_auth_rpt) {

		TntVO tntVO = new TntVO();

		tntVO.setTnt_reported_count(tnt_reported_count);
		tntVO.setTnt_auth_chat(tnt_auth_chat);
		tntVO.setTnt_auth_res(tnt_auth_res);
		tntVO.setTnt_auth_cmt(tnt_auth_cmt);
		tntVO.setTnt_auth_rpt(tnt_auth_rpt);
		dao.update_rpt_auth(tntVO);

		return tntVO;
	}

	public TntVO getOneRptAuth(String tnt_no) {
		return dao.findByPK_rpt_auth(tnt_no);
	}


}
